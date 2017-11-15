template.helper('toFixed', function (value, i) {
    i = i ? i : 2;
    var f = parseFloat(value);
    var w = Math.pow(10, i);
    if (isNaN(f)) {
        return;
    }
    f = Math.round(value * w) / w;
    return f;
});
template.helper('substr', function (value, i) {
    var v = new String(value);
    var index = parseInt(i);
    return v.substr(v.length - index);
});
template.helper('toFixedX', function (value, i) {
    i = i ? i : 2;
    var f = parseFloat(value);
    if (isNaN(f)) {
        return false;
    }
    var w = Math.pow(10, i);
    var f = Math.round(value * w) / w;
    var s = f.toString();
    var rs = s.indexOf('.');
    if (rs < 0) {
        rs = s.length;
        s += '.';
    }
    while (s.length <= rs + i) {
        s += '0';
    }
    return s;
});

function DateFormat(date, format) {
    date = new Date(date);

    var map = {
        "M": date.getMonth() + 1, //月份
        "d": date.getDate(), //日
        "h": date.getHours(), //小时
        "m": date.getMinutes(), //分
        "s": date.getSeconds(), //秒
        "q": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds() //毫秒
    };
    format = format.replace(/([yMdhmsqS])+/g, function (all, t) {
        var v = map[t];
        if (v !== undefined) {
            if (all.length > 1) {
                v = '0' + v;
                v = v.substr(v.length - 2);
            }
            return v;
        }
        else if (t === 'y') {
            return (date.getFullYear() + '').substr(4 - all.length);
        }
        return all;
    });
    return format;
}
template.helper('dateFormat', DateFormat);
