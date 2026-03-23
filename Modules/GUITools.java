/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.*;

/**
 *
 * @author Takamaru Senshi
 */
public class GUITools {

    /* Public object from this class*/
    private static GUITools guitools;

    private GUITools() {
    } //Private constructor to avoid multiple instances

    /*Function to get this class instance or create one if it's null */
    public static GUITools getInstance() {
        if (guitools == null) {
            guitools = new GUITools();
        }
        return guitools;
    }

    public void setTeams(JComboBox TxtNameTeam1, JComboBox TxtNameTeam2, String team1, String team2) {
        TxtNameTeam1.getModel().setSelectedItem(team1);
        TxtNameTeam2.getModel().setSelectedItem(team2);
    }

    public void resetGoals(String[] goals) {
        goals[0] = null;
        goals[1] = null;
    }

    /**
     * Imprime la falta en el tablero del encuentro
     *
     * @param MatchArea1
     * @param team_name: Nombre del equipo
     * @param time: Minuto del evento
     * @param team: 1 o 2
     */
    public void putGoal(JTextArea MatchArea1, String team_name, String time, int team) {
        if (team == 1) {
            MatchArea1.setForeground(Color.green);
            MatchArea1.setText(MatchArea1.getText() + time + "' Goal from " + team_name + ".\n");
            MatchArea1.setForeground(Color.black);

        } else {
            MatchArea1.setForeground(Color.green);
            MatchArea1.setText(MatchArea1.getText() + time + "' Goal from " + team_name + ".\n");
            MatchArea1.setForeground(Color.black);
        }
    }

    /**
     * Imprime la falta en el tablero del encuentro
     *
     * @param MatchArea1
     * @param team_name: Nombre del equipo
     * @param time: Minuto del evento
     * @param team: 1 o 2
     */
    public void putPoint(JTextArea MatchArea1, String team_name, String time, int team) {
        if (team == 1) {
            MatchArea1.setForeground(Color.green);
            MatchArea1.setText(MatchArea1.getText() + "--> Point from " + team_name + ".\n");
            MatchArea1.setForeground(Color.black);

        } else {
            MatchArea1.setForeground(Color.green);
            MatchArea1.setText(MatchArea1.getText() + "--> Point from " + team_name + ".\n");
            MatchArea1.setForeground(Color.black);
        }
    }

    /**
     * Imprime el texto asociado al inicio del partido
     *
     * @param MatchArea1
     */
    public void putBegin(JTextArea MatchArea1) {
        MatchArea1.setText(MatchArea1.getText() + "Match Started." + "\n");
    }

    /**
     * Imprime el texto asociado al final del primer tiempo
     *
     * @param MatchArea1
     */
    public void putMidtime(JTextArea MatchArea1) {
        MatchArea1.setText(MatchArea1.getText() + "First time finished." + "\n");
    }

    /**
     * Imprime el texto asociado al final del primer tiempo
     *
     * @param MatchArea1
     */
    public void putTime(JTextArea MatchArea1) {
        MatchArea1.setText(MatchArea1.getText() + "Time finished." + "\n");
    }

    /**
     * Imprime el texto asociado al final del segundo tiempo si hay empate y TE
     *
     * @param MatchArea1
     */
    public void putEndtime(JTextArea MatchArea1) {
        MatchArea1.setText(MatchArea1.getText() + "Second time finished." + "\n");
    }

    /**
     * Imprime el texto asociado al final del segundo tiempo si hay empate y TE
     *
     * @param MatchArea1
     */
    public void putExtime(JTextArea MatchArea1) {
        MatchArea1.setText(MatchArea1.getText() + "First Extra time finished." + "\n");
    }

    /**
     * Imprime el texto asociado al final del segundo tiempo extra si hay empate
     * y TE
     *
     * @param MatchArea1
     */
    public void putSecExtime(JTextArea MatchArea1) {
        MatchArea1.setText(MatchArea1.getText() + "Second Extra time finished." + "\n");
    }

    /**
     * Imprime el texto asociado al final del partido, mostrando el resultado
     * por penales
     *
     * @param info: resultado del partido (ganador y marcador)
     * @param MatchArea1
     */
    public void putWinnerPen(String info, JTextArea MatchArea1) {
        MatchArea1.setText(MatchArea1.getText() + "Penalties finished." + "\n");
        MatchArea1.setText(MatchArea1.getText() + info + "\n");
        MatchArea1.setText(MatchArea1.getText() + "Match Finished." + "\n");
    }

    /**
     * Imprime el texto asociado al final del partido, mostrando el resultado
     *
     * @param info: resultado del partido (ganador y marcador)
     * @param MatchArea1
     */
    public void putWinner(String info, JTextArea MatchArea1) {
        MatchArea1.setText(MatchArea1.getText() + info + "\n");
        MatchArea1.setText(MatchArea1.getText() + "Match Finished." + "\n");
    }

    /**
     * Limpia el tablero del encuentro
     *
     * @param MatchArea1
     */
    public void cleanMatch(JTextArea MatchArea1) {
        MatchArea1.setText(null);
    }

    /* Creates an mood state to a team before the match */
    public int createState() {
        int rand = 1 + (int) Math.floor(Math.random() * 6);
        switch (rand) {
            case 1:
                return -7;
            case 2:
                return -3;
            case 3:
                return 0;
            case 4:
                return 3;
            case 5:
                return 7;
            default:
                return 0;
        }
    }

    /**
     * Función para redondear valores (posesión)
     *
     * @param value: valor a redondear
     * @param places: cantidad de decimales a fijar
     * @return el valor redondeado
     */
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
