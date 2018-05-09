(function(b) {
    function c(e) {
        if (window.console && window.console.log) {
            window.console.log(e)
        }
    }
    function d() {
        var e = this;
        d.inst = e;
        setInterval(function() {
            e.update(e)
        },
        200)
    }
    function a(f) {
        var e = f.match(/[\/\\]*([^\/\\]+)\.(png|jpg|jpeg|gif)$/i);
        return (e && e[1]) || ""
    }
    d.prototype = {
        _skinList: {},
        _ctrls: {},
        _wlQueue: [],
        addControl: function(h, e, j) {
            if (h != null && !this.hasControl(h.id)) {
                var f = e.split(";");
                var k = this;
                this._ctrls[h.id] = {
                    skinList: {},
                    skinSyn: f.length,
                    skinUrlList: [],
                    items: []
                };
                var g = function(l) {
                    return function(m) {
                        _ctrl = k.getControl(l.id);
                        _ctrl.skinList[a(m.src.toLowerCase())] = m;
                        if ((--_ctrl.skinSyn) == 0) {
                            j(l, _ctrl.skinList[_ctrl.skinUrlList[0]])
                        }
                    }
                };
                for (idx in f) {
                    url = b.trim(f[idx].toLowerCase());
                    this._ctrls[h.id].skinUrlList.push(a(url));
                    if (url !== "") {
                        this.loadSkin(h, url, g(h))
                    }
                }
            }
        },
        getControl: function(e) {
            return this._ctrls[e]
        },
        hasControl: function(e) {
            return (this._ctrls[e] != undefined)
        },
        addItem: function(e, g, f) {
            if (this.hasControl(e)) {
                this._ctrls[e].items.push({
                    ref: g,
                    onProgress: f
                })
            }
        },
        progress: function(e, f) {
            if (this.hasControl(e)) {
                var f = isFinite(f) ? Math.max(Math.min(f, 100), 0) : 0;
                for (i = 0; i < this._ctrls[e].items.length; ++i) {
                    this._ctrls[e].items[i].onProgress(this._ctrls[e].items[i].ref, f)
                }
            }
        },
        update: function(h) {
            var f = [];
            for (var e = 0; e < h._wlQueue.length; ++e) {
                var g = h._skinList[h._wlQueue[e].url];
                if (g && g.state == 1) {
                    f.push(e);
                    h._wlQueue[e].cb(g.img)
                }
            }
            if (f.length > 0) {
                this._wlQueue = b.grep(this._wlQueue,
                function(k, j) {
                    return b.inArray(j, f) == -1
                })
            }
        },
        loadSkin: function(h, f, j) {
            f = f.toLowerCase();
            if (this._skinList[f] == undefined) {
                var e = document.createElement("img");
                var g = b(e);
                g.css({
                    visibility: "hidden",
                    position: "absolute"
                });
                this._skinList[f] = {
                    img: e,
                    state: 0
                };
                g.load((function(k) {
                    return function() {
                        k.state = 1;
                        j(k.img)
                    }
                } (this._skinList[f])));
                g.error(function(k) {
                    c("unable to load skin '" + this.src + "'")
                });
                e.src = f;
                b(h).before(g)
            } else {
                this._wlQueue.push({
                    url: f,
                    cb: j
                })
            }
        }
    };
    b.fn.progressCtrl = function(j, f) {
        var k = (d.inst != undefined) ? d.inst: (new d());
        var n = function() {
            return (typeof(j) == "string") ? {
                setter: j,
                args: f
            }: j || {}
        };
        var o = (typeof(j) == "number") ? b.extend({
            pos: j
        },
        f || {}) : n();
        var m = b.extend({
            pos: 0
        },
        b.fn.progressCtrl.defaults, o);
        function h(q, t) {
            if (k.hasControl(q.id)) {
                var r = m.capWidth || (t.width / 4);
                var p = Math.max(m.width, (r * 2) + 2);
                var A = m.height || (t.height / (m.useCaps ? 3 : 2));
                var w = m.useCaps ? p - (r * 2) : p;
                var v = b("<div />");
                v.css({
                    width: p + "px",
                    height: A + "px",
                    padding: "0"
                });
                if (m.title === "") {
                    k.addItem(q.id, v,
                    function(B, C) {
                        B.attr("title", C + "%")
                    })
                } else {
                    v.attr("title", m.title)
                }
                var y = b("#" + q.id + "-label");
                if (m.label && y.is("span")) {
                    k.addItem(q.id, y,
                    function(B, C) {
                        B.text(C + "%")
                    })
                }
                v.addClass(m.containerClass);
                function x(B) {
                    if (m.useCaps == false || r == 0) {
                        return
                    }
                    posy = -A * 2;
                    var D = b("<div />");
                    D.attr("id", q.id + "-cap-" + B);
                    D.css({
                        width: r + "px",
                        height: A + "px",
                        padding: "0",
                        margin: "0",
                        "float": "left"
                    });
                    D.css("background-image", "url(" + t.src + ")");
                    D.css("overflow", "hidden");
                    var C = (function(F, E, G) {
                        return function(H, J) {
                            var I = (F == "left") ? ((J == 0) ? 0 : 1) : ((J == 100) ? 3 : 2);
                            H.css("background-position", ( - I * E) + "px " + G + "px")
                        }
                    })(B, r, posy);
                    k.addItem(q.id, D, C);
                    v.append(D)
                }
                x("left");
                var s = b("<div />");
                s.attr("id", q.id + "-track");
                s.css({
                    width: w + "px",
                    height: A + "px",
                    padding: "0",
                    margin: "0",
                    "float": "left"
                });
                s.css("overflow", "hidden");
                s.css("background", "transparent url(" + t.src + ") repeat-x left " + ( - A).toString() + "px");
                v.append(s);
                var u = b("<div />");
                u.attr("id", q.id + "-ctrl");
                u.css({
                    width: w + "px",
                    height: A + "px",
                    padding: "0",
                    margin: "0"
                });
                u.css("background", "transparent url(" + t.src + ") repeat-x left top");
                var z = (function(C, B) {
                    return function(D, F) {
                        var E = Math.ceil(C * (F / 100));
                        D.css("margin-left", E + "px")
                    }
                })(w, null);
                k.addItem(q.id, u, z);
                s.append(u);
                x("right");
                k.progress(q.id, m.pos);
                b(q).append(v)
            }
        }
        function g(r, p) {
            var s = k.getControl(r.id);
            p = (typeof(p) != "number") ? 0 : p;
            if (s && p >= 0 && p < s.skinUrlList.length) {
                function q(u) {
                    var v = b("#" + (r.id + "-" + u));
                    var t = s.skinList[s.skinUrlList[p]];
                    if (v.is("div")) {
                        v.css("background-image", "url(" + t.src + ")")
                    }
                }
                q("cap-left");
                q("track");
                q("ctrl");
                q("cap-right")
            }
        }
        function e(p, q) {
            k.progress(p.id, q)
        }
        var l = {
            skinIdx: g,
            progress: e
        };
        return this.each(function() {
            var p = this;
            if (!k.hasControl(this.id)) {
                b(p).empty();
                k.addControl(p, m.skin, h)
            } else {
                if (typeof(m.setter) == "string" && l[m.setter] != undefined) {
                    l[m.setter](p, m.args || [])
                } else {
                    k.progress(p.id, m.pos)
                }
            }
        })
    };
    b.fn.progressCtrl.defaults = {
        skin: "",
        width: 100,
        height: 0,
        useCaps: true,
        capWidth: 0,
        title: "",
        containerClass: "pbContainer",
        label: true
    }
})(jQuery);