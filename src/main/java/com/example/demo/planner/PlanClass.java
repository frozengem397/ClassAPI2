package com.example.demo.planner;
import com.example.demo.Class.Class;

import java.util.List;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Document(collection = "plan")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PlanClass {
    
    //private String id;
    private String courseId;
    private String title;
    private String term;
    //private PlanCart cart;
    

    public PlanClass(PlanClass newPlan){
        //this.id = newPlan.id;
        this.courseId = newPlan.courseId;
        this.title = newPlan.title;
        this.term = newPlan.term;
        //this.cart = newPlan.cart;
    }
    
}