package cn.liontalk.mysql2file.write;

import cn.liontalk.mysql2file.dao.MusicDao;
import cn.liontalk.mysql2file.entity.Music;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("FlatFileWriter")
public class FlatFileWriter implements ItemWriter<Music> {


    private static Logger logger = LoggerFactory.getLogger(FlatFileWriter.class);

    @Autowired
    MusicDao musicDao;


    @Override
    public void write(List<? extends Music> list) throws Exception {
        logger.info("开始写入数据。。。。。。。。。。。。");
        for (Music music:list){
             musicDao.save(music);
            System.out.println(music);
        }
    }
}
