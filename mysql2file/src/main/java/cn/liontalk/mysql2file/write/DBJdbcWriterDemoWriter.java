package cn.liontalk.mysql2file.write;

import cn.liontalk.mysql2file.entity.Music;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("DBJdbcWriterDemoWriter")
public class DBJdbcWriterDemoWriter implements ItemWriter<Music> {



    @Override
    public void write(List<? extends Music> list) throws Exception {
        for(Music music:list) {
            System.out.println(music);
        }
    }
}
