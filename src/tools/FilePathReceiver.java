package tools;

import java.util.Scanner;

public class FilePathReceiver {

    private String path;
    private Scanner sc;

    /* Set the path that will be used iver the session */
    public String makePath() {
        System.out.println("************Please enter the path of the folder************");
        sc = new Scanner(System.in);
        this.path = sc.nextLine().replace('\\', '/') + "/";
        return path;
    }

    /* Takes the path and format it in convenient way, replacing \ with / */
    public String getPath() {
        return path;
    }

}
