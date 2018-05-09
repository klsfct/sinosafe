jQuery.fn.extend({
    spliterClose: function() {
        var a = $(this);
        if (a.spliterGetState()) {
            a.find(">div").click()
        }
    },
    spliterOpen: function() {
        var a = $(this);
        if (!a.spliterGetState()) {
            a.find(">div").click()
        }
    },
    spliterGetState: function() {
        var c = $(this);
        var b;
        var a;
        if (c.attr("targetId")) {
            a = c.attr("targetId")
        } else {
            alert("请指定要收缩区域")
        }
        if ($("#" + a)[0].style.display == "none") {
            b = false
        } else {
            b = true
        }
        return b
    },
    spliterChangeState: function() {
        $(this).find(">div").click()
    },
    spliterRender: function() {
        $(this).empty();
        var d = $(this);
        var c = "spliterLeft";
        var h = "spliterRight";
        var f = "收缩面板";
        var b = "展开面板";
        var a;
        var g = "show";
        if (d.attr("beforeClickClass")) {
            c = d.attr("beforeClickClass")
        }
        if (d.attr("afterClickClass")) {
            h = d.attr("afterClickClass")
        }
        if (d.attr("beforeClickTip")) {
            f = d.attr("beforeClickTip")
        }
        if (d.attr("afterClickTip")) {
            b = d.attr("afterClickTip")
        }
        if (d.attr("targetId")) {
            a = d.attr("targetId")
        } else {
            alert("请指定要收缩区域")
        }
        if (d.attr("init") == "hide") {
            g = "hide"
        }
        var e = $("<div></div>");
        e.addClass(c);
        e.attr("title", f);
        d.empty();
        d.append(e);
        d.addClass("ver02");
        d.addClass("ali02");
        if (g == "hide") {
            $("#" + a).hide();
            $("#" + a).attr("trueHeight", 0)
        } else {}
        e.toggle(function() {
            if (g == "show") {
                $("#" + a).hide(200,
                function() {
                    $("#" + a).attr("trueHeight", 0);
                    d.trigger("stateChange", "hide");
                    try {
                        customHeightSet()
                    } catch(i) {}
                })
            } else {
                $("#" + a).show(0,
                function() {
                    $("#" + a).attr("trueHeight", $("#" + a).height());
                    d.trigger("stateChange", "show");
                    try {
                        customHeightSet()
                    } catch(i) {}
                })
            }
            $(this).removeClass(c);
            $(this).addClass(h);
            $(this).attr("title", b)
        },
        function() {
            if (g == "show") {
                $("#" + a).show(0,
                function() {
                    $("#" + a).attr("trueHeight", $("#" + a).height());
                    d.trigger("stateChange", "show");
                    try {
                        customHeightSet()
                    } catch(i) {}
                })
            } else {
                $("#" + a).hide(200,
                function() {
                    $("#" + a).attr("trueHeight", 0);
                    d.trigger("stateChange", "hide");
                    try {
                        customHeightSet()
                    } catch(i) {}
                })
            }
            $(this).removeClass(h);
            $(this).addClass(c);
            $(this).attr("title", f)
        })
    }
});