var homeUrl = "open.jsp";
var treeData;
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
    initTree(0);
    showContent();
    triggerCustomHeightSet()
}
function initTree(a,httpIp) {
    treeData = getTreeData(a,httpIp);
    var b = new String(document.location);
    var c = treeData.treeNodes;
    b = b.substr(0, 32);
    $.fn.zTree.init($("#treeDemo"), setting, c);
    zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo")
}
function showContent() {
    var f = jQuery.jCookie("leftvtabIdx");
    if (f == false || f == "false") {
        initTree(0)
    } else {
        var b = parseInt(f);
        $("#vTab").verticalTabSetIdx(b);
        initTree(b)
    }
    var e = jQuery.jCookie("leftTreeNodeId");
    if (e == false || e == "false") {
        top.positionType = "simple";
        top.positionContent = "【当前位置：系统主页】";
        top.frmright.location = homeUrl
    } else {
        var d = zTree_Menu.getNodeByParam("id", e);
        zTree_Menu.selectNode(d);
        if (d.level === 0 || d.level === 1) {
            var c = $("#" + d.tId + "_a");
            c.click()
        }
        if (d.url) {
            top.positionType = "simple";
            if (d.getParentNode()) {
                top.positionContent = "【当前位置：" + d.getParentNode().name + ">>" + d.name + "】"
            }
            top.frmright.location = d.url
        }
    }
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
    if (c.url != null) {
        showProgressBar();
        if (c.getParentNode()) {
            top.positionContent = "【当前位置：" + c.getParentNode().name + ">>" + c.name + "】"
        } else {
            top.positionContent = "【当前位置：" + c.name + "】"
        }
        top.positionType = "simple"
    }
    jQuery.jCookie("leftTreeNodeId", c.id.toString());
    if (c.level === 0) {
        if (!c.open) {
            jQuery.jCookie("leftTreeNodeId", "false")
        }
    }
}
function showAll() {
    zTree_Menu.expandAll(true)
}
function hideAll() {
    zTree_Menu.expandAll(false);
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