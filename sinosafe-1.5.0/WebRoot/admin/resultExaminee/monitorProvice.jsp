<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String baseIP = request.getScheme() + "://"
			+ request.getServerName();
	request.setAttribute("basePath", basePath);
	request.setAttribute("baseIP", baseIP);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>省级监控平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css">
    	body{
    		background: #1b1b1b;
    	}
    	.center-title {
    		position: absolute;
    		top: 5px;
    		width:100%;
    		text-align: center;
    		color: #fff;
    		font-size: 22px;
    		font-weight: bold;
    		padding: 10px 0 0 0 ;
    	}
    	.center-title p{
    	margin: 0;
    	padding: 0;
    	line-height: 1.5em;
    	}
    	
    	.center场次一tle2 {
    		position: absolute;top: 50px;
    		left: 20px;
    		width: 100%;
    		text-align: left;
    		color: #fff;
    		font-size: 18px;
    		padding: 10px 0 0 0 ;
    	}
    	.center-title2 p{
    	margin: 0;
    	padding: 0;
    	line-height: 1.5em;
    	position: absolute;
    	left:20px;
   		top: 45px;
   		width:100%;
   		text-align: left;
   		color: #fff;
   		font-size: 18px;
   		font-weight: bold;
   		padding: 10px 0 0 0 ;
    	}
    	.center-title3 p{
    	margin: 0;
    	padding: 0;
    	line-height: 1.5em;
    	position: absolute;
    	left:20px;
   		top: 75px;
   		width:100%;
   		text-align: left;
   		color: #fff;
   		font-size: 18px;
   		font-weight: bold;
   		padding: 10px 0 0 0 ;
    	}
    	.center-title4 p{
    	margin: 0;
    	padding: 0;
    	line-height: 1.5em;
    	position: absolute;
    	left:20px;
   		top: 105px;
   		width:100%;
   		text-align: left;
   		color: #fff;
   		font-size: 18px;
   		font-weight: bold;
   		padding: 10px 0 0 0 ;
    	}
    	.center-title5 p{
    	margin: 0;
    	padding: 0;
    	line-height: 1.5em;
    	position: absolute;
    	left:20px;
   		top: 135px;
   		width:100%;
   		text-align: left;
   		color: #fff;
   		font-size: 18px;
   		font-weight: bold;
   		padding: 10px 0 0 0 ;
    	}
    	.top-button {
    		position: absolute;
    		top: 5px;
    		left:10px;
    		width:300px;
    		color: #fff;
    		font-size: 18px;
    		padding: 10px 0 0 0 ;
    		z-index: 9999;
    	}
    	.top-button a {
    		color: #fff;
    	}
    	.padding2 {
    		padding-left: 2em;
    	}
    </style>
</head>
<body>
	<div class="top-button">
		<s:select list="#{1:'===科目一===',2:'===科目二===',3:'===科目三===',4:'===科目四==='}"
		 name="subject" id="globlisSubject" cssStyle="font-size: 15px;"
		listKey="key" listValue="value"></s:select>
		 <a href="javascript:sortCharts();">排序</a>
		<!-- <a href="javascript:gotoVideo();">视频监控平台</a> -->
		<s:hidden name="areaId" id="areaId"></s:hidden>
	</div>
	<div class="center-title">
		<p>广西住房城乡建设领域专业技术人员考试信息化云平台</p>
	</div>
	<div class="center-title2">
		<p>全区总考场数：41
		南宁地区考场数：32
		柳州地区考场数：4
		桂林地区考场数：5</p></div>
	<div class="center-title3"> <p>全区参考考生数：<span id="totle2"></span>
		总参考虑：<span id="totle1"></span>
		缺考率：<span id="totle5"></span>
		违纪人数：0</p></div>
	<div class="center-title4"><p>人脸识别通过率：<span id="totle3"></span>
		通过人数：<span id="totle4"></span></p></div>
	<div class="center-title5"><p>试卷主数据包下发情况（已下载/总数）：41/41
		切片：0/41
		密钥：0/41</p> 
	</div>
	<div style="height: 10px;"></div>
    <div id="g0" style="height:680px;padding-bottom: 100px"></div>
    <script src="../libs/js/echarts/jquery.js"></script>
    <script src="../libs/js/echarts/echarts.js"></script>
    <!--框架必需start-->
	<script type="text/javascript" src="../libs/js/framework.js"></script>
	<link href="../libs/css/import_basic.css" rel="stylesheet"
		type="text/css" />
	<link rel="stylesheet" type="text/css" id="skin" prePath="../" />
	<link rel="stylesheet" type="text/css" id="customSkin" />
	<!--框架必需end-->
    <script type="text/javascript">
    require.config({
        paths: {
        	echarts: '../libs/js/echarts'
        }
    });
    var myChart0;
    var timeTicket;
    var data_url1 = "monitorByProvince.action";//地区数据统计
    var data_url2 = "areaData.json";//地区数据统计
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
    		}, 5000);
    		myChart0.on(config.EVENT.CLICK, eConsole);
        }
    );

    function eConsole(param) {
        console.log(param);
        //alert(param.data.d1);
        //跳转
        window.location.href = "../../public/control/gotoMonitorByCity.action?paramAreaId="+param.data.d1;
    }

    function gotoVideo(){
    	ys7.ExecMe("${basePath}/public/video/gotoVideo2.action");
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
    	   data: {"subject":$("#globlisSubject").val(),"areaId":$("#areaId").val()},
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
    					$("#totle5").html((100-parseFloat(_totalData.value))+"%");
    					
    					//更新地图
    					var _chartData = data.dataMain.mapItemList;
    					var _yAxis1_data = data.dataMain.barTitleList;
    					var _series0_mapType = data.dataMain.mapName;
    					$("#mapName").html(_series0_mapType);
    					
    					var _option;
    					if(firstFlag==true){
    					 	_option = option1();
    					 	_option.series[0].mapType = _series0_mapType;
    					 	_option.yAxis[0].data = _yAxis1_data;
    					}else{
    						_option = chart.getOption();
    					}
    					
    					if(sortFlag==true){
    						_yAxis1_data = [];
    						$.each(_chartData,function(index,item) {
    							_yAxis1_data.push(item.name);
    						});
    						_option.yAxis[0].data = _yAxis1_data;
    					}else{
    						_yAxis1_data = _option.yAxis[0].data;
    					}
    					
    					var _series0_data = _chartData;
    					var _series1_data = [];
    					$.each(_yAxis1_data,function(index1,arrVal1) {
    						$.each(_chartData,function(index,item) {
    							if(arrVal1==item.name){
    								_series1_data.push(item);
    							}
    						});
    					});
    					_option.series[0].data =_series0_data;
    					_option.series[1].data =_series1_data;
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


    function option1 () {
        return {
            title : {
            	show:false,
                text: '国际司法考试考务安全系统监控平台',
                subtext: '',
                x:'center',
                y: 'top'
            },
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
                x: 'right',
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
                 	name:'地市',
                    type : 'value',
                    position: 'top',
                    splitLine: {show:false},
                    axisLine: {show:false},
                    axisLabel : {show:false},
                    min: 0,
                    max: 200
                }
            ],
            yAxis : [
                {
                   type : 'category',
                    splitLine: {show:false},
                    axisLine: {show:false},
                    axisLabel : {show:false},
                    data : []
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
                    mapType: '黑龙江',
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
                    /*roam:false,
                    scaleLimit:{max:2, min:1},*/
                    data:[]
                },
                {
                    name: '参考率',
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
                                    0, 80, 0, 500,
                                   // ['orangered','yellow','lightskyblue']
                                    [[0, 'green'],[0.5, 'yellow'],[1, 'lightskyblue']]
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
                }
            ]
        };
    }
    
    </script>
</body>
</html>