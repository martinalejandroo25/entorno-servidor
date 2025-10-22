<!-- 
Tenemos dos cadenas $cadena1 con valor "hola a todo el mundo" y $cadena2 con
valor "mi nombre es <nombre y apellidos del alumno/a>". Se pide:
- $cadena3 contendrá el valor de la concatenación de $cadena1 y $cadena2,
mostrar por pantalla el contenido de $cadena3
- $cadena1 contendrá el resultado de la concatenación de sí misma con
$cadena2, mostrar por pantalla el contenido de $cadena1 -->
<?php
$cadena1 = "Hola a todo el mundo ";
$cadena2 = "Mi nombre es Martin Alejandro";
$cadena3 = $cadena1 . $cadena2;
$cadena1 = $cadena1 . $cadena2;
?>
<?= "<h1>$cadena1</h1>"?>
<?= "<h1>$cadena3</h1>"?>