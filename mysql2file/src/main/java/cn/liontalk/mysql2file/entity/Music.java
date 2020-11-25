package cn.liontalk.mysql2file.entity;

public class Music {

    // 主键id
    private Integer id;

    // 歌手名
    private String singerName;

    // 歌曲大小
    private String musicSize;

    // 歌曲名
    private String musicName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getMusicSize() {
        return musicSize;
    }

    public void setMusicSize(String musicSize) {
        this.musicSize = musicSize;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", singerName='" + singerName + '\'' +
                ", musicSize='" + musicSize + '\'' +
                ", musicName='" + musicName + '\'' +
                '}';
    }
}
