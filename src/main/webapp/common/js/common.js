function isPhoneAvailable(phone) {
    var reg=/^[1][3,4,5,7,8][0-9]{9}$/;
    return reg.test(phone);
}
function isEmailAvailable(email) {
    var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    return reg.test(email);
}
function isPositiveInteger(s){//是否为正整数
    var reg = /^[0-9]+$/ ;
    return reg.test(s)
}
function trimStr(str,is_global)
{
    if(typeof (str)=="undefined" || str==null){
        str="";
    }
    str = str + "";
    if(typeof (str)!="string"){
        str="";
    }
    var result;
    result = str.replace(/(^\s+)|(\s+$)/g,"");
    if(is_global)
    {
        result = result.replace(/\s/g,"");
    }
    return result;
}
function timestampToTime(timestamp) {
    if(typeof (timestamp)=="undefined" || timestamp==null || trimStr(timestamp,true).length<1){
        return "";
    }
    if(timestamp.length==10){
        timestamp = parseInt(timestamp)*1000;
    }
    var date = new Date(parseInt(timestamp));//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '年';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '月';
    D = date.getDate() + '日 ';
    h = date.getHours() + '时';
    m = date.getMinutes() + '分';
    s = date.getSeconds() + '秒';
    return Y+M+D+h+m+s;
}