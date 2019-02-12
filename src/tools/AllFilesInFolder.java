package tools;

import java.io.File;
import java.util.ArrayList;

public class AllFilesInFolder {

    private ArrayList<File> filesList;
    private File folder;

    public void receiveFiles(String path) {

//		fpr = new FilePathReceiver();

        folder = new File(path);

        filesList = new ArrayList<>();

        File[] filesArray = folder.listFiles();
        for (int i = 0; i < filesArray.length; i++) {

            if (filesArray[i].isFile()) {

                filesList.add(filesArray[i]);

            }
        }

    }

    public ArrayList<File> getFilesList() {
        return filesList;
    }

}
