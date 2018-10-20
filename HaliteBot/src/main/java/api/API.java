package api;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.naming.directory.SearchResult;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpRequest;
import org.w3c.dom.Document;


public class API {

    public static String requestAPI(String URL) throws IOException {
        String body = "";
        URL url = new URL(URL);
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String encoding = con.getContentEncoding();
        encoding = encoding == null ? "UTF-8" : encoding;
        body = IOUtils.toString(in, encoding);
        return body;
    }

    public static String encode(String arg0) throws UnsupportedEncodingException {
        return URLEncoder.encode(arg0,"UTF-8");
    }

    public Document requestXMLAPI(String URL) throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL(URL).openStream());

        return doc;
    }
}