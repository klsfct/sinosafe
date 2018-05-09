var standardObjectsCreated = false;
var clientInfoObj;
var dhtmlSuiteConfigObj = false;
var dhtmlSuiteCommonObj;
function DHTMLgoodies_createStandardObjects() {
    clientInfoObj = new DHTMLgoodies_clientInfo();
    clientInfoObj.init();
    if (!dhtmlSuiteConfigObj) {
        dhtmlSuiteConfigObj = new DHTMLgoodies_config();
        dhtmlSuiteConfigObj.init()
    }
    dhtmlSuiteCommonObj = new DHTMLgoodies_common();
    dhtmlSuiteCommonObj.init()
}
function DHTMLgoodies_config() {
    var a;
    var b
}
DHTMLgoodies_config.prototype = {
    init: function() {
        this.imagePath = "images_dhtmlsuite/";
        this.cssPath = "css_dhtmlsuite/"
    },
    setCssPath: function(a) {
        this.cssPath = a
    },
    setImagePath: function(a) {
        this.imagePath = a
    }
};
function DHTMLgoodies_common() {
    var a
}
DHTMLgoodies_common.prototype = {
    init: function() {
        this.loadedCSSFiles = new Array()
    },
    getTopPos: function(a) {
        var b = a.offsetTop;
        while ((a = a.offsetParent) != null) {
            if (a.tagName != "HTML") {
                b += a.offsetTop;
                if (document.all) {
                    b += a.clientTop
                }
            }
        }
        return b
    },
    getLeftPos: function(a) {
        var b = a.offsetLeft;
        while ((a = a.offsetParent) != null) {
            if (a.tagName != "HTML") {
                b += a.offsetLeft;
                if (document.all) {
                    b += a.clientLeft
                }
            }
        }
        return b
    },
    cancelEvent: function() {
        return false
    }
};
function DHTMLgoodies_clientInfo() {
    var d;
    var c;
    var a;
    var e;
    var b
}
DHTMLgoodies_clientInfo.prototype = {
    init: function() {
        this.browser = navigator.userAgent;
        this.isOpera = (this.browser.toLowerCase().indexOf("opera") >= 0) ? true: false;
        this.isFirefox = (this.browser.toLowerCase().indexOf("firefox") >= 0) ? true: false;
        this.isMSIE = (this.browser.toLowerCase().indexOf("msie") >= 0) ? true: false;
        this.navigatorVersion = navigator.appVersion.replace(/.*?MSIE (\d\.\d).*/g, "$1") / 1
    }
};
var referenceToDragDropObject;
function DHTMLgoodies_dragDrop() {
    var h;
    var g;
    var k;
    var j;
    var f;
    var d;
    var a;
    var l;
    var c;
    var b;
    var i;
    var e
}
DHTMLgoodies_dragDrop.prototype = {
    init: function() {
        if (!standardObjectsCreated) {
            DHTMLgoodies_createStandardObjects()
        }
        this.currentZIndex = 10000;
        this.dragDropTimer = -1;
        this.dragObjCloneArray = new Array();
        this.numericIdToBeDragged = false;
        this.__initDragDropScript();
        referenceToDragDropObject = this;
        this.okToStartDrag = true;
        this.moveBackBySliding = true
    },
    addSource: function(e, g, d, a, b, c) {
        if (!c) {
            c = false
        }
        if (!this.dragDropSourcesArray) {
            this.dragDropSourcesArray = new Array()
        }
        if (!document.getElementById(e)) {
            alert("The source element with id " + e + " does not exists")
        }
        var f = document.getElementById(e);
        if (d !== false) {
            d = true
        }
        if (a !== false) {
            a = true
        }
        this.dragDropSourcesArray[this.dragDropSourcesArray.length] = [f, g, d, a, b, c];
        f.setAttribute("dragableElement", this.dragDropSourcesArray.length - 1);
        f.dragableElement = this.dragDropSourcesArray.length - 1
    },
    addTarget: function(b, a) {
        if (!this.dragDropTargetArray) {
            this.dragDropTargetArray = new Array()
        }
        if (!document.getElementById(b)) {
            alert("The target element with id " + b + " does not exists")
        }
        var c = document.getElementById(b);
        this.dragDropTargetArray[this.dragDropTargetArray.length] = [c, a]
    },
    setSlide: function(a) {
        this.moveBackBySliding = a
    },
    __initDragDropScript: function() {
        var b = this;
        for (var c = 0; c < this.dragDropSourcesArray.length; c++) {
            var a = this.dragDropSourcesArray[c][0].cloneNode(true);
            a.onmousedown = this.__initDragDropElement;
            a.id = "DHTMLgoodies_dragableElement" + c;
            a.style.position = "absolute";
            a.style.visibility = "hidden";
            a.style.display = "none";
            this.dragDropSourcesArray[c][0].parentNode.insertBefore(a, this.dragDropSourcesArray[c][0]);
            a.style.top = dhtmlSuiteCommonObj.getTopPos(this.dragDropSourcesArray[c][0]) + "px";
            a.style.left = dhtmlSuiteCommonObj.getLeftPos(this.dragDropSourcesArray[c][0]) + "px";
            this.dragDropSourcesArray[c][0].onmousedown = this.__initDragDropElement;
            this.dragObjCloneArray[c] = a
        }
        document.documentElement.onmousemove = this.__moveDragableElement;
        document.documentElement.onmouseup = this.__stop_dragDropElement;
        document.documentElement.onselectstart = function() {
            return b.__cancelSelectionEvent(false, this)
        };
        document.documentElement.ondragstart = function() {
            return dhtmlSuiteCommonObj.cancelEvent(false, this)
        }
    },
    __initDragDropElement: function(c) {
        if (!referenceToDragDropObject.okToStartDrag) {
            return
        }
        referenceToDragDropObject.okToStartDrag = false;
        setTimeout("referenceToDragDropObject.okToStartDrag = true;", 100);
        if (document.all) {
            c = event
        }
        referenceToDragDropObject.numericIdToBeDragged = this.getAttribute("dragableElement");
        referenceToDragDropObject.numericIdToBeDragged = referenceToDragDropObject.numericIdToBeDragged + "";
        if (referenceToDragDropObject.numericIdToBeDragged == "") {
            referenceToDragDropObject.numericIdToBeDragged = this.dragableElement
        }
        referenceToDragDropObject.dragDropTimer = 0;
        referenceToDragDropObject.mouse_x = c.clientX;
        referenceToDragDropObject.mouse_y = c.clientY;
        referenceToDragDropObject.currentZIndex = referenceToDragDropObject.currentZIndex + 1;
        referenceToDragDropObject.dragObjCloneArray[referenceToDragDropObject.numericIdToBeDragged].style.zIndex = referenceToDragDropObject.currentZIndex;
        referenceToDragDropObject.currentEl_allowX = referenceToDragDropObject.dragDropSourcesArray[referenceToDragDropObject.numericIdToBeDragged][2];
        referenceToDragDropObject.currentEl_allowY = referenceToDragDropObject.dragDropSourcesArray[referenceToDragDropObject.numericIdToBeDragged][3];
        var a = referenceToDragDropObject.dragDropSourcesArray[referenceToDragDropObject.numericIdToBeDragged][4];
        referenceToDragDropObject.drag_minX = false;
        referenceToDragDropObject.drag_minY = false;
        referenceToDragDropObject.drag_maxX = false;
        referenceToDragDropObject.drag_maxY = false;
        if (a) {
            var b = document.getElementById(a);
            if (b) {
                referenceToDragDropObject.drag_minX = dhtmlSuiteCommonObj.getLeftPos(b);
                referenceToDragDropObject.drag_minY = dhtmlSuiteCommonObj.getTopPos(b);
                referenceToDragDropObject.drag_maxX = referenceToDragDropObject.drag_minX + b.clientWidth;
                referenceToDragDropObject.drag_maxY = referenceToDragDropObject.drag_minY + b.clientHeight
            }
        }
        if (referenceToDragDropObject.dragDropSourcesArray[referenceToDragDropObject.numericIdToBeDragged][1]) {
            referenceToDragDropObject.dragObjCloneArray[referenceToDragDropObject.numericIdToBeDragged].style.top = dhtmlSuiteCommonObj.getTopPos(referenceToDragDropObject.dragDropSourcesArray[referenceToDragDropObject.numericIdToBeDragged][0]) + "px";
            referenceToDragDropObject.dragObjCloneArray[referenceToDragDropObject.numericIdToBeDragged].style.left = dhtmlSuiteCommonObj.getLeftPos(referenceToDragDropObject.dragDropSourcesArray[referenceToDragDropObject.numericIdToBeDragged][0]) + "px"
        }
        referenceToDragDropObject.el_x = referenceToDragDropObject.dragObjCloneArray[referenceToDragDropObject.numericIdToBeDragged].style.left.replace("px", "") / 1;
        referenceToDragDropObject.el_y = referenceToDragDropObject.dragObjCloneArray[referenceToDragDropObject.numericIdToBeDragged].style.top.replace("px", "") / 1;
        referenceToDragDropObject.__timerDragDropElement();
        return false
    },
    __timerDragDropElement: function() {
        window.thisRef = this;
        if (this.dragDropTimer >= 0 && this.dragDropTimer < 5) {
            this.dragDropTimer = this.dragDropTimer + 1;
            setTimeout("window.thisRef.__timerDragDropElement()", 2);
            return
        }
        if (this.dragDropTimer >= 5) {
            if (this.dragObjCloneArray[this.numericIdToBeDragged].style.display == "none") {
                this.dragDropSourcesArray[this.numericIdToBeDragged][0].style.visibility = "hidden";
                this.dragObjCloneArray[this.numericIdToBeDragged].style.display = "block";
                this.dragObjCloneArray[this.numericIdToBeDragged].style.visibility = "visible";
                this.dragObjCloneArray[this.numericIdToBeDragged].style.top = dhtmlSuiteCommonObj.getTopPos(this.dragDropSourcesArray[this.numericIdToBeDragged][0]) + "px";
                this.dragObjCloneArray[this.numericIdToBeDragged].style.left = dhtmlSuiteCommonObj.getLeftPos(this.dragDropSourcesArray[this.numericIdToBeDragged][0]) + "px"
            }
            if (this.dragDropSourcesArray[referenceToDragDropObject.numericIdToBeDragged][5]) {
                var id1 = this.dragObjCloneArray[this.numericIdToBeDragged].id + "";
                var id2 = this.dragDropSourcesArray[this.numericIdToBeDragged][0].id + "";
                var string = this.dragDropSourcesArray[referenceToDragDropObject.numericIdToBeDragged][5] + '("' + id1 + '","' + id2 + '")';
                eval(string)
            }
        }
    },
    __cancelSelectionEvent: function() {
        if (this.dragDropTimer >= 0) {
            return false
        }
        return true
    },
    __moveDragableElement: function(g) {
        if (document.all) {
            g = event
        }
        if (referenceToDragDropObject.dragDropTimer < 5) {
            return
        }
        var f = referenceToDragDropObject.dragObjCloneArray[referenceToDragDropObject.numericIdToBeDragged];
        if (referenceToDragDropObject.currentEl_allowX) {
            var b = (g.clientX - referenceToDragDropObject.mouse_x + referenceToDragDropObject.el_x);
            if (referenceToDragDropObject.drag_maxX) {
                var d = referenceToDragDropObject.drag_maxX - f.offsetWidth;
                if (b > d) {
                    b = d
                }
                if (b < referenceToDragDropObject.drag_minX) {
                    b = referenceToDragDropObject.drag_minX
                }
            }
            f.style.left = b + "px"
        }
        if (referenceToDragDropObject.currentEl_allowY) {
            var a = (g.clientY - referenceToDragDropObject.mouse_y + referenceToDragDropObject.el_y);
            if (referenceToDragDropObject.drag_maxY) {
                var c = referenceToDragDropObject.drag_maxY - f.offsetHeight;
                if (a > c) {
                    a = c
                }
                if (a < referenceToDragDropObject.drag_minY) {
                    a = referenceToDragDropObject.drag_minY
                }
            }
            f.style.top = a + "px"
        }
    },
    __stop_dragDropElement: function(e) {
        if (referenceToDragDropObject.dragDropTimer < 5) {
            return
        }
        if (document.all) {
            e = event
        }
        if (e.target) {
            dropDestination = e.target
        } else {
            if (e.srcElement) {
                dropDestination = e.srcElement
            }
        }
        if (dropDestination.nodeType == 3) {
            dropDestination = dropDestination.parentNode
        }
        var leftPosMouse = e.clientX + Math.max(document.body.scrollLeft, document.documentElement.scrollLeft);
        var topPosMouse = e.clientY + Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        if (!referenceToDragDropObject.dragDropTargetArray) {
            referenceToDragDropObject.dragDropTargetArray = new Array()
        }
        for (var no = 0; no < referenceToDragDropObject.dragDropTargetArray.length; no++) {
            var leftPosEl = dhtmlSuiteCommonObj.getLeftPos(referenceToDragDropObject.dragDropTargetArray[no][0]);
            var topPosEl = dhtmlSuiteCommonObj.getTopPos(referenceToDragDropObject.dragDropTargetArray[no][0]);
            var widthEl = referenceToDragDropObject.dragDropTargetArray[no][0].offsetWidth;
            var heightEl = referenceToDragDropObject.dragDropTargetArray[no][0].offsetHeight;
            if (leftPosMouse > leftPosEl && leftPosMouse < (leftPosEl + widthEl) && topPosMouse > topPosEl && topPosMouse < (topPosEl + heightEl)) {
                if (referenceToDragDropObject.dragDropTargetArray[no][1]) {
                    eval(referenceToDragDropObject.dragDropTargetArray[no][1] + '("' + referenceToDragDropObject.dragDropSourcesArray[referenceToDragDropObject.numericIdToBeDragged][0].id + '","' + referenceToDragDropObject.dragDropTargetArray[no][0].id + '",' + e.clientX + "," + e.clientY + ")")
                }
                break
            }
        }
        if (referenceToDragDropObject.dragDropSourcesArray[referenceToDragDropObject.numericIdToBeDragged][1]) {
            referenceToDragDropObject.__slideElementBackIntoItsOriginalPosition(referenceToDragDropObject.numericIdToBeDragged)
        }
        referenceToDragDropObject.dragDropTimer = -1;
        referenceToDragDropObject.numericIdToBeDragged = false
    },
    __slideElementBackIntoItsOriginalPosition: function(e) {
        var b = this.dragObjCloneArray[e].style.left.replace("px", "") / 1;
        var a = this.dragObjCloneArray[e].style.top.replace("px", "") / 1;
        var d = dhtmlSuiteCommonObj.getLeftPos(referenceToDragDropObject.dragDropSourcesArray[e][0]);
        var c = dhtmlSuiteCommonObj.getTopPos(referenceToDragDropObject.dragDropSourcesArray[e][0]);
        if (this.moveBackBySliding) {
            this.__processSlide(e, b, a, d, c)
        } else {
            this.dragObjCloneArray[e].style.display = "none";
            this.dragDropSourcesArray[e][0].style.visibility = "visible"
        }
    },
    __processSlide: function(e, b, a, d, c) {
        var g = Math.round(Math.abs(Math.max(b, d) - Math.min(b, d)) / 10);
        var f = Math.round(Math.abs(Math.max(a, c) - Math.min(a, c)) / 10);
        if (f < 3 && Math.abs(g) < 10) {
            f = 3
        }
        if (g < 3 && Math.abs(f) < 10) {
            g = 3
        }
        if (b > d) {
            g *= -1
        }
        if (a > c) {
            f *= -1
        }
        b = b + g;
        a = a + f;
        if (Math.max(b, d) - Math.min(b, d) < 4) {
            b = d
        }
        if (Math.max(a, c) - Math.min(a, c) < 4) {
            a = c
        }
        this.dragObjCloneArray[e].style.left = b + "px";
        this.dragObjCloneArray[e].style.top = a + "px";
        if (b != d || a != c) {
            window.thisRef = this;
            setTimeout('window.thisRef.__processSlide("' + e + '",' + b + "," + a + "," + d + "," + c + ")", 5)
        } else {
            this.dragObjCloneArray[e].style.display = "none";
            this.dragDropSourcesArray[e][0].style.visibility = "visible"
        }
    }
};