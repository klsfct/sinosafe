(function(b) {
    b.switchable = b.switchable || {};
    b.switchable = {
        cfg: {
            triggers: "a",
            currentCls: "current",
            initIndex: 0,
            triggerType: "mouse",
            delay: 0.1,
            effect: "default",
            steps: 1,
            visible: 1,
            speed: 0.7,
            easing: "swing",
            circular: false,
            vertical: false,
            panelSwitch: false,
            beforeSwitch: null,
            onSwitch: null,
            api: false
        },
        addEffect: function(d, e) {
            a[d] = e
        }
    };
    var a = {
        "default": function(e, d) {
            this.getPanels().hide();
            this.getVisiblePanel(e).show();
            d.call()
        },
        ajax: function(e, d) {
            this.getPanels().first().load(this.getTriggers().eq(e).attr("href"), d)
        },
        iframe: function(e, d) {
            this.getPanels().first().find("iframe").attr("src", this.getTriggers().eq(e).attr("href"))
        }
    };
    function c(h, g, d) {
        var e = this,
        k = b(this),
        j,
        f = h.length - 1;
        b.each(d,
        function(l, m) {
            if (b.isFunction(m)) {
                k.bind(l, m)
            }
        });
        b.extend(this, {
            click: function(m, n) {
                var l = h.eq(m);
                if (typeof m == "string" && m.replace("#", "")) {
                    l = h.filter("[href*=" + m.replace("#", "") + "]");
                    m = Math.max(l.index(), 0)
                }
                n = n || b.Event();
                n.type = "beforeSwitch";
                k.trigger(n, [m]);
                if (n.isDefaultPrevented()) {
                    return
                }
                a[d.effect].call(e, m,
                function() {
                    n.type = "onSwitch";
                    k.trigger(n, [m])
                });
                n.type = "onStart";
                k.trigger(n, [m]);
                if (n.isDefaultPrevented()) {
                    return
                }
                j = m;
                h.removeClass(d.currentCls);
                l.addClass(d.currentCls);
                return e
            },
            getCfg: function() {
                return d
            },
            getTriggers: function() {
                return h
            },
            getPanels: function() {
                return g
            },
            getVisiblePanel: function(l) {
                return e.getPanels().slice(l * d.steps, (l + 1) * d.steps)
            },
            getIndex: function() {
                return j
            },
            move: function(l) {
                if (g.parent().is(":animated") || g.length <= d.visible) {
                    return e
                }
                if (typeof l == "number") {
                    if (l < 0) {
                        return d.circular ? e.click(f) : e
                    } else {
                        if (l > f) {
                            return d.circular ? e.click(0) : e
                        } else {
                            return e.click(l)
                        }
                    }
                } else {
                    return e.click()
                }
            },
            next: function() {
                return e.move(j + 1)
            },
            prev: function() {
                return e.move(j - 1)
            },
            bind: function(l, m) {
                k.bind(l, m);
                return e
            },
            unbind: function(l) {
                k.unbind(l);
                return e
            },
            beforeSwitch: function(l) {
                return this.bind("beforeSwitch", l)
            },
            onSwitch: function(l) {
                return this.bind("onSwitch", l)
            },
            resetPosition: function(l) {}
        });
        var i;
        h.each(function(l) {
            if (d.triggerType === "mouse") {
                b(this).bind({
                    mouseenter: function(m) {
                        if (l !== j) {
                            i = setTimeout(function() {
                                e.click(l, m)
                            },
                            d.delay * 1000)
                        }
                    },
                    mouseleave: function() {
                        clearTimeout(i)
                    }
                })
            } else {
                b(this).bind("click",
                function(m) {
                    if (l !== j) {
                        e.click(l, m)
                    }
                    return false
                })
            }
        });
        if (location.hash) {
            e.click(location.hash)
        } else {
            if (d.initIndex === 0 || d.initIndex > 0) {
                e.click(d.initIndex)
            }
        }
        g.find("a[href^=#]").click(function(l) {
            e.click(b(this).attr("href"), l)
        });
        if (d.panelSwitch) {
            g.css("cursor", "pointer").click(function() {
                e.next();
                return false
            })
        }
    }
    b.fn.switchable = function(f, e) {
        var g = this.eq(typeof e == "number" ? e: 0).data("switchable");
        if (g) {
            return g
        }
        if (b.isFunction(e)) {
            e = {
                beforeSwitch: e
            }
        }
        var h = b.extend({},
        b.switchable.cfg),
        d = this.length;
        e = b.extend(h, e);
        this.each(function(n) {
            var j = b(this);
            var k = f.jquery ? f: j.children(f);
            if (!k.length) {
                k = d == 1 ? b(f) : j.parent().find(f)
            }
            var m = j.find(e.triggers);
            if (!m.length) {
                var l = Math.ceil(k.length / e.steps);
                for (var n = 1; n <= l; n++) {
                    b("<a>", {
                        href: "javascript:void(0);",
                        target: "_self",
                        text: n
                    }).appendTo(j)
                }
                m = j.children("a")
            }
            g = new c(m, k, e);
            j.data("switchable", g)
        });
        return e.api ? g: this
    }
})(jQuery); (function(a) {
    a.fn.wheel = function(d) {
        return this[d ? "bind": "trigger"]("wheel", d)
    };
    a.event.special.wheel = {
        setup: function() {
            a.event.add(this, c, b, {})
        },
        teardown: function() {
            a.event.remove(this, c, b)
        }
    };
    var c = !a.browser.mozilla ? "mousewheel": "DOMMouseScroll" + (a.browser.version < "1.9" ? " mousemove": "");
    function b(d) {
        switch (d.type) {
        case "mousemove":
            return a.extend(d.data, {
                clientX: d.clientX,
                clientY: d.clientY,
                pageX: d.pageX,
                pageY: d.pageY
            });
        case "DOMMouseScroll":
            a.extend(d, d.data);
            d.delta = -d.detail / 3;
            break;
        case "mousewheel":
            d.delta = d.wheelDelta / 120;
            break
        }
        d.type = "wheel";
        return a.event.handle.call(this, d, d.delta)
    }
    a.fn.mousewheel = function() {
        this.each(function() {
            var d = a(this).switchable();
            d.getPanels().parent().wheel(function(f, g) {
                if (g < 0) {
                    d.next()
                } else {
                    d.prev()
                }
                return false
            })
        });
        return this
    }
})(jQuery);