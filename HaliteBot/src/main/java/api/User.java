package api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import database.Database;
import net.dv8tion.jda.core.entities.MessageEmbed;

public class User {

    //User information
    private String country_code;
    private String country_subdivision_code;
    private boolean is_email_good;
    private boolean is_gpu_enabled;
    private String level;
    private double mu;
    private int num_bots;
    private int num_games;
    private int num_submissions;
    private String oauth_provider;
    private String organization;
    private int organization_id;
    private int rank;
    private double score;
    private double sigma;
    private int team_id;
    private int team_leader_id;
    private String team_name;
    private String tier;
    private int user_id;
    private String username;
    private String profile_image_key;

    //Bot information
    private int bot_id;
    private String compilation_status;
    private int games_played;
    private String language;
    private int version_number;

    public User(String country_code, String country_subdivision_code, boolean is_email_good, boolean is_gpu_enabled, String level, double mu, int num_bots, int num_games, int num_submissions, String oauth_provider, String organization, int organization_id, int rank, double score, double sigma, int team_id, int team_leader_id, String team_name, String tier, int user_id, String username, String profile_image_key) {
        this.country_code = country_code;
        this.country_subdivision_code = country_subdivision_code;
        this.is_email_good = is_email_good;
        this.is_gpu_enabled = is_gpu_enabled;
        this.level = level;
        this.mu = mu;
        this.num_bots = num_bots;
        this.num_games = num_games;
        this.num_submissions = num_submissions;
        this.oauth_provider = oauth_provider;
        this.organization = organization;
        this.organization_id = organization_id;
        this.rank = rank;
        this.score = score;
        this.sigma = sigma;
        this.team_id = team_id;
        this.team_leader_id = team_leader_id;
        this.team_name = team_name;
        this.tier = tier;
        this.user_id = user_id;
        this.username = username;
        this.profile_image_key = profile_image_key;
    }

    public User(String country_code, String country_subdivision_code, boolean is_email_good, boolean is_gpu_enabled, String level, double mu, int num_bots, int num_games, int num_submissions, String oauth_provider, String organization, int organization_id, int rank, double score, double sigma, int team_id, int team_leader_id, String team_name, String tier, int user_id, String username, int bot_id, String compilation_status, int games_played, String language, int version_number, String profile_image_key) {
        this.country_code = country_code;
        this.country_subdivision_code = country_subdivision_code;
        this.is_email_good = is_email_good;
        this.is_gpu_enabled = is_gpu_enabled;
        this.level = level;
        this.mu = mu;
        this.num_bots = num_bots;
        this.num_games = num_games;
        this.num_submissions = num_submissions;
        this.oauth_provider = oauth_provider;
        this.organization = organization;
        this.organization_id = organization_id;
        this.rank = rank;
        this.score = score;
        this.sigma = sigma;
        this.team_id = team_id;
        this.team_leader_id = team_leader_id;
        this.team_name = team_name;
        this.tier = tier;
        this.user_id = user_id;
        this.username = username;
        this.bot_id = bot_id;
        this.compilation_status = compilation_status;
        this.games_played = games_played;
        this.language = language;
        this.version_number = version_number;
        this.profile_image_key = profile_image_key;
    }

    public User(double mu, int rank, double score, double sigma, int bot_id, String compilation_status, int games_played, String language, int version_number) {
        this.mu = mu;
        this.rank = rank;
        this.score = score;
        this.sigma = sigma;
        this.bot_id = bot_id;
        this.compilation_status = compilation_status;
        this.games_played = games_played;
        this.language = language;
        this.version_number = version_number;
    }

    public static User fromJsonFull(String strJson){
        JsonArray json = new JsonParser().parse(strJson).getAsJsonArray();
        JsonObject info = json.get(0).getAsJsonObject();


        //User information
        String country_code = "NA";
        try{
            country_code = info.get("country_code").getAsString();
        }catch(Exception e){}

        String country_subdivision_code = "NA";
        try{
            country_subdivision_code = info.get("country_subdivision_code").getAsString();
        }catch(Exception e){}

        boolean is_email_good = false;
        try{
            is_email_good = info.get("is_email_good").getAsBoolean();
        }catch(Exception e){}

        boolean is_gpu_enabled = false;
        try{
            is_gpu_enabled = info.get("is_gpu_enabled").getAsBoolean();
        }catch(Exception e){}

        String level = "NA";
        try{
            level = info.get("level").getAsString();
        }catch(Exception e){}

        double mu = -1;
        try{
            mu = info.get("mu").getAsDouble();
        }catch(Exception e){}

        int num_bots = -1;
        try{
            num_bots = info.get("num_bots").getAsInt();
        }catch(Exception e){}

        int num_games = -1;
        try{
            num_games = info.get("num_games").getAsInt();
        }catch(Exception e){}

        int num_submissions = -1;
        try{
            num_submissions = info.get("num_submissions").getAsInt();
        }catch(Exception e){}

        String oauth_provider = "NA";
        try{
            oauth_provider = info.get("oauth_provider").getAsString();
        }catch(Exception e){}

        String organization = "NA";
        try{
             organization = info.get("organization").getAsString();
        }catch(Exception e){}

        int organization_id = -1;
        try{
             organization_id = info.get("organization_id").getAsInt();
        }catch(Exception e){}

        int rank = -1;
        try{
            rank = info.get("rank").getAsInt();
        }catch(Exception e){}

        double score = -1;
        try{
            score = info.get("score").getAsDouble();
        }catch(Exception e){}

        double sigma = -1;
        try{
            sigma = info.get("sigma").getAsDouble();
        }catch(Exception e){}

        int team_id = -1;
        try{
            team_id = info.get("team_id").getAsInt();
        }catch(Exception e){}

        int team_leader_id = -1;
        try{
            team_leader_id = info.get("team_leader_id").getAsInt();
        }catch(Exception e){}

        String team_name = "NA";
        try{
            team_name = info.get("team_name").getAsString();
        }catch(Exception e){}

        String tier = "NA";
        try{
            tier = info.get("tier").getAsString();
        }catch(Exception e){}

        int user_id = -1;
        try{
            user_id = info.get("user_id").getAsInt();
        }catch(Exception e){}

        String username = "NA";
        try{
             username = info.get("username").getAsString();
        }catch(Exception e){}

        String profile_image_key = "https://upload.wikimedia.org/wikipedia/commons/9/93/Default_profile_picture_%28male%29_on_Facebook.jpg";
        try{
            profile_image_key = info.get("profile_image_key").getAsString();
        }catch(Exception e){}

        //Bot information
        int bot_id = info.get("bot_id").getAsInt();
        String compilation_status = info.get("compilation_status").getAsString();
        int games_played = info.get("games_played").getAsInt();
        String language = info.get("language").getAsString();
        int version_number = info.get("version_number").getAsInt();

        return new User(country_code, country_subdivision_code, is_email_good, is_gpu_enabled,level, mu, num_bots, num_games, num_submissions, oauth_provider, organization, organization_id, rank, score, sigma, team_id, team_leader_id, team_name, tier, user_id, username, bot_id, compilation_status, games_played, language, version_number, profile_image_key);
    }

    public static User fromJsonBot(String strJson) throws Exception{
        JsonArray json = new JsonParser().parse(strJson).getAsJsonArray();
        JsonObject info = json.get(0).getAsJsonObject();

        //Bot information
        double mu = info.get("mu").getAsDouble();
        int rank = info.get("rank").getAsInt();
        double score = info.get("score").getAsDouble();
        double sigma = info.get("sigma").getAsDouble();
        int bot_id = info.get("bot_id").getAsInt();
        String compilation_status = info.get("compilation_status").getAsString();
        int games_played = info.get("games_played").getAsInt();
        String language = info.get("language").getAsString();
        int version_number = info.get("version_number").getAsInt();

        return new User(mu, rank, score, sigma, bot_id, compilation_status, games_played, language, version_number);
    }

    public String getProfile_image_key() {
        return profile_image_key;
    }

    public void setProfile_image_key(String profile_image_key) {
        this.profile_image_key = profile_image_key;
    }

    public static User fromJsonUser(String strJson){
        JsonObject info = new JsonParser().parse(strJson).getAsJsonObject();


        //User information
        String country_code = "NA";
        try{
            country_code = info.get("country_code").getAsString();
        }catch(Exception e){}

        String country_subdivision_code = "NA";
        try{
            country_subdivision_code = info.get("country_subdivision_code").getAsString();
        }catch(Exception e){}

        boolean is_email_good = false;
        try{
            is_email_good = info.get("is_email_good").getAsBoolean();
        }catch(Exception e){}

        boolean is_gpu_enabled = false;
        try{
            is_gpu_enabled = info.get("is_gpu_enabled").getAsBoolean();
        }catch(Exception e){}

        String level = "NA";
        try{
            level = info.get("level").getAsString();
        }catch(Exception e){}

        double mu = -1;
        try{
            mu = info.get("mu").getAsDouble();
        }catch(Exception e){}

        int num_bots = -1;
        try{
            num_bots = info.get("num_bots").getAsInt();
        }catch(Exception e){}

        int num_games = -1;
        try{
            num_games = info.get("num_games").getAsInt();
        }catch(Exception e){}

        int num_submissions = -1;
        try{
            num_submissions = info.get("num_submissions").getAsInt();
        }catch(Exception e){}

        String oauth_provider = "NA";
        try{
            oauth_provider = info.get("oauth_provider").getAsString();
        }catch(Exception e){}

        String organization = "NA";
        try{
            organization = info.get("organization").getAsString();
        }catch(Exception e){}

        int organization_id = -1;
        try{
            organization_id = info.get("organization_id").getAsInt();
        }catch(Exception e){}

        int rank = -1;
        try{
            rank = info.get("rank").getAsInt();
        }catch(Exception e){}

        double score = -1;
        try{
            score = info.get("score").getAsDouble();
        }catch(Exception e){}

        double sigma = -1;
        try{
            sigma = info.get("sigma").getAsDouble();
        }catch(Exception e){}

        int team_id = -1;
        try{
            team_id = info.get("team_id").getAsInt();
        }catch(Exception e){}

        int team_leader_id = -1;
        try{
            team_leader_id = info.get("team_leader_id").getAsInt();
        }catch(Exception e){}

        String team_name = "NA";
        try{
            team_name = info.get("team_name").getAsString();
        }catch(Exception e){}

        String tier = "NA";
        try{
            tier = info.get("tier").getAsString();
        }catch(Exception e){}

        int user_id = -1;
        try{
            user_id = info.get("user_id").getAsInt();
        }catch(Exception e){}

        String username = "NA";
        try{
            username = info.get("username").getAsString();
        }catch(Exception e){}

        String profile_image_key = "https://upload.wikimedia.org/wikipedia/commons/9/93/Default_profile_picture_%28male%29_on_Facebook.jpg";
        try{
            profile_image_key = info.get("profile_image_key").getAsString();
        }catch(Exception e){}

        return new User(country_code, country_subdivision_code, is_email_good, is_gpu_enabled,level, mu, num_bots, num_games, num_submissions, oauth_provider, organization, organization_id, rank, score, sigma, team_id, team_leader_id, team_name, tier, user_id, username, profile_image_key);
    }


    public String getCountry_code() {
        return country_code;
    }

    public String getCountry_subdivision_code() {
        return country_subdivision_code;
    }

    public boolean isIs_email_good() {
        return is_email_good;
    }

    public boolean isIs_gpu_enabled() {
        return is_gpu_enabled;
    }

    public String getLevel() {
        return level;
    }

    public double getMu() {
        return mu;
    }

    public int getNum_bots() {
        return num_bots;
    }

    public int getNum_games() {
        return num_games;
    }

    public int getNum_submissions() {
        return num_submissions;
    }

    public String getOauth_provider() {
        return oauth_provider;
    }

    public String getOrganization() {
        return organization;
    }

    public int getOrganization_id() {
        return organization_id;
    }

    public int getRank() {
        return rank;
    }

    public double getScore() {
        return score;
    }

    public double getSigma() {
        return sigma;
    }

    public int getTeam_id() {
        return team_id;
    }

    public int getTeam_leader_id() {
        return team_leader_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public String getTier() {
        return tier;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public int getBot_id() {
        return bot_id;
    }

    public String getCompilation_status() {
        return compilation_status;
    }

    public int getGames_played() {
        return games_played;
    }

    public String getLanguage() {
        return language;
    }

    public int getVersion_number() {
        return version_number;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public void setCountry_subdivision_code(String country_subdivision_code) {
        this.country_subdivision_code = country_subdivision_code;
    }

    public void setIs_email_good(boolean is_email_good) {
        this.is_email_good = is_email_good;
    }

    public void setIs_gpu_enabled(boolean is_gpu_enabled) {
        this.is_gpu_enabled = is_gpu_enabled;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public void setNum_bots(int num_bots) {
        this.num_bots = num_bots;
    }

    public void setNum_games(int num_games) {
        this.num_games = num_games;
    }

    public void setNum_submissions(int num_submissions) {
        this.num_submissions = num_submissions;
    }

    public void setOauth_provider(String oauth_provider) {
        this.oauth_provider = oauth_provider;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setOrganization_id(int organization_id) {
        this.organization_id = organization_id;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setSigma(double sigma) {
        this.sigma = sigma;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public void setTeam_leader_id(int team_leader_id) {
        this.team_leader_id = team_leader_id;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBot_id(int bot_id) {
        this.bot_id = bot_id;
    }

    public void setCompilation_status(String compilation_status) {
        this.compilation_status = compilation_status;
    }

    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setVersion_number(int version_number) {
        this.version_number = version_number;
    }
}
