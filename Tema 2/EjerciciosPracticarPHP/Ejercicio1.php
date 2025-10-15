<!-- Partiendo de 2 variables $primera y $segunda con valores aleatorios, hacer una
página PHP que calcule y muestre por pantalla:
- la diferencia de $primera menos $segunda
- la división de $primera entre $segunda
Añade un comentario que explique la función de generar números aleatorios -->

<?php
$primera = rand(1, 100); // Genera un número aleatorio entre 1 y 100
$segunda = rand(1, 100); // Genera otro número aleatorio entre 1 y 100
$diferencia = $primera - $segunda; // Calcula la diferencia
$division = $segunda != 0 ? $primera / $segunda : 'Indefinido'; // Calcula la división, evitando división por cero
?>

<?="<h1>$diferencia</h1>" ?>
<?="<h1>$division</h1>" ?>