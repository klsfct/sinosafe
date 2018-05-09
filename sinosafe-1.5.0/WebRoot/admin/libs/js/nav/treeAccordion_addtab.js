var zTree_Menu = null;
var setting = {
    view: {
        fontCss: getFontCss,
        showLine: false,
        selectedMulti: false,
        dblClickExpand: false
    },
    callback: {
        beforeClick: beforeClick,
        onClick: onClick
    }
};
function initComplete() {
    var a = new String(document.location);
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
    a = a.substr(0, 32);
    $("#searchKey").keydown(function(b) {
        if (b.keyCode == 13) {
            findNode()
        }
    })
}
function beforeClick(e, d) {
    if (d.level === 0) {
        $("#" + e).find("a").each(function() {
            if ($(this).hasClass("curLevel0")) {
                $(this).removeClass("curLevel0")
            }
        });
        var c = $("#" + d.tId + "_a");
        c.addClass("curLevel0");
        if (d.open) {
            zTree_Menu.expandNode(d, false);
            var c = $("#" + d.tId + "_a");
            c.removeClass("curLevel0");
            c.removeClass("curSelectedNode")
        } else {
            zTree_Menu.expandAll(false);
            zTree_Menu.expandNode(d)
        }
    } else {
        if (d.level === 1) {
            $("#" + e).find("a").each(function() {
                if ($(this).hasClass("curLevel1")) {
                    $(this).removeClass("curLevel1")
                }
            });
            var c = $("#" + d.tId + "_a");
            c.addClass("curLevel1");
            if (d.open) {
                zTree_Menu.expandNode(d, false);
                var c = $("#" + d.tId + "_a");
                c.removeClass("curLevel1");
                c.removeClass("curSelectedNode")
            } else {
                zTree_Menu.expandNode(d);
                var b = $("#" + d.getParentNode().tId + "_a");
                b.addClass("curLevel0")
            }
        } else {
            zTree_Menu.expandNode(d)
        }
    }
}
function onClick(f, d, c) {
    if (c.level === 0) {
        if (!c.open) {
            var b = $("#" + c.tId + "_a");
            b.removeClass("curSelectedNode")
        }
    }
    if (c.tabUrl != null) {
        showProgressBar();
        top.frmright.tabAddHandler(c.id, c.name, c.tabUrl)
    }
}
function showAll() {
    zTree_Menu.expandAll(true)
}
function hideAll() {
    zTree_Menu.expandAll(false);
    $("#treeDemo").find(">li").find("a").removeClass("curLevel0");
    jQuery.jCookie("leftTreeNodeId", "false")
}
function findNode() {
    var a = $.trim($("#searchKey").val());
    if (a != "") {
        getNodesByParamFuzzy("name", a)
    }
}
function getNodesByParamFuzzy(d, e, a) {
    var b = zTree_Menu.getNodesByParamFuzzy(d, e, a);
    highlightNodes(zTree_Menu, zTree_Menu.highlightNodeList, false);
    highlightNodes(zTree_Menu, b, true);
    zTree_Menu.highlightNodeList = b;
    if (null != b && b.length > 0) {
        zTree_Menu.expandAll(false);
        var c = b[0].getParentNode();
        zTree_Menu.expandNode(c, true);
        zTree_Menu.expandNode(b[0], true)
    }
}
function getNodesByParam(d, e, a) {
    var b = zTree_Menu.getNodesByParam(d, e, a);
    highlightNodes(zTree_Menu, zTree_Menu.highlightNodeList, false);
    highlightNodes(zTree_Menu, b, true);
    zTree_Menu.highlightNodeList = b;
    if (null != b && b.length > 0) {
        var c = b[0].getParentNode();
        if (!c.open) {
            zTree_Menu.expandAll(false);
            zTree_Menu.expandNode(c, true)
        }
    }
}
function highlightNodes(d, c, b) {
    if (null == c) {
        return
    }
    for (var e = 0,
    a = c.length; e < a; e++) {
        c[e].highlight = b;
        d.updateNode(c[e])
    }
}
function getFontCss(b, a) {
    return ( !! a.highlight) ? {
        color: "#A60000",
        "font-weight": "bold"
    }: {
        color: "#333",
        "font-weight": "normal"
    }
}
function homeHandler() {
    zTree_Menu.expandAll(false);
    top.positionType = "none";
    jQuery.jCookie("leftTreeNodeId", "false")
};