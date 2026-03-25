package hei.group.td2td3.Controller;

import hei.group.td2td3.Entity.StudentEntity;
import hei.group.td2td3.Service.StudentService;
import hei.group.td2td3.Service.WelcomeService;
import hei.group.td2td3.Validateur.StudentValidateur;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    private final StudentService studentService;
    private final WelcomeService welcomeService;
    private final StudentValidateur studentValidateur;

    public MainController(StudentService studentService, WelcomeService welcomeService, StudentValidateur studentValidateur) {
        this.studentService = studentService;
        this.welcomeService = welcomeService;
        this.studentValidateur = studentValidateur;
    }
    List<StudentEntity> listGeneral=new ArrayList<>();
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(welcomeService.welcome(name));
    }

    @PostMapping(value = "/student",produces = "application/json")
    public ResponseEntity<?> addStudent(@RequestBody List<StudentEntity> student,@RequestHeader(value = "Accept", required = false) String accept) {
    try{
        studentValidateur.validatorStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudents(student));
    }catch (IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    }
    @GetMapping(value = "/student",produces = "application/json")
    public ResponseEntity<?> getAllStudents(@RequestHeader(value = "Accept", required = false) String accept) {
       try{
           return ResponseEntity.ok(studentService.getAllStudents());

       }catch (RuntimeException e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-Type","text/plain").body(e.getMessage());
       }
    }
}
