window.onload = function() {
    fetch("http://localhost:8080/odontologos/listar")
    .then(function(respuesta){
        return respuesta.json();
    })
    .then(function(informacion) {
        console.log(informacion);
        //console.log(informacion.domicilioSalidaDto);
        for (let i = 0; i < informacion.length; i++) {
            let datos = "<p> ID: " + informacion[i].id + "</p>";
            datos += "<p> Nombre: " + informacion[i].nombre + " " + informacion[i].apellido + "</p>";
            datos += "<p> Numero de Matricula: " + informacion[i].numeroDeMatricula + "</p>";
            document.querySelector("ul").innerHTML += "<li>" + datos + "</li>"
        
        }


    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error al listar odontologos');
    });


}
