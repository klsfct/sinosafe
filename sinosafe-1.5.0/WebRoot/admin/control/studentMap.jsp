<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html style="height: 100%">
   <head>
       <meta charset="utf-8">
       <link rel="stylesheet" href="../libs/css/title.css" type="text/css">
       <script type="text/javascript" src="../libs/js/jquery.js"></script>
       <script type="text/javascript" src="../libs/js/echarts/echarts-all-3.js"></script>
       <script type="text/javascript" src="../libs/js/echarts/ecStat.min.js"></script>
       <script type="text/javascript" src="../libs/js/echarts/dataTool.min.js"></script> 
       <script type="text/javascript" src="../libs/js/echarts/ak.js"></script>
       <script type="text/javascript" src="../libs/js/echarts/bmap.min.js"></script>
   </head>
   <body style="height: 100%; margin: 0">
       <div id="container" style="height: 100%"></div>
       <div class="center-title">
            <!-- <p style=" font-size: 26px;">考务安全系统监控平台(<span id="mapName"></span>)</p> -->
            <!-- <p style=" font-size: 26px;">
            	<span class="label">科目：</span>
            		<select class="subject" id="subject">
						<option value=1 selected="selected">一</option>
						<option value=2 >二</option>
						<option value=3>三</option>
						<option value=4>四</option>
					</select>
            </p> -->
          
            <p><span class="label">应到人数：</span><span id="totle1"></span></p>
             <p><span class="label">实到人数：</span><span id="totle2"></span></p>
            <p><span class="label">实到率：</span><span id="totle3"></span></p>
            <p><span class="label">通过人数：</span><span id="totle4"></span></p>
            <p><span class="label">通过率：</span><span id="totle5"></span></p>
        </div> 
        <div class="top-button">
		    	<button id="top20Btn" class="top20">实到率前20</button>
		    	<button id="bottom20Btn" class="bottom20">实到率后20</button>
		</div>  
    <script type="text/javascript">
		var dom = document.getElementById("container");
		var myChart = echarts.init(dom);
	/* 	myChart.on(ecConfig.EVENT.CLICK, eConsole);
		
		function eConsole(param) {    
		    if (typeof param.seriesIndex == 'undefined') {    
		        return;    
		    }    
		    if (param.type == 'click') {    
		        alert(param.name);    
		    }    
		} */   
		var firstFlag = true;
		option = null;
		
		//上海市散点38个考点全部数据
		var convertedData = [];
		//实到率高于60%数据
		var convertedDataHigher =[];
		//实到率低于60%数据
		var convertedDataLower =[];
		
		//生成全国前20柱状图数据 categoryData:城市名
		var categoryData = [];
		//生成全国后20柱状图数据 categoryData:城市名
		var categoryDataBottom20 = [];
		
		//生成全国前后20柱状图数据 barData：实到率
		var barData = [];	
		var barDataBottom20 = [];
		
		
		//上海市散点38个考点实到率前5
		var top5={};
		
		//上海市散点38个考点实到率前20,后20
		var top20={};
		var bottom20={};
		
		//当前显示柱状图是前20 还是后20
		var isTop20 = true;
	 	var isBottom20 = false;
	
		//初始化第一次就调用数据，以后每5秒请求数据
		if(firstFlag==true){
		    ajaxRefreshData();
		}
		setInterval(ajaxRefreshData, 2000);
		
		//柱状图点击事件
		/* myChart.on('click', function (params) {
		    // 控制台打印数据的名称
		    //console.log(params.name);
		    if(isTop20 == true){
                 return "实到人数："+top20[params.dataIndex].d3 +"  实到率：" +params.value+"%";
		    }else{
		    	 return "实到人数："+bottom20[params.dataIndex].d3 +"  实到率：" +params.value+"%";
		    }
		}); */
		$("#top20Btn").click(function(){
				option = myChart.getOption();
			 	isTop20 = true;
			    //柱状图考点名y轴
			    option.yAxis.data = categoryData;
			    ///option.yAxis[1].data = categoryDataBottom20;
			    //柱状图实到率
			    option.series[2].data =barData;
			  	//柱状图实到率后20
			   // option.series[3].data =barDataBottom20;
			    if (option && typeof option === "object") {
			        myChart.setOption(option);
			        myChart.setOption({  
                          yAxis: {  
                              data: categoryData 
                          }
                      });  
		    	}
		})
		$("#bottom20Btn").click(function(){
		        option = myChart.getOption();
				isTop20 = false;
			    //柱状图考点名y轴
			    option.yAxis.data = categoryDataBottom20;
			    ///option.yAxis[1].data = categoryDataBottom20;
			    //柱状图实到率
			    option.series[2].data =barDataBottom20;
			  	//柱状图实到率后20
			    // option.series[3].data =barDataBottom20;
			    if (option && typeof option === "object") {
			        myChart.setOption(option);
			        myChart.setOption({  
		                            yAxis: {  
		                                data: categoryDataBottom20 
		                            }
		                        });  
		    	}
		})
		function ajaxRefreshData(){
			convertedData= [];
		    categoryData = [];
		    convertedDataHigher =[];
			convertedDataLower =[];
		    categoryDataBottom20= [];
		    barData = [];
		    barDataBottom20= [];
		    top5={};
		    top20={};
		    bottom20 ={};
		    var url_list ="../resultExaminee/findMonitorByCity.action";
		    var subject =parseInt($("#subject option:selected").val());
		   	// console.log(subject);
		  	//请求树结构
            $.ajax({
        		type : "POST",
        		url : url_list,
        		cache : true,
        		data:{"subject":subject},//$("#globlisSubject").val()  
        		dataType : "json",
        		success : function(data) {
        			if (data != null) {
        				if (data.dataStatus == "1") {
        					if(data.dataMain == null){
        						alert("科目"+$("#subject option:selected").text()+"没有数据");
        						return false;
        					}
        					//更新总数
        					var allData= data.dataMain.totalMapItem;
        					//应到人数
        					$("#totle1").html(allData.d6+"人");
        					//实到人数 及实到率
        					$("#totle2").html(allData.d3+"人");
        					$("#totle3").html(allData.d2);
        					//通过人数 及通过率
        					$("#totle4").html(allData.d5+"人");
        					$("#totle5").html(allData.d4);
        					
        					//console.log(allData);
        					
        				
        					//最开始初始化数据
        					var mapItemList= data.dataMain.mapItemList;
        					 if(convertedData != null){
        						for (var i = 0; i < mapItemList.length; i++) {
         				            var geoCoord = mapItemList[i].d2;
         				            if (geoCoord) {
         				            	//实到率大于60%数据convertedDataHigher
         				            	if(parseFloat(geoCoord) >= 60){
         				            		convertedDataHigher.push({
             				                    name: mapItemList[i].name,
             				                    value: mapItemList[i].value.concat(geoCoord),
             				                    d6: mapItemList[i].d6,
             				                    d2: mapItemList[i].d2,
             				                    d3: mapItemList[i].d3,
             				                    d5: mapItemList[i].d5,
             				                    d4: mapItemList[i].d4,
             				                    linkName: mapItemList[i].linkName,
             				                    linkPhone: mapItemList[i].linkPhone
             				                });
         				            	}else{
         				            		//实到率小于60%数据convertedDataLower
         				            		convertedDataLower.push({
             				                    name: mapItemList[i].name,
             				                    value: mapItemList[i].value.concat(geoCoord),
             				                    d6: mapItemList[i].d6,
             				                    d2: mapItemList[i].d2,
             				                    d3: mapItemList[i].d3,
             				                    d5: mapItemList[i].d5,
             				                    d4: mapItemList[i].d4,
             				                    linkName: mapItemList[i].linkName,
             				                    linkPhone: mapItemList[i].linkPhone
             				                });
         				            	}
         				            	convertedData.push({
         				                    name: mapItemList[i].name,
         				                    value: mapItemList[i].value.concat(geoCoord),
         				                    d6: mapItemList[i].d6,
         				                    d2: mapItemList[i].d2,
         				                    d3: mapItemList[i].d3,
         				                    d5: mapItemList[i].d5,
         				                    d4: mapItemList[i].d4,
         				                    linkName: mapItemList[i].linkName,
         				                    linkPhone: mapItemList[i].linkPhone
         				                });
         				            }   
         				      
         				        }
        					} 

        				    //合成全部数据，前5
        				    top5 = convertedData.slice(0, 5);
        				    //前20名城市
        				    var _topLength = 20;
        				    if(_topLength>convertedData.length){
        				    	_topLength = convertedData.length;
        				    }
        				    var _iIndex = _topLength;
        				    for(var _i=0;_i<_topLength;_i++){
        				    	_iIndex--;
        				    	top20[_iIndex]= convertedData[_i];
        				    	categoryData[_iIndex]= convertedData[_i].name;
        				    	barData[_iIndex] = convertedData[_i].value[2].replace("%","");
        				    	
        				    }
        				    
        				    var _bottomStep = 20;
        				    
        				    var _bottomLength = 0; 
        				    if(_bottomStep>convertedData.length){
        				    	_bottomLength = 0;
        				    	_bottomStep = convertedData.length;
        				    }else{
        				    	_bottomLength = convertedData.length-_bottomStep;
        				    }
        				    var _jIndex = _bottomStep;
        				    for(var _j=_bottomLength;_j<convertedData.length;_j++){
        				    	_jIndex--;
        				    	bottom20[_jIndex]= convertedData[_j];
        				    	categoryDataBottom20[_jIndex]= convertedData[_j].name;
        				    	barDataBottom20[_jIndex] = convertedData[_j].value[2].replace("%","");
        				    }
        				    
        				    //top20 = convertedData.slice(-20, 20);
        				
        				    //后20名城市
        				    //bottom20 = convertedData.slice(20, 20);
        				    //获取柱状图的y轴数据考点列表以及实到率
        				    /* var top20Arr = top20.reverse();
        				    for (var i = 0; i < Math.min(top20Arr.length); i++) {
        				        categoryData.push(top20Arr[i].name);
        				        barData.push(top20Arr[i].value[2].replace("%",""));
        				    }
        				
        				  	//获取柱状图的y轴数据考点列表以及实到率
        				    var bottom20Arr = bottom20;
        				    for (var i = 0; i < Math.min(bottom20Arr.length); i++) {
        				    	categoryDataBottom20.push(bottom20Arr[i].name);
        				        barDataBottom20.push(bottom20Arr[i].value[2].replace("%",""));
        				    } */
        				    
        					/* console.log(top20.name+":"++top20.d2);
        					console.log("++++");
        					console.log(bottom20.d2); */
        				    
        				    if(firstFlag==true){
        				        option = option0();
        				        firstFlag = false;
        				    }else{
        				        option = myChart.getOption();
        				    }
        				    //热力点
        				    option.series[0].data =convertedDataHigher;
        				    //全国实到率前五 top5
        				    option.series[1].data =convertedDataLower;
        				   
        				    if(isTop20 == true){
        				    	 //柱状图考点名y轴
        				    	option.yAxis.data = categoryData;
        				    	//柱状图实到率
        					    option.series[2].data =barData;
        				    }else{
        				    	 //柱状图考点名y轴
        				    	option.yAxis.data = categoryDataBottom20;
        				    	//柱状图实到率
        				    	option.series[2].data =barDataBottom20;
        				    }
        				    
        				    if (option && typeof option === "object") {
        				        myChart.setOption(option);
        				        if(isTop20 == true){
        				        	myChart.setOption({  
        			                    yAxis: {  
        			                        data: categoryData 
        			                    }
        			                });  
        					    }else{
        					    	 //柱状图考点名y轴
        					    	myChart.setOption({  
        			                    yAxis: {  
        			                        data: categoryDataBottom20 
        			                    }
        			                });  
        					    }
        				        
        			    	} 
        					
        				} else {
        					if (data.errorMsg != null
        							&& data.errorMsg.length > 0) {
        						alert(data.errorMsg);
        					}
        				}
        			}
        		},
        		error : function(XMLHttpRequest, textStatus, errorThrown) {
        			//alert("系统异常[" + textStatus + "]:" + errorThrown);
        		}
        	});
		    
	}
   	function option0 () {
        return {
            title: {
                text: '中国政法大学研究生入学学生身份验证识别监控系统',
                left: 'center',
                textStyle: {
                    fontSize:'30'
                }

            },
           //tooltip工具提示
            tooltip: {
                trigger: 'item',//及时触发
                formatter: function (params) {
                    if(params.componentSubType=="effectScatter" || params.componentSubType=="scatter"){
                        return params.name + 
                                ' </br>应到人数：' + params.data.d6 + 
                                ' </br>实到人数：' + params.data.d3 +
                                ' </br>实到率：' + params.data.d2 +
                                ' </br>通过人数：' + params.data.d5 +
                                ' </br>通过率：' + params.data.d4+
                                ' </br>联系人：' + params.data.linkName +
                                ' </br>电话：' + params.data.linkPhone;
                    }else{
                        return "实到率："+params.value+"%";
                    }
                }
            },

            bmap: {
                left: '10',
                right: '35%',
                center: [121.84311, 31.320333],
                zoom: 11,
                roam: true,
                mapStyle: {
                    styleJson: [{
                        'featureType': 'water',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#d1d1d1'
                        }
                    }, {
                        'featureType': 'land',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#f3f3f3'
                        }
                    }, {
                        'featureType': 'railway',
                        'elementType': 'all',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'highway',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#fdfdfd'
                        }
                    }, {
                        'featureType': 'highway',
                        'elementType': 'labels',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'arterial',
                        'elementType': 'geometry',
                        'stylers': {
                            'color': '#fefefe'
                        }
                    }, {
                        'featureType': 'arterial',
                        'elementType': 'geometry.fill',
                        'stylers': {
                            'color': '#fefefe'
                        }
                    }, {
                        'featureType': 'poi',
                        'elementType': 'all',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'green',
                        'elementType': 'all',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'subway',
                        'elementType': 'all',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'manmade',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#d1d1d1'
                        }
                    }, {
                        'featureType': 'local',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#d1d1d1'
                        }
                    }, {
                        'featureType': 'arterial',
                        'elementType': 'labels',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'boundary',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#fefefe'
                        }
                    }, {
                        'featureType': 'building',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#d1d1d1'
                        }
                    }, {
                        'featureType': 'label',
                        'elementType': 'labels.text.fill',
                        'stylers': {
                            'color': '#000'//上海每个区的文字颜色 例如松江区
                        }
                    }]
                }
            },

            grid: {
                right: 40,
                top: 80,
                bottom: 40,
                width: '30%'
            },
             xAxis: {
                type: 'value',
                scale: true,
                position: 'top',
                boundaryGap: false,
                nameTextStyle: {
                    color: '#000',

                },
                min: 0,
                max: 100,
                splitLine: {show: false},
                axisLine: {show: false},
                axisTick: {show: false},
                axisLabel: {margin: 2, textStyle: {color: '#000'}},
            }, 
           	yAxis: {
                type: 'category',
                name: '总实到率（%）',
                nameGap: 16,
                nameTextStyle: {
                    color: '#000',

                },
                axisLine: {show: false, lineStyle: {color: '#000'}},
                axisTick: {show: false, lineStyle: {color: '#000'}},
                axisLabel: {interval: 0, textStyle: {color: '#000'}},
                data: categoryData
            }, 
            series : [
                {
                    name: '实到率高于60%',
                    type: 'scatter',
                    coordinateSystem: 'bmap',
                    data: convertedDataHigher,
                    symbolSize: function (val) {
                        var siecleSize =val[2].replace("%","");
                        return Math.max(siecleSize/ 5, 8);
                    },
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#d63131'//实到率高于60%的圆圈颜色  红色
                        }
                    }
                },
                {
                    name: '实到率低于60%',
                    type: 'scatter',
                    coordinateSystem: 'bmap',
                    data: convertedDataLower,
                    symbolSize: function (val) {
                        var siecleSize =val[2].replace("%","");
                        return Math.max(siecleSize/ 5, 8);
                    },
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#198e68'//实到率低于60%的圆圈颜色  绿色
                        }
                    }
                },
                {
                    id: 'bar',
                    zlevel: 2,
                    type: 'bar',
                    symbol: 'none',
                    label: {
                        normal: {
                            formatter: function (params) {
                                //console.log(params.name+"----");
                                //柱状图显示总人数和实到率
                            	if(isTop20 == true){
                                    return "实到人数："+top20[params.dataIndex].d3 +"  实到率：" +params.value+"%";
           				    	}else{
           				    	 	return "实到人数："+bottom20[params.dataIndex].d3 +"  实到率：" +params.value+"%";
           				    	}

                            },//鼠标放上去显示字 模板变量有 {a}、{b}、{c}，分别表示系列名，数据名，数据值。
                            position: 'insideLeft',
                            show: true
                        }
                    },
                    itemStyle: {
                    	 normal: {
                             color: function(params) {
								//柱状图颜色
								if(isTop20 == true){
                                    if(top20[params.dataIndex].d2 >= 60){
                                    	return '#d63131';//柱状图实到率大于60%  设置红颜色红色
                                    }else{
                                    	return '#198e68';//柱状图实到率小于60%  设置红颜色绿色
                                    }
           				    	}else{
	           				    	if(bottom20[params.dataIndex].d2 >= 60){
	                                 	return '#d63131';//柱状图实到率大于60%  设置红颜色红色
	                                }else{
	                                 	return '#198e68';//柱状图实到率小于60%  设置红颜色绿色
	                                }
           				    	}

							}
                        }
                    },
                    data: barData
                }
            ]
        };
   }
       </script>
   </body>
</html>