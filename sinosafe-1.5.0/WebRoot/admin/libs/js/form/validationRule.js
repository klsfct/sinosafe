/*edited by fukai*/
/*required
 必填项
 
length[0,20]
 限制长度
 
minCheckbox[1]
 允许最少选中的checkbox的数量，括号中代表的是数量
 
maxCheckbox[3]
 允许最多选中的checkbox的数量，括号中代表的是数量
 
confirm[pwd]
 需要与某个元素的值保持一致，例如用于确认密码。括号中代表那个元素的id
 
custom[chinese]
 只允许中文
 
custom[telephone]
 只允许按固定电话号码格式填写，如010-51613333
 
custom[mobilephone]
 只允许按手机号码格式填写
 
custom[email]
 只允许按电子邮件格式填写
 
custom[date]
 只允许按日期格式填写，如2010-8-14
 
custom[ip]
 只允许按IP地址格式填写
 
custom[zipcode]
 只允许按邮编格式填写（5位数字）
 
custom[qq]
 只允许按QQ号格式填写（4-9位数字）
 
custom[onlyNumber]
 只允许输入纯数字（非负整数）
 
custom[onlyDecimal]
 只允许输入4位以内的小数（正负不限）（小数的位数可通过修改代码实现自定义）
 
custom[onlyNumberWide]
 只允许输入广义的数字（包括正负整数或正负4位以内小数）
 
custom[onlyLetter]
 只允许输入英文
 
custom[noSpecialCaracters]
 只允许输入英文或数字
 
custom[illegalLetter]
 检验非法字符，包括：
"~","`","!","@","#","$","%","^","&","*",
"(",")","+","[","]","{","}","|","\\",":",
";","\","'","<",">","?","/","=","_"
*/
(function($) {
	$.fn.validationEngineLanguage = function() {};
	$.validationEngineLanguage = {
		newLang: function() {
			$.validationEngineLanguage.allRules = 	{"required":{   
						"regex":"none",
						"alertText":"* 非空选项.",
						"alertTextCheckboxMultiple":"* 请选择一个单选框.",
						"alertTextCheckboxe":"* 请选择一个复选框."},
					"length":{
						"regex":"none",
						"alertText":"* 长度必须在",
						"alertText2":" 至 ",
						"alertText3": "之间."},
					"maxCheckbox":{
						"regex":"none",
						"alertText":"* 最多选择 ",
						"alertText2":" 项."},	
					"minCheckbox":{
						"regex":"none",
						"alertText":"* 至少选择 ",
						"alertText2":" 项."},	
					"confirm":{
						"regex":"none",
						"alertText":"* 两次输入不一致,请重新输入."},		
					"telephone":{
						"regex":"/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/",
						"alertText":"* 请输入有效的电话号码,如:010-29292929."},
					"mobilephone":{
						"regex":"/(^[1][0-9]{10}$)/",
						"alertText":"* 请输入有效的手机号码."},	
					"email":{
						"regex":"/^[a-zA-Z0-9_\.\-]+\@([a-zA-Z0-9\-]+\.)+[a-zA-Z0-9]{2,4}$/",
						"alertText":"* 请输入有效的邮件地址."},	
					"date":{
                         "regex":"/^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/",
                         "alertText":"* 请输入有效的日期,如:2008-08-08."},
					"ip":{
                         "regex":"/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/",
                         "alertText":"* 请输入有效的IP."},
					"chinese":{
						"regex":"/^[\u4e00-\u9fa5]+$/",
						"alertText":"* 请输入中文."},
					"url":{
						"regex":"/^[a-zA-z]:\\/\\/[^s]$/",
						"alertText":"* 请输入有效的网址."},
					"zipcode":{
						"regex":"/^[1-9]\\d{5}$/",
						"alertText":"* 请输入有效的邮政编码."},
					"qq":{
						"regex":"/^[1-9]\\d{4,9}$/",
						"alertText":"* 请输入有效的QQ号码."},
					"onlyNumber":{
						"regex":"/^[0-9]+$/",
						"alertText":"* 请输入数字."},
					"onlyNumberWide":{
						"regex":"/^[-]?\\d+(\\.\\d{1,4})?$/",
						"alertText":"* 请输入整数或小数（正负均可）."},
					"onlyDecimal":{
						"regex":"/^[-]?\\d+(\\.\\d{1,4})$/",
						"alertText":"* 请输入4位以内的小数（正负均可）."},
					"illegalLetter":{
						"regex":"/^[^\`\{\}\[!\(\+~@#%\^&\*\)\|\\\\:;\'\"><\?/=_]+$/",
						"alertText":"* 含有非法字符,请检查."},
					"onlyLetter":{
						"regex":"/^[a-zA-Z]+$/",
						"alertText":"* 请输入英文字母."},
					"noSpecialCaracters":{
						"regex":"/^[0-9a-zA-Z]+$/",
						"alertText":"* 请输入英文字母或数字."},	
					"ajaxOrganizationcode":{
						"file":"./company/validateCompanyOrganizationcode.action",
						"extraData": "aaaa=11",
						"alertTextOk":"* 可以使用.",	
						"alertTextLoad":"* 检查中, 请稍后...",
						"alertText":"* 组织机构代码已经存在."}
					}	
		}
	}
})(jQuery);

$(document).ready(function() {	
	$.validationEngineLanguage.newLang()
});