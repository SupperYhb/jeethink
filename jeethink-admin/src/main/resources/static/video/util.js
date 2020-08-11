function addEvent(obj, name, func) {
    if (!obj) return;

    if (obj.attachEvent) {
        obj.attachEvent("on" + name, func);
    } else {
        obj.addEventListener(name, func, false);
    }
}

function speedToString(speed) {
    var ret = 'X1';

    switch (+speed) {
        case 1:
            ret = 'X1/8';
            break;
        case 2:
            ret = 'X1/4';
            break;
        case 3:
            ret = 'X1/2';
            break;
        case 4:
            ret = 'X1';
            break;
        case 5:
            ret = 'X2';
            break;
        case 6:
            ret = 'X4';
            break;
        case 7:
            ret = 'X8';
            break;
        case 8:
            ret = 'X16';
            break;
    }

    return ret;
}

function isRootGroup(group) {
    if (!group) return false;

    if (group.supid === '00000000000000000000000000000000') {
        return (group.id === '00000000000000000000000000000000');
    } else {
        return (group.supid == null || group.supid == '');
    }
}

function recTimeToString(t) {
    try {
        var d = new Date(t * 1000);
        return d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
    } catch (error) {
        return '';
    }
}
