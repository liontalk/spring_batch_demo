package cn.liontalk.helloworld.config;


import cn.liontalk.helloworld.batch.PersonItemProcessor;
import cn.liontalk.helloworld.entity.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class HelloWorldJobConfig {

    /**
     *
     * JobBuilderFactory来创建作业。我们传递Job(作业)的名称和需要运行的Step(步骤)。
     * 在helloWorlJob()Bean中,Spring将自动注入 jobBuilders 和 stepBuilders Beans。
     * @param jobBuilders
     * @param stepBuilders
     * @return
     */
    @Bean
    public Job helloWorlJob(JobBuilderFactory jobBuilders,
                            StepBuilderFactory stepBuilders) {
        return jobBuilders.get("helloWorldJob")
                .start(helloWorldStep(stepBuilders))
                .build();
    }


    /**
     * 使用StepBuilderFactory创建步骤。
     * 首先，我们传入步骤的名称。使用chunk()，
     * 我们指定每个事务中处理的项的数量。Chunk还指定步骤的输入(Person)
     * 和输出(String)类型。然后，我们将ItemReader (reader)、ItemProcessor (processor)
     * 和ItemWriter (writer)添加到步骤中。
     * @param stepBuilders
     * @return
     */
    @Bean
    public Step helloWorldStep(StepBuilderFactory stepBuilders) {
        return stepBuilders.get("helloWorldStep")
                .<Person, String>chunk(10).reader(reader())
                .processor(processor()).writer(writer()).build();
    }




    @Bean
    public Step hello2WorldStep(StepBuilderFactory stepBuilders) {
        return stepBuilders.get("helloWorldStep")
                .<Person, String>chunk(10).reader(reader())
                .processor(processor()).writer(writer()).build();
    }


    /**
     * 我们使用FlatFileItemReader读取person CSV文件。这个类提供了读取和解析CSV文件的基本功能。
     * 有一个FlatFileItemReaderBuilder实现，它允许我们创建一个FlatFileItemReader。
     * 我们首先指定读取文件中每一行的结果是Person对象。
     * 然后，我们将使用name()方法为FlatFileItemReader添加一个名称，
     * 并指定需要读取的资源(在本例中是persons.csv文件)。
     * @return
     */
    @Bean
    public FlatFileItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new ClassPathResource("csv/persons.csv"))
                /**
                 * 指定了如何将一行中的每个字段映射到Person对象。这是使用names()来完成的，
                 * 通过将名称与对象上的setter匹配，可以使Spring Batch映射字段。
                 * 在本文的例子中，一行的第一个字段将使用firstName setter进行映射。
                 * 为了实现这一点，我们还需要指定targetType,即Person对象。
                 */
                .delimited().names(new String[] {"firstName", "lastName"})
                .targetType(Person.class).build();
    }


    /**
     * PersonItemProcessor处理数据。它将一个Person转换成一个问候String。
     * @return
     */
    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }


    /**
     * 一旦数据被处理，我们将把它写入一个文本文件。我们使用FlatFileItemWriter来完成这项任务。
     *
     * 我们使用FlatFileItemWriterBuilder实现来创建一个FlatFileItemWriter。我们为writer添加一个名称，
     * 并指定需要将数据写入其中的资源(在本例中是greeting.txt文件)。
     * @return
     */
    @Bean
    public FlatFileItemWriter<String> writer() {
        return new FlatFileItemWriterBuilder<String>()
                .name("greetingItemWriter")
                .resource(new FileSystemResource(
                        "target/test-outputs/greetings.txt"))
                .lineAggregator(new PassThroughLineAggregator<>()).build();
    }
}
