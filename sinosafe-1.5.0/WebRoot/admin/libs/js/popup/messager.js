var bw; (function(a) {
    this.version = "@1.1";
    this.layer = {
        width: 200,
        height: 100
    };
    this.title = "信息提示";
    this.time = 4000;
    this.anims = {
        type: "slide",
        speed: 600
    };
    this.inits = function(g, f) {
        if ($("#message").is("div")) {
            return
        }
        var c = $('<div id="message" style="z-index:800;width:' + this.layer.width + 'px;position:absolute; display:none;bottom:0px; right:1px; overflow:hidden;"></div>');
        var b = $('<div class="msg_topcenter"><div class="msg_topleft"><div class="msg_topright"><div class="msg_title">' + g + '</div><div class="msg_close" id="message_close"></div></div></div></div>');
        var e = $('<div class="msg_middlecenter"><div class="msg_middleleft"><div class="msg_middleright"><div class="boxContent" style="height:' + (this.layer.height - 35) + 'px;">' + f + "</div></div></div></div>");
        var d = $('<div class="msg_bottomcenter"><div class="msg_bottomleft"><div class="msg_bottomright"></div></div></div>');
        c.append(b).append(e).append(d);
        $(document.body).append(c)
    };
    this.show = function(i, h, g, f, c) {
        if ($("#message").is("div")) {
            return
        }
        if (i == 0 || !i) {
            i = this.title
        }
        this.inits(i, h);
        if (g) {
            this.time = g
        }
        if (c != null && c != "") {
            $("#message").addClass(c)
        } else {
            $("#message").addClass("box_msg")
        }
        switch (this.anims.type) {
        case "slide":
            $("#message").slideDown(this.anims.speed);
            break;
        case "fade":
            $("#message").fadeIn(this.anims.speed);
            break;
        case "show":
            $("#message").show(this.anims.speed);
            break;
        default:
            $("#message").slideDown(this.anims.speed);
            break
        }
        $("#message_close").click(function() {
            mesclose()
        });
        if (f != null && f != "") {
            try {
                var b = $("<embed src=" + f + " AutoStart='true' type='application/x-mplayer2'></embed>");
                $("body").append(b)
            } catch(d) {}
        }
        this.rmmessage(this.time)
    };
    this.lays = function(c, b) {
        if ($("#message").is("div")) {
            return
        }
        if (c != 0 && c) {
            this.layer.width = c
        }
        if (b != 0 && b) {
            this.layer.height = b
        }
    };
    this.anim = function(b, c) {
        if ($("#message").is("div")) {
            return
        }
        if (b != 0 && b) {
            this.anims.type = b
        }
        if (c != 0 && c) {
            switch (c) {
            case "slow":
                break;
            case "fast":
                this.anims.speed = 200;
                break;
            case "normal":
                this.anims.speed = 400;
                break;
            default:
                this.anims.speed = c
            }
        }
    };
    this.rmmessage = function(b) {
        if (b != "stay") {
            bw = setTimeout("mesclose()", b)
        }
    };
    this.mesclose = function() {
        clearTimeout(bw);
        switch (this.anims.type) {
        case "slide":
            $("#message").slideUp(this.anims.speed);
            break;
        case "fade":
            $("#message").fadeOut(this.anims.speed);
            break;
        case "show":
            $("#message").hide(this.anims.speed);
            break;
        default:
            $("#message").slideUp(this.anims.speed);
            break
        }
        var b = setTimeout('$("#message").remove();', this.anims.speed);
        setTimeout('$("#messageSound").remove();', this.anims.speed);
        this.original()
    };
    this.original = function() {
        this.layer = {
            width: 200,
            height: 100
        };
        this.title = "信息提示";
        this.time = 4000;
        this.anims = {
            type: "slide",
            speed: 600
        }
    };
    a.messager = this;
    return a
})(jQuery);