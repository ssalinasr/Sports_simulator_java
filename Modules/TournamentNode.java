/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules;

/**
 *
 * @author Salinas
 */
public class TournamentNode {
    
    private Team element;
    private TournamentNode left_node;
    private TournamentNode right_node;
    
    public TournamentNode(Team element){
        this.element = element;
        this.left_node = null;
        this.right_node = null;
    }

    public Team getElement() {
        return element;
    }

    public void setElement(Team element) {
        this.element = element;
    }

    public TournamentNode getLeft_node() {
        return left_node;
    }

    public void setLeft_node(TournamentNode left_node) {
        this.left_node = left_node;
    }

    public TournamentNode getRight_node() {
        return right_node;
    }

    public void setRight_node(TournamentNode right_node) {
        this.right_node = right_node;
    }
    
    
    
}
