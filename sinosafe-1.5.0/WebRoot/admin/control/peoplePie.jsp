<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html style="height: 100%">
   <head>
       <meta charset="utf-8">
   </head>
   <body style="height: 100%; margin: 0">
       <div id="container" style="height: 100%"></div>
       <script type="text/javascript" src="../libs/js/echarts/echarts-all-3.js"></script>
       <script type="text/javascript">
		var dom = document.getElementById("container");
		var myChart = echarts.init(dom);
		var app = {};
		option = null;
		option = {
		    title : {
		        text: '报名人员户籍分布',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: '10%',
		        top: '10%',
		        data: ['北京','上海','天津','重庆','黑龙江','河北','山东','四川','湖北','青海','广州','江苏','湖南','江西','福建','其它']
		    },
			color:['#c23531','#2f4554', '#61a0a8', '#d48265', '#91c7ae','#749f83',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3','#aec9fe','#0f4275','#5c1292','#647318','#397d62','#f1ae30','#0b5ea0'],
		    series : [
		        {
		            name: '访问来源',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:[
		                {value:756, name:'北京'},
		                {value:610, name:'上海'},
		                {value:532, name:'天津'},
		                {value:135, name:'重庆'},
						{value:435, name:'黑龙江'},
						
		                {value:310, name:'河北'},
		                {value:234, name:'山东'},
		                {value:135, name:'四川'},
						{value:335, name:'湖北'},
		                {value:310, name:'青海'},
						
		                {value:434, name:'广州'},
		                {value:315, name:'江苏'},
						{value:235, name:'湖南'},
		                {value:210, name:'江西'},
		                {value:374, name:'福建'},
		                {value:995, name:'其它'}
		            ],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		if (option && typeof option === "object") {
		    myChart.setOption(option, true);
		}
   		</script>
   </body>
</html>