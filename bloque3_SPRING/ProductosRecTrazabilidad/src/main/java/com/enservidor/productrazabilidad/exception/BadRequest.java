package com.enservidor.productrazabilidad.exception;

//peticiones incorrectas: ej. intentar registrar un producto que ya existe
public class BadRequest extends RuntimeException{
    public BadRequest(String message) {
        super(message);
    }
}
