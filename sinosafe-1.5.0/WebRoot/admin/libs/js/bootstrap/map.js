  document.getElementById('open').onclick = function () {  
        if (document.getElementById('allmap').style.display == 'none') {  
            document.getElementById('allmap').style.display = 'block';  
        } else {  
            document.getElementById('allmap').style.display = 'none';  
        }  
    }  
   
    var map = new BMap.Map("allmap"); 
	map.addControl(new BMap.NavigationControl());    
	map.addControl(new BMap.ScaleControl());    
	map.addControl(new BMap.OverviewMapControl());    
	//map.addControl(new BMap.MapTypeControl());	
    var geoc = new BMap.Geocoder();   //地址解析对象  
    var markersArray = [];  
    var geolocation = new BMap.Geolocation();  
   
   
    var point = new BMap.Point(116.153748,40.031494);  
    map.centerAndZoom(point, 12); // 中心点  
    geolocation.getCurrentPosition(function (r) {  
        if (this.getStatus() == BMAP_STATUS_SUCCESS) {  
            var mk = new BMap.Marker(r.point);  
            map.addOverlay(mk);  
            map.panTo(r.point);  
            map.enableScrollWheelZoom(true);  
        }  
        else {  
            alert('failed' + this.getStatus());  
        }  
    }, {enableHighAccuracy: true})  
    map.addEventListener("click", showInfo);  
   
   
    //清除标识  
    function clearOverlays() {  
        if (markersArray) {  
            for (i in markersArray) {  
                map.removeOverlay(markersArray[i])  
            }  
        }  
    }  
    //地图上标注  
    function addMarker(point) {  
        var marker = new BMap.Marker(point);  
        markersArray.push(marker);  
        clearOverlays();  
        map.addOverlay(marker);  
    }  
    //点击地图时间处理  
    function showInfo(e) {  
        document.getElementById('lng').value = e.point.lng;  
        document.getElementById('lat').value =  e.point.lat;  
        geoc.getLocation(e.point, function (rs) {  
            var addComp = rs.addressComponents;  
            var address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;  
            if (confirm(address)) {  
                document.getElementById('allmap').style.display = 'none'; 
			var	wei = document.getElementById('lat').value;
			var jing = 	document.getElementById('lng').value;		
                document.getElementById('point').value = jing+","+wei; 
				document.getElementById('address').value = address; 				
            }  
        });  
        addMarker(e.point);  
    }