package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by katja on 19.08.2016
 */
public class LoadProperties {

    public String getProperies(String value) throws IOException {

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream("./src/main/resources/properties/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Properties properties = new Properties();
        //load properties file
        try {
            properties.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(value);
    }
}
