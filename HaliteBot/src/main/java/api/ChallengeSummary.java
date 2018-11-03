package api;

import com.google.api.client.json.Json;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

 public class ChallengeSummary {

     public int challenge_id;
     public boolean finished;
     public int issuer;
     public int receiver;
     public int issuer_points;
     public int receiver_points;
     public int num_games;
     public String time_created;
     public String time_finished;
     public int winner;

     public ChallengeSummary(int challenge_id, boolean finished, int issuer, int receiver, int issuer_points, int receiver_points, int num_games, String time_created, String time_finished, int winner) {
         this.challenge_id = challenge_id;
         this.finished = finished;
         this.issuer = issuer;
         this.receiver = receiver;
         this.issuer_points = issuer_points;
         this.receiver_points = receiver_points;
         this.num_games = num_games;
         this.time_created = time_created;
         this.time_finished = time_finished;
         this.winner = winner;
     }

     public static ArrayList<ChallengeSummary> fromJson(String strJson){
        JsonArray json = new JsonParser().parse(strJson).getAsJsonArray();
        ArrayList<ChallengeSummary> output = new ArrayList<>();
        for(JsonElement jsonObj : json){
             int challenge_id = ((JsonObject)jsonObj).get("challenge_id").getAsInt();
             boolean finished = ((JsonObject)jsonObj).get("finished").getAsBoolean();
             int issuer = ((JsonObject)jsonObj).get("issuer").getAsInt();
             JsonObject players = ((JsonObject)jsonObj).get("players").getAsJsonObject();
             Object[] playersKeys = players.keySet().toArray();
             int receiver =  Integer.parseInt(playersKeys[players.get(playersKeys[0].toString()).getAsJsonObject().get("is_issuer").getAsBoolean() ? 1 : 0].toString());
             int num_games = ((JsonObject)jsonObj).get("num_games").getAsInt();
             String time_created = ((JsonObject)jsonObj).get("time_created").getAsString();
             String time_finished = "Challenge incomplete.";
             try{
                 time_finished = ((JsonObject)jsonObj).get("time_finished").getAsString();
             }catch(Exception e){}
             int winner = 0;
             try {
                 winner = ((JsonObject) jsonObj).get("winner").getAsInt();
             }catch(Exception e){}

             int issuer_points = players.get(issuer + "").getAsJsonObject().get("points").getAsInt();
             int receiver_points = players.get(receiver + "").getAsJsonObject().get("points").getAsInt();

             ChallengeSummary cs = new ChallengeSummary(challenge_id, finished, issuer, receiver,  issuer_points, receiver_points,  num_games, time_created, time_finished, winner);
             output.add(cs);
        }

        return output;
    }
}
