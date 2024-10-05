package Student.demo.dao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Student.demo.entity.Student;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentDAO studentDAO;

    //@Autowired
    public StudentController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    // Create a student (POST)
    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        // Validate the input data if needed
        if (student.getEmail() == null || !student.getEmail().contains("@")) {
            return new ResponseEntity<>("Invalid email address", HttpStatus.BAD_REQUEST);
        }

        studentDAO.save(student);
        return new ResponseEntity<>("Student created with ID: " + student.getId(), HttpStatus.CREATED);
    }


    // Sample get request to fetch details by ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        System.out.println("Fetching student with ID: " + id);
        Student student = studentDAO.findById(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllStudents() {
        studentDAO.deleteAll();
        return new ResponseEntity<>("All students have been deleted", HttpStatus.OK);
    }

}
