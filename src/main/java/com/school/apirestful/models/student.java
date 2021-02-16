package com.school.apirestful.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "student")
public class student {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
