function nascondiMessaggio(){
	document.getElementById("messaggioConferma").style.display = "none";
}

function validatePass(password){
	return !!password.match(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%* #+=\(\)\^?&])[A-Za-z\d$@$!%* #+=\(\)\^?&]{3,}$/);
};

