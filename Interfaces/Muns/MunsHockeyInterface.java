/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces.Muns;

import Interfaces.Games.*;
import Modules.DefaultValues;
import Modules.GUITools;
import Modules.Team;
import Modules.TeamPlayer;
import Tools.Database.DBConsultasOL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 *
 * @author Salinas
 */
public class MunsHockeyInterface implements MainInterface {

    public GUITools tools;

    public MunsHockeyInterface() {
        this.tools = tools.getInstance();
    }

    public ArrayList<String> getCountries() {
        ArrayList<String> countries = new ArrayList<>();
        DBConsultasOL r = new DBConsultasOL();
        ResultSet rs;
        try {
            rs = r.getCountriesMun();
            while (rs.next()) {
                countries.add(rs.getString("country"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            System.out.println(ex);
            return countries;
        }

        return countries;
    }

    public ArrayList<String> getPlayerByCountry(String country) {
        ArrayList<String> players = new ArrayList<>();
        DBConsultasOL r = new DBConsultasOL();
        ResultSet rs;
        try {
            rs = r.getPlayersMunByCountry(country);
            while (rs.next()) {
                players.add(rs.getString("mun_name"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            System.out.println(ex);
            return players;
        }

        return players;
    }

    @Override
    public ArrayList<Team> getDatabaseValues() {
        ArrayList<Team> database = new ArrayList<>();
        DBConsultasOL r = new DBConsultasOL();
        ResultSet rs;
        try {
            rs = r.getPlayersMun();
            while (rs.next()) {

                //System.out.println(rs.getString("team_name"));
                String n = rs.getString("mun_name");
                double d = rs.getDouble("value");
                String b = rs.getString("country");
                TeamPlayer tm = new TeamPlayer(n, d, 0.0, b, b, "other");
                database.add(tm);

            }
            r.cn.desconectar();
            return database;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            System.out.println(ex);
            return database;
        }

    }

    public ArrayList<Team> getDatabaseValuesCountries() {
        ArrayList<Team> database = new ArrayList<>();
        DBConsultasOL r = new DBConsultasOL();
        ResultSet rs;
        try {
            rs = r.getPlayersMunCountries();
            while (rs.next()) {

                //System.out.println(rs.getString("team_name"));
                String n = rs.getString("mun_name");
                double d = rs.getDouble("value");
                String b = rs.getString("country");
                TeamPlayer tm = new TeamPlayer(n, d, 0.0, b, b, "other");
                database.add(tm);

            }
            r.cn.desconectar();
            return database;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            System.out.println(ex);
            return database;
        }

    }

    @Override
    public double getSucessProbability(double difTeam1, double difTeam2) {
        return DefaultValues.MUNS_PROB_LD;
    }

    @Override
    public void generateRandomInstance(double difTeam, double sucessProb, Team team1, Team team2, int id, JTextArea Match1, int time) {
        int goal_Case = (int) Math.ceil(Math.random() * sucessProb);
        if (goal_Case == 1) {
            team1.setGoalsteam(team1.getGoalsteam() + 1);
            tools.putPoint(Match1, team1.getName(), time + "", id);
        }
    }

    @Override
    public void goalsInstantMatch(double timeelapsed, Team team1,
            Team team2, JCheckBox CheckTE,
            boolean isDoubleRound, boolean auto, int duration, JTextArea Match1,
            JLabel GoalTeam1, JLabel GoalTeam2,
            JProgressBar timer, int gcounter) throws InterruptedException {

        double prob1 = getSucessProbability(team1.getTeamdiff(), team2.getTeamdiff());
        double prob2 = getSucessProbability(team2.getTeamdiff(), team1.getTeamdiff());

        System.out.println(prob1);
        System.out.println(prob2);
        System.out.println("");
        int counter = 0;
        tools.putBegin(Match1);

        while (counter < timeelapsed) {
            generateRandomInstance(team1.getTeamdiff(), prob1, team1, team2, 1, Match1, counter);
            generateRandomInstance(team2.getTeamdiff(), prob2, team2, team1, 2, Match1, counter);
            GoalTeam1.setText(Integer.toString(team1.getGoalsteam()));
            GoalTeam2.setText(Integer.toString(team2.getGoalsteam()));
            timer.setMaximum((int) timeelapsed);
            timer.setValue(counter);
            timer.setString(counter + 1 + "'");
            if ((counter == (timeelapsed - 2)) && (team1.getGoalsteam() == team2.getGoalsteam())) {
                timeelapsed += 50;
            }

            System.out.println("Dif1: " + team1.getTeamdiff() + " Dif2: " + team2.getTeamdiff() + " time: " + counter + " " + team1.getGoalsteam() + "-" + team2.getGoalsteam());
            counter++;
        }
        String info = team1.getGoalsteam() + "-" + team2.getGoalsteam();
        tools.putWinner(info, Match1);

    }

}
