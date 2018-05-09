(function(b) {
    b.fn.vTicker = function(a) {
        var d = {
            speed: 700,
            pause: 4000,
            showItems: 3,
            animation: "",
            mousePause: true,
            isPaused: false,
            direction: "up",
            height: 0
        };
        var a = b.extend(d, a);
        moveUp = function(i, l, k) {
            if (k.isPaused) {
                return
            }
            var j = i.children("ul");
            var c = j.children("li:first").clone(true);
            if (k.height > 0) {
                l = j.children("li:first").height()
            }
            j.animate({
                top: "-=" + l + "px"
            },
            k.speed,
            function() {
                b(this).children("li:first").remove();
                b(this).css("top", "0px")
            });
            if (k.animation == "fade") {
                j.children("li:first").fadeOut(k.speed);
                if (k.height == 0) {
                    j.children("li:eq(" + k.showItems + ")").hide().fadeIn(k.speed)
                }
            }
            c.appendTo(j)
        };
        moveDown = function(i, l, k) {
            if (k.isPaused) {
                return
            }
            var j = i.children("ul");
            var c = j.children("li:last").clone(true);
            if (k.height > 0) {
                l = j.children("li:first").height()
            }
            j.css("top", "-" + l + "px").prepend(c);
            j.animate({
                top: 0
            },
            k.speed,
            function() {
                b(this).children("li:last").remove()
            });
            if (k.animation == "fade") {
                if (k.height == 0) {
                    j.children("li:eq(" + k.showItems + ")").fadeOut(k.speed)
                }
                j.children("li:first").hide().fadeIn(k.speed)
            }
        };
        return this.each(function() {
            var c = b(this);
            var g = 0;
            c.css({
                overflow: "hidden",
                position: "relative"
            }).children("ul").css({
                position: "absolute",
                margin: 0,
                padding: 0
            }).children("li").css({
                margin: 0,
                padding: 0
            });
            if (a.height == 0) {
                c.children("ul").children("li").each(function() {
                    if (b(this).height() > g) {
                        g = b(this).height()
                    }
                });
                c.children("ul").children("li").each(function() {
                    b(this).height(g)
                });
                c.height(g * a.showItems)
            } else {
                c.height(a.height)
            }
            var h = setInterval(function() {
                if (a.direction == "up") {
                    moveUp(c, g, a)
                } else {
                    moveDown(c, g, a)
                }
            },
            a.pause);
            if (a.mousePause) {
                c.bind("mouseenter",
                function() {
                    a.isPaused = true
                }).bind("mouseleave",
                function() {
                    a.isPaused = false
                })
            }
        })
    }
})(jQuery);