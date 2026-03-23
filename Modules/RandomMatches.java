/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import Tools.Database.DBConsultas;
import Tools.Database.DBConsultasOL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Flia_Salinas
 */
public class RandomMatches {

    public ArrayList<ArrayList<String>> groups = new ArrayList<>();
    public String mat = "";

    public void createRandomMatches() throws SQLException {

        DBConsultas con = new DBConsultas();
        ResultSet r = con.getTeams();

        ArrayList<String[]> teamsAf = new ArrayList<>();
        ArrayList<String[]> teamsAs = new ArrayList<>();
        ArrayList<String[]> teamsAm = new ArrayList<>();
        ArrayList<String[]> teamsEu = new ArrayList<>();

        while (r.next()) {
            if (r.getString("team_region").equals("Africa")) {
                String[] team = new String[2];
                team[0] = r.getString("team_name");
                team[1] = r.getString("team_region");
                teamsAf.add(team);
            }

            if (r.getString("team_region").equals("Asia/Oceania")) {
                String[] team = new String[2];
                team[0] = r.getString("team_name");
                team[1] = r.getString("team_region");
                teamsAs.add(team);
            }

            if (r.getString("team_region").equals("America")) {
                String[] team = new String[2];
                team[0] = r.getString("team_name");
                team[1] = r.getString("team_region");
                teamsAm.add(team);
            }

            if (r.getString("team_region").equals("Europa")) {
                String[] team = new String[2];
                team[0] = r.getString("team_name");
                team[1] = r.getString("team_region");
                teamsEu.add(team);
            }

        }

        //Africa
        mat += "África \n";
        while (teamsAf.size() > 0) {
            ArrayList<String> temp = new ArrayList<>();
            while (temp.size() < 6) {
                int rand = (int) Math.floor(Math.random() * teamsAf.size());
                String[] e = teamsAf.get(rand);
                if (e[1].equals("Africa")) {
                    temp.add(e[0]);
                    teamsAf.remove(rand);
                }
            }
            groups.add(temp);
            mat += temp + "\n";
        }

        //Asia/Oceania
        mat += "Asia/Oceania \n";
        while (teamsAs.size() > 49) {
            ArrayList<String> temp = new ArrayList<>();
            while (temp.size() < 8) {
                int rand = (int) Math.floor(Math.random() * teamsAs.size());
                String[] e = teamsAs.get(rand);
                if (e[1].equals("Asia/Oceania")) {
                    temp.add(e[0]);
                    teamsAs.remove(rand);
                }
            }
            groups.add(temp);
            mat += temp + "\n";
        }

        while (teamsAs.size() > 0) {
            ArrayList<String> temp = new ArrayList<>();
            while (temp.size() < 7) {
                int rand = (int) Math.floor(Math.random() * teamsAs.size());
                String[] e = teamsAs.get(rand);
                if (e[1].equals("Asia/Oceania")) {
                    temp.add(e[0]);
                    teamsAs.remove(rand);
                }
            }
            groups.add(temp);
            mat += temp + "\n";
        }

        //America
        mat += "América \n";
        while (teamsAm.size() > 7) {
            ArrayList<String> temp = new ArrayList<>();
            while (temp.size() < 8) {
                int rand = (int) Math.floor(Math.random() * teamsAm.size());
                String[] e = teamsAm.get(rand);
                if (e[1].equals("America")) {
                    temp.add(e[0]);
                    teamsAm.remove(rand);
                }
            }
            groups.add(temp);
            mat += temp + "\n";
        }

        while (teamsAm.size() > 0) {
            ArrayList<String> temp = new ArrayList<>();
            while (temp.size() < 7) {
                int rand = (int) Math.floor(Math.random() * teamsAm.size());
                String[] e = teamsAm.get(rand);
                if (e[1].equals("America")) {
                    temp.add(e[0]);
                    teamsAm.remove(rand);
                }
            }
            groups.add(temp);
            mat += temp + "\n";
        }

        //Europa
        mat += "Europa \n";
        while (teamsEu.size() > 49) {
            ArrayList<String> temp = new ArrayList<>();
            while (temp.size() < 8) {
                int rand = (int) Math.floor(Math.random() * teamsEu.size());
                String[] e = teamsEu.get(rand);
                if (e[1].equals("Europa")) {
                    temp.add(e[0]);
                    teamsEu.remove(rand);
                }
            }
            groups.add(temp);
            mat += temp + "\n";
        }

        while (teamsEu.size() > 0) {
            ArrayList<String> temp = new ArrayList<>();
            while (temp.size() < 7) {
                int rand = (int) Math.floor(Math.random() * teamsEu.size());
                String[] e = teamsEu.get(rand);
                if (e[1].equals("Europa")) {
                    temp.add(e[0]);
                    teamsEu.remove(rand);
                }
            }
            groups.add(temp);
            mat += temp + "\n";
        }

        for (ArrayList<String> e : groups) {
            System.out.println(e);
        }
    }

    public void createRandomMatchesGm(boolean isGroups) throws SQLException {
        DBConsultasOL con = new DBConsultasOL();
        ResultSet r = con.getPlayersSsb();

        ArrayList<String> teams_SSB = new ArrayList<>();
        if (!isGroups) {
            while (r.next()) {
                String player = r.getString("player_name");
                if (!player.contains("/")) {
                    teams_SSB.add(r.getString("player_name"));
                }
            }
            while (teams_SSB.size() > 5) {
                ArrayList<String> temp = new ArrayList<>();
                while (temp.size() < 4) {
                    int rand = (int) Math.floor(Math.random() * teams_SSB.size());
                    String e = teams_SSB.get(rand);
                    temp.add(e);
                    teams_SSB.remove(rand);
                }
                groups.add(temp);
            }

            while (!teams_SSB.isEmpty()) {
                ArrayList<String> temp = new ArrayList<>();
                while (temp.size() < 5) {
                    int rand = (int) Math.floor(Math.random() * teams_SSB.size());
                    String e = teams_SSB.get(rand);
                    temp.add(e);
                    teams_SSB.remove(rand);
                }
                groups.add(temp);
            }
        } else {
            while (r.next()) {
                String player = r.getString("player_name");
                if (player.contains("/") || player.equals("Samus")) {
                    teams_SSB.add(r.getString("player_name"));
                }
            }
            while (teams_SSB.size() > 5) {
                ArrayList<String> temp = new ArrayList<>();
                while (temp.size() < 4) {
                    int rand = (int) Math.floor(Math.random() * teams_SSB.size());
                    String e = teams_SSB.get(rand);
                    temp.add(e);
                    teams_SSB.remove(rand);
                }
                groups.add(temp);
            }

            while (!teams_SSB.isEmpty()) {
                ArrayList<String> temp = new ArrayList<>();
                while (temp.size() < 5) {
                    int rand = (int) Math.floor(Math.random() * teams_SSB.size());
                    String e = teams_SSB.get(rand);
                    temp.add(e);
                    teams_SSB.remove(rand);
                }
                groups.add(temp);
            }
        }

        for (ArrayList<String> e : groups) {
            mat += e + "\n";
            System.out.println(e);
        }

    }

    public void createRandomMatchesGeye(boolean isGroups) throws SQLException {
        DBConsultasOL con = new DBConsultasOL();
        

        ArrayList<String> teams_GE = new ArrayList<>();
        if (!isGroups) {
            ResultSet r = con.getPlayersGeyeInd();
            while (r.next()) {
                String player = r.getString("player_name");
                if (!player.contains("/")) {
                    teams_GE.add(r.getString("player_name"));
                }
            }
            while (teams_GE.size() > 8) {
                ArrayList<String> temp = new ArrayList<>();
                while (temp.size() < 3) {
                    int rand = (int) Math.floor(Math.random() * teams_GE.size());
                    String e = teams_GE.get(rand);
                    temp.add(e);
                    teams_GE.remove(rand);
                }
                groups.add(temp);
            }

            while (!teams_GE.isEmpty()) {
                ArrayList<String> temp = new ArrayList<>();
                while (temp.size() < 4) {
                    int rand = (int) Math.floor(Math.random() * teams_GE.size());
                    String e = teams_GE.get(rand);
                    temp.add(e);
                    teams_GE.remove(rand);
                }
                groups.add(temp);
            }
        } else {
            ResultSet r = con.getPlayersGeye();
            while (r.next()) {
                teams_GE.add(r.getString("player_name"));

            }

            while (!teams_GE.isEmpty()) {
                ArrayList<String> temp = new ArrayList<>();
                while (temp.size() < 4) {
                    int rand = (int) Math.floor(Math.random() * teams_GE.size());
                    String e = teams_GE.get(rand);
                    temp.add(e);
                    teams_GE.remove(rand);
                }
                groups.add(temp);
            }
        }

        for (ArrayList<String> e : groups) {
            mat += e + "\n";
            System.out.println(e);
        }

    }

    public void createRandomMatchesOl() throws SQLException {

        DBConsultas con = new DBConsultas();
        ResultSet r = con.getTeams();

        ArrayList<String[]> teamsAf = new ArrayList<>();
        ArrayList<String[]> teamsAs = new ArrayList<>();
        ArrayList<String[]> teamsAm = new ArrayList<>();
        ArrayList<String[]> teamsEu = new ArrayList<>();

        while (r.next()) {
            if (r.getString("team_region").equals("Africa")) {
                String[] team = new String[2];
                team[0] = r.getString("team_name");
                team[1] = r.getString("team_region");
                teamsAf.add(team);
            }

            if (r.getString("team_region").equals("Asia/Oceania")) {
                String[] team = new String[2];
                team[0] = r.getString("team_name");
                team[1] = r.getString("team_region");
                teamsAs.add(team);
            }

            if (r.getString("team_region").equals("America")) {
                String[] team = new String[2];
                team[0] = r.getString("team_name");
                team[1] = r.getString("team_region");
                teamsAm.add(team);
            }

            if (r.getString("team_region").equals("Europa")) {
                String[] team = new String[2];
                team[0] = r.getString("team_name");
                team[1] = r.getString("team_region");
                teamsEu.add(team);
            }

        }

        //Africa
        mat += "África \n";
        while (teamsAf.size() > 26) {
            ArrayList<String> temp = new ArrayList<>();
            while (temp.size() < 14) {
                int rand = (int) Math.floor(Math.random() * teamsAf.size());
                String[] e = teamsAf.get(rand);
                if (e[1].equals("Africa")) {
                    temp.add(e[0]);
                    teamsAf.remove(rand);
                }
            }
            groups.add(temp);
            mat += temp + "\n";
        }

        while (teamsAf.size() > 0) {
            ArrayList<String> temp = new ArrayList<>();
            while (temp.size() < 13) {
                int rand = (int) Math.floor(Math.random() * teamsAf.size());
                String[] e = teamsAf.get(rand);
                if (e[1].equals("Africa")) {
                    temp.add(e[0]);
                    teamsAf.remove(rand);
                }
            }
            groups.add(temp);
            mat += temp + "\n";
        }

        //Asia/Oceania
        mat += "Asia/Oceania \n";
        while (teamsAs.size() > 48) {
            ArrayList<String> temp = new ArrayList<>();
            while (temp.size() < 17) {
                int rand = (int) Math.floor(Math.random() * teamsAs.size());
                String[] e = teamsAs.get(rand);
                if (e[1].equals("Asia/Oceania")) {
                    temp.add(e[0]);
                    teamsAs.remove(rand);
                }
            }
            groups.add(temp);
            mat += temp + "\n";
        }

        while (teamsAs.size() > 0) {
            ArrayList<String> temp = new ArrayList<>();
            while (temp.size() < 16) {
                int rand = (int) Math.floor(Math.random() * teamsAs.size());
                String[] e = teamsAs.get(rand);
                if (e[1].equals("Asia/Oceania")) {
                    temp.add(e[0]);
                    teamsAs.remove(rand);
                }
            }
            groups.add(temp);
            mat += temp + "\n";
        }

        //America
        mat += "América \n";
        while (teamsAm.size() > 11) {
            ArrayList<String> temp = new ArrayList<>();
            while (temp.size() < 12) {
                int rand = (int) Math.floor(Math.random() * teamsAm.size());
                String[] e = teamsAm.get(rand);
                if (e[1].equals("America")) {
                    temp.add(e[0]);
                    teamsAm.remove(rand);
                }
            }
            groups.add(temp);
            mat += temp + "\n";
        }

        while (teamsAm.size() > 0) {
            ArrayList<String> temp = new ArrayList<>();
            while (temp.size() < 11) {
                int rand = (int) Math.floor(Math.random() * teamsAm.size());
                String[] e = teamsAm.get(rand);
                if (e[1].equals("America")) {
                    temp.add(e[0]);
                    teamsAm.remove(rand);
                }
            }
            groups.add(temp);
            mat += temp + "\n";
        }

        //Europa
        mat += "Europa \n";
        while (teamsEu.size() > 42) {
            ArrayList<String> temp = new ArrayList<>();
            while (temp.size() < 15) {
                int rand = (int) Math.floor(Math.random() * teamsEu.size());
                String[] e = teamsEu.get(rand);
                if (e[1].equals("Europa")) {
                    temp.add(e[0]);
                    teamsEu.remove(rand);
                }
            }
            groups.add(temp);
            mat += temp + "\n";
        }

        while (teamsEu.size() > 0) {
            ArrayList<String> temp = new ArrayList<>();
            while (temp.size() < 14) {
                int rand = (int) Math.floor(Math.random() * teamsEu.size());
                String[] e = teamsEu.get(rand);
                if (e[1].equals("Europa")) {
                    temp.add(e[0]);
                    teamsEu.remove(rand);
                }
            }
            groups.add(temp);
            mat += temp + "\n";
        }

        for (ArrayList<String> e : groups) {
            System.out.println(e);
        }
    }

    public void createGroups(Object[] teamsGr) throws SQLException {

        ArrayList<String> teams = new ArrayList<>();

        for (Object ob : teamsGr) {
            teams.add((String) ob);
        }

        while (teams.size() > 0) {
            ArrayList<String> temp = new ArrayList<>();

            while (temp.size() < 4) {
                int rand = (int) Math.floor(Math.random() * teams.size());
                String e = teams.get(rand);
                temp.add(e);
                teams.remove(rand);
            }
            groups.add(temp);
        }

        for (ArrayList<String> e : groups) {
            mat += e + "\n";
            System.out.println(e);
        }
    }

    public void createGroupsSSB(String mode) throws SQLException {
        DBConsultasOL con = new DBConsultasOL();
        ResultSet r = con.getPlayersSsb();
        ArrayList<String> teams = new ArrayList<>();

        while (r.next()) {
            if (mode.equals("teams")) {
                if (r.getString("player_name").contains("/") || r.getString("player_name").equals("Samus")) {
                    teams.add(r.getString("player_name"));
                }
            }
            if (mode.equals("individual")) {
                if (!r.getString("player_name").contains("/")) {
                    teams.add(r.getString("player_name"));
                }
            }
        }

        if (mode.equals("individual")) {
            while (teams.size() > 5) {
                ArrayList<String> temp = new ArrayList<>();

                while (temp.size() < 4) {
                    int rand = (int) Math.floor(Math.random() * teams.size());
                    String e = teams.get(rand);
                    temp.add(e);
                    teams.remove(rand);
                }
                groups.add(temp);
            }

            while (teams.size() > 0) {
                ArrayList<String> temp = new ArrayList<>();

                while (temp.size() < 5) {
                    int rand = (int) Math.floor(Math.random() * teams.size());
                    String e = teams.get(rand);
                    temp.add(e);
                    teams.remove(rand);
                }
                groups.add(temp);
            }
        }

        if (mode.equals("teams")) {
            while (teams.size() > 5) {
                ArrayList<String> temp = new ArrayList<>();

                while (temp.size() < 4) {
                    int rand = (int) Math.floor(Math.random() * teams.size());
                    String e = teams.get(rand);
                    temp.add(e);
                    teams.remove(rand);
                }
                groups.add(temp);
            }

            while (teams.size() > 0) {
                ArrayList<String> temp = new ArrayList<>();

                while (temp.size() < 5) {
                    int rand = (int) Math.floor(Math.random() * teams.size());
                    String e = teams.get(rand);
                    temp.add(e);
                    teams.remove(rand);
                }
                groups.add(temp);
            }
        }

        for (ArrayList<String> e : groups) {
            mat += e + "\n";
            System.out.println(e);
        }
    }

}
