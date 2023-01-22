package com.demo.pokepb.exception;

public class MyException extends Exception {
    public MyException(String message){
        super(message);
        System.out.println("独自例外発生：" + message);
    }
}
