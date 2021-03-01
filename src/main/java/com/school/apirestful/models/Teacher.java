package com.school.apirestful.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@JsonIgnoreProperties(value = {"students"},allowGetters = false, allowSetters = true)
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "nationality", length = 128)
    private String nationality;

    @Column(name = "description", length = 128)
    private String description;

    @Column(name = "email", length = 128)
    private String email;

    @JsonIgnoreProperties("teachers")
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "teacher_student", joinColumns = @JoinColumn(name = "teacher_id" ),
    inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
