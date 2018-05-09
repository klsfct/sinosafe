/** 
 * question.js 
 * 试题相关方法 
 */ 

jQuery.extend({
   //验证表单内容为空 非空true,空false
   question_checkFormEmpty :function (formId){
   	var _tempFlag = false;
   	$("#"+formId + " input:text,textarea").each(function(){
   		if($.trim($(this).val()).length>0){
   			_tempFlag = true;
   			return false;
   		}
   	});
   	$("#"+formId + " input:radio,input:checkbox").each(function(){
   		//难度有默认值，不校验
   		if($(this).attr("name").indexOf("difficulty")>=0){
   			return true ;
   		}
		if($(this).is(":checked")){
   			_tempFlag = true;
   			return false;
   		}
   	});
   	return _tempFlag;
   },
   //题干是否完整  全部填写true,未完全填写false
   question_checkQuestionMain :function (formId) {
		var _tempFlag = true;
		$("#"+formId + " .validation-main").each(function(){
			if($.trim($(this).val()).length<1){
				_tempFlag = false;
				return false;
			}
		});
		return _tempFlag;
	},
	//修改子题删除
	question_removeChildren :function (obj){
		var _btnObj = $(obj);
		var _childDiv = _btnObj.parents(".question-input-children-box");
		var _childNum = $.question_getChildrenNum();
		if(_childNum<2){
			alert("无法删除，至少保留一道子题！");
		}else{
			if(confirm("确认删除试题信息？")) {
				_childDiv.remove();
				$.question_changeChildrenIndex();
				//重新计算字数
				var _fontInputNum = $.question_getFontNum();
				$("#font-Num").html(_fontInputNum+"字");
			}
		}
	},
	//录入子题顺序下移
	question_downChildren :function (obj){
		var _btnObj = $(obj);
		var _childDiv = _btnObj.parents(".question-input-children-box");
		var _downChildren = _childDiv.next(".question-input-children-box");
		if(_downChildren.length>0){
			_downChildren.after(_childDiv);
			$.question_changeChildrenIndex();
			
			_childDiv.find("textarea:eq(0)").focus();
		}else{
			alert("已经是最后一题了!");
		}
	},
	//录入子题顺序上移
	question_upChildren :function (obj){
		var _btnObj = $(obj);
		var _childDiv = _btnObj.parents(".question-input-children-box");
		var _upChildren = _childDiv.prev(".question-input-children-box");
		if(_upChildren.length>0){
			_upChildren.before(_childDiv);
			$.question_changeChildrenIndex();
			
			_childDiv.find("textarea:eq(0)").focus();
		}else{
			alert("已经是第一题了!");
		}
	},
	//更新录入子题序号
	question_changeChildrenIndex :function (){
		$(".question-input-children-box").each(function(i){
			var _order = i+1;
			$(this).find(".question-order-text").html("子题"+_order);
			$(this).find(".question-order").val(i);
		});
		
		var _childNum = $.question_getChildrenNum();
		$("#children-Num").html(_childNum+"题");
	},
	//计算字数
	question_getFontNum :function (){
		var _fontInputNum = 0;
		$(".input-font-num").each(function(){
			_fontInputNum+=$(this).val().length;
		});
		return _fontInputNum;
	},
	//计算子题数
	question_getChildrenNum:function (){
		var _childNum = 0;
		_childNum = $(".question-input-children-box").length;
		return _childNum;
	},
	//计算分数
	question_getPointNum:function(){
		var _pointNum = 0;
		$(".input-point").each(function(){
			_pointNum+=parseInt($(this).val());
		});
		return _pointNum;
	},
	//知识点点击事件
	question_initSubjectDialog :function (inputObj,isMulti,url,paramName){
		var _tempInput = inputObj;
		var _tempHidden = inputObj.parent().children(".subject-textarea-hidden");
		var _subjectId = _tempHidden.val();
		url+="?checkType="+isMulti;
		if(_subjectId.length>0){
			url+="&"+paramName+"="+_subjectId;
		}
		subjectDialog = new top.Dialog();
		subjectDialog.Title = "知识点选择";
		subjectDialog.URL = url;
		subjectDialog.Width=600;
		subjectDialog.Height=400;
		subjectDialog.ID="subjectDialog",
		subjectDialog.ShowButtonRow=true;
		subjectDialog.OkButtonText="保存";
		subjectDialog.OKEvent = function(){
			var _contentWindow = subjectDialog.innerFrame.contentWindow;
			var _checkTextData = _contentWindow.getCheckTextData();
			var _checkData = _contentWindow.getCheckData();
			_tempInput.val(_checkTextData);
			_tempHidden.val(_checkData);
			_tempInput.autosizeUpdate();
			_tempInput.focus();
			subjectDialog.close();
		};
		subjectDialog.show();
	},//知识点点击事件
	question_initSubjectInputDialog :function (inputObj,isMulti,url,paramName){
		var _tempInput = inputObj;
		var _tempHidden = inputObj.parent().children(".subject-textarea-hidden");
		var _subjectId = _tempHidden.val();
		url+="?checkType="+isMulti;
		if(_subjectId.length>0){
			url+="&"+paramName+"="+_subjectId;
		}
		subjectDialog = new top.Dialog();
		subjectDialog.Title = "知识点选择";
		subjectDialog.URL = url;
		subjectDialog.Width=600;
		subjectDialog.Height=400;
		subjectDialog.ID="subjectDialog",
		subjectDialog.ShowButtonRow=true;
		subjectDialog.OkButtonText="保存";
		subjectDialog.OKEvent = function(){
			var _contentWindow = subjectDialog.innerFrame.contentWindow;
			var _checkTextData = _contentWindow.getCheckTextData();
			var _checkData = _contentWindow.getCheckData();
			_tempInput.val(_checkTextData);
			_tempInput.attr("title",_checkTextData);
			addTooltip(_tempInput[0]);
			_tempHidden.val(_checkData);
			_tempInput.focus();
			subjectDialog.close();
		};
		subjectDialog.show();
	},
	//初始化复选框
	question_initCheckbox:function (self){
        var label = self.next();
        var label_text = label.text();
        label.remove();
        self.iCheck({
            checkboxClass: "icheckbox_sm-blue",
            radioClass: "radio_sm-blue",
            insert: label_text
        });
	}
	
 }); 


