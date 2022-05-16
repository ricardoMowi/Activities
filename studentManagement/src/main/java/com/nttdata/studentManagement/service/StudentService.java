package com.nttdata.studentManagement.service;

import java.util.List;

import com.nttdata.studentManagement.entity.Student;

public interface StudentService {
    List<Student> getAll();
    Student createStudent(Student new_student);
}
