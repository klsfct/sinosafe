$.fn.basicTabModernSetIdx = function(b) {
    var c = $(this);
    var g;
    var f;
    if (c.attr("iframeMode") == true || c.attr("iframeMode") == "true") {
        f = c.find("a").eq(b);
        var a = c.data("data");
        if (!a) {
            return
        }
        var e = "list";
        if (c.attr("dataRoot")) {
            e = c.attr("dataRoot")
        }
        g = a[e].length;
        var d = c.find(".basicTabModern_con").eq(0).find("iframe").eq(0);
        d.attr("src", a[e][b].url);
        f.prevAll("a").find("li").removeClass("basicTabModern_current_center");
        f.nextAll("a").find("li").removeClass("basicTabModern_current_center");
        f.find("li").addClass("basicTabModern_current_center")
    } else {
        f = c.find(".basicTabModern_normal_center").eq(b);
        g = c.find(".basicTabModern_con").length;
        c.find(".basicTabModern_con").hide();
        c.find(".basicTabModern_con").eq(b).fadeIn();
        f.prevAll("li").removeClass("basicTabModern_current_center");
        f.nextAll("li").removeClass("basicTabModern_current_center");
        f.addClass("basicTabModern_current_center")
    }
    f.prevAll("li").removeClass("basicTabModern_current_left");
    f.prevAll("li").removeClass("basicTabModern_current_right");
    f.nextAll("li").removeClass("basicTabModern_current_left");
    f.nextAll("li").removeClass("basicTabModern_current_right");
    f.prev().addClass("basicTabModern_current_left");
    f.next().addClass("basicTabModern_current_right");
    c.trigger("actived", b);
    c.attr("selectedIdx", b)
};
$.fn.basicTabModernSetEnable = function(c, b) {
    var d = $(this);
    var g;
    var f;
    if (d.attr("iframeMode") == true || d.attr("iframeMode") == "true") {
        var a = d.data("data");
        if (!a) {
            return
        }
        var e = "list";
        if (d.attr("dataRoot")) {
            e = d.attr("dataRoot")
        }
        g = a[e].length;
        f = d.find("a").eq(c);
        if (b == true) {
            f.find("li").removeClass("disabled");
            f.bideClickIframe(true, d, c, g)
        } else {
            f.find("li").addClass("disabled");
            f.bideClickIframe(false, d, c, g)
        }
    } else {
        g = d.find(".basicTabModern_con").length;
        f = d.find(".basicTabModern_normal_center").eq(c);
        if (b == true) {
            f.removeClass("disabled");
            f.bideClick(true, d, c, g)
        } else {
            f.addClass("disabled");
            f.bideClick(false, d, c, g)
        }
    }
};
$.fn.basicTabModernRender = function() {
    var l = $(this);
    var h;
    var i = false;
    if (l.attr("showName") == true || l.attr("showName") == "true") {
        i = true
    }
    var o;
    var f;
    if (l.attr("tabTitle")) {
        o = $('<div class="basicTabModern_top"><div class="basicTabModern_top_left"><div class="basicTabModern_top_right"><span class="basicTabModern_title"></span><div class="basicTabModern_tabcon"></div><div class="clear"></div></div></div></div>');
        f = o.find(".basicTabModern_tabcon");
        var t = o.find(".basicTabModern_title");
        t.html(l.attr("tabTitle"));
        if (l.attr("iconClass") != null) {
            t.addClass(l.attr("iconClass"));
            t.css({
                backgroundPosition: "0 60%"
            })
        } else {
            if (l.attr("iconSrc") != null) {
                t.css({
                    backgroundImage: "url(" + $(this).attr("iconSrc") + ")",
                    backgroundRepeat: "no-repeat",
                    backgroundPosition: "0 60%",
                    display: "block",
                    paddingLeft: "18px"
                })
            }
        }
        o.find(".basicTabModern_top_right").css("paddingLeft", "10px")
    } else {
        o = $('<div class="basicTabModern_top"><div class="basicTabModern_top_left"><div class="basicTabModern_top_right"></div></div></div>');
        f = o.find(".basicTabModern_top_right")
    }
    var a = 0;
    if (l.attr("selectedIdx")) {
        a = Number(l.attr("selectedIdx"))
    } else {
        l.attr("selectedIdx", 0)
    }
    var g = true;
    if (l.attr("allItemDisabled") == true || l.attr("allItemDisabled") == "true") {
        g = false
    }
    var q = null;
    var j;
    var k = false;
    if (l.attr("showProgress") == true || l.attr("showProgress") == "true") {
        k = true
    }
    if (l.attr("iframeMode") == true || l.attr("iframeMode") == "true" || l.attr("singleContentMode") == true || l.attr("singleContentMode") == "true") {
        if (l.find(">div").length > 1) {
            alert("iframe模式或单内容区域模式下只允许有一个内容所在容器！")
        }
        l.find(">div").addClass("basicTabModern_con");
        l.find(">div").css({
            overflowX: "hidden",
            overflowY: "auto"
        });
        j = l.find(">div").eq(0).find("iframe").eq(0);
        if (j.attr("name")) {
            q = j.attr("name")
        }
        var c = "list";
        if (l.attr("dataRoot")) {
            c = l.attr("dataRoot")
        }
        var b = l.attr("params");
        var u;
        if (b) {
            try {
                u = JSON.parse(b)
            } catch(r) {
                u = "";
                alert("参数格式有误！（提示：json数据的属性和名称必须以双引号包围）")
            }
        } else {
            u = ""
        }
        var m = "";
        var p = l.attr("url");
        var s = l.attr("data");
        var d = l.data("data");
        if (d) {
            n(d)
        } else {
            if (s) {
                try {
                    m = JSON.parse(s)
                } catch(r) {
                    m = "";
                    alert("参数格式有误！（提示：放在标签中的json数据的属性和名称必须以双引号包围）")
                }
                l.data("data", m);
                n(m)
            } else {
                if (p) {
                    $.ajax({
                        url: l.attr("url"),
                        dataType: "json",
                        data: u,
                        error: function() {
                            alert("数据源出错，请检查url路径")
                        },
                        success: function(e) {
                            l.data("data", e);
                            m = e;
                            n(e)
                        }
                    })
                }
            }
        }
    } else {
        h = l.find(">div").length;
        l.find(">div").each(function(y) {
            var z = "未命名";
            if ($(this).attr("name")) {
                z = $(this).attr("name")
            }
            $(this).addClass("basicTabModern_con");
            if (a != y) {
                $(this).hide()
            }
            var x = $('<li class="basicTabModern_normal_left"></li>');
            f.append(x);
            if (a == y) {
                x.addClass("basicTabModern_current_left")
            }
            var v = $('<li class="basicTabModern_normal_center"></li>');
            f.append(v);
            v.text(z);
            v.data("idx", y);
            if (!g) {
                v.addClass("disabled")
            }
            var A = true;
            if ($(this).attr("itemDisabled") == "true" || $(this).attr("itemDisabled") == true) {
                v.addClass("disabled");
                A = false
            }
            if (a == y) {
                v.addClass("basicTabModern_current_center")
            }
            var e = $('<li class="basicTabModern_normal_right"></li>');
            f.append(e);
            if (a == y) {
                e.addClass("basicTabModern_current_right")
            }
            if (h == 1) {
                v.css("cursor", "default");
                if (l.attr("tabSubTitle") != null) {
                    var B = $("<div></div>");
                    B.html(l.attr("tabSubTitle"));
                    B.addClass("basicTabModern_subTitle");
                    e.after(B)
                }
                if (l.attr("statusText") != null) {
                    var C = $("<div></div>");
                    if (l.attr("tabUrl") != null) {
                        var w = $("<a></a>");
                        w.html(l.attr("statusText"));
                        w.attr("href", l.attr("tabUrl"));
                        if (l.attr("tabTarget") != null) {
                            w.attr("target", l.attr("tabTarget"))
                        }
                        C.append(w)
                    } else {
                        C.html(l.attr("statusText"))
                    }
                    C.addClass("basicTabModern_status");
                    f.append(C)
                }
            } else {
                if (g && A) {
                    if (l.attr("hoverMode") == true || l.attr("hoverMode") == "true") {
                        v.bideOver(true, l, y, h)
                    } else {
                        v.bideClick(true, l, y, h)
                    }
                }
            }
        })
    }
    f.append($('<div class="clear"></div>'));
    l.prepend(o);
    function n(e) {
        if (!e) {
            return
        }
        h = e[c].length;
        $.each(e[c],
        function(x, A) {
            var C = "未命名";
            if (A.name) {
                C = A.name
            }
            if (a == x) {
                j.attr("src", A.url)
            }
            var z = $('<li class="basicTabModern_normal_left"></li>');
            f.append(z);
            if (a == x) {
                z.addClass("basicTabModern_current_left")
            }
            var y = $('<li class="basicTabModern_normal_center"></li>');
            var B = $("<a></a>");
            if (A.url != null) {
                B.attr("href", A.url)
            }
            if (q != null) {
                B.attr("target", q)
            }
            y.text(C);
            B.append(y);
            f.append(B);
            if (!g) {
                y.addClass("disabled")
            }
            var v = true;
            if (A.itemDisabled) {
                if (A.itemDisabled == "true" || A.itemDisabled == true) {
                    y.addClass("disabled");
                    v = false
                }
            }
            y.data("idx", x);
            if (a == x) {
                y.addClass("basicTabModern_current_center")
            }
            var w = $('<li class="basicTabModern_normal_right"></li>');
            f.append(w);
            if (a == x) {
                w.addClass("basicTabModern_current_right")
            }
            if (g && v) {
                B.bideClickIframe(true, l, x, h)
            } else {
                B.bideClickIframe(false, l, x, h)
            }
        })
    }
};
$.fn.bideClick = function(b, d, c, e) {
    var a = $(this);
    a.unbind("click");
    if (b) {
        a.bind("click", {},
        function() {
            d.find(".basicTabModern_con").hide();
            d.find(".basicTabModern_con").eq(c).fadeIn();
            $(this).prevAll("li").removeClass("basicTabModern_current_left");
            $(this).prevAll("li").removeClass("basicTabModern_current_center");
            $(this).prevAll("li").removeClass("basicTabModern_current_right");
            $(this).nextAll("li").removeClass("basicTabModern_current_left");
            $(this).nextAll("li").removeClass("basicTabModern_current_center");
            $(this).nextAll("li").removeClass("basicTabModern_current_right");
            $(this).addClass("basicTabModern_current_center");
            $(this).prev().addClass("basicTabModern_current_left");
            $(this).next().addClass("basicTabModern_current_right");
            d.trigger("actived", c);
            d.attr("selectedIdx", c)
        })
    } else {}
};
$.fn.bideOver = function(b, d, c, e) {
    var a = $(this);
    a.unbind("mouseover");
    if (b) {
        a.bind("mouseover", {},
        function() {
            d.find(".basicTabModern_con").hide();
            d.find(".basicTabModern_con").eq(c).fadeIn();
            $(this).prevAll("li").removeClass("basicTabModern_current_left");
            $(this).prevAll("li").removeClass("basicTabModern_current_center");
            $(this).prevAll("li").removeClass("basicTabModern_current_right");
            $(this).nextAll("li").removeClass("basicTabModern_current_left");
            $(this).nextAll("li").removeClass("basicTabModern_current_center");
            $(this).nextAll("li").removeClass("basicTabModern_current_right");
            $(this).addClass("basicTabModern_current_center");
            $(this).prev().addClass("basicTabModern_current_left");
            $(this).next().addClass("basicTabModern_current_right");
            d.trigger("actived", c);
            d.attr("selectedIdx", c)
        })
    } else {}
};
$.fn.bideClickIframe = function(b, d, c, e) {
    var a = $(this);
    a.unbind("click");
    if (b) {
        a.bind("click", {},
        function() {
            $(this).prevAll("li").removeClass("basicTabModern_current_left");
            $(this).prevAll("li").removeClass("basicTabModern_current_right");
            $(this).prevAll("a").find("li").removeClass("basicTabModern_current_center");
            $(this).nextAll("li").removeClass("basicTabModern_current_left");
            $(this).nextAll("li").removeClass("basicTabModern_current_right");
            $(this).nextAll("a").find("li").removeClass("basicTabModern_current_center");
            $(this).find("li").addClass("basicTabModern_current_center");
            $(this).prev().addClass("basicTabModern_current_left");
            $(this).next().addClass("basicTabModern_current_right");
            if (d.attr("showProgress") == "true" || d.attr("showProgress") == true) {
                try {
                    showProgressBar()
                } catch(f) {}
            }
            d.trigger("actived", c);
            d.attr("selectedIdx", c)
        })
    } else {
        a.bind("click", {},
        function() {
            return false
        })
    }
};