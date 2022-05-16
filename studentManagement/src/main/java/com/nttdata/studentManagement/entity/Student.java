package com.nttdata.studentManagement.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import org.springframework.data.annotation.Id;

@Document(collection="student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    private String id;
    private String fullName;
    //private String lastName;
    private String address;
    private String phone;
    private String status;
    //private String[] courses; 
    private List<String> courses;
}
