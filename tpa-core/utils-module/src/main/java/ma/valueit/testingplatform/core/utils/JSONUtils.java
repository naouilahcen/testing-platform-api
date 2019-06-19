package ma.valueit.testingplatform.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.ResultSet;

/**
 * Created by yelansari on 4/30/18.
 */
public class JSONUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJSON(Object obj) throws JsonProcessingException {

        return mapper.writeValueAsString(obj);
    }

    public static <T> T toObject(String jsonString, Class<T> clazz) throws IOException {

        return mapper.readValue(jsonString, clazz);
    }

    public static JSONArray convertToJSON(ResultSet resultSet) throws Exception {
        JSONArray jsonArray = new JSONArray();

        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();

            JSONObject obj = new JSONObject();

            for (int i = 0; i < total_rows; i++) {
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));

                jsonArray.put(obj);
            }
        }

        return jsonArray;
    }

    public static String convertToXML(ResultSet resultSet) throws Exception {
        StringBuilder xmlArray = new StringBuilder("&lt;results&gt;");

        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();

            xmlArray.append("&lt;result ");

            for (int i = 0; i < total_rows; i++) {
                xmlArray.append(" ").append(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase()).append("='").append(resultSet.getObject(i + 1)).append("'");
            }

            xmlArray.append(" /&gt;");
        }

        xmlArray.append("&lt;/results&gt;");

        return xmlArray.toString();
    }
}
