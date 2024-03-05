package com.kunal.allmappings.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(mappedBy = "department")
    @JsonManagedReference
    private Manager manager;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Employee> employees;

    @ManyToMany(mappedBy = "departments")
    @JsonBackReference
    private List<Project> projects;

}
