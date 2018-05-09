/*
 * JQuery zTree exHideNodes 3.5.13
 * http://zTree.me/
 *
 * Copyright (c) 2010 Hunter.z
 *
 * Licensed same as jquery - MIT License
 * http://www.opensource.org/licenses/mit-license.php
 *
 * email: hunter.z@263.net
 * Date: 2013-06-02
 */
(function(g) {
    g.extend(!0, g.fn.zTree._z, {
        view: {
            clearOldFirstNode: function(c, a) {
                for (var b = a.getNextNode(); b;) {
                    if (b.isFirstNode) {
                        b.isFirstNode = !1;
                        f.setNodeLineIcos(c, b);
                        break
                    }
                    if (b.isLastNode) break;
                    b = b.getNextNode()
                }
            },
            clearOldLastNode: function(c, a) {
                for (var b = a.getPreNode(); b;) {
                    if (b.isLastNode) {
                        b.isLastNode = !1;
                        f.setNodeLineIcos(c, b);
                        break
                    }
                    if (b.isFirstNode) break;
                    b = b.getPreNode()
                }
            },
            makeDOMNodeMainBefore: function(c, a, b) {
                c.push("<li ", b.isHidden ? "style='display:none;' ": "", "id='", b.tId, "' class='", l.className.LEVEL, b.level, "' tabindex='0' hidefocus='true' treenode>")
            },
            showNode: function(c, a) {
                a.isHidden = !1;
                e.initShowForExCheck(c, a);
                j(a, c).show()
            },
            showNodes: function(c, a, b) {
                if (a && a.length != 0) {
                    var d = {},
                    i, k;
                    for (i = 0, k = a.length; i < k; i++) {
                        var h = a[i];
                        if (!d[h.parentTId]) {
                            var g = h.getParentNode();
                            d[h.parentTId] = g === null ? e.getRoot(c) : h.getParentNode()
                        }
                        f.showNode(c, h, b)
                    }
                    for (var j in d) a = d[j][c.data.key.children],
                    f.setFirstNodeForShow(c, a),
                    f.setLastNodeForShow(c, a)
                }
            },
            hideNode: function(c, a) {
                a.isHidden = !0;
                a.isFirstNode = !1;
                a.isLastNode = !1;
                e.initHideForExCheck(c, a);
                f.cancelPreSelectedNode(c, a);
                j(a, c).hide()
            },
            hideNodes: function(c, a, b) {
                if (a && a.length != 0) {
                    var d = {},
                    i, k;
                    for (i = 0, k = a.length; i < k; i++) {
                        var h = a[i];
                        if ((h.isFirstNode || h.isLastNode) && !d[h.parentTId]) {
                            var g = h.getParentNode();
                            d[h.parentTId] = g === null ? e.getRoot(c) : h.getParentNode()
                        }
                        f.hideNode(c, h, b)
                    }
                    for (var j in d) a = d[j][c.data.key.children],
                    f.setFirstNodeForHide(c, a),
                    f.setLastNodeForHide(c, a)
                }
            },
            setFirstNode: function(c, a) {
                var b = c.data.key.children,
                d = a[b].length;
                d > 0 && !a[b][0].isHidden ? a[b][0].isFirstNode = !0 : d > 0 && f.setFirstNodeForHide(c, a[b])
            },
            setLastNode: function(c, a) {
                var b = c.data.key.children,
                d = a[b].length;
                d > 0 && !a[b][0].isHidden ? a[b][d - 1].isLastNode = !0 : d > 0 && f.setLastNodeForHide(c, a[b])
            },
            setFirstNodeForHide: function(c, a) {
                var b, d, i;
                for (d = 0, i = a.length; d < i; d++) {
                    b = a[d];
                    if (b.isFirstNode) break;
                    if (!b.isHidden && !b.isFirstNode) {
                        b.isFirstNode = !0;
                        f.setNodeLineIcos(c, b);
                        break
                    } else b = null
                }
                return b
            },
            setFirstNodeForShow: function(c, a) {
                var b, d, i, e, h;
                for (d = 0, i = a.length; d < i; d++) if (b = a[d], !e && !b.isHidden && b.isFirstNode) {
                    e = b;
                    break
                } else if (!e && !b.isHidden && !b.isFirstNode) b.isFirstNode = !0,
                e = b,
                f.setNodeLineIcos(c, b);
                else if (e && b.isFirstNode) {
                    b.isFirstNode = !1;
                    h = b;
                    f.setNodeLineIcos(c, b);
                    break
                }
                return {
                    "new": e,
                    old: h
                }
            },
            setLastNodeForHide: function(c, a) {
                var b, d;
                for (d = a.length - 1; d >= 0; d--) {
                    b = a[d];
                    if (b.isLastNode) break;
                    if (!b.isHidden && !b.isLastNode) {
                        b.isLastNode = !0;
                        f.setNodeLineIcos(c, b);
                        break
                    } else b = null
                }
                return b
            },
            setLastNodeForShow: function(c, a) {
                var b, d, e, g;
                for (d = a.length - 1; d >= 0; d--) if (b = a[d], !e && !b.isHidden && b.isLastNode) {
                    e = b;
                    break
                } else if (!e && !b.isHidden && !b.isLastNode) b.isLastNode = !0,
                e = b,
                f.setNodeLineIcos(c, b);
                else if (e && b.isLastNode) {
                    b.isLastNode = !1;
                    g = b;
                    f.setNodeLineIcos(c, b);
                    break
                }
                return {
                    "new": e,
                    old: g
                }
            }
        },
        data: {
            initHideForExCheck: function(c, a) {
                if (a.isHidden && c.check && c.check.enable) {
                    if (typeof a._nocheck == "undefined") a._nocheck = !!a.nocheck,
                    a.nocheck = !0;
                    a.check_Child_State = -1;
                    f.repairParentChkClassWithSelf && f.repairParentChkClassWithSelf(c, a)
                }
            },
            initShowForExCheck: function(c, a) {
                if (!a.isHidden && c.check && c.check.enable) {
                    if (typeof a._nocheck != "undefined") a.nocheck = a._nocheck,
                    delete a._nocheck;
                    if (f.setChkClass) {
                        var b = j(a, l.id.CHECK, c);
                        f.setChkClass(c, b, a)
                    }
                    f.repairParentChkClassWithSelf && f.repairParentChkClassWithSelf(c, a)
                }
            }
        }
    });
    var g = g.fn.zTree,
    m = g._z.tools,
    l = g.consts,
    f = g._z.view,
    e = g._z.data,
    j = m.$;
    e.addInitNode(function(c, a, b) {
        if (typeof b.isHidden == "string") b.isHidden = m.eqs(b.isHidden, "true");
        b.isHidden = !!b.isHidden;
        e.initHideForExCheck(c, b)
    });
    e.addBeforeA(function() {});
    e.addZTreeTools(function(c, a) {
        a.showNodes = function(a, b) {
            f.showNodes(c, a, b)
        };
        a.showNode = function(a, b) {
            a && f.showNodes(c, [a], b)
        };
        a.hideNodes = function(a, b) {
            f.hideNodes(c, a, b)
        };
        a.hideNode = function(a, b) {
            a && f.hideNodes(c, [a], b)
        };
        var b = a.checkNode;
        if (b) a.checkNode = function(c, e, f, h) { (!c || !c.isHidden) && b.apply(a, arguments)
        }
    });
    var n = e.initNode;
    e.tmpHideParent = -1;
    e.initNode = function(c, a, b, d, i, g, h) {
        if (e.tmpHideParent !== d) {
            e.tmpHideParent = d == null ? -1 : d;
            var j = (d ? d: e.getRoot(c))[c.data.key.children];
            e.tmpHideFirstNode = f.setFirstNodeForHide(c, j);
            e.tmpHideLastNode = f.setLastNodeForHide(c, j);
            f.setNodeLineIcos(c, e.tmpHideFirstNode);
            f.setNodeLineIcos(c, e.tmpHideLastNode)
        }
        i = e.tmpHideFirstNode === b;
        g = e.tmpHideLastNode === b;
        n && n.apply(e, arguments);
        g && f.clearOldLastNode(c, b)
    };
    var o = e.makeChkFlag;
    if (o) e.makeChkFlag = function(c, a) { (!a || !a.isHidden) && o.apply(e, arguments)
    };
    var p = e.getTreeCheckedNodes;
    if (p) e.getTreeCheckedNodes = function(c, a, b, d) {
        if (a && a.length > 0) {
            var f = a[0].getParentNode();
            if (f && f.isHidden) return []
        }
        return p.apply(e, arguments)
    };
    var q = e.getTreeChangeCheckedNodes;
    if (q) e.getTreeChangeCheckedNodes = function(c, a, b) {
        if (a && a.length > 0) {
            var d = a[0].getParentNode();
            if (d && d.isHidden) return []
        }
        return q.apply(e, arguments)
    };
    var r = f.expandCollapseSonNode;
    if (r) f.expandCollapseSonNode = function(c, a, b, d, e) { (!a || !a.isHidden) && r.apply(f, arguments)
    };
    var s = f.setSonNodeCheckBox;
    if (s) f.setSonNodeCheckBox = function(c, a, b, d) { (!a || !a.isHidden) && s.apply(f, arguments)
    };
    var t = f.repairParentChkClassWithSelf;
    if (t) f.repairParentChkClassWithSelf = function(c, a) { (!a || !a.isHidden) && t.apply(f, arguments)
    }
})(jQuery);