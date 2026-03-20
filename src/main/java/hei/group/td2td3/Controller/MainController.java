package hei.group.td2td3.Controller;

import hei.group.td2td3.Entity.StudentEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    List<StudentEntity> listGeneral=new ArrayList<>();
    @GetMapping("/bonjour")
    public ResponseEntity<String> welcome(@RequestParam(required = false) String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Name not found");
        }
        return ResponseEntity.ok("Welcome " + name);
    }

    @PostMapping(value = "/student",produces = "application/json")
    public ResponseEntity<String> addStudent(@RequestBody List<StudentEntity> student) {
        List<String> list=new ArrayList<>();
    for (StudentEntity studentEntity : student) {
        listGeneral.add(studentEntity);
    }
    for (StudentEntity studentEntity : listGeneral) {
        list.add(studentEntity.getFirstName() + " " + studentEntity.getLastName());
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(list.toString());
    }
}
