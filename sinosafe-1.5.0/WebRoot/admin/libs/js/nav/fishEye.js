jQuery.iFisheye = {
    build: function(a) {
        return this.each(function() {
            var b = this;
            b.fisheyeCfg = {
                items: jQuery(a.items, this),
                container: jQuery(a.container, this),
                pos: jQuery.iUtil.getPosition(this),
                itemWidth: a.itemWidth,
                itemsText: a.itemsText,
                proximity: a.proximity,
                valign: a.valign,
                halign: a.halign,
                maxWidth: a.maxWidth
            };
            jQuery.iFisheye.positionContainer(b, 0);
            jQuery(window).bind("resize",
            function() {
                b.fisheyeCfg.pos = jQuery.iUtil.getPosition(b);
                jQuery.iFisheye.positionContainer(b, 0);
                jQuery.iFisheye.positionItems(b)
            });
            jQuery.iFisheye.positionItems(b);
            b.fisheyeCfg.items.bind("mouseover",
            function() {
                jQuery(b.fisheyeCfg.itemsText, this).get(0).style.display = "block"
            }).bind("mouseout",
            function() {
                jQuery(b.fisheyeCfg.itemsText, this).get(0).style.display = "none"
            });
            jQuery(document).bind("mousemove",
            function(f) {
                var g = jQuery.iUtil.getPointer(f);
                var d = 0;
                if (b.fisheyeCfg.halign && b.fisheyeCfg.halign == "center") {
                    var c = g.x - b.fisheyeCfg.pos.x - (b.offsetWidth - b.fisheyeCfg.itemWidth * b.fisheyeCfg.items.size()) / 2 - b.fisheyeCfg.itemWidth / 2
                } else {
                    if (b.fisheyeCfg.halign && b.fisheyeCfg.halign == "right") {
                        var c = g.x - b.fisheyeCfg.pos.x - b.offsetWidth + b.fisheyeCfg.itemWidth * b.fisheyeCfg.items.size()
                    } else {
                        var c = g.x - b.fisheyeCfg.pos.x
                    }
                }
                var h = Math.pow(g.y - b.fisheyeCfg.pos.y - b.offsetHeight / 2, 2);
                b.fisheyeCfg.items.each(function(e) {
                    distance = Math.sqrt(Math.pow(c - e * b.fisheyeCfg.itemWidth, 2) + h);
                    distance -= b.fisheyeCfg.itemWidth / 2;
                    distance = distance < 0 ? 0 : distance;
                    distance = distance > b.fisheyeCfg.proximity ? b.fisheyeCfg.proximity: distance;
                    distance = b.fisheyeCfg.proximity - distance;
                    extraWidth = b.fisheyeCfg.maxWidth * distance / b.fisheyeCfg.proximity;
                    this.style.width = b.fisheyeCfg.itemWidth + extraWidth + "px";
                    this.style.left = b.fisheyeCfg.itemWidth * e + d + "px";
                    d += extraWidth
                });
                jQuery.iFisheye.positionContainer(b, d)
            })
        })
    },
    positionContainer: function(b, a) {
        if (b.fisheyeCfg.halign) {
            if (b.fisheyeCfg.halign == "center") {
                b.fisheyeCfg.container.get(0).style.left = (b.offsetWidth - b.fisheyeCfg.itemWidth * b.fisheyeCfg.items.size()) / 2 - a / 2 + "px"
            } else {
                if (b.fisheyeCfg.halign == "left") {
                    b.fisheyeCfg.container.get(0).style.left = -a / b.fisheyeCfg.items.size() + "px"
                } else {
                    if (b.fisheyeCfg.halign == "right") {
                        b.fisheyeCfg.container.get(0).style.left = (b.offsetWidth - b.fisheyeCfg.itemWidth * b.fisheyeCfg.items.size()) - a / 2 + "px"
                    }
                }
            }
        }
        b.fisheyeCfg.container.get(0).style.width = b.fisheyeCfg.itemWidth * b.fisheyeCfg.items.size() + a + "px"
    },
    positionItems: function(a) {
        a.fisheyeCfg.items.each(function(b) {
            this.style.width = a.fisheyeCfg.itemWidth + "px";
            this.style.left = a.fisheyeCfg.itemWidth * b + "px"
        })
    }
};
jQuery.fn.Fisheye = jQuery.iFisheye.build;
jQuery.iUtil = {
    getPosition: function(g) {
        var a = 0;
        var i = 0;
        var h = g.style;
        var f = false;
        if (jQuery(g).css("display") == "none") {
            var b = h.visibility;
            var d = h.position;
            f = true;
            h.visibility = "hidden";
            h.display = "block";
            h.position = "absolute"
        }
        var c = g;
        while (c) {
            a += c.offsetLeft + (c.currentStyle && !jQuery.browser.opera ? parseInt(c.currentStyle.borderLeftWidth) || 0 : 0);
            i += c.offsetTop + (c.currentStyle && !jQuery.browser.opera ? parseInt(c.currentStyle.borderTopWidth) || 0 : 0);
            c = c.offsetParent
        }
        c = g;
        while (c && c.tagName && c.tagName.toLowerCase() != "body") {
            a -= c.scrollLeft || 0;
            i -= c.scrollTop || 0;
            c = c.parentNode
        }
        if (f == true) {
            h.display = "none";
            h.position = d;
            h.visibility = b
        }
        return {
            x: a,
            y: i
        }
    },
    getPositionLite: function(b) {
        var a = 0,
        c = 0;
        while (b) {
            a += b.offsetLeft || 0;
            c += b.offsetTop || 0;
            b = b.offsetParent
        }
        return {
            x: a,
            y: c
        }
    },
    getSize: function(g) {
        var a = jQuery.css(g, "width");
        var d = jQuery.css(g, "height");
        var f = 0;
        var j = 0;
        var i = g.style;
        if (jQuery(g).css("display") != "none") {
            f = g.offsetWidth;
            j = g.offsetHeight
        } else {
            var b = i.visibility;
            var c = i.position;
            i.visibility = "hidden";
            i.display = "block";
            i.position = "absolute";
            f = g.offsetWidth;
            j = g.offsetHeight;
            i.display = "none";
            i.position = c;
            i.visibility = b
        }
        return {
            w: a,
            h: d,
            wb: f,
            hb: j
        }
    },
    getSizeLite: function(a) {
        return {
            wb: a.offsetWidth || 0,
            hb: a.offsetHeight || 0
        }
    },
    getClient: function(c) {
        var b, a, d;
        if (c) {
            a = c.clientWidth;
            b = c.clientHeight
        } else {
            d = document.documentElement;
            a = window.innerWidth || self.innerWidth || (d && d.clientWidth) || document.body.clientWidth;
            b = window.innerHeight || self.innerHeight || (d && d.clientHeight) || document.body.clientHeight
        }
        return {
            w: a,
            h: b
        }
    },
    getScroll: function(i) {
        var d = 0,
        b = 0,
        a = 0,
        f = 0,
        c = 0,
        g = 0;
        if (i && i.nodeName.toLowerCase() != "body") {
            d = i.scrollTop;
            b = i.scrollLeft;
            a = i.scrollWidth;
            f = i.scrollHeight;
            c = 0;
            g = 0
        } else {
            if (document.documentElement) {
                d = document.documentElement.scrollTop;
                b = document.documentElement.scrollLeft;
                a = document.documentElement.scrollWidth;
                f = document.documentElement.scrollHeight
            } else {
                if (document.body) {
                    d = document.body.scrollTop;
                    b = document.body.scrollLeft;
                    a = document.body.scrollWidth;
                    f = document.body.scrollHeight
                }
            }
            c = self.innerWidth || document.documentElement.clientWidth || document.body.clientWidth || 0;
            g = self.innerHeight || document.documentElement.clientHeight || document.body.clientHeight || 0
        }
        return {
            t: d,
            l: b,
            w: a,
            h: f,
            iw: c,
            ih: g
        }
    },
    getMargins: function(i, d) {
        var g = jQuery(i);
        var f = g.css("marginTop") || "";
        var h = g.css("marginRight") || "";
        var a = g.css("marginBottom") || "";
        var c = g.css("marginLeft") || "";
        if (d) {
            return {
                t: parseInt(f) || 0,
                r: parseInt(h) || 0,
                b: parseInt(a) || 0,
                l: parseInt(c)
            }
        } else {
            return {
                t: f,
                r: h,
                b: a,
                l: c
            }
        }
    },
    getPadding: function(i, d) {
        var g = jQuery(i);
        var f = g.css("paddingTop") || "";
        var h = g.css("paddingRight") || "";
        var a = g.css("paddingBottom") || "";
        var c = g.css("paddingLeft") || "";
        if (d) {
            return {
                t: parseInt(f) || 0,
                r: parseInt(h) || 0,
                b: parseInt(a) || 0,
                l: parseInt(c)
            }
        } else {
            return {
                t: f,
                r: h,
                b: a,
                l: c
            }
        }
    },
    getBorder: function(i, d) {
        var g = jQuery(i);
        var f = g.css("borderTopWidth") || "";
        var h = g.css("borderRightWidth") || "";
        var a = g.css("borderBottomWidth") || "";
        var c = g.css("borderLeftWidth") || "";
        if (d) {
            return {
                t: parseInt(f) || 0,
                r: parseInt(h) || 0,
                b: parseInt(a) || 0,
                l: parseInt(c) || 0
            }
        } else {
            return {
                t: f,
                r: h,
                b: a,
                l: c
            }
        }
    },
    getPointer: function(b) {
        var a = b.pageX || (b.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft)) || 0;
        var c = b.pageY || (b.clientY + (document.documentElement.scrollTop || document.body.scrollTop)) || 0;
        return {
            x: a,
            y: c
        }
    },
    traverseDOM: function(a, b) {
        b(a);
        a = a.firstChild;
        while (a) {
            jQuery.iUtil.traverseDOM(a, b);
            a = a.nextSibling
        }
    },
    purgeEvents: function(a) {
        jQuery.iUtil.traverseDOM(a,
        function(c) {
            for (var b in c) {
                if (typeof c[b] === "function") {
                    c[b] = null
                }
            }
        })
    },
    centerEl: function(d, c) {
        var a = jQuery.iUtil.getScroll();
        var b = jQuery.iUtil.getSize(d);
        if (!c || c == "vertically") {
            jQuery(d).css({
                top: a.t + ((Math.max(a.h, a.ih) - a.t - b.hb) / 2) + "px"
            })
        }
        if (!c || c == "horizontally") {
            jQuery(d).css({
                left: a.l + ((Math.max(a.w, a.iw) - a.l - b.wb) / 2) + "px"
            })
        }
    },
    fixPNG: function(b, d) {
        var a = jQuery('img[@src*="png"]', b || document),
        c;
        a.each(function() {
            c = this.src;
            this.src = d;
            this.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + c + "')"
        })
    }
}; [].indexOf || (Array.prototype.indexOf = function(b, d) {
    d = (d == null) ? 0 : d;
    var a = this.length;
    for (var c = d; c < a; c++) {
        if (this[c] == b) {
            return c
        }
    }
    return - 1
});