function nascondiMessaggio(){
	document.getElementById("messaggioConferma").style.display = "none";
}

function validatePass(password){
	return !!password.match(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%* #+=\(\)\^?&])[A-Za-z\d$@$!%* #+=\(\)\^?&]{3,}$/);
};

function openModal(imageSrc, altText) {
    document.getElementById('imageModal').style.display = 'block';
    document.getElementById('modalImage').src = imageSrc;
    document.getElementById('modalImage').alt = altText;
}

function closeModal() {
    document.getElementById('imageModal').style.display = 'none';
}

window.onclick = function(event) {
    var modal = document.getElementById('imageModal');
    if (event.target == modal) {
        closeModal();
    }
}