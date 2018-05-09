<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>数据初始化</title>
<meta http-equiv="pragma" content="no-cache"></meta>
<meta http-equiv="cache-control" content="no-cache"></meta>
<meta http-equiv="expires" content="0"></meta>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
<meta http-equiv="description" content="This is my page"></meta>
<link rel="stylesheet" href="./admin/libs/css/bootstrap/bootstrap.min.css">
<script src="./admin/libs/js/bootstrap/jquery-1.7.2.min.js"></script>
<script src="./admin/libs/js/bootstrap/bootstrap.min.js"></script>
<%-- <script type="text/javascript" src="./admin/libs/js/jquery.js"></script> --%>
</head>
  
  <body>
  <div class="container">
	<h3>数据初始化</h3>
	<br/>
			<label class="col-xs-2 control-label">清空流水处理表：</label>
			<div >
				<button type="button" class="btn btn-success" id="deleteScoket"
   						data-complete-text="清空成功！">
   						清空
				</button>
			</div>
			<br/>
			
			<label class="col-xs-2 control-label">考生数据初始化：</label>
			<div >
				<button type="button" class="btn btn-success" id="initResultExaminee"
   						data-complete-text="初始化成功！">
   						初始化
				</button>
			</div>
			<br/>
			
			<label class="col-xs-2 control-label">统计表初始化：</label>
			<div >
				<button type="button" class="btn btn-success" id="initResultExamineeSum"
   						data-complete-text="初始化成功！">
   						初始化
				</button>
			</div>
			<br/>
		
	
	</div>
  </body>
  <script type="text/javascript">
  
  var deleteScoket_url = "./deleteScoket.action";
  var initResultExaminee_url = "./initResultExaminee.action";
  var initResultExamineeSum_url = "./initResultExamineeSum.action";
  
  $(function() { 
      $("#deleteScoket").click(function(){
    	  $(this).button('loading').delay(2000).queue(function() {
	              $(this).button('complete');
	        }); 
    	  $.ajax({
      	    type : "POST",
      	    url : deleteScoket_url,
      	    cache : false,
      		jsonp:'callback',  
      	    success : function(data) {
      		},
      		error : function() {
      			alert("清空失败！");
      		}
      	});
      });
  });
  
  $(function() { 
      $("#initResultExaminee").click(function(){
    	  $(this).button('loading').delay(2000).queue(function() {
	              $(this).button('complete');
	        }); 
    	  $.ajax({
      	    type : "POST",
      	    url : initResultExaminee_url,
      	    cache : false,
      		jsonp:'callback',  
      	    success : function(data) {
      		},
      		error : function() {
      			alert("初始化失败！");
      		}
      	});
      });
  });
  $(function() { 
      $("#initResultExamineeSum").click(function(){
    	  $(this).button('loading').delay(2000).queue(function() {
	              $(this).button('complete');
	        }); 
    	  $.ajax({
      	    type : "POST",
      	    url : initResultExamineeSum_url,
      	    cache : false,
      		jsonp:'callback',  
      	    success : function(data) {
      		},
      		error : function() {
      			alert("初始化失败！");
      		}
      	});
      });
  });
  
</script>
</html>
