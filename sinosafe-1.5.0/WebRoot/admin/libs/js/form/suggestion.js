var parentTopHeight;
var broswerFlag;
jQuery.fn.extend({
    suggestionRender: function() {
        return this.each(function() {
            $(this).empty();
            new jQuery.suggestionBox(this)
        })
    }
});
var selectTree_id = 1;
jQuery.suggestionBox = function(av) {
    var ai = {};
    ai.inputClass = ai.inputClass || "suggestion_input";
    ai.containerClass = ai.containerClass || "selectbox-tree";
    ai.hoverClass = ai.hoverClass || "current";
    ai.currentClass = ai.selectedClass || "selected";
    ai.debug = ai.debug || false;
    var ao = 24;
    var at = 24;
    if (!splitMode) {
        var n = $(window.top.document.getElementById("theme"));
        if (n.attr("selInputHeight") != null) {
            ao = Number(n.attr("selInputHeight"))
        }
        if (n.attr("selButtonWidth") != null) {
            at = Number(n.attr("selButtonWidth"))
        }
    }
    selectTree_id++;
    var P = "0_input";
    var X = "0_button";
    var N = false;
    var m = false;
    var q = false;
    var ar = false;
    var I = $(av);
    I.addClass("mainCon");
    var k = "请选择或输入名称或拼音";
    if (I.attr("popTitle") != null) {
        k = I.attr("popTitle")
    }
    var z = false;
    if (I.attr("showMultiList") == true || I.attr("showMultiList") == "true") {
        z = true
    }
    var K = false;
    if (I.attr("showList") == true || I.attr("showList") == "true") {
        K = true
    }
    var W = "请输入";
    if (I.attr("prompt") != null) {
        W = I.attr("prompt")
    } else {
        if (K || z) {
            W = "请选择或输入"
        }
    }
    var A = "";
    if (I.attr("selectedValue")) {
        A = I.attr("selectedValue")
    }
    var E = 10;
    if (I.attr("pageNum") != null) {
        E = Number(I.attr("pageNum"))
    }
    var w = 5;
    if (I.attr("pageCount") != null) {
        w = Number(I.attr("pageCount"))
    }
    var h = 50;
    if (I.attr("colWidth") != null) {
        h = Number(I.attr("colWidth"))
    }
    var F = "↑↓选择← →分页";
    if (I.attr("suggestTitle") != null) {
        F = I.attr("suggestTitle")
    }
    var c = "您输入的不合法";
    if (I.attr("errorMsg") != null) {
        c = I.attr("errorMsg")
    }
    var g;
    if (I.attr("iconSrc") != null) {
        g = I.attr("iconSrc")
    }
    var a = true;
    if (I.attr("autoCheck") == false || I.attr("autoCheck") == "false") {
        a = false
    }
    var C = false;
    if (I.attr("errorAlert") == true || I.attr("errorAlert") == "true") {
        C = true
    }
    var ab = "";
    var G = "local";
    if (I.attr("suggestMode") == "remote") {
        G = "remote"
    }
    var s = 1;
    if (I.attr("minChars") != null) {
        s = Number(I.attr("minChars"))
    }
    var b = 0;
    if (I.attr("delay") != null) {
        b = Number(I.attr("delay"))
    }
    var v = "q";
    if (I.attr("matchName") != null) {
        v = I.attr("matchName")
    }
    var aj = x(ai);
    var S = aj.find(".pop_city_container");
    var o = aj.find(".list_label");
    var ac = Z(ai);
    var ad = au(ai);
    var Q = ah(ai);
    var H;
    if (I.attr("inputWidth") != null) {
        H = Number(I.attr("inputWidth"));
        ad.width(H)
    } else {
        H = ad.width()
    }
    if (I.attr("inputHeight") != null) {
        ao = Number(I.attr("inputHeight"));
        ad.height(ao)
    }
    I.append(ad);
    I.append(aj);
    I.append(ac);
    I.append(Q);
    aj.hide();
    ac.hide();
    var u = "list";
    if (I.attr("dataRoot")) {
        u = I.attr("dataRoot")
    }
    var d = I.attr("params");
    var j = {};
    if (d) {
        try {
            j = JSON.parse(d)
        } catch(L) {
            j = [];
            alert("自动提示框参数格式有误！（提示：json数据的属性和名称必须以双引号包围）")
        }
    }
    var Y = I.attr("url");
    if (G == "local") {
        var f = "";
        var ak = I.data("data");
        var B = null;
        if (I.data("tab") != null) {
            B = I.data("tab")
        }
        var l = null;
        if (I.data("list") != null) {
            l = I.data("list")
        }
        if (ak) {
            if (z) {
                p(ak, B)
            }
            if (A == "") {
                if ($.trim(ad.val()) == "" || $.trim(ad.val()) == W) {
                    ad.val(W).css("color", "#aaa")
                }
                I.attr("relText", "");
                I.attr("relValue", "");
                Q.val("")
            } else {
                var l = I.data("data");
                if (l) {
                    for (var J = 0; J < l[u].length; J++) {
                        if (l[u][J].value == A) {
                            R(l[u][J].key, A);
                            break
                        }
                    }
                }
            }
        } else {
            if (Y) {
                $.ajax({
                    url: I.attr("url"),
                    dataType: "json",
                    data: j,
                    error: function() {
                        alert("自动提示框数据源出错，请检查url路径")
                    },
                    success: function(az) {
                        I.data("data", az);
                        f = az;
                        if (z) {
                            p(az, B)
                        }
                        if (A == "") {
                            if ($.trim(ad.val()) == "" || $.trim(ad.val()) == W) {
                                ad.val(W).css("color", "#aaa")
                            }
                            I.attr("relText", "");
                            I.attr("relValue", "");
                            Q.val("")
                        } else {
                            var ay = I.data("data");
                            if (ay) {
                                for (var e = 0; e < ay[u].length; e++) {
                                    if (ay[u][e].value == A) {
                                        R(ay[u][e].key, A);
                                        break
                                    }
                                }
                            }
                        }
                    }
                })
            }
        }
    } else {
        if (I.attr("relValue") && I.attr("relValue") != "") {
            Q.val(I.attr("relValue"));
            ad.val(I.attr("relText")).css("color", "#000000")
        } else {
            if ($.trim(ad.val()) == "" || $.trim(ad.val()) == W) {
                ad.val(W).css("color", "#aaa")
            }
            I.attr("relText", "");
            I.attr("relValue", "");
            Q.val("")
        }
    }
    ad.focus(function() {
        if (ar) {
            ar = false;
            return true
        }
        if ($.trim($(this).val()) == W) {
            $(this).val("").css("color", "#000")
        }
    }).click(function() {
        if (ar) {
            ar = false;
            return
        }
        if (G == "local") {
            an();
            T();
            if (z) {
                O()
            } else {
                if (K) {
                    aw();
                    return
                }
            }
        }
    }).blur(function() {
        if (m == false) {
            aq()
        }
        if (q == false) {
            an()
        }
        if ($.trim(ad.val()) == "" || $.trim(ad.val()) == W) {
            R(W, "");
            setTimeout(function() {
                I.trigger("validate2")
            },
            500)
        } else {
            if (G == "local") {
                if (a) {
                    setTimeout(function() {
                        if (ac[0].style.display == "none" && aj[0].style.display == "none") {
                            var ay = ad.val();
                            var aB = false;
                            var aA = I.data("data");
                            if (aA) {
                                for (var az = 0; az < aA[u].length; az++) {
                                    if (aA[u][az].key == ay) {
                                        R(ay, aA[u][az].value);
                                        aB = true;
                                        break
                                    }
                                }
                                if (!aB) {
                                    if (C) {
                                        try {
                                            top.Dialog.alert(c)
                                        } catch(aC) {
                                            alert(c)
                                        }
                                    }
                                    R(W, "")
                                }
                            }
                        }
                    },
                    500)
                }
            } else {
                I.attr("relText", ad.val())
            }
            setTimeout(function() {
                I.trigger("validate", $.trim(ad.val()))
            },
            500)
        }
    });
    o.find("a").bind("click",
    function() {
        ad.focus();
        m = true;
        var e = $(this).attr("id");
        o.find("li a").removeClass("current");
        $(this).addClass("current");
        S.find("ul").hide();
        $("#" + e + "_container").show()
    });
    S.find("a").bind("click",
    function() {
        R($(this).text(), $(this).attr("rel"));
        I.trigger("multiListSelect", $(this).attr("rel"), $(this).text());
        aq()
    });
    aj.mouseover(function() {
        m = true
    }).mouseout(function() {
        m = false
    });
    ad.keydown(function(e) {
        aq();
        e = window.event || e;
        var i = e.keyCode || e.which || e.charCode;
        if (i == 37) {
            t()
        } else {
            if (i == 39) {
                ax()
            } else {
                if (i == 38) {
                    ag()
                } else {
                    if (i == 40) {
                        am()
                    }
                }
            }
        }
    }).keypress(function(i) {
        i = window.event || i;
        var ay = i.keyCode || i.which || i.charCode;
        if (13 == ay) {
            if (ac.find(".list_city_container a.selected").length > 0) {
                var e = ac.find(".list_city_container a.selected");
                R(e.text(), e.attr("rel"));
                I.trigger("listSelect", {
                    relValue: e.attr("rel"),
                    relText: e.text()
                });
                an()
            }
        }
    }).keyup(function(e) {
        e = window.event || e;
        var i = e.keyCode || e.which || e.charCode;
        if (i != 13 && i != 37 && i != 39 && i != 9 && i != 38 && i != 40) {
            M()
        }
    });
    function ax() {
        var e = ac.find(".page_break a.current").next();
        if (e != null) {
            if ($(e).attr("inum") != null) {
                y($(e).attr("inum"))
            }
        }
    }
    function t() {
        var e = ac.find(".page_break a.current").prev();
        if (e != null) {
            if ($(e).attr("inum") != null) {
                y($(e).attr("inum"))
            }
        }
    }
    function am() {
        var i = ac.find(".list_city_container a").index(ac.find(".list_city_container a.selected")[0]);
        ac.find(".list_city_container").children().removeClass("selected");
        i += 1;
        var e = ac.find(".list_city_container a").index(ac.find(".list_city_container a:visible").filter(":last")[0]);
        if (i > e) {
            i = ac.find(".list_city_container a").index(ac.find(".list_city_container a:visible").eq(0))
        }
        ac.find(".list_city_container a").eq(i).addClass("selected")
    }
    function ag() {
        var i = ac.find(".list_city_container a").index(ac.find(".list_city_container a.selected")[0]);
        ac.find(".list_city_container").children().removeClass("selected");
        i -= 1;
        var e = ac.find(".list_city_container a").index(ac.find(".list_city_container a:visible").filter(":first")[0]);
        if (i < e) {
            i = ac.find(".list_city_container a").index(ac.find(".list_city_container a:visible").filter(":last")[0])
        }
        ac.find(".list_city_container a").eq(i).addClass("selected")
    }
    function aw() {
        var i = ac.find(".list_city_container");
        i.empty();
        var e;
        if (l) {
            e = l
        } else {
            e = I.data("data")
        }
        if (e) {
            $.each(e[u],
            function(ay, aA) {
                var az = $("<a href='javascript:void(0)' style='display:none'><span>" + aA.key + "</span></a>");
                az.attr("rel", aA.value);
                i.append(az);
                az.bind("click",
                function() {
                    R($(this).text(), $(this).attr("rel"));
                    I.trigger("listSelect", {
                        relValue: $(this).attr("rel"),
                        relText: $(this).text()
                    });
                    an()
                }).bind("mouseover",
                function() {
                    q = true
                }).bind("mouseout",
                function() {
                    q = false
                })
            })
        }
        ac.find(".list_city_head").html(F);
        y(1);
        V();
        r();
        ap()
    }
    function M() {
        if (G == "local") {
            aq();
            var aD = ad.val().toLowerCase();
            if (aD.length == 0) {
                if (z) {
                    O();
                    an();
                    T()
                } else {
                    aw()
                }
                return
            }
            V();
            r();
            var i = ac.find(".list_city_container");
            var aC = false;
            var az = new Array();
            var aB = I.data("data");
            if (aB) {
                $.each(aB[u],
                function(aE, aG) {
                    if (aG.suggest) {
                        var aH = aG.suggest.split("|");
                        for (var aF = 0; aF < aH.length; aF++) {
                            if (aH[aF].indexOf(aD) >= 0) {
                                aC = true;
                                az.push(aG);
                                break
                            }
                        }
                    }
                })
            }
            if (aC) {
                i.empty();
                for (var aA in az) {
                    var ay = az[aA];
                    var e = $("<a href='javascript:void(0)' style='display:none'><span>" + ay.key + "</span></a>");
                    e.attr("rel", ay.value);
                    i.append(e);
                    e.bind("click",
                    function() {
                        R($(this).text(), $(this).attr("rel"));
                        I.trigger("listSelect", {
                            relValue: $(this).attr("rel"),
                            relText: $(this).text()
                        });
                        an()
                    }).bind("mouseover",
                    function() {
                        q = true
                    }).bind("mouseout",
                    function() {
                        q = false
                    })
                }
                ac.find(".list_city_head").html(F);
                y(1);
                ap()
            } else {
                ac.find(".list_city_head").html("<span class='msg'>对不起,找不到" + aD + "</span>")
            }
        } else {
            ab = $.trim(ad.val());
            if (ab == "") {
                an()
            } else {
                if (ab.length >= s) {
                    setTimeout(function() {
                        D(ab)
                    },
                    b)
                } else {
                    an()
                }
            }
        }
    }
    function D(i) {
        var e = al(i);
        ad.addClass("hintbox_loading");
        $.post(e.url, e.params,
        function(aA, aB, az) {
            var ay = ac.find(".list_city_container");
            ay.empty();
            if (aA[u]) {
                if (aA[u].length > 0) {
                    $.each(aA[u],
                    function(aC, aE) {
                        var aD = $("<a href='javascript:void(0)' style='display:none' ><span>" + aA[u][aC].key + "</span></a>");
                        aD.attr("rel", aA[u][aC].value);
                        ay.append(aD);
                        aD.bind("click",
                        function() {
                            R($(this).text(), $(this).attr("rel"));
                            I.trigger("listSelect", {
                                relValue: $(this).attr("rel"),
                                relText: $(this).text()
                            });
                            an()
                        }).bind("mouseover",
                        function() {
                            q = true
                        }).bind("mouseout",
                        function() {
                            q = false
                        })
                    });
                    ac.find(".list_city_head").html(F);
                    y(1);
                    ap()
                } else {
                    ac.find(".list_city_head").html("<span class='msg'>对不起,找不到" + ad.val() + "</span>");
                    y(1)
                }
                V();
                if (ac[0].style.display == "none") {
                    r()
                }
            }
            ad.removeClass("hintbox_loading")
        },
        "json")
    }
    function al(i) {
        var e = {
            url: Y,
            params: {},
            queryString: ""
        };
        e.params[v] = i;
        e.queryString = Y + "?" + v + "=" + i;
        $.extend(true, e.params, j);
        return e
    }
    function y(e) {
        ac.find(".list_city_container a").removeClass("selected");
        ac.find(".list_city_container").children().each(function(az) {
            var ay = az + 1;
            if (ay > E * (e - 1) && ay <= E * e) {
                $(this).css("display", "block")
            } else {
                $(this).hide()
            }
        });
        ap();
        af(e)
    }
    function af(ay) {
        var e = ac.find(".page_break");
        e.empty();
        if (ac.find(".list_city_container").children().length > E) {
            var aB = Math.ceil(ac.find(".list_city_container").children().length / E);
            if (aB <= 1) {
                return
            }
            var aE = end = ay;
            for (var aA = 0,
            az = 1; aA < w && az < w; aA++) {
                if (aE > 1) {
                    aE--;
                    az++
                }
                if (end < aB) {
                    end++;
                    az++
                }
            }
            if (ay > 1) {}
            for (var aC = aE; aC <= end; aC++) {
                if (aC == ay) {
                    e.append("<a href='javascript:void(0)' class='current' inum='" + (aC) + "'>" + (aC) + "</a")
                } else {
                    e.append("<a href='javascript:void(0)' inum='" + (aC) + "'>" + (aC) + "</a")
                }
            }
            if (ay < aB) {}
            e.show()
        } else {
            e.hide()
        }
        var aD = e.find("a");
        aD.unbind("click");
        aD.unbind("mouseover");
        aD.unbind("mouseout");
        aD.bind("click",
        function(i) {
            ar = true;
            if ($(this).attr("inum") != null) {
                y($(this).attr("inum"))
            }
            ad.focus()
        });
        aD.bind("mouseover",
        function() {
            q = true
        }).bind("mouseout",
        function() {
            q = false
        });
        V();
        q = false;
        return
    }
    function ap() {
        if (ac.find(".list_city_container").children().length > 0) {
            ac.find(".list_city_container").children(":visible").eq(0).addClass("selected")
        }
    }
    function T() {
        aj.css({
            overflowY: "visible",
            overflowX: "visible"
        });
        aj.width("");
        aj.height("");
        var e = 200;
        e = window.document.documentElement.clientHeight - (I.offset().top - $(window).scrollTop()) - 30;
        var ay;
        if (!I.attr("multiListWidth")) {
            ay = aj.width()
        }
        aj.css({
            overflowY: "auto",
            overflowX: "hidden"
        });
        if (!I.attr("multiListWidth")) {
            aj.width(ay)
        } else {
            aj.width(Number(I.attr("multiListWidth")))
        }
        var i = 0;
        if (I.attr("multiListHeight")) {
            i = Number(I.attr("multiListHeight"))
        }
        if (i != 0) {
            aj.height(i);
            if (I.attr("openDirection") == "top") {
                aj.css({
                    top: -i
                })
            } else {
                if (I.attr("openDirection") == "bottom") {
                    aj.css({
                        top: ao
                    })
                } else {
                    if (e < i) {
                        if (I.offset().top > i) {
                            aj.css({
                                top: -i
                            })
                        } else {
                            if (e < 100 && I.offset().top > e && I.offset().top > 100) {
                                aj.css({
                                    top: -i
                                })
                            } else {
                                aj.css({
                                    top: ao
                                })
                            }
                        }
                    } else {
                        aj.css({
                            top: ao
                        })
                    }
                }
            }
        } else {
            if (I.attr("openDirection") == "top") {
                if (I.offset().top > aj.height()) {
                    aj.css({
                        top: -aj.height()
                    })
                } else {
                    aj.height($mainCon.offset().top);
                    aj.css({
                        top: -$mainCon.offset().top
                    })
                }
            } else {
                if (I.attr("openDirection") == "bottom") {
                    if (e < aj.height()) {
                        aj.css({
                            top: ao
                        });
                        aj.height(e)
                    } else {
                        aj.css({
                            top: ao
                        })
                    }
                } else {
                    if (e < aj.height()) {
                        if (I.offset().top > aj.height()) {
                            aj.css({
                                top: -aj.height()
                            })
                        } else {
                            if (e < 100 && I.offset().top > e && I.offset().top > 100) {
                                aj.height(I.offset().top);
                                aj.css({
                                    top: -I.offset().top
                                })
                            } else {
                                aj.css({
                                    top: ao
                                });
                                aj.height(e)
                            }
                        }
                    } else {
                        aj.css({
                            top: ao
                        })
                    }
                }
            }
        }
        if (!I.attr("multiListWidth")) {
            if (aj.width() < ad.width()) {
                aj.width(ad.width())
            }
        }
    }
    function V() {
        ac.css({
            overflowY: "visible",
            overflowX: "visible"
        });
        ac.width("");
        ac.height("");
        var e = 200;
        e = window.document.documentElement.clientHeight - (I.offset().top - $(window).scrollTop()) - 30;
        ac.css({
            overflowY: "auto",
            overflowX: "hidden"
        });
        if (I.attr("boxWidth")) {
            ac.width(Number(I.attr("boxWidth")))
        }
        var i = 0;
        if (I.attr("boxHeight")) {
            i = Number(I.attr("boxHeight"))
        }
        if (i != 0) {
            ac.height(i);
            if (I.attr("openDirection") == "top") {
                ac.css({
                    top: -i
                })
            } else {
                if (I.attr("openDirection") == "bottom") {
                    ac.css({
                        top: ao
                    })
                } else {
                    if (e < i) {
                        if (I.offset().top > i) {
                            ac.css({
                                top: -i
                            })
                        } else {
                            if (e < 100 && I.offset().top > e && I.offset().top > 100) {
                                ac.css({
                                    top: -i
                                })
                            } else {
                                ac.css({
                                    top: ao
                                })
                            }
                        }
                    } else {
                        ac.css({
                            top: ao
                        })
                    }
                }
            }
        } else {
            if (I.attr("openDirection") == "top") {
                if (I.offset().top > ac.height()) {
                    ac.css({
                        top: -ac.height()
                    })
                } else {
                    ac.height($mainCon.offset().top);
                    ac.css({
                        top: -$mainCon.offset().top
                    })
                }
            } else {
                if (I.attr("openDirection") == "bottom") {
                    if (e < ac.height()) {
                        ac.css({
                            top: ao
                        });
                        ac.height(e)
                    } else {
                        ac.css({
                            top: ao
                        })
                    }
                } else {
                    if (e < ac.height()) {
                        if (I.offset().top > ac.height()) {
                            ac.css({
                                top: -ac.height()
                            })
                        } else {
                            if (e < 100 && I.offset().top > e && I.offset().top > 100) {
                                ac.height(I.offset().top);
                                ac.css({
                                    top: -I.offset().top
                                })
                            } else {
                                ac.css({
                                    top: ao
                                });
                                ac.height(e)
                            }
                        }
                    } else {
                        ac.css({
                            top: ao
                        })
                    }
                }
            }
        }
        if (!I.attr("boxWidth")) {
            if (broswerFlag == "IE6") {
                ac.width(ad.width())
            } else {
                if (ac.width() < ad.width()) {
                    ac.width(ad.width())
                }
            }
        }
    }
    function aa() {
        var e = $("<div></div>");
        e.addClass("mainCon");
        return e
    }
    function x(e) {
        var i = $("<div class='pop_city'><div class='pop_head'></div><ul class='list_label'></ul><div class='pop_city_container'></div></div>");
        i.attr("id", "pop_city" + selectTree_id + "_container");
        return i
    }
    function Z(e) {
        var i = $("<div class='list_city'><div class='list_city_head'></div><div class='list_city_container'></div><div class='page_break'></div></div>");
        i.attr("id", "suggest_city_" + selectTree_id + "_container");
        return i
    }
    function au(i) {
        var e = document.createElement("input");
        var ay = $(e);
        ay.attr("id", "suggestion" + selectTree_id + "_input");
        ay.attr("type", "text");
        ay.addClass(i.inputClass);
        if (g != null) {
            ay.css({
                backgroundImage: "url(" + g + ")"
            })
        }
        ay.attr("autocomplete", "off");
        if (I.attr("disabled") == "disabled" || I.attr("disabled") == "true" || I.attr("disabled") == true) {
            ay.attr("disabled", true);
            ay.addClass("suggestion_input_disabled")
        }
        return ay
    }
    function ah(i) {
        var e = document.createElement("input");
        var ay = $(e);
        ay.attr("type", "hidden");
        if (I.attr("name") != null) {
            ay.attr("name", I.attr("name"))
        }
        return ay
    }
    function R(i, e) {
        if (e == "") {
            ad.css("color", "#aaa")
        } else {
            ad.css("color", "#000")
        }
        if (i == W) {
            I.attr("relText", "")
        } else {
            I.attr("relText", i)
        }
        I.attr("relValue", e);
        Q.val(e);
        ad.val(i)
    }
    function p(i, e) {
        aj.find(".pop_head").text(k);
        if (!e) {
            S.append("<ul id='label_" + selectTree_id + "_container' class='current'></ul>");
            o.remove();
            $.each(i[u],
            function(ay, az) {
                var aA = $("<li><a href='javascript:void(0)'>" + az.key + "</a></li>");
                aA.width(h);
                aA.find("a").attr("rel", az.value);
                $("#label_" + selectTree_id + "_container").append(aA)
            })
        } else {
            $.each(e[u],
            function(ay, aA) {
                if (ay == 0) {
                    S.append("<ul id='label_" + selectTree_id + ay + "_container' class='current' data-type='" + aA.name + "'></ul>");
                    o.append("<li><a id='label_" + selectTree_id + ay + "' class='current' href='javascript:void(0)'>" + aA.name + "</a></li>")
                } else {
                    S.append("<ul style='display:none' id='label_" + selectTree_id + ay + "_container' data-type='" + aA.name + "'></ul>");
                    o.append("<li><a id='label_" + selectTree_id + ay + "' href='javascript:void(0)'>" + aA.name + "</a></li>")
                }
                var az = ay;
                $.each(aA.tab,
                function(aB, aD) {
                    var aC = $("<li><a href='javascript:void(0)'>" + aD.key + "</a></li>");
                    aC.width(h);
                    aC.find("a").attr("rel", aD.value);
                    $("#label_" + selectTree_id + az + "_container").append(aC)
                })
            })
        }
    }
    function aq() {
        aj.hide();
        I.trigger("popClose")
    }
    function O() {
        if (z) {
            depth++;
            I.css({
                zIndex: depth
            });
            aj.show();
            I.trigger("popOpen")
        }
    }
    function an() {
        ac.hide();
        I.trigger("suggestClose")
    }
    function r() {
        depth++;
        I.css({
            zIndex: depth
        });
        ac.show();
        I.trigger("suggestOpen")
    }
    function U() {
        return I.val()
    }
    function ae() {
        return ad.val()
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