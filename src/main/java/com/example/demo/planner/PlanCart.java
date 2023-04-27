package com.example.demo.planner;
import java.util.HashMap;
import java.util.List;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Document(collection = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanCart {
    @Id
    private ObjectId id;
    private String userId;
    //private List<PlanClass> planClasses;
    private Integer total;
    private HashMap<String, PlanClass> planClasses;

    public PlanCart(String id) {
        this.userId = id;
        this.total = 0;
        this.planClasses = new HashMap<String, PlanClass>();    
    }

    public void addNew(PlanClass newClass) {
        this.planClasses.put(newClass.getCourseId(),newClass);
    
        
    };
    public void delClass(String courseId) {
        this.planClasses.remove(courseId);
    };

    public boolean hasPlan(String courseId) {
        return this.planClasses.containsKey(courseId);
    };
    
    public void setTotal() {
        this.total = this.planClasses.size();
    }; 
}
