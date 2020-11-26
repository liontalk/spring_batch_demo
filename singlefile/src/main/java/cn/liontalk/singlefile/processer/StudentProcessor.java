package cn.liontalk.singlefile.processer;

import cn.liontalk.singlefile.entity.People;
import cn.liontalk.singlefile.entity.Student;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class StudentProcessor extends CommonProcessor<People, Student> {
    @Override
    public void processor(Student o, People people) {
        o.setName(people.getName());
        o.setAddress(people.getAddress());
        o.setAge(people.getAge());
        o.setIdCar(people.getIdCard());
    }
}
