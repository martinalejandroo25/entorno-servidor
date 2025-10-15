<?php

// Hola mundo
/*
este es un comentaro
de varias 
lineas
*/
    echo "Hola, PHP \n";

    //variables
    $my_string =  "Esto es una cadena de texto";
    $my_string = "Aqui cambio el valor de la cadena de texto";
    echo $my_string . "\n";
    echo gettype($my_string) . "\n";

    // $my_string = 6; //Tipado dinamico
    echo $my_string . "\n";
    echo gettype($my_string) . "\n";

    $my_int = 7;
    $my_int = $my_int + 4;
    echo $my_int . "\n";
    echo $my_int - 1 . "\n";
    echo $my_int . "\n";
    echo gettype($my_int) . "\n";


    $my_double = 6.5;
    echo gettype($my_double) . "\n";
    echo $my_int + $my_double . "\n";
    // echo $my_int + $my_double + $my_string . "\n";

    $my_bool = false;
    echo $my_bool . "\n";
    $my_bool = true;
    echo $my_bool . "\n";
    echo gettype($my_bool) . "\n";

    echo "El valor de mi integer es $my_int y el de mi boolean es $my_bool. \n";

    //constantes

    const MY_CONSTANT = "valor de una constante";
    echo MY_CONSTANT . "\n";

    //listas

    $my_array = array($my_string, $my_int, $my_double, $my_bool);
    echo gettype($my_array) . "\n";
    echo $my_array[0] . "\n";
    array_push($my_array, "nuevo valor");
    echo $my_array[4] . "\n";
    print_r($my_array);
    //echo $my_array[5] . "\n"; //error

    $my_dict = array(
        "nombre" => "MoureDev",
        "edad" => 35,
        "altura" => 1.88,
        "developer" => true,
        "hobbies" => array("programar", "bici", "leer")
    );
    echo gettype($my_dict) . "\n";
        echo $my_dict["nombre"] . "\n";
    print_r($my_dict);

    $my_dict2 = array(
        "String" => $my_string,
        "Integer" => $my_int,
        "Double" => $my_double,
        "Boolean" => $my_bool,
    );
    echo gettype($my_dict2) . "\n";
    print_r($my_dict2);

    //set

    array_push($my_array, "Brais");
    array_push($my_array, "Brais");
    print_r($my_array);
    print_r(array_unique($my_array));

    //flujos 

    for ($i=0; $i <= 10; $i++) { 
        echo $i . "\n";
        
    }

    foreach ($my_array as $my_item) {
        echo $my_item . "\n";
    }

    $index = 0;
    while ($index <= sizeof($my_array)-1) {
        echo $my_array[$index] . "\n";
        $index++;
    }

    if ($my_int == 11) {
        echo "El valor de my_int es 11 \n";
    } elseif ($my_int > 11) {
        echo "El valor de my_int es mayor que 11 \n";
    } else {
        echo "El valor de my_int es menor que 11 \n";
    }

    //funciones

    function print_number(int $my_number) {
        echo $my_number . "\n";
    }

    print_number(10.5);
    print_number(11);
    print_number(12);
    // print_number($my_string); //error

    class MyClass {
        public $name;
        public $age;

        function __construct($name, $age) {
            $this->name = $name;
            $this->age = $age;
        }
    }

    $my_class = new MyClass("Brais", 35);
    print_r($my_class);
    echo $my_class->name . "\n";
    $my_class->name = "MoureDev";
    echo $my_class->name . "\n";
    echo gettype($my_class) . "\n";
?>