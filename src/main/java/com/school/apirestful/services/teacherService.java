package com.school.apirestful.services;

import com.school.apirestful.exceptions.RecordNotFoundException;
import com.school.apirestful.models.teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.apirestful.repositories.teacherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class teacherService {

    @Autowired
    teacherRepository repository;

    public List<teacher> getAllTeachers()
    {
        List<teacher> teacherList = repository.findAll();

        if(teacherList.size() > 0) {
            return teacherList;
        } else {
            return new ArrayList<teacher>();
        }
    }

    public teacher getTeacherById(Long id) throws RecordNotFoundException
    {
        Optional<teacher> teacher = repository.findById(id);

        if(teacher.isPresent()) {
            return teacher.get();
        } else {
            throw new RecordNotFoundException("No teacher record exist for given id",id);
        }
    }

    public teacher createTeacher(teacher entity){
        entity = repository.save(entity);
        return entity;
    }

    public teacher UpdateTeacher(teacher entity) throws RecordNotFoundException
    {

        if(entity.getId()!=null)
        {
            Optional<teacher> teacher = repository.findById(entity.getId());

            if(teacher.isPresent())
            {
                teacher newEntity = teacher.get();
                //newEntity.setId(entity.getId());
                newEntity.setName(entity.getName());
                newEntity.setNationality(entity.getNationality());
                newEntity.setDescription(entity.getDescription());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Teacher not found",entity.getId());
            }
        }else{
            throw new RecordNotFoundException("No id of teacher given",0l);
        }
    }

    public void deleteTeacherById(Long id) throws RecordNotFoundException
    {
        Optional<teacher> teacher = repository.findById(id);

        if(teacher.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No teacher record exist for given id",id);
        }
    }


    public List<teacher> getTeacherByName(String name) {
        List<teacher> teacherList = repository.getByName(name);

        if(teacherList.size() > 0) {
            return teacherList;
        } else {
            return new ArrayList<teacher>();
        }
    }


}
