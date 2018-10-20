package emojis;

import api.DiscordUser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import constants.Constants;
import util.StringUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class EmojiUtil {

    private static Map<String, String> Emojis = new HashMap<String, String>();

    public static void parseEmojis() {
        try {
            String raw = StringUtil.readFileAsString(Constants.emojibase.getPath());
            JsonArray database = new JsonParser().parse(raw).getAsJsonArray();
            for(int i = 0; i < database.size(); i++){
                String emoji = database.get(i).getAsJsonObject().get("emojiChar").getAsString();
                String alias = database.get(i).getAsJsonObject().get("aliases").getAsJsonArray().get(0).getAsString();
                Emojis.put(alias,emoji);
            }
            System.out.println("Emojibase parsed successfully.");
        }catch(JsonParseException jpe) {
            System.err.println("Corrupted JSON detected. Printing stack trace...");
        }catch(Exception e) {
            System.err.println("Error reading JSON database.");
        }
    }

    public static String getEmojiByAlias(String alias){
        return Emojis.get(alias);
    }

}
