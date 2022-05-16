package com.nttdata.studentManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nttdata.studentManagement.entity.Student;
import com.nttdata.studentManagement.repository.StudentRepository;
import com.nttdata.studentManagement.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepo;
    
    //CRUD
    @GetMapping(value = "/all")
    public List<Student> getAll() {
        log.info("lista todos");
        return studentService.getAll();
    }  

    @GetMapping(value = "/query1/{text}")
    public List<Student> query1(@PathVariable("text") String text) {
        log.info("listar direcciones que inician con");
        return studentRepo.findByAddressStartsWith(text);
    }  

    @GetMapping(value = "/query2/{text}")
    public List<Student> query2(@PathVariable("text") String text) {
        log.info("listar nombre que contienen");
        return studentRepo.findByFullNameContains(text);
    } 

    @GetMapping(value = "/query3/{text}")
    public ResponseEntity<Map<String, Object>> query3(@PathVariable("text") String text) {
        log.info("listar estudiantes por cursos ");
        Map<String, Object> salida = new HashMap<>();
        salida.put("cant_alumnos", studentRepo.findByCourses(text).size());    
        return ResponseEntity.ok(salida);    
        //return studentRepo.findByCourses(text);
    } 

    @GetMapping("getStudent/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getStudentData(@PathVariable("id") String id){
      Map<String, Object> salida = new HashMap<>();
      Optional<Student> student_doc = studentRepo.findById(id);
      if (student_doc.isPresent()) {
        salida.put("student", student_doc);
      }else{
        salida.put("status", "Id de Estudiante no encontrado");
      }
      return ResponseEntity.ok(salida);
    }



    @PostMapping(value = "/create")
    public Student createStudent(@RequestBody Student new_student){
        new_student.setStatus("ACTIVE");
        return studentService.createStudent(new_student);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") String id, @RequestBody Student temp) {
      Optional<Student> student = studentRepo.findById(id);
      if (student.isPresent()) {
        temp.setId(id);
        return new ResponseEntity<>(studentService.createStudent(temp), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PutMapping("setInactive/{id}")
    public ResponseEntity<Student> setInactive(@PathVariable("id") String id, @RequestBody Student temp_student) {
      Optional<Student> student_doc = studentRepo.findById(id);
      if (student_doc.isPresent()) {
        Student _student = student_doc.get();
        _student.setStatus("INACTIVE");
        return new ResponseEntity<>(studentRepo.save(_student), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } 

}
