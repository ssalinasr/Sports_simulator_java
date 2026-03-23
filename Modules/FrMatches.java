/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import Tools.Database.DBConsultas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Flia_Salinas
 */
public class FrMatches {
    
    public ArrayList<ArrayList<String>> groups = new ArrayList<>();
    public String mat = "";
    
    public void create_frMatches() throws SQLException{
        
        DBConsultas con = new DBConsultas();
        ResultSet r = con.getTeams();
        
        ArrayList<String> teams = new ArrayList<>();
        
        
        while(r.next()){
            teams.add(r.getString("team_name"));
        }
        
        while(teams.size() > 6){
            ArrayList<String> temp = new ArrayList<>();
            
            while(temp.size() < 4){
                int rand = (int)Math.floor(Math.random()*teams.size());
                String e = teams.get(rand);
                temp.add(e);
                teams.remove(rand); 
            }
            groups.add(temp);
        }
        
        while(teams.size() > 0){
            
        ArrayList<String> temp = new ArrayList<>();
            
            while(temp.size() < 3){
                int rand = (int)Math.floor(Math.random()*teams.size());
                String e = teams.get(rand);
                temp.add(e);
                teams.remove(rand); 
            }
            groups.add(temp);
            
        }
        
        for(ArrayList<String> e: groups){
            mat += e + "\n";
            System.out.println(e);
        }
    }
    
    
}
