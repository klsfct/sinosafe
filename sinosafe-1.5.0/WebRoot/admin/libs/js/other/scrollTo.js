(function(c) {
    var a = c.scrollTo = function(f, e, d) {
        c(window).scrollTo(f, e, d)
    };
    a.defaults = {
        axis: "xy",
        duration: parseFloat(c.fn.jquery) >= 1.3 ? 0 : 1
    };
    a.window = function(d) {
        return c(window).scrollable()
    };
    c.fn.scrollable = function() {
        return this.map(function() {
            var e = this,
            d = !e.nodeName || c.inArray(e.nodeName.toLowerCase(), ["iframe", "#document", "html", "body"]) != -1;
            if (!d) {
                return e
            }
            var f = (e.contentWindow || e).document || e.ownerDocument || e;
            return c.browser.safari || f.compatMode == "BackCompat" ? f.body: f.documentElement
        })
    };
    c.fn.scrollTo = function(f, e, d) {
        if (typeof e == "object") {
            d = e;
            e = 0
        }
        if (typeof d == "function") {
            d = {
                onAfter: d
            }
        }
        if (f == "max") {
            f = 9000000000
        }
        d = c.extend({},
        a.defaults, d);
        e = e || d.speed || d.duration;
        d.queue = d.queue && d.axis.length > 1;
        if (d.queue) {
            e /= 2
        }
        d.offset = b(d.offset);
        d.over = b(d.over);
        return this.scrollable().each(function() {
            var m = this,
            k = c(m),
            l = f,
            j,
            h = {},
            n = k.is("html,body");
            switch (typeof l) {
            case "number":
            case "string":
                if (/^([+-]=)?\d+(\.\d+)?(px)?$/.test(l)) {
                    l = b(l);
                    break
                }
                l = c(l, this);
            case "object":
                if (l.is || l.style) {
                    j = (l = c(l)).offset()
                }
            }
            c.each(d.axis.split(""),
            function(r, s) {
                var t = s == "x" ? "Left": "Top",
                u = t.toLowerCase(),
                q = "scroll" + t,
                o = m[q],
                p = s == "x" ? "Width": "Height";
                if (j) {
                    h[q] = j[u] + (n ? 0 : o - k.offset()[u]);
                    if (d.margin) {
                        h[q] -= parseInt(l.css("margin" + t)) || 0;
                        h[q] -= parseInt(l.css("border" + t + "Width")) || 0
                    }
                    h[q] += d.offset[u] || 0;
                    if (d.over[u]) {
                        h[q] += l[p.toLowerCase()]() * d.over[u]
                    }
                } else {
                    h[q] = l[u]
                }
                if (/^\d+$/.test(h[q])) {
                    h[q] = h[q] <= 0 ? 0 : Math.min(h[q], g(p))
                }
                if (!r && d.queue) {
                    if (o != h[q]) {
                        i(d.onAfterFirst)
                    }
                    delete h[q]
                }
            });
            i(d.onAfter);
            function i(o) {
                k.animate(h, e, d.easing, o &&
                function() {
                    o.call(this, f, d)
                })
            }
            function g(s) {
                var p = "scroll" + s;
                if (!n) {
                    return m[p]
                }
                var r = "client" + s,
                q = m.ownerDocument.documentElement,
                o = m.ownerDocument.body;
                return Math.max(q[p], o[p]) - Math.min(q[r], o[r])
            }
        }).end()
    };
    function b(d) {
        return typeof d == "object" ? d: {
            top: d,
            left: d
        }
    }
})(jQuery);