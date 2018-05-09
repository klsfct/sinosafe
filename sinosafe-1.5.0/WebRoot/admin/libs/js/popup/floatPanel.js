jQuery.fn.extend({
    floatPanelClose: function() {
        var a = $(this).attr("kdirection");
        var b = $(this).floatPanelGetState();
        if (!b) {
            return
        }
        if (a == "top" || a == "left") {
            $(this).find("#searchBtn").click()
        } else {
            if (a == "bottom" || a == "right") {
                $(this).find("#searchBtn2").click()
            }
        }
    },
    floatPanelOpen: function() {
        var a = $(this).attr("kdirection");
        var b = $(this).floatPanelGetState();
        if (b) {
            return
        }
        if (a == "top" || a == "left") {
            $(this).find("#searchBtn").click()
        } else {
            if (a == "bottom" || a == "right") {
                $(this).find("#searchBtn2").click()
            }
        }
    },
    floatPanelChangeState: function() {
        var a = $(this).attr("kdirection");
        if (a == "top" || a == "left") {
            $(this).find("#searchBtn").click()
        } else {
            if (a == "bottom" || a == "right") {
                $(this).find("#searchBtn2").click()
            }
        }
    },
    floatPanelGetState: function() {
        var b;
        var a = $(this).attr("kdirection");
        if (a == "top") {
            if (parseInt($(this)[0].style.top) < 0) {
                b = false
            } else {
                b = true
            }
        } else {
            if (a == "left") {
                if (parseInt($(this)[0].style.left) < 0) {
                    b = false
                } else {
                    b = true
                }
            } else {
                if (a == "right") {
                    if (parseInt($(this)[0].style.right) < 0) {
                        b = false
                    } else {
                        b = true
                    }
                } else {
                    if (a == "bottom") {
                        if (parseInt($(this)[0].style.bottom) < -2) {
                            b = false
                        } else {
                            b = true
                        }
                    }
                }
            }
        }
        return b
    },
    floatPanelRender: function() {
        var a;
        if ($(this).find(".searchPan_con").length > 0) {
            $(this).buildBox()
        } else {
            a = $(this).html();
            $(this).empty();
            new jQuery.FloatPanel(this, a)
        }
    },
    buildBox: function() {
        var b = $(this);
        b.find("#searchBtn").unbind("click");
        b.find("#searchBtn2").unbind("click");
        b.find("#searchBtn").attr("title", b.attr("beforeClickText"));
        b.find("#searchBtn").text(b.attr("beforeClickText"));
        b.find("#searchBtn2").attr("title", b.attr("beforeClickText"));
        b.find("#searchBtn2").text(b.attr("beforeClickText"));
        b.find("#searchPan").width(Number(b.attr("panelWidth")));
        var a;
        if (b.attr("iframe") == false || b.attr("iframe") == "false") {
            if (b.attr("panelHeight") == "0") {
                a = b.find(".searchPan_con").height()
            } else {
                a = Number(b.attr("panelHeight"));
                b.find(".searchPan_con").height(a)
            }
        } else {
            if (b.attr("panelHeight") == "0") {
                a = b.find(".searchPan_con").height()
            } else {
                a = Number(b.attr("panelHeight"));
                b.find("iframe").height(a)
            }
        }
        if (b.attr("kdirection") == "top") {
            if (b.attr("init") == "hide") {
                if (b.attr("animatefirst") == true || b.attr("animatefirst") == "true") {
                    b.animate({
                        top: -a - 12
                    },
                    {
                        duration: 1000
                    })
                } else {
                    b.css({
                        top: -a - 12
                    })
                }
                b.find("#searchBtn").toggle(function() {
                    b.animate({
                        top: "0"
                    },
                    parseInt(b.attr("duration")),
                    function() {
                        b.trigger("stateChange", "show")
                    });
                    $(this).attr("title", b.attr("afterClickText"));
                    $(this).text(b.attr("afterClickText"))
                },
                function() {
                    b.animate({
                        top: -a - 12
                    },
                    parseInt(b.attr("duration")),
                    function() {
                        b.trigger("stateChange", "hide")
                    });
                    $(this).attr("title", b.attr("beforeClickText"));
                    $(this).text(b.attr("beforeClickText"))
                })
            } else {
                if (b.attr("animatefirst") == true || b.attr("animatefirst") == "true") {
                    b.css({
                        top: -a - 12
                    });
                    b.animate({
                        top: 0
                    },
                    {
                        duration: 1000
                    })
                } else {
                    b.css({
                        top: 0
                    })
                }
                b.find("#searchBtn").toggle(function() {
                    b.animate({
                        top: -a - 12
                    },
                    parseInt(b.attr("duration")),
                    function() {
                        b.trigger("stateChange", "hide")
                    });
                    $(this).attr("title", b.attr("afterClickText"));
                    $(this).text(b.attr("afterClickText"))
                },
                function() {
                    b.animate({
                        top: "0"
                    },
                    parseInt(b.attr("duration")),
                    function() {
                        b.trigger("stateChange", "show")
                    });
                    $(this).attr("title", b.attr("beforeClickText"));
                    $(this).text(b.attr("beforeClickText"))
                })
            }
        } else {
            if (b.attr("kdirection") == "left") {
                b.width(Number(b.attr("panelWidth")) + 30);
                if (b.attr("init") == "hide") {
                    if (b.attr("animatefirst") == true || b.attr("animatefirst") == "true") {
                        b.css({
                            top: Number(b.attr("topDistance"))
                        });
                        b.animate({
                            top: Number(b.attr("topDistance")),
                            left: -Number(b.attr("panelWidth")) - 1
                        },
                        {
                            duration: 1000
                        })
                    } else {
                        b.css({
                            top: Number(b.attr("topDistance")),
                            left: -Number(b.attr("panelWidth")) - 1
                        })
                    }
                    b.find("#searchBtn").toggle(function() {
                        b.animate({
                            top: b.attr("topDistance"),
                            left: 0
                        },
                        parseInt(b.attr("duration")),
                        function() {
                            b.trigger("stateChange", "show")
                        });
                        $(this).attr("title", b.attr("afterClickText"));
                        $(this).text(b.attr("afterClickText"))
                    },
                    function() {
                        b.animate({
                            top: b.attr("topDistance"),
                            left: -Number(b.attr("panelWidth")) - 1
                        },
                        parseInt(b.attr("duration")),
                        function() {
                            b.trigger("stateChange", "hide")
                        });
                        $(this).attr("title", b.attr("beforeClickText"));
                        $(this).text(b.attr("beforeClickText"))
                    })
                } else {
                    if (b.attr("animatefirst") == true || b.attr("animatefirst") == "true") {
                        b.css({
                            top: Number(b.attr("topDistance")),
                            left: -Number(b.attr("panelWidth")) - 1
                        });
                        b.animate({
                            top: Number(b.attr("topDistance")),
                            left: 0
                        },
                        {
                            duration: 1000
                        })
                    } else {
                        b.css({
                            top: Number(b.attr("topDistance")),
                            left: 0
                        })
                    }
                    b.find("#searchBtn").toggle(function() {
                        b.animate({
                            top: Number(b.attr("topDistance")),
                            left: -Number(b.attr("panelWidth")) - 1
                        },
                        parseInt(b.attr("duration")),
                        function() {
                            b.trigger("stateChange", "show")
                        });
                        $(this).attr("title", b.attr("afterClickText"));
                        $(this).text(b.attr("afterClickText"))
                    },
                    function() {
                        b.animate({
                            top: Number(b.attr("topDistance")),
                            left: 0
                        },
                        parseInt(b.attr("duration")),
                        function() {
                            b.trigger("stateChange", "hide")
                        });
                        $(this).attr("title", b.attr("beforeClickText"));
                        $(this).text(b.attr("beforeClickText"))
                    })
                }
            } else {
                if (b.attr("kdirection") == "right") {
                    b.width(Number(b.attr("panelWidth")) + 30);
                    if (b.attr("init") == "hide") {
                        if (b.attr("animatefirst") == true || b.attr("animatefirst") == "true") {
                            b.css({
                                top: Number(b.attr("topDistance"))
                            });
                            $(".searchMain").filter("[id=" + parentId + "]").animate({
                                top: Number(b.attr("topDistance")),
                                right: -Number(b.attr("panelWidth")) - 1
                            },
                            {
                                duration: 1000
                            })
                        } else {
                            b.css({
                                top: Number(b.attr("topDistance")),
                                right: -Number(b.attr("panelWidth")) - 1
                            })
                        }
                        b.find("#searchBtn2").toggle(function() {
                            b.animate({
                                top: Number(b.attr("topDistance")),
                                right: 0
                            },
                            parseInt(b.attr("duration")),
                            function() {
                                b.trigger("stateChange", "show")
                            });
                            $(this).attr("title", b.attr("afterClickText"));
                            $(this).text(b.attr("afterClickText"))
                        },
                        function() {
                            b.animate({
                                top: Number(b.attr("topDistance")),
                                right: -Number(b.attr("panelWidth")) - 1
                            },
                            parseInt(b.attr("duration")),
                            function() {
                                b.trigger("stateChange", "hide")
                            });
                            $(this).attr("title", b.attr("beforeClickText"));
                            $(this).text(b.attr("beforeClickText"))
                        })
                    } else {
                        if (b.attr("animatefirst") == true || b.attr("animatefirst") == "true") {
                            b.css({
                                top: Number(b.attr("topDistance")),
                                right: -Number(b.attr("panelWidth")) - 1
                            });
                            b.animate({
                                top: Number(b.attr("topDistance")),
                                right: 0
                            },
                            {
                                duration: 1000
                            })
                        } else {
                            b.css({
                                top: Number(b.attr("topDistance")),
                                right: 0
                            })
                        }
                        b.find("#searchBtn2").toggle(function() {
                            b.animate({
                                top: Number(b.attr("topDistance")),
                                right: -Number(b.attr("panelWidth")) - 1
                            },
                            parseInt(b.attr("duration")),
                            function() {
                                b.trigger("stateChange", "hide")
                            });
                            $(this).attr("title", b.attr("afterClickText"));
                            $(this).text(b.attr("afterClickText"))
                        },
                        function() {
                            b.animate({
                                top: Number(b.attr("topDistance")),
                                right: 0
                            },
                            parseInt(b.attr("duration")),
                            function() {
                                b.trigger("stateChange", "show")
                            });
                            $(this).attr("title", b.attr("beforeClickText"));
                            $(this).text(b.attr("beforeClickText"))
                        })
                    }
                } else {
                    if (b.attr("kdirection") == "bottom") {
                        if (b.attr("init") == "hide") {
                            if (b.attr("animatefirst") == true || b.attr("animatefirst") == "true") {
                                b.animate({
                                    bottom: -a - 12
                                },
                                {
                                    duration: 1000
                                })
                            } else {
                                b.css({
                                    bottom: -a - 12
                                })
                            }
                            b.find("#searchBtn2").toggle(function() {
                                b.animate({
                                    bottom: -2
                                },
                                parseInt(b.attr("duration")),
                                function() {
                                    b.trigger("stateChange", "show")
                                });
                                $(this).attr("title", b.attr("afterClickText"));
                                $(this).text(b.attr("afterClickText"))
                            },
                            function() {
                                b.animate({
                                    bottom: -a - 12
                                },
                                parseInt(b.attr("duration")),
                                function() {
                                    b.trigger("stateChange", "hide")
                                });
                                $(this).attr("title", b.attr("beforeClickText"));
                                $(this).text(b.attr("beforeClickText"))
                            })
                        } else {
                            if (b.attr("animatefirst") == true || b.attr("animatefirst") == "true") {
                                b.css({
                                    bottom: -a - 12
                                });
                                b.animate({
                                    bottom: -2
                                },
                                {
                                    duration: 1000
                                })
                            } else {
                                b.css({
                                    bottom: -2
                                })
                            }
                            b.find("#searchBtn2").toggle(function() {
                                b.animate({
                                    bottom: -a - 12
                                },
                                parseInt(b.attr("duration")),
                                function() {
                                    b.trigger("stateChange", "hide")
                                });
                                $(this).attr("title", b.attr("afterClickText"));
                                $(this).text(b.attr("afterClickText"))
                            },
                            function() {
                                b.animate({
                                    bottom: -2
                                },
                                parseInt(b.attr("duration")),
                                function() {
                                    b.trigger("stateChange", "show")
                                });
                                $(this).attr("title", b.attr("beforeClickText"));
                                $(this).text(b.attr("beforeClickText"))
                            })
                        }
                    }
                }
            }
        }
        b[0].style.visibility = "visible"
    }
});
var idNum = 0;
var depth = 700;
jQuery.FloatPanel = function(e, k) {
    if (!e.attr("id")) {
        e.attr("id", "floatPanel_auto_" + idNum)
    }
    idNum++;
    if (e.attr("depth")) {
        e.css({
            zIndex: Number(e.attr("depth"))
        })
    } else {
        e.css({
            zIndex: Number(depth)
        })
    }
    depth++;
    var n = "打开面板";
    var c = "关闭面板";
    var g = 500;
    var f = true;
    var d = 400;
    var l = "tc";
    var m = "top";
    var i = false;
    var q = "hide";
    var h = 30;
    var p = 0;
    var b = "top";
    if (e.attr("beforeClickText")) {
        n = e.attr("beforeClickText")
    } else {
        e.attr("beforeClickText", n)
    }
    if (e.attr("afterClickText")) {
        c = e.attr("afterClickText")
    } else {
        e.attr("afterClickText", c)
    }
    if (e.attr("duration")) {
        g = Number(e.attr("duration"))
    } else {
        e.attr("duration", g)
    }
    if (e.attr("animatefirst") == "false" || e.attr("animatefirst") == false) {
        f = false
    } else {
        e.attr("animatefirst", f)
    }
    if (e.attr("panelWidth")) {
        d = Number(e.attr("panelWidth"))
    } else {
        e.attr("panelWidth", d)
    }
    if (e.attr("direction")) {
        l = e.attr("direction")
    }
    if (e.attr("kDirection")) {
        m = e.attr("kDirection")
    }
    if (e.attr("iframe")) {
        if (e.attr("iframe") == "false" || e.attr("iframe") == false) {
            i = false
        } else {
            i = e.attr("iframe")
        }
    } else {
        e.attr("iframe", "false")
    }
    if (e.attr("init")) {
        q = e.attr("init")
    } else {
        e.attr("init", q)
    }
    if (e.attr("topDistance")) {
        h = Number(e.attr("topDistance"))
    } else {
        e.attr("topDistance", h)
    }
    if (e.attr("panelHeight")) {
        p = Number(e.attr("panelHeight"))
    } else {
        e.attr("panelHeight", p)
    }
    var j = $("<div id='searchBtnCon2'><button id='searchBtn2' type='button'></button></div><div id='clear2'></div><div id='searchPan'><div class='searchPan_con'></div></div><div id='clear'></div><div id='searchBtnCon'><button id='searchBtn' type='button'></button></div>");
    e.append(j);
    e[0].style.visibility = "hidden";
    if (l == "tl") {
        e.find("#searchBtnCon2").css({
            display: "none"
        });
        e.css({
            paddingLeft: "10px"
        })
    } else {
        if (l == "tc") {
            e.css({
                textAlign: "center",
                width: "100%"
            });
            e.find("#searchPan").css({
                marginLeft: "auto",
                marginRight: "auto"
            });
            e.find("#searchBtnCon").css({
                textAlign: "center"
            });
            e.find("#searchBtnCon2").css({
                display: "none"
            })
        } else {
            if (l == "tr") {
                e.css({
                    textAlign: "right",
                    right: 0,
                    paddingRight: "10px"
                });
                if (!document.all) {
                    e.find("#searchPan").css({
                        "float": "right"
                    })
                }
                e.find("#searchBtnCon").css({
                    textAlign: "right"
                });
                e.find("#searchBtnCon2").css({
                    display: "none"
                })
            } else {
                if (l == "ml") {
                    e.find("#searchPan").css({
                        "float": "left"
                    });
                    e.find("#searchBtnCon").css({
                        "float": "left"
                    });
                    e.find("#searchBtnCon2").css({
                        display: "none"
                    });
                    b = "left"
                } else {
                    if (l == "mr") {
                        e.find("#searchPan").css({
                            "float": "left"
                        });
                        e.find("#searchBtnCon2").css({
                            "float": "left"
                        });
                        e.find("#searchBtnCon").css({
                            display: "none"
                        });
                        b = "right"
                    } else {
                        if (l == "bl") {
                            e.find("#searchBtnCon").css({
                                display: "none"
                            });
                            e.css({
                                left: "5"
                            });
                            b = "bottom"
                        } else {
                            if (l == "bc") {
                                e.css({
                                    textAlign: "center",
                                    width: "100%"
                                });
                                e.find("#searchPan").css({
                                    marginLeft: "auto",
                                    marginRight: "auto"
                                });
                                e.find("#searchBtnCon2").css({
                                    textAlign: "center"
                                });
                                e.find("#searchBtnCon").css({
                                    display: "none"
                                });
                                b = "bottom"
                            } else {
                                if (l == "br") {
                                    e.css({
                                        textAlign: "right",
                                        right: 0,
                                        paddingRight: "10px"
                                    });
                                    if (!document.all) {
                                        e.find("#searchPan").css({
                                            "float": "right"
                                        })
                                    }
                                    e.find("#searchBtnCon2").css({
                                        textAlign: "right"
                                    });
                                    e.find("#searchBtnCon").css({
                                        display: "none"
                                    });
                                    b = "bottom"
                                } else {
                                    alert("direction参数有误，只能是tl、tc、tr、ml、mr、bl、bc、br中的一个");
                                    return
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    e.attr("kDirection", b);
    if (b == "top") {
        e.find("#searchPan")[0].className = "searchPanTop";
        e.find("#searchBtn")[0].className = "searchBtnTop";
        e.find("#searchBtnCon")[0].className = "searchBtnConTop";
        e.find("#clear")[0].className = "clearTop"
    } else {
        if (b == "left") {
            e.find("#searchPan")[0].className = "searchPanLeft";
            e.find("#searchBtn")[0].className = "searchBtnLeft";
            e.find("#searchBtnCon")[0].className = "searchBtnConLeft"
        } else {
            if (b == "right") {
                e.find("#searchPan")[0].className = "searchPanRight";
                e.find("#searchBtn2")[0].className = "searchBtnRight";
                e.find("#searchBtnCon")[0].className = "searchBtnConRight"
            } else {
                if (b == "bottom") {
                    e.find("#searchPan")[0].className = "searchPanBottom";
                    e.find("#searchBtn2")[0].className = "searchBtnBottom";
                    e.find("#searchBtnCon2")[0].className = "searchBtnConBottom";
                    e.find("#clear2")[0].className = "clearTop"
                }
            }
        }
    }
    var o;
    if (!i) {
        e.find("div[class='searchPan_con']").append(k);
        e.buildBox()
    } else {
        var a;
        if (p > 0) {
            a = $("<IFRAME scrolling=auto width=100% frameBorder=0 id=frmcontent name=frmcontent allowTransparency=true></IFRAME>");
            e.find("div[class='searchPan_con']").append(a);
            a.attr("src", i);
            a.bind("load",
            function() {
                var r = a[0];
                try {
                    r.contentWindow.document.body.style.backgroundColor = "transparent";
                    r.contentWindow.document.body.style.backgroundImage = "none"
                } catch(s) {}
                e.buildBox()
            })
        } else {
            alert("使用iframe模式的浮动面板需要设置panelHeight")
        }
    }
};