const utils = [
    "Lapiz",
    "Cuaderno",
    "Borrador",
    "Regla",
    "Mochila",
    "Tijeras",
    "Pegamento",
    "Colores"
];

//ForEach -> recorre la lista
utils.forEach((elemento) => console.log(elemento))

//Map -> cambia cada elemento
const enunciados = utils.map((elemento) => "El util es: " + elemento);

//Filter -> filtrar
utils.filter((elemento) => {
    return elemento[0] === "L"
})

//Find 
const found = utils.find((elemento) => elemento[0] === "C");
console.log(found);

//Push -> agrega un elemento
utils.push();

//Eliminar el ultimo elemento
utils.pop();


//Eliminar varios elementos en masa
utils.splice();

//Elimanar solo uno
