package com.testorangeci.gestcom.Exceptions;

public class OrderNotFoundExeption extends RuntimeException{
    public OrderNotFoundExeption(String message) {
        super(message);
    }
}
