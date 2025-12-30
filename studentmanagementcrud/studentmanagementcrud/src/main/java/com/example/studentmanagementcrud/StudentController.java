package com.example.studentmanagementcrud;



import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    // GET ALL
    @GetMapping
    public List<Student> getAll() {
        return repo.findAll();
    }

    // ADD
    @PostMapping
    public String addStudent(@RequestBody Student student) {
        repo.save(student);
        return "Student added successfully";
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Student deleted!";
        }
        return "Student not found!";
    }

    // UPDATE
    @PutMapping("/{id}")
    public String updateStudent(@PathVariable int id,
                                @RequestBody Student updatedStudent) {

        return repo.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setCourse(updatedStudent.getCourse());
                    repo.save(student);
                    return "Student updated successfully";
                })
                .orElse("Student not updated");
    }
}
