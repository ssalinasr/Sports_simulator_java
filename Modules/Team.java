/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules;

/**
 *
 * @author Salinas
 */
public class Team {

    private String name;
    private double levelteam;
    private double teamdiff;
    private String ruta_e;
    private String ruta_b;
    private int goalsteam;
    private int globalgoalsteam;

    public Team(String name, double levelteam, double teamdiff, String ruta_e, String ruta_b) {
        this.name = name;
        this.levelteam = levelteam;
        this.teamdiff = teamdiff;
        this.ruta_e = ruta_e;
        this.ruta_b = ruta_b;
        this.goalsteam = 0;
        this.globalgoalsteam = 0;
    }

    public int getGoalsteam() {
        return goalsteam;
    }

    public void setGoalsteam(int goalsteam) {
        this.goalsteam = goalsteam;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLevelteam() {
        return levelteam;
    }

    public void setLevelteam(double levelteam) {
        this.levelteam = levelteam;
    }

    public double getTeamdiff() {
        return teamdiff;
    }

    public void setTeamdiff(double teamdiff) {
        this.teamdiff = teamdiff;
    }

    public String getRuta_e() {
        return ruta_e;
    }

    public void setRuta_e(String ruta_e) {
        this.ruta_e = ruta_e;
    }

    public String getRuta_b() {
        return ruta_b;
    }

    public void setRuta_b(String ruta_b) {
        this.ruta_b = ruta_b;
    }

    public int getGlobalgoalsteam() {
        return globalgoalsteam;
    }

    public void setGlobalgoalsteam(int globalgoalsteam) {
        this.globalgoalsteam = globalgoalsteam;
    }

    
    
}
