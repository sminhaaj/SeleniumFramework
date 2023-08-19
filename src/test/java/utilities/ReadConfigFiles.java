package utilities;

import java.util.Properties;

public class ReadConfigFiles {

    public static String getPropertyValues(String propName){

        Properties prop;
        String propValue = null;
        try{
            prop = new LoadConfigFiles().readPropertyValues();
            propValue = prop.getProperty(propName);
        } catch (Exception e){
            System.out.println();
        }

        return propValue;

    }
}