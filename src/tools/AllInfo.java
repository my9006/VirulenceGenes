package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class AllInfo {

    private HashMap<String, LinkedList<String>> allInfo;
    private AllFilesInFolder aff;
    private TXT_FileReader tfr;
    private KeyFormater kf;

    public void collectAllInfo(String path) {

        allInfo = new HashMap<>();


        aff = new AllFilesInFolder();
        tfr = new TXT_FileReader();

        kf = new KeyFormater();

        aff.receiveFiles(path);

        for (int i = 0; i < aff.getFilesList().size(); i++) {
            tfr.readText(path, aff.getFilesList().get(i).getName());
            allInfo.put(kf.formatKey(aff.getFilesList().get(i).getName()), tfr.getTextLines());
        }

    }

    public HashMap<String, LinkedList<String>> getAllInfo() {
        return allInfo;
    }



}
