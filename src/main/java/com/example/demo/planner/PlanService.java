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
    private PlanCartRepository planCartRepository;
    @Autowired
    private ClassRepository classRepository;

    public List<PlanCart> getAll() {
        return planCartRepository.findAll();
    }

    public Optional<PlanCart> userCart(String userId) {
        
        return planCartRepository.findPlanCartByUserId(userId); 
    }

    public static boolean ContainsCaseInsensitive(List<String> searchList, String searchTerm)
    {
        for (String item : searchList)
        {
            if (item.equalsIgnoreCase(searchTerm)) 
                return true;
        }
        return false;
    }

    public String findCourseId(String title) {
        Optional<Class> course = classRepository.findClassByTitle(title);
        String courseId = course.get().getCourseId();
        return courseId;
    }

    public PlanCart createCartPlanClass(PlanClass newPlan, String userId) throws NotFoundException, PreNeedException, DuplicatedClassException, NotinTermException{
        
        Optional<Class> checked = classRepository.findClassByCourseId(newPlan.getCourseId());
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
        };

        for(String pre: pres) {
            System.out.println(pre);
            if(!(cart.get().hasPlan(findCourseId(pre)))){
                throw new PreNeedException("Need:" + pre);
            };
        };
        PlanClass np = new PlanClass(newPlan);
        cart.get().addNew(np);
        cart.get().setTotal();      
        planCartRepository.save(cart.get());
        return cart.get();
    };

    public void deletePlanClass(String userId, String courseId) throws NotFoundException {
        Optional<PlanCart> cart = planCartRepository.findPlanCartByUserId(userId);
        cart.get().delClass(courseId);
        cart.get().setTotal();
        if(cart.get().getPlanClasses().size() ==0){
            planCartRepository.delete(cart.get());
        } else{
            planCartRepository.save(cart.get());
        }
    }
}
