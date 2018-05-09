var SWFUpload;
if (SWFUpload == undefined) {
    SWFUpload = function(a) {
        this.initSWFUpload(a)
    }
}
SWFUpload.prototype.initSWFUpload = function(b) {
    try {
        this.customSettings = {};
        this.settings = b;
        this.eventQueue = [];
        this.movieName = "SWFUpload_" + SWFUpload.movieCount++;
        this.movieElement = null;
        SWFUpload.instances[this.movieName] = this;
        this.initSettings();
        this.loadFlash();
        this.displayDebugInfo()
    } catch(a) {
        delete SWFUpload.instances[this.movieName];
        throw a
    }
};
SWFUpload.instances = {};
SWFUpload.movieCount = 0;
SWFUpload.version = "2.2.0 2009-03-25";
SWFUpload.QUEUE_ERROR = {
    QUEUE_LIMIT_EXCEEDED: -100,
    FILE_EXCEEDS_SIZE_LIMIT: -110,
    ZERO_BYTE_FILE: -120,
    INVALID_FILETYPE: -130
};
SWFUpload.UPLOAD_ERROR = {
    HTTP_ERROR: -200,
    MISSING_UPLOAD_URL: -210,
    IO_ERROR: -220,
    SECURITY_ERROR: -230,
    UPLOAD_LIMIT_EXCEEDED: -240,
    UPLOAD_FAILED: -250,
    SPECIFIED_FILE_ID_NOT_FOUND: -260,
    FILE_VALIDATION_FAILED: -270,
    FILE_CANCELLED: -280,
    UPLOAD_STOPPED: -290
};
SWFUpload.FILE_STATUS = {
    QUEUED: -1,
    IN_PROGRESS: -2,
    ERROR: -3,
    COMPLETE: -4,
    CANCELLED: -5
};
SWFUpload.BUTTON_ACTION = {
    SELECT_FILE: -100,
    SELECT_FILES: -110,
    START_UPLOAD: -120
};
SWFUpload.CURSOR = {
    ARROW: -1,
    HAND: -2
};
SWFUpload.WINDOW_MODE = {
    WINDOW: "window",
    TRANSPARENT: "transparent",
    OPAQUE: "opaque"
};
SWFUpload.completeURL = function(a) {
    if (typeof(a) !== "string" || a.match(/^https?:\/\//i) || a.match(/^\//)) {
        return a
    }
    var c = window.location.protocol + "//" + window.location.hostname + (window.location.port ? ":" + window.location.port: "");
    var b = window.location.pathname.lastIndexOf("/");
    if (b <= 0) {
        path = "/"
    } else {
        path = window.location.pathname.substr(0, b) + "/"
    }
    return path + a
};
SWFUpload.prototype.initSettings = function() {
    this.ensureDefault = function(b, a) {
        this.settings[b] = (this.settings[b] == undefined) ? a: this.settings[b]
    };
    this.ensureDefault("upload_url", "");
    this.ensureDefault("preserve_relative_urls", false);
    this.ensureDefault("file_post_name", "fileData");
    this.ensureDefault("post_params", {});
    this.ensureDefault("use_query_string", false);
    this.ensureDefault("requeue_on_error", false);
    this.ensureDefault("http_success", []);
    this.ensureDefault("assume_success_timeout", 0);
    this.ensureDefault("file_types", "*.*");
    this.ensureDefault("file_types_description", "All Files");
    this.ensureDefault("file_size_limit", 0);
    this.ensureDefault("file_upload_limit", 0);
    this.ensureDefault("file_queue_limit", 0);
    this.ensureDefault("flash_url", "swfupload.swf");
    this.ensureDefault("prevent_swf_caching", true);
    this.ensureDefault("button_image_url", "");
    this.ensureDefault("button_width", 124);
    this.ensureDefault("button_height", 25);
    this.ensureDefault("button_text", "");
    this.ensureDefault("button_text_style", "color: #000000; font-size: 16pt;");
    this.ensureDefault("button_text_top_padding", 0);
    this.ensureDefault("button_text_left_padding", 0);
    this.ensureDefault("button_action", SWFUpload.BUTTON_ACTION.SELECT_FILES);
    this.ensureDefault("button_disabled", false);
    this.ensureDefault("button_placeholder_id", "");
    this.ensureDefault("button_placeholder", null);
    this.ensureDefault("button_cursor", SWFUpload.CURSOR.HAND);
    this.ensureDefault("button_window_mode", SWFUpload.WINDOW_MODE.TRANSPARENT);
    this.ensureDefault("debug", false);
    this.settings.debug_enabled = this.settings.debug;
    this.settings.return_upload_start_handler = this.returnUploadStart;
    this.ensureDefault("swfupload_loaded_handler", null);
    this.ensureDefault("file_dialog_start_handler", null);
    this.ensureDefault("file_queued_handler", null);
    this.ensureDefault("file_queue_error_handler", null);
    this.ensureDefault("file_dialog_complete_handler", null);
    this.ensureDefault("upload_start_handler", null);
    this.ensureDefault("upload_progress_handler", null);
    this.ensureDefault("upload_error_handler", null);
    this.ensureDefault("upload_success_handler", null);
    this.ensureDefault("upload_complete_handler", null);
    this.ensureDefault("debug_handler", this.debugMessage);
    this.ensureDefault("custom_settings", {});
    this.customSettings = this.settings.custom_settings;
    if ( !! this.settings.prevent_swf_caching) {
        this.settings.flash_url = this.settings.flash_url + (this.settings.flash_url.indexOf("?") < 0 ? "?": "&") + "preventswfcaching=" + new Date().getTime()
    }
    if (!this.settings.preserve_relative_urls) {
        this.settings.upload_url = SWFUpload.completeURL(this.settings.upload_url);
        this.settings.button_image_url = SWFUpload.completeURL(this.settings.button_image_url)
    }
    delete this.ensureDefault
};
SWFUpload.prototype.loadFlash = function() {
    var a, b;
    if (document.getElementById(this.movieName) !== null) {
        throw "ID " + this.movieName + " is already in use. The Flash Object could not be added"
    }
    a = document.getElementById(this.settings.button_placeholder_id) || this.settings.button_placeholder;
    if (a == undefined) {
        throw "Could not find the placeholder element: " + this.settings.button_placeholder_id
    }
    b = document.createElement("div");
    b.innerHTML = this.getFlashHTML();
    a.parentNode.replaceChild(b.firstChild, a);
    if (window[this.movieName] == undefined) {
        window[this.movieName] = this.getMovieElement()
    }
};
SWFUpload.prototype.getFlashHTML = function() {
    return ['<object id="', this.movieName, '" type="application/x-shockwave-flash" data="', this.settings.flash_url, '" width="', this.settings.button_width, '" height="', this.settings.button_height, '" class="swfupload">', '<param name="wmode" value="', this.settings.button_window_mode, '" />', '<param name="movie" value="', this.settings.flash_url, '" />', '<param name="quality" value="high" />', '<param name="menu" value="false" />', '<param name="allowScriptAccess" value="always" />', '<param name="flashvars" value="' + this.getFlashVars() + '" />', "</object>"].join("")
};
SWFUpload.prototype.getFlashVars = function() {
    var b = this.buildParamString();
    var a = this.settings.http_success.join(",");
    return ["movieName=", encodeURIComponent(this.movieName), "&amp;uploadURL=", encodeURIComponent(this.settings.upload_url), "&amp;useQueryString=", encodeURIComponent(this.settings.use_query_string), "&amp;requeueOnError=", encodeURIComponent(this.settings.requeue_on_error), "&amp;httpSuccess=", encodeURIComponent(a), "&amp;assumeSuccessTimeout=", encodeURIComponent(this.settings.assume_success_timeout), "&amp;params=", encodeURIComponent(b), "&amp;filePostName=", encodeURIComponent(this.settings.file_post_name), "&amp;fileTypes=", encodeURIComponent(this.settings.file_types), "&amp;fileTypesDescription=", encodeURIComponent(this.settings.file_types_description), "&amp;fileSizeLimit=", encodeURIComponent(this.settings.file_size_limit), "&amp;fileUploadLimit=", encodeURIComponent(this.settings.file_upload_limit), "&amp;fileQueueLimit=", encodeURIComponent(this.settings.file_queue_limit), "&amp;debugEnabled=", encodeURIComponent(this.settings.debug_enabled), "&amp;buttonImageURL=", encodeURIComponent(this.settings.button_image_url), "&amp;buttonWidth=", encodeURIComponent(this.settings.button_width), "&amp;buttonHeight=", encodeURIComponent(this.settings.button_height), "&amp;buttonText=", encodeURIComponent(this.settings.button_text), "&amp;buttonTextTopPadding=", encodeURIComponent(this.settings.button_text_top_padding), "&amp;buttonTextLeftPadding=", encodeURIComponent(this.settings.button_text_left_padding), "&amp;buttonTextStyle=", encodeURIComponent(this.settings.button_text_style), "&amp;buttonAction=", encodeURIComponent(this.settings.button_action), "&amp;buttonDisabled=", encodeURIComponent(this.settings.button_disabled), "&amp;buttonCursor=", encodeURIComponent(this.settings.button_cursor)].join("")
};
SWFUpload.prototype.getMovieElement = function() {
    if (this.movieElement == undefined) {
        this.movieElement = document.getElementById(this.movieName)
    }
    if (this.movieElement === null) {
        throw "Could not find Flash element"
    }
    return this.movieElement
};
SWFUpload.prototype.buildParamString = function() {
    var c = this.settings.post_params;
    var b = [];
    if (typeof(c) === "object") {
        for (var a in c) {
            if (c.hasOwnProperty(a)) {
                b.push(encodeURIComponent(a.toString()) + "=" + encodeURIComponent(c[a].toString()))
            }
        }
    }
    return b.join("&amp;")
};
SWFUpload.prototype.destroy = function() {
    try {
        this.cancelUpload(null, false);
        var a = null;
        a = this.getMovieElement();
        if (a && typeof(a.CallFunction) === "unknown") {
            for (var c in a) {
                try {
                    if (typeof(a[c]) === "function") {
                        a[c] = null
                    }
                } catch(e) {}
            }
            try {
                a.parentNode.removeChild(a)
            } catch(b) {}
        }
        window[this.movieName] = null;
        SWFUpload.instances[this.movieName] = null;
        delete SWFUpload.instances[this.movieName];
        this.movieElement = null;
        this.settings = null;
        this.customSettings = null;
        this.eventQueue = null;
        this.movieName = null;
        return true
    } catch(d) {
        return false
    }
};
SWFUpload.prototype.displayDebugInfo = function() {
    this.debug(["---SWFUpload Instance Info---\n", "Version: ", SWFUpload.version, "\n", "Movie Name: ", this.movieName, "\n", "Settings:\n", "\t", "upload_url: ", this.settings.upload_url, "\n", "\t", "flash_url: ", this.settings.flash_url, "\n", "\t", "use_query_string: ", this.settings.use_query_string.toString(), "\n", "\t", "requeue_on_error: ", this.settings.requeue_on_error.toString(), "\n", "\t", "http_success: ", this.settings.http_success.join(", "), "\n", "\t", "assume_success_timeout: ", this.settings.assume_success_timeout, "\n", "\t", "file_post_name: ", this.settings.file_post_name, "\n", "\t", "post_params: ", this.settings.post_params.toString(), "\n", "\t", "file_types: ", this.settings.file_types, "\n", "\t", "file_types_description: ", this.settings.file_types_description, "\n", "\t", "file_size_limit: ", this.settings.file_size_limit, "\n", "\t", "file_upload_limit: ", this.settings.file_upload_limit, "\n", "\t", "file_queue_limit: ", this.settings.file_queue_limit, "\n", "\t", "debug: ", this.settings.debug.toString(), "\n", "\t", "prevent_swf_caching: ", this.settings.prevent_swf_caching.toString(), "\n", "\t", "button_placeholder_id: ", this.settings.button_placeholder_id.toString(), "\n", "\t", "button_placeholder: ", (this.settings.button_placeholder ? "Set": "Not Set"), "\n", "\t", "button_image_url: ", this.settings.button_image_url.toString(), "\n", "\t", "button_width: ", this.settings.button_width.toString(), "\n", "\t", "button_height: ", this.settings.button_height.toString(), "\n", "\t", "button_text: ", this.settings.button_text.toString(), "\n", "\t", "button_text_style: ", this.settings.button_text_style.toString(), "\n", "\t", "button_text_top_padding: ", this.settings.button_text_top_padding.toString(), "\n", "\t", "button_text_left_padding: ", this.settings.button_text_left_padding.toString(), "\n", "\t", "button_action: ", this.settings.button_action.toString(), "\n", "\t", "button_disabled: ", this.settings.button_disabled.toString(), "\n", "\t", "custom_settings: ", this.settings.custom_settings.toString(), "\n", "Event Handlers:\n", "\t", "swfupload_loaded_handler assigned: ", (typeof this.settings.swfupload_loaded_handler === "function").toString(), "\n", "\t", "file_dialog_start_handler assigned: ", (typeof this.settings.file_dialog_start_handler === "function").toString(), "\n", "\t", "file_queued_handler assigned: ", (typeof this.settings.file_queued_handler === "function").toString(), "\n", "\t", "file_queue_error_handler assigned: ", (typeof this.settings.file_queue_error_handler === "function").toString(), "\n", "\t", "upload_start_handler assigned: ", (typeof this.settings.upload_start_handler === "function").toString(), "\n", "\t", "upload_progress_handler assigned: ", (typeof this.settings.upload_progress_handler === "function").toString(), "\n", "\t", "upload_error_handler assigned: ", (typeof this.settings.upload_error_handler === "function").toString(), "\n", "\t", "upload_success_handler assigned: ", (typeof this.settings.upload_success_handler === "function").toString(), "\n", "\t", "upload_complete_handler assigned: ", (typeof this.settings.upload_complete_handler === "function").toString(), "\n", "\t", "debug_handler assigned: ", (typeof this.settings.debug_handler === "function").toString(), "\n"].join(""))
};
SWFUpload.prototype.addSetting = function(b, c, a) {
    if (c == undefined) {
        return (this.settings[b] = a)
    } else {
        return (this.settings[b] = c)
    }
};
SWFUpload.prototype.getSetting = function(a) {
    if (this.settings[a] != undefined) {
        return this.settings[a]
    }
    return ""
};
SWFUpload.prototype.callFlash = function(functionName, argumentArray) {
    argumentArray = argumentArray || [];
    var movieElement = this.getMovieElement();
    var returnValue, returnString;
    try {
        returnString = movieElement.CallFunction('<invoke name="' + functionName + '" returntype="javascript">' + __flash__argumentsToXML(argumentArray, 0) + "</invoke>");
        returnValue = eval(returnString)
    } catch(ex) {
        throw "Call to " + functionName + " failed"
    }
    if (returnValue != undefined && typeof returnValue.post === "object") {
        returnValue = this.unescapeFilePostParams(returnValue)
    }
    return returnValue
};
SWFUpload.prototype.selectFile = function() {
    this.callFlash("SelectFile")
};
SWFUpload.prototype.selectFiles = function() {
    this.callFlash("SelectFiles")
};
SWFUpload.prototype.startUpload = function(a) {
    this.callFlash("StartUpload", [a])
};
SWFUpload.prototype.cancelUpload = function(a, b) {
    if (b !== false) {
        b = true
    }
    this.callFlash("CancelUpload", [a, b])
};
SWFUpload.prototype.stopUpload = function() {
    this.callFlash("StopUpload")
};
SWFUpload.prototype.getStats = function() {
    return this.callFlash("GetStats")
};
SWFUpload.prototype.setStats = function(a) {
    this.callFlash("SetStats", [a])
};
SWFUpload.prototype.getFile = function(a) {
    if (typeof(a) === "number") {
        return this.callFlash("GetFileByIndex", [a])
    } else {
        return this.callFlash("GetFile", [a])
    }
};
SWFUpload.prototype.addFileParam = function(a, b, c) {
    return this.callFlash("AddFileParam", [a, b, c])
};
SWFUpload.prototype.removeFileParam = function(a, b) {
    this.callFlash("RemoveFileParam", [a, b])
};
SWFUpload.prototype.setUploadURL = function(a) {
    this.settings.upload_url = a.toString();
    this.callFlash("SetUploadURL", [a])
};
SWFUpload.prototype.setPostParams = function(a) {
    this.settings.post_params = a;
    this.callFlash("SetPostParams", [a])
};
SWFUpload.prototype.addPostParam = function(a, b) {
    this.settings.post_params[a] = b;
    this.callFlash("SetPostParams", [this.settings.post_params])
};
SWFUpload.prototype.removePostParam = function(a) {
    delete this.settings.post_params[a];
    this.callFlash("SetPostParams", [this.settings.post_params])
};
SWFUpload.prototype.setFileTypes = function(a, b) {
    this.settings.file_types = a;
    this.settings.file_types_description = b;
    this.callFlash("SetFileTypes", [a, b])
};
SWFUpload.prototype.setFileSizeLimit = function(a) {
    this.settings.file_size_limit = a;
    this.callFlash("SetFileSizeLimit", [a])
};
SWFUpload.prototype.setFileUploadLimit = function(a) {
    this.settings.file_upload_limit = a;
    this.callFlash("SetFileUploadLimit", [a])
};
SWFUpload.prototype.setFileQueueLimit = function(a) {
    this.settings.file_queue_limit = a;
    this.callFlash("SetFileQueueLimit", [a])
};
SWFUpload.prototype.setFilePostName = function(a) {
    this.settings.file_post_name = a;
    this.callFlash("SetFilePostName", [a])
};
SWFUpload.prototype.setUseQueryString = function(a) {
    this.settings.use_query_string = a;
    this.callFlash("SetUseQueryString", [a])
};
SWFUpload.prototype.setRequeueOnError = function(a) {
    this.settings.requeue_on_error = a;
    this.callFlash("SetRequeueOnError", [a])
};
SWFUpload.prototype.setHTTPSuccess = function(a) {
    if (typeof a === "string") {
        a = a.replace(" ", "").split(",")
    }
    this.settings.http_success = a;
    this.callFlash("SetHTTPSuccess", [a])
};
SWFUpload.prototype.setAssumeSuccessTimeout = function(a) {
    this.settings.assume_success_timeout = a;
    this.callFlash("SetAssumeSuccessTimeout", [a])
};
SWFUpload.prototype.setDebugEnabled = function(a) {
    this.settings.debug_enabled = a;
    this.callFlash("SetDebugEnabled", [a])
};
SWFUpload.prototype.setButtonImageURL = function(a) {
    if (a == undefined) {
        a = ""
    }
    this.settings.button_image_url = a;
    this.callFlash("SetButtonImageURL", [a])
};
SWFUpload.prototype.setButtonDimensions = function(c, a) {
    this.settings.button_width = c;
    this.settings.button_height = a;
    var b = this.getMovieElement();
    if (b != undefined) {
        b.style.width = c + "px";
        b.style.height = a + "px"
    }
    this.callFlash("SetButtonDimensions", [c, a])
};
SWFUpload.prototype.setButtonText = function(a) {
    this.settings.button_text = a;
    this.callFlash("SetButtonText", [a])
};
SWFUpload.prototype.setButtonTextPadding = function(b, a) {
    this.settings.button_text_top_padding = a;
    this.settings.button_text_left_padding = b;
    this.callFlash("SetButtonTextPadding", [b, a])
};
SWFUpload.prototype.setButtonTextStyle = function(a) {
    this.settings.button_text_style = a;
    this.callFlash("SetButtonTextStyle", [a])
};
SWFUpload.prototype.setButtonDisabled = function(a) {
    this.settings.button_disabled = a;
    this.callFlash("SetButtonDisabled", [a])
};
SWFUpload.prototype.setButtonAction = function(a) {
    this.settings.button_action = a;
    this.callFlash("SetButtonAction", [a])
};
SWFUpload.prototype.setButtonCursor = function(a) {
    this.settings.button_cursor = a;
    this.callFlash("SetButtonCursor", [a])
};
SWFUpload.prototype.queueEvent = function(b, c) {
    if (c == undefined) {
        c = []
    } else {
        if (! (c instanceof Array)) {
            c = [c]
        }
    }
    var a = this;
    if (typeof this.settings[b] === "function") {
        this.eventQueue.push(function() {
            this.settings[b].apply(this, c)
        });
        setTimeout(function() {
            a.executeNextEvent()
        },
        0)
    } else {
        if (this.settings[b] !== null) {
            throw "Event handler " + b + " is unknown or is not a function"
        }
    }
};
SWFUpload.prototype.executeNextEvent = function() {
    var a = this.eventQueue ? this.eventQueue.shift() : null;
    if (typeof(a) === "function") {
        a.apply(this)
    }
};
SWFUpload.prototype.unescapeFilePostParams = function(c) {
    var e = /[$]([0-9a-f]{4})/i;
    var f = {};
    var d;
    if (c != undefined) {
        for (var a in c.post) {
            if (c.post.hasOwnProperty(a)) {
                d = a;
                var b;
                while ((b = e.exec(d)) !== null) {
                    d = d.replace(b[0], String.fromCharCode(parseInt("0x" + b[1], 16)))
                }
                f[d] = c.post[a]
            }
        }
        c.post = f
    }
    return c
};
SWFUpload.prototype.testExternalInterface = function() {
    try {
        return this.callFlash("TestExternalInterface")
    } catch(a) {
        return false
    }
};
SWFUpload.prototype.flashReady = function() {
    var a = this.getMovieElement();
    if (!a) {
        this.debug("Flash called back ready but the flash movie can't be found.");
        return
    }
    this.cleanUp(a);
    this.queueEvent("swfupload_loaded_handler")
};
SWFUpload.prototype.cleanUp = function(a) {
    try {
        if (this.movieElement && typeof(a.CallFunction) === "unknown") {
            this.debug("Removing Flash functions hooks (this should only run in IE and should prevent memory leaks)");
            for (var c in a) {
                try {
                    if (typeof(a[c]) === "function") {
                        a[c] = null
                    }
                } catch(b) {}
            }
        }
    } catch(d) {}
    window.__flash__removeCallback = function(e, f) {
        try {
            if (e) {
                e[f] = null
            }
        } catch(g) {}
    }
};
SWFUpload.prototype.fileDialogStart = function() {
    this.queueEvent("file_dialog_start_handler")
};
SWFUpload.prototype.fileQueued = function(a) {
    a = this.unescapeFilePostParams(a);
    this.queueEvent("file_queued_handler", a)
};
SWFUpload.prototype.fileQueueError = function(a, c, b) {
    a = this.unescapeFilePostParams(a);
    this.queueEvent("file_queue_error_handler", [a, c, b])
};
SWFUpload.prototype.fileDialogComplete = function(b, c, a) {
    this.queueEvent("file_dialog_complete_handler", [b, c, a])
};
SWFUpload.prototype.uploadStart = function(a) {
    a = this.unescapeFilePostParams(a);
    this.queueEvent("return_upload_start_handler", a)
};
SWFUpload.prototype.returnUploadStart = function(a) {
    var b;
    if (typeof this.settings.upload_start_handler === "function") {
        a = this.unescapeFilePostParams(a);
        b = this.settings.upload_start_handler.call(this, a)
    } else {
        if (this.settings.upload_start_handler != undefined) {
            throw "upload_start_handler must be a function"
        }
    }
    if (b === undefined) {
        b = true
    }
    b = !!b;
    this.callFlash("ReturnUploadStart", [b])
};
SWFUpload.prototype.uploadProgress = function(a, c, b) {
    a = this.unescapeFilePostParams(a);
    this.queueEvent("upload_progress_handler", [a, c, b])
};
SWFUpload.prototype.uploadError = function(a, c, b) {
    a = this.unescapeFilePostParams(a);
    this.queueEvent("upload_error_handler", [a, c, b])
};
SWFUpload.prototype.uploadSuccess = function(b, a, c) {
    b = this.unescapeFilePostParams(b);
    this.queueEvent("upload_success_handler", [b, a, c])
};
SWFUpload.prototype.uploadComplete = function(a) {
    a = this.unescapeFilePostParams(a);
    this.queueEvent("upload_complete_handler", a)
};
SWFUpload.prototype.debug = function(a) {
    this.queueEvent("debug_handler", a)
};
SWFUpload.prototype.debugMessage = function(c) {
    if (this.settings.debug) {
        var a, d = [];
        if (typeof c === "object" && typeof c.name === "string" && typeof c.message === "string") {
            for (var b in c) {
                if (c.hasOwnProperty(b)) {
                    d.push(b + ": " + c[b])
                }
            }
            a = d.join("\n") || "";
            d = a.split("\n");
            a = "EXCEPTION: " + d.join("\nEXCEPTION: ");
            SWFUpload.Console.writeLine(a)
        } else {
            SWFUpload.Console.writeLine(c)
        }
    }
};
SWFUpload.Console = {};
SWFUpload.Console.writeLine = function(d) {
    var b, a;
    try {
        b = document.getElementById("SWFUpload_Console");
        if (!b) {
            a = document.createElement("form");
            document.getElementsByTagName("body")[0].appendChild(a);
            b = document.createElement("textarea");
            b.id = "SWFUpload_Console";
            b.style.fontFamily = "monospace";
            b.setAttribute("wrap", "off");
            b.wrap = "off";
            b.style.overflow = "auto";
            b.style.width = "700px";
            b.style.height = "350px";
            b.style.margin = "5px";
            a.appendChild(b)
        }
        b.value += d + "\n";
        b.scrollTop = b.scrollHeight - b.clientHeight
    } catch(c) {
        alert("Exception: " + c.name + " Message: " + c.message)
    }
};
var SWFUpload;
if (typeof(SWFUpload) === "function") {
    SWFUpload.queue = {};
    SWFUpload.prototype.initSettings = (function(a) {
        return function() {
            if (typeof(a) === "function") {
                a.call(this)
            }
            this.queueSettings = {};
            this.queueSettings.queue_cancelled_flag = false;
            this.queueSettings.queue_upload_count = 0;
            this.queueSettings.user_upload_complete_handler = this.settings.upload_complete_handler;
            this.queueSettings.user_upload_start_handler = this.settings.upload_start_handler;
            this.settings.upload_complete_handler = SWFUpload.queue.uploadCompleteHandler;
            this.settings.upload_start_handler = SWFUpload.queue.uploadStartHandler;
            this.settings.queue_complete_handler = this.settings.queue_complete_handler || null
        }
    })(SWFUpload.prototype.initSettings);
    SWFUpload.prototype.startUpload = function(a) {
        this.queueSettings.queue_cancelled_flag = false;
        this.callFlash("StartUpload", [a])
    };
    SWFUpload.prototype.cancelQueue = function() {
        this.queueSettings.queue_cancelled_flag = true;
        this.stopUpload();
        var a = this.getStats();
        while (a.files_queued > 0) {
            this.cancelUpload();
            a = this.getStats()
        }
    };
    SWFUpload.queue.uploadStartHandler = function(a) {
        var b;
        if (typeof(this.queueSettings.user_upload_start_handler) === "function") {
            b = this.queueSettings.user_upload_start_handler.call(this, a)
        }
        b = (b === false) ? false: true;
        this.queueSettings.queue_cancelled_flag = !b;
        return b
    };
    SWFUpload.queue.uploadCompleteHandler = function(b) {
        var c = this.queueSettings.user_upload_complete_handler;
        var d;
        if (b.filestatus === SWFUpload.FILE_STATUS.COMPLETE) {
            this.queueSettings.queue_upload_count++
        }
        if (typeof(c) === "function") {
            d = (c.call(this, b) === false) ? false: true
        } else {
            if (b.filestatus === SWFUpload.FILE_STATUS.QUEUED) {
                d = false
            } else {
                d = true
            }
        }
        if (d) {
            var a = this.getStats();
            if (a.files_queued > 0 && this.queueSettings.queue_cancelled_flag === false) {
                this.startUpload()
            } else {
                if (this.queueSettings.queue_cancelled_flag === false) {
                    this.queueEvent("queue_complete_handler", [this.queueSettings.queue_upload_count]);
                    this.queueSettings.queue_upload_count = 0
                } else {
                    this.queueSettings.queue_cancelled_flag = false;
                    this.queueSettings.queue_upload_count = 0
                }
            }
        }
    }
}
function FileProgress(c, d, h, j) {
    this.fileProgressID = c.id;
    this.opacity = 100;
    this.height = 0;
    this.fileProgressWrapper = document.getElementById(this.fileProgressID);
    if (!this.fileProgressWrapper) {
        this.fileProgressWrapper = document.createElement("div");
        this.fileProgressWrapper.className = "upload_list";
        this.fileProgressWrapper.id = this.fileProgressID;
        var a = document.createElement("div");
        a.className = "upload_iconBg";
        this.uploadIcon = document.createElement("div");
        this.uploadIcon.className = "upload_icon_progess";
        a.appendChild(this.uploadIcon);
        var f = $('<div class="upload_name"></div>');
        var e = $("<span></span>");
        if (j) {
            e.attr("title", c.name)
        }
        e.html(c.name);
        f.append(e);
        f.width(h);
        this.progressBar = document.createElement("div");
        this.progressBar.className = "upload_progress_inner";
        var i = document.createElement("div");
        i.className = "upload_progress_con";
        var b = document.createElement("div");
        b.className = "upload_progress";
        b.appendChild(this.progressBar);
        i.appendChild(b);
        this.progressBar.style.width = "0%";
        this.progressText = document.createElement("div");
        this.progressText.className = "upload_progress_text";
        this.progressText.innerHTML = "0%";
        this.waitingTextCon = document.createElement("div");
        this.waitingTextCon.className = "upload_waiting";
        this.waitingTextCon.innerHTML = "等待中...";
        this.deleteTextCon = document.createElement("div");
        this.deleteTextCon.className = "upload_delete";
        this.deleteText = $("<a>删除</a>");
        $(this.deleteTextCon).append(this.deleteText);
        var g = $('<div class="clear"></div>');
        this.fileProgressWrapper.appendChild(a);
        $(this.fileProgressWrapper).append(f);
        this.fileProgressWrapper.appendChild(i);
        this.fileProgressWrapper.appendChild(this.progressText);
        $(this.fileProgressWrapper).append(this.waitingTextCon);
        $(this.fileProgressWrapper).append(this.deleteTextCon);
        $(this.fileProgressWrapper).append(g);
        document.getElementById(d).appendChild(this.fileProgressWrapper)
    } else {
        this.uploadIcon = this.fileProgressWrapper.childNodes[0].childNodes[0];
        this.progressBar = this.fileProgressWrapper.childNodes[2].childNodes[0].childNodes[0];
        this.progressText = this.fileProgressWrapper.childNodes[3];
        this.waitingTextCon = this.fileProgressWrapper.childNodes[4];
        this.deleteTextCon = this.fileProgressWrapper.childNodes[5];
        this.reset()
    }
    this.height = this.fileProgressWrapper.offsetHeight;
    this.setTimer(null)
}
FileProgress.prototype.setTimer = function(a) {};
FileProgress.prototype.getTimer = function(a) {};
FileProgress.prototype.reset = function() {
    this.progressBar.style.width = "0%";
    this.waitingTextCon.innerHTML = "等待中..."
};
FileProgress.prototype.setProgress = function(a) {
    this.progressBar.style.width = a + "%";
    this.progressText.innerHTML = a + "%";
    this.waitingTextCon.style.display = "none";
    if (a == 100) {
        this.waitingTextCon.style.display = "";
        this.waitingTextCon.innerHTML = "完成中...";
        this.deleteTextCon.style.display = "none"
    }
};
FileProgress.prototype.setComplete = function() {
    this.progressBar.style.width = "100%";
    this.progressText.innerHTML = "100%";
    this.uploadIcon.className = "upload_icon_ok";
    this.waitingTextCon.style.display = "none";
    this.deleteTextCon.style.display = "";
    try {
        enableTooltips()
    } catch(a) {}
};
FileProgress.prototype.setError = function() {
    this.progressBar.style.width = "0%";
    this.progressText.innerHTML = "0%";
    this.uploadIcon.className = "upload_icon_error";
    this.waitingTextCon.style.display = "none"
};
FileProgress.prototype.setCancelled = function() {
    this.fileProgressWrapper.innerHTML = "";
    this.fileProgressWrapper.style.height = 0;
    this.fileProgressWrapper.style.display = "none"
};
FileProgress.prototype.setStatus = function(a) {
    try {
        top.Dialog.alert(a)
    } catch(b) {
        alert(a)
    }
    this.fileProgressWrapper.innerHTML = "";
    this.fileProgressWrapper.style.height = 0;
    this.fileProgressWrapper.style.display = "none"
};
FileProgress.prototype.setStatus2 = function(a) {
    try {
        top.Dialog.alert(a)
    } catch(b) {
        alert(a)
    }
};
FileProgress.prototype.toggleCancel = function(status, swfUploadInstance, serverData) {
    if (swfUploadInstance) {
        var fileID = this.fileProgressID;
        var $target = $(this);
        this.deleteTextCon.onclick = function() {
            if (status == 0) {
                swfUploadInstance.cancelUpload(fileID)
            } else {
                if (status == 1) {
                    $(this).parent().html();
                    $(this).parent().height(0);
                    $(this).parent().hide();
                    var deleteUrl = swfUploadInstance.settings.delete_url + eval("(" + serverData + ")").fileId;
                    $.post(deleteUrl)
                } else {
                    if (status == 2) {
                        $(this).parent().html();
                        $(this).parent().height(0);
                        $(this).parent().hide();
                        swfUploadInstance.cancelUpload(fileID)
                    }
                }
            }
        }
    }
};
FileProgress.prototype.appear = function() {};
FileProgress.prototype.disappear = function() {};
function cancelQueue(a) {
    a.stopUpload();
    var c;
    do {
        c = a.getStats();
        a.cancelUpload()
    } while ( c . files_queued !== 0 );
    try {
        $.post(a.settings.delete_all_url, {},
        function() {
            $("#" + a.customSettings.cancelButtonId).hide();
            var d = $("#" + a.customSettings.cancelButtonId).attr("targetId");
            $("#" + d).html("");
            $(".existUploadList").each(function() {
                if ($(this).attr("targetId") == d) {
                    $(this).html("");
                    $(this).hide()
                }
            })
        })
    } catch(b) {
        alert("全部删除出错！")
    }
}
function fileDialogStart() {}
function fileQueued(c) {
    try {
        var a = new FileProgress(c, this.customSettings.progressTarget, this.customSettings.fileNameWidth, this.customSettings.showInfo);
        a.toggleCancel(2, this)
    } catch(b) {
        this.debug(b)
    }
}
function fileQueueError(c, e, d) {
    try {
        if (e === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
            var f;
            if (d == 0) {
                f = "上传文件数目达到最大值"
            } else {
                if (d > 0) {
                    f = "只能再上传" + d + "个文件"
                }
            }
            try {
                top.Dialog.alert(f)
            } catch(b) {
                alert(f)
            }
            return
        }
        var a = new FileProgress(c, this.customSettings.progressTarget, this.customSettings.fileNameWidth, this.customSettings.showInfo);
        a.setError();
        a.toggleCancel(2, this);
        switch (e) {
        case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
            a.setStatus("文件体积太大");
            this.debug("Error Code: File too big, File name: " + c.name + ", File size: " + c.size + ", Message: " + d);
            break;
        case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
            a.setStatus("无法上传0字节文件");
            this.debug("Error Code: Zero byte file, File name: " + c.name + ", File size: " + c.size + ", Message: " + d);
            break;
        case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
            a.setStatus("文件类型不符合要求");
            this.debug("Error Code: Invalid File Type, File name: " + c.name + ", File size: " + c.size + ", Message: " + d);
            break;
        case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
            a.setStatus("选择的文件太多. " + (d > 1 ? "只能再添加 " + d + " 个文件": "不能再添加文件了"));
            break;
        default:
            if (c !== null) {
                a.setStatus("上传失败，无法预知的错误。")
            }
            this.debug("Error Code: " + e + ", File name: " + c.name + ", File size: " + c.size + ", Message: " + d);
            break
        }
    } catch(b) {
        this.debug(b)
    }
}
function fileDialogComplete(a, c) {
    try {
        if (this.getStats().files_queued > 0) {}
        this.startUpload()
    } catch(b) {
        this.debug(b)
    }
}
function uploadStart(c) {
    try {
        var a = new FileProgress(c, this.customSettings.progressTarget, this.customSettings.fileNameWidth, this.customSettings.showInfo);
        a.toggleCancel(0, this)
    } catch(b) {}
    try {
        document.getElementById(this.customSettings.cancelButtonId).style.display = ""
    } catch(b) {}
    return true
}
function uploadProgress(c, f, e) {
    try {
        var d = Math.ceil((f / e) * 100);
        var a = new FileProgress(c, this.customSettings.progressTarget, this.customSettings.fileNameWidth, this.customSettings.showInfo);
        a.setProgress(d)
    } catch(b) {
        this.debug(b)
    }
}
function uploadSuccess(d, b) {
    try {
        var a = new FileProgress(d, this.customSettings.progressTarget, this.customSettings.fileNameWidth, this.customSettings.showInfo);
        a.setComplete();
        a.toggleCancel(1, this, b)
    } catch(c) {
        this.debug(c)
    }
    try {
        $("#" + this.customSettings.cancelButtonId).attr("targetId", this.customSettings.progressTarget)
    } catch(c) {
        this.debug(c)
    }
}
function uploadComplete(b) {
    try {
        if (this.getStats().files_queued === 0) {} else {
            this.startUpload()
        }
    } catch(a) {
        this.debug(a)
    }
}
function uploadError(c, e, d) {
    try {
        var a = new FileProgress(c, this.customSettings.progressTarget, this.customSettings.fileNameWidth, this.customSettings.showInfo);
        a.setError();
        a.toggleCancel(2, this);
        switch (e) {
        case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
            a.setStatus2("上传失败: " + d);
            this.debug("Error Code: HTTP Error, File name: " + c.name + ", Message: " + d);
            break;
        case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
            a.setStatus2("上传失败：找不到上传的路径");
            this.debug("Error Code: No backend file, File name: " + c.name + ", Message: " + d);
            break;
        case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
            a.setStatus2("上传失败，请重新上传");
            this.debug("Error Code: Upload Failed, File name: " + c.name + ", File size: " + c.size + ", Message: " + d);
            break;
        case SWFUpload.UPLOAD_ERROR.IO_ERROR:
            a.setStatus2("上传失败：服务端连接错误");
            this.debug("Error Code: IO Error, File name: " + c.name + ", Message: " + d);
            break;
        case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
            a.setStatus2("上传失败：安全设置");
            this.debug("Error Code: Security Error, File name: " + c.name + ", Message: " + d);
            break;
        case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
            a.setStatus2("上传失败：超出上传限制");
            this.debug("Error Code: Upload Limit Exceeded, File name: " + c.name + ", File size: " + c.size + ", Message: " + d);
            break;
        case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:
            a.setStatus2("上传失败：文件未找到");
            this.debug("Error Code: The file was not found, File name: " + c.name + ", File size: " + c.size + ", Message: " + d);
            break;
        case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
            a.setStatus2("验证失败：上传被跳过.");
            this.debug("Error Code: File Validation Failed, File name: " + c.name + ", File size: " + c.size + ", Message: " + d);
            break;
        case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
            if (this.getStats().files_queued === 0) {}
            a.setCancelled();
            break;
        case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
            break;
        default:
            a.setStatus2("上传失败。错误信息: " + error_code);
            this.debug("Error Code: " + e + ", File name: " + c.name + ", File size: " + c.size + ", Message: " + d);
            break
        }
    } catch(b) {
        this.debug(b)
    }
};