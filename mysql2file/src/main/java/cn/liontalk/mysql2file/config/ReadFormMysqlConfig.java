package cn.liontalk.mysql2file.config;


import cn.liontalk.mysql2file.entity.Music;
import cn.liontalk.mysql2file.rowmapper.MusicRowMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ReadFormMysqlConfig {


//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    @Qualifier("DBJdbcWriterDemoWriter")
//    private ItemWriter<? super Music> DBJdbcWriterDemoWriter;
//
//    @Bean
//    public Job DBJdbcItemReaderJob(){
//        return jobBuilderFactory.get("DBJdbcItemReaderJob"+"__"+new Date().getTime())
//                .start(DBJdbcItemReaderJobStep())
//                .build();
//    }
//
//    @Bean
//    public Step DBJdbcItemReaderJobStep(){
//       return  stepBuilderFactory.get("DBJdbcItemReaderJobStep__"+new Date().getTime())
//               .<Music,Music>chunk(2)
//               .reader(DBJdbcReaderDemoReader())
//               .writer(DBJdbcWriterDemoWriter)
//               .build();
//    }
//
//    @Bean
//    @StepScope
//    public JdbcPagingItemReader<Music> DBJdbcReaderDemoReader() {
//        JdbcPagingItemReader<Music> reader = new JdbcPagingItemReader<>();
//        reader.setDataSource(this.dataSource); // 设置数据源
//        reader.setFetchSize(2); // 设置一次最大读取条数
//        reader.setRowMapper(new MusicRowMapper()); // 把数据库中的每条数据映射到Person对中
//        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
//        queryProvider.setSelectClause("id,singer_name,music_size,music_name"); // 设置查询的列
//        queryProvider.setFromClause("from music_info"); // 设置要查询的表
//        Map<String, Order> sortKeys = new HashMap<String, Order>();// 定义一个集合用于存放排序列
//        sortKeys.put("id", Order.ASCENDING);// 按照升序排序
//        queryProvider.setSortKeys(sortKeys);
//        reader.setQueryProvider(queryProvider);// 设置排序列
//        return reader;
//    }

}
