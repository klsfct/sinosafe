<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>人脸识别客户端数据导出</title>
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
   	<h3>人脸识别客户端数据导出</h3>
   	<br />
		<div class="form-group">
			<label class="col-xs-2 control-label">点击导出考区数据：</label>
			<div >
				<button type="button" class="btn btn-primary" id="exportAreaData"
   						data-complete-text="导出成功！">
   						导出考区数据
				</button>
			</div>
			<br/>
			<label class="col-xs-2 control-label">点击导出考点数据：</label>
			<div >
				<button type="button" class="btn btn-danger" id="exportPlaceData"
   						data-complete-text="导出成功！" >
   						导出考区数据
				</button>
			</div>
		
			<br/>
			<label class="col-xs-2 control-label">导出场次一考生数据：</label>
			<div >
				<button type="button" class="btn btn-success" id="exportExaminee1Data"
   						data-complete-text="导出成功！" >
   						导出
				</button>
			</div>
			<br/>
			<label class="col-xs-2 control-label">导出场次二考生数据：</label>
			<div >
				<button type="button" class="btn btn-success" id="exportExaminee2Data"
   						data-complete-text="导出成功！">
   						导出
				</button>
			</div>
			<br/>
			<label class="col-xs-2 control-label">导出场次三考生数据：</label>
			<div >
				<button type="button" class="btn btn-success"  id="exportExaminee3Data"
   						data-complete-text="导出成功！">
   						导出
				</button>
			</div>
			<br/>
			<label class="col-xs-2 control-label">导出场次四考生数据：</label>
			<div >
				<button type="button" class="btn btn-success" id="exportExaminee4Data"
   						data-complete-text="导出成功！">
   						导出
				</button>
			</div>
			<br/>
			<label class="col-xs-2 control-label">导出场次五考生数据：</label>
			<div >
				<button type="button" class="btn btn-success" id="exportExaminee5Data"
   						data-complete-text="导出成功！">
   						导出
				</button>
			</div>
			<br/>
			<label class="col-xs-2 control-label">导出场次六考生数据：</label>
			<div >
				<button type="button" class="btn btn-success" id="exportExaminee6Data"
   						data-complete-text="导出成功！">
   						导出
				</button>
			</div>
			<br/>
			
		</div>
	<h3>人工核查系统数据导出</h3>
	<br/>
			<label class="col-xs-2 control-label">人工核查科目文件导出：</label>
			<div >
				<button type="button" class="btn btn-success" id="checkSubjectDataExport"
   						data-complete-text="导出成功！">
   						导出
				</button>
			</div>
			<br/>
			
			<label class="col-xs-2 control-label">人工核查考点文件导出：</label>
			<div >
				<button type="button" class="btn btn-success" id="checkPlaceDataExport"
   						data-complete-text="导出成功！">
   						导出
				</button>
			</div>
			<br/>
			
			<label class="col-xs-2 control-label">人工核查考生信息导出：</label>
			<div >
				<button type="button" class="btn btn-success" id="checkExamineeDataExport"
   						data-complete-text="导出成功！">
   						导出
				</button>
			</div>
			<br/>
		<h3>按照考点批量导出数据</h3>
	<br/>
			<label class="col-xs-2 control-label">人脸识别文件导出：</label>
			<div >
				<button type="button" class="btn btn-success" id="exportDataByExamPlace"
   						data-complete-text="导出成功！">
   						导出
				</button>
			</div>
			<br />
			<label class="col-xs-2 control-label">人工核查文件导出：</label>
			<div >
				<button type="button" class="btn btn-success" id="checkExportDataByExamPlace"
   						data-complete-text="导出成功！">
   						导出
				</button>
			</div>
	
	</div>
  </body>
  <script type="text/javascript">
  
  var exportAreaData_url = "./areaDataExport.action";
  var exportPlaceData_url = "./facePlaceDataExport.action";
  var exportExaminee1Data_url = "./examineeDataExport.action?subject=1";
  var exportExaminee2Data_url = "./examineeDataExport.action?subject=2";
  var exportExaminee3Data_url = "./examineeDataExport.action?subject=3";
  var exportExaminee4Data_url = "./examineeDataExport.action?subject=4";
  var exportExaminee5Data_url = "./examineeDataExport.action?subject=5";
  var exportExaminee6Data_url = "./examineeDataExport.action?subject=6";
  
  
  var checkSubjectDataExport_url = "./checkSubjectDataExport.action";
  var checkPlaceDataExport_url = "./checkPlaceDataExport.action";
  var checkExamineeDataExport_url = "./checkExamineeDataExport.action";
  
  var faceExportDataByExamPlace_url= "./exportDataByExamPlace.action";
  var checkExportDataByExamPlace_url = "./checkExportDataByExamPlace.action";
  
  $(function() { 
      $("#checkExportDataByExamPlace").click(function(){
    	  $(this).button('loading').delay(2000).queue(function() {
	              $(this).button('complete');
	        }); 
    	  $.ajax({
      	    type : "POST",
      	    url : checkExportDataByExamPlace_url,
      	    cache : false,
      		jsonp:'callback',  
      	    success : function(data) {
      		},
      		error : function() {
      			alert("导出数据失败！");
      		}
      	});
      });
  });
  
  $(function() { 
      $("#faceExportDataByExamPlace").click(function(){
    	  $(this).button('loading').delay(2000).queue(function() {
	              $(this).button('complete');
	        }); 
    	  $.ajax({
      	    type : "POST",
      	    url : faceExportDataByExamPlace_url,
      	    cache : false,
      		jsonp:'callback',  
      	    success : function(data) {
      		},
      		error : function() {
      			alert("导出数据失败！");
      		}
      	});
      });
  });
  
  $(function() { 
      $("#checkSubjectDataExport").click(function(){
    	  $(this).button('loading').delay(2000).queue(function() {
	              $(this).button('complete');
	        }); 
    	  $.ajax({
      	    type : "POST",
      	    url : checkSubjectDataExport_url,
      	    cache : false,
      		jsonp:'callback',  
      	    success : function(data) {
      		},
      		error : function() {
      			alert("导出人工核查科目数据失败！");
      		}
      	});
      });
  });
  
  $(function() { 
      $("#checkPlaceDataExport").click(function(){
    	  $(this).button('loading').delay(2000).queue(function() {
	              $(this).button('complete');
	        }); 
    	  $.ajax({
      	    type : "POST",
      	    url : checkPlaceDataExport_url,
      	    cache : false,
      		jsonp:'callback',  
      	    success : function(data) {
      		},
      		error : function() {
      			alert("导出人工核查考点数据失败！");
      		}
      	});
      });
  });
  $(function() { 
      $("#checkExamineeDataExport").click(function(){
    	  $(this).button('loading').delay(2000).queue(function() {
	              $(this).button('complete');
	        }); 
    	  $.ajax({
      	    type : "POST",
      	    url : checkExamineeDataExport_url,
      	    cache : false,
      		jsonp:'callback',  
      	    success : function(data) {
      		},
      		error : function() {
      			alert("导出人工核查考生数据失败！");
      		}
      	});
      });
  });
  
  $(function() { 
      $("#exportAreaData").click(function(){
    	  $(this).button('loading').delay(2000).queue(function() {
	              $(this).button('complete');
	        }); 
    	  $.ajax({
      	    type : "POST",
      	    url : exportAreaData_url,
      	    cache : false,
      		jsonp:'callback',  
      	    success : function(data) {
      		},
      		error : function() {
      			alert("导出考区数据失败！");
      		}
      	});
      });
  });
  
  $(function() { 
      $("#exportPlaceData").click(function(){
    	  $(this).button('loading').delay(2000).queue(function() {
	              $(this).button('complete');
	        }); 
    	  $.ajax({
      	    type : "POST",
      	    url : exportPlaceData_url,
      	    cache : false,
      		jsonp:'callback',  
      	    success : function(data) {
      		},
      		error : function() {
      			alert("导出考点数据失败！");
      		}
      	});
      });
  });
  
  $(function() { 
      $("#exportExaminee1Data").click(function(){
    	  $(this).button('loading').delay(2000).queue(function() {
	              $(this).button('complete');
	        }); 
    	  $.ajax({
      	    type : "POST",
      	    url : exportExaminee1Data_url,
      	    cache : false,
      		jsonp:'callback',  
      	    success : function(data) {
      		},
      		error : function() {
      			alert("导出场次一考生数据失败！");
      		}
      	});
      });
  });
  
  $(function() { 
      $("#exportExaminee2Data").click(function(){
    	  $(this).button('loading').delay(2000).queue(function() {
	              $(this).button('complete');
	        }); 
    	  $.ajax({
      	    type : "POST",
      	    url : exportExaminee2Data_url,
      	    cache : false,
      		jsonp:'callback',  
      	    success : function(data) {
      		},
      		error : function() {
      			alert("导出场次二考生数据失败！");
      		}
      	});
      });
  });
  
  $(function() { 
      $("#exportExaminee3Data").click(function(){
    	  $(this).button('loading').delay(2000).queue(function() {
	              $(this).button('complete');
	        }); 
    	  $.ajax({
      	    type : "POST",
      	    url : exportExaminee3Data_url,
      	    cache : false,
      		jsonp:'callback',  
      	    success : function(data) {
      		},
      		error : function() {
      			alert("导出场次三考生数据失败！");
      		}
      	});
      });
  });
  
  $(function() { 
      $("#exportExaminee4Data").click(function(){
    	  $(this).button('loading').delay(2000).queue(function() {
	              $(this).button('complete');
	        }); 
    	  $.ajax({
      	    type : "POST",
      	    url : exportExaminee4Data_url,
      	    cache : false,
      		jsonp:'callback',  
      	    success : function(data) {
      		},
      		error : function() {
      			alert("导出场次四考生数据失败！");
      		}
      	});
      });
  });
  $(function() { 
      $("#exportExaminee5Data").click(function(){
    	  $(this).button('loading').delay(2000).queue(function() {
	              $(this).button('complete');
	        }); 
    	  $.ajax({
      	    type : "POST",
      	    url : exportExaminee5Data_url,
      	    cache : false,
      		jsonp:'callback',  
      	    success : function(data) {
      		},
      		error : function() {
      			alert("导出场次四考生数据失败！");
      		}
      	});
      });
  });
  $(function() { 
      $("#exportExaminee6Data").click(function(){
    	  $(this).button('loading').delay(2000).queue(function() {
	              $(this).button('complete');
	        }); 
    	  $.ajax({
      	    type : "POST",
      	    url : exportExaminee6Data_url,
      	    cache : false,
      		jsonp:'callback',  
      	    success : function(data) {
      		},
      		error : function() {
      			alert("导出场次四考生数据失败！");
      		}
      	});
      });
  });
  
</script>
</html>
