/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules;

/**
 *
 * @author Salinas
 */
public class TeamFootball extends Team {
    
    private boolean status; //Estado de actividad del equipo en el encuentro
    
    private double at; //Nivel de ataque del equipo
    private double md; //Nivel de mediocampo del equipo
    private double df; //Nivel de defensa del equipo
       
    public TeamFootball(String name, double levelteam, double teamdiff, String ruta_e, 
            String ruta_b, int at, int df, int md){
        super(name, levelteam, teamdiff, ruta_e, ruta_b);
        this.at = at;
        this.df = df;
        this.md = md;
           
        this.status = false;
        
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getAt() {
        return at;
    }

    public void setAt(double at) {
        this.at = at;
    }

    public double getMd() {
        return md;
    }

    public void setMd(double md) {
        this.md = md;
    }

    public double getDf() {
        return df;
    }

    public void setDf(double df) {
        this.df = df;
    }

}
