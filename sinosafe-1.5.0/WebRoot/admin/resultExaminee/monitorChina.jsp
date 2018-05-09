<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>全国监控平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css">
    	body{
    		background: #1b1b1b;
    	}
    	.center-title {
    		position: absolute;
    		top: 0;
    		width:100%;
    		text-align: center;
    		color: #fff;
    		font-size: 26px;
    		font-weight: bold;
    		padding: 10px 0 0 0 ;
    	}
    	.center-title p{
    	margin: 0;
    	padding: 0;
    	line-height: 1.5em;
    	}
    	.top-button {
    		position: absolute;
    		top: 0;
    		left:10px;
    		width:200px;
    		color: #fff;
    		font-size: 22px;
    		font-weight: bold;
    		padding: 10px 0 0 0 ;
    		z-index: 9999;
    	}
    </style>
</head>
<body>
	<div class="top-button"><s:select list="#{1:'===科目一===',2:'===科目二===',3:'===科目三===',4:'===科目四==='}" name="subject" id="globlisSubject" cssStyle="font-size: 15px;"
									listKey="key" listValue="value"></s:select><a href="javascript:sortCharts();">排序</a></div>
	<div class="center-title">
		<p>考务安全系统监控平台</p>
		<p>安检总人数：<span id="totle2"></span>人 </p><p>总参考率：<span id="totle1"></span></p>
		<p>通过总人数：<span id="totle4"></span>人  </p><p>总通过率：<span id="totle3"></span></p>
	</div>
    <div id="g0" style="height:730px"></div>
    <script src="../libs/js/echarts/jquery.js"></script>
    <script src="../libs/js/echarts/echarts.js"></script>
    <script type="text/javascript">
    require.config({
        paths: {
            echarts: '../libs/js/echarts'
        }
    });
    var myChart0;
    var timeTicket;
    var data_url1 = "monitorByArea.action";//地区数据统计
    var data_url2 = "gotoMonitorByProvince.action";//跳转至下级界面
    require(
        [
            'echarts',
            'echarts/dark',
            'echarts/config',
            'echarts/chart/line',
            'echarts/chart/bar',
            'echarts/chart/scatter',
            //'echarts/chart/k',
            'echarts/chart/pie',
            'echarts/chart/radar',
            //'echarts/chart/force',
            //'echarts/chart/chord',
            'echarts/chart/map'
            
        ],
        function (ec,theme,config) {
            myChart0 = ec.init(document.getElementById('g0'),theme);
            ajaxRefreshData(myChart0,true,false);
            timeTicket = setInterval(function (){
            	ajaxRefreshData(myChart0,false,false);
    		}, 2000);
    		//点击跳转至下级界面
            myChart0.on(config.EVENT.CLICK, openChildPage);
        }
    );

    function openChildPage(param) {
    	console.log(param);
    	if(param){
    		var _areaId = param.data.d1;
    		window.open(data_url2+"?subject="+$("#globlisSubject").val()+"&areaId="+_areaId);
    	}
    }

    
    //排序
    function sortCharts(){
    	if(myChart0){
    		ajaxRefreshData(myChart0,false,true);
    	}
    }

    //更新实时数据
    function ajaxRefreshData(chart,firstFlag,sortFlag){
    	if(!chart){
    	 	  clearInterval(timeTicket);
    	      return;
    	 }
    	//异步获取数据
    	 $.ajax({
    	   type: "POST",
    	   url: data_url1,
    	   data: {"subject":$("#globlisSubject").val()},
    	   async : true,
    	   dataType : "json",
    	   success : function(data) {
    			if (data != null) {
    				if (data.dataStatus == "1") {
    					//更新总数
    					var _totalData = data.dataMain.totalMapItem;
    					$("#totle1").html(_totalData.value);
    					$("#totle2").html(_totalData.d2);
    					$("#totle3").html(_totalData.d3);
    					$("#totle4").html(_totalData.d4);
    					
    					//更新地图
    					var _chartData = data.dataMain.mapItemList;
    					var _option;
    					if(firstFlag==true){
    					 	_option = option0();
    					}else{
    						_option = chart.getOption();
    					}
    					//进场率前15的省份
    					var _yAxis1_data = [];
    					//进场率后15的省份
    					var _yAxis2_data = [];
    					if(sortFlag==true){
    						_yAxis1_data = [];
    						_yAxis2_data = [];
    						$.each(_chartData,function(index,item) {
    							if(item.name!="河南"){
    								if(_yAxis2_data.length<15){
    									console.info("_yAxis2_data:---"+_yAxis2_data.length+"--item.name---"+item.name);
    									_yAxis2_data.push(item.name);
    								}else{
    									console.info("_yAxis1_data:---"+_yAxis1_data.length+"--item.name---"+item.name);
    									_yAxis1_data.push(item.name);
    								}
    							}
    						});
    						
    						_option.yAxis[0].data = _yAxis1_data;
    						_option.yAxis[1].data = _yAxis2_data;
    					}else{
    						_yAxis1_data = _option.yAxis[0].data;
    						_yAxis2_data = _option.yAxis[1].data;
    					}
    					
    					var _series0_data = _chartData;
    					var _series1_data = [];
    					var _series2_data = [];
    					$.each(_yAxis1_data,function(index1,arrVal1) {
    						$.each(_chartData,function(index,item) {
    							if(arrVal1==item.name){
    								_series1_data.push(item);
    							}
    						});
    					});
    					
    					$.each(_yAxis2_data,function(index2,arrVal2) {
    						$.each(_chartData,function(index,item) {
    							if(arrVal2==item.name){
    								var _tempVal = item.value;
    								_series2_data.push({"name":item.name,"value":-_tempVal});
    							}
    						});
    					});
    					_option.series[0].data =_series0_data;
    					_option.series[1].data =_series1_data;
    					_option.series[2].data =_series2_data;
						/* if(sortFlag==true){
							chart.clear();
    	    			} */
    	    			chart.setOption(_option); 
    	    			
    				} else {
    					if (data.errorMsg != null
    							&& data.errorMsg.length > 0) {
    						console.info(data.errorMsg);
    					}
    				}
    			}
    		},
    		error : function(XMLHttpRequest, textStatus, errorThrown) {
    			console.info("系统异常[" + textStatus + "]:" + errorThrown);
    		}
    	});
    }
    
    
    function option0 () {
        return {
            title : {
            	show:false,
                text: '国际司法考试考务安全系统监控平台',
                subtext: '',
                x:'center',
                y: 'top'
            },
            animation : false,
            tooltip : {
                trigger: 'item'
            },
            dataRange: {
            	//show:true,
               // min: 1,
                //max: 100,
                //orient: 'horizontal',
                color:['green','#FFFF66','lightskyblue','#cccccc'],
                //text:['高','低'],           // 文本，默认为数值文本
                //calculable : true,
                x: 430,
                y: 'bottom',
    	        splitList: [
    	            {start: 65, end: 100},
    	            {start: 40, end: 65},
    	            {start: 15, end: 40},
    	            {start: 15,end: 1}
    	        ],
                formatter : function(v, v2){
    	            return '参考率：'+v+'%-'+v2+'%';
            	},
                textStyle: {
                	fontSize:14,
                    color: '#fff'
                }
            },
            toolbox: {
                show : false,
                //orient : 'vertical',
                x: 'right',
                y: 'top',
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            grid:{
                x: 0,
                y: 50,
                x2: 0,
                y2: 0,
                borderWidth:0
            },
            xAxis : [
                 {
                 	name:'进场率前15的省份',
                    type : 'value',
                    position: 'top',
                    splitLine: {show:false},
                    axisLine: {show:false},
                    axisLabel : {show:false},
                    min: 0,
                    max: 500
                },
                {
                	name:'进场率后15的省份',
                    type : 'value',
                    position: 'bottom',
                    splitLine: {show:false},
                    axisLine: {show:false},
                    min: -500,
                    max: 0,
                    axisLabel:{
                        show:false,
                        formatter: function (v){
                            return -v;
                        }
                    }
                }
            ],
            yAxis : [
                {
                   type : 'category',
                    splitLine: {show:false},
                    axisLine: {show:false},
                    axisLabel : {show:false},
                    data : ['山东','江西','福建','安徽','浙江','江苏','上海','黑龙江','吉林','辽宁','内蒙古','山西','河北','北京']
                
                
                },
                {
                    type : 'category',
                    splitLine: {show:false},
                    axisLine: {show:false},
                    axisLabel : {show:false},
                    data : ['新疆','宁夏','青海','甘肃','陕西','西藏','云南','贵州','四川','重庆','海南','广西','广东','湖南','湖北']
                }
            ],
            /*roamController: {
    	        show: false,
    	        x: 'right',
    	        mapTypeControl: {
    	            'china': true
    	        }
    	    },*/
            series : [
                {
                    name: '进场率',
                    type: 'map',
                    mapType: 'china',
                    tooltip:{
                        trigger: 'item',
                        formatter : function (v) {
                           return v.data.name + '<br/>'
                                   + '参考率 : ' + ((v.data.value == undefined)?'0':v.data.value) + '%<br/>'
                                   + '参考人数: ' + ((v.data.d2 == undefined)?'0':v.data.d2) + '<br/>'
                                   + '通过率 : ' + ((v.data.d3 == undefined)?'0':v.data.d3) + '%<br/>'
                                   + '通过人数: ' + ((v.data.d4 == undefined)?'0':v.data.d4);
                        }
                    },
    	            itemStyle:{
    	                normal:{label : {
                                show : true,
                                textStyle: {fontSize:16,color:'#000'}
                            }},
    	                emphasis:{label:{show:true}}
    	            },
                    mapLocation: {
                        x: 'center',
                        y: 80
                    },
                    //roam:true,
                    //scaleLimit:{max:2, min:1},
                    data:[]
                },
                {
                    name: '参考率前15',
                   type: 'bar',
                   tooltip:{
                        trigger: 'item',
                        formatter : function (v) {
                           return v.data.name + '<br/>'
                                   + '参考率 : ' + ((v.data.value == undefined)?'0':v.data.value) + '%<br/>'
                                   + '参考人数: ' + ((v.data.d2 == undefined)?'0':v.data.d2) + '<br/>'
                                   + '通过率 : ' + ((v.data.d3 == undefined)?'0':v.data.d3) + '%<br/>'
                                   + '通过人数: ' + ((v.data.d4 == undefined)?'0':v.data.d4);
                        }
                    },
                    itemStyle : {
                        normal : {
                            color : (function (){
                                var zrColor = require('zrender/tool/color');
                                return zrColor.getLinearGradient(
                                    0, 80, 0, 700,
                                   // ['orangered','yellow','lightskyblue']
                                    [[0, 'green'],[1, 'yellow']]
                                )
                            })(),
                            label : {
                                show : true,
                                position: 'right',
                                formatter: function (params) {
                                    return params.data.name + ' : ' + (params.data.value)+'%';
                                },
                                textStyle: {fontSize:20,fontWeight:'bold'}
                            }
                        }
                    },
                   data:[]
                },
                {
                    name: '参考率后15',
                    type: 'bar',
                    tooltip:{
                        trigger: 'item',
                        formatter : function (v) {
                            return v.data.name + '<br/>'
                                   + '参考率 : ' + ((v.data.value == undefined)?'0':-v.data.value) + '%<br/>'
                                   + '参考人数: ' + ((v.data.d2 == undefined)?'0':v.data.d2) + '<br/>'
                                   + '通过率 : ' + ((v.data.d3 == undefined)?'0':v.data.d3) + '%<br/>'
                                   + '通过人数: ' + ((v.data.d4 == undefined)?'0':v.data.d4);
                        }
                    },
                    xAxisIndex:1,
                    yAxisIndex:1,
                    barMinHeight: 5,
                    itemStyle : {
                        normal : {
                            color : (function (){
                                var zrColor = require('zrender/tool/color');
                                return zrColor.getLinearGradient(
                                    0, 80, 0, 700,
                                    //['orangered','yellow','lightskyblue']
                                    [[0, 'yellow'],[1, 'lightskyblue']]
                                )
                            })(),
                            label : {
                                show : true,
                                position: 'left',
                                formatter: function (params) {
                                   return params.data.name + ' : ' + (-params.data.value)+'%';
                                },
                                 textStyle: {fontSize:20,fontWeight:'bold'}
                            }
                        }
                    },
                    data:[]
                }
            ]
        };
    }
    
    
    </script>
</body>
</html>