package api;

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

    public static ArrayList<ChallengeSummary> getUserChallenges(String user_id) throws Exception{
        String strJson = "{}";
        try{
            strJson = API.requestAPI("https://api.2018.halite.io/v1/api/user/"+user_id+"/challenge");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ChallengeSummary.fromJson(strJson);
    }

    public static HistoryUnit getLastBotInfo(String user_id) throws Exception{
        String strJson = "{}";
        try{
            strJson = API.requestAPI("https://api.2018.halite.io/v1/api/user/"+user_id+"/history");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return HistoryUnit.lastHistoryUnitFromJson(strJson);
    }

    public static ArrayList<Challenge> getChallenge(String challenge_id) throws Exception{
        String strJson = "{}";
        try{
            strJson = API.requestAPI("https://api.2018.halite.io/v1/api/user/216/challenge");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
