(function(e) {
    e.fn.accorditionRender = function() {
        var g = "click";
        if (e(this).attr("hoverMode") == "true") {
            g = "mouseover"
        }
        e(this).accordion({
            event: g
        })
    };
    e.ui = e.ui || {};
    e.fn.extend({
        accordion: function(h, i) {
            var g = Array.prototype.slice.call(arguments, 1);
            return this.each(function() {
                if (typeof h == "string") {
                    var j = e.data(this, "ui-accordion");
                    j[h].apply(j, g)
                } else {
                    if (!e(this).is(".ui-accordion")) {
                        e.data(this, "ui-accordion", new e.ui.accordion(this, h))
                    }
                }
            })
        },
        activate: function(g) {
            return this.accordion("activate", g)
        }
    });
    e.ui.accordion = function(g, h) {
        this.options = h = e.extend({},
        e.ui.accordion.defaults, h);
        this.element = g;
        e(g).addClass("ui-accordion");
        e(g).find(">a").addClass("titlebar");
        if (h.navigation) {
            var k = e(g).find("a").filter(h.navigationFilter);
            if (k.length) {
                if (k.filter(h.header).length) {
                    h.active = k
                } else {
                    h.active = k.parent().parent().prev();
                    k.addClass("current")
                }
            }
        }
        h.headers = e(g).find(">a");
        h.active = c(h.headers, h.active);
        if (h.fillSpace) {
            var j = e(g).parent().height();
            h.headers.each(function() {
                j -= e(this).outerHeight()
            });
            var i = 0;
            h.headers.next().each(function() {
                i = Math.max(i, e(this).innerHeight() - e(this).height())
            }).height(j - i)
        } else {
            if (h.autoheight) {
                var j = 0;
                h.headers.next().each(function() {
                    j = Math.max(j, e(this).outerHeight())
                }).height(j)
            }
        }
        h.headers.not(h.active || "").next().hide();
        h.active.parent().andSelf().addClass(h.selectedClass);
        if (h.event) {
            e(g).bind((h.event) + ".ui-accordion", f)
        }
    };
    e.ui.accordion.prototype = {
        activate: function(g) {
            f.call(this.element, {
                target: c(this.options.headers, g)[0]
            })
        },
        enable: function() {
            this.options.disabled = false
        },
        disable: function() {
            this.options.disabled = true
        },
        destroy: function() {
            this.options.headers.next().css("display", "");
            if (this.options.fillSpace || this.options.autoheight) {
                this.options.headers.next().css("height", "")
            }
            e.removeData(this.element, "ui-accordion");
            e(this.element).removeClass("ui-accordion").unbind(".ui-accordion")
        }
    };
    function b(h, g) {
        return function() {
            return h.apply(g, arguments)
        }
    }
    function d(i) {
        if (!e.data(this, "ui-accordion")) {
            return
        }
        var g = e.data(this, "ui-accordion");
        var h = g.options;
        h.running = i ? 0 : --h.running;
        if (h.running) {
            return
        }
        if (h.clearStyle) {
            h.toShow.add(h.toHide).css({
                height: "",
                overflow: ""
            })
        }
        e(this).triggerHandler("change.ui-accordion", [h.data], h.change)
    }
    function a(g, k, l, j, m) {
        var i = e.data(this, "ui-accordion").options;
        i.toShow = g;
        i.toHide = k;
        i.data = l;
        var h = b(d, this);
        i.running = k.size() == 0 ? g.size() : k.size();
        if (i.animated) {
            if (!i.alwaysOpen && j) {
                e.ui.accordion.animations[i.animated]({
                    toShow: jQuery([]),
                    toHide: k,
                    complete: h,
                    down: m,
                    autoheight: i.autoheight
                })
            } else {
                e.ui.accordion.animations[i.animated]({
                    toShow: g,
                    toHide: k,
                    complete: h,
                    down: m,
                    autoheight: i.autoheight
                })
            }
        } else {
            if (!i.alwaysOpen && j) {
                g.toggle()
            } else {
                k.hide();
                g.show()
            }
            h(true)
        }
    }
    function f(l) {
        var j = e.data(this, "ui-accordion").options;
        if (j.disabled) {
            return false
        }
        if (!l.target && !j.alwaysOpen) {
            j.active.parent().andSelf().toggleClass(j.selectedClass);
            var i = j.active.next(),
            m = {
                instance: this,
                options: j,
                newHeader: jQuery([]),
                oldHeader: j.active,
                newContent: jQuery([]),
                oldContent: i
            },
            g = j.active = e([]);
            a.call(this, g, i, m);
            return false
        }
        var k = e(l.target);
        if (k.parents(j.header).length) {
            while (!k.is(j.header)) {
                k = k.parent()
            }
        }
        var h = k[0] == j.active[0];
        if (j.running || (j.alwaysOpen && h)) {
            return false
        }
        if (k.attr("class")) {
            if (k.attr("class").indexOf("titlebar") > -1) {} else {
                return
            }
        } else {
            return
        }
        j.active.parent().andSelf().toggleClass(j.selectedClass);
        if (!h) {
            k.parent().andSelf().addClass(j.selectedClass)
        }
        var g = k.next(),
        i = j.active.next(),
        m = {
            instance: this,
            options: j,
            newHeader: k,
            oldHeader: j.active,
            newContent: g,
            oldContent: i
        },
        n = j.headers.index(j.active[0]) > j.headers.index(k[0]);
        j.active = h ? e([]) : k;
        a.call(this, g, i, m, h, n);
        return false
    }
    function c(h, g) {
        return g != undefined ? typeof g == "number" ? h.filter(":eq(" + g + ")") : h.not(h.not(g)) : g === false ? e([]) : h.filter(":eq(0)")
    }
    e.extend(e.ui.accordion, {
        defaults: {
            selectedClass: "selected",
            alwaysOpen: true,
            animated: "slide",
            event: "click",
            header: "a",
            autoheight: true,
            running: 0,
            navigationFilter: function() {
                return this.href.toLowerCase() == location.href.toLowerCase()
            }
        },
        animations: {
            slide: function(g, i) {
                g = e.extend({
                    easing: "swing",
                    duration: 300
                },
                g, i);
                if (!g.toHide.size()) {
                    g.toShow.animate({
                        height: "show"
                    },
                    g);
                    return
                }
                var h = g.toHide.height(),
                j = g.toShow.height(),
                k = j / h;
                g.toShow.css({
                    height: 0,
                    overflow: "hidden"
                }).show();
                g.toHide.filter(":hidden").each(g.complete).end().filter(":visible").animate({
                    height: "hide"
                },
                {
                    step: function(l) {
                        var m = (h - l) * k;
                        if (e.browser.msie || e.browser.opera) {
                            m = Math.ceil(m)
                        }
                        g.toShow.height(m)
                    },
                    duration: g.duration,
                    easing: g.easing,
                    complete: function() {
                        if (!g.autoheight) {
                            g.toShow.css("height", "auto")
                        }
                        g.complete()
                    }
                })
            },
            bounceslide: function(g) {
                this.slide(g, {
                    easing: g.down ? "bounceout": "swing",
                    duration: g.down ? 1000 : 200
                })
            },
            easeslide: function(g) {
                this.slide(g, {
                    easing: "easeinout",
                    duration: 700
                })
            }
        }
    })
})(jQuery);