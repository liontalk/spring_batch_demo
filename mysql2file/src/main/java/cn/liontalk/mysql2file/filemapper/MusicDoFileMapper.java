package cn.liontalk.mysql2file.filemapper;

import cn.liontalk.mysql2file.entity.Music;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class MusicDoFileMapper implements FieldSetMapper<Music> {
    @Override
    public Music mapFieldSet(FieldSet fieldSet) throws BindException {
        return new Music(
                fieldSet.readString("singer_name"),
                fieldSet.readString("music_size"),
                fieldSet.readString("music_name")
        );
    }
}
