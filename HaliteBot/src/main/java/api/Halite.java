package api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;

public class Halite {

    public Halite(){

    }

    public static User getUserInfo(String user_id) throws Exception{
        String strJson = "{}";
        strJson = API.requestAPI("https://api.2018.halite.io/v1/api/user/"+user_id);
        return User.fromJsonUser(strJson);
    }

    public static User getBotInfo(String user_id) throws Exception{
        String strJson = "{}";
        try {
            strJson = API.requestAPI("https://api.2018.halite.io/v1/api/user/"+user_id + "/bot");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return User.fromJsonBot(strJson);
    }

    public static ArrayList<Integer> getLeaderboard() throws Exception{
        String strJson = "{}";
        try {
            strJson = API.requestAPI("https://api.2018.halite.io/v1/api/leaderboard");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return LeaderboardUtil.fromJson(strJson);
    }
}
