$(function() {
    $("form[class=stepForm]").each(function() {
        if ($(this).attr("stepFormTitle") == "true") {
            var c = $("<div class='titleMain'><ul></ul><div class='clear'></div></div>");
            $(this).prepend(c);
            var d = $("form[class=stepForm] div[class=stepForm]");
            var f = $("<li class='stepFormTitle'><div class='left'></div><div class='center'></div><div class='right'></div><div class='clear'></div></li>");
            f.find(".center").text(d.eq(0).attr("stepFormTitle"));
            c.append(f);
            var j = d.length - 1;
            for (var e = 0; e < j; e++) {
                var h = $("<li class='stepFormTitle'><div class='left'></div><div class='center'></div><div class='right'></div><div class='clear'></div></li>");
                h.find(".center").text(d.eq(e + 1).attr("stepFormTitle"));
                c.append(h)
            }
            var g = $("<div class='clear'></div>");
            c.append(g);
            c.after($("<br/>"))
        }
    });
    var a;
    if ($("form[class=stepForm]").eq(0).attr("currentStepNum") != null) {
        a = Number($("form[class=stepForm]").eq(0).attr("currentStepNum"))
    } else {
        a = 0
    }
    $("form[class=stepForm] div[class=stepForm]").not(":eq(" + a + ")").hide();
    try {
        $("form[class=stepForm] div[class=titleMain] li").eq(a)[0].className = "stepFormTitleCur"
    } catch(b) {}
    $("input:button").each(function() {
        var c = $(this).attr("selfTarget");
        if ($(this).attr("nextTarget") != null) {
            var d = $(this).attr("nextTarget");
            $(this).click(function() {
                var f = true;
                try {
                    f = $("#" + c).validationEngine({
                        returnIsValid: true
                    })
                } catch(g) {}
                if (f == true) {
                    a++;
                    $("#" + c).hide();
                    $("#" + d).show(500);
                    $("form[class=stepForm] div[class=titleMain] li").each(function() {
                        $(this)[0].className = "stepFormTitle"
                    });
                    try {
                        $("form[class=stepForm] div[class=titleMain] li").eq(a)[0].className = "stepFormTitleCur"
                    } catch(g) {}
                }
            })
        } else {
            if ($(this).attr("prevTarget") != null) {
                var e = $(this).attr("prevTarget");
                $(this).click(function() {
                    a--;
                    $("#" + c).hide();
                    $("#" + e).fadeIn(500);
                    $("form[class=stepForm] div[class=titleMain] li").each(function() {
                        $(this)[0].className = "stepFormTitle"
                    });
                    try {
                        $("form[class=stepForm] div[class=titleMain] li").eq(a)[0].className = "stepFormTitleCur"
                    } catch(f) {}
                })
            }
        }
    })
});