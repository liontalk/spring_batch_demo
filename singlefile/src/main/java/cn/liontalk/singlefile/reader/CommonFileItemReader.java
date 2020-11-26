package cn.liontalk.singlefile.reader;

import cn.liontalk.singlefile.constant.CommonConstants;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DefaultFieldSetFactory;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 公共读方法
 * @param <T>
 */
public class CommonFileItemReader<T> extends FlatFileItemReader<T> {

    private FileSystemResource fileSystemResource;

    public CommonFileItemReader(Class clz) {
        setEncoding(CommonConstants.ENCODING_READ);
        fileSystemResource = new FileSystemResource("E:\\spring_batch_test_file\\read"+clz.getSimpleName()+".csv");
        setResource(fileSystemResource);
        DefaultLineMapper defaultLineMapper = new DefaultLineMapper();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setFieldSetFactory(new DefaultFieldSetFactory());
        Field[] fields = clz.getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                list.add(field.getName());
            }
        }
        String[] names = new String[list.size()];
        delimitedLineTokenizer.setNames(list.toArray(names));
        delimitedLineTokenizer.setDelimiter(",");
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();
        fieldSetMapper.setTargetType(clz);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        setLineMapper(defaultLineMapper);
    }
}
