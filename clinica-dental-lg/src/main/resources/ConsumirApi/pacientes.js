window.onload = function() {
    fetch("http://localhost:8080/pacientes/listar")
    .then(function(respuesta){
        return respuesta.json();
    })
    .then(function(informacion) {
        console.log(informacion);
        //console.log(informacion.domicilioSalidaDto);
        for (let i = 0; i < informacion.length; i++) {
            let datos = "<p> ID: " + informacion[i].id + "</p>";
            datos += "<p> Nombre: " + informacion[i].nombre + " " + informacion[i].apellido + "</p>";
            //datos += "<p>" + informacion[i].apellido + "</p>";
            datos += "<p> DNI: " + informacion[i].dni + "</p>";
            datos += "<p> Fecha Ingreso: " + informacion[i].fechaIngreso + "</p>";
            //datos += "<p>" + informacion[i].domicilioSalidaDto + "</p>";
            //datos += "<p>" + informacion[i].domicilioSalidaDto[i] + "</p>";

            document.querySelector("ul").innerHTML += "<li>" + datos + "</li>"

        }


    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error al listar pacientes');
    });


}
