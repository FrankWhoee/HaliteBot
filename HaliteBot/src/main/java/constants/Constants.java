package constants;

import core.App;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final String PREFIX = "!";

    //Paths, prefix and version
    public static final String version = "1.0.0";
    public static String jarPath = "";
    public static File database = new File("../HaliteBotDB/users.json");
    public static File databaseParent = new File("../HaliteBotDB");
    public static File temp = new File("../HaliteBotDB/temp/");

    public static final ArrayList<Long> adminIds = new ArrayList<>(Arrays.asList(194857448673247235L));

    public static final Color HALITE_BLUE = new Color(0x1C3184);
    public static final Color WARNING_RED = new Color(0xff0000);

    //Used when running project as a jar file.
//    static{
//        try {
//            database = new File(App.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "HaliteBotDB/users.json");
//            databaseParent = new File(App.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "HaliteBotDB");
//            temp = new File(App.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "temp/");
//        }catch(Exception e) {
//            System.out.println("Error getting JAR file path.");
//        }
//    }

    public static final Map<String,String> tierEmojis = new HashMap<String,String>();
    static{
        tierEmojis.put("Diamond","<:diamond:501800017477238804>");
        tierEmojis.put("Platinum","<:platinum:501800017174986754>");
        tierEmojis.put("Gold","<:gold:501800017426907137>");
        tierEmojis.put("Silver","<:silver:501800017246421013>");
        tierEmojis.put("Bronze","<:bronze:501800017468588062>");
    }

    public static final String HELP_MESSAGE = "```css\n" +
            "COMMANDS:\n\n" +
            "The Halite Discord bot is made by FrankWhoee. If you're having troubles with it, don't hesitate to ping him!\n\n" +
            "[OFR] means that the parameter is Optional For Registered people.\n\n" +
            PREFIX + "help - returns the help message." + "\n" +
            PREFIX + "register <user_id> - Registers you for user info so you don't need to input your user_id all the time. You can run this command again at any time to update the id." + "\n" +
            PREFIX + "me - Returns information for your halite account. You must !register first." + "\n" +
            PREFIX + "who <user_id> <discord_nickname> - Returns information for the halite account associated with user_id" + "\n" +
            PREFIX + "rank <[OFR]user_id> - "  + "Displays information purely about your rank and ratings.\n" +
            PREFIX + "top - <list_size>" + "Displays the top players, where list_size is the number of top players the bot will list.\n" +
            "```";


}
