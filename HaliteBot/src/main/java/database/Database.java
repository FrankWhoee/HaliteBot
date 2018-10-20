package database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import api.DiscordUser;
import api.User;
import com.google.gson.*;

import constants.Constants;
import core.App;
import util.StringUtil;

public class Database {

    public static JsonObject database;
    private static List<DiscordUser> users = new ArrayList<>();


    public static void parseDB(File file, File parentFile) {
        try {
            String raw = StringUtil.readFileAsString(file.getPath());
            database = new JsonParser().parse(raw).getAsJsonObject();
            for(String key : database.keySet()) {
                DiscordUser user = new DiscordUser(database.get(key).getAsString(), key);
                users.add(user);
            }
            System.out.println("Database parsed succesfully.");
        }catch(JsonParseException jpe) {
            System.err.println("Corrupted JSON detected. Printing stack trace...");
        }catch(Exception e) {
            System.err.println("Error reading JSON database.");
            if(!file.exists()) {
                System.err.println("JSON does not exist.");
                if(!parentFile.exists()) {
                    System.err.println("Folder does not exist. Creating new folder...");
                    try {
                        parentFile.mkdir();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                database = new JsonObject();
                rawSave(file);
                System.err.println("New JSON file created.");
            }else {
                System.err.println("Unknown error! Printing stack trace...");
                e.printStackTrace();
            }
        }
    }

    public static void addUser(DiscordUser user) {
        users.add(user);
    }

    public static void removeUser(DiscordUser user) {
        users.remove(user);
    }

    public static void removeUser(String userId) {
        for(int i = users.size() - 1; i >= 0 ; i--) {
            DiscordUser u = users.get(i);
            String id = u.discord_id;
            if(id.equals(userId)) {
                users.remove(i);
                return;
            }
        }
    }

    public static boolean hasUser(DiscordUser user) {
        return users.contains(user);
    }

    public static boolean hasUser(String userId) {
        for(int i = 0; i < users.size(); i++) {
            DiscordUser u = users.get(i);
            String id = u.discord_id;
            if(id.equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public static DiscordUser getUser(String discord_id) {
        for(DiscordUser u : users) {
            if(u.discord_id.equals(discord_id)) {
                return u;
            }
        }
        return null;
    }

    public static void rawSave(File f) {
        StringUtil.writeToFile(database.toString(), f.getPath());
    }


    //Detects if the discord_id is the halite_id, and flips it.
    @Deprecated
    public static void sanitise(){
        for(int i = users.size() - 1; i >= 0; i--){
            if(users.get(i).discord_id.length() < 5){
                String temp = users.get(i).discord_id;
                users.get(i).discord_id = users.get(i).halite_id;
                users.get(i).halite_id = temp;
            }
        }
    }

    public static void save(File f) {

        database = new JsonObject();

        for(DiscordUser u : users) {
            database.addProperty(u.getDiscord_id(), u.halite_id);
        }

        StringUtil.writeToFile(database.toString(), f.getPath());
    }

}