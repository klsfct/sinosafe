jQuery.fn.extend({
    imagePreviewRender: function() {
        xOffset = 10;
        yOffset = 30;
        _width = "300px";
        var a;
        $(this).hover(function(b) {
            $(this).attr("t", $(this).attr("title"));
            $(this).attr("title", "");
            var d = ($(this).attr("t") != "" && $(this).attr("t") != null) ? "<br/>" + $(this).attr("t") : "";
            a = $("<p id='imgpreview'><img style='width:"+_width+"' src='" + $(this).attr("ref") + "' alt='正在加载预览图...' />" + d + "</p>");
            $("body").append(a);
            a.css("top", (b.pageY - xOffset) + "px").css("left", (b.pageX + yOffset) + "px").fadeIn("fast");
        },
        function() {
            $(this).attr("title", $(this).attr("t"));
            a.remove();
        });
        $(this).mousemove(function(b) {
            $("#imgpreview").css("top", (b.pageY - xOffset) + "px").css("left", (b.pageX + yOffset) + "px");
        })
    }
});