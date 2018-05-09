jQuery.fn.extend({
    detailTableRender: function() {
        $(this).find("tr").filter(":has(table)").hide();
        var b = false;
        var c;
        var a;
        $(this).find(".img_remove2").attr("keepDefaultStyle", "true");
        $(this).find(".img_add2").attr("keepDefaultStyle", "true");
        if ($(this).attr("ajaxMode") == "true") {
            b = true
        }
        if ($(this).attr("trClick") == "true") {
            $(this).find("tr").eq(0).nextAll().not(":has(table)").each(function() {
                $(this).addClass("hand");
                $(this).hover(function() {
                    $(this).addClass("highlight")
                },
                function() {
                    $(this).removeClass("highlight")
                });
                $(this).click(function() {
                    if ($(this).next().css("display") == "none") {
                        if ($(this).parents("table").attr("ohterClose") != "false") {
                            $(this).parents("table").find(".img_remove2").attr("title", "点击展开");
                            $(this).parents("table").find(".img_remove2").addClass("img_add2");
                            $(this).parents("table").find(".img_remove2").removeClass("img_remove2");
                            $(this).next().nextAll().filter(":has(table)").hide();
                            $(this).next().prevAll().filter(":has(table)").hide()
                        }
                        if (b == true) {
                            a = $(this).find(".img_add2");
                            a.each(function() {
                                $(this).removeClass("img_add2");
                                $(this).addClass("img_loading")
                            });
                            c = a.attr("url");
                            window.setTimeout(function() {
                                cusTreeTableLoadLater(a, c)
                            },
                            200)
                        } else {
                            $(this).next().show();
                            $(this).find(".img_add2").each(function() {
                                $(this).attr("title", "点击收缩");
                                $(this).removeClass("img_add2");
                                $(this).addClass("img_remove2")
                            })
                        }
                    } else {
                        $(this).next().hide();
                        $(this).find(".img_remove2").each(function() {
                            $(this).removeClass("img_remove2");
                            $(this).addClass("img_add2");
                            $(this).attr("title", "点击展开")
                        })
                    }
                })
            })
        } else {
            $(this).find(".img_add2").click(function() {
                c = $(this).attr("url");
                if ($(this).parents("tr").next().css("display") == "none") {
                    if ($(this).parents("table").attr("ohterClose") != "false") {
                        $(this).parents("table").find(".img_remove2").attr("title", "点击展开");
                        $(this).parents("table").find(".img_remove2").addClass("img_add2");
                        $(this).parents("table").find(".img_remove2").removeClass("img_remove2");
                        $(this).parents("tr").next().nextAll().filter(":has(table)").hide();
                        $(this).parents("tr").next().prevAll().filter(":has(table)").hide()
                    }
                    $(this).removeClass("img_add2");
                    if (b == true) {
                        $(this).addClass("img_loading");
                        a = $(this);
                        window.setTimeout(function() {
                            cusTreeTableLoadLater(a, c)
                        },
                        200)
                    } else {
                        $(this).attr("title", "点击收缩");
                        $(this).addClass("img_remove2");
                        $(this).parents("tr").next().show()
                    }
                } else {
                    $(this).parents("tr").next().hide();
                    $(this).removeClass("img_remove2");
                    $(this).addClass("img_add2");
                    $(this).attr("title", "点击展开")
                }
            })
        }
    }
});