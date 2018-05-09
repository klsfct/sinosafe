(function(a) {
    function b() {
        return false
    }
    a.fn.contextmenu = function(l) {
        l = a.extend({
            alias: "cmroot",
            width: 150
        },
        l);
        var q = null,
        z = null,
        u = {},
        y = {},
        i = {},
        p = [],
        t = "<div class='b-m-$[type]' unselectable=on><nobr unselectable=on><img src='$[icon]' align='absmiddle'/><span unselectable=on>$[text]</span></nobr></div>";
        var k = a("<div/>").addClass("b-m-mpanel").attr("unselectable", "on").css("display", "none");
        var j = a("<div/>").addClass("b-m-item").attr("unselectable", "on");
        var h = a("<div/>").addClass("b-m-split");
        var s = function(A) {
            u[A.alias] = this;
            this.gidx = A.alias;
            this.id = A.alias;
            if (A.disable) {
                this.disable = A.disable;
                this.className = "b-m-idisable"
            }
            a(this).width(A.width).click(b).mousedown(b).appendTo(a("body"));
            A = null;
            return this
        };
        var g = function(B) {
            var A = this;
            A.title = B.text;
            A.idx = B.alias;
            A.gidx = B.gidx;
            A.data = B;
            A.innerHTML = t.replace(/\$\[([^\]]+)\]/g,
            function() {
                return B[arguments[1]]
            });
            if (B.disable) {
                A.disable = B.disable;
                A.className = "b-m-idisable"
            }
            B.items && (A.group = true);
            B.action && (i[B.alias] = B.action);
            y[B.alias] = A;
            A = B = null;
            return this
        };
        var m = function(D, A) {
            var C = null;
            for (var B = 0; B < A.length; B++) {
                if (A[B].type == "splitLine") {
                    C = h.clone()[0]
                } else {
                    A[B].gidx = D;
                    if (A[B].type == "group") {
                        s.apply(k.clone()[0], [A[B]]);
                        arguments.callee(A[B].alias, A[B].items);
                        A[B].type = "arrow";
                        C = g.apply(j.clone()[0], [A[B]])
                    } else {
                        A[B].type = "ibody";
                        C = g.apply(j.clone()[0], [A[B]]);
                        a(C).click(function(E) {
                            if (!this.disable) {
                                if (a.isFunction(i[this.idx])) {
                                    i[this.idx].call(this, z)
                                }
                                c()
                            }
                            return false
                        })
                    }
                    a(C).bind("contextmenu", b).hover(o, v)
                }
                u[D].appendChild(C);
                C = A[B] = A[B].items = null
            }
            D = A = null
        };
        var o = function(B) {
            if (this.disable) {
                return false
            }
            c.call(u[this.gidx]);
            if (this.group) {
                var C = a(this).offset();
                var A = a(this).outerWidth();
                e.apply(u[this.idx], [C, A])
            }
            this.className = "b-m-ifocus";
            return false
        };
        var v = function(A) {
            if (this.disable) {
                return false
            }
            if (!this.group) {
                this.className = "b-m-item"
            }
            return false
        };
        var e = function(F, C) {
            var E = a("body").width();
            var B = document.documentElement.clientHeight;
            var D = a(this).outerWidth();
            var A = a(this).outerHeight();
            F.left = (F.left + C + D > E) ? (F.left - D < 0 ? 0 : F.left - D) : F.left + C;
            F.top = (F.top + A > B) ? (F.top - A + (C > 0 ? 25 : 0) < 0 ? 0 : F.top - A + (C > 0 ? 25 : 0)) : F.top;
            a(this).css(F).show();
            p.push(this.gidx)
        };
        var c = function() {
            var B = null;
            for (var A = p.length - 1; A >= 0; A--) {
                if (p[A] == this.gidx) {
                    break
                }
                B = p.pop();
                u[B].style.display = "none";
                y[B] && (y[B].className = "b-m-item")
            }
        };
        function f(B) {
            if (q && q == B.name) {
                return false
            }
            for (var A in y) {
                d(A, !B.disable)
            }
            for (var A = 0; A < B.items.length; A++) {
                d(B.items[A], B.disable)
            }
            q = B.name
        }
        function d(A, B) {
            var C = y[A];
            C.className = (C.disable = C.lastChild.disabled = B) ? "b-m-idisable": "b-m-item"
        }
        function n(A, B) {
            z = B;
            e.call(u.cmroot, {
                left: A.pageX,
                top: A.pageY
            },
            0);
            a(document).one("mousedown", c)
        }
        var w = a("#" + l.alias);
        var r = null;
        if (w.length == 0) {
            r = s.apply(k.clone()[0], [l]);
            r.applyrule = f;
            r.showMenu = n;
            m(l.alias, l.items)
        } else {
            r = w[0]
        }
        var x = a(this).each(function() {
            return a(this).bind("contextmenu",
            function(B) {
                var A = (l.onContextMenu && a.isFunction(l.onContextMenu)) ? l.onContextMenu.call(this, B) : true;
                if (A) {
                    if (l.onShow && a.isFunction(l.onShow)) {
                        l.onShow.call(this, r)
                    }
                    r.showMenu(B, this)
                }
                return false
            })
        });
        if (l.rule) {
            f(l.rule)
        }
        k = j = h = t = s = g = null;
        m = o = v = null;
        return x
    }
})(jQuery);