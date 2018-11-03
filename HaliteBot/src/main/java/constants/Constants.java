package constants;

import api.Halite;
import core.App;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Role;

import java.awt.*;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Constants {

    public static final String PREFIX = "!";

    public static final int TOP_DEFAULT_LENGTH = 5;
    //Paths, prefix and version
    public static final String version = "1.0.0";
    public static String jarPath = "";
    public static File database = new File("../HaliteBotDB/users.json");
    public static File emojibase = new File("../HaliteBotDB/emojis.json");
    public static File databaseParent = new File("../HaliteBotDB");
    public static File temp = new File("../HaliteBotDB/temp/");

    public static final ArrayList<Long> adminIds = new ArrayList<>(Arrays.asList(194857448673247235L, 126497320064909321L));

    public static final Color HALITE_BLUE = new Color(0x1C3184);
    public static final Color WARNING_RED = new Color(0xff0000);

    public static final DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);

    public static final Map<String, Integer> top250 = new HashMap<>();

    public static boolean isTop250Loaded = false;

    //Used when running project as a jar file.
    static{
        try {
            String AppPath = App.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(0,App.class.getProtectionDomain().getCodeSource().getLocation().getPath().length() - 14);
            database = new File(AppPath + "/HaliteBotDB/users.json");
            System.out.println(database.getPath());
            databaseParent = new File(AppPath + "/HaliteBotDB");
            temp = new File(AppPath + "/temp/");
            emojibase = new File(AppPath + "/HaliteBotDB/emojis.json");
        }catch(Exception e) {
            System.out.println("Error getting JAR file path.");
        }
    }

    //Populate top 250
    public static void populateTop250(){
        ArrayList<Integer> top = null;
        try {
            top = Halite.getLeaderboard();
        } catch (Exception e) {}

        for(Integer user_id : top){
            String username = null;
            try {
                username = Halite.getUserInfo("" + user_id).getUsername();
            } catch (Exception e) {
                e.printStackTrace();
            }
            top250.put(username, user_id);
        }
        System.out.println("Top 250 loaded.");
        isTop250Loaded = true;
    }

    public static final Map<String,String> tierEmojis = new HashMap<String,String>();
    static{
        tierEmojis.put("Diamond","<:diamond:501800017477238804>");
        tierEmojis.put("Platinum","<:platinum:501800017174986754>");
        tierEmojis.put("Gold","<:gold:501800017426907137>");
        tierEmojis.put("Silver","<:silver:501800017246421013>");
        tierEmojis.put("Bronze","<:bronze:501800017468588062>");
    }

    public static final Map<String,Role> tierRoles = new HashMap<>();
    public static void populateRoles(){
        Guild halite = App.jda.getGuildById(248237939123945493L);
        System.out.println(halite.getName());
        tierRoles.put("Diamond", halite.getRoleById(504813668547166218L));
        tierRoles.put("Platinum", halite.getRoleById(504813743813951498L));
        tierRoles.put("Gold", halite.getRoleById(504814437207638018L));
        tierRoles.put("Silver", halite.getRoleById(504814548633780224L));
        tierRoles.put("Bronze", halite.getRoleById(504814656062357534L));

        System.out.println("Tier roles loaded.");
    }

    public static final String HELP_MESSAGE = "```css\n" +
            "COMMANDS:\n\n" +
            "The Halite Discord bot is made by FrankWhoee. If you're having troubles with it, don't hesitate to ping him!\n\n" +
            "[OFR] means that the parameter is Optional For Registered people.\n\n" +
            "[O] means that the parameter is optional.\n\n" +
            PREFIX + "help - returns the help message." + "\n" +
            PREFIX + "register <user_id> - Registers you for user info so you don't need to input your user_id all the time. You can run this command again at any time to update the id." + "\n" +
            PREFIX + "me - Returns information for your halite account. You must !register first." + "\n" +
            PREFIX + "who <[O]user_id> <[O]discord_nickname> <[O]@mention> - Information for the halite account associated with the parameters" + "\n" +
            PREFIX + "rank <[OFR]user_id> - "  + "Displays information about your rank and ratings.\n" +
            PREFIX + "top <list_size> - " + "Displays the top players, where list_size is the number of top players the bot will list.\n" +
            PREFIX + "challenges <[O]user_id> <[O]discord_nickname> <[O]@mention>" + "Displays the challenges for the account associated with the parameters\n" +
            PREFIX + "rate <[O]user_id> <[O]discord_nickname> <[O]@mention>" + "Displays the games per hour for the account associated with the parameters\n" +
            "```";

    public static Map<String, Locale> localeMap;
    static{
        String[] countries = Locale.getISOCountries();
        localeMap = new HashMap<String, Locale>(countries.length);
        for (String country : countries) {
            Locale locale = new Locale("", country);
            localeMap.put(locale.getISO3Country().toUpperCase(), locale);
        }
    }

    public static String iso3ToIso2(String iso3CountryCode) {
        return localeMap.get(iso3CountryCode).getCountry();
    }

    public static String iso2ToIso3(String iso2CountryCode){
        Locale locale = new Locale("", iso2CountryCode);
        return locale.getISO3Country();
    }
}
