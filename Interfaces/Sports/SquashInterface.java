/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces.Sports;

import Modules.DefaultValues;
import Modules.GUITools;
import Modules.Team;
import Modules.TeamFootball;
import Modules.TeamOlympic;
import Tools.Database.DBConsultas;
import Tools.Database.DBConsultasOL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 *
 * @author Salinas
 */
public class SquashInterface implements MainInterface {

    public GUITools tools;

    public SquashInterface() {
        this.tools = tools.getInstance();
    }

    @Override
    public ArrayList<Team> getDatabaseValues() {
        ArrayList<Team> database = new ArrayList<>();
        try {
            DBConsultasOL r = new DBConsultasOL();
            ResultSet rs = r.getTeamsOL();
            while (rs.next()) {

                //System.out.println(rs.getString("team_name"));
                String n = rs.getString("team_name");
                double a = rs.getDouble("promedio_dep");
                String b = rs.getString("ruta");
                ArrayList<Integer> act_sum_values = new ArrayList<>();
                ArrayList<Integer> act_win_values = new ArrayList<>();
                TeamOlympic tm = new TeamOlympic(n, a, 0.0, act_sum_values,act_win_values, b, b);
                database.add(tm);
            }

            r.cn.desconectar();
            return database;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            return database;
        }
    }

    @Override
    public void getDifference(Team t1, Team t2) {

        double teamdiff1;
        double teamdiff2;

        teamdiff1 = (t1.getLevelteam() - t2.getLevelteam());
        teamdiff2 = (t2.getLevelteam() - t1.getLevelteam());
        t1.setTeamdiff(teamdiff1);
        t2.setTeamdiff(teamdiff2);

    }

    @Override
    public void getLocalBuff(Team t1, Team t2) {
        if (t1.getLevelteam() > 90) {
            t1.setLevelteam(t1.getLevelteam() + 21000.0);
        }

        if (t1.getLevelteam() < 90 && t1.getLevelteam() >= 80) {
            t1.setLevelteam(t1.getLevelteam() + 16000.5);
        }

        if (t1.getLevelteam() < 80 && t1.getLevelteam() >= 70) {
            t1.setLevelteam(t1.getLevelteam() + 10000.5);
        }

        if (t1.getLevelteam() < 70 && t1.getLevelteam() >= 60) {
            t1.setLevelteam(t1.getLevelteam() + 6000.5);
        }

        if (t1.getLevelteam() < 60 && t1.getLevelteam() >= 50) {
            t1.setLevelteam(t1.getLevelteam() + 3000.5);
        }

        this.getDifference(t1, t2);
    }

    @Override
    public double getSucessProbability(double difTeam1, double difTeam2) {
        double diff = difTeam1 - difTeam2;

        if (diff < -500000) { //Diferencia menor a -70 (negativa máxima)
            return DefaultValues.BASKETBALL_PROB_L1;
        }
        if (diff >= -500000 && diff <= -200000) { //Diferencia entre -60 y 70 (negativa aún más grande)
            return DefaultValues.BASKETBALL_PROB_L2;
        }

        if (diff >= -200000 && diff <= -150000) { //Diferencia entre -50 y 60 (negativa muy grande)
            return DefaultValues.BASKETBALL_PROB_L3;
        }

        if (diff >= -150000 && diff <= 150000) { //Diferencia entre -50 y -40 (negativa grande)
            return DefaultValues.BASKETBALL_PROB_L4;
        }

        if (diff >= 150000 && diff <= 500000) { //Diferencia entre -50 y -40 (negativa grande)
            return DefaultValues.BASKETBALL_PROB_L5;
        }

        if (diff > 500000) { //Diferencia entre -40 y -30 (negativa grande)
            return DefaultValues.BASKETBALL_PROB_L6;
        }

        return 499999999;  //valor por defecto (nunca utilizado)
    }

    @Override
    public double getPossesionValue(double difTeam1, double difTeam2, double globalpass) {
        double diff = difTeam1 - difTeam2;

        int sub_rand = 0;

        if (diff < -50) {
            sub_rand = 1 + (int) Math.floor(Math.random() * 2);
            int rand = (int) Math.floor(Math.random() * 2) + sub_rand;
            globalpass += rand;
            return rand;
        }

        if (diff >= -50 && diff <= -40) { //Minimal
            sub_rand = 1 + (int) Math.floor(Math.random() * 3);
            int rand = 1 + (int) Math.floor(Math.random() * 2) + sub_rand;
            globalpass += rand;
            return rand;
        }

        if (diff >= -40 && diff <= -30) { //Minimal
            sub_rand = 1 + (int) Math.floor(Math.random() * 4);
            int rand = 2 + (int) Math.floor(Math.random() * 2) + sub_rand;
            globalpass += rand;
            return rand;
        }

        if (diff >= -30 && diff <= -20) { //Minimal
            sub_rand = 1 + (int) Math.floor(Math.random() * 5);
            int rand = 3 + (int) Math.floor(Math.random() * 2) + sub_rand;
            globalpass += rand;
            return rand;
        }
        if (diff >= -20 && diff <= -10) {
            sub_rand = 1 + (int) Math.floor(Math.random() * 6);
            int rand = 5 + (int) Math.floor(Math.random() * 2) + sub_rand;
            globalpass += rand;
            return rand;
        }
        if (diff >= -10 && diff < 0) {
            sub_rand = 1 + (int) Math.floor(Math.random() * 8);
            int rand = 7 + (int) Math.floor(Math.random() * 3) + sub_rand;
            globalpass += rand;
            return rand;
        }

        if (diff >= 0 && diff <= 10) { //Minimal
            sub_rand = 1 + (int) Math.floor(Math.random() * 8);
            int rand = 7 + (int) Math.floor(Math.random() * 3) + sub_rand;
            globalpass += rand;
            return rand;
        }
        if (diff >= 10 && diff <= 20) {
            sub_rand = 1 + (int) Math.floor(Math.random() * 6);
            int rand = 5 + (int) Math.floor(Math.random() * 2) + sub_rand;
            globalpass += rand;
            return rand;
        }
        if (diff >= 20 && diff <= 30) {
            sub_rand = 1 + (int) Math.floor(Math.random() * 6);
            int rand = 3 + (int) Math.floor(Math.random() * 2) + sub_rand;
            globalpass += rand;
            return rand;
        }

        if (diff >= 30 && diff <= 40) {
            sub_rand = 1 + (int) Math.floor(Math.random() * 8);
            int rand = 5 + (int) Math.floor(Math.random() * 2) + sub_rand;
            globalpass += rand;
            return rand;
        }

        if (diff >= 40 && diff <= 50) {
            sub_rand = 1 + (int) Math.floor(Math.random() * 10);
            int rand = 7 + (int) Math.floor(Math.random() * 2) + sub_rand;
            globalpass += rand;
            return rand;
        }
        if (diff > 50) {
            sub_rand = 1 + (int) Math.floor(Math.random() * 12);
            int rand = 9 + (int) Math.floor(Math.random() * 2) + sub_rand;
            globalpass += rand;
            return rand;
        }

        return 0;
    }

    @Override
    public void generateRandomInstance(double difTeam, double sucessProb, Team team, int id,
            JTextArea Match1, int time) {
        int goal_Case = (int) Math.ceil(Math.random() * sucessProb);
        if (goal_Case == 1) {
            team.setGoalsteam(team.getGoalsteam() + 1);
            tools.putPoint(Match1, team.getName(), time + "", id);
        }

    }

    @Override
    public void goalsInstantMatch(double timeelapsed, Team team1, Team team2, JCheckBox CheckTE,
            boolean isDoubleRound, boolean auto, int duration, JTextArea Match1,
            JLabel GoalTeam1, JLabel GoalTeam2, JProgressBar timer, int gcounter) throws InterruptedException {

        double prob1 = getSucessProbability(team1.getTeamdiff(), team2.getTeamdiff());
        double prob2 = getSucessProbability(team2.getTeamdiff(), team1.getTeamdiff());

        System.out.println(prob1);
        System.out.println(prob2);
        System.out.println("");
        int counter = 0;
        tools.putBegin(Match1);

        while (true) {
            generateRandomInstance(team1.getTeamdiff(), prob1, team1, 1, Match1, counter);
            generateRandomInstance(team2.getTeamdiff(), prob2, team2, 2, Match1, counter);
            GoalTeam1.setText(Integer.toString(team1.getGoalsteam()));
            GoalTeam2.setText(Integer.toString(team2.getGoalsteam()));
            timer.setMaximum(counter);
            timer.setValue(counter);
            timer.setString(counter + 1 + "'");
            
            if (((team1.getGoalsteam() > 15 || team2.getGoalsteam() > 15) || (team1.getGoalsteam() > 14 && team2.getGoalsteam() - team1.getGoalsteam() < 2))) {
                break;
            }
        }
        String info = team1.getGoalsteam() + "-" + team2.getGoalsteam();
        tools.putWinner(info, Match1);

    }
}

