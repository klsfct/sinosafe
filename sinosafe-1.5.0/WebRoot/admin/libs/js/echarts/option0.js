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
	            {start: 75, end: 100},
	            {start: 50, end: 75},
	            {start: 25, end: 50},
	            {start: 25,end: 1}
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
                data : ['山东','江西','福建','安徽','浙江','江苏','上海','黑龙江','吉林','辽宁','内蒙古','山西','河北','天津','北京']
            
            
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
                name: '通过率',
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