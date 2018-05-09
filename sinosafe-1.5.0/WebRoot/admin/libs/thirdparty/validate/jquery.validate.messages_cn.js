/*
 * Translated default messages for the jQuery validation plugin.
 * Language: CN
 * Author: Fayland Lam <fayland at gmail dot com>
 */
jQuery.extend(jQuery.validator.messages, {
        required: "必选字段",
		remote: "请修正该字段",
		email: "请输入正确格式的电子邮件",
		url: "请输入合法的网址",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期 (ISO).",
		number: "请输入合法的数字",
		digits: "只能输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请再次输入相同的值",
		accept: "请输入拥有合法后缀名的字符串",
		maxlength: jQuery.format("请输入一个长度最多是 {0} 的字符串"),
		minlength: jQuery.format("请输入一个长度最少是 {0} 的字符串"),
		rangelength: jQuery.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
		range: jQuery.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max: jQuery.format("请输入一个最大为 {0} 的值"),
		min: jQuery.format("请输入一个最小为 {0} 的值")
});

jQuery.validator.addMethod("phone", function(value, element, param) {
    return this.optional(element) || /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}(-\d{1,4})?$/.test(value)|| /^1(\d){10}$/.test(value);
}, "请输入合法的电话号码");

jQuery.validator.addMethod("postCode", function(value, element, param) {
    return this.optional(element) || /^[1-9]{1}(\d){5}$/.test(value);
}, "请输入合法的邮政编码");
jQuery.validator.addMethod("mobile", function(value, element, param) {
    return this.optional(element) || /^1(\d){10}$/.test(value);
}, "请输入合法的移动电话");
jQuery.validator.addMethod("pattern", function(value, element, param) {
    return this.optional(element) || param.test(value);
}, "Invalid format.");
jQuery.validator.addMethod("maxByteLength", function(value, element, param) {
	if (this.optional(element)) return "dependency-mismatch";
	var byteLength=value.length;
	var regRet=value.match(/[^\x00-\xff]/g);
	if(regRet!=null){
		byteLength+=regRet.length;
	}
    return byteLength<=param;
}, jQuery.format("请输入一个字节长度不大于 {0} 的字符串"));

jQuery.validator.addMethod("cascadeSvrVld", function(value, element, param) {
	if (this.optional(element)) return "dependency-mismatch";
	var cascadeEl;
	for(var i=0;i<param.length;i++){
		cascadeEl=$(param[i]);
		cascadeEl.attr('ignorPrevious',true);
		this.check(cascadeEl);
	}
    return true;
}, "");

jQuery.validator.addMethod("serverValid", function(value, element, param) {
	if (this.optional(element)) return "dependency-mismatch";
	var previous = this.previousValue(element);
	if($(element).attr('ignorPrevious')){
		previous.old=null;
		$(element).removeAttr('ignorPrevious')
	}
	if (!this.settings.messages[element.name] )
		this.settings.messages[element.name] = {};
	if ( previous.old !== value ) {
		previous.old = value;
		var validator = this;
		this.startRequest(element);
		var data = {};
		data[element.name] = encodeURIComponent(value);
		if(typeof param.data=='function'){
			var paramData=param.data.call(this);
			$.extend(data,paramData);
		}
		$.post(param.url,data,function(response){
			if (response.state==0) {//验证通过
				var submitted = validator.formSubmitted;
				validator.prepareElement(element);
				validator.formSubmitted = submitted;
				validator.successList.push(element);
				validator.showErrors();
				previous.valid = true;
				previous.message=null;
				validator.settings.messages[element.name].serverValid=undefined;
			} else {
				var errors = {};
				errors[element.name] =  response.msg || "服务端验证不通过";
				validator.showErrors(errors);
				previous.valid = false;
				validator.settings.messages[element.name].serverValid=errors[element.name];
			}
			validator.stopRequest(element, response);
		},'json');
		return "pending";
	} else if( this.pending[element.name] ) {
		return "pending";
	}
	return previous.valid;
}, "服务端验证不通过");
if(jQuery.metadata){
	jQuery.metadata.setType("attr","validate");
}
$.validator.setDefaults({
	errorElement: "em",
	errorPlacement: function(error, element) {
		var TeElement=element.parent("td");
		TeElement.append("<br/>");
		TeElement.append(error);
	}
});
