$(function () {
    $(document).click(function () {
        $(".main-search-bottom").hide();
    });
    $('#seaWords').on('keydown', function (e) {
        if (e.keyCode == 13) {
            $('#seaGamebtn').trigger('click');
            return false;
        }
    })
    $(".main-search-box").click(function (event) {
        event.stopPropagation();
    });

    $(".main-search-top").find(".main-search-item").click(function () {
        var curFlag = $(this).attr("data-flag");
        var _index = $(this).index();
        if (_index !== 0) {
            if ($(this).prev().find("p").text() !== $(this).prev().find("p").attr("data-start")) {
                var _dataEquivalents = $(this).attr("data-equivalents");
                var _prevDatavalue = $(this).prev().find(".hidden-input").val();
                if (_dataEquivalents == _prevDatavalue) {
                    searchEffect(curFlag, false);
                } else {
                    searchEffect(curFlag, true);
                }

            }
        } else {
            searchEffect(curFlag);
        }
    });

    $(".main-search-list").on("click", "a", function () {
        var _dataflag = $(this).parents(".main-search-list").attr("data-flag");
        var _datavalue = $(this).attr("data-value");
        var _txt = $(this).text();
        if (_dataflag == "seaGamename") {
            var _GameName = $(".main-search-item[data-flag='seaGamename']").find("p").text();
            if (_txt !== _GameName) {
                $(".main-search-item").each(function () {
                    var _datastart = $(this).find("p").attr("data-start");
                    $(this).attr("data-equivalents", "");
                    $(this).find("p").text(_datastart);
                });
                $("#seaGameareaList").empty();
                $("#seaGameserviceList").empty();
            }
        } else if (_dataflag == "seaGameservice") {
            $(this).parents(".main-search-list").hide();
            $(".main-search-bottom").hide();
        }
        ;
        $(".main-search-item[data-flag=" + _dataflag + "]").find("p").text(_txt);
        $(".main-search-item[data-flag=" + _dataflag + "]").find(".hidden-input").val(_datavalue);
        $(".main-search-item[data-flag=" + _dataflag + "]").next().trigger("click");
        $(".main-search-item[data-flag=" + _dataflag + "]").next().attr("data-equivalents", _datavalue);
    });

    $('#seaGamebtn').on('click', function () {
        var game = $.trim($('#seaGamename').val());

        if (!game || game == '0') {
            $('.main-search-item:eq(0)').trigger('click');
            return;
        }
        $('#searchForm').attr('action', '/zuhao-' + $('#seaGamename').val() +
            "-" + $('#seaGamearea').val() +
            "-" + $('#seaGameservice').val() + "-1").submit();
        return false;
    })
});


function searchEffect(curFlag, switches) {
    if (curFlag == "seaGamename") {
        $(".main-search-bottom").show();
        $(".main-search-arrow").stop().animate({left: "60px"}, 500);
        $("#seaGamenameList").show().siblings().hide();
        if ($("#seaGamenameList").find("li").length == 0) {
            gameAjaxData(curFlag, "");
        }
        ;
    } else if (curFlag == "seaGamearea") {
        var _datavalue = $(".main-search-item[data-flag='seaGamename']").find(".hidden-input").val();
        $(".main-search-bottom").show();
        $(".main-search-arrow").stop().animate({left: "175px"}, 500);
        $("#seaGameareaList").show().siblings().hide();
        if (switches) {
            gameAjaxData(curFlag, _datavalue, "全部大区");
        }
        ;
    } else if (curFlag == "seaGameservice") {
        var _datavalue = $(".main-search-item[data-flag='seaGamearea']").find(".hidden-input").val();
        $(".main-search-bottom").show();
        $(".main-search-arrow").stop().animate({left: "285px"}, 500);
        $("#seaGameserviceList").show().siblings().hide();
        if (switches) {
            gameAjaxData(curFlag, _datavalue, "全部服务器");
        }
        ;
    }
    ;

}

function gameAjaxData(curFlag, dataValue, defaultName) {
    var template = '<li><a href="javascript:void(0)" data-value="{id}">{name}</a></li>';
    if (curFlag == "seaGamename") {
        //ajax加载游戏名

        $.ajax({
            url: '/gameinfo/json/games',
            type: 'POST',
            success: function (data) {
                var html = defaultName ? template.replace("{id}", "0").replace("{name}", defaultName) : "";
                if (data.responseCode == '0000') {
                    for (var i = 0; i < data.object.length; i++) {
                        var game = data.object[i];
                        html += template.replace('{id}', game.gameId).replace('{name}', game.gameName);
                    }
                }
                $("#seaGamenameList").html(html);
            }
        })

    } else if (curFlag == "seaGamearea") {
        //ajax加载游戏区

        $.ajax({
            url: '/gameinfo/json/games/' + dataValue,
            type: 'POST',
            success: function (data) {
                var html = defaultName ? template.replace("{id}", "0").replace("{name}", defaultName) : "";
                if (data.responseCode == '0000') {
                    for (var i = 0; i < data.object.length; i++) {
                        var game = data.object[i];
                        html += template.replace('{id}', game.id).replace('{name}', game.name);
                    }
                }
                $("#seaGameareaList").html(html);
            }
        })
    } else if (curFlag == "seaGameservice") {
        //ajax加载游戏服
        $.ajax({
            url: '/gameinfo/json/games/' + dataValue,
            type: 'POST',
            success: function (data) {
                var html = defaultName ? template.replace("{id}", "0").replace("{name}", defaultName) : "";
                if (data.responseCode == '0000') {
                    for (var i = 0; i < data.object.length; i++) {
                        var game = data.object[i];
                        html += template.replace('{id}', game.id).replace('{name}', game.name);
                    }
                }
                $("#seaGameserviceList").html(html);
            }
        })
    }

}