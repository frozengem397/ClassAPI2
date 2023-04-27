package com.example.demo.planner;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanCartRepository extends MongoRepository<PlanCart, ObjectId> {
    //List<PlanClass> findAllByTerm(String term);
    Optional<PlanCart> findPlanCartByUserId(String userId);
    //HashMap<String, String> findPlanClassesByUserId(String userId);
    //Optional<PlanClass> findPlanClassByTitle(String title);
    //Optional<PlanClass> findPlanClassByCourseId(String id);
}
