package api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import constants.Constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HistoryUnit {

    /*
    [
  {
    "bot_version": 1,
    "last_games_played": 289,
    "last_num_players": 1043,
    "last_rank": 154,
    "last_score": 27.966988510978,
    "when_retired": "Tue, 16 Oct 2018 22:53:54 GMT"
  },
  {
    "bot_version": 2,
    "last_games_played": 4,
    "last_num_players": 1063,
    "last_rank": 264,
    "last_score": 11.9452267504887,
    "when_retired": "Tue, 16 Oct 2018 23:17:59 GMT"
  }]
     */

    public int bot_version;
    public int last_games_played;
    public int last_num_players;
    public int last_rank;
    public int last_score;
    public Date when_retired;

    public HistoryUnit(int bot_version, int last_games_played, int last_num_players, int last_rank, int last_score, Date when_retired) {
        this.bot_version = bot_version;
        this.last_games_played = last_games_played;
        this.last_num_players = last_num_players;
        this.last_rank = last_rank;
        this.last_score = last_score;
        this.when_retired = when_retired;
    }

    public static HistoryUnit lastHistoryUnitFromJson(String strJson){
        JsonArray history = new JsonParser().parse(strJson).getAsJsonArray();
        JsonObject unit = history.get(history.size() - 1).getAsJsonObject();

        int bot_version = unit.get("bot_version").getAsInt();
        int last_games_played = unit.get("last_games_played").getAsInt();
        int last_num_players = unit.get("last_num_players").getAsInt();
        int last_rank = unit.get("last_rank").getAsInt();
        int last_score = unit.get("last_score").getAsInt();


        Date when_retired = null;
        try {
            when_retired = Constants.dateFormat.parse(unit.get("when_retired").getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new HistoryUnit(bot_version, last_games_played, last_num_players, last_rank, last_score, when_retired);

    }
}
