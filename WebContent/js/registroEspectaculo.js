function habilitarSesiones(opcion) {
	if(opcion.value == "PUNTUAL" && document.querySelector('#titulo').value != "" && document.querySelector('#descripcion').value != "" && document.querySelector('#categoria').value != "vacio") {
		mostrar = document.querySelector('#horaInput');
		mostrar.disabled = false;
		mostrar = document.querySelector('#puntualSesion');
		mostrar.disabled = false;
		mostrar = document.querySelector('#inicioTemporada');
		mostrar.disabled = true;
		mostrar = document.querySelector('#finTemporada');
		mostrar.disabled = true;
		mostrar = document.querySelector('#entradas');
		mostrar.disabled = false;
	}
	else if(opcion.value == "PASEMULTIPLE" && document.querySelector('#titulo').value != "" && document.querySelector('#descripcion').value != "" && document.querySelector('#categoria').value != "vacio") {
		alert("Pulse el botón 'Registrar' y posteriormente podrá añadir las sesiones del espectáculo");
		mostrar = document.querySelector('#inicioTemporada');
		mostrar.disabled = true;
		mostrar = document.querySelector('#finTemporada');
		mostrar.disabled = true;
		mostrar = document.querySelector('#horaInput');
		mostrar.disabled = true;
		mostrar = document.querySelector('#puntualSesion');
		mostrar.disabled = true;
		mostrar = document.querySelector('#entradas');
		mostrar.disabled = true;
	}
	else if(opcion.value == "TEMPORADA" && document.querySelector('#titulo').value != "" && document.querySelector('#descripcion').value != "" && document.querySelector('#categoria').value != "vacio") {
		mostrar = document.querySelector('#inicioTemporada');
		mostrar.disabled = false;
		mostrar = document.querySelector('#finTemporada');
		mostrar.disabled = false;
		mostrar = document.querySelector('#horaInput');
		mostrar.disabled = false;
		mostrar = document.querySelector('#puntualSesion');
		mostrar.disabled = true;
		mostrar = document.querySelector('#entradas');
		mostrar.disabled = false;
	}
	else {
		alert("Deber completar los campos de titulo, descripcion y categoria");
	}
}