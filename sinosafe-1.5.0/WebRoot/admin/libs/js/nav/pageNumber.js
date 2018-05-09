$.fn.pageNumberRender = function() {
    $(this).empty();
    var j;
    if ($(this).attr("total")) {
        j = Number($(this).attr("total"))
    } else {
        alert("需要为组件指定总数据条数！")
    }
    var i = 10;
    if ($(this).attr("pageSize")) {
        i = Number($(this).attr("pageSize"))
    } else {
        $(this).attr("pageSize", i)
    }
    var a = 50;
    if ($(this).attr("selWidth")) {
        a = Number($(this).attr("selWidth"))
    }
    var g = 3;
    if ($(this).attr("centerPageNum")) {
        g = Number($(this).attr("centerPageNum"))
    }
    var d = 1;
    if ($(this).attr("edgePageNum")) {
        d = Number($(this).attr("edgePageNum"))
    }
    var h = 0;
    if ($(this).attr("page")) {
        h = Number($(this).attr("page"))
    } else {
        $(this).attr("page", h)
    }
    var k = "上一页";
    if ($(this).attr("prevText")) {
        k = $(this).attr("prevText")
    }
    var n = "下一页";
    if ($(this).attr("nextText")) {
        n = $(this).attr("nextText")
    }
    var m = false;
    if ($(this).attr("showSelect") == "true" || $(this).attr("showSelect") == true) {
        m = true
    }
    var c = {
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
            c = JSON.parse($(this).attr("selectData"))
        } catch(f) {
            c = {
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
    var b = false;
    if ($(this).attr("showInput") == "true" || $(this).attr("showInput") == true) {
        b = true
    }
    var l = "top";
    if ($(this).attr("selectDirection") == "bottom") {
        l = "bottom"
    }
    $(this).pagination(j, {
        items_per_page: i,
        num_display_entries: g,
        num_edge_entries: d,
        current_page: h,
        prev_text: k,
        next_text: n,
        showSelect: m,
        selectData: c,
        showInput: b,
        selectDirection: l,
        selWidth: a
    })
};
jQuery.fn.pagination = function(a, b) {
    b = jQuery.extend({
        items_per_page: 10,
        num_display_entries: 5,
        current_page: 0,
        num_edge_entries: 1,
        link_to: "javascript:void(0);",
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
        showInput: false,
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
            var j = h();
            var u = f();
            var m = function(i) {
                return function(v) {
                    return e(i, v)
                }
            };
            var q = function(i, v) {
                i = i < 0 ? 0 : (i < u ? i: u - 1);
                v = jQuery.extend({
                    text: i + 1,
                    classes: ""
                },
                v || {});
                if (i == g) {
                    var w = jQuery("<span class='current'>" + (v.text) + "</span>")
                } else {
                    var w = jQuery("<a>" + (v.text) + "</a>").bind("click", m(i))
                }
                if (v.classes) {
                    w.addClass(v.classes)
                }
                d.append(w)
            };
            if (b.prev_text && (g > 0 || b.prev_show_always)) {
                q(g - 1, {
                    text: b.prev_text,
                    classes: "prev"
                })
            }
            if (j[0] > 0 && b.num_edge_entries > 0) {
                var o = Math.min(b.num_edge_entries, j[0]);
                for (var p = 0; p < o; p++) {
                    q(p)
                }
                if (b.num_edge_entries < j[0] && b.ellipse_text) {
                    jQuery("<span>" + b.ellipse_text + "</span>").appendTo(d)
                }
            }
            for (var p = j[0]; p < j[1]; p++) {
                q(p)
            }
            if (j[1] < u && b.num_edge_entries > 0) {
                if (u - b.num_edge_entries > j[1] && b.ellipse_text) {
                    jQuery("<span>" + b.ellipse_text + "</span>").appendTo(d)
                }
                var k = Math.max(u - b.num_edge_entries, j[1]);
                for (var p = k; p < u; p++) {
                    q(p)
                }
            }
            if (b.next_text && (g < u - 1 || b.next_show_always)) {
                q(g + 1, {
                    text: b.next_text,
                    classes: "next"
                })
            }
            if (b.showSelect == true) {
                var n = $("<select></select>");
                n.data("data", b.selectData);
                var l = $('<div style="float:left;padding:5px 0 0 5px">每页</div><div style="float:left;padding:0 0 0 2px;"></div><div style="float:left;padding:5px 0 0 2px">条</div>');
                l.eq(1).append(n);
                l.eq(1).width(b.selWidth + 10);
                var s;
                s = Number(d.attr("pageSize"));
                n.attr("selectedValue", s);
                n.attr("selWidth", b.selWidth);
                n.attr("boxWidth", b.selWidth);
                n.attr("openDirection", b.selectDirection);
                n.selectRender();
                n.unbind("change");
                n.bind("change",
                function() {
                    s = n.attr("relValue");
                    d.attr("pageSize", s);
                    b.items_per_page = Number(s);
                    var i = f();
                    if (g > i - 1) {
                        e(i - 1, null, s)
                    } else {
                        d.trigger("sizeChange", s)
                    }
                    c()
                });
                d.append(l)
            }
            if (b.showInput == true) {
                var t = $('<input type="text" style="width:30px;" inputMode="numberOnly"/>');
                var r = $('<div style="float:left;padding:5px 0 0 5px">转到</div><div style="float:left;padding:0 0 0 2px"></div><div style="float:left;padding:5px 0 0 2px">页</div>');
                r.eq(1).append(t);
                t.render();
                t.keydown(function(v) {
                    if (v.keyCode == 13) {
                        var i = f();
                        if (Number(t.val()) > i) {
                            e(i - 1)
                        } else {
                            if (Number(t.val()) < 1) {
                                e(0)
                            } else {
                                e(Number(t.val()) - 1)
                            }
                        }
                    }
                });
                d.append(r)
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

$(function(){
    $("#page-7").bind("pageChange",function(e,index){
    	$("#currentPageId").val(index);
		$("#pageQueryId").submit();       
    }).bind("sizeChange",function(e,num){
    	$("#everyPageId").val(num);
		$("#pageQueryId").submit();       
    });
});
