import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONUserWrite {
    public static void main(String[] args) throws IOException, ParseException {
        String fileLocation="./src/main/resources/users.json";
        JSONParser parser=new JSONParser();
        JSONArray jsonArray=(JSONArray) parser.parse(new FileReader(fileLocation));

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("username","admin");
        jsonObject.put("password","1234");
        jsonObject.put("role","admin");

        jsonArray.add(jsonObject);

        JSONObject jsonObject1=new JSONObject();
        jsonObject1.put("username","salman");
        jsonObject1.put("password","1234");
        jsonObject1.put("role","student");

        jsonArray.add(jsonObject1);

        System.out.println(jsonArray);

        FileWriter writer=new FileWriter(fileLocation);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();

    }

}
