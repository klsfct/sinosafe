$(function() {
    var a = 300;
    var b = 300;
    $(".stack>img").toggle(function() {
        var d = 0;
        var c = 0;
        var e = $(this);
        e.next().children().each(function() {
            $(this).animate({
                top: "-" + d + "px",
                left: c + "px"
            },
            a);
            d = d + 55;
            c = (c + 0.75) * 2
        });
        e.next().animate({
            top: "-50px",
            left: "10px"
        },
        a).addClass("openStack").find("li a>img").animate({
            width: "50px",
            marginLeft: "9px"
        },
        a);
        e.animate({
            paddingTop: "0"
        })
    },
    function() {
        var c = $(this);
        c.next().removeClass("openStack").children("li").animate({
            top: "55px",
            left: "-10px"
        },
        b);
        c.next().find("li a>img").animate({
            width: "79px",
            marginLeft: "0"
        },
        b);
        c.animate({
            paddingTop: "35px"
        })
    });
    $(".stack li a").hover(function() {
        $("img", this).animate({
            width: "56px"
        },
        100);
        $("span", this).animate({
            marginRight: "30px"
        })
    },
    function() {
        $("img", this).animate({
            width: "50px"
        },
        100);
        $("span", this).animate({
            marginRight: "0"
        })
    })
});