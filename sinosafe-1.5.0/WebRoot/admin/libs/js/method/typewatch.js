(function(a) {
    a.fn.typeWatch = function(d) {
        var b = a.extend({
            wait: 750,
            callback: function() {},
            highlight: true,
            captureLength: 2
        },
        d);
        function c(h, g) {
            var f = a(h.el).val();
            if ((f.length > b.captureLength && f.toUpperCase() != h.text) || (g && f.length > b.captureLength)) {
                h.text = f.toUpperCase();
                h.cb(f)
            }
        }
        function e(g) {
            if (g.type.toUpperCase() == "TEXT" || g.nodeName.toUpperCase() == "TEXTAREA") {
                var h = {
                    timer: null,
                    text: a(g).val().toUpperCase(),
                    cb: b.callback,
                    el: g,
                    wait: b.wait
                };
                if (b.highlight) {
                    a(g).focus(function() {
                        this.select()
                    })
                }
                var f = function(i) {
                    var l = h.wait;
                    var k = false;
                    if (i.keyCode == 13 && this.type.toUpperCase() == "TEXT") {
                        l = 1;
                        k = true
                    }
                    var j = function() {
                        c(h, k)
                    };
                    clearTimeout(h.timer);
                    h.timer = setTimeout(j, l)
                };
                a(g).keydown(f)
            }
        }
        return this.each(function(f) {
            e(this)
        })
    }
})(jQuery);