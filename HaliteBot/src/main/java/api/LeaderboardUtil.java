package api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class LeaderboardUtil {

    public static ArrayList<Integer> fromJson(String strJson){
        JsonArray json = new JsonParser().parse(strJson).getAsJsonArray();
        ArrayList<Integer> output = new ArrayList<>();
        for(JsonElement obj : json){
            output.add(((JsonObject)(obj)).get("user_id").getAsInt());
        }

        return output;
    }

}
