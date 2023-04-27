package com.example.demo.Class;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "classes")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Class {
    @Id
    private ObjectId id;
    private String courseId;
    private String title;
    private List<String> term;
    private String courseLink;
    private List<String> pre;

    public Class(Class newClass){
        this.id = newClass.id;
        this.courseId = newClass.courseId;
        this.title = newClass.title;
        this.term = newClass.term;
        this.courseLink = newClass.courseLink;
        this.pre = newClass.pre; 
    }
    
}
