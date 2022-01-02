package Model;

import javax.sound.sampled.Clip;

public class SongModel {
    private String filename;
    private String url;
    private String pathname;

    public SongModel(String filename,String url) {
        this.filename = filename;
        this.url = url;
        this.pathname ="C://MyMusics//"+filename;
    }
}
