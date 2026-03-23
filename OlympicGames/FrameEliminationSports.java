/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package OlympicGames;

import Interfaces.Sports.BasketballInterface;
import Interfaces.Sports.MenFootballInterface;
import Interfaces.Sports.WomenFootballInterface;
import Main.FrameInicio;
import Modules.TableTeam;
import Modules.Team;
import Modules.TournamentNode;
import Modules.TournamentTree;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Salinas
 */
public class FrameEliminationSports extends javax.swing.JFrame {

    private ArrayList<Team> database = new ArrayList<>();
    private ArrayList<Team> selected_teams = new ArrayList<>();
    private ArrayList<String> teams = new ArrayList<>();
    private DefaultListModel modlist = new DefaultListModel();
    private ArrayList<ArrayList<Integer>> points = new ArrayList<>();
    ArrayList<TableTeam> tableteam = new ArrayList<>();
    private TournamentTree tree;
    private FrameOlympic ol;
    private ArrayList<Vector> results = new ArrayList<>();
    private String thirdTeam1;
    private String thirdTeam2;

    private DefaultTableModel tblres = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    /* Tree Parameters */
    private int XINI = 15;
    private int YINI = 15;
    private int XEND = XINI + 100;
    private int YEND = YINI;
    private int ROUNDNESS = 10;
    private Color bracketsColor = Color.WHITE;
    private Color resultsColor = Color.CYAN;
    private Color textColor = Color.BLACK;

    /**
     * Creates new form FrameBrackets
     */
    public FrameEliminationSports() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Elimination League");
        setResizable(false);
        this.tree = new TournamentTree();
        this.BtnGenerateTree.setEnabled(false);
        this.BtnThirdPlace.setEnabled(false);
        getContentPane().setBackground(new Color(0, 51, 51));
        this.initTableGUI();
        this.initTableResults();
    }

    public void setComboSportsValue(String sport) {
        this.ComboSports.getModel().setSelectedItem(sport);
    }

    public void setComboTeamsValue(String team) {
        this.ComboTeams.getModel().setSelectedItem(team);
    }

    public JTable getSimTable() {
        return this.TablePoints;
    }

    public RowSorter<? extends TableModel> getSimTableSorter() {
        RowSorter<? extends TableModel> rowSorter = this.TablePoints.getRowSorter();
        return rowSorter;
    }

    public void setNumberTeams(String numTeams) {
        this.ComboNumberTeams.getModel().setSelectedItem(numTeams);
    }

    public JLabel getTreeLabel() {
        return this.LblTree;
    }

    public TournamentNode getTreeGraphics() {
        return this.tree.getRoot();
    }

    public void addTeam() {
        if (this.ComboTeams.getSelectedItem().toString().equals("select team")) {
            JOptionPane.showMessageDialog(null, "Illegal Value", "Error", 0);
        }

        ListModel vm = this.ListSelectedTeams.getModel();
        boolean isDuplicated = false;

        for (int i = 0; i < vm.getSize(); i++) {
            if (vm.getElementAt(i).equals(this.ComboTeams.getSelectedItem())) {
                JOptionPane.showMessageDialog(null, "This team is already selected.", "Warning", 1);
                isDuplicated = true;
            }
        }

        int numTeams = Integer.parseInt(this.ComboNumberTeams.getSelectedItem().toString());

        if (!this.ComboTeams.getSelectedItem().toString().equals("select team") && !isDuplicated) {
            if (!(this.modlist.size() >= numTeams)) {
                this.modlist.addElement(this.ComboTeams.getSelectedItem());
                this.ListSelectedTeams.setModel(modlist);
            } else {
                JOptionPane.showMessageDialog(null, "Can't add more teams with this configuration.", "Warning", 1);
            }
        }
    }

    public void changeTeam() {
        ListModel vm = this.ListSelectedTeams.getModel();
        boolean isDuplicated = false;

        for (int i = 0; i < vm.getSize(); i++) {
            if (vm.getElementAt(i).equals(this.ComboTeams.getSelectedItem())) {
                JOptionPane.showMessageDialog(null, "This team is already selected.", "Warning", 1);
                isDuplicated = true;
            }
        }

        if (!isDuplicated) {
            int vs = this.ListSelectedTeams.getSelectedIndex();
            this.modlist.setElementAt(this.ComboTeams.getSelectedItem(), vs);
            this.ListSelectedTeams.setModel(modlist);
        }
    }

    public void removeTeam() {
        int vs = this.ListSelectedTeams.getSelectedIndex();
        this.modlist.remove(vs);
        this.ListSelectedTeams.setModel(modlist);
    }

    public void generateTree() {
        this.BtnThirdPlace.setEnabled(true);
        this.displayTree(this.LblTree.getGraphics(), this.tree.getRoot(), this.LblTree.getWidth() / 2, vGap,
                this.LblTree.getWidth() / 4);
    }

    public void thirdPlace() {
        try {
            String team1 = JOptionPane.showInputDialog("Please input first team (at the left): ");
            String team2 = JOptionPane.showInputDialog("Please input second team (at the right): ");
            ArrayList<Team> third_teams = new ArrayList<>();
            for (Team s : this.database) {
                if (s.getName().equals(team1)) {
                    third_teams.add(s);
                    break;
                }
            }

            for (Team s : this.database) {
                if (s.getName().equals(team2)) {
                    third_teams.add(s);
                    break;
                }
            }

            String sport = (String) this.ComboSports.getSelectedItem();

            switch (sport) {
                case "MenFootball":
                    this.ol = new FrameOlympic("MenFootball", third_teams.get(0).getName(), third_teams.get(1).getName(), true);
                    this.ol.autoExecution();
                    break;
                case "WomenFootball":
                    this.ol = new FrameOlympic("WomenFootball", third_teams.get(0).getName(), third_teams.get(1).getName(), true);
                    this.ol.autoExecution();
                    break;
                case "MenFutsal":
                    this.ol = new FrameOlympic("MenFutsal", third_teams.get(0).getName(), third_teams.get(1).getName(), true);
                    this.ol.autoExecution();
                    break;
                case "Volleyball":
                    this.ol = new FrameOlympic("Volleyball", third_teams.get(0).getName(), third_teams.get(1).getName(), true);
                    this.ol.autoExecution();
                    break;
                case "Tennis":
                    this.ol = new FrameOlympic("Tennis", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "TableTennis":
                    this.ol = new FrameOlympic("TableTennis", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "Baseball":
                    this.ol = new FrameOlympic("Baseball", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "Basketball":
                    this.ol = new FrameOlympic("Basketball", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "Rugby":
                    this.ol = new FrameOlympic("Rugby", third_teams.get(0).getName(), third_teams.get(1).getName(), true);
                    this.ol.autoExecution();
                    break;
                case "Handball":
                    this.ol = new FrameOlympic("Handball", third_teams.get(0).getName(), third_teams.get(1).getName(), true);
                    this.ol.autoExecution();
                    break;
                case "Squash":
                    this.ol = new FrameOlympic("Squash", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "Hockey":
                    this.ol = new FrameOlympic("Hockey", third_teams.get(0).getName(), third_teams.get(1).getName(), true);
                    this.ol.autoExecution();
                    break;
                case "Archery":
                    this.ol = new FrameOlympic("Archery", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "Fencing":
                    this.ol = new FrameOlympic("Fencing", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "Curling":
                    this.ol = new FrameOlympic("Curling", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "Badminton":
                    this.ol = new FrameOlympic("Badminton", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "BeachVolley":
                    this.ol = new FrameOlympic("BeachVolley", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "Waterpolo":
                    this.ol = new FrameOlympic("Waterpolo", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
            }

            String goals[] = this.ol.goals;
            String penalties[] = this.ol.penalties;
            if (penalties[0] == null) {
                penalties[0] = "" + 0;
            }

            if (penalties[1] == null) {
                penalties[1] = "" + 0;
            }
            this.LblThirdResult.setText(third_teams.get(0).getName() + " " + goals[0] + "(" + penalties[0] + ")" + "-" + third_teams.get(1).getName() + " " + goals[1] + "(" + penalties[1] + ")");
            String[] results = new String[4];

            results[0] = third_teams.get(0).getName();
            results[0] = results[0].replaceAll("\\s", "|");
            results[1] = goals[0];
            results[2] = third_teams.get(1).getName();
            results[2] = results[2].replaceAll("\\s", "|");
            results[3] = goals[1];
            this.putResults(results);
            this.tableteam.clear();
            this.TablePoints.setModel(new DefaultTableModel());
            this.points.clear();
            this.results.clear();
            this.initTableGUI();
            this.createTable();

        } catch (InterruptedException ex) {
            System.out.println("Interrupted Exception");
            System.out.println(ex);
        }
    }

    public void thirdPlaceAuto(String t1, String t2) {
        try {
            String team1 = t1;
            String team2 = t2;
            ArrayList<Team> third_teams = new ArrayList<>();
            for (Team s : this.database) {
                if (s.getName().equals(team1)) {
                    third_teams.add(s);
                    break;
                }
            }

            for (Team s : this.database) {
                if (s.getName().equals(team2)) {
                    third_teams.add(s);
                    break;
                }
            }

            String sport = (String) this.ComboSports.getSelectedItem();

            switch (sport) {
                case "MenFootball":
                    this.ol = new FrameOlympic("MenFootball", third_teams.get(0).getName(), third_teams.get(1).getName(), true);
                    this.ol.autoExecution();
                    break;
                case "WomenFootball":
                    this.ol = new FrameOlympic("WomenFootball", third_teams.get(0).getName(), third_teams.get(1).getName(), true);
                    this.ol.autoExecution();
                    break;
                case "MenFutsal":
                    this.ol = new FrameOlympic("MenFutsal", third_teams.get(0).getName(), third_teams.get(1).getName(), true);
                    this.ol.autoExecution();
                    break;
                case "Volleyball":
                    this.ol = new FrameOlympic("Volleyball", third_teams.get(0).getName(), third_teams.get(1).getName(), true);
                    this.ol.autoExecution();
                    break;
                case "Tennis":
                    this.ol = new FrameOlympic("Tennis", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "TableTennis":
                    this.ol = new FrameOlympic("TableTennis", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "Baseball":
                    this.ol = new FrameOlympic("Baseball", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "Basketball":
                    this.ol = new FrameOlympic("Basketball", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "Rugby":
                    this.ol = new FrameOlympic("Rugby", third_teams.get(0).getName(), third_teams.get(1).getName(), true);
                    this.ol.autoExecution();
                    break;
                case "Handball":
                    this.ol = new FrameOlympic("Handball", third_teams.get(0).getName(), third_teams.get(1).getName(), true);
                    this.ol.autoExecution();
                    break;
                case "Squash":
                    this.ol = new FrameOlympic("Squash", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "Hockey":
                    this.ol = new FrameOlympic("Hockey", third_teams.get(0).getName(), third_teams.get(1).getName(), true);
                    this.ol.autoExecution();
                    break;
                case "Archery":
                    this.ol = new FrameOlympic("Archery", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "Fencing":
                    this.ol = new FrameOlympic("Fencing", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "Curling":
                    this.ol = new FrameOlympic("Curling", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "Badminton":
                    this.ol = new FrameOlympic("Badminton", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "BeachVolley":
                    this.ol = new FrameOlympic("BeachVolley", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
                case "Waterpolo":
                    this.ol = new FrameOlympic("Waterpolo", third_teams.get(0).getName(), third_teams.get(1).getName());
                    this.ol.autoExecution();
                    break;
            }

            String goals[] = this.ol.goals;
            String penalties[] = this.ol.penalties;
            if (penalties[0] == null) {
                penalties[0] = "" + 0;
            }

            if (penalties[1] == null) {
                penalties[1] = "" + 0;
            }
            this.LblThirdResult.setText(third_teams.get(0).getName() + " " + goals[0] + "(" + penalties[0] + ")" + "-" + third_teams.get(1).getName() + " " + goals[1] + "(" + penalties[1] + ")");
            String[] results = new String[4];

            results[0] = third_teams.get(0).getName();
            results[0] = results[0].replaceAll("\\s", "|");
            results[1] = goals[0];
            results[2] = third_teams.get(1).getName();
            results[2] = results[2].replaceAll("\\s", "|");
            results[3] = goals[1];
            this.putResults(results);
            this.tableteam.clear();
            this.TablePoints.setModel(new DefaultTableModel());
            this.points.clear();
            this.results.clear();
            this.initTableGUI();
            this.createTable();

        } catch (InterruptedException ex) {
            System.out.println("Interrupted Exception");
            System.out.println(ex);
        }
    }

    public void playMatches() {
        this.teams.clear();
        this.BtnGenerateTree.setEnabled(true);
        this.BtnPlayMatches.setEnabled(false);
        ListModel<String> actList = this.ListSelectedTeams.getModel();
        ArrayList<String> auxTeams = new ArrayList<>();
        String sport = (String) this.ComboSports.getSelectedItem();
        int numTeams = Integer.parseInt(this.ComboNumberTeams.getSelectedItem().toString());

        if (!(actList.getSize() < numTeams)) {
            for (int r = 0; r < actList.getSize(); r++) {
                auxTeams.add(actList.getElementAt(r));
            }

            for (int q = 0; q < auxTeams.size(); q++) {
                teams.add(auxTeams.get(q));
            }
            for (Team s : this.database) {
                for (String k : this.teams) {
                    if (s.getName().equals(k)) {
                        this.selected_teams.add(s);
                    }
                }
            }

            Collections.shuffle(this.selected_teams);
            int counter = 0;
            Team winner = null;
            switch (numTeams) {
                case 16:
                    tree.generateTree(numTeams);
                    ArrayList<Team> currTeams16 = new ArrayList<>();
                    for (Team st : selected_teams) {
                        tree.changeNode(tree.getRoot(), st);
                    }
                    tree.print2D(tree.getRoot());
                    while (counter < 4) {
                        for (Team t : selected_teams) {
                            System.out.print(t.getName() + " ");
                        }
                        System.out.println("");
                        currTeams16.clear();
                        for (int k = 0; k < selected_teams.size(); k += 2) {
                            System.out.println("Fixture TRN: " + this.selected_teams.get(k).getName() + "-" + this.selected_teams.get(k + 1).getName());
                            try {
                                switch (sport) {
                                    case "MenFootball":
                                        this.ol = new FrameOlympic("MenFootball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "WomenFootball":
                                        this.ol = new FrameOlympic("WomenFootball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "MenFutsal":
                                        this.ol = new FrameOlympic("MenFutsal", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "WomenFutsal":
                                        this.ol = new FrameOlympic("WomenFootball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Volleyball":
                                        this.ol = new FrameOlympic("Volleyball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Tennis":
                                        this.ol = new FrameOlympic("Tennis", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "TableTennis":
                                        this.ol = new FrameOlympic("TableTennis", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Baseball":
                                        this.ol = new FrameOlympic("Baseball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Basketball":
                                        this.ol = new FrameOlympic("Basketball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Rugby":
                                        this.ol = new FrameOlympic("Rugby", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Handball":
                                        this.ol = new FrameOlympic("Handball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Squash":
                                        this.ol = new FrameOlympic("Squash", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Hockey":
                                        this.ol = new FrameOlympic("Hockey", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Archery":
                                        this.ol = new FrameOlympic("Archery", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Fencing":
                                        this.ol = new FrameOlympic("Fencing", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Curling":
                                        this.ol = new FrameOlympic("Curling", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Badminton":
                                        this.ol = new FrameOlympic("Badminton", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "BeachVolley":
                                        this.ol = new FrameOlympic("BeachVolley", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Waterpolo":
                                        this.ol = new FrameOlympic("Waterpolo", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                }

                                String[] goals = this.ol.goals;
                                String[] penalties = this.ol.penalties;

                                if (penalties[0] == null) {
                                    penalties[0] = "" + 0;
                                }
                                if (penalties[1] == null) {
                                    penalties[1] = "" + 0;
                                }

                                System.out.println(Arrays.toString(goals));
                                System.out.println(Arrays.toString(penalties));

                                String[] results = new String[6];
                                results[0] = this.selected_teams.get(k).getName();
                                results[0] = results[0].replaceAll("\\s", "|");
                                results[1] = goals[0];
                                results[2] = this.selected_teams.get(k + 1).getName();
                                results[2] = results[2].replaceAll("\\s", "|");
                                results[3] = goals[1];
                                this.putResults(results);

                                if (Integer.parseInt(penalties[0]) > Integer.parseInt(penalties[1])) {
                                    int auxGoals = Integer.parseInt(goals[0]) + 1;
                                    goals[0] += auxGoals;
                                }
                                if (Integer.parseInt(penalties[1]) > Integer.parseInt(penalties[0])) {
                                    int auxGoals = Integer.parseInt(goals[1]) + 1;
                                    goals[1] += auxGoals;
                                }

                                if (Integer.parseInt(goals[0]) > Integer.parseInt(goals[1])) {
                                    winner = this.selected_teams.get(k);
                                    if (counter == 2) {
                                        this.thirdTeam1 = this.selected_teams.get(k + 1).getName();
                                    }
                                }
                                if (Integer.parseInt(goals[1]) > Integer.parseInt(goals[0])) {
                                    winner = this.selected_teams.get(k + 1);
                                    if (counter == 2) {
                                        this.thirdTeam2 = this.selected_teams.get(k).getName();
                                    }
                                }

                                currTeams16.add(winner);
                                tree.changeFilledNode(tree.getRoot(), this.selected_teams.get(k), this.selected_teams.get(k + 1), winner);

                            } catch (InterruptedException | IndexOutOfBoundsException ex) {
                                System.out.println("Interrupted Exception");
                            }

                        }

                        this.selected_teams.clear();
                        for (Team curr : currTeams16) {
                            this.selected_teams.add(curr);
                        }
                        counter++;
                    }
                    tree.print2D(tree.getRoot());
                    this.ol.dispose();
                    break;

                case 32:
                    tree.generateTree(numTeams);
                    ArrayList<Team> currTeams32 = new ArrayList<>();
                    for (Team st : selected_teams) {
                        tree.changeNode(tree.getRoot(), st);
                    }
                    tree.print2D(tree.getRoot());
                    while (counter < 5) {
                        for (Team t : selected_teams) {
                            System.out.print(t.getName() + " ");
                        }
                        System.out.println("");
                        currTeams32.clear();
                        for (int k = 0; k < selected_teams.size(); k += 2) {
                            System.out.println("Fixture TRN: " + this.selected_teams.get(k).getName() + "-" + this.selected_teams.get(k + 1).getName());
                            try {
                                switch (sport) {
                                    case "MenFootball":
                                        this.ol = new FrameOlympic("MenFootball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "WomenFootball":
                                        this.ol = new FrameOlympic("WomenFootball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "MenFutsal":
                                        this.ol = new FrameOlympic("MenFutsal", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "WomenFutsal":
                                        this.ol = new FrameOlympic("WomenFutsal", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Volleyball":
                                        this.ol = new FrameOlympic("Volleyball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Tennis":
                                        this.ol = new FrameOlympic("Tennis", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "TableTennis":
                                        this.ol = new FrameOlympic("TableTennis", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Baseball":
                                        this.ol = new FrameOlympic("Baseball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Basketball":
                                        this.ol = new FrameOlympic("Basketball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Rugby":
                                        this.ol = new FrameOlympic("Rugby", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Handball":
                                        this.ol = new FrameOlympic("Handball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Squash":
                                        this.ol = new FrameOlympic("Squash", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Hockey":
                                        this.ol = new FrameOlympic("Hockey", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Archery":
                                        this.ol = new FrameOlympic("Archery", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Fencing":
                                        this.ol = new FrameOlympic("Fencing", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Curling":
                                        this.ol = new FrameOlympic("Curling", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Badminton":
                                        this.ol = new FrameOlympic("Badminton", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "BeachVolley":
                                        this.ol = new FrameOlympic("BeachVolley", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Waterpolo":
                                        this.ol = new FrameOlympic("Waterpolo", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                }

                                String[] goals = this.ol.goals;
                                String[] penalties = this.ol.penalties;

                                if (penalties[0] == null) {
                                    penalties[0] = "" + 0;
                                }
                                if (penalties[1] == null) {
                                    penalties[1] = "" + 0;
                                }
                                System.out.println(Arrays.toString(goals));
                                System.out.println(Arrays.toString(penalties));

                                String[] results = new String[6];
                                results[0] = this.selected_teams.get(k).getName();
                                results[0] = results[0].replaceAll("\\s", "|");
                                results[1] = goals[0];
                                results[2] = this.selected_teams.get(k + 1).getName();
                                results[2] = results[2].replaceAll("\\s", "|");
                                results[3] = goals[1];
                                this.putResults(results);

                                if (Integer.parseInt(penalties[0]) > Integer.parseInt(penalties[1])) {
                                    int auxGoals = Integer.parseInt(goals[0]) + 1;
                                    goals[0] += auxGoals;
                                }
                                if (Integer.parseInt(penalties[1]) > Integer.parseInt(penalties[0])) {
                                    int auxGoals = Integer.parseInt(goals[1]) + 1;
                                    goals[1] += auxGoals;
                                }

                                if (Integer.parseInt(goals[0]) > Integer.parseInt(goals[1])) {
                                    winner = this.selected_teams.get(k);
                                    if (counter == 3) {
                                        this.thirdTeam1 = this.selected_teams.get(k + 1).getName();
                                    }
                                }
                                if (Integer.parseInt(goals[1]) > Integer.parseInt(goals[0])) {
                                    winner = this.selected_teams.get(k + 1);
                                    if (counter == 3) {
                                        this.thirdTeam2 = this.selected_teams.get(k).getName();
                                    }
                                }

                                currTeams32.add(winner);
                                tree.changeFilledNode(tree.getRoot(), this.selected_teams.get(k), this.selected_teams.get(k + 1), winner);

                            } catch (InterruptedException ex) {
                                System.out.println("Interrupted Exception");
                            }

                        }

                        this.selected_teams.clear();
                        for (Team curr : currTeams32) {
                            this.selected_teams.add(curr);
                        }
                        counter++;
                    }
                    tree.print2D(tree.getRoot());
                    this.ol.dispose();
                    break;

                case 8:
                    tree.generateTree(numTeams);
                    ArrayList<Team> currTeams8 = new ArrayList<>();
                    for (Team st : selected_teams) {
                        tree.changeNode(tree.getRoot(), st);
                    }
                    tree.print2D(tree.getRoot());
                    while (counter < 3) {
                        for (Team t : selected_teams) {
                            System.out.print(t.getName() + " ");
                        }
                        System.out.println("");
                        currTeams8.clear();
                        for (int k = 0; k < selected_teams.size(); k += 2) {
                            System.out.println("Fixture TRN: " + this.selected_teams.get(k).getName() + "-" + this.selected_teams.get(k + 1).getName());
                            try {
                                switch (sport) {
                                    case "MenFootball":
                                        this.ol = new FrameOlympic("MenFootball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "WomenFootball":
                                        this.ol = new FrameOlympic("WomenFootball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "MenFutsal":
                                        this.ol = new FrameOlympic("MenFutsal", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "WomenFutsal":
                                        this.ol = new FrameOlympic("WomenFutsal", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Volleyball":
                                        this.ol = new FrameOlympic("Volleyball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Tennis":
                                        this.ol = new FrameOlympic("Tennis", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "TableTennis":
                                        this.ol = new FrameOlympic("TableTennis", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Baseball":
                                        this.ol = new FrameOlympic("Baseball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Basketball":
                                        this.ol = new FrameOlympic("Basketball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Rugby":
                                        this.ol = new FrameOlympic("Rugby", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Handball":
                                        this.ol = new FrameOlympic("Handball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Squash":
                                        this.ol = new FrameOlympic("Squash", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Hockey":
                                        this.ol = new FrameOlympic("Hockey", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Archery":
                                        this.ol = new FrameOlympic("Archery", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Fencing":
                                        this.ol = new FrameOlympic("Fencing", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Curling":
                                        this.ol = new FrameOlympic("Curling", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Badminton":
                                        this.ol = new FrameOlympic("Badminton", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "BeachVolley":
                                        this.ol = new FrameOlympic("BeachVolley", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Waterpolo":
                                        this.ol = new FrameOlympic("Waterpolo", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                }

                                String[] goals = this.ol.goals;
                                String[] penalties = this.ol.penalties;

                                if (penalties[0] == null) {
                                    penalties[0] = "" + 0;
                                }
                                if (penalties[1] == null) {
                                    penalties[1] = "" + 0;
                                }

                                System.out.println(Arrays.toString(goals));
                                System.out.println(Arrays.toString(penalties));

                                String[] results = new String[6];
                                results[0] = this.selected_teams.get(k).getName();
                                results[0] = results[0].replaceAll("\\s", "|");
                                results[1] = goals[0];
                                results[2] = this.selected_teams.get(k + 1).getName();
                                results[2] = results[2].replaceAll("\\s", "|");
                                results[3] = goals[1];
                                this.putResults(results);

                                if (Integer.parseInt(penalties[0]) > Integer.parseInt(penalties[1])) {
                                    int auxGoals = Integer.parseInt(goals[0]) + 1;
                                    goals[0] += auxGoals;
                                }
                                if (Integer.parseInt(penalties[1]) > Integer.parseInt(penalties[0])) {
                                    int auxGoals = Integer.parseInt(goals[1]) + 1;
                                    goals[1] += auxGoals;
                                }

                                if (Integer.parseInt(goals[0]) > Integer.parseInt(goals[1])) {
                                    winner = this.selected_teams.get(k);
                                    if (counter == 1) {
                                        this.thirdTeam1 = this.selected_teams.get(k + 1).getName();
                                    }
                                }
                                if (Integer.parseInt(goals[1]) > Integer.parseInt(goals[0])) {
                                    winner = this.selected_teams.get(k + 1);
                                    if (counter == 1) {
                                        this.thirdTeam2 = this.selected_teams.get(k).getName();
                                    }
                                }

                                tree.changeFilledNode(tree.getRoot(), this.selected_teams.get(k), this.selected_teams.get(k + 1), winner);
                                currTeams8.add(winner);

                            } catch (InterruptedException ex) {
                                System.out.println("Interrupted Exception");
                            }

                        }

                        this.selected_teams.clear();
                        for (Team curr : currTeams8) {
                            this.selected_teams.add(curr);
                        }

                        tree.print2D(tree.getRoot());
                        counter++;

                    }
                    this.ol.dispose();
                    break;

                case 4:
                    tree.generateTree(numTeams);
                    ArrayList<Team> currTeams4 = new ArrayList<>();
                    for (Team st : selected_teams) {
                        tree.changeNode(tree.getRoot(), st);
                    }
                    tree.print2D(tree.getRoot());
                    while (counter < 2) {
                        currTeams4.clear();
                        for (Team t : selected_teams) {
                            System.out.print(t.getName() + " ");
                        }
                        System.out.println("");
                        for (int k = 0; k < selected_teams.size(); k += 2) {
                            try {
                                System.out.println("Fixture TRN: " + this.selected_teams.get(k).getName() + "-" + this.selected_teams.get(k + 1).getName());
                                switch (sport) {
                                    case "MenFootball":
                                        this.ol = new FrameOlympic("MenFootball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "WomenFootball":
                                        this.ol = new FrameOlympic("WomenFootball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "MenFutsal":
                                        this.ol = new FrameOlympic("MenFutsal", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "WomenFutsal":
                                        this.ol = new FrameOlympic("WomenFutsal", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Volleyball":
                                        this.ol = new FrameOlympic("Volleyball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Tennis":
                                        this.ol = new FrameOlympic("Tennis", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "TableTennis":
                                        this.ol = new FrameOlympic("TableTennis", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Baseball":
                                        this.ol = new FrameOlympic("Baseball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Basketball":
                                        this.ol = new FrameOlympic("Basketball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Rugby":
                                        this.ol = new FrameOlympic("Rugby", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Handball":
                                        this.ol = new FrameOlympic("Handball", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Squash":
                                        this.ol = new FrameOlympic("Squash", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Hockey":
                                        this.ol = new FrameOlympic("Hockey", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName(), true);
                                        this.ol.autoExecution();
                                        break;
                                    case "Archery":
                                        this.ol = new FrameOlympic("Archery", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Fencing":
                                        this.ol = new FrameOlympic("Fencing", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Curling":
                                        this.ol = new FrameOlympic("Curling", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Badminton":
                                        this.ol = new FrameOlympic("Badminton", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "BeachVolley":
                                        this.ol = new FrameOlympic("BeachVolley", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                    case "Waterpolo":
                                        this.ol = new FrameOlympic("Waterpolo", this.selected_teams.get(k).getName(), this.selected_teams.get(k + 1).getName());
                                        this.ol.autoExecution();
                                        break;
                                }

                                String[] goals = this.ol.goals;
                                String[] penalties = this.ol.penalties;

                                if (penalties[0] == null) {
                                    penalties[0] = "" + 0;
                                }
                                if (penalties[1] == null) {
                                    penalties[1] = "" + 0;
                                }

                                System.out.println(Arrays.toString(goals));
                                System.out.println(Arrays.toString(penalties));

                                String[] results = new String[6];
                                results[0] = this.selected_teams.get(k).getName();
                                results[0] = results[0].replaceAll("\\s", "|");
                                results[1] = goals[0];
                                results[2] = this.selected_teams.get(k + 1).getName();
                                results[2] = results[2].replaceAll("\\s", "|");
                                results[3] = goals[1];
                                this.putResults(results);

                                if (Integer.parseInt(penalties[0]) > Integer.parseInt(penalties[1])) {
                                    int auxGoals = Integer.parseInt(goals[0]) + 1;
                                    goals[0] += auxGoals;
                                }
                                if (Integer.parseInt(penalties[1]) > Integer.parseInt(penalties[0])) {
                                    int auxGoals = Integer.parseInt(goals[1]) + 1;
                                    goals[1] += auxGoals;
                                }

                                if (Integer.parseInt(goals[0]) > Integer.parseInt(goals[1])) {
                                    winner = this.selected_teams.get(k);
                                    if (counter == 0) {
                                        this.thirdTeam1 = this.selected_teams.get(k + 1).getName();
                                    }
                                }
                                if (Integer.parseInt(goals[1]) > Integer.parseInt(goals[0])) {
                                    winner = this.selected_teams.get(k + 1);
                                    if (counter == 0) {
                                        this.thirdTeam2 = this.selected_teams.get(k).getName();
                                    }
                                }

                                currTeams4.add(winner);
                                tree.changeFilledNode(tree.getRoot(), this.selected_teams.get(k), this.selected_teams.get(k + 1), winner);

                            } catch (InterruptedException ex) {
                                System.out.println("Interrupted Exception");
                            }

                        }

                        this.selected_teams.clear();
                        for (Team curr : currTeams4) {
                            this.selected_teams.add(curr);
                        }
                        counter++;
                    }
                    tree.print2D(tree.getRoot());
                    this.ol.dispose();
                    break;
            }
            this.createTable();
        } else {
            JOptionPane.showMessageDialog(null, "Not enough teams added.", "Warning", 1);
        }
    }

    public void resetMatches() {
        this.ListSelectedTeams.setModel(new DefaultListModel());
        this.LblTree.repaint();
        this.YINI = 15;
        this.points.clear();
        this.tree = new TournamentTree();
        this.tableteam.clear();
        this.TableResults.setModel(new DefaultTableModel());
        this.initTableGUI();
        this.initTableResults();
        this.results.clear();
        this.teams.clear();
        this.selected_teams.clear();
        this.modlist.clear();
        this.BtnGenerateTree.setEnabled(false);
        this.BtnPlayMatches.setEnabled(true);
        this.LblThirdResult.setText("3rd Place result...");
        this.BtnThirdPlace.setEnabled(false);
    }

    public void createTable() {

        DefaultTableModel tm = (DefaultTableModel) this.TableResults.getModel();
        for (int i = 0; i < this.TableResults.getRowCount(); i++) {
            Vector<Object> rowData = tm.getDataVector().elementAt(i);
            this.results.add(rowData);
        }
        String areaValues = "";

        for (Vector v : this.results) {
            for (int i = 0; i < v.size(); i++) {
                if (i == v.size() - 1) {
                    areaValues += v.elementAt(i);
                } else {
                    areaValues += v.elementAt(i) + " ";
                }

            }
            areaValues += "\n";
        }

        String[] results = areaValues.split("\n");
        System.out.println(Arrays.toString(results));
        ArrayList<String> splitted_res = new ArrayList<>();
        for (String r : results) {
            r = r.replaceAll("\\s+", ",");
            // System.out.println(r);
            splitted_res.add(r);

        }

        String[] div = null;

        Object[] spl_teams = this.modlist.toArray();
        for (Object q : spl_teams) {
            String qu = q.toString();
            qu = qu.replaceAll("\\s", "|");
            TableTeam tbl = new TableTeam(qu);
            this.tableteam.add(tbl);
        }

        for (TableTeam t : this.tableteam) {

            for (String k : splitted_res) {
                k = k.trim();
                div = k.split(",");

                if (div[0].equals(t.getName())) {

                    if (Integer.parseInt(div[1]) > Integer.parseInt(div[3])) {
                        t.setVic(t.getVic() + 1);
                    }
                    if (Integer.parseInt(div[1]) < Integer.parseInt(div[3])) {
                        t.setDer(t.getDer() + 1);
                    }
                    if (Integer.parseInt(div[1]) == Integer.parseInt(div[3])) {
                        t.setEmp(t.getEmp() + 1);
                    }

                    t.setGf(t.getGf() + Integer.parseInt(div[1]));
                    t.setGc(t.getGc() + Integer.parseInt(div[3]));
                }

                if (div[2].equals(t.getName())) {

                    if (Integer.parseInt(div[3]) > Integer.parseInt(div[1])) {
                        t.setVic(t.getVic() + 1);
                    }
                    if (Integer.parseInt(div[3]) < Integer.parseInt(div[1])) {
                        t.setDer(t.getDer() + 1);
                    }
                    if (Integer.parseInt(div[3]) == Integer.parseInt(div[1])) {
                        t.setEmp(t.getEmp() + 1);
                    }

                    t.setGf(t.getGf() + Integer.parseInt(div[3]));
                    t.setGc(t.getGc() + Integer.parseInt(div[1]));
                }

            }

            t.setDif(t.getGf() - t.getGc());
            t.setPts((t.getVic() * 3) + t.getEmp());
        }

        for (TableTeam e : this.tableteam) {
            //e.print();
        }
        this.showTableGUI();
    }

    public void initTableResults() {

        tblres = new DefaultTableModel();

        tblres.addColumn("Local");
        tblres.addColumn("GL");
        tblres.addColumn("GA");
        tblres.addColumn("Away");

        this.TableResults.setModel(tblres);
    }

    public void initTableGUI() {

        DefaultTableModel md = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        md.addColumn("Equipo");
        md.addColumn("Pts");
        md.addColumn("V");
        md.addColumn("D");
        md.addColumn("E");
        md.addColumn("GF");
        md.addColumn("GC");
        md.addColumn("DIF");

        this.TablePoints.setModel(md);

    }

    public void putResults(String[] results) {
        tblres.addRow(results);
        this.TableResults.setModel(tblres);
    }

    public void showTableGUI() {

        DefaultTableModel md = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0, columnIndex).getClass();
            }
        };

        md.addColumn("Equipo");
        md.addColumn("Pts");
        md.addColumn("V");
        md.addColumn("D");
        md.addColumn("E");
        md.addColumn("GF");
        md.addColumn("GC");
        md.addColumn("DIF");

        for (TableTeam m : this.tableteam) {
            Object[] row = new Object[8];
            row[0] = m.getName().replaceAll("\\|", " ");
            row[1] = m.getPts();
            row[2] = m.getVic();
            row[3] = m.getDer();
            row[4] = m.getEmp();
            row[5] = m.getGf();
            row[6] = m.getGc();
            row[7] = m.getDif();
            md.addRow(row);
        }

        this.TablePoints.setModel(md);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.TablePoints.setDefaultRenderer(Integer.class, centerRenderer);

        this.TablePoints.setModel(md);
        this.TablePoints.setAutoCreateRowSorter(true);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(this.TablePoints.getModel());
        this.TablePoints.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();

        int ptsIndex = 1;
        sortKeys.add(new RowSorter.SortKey(ptsIndex, SortOrder.DESCENDING));

        int difIndex = 7;
        sortKeys.add(new RowSorter.SortKey(difIndex, SortOrder.DESCENDING));

        int goalsIndex = 5;
        sortKeys.add(new RowSorter.SortKey(goalsIndex, SortOrder.DESCENDING));

        sorter.setSortKeys(sortKeys);
        sorter.sort();

    }

    private int radius = 20; // Tree node radius
    private int vGap = 50; // Gap between two levels in a tree

    protected void paintComponent(Graphics g) {
        g = this.LblTree.getGraphics();

        if (tree.getRoot() != null) {
            // Display tree recursively    
            displayTree(g, tree.getRoot(), getWidth() / 2, 30,
                    getWidth() / 4);
        }
    }

    /**
     * Display a subtree rooted at position (x, y)
     */
    private void displayTree(Graphics g, TournamentNode root,
            int x, int y, int hGap) {
        // Display the root
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        g.setColor(Color.blue);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
        g.setColor(Color.white);
        g.drawString(root.getElement().getName() + "", x - 6, y + 4);

        if (root.getLeft_node() != null) {
            // Draw a line to the left node
            connectLeftChild(g, x - hGap, y + vGap, x, y);
            // Draw the left subtree recursively
            displayTree(g, root.getLeft_node(), x - hGap, y + vGap, hGap / 2);
        }

        if (root.getRight_node() != null) {
            // Draw a line to the right node
            connectRightChild(g, x + hGap, y + vGap, x, y);
            // Draw the right subtree recursively
            displayTree(g, root.getRight_node(), x + hGap, y + vGap, hGap / 2);
        }
    }

    /**
     * Connect a parent at (x2, y2) with its left child at (x1, y1)
     */
    private void connectLeftChild(Graphics g,
            int x1, int y1, int x2, int y2) {
        g.setColor(Color.white);
        double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
        int x11 = (int) (x1 + radius * (x2 - x1) / d);
        int y11 = (int) (y1 - radius * vGap / d);
        int x21 = (int) (x2 - radius * (x2 - x1) / d);
        int y21 = (int) (y2 + radius * vGap / d);
        g.drawLine(x11, y11, x21, y21);
    }

    /**
     * Connect a parent at (x2, y2) with its right child at (x1, y1)
     */
    private void connectRightChild(Graphics g,
            int x1, int y1, int x2, int y2) {
        g.setColor(Color.white);
        double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
        int x11 = (int) (x1 - radius * (x1 - x2) / d);
        int y11 = (int) (y1 - radius * vGap / d);
        int x21 = (int) (x2 + radius * (x1 - x2) / d);
        int y21 = (int) (y2 + radius * vGap / d);
        g.drawLine(x11, y11, x21, y21);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelOptions = new javax.swing.JPanel();
        PanelListActions = new javax.swing.JPanel();
        BtnAddTeam = new javax.swing.JButton();
        BtnRemoveTeam = new javax.swing.JButton();
        BtnChangeTeam = new javax.swing.JButton();
        ComboTeams = new javax.swing.JComboBox<>();
        LblTeam = new javax.swing.JLabel();
        BtnRandom = new javax.swing.JButton();
        PanelMainActions = new javax.swing.JPanel();
        BtnPlayMatches = new javax.swing.JButton();
        BtnGenerateTree = new javax.swing.JButton();
        BtnReset = new javax.swing.JButton();
        ComboNumberTeams = new javax.swing.JComboBox<>();
        LblNumberTeams = new javax.swing.JLabel();
        PanelTeams = new javax.swing.JPanel();
        LblSport = new javax.swing.JLabel();
        ComboSports = new javax.swing.JComboBox<>();
        LblSelectedTeams = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListSelectedTeams = new javax.swing.JList<>();
        PanelTree = new javax.swing.JPanel();
        LblTree = new javax.swing.JLabel();
        BtnBack = new javax.swing.JButton();
        BtnThirdPlace = new javax.swing.JButton();
        LblThirdResult = new javax.swing.JLabel();
        PanelResults = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableResults = new javax.swing.JTable();
        PanelTable = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablePoints = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelOptions.setBackground(new java.awt.Color(0, 51, 51));
        PanelOptions.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "List Actions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        PanelListActions.setBackground(new java.awt.Color(0, 51, 51));
        PanelListActions.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "List Actions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        BtnAddTeam.setBackground(new java.awt.Color(0, 125, 125));
        BtnAddTeam.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnAddTeam.setForeground(new java.awt.Color(255, 255, 255));
        BtnAddTeam.setText("Add");
        BtnAddTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddTeamActionPerformed(evt);
            }
        });

        BtnRemoveTeam.setBackground(new java.awt.Color(0, 125, 125));
        BtnRemoveTeam.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnRemoveTeam.setForeground(new java.awt.Color(255, 255, 255));
        BtnRemoveTeam.setText("Remove");
        BtnRemoveTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRemoveTeamActionPerformed(evt);
            }
        });

        BtnChangeTeam.setBackground(new java.awt.Color(0, 125, 125));
        BtnChangeTeam.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnChangeTeam.setForeground(new java.awt.Color(255, 255, 255));
        BtnChangeTeam.setText("Change");
        BtnChangeTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnChangeTeamActionPerformed(evt);
            }
        });

        LblTeam.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblTeam.setForeground(new java.awt.Color(255, 255, 255));
        LblTeam.setText("Team");

        BtnRandom.setBackground(new java.awt.Color(0, 125, 125));
        BtnRandom.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnRandom.setForeground(new java.awt.Color(255, 255, 255));
        BtnRandom.setText("Random Teams");
        BtnRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRandomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelListActionsLayout = new javax.swing.GroupLayout(PanelListActions);
        PanelListActions.setLayout(PanelListActionsLayout);
        PanelListActionsLayout.setHorizontalGroup(
            PanelListActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelListActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelListActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnRandom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnChangeTeam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnRemoveTeam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(BtnAddTeam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboTeams, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblTeam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelListActionsLayout.setVerticalGroup(
            PanelListActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelListActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblTeam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboTeams, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnAddTeam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnRemoveTeam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnChangeTeam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(BtnRandom))
        );

        PanelMainActions.setBackground(new java.awt.Color(0, 51, 51));
        PanelMainActions.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "List Actions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        BtnPlayMatches.setBackground(new java.awt.Color(0, 125, 125));
        BtnPlayMatches.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnPlayMatches.setForeground(new java.awt.Color(255, 255, 255));
        BtnPlayMatches.setText("Play Matches");
        BtnPlayMatches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPlayMatchesActionPerformed(evt);
            }
        });

        BtnGenerateTree.setBackground(new java.awt.Color(0, 125, 125));
        BtnGenerateTree.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnGenerateTree.setForeground(new java.awt.Color(255, 255, 255));
        BtnGenerateTree.setText("Generate Tree");
        BtnGenerateTree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGenerateTreeActionPerformed(evt);
            }
        });

        BtnReset.setBackground(new java.awt.Color(0, 125, 125));
        BtnReset.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnReset.setForeground(new java.awt.Color(255, 255, 255));
        BtnReset.setText("Reset");
        BtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResetActionPerformed(evt);
            }
        });

        ComboNumberTeams.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "4", "8", "16", "32" }));

        LblNumberTeams.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblNumberTeams.setForeground(new java.awt.Color(255, 255, 255));
        LblNumberTeams.setText("Number of Teams");

        javax.swing.GroupLayout PanelMainActionsLayout = new javax.swing.GroupLayout(PanelMainActions);
        PanelMainActions.setLayout(PanelMainActionsLayout);
        PanelMainActionsLayout.setHorizontalGroup(
            PanelMainActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BtnPlayMatches, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnGenerateTree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnReset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelMainActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelMainActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMainActionsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ComboNumberTeams, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LblNumberTeams, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelMainActionsLayout.setVerticalGroup(
            PanelMainActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMainActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnGenerateTree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnPlayMatches)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnReset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblNumberTeams)
                .addGap(4, 4, 4)
                .addComponent(ComboNumberTeams, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelTeams.setBackground(new java.awt.Color(0, 51, 51));
        PanelTeams.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "List Actions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        LblSport.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblSport.setForeground(new java.awt.Color(255, 255, 255));
        LblSport.setText("Sport");

        ComboSports.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select Sport>", "MenFootball", "WomenFootball", "MenFutsal", "WomenFutsal", "Volleyball", "Tennis", "TableTennis", "Baseball", "Basketball", "Rugby", "Handball", "Waterpolo", "Badminton", "Squash", "Hockey", "BeachVolley", "Curling", "Archery", "Fencing" }));
        ComboSports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSportsActionPerformed(evt);
            }
        });

        LblSelectedTeams.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblSelectedTeams.setForeground(new java.awt.Color(255, 255, 255));
        LblSelectedTeams.setText("Selected Teams");

        jScrollPane1.setViewportView(ListSelectedTeams);

        javax.swing.GroupLayout PanelTeamsLayout = new javax.swing.GroupLayout(PanelTeams);
        PanelTeams.setLayout(PanelTeamsLayout);
        PanelTeamsLayout.setHorizontalGroup(
            PanelTeamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTeamsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelTeamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelTeamsLayout.createSequentialGroup()
                        .addComponent(LblSelectedTeams, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(73, 73, 73))
                    .addGroup(PanelTeamsLayout.createSequentialGroup()
                        .addGroup(PanelTeamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ComboSports, 0, 128, Short.MAX_VALUE)
                            .addComponent(LblSport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTeamsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        PanelTeamsLayout.setVerticalGroup(
            PanelTeamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTeamsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblSport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboSports, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblSelectedTeams)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelOptionsLayout = new javax.swing.GroupLayout(PanelOptions);
        PanelOptions.setLayout(PanelOptionsLayout);
        PanelOptionsLayout.setHorizontalGroup(
            PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelTeams, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelListActions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelMainActions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        PanelOptionsLayout.setVerticalGroup(
            PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOptionsLayout.createSequentialGroup()
                .addGroup(PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelListActions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelOptionsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PanelMainActions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(PanelTeams, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        PanelTree.setBackground(new java.awt.Color(0, 51, 51));
        PanelTree.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "List Actions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        LblTree.setBackground(new java.awt.Color(0, 0, 0));
        LblTree.setOpaque(true);

        BtnBack.setBackground(new java.awt.Color(0, 125, 125));
        BtnBack.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnBack.setForeground(new java.awt.Color(255, 255, 255));
        BtnBack.setText("Back");
        BtnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBackActionPerformed(evt);
            }
        });

        BtnThirdPlace.setBackground(new java.awt.Color(0, 125, 125));
        BtnThirdPlace.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnThirdPlace.setForeground(new java.awt.Color(255, 255, 255));
        BtnThirdPlace.setText("3rd Place Match");
        BtnThirdPlace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnThirdPlaceActionPerformed(evt);
            }
        });

        LblThirdResult.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblThirdResult.setForeground(new java.awt.Color(255, 255, 255));
        LblThirdResult.setText("3rd Place result...");

        javax.swing.GroupLayout PanelTreeLayout = new javax.swing.GroupLayout(PanelTree);
        PanelTree.setLayout(PanelTreeLayout);
        PanelTreeLayout.setHorizontalGroup(
            PanelTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTreeLayout.createSequentialGroup()
                .addGroup(PanelTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelTreeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LblTree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelTreeLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(BtnThirdPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblThirdResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelTreeLayout.setVerticalGroup(
            PanelTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTreeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblTree, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnBack)
                    .addComponent(BtnThirdPlace)
                    .addComponent(LblThirdResult, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        PanelResults.setBackground(new java.awt.Color(0, 51, 51));
        PanelResults.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "List Actions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        TableResults.setBackground(new java.awt.Color(0, 153, 153));
        TableResults.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        TableResults.setForeground(new java.awt.Color(255, 255, 255));
        TableResults.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableResults.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableResults.setColumnSelectionAllowed(true);
        jScrollPane5.setViewportView(TableResults);

        javax.swing.GroupLayout PanelResultsLayout = new javax.swing.GroupLayout(PanelResults);
        PanelResults.setLayout(PanelResultsLayout);
        PanelResultsLayout.setHorizontalGroup(
            PanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelResultsLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelResultsLayout.setVerticalGroup(
            PanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelResultsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelTable.setBackground(new java.awt.Color(0, 51, 51));
        PanelTable.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "List Actions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        TablePoints.setBackground(new java.awt.Color(0, 153, 153));
        TablePoints.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        TablePoints.setForeground(new java.awt.Color(255, 255, 255));
        TablePoints.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TablePoints.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TablePoints.setColumnSelectionAllowed(true);
        jScrollPane3.setViewportView(TablePoints);

        javax.swing.GroupLayout PanelTableLayout = new javax.swing.GroupLayout(PanelTable);
        PanelTable.setLayout(PanelTableLayout);
        PanelTableLayout.setHorizontalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 381, Short.MAX_VALUE)
            .addGroup(PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelTableLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        PanelTableLayout.setVerticalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelTableLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelResults, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addComponent(PanelTree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboSportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSportsActionPerformed
        String value = (String) this.ComboSports.getSelectedItem();

        this.ComboTeams.removeAllItems();

        if (value.equals("MenFootball") || value.equals("MenFutsal")) {
            MenFootballInterface foot = new MenFootballInterface();
            this.database = foot.getDatabaseValues();
            for (Team t : this.database) {
                this.ComboTeams.addItem(t.getName());
            }
        } else {
            if (value.equals("WomenFootball") || value.equals("WomenFutsal")) {

                WomenFootballInterface foot = new WomenFootballInterface();
                this.database = foot.getDatabaseValues();
                for (Team t : this.database) {
                    this.ComboTeams.addItem(t.getName());
                }

            } else {
                BasketballInterface bask = new BasketballInterface();
                this.database = bask.getDatabaseValues();
                for (Team t : this.database) {
                    this.ComboTeams.addItem(t.getName());
                }

            }
        }
    }//GEN-LAST:event_ComboSportsActionPerformed

    private void BtnAddTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddTeamActionPerformed
        this.addTeam();
    }//GEN-LAST:event_BtnAddTeamActionPerformed

    private void BtnRemoveTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRemoveTeamActionPerformed
        this.removeTeam();
    }//GEN-LAST:event_BtnRemoveTeamActionPerformed

    private void BtnChangeTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnChangeTeamActionPerformed
        this.changeTeam();
    }//GEN-LAST:event_BtnChangeTeamActionPerformed

    private void BtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResetActionPerformed
        this.resetMatches();
    }//GEN-LAST:event_BtnResetActionPerformed

    private void BtnGenerateTreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerateTreeActionPerformed
        this.generateTree();
    }//GEN-LAST:event_BtnGenerateTreeActionPerformed

    private void BtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBackActionPerformed
        FrameInicio b = new FrameInicio();
        b.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnBackActionPerformed

    private void BtnPlayMatchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPlayMatchesActionPerformed
        this.playMatches();
    }//GEN-LAST:event_BtnPlayMatchesActionPerformed

    private void BtnThirdPlaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnThirdPlaceActionPerformed

    }//GEN-LAST:event_BtnThirdPlaceActionPerformed

    private void BtnRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRandomActionPerformed
        String value = (String) this.ComboSports.getSelectedItem();
        int numTeams = Integer.parseInt(this.ComboNumberTeams.getSelectedItem().toString());
        this.modlist.clear();
        if (value.equals("MenFootball") || value.equals("MenFutsal")) {
            MenFootballInterface foot = new MenFootballInterface();
            ArrayList<Team> tempTeams = foot.getDatabaseValues();
            while (this.modlist.getSize() < numTeams) {
                int rand = (int) Math.floor(Math.random() * tempTeams.size());
                this.modlist.addElement(tempTeams.get(rand).getName());
                tempTeams.remove(rand);
                this.ListSelectedTeams.setModel(modlist);
            }

        } else {
            if (value.equals("WomenFootball") || value.equals("WomenFutsal")) {

                WomenFootballInterface foot = new WomenFootballInterface();
                ArrayList<Team> tempTeams = foot.getDatabaseValues();
                while (this.modlist.getSize() < numTeams) {
                    int rand = (int) Math.floor(Math.random() * tempTeams.size());
                    this.modlist.addElement(tempTeams.get(rand).getName());
                    tempTeams.remove(rand);
                    this.ListSelectedTeams.setModel(modlist);
                }

            } else {
                BasketballInterface bask = new BasketballInterface();
                ArrayList<Team> tempTeams = bask.getDatabaseValues();
                while (this.modlist.getSize() < numTeams) {
                    int rand = (int) Math.floor(Math.random() * tempTeams.size());
                    this.modlist.addElement(tempTeams.get(rand).getName());
                    tempTeams.remove(rand);
                    this.ListSelectedTeams.setModel(modlist);
                }

            }
        }
    }//GEN-LAST:event_BtnRandomActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameEliminationSports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameEliminationSports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameEliminationSports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameEliminationSports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameEliminationSports().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAddTeam;
    private javax.swing.JButton BtnBack;
    private javax.swing.JButton BtnChangeTeam;
    private javax.swing.JButton BtnGenerateTree;
    private javax.swing.JButton BtnPlayMatches;
    private javax.swing.JButton BtnRandom;
    private javax.swing.JButton BtnRemoveTeam;
    private javax.swing.JButton BtnReset;
    private javax.swing.JButton BtnThirdPlace;
    private javax.swing.JComboBox<String> ComboNumberTeams;
    private javax.swing.JComboBox<String> ComboSports;
    private javax.swing.JComboBox<String> ComboTeams;
    private javax.swing.JLabel LblNumberTeams;
    private javax.swing.JLabel LblSelectedTeams;
    private javax.swing.JLabel LblSport;
    private javax.swing.JLabel LblTeam;
    private javax.swing.JLabel LblThirdResult;
    private javax.swing.JLabel LblTree;
    private javax.swing.JList<String> ListSelectedTeams;
    private javax.swing.JPanel PanelListActions;
    private javax.swing.JPanel PanelMainActions;
    private javax.swing.JPanel PanelOptions;
    private javax.swing.JPanel PanelResults;
    private javax.swing.JPanel PanelTable;
    private javax.swing.JPanel PanelTeams;
    private javax.swing.JPanel PanelTree;
    private javax.swing.JTable TablePoints;
    private javax.swing.JTable TableResults;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables
}
