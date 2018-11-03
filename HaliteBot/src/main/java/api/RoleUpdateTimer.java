package api;

import constants.Constants;
import core.App;
import database.Database;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.managers.GuildController;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class RoleUpdateTimer extends TimerTask {

    public boolean isCompleteFirstRun = false;

    @Override
    public void run() {
        for(DiscordUser u : Database.getUsers()) {
            Guild g = App.jda.getGuildById(248237939123945493L);
            User user = null;
            try {
                user = Halite.getUserInfo(u.halite_id);
            } catch (Exception e) {
                continue;
            }
            if (user.getTier() == null) {
                continue;
            }
            net.dv8tion.jda.core.entities.User objUser = App.jda.getUserById(u.discord_id);
            Member m;
            try {
                m = g.getMember(objUser);
            } catch (Exception e) {
                continue;
            }
            if(m == null){
                continue;
            }
            GuildController gc = g.getController();
            Role r = null;
            try {
                r = Constants.tierRoles.get(user.getTier());
            } catch (Exception e) {
                continue;
            }
            if (r == null) {
                continue;
            }
            boolean mustRefresh = false;
            for(Role role : m.getRoles()){
                if(!role.getName().equals(r.getName()) && Constants.tierRoles.containsKey(role.getName())){
                    mustRefresh = true;
                    break;
                }
            }
            if(!m.getRoles().contains(r) || mustRefresh){
                gc.removeRolesFromMember(m, Constants.tierRoles.values()).queue();
                gc.addSingleRoleToMember(m, r).queue();
            }
        }
        isCompleteFirstRun = true;
        System.out.println("Roles updated.");
    }

}
