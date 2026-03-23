
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces.Sports;

import Modules.Team;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Takamaru Senshi
 */
public interface MainInterface {

    /* Get values from database by game played */
    public ArrayList<Team> getDatabaseValues();

    /* Get difference between two teams levels */
    public void getDifference(Team t1, Team t2);

    /* Get local buff added to a team level */
    public void getLocalBuff(Team t1, Team t2);

    /* Get sucess probability used in an instance */
    /**
     * Calcula el # de posibilidades de gol para un equipo Una diferencia grande
     * positiva, implica un número MENOR de posibilidades, por lo que un gol es
     * MÁS probable. Una diferencia grande negativa, implica un número MAYOR de
     * posibilidades, por lo que un gol es MENOS probable. Una diferencia
     * pequeña positiva/negativa o de cero, implica un número MAYOR de
     * posibilidades, por lo que un gol es MENOS probable.
     *
     * @param difTeam1: Diferencia calculada del equipo local
     * @param difTeam2: Diferencia calculada del equipo visitante
     * @return la cantidad de posibilidades de gol para el equipo
     */
    public double getSucessProbability(double difTeam1, double difTeam2);

    /* Get possesion probability used in an instance */
    public double getPossesionValue(double difTeam1, double difTeam2, double globalpass);

    /* generate random value using probability to get a team's actions related to Goals/Points */
    /**
     * Calcula el evento de gol para el equipo asociado en un segundo específico
     *
     * @param difTeam: diferencia calculada del equipo
     * @param sucessProb: # de posibilidades asociadas al equipo (ver función
     * anterior)
     * @param team
     * @param id
     * @param Match1
     * @param time
     */
    public void generateRandomInstance(double difTeam, double sucessProb, Team team, int id
    ,JTextArea Match1, int time);

        /**
     * Función controladora de los eventos del equipo local Fija goles, posesión
     * y pases del equipo local Envia datos al tablero del encuentro Genera las
     * acciones para después del encuentro (activación de botones)
     *
     * @param timeelapsed
     * @param team1
     * @param team2
     * @param CheckTE
     * @param isDoubleRound
     * @param auto
     * @param duration
     * @param Match1
     * @param GoalTeam1
     * @param GoalTeam2
     * @param timer
     * @param gcounter
     * @throws java.lang.InterruptedException
     */
    public void goalsInstantMatch(double timeelapsed, Team team1, Team team2, JCheckBox CheckTE,
            boolean isDoubleRound, boolean auto, int duration, JTextArea Match1,
            JLabel GoalTeam1, JLabel GoalTeam2, JProgressBar timer, int gcounter) throws InterruptedException;

}
