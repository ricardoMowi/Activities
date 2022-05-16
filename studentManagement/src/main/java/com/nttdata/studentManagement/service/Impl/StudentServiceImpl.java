package com.nttdata.studentManagement.service.Impl;

import java.util.List;

import com.nttdata.studentManagement.entity.Student;
import com.nttdata.studentManagement.repository.StudentRepository;
import com.nttdata.studentManagement.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    
    


    @Override
    public List<Student> getAll() {
      return studentRepository.findAll();
    }

    @Override
    public Student createStudent(Student student){
      return studentRepository.save(student);
    }



}
