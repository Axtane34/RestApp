package ru.axtane.springcource.FirstRestApp.util;

public class PersonNotCreatedException extends RuntimeException{
    public PersonNotCreatedException(String msg){
        super(msg);
    }
}
