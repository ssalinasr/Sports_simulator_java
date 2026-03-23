/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules;

import java.util.ArrayList;

/**
 *
 * @author Salinas
 */
public class TeamOlympic extends Team {

    private double result;
    private ArrayList<Integer> sum_values = new ArrayList<>();
    private ArrayList<Integer> win_values = new ArrayList<>();

    public TeamOlympic(String name, double levelteam, double teamdiff, ArrayList<Integer> sum_values,
            ArrayList<Integer> win_values, String ruta_e,
            String ruta_b) {
        super(name, levelteam, teamdiff, ruta_e, ruta_b);
        this.sum_values = sum_values;
        this.win_values = win_values;
        this.result = 0.000;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int getSumRankValue(int index) {
        return this.sum_values.get(index);
    }

    public int getWinRankValue(int index) {
        return this.win_values.get(index);
    }

}
