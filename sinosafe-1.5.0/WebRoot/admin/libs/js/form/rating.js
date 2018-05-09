(function(g) {
    var f = "../";
    if (g("#skin").attr("prePath") != null) {
        f = g("#skin").attr("prePath")
    }
    var d = 500;
    g.fn.ratingRender = function() {
        g(this).html(" ");
        if (g(this).attr("id") == null || g(this).attr("id") == "") {
            g(this).attr("id", "star_" + d);
            d++
        }
        var p = false;
        if (g(this).attr("readOnly") == true || g(this).attr("readOnly") == "true") {
            p = true
        }
        var j = 0;
        if (g(this).attr("value")) {
            j = Number(g(this).attr("value"))
        }
        var o = "score";
        if (g(this).attr("name")) {
            o = g(this).attr("name")
        }
        var l = 5;
        if (g(this).attr("number")) {
            l = Number(g(this).attr("number"))
        }
        var q = ["非常差", "比较差", "一般", "好", "非常好"];
        if (g(this).attr("tip")) {
            q = g(this).attr("tip").split(",")
        }
        var m = false;
        if (g(this).attr("starHalf") == true || g(this).attr("starHalf") == "true") {
            m = true
        }
        var i = false;
        if (g(this).attr("showCancel") == true || g(this).attr("showCancel") == "true") {
            i = true
        }
        var n = "star-on.gif";
        var k = "star-off.gif";
        if (g(this).attr("starOn")) {
            n = g(this).attr("starOn")
        }
        if (g(this).attr("starOff")) {
            k = g(this).attr("starOff")
        }
        g(this).Rating({
            readOnly: p,
            start: j,
            scoreName: o,
            number: l,
            hintList: q,
            starHalf: m,
            showCancel: i,
            starOn: n,
            starOff: k
        });
        g(this).find("img").each(function() {
            try {
                addTooltip(g(this)[0])
            } catch(r) {}
        })
    };
    g.fn.Rating = function(n) {
        options = g.extend({},
        g.fn.Rating.defaults, n);
        if (this.attr("id") === undefined) {
            c("Invalid selector!");
            return
        }
        $this = g(this);
        if (options.number > 20) {
            options.number = 20
        } else {
            if (options.number < 0) {
                options.number = 0
            }
        }
        if (options.path.substring(options.path.length - 1, options.path.length) != "/") {
            options.path += "/"
        }
        var s = $this.attr("id"),
        z = options.path,
        x = options.cancelOff,
        v = options.cancelOn,
        t = options.showHalf,
        q = options.starHalf,
        k = options.starOff,
        p = options.starOn,
        u = options.onClick,
        j = 0,
        o = "";
        if (!isNaN(options.start) && options.start > 0) {
            j = (options.start > options.number) ? options.number: options.start
        }
        for (var r = 1; r <= options.number; r++) {
            o = (options.number <= options.hintList.length && options.hintList[r - 1] !== null) ? options.hintList[r - 1] : r;
            starFile = (j >= r) ? p: k;
            $this.append('<img width="16" id="' + s + "-" + r + '" src="' + z + starFile + '" altName="' + r + '" title="' + o + '" class="' + s + '"/>').append((r < options.number) ? "&nbsp;": "")
        }
        $this.append('<input id="' + s + '-score" type="hidden" name="' + options.scoreName + '"/>');
        g("#" + s + "-score").val(j);
        if (t) {
            var m = g("input#" + s + "-score").val(),
            l = Math.ceil(m),
            w = (l - m).toFixed(1);
            if (w >= 0.3 && w <= 0.7) {
                l = l - 0.5;
                g("img#" + s + "-" + Math.ceil(l)).attr("src", z + q)
            } else {
                if (w >= 0.8) {
                    l--
                } else {
                    g("img#" + s + "-" + l).attr("src", z + p)
                }
            }
        }
        if (!options.readOnly) {
            if (options.showCancel) {
                var y = '<img width="16" src="' + z + options.cancelOff + '" altName="x" title="' + options.cancelHint + '" class="button-cancel"/>';
                if (options.cancelPlace == "left") {
                    $this.prepend(y + "&nbsp;")
                } else {
                    $this.append("&nbsp;").append(y)
                }
                $this.css("width", options.number * 25 + 25);
                g("#" + s + " img.button-cancel").live("mouseenter",
                function() {
                    g(this).attr("src", z + v);
                    g("img." + s).attr("src", z + k)
                }).live("mouseleave",
                function() {
                    g(this).attr("src", z + x);
                    g("img." + s).trigger("mouseout")
                }).live("click",
                function() {
                    g("input#" + s + "-score").val(0);
                    if (u) {
                        u(0)
                    }
                })
            } else {
                $this.css("width", options.number * 25)
            }
            g("img." + s).live("mouseenter",
            function() {
                var A = g("img." + s).length;
                for (var B = 1; B <= A; B++) {
                    if (B <= g(this).attr("altName")) {
                        g("img#" + s + "-" + B).attr("src", z + p)
                    } else {
                        g("img#" + s + "-" + B).attr("src", z + k)
                    }
                }
            }).live("click",
            function() {
                g("input#" + s + "-score").val(g(this).attr("altName"));
                if (u) {
                    u(g(this).attr("altName"))
                }
            }).live("mouseleave",
            function() {
                var F = s,
                B = g("img." + F).length,
                E = g("input#" + F + "-score").val();
                for (var C = 1; C <= B; C++) {
                    if (C <= E) {
                        g("img#" + F + "-" + C).attr("src", z + p)
                    } else {
                        g("img#" + F + "-" + C).attr("src", z + k)
                    }
                }
                if (t) {
                    var E = g("input#" + F + "-score").val(),
                    A = Math.ceil(E),
                    D = (A - E).toFixed(1);
                    if (D >= 0.3 && D <= 0.7) {
                        A = A - 0.5;
                        g("img#" + F + "-" + Math.ceil(A)).attr("src", z + q)
                    } else {
                        if (D >= 0.8) {
                            A--
                        } else {
                            g("img#" + F + "-" + A).attr("src", z + p)
                        }
                    }
                }
            }).css("cursor", "pointer")
        } else {
            $this.css("cursor", "default")
        }
        return $this
    };
    g.fn.Rating.defaults = {
        cancelHint: "取消评价!",
        cancelOff: "cancel-off.gif",
        cancelOn: "cancel-on.gif",
        cancelPlace: "left",
        hintList: ["非常差", "比较差", "一般", "好", "非常好"],
        number: 5,
        path: f + "libs/images/star/",
        readOnly: false,
        scoreName: "score",
        showCancel: false,
        showHalf: false,
        starHalf: "star-half.gif",
        start: 0,
        starOff: "star-off.gif",
        starOn: "star-on.gif"
    };
    g.fn.Rating.readOnly = function(i) {
        if (i) {
            g("img." + $this.attr("id")).die();
            $this.css("cursor", "default").die()
        } else {
            e();
            h();
            b();
            $this.css("cursor", "pointer")
        }
        return g.fn.Rating
    };
    g.fn.Rating.start = function(i) {
        a(i);
        return g.fn.Rating
    };
    g.fn.Rating.click = function(j) {
        var i = (j >= options.number) ? options.number: j;
        a(i);
        if (options.onClick) {
            options.onClick(i)
        } else {
            c('You should add the "onClick: function() {}" option.')
        }
        return g.fn.Rating
    };
    function e() {
        var i = $this.attr("id");
        g("img." + i).live("mouseenter",
        function() {
            var j = g("img." + i).length;
            for (var k = 1; k <= j; k++) {
                if (k <= g(this).attr("altName")) {
                    g("img#" + i + "-" + k).attr("src", options.path + options.starOn)
                } else {
                    g("img#" + i + "-" + k).attr("src", options.path + options.starOff)
                }
            }
        })
    }
    function h() {
        $this.live("mouseleave",
        function() {
            var m = g(this).attr("id");
            var j = g("img." + m).length;
            var l = g("input#" + m + "-score").val();
            for (var k = 1; k <= j; k++) {
                if (k <= l) {
                    g("img#" + m + "-" + k).attr("src", options.path + options.starOn)
                } else {
                    g("img#" + m + "-" + k).attr("src", options.path + options.starOff)
                }
            }
        })
    }
    function b() {
        var i = $this.attr("id");
        g("img." + i).live("click",
        function() {
            g("input#" + i + "-score").val(g(this).attr("altName"))
        })
    }
    function a(m) {
        var l = $this.attr("id"),
        j = g("img." + l).length;
        g("input#" + l + "-score").val(m);
        for (var k = 1; k <= j; k++) {
            if (k <= m) {
                g("img#" + l + "-" + k).attr("src", options.path + options.starOn)
            } else {
                g("img#" + l + "-" + k).attr("src", options.path + options.starOff)
            }
        }
    }
    function c(i) {
        if (window.console && window.console.log) {
            window.console.log(i)
        }
    }
})(jQuery);