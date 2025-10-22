<?php
    $name = "Miguel";
    $isDev = true;
    $age = 21;

    define('LOGO_URL', 'https://upload.wikimedia.org/wikipedia/commons/2/27/PHP-logo.svg');

    const NOMBRE = "Miguel";

    $newAge = 39 + '1';
    $newAge2 = $age . 1;
    
    var_dump($name);
    var_dump($isDev);
    var_dump($age);

    echo gettype($name);
    echo gettype($isDev);
    echo gettype($age);

    is_string($name);
    is_bool($isDev);
    is_int($age);

    $age = (bool) 21;

    $output = "Hola $name";
    $output .= ", que tal?";
    

?>

<img src="<?= LOGO_URL ?>" alt="PHP Logo" width="200">
<h1>
    <!-- <?= "Hola" . $name ?>
    <?=$name;?>
    <?=$newAge?>
    <?=$newAge2?>
    <?= "Con una edad de " . $age . " años" ?>
    <?=
    "Hola2"
    . $name
    . " con una edad de "
    . $age
    ?> 
    <?= $output ?>

    <?=NOMBRE;?>
    -->
</h1>
<?php
if ($age >= 65) {
    echo "<h2>Eres viejo</h2>";
} elseif ($age >= 18 && $age < 65) {
    echo "<h2>Eres mayor de edad</h2>"; 
} else {
    echo "<h2>Eres menor de edad</h2>";
}
?>

<?php if ($age >= 18): ?>
    <h2>Eres mayor de edad</h2>
<?php else: ?>
    <h2>Eres menor de edad</h2>
<?php endif; ?>

<h1>
    <?php
$age = 17;
$outputAge = $age >= 18 
? "Eres mayor de edad" 
: "Eres menor de edad";

$outputAge2 = match ($age) {
    0, 1, 2 => "Eres un bebé",
    3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 => "Eres un niño",
    18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29 => "Eres un joven",
    30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49 => "Eres un adulto",
    default => "Eres un anciano",
}
?>
</h1>











<style>

    :root {
        color-scheme: light dark;
    }

    body {
        display: grid;
        place-content: center;
    }

</style>