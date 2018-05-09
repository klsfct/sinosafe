(function(a) {
    a.fn.dragsort = function(c) {
        var d = a.extend({},
        a.fn.dragsort.defaults, c);
        var b = new Array();
        var f = null,
        e = null;
        if (this.selector) {
            a("head").append("<style type='text/css'>" + (this.selector.split(",").join(" " + d.dragSelector + ",") + " " + d.dragSelector) + " { cursor: pointer; }</style>")
        }
        this.each(function(h, g) {
            if (a(g).is("table") && a(g).children().size() == 1 && a(g).children().is("tbody")) {
                g = a(g).children().get(0)
            }
            var j = {
                draggedItem: null,
                placeHolderItem: null,
                pos: null,
                offset: null,
                offsetLimit: null,
                container: g,
                init: function() {
                    a(this.container).attr("listIdx", h).mousedown(this.grabItem).find(d.dragSelector).css("cursor", "pointer")
                },
                grabItem: function(l) {
                    if (l.button == 2 || a(l.target).is(d.dragSelectorExclude)) {
                        return
                    }
                    var n = l.target;
                    while (!a(n).is("[listIdx=" + a(this).attr("listIdx") + "] " + d.dragSelector)) {
                        if (n == this) {
                            return
                        }
                        n = n.parentNode
                    }
                    if (f != null && f.draggedItem != null) {
                        f.dropItem()
                    }
                    a(l.target).css("cursor", "move");
                    f = b[a(this).attr("listIdx")];
                    f.draggedItem = a(n).closest(d.itemSelector);
                    var i = parseInt(f.draggedItem.css("marginTop"));
                    var m = parseInt(f.draggedItem.css("marginLeft"));
                    f.offset = f.draggedItem.offset();
                    f.offset.top = l.pageY - f.offset.top + (isNaN(i) ? 0 : i) - 1;
                    f.offset.left = l.pageX - f.offset.left + (isNaN(m) ? 0 : m) - 1;
                    if (!d.dragBetween) {
                        var k = a(f.container).outerHeight() == 0 ? Math.max(1, Math.round(0.5 + a(f.container).children(d.itemSelector).size() * f.draggedItem.outerWidth() / a(f.container).outerWidth())) * f.draggedItem.outerHeight() : a(f.container).outerHeight();
                        f.offsetLimit = a(f.container).offset();
                        f.offsetLimit.right = f.offsetLimit.left + a(f.container).outerWidth() - f.draggedItem.outerWidth();
                        f.offsetLimit.bottom = f.offsetLimit.top + k - f.draggedItem.outerHeight()
                    }
                    f.draggedItem.css({
                        position: "absolute",
                        opacity: 0.8,
                        "z-index": 999
                    }).after(d.placeHolderTemplate);
                    f.placeHolderItem = f.draggedItem.next().css("height", f.draggedItem.height()).attr("placeHolder", true);
                    a(b).each(function(p, o) {
                        o.ensureNotEmpty();
                        o.buildPositionTable()
                    });
                    f.setPos(l.pageX, l.pageY);
                    a(document).bind("selectstart", f.stopBubble);
                    a(document).bind("mousemove", f.swapItems);
                    a(document).bind("mouseup", f.dropItem);
                    return false
                },
                setPos: function(i, m) {
                    var l = m - this.offset.top;
                    var k = i - this.offset.left;
                    if (!d.dragBetween) {
                        l = Math.min(this.offsetLimit.bottom, Math.max(l, this.offsetLimit.top));
                        k = Math.min(this.offsetLimit.right, Math.max(k, this.offsetLimit.left))
                    }
                    this.draggedItem.parents().each(function() {
                        if (a(this).css("position") != "static" && (!a.browser.mozilla || a(this).css("display") != "table")) {
                            var n = a(this).offset();
                            l -= n.top;
                            k -= n.left;
                            return false
                        }
                    });
                    this.draggedItem.css({
                        top: l,
                        left: k
                    })
                },
                buildPositionTable: function() {
                    var i = this.draggedItem == null ? null: this.draggedItem.get(0);
                    var k = new Array();
                    a(this.container).children(d.itemSelector).each(function(l, n) {
                        if (n != i) {
                            var m = a(n).offset();
                            m.right = m.left + a(n).width();
                            m.bottom = m.top + a(n).height();
                            m.elm = n;
                            k.push(m)
                        }
                    });
                    this.pos = k
                },
                dropItem: function() {
                    if (f.draggedItem == null) {
                        return
                    }
                    a(f.container).find(d.dragSelector).css("cursor", "pointer");
                    f.placeHolderItem.before(f.draggedItem);
                    f.draggedItem.css({
                        position: "",
                        top: "",
                        left: "",
                        opacity: "",
                        "z-index": ""
                    });
                    f.placeHolderItem.remove();
                    a("*[emptyPlaceHolder]").remove();
                    d.dragEnd.apply(f.draggedItem);
                    f.draggedItem = null;
                    a(document).unbind("selectstart", f.stopBubble);
                    a(document).unbind("mousemove", f.swapItems);
                    a(document).unbind("mouseup", f.dropItem);
                    return false
                },
                stopBubble: function() {
                    return false
                },
                swapItems: function(n) {
                    if (f.draggedItem == null) {
                        return false
                    }
                    f.setPos(n.pageX, n.pageY);
                    var m = f.findPos(n.pageX, n.pageY);
                    var l = f;
                    for (var k = 0; m == -1 && d.dragBetween && k < b.length; k++) {
                        m = b[k].findPos(n.pageX, n.pageY);
                        l = b[k]
                    }
                    if (m == -1 || a(l.pos[m].elm).attr("placeHolder")) {
                        return false
                    }
                    if (e == null || e.top > f.draggedItem.offset().top || e.left > f.draggedItem.offset().left) {
                        a(l.pos[m].elm).before(f.placeHolderItem)
                    } else {
                        a(l.pos[m].elm).after(f.placeHolderItem)
                    }
                    a(b).each(function(p, o) {
                        o.ensureNotEmpty();
                        o.buildPositionTable()
                    });
                    e = f.draggedItem.offset();
                    return false
                },
                findPos: function(k, m) {
                    for (var l = 0; l < this.pos.length; l++) {
                        if (this.pos[l].left < k && this.pos[l].right > k && this.pos[l].top < m && this.pos[l].bottom > m) {
                            return l
                        }
                    }
                    return - 1
                },
                ensureNotEmpty: function() {
                    if (!d.dragBetween) {
                        return
                    }
                    var i = this.draggedItem == null ? null: this.draggedItem.get(0);
                    var l = null,
                    k = true;
                    a(this.container).children(d.itemSelector).each(function(m, n) {
                        if (a(n).attr("emptyPlaceHolder")) {
                            l = n
                        } else {
                            if (n != i) {
                                k = false
                            }
                        }
                    });
                    if (k && l == null) {
                        a(this.container).append(d.placeHolderTemplate).children(":last").attr("emptyPlaceHolder", true)
                    } else {
                        if (!k && l != null) {
                            a(l).remove()
                        }
                    }
                }
            };
            j.init();
            b.push(j)
        });
        return this
    };
    a.fn.dragsort.defaults = {
        itemSelector: "li",
        dragSelector: "li",
        dragSelectorExclude: "input, a[href]",
        dragEnd: function() {},
        dragBetween: false,
        placeHolderTemplate: "<li>&nbsp;</li>"
    }
})(jQuery);