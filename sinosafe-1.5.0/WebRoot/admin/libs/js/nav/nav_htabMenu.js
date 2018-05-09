var listData;
function createTabH(e) {
    var a = -1;
    var d = jQuery.jCookie("htabIndex");
    if (d == false || d == "false") {} else {
        a = parseInt(d);
        if (isNaN(a)) {
            a = 0
        }
    }
    var c = new String(document.location);
    var b;
    if (broswerFlag == "IE6") {
        b = $('<div class="htab_ie6"></div>')
    } else {
        b = $('<div class="htab"></div>')
    }
    c = c.substr(0, 32);
    $("#bs_bannerright").append(b);
    $.each(e,
    function(f, i) {
        var g = $('<a href="javascript:;"><b></b></a>');
        var h = g.find("b");
        if (i.name) {
            h.text(i.name)
        }
        if (i.id) {
            g.attr("id", i.id)
        }
        if (i.url) {
            g.attr("turl", i.url)
        }
        b.append(g)
    });
    b.find(">a").each(function(g) {
        $(this).click(function() {
            b.find(">a").removeClass("current");
            $(this).addClass("current");
            var h = $(this).attr("turl");
            changeLeftMenu(h);
            //jQuery.jCookie("htabIndex", g.toString())
        });
        if (g == a) {
            $(this).addClass("current");
            var f = $(this).attr("turl");
            listData = getListData(f);
        }
    })
}
function changeLeftMenu(a) {
    listData = getListData(a);
    if (listData != null) {
        document.getElementById("frmleft").contentWindow.initTreeMenu(listData)
    }
};