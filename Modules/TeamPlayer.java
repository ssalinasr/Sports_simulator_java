/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules;

/**
 *
 * @author Salinas
 */
public class TeamPlayer extends Team{
    
    public TeamPlayer(String name, double levelteam, double teamdiff, String ruta_e,
            String ruta_b, String mode){
        super(name, levelteam, teamdiff, ruta_e, ruta_b);
        switch(mode){
            case "kart":
                super.setGoalsteam(3);
                break;
            case "stamina":
                super.setGoalsteam(150);
                break;
            case "lives":
                super.setGoalsteam(6);
                break;
            case "other":
                super.setGoalsteam(0);
                break;
            default:
                super.setGoalsteam(0);
                break;
        }
    }
    
}
