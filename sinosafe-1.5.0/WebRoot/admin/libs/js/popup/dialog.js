var IMAGESPATH = "";
var HideScrollbar = true;
var agt = window.navigator.userAgent;
var isIE = agt.toLowerCase().indexOf("msie") != -1;
var isGecko = agt.toLowerCase().indexOf("gecko") != -1;
var ieVer = isIE ? parseInt(agt.split(";")[1].replace(/(^\s*)|(\s*$)/g, "").split(" ")[1]) : 0;
var isIE8 = !!window.XDomainRequest && !!document.documentMode;
var isIE7 = ieVer == 7 && !isIE8;
var ielt7 = isIE && ieVer < 7;
var isQuirks = document.compatMode == "BackCompat";
var maxIndex = 900;
var instance;
var $id = function(a) {
    return typeof a == "string" ? document.getElementById(a) : a
};
function stopEvent(a) {
    a = window.event || a;
    if (!a) {
        return
    }
    if (isGecko) {
        a.preventDefault();
        a.stopPropagation()
    }
    a.cancelBubble = true;
    a.returnValue = false
}
function removeWinArray(d, c, b) {
    if (b) {
        var f = [];
        for (var a = 0; a < d.length; a++) {
            if (c == d[a]) {
                f.push(d.splice(a, 1)[0])
            }
        }
        return f
    }
    for (var a = 0; a < d.length; a++) {
        if (c == d[a]) {
            d.splice(a, 1)
        }
    }
    return d
}
if (!isIE && HTMLElement) {
    if (!HTMLElement.prototype.attachEvent) {
        window.attachEvent = document.attachEvent = HTMLElement.prototype.attachEvent = function(b, a) {
            b = b.substring(2);
            this.addEventListener(b, a, false)
        };
        window.detachEvent = document.detachEvent = HTMLElement.prototype.detachEvent = function(b, a) {
            b = b.substring(2);
            this.removeEventListener(b, a, false)
        }
    }
} else {
    if (isIE && ieVer < 8) {
        try {
            document.execCommand("BackgroundImageCache", false, true)
        } catch(e) {}
    }
}
var $topWindow = function() {
    var a = window;
    return a
};
var $bodyDimensions = function(i) {
    i = i || window;
    var h = i.document;
    var c = h.compatMode == "BackCompat" ? h.body.clientWidth: h.documentElement.clientWidth;
    var g = h.compatMode == "BackCompat" ? h.body.clientHeight: h.documentElement.clientHeight;
    var b = Math.max(h.documentElement.scrollLeft, h.body.scrollLeft);
    var d = Math.max(h.documentElement.scrollTop, h.body.scrollTop);
    var a = Math.max(h.documentElement.scrollWidth, h.body.scrollWidth);
    var f = Math.max(h.documentElement.scrollHeight, h.body.scrollHeight);
    if (f < g) {
        f = g
    }
    return {
        clientWidth: c,
        clientHeight: g,
        scrollLeft: b,
        scrollTop: d,
        scrollWidth: a,
        scrollHeight: f
    }
};
var fadeEffect = function(b, f, a, c, d) {
    if (!b.effect) {
        b.effect = {
            fade: 0,
            move: 0,
            size: 0
        }
    }
    clearInterval(b.effect.fade);
    var c = c || 20;
    b.effect.fade = setInterval(function() {
        f = f < a ? Math.min(f + c, a) : Math.max(f - c, a);
        b.style.opacity = f / 100;
        b.style.filter = "alpha(opacity=" + f + ")";
        if (f == a) {
            clearInterval(b.effect.fade);
            if (d) {
                d.call(b)
            }
        }
    },
    20)
};
var topWin = $topWindow();
var topDoc = topWin.document;
var Dialog = function() {
    this.ID = null;
    this.Width = 550;
    this.Height = 380;
    this.URL = null;
    this.OnLoad = null;
    this.InnerHtml = "";
    this.InvokeElementId = "";
    this.Top = "50%";
    this.Left = "50%";
    this.Title = "　";
    this.OkButtonText = " 确 定 ";
    this.CancelButtonText = " 取 消 ";
    this.OKEvent = null;
    this.CancelEvent = null;
    this.MaxEvent = null;
    this.DecreaseEvent = null;
    this.MinEvent = null;
    this.ShowButtonRow = false;
    this.ShowOkButton = true;
    this.ShowCancelButton = true;
    this.ButtonClass="";
    this.MessageIcon = "window.gif";
    this.MessageTitle = "";
    this.Message = "";
    this.ShowMessageRow = false;
    this.Modal = true;
    this.Drag = true;
    this.AutoClose = null;
    this.ShowCloseButton = true;
    this.ShowMaxButton = false;
    this.ShowMinButton = false;
    this.Animator = !ielt7;
    this.MsgForESC = "";
    this.InnerFrameName = null;
    this.Style = "normal";
    this.ButtonAlign = "right";
    this.DecreaseResetPosition = false;
    this.ResizeResetPosition = true;
    this.Alpha = 40;
    this.Bgcolor = "#333333";
    this.CloseHideScroller = false;
    this.AllowChangeIndex = true;
    this.MinToTask = false;
    this.IconClass = "dialog_icon_default";
    this.IconSrc = "";
    this.ParamsObj = null;
    this.dialogDiv = null;
    this.bgDiv = null;
    this.openerWindow = null;
    this.openerDialog = null;
    this.innerFrame = null;
    this.innerWin = null;
    this.innerDoc = null;
    this.zindex = 900;
    this.cancelButton = null;
    this.okButton = null;
    this.maxButton = null;
    this.minButton = null;
    this.unauthorized = false;
    this.maxFlag = false;
    this.minFlag = false;
    if (arguments.length > 0 && typeof(arguments[0]) == "string") {
        this.ID = arguments[0]
    } else {
        if (arguments.length > 0 && typeof(arguments[0]) == "object") {
            Dialog.setOptions(this, arguments[0])
        }
    }
    if (!this.ID) {
        this.ID = topWin.Dialog._dialogArray.length + ""
    }
    instance = this
};
Dialog._dialogArray = [];
Dialog._childDialogArray = [];
Dialog._taskArray = [];
Dialog.bgDiv = null;
Dialog.setOptions = function(c, b) {
    if (!b) {
        return
    }
    for (var a in b) {
        c[a] = b[a]
    }
};
Dialog.attachBehaviors = function() {
    document.attachEvent("onkeydown", Dialog.onKeyDown);
    window.attachEvent("onresize",
    function() {
        Dialog.setBgDivSize();
        for (var c = 0,
        b = topWin.Dialog._dialogArray.length; c < b; c++) {
            var a = topWin.Dialog._dialogArray[c];
            if (a.ResizeResetPosition) {
                a.setPosition()
            }
        }
    });
    if (!HideScrollbar && ielt7) {
        window.attachEvent("onscroll", Dialog.resetPosition)
    }
};
Dialog.prototype.attachBehaviors = function() {
    var c = this;
    if (this.Drag && topWin.Drag) {
        var a = topWin.$id("_Draghandle_" + this.ID),
        b = topWin.$id("_DialogDiv_" + this.ID);
        topWin.Drag.init(a, b);
        b.onDragStart = function(h, g, f, d) {
            if (!isIE && c.URL) {
                topWin.$id("_Covering_" + c.ID).style.display = ""
            }
        };
        b.onDragEnd = function(i, h, f, d) {
            if (!isIE && c.URL) {
                topWin.$id("_Covering_" + c.ID).style.display = "none"
            }
            var g = $bodyDimensions(topWin);
            if (i < 0) {
                this.style.left = "0px"
            }
            if (i + this.clientWidth > g.clientWidth) {
                this.style.left = g.clientWidth - this.clientWidth + "px"
            }
            if (c.fixedPosition) {
                if (h < 0) {
                    this.style.top = "0px"
                }
                if (h + 33 > g.clientHeight) {
                    this.style.top = g.clientHeight - 33 + "px"
                }
            } else {
                if (h < g.scrollTop) {
                    this.style.top = g.scrollTop + "px"
                }
                if (h + 33 > g.scrollTop + g.clientHeight) {
                    this.style.top = g.scrollTop + g.clientHeight - 33 + "px"
                }
            }
        }
    }
};
Dialog.prototype.displacePath = function() {
    if (this.URL.substr(0, 7) == "http://" || this.URL.substr(0, 1) == "/" || this.URL.substr(0, 11) == "javascript:") {
        return this.URL
    } else {
        var a = this.URL;
        var b = window.location.href;
        b = b.substring(0, b.lastIndexOf("/"));
        while (a.indexOf("../") >= 0) {
            a = a.substring(3);
            b = b.substring(0, b.lastIndexOf("/"))
        }
        return b + "/" + a
    }
};
Dialog.prototype.setPosition = function() {
    var d = $bodyDimensions(topWin);
    var g = this.Top,
    b = this.Left,
    c = this.getDialogDiv();
    if (typeof this.Top == "string" && this.Top.indexOf("%") != -1) {
        var f = parseFloat(this.Top) * 0.01;
        g = this.fixedPosition ? d.clientHeight * f - c.scrollHeight * f: d.clientHeight * f - c.scrollHeight * f + d.scrollTop
    }
    if (typeof this.Left == "string" && this.Left.indexOf("%") != -1) {
        var a = parseFloat(this.Left) * 0.01;
        b = ielt7 ? d.clientWidth * a - c.scrollWidth * a + d.scrollLeft: d.clientWidth * a - c.scrollWidth * a
    }
    if (c) {
        c.style.top = Math.round(g) + "px";
        c.style.left = Math.round(b) + "px"
    }
};
Dialog.setBgDivSize = function() {
    var a = $bodyDimensions(topWin);
    if (Dialog.bgDiv) {
        if (ielt7) {
            Dialog.bgDiv.style.height = a.clientHeight + "px";
            Dialog.bgDiv.style.top = a.scrollTop + "px";
            Dialog.bgDiv.childNodes[0].style.display = "none";
            Dialog.bgDiv.childNodes[0].style.display = ""
        } else {
            Dialog.bgDiv.style.height = a.scrollHeight + "px"
        }
    }
};
Dialog.resetPosition = function() {
    Dialog.setBgDivSize();
    for (var b = 0,
    a = topWin.Dialog._dialogArray.length; b < a; b++) {
        topWin.Dialog._dialogArray[b].setPosition()
    }
};
Dialog.prototype.create = function() {
    var g = $bodyDimensions(topWin);
    if (typeof(this.OKEvent) == "function") {
        this.ShowButtonRow = true
    }
    if (!this.Width) {
        this.Width = Math.round(g.clientWidth * 4 / 10)
    }
    if (!this.Height) {
        this.Height = Math.round(this.Width / 2)
    }
    if (this.MessageTitle || this.Message) {
        this.ShowMessageRow = true
    }
    var b = this.Width + 13 + 13;
    var c = this.Height + 33 + 13 + (this.ShowButtonRow ? 40 : 0) + (this.ShowMessageRow ? 50 : 0);
    if (b > g.clientWidth) {
        this.Width = Math.round(g.clientWidth - 26)
    }
    if (c > g.clientHeight) {
        this.Height = Math.round(g.clientHeight - 46 - (this.ShowButtonRow ? 40 : 0) - (this.ShowMessageRow ? 50 : 0))
    }
    var f = "";
    if (this.Style == "normal") {
        f = ' <table id="_DialogTable_{thisID}" width="' + (this.Width + 26) + '" cellspacing="0" cellpadding="0" border="0" style="font-size:12px; line-height:1.4;border-collapse: collapse;"> <tr id="_Draghandle_{thisID}" onselectstart="return false;" style="-moz-user-select: -moz-none; ' + (this.Drag ? "cursor: move;": "") + '"> <td class="dialog_lt dialog_borderWidth"><div class="dialog_borderWidth"></div></td> <td class="dialog_ct" ><div class="dialog_title"><span id="_Title_{thisID}" class="' + (this.IconClass != "" ? this.IconClass: "") + '" style="float:left;' + (this.IconSrc != "" ? "background-repeat: no-repeat;background-position: 0 40%;padding-left:18px;display:inline-block;background-image:url(" + this.IconSrc + ")": "") + '">' + this.Title + '</span><input type="button" class="dialog_trans_icon"/></div> <div id="_ButtonClose_{thisID}" onclick="fixProgress();Dialog.getInstance(\'{thisID}\').cancelButton.onclick.apply(Dialog.getInstance(\'{thisID}\').cancelButton,[]);" title="关闭" class="dialog_closebtn" onmouseout="this.className=\'dialog_closebtn\'" onmouseover="this.className=\'dialog_closebtn_over\'" style=" ' + (this.ShowCloseButton ? "": "display:none;") + '"></div><div id="_ButtonMax_{thisID}" onclick="Dialog.getInstance(\'{thisID}\').max()" title="最大化" class="dialog_maxbtn" style="' + (this.ShowMaxButton ? "": "display:none;") + '"></div><div id="_ButtonMin_{thisID}" onclick="Dialog.getInstance(\'{thisID}\').minHandler()" title="最小化" class="dialog_minbtn" style="' + (this.ShowMinButton ? "": "display:none;") + '"></div></td> <td class="dialog_rt dialog_borderWidth"><div class="dialog_borderWidth"><a id="_forTab_{thisID}" href="#;"></a></div></td> </tr> <tr valign="top"> <td class="dialog_mlm dialog_borderWidth"></td> <td align="center"><table width="100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#ffffff"> <tr id="_MessageRow_{thisID}" style="' + (this.ShowMessageRow ? "": "display:none") + '"> <td valign="top" height="50"><table width="100%" cellspacing="0" cellpadding="0" border="0" class="dialog_bg" id="_MessageTable_{thisID}"> <tr> <td width="50" height="50" align="center"><input type="button" class="dialog_messageIcon" id="_MessageIcon_{thisID}"/></td> <td align="left" style="line-height: 16px;"><div id="_MessageTitle_{thisID}" style="font-weight:bold">' + this.MessageTitle + '</div> <div id="_Message_{thisID}">' + this.Message + '</div></td> </tr> </table></td> </tr> <tr> <td valign="top"><div id="_Container_{thisID}" style="text-align:left;position: relative; width: ' + this.Width + "px; height: " + this.Height + 'px;"> <div style="position: absolute; height: 100%; width: 100%; display: none; background-color:#fff; opacity: 0.5;" id="_Covering_{thisID}">&nbsp;</div> ' + (function(k) {
            if (k.InnerHtml) {
                return k.InnerHtml
            }
            if (k.URL) {
                return '<iframe width="100%" height="100%" frameborder="0" style="background-color:#ffffff;border:none 0;" id="_DialogFrame_' + k.ID + '" ' + (k.InnerFrameName ? 'name="' + k.InnerFrameName + '"': "") + ' src="' + k.displacePath() + '"></iframe>'
            }
            return ""
        })(this) + ' </div></td> </tr> <tr id="_ButtonRow_{thisID}" style="' + (this.ShowButtonRow ? "": "display:none") + '"> <td height="36"><div id="_DialogButtons_{thisID}" style="text-align:' + this.ButtonAlign + '; border-top: 1px solid #DADEE5; padding: 8px 20px;background-color:#f6f6f6;"> <input type="button" style="' + (this.ShowOkButton ? "": "display:none") + '" value="' + this.OkButtonText + '" id="_ButtonOK_{thisID}"/> <input type="button" style="' + (this.ShowCancelButton ? "": "display:none") + '" value="' + this.CancelButtonText + '" onclick="Dialog.getInstance(\'{thisID}\').close();" id="_ButtonCancel_{thisID}"/> </div></td> </tr> </table></td> <td class="dialog_mrm dialog_borderWidth"></td> </tr> <tr> <td class="dialog_lb dialog_borderWidth"></td> <td class="dialog_cb"></td> <td class="dialog_rb dialog_borderWidth"><a onfocus=\'$id("_forTab_{thisID}").focus();\' href="#;"></a></td> </tr> </table></div>'
    } else {
        if (this.Style == "simple") {
            f = ' <table id="_DialogTable_{thisID}" width="' + (this.Width + 26) + '" cellspacing="0" cellpadding="0" border="0" style="font-size:12px; line-height:1.4;border-collapse: collapse;"> <tr id="_Draghandle_{thisID}" onselectstart="return false;" style="-moz-user-select: -moz-none; ' + (this.Drag ? "cursor: move;": "") + '"> <td ><div class="dialog_sample_top"><div style="padding: 9px 0 0 4px; float: left; "><span id="_Title_{thisID}">' + this.Title + '</span></div> <div id="_ButtonClose_{thisID}" onclick="fixProgress();Dialog.getInstance(\'{thisID}\').cancelButton.onclick.apply(Dialog.getInstance(\'{thisID}\').cancelButton,[]);" class="dialog__simple_closebtn" style=" ' + (ielt7 ? "margin-top: 3px;": "") + (this.ShowCloseButton ? "": "display:none;") + '"></div></div></td> </tr> <tr valign="top"> <td align="center"><div class="dialog_sample_middle"><table width="100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#ffffff"> <tr id="_MessageRow_{thisID}" style="' + (this.ShowMessageRow ? "": "display:none") + '"> <td valign="top" height="50"><table width="100%" cellspacing="0" cellpadding="0" border="0" class="dialog_bg" id="_MessageTable_{thisID}"> <tr> <td width="50" height="50" align="center"><input type="button" class="dialog_messageIcon" id="_MessageIcon_{thisID}"/></td> <td align="left" style="line-height: 16px;"><div id="_MessageTitle_{thisID}" style="font-weight:bold">' + this.MessageTitle + '</div> <div id="_Message_{thisID}">' + this.Message + '</div></td> </tr> </table></td> </tr> <tr> <td valign="top"><div id="_Container_{thisID}" style="text-align:left;position: relative; width: ' + this.Width + "px; height: " + this.Height + 'px;"> <div style="position: absolute; height: 100%; width: 100%; display: none; background-color:#fff; opacity: 0.5;" id="_Covering_{thisID}">&nbsp;</div> ' + (function(k) {
                if (k.InnerHtml) {
                    return k.InnerHtml
                }
                if (k.URL) {
                    return '<iframe width="100%" height="100%" frameborder="0" style="background-color:#ffffff;border:none 0;" id="_DialogFrame_' + k.ID + '" ' + (k.InnerFrameName ? 'name="' + k.InnerFrameName + '"': "") + ' src="' + k.displacePath() + '"></iframe>'
                }
                return ""
            })(this) + ' </div></td> </tr> <tr id="_ButtonRow_{thisID}" style="' + (this.ShowButtonRow ? "": "display:none") + '"> <td height="36"><div id="_DialogButtons_{thisID}" style="border-top: 1px solid #DADEE5; padding: 8px 20px; text-align: right; background-color:#f6f6f6;"> <input type="button" style="' + (this.ShowOkButton ? "": "display:none") + '" value="' + this.OkButtonText + '" id="_ButtonOK_{thisID}"/> <input type="button" style="' + (this.ShowCancelButton ? "": "display:none") + '" value="' + this.CancelButtonText + '" onclick="Dialog.getInstance(\'{thisID}\').close();" id="_ButtonCancel_{thisID}"/> </div></td> </tr> </table></div></td> </tr> </table></div>'
        } else {
            if (this.Style == "simpleTip") {
                f = ' <table id="_DialogTable_{thisID}" width="' + (this.Width + 26) + '" cellspacing="0" cellpadding="0" border="0" style="font-size:12px; line-height:1.4;border-collapse: collapse;"> <tr id="_Draghandle_{thisID}" onselectstart="return false;" style="-moz-user-select: -moz-none; ' + (this.Drag ? "cursor: move;": "") + '"> <td ><div class="dialog_sample_top"><div style="padding: 9px 0 0 4px; float: left; "><span id="_Title_{thisID}">' + this.Title + '</span></div> <div id="_ButtonClose_{thisID}" onclick="fixProgress();Dialog.getInstance(\'{thisID}\').cancelButton.onclick.apply(Dialog.getInstance(\'{thisID}\').cancelButton,[]);" class="dialog__simple_closebtn" style=" ' + (ielt7 ? "margin-top: 3px;": "") + (this.ShowCloseButton ? "": "display:none;") + '"></div></div></td> </tr> <tr valign="top"> <td align="center"><div class="dialog_tip_middle"><table width="100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#ffffff"> <tr id="_MessageRow_{thisID}" style="' + (this.ShowMessageRow ? "": "display:none") + '"> <td valign="top" height="50"><table width="100%" cellspacing="0" cellpadding="0" border="0" class="dialog_bg" id="_MessageTable_{thisID}"> <tr> <td width="50" height="50" align="center"><input type="button" class="dialog_messageIcon" id="_MessageIcon_{thisID}"/></td> <td align="left" style="line-height: 16px;"><div id="_MessageTitle_{thisID}" style="font-weight:bold">' + this.MessageTitle + '</div> <div id="_Message_{thisID}">' + this.Message + '</div></td> </tr> </table></td> </tr> <tr> <td valign="top"><div id="_Container_{thisID}" style="text-align:left;position: relative; width: ' + this.Width + "px; height: " + this.Height + 'px;"> <div style="position: absolute; height: 100%; width: 100%; display: none; background-color:#fff; opacity: 0.5;" id="_Covering_{thisID}">&nbsp;</div> ' + (function(k) {
                    if (k.InnerHtml) {
                        return k.InnerHtml
                    }
                    if (k.URL) {
                        return '<iframe width="100%" height="100%" frameborder="0" style="background-color:#ffffff;border:none 0;" id="_DialogFrame_' + k.ID + '" ' + (k.InnerFrameName ? 'name="' + k.InnerFrameName + '"': "") + ' src="' + k.displacePath() + '"></iframe>'
                    }
                    return ""
                })(this) + ' </div></td> </tr> <tr id="_ButtonRow_{thisID}" style="' + (this.ShowButtonRow ? "": "display:none") + '"> <td height="36"><div id="_DialogButtons_{thisID}" style="border-top: 1px solid #DADEE5; padding: 8px 20px; text-align: right; background-color:#f6f6f6;"> <input type="button" style="' + (this.ShowOkButton ? "": "display:none") + '" value="' + this.OkButtonText + '" id="_ButtonOK_{thisID}"/> <input type="button" style="' + (this.ShowCancelButton ? "": "display:none") + '" value="' + this.CancelButtonText + '" onclick="Dialog.getInstance(\'{thisID}\').close();" id="_ButtonCancel_{thisID}"/> </div></td> </tr> </table></div></td> </tr> <tr> <td><div class="dialog_tip_bottom"><div class="dialog_tip_bottomArr"></div></div></td> </tr> </table></div>'
            } else {
                if (this.Style == "shadowTip") {
                    var i = this.Height + 95;
                    f = ' <table><tr><td><div class="dialog_shadow_content"><table id="_DialogTable_{thisID}" width="' + (this.Width) + '" cellspacing="0" cellpadding="0" border="0" style="font-size:12px; line-height:1.4;border-collapse: collapse;"> <tr id="_Draghandle_{thisID}" onselectstart="return false;" style="-moz-user-select: -moz-none; ' + (this.Drag ? "cursor: move;": "") + '"> <td><div class="dialog_shadow_content_top"><div style="padding: 9px 0 0 4px; float: left; "><span id="_Title_{thisID}">' + this.Title + '</span></div> <div id="_ButtonClose_{thisID}" onclick="fixProgress();Dialog.getInstance(\'{thisID}\').cancelButton.onclick.apply(Dialog.getInstance(\'{thisID}\').cancelButton,[]);" class="dialog__simple_closebtn" style=" ' + (ielt7 ? "margin-top: 3px;": "") + (this.ShowCloseButton ? "": "display:none;") + '"></div></div></td> </tr> <tr valign="top"> <td align="center"><table width="100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#ffffff"> <tr id="_MessageRow_{thisID}" style="' + (this.ShowMessageRow ? "": "display:none") + '"> <td valign="top" height="50"><table width="100%" cellspacing="0" cellpadding="0" border="0" class="dialog_bg" id="_MessageTable_{thisID}"> <tr> <td width="50" height="50" align="center"><input type="button" class="dialog_messageIcon" id="_MessageIcon_{thisID}"/></td> <td align="left" style="line-height: 16px;"><div id="_MessageTitle_{thisID}" style="font-weight:bold">' + this.MessageTitle + '</div> <div id="_Message_{thisID}">' + this.Message + '</div></td> </tr> </table></td> </tr> <tr> <td valign="top"><div id="_Container_{thisID}" style="text-align:left;position: relative; width: ' + (this.Width) + "px; height: " + this.Height + 'px;"> <div style="position: absolute; height: 100%; width: 100%; display: none; background-color:#fff; opacity: 0.5;" id="_Covering_{thisID}">&nbsp;</div> ' + (function(k) {
                        if (k.InnerHtml) {
                            return k.InnerHtml
                        }
                        if (k.URL) {
                            return '<iframe width="100%" height="100%" frameborder="0" style="background-color:#ffffff;border:none 0;" id="_DialogFrame_' + k.ID + '" ' + (k.InnerFrameName ? 'name="' + k.InnerFrameName + '"': "") + ' src="' + k.displacePath() + '"></iframe>'
                        }
                        return ""
                    })(this) + ' </div></td> </tr> <tr id="_ButtonRow_{thisID}" style="' + (this.ShowButtonRow ? "": "display:none") + '"> <td height="36"><div id="_DialogButtons_{thisID}" style="border-top: 1px solid #DADEE5; padding: 8px 20px; text-align: right; background-color:#f6f6f6;"> <input type="button" style="' + (this.ShowOkButton ? "": "display:none") + '" value="' + this.OkButtonText + '" id="_ButtonOK_{thisID}"/> <input type="button" style="' + (this.ShowCancelButton ? "": "display:none") + '" value="' + this.CancelButtonText + '" onclick="Dialog.getInstance(\'{thisID}\').close();" id="_ButtonCancel_{thisID}"/> </div></td> </tr> </table></td> </tr> </table> </div><div><table cellpadding="0" cellspacing="0" width="' + (this.Width + 120) + '" height="' + i + '"><tr><td class="dialog_shadow_lt">&nbsp;</td><td class="dialog_shadow_ct">&nbsp;</td><td class="dialog_shadow_rt">&nbsp;</td></tr> <tr><td class="dialog_shadow_lm" height="' + (this.Height + 95 - 39 - 130) + '">&nbsp;</td><td class="dialog_shadow_cm">&nbsp;</td><td class="dialog_shadow_rm">&nbsp;</td></tr> <tr><td class="dialog_shadow_lb">&nbsp;</td><td class="dialog_shadow_cb">&nbsp;</td><td class="dialog_shadow_rb">&nbsp;</td></tr></table> </div></td></tr></table></div>'
                } else {
                    if (this.Style == "custom") {
                        f = ' <table id="_DialogTable_{thisID}" width="' + (this.Width + 26) + '" cellspacing="0" cellpadding="0" border="0" style="font-size:12px; line-height:1.4;border-collapse: collapse;"> <tr id="_Draghandle_{thisID}" style="display:none;" onselectstart="return false;" style="-moz-user-select: -moz-none; ' + (this.Drag ? "cursor: move;": "") + '"> </tr> <tr> <td valign="top"><div id="_Container_{thisID}" style="text-align:left;position: relative; width: ' + this.Width + "px; height: " + this.Height + 'px;"> <div style="position: absolute; height: 100%; width: 100%; display: none; background-color:#fff; opacity: 0.5;" id="_Covering_{thisID}">&nbsp;</div> ' + (function(k) {
                            if (k.InnerHtml) {
                                return k.InnerHtml
                            }
                            if (k.URL) {
                                return '<iframe width="100%" height="100%" frameborder="0" style="border:none 0;" id="_DialogFrame_' + k.ID + '" ' + (k.InnerFrameName ? 'name="' + k.InnerFrameName + '"': "") + ' src="' + k.displacePath() + '"></iframe>'
                            }
                            return ""
                        })(this) + ' </div></td> </tr> <tr id="_ButtonRow_{thisID}" style="display:none;" > <td height="36"><div id="_DialogButtons_{thisID}" style="border-top: 1px solid #DADEE5; padding: 8px 20px; text-align: right; background-color:#f6f6f6;"> <input type="button" style="' + (this.ShowOkButton ? "": "display:none") + '" value="' + this.OkButtonText + '" id="_ButtonOK_{thisID}"/> <input type="button" style="' + (this.ShowCancelButton ? "": "display:none") + '" value="' + this.CancelButtonText + '" onclick="Dialog.getInstance(\'{thisID}\').close();" id="_ButtonCancel_{thisID}"/> </div></td> </tr> </table></div></td> </tr> </table></div>'
                    }
                }
            }
        }
    }
    f = f.replace(/\{IMAGESPATH\}/gm, IMAGESPATH).replace(/\{thisID\}/gm, this.ID);
    var a = topWin.$id("_DialogDiv_" + this.ID);
    if (!a) {
        a = topDoc.createElement("div");
        a.id = "_DialogDiv_" + this.ID;
        topDoc.getElementsByTagName("BODY")[0].appendChild(a)
    }
    if (isIE && topDoc.compatMode == "BackCompat" || ielt7) {
        a.style.position = "absolute";
        this.fixedPosition = false
    } else {
        a.style.position = "fixed";
        this.fixedPosition = true
    }
    a.style.left = "-9999px";
    a.style.top = "-9999px";
    a.innerHTML = f;
    $(a).attr("AllowChangeIndex", this.AllowChangeIndex);
    $(a).attr("MinToTask", this.MinToTask);
    $(a).attr("taskId", "task_" + this.ID);
    $(a).bind("click",
    function() {
        if ($(this).attr("AllowChangeIndex") == true || $(this).attr("AllowChangeIndex") == "true") {
            maxIndex = maxIndex + topWin.Dialog._dialogArray.length + 2;
            $(this)[0].style.zIndex = maxIndex;
            if ($(this).attr("MinToTask") == true || $(this).attr("MinToTask") == "true") {
                var l = topWin.Dialog._taskArray;
                var k = getArrayPosition($(this).attr("taskId"), l);
                if (k != -1) {
                    l.splice(k, 1);
                    l.push($(this).attr("taskId"))
                }
                var n = getTaskTarget();
                if (n != "") {
                    var m = $("#" + n);
                    setTaskCurrent(m, l)
                }
            }
        }
    });
    if (this.InvokeElementId) {
        var d = $id(this.InvokeElementId);
        d.style.position = "";
        d.style.display = "";
        if (isIE) {
            var h = topDoc.createElement("div");
            h.innerHTML = d.outerHTML;
            d.outerHTML = "";
            topWin.$id("_Covering_" + this.ID).parentNode.appendChild(h)
        } else {
            topWin.$id("_Covering_" + this.ID).parentNode.appendChild(d)
        }
    }
    this.openerWindow = window;
    if (window.ownerDialog) {
        this.openerDialog = window.ownerDialog
    }
    if (this.URL) {
        if (topWin.$id("_DialogFrame_" + this.ID)) {
            this.innerFrame = topWin.$id("_DialogFrame_" + this.ID)
        }
        var j = this;
        this.innerFrameOnload = function() {
            j.innerWin = j.innerFrame.contentWindow;
            try {
                j.innerWin.ownerDialog = j;
                j.innerDoc = j.innerWin.document;
                if (j.Title == "　" && j.innerDoc && j.innerDoc.title) {
                    if (j.innerDoc.title) {
                        topWin.$id("_Title_" + j.ID).innerHTML = j.innerDoc.title
                    }
                }
            } catch(k) {
                if (window.console && window.console.log) {
                    console.log("可能存在访问限制，不能获取到浮动窗口中的文档对象。")
                }
                j.unauthorized = true
            }
            if (typeof(j.OnLoad) == "function") {
                j.OnLoad()
            }
        };
        if (!isGecko) {
            this.innerFrame.attachEvent("onreadystatechange",
            function() {
                if ((/loaded|complete/).test(j.innerFrame.readyState)) {
                    j.innerFrameOnload()
                }
            })
        } else {
            this.innerFrame.onload = j.innerFrameOnload
        }
    }
    topWin.$id("_DialogDiv_" + this.ID).dialogId = this.ID;
    topWin.$id("_DialogDiv_" + this.ID).dialogInstance = this;
    this.attachBehaviors();
    this.okButton = topWin.$id("_ButtonOK_" + this.ID);
    this.cancelButton = topWin.$id("_ButtonCancel_" + this.ID);
    this.maxButton = topWin.$id("_ButtonMax_" + this.ID);
    this.minButton = topWin.$id("_ButtonMin_" + this.ID);
    var _temp = this;
    $(a).find("input[type='button']").each(function() {
        if (!$(this).attr("class")) {
            $(this).addClass("button");
            if(_temp.ButtonClass){
            	 $(this).addClass(_temp.ButtonClass);
            }
            $(this).hover(function() {
                $(this).addClass("button_hover")
            },
            function() {
                $(this).removeClass("button_hover")
            })
        }
    });
    if ($(a).find(".progressBar").length == 0) {
        if ($(a).find(".button").parents("tr")[0].style.display == "none") {
            $(a).find(".icon_dialog").eq(0).focus()
        } else {
            $(a).find(".button").eq(0).focus()
        }
    }
    a = null
};
Dialog.prototype.setSize = function(a, b) {
    if (a && +a > 20) {
        this.Width = +a;
        topWin.$id("_DialogTable_" + this.ID).width = this.Width + 26;
        topWin.$id("_Container_" + this.ID).style.width = this.Width + "px"
    }
    if (b && +b > 10) {
        this.Height = +b;
        topWin.$id("_Container_" + this.ID).style.height = this.Height + "px"
    }
    this.setPosition()
};
Dialog.prototype.max = function() {
    var d = $("#_DialogDiv_" + this.ID);
    var c = $("#_DialogTable_" + this.ID).find(">tbody").find(">tr").eq(1);
    if (c[0].style.display == "none") {
        c.show();
        $("#_Draghandle_" + this.ID).find(".dialog_minbtn").removeClass("dialog_decreasebtn");
        $("#_Draghandle_" + this.ID).find(".dialog_minbtn").attr("title", "最小化")
    }
    if (this.maxFlag == true) {
        topWin.$id("_DialogTable_" + this.ID).style.width = this.Width + 26;
        topWin.$id("_Container_" + this.ID).style.width = this.Width + "px";
        topWin.$id("_Container_" + this.ID).style.height = this.Height + "px";
        $("#_Draghandle_" + this.ID).find(".dialog_maxbtn").removeClass("dialog_decreasebtn");
        $("#_Draghandle_" + this.ID).find(".dialog_maxbtn").attr("title", "最大化");
        this.maxFlag = false;
        if (this.DecreaseResetPosition) {
            this.setPosition()
        } else {
            d[0].style.left = d.attr("oldleft");
            d[0].style.top = d.attr("oldtop")
        }
    } else {
        d.attr("oldleft", d[0].style.left);
        d.attr("oldtop", d[0].style.top);
        var b = window.document.documentElement.clientHeight;
        var a = window.document.documentElement.clientWidth;
        topWin.$id("_DialogTable_" + this.ID).style.width = a;
        topWin.$id("_Container_" + this.ID).style.width = a - 26 + "px";
        if ($("#_ButtonRow_" + this.ID)[0].style.display == "none") {
            topWin.$id("_Container_" + this.ID).style.height = b - 46 + "px"
        } else {
            topWin.$id("_Container_" + this.ID).style.height = b - 46 - $("#_ButtonRow_" + this.ID).height() + "px"
        }
        $("#_Draghandle_" + this.ID).find(".dialog_maxbtn").addClass("dialog_decreasebtn");
        $("#_Draghandle_" + this.ID).find(".dialog_maxbtn").attr("title", "还原");
        if (this.MinToTask == true || this.MinToTask == "true") {
            this.Left = 0;
            this.Top = 0
        }
        this.maxFlag = true;
        this.setPosition()
    }
};
Dialog.prototype.minHandler = function() {
    if (this.MinToTask == true || this.MinToTask == "true") {
        this.minToTask()
    } else {
        this.min()
    }
};
Dialog.prototype.min = function() {
    var a = $("#_DialogTable_" + this.ID).find(">tbody").find(">tr").eq(1);
    if (a[0].style.display == "none") {
        topWin.$id("_DialogTable_" + this.ID).width = this.Width + 26;
        topWin.$id("_Container_" + this.ID).style.width = this.Width + "px";
        topWin.$id("_Container_" + this.ID).style.height = this.Height + "px";
        $("#_Draghandle_" + this.ID).find(".dialog_minbtn").removeClass("dialog_decreasebtn");
        $("#_Draghandle_" + this.ID).find(".dialog_minbtn").attr("title", "最小化");
        a.show();
        if (this.DecreaseResetPosition) {
            this.setPosition()
        }
        this.minFlag = false
    } else {
        topWin.$id("_DialogTable_" + this.ID).style.width = 200 + 26 + "px";
        topWin.$id("_Container_" + this.ID).style.width = 200 + "px";
        $("#_Draghandle_" + this.ID).find(".dialog_minbtn").addClass("dialog_decreasebtn");
        $("#_Draghandle_" + this.ID).find(".dialog_minbtn").attr("title", "还原");
        a.hide();
        this.minFlag = true
    }
    this.maxFlag = false
};
Dialog.prototype.minToTask = function() {
    var c = $("#_DialogDiv_" + this.ID);
    var i = getTaskTarget();
    var g = this.ID;
    if (i != "") {
        var f = $("#" + i);
        var j = c[0].style.left;
        var b = c[0].style.top;
        var d = c.width();
        var h = c.height();
        var a = $('<div class="dialogMinEffect"/>');
        a.css({
            width: d,
            height: h,
            left: j,
            top: b
        }).animate({
            width: 0,
            height: 0,
            left: f.find("#task_" + this.ID).offset().left + "px",
            top: top.window.document.documentElement.clientHeight + "px",
            opacity: 0
        },
        200,
        function() {
            a.remove();
            var l = topWin.Dialog._taskArray;
            var k = getArrayPosition("task_" + g, l);
            if (k != -1) {
                l.splice(k, 1)
            }
            setTaskCurrent(f, l)
        })
    }
    c.hide();
    $("body").append(a)
};
Dialog.prototype.show = function() {
    var j = topWin.$id("_DialogDiv_" + this.ID);
    if (j) {
        maxIndex = maxIndex + topWin.Dialog._dialogArray.length + 2;
        $(j)[0].style.zIndex = maxIndex;
        if (this.MinToTask == true || this.MinToTask == "true") {
            var f = topWin.Dialog._taskArray;
            var u = $(j)[0].style.left;
            var d = $(j)[0].style.top;
            var i = $(j).width();
            var t = $(j).height();
            var s = $('<div class="dialogMinEffect"/>');
            var c = getArrayPosition("task_" + this.ID, f);
            if (c != -1) {
                f.splice(c, 1);
                f.push("task_" + this.ID)
            } else {
                f.push("task_" + this.ID)
            }
            var o = getTaskTarget();
            var p;
            if (o != "") {
                p = $("#" + o);
                setTaskCurrent(p, f)
            }
            if ($(j)[0].style.display == "none") {
                s.css({
                    width: 0,
                    height: 0,
                    left: p.find("#task_" + this.ID).offset().left + "px",
                    top: top.window.document.documentElement.clientHeight + "px"
                }).animate({
                    width: i,
                    height: t,
                    left: u,
                    top: d
                },
                200,
                function() {
                    $(j).show();
                    s.remove()
                });
                $("body").append(s)
            } else {
                if (this.ID != f[f.length - 1]) {}
            }
        }
        return
    }
    this.create();
    var g = this.getBgdiv(),
    r = this.getDialogDiv();
    r.style.zIndex = this.zindex = parseInt(Dialog.bgDiv.style.zIndex) + 1;
    if (topWin.Dialog._dialogArray.length > 0) {
        maxIndex = maxIndex + topWin.Dialog._dialogArray.length + 2;
        r.style.zIndex = maxIndex
    } else {
        g.style.display = "none";
        if (HideScrollbar) {
            var k = topDoc.getElementsByTagName(topDoc.compatMode == "BackCompat" ? "BODY": "HTML")[0];
            k.styleOverflow = k.style.overflow;
            if (window.navigator.userAgent.indexOf("Firefox/3.6") != -1) {
                var v = k.scrollTop;
                k.style.overflow = "hidden";
                k.scrollTop = v
            } else {
                k.style.overflow = "hidden"
            }
        }
    }
    topWin.Dialog._dialogArray.push(this);
    Dialog._childDialogArray.push(this);
    if (Dialog._childDialogArray.length == 1) {
        if (window.ownerDialog) {
            ownerDialog.hiddenCloseButton()
        }
    }
    if (this.CancelEvent) {
        this.cancelButton.onclick = this.CancelEvent
    }
    var q = this;
    if (this.OKEvent) {
        this.okButton.onclick = this.OKEvent
    }
    if (this.maxButton) {
        this.maxButton.onclick = function() {
            if (q.maxFlag) {
                if (q.DecreaseEvent) {
                    $(q.DecreaseEvent)
                }
            } else {
                if (q.MaxEvent) {
                    $(q.MaxEvent)
                }
            }
            q.max()
        }
    }
    if (this.minButton) {
        this.minButton.onclick = function() {
            if (q.minFlag) {
                if (q.DecreaseEvent) {
                    $(q.DecreaseEvent)
                }
            } else {
                if (q.MinEvent) {
                    $(q.MinEvent)
                }
            }
            q.minHandler()
        }
    }
    if (this.AutoClose && this.AutoClose > 0) {
        this.autoClose()
    }
    this.opened = true;
    this.setPosition();
    if (this.MinToTask == true || this.MinToTask == "true") {
        this.Modal = false;
        this.ResizeResetPosition = false;
        this.DecreaseResetPosition = false;
        this.AllowChangeIndex = true;
        var l = $("#_DialogDiv_" + this.ID);
        var b = getTaskTarget();
        if (b != "") {
            var n = $("#" + b);
            if (n[0]) {
                if (n[0].style.display == "none") {
                    n.fadeIn(500);
                    $("body").trigger("dialogTaskShow")
                }
            }
            var a = $('<a class="dialogTaskItem"><span keepDefaultStyle="true" class="dialogTaskItemRight"><span class="dialogTaskItemIcon"></span></span></a>');
            a.attr("id", "task_" + this.ID);
            var m = a.find(".dialogTaskItemIcon");
            m.text(this.Title);
            m.attr("title", this.Title);
            if (this.IconSrc != "") {
                m.css({
                    paddingLeft: "18px",
                    backgroundPositon: "0 40%",
                    backgroundRepeat: "no-repeat",
                    backgroundImage: "url(" + this.IconSrc + ")"
                })
            } else {
                if (this.IconClass != "") {
                    m.addClass(this.IconClass)
                }
            }
            n.find(".taskItemContainer").append(a);
            itemTotalWidth = itemTotalWidth + a.width() + 8;
            resetDialogTask();
            var h = topWin.Dialog._taskArray;
            if (getArrayPosition("task_" + this.ID, h) == -1) {
                h.push("task_" + this.ID)
            }
            this.Left = l.offset().left + h.length * 20;
            this.Top = l.offset().top + h.length * 20;
            setTaskCurrent(n, h);
            a.bind("click",
            function() {
                var C = topWin.Dialog._taskArray;
                var x = getArrayPosition($(this).attr("id"), C);
                var w = l[0].style.left;
                var A = l[0].style.top;
                var B = l.width();
                var z = l.height();
                var y = $('<div class="dialogMinEffect"/>');
                if (l[0].style.display == "none") {
                    maxIndex = maxIndex + topWin.Dialog._dialogArray.length + 2;
                    l[0].style.zIndex = maxIndex;
                    if (x == -1) {
                        C.push($(this).attr("id"))
                    } else {
                        C.splice(x, 1);
                        C.push($(this).attr("id"))
                    }
                    y.css({
                        width: 0,
                        height: 0,
                        left: $(this).offset().left + "px",
                        top: top.window.document.documentElement.clientHeight + "px"
                    }).animate({
                        width: B,
                        height: z,
                        left: w,
                        top: A
                    },
                    200,
                    function() {
                        l.show();
                        y.remove()
                    });
                    $("body").append(y);
                    setTaskCurrent(n, C)
                } else {
                    if ($(this).attr("id") != C[C.length - 1]) {
                        maxIndex = maxIndex + topWin.Dialog._dialogArray.length + 2;
                        l[0].style.zIndex = maxIndex;
                        if (x == -1) {
                            C.push($(this).attr("id"))
                        } else {
                            C.splice(x, 1);
                            C.push($(this).attr("id"))
                        }
                        setTaskCurrent(n, C)
                    } else {
                        y.css({
                            width: B,
                            height: z,
                            left: w,
                            top: A
                        }).animate({
                            width: 0,
                            height: 0,
                            left: $(this).offset().left + "px",
                            top: top.window.document.documentElement.clientHeight + "px",
                            opacity: 0
                        },
                        200,
                        function() {
                            y.remove();
                            setTaskCurrent(n, C)
                        });
                        $("body").append(y);
                        l.hide();
                        C.pop($(this).attr("id"))
                    }
                }
            })
        }
        this.setPosition()
    }
    if (this.Modal) {
        g.style.zIndex = r.style.zIndex - 1;
        Dialog.setBgDivSize();
        g.style.display = ""
    }
    g = null
};
Dialog.prototype.close = function() {
    if (this.unauthorized == false) {
        if (this.innerWin && this.innerWin.Dialog && this.innerWin.Dialog._childDialogArray.length > 0) {
            return
        }
    }
    var n = this.getDialogDiv();
    if (this == topWin.Dialog._dialogArray[topWin.Dialog._dialogArray.length - 1]) {
        var k = topWin.Dialog._dialogArray.pop()
    } else {
        removeWinArray(topWin.Dialog._dialogArray, this)
    }
    removeWinArray(Dialog._childDialogArray, this);
    if (Dialog._childDialogArray.length == 0) {
        if (window.ownerDialog) {
            ownerDialog.showCloseButton()
        }
    }
    if (this.InvokeElementId) {
        var h = topWin.$id(this.InvokeElementId);
        h.style.display = "none";
        if (isIE) {
            var j = document.createElement("div");
            j.innerHTML = h.outerHTML;
            h.outerHTML = "";
            document.getElementsByTagName("BODY")[0].appendChild(j)
        } else {
            document.getElementsByTagName("BODY")[0].appendChild(h)
        }
    }
    if (topWin.Dialog._dialogArray.length > 0) {
        if (this.Modal && k) {
            var i = topWin.Dialog._dialogArray.length;
            var c = true;
            while (i) {--i;
                if (topWin.Dialog._dialogArray[i].Modal) {
                    Dialog.bgDiv.style.zIndex = topWin.Dialog._dialogArray[i].zindex - 1;
                    c = false;
                    break
                }
            }
            if (c) {
                Dialog.bgDiv.style.display = "none"
            }
        }
    } else {
        Dialog.bgDiv.style.zIndex = "900";
        Dialog.bgDiv.style.display = "none";
        if (HideScrollbar) {
            var d = topDoc.getElementsByTagName(topDoc.compatMode == "BackCompat" ? "BODY": "HTML")[0];
            if (d.styleOverflow != undefined) {
                if (window.navigator.userAgent.indexOf("Firefox/3.6") != -1) {
                    var b = d.scrollTop;
                    if (this.CloseHideScroller) {
                        d.style.overflow = "hidden"
                    } else {
                        d.style.overflow = d.styleOverflow
                    }
                    d.scrollTop = b
                } else {
                    if (this.CloseHideScroller) {
                        d.style.overflow = "hidden"
                    } else {
                        d.style.overflow = d.styleOverflow
                    }
                }
            }
        }
    }
    this.openerWindow.focus();
    if (isIE && !isIE8) {
        n.dialogInstance = null;
        if (this.CancelEvent) {
            this.cancelButton.onclick = null
        }
        if (this.OKEvent) {
            this.okButton.onclick = null
        }
        topWin.$id("_DialogDiv_" + this.ID).onDragStart = null;
        topWin.$id("_DialogDiv_" + this.ID).onDragEnd = null;
        topWin.$id("_Draghandle_" + this.ID).onmousedown = null;
        topWin.$id("_Draghandle_" + this.ID).root = null;
        n.outerHTML = "";
        CollectGarbage()
    } else {
        var o = topWin.$id("_RycDiv");
        if (!o) {
            o = topDoc.createElement("div");
            o.id = "_RycDiv"
        }
        o.appendChild(n);
        o.innerHTML = "";
        o = null
    }
    this.innerFrame = null;
    this.bgDiv = null;
    n = null;
    this.closed = true;
    if (this.MinToTask == true || this.MinToTask == "true") {
        var g = topWin.Dialog._taskArray;
        var m = getArrayPosition("task_" + this.ID, g);
        if (m != -1) {
            g.splice(m, 1)
        }
        var l = getTaskTarget();
        if (l != "") {
            var f = $("#" + l);
            var a = f.find("#task_" + this.ID);
            itemTotalWidth = itemTotalWidth - a.width() - 8;
            resetDialogTask();
            a.remove();
            setTaskCurrent(f, g);
            if (f.find(".taskItemContainer").html() == "") {
                f.fadeOut(500);
                $("body").trigger("dialogTaskHide")
            }
        }
    }
};
Dialog.prototype.autoClose = function() {
    if (this.closed) {
        clearTimeout(this._closeTimeoutId);
        return
    }
    this.AutoClose -= 1;
    topWin.$id("_Title_" + this.ID).innerHTML = this.AutoClose + " 秒后自动关闭";
    if (this.AutoClose <= 0) {
        this.close()
    } else {
        var a = this;
        this._closeTimeoutId = setTimeout(function() {
            a.autoClose()
        },
        1000)
    }
};
Dialog.getInstance = function(b) {
    var a = topWin.$id("_DialogDiv_" + b);
    if (!a) {
        alert("没有取到对应ID的弹出框页面对象")
    }
    try {
        return a.dialogInstance
    } finally {
        a = null
    }
};
Dialog.prototype.addButton = function(f, a, d) {
    topWin.$id("_ButtonRow_" + this.ID).style.display = "";
    this.ShowButtonRow = true;
    var c = topDoc.createElement("input");
    c.id = "_Button_" + this.ID + "_" + f;
    c.type = "button";
    c.style.cssText = "margin-right:5px";
    c.value = a;
    c.onclick = d;
    var b = topWin.$id("_DialogButtons_" + this.ID).getElementsByTagName("INPUT")[0];
    b.parentNode.insertBefore(c, b);
    var _temp=this;
    $(c).each(function() {
        $(this).addClass("button");
        if(_temp.ButtonClass){
       	 $(this).addClass(_temp.ButtonClass);
       }
        $(this).hover(function() {
            $(this).addClass("button_hover")
        },
        function() {
            $(this).removeClass("button_hover")
        })
    });
    return c
};
Dialog.prototype.removeButton = function(b) {
    var a = topWin.$id("_DialogButtons_" + this.ID).getElementsByTagName("INPUT")[0];
    a.parentNode.removeChild(b)
};

Dialog.prototype.disableOkButton = function(b) {
	var a = topWin.$id("_ButtonOK_" + this.ID);
    if (a) {
        a.disabled = true;
    }
};
Dialog.prototype.unDisableOkButton = function(b) {
	var a = topWin.$id("_ButtonOK_" + this.ID);
    if (a) {
        a.disabled = false;
    }
};
Dialog.prototype.hiddenCloseButton = function(b) {
    var a = topWin.$id("_ButtonClose_" + this.ID);
    if (a) {
        a.style.display = "none"
    }
};
Dialog.prototype.showCloseButton = function(b) {
    var a = topWin.$id("_ButtonClose_" + this.ID);
    if (a) {
        a.style.display = ""
    }
};
Dialog.prototype.getBgdiv = function() {
    var h = this.Alpha;
    var g = String(this.Alpha / 100);
    if (Dialog.bgDiv) {
        if (this.Modal) {
            document.getElementById("_DialogBGMask").style.opacity = g;
            document.getElementById("_DialogBGMask").style.filter = "alpha(opacity=" + h + ")";
            document.getElementById("_DialogBGMask").style.backgroundColor = this.Bgcolor
        }
        return Dialog.bgDiv
    }
    var d = topWin.$id("_DialogBGDiv");
    if (!d) {
        d = topDoc.createElement("div");
        d.id = "_DialogBGDiv";
        d.style.cssText = "position:absolute;left:0px;top:0px;width:100%;height:100%;z-index:900";
        var a = '<div style="position:relative;width:100%;height:100%;">';
        var c = '<div id="_DialogBGMask" style="position:absolute;width:100%;height:100%;"></div>';
        var f = ielt7 ? '<iframe src="about:blank" style="filter:alpha(opacity=0);" width="100%" height="100%"></iframe>': "";
        d.innerHTML = a + c + f + "</div>";
        topDoc.getElementsByTagName("BODY")[0].appendChild(d);
        document.getElementById("_DialogBGMask").style.opacity = g;
        document.getElementById("_DialogBGMask").style.filter = "alpha(opacity=" + h + ")";
        document.getElementById("_DialogBGMask").style.backgroundColor = this.Bgcolor;
        if (ielt7) {
            var b = d.getElementsByTagName("IFRAME")[0].contentWindow.document;
            b.open();
            b.write("<body style='background-color:#333' oncontextmenu='return false;'></body>");
            b.close();
            b = null
        }
    }
    Dialog.bgDiv = d;
    d = null;
    return Dialog.bgDiv
};
Dialog.prototype.getDialogDiv = function() {
    var a = topWin.$id("_DialogDiv_" + this.ID);
    try {
        return a
    } finally {
        a = null
    }
};
Dialog.onKeyDown = function(a) {
    var a = window.event || a;
    if ((a.shiftKey && a.keyCode == 9) || a.keyCode == 8) {
        if (topWin.Dialog._dialogArray.length > 0) {
            var c = a.srcElement || a.target;
            if (c.tagName != "INPUT" && c.tagName != "TEXTAREA") {
                stopEvent(a);
                return false
            }
        }
    }
    if (a.keyCode == 27) {
        var b = topWin.Dialog._dialogArray[topWin.Dialog._dialogArray.length - 1];
        if (b.ShowCloseButton) {
            Dialog.close()
        }
    }
};
Dialog.close = function(b) {
    if (topWin.Dialog._dialogArray.length > 0) {
        var a = topWin.Dialog._dialogArray[topWin.Dialog._dialogArray.length - 1];
        if (a.MsgForESC) {
            Dialog.confirm(a.MsgForESC,
            function() {
                a.cancelButton.onclick.apply(a.cancelButton, [])
            })
        } else {
            a.cancelButton.onclick.apply(a.cancelButton, [])
        }
    }
};
Dialog.showDialogCloseButton = function(b) {
    if (topWin.Dialog._dialogArray.length > 0) {
        var a = topWin.Dialog._dialogArray[topWin.Dialog._dialogArray.length - 1];
        if (a) {
            a.showCloseButton();
        }
    }
};
Dialog.disableDialogOkButton = function(b) {
    if (topWin.Dialog._dialogArray.length > 0) {
        var a = topWin.Dialog._dialogArray[topWin.Dialog._dialogArray.length - 1];
        if (a) {
            a.disableOkButton();
        }
    }
};
Dialog.unDisableDialogOkButton = function(b) {
    if (topWin.Dialog._dialogArray.length > 0) {
        var a = topWin.Dialog._dialogArray[topWin.Dialog._dialogArray.length - 1];
        if (a) {
            a.unDisableOkButton();
        }
    }
};
Dialog.alert = function(i, f, b, d, c) {
    var b = b || 300,
    d = d || 110;
    var g = new Dialog({
        Width: b,
        Height: d
    });
    g.ShowButtonRow = true;
    g.CancelEvent = function() {
        g.close();
        if (f) {
            f()
        }
    };
    if (i) {
        var a = i.split("|");
        if (a.length > 1) {
            g.Title = a[1]
        } else {
            g.Title = "系统提示"
        }
        g.InnerHtml = '<table height="100%" border="0" align="center" cellpadding="10" cellspacing="0"> <tr><td align="right"><input type="button" id="Icon_' + this.ID + '" class="icon_alert" align="absmiddle"></td> <td align="left" id="Message_' + this.ID + '" style="font-size:9pt">' + a[0] + "</td></tr> </table>"
    } else {
        g.Title = "系统提示";
        g.InnerHtml = '<table height="100%" border="0" align="center" cellpadding="10" cellspacing="0"> <tr><td align="right"><input type="button" id="Icon_' + this.ID + '" class="icon_alert" align="absmiddle"></td> <td align="left" id="Message_' + this.ID + '" style="font-size:9pt"></td></tr> </table>'
    }
    g.show();
    g.okButton.parentNode.style.textAlign = "center";
    g.okButton.style.display = "none";
    g.cancelButton.value = g.OkButtonText;
    setTimeout(function() {
        g.cancelButton.focus()
    },
    200);
    if (c && c > 0) {
        topWin.$id("_Title_" + g.ID).innerHTML = c + "秒后自动关闭";
        setTimeout(function() {
            g.close()
        },
        c * 1000)
    }
};
Dialog.confirm = function(i, d, c, b, f) {
    var b = b || 300,
    f = f || 110;
    var g = new Dialog({
        Width: b,
        Height: f
    });
    g.ShowButtonRow = true;
    var a = i.split("|");
    if (a.length > 1) {
        g.Title = a[1]
    } else {
        g.Title = "信息确认"
    }
    g.CancelEvent = function() {
        g.close();
        if (c) {
            c()
        }
    };
    g.OKEvent = function() {
        g.close();
        if (d) {
            d()
        }
    };
    g.InnerHtml = '<table height="100%" border="0" align="center" cellpadding="10" cellspacing="0"> <tr><td align="right"><input type="button" id="Icon_' + this.ID + '" class="icon_query" align="absmiddle"></td> <td align="left" id="Message_' + this.ID + '" style="font-size:9pt">' + a[0] + "</td></tr> </table>";
    g.show();
    g.okButton.parentNode.style.textAlign = "center";
    setTimeout(function() {
        g.okButton.focus()
    },
    200)
};
Dialog.open = function(a) {
    var b = new Dialog(a);
    b.show();
    return b
};
window.attachEvent("onload", Dialog.attachBehaviors);
function fixProgress() {
    try {
        if (top.progressFlag == 1) {
            top.progressFlag = 0
        }
    } catch(a) {}
}
function getArrayPosition(c, d) {
    var a = -1;
    for (var b = 0; b < d.length; b++) {
        if (c == d[b]) {
            a = b;
            break
        }
    }
    return a
}
function getTaskTarget() {
    var b = "dialogTask";
    if ($("#skin").attr("splitMode") == true || $("#skin").attr("splitMode") == "true") {} else {
        var a = $(window.top.document.getElementById("theme"));
        if (a.attr("taskTarget") != null) {
            b = a.attr("taskTarget")
        }
    }
    return b
}
function setTaskCurrent(b, a) {
    b.find("a").removeClass("dialogTaskItemCurrent");
    if (a.length != 0) {
        b.find("#" + a[a.length - 1]).addClass("dialogTaskItemCurrent")
    }
}
var timer;
var itemContainerWidth = 0;
var currentItemTotalWidth = 0;
var itemTotalWidth = 0;
$(function() {
    itemContainerWidth = window.document.documentElement.clientWidth;
    $(window).resize(function() {
        resetDialogTask()
    });
    var f = getTaskTarget();
    if (f != "") {
        var d = $("#" + f);
        var g = 2;
        var c = $('<div class="taskItemContainer"></div>');
        d.append(c);
        var a = $('<div class="taskItemButtonLeft" style="display:none;" ></div>');
        var b = $('<div class="taskItemButtonRight" style="display:none;" ></div>');
        d.append(a);
        d.append(b);
        a.bind("mousedown",
        function() {
            timer = setInterval(function() {
                if (g > 17) {
                    g = 18;
                    c.css("left", g);
                    clearInterval(timer);
                    return
                }
                g = g + 8;
                c.css("left", g)
            },
            30)
        });
        a.bind("mouseup",
        function() {
            clearInterval(timer)
        });
        b.bind("mousedown",
        function() {
            var h = $(this).next("ul");
            timer = setInterval(function() {
                if (g > 18) {
                    g = 17;
                    clearInterval(timer);
                    return
                } else {
                    if (g < itemContainerWidth - itemTotalWidth) {
                        g = itemContainerWidth - itemTotalWidth;
                        clearInterval(timer);
                        return
                    }
                }
                g = g - 8;
                c.css("left", g)
            },
            30)
        });
        b.bind("mouseup",
        function() {
            clearInterval(timer)
        })
    }
});
function resetDialogTask() {
    itemContainerWidth = top.window.document.documentElement.clientWidth;
    var b = getTaskTarget();
    if (b != "") {
        var a = $("#" + b);
        if (a[0]) {
            if (a[0].style.display != "none") {
                if (itemTotalWidth > itemContainerWidth) {
                    a.find(".taskItemButtonLeft").show();
                    a.find(".taskItemButtonRight").show()
                } else {
                    a.find(".taskItemButtonLeft").hide();
                    a.find(".taskItemButtonRight").hide();
                    a.find(".taskItemContainer").css("left", 18)
                }
            }
        }
    }
};