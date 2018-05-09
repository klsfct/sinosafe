var ss = {
    fixAllLinks: function() {
        var b = document.getElementsByTagName("a");
        for (var a = 0; a < b.length; a++) {
            var c = b[a];
            if ((c.href && c.href.indexOf("#") != -1) && ((c.pathname == location.pathname) || ("/" + c.pathname == location.pathname)) && (c.search == location.search)) {
                ss.addEvent(c, "click", ss.smoothScroll)
            }
        }
    },
    smoothScroll: function(h) {
        if (window.event) {
            target = window.event.srcElement
        } else {
            if (h) {
                target = h.target
            } else {
                return
            }
        }
        if (target.nodeName.toLowerCase() != "a") {
            target = target.parentNode
        }
        if (target.nodeName.toLowerCase() != "a") {
            return
        }
        anchor = target.hash.substr(1);
        var g = document.getElementsByTagName("a");
        var a = null;
        for (var f = 0; f < g.length; f++) {
            var j = g[f];
            if (j.name && (j.name == anchor)) {
                a = j;
                break
            }
        }
        if (!a) {
            a = document.getElementById(anchor)
        }
        if (!a) {
            return true
        }
        var c = a.offsetLeft;
        var b = a.offsetTop;
        var d = a;
        while (d.offsetParent && (d.offsetParent != document.body)) {
            d = d.offsetParent;
            c += d.offsetLeft;
            b += d.offsetTop
        }
        clearInterval(ss.INTERVAL);
        cypos = ss.getCurrentYPos();
        ss_stepsize = parseInt((b - cypos) / ss.STEPS);
        ss.INTERVAL = setInterval("ss.scrollWindow(" + ss_stepsize + "," + b + ',"' + anchor + '")', 10);
        if (window.event) {
            window.event.cancelBubble = true;
            window.event.returnValue = false
        }
        if (h && h.preventDefault && h.stopPropagation) {
            h.preventDefault();
            h.stopPropagation()
        }
    },
    scrollWindow: function(a, c, b) {
        wascypos = ss.getCurrentYPos();
        isAbove = (wascypos < c);
        window.scrollTo(0, wascypos + a);
        iscypos = ss.getCurrentYPos();
        isAboveNow = (iscypos < c);
        if ((isAbove != isAboveNow) || (wascypos == iscypos)) {
            window.scrollTo(0, c);
            clearInterval(ss.INTERVAL);
            location.hash = b
        }
    },
    getCurrentYPos: function() {
        if (document.body && document.body.scrollTop) {
            return document.body.scrollTop
        }
        if (document.documentElement && document.documentElement.scrollTop) {
            return document.documentElement.scrollTop
        }
        if (window.pageYOffset) {
            return window.pageYOffset
        }
        return 0
    },
    addEvent: function(e, d, b, a) {
        if (e.addEventListener) {
            e.addEventListener(d, b, a);
            return true
        } else {
            if (e.attachEvent) {
                var c = e.attachEvent("on" + d, b);
                return c
            } else {
                alert("Handler could not be removed")
            }
        }
    }
};
ss.STEPS = 25;
ss.addEvent(window, "load", ss.fixAllLinks);