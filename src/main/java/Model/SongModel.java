package Model;

import javax.sound.sampled.Clip;
import java.io.Serializable;

public class SongModel implements Serializable {
    private String filename;
    private String url;
    private String pathname;

    public SongModel(String filename,String url) {
        this.filename = filename;
        this.url = url;
        this.pathname ="C:\\Musics"+filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }
}
