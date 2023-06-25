package com.qpros.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonDataReader {
    public String name;
    public String country;
    public String city;
    public String creditCard;
    public String month;
    public String year;
    public void jsonReader() throws IOException, ParseException {
        String filePath = System.getProperty("user.dir") + "/src/test/java/data/PlaceOrderUserData.json";
        File srcFile = new File(filePath);

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(srcFile));
        for(Object jsonObject : jsonArray ){
            JSONObject userInfo = (JSONObject) jsonObject;
            name = (String)userInfo.get("name");
            country = (String)userInfo.get("country");
            city = (String)userInfo.get("city");
            creditCard = (String)userInfo.get("creditCard");
            month = (String)userInfo.get("month");
            year = (String)userInfo.get("year");
        }
    }
}
