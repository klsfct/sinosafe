$(function(){
    //签到人数 未签到人数
    var onNum=0;
    var offNum=0;
    var dataOn=[];
    var dataOff=[];
    var markerArr=[];

   
    //全部数据
    
    // -======================================================== 初始地图start
    var search = $(".manageControl");
	// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例
	map.centerAndZoom("上海",12);  // 初始化地图,设置中心点坐标和地图级别
	map.setCurrentCity("上海");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	map.enableInertialDragging(); 		// 开启惯性拖拽效果
	map.enableContinuousZoom();			// 开启连续缩放效果
	// 添加缩放比例 anchor:控件停靠位置  type：控件类型
	var opts = {anchor: BMAP_ANCHOR_BOTTOM_RIGHT, type: BMAP_NAVIGATION_CONTROL_LARGE}    
	map.addControl(new BMap.NavigationControl(opts));
	// 添加城市列表控件
	var size = new BMap.Size(15, 25);
	map.addControl(new BMap.CityListControl({
			anchor: BMAP_ANCHOR_TOP_RIGHT,
			offset: size,
			// 切换城市之间事件
			// onChangeBefore: function(){
			//    alert('before');
			// },
			// 切换城市之后事件
			// onChangeAfter:function(){
			//   alert('after');
			// }
	}));
	// 获取服务器端数据
	
	var webUrl = window.location.host;  //域名
	var url_list = "http://" + webUrl + "/SHSFKS/baidu/getLinkManByLoginArea.action";  //服务端获取数据地址
	
		
		$(".trackControl").append(search);
		// 添加标注  
		function addMarker(point, index, title) {  
			var myIcon = new BMap.Icon("http://api.map.baidu.com/img/markers.png",  
				new BMap.Size(23, 25), {  
					offset: new BMap.Size(10, 25),  
					imageOffset: new BMap.Size(0, 0 - index * 25)  
				});  
			
			var marker = new BMap.Marker(point); 
				
			map.addOverlay(marker);  
			var label = new window.BMap.Label(title, { offset: new window.BMap.Size(20, -10) });  //创建标签
			marker.setLabel(label);  //设置标注标签	 	
			return marker;  
		}  
			// 添加信息窗口  
			function addInfoWindow(marker, poi) {  
				//pop弹窗标题  
				var title = '<div style="font-weight:bold;color:#CE5521;font-size:14px">' + poi.name + '</div>';  
				//pop弹窗信息  
				var html = [];  
				html.push('<table cellspacing="0" style="table-layout:fixed;width:100%;font:12px arial,simsun,sans-serif"><tbody>');  
				html.push('<tr>');  
				html.push('<td style="vertical-align:top;line-height:20px;width:55px;white-space:nowrap;word-break:keep-all">签到时间：</td>');  
				html.push('<td style="vertical-align:top;line-height:20px">' + poi.lastLoginDate + ' </td>'); 
				html.push('</tr>');
				html.push('<tr>'); 
				html.push('<td style="vertical-align:top;line-height:20px;width:55px;white-space:nowrap;word-break:keep-all">联系电话： </td>');  
				html.push('<td style="vertical-align:top;line-height:20px">' + poi.phone + ' </td>');			
				html.push('</tr>');  
				html.push('</tbody></table>');  
				var infoWindow = new BMap.InfoWindow(html.join(""), { title: title, width: 200 });  

				var openInfoWinFun = function () {  
					marker.openInfoWindow(infoWindow);  
				};  
				marker.addEventListener("click", openInfoWinFun);  
				return openInfoWinFun;  
			}  
    // 初始地图=====================================================================================end
			
    //初始化数据
    function init(){
    	//console.log(makerArr);
    	$.ajax({
    	    type : "POST",
    	    url : url_list,
    	    cache : false,
    	    dataType:"jsonp",  
    		jsonpCallback:'handler',   //回调函数  
    		jsonp:'callback',  
    	    success : function(data) {
    			if (data != null) {
    				if (data.dataStatus == "1") {
    					markerArr = data.dataMain;
    					var listAllName ="";
    					var listOnName = "";
    					var listOffName = "";
    					  for(i=0;i<markerArr.length;i++){
    						  	var p0 = markerArr[i].lastLoginLng;
	      						var p1 = markerArr[i].lastLoginLat;  
	      						var maker = addMarker(new window.BMap.Point(p0, p1), i,markerArr[i].name);  
	      						addInfoWindow(maker, markerArr[i], i); 
		      					if(markerArr[i].lastLoginDate == null){
		      						markerArr[i].lastLoginDate = "未签到";
		      					}
    				            listAllName += '<div class="monitorListItem1">'+
    				                          '  <div class="monitorListItemName">'+
    				                          '    <abbr title='+markerArr[i].phone+'>'+markerArr[i].name+'</abbr>'+
    				                          '  </div>'+
    				                          '  <div class="monitorListItemSpeed"> '+markerArr[i].lastLoginDate+'</div>'
    				                          '  </div>';

    				            if(markerArr[i].signStatus == "on"){
    				                onNum++;
    				                listOnName += '<div class="monitorListItem1">'+
    				                              '  <div class="monitorListItemName">'+
    				                              '    <abbr title='+markerArr[i].phone+'>'+markerArr[i].name+'</abbr>'+
    				                              '  </div>'+
    				                              '  <div class="monitorListItemSpeed"> '+markerArr[i].lastLoginDate+'</div>'
    				                              '  </div>';
    				                dataOn.push({
    				                    name: markerArr[i].name,
    				                    date: markerArr[i].lastLoginDate,
    				                    state: markerArr[i].signStatus
    				                });
    				            }else{
    				                offNum++;
    				               listOffName += '<div class="monitorListItem1">'+
    				                              '  <div class="monitorListItemName">'+
    				                              '    <abbr title='+markerArr[i].phone+'>'+markerArr[i].name+'</abbr>'+
    				                              '  </div>'+
    				                              '  <div class="monitorListItemSpeed"> '+markerArr[i].lastLoginDate+'</div>'
    				                              '  </div>';
    				                dataOff.push({
    				                	 name: markerArr[i].name,
    				                     date: markerArr[i].lastLoginDate,
    				                     state: markerArr[i].signStatus
    				                });
    				            }

    				        }

				            $(".monitorAllContent").find(".monitorFrame").append(listAllName);
				            $(".monitorOnlineContent").find(".monitorFrame").append(listOnName);
				            $(".monitorOfflineContent").find(".monitorFrame").append(listOffName);
				            //alert(markerArr.length,onNum,offNum);
				            $("#allNum").html(markerArr.length);
				            $("#onlineNum").html(onNum);
				            $("#offlineNum").html(offNum);
    					
    				} else {
    					if (data.errorMsg != null
    							&& data.errorMsg.length > 0) {
    						alert(data.errorMsg);
    					}
    				}
    			}
    		},
    		error : function(XMLHttpRequest, textStatus,
    				errorThrown) {
    			alert("系统异常[" + textStatus + "]:" + errorThrown);
    		}
    	});
      
    }
    init();
    //蓝色头部上下箭头点击出现隐藏
    $(".toggleInManage").click(function(){
        if($("#manageBottom").css("display") == "block"){
            $("#manageBottom").css("display","none");
        }else{
            $("#manageBottom").css("display","block");
        }
    })
    //input监听输入字发送请求
    /*$(".searchInputMonitor").bind('input propertychange', function(){
        search();           
    })*/

    document.onkeydown=function(event){
      var e = event || window.event || arguments.callee.caller.arguments[0];
         // 回车 键
         if(e && e.keyCode==13){ 
        	 searchAll(); 
        }
    }; 
    //搜索按钮
    $(".searchBtnMonitor").click(function(){
    	searchAll();
    })
    //点击全部按钮出现全部页面 
    $(".monitorAll").click(function(){
        if($(".monitorAllContent").attr("class") == "monitorAllContent hidden"){
            $(this).addClass("boderBottom");
            $(".monitorOnline").removeClass("boderBottom");
            $(".monitorOffline").removeClass("boderBottom");
            $(".monitorAllContent").attr("class","monitorAllContent visible");
            $(".monitorOnlineContent").attr("class","monitorOnlineContent hidden");
            $(".monitorOfflineContent").attr("class","monitorOfflineContent hidden");
        }
    })
    //点击已签到按钮出现已签到页面 
    $(".monitorOnline").click(function(){
        if($(".monitorOnlineContent").attr("class") == "monitorOnlineContent hidden"){
            $(this).addClass("boderBottom");
            $(".monitorAll").removeClass("boderBottom");
            $(".monitorOffline").removeClass("boderBottom");
            $(".monitorOnlineContent").attr("class","monitorOnlineContent visible");
            $(".monitorAllContent").attr("class","monitorAllContent hidden");
            $(".monitorOfflineContent").attr("class","monitorOfflineContent hidden");
        }
    })
    //点击未签到按钮出现未签到页面 
    $(".monitorOffline").click(function(){
        if($(".monitorOfflineContent").attr("class") == "monitorOfflineContent hidden"){
            $(this).addClass("boderBottom");
            $(".monitorAll").removeClass("boderBottom");
            $(".monitorOnline").removeClass("boderBottom");
            $(".monitorOfflineContent").attr("class","monitorOfflineContent visible");
            $(".monitorAllContent").attr("class","monitorAllContent hidden");
            $(".monitorOnlineContent").attr("class","monitorOnlineContent hidden");
        }
    })
   function searchAll(){
    	//console.log("111");
    	 var url_list = "../../data.action"
    	        var searchParam = $(".searchInputMonitor").val();
    	        console.log(searchParam);
    	        if($(".monitorAllContent").css("visibility") == "visible"){
    	          $(".monitorAllContent").find(".monitorListItem1").remove();

    	          for(i=0;i<markerArr.length;i++){  
    	            if(markerArr[i].name.indexOf(searchParam) != -1){
    	              var listOneName= '<div class="monitorListItem1">'+
    	                            '  <div class="monitorListItemName">'+
    	                            '    <abbr title='+markerArr[i].phone+'>'+markerArr[i].name+'</abbr>'+
    	                            '  </div>'+
    	                            '  <div class="monitorListItemSpeed"> '+markerArr[i].lastLoginDate+'</div>'
    	                            '  </div>';
    	              $(".monitorAllContent").find(".monitorFrame").append(listOneName);
    	            }    
    	          }

    	        }else if($(".monitorOnlineContent").css("visibility") == "visible"){
    	          $(".monitorOnlineContent").find(".monitorListItem1").remove();

    	            for(i=0;i<dataOn.length;i++){  
    	              if(dataOn[i].name.indexOf(searchParam) != -1){
    	                var listOneName= '<div class="monitorListItem1">'+
    	                              '  <div class="monitorListItemName">'+
    	                              '    <abbr title='+markerArr[i].phone+'>'+dataOn[i].name+'</abbr>'+
    	                              '  </div>'+
    	                              '  <div class="monitorListItemSpeed"> '+dataOn[i].date+'</div>'
    	                              '  </div>';
    	                $(".monitorOnlineContent").find(".monitorFrame").append(listOneName);
    	              }    
    	            }
    	        }else{
    	          $(".monitorOfflineContent").find(".monitorListItem1").remove();

    	            for(i=0;i<dataOff.length;i++){  
    	              if(dataOff[i].name.indexOf(searchParam) != -1){
    	                var listOneName= '<div class="monitorListItem1">'+
    	                              '  <div class="monitorListItemName">'+
    	                              '    <abbr title='+markerArr[i].phone+'>'+dataOff[i].name+'</abbr>'+
    	                              '  </div>'+
    	                              '  <div class="monitorListItemSpeed"> '+dataOff[i].date+'</div>'
    	                              '  </div>';
    	                $(".monitorOfflineContent").find(".monitorFrame").append(listOneName);
    	              }    
    	            }
    	        }
    }
    //搜索数据请求方法
    function search(){

        /*     
        $.ajax({
            type : "POST",
            url : url_list,
            data: searchParam,
            cache : true,
            dataType : "json",
            success : function(data) {
                if (data != null) {
                    if (data.dataStatus == "1") {
                        
                    } else {
                        if (data.errorMsg != null
                                && data.errorMsg.length > 0) {
                            alert(data.errorMsg);
                        }
                    }
                }
            },
            
        });*/
    }
})