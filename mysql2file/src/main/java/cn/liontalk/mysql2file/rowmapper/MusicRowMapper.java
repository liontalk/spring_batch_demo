package cn.liontalk.mysql2file.rowmapper;


import cn.liontalk.mysql2file.entity.Music;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MusicRowMapper implements RowMapper<Music> {

    @Override
    public Music mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Music(
                resultSet.getInt("id"),
                resultSet.getString("singer_name"),
                resultSet.getString("music_size"),
                resultSet.getString("music_name")
                );

    }
}
