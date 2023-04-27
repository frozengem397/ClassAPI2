package com.example.demo.Class;

public class DuplicatedClassException extends Exception{
    public DuplicatedClassException(String errorMessage) {
        super(errorMessage);
        //spring.data.mongodb.database = ${env.MONGO_DATABASE}
        //spring.data.mongodb.uri = mongodb+srv://${env.MONGO_USER}:${env.MONGO_PASSWORD}@${env.MONGO_CLUSTER}
    }
    
}
