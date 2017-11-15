$(function () {
    //幻灯片
    $(".slider").slide({
        titCell: ".hd ul", mainCell: ".bd ul",
        interTime: 5000, effect: "fold", autoPlay: true, autoPage: true, trigger: "click",
        mouseOverStop: false,
        startFun: function () {
            var timer = jQuery(".slider .timer");
            timer.stop(true, true).animate({"width": "0%"}, 0).animate({"width": "100%"}, 5000);
        }
    });
    //Tab切换

    // // 大家如何说
    // $(".dlScroll-top").slide({
    //     titCell: ".hd ul",
    //     mainCell: ".bd ul",
    //     autoPage: true,
    //     effect: "top",
    //     autoPlay: true,
    //     vis: 1
    // });
    //main-area-04


    $('#searchBox').on('click', '.zuhaolist', function () {
        var that = $(this),
            gameId = that.closest('div[data-game]').attr('data-game');
        type = that.attr('data-value'),
            $form = $('#fastForm');
        resetForm($form);
        $form.attr('action', 'zuhao-' + gameId + '-0-0-1');
        switch (type) {
            case '1': {
                return true;
            }
            case '2': {
                setFormVal($form, "options", "G603PWS1001");
                setFormVal($form, "optionsSize", "1");
                break;
            }
            case '3': {
                setFormVal($form, "userIdentity", "1");
                break;
            }
            case '4': {
                setFormVal($form, "isClaim", "1");
                break;
            }
            case '5': {
                $form.find('');
                break;
            }
            case '6': {
                setFormVal($form, "hasNoDeposit", "1");
                break;
            }
            case '8': {
                setFormVal($form, "optionsSize", "1");
                setFormVal($form, 'options', 'G603YXSLR1004');
                appendInput($form, 'options', 'G603YXSLR1005');
                appendInput($form, 'options', 'G603YXSLR1006');
                break;
            }
            case '7': {
                setFormVal($form, "optionsSize", "1");
                setFormVal($form, 'options', 'G603PFSLR1003');
                appendInput($form, 'options', 'G603PFSLR1004');
                appendInput($form, 'options', 'G603PFSLR1005');
                break;
            }
            case '9': {
                setFormVal($form, "optionsSize", "1");
                setFormVal($form, 'options', 'G603DW1003');
                appendInput($form, 'options', 'G603DW1004');
                appendInput($form, 'options', 'G603DW1005');
                appendInput($form, 'options', 'G603DW1006');
                appendInput($form, 'options', 'G603DW1007');
                break;
            }
            case  '10': {
                setFormVal($form, 'newUserUnLimit', '1');
                break;
            }
            case '11': {
                setFormVal($form, "optionsSize", "1");
                setFormVal($form, 'options', 'G68YXWQS1004');
                break;
            }
            case '12': {
                setFormVal($form, "optionsSize", "1");
                setFormVal($form, 'options', 'G68VIPDJ1006');
                break;
            }
            case '13': {
                setFormVal($form, "rentalByHourStart", "1");
                setFormVal($form, "rentalByHourEnd", "2.5");
                break;
            }
            case '14': {
                setFormVal($form, "optionsSize", "1");
                setFormVal($form, "options", 'G68ZHLX1001');
                break;
            }
            case '15': {
                setFormVal($form, "optionsSize", "1");
                setFormVal($form, "options", 'G68ZHLX1002');
                break;
            }
            case '16': {
                setFormVal($form, "optionsSize", "1");
                setFormVal($form, "options", 'G68ZHLX1003');
                break;
            }
            case '17': {
                setFormVal($form, "optionsSize", "1");
                setFormVal($form, "options", 'G68ZHLX1004');
                break;
            }
            case '18': {
                setFormVal($form, "order", "1");
                break;
            }
            default: {
                return false;
            }
        }
        $form.submit();
        return false;
    })

    function appendInput($form, name, val) {
        $form.append('<input type="text" value="' + val + '" name="' + name + '"/>');
    }

    function setFormVal($form, name, val) {
        $form.find('[name="' + name + '"]').val(val);
    }

    function resetForm($form) {
        $form.find('[name="options"]:gt(0)').remove();
        $form[0].reset();
        $form.attr('action', '');
        $form.find('[name="isAppointment"]').val('1');
        $form.find('[name="order"]').val('0');
        $form.find('[name="page"]').val('1');
        $form.find('[name="pageSize"]').val('20');
        setFormVal($form, "optionsSize", "0");
    }

    var template = '<li><span>{title}</span><em class="e2">{time}</em><em class="e1">{name}</em></li>',
        template2 = '  <li><em class="e1">{name}</em><em class="e2">{game}</em><span>{title}</span></li>';
    var defer = $.Deferred(), defer2 = $.Deferred();
    $.ajax({
        url: '/index/newestorder',
        type: 'POST',
        success: function (data) {
            $('#newestorder').empty();
            if (data.responseCode == '0000') {
                var l = data.object.list.length;
                for (var i = 0; i < l; i++) {
                    var curData = data.object.list[i];
                    $('#newestorder').append(template.replace('{title}', curData.goodsTitle)
                        .replace('{time}', curData.rentTime + "小时")
                        .replace('{name}', curData.buyerName));
                }

                var $tj = $('#orderStatistics');
                $tj.find("span.lastday").html(data.object.total['dailyOrderAmount']);
                $tj.find("span.lastmonth").html(data.object.total['monthlyOrderAmount'])
                defer.resolve();
            }

        }
    })

    $.ajax({
        url: '/index/blacklist',
        type: 'POST',
        success: function (data) {
            $('#blacklist').empty();
            if (data.responseCode == '0000') {
                var l = data.object.list.length;
                for (var i = 0; i < l; i++) {
                    var curData = data.object.list[i];
                    $('#blacklist').append(template2.replace('{title}', curData.sellerComplainContent)
                        .replace('{game}', curData.gameName)
                        .replace('{name}', curData.buyerName));
                }

                var $tj = $('#blacklistStatistics');
                $tj.find("span.lastday").html(data.object.total['dailyOrderAmount']);
                $tj.find("span.lastmonth").html(data.object.total['monthlyOrderAmount'])
                defer2.resolve();
            }

        }
    })

    $.when(defer, defer2).done(function () {
        $(".txtScroll-top").slide({
            titCell: ".hd ul",
            mainCell: ".bd ul",
            autoPage: true,
            effect: "top",
            autoPlay: true,
            vis: 6
        });
    });

    var cmstemplate = '<div class="index-tab-item"><ul></ul></div>';

    var cates = [];
    $('#cmsul').find('li[data-value]').each(function () {
        cates.push($(this).attr('data-value'));
    })

    var listtemplate = '<li><a href="{url}" target="_blank">{title}</a></li>';
    var timer;
    if (cates.length > 0) {
        $.ajax({
            url: '/index/category',
            data: {
                cate: cates,
                pageNum: 1,
                pageSize: 4
            },
            type: 'POST',
            error: function (d) {
            },

            success: function (data) {
                if (data.responseCode == '0000') {
                    var len = cates.length, $items = $('#cmsitems');
                    for (var i = 0; i < len; i++) {
                        var curData = data.object[cates[i]];
                        var vd = $(cmstemplate), $ul = vd.find('ul');
                        for (var j = 0, length = curData.length; j < length; j++) {
                            var newd = curData[j];
                            $ul.append(listtemplate.replace('{title}', newd.title)
                                .replace('{url}', i == 0 ? ('/notice/list/' + newd.id ) : ('/help/list/' + newd.category + '/' + newd.id)));
                        }
                        if (i == 0) vd.show();
                        $items.append(vd)
                    }
                    clearTimeout(timer);
                    $(".index-tab-box").children(".index-tab-top").find("li").hover(function(){
                        var _that = $(this);
                        timer = setTimeout(
                            function(){
                                _that.addClass("on").siblings("li").removeClass("on");
                                var flag= _that.index();
                                var _left = $(".index-tab-box").find(".on").position().left;
                                $(".slider-line").stop().animate({"left":_left},100);
                                _that.parents(".index-tab-box").find(".index-tab-item").eq(flag).show().siblings().hide();
                            },300);
                    },function(){
                        clearTimeout(timer);
                    });
                }
            }
        })
    }


});

