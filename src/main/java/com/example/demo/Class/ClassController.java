package com.example.demo.Class;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/classes")
public class ClassController {
    @Autowired
    private ClassService classService;

    @GetMapping
    public ResponseEntity<List<Class>> getAllClasses() {
        return new ResponseEntity<List<Class>>(classService.allClasses(),HttpStatus.OK);
    }
    
    @GetMapping("/{courseId}")
    public ResponseEntity<Optional<Class>> getSingleClass(@PathVariable String courseId){
        return new ResponseEntity<Optional<Class>>(classService.singleClass(courseId), HttpStatus.OK);
        
    }
    @PostMapping
    public ResponseEntity<Class> createClass(@RequestBody Class course){
        try{
            return new ResponseEntity<Class>(classService.createClass(course),HttpStatus.OK);

        } catch(DuplicatedClassException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{courseId}")
    public ResponseEntity<Class> updateClass(@PathVariable String courseId, @RequestBody Class course) {
        try {
            return new ResponseEntity<Class>(classService.updateClass(courseId, course), HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Optional<Class>> deleteClass(@PathVariable String courseId) {
        try {
            classService.deleteClass(courseId);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }
}
