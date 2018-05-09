var gradualFader = {};
gradualFader.baseopacity = 0.4;
gradualFader.increment = 0.2;
document.write('<style type="text/css">\n');
document.write(".gradualfader{filter:progid:DXImageTransform.Microsoft.alpha(opacity=" + gradualFader.baseopacity * 100 + "); -moz-opacity:" + gradualFader.baseopacity + "; opacity:" + gradualFader.baseopacity + ";}\n");
document.write("</style>");
gradualFader.setopacity = function(c, b) {
    var a = c;
    if (a && a.filters && a.filters[0]) {
        if (typeof a.filters[0].opacity == "number") {
            a.filters[0].opacity = b * 100
        } else {
            a.style.filter = "alpha(opacity=" + b * 100 + ")"
        }
    } else {
        if (a && typeof a.style.MozOpacity != "undefined") {
            a.style.MozOpacity = b
        } else {
            if (a && typeof a.style.opacity != "undefined") {
                a.style.opacity = b
            }
        }
    }
    a.currentopacity = b
};
gradualFader.fadeupdown = function(d, c) {
    var b = d;
    var a = (c == "fadeup") ? this.increment: -this.increment;
    if (b && (c == "fadeup" && b.currentopacity < 1 || c == "fadedown" && b.currentopacity > this.baseopacity)) {
        this.setopacity(d, b.currentopacity + a);
        window["opacityfader" + d._fadeorder] = setTimeout(function() {
            gradualFader.fadeupdown(d, c)
        },
        50)
    }
};
gradualFader.clearTimer = function(a) {
    if (typeof window["opacityfader" + a._fadeorder] != "undefined") {
        clearTimeout(window["opacityfader" + a._fadeorder])
    }
};
gradualFader.isContained = function(a, b) {
    var b = window.event || b;
    var d = b.relatedTarget || ((b.type == "mouseover") ? b.fromElement: b.toElement);
    while (d && d != a) {
        try {
            d = d.parentNode
        } catch(b) {
            d = a
        }
    }
    if (d == a) {
        return true
    } else {
        return false
    }
};
gradualFader.fadeinterface = function(c, b, a) {
    if (!this.isContained(c, b)) {
        gradualFader.clearTimer(c);
        gradualFader.fadeupdown(c, a)
    }
};
gradualFader.collectElementbyClass = function(d) {
    var e = new RegExp("(^|\\s+)" + d + "($|\\s+)", "i");
    var c = [];
    var b = document.all ? document.all: document.getElementsByTagName("*");
    for (var a = 0; a < b.length; a++) {
        if (typeof b[a].className == "string" && b[a].className.search(e) != -1) {
            c[c.length] = b[a]
        }
    }
    return c
};
gradualFader.init = function() {
    var b = this.collectElementbyClass("imgFade");
    for (var a = 0; a < b.length; a++) {
        b[a]._fadeorder = a;
        this.setopacity(b[a], this.baseopacity);
        b[a].onmouseover = function(c) {
            gradualFader.fadeinterface(this, c, "fadeup")
        };
        b[a].onmouseout = function(c) {
            gradualFader.fadeinterface(this, c, "fadedown")
        }
    }
};
jQuery.fn.extend({
    imgFadeRender: function() {
        gradualFader.init()
    }
});