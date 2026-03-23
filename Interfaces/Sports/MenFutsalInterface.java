/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces.Sports;

import Modules.DefaultValues;
import Modules.GUITools;
import Modules.Team;
import Modules.TeamFootball;
import Tools.Database.DBConsultas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Salinas
 */
public class MenFutsalInterface implements MainInterface {

    public GUITools tools;

    public MenFutsalInterface() {
        this.tools = GUITools.getInstance();
    }

    @Override
    public ArrayList<Team> getDatabaseValues() {
        ArrayList<Team> database = new ArrayList<>();
        try {
            DBConsultas r = new DBConsultas();
            ResultSet rs = r.getTeams();
            while (rs.next()) {
                String n = rs.getString("team_name");
                int d = rs.getInt("team_def");
                int m = rs.getInt("team_mid");
                int a = rs.getInt("team_atk");
                String b = rs.getString("ruta");
                String e = rs.getString("ruta_esc");
                double levelteam = (a + m + d) / 3;
                TeamFootball tm = new TeamFootball(n, levelteam, 0.0, e, b, a, d, m);
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
            t1.setLevelteam(t1.getLevelteam() + 21.0);
        }

        if (t1.getLevelteam() < 90 && t1.getLevelteam() >= 80) {
            t1.setLevelteam(t1.getLevelteam() + 16.5);
        }

        if (t1.getLevelteam() < 80 && t1.getLevelteam() >= 70) {
            t1.setLevelteam(t1.getLevelteam() + 10.5);
        }

        if (t1.getLevelteam() < 70 && t1.getLevelteam() >= 60) {
            t1.setLevelteam(t1.getLevelteam() + 6.5);
        }

        if (t1.getLevelteam() < 60 && t1.getLevelteam() >= 50) {
            t1.setLevelteam(t1.getLevelteam() + 3.5);
        }

        this.getDifference(t1, t2);
    }

    @Override
    public double getSucessProbability(double difTeam1, double difTeam2) {
        double diff = difTeam1 - difTeam2;

        if (diff < -70) { //Diferencia menor a -70 (negativa máxima)
            return DefaultValues.MEN_FUTSAL_PROB_L1;
        }
        if (diff >= -70 && diff <= -60) { //Diferencia entre -60 y 70 (negativa aún más grande)
            return DefaultValues.MEN_FUTSAL_PROB_L2;
        }

        if (diff >= -60 && diff <= -50) { //Diferencia entre -50 y 60 (negativa muy grande)
            return DefaultValues.MEN_FUTSAL_PROB_L3;
        }

        if (diff >= -50 && diff <= -40) { //Diferencia entre -50 y -40 (negativa grande)
            return DefaultValues.MEN_FUTSAL_PROB_L4;
        }

        if (diff >= -40 && diff <= -30) { //Diferencia entre -40 y -30 (negativa grande)
            return DefaultValues.MEN_FUTSAL_PROB_L5;
        }

        if (diff >= -30 && diff <= -20) { //Diferencia entre -30 y -20 (negativa media)
            return DefaultValues.MEN_FUTSAL_PROB_L6;
        }
        if (diff >= -20 && diff <= -10) { //Diferencia entre -20 y -10 (negativa pequeña)
            return DefaultValues.MEN_FUTSAL_PROB_L7;
        }
        if (diff >= -10 && diff < 0) { //Diferencia entre -10 y  0 (negativa mínima)
            return DefaultValues.MEN_FUTSAL_PROB_L8;
        }

        if (diff >= 0 && diff <= 10) { //Diferencia entre 0 y 10 (positiva mínima)
            return DefaultValues.MEN_FUTSAL_PROB_L9;
        }
        if (diff >= 10 && diff <= 20) { //Diferencia entre 10 y 20 (positiva pequeña)
            return DefaultValues.MEN_FUTSAL_PROB_L10;
        }
        if (diff >= 20 && diff <= 30) { //Diferencia entre 20 y 30 (positiva media)
            return DefaultValues.MEN_FUTSAL_PROB_L11;
        }

        if (diff >= 30 && diff <= 40) { //Diferencia entre 30 y 40 (positiva grande)
            return DefaultValues.MEN_FUTSAL_PROB_L12;
        }

        if (diff >= 40 && diff <= 50) { //Diferencia entre 40 y 50 (positiva grande)
            return DefaultValues.MEN_FUTSAL_PROB_L13;
        }
        if (diff >= 50 && diff <= 60) { //Diferencia entre 50 y 60(positiva muy grande)
            return DefaultValues.MEN_FUTSAL_PROB_L14;
        }

        if (diff >= 60 && diff <= 70) { //Diferencia entre 60 y 70(positiva aún más grande)
            return DefaultValues.MEN_FUTSAL_PROB_L15;
        }

        if (diff > 70) { //Diferencia mayor a 70 (Positiva máxima)
            return DefaultValues.MEN_FUTSAL_PROB_L16;
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
           tools.putGoal(Match1, team.getName(), time + "", id);
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

        while (counter < timeelapsed) {
            generateRandomInstance(team1.getTeamdiff(), prob1, team1, 1, Match1, counter);
            generateRandomInstance(team2.getTeamdiff(), prob2, team2, 2, Match1, counter);
            GoalTeam1.setText(Integer.toString(team1.getGoalsteam()));
            GoalTeam2.setText(Integer.toString(team2.getGoalsteam()));
            timer.setMaximum(40);
            timer.setValue(counter);
            timer.setString(counter + 1 + "'");

            

            if (counter == 39 && (team1.getGoalsteam() == team2.getGoalsteam()) && gcounter == 0) {
                if (CheckTE.isSelected()) {
                    timeelapsed += 5;
                }
            }

            if (isDoubleRound && counter == 39 && (team1.getGoalsteam() == team2.getGoalsteam()) && gcounter == 2) {
                CheckTE.setSelected(true);
                if (CheckTE.isSelected()) {
                    timeelapsed += 5;
                }
            }

            if (counter == 39) {
                tools.putEndtime(Match1);
            } else {
            }

            if (counter == 45) {
                tools.putExtime(Match1);
            } else {
            }

            if (counter == 20) {
                if (!auto) {
                }
                tools.putMidtime(Match1);
            } else {

            }
            counter++;
        }
        String info = team1.getGoalsteam() + "-" + team2.getGoalsteam();
        tools.putWinner(info, Match1);

    }

    /**
     * Método que realliza la ronda de penales si los tiempos extra existen
     *
     * @param GoalPT1
     * @param GoalPT2
     * @param Team1
     * @param Team2
     * @param Match1
     */
    public void penaltiesMethod(JLabel GoalPT1, JLabel GoalPT2, Team Team1, Team Team2,
            JTextArea Match1) {

        GoalPT1.setVisible(true);
        GoalPT2.setVisible(true);

        int rand_team1 = 0;
        int rand_team2 = 0;
        int rounds = 0;

        int pen_team1 = 0;
        int pen_team2 = 0;

        boolean notWinner = true;
        String win = "";

        while (notWinner) {

            rand_team1 = (int) Math.floor(Math.random() * 2);
            rand_team2 = (int) Math.floor(Math.random() * 2);

            if (rand_team1 == 1) {
                pen_team1++;
            }
            if (rand_team2 == 1) {
                pen_team2++;
            }

            rounds++;

            if (rounds >= 3) {

                if (pen_team1 > pen_team2) {
                    win = "El ganador es: \n"
                            + Team1.getName() + "\n"
                            + "Por: " + pen_team1 + "-" + pen_team2;
                    GoalPT1.setText(pen_team1 + "");
                    GoalPT2.setText(pen_team2 + "");
                  //  JOptionPane.showMessageDialog(null, win, "Info", 1);
                    tools.putWinnerPen(win, Match1);
                    break;
                }

                if (pen_team2 > pen_team1) {
                    win = "El ganador es: \n"
                            + Team2.getName() + "\n"
                            + "Por: " + pen_team1 + "-" + pen_team2;
                    GoalPT1.setText(pen_team1 + "");
                    GoalPT2.setText(pen_team2 + "");
                   // JOptionPane.showMessageDialog(null, win, "Info", 1);
                    tools.putWinnerPen(win, Match1);
                    break;
                }
            }
        }

    }

}
