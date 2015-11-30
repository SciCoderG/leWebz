/**
 * 
 */

function validateForm() {
	var form = document.forms["newsletter"];
	var isCorrect = true;

	var nameformat = /^([a-zA-Z]{2,}){1}\s*$/;
	v
	var phoneformat = /^(\+\d{2}\s)?(\d+\s?)*(\d+)+\s*$/;
	var plzformat = /^\d{5}\s*$/;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+\s*$/;
	// from:
	// https://github.com/jzaefferer/jquery-validation/blob/master/src/core.js#L1191
	var urlformat = /^(?:(?:(?:https?|ftp):)?\/\/)?(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[/?#]\S*)?$/;

	isCorrect = isCorrect & checkFormValue(form, "fname", nameformat);
	isCorrect = isCorrect & checkFormValue(form, "lname", nameformat);
	isCorrect = isCorrect & checkFormValue(form, "street", streetformat);
	isCorrect = isCorrect & checkFormValue(form, "plz", plzformat);
	isCorrect = isCorrect & checkFormValue(form, "city", nameformat);
	isCorrect = isCorrect & checkFormValue(form, "phone", phoneformat);
	isCorrect = isCorrect & checkFormValue(form, "mail", mailformat);
	isCorrect = isCorrect & checkFormValue(form, "url", urlformat);
	
	if(isCorrect){
		alert("Form successfully submitted!")
		form.submit();
	}
}

function checkFormValue(form, valueName, format) {
	var noticeColor = "#FF1111";
	var normalColor = "#555555";
	if (!form[valueName].value.match(format)) {
		form[valueName].style.backgroundColor = noticeColor;
		return false;
	} else {
		form[valueName].style.backgroundColor = normalColor;
		return true;
	}
}