(function(c) {
    jQuery.fn.jomino = function(h) {
        var k = {
            interval: "400",
            duration: "800",
            easing: "swing",
            reverse: false,
            random: false,
            autostart: true,
            onAnimate: null,
            onComplete: null
        };
        var i = jQuery.extend({},
        k, h);
        var e = jQuery.makeArray(this);
        var d = new Array;
        var g = i.reverse;
        var j = null;
        e = i.reverse ? e.reverse() : e;
        e = i.random ? e.sort(function() {
            return 0.5 - Math.random()
        }) : e;
        jQuery.jomino = {
            forward: function() {
                g = false;
                if (!j) {
                    j = f()
                }
            },
            rewind: function() {
                if (!g) {
                    jQuery.jomino.stop();
                    g = true;
                    j = f()
                }
            },
            stop: function() {
                clearTimeout(j);
                j = null;
                g = false
            }
        };
        function f() {
            var m = g ? d.length: e.length;
            var l = null;
            if (m > 0) {
                if (!g) {
                    l = e.shift();
                    d.push(l)
                } else {
                    l = d.pop();
                    e.unshift(l)
                }
                jQuery.fn.jomino.animateElement(l, g, i.duration, i.easing);
                setTimeout(function() {
                    a(i, "onAnimate", l)
                },
                i.duration);
                j = setTimeout(function() {
                    f()
                },
                i.interval)
            } else {
                if (m == 0) {
                    setTimeout(function() {
                        a(i, "onComplete", l)
                    },
                    i.duration);
                    g = false;
                    j = null
                }
            }
        }
        if (i.autostart) {
            jQuery.jomino.forward()
        }
    };
    jQuery.fn.jomino.animateElement = function(f, d, g, e) {
        d ? jQuery(f).fadeOut(g, e) : jQuery(f).fadeIn(g, e)
    };
    function a(g, e, h) {
        var f = g[e];
        if (c.isFunction(f)) {
            try {
                return f.call(h)
            } catch(d) {
                b("jomino." + e + ": ", d);
                return false
            }
        }
        return true
    }
    function b(d, e) {
        if (window.console && window.console.log) {
            window.console.log(d, e)
        }
    }
})(jQuery);