package ru.axtane.springcource.FirstRestApp.util;

public class PersonNotUpdatedException extends RuntimeException{
    public PersonNotUpdatedException(String message) {
        super(message);
    }
}
