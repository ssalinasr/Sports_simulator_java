/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules;

/**
 *
 * @author Salinas
 */
public class TournamentTree {

    private TournamentNode root;
    static final int COUNT = 10;
    private boolean done = false;

    public TournamentTree() {
        this.root = new TournamentNode(new Team(".", 0.0, 0.0, "", ""));
    }

    public TournamentNode getRoot() {
        return root;
    }

    public void setRoot(TournamentNode root) {
        this.root = root;
    }

    public void generateTree(int Nteams) {

        if (isPowerOfTwo(Nteams) && Nteams % 2 == 0) {
            int counter = 0;
            switch (Nteams) {
                case 16:
                    while (counter < 4) {
                        this.fillNode(this.root);
                        counter++;
                    }
                    break;

                case 32:
                    while (counter < 5) {
                        this.fillNode(this.root);
                        counter++;
                    }
                    break;

                case 8:
                    while (counter < 3) {
                        this.fillNode(this.root);
                        counter++;
                    }
                    break;

                case 4:
                    while (counter < 2) {
                        this.fillNode(this.root);
                        counter++;
                    }
                    break;
                case 2:
                    while (counter < 1) {
                        this.fillNode(this.root);
                        counter++;
                    }
                    break;
            }

        } else {
            System.out.println("Cantidad de equipos no válida");
        }

    }

    public void fillNode(TournamentNode node) {
        if (node.getLeft_node() == null && node.getRight_node() == null) {
            node.setLeft_node(new TournamentNode(new Team("**", 0.0, 0.0, "", "")));
            node.setRight_node(new TournamentNode(new Team("**", 0.0, 0.0, "", "")));
        } else {
            this.fillNode(node.getLeft_node());
            this.fillNode(node.getRight_node());
        }
    }

    public void changeNode(TournamentNode node, Team t) {
        if (done) {
            done = false;
        }
        // base case
        if (node == null) {
            return;
        }

        if (node.getLeft_node() == null && node.getRight_node() == null
                && node.getElement().getName().equals("**")) {
            node.setElement(t);
            done = true;
        }
        if (!done) {
            changeNode(node.getLeft_node(), t);
        }
        if (!done) {
            changeNode(node.getRight_node(), t);
        }
    }

    public void changeFilledNode(TournamentNode node, Team t1, Team t2, Team win) {
        if (done) {
            done = false;
        }
        // base case
        if (node == null) {
            return;
        }
        try{
        if (node.getLeft_node().getElement().equals(t1) 
                && node.getRight_node().getElement().equals(t2)) {
            node.setElement(win);
            done = true;
        }
        }catch(Exception e){
            System.out.println("Dead end here, proceeding...");
        }
        if (!done) {
            changeFilledNode(node.getLeft_node(), t1, t2, win);
        }
        if (!done) {
            changeFilledNode(node.getRight_node(), t1, t2, win);
        }
    }

    /* Function to check if x is power of 2*/
    boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }

        return (int) (Math.ceil((Math.log(n) / Math.log(2))))
                == (int) (Math.floor(
                        ((Math.log(n) / Math.log(2)))));
    }

    // Function to print binary tree in 2D
    // It does reverse inorder traversal
    static void print2DUtil(TournamentNode root, int space) {
        // Base case
        if (root == null) {
            return;
        }

        // Increase distance between levels
        space += COUNT;

        // Process right child first
        print2DUtil(root.getRight_node(), space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = COUNT; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(root.getElement().getName() + " "+ root.getElement().getGoalsteam()+ "\n");

        // Process left child
        print2DUtil(root.getLeft_node(), space);
    }

    // Wrapper over print2DUtil()
    public void print2D(TournamentNode root) {
        // Pass initial space count as 0
        print2DUtil(root, 0);
    }

    public void printLeafNodes(TournamentNode node) {
        // base case
        if (node == null) {
            return;
        }

        if (node.getLeft_node() == null && node.getRight_node() == null) {
            System.out.print(node.getElement().getName() + " "+ node.getElement().getGoalsteam());
        }

        printLeafNodes(node.getLeft_node());
        printLeafNodes(node.getRight_node());
    }

}
