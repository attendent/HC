function test() {
	if (document.form1.userName.value.length > 16) {
		alert("用户名不能超过16个字符");
		document.form1.userName.focus();
		return false;
	}
	if (document.form1.password.value.length > 16) {
		alert("密码不能超过16个字符");
		document.form1.password.focus();
		return false;
	}
	if (document.form1.userName.value.length == 0) {
		alert("用户名不能为空");
		document.form1.userName.focus();
		return false;
	}
	if (document.form1.password.value.length == 0) {
		alert("密码不能为空");
		document.form1.password.focus();
		return false;
	}
	if (document.form1.nickName.value.length > 16) {
		alert("昵称不能超过16个字符");
		document.form1.nickName.focus();
		return false;
	}
	if (document.form1.nickName.value.length == 0) {
		alert("昵称不能为空");
		document.form1.nickName.focus();
		return false;
	}
	var temp = document.form1.mail.value; // 对电子邮件的验证 
	var myreg = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
	if (!myreg.test(temp.value)) {
		alert('请输入有效的E_mail！');
		document.form1.mail.focus();
		return false;
	}
}

function change() {
	document.getElementById("myimg").src = "pictureCode?"
			+ new Date().getTime();
}