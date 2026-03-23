/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import java.util.Comparator;

/**
 *
 * @author Flia_Salinas
 */
public class TableTeam implements Comparable<TableTeam> {
    
    private String name;
    private int gf;
    private int gc;
    private int pts;
    private int vic;
    private int der;
    private int emp;
    private int dif;
    
    public TableTeam(String name){
        this.name = name;
        this.gf= 0;
        this.gc= 0;
        this.pts= 0;
        this.vic= 0;
        this.der= 0;
        this.emp= 0;
        this.dif= 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGf() {
        return gf;
    }

    public void setGf(int gf) {
        this.gf = gf;
    }

    public int getGc() {
        return gc;
    }

    public void setGc(int gc) {
        this.gc = gc;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public int getVic() {
        return vic;
    }

    public void setVic(int vic) {
        this.vic = vic;
    }

    public int getDer() {
        return der;
    }

    public void setDer(int der) {
        this.der = der;
    }

    public int getEmp() {
        return emp;
    }

    public void setEmp(int emp) {
        this.emp = emp;
    }

    public int getDif() {
        return dif;
    }

    public void setDif(int dif) {
        this.dif = dif;
    }
    
    public void print(){
        System.out.print(this.name+" ");
        System.out.print(this.pts+" ");
        System.out.print(this.vic+" ");
        System.out.print(this.der+" ");
        System.out.print(this.emp+" ");
        System.out.print(this.gf+" ");
        System.out.print(this.gc+" ");
        System.out.print(this.dif+" ");
    }

    @Override
    public int compareTo(TableTeam t) {
        return t.getPts() - this.getPts();
    }

}
