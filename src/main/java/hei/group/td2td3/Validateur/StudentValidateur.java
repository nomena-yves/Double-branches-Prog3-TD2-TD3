package hei.group.td2td3.Validateur;

import hei.group.td2td3.Entity.StudentEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Component
public class StudentValidateur {
    public void validatorStudent(List<StudentEntity> student) {
        for ( StudentEntity st: student){
            if (st.getFirstName() == null ||
                    st.getLastName() == null ||
                    st.getLastName().isBlank() ||
                    st.getReference() == null ||
                    st.getReference().isBlank()
                    ){
                throw new IllegalArgumentException("NewStudent.reference cannot be null or blank");
            }
        }

    }
}
