package com.example.demo.Class;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassRepository extends MongoRepository<Class, ObjectId>{
    Optional<Class> findClassByCourseId(String courseId);
    Optional<Class> findClassByTitle(String title);

    
}
