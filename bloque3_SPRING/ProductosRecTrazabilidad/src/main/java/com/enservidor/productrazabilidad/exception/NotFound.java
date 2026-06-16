package com.enservidor.productrazabilidad.exception;

//se lanza cuando un recurso no existe en la bbdd, ej. id de un producto no existente
public class NotFound  extends RuntimeException{
    public NotFound(String message){
        super(message);
    }
}
