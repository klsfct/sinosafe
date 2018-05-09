$.switchable.addEffect("fade",
function(f, b) {
    var d = this,
    a = d.getCfg(),
    c = d.getPanels(),
    e = d.getVisiblePanel(f);
    c.hide();
    e.fadeIn(a.speed * 1000, b)
});
$.switchable.addEffect("scroll",
function(e, d) {
    var l = this,
    g = l.getCfg(),
    b = l.getVisiblePanel(e),
    c = l.getPanels().parent(),
    j = l.getIndex(),
    h = l.getTriggers().length - 1,
    f = (j === 0 && e === h) || (j === h && e === 0),
    k = (j === 0 && e === h) ? true: false,
    a = g.vertical ? {
        top: -b.position().top
    }: {
        left: -b.position().left
    };
    if (c.is(":animated")) {
        c.stop(true)
    }
    c.animate(a, g.speed * 1000, g.easing,
    function() {
        d.call();
        if (f) {
            l.resetPosition(k)
        }
    })
}); (function(b) {
    var a = b.switchable;
    a.plugin = a.plugin || {};
    a.plugin.autoplay = {
        cfg: {
            autoplay: true,
            interval: 3,
            autopause: true,
            api: false
        }
    };
    b.fn.autoplay = function(c) {
        if (typeof c == "number") {
            c = {
                interval: c
            }
        }
        var e = b.extend({},
        a.plugin.autoplay.cfg),
        d;
        b.extend(e, c);
        this.each(function() {
            var g = b(this).switchable();
            if (g) {
                d = g
            }
            var i, f, h = true;
            g.play = function() {
                if (i) {
                    return
                }
                h = false;
                i = setInterval(function() {
                    g.next()
                },
                e.interval * 1000);
                g.next()
            };
            g.pause = function() {
                i = clearInterval(i)
            };
            g.stop = function() {
                g.pause();
                h = true
            };
            if (e.autopause) {
                g.getPanels().hover(function() {
                    g.pause();
                    clearTimeout(f)
                },
                function() {
                    if (!h) {
                        f = setTimeout(g.play, e.interval * 1000)
                    }
                })
            }
            if (e.autoplay) {
                setTimeout(g.play, e.interval * 1000)
            }
        });
        return e.api ? d: this
    }
})(jQuery); (function(a) {
    a.fn.carousel = function() {
        this.each(function() {
            var d = a(this).switchable(),
            f = d.getCfg(),
            g = d.getPanels(),
            c = g.parent(),
            e = d.getTriggers().length - 1,
            h = g.slice(0, f.steps),
            j = g.slice(e * f.steps),
            k = f.vertical ? j.position().top: j.position().left,
            i = f.vertical ? "top": "left",
            b = g.length > f.visible,
            l = 0;
            g.css("position", "relative").each(function() {
                l += f.vertical ? a(this).outerHeight(true) : a(this).outerWidth(true)
            });
            if (b && j.length < f.visible) {
                c.append(g.slice(0, f.visible).clone().addClass("clone"))
            }
            a.extend(d, {
                move: function(m) {
                    if (c.is(":animated") || !b) {
                        return this
                    }
                    if (m < 0) {
                        this.adjustPosition(true);
                        return this.click(e)
                    } else {
                        if (m > e) {
                            this.adjustPosition(false);
                            return this.click(0)
                        } else {
                            return this.click(m)
                        }
                    }
                },
                adjustPosition: function(m) {
                    var n = m ? j: h;
                    a.each(n,
                    function() {
                        a(this).css(i, m ? -l: l + "px")
                    })
                },
                resetPosition: function(m) {
                    var n = m ? j: h;
                    a.each(n,
                    function() {
                        a(this).css(i, "0px")
                    });
                    c.css(i, m ? -k: 0 + "px")
                }
            })
        });
        return this
    }
})(jQuery);