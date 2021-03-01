package com.school.apirestful.services;

import com.school.apirestful.exceptions.RecordNotFoundException;
import com.school.apirestful.models.Teacher;
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

    public List<Teacher> getAllTeachers()
    {
        List<Teacher> teacherList = repository.findAll();

        if(teacherList.size() > 0) {
            return teacherList;
        } else {
            return new ArrayList<Teacher>();
        }
    }

    public Teacher getTeacherById(Long id) throws RecordNotFoundException
    {
        Optional<Teacher> teacher = repository.findById(id);

        if(teacher.isPresent()) {
            return teacher.get();
        } else {
            throw new RecordNotFoundException("No teacher record exist for given id",id);
        }
    }

    public Teacher createTeacher(Teacher entity){
        entity = repository.save(entity);
        return entity;
    }

    public Teacher UpdateTeacher(Teacher entity) throws RecordNotFoundException
    {

        if(entity.getId()!=null)
        {
            Optional<Teacher> teacher = repository.findById(entity.getId());

            if(teacher.isPresent())
            {
                Teacher newEntity = teacher.get();
                //newEntity.setId(entity.getId());
                newEntity.setName(entity.getName());
                newEntity.setNationality(entity.getNationality());
                newEntity.setDescription(entity.getDescription());
                newEntity.setEmail(entity.getEmail());
                if (entity.getStudents()!=null) {
                    newEntity.setStudents(entity.getStudents());
                }
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
        Optional<Teacher> teacher = repository.findById(id);

        if(teacher.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No teacher record exist for given id",id);
        }
    }


    public List<Teacher> getTeacherByName(String name) {
        List<Teacher> teacherList = repository.getByName(name);

        if(teacherList.size() > 0) {
            return teacherList;
        } else {
            return new ArrayList<Teacher>();
        }
    }

    public List<Teacher> getTeacherByStudentId(Long id, int page){

        int limit = 3;

        int offset = (limit*page)-limit;

        List<Teacher> teachers = repository.getTeacherByStudentId(id,limit,offset);

        if(teachers.size() > 0){
            return teachers;
        }else{
            return new ArrayList<>();
        }
    }

}
