package com.example.demo.planner;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Class.DuplicatedClassException;

@RestController
@RequestMapping("/api/v1/plan")
public class PlanController {
    @Autowired

    private PlanService planService;

    @GetMapping
    public ResponseEntity<List<PlanCart>> getAll() {
        return new ResponseEntity<>(planService.getAll(),HttpStatus.OK);
    };

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<PlanCart>> getAllClasses(@PathVariable String userId) {
        return new ResponseEntity<Optional<PlanCart>>(planService.userCart(userId),HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity register(@RequestBody PlanClass course, @PathVariable String userId) throws NotFoundException {
        try {
            return new ResponseEntity<>(planService.createCartPlanClass(course,userId), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return ResponseEntity.badRequest().body("not found");
        }
        catch (PreNeedException e) {
            return ResponseEntity.badRequest().body("need pre");
        } 
        catch (DuplicatedClassException e) {
            return ResponseEntity.badRequest().body("duplicated");
        }
        catch (NotinTermException e) {
            return ResponseEntity.badRequest().body("not available in the term");
        }
    }

    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<Optional<PlanClass>> deletePlan(@PathVariable String id, @PathVariable String userId) {
        try {
            planService.deletePlanClass(userId, id);;
            return new ResponseEntity<>(HttpStatus.OK);

        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    
}
