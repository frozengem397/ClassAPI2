package com.example.demo.planner;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Class.ClassRepository;
import com.example.demo.Class.Class;
import com.example.demo.Class.DuplicatedClassException;

@Service
public class PlanService {
    @Autowired
    // private PlanRepository planRepository;
    private PlanCartRepository planCartRepository;
    @Autowired
    private ClassRepository classRepository;

    // public List<PlanClass> allPlanClass(String term) {
    //     return planRepository.findAllByTerm(term);
    // }

    public List<PlanCart> getAll() {
        return planCartRepository.findAll();
    }

    public Optional<PlanCart> userCart(String userId) {
        
        return planCartRepository.findPlanCartByUserId(userId); 
    }

    // public List<PlanClass> allPlanClass() {
    //     return planRepository.findAll();
    // }

    public static boolean ContainsCaseInsensitive(List<String> searchList, String searchTerm)
    {
        for (String item : searchList)
        {
            if (item.equalsIgnoreCase(searchTerm)) 
                return true;
        }
        return false;
    }
    
    // public PlanClass createPlanClass(PlanClass newPlan) throws NotFoundException, PreNeedException, DuplicatedClassException, NotinTermException{
    //     Optional<Class> checked = classRepository.findClassByCourseId(newPlan.getCourseId());
    //     Optional<PlanClass> hasPlan = planRepository.findPlanClassByCourseId(newPlan.getCourseId());
        
    //     if(!checked.isPresent()) {
            
    //         throw new NotFoundException();
    //     };
    //     if(hasPlan.isPresent()) {
    //         throw new DuplicatedClassException("duplicated");
    //     };
        
    //     if(!ContainsCaseInsensitive(checked.get().getTerm(),newPlan.getTerm())){
    //         throw new NotinTermException("the course is not available in the term");
    //     }

    //     List<String> pres = checked.get().getPre();
    //     for(String pre: pres) {
    //         if(!(planRepository.findPlanClassByTitle(pre).isPresent())){
    //             throw new PreNeedException("Need:" + pre);
    //         };
    //     };
    //     PlanClass np = new PlanClass(newPlan);
    //     planRepository.insert(np);
    //     return np;
    // };

    public String findCourseId(String title) {
        Optional<Class> course = classRepository.findClassByTitle(title);
        String courseId = course.get().getCourseId();
        return courseId;
    }

    public PlanCart createCartPlanClass(PlanClass newPlan, String userId) throws NotFoundException, PreNeedException, DuplicatedClassException, NotinTermException{
        
        Optional<Class> checked = classRepository.findClassByCourseId(newPlan.getCourseId());
        

        //Optional<PlanClass> hasPlan = planRepository.findPlanClassByCourseId(newPlan.getCourseId());
        Optional<PlanCart> cart = planCartRepository.findPlanCartByUserId(userId);
      

        if(!checked.isPresent()) {
            throw new NotFoundException();
        };

        if(!ContainsCaseInsensitive(checked.get().getTerm(),newPlan.getTerm())){
            throw new NotinTermException("the course is not available in the term");
        };
        List<String> pres = checked.get().getPre();

        if(!cart.isPresent()) {
            PlanCart newCart = new PlanCart(userId);
            planCartRepository.insert(newCart);
            cart = planCartRepository.findPlanCartByUserId(userId);
            // return newCart;
        };

        for(String pre: pres) {
            System.out.println(pre);
            if(!(cart.get().hasPlan(findCourseId(pre)))){
                throw new PreNeedException("Need:" + pre);
            };
        };

        PlanClass np = new PlanClass(newPlan);
        cart.get().addNew(np);
        
        planCartRepository.save(cart.get());
        return cart.get();
    };
    
    // public void deletePlanClass(String userId, String courseId) throws NotFoundException {
    //     Optional<PlanClass> deleted = planRepository.findPlanClassByCourseId(id);
    //     if(deleted.isPresent()){
    //         PlanClass _planClass = deleted.get();
    //         planRepository.delete(_planClass);
    //     } else {
    //         throw new NotFoundException();
    //     }
        
    // };

    public void deletePlanClass(String userId, String courseId) throws NotFoundException {
        Optional<PlanCart> cart = planCartRepository.findPlanCartByUserId(userId);
        cart.get().delClass(courseId);
        planCartRepository.save(cart.get());

    }
}
