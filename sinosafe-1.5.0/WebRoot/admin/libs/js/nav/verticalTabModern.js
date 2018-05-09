$.fn.verticalTabModernSetIdx = function(b) {
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
        var d = c.find(".verticalTabModern_con").eq(0).find("iframe").eq(0);
        d.attr("src", a[e][b].url);
        f.prevAll("a").find("li").removeClass("verticalTabModern_current_center");
        f.nextAll("a").find("li").removeClass("verticalTabModern_current_center");
        f.find("li").addClass("verticalTabModern_current_center")
    } else {
        f = c.find(".verticalTabModern_normal_center").eq(b);
        g = c.find(".verticalTabModern_con").length;
        c.find(".verticalTabModern_con").hide();
        c.find(".verticalTabModern_con").eq(b).fadeIn();
        f.prevAll("li").removeClass("verticalTabModern_current_center");
        f.nextAll("li").removeClass("verticalTabModern_current_center");
        f.addClass("verticalTabModern_current_center")
    }
    f.prevAll("li").removeClass("verticalTabModern_current_left");
    f.prevAll("li").removeClass("verticalTabModern_current_middle");
    f.prevAll("li").removeClass("verticalTabModern_current_middle2");
    f.prevAll("li").removeClass("verticalTabModern_current_right");
    f.nextAll("li").removeClass("verticalTabModern_current_left");
    f.nextAll("li").removeClass("verticalTabModern_current_middle");
    f.nextAll("li").removeClass("verticalTabModern_current_middle2");
    f.nextAll("li").removeClass("verticalTabModern_current_right");
    if (b == 0) {
        f.prev().addClass("verticalTabModern_current_left");
        f.next().addClass("verticalTabModern_current_middle")
    } else {
        if (b == g - 1) {
            f.prev().addClass("verticalTabModern_current_middle2");
            f.next().addClass("verticalTabModern_current_right")
        } else {
            f.prev().addClass("verticalTabModern_current_middle2");
            f.next().addClass("verticalTabModern_current_middle")
        }
    }
    c.trigger("actived", b);
    c.attr("selectedIdx", b)
};
$.fn.verticalTabModernSetEnable = function(c, b) {
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
        g = d.find(".verticalTabModern_con").length;
        f = d.find(".verticalTabModern_normal_center").eq(c);
        if (b == true) {
            f.removeClass("disabled");
            f.bideClick(true, d, c, g)
        } else {
            f.addClass("disabled");
            f.bideClick(false, d, c, g)
        }
    }
};
$.fn.verticalTabModernRender = function() {
    var c = $(this);
    var q;
    var f = $('<div class="verticalTabModern_top"></div>');
    var h = 0;
    if (c.attr("selectedIdx")) {
        h = Number(c.attr("selectedIdx"))
    } else {
        c.attr("selectedIdx", 0)
    }
    var m = true;
    if (c.attr("allItemDisabled") == true || c.attr("allItemDisabled") == "true") {
        m = false
    }
    var b = null;
    var r;
    var k = false;
    if (c.attr("showProgress") == true || c.attr("showProgress") == "true") {
        k = true
    }
    if (c.attr("iframeMode") == true || c.attr("iframeMode") == "true" || c.attr("singleContentMode") == true || c.attr("singleContentMode") == "true") {
        if (c.find(">div").length > 1) {
            alert("iframe模式或单内容区域模式下只允许有一个内容所在容器！")
        }
        c.find(">div").addClass("verticalTabModern_con");
        c.find(">div").css({
            overflowX: "hidden",
            overflowY: "auto"
        });
        r = c.find(">div").eq(0).find("iframe").eq(0);
        if (r.attr("name")) {
            b = r.attr("name")
        }
        var p = "list";
        if (c.attr("dataRoot")) {
            p = c.attr("dataRoot")
        }
        var n = c.attr("params");
        var j;
        if (n) {
            try {
                j = JSON.parse(n)
            } catch(l) {
                j = [];
                alert("参数格式有误！（提示：json数据的属性和名称必须以双引号包围）")
            }
        } else {
            j = []
        }
        var a = [];
        var i = c.attr("url");
        var d = c.attr("data");
        var g = c.data("data");
        if (g) {
            o(g)
        } else {
            if (d) {
                try {
                    a = JSON.parse(d)
                } catch(l) {
                    a = [];
                    alert("数据格式有误！（提示：放在标签中的json数据的属性和名称必须以双引号包围）")
                }
                c.data("data", a);
                o(a)
            } else {
                if (i) {
                    $.ajax({
                        url: c.attr("url"),
                        dataType: "json",
                        data: j,
                        error: function() {
                            alert("数据源出错，请检查url路径")
                        },
                        success: function(e) {
                            c.data("data", e);
                            a = e;
                            o(e)
                        }
                    })
                }
            }
        }
    } else {
        q = c.find(">div").length;
        c.find(">div").each(function(v) {
            var y = "未命名";
            if ($(this).attr("name")) {
                y = $(this).attr("name")
            }
            $(this).addClass("verticalTabModern_con");
            if (h != v) {
                $(this).hide()
            }
            if (v == 0) {
                var x = $('<li class="verticalTabModern_normal_left"></li>');
                f.append(x);
                if (h == v) {
                    x.addClass("verticalTabModern_current_left")
                }
            }
            var w = $('<li class="verticalTabModern_normal_center"></li>');
            f.append(w);
            var u = $('<div class="tab_title_content"></div>');
            u.text(y);
            w.append(u);
            w.data("idx", v);
            if (!m) {
                w.addClass("disabled")
            }
            var e = true;
            if ($(this).attr("itemDisabled") == "true" || $(this).attr("itemDisabled") == true) {
                w.addClass("disabled");
                e = false
            }
            if (h == v) {
                w.addClass("verticalTabModern_current_center")
            }
            if (v == q - 1) {
                var t = $('<li class="verticalTabModern_normal_right"></li>');
                f.append(t);
                if (h == v) {
                    t.addClass("verticalTabModern_current_right")
                }
            } else {
                var s = $('<li class="verticalTabModern_normal_middle"></li>');
                f.append(s);
                if (h == v) {
                    s.addClass("verticalTabModern_current_middle")
                } else {
                    if (h == v + 1) {
                        s.addClass("verticalTabModern_current_middle2")
                    }
                }
            }
            if (m && e) {
                if (c.attr("hoverMode") == true || c.attr("hoverMode") == "true") {
                    w.bideOver(true, c, v, q)
                } else {
                    w.bideClick(true, c, v, q)
                }
            }
        })
    }
    c.append($('<div class="clear"></div>'));
    c.prepend(f);
    function o(e) {
        if (!e) {
            return
        }
        q = e[p].length;
        $.each(e[p],
        function(x, B) {
            var y = "未命名";
            if (B.name) {
                y = B.name
            }
            if (h == x) {
                r.attr("src", B.url)
            }
            if (x == 0) {
                var w = $('<li class="verticalTabModern_normal_left"></li>');
                f.append(w);
                f.append('<div class="clear"></div>');
                if (h == x) {
                    w.addClass("verticalTabModern_current_left")
                }
            }
            var u = $('<li class="verticalTabModern_normal_center"></li>');
            var v = $("<a></a>");
            if (B.url != null) {
                v.attr("href", B.url)
            }
            if (b != null) {
                v.attr("target", b)
            }
            var z = $('<div class="tab_title_content"></div>');
            z.text(y);
            u.append(z);
            v.append(u);
            f.append(v);
            f.append('<div class="clear"></div>');
            if (!m) {
                u.addClass("disabled")
            }
            var A = true;
            if (B.itemDisabled) {
                if (B.itemDisabled == "true" || B.itemDisabled == true) {
                    u.addClass("disabled");
                    A = false
                }
            }
            u.data("idx", x);
            if (h == x) {
                u.addClass("verticalTabModern_current_center")
            }
            if (x == q - 1) {
                var t = $('<li class="verticalTabModern_normal_right"></li>');
                f.append(t);
                f.append('<div class="clear"></div>');
                if (h == x) {
                    t.addClass("verticalTabModern_current_right")
                }
            } else {
                var s = $('<li class="verticalTabModern_normal_middle"></li>');
                f.append(s);
                f.append('<div class="clear"></div>');
                if (h == x) {
                    s.addClass("verticalTabModern_current_middle")
                } else {
                    if (h == x + 1) {
                        s.addClass("verticalTabModern_current_middle2")
                    }
                }
            }
            if (m && A) {
                v.bideClickIframe(true, c, x, q)
            } else {
                v.bideClickIframe(false, c, x, q)
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
            d.find(".verticalTabModern_con").hide();
            d.find(".verticalTabModern_con").eq(c).fadeIn();
            $(this).prevAll("li").removeClass("verticalTabModern_current_left");
            $(this).prevAll("li").removeClass("verticalTabModern_current_center");
            $(this).prevAll("li").removeClass("verticalTabModern_current_middle");
            $(this).prevAll("li").removeClass("verticalTabModern_current_middle2");
            $(this).prevAll("li").removeClass("verticalTabModern_current_right");
            $(this).nextAll("li").removeClass("verticalTabModern_current_left");
            $(this).nextAll("li").removeClass("verticalTabModern_current_center");
            $(this).nextAll("li").removeClass("verticalTabModern_current_middle");
            $(this).nextAll("li").removeClass("verticalTabModern_current_middle2");
            $(this).nextAll("li").removeClass("verticalTabModern_current_right");
            $(this).addClass("verticalTabModern_current_center");
            if (c == 0) {
                $(this).prev().addClass("verticalTabModern_current_left");
                $(this).next().addClass("verticalTabModern_current_middle")
            } else {
                if (c == e - 1) {
                    $(this).prev().addClass("verticalTabModern_current_middle2");
                    $(this).next().addClass("verticalTabModern_current_right")
                } else {
                    $(this).prev().addClass("verticalTabModern_current_middle2");
                    $(this).next().addClass("verticalTabModern_current_middle")
                }
            }
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
            d.find(".verticalTabModern_con").hide();
            d.find(".verticalTabModern_con").eq(c).fadeIn();
            $(this).prevAll("li").removeClass("verticalTabModern_current_left");
            $(this).prevAll("li").removeClass("verticalTabModern_current_center");
            $(this).prevAll("li").removeClass("verticalTabModern_current_middle");
            $(this).prevAll("li").removeClass("verticalTabModern_current_middle2");
            $(this).prevAll("li").removeClass("verticalTabModern_current_right");
            $(this).nextAll("li").removeClass("verticalTabModern_current_left");
            $(this).nextAll("li").removeClass("verticalTabModern_current_center");
            $(this).nextAll("li").removeClass("verticalTabModern_current_middle");
            $(this).nextAll("li").removeClass("verticalTabModern_current_middle2");
            $(this).nextAll("li").removeClass("verticalTabModern_current_right");
            $(this).addClass("verticalTabModern_current_center");
            if (c == 0) {
                $(this).prev().addClass("verticalTabModern_current_left");
                $(this).next().addClass("verticalTabModern_current_middle")
            } else {
                if (c == e - 1) {
                    $(this).prev().addClass("verticalTabModern_current_middle2");
                    $(this).next().addClass("verticalTabModern_current_right")
                } else {
                    $(this).prev().addClass("verticalTabModern_current_middle2");
                    $(this).next().addClass("verticalTabModern_current_middle")
                }
            }
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
            $(this).prevAll("li").removeClass("verticalTabModern_current_left");
            $(this).prevAll("li").removeClass("verticalTabModern_current_middle");
            $(this).prevAll("li").removeClass("verticalTabModern_current_middle2");
            $(this).prevAll("li").removeClass("verticalTabModern_current_right");
            $(this).prevAll("a").find("li").removeClass("verticalTabModern_current_center");
            $(this).nextAll("li").removeClass("verticalTabModern_current_left");
            $(this).nextAll("li").removeClass("verticalTabModern_current_middle");
            $(this).nextAll("li").removeClass("verticalTabModern_current_middle2");
            $(this).nextAll("li").removeClass("verticalTabModern_current_right");
            $(this).nextAll("a").find("li").removeClass("verticalTabModern_current_center");
            $(this).find("li").addClass("verticalTabModern_current_center");
            if (c == 0) {
                $(this).prevAll("li").eq(0).addClass("verticalTabModern_current_left");
                $(this).nextAll("li").eq(0).addClass("verticalTabModern_current_middle")
            } else {
                if (c == e - 1) {
                    $(this).prevAll("li").eq(0).addClass("verticalTabModern_current_middle2");
                    $(this).nextAll("li").eq(0).addClass("verticalTabModern_current_right")
                } else {
                    $(this).prevAll("li").eq(0).addClass("verticalTabModern_current_middle2");
                    $(this).nextAll("li").eq(0).addClass("verticalTabModern_current_middle")
                }
            }
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