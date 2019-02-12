package tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentDateTime {

    private String dateTime;

    public String getDateTime() {
        dateTime = new SimpleDateFormat("HH-mm dd-MM-yyyy").format(Calendar.getInstance().getTime());
        return dateTime;
    }

}
