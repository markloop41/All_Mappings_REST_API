package com.kunal.allmappings.controller;

import com.kunal.allmappings.entities.Department;
import com.kunal.allmappings.entities.Employee;
import com.kunal.allmappings.entities.Manager;
import com.kunal.allmappings.entities.Project;
import com.kunal.allmappings.repository.DepartmentRepository;
import com.kunal.allmappings.repository.EmployeeRepository;
import com.kunal.allmappings.repository.ManagerRepository;
import com.kunal.allmappings.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class OrganizationController {

    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;
    private ManagerRepository managerRepository;
    private ProjectRepository projectRepository;

    @PostMapping("/departments")
    public Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PostMapping("/managers")
    public Manager createManager(@RequestBody Manager manager) {
        return managerRepository.save(manager);
    }

    @PostMapping("/projects")
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/managers")
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }


    @GetMapping("/managers/department/{departmentId}")
    public List<Manager> getManagersByDepartmentId(@PathVariable Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        List<Manager> managers = new ArrayList<>();
        assert department != null;
        Manager manager = department.getManager();
        if (manager != null) {
            managers.add(manager);
        }
        return managers;
    }
    @GetMapping("/departments/project/{projectId}")
    public List<Department> getDepartmentsByProjectId(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            return project.getDepartments();
        }
        return Collections.emptyList(); // Project not found
    }

    @GetMapping("/projects/department/{departmentId}")
    public List<Project> getProjectsByDepartmentId(@PathVariable Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department != null) {
            return department.getProjects();
        }
        return Collections.emptyList(); // Department not found
    }

}
