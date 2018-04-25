package com.ld41.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileUtils {

    public static boolean FileExists(String name) {

        return new File(name).isFile();

    }

    public static Properties loadProperties(String name) {

        File file = new File(name);
        FileInputStream iStream;

        try {

            iStream = new FileInputStream(file);

        } catch (FileNotFoundException e) {

            e.printStackTrace();
            return null;

        }

        Properties properties = new Properties();

        try {

            properties.load(iStream);
            iStream.close();
            return properties;

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }

    }

}
