package Client;

import Model.SongModel;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListOfMusicUtils implements Serializable {

    private List<SongModel> listOfMusics = new ArrayList<SongModel>();
    private File folder = new File("C:\\Musics");
    private File[] listOfFiles = folder.listFiles();


    public ListOfMusicUtils() {
        fillList();
    }

    private void fillList() {

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                //TODO add only mp3 files.
                listOfMusics.add(new SongModel(listOfFiles[i].getName(), listOfFiles[i].getAbsolutePath()));
            }
        }

    }

    public List<SongModel> getListOfMusics() {
        return listOfMusics;
    }

    public void setListOfMusics(List<SongModel> listOfMusics) {
        this.listOfMusics = listOfMusics;
    }

}
