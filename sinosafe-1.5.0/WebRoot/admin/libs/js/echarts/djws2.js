require.config({
    paths: {
        echarts: './demodist'
    }
});
var myChart0;
var timeTicket;
var data_uar1 = "hlj.json";//地区数据统计
var data_uar2 = "areaData.json";//地区数据统计
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
		//myChart0.on(config.EVENT.CLICK, eConsole);
    }
);

function eConsole(param) {
    console.log(param);
}


function getAjaxUrl(){
	return data_uar1;
}

function getAjaxData(){
	return "";
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
	   url: getAjaxUrl(),
	   data: getAjaxData(),
	   async : true,
	   dataType : "json",
	   success : function(data) {
			if (data != null) {
				if (data.dataStatus == "1") {
					var _chartData = data.dataMain;
					var _yAxis1_data = data.dataOther;
					var _series0_mapType = data.dataOther2;
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


