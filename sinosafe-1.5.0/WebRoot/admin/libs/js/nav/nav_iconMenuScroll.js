function backHome() {
    jQuery.jCookie("hiconIndex", "false");
    jQuery.jCookie("tabbarIndex", "false")
}
var listData;
var timer;
var itemContainerWidth = 0;
var currentIdx = 0;
var currentItemTotalWidth = 0;
function createIconMenu(f) {
    var h = $(".nav_icon_h");
    var e = new String(document.location);
    var a = $(".tab_bar_content");
    e = e.substr(0, 32);
    a.css({
        position: "relative",
        top: "0px",
        right: "0px"
    });
    $.each(f.list,
    function(q, t) {
        var r = $('<div class="nav_icon_h_item"><a><div class="nav_icon_h_item_img"></div><div class="nav_icon_h_item_text"></div></a></div>');
        var m = r.find("a");
        if (t.link) {
            m.attr("href", t.link)
        }
        if (t.target) {
            m.attr("target", t.target)
        }
        if (t.name) {
            r.find(".nav_icon_h_item_text").text(t.name)
        }
        if (t.icon) {
            var i = $("<img/>");
            i.attr("src", t.icon);
            r.find(".nav_icon_h_item_img").append(i)
        }
        h.append(r);
        if (t.id) {
            m.attr("id", t.id);
            if (t.clickHandler) {
                if (t.clickHandler == true || t.clickHandler == "true") {
                    m.click(function() {
                        changeLeftMenu(t.id, t.name)
                    })
                }
            }
        }
        if (t.children) {
            var j = -1;
            if (t.maxnum) {
                j = parseInt(t.maxnum)
            }
            var p = 2;
            var k = 0;
            var o = $('<ul style="display:none;" class="menuItemContainer"></ul>');
            a.append(o);
            var n = $('<div class="menuItemButtonLeft" style="display:none"></div>');
            var l = $('<div class="menuItemButtonRight" style="display:none"></div>');
            a.append(n);
            a.append(l);
            n.bind("mousedown",
            function() {
                timer = setInterval(function() {
                    if (p > 17) {
                        p = 18;
                        o.css("left", p);
                        clearInterval(timer);
                        return
                    }
                    p = p + 8;
                    o.css("left", p)
                },
                30)
            });
            n.bind("mouseup",
            function() {
                clearInterval(timer)
            });
            l.bind("mousedown",
            function() {
                var u = $(this).next("ul");
                timer = setInterval(function() {
                    if (p > 18) {
                        p = 17;
                        clearInterval(timer);
                        return
                    } else {
                        if (p < itemContainerWidth - k) {
                            p = itemContainerWidth - k;
                            clearInterval(timer);
                            return
                        }
                    }
                    p = p - 8;
                    o.css("left", p)
                },
                30)
            });
            l.bind("mouseup",
            function() {
                clearInterval(timer)
            });
            var s = $('<li class="popupMenu nav_more"><div class="popupMenu_link nav_more_link"></div><div class="popupMenu_con white_con nav_more_con"></div></li>');
            $.each(t.children,
            function(u, w) {
                var v = $('<li class="item"><a></a></li>');
                var x = v.find("a");
                if (w.name) {
                    x.text(w.name)
                }
                if (w.link) {
                    x.attr("href", w.link)
                }
                if (w.target) {
                    x.attr("target", w.target)
                }
                if (w.title) {
                    x.attr("title", w.title)
                }
                o.append(v);
                if (w.id) {
                    x.attr("id", w.id);
                    if (w.clickHandler) {
                        if (w.clickHandler == true || w.clickHandler == "true") {
                            v.click(function() {
                                changeLeftMenu(w.id, w.name)
                            })
                        }
                    }
                }
                k = k + 110
            });
            o.data("itemTotalWidth", k);
            o.append('<div class="clear"></div>');
            a.append(o)
        }
    });
    h.append('<div class="clear"></div>');
    var g = 0;
    var c = jQuery.jCookie("hiconIndex");
    if (c == "false") {} else {
        g = parseInt(c);
        if (isNaN(g)) {
            g = 0
        }
    }
    $(".tab_bar_content >ul").eq(g).show();
    $(".tab_bar_content .menuItemButtonLeft").eq(g).show();
    $(".tab_bar_content .menuItemButtonRight").eq(g).show();
    currentIdx = g;
    $(".nav_icon_h_item >a").each(function(k) {
        $(this).click(function() {
            $(".nav_icon_h_item >a").removeClass("current");
            $(this).addClass("current");
            $(".tab_bar_content >ul").hide();
            $(".tab_bar_content >ul").eq(k).show();
            $(".tab_bar_content .menuItemButtonLeft").hide();
            $(".tab_bar_content .menuItemButtonRight").hide();
            $(".tab_bar_content .menuItemButtonLeft").eq(k).show();
            $(".tab_bar_content .menuItemButtonRight").eq(k).show();
            currentIdx = k;
            resetItemHandler();
            jQuery.jCookie("hiconIndex", k.toString())
        });
        if (k == g) {
            if (f.type == "single") {
                var j = $(this).attr("id");
                listData = getListData(j, $(this).text())
            } else {
                $(this).addClass("current")
            }
        }
    });
    var b = -1;
    var d = jQuery.jCookie("tabbarIndex");
    if (d == "false") {} else {
        b = parseInt(d);
        if (isNaN(b)) {
            b = 0
        }
    }
    $(".tab_bar_content li a").each(function(k) {
        $(this).click(function() {
            $(".tab_bar_content li a").removeClass("current");
            $(this).addClass("current");
            if ($(this).attr("target") != null) {
                var i = "正在加载中...";
                progressFlag = 2;
                showSimpleProgress(i, 0, true, "#ffffff")
            }
            jQuery.jCookie("tabbarIndex", k.toString())
        });
        if (k == b) {
            $(this).addClass("current");
            var j = $(this).attr("id");
            try {
                listData = getListData(j, $(this).text())
            } catch(l) {}
        }
    });
    resetItemHandler()
}
function changeLeftMenu(a, c) {
    try {
        listData = getListData(a, c)
    } catch(b) {}
    if (listData != null) {
        document.getElementById("frmleft").contentWindow.initLeftMenu(listData)
    }
}
function mainResizeHandler() {
    resetItemHandler()
}
function resetItemHandler() {
    var a = document.documentElement.clientWidth;
    itemContainerWidth = a - 400;
    $(".tab_bar_content").width(itemContainerWidth);
    var c = $(".tab_bar_content").find(".menuItemContainer").eq(currentIdx);
    c.css("left", 18);
    var b = c.find("li").length;
    currentItemTotalWidth = $(".tab_bar_content").find(".menuItemContainer").eq(currentIdx).data("itemTotalWidth");
    if (currentItemTotalWidth > itemContainerWidth) {
        $(".tab_bar_content").find(".menuItemButtonLeft").eq(currentIdx).show();
        $(".tab_bar_content").find(".menuItemButtonRight").eq(currentIdx).show();
        $(".tab_bar_content").find(".menuItemContainer").eq(currentIdx).css("left", 18)
    } else {
        $(".tab_bar_content").find(".menuItemButtonLeft").eq(currentIdx).hide();
        $(".tab_bar_content").find(".menuItemButtonRight").eq(currentIdx).hide();
        $(".tab_bar_content").find(".menuItemContainer").eq(currentIdx).css("left", itemContainerWidth - currentItemTotalWidth + 10)
    }
};