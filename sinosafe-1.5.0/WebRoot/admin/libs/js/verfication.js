Validator = {
	userId : /^[a-zA-Z0-9_]{6,20}$/,
	passWd : /^[\dA-Za-z(!@#$%&)]{6,16}$/,
	phone : /^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
	mobile : /^((\(\d{3}\))|(\d{3}\-))?(13|18|15|14|17)\d{9}$/,
	email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
	idCard : /^(\d{18,18}|\d{15,15}|\d{17,17}X)$/,
	integer : /^[-\+]?\d+$/,
	url : /((^http)|(^https)|(^ftp)):\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/
}
function isNull(id, msg) {
	var objId = "#" + id;
	if ($.trim($(objId).val()) == "") {
		alert(msg);
		$(objId).focus();
		return false;
	} else {
		return true;
	}
}
function isRadioChecked(name, msg) {
	var radio = ":radio[name='" + name + "']:checked"
	var len = $(radio).length;
	if (len == 0) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}
function isCheckBoxChecked(name, msg) {
	var radio = ":checkbox[name='" + name + "']:checked"
	var len = $(radio).length;
	if (len == 0) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}
function isRadioIdChecked(id, msg) {
	var obj = ":radio[id^='" + id + "']:checked"
	var len = $(obj).length;
	if (len == 0) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}
function isCheckBoxIdChecked(id, msg) {
	var obj = ":checkbox[id^='" + id + "']:checked"
	var len = $(obj).length;
	if (len == 0) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}
function isCheckTextarea(id, msg, length, msgLenght) {
	var objId = "#" + id;
	var objVal = $.trim($(objId).val());
	var objLen = objVal.length;
	if (objVal == "") {
		alert(msg);
		$(objId).focus();
		return false;
	} else if (objLen > length) {
		alert(msgLenght);
		$(objId).focus();
		return false;
	} else {
		return true;
	}
}

function getRadioValue(name) {
	var radio = ":radio[name=" + name + "]:checked"
	return $("#" + radio).val();
}
/**
 * 大于给定起始值整数验证
 * @param {Object} id
 * @param {Object} msg
 */
function isIntegerRange (id,startValue,msg){
	var objId = "#" + id;
	if(regularVerify(id,msg,"integer")){
		var currentValue = $(objId).val();
		if(!Number(currentValue) > Number(startValue)){
			window.alert(msg);
			$(objId).focus();
			return false;
		}
	}
	else{
		return false;
	}
	return true;
}
/**
 * 整数验证
 * @param {Object} id
 * @param {Object} msg
 */
function isInteger(id,msg){
	return regularVerify(id,msg,"integer");
}
/**
 * 网址URL验证
 * @param {Object} id
 * @param {Object} msg
 */
function isUrl(id,msg){
	return regularVerify(id,msg,"url");
}
/**
 * 验证用户登录ID规则
 * @param {Object} id
 * @param {Object} msg
 * @return {TypeName} 
 */
function isUserId(id,msg){
	return regularVerify(id,msg,"userId");
}
/**
 * 验证用户密码规则
 * @param {Object} id
 * @param {Object} msg
 * @return {TypeName} 
 */
function isPassWd(id,msg){
	return regularVerify(id,msg,"passWd");
}
/**
 * 验证身份证号,出生日期,性别之间的逻辑关系
 * @param {Object} identityId
 * @param {Object} birthdayId
 * @param {Object} sexName
 */
function checkIdentity(identityId,birthdayId,sexVlaue){
	var identity = $("#" + identityId).val();
	var birthday = $("#" + birthdayId).val();
	var regexStr = Validator["idCard"];
	if (!regexStr.test(identity)){ 
		alert("身份证号码填写错误请重新填写，如果身份证号码末位为“X”请输入大写字母“X”！");
		$(identityId).focus();
   		return false;  
    }
	var identityBirthday = identity.length == 15 ? "19" + identity.substring(6, 12) : identity
			.substring(6, 14);
	birthday = birthday.replace(/-/g, "");
	if (identityBirthday != birthday) {
		alert("身份证号中的出生日期与填写的出生日期不符！");
		$(identityId).focus();
		return false;
	}
	var identitySex = identity.length == 15 ? identity.substring(14, 15) : identity
			.substring(16, 17);
	if (sexVlaue == "男")
		sexVlaue = "1";
	if (sexVlaue == "女")
		sexVlaue = "0";
	identitySex = (parseInt(identitySex) % 2).toString();
	if (identitySex != sexVlaue) {
		alert("身份证号中的性别与填写的性别不符！");
		$(identityId).focus();
		return false;
	}
	return true;
}
/**
 * 验证电子邮箱格式
 * @param {Object} id
 * @param {Object} msg
 * @return {TypeName} 
 */
function isEmail(id,msg){
	return regularVerify(id,msg,"email");
}
/**
 * 移动电话验证
 * @param {Object} id
 * @param {Object} msg
 */
function isMobile(id,msg){
	return regularVerify(id,msg,"mobile");
}
/**
 * 固定电话验证
 * @param {Object} id
 * @param {Object} msg
 */
function isPhone(id,msg){
	return regularVerify(id,msg,"phone");
}
function checkStartEnd(startDateId,endDateId,msg){
	var datePattern = /^(\d{4})-(\d{1,2})-(\d{1,2})$/; 
	var startDate = $("#" + startDateId).val();
	var endDate = $("#" + endDateId).val();
  	if (!datePattern.test(startDate)) { 
    	window.alert("请填写正确的开始日期格式"); 
    	return false; 
  	} 
  	if (! datePattern.test(endDate)) { 
    	window.alert("请填写正确的结束日期格式"); 
    	return false; 
  	} 
	var d1 = new Date(startDate.replace(/-/g, "/")); 
  	var d2 = new Date(endDate.replace(/-/g, "/")); 
  	/**if (Date.parse(d1) - Date.parse(d2) == 0) { 
    	window.alert("两个日期相等"); 
    	return false; 
  	} 
  	if (Date.parse(d1) - Date.parse(d2) < 0) { 
   		window.alert("结束日期 大于 开始日期"); 
  	}*/
  	if (Date.parse(d1) - Date.parse(d2) > 0) { 
    	window.alert(msg); 
    	return false; 
  	} 
  	return true; 
}
/**
 * 
 * @param {Object} id
 * @param {Object} msg
 * @param {Object} regularExp
 * @return {TypeName} 
 */
function regularVerify(id,msg,regularExp){
	var value = $("#" + id).val();
	if(value !=""){
		var regexStr = Validator[regularExp];
		if (!regexStr.test(value)){ 
			alert(msg);
			$("#" + id).focus();
   			return false;  
    	}
		else{
        	return true;  
   		}
	}
	else{
		return true;
	}
}
/**
 * 判断文本域输入的汉字个数
 */
function getStringUTFLength(id) {
	var str = $("#" + id).val();
    var value = str.replace(/[^\x00-\xff]/g," ");
    return value.length;
}
function leftUTFString(id,len,msg) {
    if(getStringUTFLength(id) >len){
    	alert(msg);
    	$(id).focus();
        return false;
    }
    else{
    	return true;
    }
}
/**
 * 验证身份证号
 * @param {Object} sId
 * @return {TypeName} 
 */
var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"} 

function isCardID(sId){ 
	var iSum=0 ;
	var info="" ;
	if(!/^\d{17}(\d|x)$/i.test(sId)) return "输入的身份证长度或格式错误！"; 
	sId=sId.replace(/x$/i,"a"); 
	if(aCity[parseInt(sId.substr(0,2))]==null) return "输入的身份证地区非法！"; 
	sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2)); 
	var d=new Date(sBirthday.replace(/-/g,"/")) ;
	if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))return "身份证上的出生日期错误！"; 
	for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
	if(iSum%11!=1) return "输入的身份证号非法！"; 
	return true;
} 
