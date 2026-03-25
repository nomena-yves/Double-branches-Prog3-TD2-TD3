package hei.group.td2td3.Controller;

import hei.group.td2td3.Entity.StudentEntity;
import hei.group.td2td3.Service.StudentService;
import hei.group.td2td3.Service.WelcomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    private final StudentService studentService;
    private final WelcomeService welcomeService;

    public MainController(StudentService studentService, WelcomeService welcomeService) {
        this.studentService = studentService;
        this.welcomeService = welcomeService;
    }
    List<StudentEntity> listGeneral=new ArrayList<>();
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(required = false) String name) {
        if (name == null ||name.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name cannot be null or blank ");
        }
        return ResponseEntity.ok(welcomeService.welcome(name));
    }

    @PostMapping(value = "/student",produces = "application/json")
    public ResponseEntity<?> addStudent(@RequestBody List<StudentEntity> student,@RequestHeader(value = "Accept", required = false) String accept) {
       for (StudentEntity studentEntity : student) {
           if (studentEntity.getFirstName()==null || studentEntity.getLastName().isBlank()||studentEntity.getReference()==null || studentEntity.getReference().isBlank()||
                   studentEntity.getLastName().equals(studentEntity.getReference())) {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Content-Type","text/plain").body("FirstName or LastName or reference cannot be null or blank ");
           }
       }
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudents(student));
    }
    @GetMapping(value = "/student",produces = "application/json")
    public ResponseEntity<String> getAllStudents(@RequestHeader(value = "Accept", required = false) String accept) {
        try {
            if (accept != null && !accept.contains("application/json")) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build(); // 406
            }

            return ResponseEntity.status(HttpStatus.OK).body(listGeneral.toString());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
