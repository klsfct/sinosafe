var options; 
(function(b) {
    var a = false;
    var c = [];
    b.fn.treeTableRender = function() {
        var e = true;
        var d = "expanded";
        if (b(this).attr("expandable") == "false") {
            e = false
        }
        if (b(this).attr("initState") == "collapsed") {
            d = "collapsed"
        }
        b(this).acts_as_tree_table({
            expandable: e,
            default_state: d
        })
    };
    b.fn.acts_as_tree_table = function(i) {
        var g = false;
        options = b.extend({},
        b.fn.acts_as_tree_table.defaults, i);
        return this.each(function() {
            var k = b(this);
            if (k.attr("mode") == "ajax" || k.attr("ajaxMode") == "true" || k.attr("ajaxMode") == true) {
                a = true
            } else {
                a = false
            }
            if (k.attr("checkMode") == "true" || k.attr("checkMode") == true) {
                g = true
            } else {
                g = false
            }
            k.addClass("acts_as_tree_table");
            if (a) {
                k.children("tbody").children("tbody tr").each(function() {
                    var l = b(this);
                    if (l.not(".parent") && l.attr("url") != null) {
                        l.addClass("parent")
                    }
                    if (l.is(".parent")) {
                        e(l)
                    }
                })
            } else {
                k.children("tbody").children("tbody tr").each(function(l) {
                    var m = b(this);
                    if (m.not(".parent") && children_of(m).size() > 0) {
                        m.addClass("parent")
                    }
                    if (m.is(".parent")) {
                        f(m)
                    } else {
                        if (l != 0) {
                            j(m)
                        }
                    }
                })
            }
        });
        function j(n) {
            if (g) {
                var k = b(n.children("td")[options.tree_column]);
                var m = n.attr("id");
                var l = n.attr("class");
                k.prepend('<input type="checkbox" id="check-' + m + '" class="check-' + l + '" style="margin-top:6px" onclick=selectCheckbox("' + m + '")>')
            }
        }
        function f(n) {
            var k = b(n.children("td")[options.tree_column]);
            var o;
            if (g) {
                o = parseInt(k.css("padding-left")) + options.indent * 2
            } else {
                o = parseInt(k.css("padding-left")) + options.indent
            }
            children_of(n).each(function() {
                b(b(this).children("td")[options.tree_column]).css("padding-left", o + "px")
            });
            if (g) {
                var m = n.attr("id");
                var l = n.attr("class");
                k.prepend('<input type="checkbox" id="check-' + m + '" class="check-' + l + '" style="margin-top:6px" onclick=javascript:selectCheckbox("' + m + '")>')
            }
            if (options.expandable) {
            	if(k.parent().find(".expander").length<1){
            		 k.prepend('<span style="margin-left:0px; padding-left: ' + options.indent + 'px" class="expander"></span>');
                     var p = b(k[0].firstChild);
                     p.click(function() {
                         toggle(n)
                     });
                     if (! (n.is(".expanded") || n.is(".collapsed"))) {
                         n.addClass(options.default_state)
                     }
                     if (n.is(".collapsed")) {
                         collapse(n)
                     } else {
                         if (n.is(".expanded")) {
                             expand(n)
                         }
                     }
            	}
               
            }
        }
        function e(n) {
            var k = b(n.children("td")[0]);
            if (g) {
                var m = n.attr("id");
                var l = n.attr("class");
                k.prepend('<input type="checkbox" id="check-' + m + '" class="check-' + l + '" style="margin-top:6px" onclick=javascript:selectCheckbox("' + m + '")>')
            }
            if(k.parent().find(".expander").length<1){
	            k.prepend('<span style="margin-left:0px; padding-left: ' + options.indent + 'px" class="expander"></span>');
	            var o = b(k[0].firstChild);
	            n.addClass("collapsed");
	            o.click(function() {
	                var q = b(this);
	                var p = n.attr("url");
	                if (p != "") {
	                    n.removeClass("collapsed");
	                    n.addClass("table_loading");
	                    window.setTimeout(function() {
	                        h(q, p, n)
	                    },
	                    200)
	                } else {
	                    toggle(n)
	                }
	            })
            }
        }
        function d(n) {
            var k = b(n.children("td")[0]);
            if (g) {
                var m = n.attr("id");
                var l = n.attr("class")
            }
            if(k.parent().find(".expander").length<1){
	            k.prepend('<span style="margin-left:0px; padding-left: ' + options.indent + 'px" class="expander"></span>');
	            var o = b(k[0].firstChild);
	            n.addClass("collapsed");
	            o.click(function() {
	                var q = b(this);
	                var p = n.attr("url");
	                if (p != "") {
	                    n.removeClass("collapsed");
	                    n.addClass("table_loading");
	                    window.setTimeout(function() {
	                        h(q, p, n)
	                    },
	                    200)
	                } else {
	                    toggle(n)
	                }
	            })
            }
        }
        function h(m, l, k) {
            b.ajax({
                url: l,
                error: function() {
                    try {
                        top.Dialog.alert("数据加载失败，请检查dataPath是否正确")
                    } catch(n) {
                        alert("数据加载失败，请检查dataPath是否正确")
                    }
                },
                success: function(p) {
                    var o = m.parents("tr");
                    var q = b(p);
                    o.after(q);
                    o.attr("url", "");
                    o.removeClass("table_loading");
                    o.addClass("expanded");
                    var n = o.find("td").eq(0);
                    var r;
                    if (g) {
                        r = parseInt(n.css("padding-left")) + options.indent * 2
                    } else {
                        r = parseInt(n.css("padding-left")) + options.indent
                    }
                    children_of(o).each(function() {
                        b(b(this).children("td")[0]).css("padding-left", r + "px");
                        var s = b(this);
                        if (g) {
                            var v = s.attr("id");
                            var u = s.attr("class");
                            var t = check_parent_of(u);
                            if (t.attr("checked") == true || t.attr("checked") == "true") {
                                b(b(this).children("td")[0]).prepend('<input checked="true" type="checkbox" id="check-' + v + '" class="check-' + u + '" style="margin-top:6px" onclick=javascript:selectCheckbox("' + v + '")>')
                            } else {
                                b(b(this).children("td")[0]).prepend('<input type="checkbox" id="check-' + v + '" class="check-' + u + '" style="margin-top:6px" onclick=javascript:selectCheckbox("' + v + '")>')
                            }
                        }
                        if (s.not(".parent") && s.attr("url") != null) {
                            s.addClass("parent")
                        }
                        if (s.is(".parent")) {
                            d(s)
                        }
                    })
                }
            })
        }
    };
    b.fn.acts_as_tree_table.defaults = {
        expandable: true,
        default_state: "expanded",
        indent: 19,
        tree_column: 0
    };
    b.fn.collapse = function() {
        collapse(this)
    };
    b.fn.expand = function() {
        expand(this)
    };
    b.fn.toggleBranch = function() {
        toggle(this)
    }
})(jQuery);
function selectCheckbox(b) {
    var a = document.getElementById("check-" + b).checked;
    check_children_of(b).each(function() {
        var c = $(this);
        c[0].checked = a;
        selectCheckbox($(this).parents("tr").attr("id"))
    })
}

function children_of(a) {
    return $("tr.child-of-" + a[0].id)
}
function check_children_of(a) {
    return $(".check-child-of-" + a)
}
function check_parent_of(b) {
    var a = b.substring(9, b.length);
    return $("#check-" + a)
}
function toggle(a) {
    if (a.is(".collapsed")) {
        a.removeClass("collapsed");
        a.addClass("expanded");
        expand(a);
    } else {
        a.removeClass("expanded");
        a.addClass("collapsed");
        collapse(a);
    }
}
function collapse(a) {
	a.removeClass("expanded");
    a.addClass("collapsed");
    children_of(a).each(function() {
        var b = $(this);
        collapse(b);
        b.hide()
    })
}

function collapseAll(obj) {
	obj.find("tr.child-of-node-0").each(function(){
		var o = $(this);
		collapse(o);
	});
}
//展开子级
function expand(a) {
	a.removeClass("collapsed");
    a.addClass("expanded");
    children_of(a).each(function() {
        var b = $(this);
        if (b.is(".expanded.parent")) {
            expand(b)
        }
        b.show()
    })
};

//展开全部下级
function expand2(a) {
	a.removeClass("collapsed");
    a.addClass("expanded");
    children_of(a).each(function() {
        var b = $(this);
        expand2(b);
        b.show();
    });
};


function expandAll(obj) {
	obj.find("tr.child-of-node-0").each(function(){
		var o = $(this);
		expand2(o);
	});
}

