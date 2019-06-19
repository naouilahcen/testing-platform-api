package ma.valueit.testingplatform.core.utils.file;

import ma.valueit.testingplatform.core.utils.StringUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by yelansari on 3/21/18.
 */
public class FileUtils {
    public static String extractContentFromFile(final String fileName) throws IOException, SAXException, TikaException {
        BodyContentHandler handler = new BodyContentHandler(-1);

        Metadata metadata = new Metadata();

        FileInputStream inputstream = new FileInputStream(new File(fileName));

        Parser parser = new AutoDetectParser();

        ParseContext pcontext = new ParseContext();

        parser.parse(inputstream, handler, metadata, pcontext);

        return handler.toString();
    }

    public static File getFileFromUrl(URL url) throws Exception {
        if (url == null) {
            throw new NullPointerException();
        }

        File file = new File(url.getFile());

        if (!file.exists()) {
            throw new Exception(String.format("file not found '%s'", url.getPath()));
        }

        return file;
    }

    public static URL getUrlFromDtring(String filePath) throws Exception{
        if (StringUtils.isEmpty(filePath)) {
            throw new Exception(String.format("Invalid file path '%s'", filePath));
        }

        URL url = null;

        File f = new File(filePath);

        if (f.exists()) {
          try {
              url = f.toURI().toURL();
          } catch (MalformedURLException e) {
              throw new Exception(String.format("Invalid file path '%s'", filePath));
          }
        } else {
            throw new Exception(String.format("Invalid file path '%s'", filePath));
        }

        return url;
    }
}
