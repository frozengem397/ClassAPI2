package com.example.demo.planner;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends MongoRepository<PlanClass, ObjectId> {
    List<PlanClass> findAllByTerm(String term);
    Optional<PlanClass> findPlanClassByTitle(String title);
    Optional<PlanClass> findPlanClassByCourseId(String id);
}
