package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.*;

import org.apache.commons.io.FileUtils;

public class StringUtil {

    public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static void writeToFile(String data, String path) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path, "UTF-8");
        } catch (Exception e) {
            return;
        }
        writer.println(data);
        writer.close();
    }

    public static void saveFileFromUrl(File f, String fileUrl) throws MalformedURLException, IOException {
        URL url=new URL(fileUrl);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", "Workflow");
        conn.connect();
        FileUtils.copyInputStreamToFile(conn.getInputStream(), f);

    }

}