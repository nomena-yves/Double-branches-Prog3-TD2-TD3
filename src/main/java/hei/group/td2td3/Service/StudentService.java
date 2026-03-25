package hei.group.td2td3.Service;

import hei.group.td2td3.Entity.StudentEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private List<StudentEntity> listGeneral = new ArrayList<>();

    public List<String> addStudents(List<StudentEntity> students) {
        listGeneral.addAll(students);

        return listGeneral.stream()
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .toList();
    }

    public List<StudentEntity> getAllStudents() {
        return listGeneral;
    }
}
