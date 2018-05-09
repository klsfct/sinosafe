$.fn.pageArrowRender = function() {
    $(this).empty();
    var k;
    if ($(this).attr("total")) {
        k = Number($(this).attr("total"))
    } else {
        alert("需要为组件指定总数据条数！")
    }
    var j = 10;
    if ($(this).attr("pageSize")) {
        j = Number($(this).attr("pageSize"))
    } else {
        $(this).attr("pageSize", j)
    }
    var a = 50;
    if ($(this).attr("selWidth")) {
        a = Number($(this).attr("selWidth"))
    }
    var h = 3;
    if ($(this).attr("centerPageNum")) {
        h = Number($(this).attr("centerPageNum"))
    }
    var f = 1;
    if ($(this).attr("edgePageNum")) {
        f = Number($(this).attr("edgePageNum"))
    }
    var i = 0;
    if ($(this).attr("page")) {
        i = Number($(this).attr("page"))
    } else {
        $(this).attr("page", i)
    }
    var m = false;
    if ($(this).attr("showSelect") == "true" || $(this).attr("showSelect") == true) {
        m = true
    }
    var d = {
        list: [{
            key: 10,
            value: 10
        },
        {
            key: 20,
            value: 20
        },
        {
            key: 50,
            value: 50
        }]
    };
    if ($(this).attr("selectData")) {
        try {
            d = JSON.parse($(this).attr("selectData"))
        } catch(g) {
            d = {
                list: [{
                    key: 10,
                    value: 10
                },
                {
                    key: 20,
                    value: 20
                },
                {
                    key: 50,
                    value: 50
                }]
            };
            alert("下拉框数据格式有误！（提示：放在标签中的json数据的属性和名称必须以双引号包围）")
        }
    }
    var c = true;
    if ($(this).attr("showInput") == "false" || $(this).attr("showInput") == false) {
        c = false
    }
    var n = "center";
    if ($(this).attr("inputPosition")) {
        n = $(this).attr("inputPosition")
    }
    var b = false;
    if ($(this).attr("showNumber") == "true" || $(this).attr("showNumber") == true) {
        b = true
    }
    var l = "top";
    if ($(this).attr("selectDirection") == "bottom") {
        l = "bottom"
    }
    $(this).paginationArrow(k, {
        items_per_page: j,
        num_display_entries: h,
        num_edge_entries: f,
        current_page: i,
        showSelect: m,
        selectData: d,
        showInput: c,
        inputPosition: n,
        showNumber: b,
        selectDirection: l,
        selWidth: a
    })
};
jQuery.fn.paginationArrow = function(a, b) {
    b = jQuery.extend({
        items_per_page: 10,
        num_display_entries: 5,
        current_page: 0,
        num_edge_entries: 1,
        link_to: "javascript:;",
        prev_text: "上一页",
        next_text: "下一页",
        ellipse_text: "...",
        prev_show_always: true,
        next_show_always: true,
        showSelect: false,
        selectData: {
            list: [{
                key: 10,
                value: 10
            },
            {
                key: 20,
                value: 20
            },
            {
                key: 50,
                value: 50
            }]
        },
        showInput: true,
        inputPosition: "center",
        showNumber: false,
        selectDirection: "top",
        selWidth: 50,
        callback: function() {
            return false
        }
    },
    b || {});
    return this.each(function() {
        function f() {
            return Math.ceil(a / b.items_per_page)
        }
        function h() {
            var k = Math.ceil(b.num_display_entries / 2);
            var l = f();
            var j = l - b.num_display_entries;
            var m = g > k ? Math.max(Math.min(g - k, j), 0) : 0;
            var i = g >= k ? Math.min(g + k, l) : Math.min(b.num_display_entries, l);
            return [m, i]
        }
        function e(k, j, i) {
            g = k;
            c();
            var l;
            l = d.trigger("pageChange", g);
            d.attr("page", g);
            if (i) {
                d.trigger("sizeChange", i)
            }
            if (!l) {
                if (j.stopPropagation) {
                    j.stopPropagation()
                } else {
                    j.cancelBubble = true
                }
            }
            return l
        }
        function c() {
            d.empty();
            var k = h();
            var w = f();
            var p = function(i) {
                return function(y) {
                    return e(i, y)
                }
            };
            var s = function(i, y) {
                i = i < 0 ? 0 : (i < w ? i: w - 1);
                y = jQuery.extend({
                    text: i + 1,
                    classes: ""
                },
                y || {});
                if (i == g) {
                    var z = jQuery("<span class='current'>" + (y.text) + "</span>")
                } else {
                    var z = jQuery("<a>" + (y.text) + "</a>").bind("click", p(i))
                }
                if (y.classes) {
                    z.addClass(y.classes)
                }
                d.append(z)
            };
            s(0, {
                text: "",
                classes: "pageArrow_first"
            });
            s(g - 1, {
                text: "",
                classes: "pageArrow_prev"
            });
            if (b.showNumber == true) {
                if (k[0] > 0 && b.num_edge_entries > 0) {
                    var q = Math.min(b.num_edge_entries, k[0]);
                    for (var r = 0; r < q; r++) {
                        s(r)
                    }
                    if (b.num_edge_entries < k[0] && b.ellipse_text) {
                        jQuery("<span>" + b.ellipse_text + "</span>").appendTo(d)
                    }
                }
                for (var r = k[0]; r < k[1]; r++) {
                    s(r)
                }
                if (k[1] < w && b.num_edge_entries > 0) {
                    if (w - b.num_edge_entries > k[1] && b.ellipse_text) {
                        jQuery("<span>" + b.ellipse_text + "</span>").appendTo(d)
                    }
                    var l = Math.max(w - b.num_edge_entries, k[1]);
                    for (var r = l; r < w; r++) {
                        s(r)
                    }
                }
            }
            if (b.showInput == true && b.inputPosition == "center") {
                var v = $('<input type="text" style="width:25px;height:14px;line-height:14px;" inputMode="numberOnly"/>');
                var t = $('<div style="float:left;padding:5px 0 0 0;"></div><div style="float:left;padding:5px 0 0 2px;font-size:14px;"></div><div style="float:left;padding:5px 0 0 2px;font-size:14px;"></div>');
                t.eq(0).append(v);
                v.render();
                v.val(g + 1);
                t.eq(1).html("/");
                t.eq(2).html(w);
                d.append(t);
                v.keydown(function(i) {
                    if (i.keyCode == 13) {
                        if (Number(v.val()) > w) {
                            e(w - 1)
                        } else {
                            if (Number(v.val()) < 1) {
                                e(0)
                            } else {
                                e(Number(v.val()) - 1)
                            }
                        }
                    }
                });
                v.click(function() {
                    $(this).select()
                })
            } else {
                if (b.showNumber == false) {
                    var j = $('<div style="float:left;padding:5px 3px 0 0;font-size:14px;"></div>');
                    j.html((g + 1) + "/" + w);
                    d.append(j)
                }
            }
            s(g + 1, {
                text: "",
                classes: "pageArrow_next"
            });
            s(w, {
                text: "",
                classes: "pageArrow_last"
            });
            if (b.showSelect == true) {
                var o = $("<select></select>");
                o.data("data", b.selectData);
                var m = $('<div style="float:left;padding:5px 0 0 5px;">每页</div><div style="float:left;padding:0px 0 0 2px;"></div><div style="float:left;padding:5px 0 0 2px;">条</div>');
                m.eq(1).append(o);
                m.eq(1).width(b.selWidth + 10);
                var u;
                u = Number(d.attr("pageSize"));
                o.attr("selectedValue", u);
                o.attr("selWidth", b.selWidth);
                o.attr("boxWidth", b.selWidth);
                o.attr("openDirection", b.selectDirection);
                o.selectRender();
                o.unbind("change");
                o.bind("change",
                function() {
                    u = o.attr("relValue");
                    d.attr("pageSize", u);
                    b.items_per_page = Number(u);
                    w = f();
                    if (g > w - 1) {
                        e(w - 1, null, u)
                    } else {
                        d.trigger("sizeChange", u)
                    }
                    c()
                });
                d.append(m)
            }
            if (b.showInput == true && b.inputPosition == "right") {
                var x = $('<input type="text" style="width:25px;" inputMode="numberOnly"/>');
                var n = $('<div style="float:left;padding:5px 0 0 4px;"></div><div style="float:left;padding:0px 0 0 4px;"></div><div style="float:left;padding:5px 0 0 2px;"></div>');
                n.eq(0).html("转到");
                n.eq(1).append(x);
                x.render();
                n.eq(2).html("页");
                d.append(n);
                x.keydown(function(i) {
                    if (i.keyCode == 13) {
                        if (Number(x.val()) > w) {
                            e(w - 1)
                        } else {
                            e(Number(x.val()) - 1)
                        }
                    }
                })
            }
            d.append($('<div style="clear:both;"></div>'))
        }
        var g = b.current_page;
        a = (!a || a < 0) ? 1 : a;
        b.items_per_page = (!b.items_per_page || b.items_per_page < 0) ? 1 : b.items_per_page;
        var d = jQuery(this);
        d.data("selectCurrent", b.selectCurrent);
        this.selectPage = function(i) {
            e(i)
        };
        this.prevPage = function() {
            if (g > 0) {
                e(g - 1);
                return true
            } else {
                return false
            }
        };
        this.nextPage = function() {
            if (g < f() - 1) {
                e(g + 1);
                return true
            } else {
                return false
            }
        };
        c()
    })
};