package api;

public class DiscordUser {

    public String halite_id;
    public String discord_id;

    public DiscordUser(String halite_id, String discord_id) {
        this.halite_id = halite_id;
        this.discord_id = discord_id;
    }

    public String getHalite_id() {
        return halite_id;
    }

    public void setHalite_id(String halite_id) {
        this.halite_id = halite_id;
    }

    public String getDiscord_id() {
        return discord_id;
    }

    public void setDiscord_id(String discord_id) {
        this.discord_id = discord_id;
    }
}
