package core;

import api.DiscordUser;
import api.Halite;
import constants.Constants;
import constants.Key;
import database.Database;
import emojis.EmojiUtil;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Locale;

public class App extends ListenerAdapter{

    public static JDA jda;

    public static void main(String[] args) throws Exception{
        jda = new JDABuilder(AccountType.BOT).setToken(Key.TOKEN).build();
        jda.addEventListener(new App());
        jda.getPresence().setGame(Game.of(Game.GameType.DEFAULT, Constants.PREFIX + "help"));

        Database.parseDB(Constants.database, Constants.databaseParent);
        EmojiUtil.parseEmojis();

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent evt) {
        //Objects
        Message objMsg = evt.getMessage();
        MessageChannel objMsgCh = evt.getTextChannel();
        if(evt.getChannelType().equals(ChannelType.PRIVATE)){
            objMsgCh = evt.getPrivateChannel();
        }

        User objUser = evt.getAuthor();
        Member objMember = evt.getMember();
        Guild objGuild = evt.getGuild();

        String raw = objMsg.getContentRaw();
        if (!raw.startsWith(Constants.PREFIX)) {
            return;
        }

        String command = "";
        String input = "";
        try {
            command = raw.substring(Constants.PREFIX.length(), raw.indexOf(" ")).trim();
            input = raw.substring(command.length() + Constants.PREFIX.length() + 1).trim();
        } catch (Exception e) {
            try {
                command = raw.substring(Constants.PREFIX.length()).trim();
            } catch (Exception exc) {
                return;
            }
        }

        command = command.toLowerCase();

        api.User user_info = null;
        EmbedBuilder eb = new EmbedBuilder();
        api.User bot_info = null;

        if (command.equals("help")) {
            objMsgCh.sendMessage(Constants.HELP_MESSAGE).queue();
        } else if (command.equals("kill") && Constants.adminIds.contains(objUser.getIdLong())) {
            eb.setColor(Constants.WARNING_RED);
            eb.setTitle("Shutting down...");
            objMsgCh.sendMessage(eb.build()).queue();
            jda.shutdown();
            return;
        }else if (command.equals("who")) {

            String user_id = input.trim();

            boolean isHaliteID = false;
            try{
                int test = Integer.parseInt(input.trim());
                isHaliteID = true;
            }catch(Exception e){}

            if(!isHaliteID){
                if(evt.getChannelType().equals(ChannelType.PRIVATE)){
                    try {
                        user_id = Database.getUser(jda.getUsersByName(input, true).get(0).getId()).halite_id;
                    }catch(IndexOutOfBoundsException iobe){
                        eb.setColor(Constants.WARNING_RED);
                        eb.setTitle("I couldn't find " + input + ". This user does not exist.");
                        objMsgCh.sendMessage(eb.build()).queue();
                        return;
                    }catch(NullPointerException npe){
                        eb.setColor(Constants.WARNING_RED);
                        eb.setTitle("I couldn't find " + input + ". This user is not registered.");
                        objMsgCh.sendMessage(eb.build()).queue();
                        return;
                    }catch(Exception e){
                        eb.setColor(Constants.WARNING_RED);
                        eb.setTitle("An unknown error occurred. Please notify @FrankWhoee, and send this:");
                        eb.setDescription("```" + e.getStackTrace().toString() + "```");
                        objMsgCh.sendMessage(eb.build()).queue();
                        return;
                    }
                }else{

                    if(objMsg.getMentionedMembers().size() > 0){
                        Member m;
                        if(objMsg.getMentionedMembers().size() == 1){
                            m = objMsg.getMentionedMembers().get(0);
                        }else{
                            eb.setColor(Constants.WARNING_RED);
                            eb.setTitle("You tagged too many people! Just tag one person, in the format `!who @mention`. For example, `!who @FrankWhoee`");
                            objMsgCh.sendMessage(eb.build()).queue();
                            return;
                        }
                        try{
                            user_id = Database.getUser(m.getUser().getId()).halite_id;
                        }catch(Exception e){
                            eb.setColor(Constants.WARNING_RED);
                            eb.setTitle("You are not registered. Type !register user_id where user_id is your Halite ID to register.");
                            objMsgCh.sendMessage(eb.build()).queue();
                            return;
                        }
                    }else{
                        Member m;
                        try {
                            m = objGuild.getMembersByEffectiveName(input, true).get(0);
                        }catch(Exception e){
                            eb.setColor(Constants.WARNING_RED);
                            eb.setTitle("I couldn't find " + input + " in this server! Are you sure you typed the name right?");
                            objMsgCh.sendMessage(eb.build()).queue();
                            return;
                        }
                        try{
                            user_id = Database.getUser(m.getUser().getId()).halite_id;
                        }catch(Exception e){
                            eb.setColor(Constants.WARNING_RED);
                            eb.setTitle("You are not registered. Type !register user_id where user_id is your Halite ID to register.");
                            objMsgCh.sendMessage(eb.build()).queue();
                            return;
                        }
                    }
                }
            }
            try {
                user_info = Halite.getUserInfo(user_id);
            } catch (Exception e) {
                eb.setColor(Constants.WARNING_RED);
                eb.setTitle("https://api.2018.halite.io/v1/api/user/" + user_id + " returned an error. Check to see if the user id you entered is correct, and try again.");

                objMsgCh.sendMessage(eb.build()).queue();
                return;
            }

            boolean isBotUploaded = true;
            try{
                bot_info = Halite.getBotInfo(user_id);
            } catch(Exception e){
                eb.setColor(Constants.WARNING_RED);
                eb.setTitle("It seems like " + user_info.getUsername() + " doesn't have a bot uploaded!");
                isBotUploaded = false;
                objMsgCh.sendMessage(eb.build()).queue();
            }

            eb.setColor(Constants.HALITE_BLUE);

            eb.setTitle(user_info.getUsername(), "https://halite.io/user/?user_id=" + user_info.getUser_id());
            eb.setDescription(user_info.getOrganization());

            eb.addField("Rank", "" + user_info.getRank(), true);
            eb.addField("Tier", "" + Constants.tierEmojis.get(user_info.getTier()), true);

            eb.addField("Country", EmojiUtil.getEmojiByAlias(Constants.iso3ToIso2(user_info.getCountry_code()).toLowerCase()), true);

            eb.addField("Level", user_info.getLevel(), true);
            eb.addField("µ", "" + user_info.getMu(), true);
            eb.addField("σ", "" + user_info.getSigma(), true);
            eb.addField("Rating", "" + user_info.getScore(), true);
            if (user_info.getTeam_id() != -1) {
                eb.addField("Team", "" + user_info.getTeam_name(), true);
            }
            if(isBotUploaded){
                eb.addField("Language", "" + bot_info.getLanguage(), true);
                eb.addField("Version", "" + bot_info.getVersion_number(), true);
                eb.addField("Games Played", "" + bot_info.getGames_played(), true);

                eb.setThumbnail("https://github.com/" + user_info.getProfile_image_key() + ".png");
            }


            objMsgCh.sendMessage(eb.build()).queue();
        } else if (command.equals("register")) {
            DiscordUser du = new DiscordUser(input, objUser.getId());

            eb = new EmbedBuilder();

            eb.setColor(Constants.HALITE_BLUE);


            try {
                Halite.getUserInfo(input);
            } catch (Exception e) {
                eb.setColor(Constants.WARNING_RED);
                eb.setTitle("Register failed. ID invalid. Please type in the format `!register user_id` where user_id is your Halite id. " +
                        "For example, `!register 216`. " +
                        "To find your halite ID, go to http://halite.io/user/?me and get the numbers that follow ?user_id=");

                objMsgCh.sendMessage(eb.build()).queue();
                return;
            }
            eb.setTitle("Register complete.");

            if(Database.hasUser(objUser.getId())){
                Database.removeUser(objUser.getId());
                eb.setTitle("User ID updated.");
            }

            Database.addUser(du);
            Database.save(Constants.database);

            objMsgCh.sendMessage(eb.build()).queue();
            System.out.println(du.halite_id + ", " + du.discord_id);
        } else if (command.equals("me")) {
            user_info = null;
            eb = new EmbedBuilder();
            String user_id = "";
            try{
                user_id = Database.getUser(objUser.getId()).halite_id;
            }catch(Exception e){
                eb.setColor(Constants.WARNING_RED);
                eb.setTitle("You are not registered. Type !register user_id where user_id is your Halite ID to register.");
                objMsgCh.sendMessage(eb.build()).queue();
                return;
            }

            try {
                user_info = Halite.getUserInfo(user_id);
            } catch (Exception e) {
                eb.setColor(Constants.WARNING_RED);
                eb.setTitle("Something went wrong. Please ping <@194857448673247235> for help.");

                objMsgCh.sendMessage(eb.build()).queue();
                return;
            }

            boolean isBotUploaded = true;
            try{
                bot_info = Halite.getBotInfo(user_id);
            } catch(Exception e){
                eb.setColor(Constants.WARNING_RED);
                eb.setTitle("It seems like " + user_info.getUsername() + " doesn't have a bot uploaded!");
                isBotUploaded = false;
                objMsgCh.sendMessage(eb.build()).queue();
                return;
            }
            eb = new EmbedBuilder();

            eb.setColor(Constants.HALITE_BLUE);

            eb.setTitle(user_info.getUsername(), "https://halite.io/user/?user_id=" + user_info.getUser_id());
            eb.setDescription(user_info.getOrganization());
            eb.addField("Rank", "" + user_info.getRank(), true);
            eb.addField("Tier", "" + Constants.tierEmojis.get(user_info.getTier()), true);
            eb.addField("Country", EmojiUtil.getEmojiByAlias(Constants.iso3ToIso2(user_info.getCountry_code()).toLowerCase()), true);
            eb.addField("Level", user_info.getLevel(), true);
            eb.addField("µ", "" + user_info.getMu(), true);
            eb.addField("σ", "" + user_info.getSigma(), true);
            eb.addField("Rating", "" + user_info.getScore(), true);
            if (user_info.getTeam_id() != -1) {
                eb.addField("Team", "" + user_info.getTeam_name(), true);
            }

            if(isBotUploaded){
                eb.addField("Language", "" + bot_info.getLanguage(), true);
                eb.addField("Version", "" + bot_info.getVersion_number(), true);
                eb.addField("Games Played", "" + bot_info.getGames_played(), true);

                eb.setThumbnail("https://github.com/" + user_info.getProfile_image_key() + ".png");
            }

            eb.setThumbnail("https://github.com/" + user_info.getProfile_image_key() + ".png");

            objMsgCh.sendMessage(eb.build()).queue();
        } else if (command.equals("rank")) {
            String user_id = input;
            if(Database.hasUser(objUser.getId()) && input.trim().equals("")){
                user_id = Database.getUser(objUser.getId()).halite_id;
            }else{
                user_id = input.trim();

                boolean isHaliteID = false;
                try{
                    int test = Integer.parseInt(input.trim());
                    isHaliteID = true;
                }catch(Exception e){}

                if(!isHaliteID){
                    Member m = objGuild.getMembersByEffectiveName(input,true).get(0);
                    user_id = Database.getUser(m.getUser().getId()).halite_id;
                }
            }

            user_info = null;
            eb = new EmbedBuilder();

            try {
                user_info = Halite.getUserInfo(user_id);
            } catch (Exception e) {
                eb.setColor(Constants.WARNING_RED);
                eb.setTitle("Something went wrong. Please ping @FrankWhoee for help.");
                eb.addField("Clickable mention", jda.getUserById(194857448673247235L).getAsMention(),true);

                objMsgCh.sendMessage(eb.build()).queue();
                return;
            }
            try{
                bot_info = Halite.getBotInfo(user_id);
            } catch(Exception e){
                eb.setColor(Constants.WARNING_RED);
                eb.setTitle("It seems like " + user_info.getUsername() + " doesn't have a bot uploaded!");

                objMsgCh.sendMessage(eb.build()).queue();
                return;
            }


            eb = new EmbedBuilder();

            eb.setColor(Constants.HALITE_BLUE);

            eb.setTitle(user_info.getUsername(), "https://halite.io/user/?user_id=" + user_info.getUser_id());
            eb.addField("Rank", "" + user_info.getRank(), true);
            eb.addField("Tier", "" + Constants.tierEmojis.get(user_info.getTier()), true);
            eb.addField("µ", "" + user_info.getMu(), true);
            eb.addField("σ", "" + user_info.getSigma(), true);
            eb.addField("Rating", "" + user_info.getScore(), true);
            if (user_info.getTeam_id() != -1) {
                eb.addField("Team", "" + user_info.getTeam_name(), true);
            }
            eb.setThumbnail("https://github.com/" + user_info.getProfile_image_key() + ".png");

            objMsgCh.sendMessage(eb.build()).queue();
        } else if (command.equals("top")) {
            eb = new EmbedBuilder();
            int size;
            if(null == input || input.length() < 1 || input.trim().length() < 1){
		size = Constants.TOP_DEFAULT_LENGTH;
	    }else{
		try{
		    size = Integer.parseInt(input.trim());
		}catch(NumberFormatException formatException){
		    size = Constants.TOP_DEFAULT_LENGTH;	
		}
	    } 
            ArrayList<Integer> top;
            try {
                top = Halite.getLeaderboard();
            } catch (Exception e) {
                eb.setColor(Constants.WARNING_RED);
                eb.setTitle("Something went wrong when fetching the leaderboard. Try again in a few minutes, or notify FrankWhoee.");
                eb.addField("Clickable mention", jda.getUserById(194857448673247235L).getAsMention(),true);

                objMsgCh.sendMessage(eb.build()).queue();
                return;
            }

            for(int i = top.size() - 1; i >= size; i--){
                top.remove(i);
            }


            eb.setColor(Constants.HALITE_BLUE);
            eb.setTitle("Leaderboard");
            eb.setDescription("Top " + size);
            for(Integer user_id : top){
                api.User user = null;
                try{
                    user = Halite.getUserInfo("" + user_id);
                }catch(Exception e){
                    e.printStackTrace();
                }


                eb.addField(Constants.tierEmojis.get(user.getTier()) + " | #" + user.getRank() + "   " + user.getUsername(),"", false);
                //eb.setThumbnail("https://github.com/" + user_info.getProfile_image_key() + ".png");

            }
            objMsgCh.sendMessage(eb.build()).queue();


        }
    }

}
