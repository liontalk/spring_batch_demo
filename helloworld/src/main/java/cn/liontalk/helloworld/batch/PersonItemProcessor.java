package cn.liontalk.helloworld.batch;

import cn.liontalk.helloworld.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonItemProcessor.class);


    /**
     * 们创建一个实现ItemProcessor接口的PersonItemProcessor。
     * 我们实现了process()方法，它将人名和姓氏添加到字符串中。
     *
     * 调试的过程中，我们记录日志结果。
     * @param person
     * @return
     * @throws Exception
     */
    @Override
    public String process(Person person) throws Exception {
        String greeting = "Hello " + person.getFirstName() + " "
                + person.getLastName() + "!";

        LOGGER.info("converting '{}' into '{}'", person, greeting);
        return greeting;
    }
}
