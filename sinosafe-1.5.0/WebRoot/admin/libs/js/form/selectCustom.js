var parentTopHeight;
var broswerFlag;
jQuery.fn.extend({
    selectCustomRender: function() {
        var a = $(this).html();
        return this.each(function() {
            $(this).empty();
            new jQuery.SelectCustomBox(this, a)
        })
    },
    selectCustomSetValue: function(c, g) {
        var a = "请选择";
        if ($(this).attr("prompt") != null) {
            a = $(this).attr("prompt")
        }
        var h;
        $(this).find("input").each(function() {
            if ($(this).attr("class") == "selectbox error-field" || $(this).attr("class") == "selectbox") {
                h = $(this)
            }
        });
        if (c == "") {
            h.val(a);
            if ($(this).attr("multiMode") == "true" || $(this).attr("multiMode") == true) {
                h.attr("title", " ");
                try {
                    addTooltip(h[0])
                } catch(d) {}
            }
            $(this).attr("relValue", "");
            $(this).attr("relText", "");
            $(this).attr("editValue", "");
            return
        }
        h.val(g);
        $(this).attr("relValue", c);
        $(this).attr("editValue", c);
        $(this).attr("relText", g);
        var b = $(this).attr("name");
        if (b) {
            $(this).find("input:hidden").each(function() {
                if ($(this).attr("name") == b) {
                    $(this).val(c)
                }
            })
        }
        if ($(this).attr("multiMode") == "true" || $(this).attr("multiMode") == true) {
            h.attr("title", g);
            try {
                addTooltip(h[0])
            } catch(d) {}
        } else {
            var f = $(this).find("div[class=selectbox-tree]");
            f.attr("hasfocus", 0);
            f.hide();
            $(this).trigger("boxClose")
        }
    },
    selectCustomClose: function() {
        var a = $(this).find("div[class=selectbox-tree]");
        a.attr("hasfocus", 0);
        a.hide();
        $(this).trigger("boxClose")
    }
});
var selectTree_id = 1;
jQuery.SelectCustomBox = function(F, d) {
    var b = {};
    b.inputClass = b.inputClass || "selectbox";
    b.containerClass = b.containerClass || "selectbox-tree";
    b.hoverClass = b.hoverClass || "current";
    b.currentClass = b.selectedClass || "selected";
    b.debug = b.debug || false;
    var A = 24;
    var s = 24;
    if (!splitMode) {
        var z = $(window.top.document.getElementById("theme"));
        if (z.attr("selInputHeight") != null) {
            A = Number(z.attr("selInputHeight"))
        }
        if (z.attr("selButtonWidth") != null) {
            s = Number(z.attr("selButtonWidth"))
        }
    }
    selectTree_id++;
    var g = "请选择";
    var D = "0_input";
    var e = "0_button";
    var f = false;
    var y = $(F);
    y.addClass("mainCon");
    if (y.attr("prompt") != null) {
        g = y.attr("prompt")
    }
    var w = l(b, d);
    var c = p(b);
    var E = a(b);
    var n;
    n = $("<input type='button' value=' ' class='selBtn'/>");
    var u = false;
    if (y.attr("multiMode") != null) {
        if (y.attr("multiMode") == "true" || y.attr("multiMode") == true) {
            u = true;
            n.addClass("selBtnMuiti")
        } else {
            u = false
        }
    }
    if (y.attr("disabled") == "disabled" || y.attr("disabled") == "true" || y.attr("disabled") == true) {
        n.attr("disabled", true);
        if (u == true) {
            n.addClass("selBtn_disabledMuiti")
        } else {
            n.addClass("selBtn_disabled")
        }
        c.addClass("selectbox_disabled")
    }
    n.attr("id", "selectTree" + selectTree_id + "_button");
    var C = 99;
    if (y.attr("selWidth") != null) {
        C = Number(y.attr("selWidth")) - 22
    }
    c.width(C);
    var o = $('<table cellspacing="0" cellpadding="0" style="border-style:none;"><tr><td class="ali01" style="border-style:none;padding:0;margin:0;"></td><td class="ali01" style="border-style:none;;padding:0;margin:0;"></td></tr></table>');
    o.find("td").eq(0).append(c);
    o.find("td").eq(1).append(n);
    y.append(o);
    y.append(w);
    y.append(E);
    var q = "";
    if (y.attr("selectedValue")) {
        q = y.attr("selectedValue")
    }
    var t = false;
    if (y.attr("editable") != null) {
        if (y.attr("editable") == "true") {
            t = true
        } else {
            t = false
        }
    }
    w.hide();
    x();
    if (!t) {
        c.css({
            cursor: "pointer"
        });
        c.click(function(G) {
            D = $(G.target).attr("id");
            i();
            depth++;
            y.css({
                zIndex: depth
            });
            if (w.attr("hasfocus") == 0) {
                v()
            } else {
                k()
            }
        })
    } else {
        c.css({
            cursor: "text"
        });
        c.change(function() {
            y.attr("editValue", $(this).val());
            E.val($(this).val())
        })
    }
    n.click(function(G) {
        e = $(G.target).attr("id");
        i();
        depth++;
        y.css({
            zIndex: depth
        });
        if (w.attr("hasfocus") == 0) {
            v()
        } else {
            k()
        }
    });
    function i() {
        w.css({
            overflowY: "visible",
            overflowX: "visible"
        });
        w.width("");
        w.height("");
        var G = 200;
        G = window.document.documentElement.clientHeight - (y.offset().top - $(window).scrollTop()) - 30;
        var I;
        if (!y.attr("boxWidth")) {
            I = w.width()
        }
        w.css({
            overflowY: "auto",
            overflowX: "hidden"
        });
        if (!y.attr("boxWidth")) {
            w.width(I)
        } else {
            w.width(Number(y.attr("boxWidth")))
        }
        var H = 0;
        if (y.attr("boxHeight")) {
            H = Number(y.attr("boxHeight"))
        }
        if (H != 0) {
            w.height(H);
            if (y.attr("openDirection") == "top") {
                w.css({
                    top: -H
                })
            } else {
                if (y.attr("openDirection") == "bottom") {
                    w.css({
                        top: A
                    })
                } else {
                    if (G < H) {
                        if (y.offset().top > H) {
                            w.css({
                                top: -H
                            })
                        } else {
                            if (G < 100 && y.offset().top > G && y.offset().top > 100) {
                                w.css({
                                    top: -H
                                })
                            } else {
                                w.css({
                                    top: A
                                })
                            }
                        }
                    } else {
                        w.css({
                            top: A
                        })
                    }
                }
            }
        } else {
            if (y.attr("openDirection") == "top") {
                if (y.offset().top > w.height()) {
                    w.css({
                        top: -w.height()
                    })
                } else {
                    w.height($mainCon.offset().top);
                    w.css({
                        top: -$mainCon.offset().top
                    })
                }
            } else {
                if (y.attr("openDirection") == "bottom") {
                    if (G < w.height()) {
                        w.css({
                            top: A
                        });
                        w.height(G)
                    } else {
                        w.css({
                            top: A
                        })
                    }
                } else {
                    if (G < w.height()) {
                        if (y.offset().top > w.height()) {
                            w.css({
                                top: -w.height()
                            })
                        } else {
                            if (G < 100 && y.offset().top > G && y.offset().top > 100) {
                                w.height(y.offset().top);
                                w.css({
                                    top: -y.offset().top
                                })
                            } else {
                                w.css({
                                    top: A
                                });
                                w.height(G)
                            }
                        }
                    } else {
                        w.css({
                            top: A
                        })
                    }
                }
            }
        }
        if (!y.attr("boxWidth")) {
            if (w.width() < C + s) {
                w.width(C + s)
            }
        }
    }
    function B() {
        var G = $("<div></div>");
        G.addClass("mainCon");
        return G
    }
    function l(G, H) {
        var I = $("<div></div>");
        I.attr("id", "selectTree" + selectTree_id + "_container");
        I.addClass(G.containerClass);
        I.attr("hasfocus", 0);
        I.html(H);
        return I
    }
    function p(H) {
        var G = document.createElement("input");
        var J = $(G);
        J.attr("id", "selectTree" + selectTree_id + "_input");
        J.attr("type", "text");
        J.addClass(H.inputClass);
        J.attr("autocomplete", "off");
        var I = false;
        if (y.attr("editable") != null) {
            if (y.attr("editable") == "true") {
                I = true
            } else {
                I = false
            }
        }
        if (!I) {
            J.attr("readonly", "readonly")
        } else {
            J.attr("readonly", false)
        }
        if (y.attr("disabled") == "disabled" || y.attr("disabled") == "true" || y.attr("disabled") == true) {
            J.attr("disabled", true);
            J.addClass("inputDisabled")
        }
        return J
    }
    function a(H) {
        var G = document.createElement("input");
        var I = $(G);
        I.attr("type", "hidden");
        if (y.attr("name") != null) {
            I.attr("name", y.attr("name"))
        }
        return I
    }
    function j(H, G) {
        y.attr("relText", H);
        y.attr("relValue", G);
        E.val(G);
        c.val(H);
        if (t == "true" || t == true) {
            y.attr("editValue", c.val());
            E.val(c.val())
        }
        y.focus();
        return true
    }
    function x() {
        if (u == true) {
            if (q == "") {
                c.val(g);
                y.attr("relText", g);
                y.attr("relValue", "");
                y.data("selectedNodes", null);
                E.val("")
            } else {
                var G = q.split(",");
                var I = "";
                if (I.length > 0) {
                    I = I.substring(0, I.length - 1)
                }
                j(I, q);
                if (y.attr("showInfo") == "false" || y.attr("showInfo") == false) {} else {
                    c.attr("title", I);
                    try {
                        addTooltip(c[0])
                    } catch(H) {}
                }
            }
        } else {
            if (q == "") {
                c.val(g);
                y.attr("relText", g);
                y.attr("relValue", "");
                y.data("selectedNode", null);
                E.val("")
            } else {
                y.attr("relValue", q);
                E.val(q)
            }
        }
        if (t == true) {
            if (q == "") {
                y.attr("editValue", g)
            } else {
                y.attr("editValue", y.attr("relText"))
            }
        }
    }
    function k() {
        w.attr("hasfocus", 0);
        w.hide();
        $("body").unbind("mousedown", h);
        y.trigger("boxClose")
    }
    function v() {
        w.attr("hasfocus", 1);
        depth++;
        y.css({
            zIndex: depth
        });
        w.show();
        y.trigger("boxOpen");
        $("body").bind("mousedown", h)
    }
    function h(G) {
        if (w.attr("hasfocus") == 0) {} else {
            if ($(G.target).attr("id") == D || $(G.target).attr("id") == e || $(G.target).parents(".selectbox-tree").length > 0) {} else {
                k()
            }
        }
    }
    function r() {
        return y.val()
    }
    function m() {
        return c.val()
    }
};
function getPosition(b, c) {
    for (var a = 0; a < c.length; a++) {
        if (b == c[a]) {
            return a;
            break
        }
    }
}
String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "")
};