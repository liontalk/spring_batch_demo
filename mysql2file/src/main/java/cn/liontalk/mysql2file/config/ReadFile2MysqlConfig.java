package cn.liontalk.mysql2file.config;


import cn.liontalk.mysql2file.entity.Music;
import cn.liontalk.mysql2file.filemapper.MusicDoFileMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.batch.item.excel.support.rowset.RowSet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.util.Date;

@Configuration
public class ReadFile2MysqlConfig {



    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Autowired
    @Qualifier("FlatFileWriter")
    private ItemWriter<? super Music> flatFileWriter;

    @Bean
    public Job FaltFileItemReaderJob() {
        return jobBuilderFactory.get("FaltFileItemReaderJob__"+new Date().getTime())
                .start(FaltFileItemReaderJobStep())
                .build();

    }

    @Bean
    public Step FaltFileItemReaderJobStep() {
        return stepBuilderFactory.get("FaltFileItemReaderJobStep__"+new Date().getTime())
                .<Music, Music>chunk(100)
                .reader(reader())
                .writer(flatFileWriter)
                .build();
    }


    @Bean
    public ItemReader<Music> reader() {
        PoiItemReader<Music> reader = new PoiItemReader<>();
        try {
            reader.setStrict(true);
            reader.setLinesToSkip(1);
            reader.setResource(new ClassPathResource("data/springbatchtest1.xlsx"));
            reader.setRowMapper(new RowMapper<Music>() {
                @Override
                public Music mapRow(RowSet rowSet) throws Exception {
                    Music music = new Music();
                    music.setSingerName(rowSet.getColumnValue(0));
                    music.setMusicSize(rowSet.getColumnValue(1));
                    music.setMusicName(rowSet.getColumnValue(2));
                    return music;
                }
            });
        } catch(Exception e) {
            System.out.println( e.getMessage());
        }
        return reader;
    }

//    @Bean
//    @StepScope
//    public FlatFileItemReader<Music> faltFileReader() {
//        FlatFileItemReader<Music> reader=new FlatFileItemReader<Music>();
//        reader.setEncoding("UTF-8");
//        reader.setResource(new ClassPathResource("/data/springbatchtest1.csv"));
//        //reader.setLinesToSkip(1);
//
//        DelimitedLineTokenizer tokenizer=new DelimitedLineTokenizer();
//        tokenizer.setNames(new String[]
//                {"singerName","musicSize","musicName"}
//        );
//        DefaultLineMapper<Music> lineMapper=new DefaultLineMapper<Music>();
//        lineMapper.setLineTokenizer(tokenizer);
//        lineMapper.setFieldSetMapper(new MusicDoFileMapper());
//        lineMapper.afterPropertiesSet();
//        reader.setLineMapper(lineMapper);
//
//        return reader;
//    }

}
