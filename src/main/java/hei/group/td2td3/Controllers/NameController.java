package hei.group.td2td3.Controllers;

import hei.group.td2td3.Entity.StudentsEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NameController {
   @GetMapping("/welcome")
    public String welcomeName(@RequestParam String name) {
       if(name==null){
           return "Welcome ";
       }
        return "welcome "+name;
    }

    List<StudentsEntity> listGeneral=new ArrayList<>();
    @PostMapping("/students")
    public List<String> addStudent(@RequestBody List<StudentsEntity> students) {
        List<String> list=new ArrayList<>();
        for(StudentsEntity student:students){
            listGeneral.add(student);
        }

        for(StudentsEntity student:listGeneral){
            list.add(student.getFirstName()+" "+student.getLastName());
        }
        return list;
    }

    @GetMapping(value="/students",produces = "application/json")
    public List<String> getStudents() {
        List<String> list=new ArrayList<>();
        for(StudentsEntity student:listGeneral){
            list.add(student.getFirstName()+" "+student.getLastName());
        }
        return list;
    }
}
