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
                    x: 'right',
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