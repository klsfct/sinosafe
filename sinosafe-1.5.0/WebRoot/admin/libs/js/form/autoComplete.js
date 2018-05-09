var parentTopHeight; (function(a) {
    a.fn.autoCompleteRender = function() {
        var g = "";
        var f = "list";
        var w = {};
        var h = "q";
        var j = 1;
        var u = 0;
        var i = "120px";
        var z = "";
        var l = 0;
        var B = false;
        var C;
        var y = false;
        var q = a(this).attr("url");
        if (q) {
            g = q
        }
        var p = a(this).attr("dataRoot");
        if (p) {
            f = p
        }
        var d = a(this).attr("params");
        if (d) {
            try {
                w = JSON.parse(d)
            } catch(t) {
                w = w;
                alert("自动完成框参数格式有误！（提示：json数据的属性和名称必须以双引号包围）")
            }
        } else {
            w = w
        }
        var b = a(this).attr("matchName");
        if (b) {
            h = b
        }
        var r = a(this).attr("minChars");
        if (r) {
            j = Number(r)
        }
        var c = a(this).attr("limit");
        if (c) {
            u = Number(c)
        }
        var A = a(this).attr("boxWidth");
        if (A) {
            i = Number(A)
        }
        var m = a(this).attr("inputWidth");
        if (m) {
            z = Number(m)
        }
        var x = a(this).attr("colIdx");
        if (x) {
            l = Number(x)
        }
        var k = 0;
        var n = a(this).attr("leftDeff");
        if (n) {
            k = Number(n)
        }
        var o = 0;
        var s = a(this).attr("topDeff");
        if (s) {
            o = Number(s)
        }
        if (a(this).attr("colMulti") == "true" || a(this).attr("colMulti") == true) {
            B = true
        }
        if (a(this).attr("matchHint") == "true" || a(this).attr("matchHint") == true) {
            y = true
        }
        C = a(this).data("data");
        var v = a(this);
        if (g != "") {
            a(this).AutoComplete({
                url: g,
                dataRoot: f,
                params: w,
                matchName: h,
                matchHint: y,
                minChars: j,
                limit: u,
                boxWidth: i,
                inputWidth: z,
                topDeff: o,
                leftDeff: k,
                onItemSelected: function(e) {
                    if (B == true) {
                        var E = e.text();
                        var D = E.split(" ")[l];
                        v.val(D)
                    }
                }
            })
        } else {
            if (C) {
                a(this).AutoComplete({
                    data: C,
                    matchHint: true,
                    dataRoot: f,
                    params: w,
                    matchName: h,
                    minChars: j,
                    limit: u,
                    boxWidth: i,
                    inputWidth: z,
                    topDeff: o,
                    leftDeff: k,
                    onItemSelected: function(e) {
                        if (B == true) {
                            var E = e.text();
                            var D = E.split(" ")[l];
                            v.val(D)
                        }
                    }
                })
            }
        }
    };
    a.fn.AutoComplete = function(h) {
        inputs = this;
        var c = {
            ENTER: 13,
            LEFT: 37,
            UP: 38,
            RIGHT: 39,
            DOWN: 40,
            SPACE: 32,
            PAGEDOWN: 34
        };
        var g = {
            STARTS_WITH: "S",
            ENDS_WITH: "E",
            CONTAINS: "C"
        };
        var o = {
            backgroundColor: "#3369F9",
            color: "#FFF",
            autoDimentions: false,
            minChars: 1,
            immediateList: false,
            inputWidth: "",
            boxWidth: "120px",
            separator: "\n",
            delay: 400,
            slideDownTime: 0,
            slideUpTime: 0,
            inputClass: "",
            inputLoadingClass: "hintbox_loading",
            hintboxContainerClass: "hintbox_list_container",
            url: "",
            params: {},
            matchName: "q",
            extraParams: {},
            json: true,
            useCache: true,
            data: "",
            matchHint: false,
            matchRule: g.STARTS_WITH,
            sort: false,
            limit: 0,
            dataRoot: "list",
            topDeff: 0,
            leftDeff: 0,
            onBeforeListLoad: function() {},
            onListRetrieved: function() {},
            onListLoaded: function() {},
            onItemSelected: function() {}
        };
        if (h.matchRule != undefined) {
            h.matchRule = h.matchRule.toUpperCase();
            if (h.matchRule != g.STARTS_WITH && h.matchRule != g.ENDS_WITH && h.matchRule != g.CONTAINS) {
                h.matchRule = o.matchRule
            }
        }
        var h = jQuery.extend(o, h);
        var C = function(H, G) {
            var J;
            var I;
            var L;
            var K = new Array();
            if (H.length == 0) {
                return G.length
            }
            if (G.length == 0) {
                return H.length
            }
            for (J = 0; J <= H.length; J++) {
                K[J] = new Array();
                K[J][0] = J
            }
            for (I = 0; I <= G.length; I++) {
                K[0][I] = I
            }
            for (J = 1; J <= H.length; J++) {
                for (I = 1; I <= G.length; I++) {
                    L = (H.charAt(J - 1) == G.charAt(I - 1)) ? 0 : 1;
                    K[J][I] = Math.min(K[J - 1][I] + 1, K[J][I - 1] + 1, K[J - 1][I - 1] + L);
                    if (J > 1 && I > 1 && H.charAt(J - 1) == G.charAt(I - 2) && H.charAt(J - 2) == G.charAt(I - 1)) {
                        K[J][I] = Math.min(K[J][I], K[J - 2][I - 2] + L)
                    }
                }
            }
            return K[H.length][G.length]
        };
        var l = function(G) {
            jQuery(document).bind("keydown",
            function(H) {
                n(H, G)
            })
        };
        var d = function() {
            jQuery(document).unbind("keydown")
        };
        var B = function(G) {
            d();
            l(G)
        };
        var t = function(G) {
            return s(G).find("ul")
        };
        var s = function(G) {
            return G.next("." + h.hintboxContainerClass)
        };
        var F = function(G) {
            return s(G).get() != ""
        };
        var i;
        var v = function(G) {
            G.attr("autocomplete", "off");
            if (h.inputWidth != "") {
                G.width(h.inputWidth)
            }
            var I = "";
            var H = "";
            G.keyup(function(K) {
                if (K.keyCode != c.UP && K.keyCode != c.DOWN && K.keyCode != c.ENTER && K.keyCode != c.LEFT && K.keyCode != c.RIGHT && K.keyCode != c.PAGEDOWN) {
                    H = jQuery.trim(G.val());
                    if (H == "") {
                        s(G).slideUp(h.slideUpTime)
                    } else {
                        if (H != I) {
                            if (H.length >= h.minChars) {
                                setTimeout(function() {
                                    z(G, H)
                                },
                                h.delay)
                            } else {
                                s(G).slideUp(h.slideUpTime)
                            }
                        }
                    }
                    I = H
                }
                if (K.keyCode == c.ENTER) {
                    I = "";
                    H = "";
                    var J = s(G);
                    if (J.get() != "") {
                        j(G)
                    }
                }
                if (h.immediateList) {
                    if (K.keyCode == c.PAGEDOWN) {
                        H = jQuery.trim(G.val());
                        if (H == "") {
                            z(G, H)
                        }
                    }
                }
            })
        };
        var u = function(G) {
            if (F(G)) {
                var H = t(G);
                H.find("li").click(function() {
                    var I = jQuery(this);
                    G.val(I.text());
                    j(G);
                    h.onItemSelected(I)
                })
            }
        };
        var n = function(G, L) {
            if (F(L)) {
                var I = jQuery.data(L, "cssBackup");
                var J = t(L);
                var M = J.find("li:first");
                var O = J.find("li:last");
                var H = J.find(".selected");
                if (H.get() == "") {
                    if (G.keyCode == c.DOWN) {
                        H = M
                    } else {
                        if (G.keyCode == c.UP) {
                            H = O
                        }
                    }
                    b(H);
                    return
                }
                if (G.keyCode == c.DOWN) {
                    D(H, I);
                    var N = H.next("li");
                    if (N.get() == "") {
                        N = M;
                        L.focus();
                        J.find(".selected").removeClass("selected")
                    } else {
                        H = N;
                        b(H)
                    }
                } else {
                    if (G.keyCode == c.UP) {
                        D(H, I);
                        var K = H.prev("li");
                        if (K.get() == "") {
                            K = O;
                            L.focus();
                            J.find(".selected").removeClass("selected")
                        } else {
                            b(K);
                            H = K
                        }
                    } else {
                        if (G.keyCode == c.ENTER) {
                            if (H.get() != "") {
                                L.val(H.text())
                            }
                            j(L);
                            h.onItemSelected(H)
                        }
                    }
                }
            }
        };
        var m = function(H) {
            var G = {
                url: h.url,
                params: {},
                queryString: ""
            };
            G.params[h.matchName] = H;
            G.queryString = h.url + "?" + h.matchName + "=" + H;
            a.extend(true, G.params, h.params);
            a.extend(true, G.params, h.extraParams);
            return G
        };
        var E = function() {
            var G = jQuery(document).data("hintbox_cache");
            if (G == undefined) {
                var G = new Array();
                G.getItem = function(H) {
                    return this[H]
                };
                G.putItem = function(H, I) {
                    this[H] = I
                };
                G.hasItem = function(H) {
                    if (this[H] == undefined) {
                        return false
                    }
                    return true
                };
                jQuery(document).data("hintbox_cache", G)
            }
            return G
        };
        var r = function(I, K, J) {
            I.removeClass(h.inputLoadingClass);
            if (J.length > 0) {
                var G = (typeof J == "object" ? J: J.split(h.separator));
                h.onListRetrieved(G);
                if (K != "") {
                    if (h.useCache) {
                        var H = E();
                        if (!H.hasItem(K.queryString)) {
                            H.putItem(K.queryString, G)
                        }
                    }
                }
                e(I, G);
                var L = {
                    backgroundColor: t(I).find("li").css("background-color"),
                    color: t(I).find("li").css("color")
                };
                jQuery.data(I, "cssBackup", L);
                q(I);
                w(I);
                k(I);
                B(I);
                u(I);
                h.onListLoaded(t(I));
                i = I;
                a("body").bind("mousedown", f)
            }
        };
        function f(G) {
            if (a(G.target).attr("class") == "hintbox_list_container" || a(G.target).parent().attr("class") == "hintbox_list_container" || a(G.target).parent().parent().attr("class") == "hintbox_list_container") {} else {
                j(i)
            }
        }
        var z = function(I, K) {
            if (h.immediateList || K.length >= h.minChars) {
                var J = m(K);
                I.addClass(h.inputLoadingClass);
                h.onBeforeListLoad();
                var G = null;
                if (h.url) {
                    if (h.useCache) {
                        var H = E();
                        if (H.hasItem(J.queryString)) {
                            G = H.getItem(J.queryString)
                        }
                    }
                    if (G == null) {
                        if (!h.json) {
                            a.post(J.url, J.params,
                            function(L) {
                                r(I, J, jQuery.trim(L))
                            })
                        } else {
                            a.post(J.url, J.params,
                            function(N, O, L) {
                                var M = jQuery(N[h.dataRoot]);
                                r(I, J, M)
                            },
                            "json")
                        }
                    } else {
                        r(I, J, G)
                    }
                } else {
                    if (h.data) {
                        r(I, "", h.data[h.dataRoot])
                    }
                }
            }
        };
        function p() {
            var G = 0;
            a("#scrollContent").prevAll("div").each(function() {
                G = G + a(this).height();
                if (a(this).css("marginBottom") != "auto") {
                    G = G + parseInt(a(this).css("marginBottom"))
                }
                if (a(this).css("marginTop") != "auto") {
                    G = G + parseInt(a(this).css("marginTop"))
                }
            });
            return G
        }
        var x = function(H) {
            var N = H.offset();
            var G = s(H);
            var L = t(H);
            var K = 0;
            if (a("#scrollContent").attr("offsetLeft") != null) {
                K = parseInt(a("#scrollContent").attr("offsetLeft"))
            }
            var I = 0;
            if (H.parents("#scrollContent").length > 0) {
                I = a("#scrollContent").scrollTop() - p()
            }
            if (H.attr("position") == "mode2") {
                G.css({
                    display: "block",
                    position: "absolute",
                    overflowY: "auto",
                    overflowX: "hidden",
                    top: H.outerHeight() + h.topDeff,
                    left: 0 + h.leftDeff
                })
            } else {
                G.css({
                    display: "block",
                    position: "absolute",
                    overflowY: "auto",
                    overflowX: "hidden",
                    top: N.top + I + H.outerHeight() - parseInt(H.css("border-bottom-width")) + h.topDeff,
                    left: N.left - 4 + h.leftDeff
                })
            }
            var J = 200;
            if (parentTopHeight > 0) {
                var M = window.top.document.documentElement.clientHeight;
                J = M - parentTopHeight - parentBottomHeight - G.offset().top
            } else {
                J = window.document.documentElement.clientHeight - (G.offset().top - a(window).scrollTop())
            }
            G.height(J)
        };
        var A = function(J, G) {
            var M = jQuery.trim(J.val());
            var I = new Array();
            var L = 0;
            jQuery.each(G,
            function() {
                var O = C(M, jQuery.trim(this));
                if (I[L] == undefined) {
                    I[L] = new Array()
                }
                I[L] = new Array(O, jQuery.trim(this));
                L++
            });
            var N = function(Q, P) {
                if (parseInt(I[P]) < parseInt(I[Q])) {
                    var O = I[Q];
                    I[Q] = I[P];
                    I[P] = O;
                    if (Q > 0) {
                        N(Q - 1, Q)
                    }
                }
            };
            for (var K = 0; K < I.length - 1; K++) {
                N(K, K + 1)
            }
            G = new Array();
            for (var K = 0; K < I.length; K++) {
                var H = I[K];
                G.push(H[1])
            }
            return G
        };
        var y = function(I, H) {
            var J = jQuery.trim(I.val());
            var G = new Array();
            jQuery.each(H,
            function() {
                var K = jQuery.trim(this).toLowerCase();
                var L = J.toLowerCase();
                if (h.matchRule == g.STARTS_WITH) {
                    if (K.match("^" + L) == L) {
                        G.push(jQuery.trim(this))
                    }
                } else {
                    if (h.matchRule == g.ENDS_WITH) {
                        if (K.match(L + "$") == L) {
                            G.push(jQuery.trim(this))
                        }
                    } else {
                        if (K.match(L) == L) {
                            G.push(jQuery.trim(this))
                        }
                    }
                }
            });
            return G
        };
        var e = function(I, G) {
            depth++;
            var H = s(I);
            if (H.get() == "") {
                H = jQuery("<div></div>").addClass(h.hintboxContainerClass).css({
                    margin: 0,
                    padding: 0,
                    display: "none",
                    "z-index": depth
                })
            } else {
                H.empty()
            }
            var J = jQuery("<ul></ul>").css({
                cursor: "default"
            });
            if (h.matchHint) {
                G = y(I, G)
            }
            if (h.sort) {
                G = A(I, G)
            }
            if (h.limit > 0) {
                G = G.slice(0, h.limit)
            }
            jQuery.each(G,
            function() {
                J.append(jQuery("<li></li>").text(jQuery.trim(this)))
            });
            H.append(J);
            I.after(H);
            x(I);
            H.slideDown(h.slideDownTime)
        };
        var q = function(J) {
            var K = t(J);
            if (K.find("li").length > 0) {
                if (h.autoDimentions) {
                    var H = parseInt(J.css("border-left-width"));
                    var I = parseInt(J.css("border-right-width"));
                    var G = J.outerWidth();
                    h.boxWidth = (G - H - I) + "px";
                    K.find("li").css({
                        "line-height": J.outerHeight() + "px"
                    })
                }
                K.css({
                    width: h.boxWidth,
                    "overflow-x": "hidden"
                })
            } else {
                K.remove()
            }
        };
        var j = function(H) {
            d();
            var G = s(H);
            G.slideUp(h.slideUpTime,
            function() {
                jQuery(this).remove()
            });
            a("body").unbind("mousedown", f)
        };
        var k = function(G) {
            var H = t(G);
            var I = jQuery.data(G, "cssBackup");
            H.find("li").hover(function() {
                D(H.find(".selected"), I);
                b(jQuery(this))
            },
            function() {
                D(jQuery(this), I)
            })
        };
        var b = function(G) {
            G.css({
                "background-color": h.backgroundColor,
                color: h.color
            });
            G.addClass("selected")
        };
        var D = function(G, H) {
            G.css({
                "background-color": H.backgroundColor,
                color: H.color
            });
            G.removeClass("selected")
        };
        var w = function(G) {
            if (!jQuery.support.boxModel) {
                t(G).css({
                    width: (parseInt(h.boxWidth) + parseInt(G.css("border-left-width")) * 2) + "px"
                }).find("li").css({
                    width: (parseInt(h.boxWidth) + parseInt(G.css("border-left-width")) * 2) + "px"
                })
            }
        };
        jQuery(inputs).each(function() {
            var G = jQuery(this);
            if (!G.hasClass(h.inputClass)) {
                G.addClass(h.inputClass)
            }
            v(G);
            G.click(function() {
                B(G)
            });
            G.focus(function() {
                B(G)
            });
            G.blur(function() {
                B(G)
            })
        });
        return inputs
    }
})(jQuery);