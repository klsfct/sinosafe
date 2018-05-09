jQuery.fn.imgFrameRender = function(a) {
    var a = jQuery.extend({
        tape: true,
        shadow: "left-bottom",
        bottom: "13px",
        top: "12px",
        left: "17px",
        right: "17px",
        shadowPos: "bottom-right",
        shadowColor: "#CCCCCC",
        backgroundColor: "#FDFDFD"
    },
    a);
    return this.each(function() {
        $(this).addClass("polaroid-pic");
        $(this).wrap('<div class="polaroid-container"></div>');
        $(this).wrap('<div class="polaroid-shadow"></div>');
        $(this).wrap('<div class="polaroid"></div>');
        $(this).wrap('<div class="polaroid-content-container"></div>');
        $(this).parent().attr("style", "margin:" + a.top + " " + a.right + " " + a.bottom + " " + a.left);
        switch (a.shadowPos) {
        case "bottom-right":
            $(this).parent().parent().attr("style", "top: -3px; left:-3px; background-color:" + a.backgroundColor);
            break;
        case "bottom-left":
            $(this).parent().parent().attr("style", "top: -3px; left:3px; background-color:" + a.backgroundColor);
            break;
        case "top-left":
            $(this).parent().parent().attr("style", "top: 3px; left:3px; background-color:" + a.backgroundColor);
            break;
        case "top-right":
            $(this).parent().parent().attr("style", "top: 3px; left:-3px; background-color:" + a.backgroundColor);
            break;
        default:
            $(this).parent().parent().attr("style", "top: -3px; left:-3px; background-color:" + a.backgroundColor)
        }
        $(this).parent().parent().parent().attr("style", "background-color:" + a.shadowColor);
        if (a.tape === true) {
            $(this).parent().parent().parent().parent().append('<span class="tape top-left">&nbsp;</span>');
            $(this).parent().parent().parent().parent().append('<span class="tape top-right">&nbsp;</span>')
        }
    })
};