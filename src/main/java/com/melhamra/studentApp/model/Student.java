package com.melhamra.studentApp.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Validated
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Should not be empty")
    @Size(min = 5,max = 20, message = "The size must be between 5 and 20")
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @NotEmpty(message = "Should not be empty")
    @Email(message = "Must be a syntactically correct email address")
    private String email;
    private String photo;

}
