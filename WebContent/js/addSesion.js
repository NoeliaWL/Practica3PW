inputTitulo = document.querySelector('#titulo');
alert(inputTitulo.value);
if(inputTitulo.value == "") {
	inputTitulo.disabled = false;
}
else {
	inputTitulo.disabled = true;
}