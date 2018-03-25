function isPhoneAvailable(phone) {
    var reg=/^[1][3,4,5,7,8][0-9]{9}$/;
    return reg.test(phone);
}
function isEmailAvailable(email) {
    var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    return reg.test(email);
}