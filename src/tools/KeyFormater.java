package tools;

public class KeyFormater {

    public String formatKey(String key){

        //Take the key, split after . for 2 parts and give me the 0-th part

        key = key.split("\\.", 2)[0];

        return key;

    }

}
