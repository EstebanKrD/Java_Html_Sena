function hola() {
    console.log("Hola");
}

function saludar(name, callback) {
    console.log(`Hola ${name}`);
    callback();
}

saludar("Juan", hola);

