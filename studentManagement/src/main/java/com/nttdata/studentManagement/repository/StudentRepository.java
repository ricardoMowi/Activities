package com.nttdata.studentManagement.repository;

import java.util.List;

import com.nttdata.studentManagement.entity.Student;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository  extends MongoRepository <Student, String> {
    List<Student> findByAddressStartsWith(String text);
    List<Student> findByFullNameContains(String text);
    List<Student> findByCourses(String text);
}
