package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties prop;

    //this method will read any property file
    public static Properties readProperties(String filePath){
        try{
            FileInputStream fis=new FileInputStream(filePath);
            prop=new Properties();
            prop.load(fis);
            fis.close(); //after reading all properties file we need to close it
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return prop;
    }

    //this method retrieves single value based on the specified key
    public static String getPropertyValue(String key){
        return prop.getProperty(key);
    }
}

