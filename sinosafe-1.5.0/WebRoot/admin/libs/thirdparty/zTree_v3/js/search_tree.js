/**
	 * 展开树
	 * @param treeId  
	 */
    function expand_ztree(treeId){
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        treeObj.expandAll(true);
    }
    
    /**
	 * 收起树：只展开根节点下的一级节点
	 * @param treeId
	 */
    function close_ztree(treeId){
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        treeObj.expandAll(false);
        var nodes = treeObj.transformToArray(treeObj.getNodes());
        var nodeLength = nodes.length;
        for (var i = 0; i < nodeLength; i++) {
            if (nodes[i].pId == null || nodes[i].pId == '0') {
	            //根节点：展开
	            treeObj.expandNode(nodes[i], true, false, false);
            }
        }
    }
    
    /**
     * 搜索树，高亮显示并展示【模糊匹配搜索条件的节点s】
     * @param treeId
	 * @param searchConditionId 文本框的id
     */
	function search_ztree(treeId, searchConditionObj){
		searchByFlag_ztree(treeId, searchConditionObj, "");
	}
    
    /**
     * 搜索树，高亮显示并展示【模糊匹配搜索条件的节点s】
     * @param treeId
     * @param searchConditionId		搜索条件Id
     * @param flag 					需要高亮显示的节点标识
     */
	function searchByFlag_ztree(treeId, searchConditionObj, flag){
		//<1>.搜索条件
		var searchCondition = searchConditionObj.val();
		//<2>.得到模糊匹配搜索条件的节点数组集合
		var highlightNodes = new Array();
		if (searchCondition != "") {
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			highlightNodes = treeObj.getNodesByParamFuzzy("name", searchCondition, null);
		}
		//<3>.高亮显示并展示【指定节点s】
		highlightAndExpand_ztree(treeId, highlightNodes, flag);
	}
	
	
	 /**
	 * 先把全部节点更新为普通样式
	 * @param treeId
	 */
    function remove_highlight_ztree(treeId){
    	var treeObj = $.fn.zTree.getZTreeObj(treeId);
		//<1>. 先把全部节点更新为普通样式
    	
		var treeNodes = treeObj.transformToArray(treeObj.getNodes());
		for (var i = 0; i < treeNodes.length; i++) {
			treeNodes[i].highlight = false;
			treeObj.updateNode(treeNodes[i]);
		}
    }
    
	
	/**
	 * 高亮显示并展示【指定节点s】
	 * @param treeId
	 * @param highlightNodes 需要高亮显示的节点数组
	 * @param flag			 需要高亮显示的节点标识
	 */
	function highlightAndExpand_ztree(treeId, highlightNodes, flag){
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		if (highlightNodes != null) {
			//<1>. 先把全部节点更新为普通样式
			remove_highlight_ztree(treeId);
			if(highlightNodes.length>0 && highlightNodes.length<30){
				//<2>.收起树, 只展开根节点下的一级节点
				//close_ztree(treeId);
				//<3>.把指定节点的样式更新为高亮显示，并展开
				for (var i = 0; i < highlightNodes.length; i++) {
					if (flag != null && flag != "") {
						if (highlightNodes[i].flag == flag) {
							//高亮显示节点，并展开
							highlightNodes[i].highlight = true;
							treeObj.updateNode(highlightNodes[i]);
							//高亮显示节点的父节点的父节点....直到根节点，并展示
							var parentNode = highlightNodes[i].getParentNode();
							var parentNodes = getParentNodes_ztree(treeId, parentNode);
							treeObj.expandNode(parentNodes, true, false, false);
							treeObj.expandNode(parentNode, true, false, false);
						}
					} else {
						//高亮显示节点，并展开
						highlightNodes[i].highlight = true;
						treeObj.updateNode(highlightNodes[i]);
						//高亮显示节点的父节点的父节点....直到根节点，并展示
						var parentNode = highlightNodes[i].getParentNode();
						var parentNodes = getParentNodes_ztree(treeId, parentNode);
						treeObj.expandNode(parentNodes, true, false, false);
						treeObj.expandNode(parentNode, true, false, false);
					}
				}
				//滚动
				var _tempNode = highlightNodes[0];
				if(null!=_tempNode && _tempNode.tId!=""){
					var _tempNodeObj = $("#"+_tempNode.tId);
					if(null!=_tempNodeObj){
						var _tempPosition = $("#"+_tempNode.tId).position();
						var _tempTop = _tempPosition.top;
						var _tempScrollTop = $("#"+treeId).scrollTop();
						var _tempScroll =  _tempScrollTop+_tempTop-80;
						if(_tempScroll<0){
							_tempScroll = 0;
						}
						$("#"+treeId).animate({scrollTop:_tempScroll},500);
					}
				}
			}
			
		}
	}
	
	/**
	 * 递归得到指定节点的父节点的父节点....直到根节点
	 */
	function getParentNodes_ztree(treeId, node){
		if (node != null) {
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			var parentNode = node.getParentNode();
			return getParentNodes_ztree(treeId, parentNode);
		} else {
			return node;
		}
	}
	
	/**
	 * 设置树节点字体样式
	 */
	function setFontCss_ztree(treeId, treeNode) {
		if (treeNode.id == 0) {
			//根节点
			return {color:"#333333", "font-weight":"bold"};
		} else if (treeNode.isParent == false){
			//叶子节点
			return (!!treeNode.highlight) ? {color:"#0e62c7", "font-weight":"bold"} : {color:"#333333", "font-weight":"normal"};
		} else {
			//父节点
			return (!!treeNode.highlight) ? {color:"#0e62c7", "font-weight":"bold"} : {color:"#333333", "font-weight":"normal"};
		}
	}
	
	function showTreeDrop(inputObj,treeId) {
		//remove_highlight_ztree(treeId);
		//close_ztree(treeId);
		$("#"+treeId).prev(".tree-search").children(".tree-search-input").val("");
		var cityOffset = inputObj.offset();
		$("#"+treeId).parent(".tree-drop").css({left:cityOffset.left + "px", top:cityOffset.top + inputObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", function(event){
			if (!($(event.target).is(".tree-drop") || $(event.target).parents(".tree-drop").length>0)) {
				hideTreeDrop(treeId);
			}
		});
	}
	
	function hideTreeDrop(treeId) {
		$("#"+treeId).parent(".tree-drop").fadeOut("fast");
		$("body").unbind("mousedown");
	}
	
	function treeSearchKeyUp(event){
		search_ztree($(this).parent(".tree-search").next(".ztree").attr("id"), $(this));
	}
	
