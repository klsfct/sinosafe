(function(a) {
    this.generateCatalogId = function(c) {
        var b;
        $.ajax({
            type: "post",
            async: false,
            url: c,
            dataType: "json",
            success: function(e, f, d) {
                catalogId = e.catalogId
            }
        });
        return catalogId
    };
    this.start = function(g) {
        var d = "/fileupload/upload.do";
        var p = "/fileupload/delete.do";
        var l = "/fileupload/deleteByKind.do";
        var h = "/fileupload/download.do";
        var c = "/fileupload/listByKind.do";
        var m = "";
        var w;
        var j;
        var u;
        var o = "1024000";
        var b = "*.*";
        var t;
        var n;
        var i;
        var v = 180;
        var k = true;
        var e = false;
        var s;
        if (g) {
            if (g.contextPath) {
                m = g.contextPath
            }
            if (g.moduleId) {
                w = g.moduleId
            }
            if (g.kind) {
                j = g.kind
            }
            if (g.catalogId) {
                u = g.catalogId
            }
            if (g.fileSize) {
                o = g.fileSize
            }
            if (g.fileTypes) {
                b = g.fileTypes
            }
            if (g.buttonContainer) {
                t = g.buttonContainer
            } else {
                alert("请指定按钮容器id")
            }
            if (g.fileListContainer) {
                n = g.fileListContainer
            } else {
                alert("请指定文件列表容器id")
            }
            if (g.deleteContainer) {
                s = g.deleteContainer
            }
            if (g.fileNameWidth) {
                v = g.fileNameWidth
            }
            if (g.showInfo == false) {
                k = false
            }
            if (g.editMode == true) {
                e = true
            }
            if (g.uploadUrl) {
                d = m + g.uploadUrl;
                if (u && j && w) {
                    d = d + "&catalogId=" + u + "&moduleId=" + w + "&kind=" + j
                } else {
                    if (u) {
                        d = d + "&catalogId=" + u
                    } else {
                        if (w) {
                            d = d + "&moduleId=" + w
                        } else {
                            if (j) {
                                d = d + "&kind=" + j
                            } else {
                                if (u && j) {
                                    d = d + "&catalogId=" + u + "&kind=" + j
                                } else {
                                    if (u && w) {
                                        d = d + "&catalogId=" + u + "&moduleId=" + w
                                    } else {
                                        if (j && w) {
                                            d = d + "&kind=" + j + "&moduleId=" + w
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                d = m + d + "&catalogId=" + u + "&moduleId=" + w + "&kind=" + j
            }
            if (g.deleteUrl) {
                p = m + g.deleteUrl
            } else {
                p = m + p + "&id="
            }
            if (g.deleteAllUrl) {
                l = m + g.deleteAllUrl;
                if (u && j) {
                    l = l + "&catalogId=" + u + "&kind=" + j
                } else {
                    if (u) {
                        l = l + "&catalogId=" + u
                    } else {
                        if (j) {
                            l = l + "&kind=" + j
                        }
                    }
                }
            } else {
                l = m + l + "&catalogId=" + u + "&kind=" + j
            }
            if (g.downloadUrl) {
                h = m + g.downloadUrl
            } else {
                h = m + h + "&id="
            }
            if (g.listUrl) {
                c = m + g.listUrl;
                if (u && j) {
                    c = c + "&catalogId=" + u + "&kind=" + j
                } else {
                    if (u) {
                        c = c + "&catalogId=" + u
                    } else {
                        if (j) {
                            c = c + "&kind=" + j
                        }
                    }
                }
            } else {
                c = m + c + "&catalogId=" + u + "&kind=" + j
            }
        }
        var q = new SWFUpload({
            catalogId: u,
            upload_url: d,
            delete_url: p,
            delete_all_url: l,
            download_url: h,
            list_url: c,
            file_size_limit: o,
            file_types: b,
            file_types_description: "所有文件",
            file_dialog_start_handler: fileDialogStart,
            file_queued_handler: fileQueued,
            file_queue_error_handler: fileQueueError,
            file_dialog_complete_handler: fileDialogComplete,
            upload_start_handler: uploadStart,
            upload_progress_handler: uploadProgress,
            upload_error_handler: uploadError,
            upload_success_handler: uploadSuccess,
            upload_complete_handler: uploadComplete,
            button_image_url: m + "/libs/js/form/upload/button.jpg",
            button_placeholder_id: t,
            flash_url: m + "/libs/js/form/upload/swfupload.swf",
            custom_settings: {
                progressTarget: n,
                fileNameWidth: v,
                cancelButtonId: s,
                showInfo: k
            }
        });
        if (s) {
            var f = $("#" + s);
            var r = $('<span class="icon_delete hand">全部删除</span>');
            f.append(r);
            r.click(function() {
                cancelQueue(q)
            })
        }
        if (e) {
            $.fileUpload.addUploadList(q)
        }
        return q
    };
    this.addUploadListRead = function(k) {
        var f = "/fileupload/download.do";
        var i = "/fileupload/listByKind.do";
        var b = "";
        var d;
        var e;
        var g;
        var h = 180;
        var c;
        var j = true;
        if (k) {
            if (k.contextPath) {
                b = k.contextPath
            }
            if (k.kind) {
                d = k.kind
            }
            if (k.catalogId) {
                e = k.catalogId
            }
            if (k.fileListContainer) {
                g = k.fileListContainer
            } else {
                alert("需要指定文件列表容器id")
            }
            if (k.downloadUrl) {
                f = b + k.downloadUrl
            } else {
                f = b + f + "&id="
            }
            if (k.listUrl) {
                i = b + k.listUrl;
                if (e && d) {
                    i = i + "&catalogId=" + e + "&kind=" + d
                } else {
                    if (e) {
                        i = i + "&catalogId=" + e
                    } else {
                        if (d) {
                            i = i + "&kind=" + d
                        }
                    }
                }
            } else {
                i = b + i + "&catalogId=" + e + "&kind=" + d
            }
            if (k.fileNameWidth) {
                h = k.fileNameWidth
            }
            if (k.deleteUrl) {
                c = b + k.deleteUrl
            }
            if (k.showInfo == false) {
                j = false
            }
        }
        $.getJSON(i,
        function(l) {
            if (!l) {
                return
            }
            var n = $("#" + g);
            $.each(l.attachmentList,
            function(u, w) {
                var v = $('<div class="upload_list"></div>');
                v.append($('<div class="upload_iconBg"><div class="upload_icon_ok"></div></div>'));
                var s = $('<div class="upload_name"></div>');
                var q = $("<span></span>");
                if (j) {
                    q.attr("title", w.uploadFileName)
                }
                q.html(w.uploadFileName);
                s.append(q);
                v.append(s);
                s.width(h);
                var p = $('<div class="upload_delete"></div>');
                var t = $("<a href='" + f + w.id + "'>下载</a>");
                p.append(t);
                v.append(p);
                if (c) {
                    var o = $('<div class="upload_delete"></div>');
                    var r = $("<a>删除</a>");
                    o.append(r);
                    v.append(o);
                    r.click(function() {
                        $.post(c + w.id,
                        function(x) {
                            v.html("");
                            v.hide()
                        })
                    })
                }
                v.append($('<div class="clear"></div>'));
                n.append(v)
            });
            if (j) {
                try {
                    enableTooltips()
                } catch(m) {}
            }
        })
    };
    this.addUploadList = function(b) {
        $.getJSON(b.settings.list_url,
        function(c) {
            if (!c) {
                return
            }
            var f = $('<div class="existUploadList"></div>');
            $("#" + b.customSettings.progressTarget).after(f);
            f.attr("targetId", b.customSettings.progressTarget);
            $.each(c.attachmentList,
            function(l, n) {
                var m = $('<div class="upload_list"></div>');
                m.append($('<div class="upload_iconBg"><div class="upload_icon_ok"></div></div>'));
                var j = $('<div class="upload_name"></div>');
                var h = $("<span></span>");
                if (b.customSettings.showInfo) {
                    h.attr("title", n.uploadFileName)
                }
                h.html(n.uploadFileName);
                j.append(h);
                m.append(j);
                j.width(b.customSettings.fileNameWidth);
                var g = $('<div class="upload_delete"></div>');
                var k = $("<a href='" + b.settings.download_url + n.id + "'>下载</a>");
                g.append(k);
                m.append(g);
                var e = $('<div class="upload_delete"></div>');
                var i = $("<a>删除</a>");
                e.append(i);
                m.append(e);
                i.click(function() {
                    $.post(b.settings.delete_url + n.id,
                    function(o) {
                        m.html("");
                        m.hide()
                    })
                });
                m.append($('<div class="clear"></div>'));
                f.append(m)
            });
            if (b.customSettings.showInfo) {
                try {
                    enableTooltips()
                } catch(d) {}
            }
        })
    };
    a.fileUpload = this;
    return a
})(jQuery);