package com.example.demo.Class;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.mongodb.DuplicateKeyException;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Class> allClasses(){
        return classRepository.findAll();
    }
    public Optional<Class> singleClass(String courseId){
        return classRepository.findClassByCourseId(courseId);
    }

    public Class createClass(Class newCourse) throws DuplicatedClassException{

        Optional<Class> checked = classRepository.findClassByCourseId(newCourse.getCourseId());
       
        if(checked.isPresent()){
            throw new DuplicatedClassException("duplicated");
        }
        Class n_class = new Class(newCourse);
        classRepository.insert(n_class);
        return n_class;
    }

    public Class updateClass(String courseId, Class newCourse) throws NotFoundException {
        Optional<Class> updated = classRepository.findClassByCourseId(courseId);
        if (updated.isPresent()) {
            Class _class = updated.get();
            _class.setCourseId(newCourse.getCourseId());
            _class.setCourseLink(newCourse.getCourseLink());
            _class.setPre(newCourse.getPre());
            _class.setTerm(newCourse.getTerm());
            _class.setTitle(newCourse.getTitle());
            classRepository.save(_class);
            return _class;
        }
        else {
            throw new NotFoundException();
        }
    }

    public void deleteClass(String courseId) throws NotFoundException {
        Optional<Class> deleted = classRepository.findClassByCourseId(courseId);
        if(deleted.isPresent()){
            Class _class = deleted.get();
            classRepository.delete(_class);
        } else {
            throw new NotFoundException();
        }
    }
 }
