package cn.liontalk.singlefile.writer;

import cn.liontalk.singlefile.constant.CommonConstants;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class CommonFileItemWriter<T> extends FlatFileItemWriter<T> {

    private FileSystemResource fileSystemResource;

    public CommonFileItemWriter(Class clz) {
        BeanWrapperFieldExtractor beanWrapperFieldExtractor = new BeanWrapperFieldExtractor();
        Field[] fields = clz.getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (java.lang.reflect.Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                list.add(field.getName());
            }
        }
        String[] names = new String[list.size()];
        beanWrapperFieldExtractor.setNames(list.toArray(names));
        beanWrapperFieldExtractor.afterPropertiesSet();
        DelimitedLineAggregator lineAggregator = new DelimitedLineAggregator();
        lineAggregator.setDelimiter(",");
        lineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
        setName(clz.getSimpleName());
        setEncoding(CommonConstants.ENCODING_READ);
        fileSystemResource = new FileSystemResource("E:\\spring_batch_test_file\\write"+ clz.getSimpleName() + ".csv");
        setResource(fileSystemResource);
        setLineAggregator(lineAggregator);
    }
}
