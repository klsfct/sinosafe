var BrowserDetect = {
    init: function() {
        this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
        this.version = this.searchVersion(navigator.userAgent) || this.searchVersion(navigator.appVersion) || "an unknown version";
        this.OS = this.searchString(this.dataOS) || "an unknown OS"
    },
    searchString: function(d) {
        for (var a = 0; a < d.length; a++) {
            var b = d[a].string;
            var c = d[a].prop;
            this.versionSearchString = d[a].versionSearch || d[a].identity;
            if (b) {
                if (b.indexOf(d[a].subString) != -1) {
                    return d[a].identity
                }
            } else {
                if (c) {
                    return d[a].identity
                }
            }
        }
    },
    searchVersion: function(b) {
        var a = b.indexOf(this.versionSearchString);
        if (a == -1) {
            return
        }
        return parseFloat(b.substring(a + this.versionSearchString.length + 1))
    },
    dataBrowser: [{
        string: navigator.userAgent,
        subString: "Chrome",
        identity: "Chrome"
    },
    {
        string: navigator.userAgent,
        subString: "OmniWeb",
        versionSearch: "OmniWeb/",
        identity: "OmniWeb"
    },
    {
        string: navigator.vendor,
        subString: "Apple",
        identity: "Safari",
        versionSearch: "Version"
    },
    {
        prop: window.opera,
        identity: "Opera"
    },
    {
        string: navigator.vendor,
        subString: "iCab",
        identity: "iCab"
    },
    {
        string: navigator.vendor,
        subString: "KDE",
        identity: "Konqueror"
    },
    {
        string: navigator.userAgent,
        subString: "Firefox",
        identity: "Firefox"
    },
    {
        string: navigator.vendor,
        subString: "Camino",
        identity: "Camino"
    },
    {
        string: navigator.userAgent,
        subString: "Netscape",
        identity: "Netscape"
    },
    {
        string: navigator.userAgent,
        subString: "MSIE",
        identity: "Explorer",
        versionSearch: "MSIE"
    },
    {
        string: navigator.userAgent,
        subString: "Gecko",
        identity: "Mozilla",
        versionSearch: "rv"
    },
    {
        string: navigator.userAgent,
        subString: "Mozilla",
        identity: "Netscape",
        versionSearch: "Mozilla"
    }],
    dataOS: [{
        string: navigator.platform,
        subString: "Win",
        identity: "Windows"
    },
    {
        string: navigator.platform,
        subString: "Mac",
        identity: "Mac"
    },
    {
        string: navigator.userAgent,
        subString: "iPhone",
        identity: "iPhone/iPod"
    },
    {
        string: navigator.platform,
        subString: "Linux",
        identity: "Linux"
    }]
};
BrowserDetect.init();
function isIE6or7or8() {
    if (BrowserDetect.browser == "Explorer" && (BrowserDetect.version == 8 || BrowserDetect.version == 7 || BrowserDetect.version == 6)) {
        return true
    } else {
        return false
    }
}
var oldHtml;
jQuery.fn.extend({
    listerRender: function() {
        this.each(function() {
            $(this).empty();
            new jQuery.Lister(this)
        })
    },
    listerSelectValue: function(e) {
        var b = $(this).data("data");
        var a = e.split(",");
        if (b.fromList) {
            for (var d = 0; d < a.length; d++) {
                var c = -1;
                $.each(b.fromList,
                function(f, g) {
                    if (g.value == a[d]) {
                        if (b.toList) {
                            b.toList.push(b.fromList[f]);
                            c = f
                        }
                    }
                });
                if (c != -1) {
                    b.fromList.splice(c, 1)
                }
            }
        }
        $(this).data("data", b);
        $(this).data("selectedNodes", b.toList);
        $(this).html("");
        new jQuery.Lister(this)
    },
    listerUnSelectValue: function(e) {
        var b = $(this).data("data");
        var a = e.split(",");
        if (b.toList) {
            for (var d = 0; d < a.length; d++) {
                var c = -1;
                $.each(b.toList,
                function(f, g) {
                    if (g.value == a[d]) {
                        if (b.fromList) {
                            b.fromList.push(b.toList[f]);
                            c = f
                        }
                    }
                });
                if (c != -1) {
                    b.toList.splice(c, 1)
                }
            }
        }
        $(this).data("data", b);
        $(this).data("selectedNodes", b.toList);
        $(this).html("");
        new jQuery.Lister(this)
    },
    listerSetValue: function(f) {
        var b = $(this).data("data");
        var a = f.split(",");
        if (b.toList) {
            var e = b.toList.length;
            for (var d = 0; d < e; d++) {
                $.each(b.toList,
                function(g, h) {
                    if (g == d) {
                        if (b.fromList) {
                            b.fromList.push(b.toList[g])
                        }
                    }
                })
            }
            b.toList.splice(0, e)
        }
        if (b.fromList) {
            for (var d = 0; d < a.length; d++) {
                var c = -1;
                $.each(b.fromList,
                function(g, h) {
                    if (h.value == a[d]) {
                        if (b.toList) {
                            b.toList.push(b.fromList[g]);
                            c = g
                        }
                    }
                });
                if (c != -1) {
                    b.fromList.splice(c, 1)
                }
            }
        }
        $(this).data("data", b);
        $(this).data("selectedNodes", b.toList);
        $(this).html("");
        new jQuery.Lister(this)
    },
    listerAddItem: function(a) {
        this.each(function() {
            var b = $(this).data("data");
            b.fromList.push(a);
            $(this).data("data", b);
            $(this).data("selectedNodes", b.toList);
            $(this).html("");
            new jQuery.Lister(this)
        })
    },
    listerRemoveItem: function(a) {
        this.each(function() {
            var b = $(this).data("data");
            var c = -1;
            $.each(b.fromList,
            function(d, e) {
                if (e.value.toString() == a) {
                    c = d
                }
            });
            if (c != -1) {
                b.fromList.splice(c, 1)
            }
            $(this).data("data", b);
            $(this).data("selectedNodes", b.toList);
            $(this).html("");
            new jQuery.Lister(this)
        })
    }
});
jQuery.Lister = function(l) {
    $.sortA = [];
    $.sortB = [];
    $.ie = isIE6or7or8();
    $.create = function(e) {
        return $(document.createElement(e))
    };
    $.fn.fillLister = function() {
        $(a).scrollTop(0).children("li").each(function() {
            if (!$(this).children("span").hasClass("left")) {
                var e = $.create("span").addClass("left").html("&#9658;");
                $(this).bind("click", j).prepend(e)
            }
        })
    };
    function n(y) {
        var e = $(l).data("data");
        if (!e) {
            return
        }
        if ($(l).attr("maxSelection")) {
            if (y) {
                var z = $(l).data("currentNum");
                var x = Number($(l).attr("maxSelection"));
                $.each(e.fromList,
                function(H, I) {
                    if (z < x) {
                        if (e.toList) {
                            e.toList.push(e.fromList[0])
                        }
                        z++;
                        e.fromList.splice(0, 1)
                    } else {
                        try {
                            top.Dialog.alert("超出最大选择条数,已按顺序选择了最大数目的选项！")
                        } catch(J) {
                            alert("超出最大选择条数,已按顺序选择了最大数目的选项！")
                        }
                    }
                });
                $(l).data("currentNum", z)
            } else {
                if (e.toList) {
                    var A = e.toList.length;
                    $.each(e.toList,
                    function(H, I) {
                        if (e.fromList) {
                            e.fromList.push(e.toList[H])
                        }
                    });
                    e.toList.splice(0, A);
                    $(l).data("currentNum", 0)
                }
            }
            $(l).data("data", e);
            $(l).data("selectedNodes", e.toList);
            $(l).html("");
            $(l).removeAttr("selectedValue");
            new jQuery.Lister(l)
        } else {
            if (y) {
                if (e.fromList) {
                    var F = e.fromList.length;
                    $.each(e.fromList,
                    function(H, I) {
                        if (e.toList) {
                            e.toList.push(e.fromList[H])
                        }
                    });
                    e.fromList.splice(0, F)
                }
            } else {
                if (e.toList) {
                    var G = e.toList.length;
                    $.each(e.toList,
                    function(H, I) {
                        if (e.fromList) {
                            e.fromList.push(e.toList[H])
                        }
                    });
                    e.toList.splice(0, G)
                }
            }
            if (y) {
                var B = a;
                var E = o;
                var D = "&#9668;"
            } else {
                var B = o;
                var E = a;
                var D = "&#9658;"
            }
            $(B).children("li[el]").each(function() {
                var H = $(this);
                var I = $(H).children("span");
                var J = $(this).parent("ul").attr("id");
                $(I).each(function() {
                    $(this).html(D)
                });
                if (y) {
                    $(o).prepend(H)
                } else {
                    $(a).prepend(H)
                }
            });
            $(B).empty();
            $(l).data("data", e);
            $(l).data("selectedNodes", e.toList);
            var C = d();
            $(l).attr("relValue", C);
            $(l).attr("relText", d("title"));
            v.val(C)
        }
        $(l).trigger("itemClick")
    }
    function d(e) {
        var x = [];
        $(o).children("li[el]").each(function() {
            if (!e) {
                var y = $.trim($(this).attr("el"))
            } else {
                var y = $(this).attr(e)
            }
            if (y != "" && y != null) {
                x.push($.trim(y))
            }
        });
        return x.toString().replace(/\s+/g, "")
    }
    $.fn.tclass = function(e) {
        $(this).bind("mouseover",
        function() {
            $(this).addClass(e)
        });
        $(this).bind("mouseout",
        function() {
            $(this).removeClass(e)
        });
        return this
    };
    function j(A) {
        var H = $(this);
        var G = $(H).children("span");
        var y = $(this).parent("ul").attr("flag");
        var x = $(l).data("data");
        if (!x) {
            return
        }
        var B = $(l).data("currentNum");
        if (y == "to") {
            a.prepend(H);
            $(G).removeClass("right").addClass("left").html("&#9658;");
            if (x.toList) {
                var D = -1;
                $.each(x.toList,
                function(e, I) {
                    if (I.value == H.attr("el")) {
                        if (x.fromList) {
                            x.fromList.push(x.toList[e]);
                            D = e
                        }
                    }
                });
                if (D != -1) {
                    x.toList.splice(D, 1)
                }
            }
            B--;
            $(l).data("currentNum", B)
        } else {
            if ($(l).attr("maxSelection")) {
                var z = Number($(l).attr("maxSelection"));
                if (B == z) {
                    try {
                        top.Dialog.alert("已达到最大可选条数！")
                    } catch(F) {
                        alert("已达到最大可选条数！")
                    }
                    return
                }
            }
            o.prepend(H);
            $(G).removeClass("left").addClass("right").html("&#9668;");
            if (x.fromList) {
                var E = -1;
                $.each(x.fromList,
                function(e, I) {
                    if (I.value == H.attr("el")) {
                        if (x.toList) {
                            x.toList.push(x.fromList[e]);
                            E = e
                        }
                    }
                });
                if (E != -1) {
                    x.fromList.splice(E, 1)
                }
            }
            B++;
            $(l).data("currentNum", B)
        }
        $(l).data("data", x);
        $(l).data("selectedNodes", x.toList);
        $(this).removeClass("hover").removeClass("listerHover");
        var C = d();
        $(l).attr("relValue", C);
        $(l).attr("relText", d("title"));
        v.val(C);
        $(l).trigger("itemClick")
    }
    $.fn.sendItem = function(y, x) {
        var e = y;
        var z = $(e).children("span");
        var A = y.parent("ul").attr("id");
        if (x) {
            if (A == el) {} else {
                $(o).prepend(e);
                $(z).removeClass("left").addClass("right").html("&#9668;")
            }
        } else {
            if (A == el) {
                $(a).prepend(e);
                $(z).removeClass("right").addClass("left").html("&#9658;")
            } else {}
        }
        y.removeClass("hover").removeClass("listerHover")
    },
    $.fn.sortLists = function(y) {
        var A = $(y);
        if (!$.ie) {
            $(A).children("li[tx]").each(function() {
                $(this).remove()
            });
            $(A).parent("div").children("a").each(function() {
                $(this).remove()
            });
            $(A).parent("div").children("span").each(function() {
                $(this).remove()
            })
        }
        var e = A.children("li").get();
        e.sort(function(C, B) {
            var E = $(C).text().toUpperCase();
            var D = $(B).text().toUpperCase();
            return (E < D) ? -1 : (E > D) ? 1 : 0
        });
        if (!$.ie) {
            var z = "";
            var x = ""
        }
        $.each(e,
        function(B, C) {
            if (!$.ie) {
                x = $(C).text().substring(1, 2);
                if (x.toUpperCase() != z.toUpperCase()) {
                    $(A).append($.create("li").text(x).attr("tx", x.toUpperCase()).addClass("liTitles"));
                    $(A).before($.create("a").bind("click", g).text(x.toUpperCase())).before($.create("span").text(" | "))
                }
            }
            A.append(C);
            if (!$.ie) {
                z = x
            }
        })
    };
    function u() {
        var B = $("<div></div>");
        var e = $("<div></div>");
        if ($(l).attr("fromTitle") != null) {
            B.html($(l).attr("fromTitle"))
        } else {
            B.html("未选列表")
        }
        if ($(l).attr("toTitle") != null) {
            e.html($(l).attr("toTitle"))
        } else {
            e.html("已选列表")
        }
        var E = $.create("div").addClass("listerLinksLeft").append(B).append(a);
        var C = $.create("div").addClass("listerLinksRight").append(e).append(o);
        if ($(l).attr("listerWidth")) {
            E.width(Number($(l).attr("listerWidth")));
            C.width(Number($(l).attr("listerWidth")))
        } else {
            E.width(200);
            C.width(200)
        }
        var A = $('<div class="listBtn"></div>');
        var y = $('<input type="button" value="全选&gt;&gt;" class="button"/>');
        y.bind("click",
        function() {
            n(true)
        });
        A.append(y);
        A.append("<br/><br/>");
        var x = $('<input type="button" value="&lt;&lt;还原" class="button">');
        x.bind("click",
        function() {
            n(false)
        });
        A.append(x);
        var D = $('<table cellspacing="0" cellpadding="0" style="border-style:none;"><tr><td class="ali01" style="border-style:none;padding:0;margin:0;"></td><td class="ali02" style="border-style:none;padding-left:5px;padding-right:5px;margin:0;"></td><td class="ali01" style="border-style:none;padding:0;margin:0;"></td></tr></table>');
        D.find("td").eq(0).append(E);
        D.find("td").eq(1).append(A);
        D.find("td").eq(2).append(C);
        $(h).append(D);
        $(h).width(D.width());
        if (k == false) {
            y.attr("disabled", "true");
            x.attr("disabled", "true");
            $(h).mask("组件被禁用", 0, false, "#ffffff")
        }
        y.buttonInputRender();
        x.buttonInputRender();
        $(h).append(v);
        var z = d();
        $(l).attr("relValue", z);
        $(l).attr("relText", d("title"));
        v.val(z);
        $(l).attr("finished", "true")
    }
    function g() {
        var x = $(this).parent("div").children("ul");
        var e = $(this).text();
        $(x).scrollTop(0);
        var y = $(x).children("li[tx=" + e + "]").offset().top - $(x).offset().top;
        $(x).animate({
            scrollTop: y
        },
        500)
    }
    function c() {
        var e = $('<input type="hidden">');
        if ($(l).attr("name") != null) {
            e.attr("name", $(l).attr("name"))
        }
        return e
    }
    function b(x, B) {
        if (!x) {
            return
        }
        var e = B.split(",");
        $(l).data("currentNum", e.length);
        if (x.toList) {
            var A = x.toList.length;
            for (var z = 0; z < A; z++) {
                $.each(x.toList,
                function(C, D) {
                    if (C == z) {
                        if (x.fromList) {
                            x.fromList.push(x.toList[C])
                        }
                    }
                })
            }
            x.toList.splice(0, A)
        }
        if (x.fromList) {
            for (var z = 0; z < e.length; z++) {
                var y = -1;
                $.each(x.fromList,
                function(C, D) {
                    if (D.value == e[z]) {
                        if (x.toList) {
                            x.toList.push(x.fromList[C]);
                            y = C
                        }
                    }
                });
                if (y != -1) {
                    x.fromList.splice(y, 1)
                }
            }
        }
        return x
    }
    function q(e) {
        if (!e) {
            return
        }
        if (e.fromList) {
            $.each(e.fromList,
            function(x, z) {
                var y = $.create("span").addClass("left").html("&#9658;");
                var A = $.create("li");
                A.html(z.key);
                A.attr("title", z.key);
                A.attr("el", z.value);
                A.bind("click", j).tclass("listerHover").prepend(y).bind("mouseover",
                function() {
                    $(this).addClass("hover")
                }).bind("mouseout",
                function() {
                    $(this).removeClass("hover")
                });
                a.append(A)
            })
        }
        if (e.toList) {
            $.each(e.toList,
            function(x, z) {
                var y = $.create("span").addClass("left").html("&#9668;");
                var A = $.create("li");
                A.html(z.key);
                A.attr("title", z.key);
                A.attr("el", z.value);
                A.bind("click", j).tclass("listerHover").prepend(y).bind("mouseover",
                function() {
                    $(this).addClass("hover")
                }).bind("mouseout",
                function() {
                    $(this).removeClass("hover")
                });
                o.append(A)
            })
        }
        u()
    }
    var v = c();
    var h = $(l);
    var a = $.create("ul").addClass("lister").attr("flag", "from");
    var o = $.create("ul").addClass("lister").attr("flag", "to");
    if ($(l).attr("listerHeight")) {
        a.height(Number($(l).attr("listerHeight")));
        o.height(Number($(l).attr("listerHeight")))
    } else {
        a.height(200);
        o.height(200)
    }
    $(l).data("currentNum", 0);
    var k = true;
    if ($(l).attr("disabled")) {
        if ($(l).attr("disabled") == "false" || $(l).attr("disabled") == false) {
            k = true
        } else {
            k = false
        }
    }
    var f = $(l).attr("params");
    var w;
    if (f) {
        try {
            w = JSON.parse(f)
        } catch(s) {
            w = [];
            alert("双向选择器参数格式有误！（提示：json数据key与value必须以双引号包围）")
        }
    } else {
        w = []
    }
    var m = "-1";
    if ($(l).attr("selectedValue") != null) {
        m = $(l).attr("selectedValue")
    }
    var p = "";
    var r = $(l).attr("url");
    var t = $(l).attr("data");
    var i = $(l).data("data");
    if (i) {
        $(l).data("currentNum", i.toList.length);
        if (m == "-1") {
            q(i)
        } else {
            q(b(i, m))
        }
    } else {
        if (t) {
            try {
                p = JSON.parse(t)
            } catch(s) {
                p = "";
                alert("双向选择器参数格式有误！（提示：json数据key与value必须以双引号包围）")
            }
            if (m == "-1") {
                q(p)
            } else {
                q(b(p, m))
            }
            $(l).data("data", p);
            $(l).data("selectedNodes", p.toList)
        } else {
            if (r) {
                $.ajax({
                    url: $(l).attr("url"),
                    dataType: "json",
                    data: w,
                    error: function() {
                        alert("双向选择器数据源出错，请检查url路径")
                    },
                    success: function(e) {
                        $(l).data("data", e);
                        $(l).data("selectedNodes", e.toList);
                        if (m == "-1") {
                            q(e)
                        } else {
                            q(b(e, m))
                        }
                    }
                })
            }
        }
    }
    return this
};