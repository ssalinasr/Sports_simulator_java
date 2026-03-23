/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package OtherGames;

import Interfaces.Games.*;
import OlympicGames.*;
import Main.FrameInicio;
import Modules.Team;
import Tools.Database.DBConsultas;
import Tools.Flagsorter.flagSorter;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Salinas
 */
public class FrameGames extends javax.swing.JFrame {

    public GoldenEyeLicensetokillInterface gelk;
    public GoldenEyeLivesInterface gelv;
    public GoldenEyeSSDVInterface gesv;
    public GoldenEyeTeamsInterface getm;
    public GoldenEyeTimeInterface geti;
    public MarioKartBattlesInterface mkbt;
    public SuperSmashCoinsInterface ssbc;
    public SuperSmashLivesInterface ssbl;
    public SuperSmashStaminaInterface ssbs;
    public SuperSmashTimeInterface ssbt;

    public DBConsultas db;
    public ArrayList<Team> database;
    public Team currentTeam1;
    public Team currentTeam2;
    public boolean auto = false;
    public int timeelapsed = 90;
    public int duration = 500;
    public String[] goals;

    /**
     * Creates new form FrameOlympic
     */
    public FrameGames() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Game Matches");
        getContentPane().setBackground(new Color(0, 51, 51));
        this.MatchArea.setEditable(false);
        this.gelk = new GoldenEyeLicensetokillInterface();
        this.gelv = new GoldenEyeLivesInterface();
        this.gesv = new GoldenEyeSSDVInterface();
        this.getm = new GoldenEyeTeamsInterface();
        this.geti = new GoldenEyeTimeInterface();
        this.mkbt = new MarioKartBattlesInterface();
        this.ssbc = new SuperSmashCoinsInterface();
        this.ssbl = new SuperSmashLivesInterface();
        this.ssbs = new SuperSmashStaminaInterface();
        this.ssbt = new SuperSmashTimeInterface();

        this.BtnRunMatch.setEnabled(false);
        this.goals = new String[2];
        this.CheckShield.setSelected(true);
        this.CheckShield.setEnabled(false);
        this.CheckTE.setEnabled(false);
        this.CheckDoubleR.setEnabled(false);
        this.CheckLoc.setEnabled(false);
    }

    /**
     * Creates new form FrameOlympic
     *
     * @param sport
     * @param tn1
     * @param tn2
     */
    public FrameGames(String sport, String tn1, String tn2) {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Olympic Matches");
        this.MatchArea.setEditable(false);
        this.MatchArea.setEditable(false);
        this.gelk = new GoldenEyeLicensetokillInterface();
        this.gelv = new GoldenEyeLivesInterface();
        this.gesv = new GoldenEyeSSDVInterface();
        this.getm = new GoldenEyeTeamsInterface();
        this.geti = new GoldenEyeTimeInterface();
        this.mkbt = new MarioKartBattlesInterface();
        this.ssbc = new SuperSmashCoinsInterface();
        this.ssbl = new SuperSmashLivesInterface();
        this.ssbs = new SuperSmashStaminaInterface();
        this.ssbt = new SuperSmashTimeInterface();
        this.BtnRunMatch.setEnabled(false);
        this.ComboBoxGames.setSelectedItem(sport);
        this.TxtNameTeam1.setSelectedItem(tn1);
        this.TxtNameTeam2.setSelectedItem(tn2);
        this.goals = new String[2];
    }

    public void autoExecution() throws InterruptedException {
        this.insertTeam1();
        this.insertTeam2();
        this.runMatch();
        this.resetTotalMatch();
    }

    public void insertTeam1() {
        try {
            String teamname = this.TxtNameTeam1.getSelectedItem().toString();

            String route_b = "";
            String route_e = "";
            for (Team t : this.database) {
                if (t.getName().equals(teamname)) {
                    route_e = t.getRuta_e();
                    route_b = t.getRuta_b();
                    break;
                }
            }
            flagSorter s;
            ImageIcon flag;
            repaint();
            this.NameTeam1.setText(teamname);
            if (this.CheckShield.isSelected()) {
                s = new flagSorter(route_e);
                flag = s.setShields(teamname);
            } else {
                s = new flagSorter(route_b);
                flag = s.setFlags(teamname);
            }

            try {
                Image img = flag.getImage().getScaledInstance(this.FlagTeam1.getWidth(),
                        this.FlagTeam1.getHeight(), Image.SCALE_DEFAULT);
                ImageIcon sizedFlag = new ImageIcon(img);
                this.FlagTeam1.setIcon(sizedFlag);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Failed at setting flag, F", "Warning", 0);
                this.FlagTeam1.setIcon(null);
            }

            if (this.FlagTeam1.getIcon() != null && this.FlagTeam2.getIcon() != null) {
                this.BtnRunMatch.setEnabled(true);
            }
        } catch (NullPointerException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ComboBox values are empty, F", "Warning", 0);
        }

    }

    public void insertTeam2() {
        try {
            String teamname = this.TxtNameTeam2.getSelectedItem().toString();
            String route_b = "";
            String route_e = "";
            for (Team t : this.database) {
                if (t.getName().equals(teamname)) {
                    route_e = t.getRuta_e();
                    route_b = t.getRuta_b();
                    break;
                }
            }

            flagSorter s;
            ImageIcon flag;
            repaint();
            this.NameTeam2.setText(teamname);
            if (this.CheckShield.isSelected()) {
                s = new flagSorter(route_e);
                flag = s.setShields(teamname);
            } else {
                s = new flagSorter(route_b);
                flag = s.setFlags(teamname);
            }

            try {
                Image img = flag.getImage().getScaledInstance(this.FlagTeam2.getWidth(),
                        this.FlagTeam2.getHeight(), Image.SCALE_DEFAULT);
                ImageIcon sizedFlag = new ImageIcon(img);
                this.FlagTeam2.setIcon(sizedFlag);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Failed at setting flag, F", "Warning", 0);
                this.FlagTeam2.setIcon(null);
            }

            if (this.FlagTeam2.getIcon() != null && this.FlagTeam1.getIcon() != null) {
                this.BtnRunMatch.setEnabled(true);
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "ComboBox values are empty, F", "Warning", 0);
        }
    }

    public void runMatch() throws InterruptedException {
        this.BtnRunMatch.setEnabled(false);
        String value = (String) this.ComboBoxGames.getSelectedItem();
        this.duration = this.ScrollTime.getValue();
        for (Team t1 : this.database) {
            if (t1.getName().equals(this.NameTeam1.getText())) {
                this.currentTeam1 = t1;
                break;
            }
        }

        for (Team t2 : this.database) {
            if (t2.getName().equals(this.NameTeam2.getText())) {
                this.currentTeam2 = t2;
                break;
            }
        }
        switch (value) {

            case "GE-Lives":
                this.gelv.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                break;

            case "GE-License to Kill":
                this.gelk.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                break;

            case "GE-SSDV":
                this.gesv.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                break;
            case "GE-Teams":
                this.getm.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                break;

            case "GE-Time":
                this.timeelapsed = 350;
                this.geti.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                break;

            case "MK-Battles":
                for(Team t: this.database){
                    t.setGoalsteam(3);
                }
                this.mkbt.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                break;

            case "SSB-Stamina":
                this.ssbs.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                break;

            case "SSB-Lives":
                this.ssbl.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                break;

            case "SSB-Time":
                this.timeelapsed = 350;
                this.ssbt.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                break;

            case "SSB-Coins":
                this.timeelapsed = 350;
                this.ssbc.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                break;
        }
    }

    public void resetMatch() {
        for (Team q : this.database) {
            q.setGoalsteam(0);
            q.setTeamdiff(0.0);
        }

        this.currentTeam1.setGoalsteam(0);
        this.currentTeam2.setGoalsteam(0);
        this.BarTime.setString("0'");
        this.BarTime.setValue(0);
    }

    public void resetTotalMatch() {
        this.BtnResetMatch.setEnabled(true);
        for (Team q : this.database) {
            if (this.ComboBoxGames.getSelectedItem().equals("MK-Battles")) {
                q.setGoalsteam(3);
            } else {
                q.setGoalsteam(0);
            }
            if (this.ComboBoxGames.getSelectedItem().equals("SSB-Stamina")) {
                q.setGoalsteam(150);
            } else {
                q.setGoalsteam(0);
            }
            q.setTeamdiff(0.0);
        }

        this.currentTeam1 = null;
        this.currentTeam2 = null;
        this.GoalTeam1.setText(null);
        this.GoalTeam2.setText(null);
        this.GoalTeamDT1.setText(null);
        this.GoalTeamDT2.setText(null);
        this.GoalPTeam1.setText(null);
        this.GoalPTeam2.setText(null);
        this.FlagTeam1.setIcon(null);
        this.FlagTeam2.setIcon(null);
        this.NameTeam1.setText("nameTeam1");
        this.NameTeam1.setText("nameTeam2");
        this.BarTime.setString("0'");
        this.BarTime.setValue(0);
        this.MatchArea.setText(null);

    }

    public void switchTeams() {
        Object team1 = this.TxtNameTeam1.getSelectedItem();
        Object team2 = this.TxtNameTeam2.getSelectedItem();

        this.GoalTeamDT1.setText(Integer.toString((int) Math.floor(this.currentTeam1.getGlobalgoalsteam())));
        this.GoalTeamDT2.setText(Integer.toString((int) Math.floor(this.currentTeam2.getGlobalgoalsteam())));
        this.TxtNameTeam1.setSelectedItem(team2);
        this.TxtNameTeam2.setSelectedItem(team1);

        Team aux = this.currentTeam1;
        this.currentTeam1 = this.currentTeam2;
        this.currentTeam2 = aux;

        this.insertTeam1();
        this.insertTeam2();

        if (!this.CheckDoubleR.isSelected()) {
            JOptionPane.showMessageDialog(null, "Teams Switched.", "Info", 1);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelMark = new javax.swing.JPanel();
        FlagTeam1 = new javax.swing.JLabel();
        FlagTeam2 = new javax.swing.JLabel();
        PanelScore = new javax.swing.JPanel();
        GoalTeam1 = new javax.swing.JLabel();
        GoalTeam2 = new javax.swing.JLabel();
        Line = new javax.swing.JLabel();
        PanelPen = new javax.swing.JPanel();
        GoalPTeam1 = new javax.swing.JLabel();
        GoalPTeam2 = new javax.swing.JLabel();
        LblLine1 = new javax.swing.JLabel();
        Lblpen = new javax.swing.JLabel();
        PanelFm = new javax.swing.JPanel();
        GoalTeamDT1 = new javax.swing.JLabel();
        GoalTeamDT2 = new javax.swing.JLabel();
        LblLine2 = new javax.swing.JLabel();
        LblDouble = new javax.swing.JLabel();
        LblDuracion = new javax.swing.JLabel();
        ScrollTime = new javax.swing.JScrollBar();
        PanelStat1 = new javax.swing.JPanel();
        NameTeam1 = new javax.swing.JLabel();
        LblStateTeam1 = new javax.swing.JLabel();
        PanelStat2 = new javax.swing.JPanel();
        NameTeam2 = new javax.swing.JLabel();
        LblStateTeam2 = new javax.swing.JLabel();
        PanelTime = new javax.swing.JPanel();
        BarTime = new javax.swing.JProgressBar();
        TxtNameTeam1 = new javax.swing.JComboBox<>();
        TxtNameTeam2 = new javax.swing.JComboBox<>();
        PanelOptions = new javax.swing.JPanel();
        PanelHistory = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        MatchArea = new javax.swing.JTextArea();
        PanelChecks = new javax.swing.JPanel();
        CheckShield = new javax.swing.JCheckBox();
        CheckLoc = new javax.swing.JCheckBox();
        CheckTE = new javax.swing.JCheckBox();
        CheckDoubleR = new javax.swing.JCheckBox();
        LblSport = new javax.swing.JLabel();
        ComboBoxGames = new javax.swing.JComboBox<>();
        PanelButtons = new javax.swing.JPanel();
        BtnInsertTeam1 = new javax.swing.JButton();
        BtnInsertTeam2 = new javax.swing.JButton();
        BtnSwitch = new javax.swing.JButton();
        BtnRunMatch = new javax.swing.JButton();
        BtnBack = new javax.swing.JButton();
        BtnResetMatch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelMark.setBackground(new java.awt.Color(0, 51, 51));
        PanelMark.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        FlagTeam1.setBackground(new java.awt.Color(0, 51, 51));
        FlagTeam1.setOpaque(true);

        FlagTeam2.setBackground(new java.awt.Color(0, 51, 51));
        FlagTeam2.setOpaque(true);

        PanelScore.setBackground(new java.awt.Color(0, 51, 51));
        PanelScore.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PanelScore.setPreferredSize(new java.awt.Dimension(173, 192));

        GoalTeam1.setBackground(new java.awt.Color(0, 125, 125));
        GoalTeam1.setFont(new java.awt.Font("Corbel Light", 1, 18)); // NOI18N
        GoalTeam1.setForeground(new java.awt.Color(255, 255, 255));
        GoalTeam1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GoalTeam1.setText("0");
        GoalTeam1.setOpaque(true);

        GoalTeam2.setBackground(new java.awt.Color(0, 125, 125));
        GoalTeam2.setFont(new java.awt.Font("Corbel Light", 1, 18)); // NOI18N
        GoalTeam2.setForeground(new java.awt.Color(255, 255, 255));
        GoalTeam2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GoalTeam2.setText("0");
        GoalTeam2.setOpaque(true);

        Line.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        Line.setForeground(new java.awt.Color(255, 255, 255));
        Line.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Line.setText("-");
        Line.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        PanelPen.setBackground(new java.awt.Color(0, 51, 51));
        PanelPen.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        GoalPTeam1.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        GoalPTeam1.setForeground(new java.awt.Color(255, 255, 255));
        GoalPTeam1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GoalPTeam1.setText("0");

        GoalPTeam2.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        GoalPTeam2.setForeground(new java.awt.Color(255, 255, 255));
        GoalPTeam2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GoalPTeam2.setText("0");

        LblLine1.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblLine1.setForeground(new java.awt.Color(255, 255, 255));
        LblLine1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblLine1.setText("-");

        javax.swing.GroupLayout PanelPenLayout = new javax.swing.GroupLayout(PanelPen);
        PanelPen.setLayout(PanelPenLayout);
        PanelPenLayout.setHorizontalGroup(
            PanelPenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GoalPTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblLine1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GoalPTeam2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelPenLayout.setVerticalGroup(
            PanelPenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPenLayout.createSequentialGroup()
                .addGroup(PanelPenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GoalPTeam2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblLine1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(GoalPTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 3, Short.MAX_VALUE))
        );

        Lblpen.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        Lblpen.setForeground(new java.awt.Color(255, 255, 255));
        Lblpen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Lblpen.setText("pen");

        PanelFm.setBackground(new java.awt.Color(0, 51, 51));
        PanelFm.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        GoalTeamDT1.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        GoalTeamDT1.setForeground(new java.awt.Color(255, 255, 255));
        GoalTeamDT1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GoalTeamDT1.setText("0");

        GoalTeamDT2.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        GoalTeamDT2.setForeground(new java.awt.Color(255, 255, 255));
        GoalTeamDT2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GoalTeamDT2.setText("0");

        LblLine2.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblLine2.setForeground(new java.awt.Color(255, 255, 255));
        LblLine2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblLine2.setText("-");

        javax.swing.GroupLayout PanelFmLayout = new javax.swing.GroupLayout(PanelFm);
        PanelFm.setLayout(PanelFmLayout);
        PanelFmLayout.setHorizontalGroup(
            PanelFmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFmLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GoalTeamDT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblLine2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GoalTeamDT2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelFmLayout.setVerticalGroup(
            PanelFmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(GoalTeamDT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LblLine2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GoalTeamDT2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        LblDouble.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblDouble.setForeground(new java.awt.Color(255, 255, 255));
        LblDouble.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblDouble.setText("f.m");

        LblDuracion.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblDuracion.setForeground(new java.awt.Color(255, 255, 255));
        LblDuracion.setText("Time: 5");

        ScrollTime.setMaximum(1000);
        ScrollTime.setMinimum(5);
        ScrollTime.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
        ScrollTime.setVisibleAmount(0);
        ScrollTime.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                ScrollTimeMouseDragged(evt);
            }
        });
        ScrollTime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ScrollTimeMousePressed(evt);
            }
        });

        javax.swing.GroupLayout PanelScoreLayout = new javax.swing.GroupLayout(PanelScore);
        PanelScore.setLayout(PanelScoreLayout);
        PanelScoreLayout.setHorizontalGroup(
            PanelScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelScoreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelScoreLayout.createSequentialGroup()
                        .addGroup(PanelScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lblpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LblDouble, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelScoreLayout.createSequentialGroup()
                                .addComponent(GoalTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Line)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(GoalTeam2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PanelFm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PanelPen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10))
                    .addGroup(PanelScoreLayout.createSequentialGroup()
                        .addComponent(LblDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ScrollTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))))
        );
        PanelScoreLayout.setVerticalGroup(
            PanelScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelScoreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GoalTeam2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Line, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GoalTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelPen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Lblpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelFm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblDouble, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(PanelScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelScoreLayout.createSequentialGroup()
                        .addComponent(LblDuracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(PanelScoreLayout.createSequentialGroup()
                        .addComponent(ScrollTime, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        PanelStat1.setBackground(new java.awt.Color(0, 51, 51));
        PanelStat1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        NameTeam1.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        NameTeam1.setForeground(new java.awt.Color(255, 255, 255));
        NameTeam1.setText("TeamName1");

        LblStateTeam1.setBackground(new java.awt.Color(102, 102, 102));
        LblStateTeam1.setOpaque(true);

        javax.swing.GroupLayout PanelStat1Layout = new javax.swing.GroupLayout(PanelStat1);
        PanelStat1.setLayout(PanelStat1Layout);
        PanelStat1Layout.setHorizontalGroup(
            PanelStat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelStat1Layout.createSequentialGroup()
                .addGroup(PanelStat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NameTeam1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addGroup(PanelStat1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LblStateTeam1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelStat1Layout.setVerticalGroup(
            PanelStat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelStat1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NameTeam1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblStateTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelStat2.setBackground(new java.awt.Color(0, 51, 51));
        PanelStat2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        NameTeam2.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        NameTeam2.setForeground(new java.awt.Color(255, 255, 255));
        NameTeam2.setText("TeamName2");

        LblStateTeam2.setBackground(new java.awt.Color(102, 102, 102));
        LblStateTeam2.setOpaque(true);

        javax.swing.GroupLayout PanelStat2Layout = new javax.swing.GroupLayout(PanelStat2);
        PanelStat2.setLayout(PanelStat2Layout);
        PanelStat2Layout.setHorizontalGroup(
            PanelStat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelStat2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelStat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblStateTeam2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NameTeam2, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelStat2Layout.setVerticalGroup(
            PanelStat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelStat2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NameTeam2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblStateTeam2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PanelTime.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BarTime.setFont(new java.awt.Font("Sitka Small", 0, 10)); // NOI18N
        BarTime.setRequestFocusEnabled(false);
        BarTime.setString("0'");
        BarTime.setStringPainted(true);

        javax.swing.GroupLayout PanelTimeLayout = new javax.swing.GroupLayout(PanelTime);
        PanelTime.setLayout(PanelTimeLayout);
        PanelTimeLayout.setHorizontalGroup(
            PanelTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTimeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BarTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelTimeLayout.setVerticalGroup(
            PanelTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTimeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BarTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        TxtNameTeam1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TxtNameTeam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNameTeam1ActionPerformed(evt);
            }
        });

        TxtNameTeam2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout PanelMarkLayout = new javax.swing.GroupLayout(PanelMark);
        PanelMark.setLayout(PanelMarkLayout);
        PanelMarkLayout.setHorizontalGroup(
            PanelMarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMarkLayout.createSequentialGroup()
                .addGroup(PanelMarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMarkLayout.createSequentialGroup()
                        .addGroup(PanelMarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PanelStat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelMarkLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(FlagTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelMarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FlagTeam2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PanelStat2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(PanelMarkLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelMarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(PanelMarkLayout.createSequentialGroup()
                                .addComponent(TxtNameTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TxtNameTeam2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        PanelMarkLayout.setVerticalGroup(
            PanelMarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMarkLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelMarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelMarkLayout.createSequentialGroup()
                        .addGroup(PanelMarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FlagTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FlagTeam2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelMarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelMarkLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PanelStat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMarkLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(PanelStat2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(PanelScore, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelMarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtNameTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNameTeam2, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        PanelOptions.setBackground(new java.awt.Color(0, 51, 51));
        PanelOptions.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        PanelHistory.setBackground(new java.awt.Color(0, 51, 51));
        PanelHistory.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        MatchArea.setBackground(new java.awt.Color(247, 247, 247));
        MatchArea.setColumns(20);
        MatchArea.setFont(new java.awt.Font("MS PGothic", 2, 12)); // NOI18N
        MatchArea.setRows(5);
        jScrollPane2.setViewportView(MatchArea);

        javax.swing.GroupLayout PanelHistoryLayout = new javax.swing.GroupLayout(PanelHistory);
        PanelHistory.setLayout(PanelHistoryLayout);
        PanelHistoryLayout.setHorizontalGroup(
            PanelHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        PanelHistoryLayout.setVerticalGroup(
            PanelHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        PanelChecks.setBackground(new java.awt.Color(0, 51, 51));
        PanelChecks.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PanelChecks.setPreferredSize(new java.awt.Dimension(108, 108));

        CheckShield.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        CheckShield.setForeground(new java.awt.Color(255, 255, 255));
        CheckShield.setText("SH");

        CheckLoc.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        CheckLoc.setForeground(new java.awt.Color(255, 255, 255));
        CheckLoc.setText("LV");
        CheckLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckLocActionPerformed(evt);
            }
        });

        CheckTE.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        CheckTE.setForeground(new java.awt.Color(255, 255, 255));
        CheckTE.setText("ET");

        CheckDoubleR.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        CheckDoubleR.setForeground(new java.awt.Color(255, 255, 255));
        CheckDoubleR.setText("DR");

        LblSport.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblSport.setForeground(new java.awt.Color(255, 255, 255));
        LblSport.setText("Selected Game");

        ComboBoxGames.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ComboBoxGames.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select Game>", "GE-Time", "GE-Lives", "GE-License to Kill", "GE-SSDV", "GE-Teams", "MK-Battles", "SSB-Time", "SSB-Lives", "SSB-Coins", "SSB-Stamina" }));
        ComboBoxGames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxGamesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelChecksLayout = new javax.swing.GroupLayout(PanelChecks);
        PanelChecks.setLayout(PanelChecksLayout);
        PanelChecksLayout.setHorizontalGroup(
            PanelChecksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChecksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelChecksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelChecksLayout.createSequentialGroup()
                        .addGroup(PanelChecksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CheckTE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CheckShield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelChecksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CheckDoubleR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CheckLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(LblSport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboBoxGames, 0, 170, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelChecksLayout.setVerticalGroup(
            PanelChecksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChecksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelChecksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CheckShield)
                    .addComponent(CheckLoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelChecksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CheckTE)
                    .addComponent(CheckDoubleR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblSport, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBoxGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelButtons.setBackground(new java.awt.Color(0, 51, 51));
        PanelButtons.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        BtnInsertTeam1.setBackground(new java.awt.Color(0, 125, 125));
        BtnInsertTeam1.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnInsertTeam1.setForeground(new java.awt.Color(255, 255, 255));
        BtnInsertTeam1.setText("Insert Team 1");
        BtnInsertTeam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInsertTeam1ActionPerformed(evt);
            }
        });

        BtnInsertTeam2.setBackground(new java.awt.Color(0, 125, 125));
        BtnInsertTeam2.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnInsertTeam2.setForeground(new java.awt.Color(255, 255, 255));
        BtnInsertTeam2.setText("Insert Team 2");
        BtnInsertTeam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInsertTeam2ActionPerformed(evt);
            }
        });

        BtnSwitch.setBackground(new java.awt.Color(0, 125, 125));
        BtnSwitch.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnSwitch.setForeground(new java.awt.Color(255, 255, 255));
        BtnSwitch.setText("Switch Teams");
        BtnSwitch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSwitchActionPerformed(evt);
            }
        });

        BtnRunMatch.setBackground(new java.awt.Color(0, 125, 125));
        BtnRunMatch.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnRunMatch.setForeground(new java.awt.Color(255, 255, 255));
        BtnRunMatch.setText("Run");
        BtnRunMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRunMatchActionPerformed(evt);
            }
        });

        BtnBack.setBackground(new java.awt.Color(0, 125, 125));
        BtnBack.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnBack.setForeground(new java.awt.Color(255, 255, 255));
        BtnBack.setText("Back");
        BtnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBackActionPerformed(evt);
            }
        });

        BtnResetMatch.setBackground(new java.awt.Color(0, 125, 125));
        BtnResetMatch.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnResetMatch.setForeground(new java.awt.Color(255, 255, 255));
        BtnResetMatch.setText("Reset");
        BtnResetMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResetMatchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelButtonsLayout = new javax.swing.GroupLayout(PanelButtons);
        PanelButtons.setLayout(PanelButtonsLayout);
        PanelButtonsLayout.setHorizontalGroup(
            PanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelButtonsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(BtnInsertTeam2, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(BtnInsertTeam1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnSwitch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BtnResetMatch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(BtnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnRunMatch, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelButtonsLayout.setVerticalGroup(
            PanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnInsertTeam1)
                    .addComponent(BtnRunMatch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnBack)
                    .addComponent(BtnInsertTeam2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(PanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnResetMatch)
                    .addComponent(BtnSwitch))
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelOptionsLayout = new javax.swing.GroupLayout(PanelOptions);
        PanelOptions.setLayout(PanelOptionsLayout);
        PanelOptionsLayout.setHorizontalGroup(
            PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelOptionsLayout.createSequentialGroup()
                        .addComponent(PanelChecks, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        PanelOptionsLayout.setVerticalGroup(
            PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelChecks, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(PanelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelMark, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelMark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtNameTeam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNameTeam1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNameTeam1ActionPerformed

    private void ScrollTimeMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ScrollTimeMouseDragged
        this.LblDuracion.setText("Time: " + this.ScrollTime.getValue());
    }//GEN-LAST:event_ScrollTimeMouseDragged

    private void ScrollTimeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ScrollTimeMousePressed

    }//GEN-LAST:event_ScrollTimeMousePressed

    private void CheckLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckLocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckLocActionPerformed

    private void BtnInsertTeam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInsertTeam1ActionPerformed
        this.insertTeam1();
    }//GEN-LAST:event_BtnInsertTeam1ActionPerformed

    private void BtnInsertTeam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInsertTeam2ActionPerformed
        this.insertTeam2();
    }//GEN-LAST:event_BtnInsertTeam2ActionPerformed

    private void BtnSwitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSwitchActionPerformed
        this.switchTeams();
    }//GEN-LAST:event_BtnSwitchActionPerformed

    private void BtnRunMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRunMatchActionPerformed
        try {
            this.runMatch();
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Failed at run Match, F", "Warning", 0);
            System.out.println(ex);
        }
    }//GEN-LAST:event_BtnRunMatchActionPerformed

    private void BtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBackActionPerformed
        FrameInicio f = new FrameInicio();
        f.setVisible(true);
        dispose();
    }//GEN-LAST:event_BtnBackActionPerformed

    private void BtnResetMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResetMatchActionPerformed
        this.resetTotalMatch();
    }//GEN-LAST:event_BtnResetMatchActionPerformed

    private void ComboBoxGamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxGamesActionPerformed
        String value = (String) this.ComboBoxGames.getSelectedItem();
        this.TxtNameTeam1.removeAllItems();
        this.TxtNameTeam2.removeAllItems();
        switch (value) {
            case "GE-Time":
                this.database = this.geti.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;
            case "GE-Lives":
                this.database = this.gelv.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "GE-License to Kill":
                this.database = this.gelk.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "GE-SSDV":
                this.database = this.gesv.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "GE-Teams":
                this.database = this.getm.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "MK-Battles":
                this.database = this.mkbt.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "SSB-Time":
                this.database = this.ssbt.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "SSB-Lives":
                this.database = this.ssbl.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "SSB-Coins":
                this.database = this.ssbc.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "SSB-Stamina":
                this.database = this.ssbs.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

        }
    }//GEN-LAST:event_ComboBoxGamesActionPerformed

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
            java.util.logging.Logger.getLogger(FrameGames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameGames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameGames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameGames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameGames().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar BarTime;
    private javax.swing.JButton BtnBack;
    private javax.swing.JButton BtnInsertTeam1;
    private javax.swing.JButton BtnInsertTeam2;
    private javax.swing.JButton BtnResetMatch;
    private javax.swing.JButton BtnRunMatch;
    private javax.swing.JButton BtnSwitch;
    private javax.swing.JCheckBox CheckDoubleR;
    private javax.swing.JCheckBox CheckLoc;
    private javax.swing.JCheckBox CheckShield;
    private javax.swing.JCheckBox CheckTE;
    private javax.swing.JComboBox<String> ComboBoxGames;
    private javax.swing.JLabel FlagTeam1;
    private javax.swing.JLabel FlagTeam2;
    private javax.swing.JLabel GoalPTeam1;
    private javax.swing.JLabel GoalPTeam2;
    private javax.swing.JLabel GoalTeam1;
    private javax.swing.JLabel GoalTeam2;
    private javax.swing.JLabel GoalTeamDT1;
    private javax.swing.JLabel GoalTeamDT2;
    private javax.swing.JLabel LblDouble;
    private javax.swing.JLabel LblDuracion;
    private javax.swing.JLabel LblLine1;
    private javax.swing.JLabel LblLine2;
    private javax.swing.JLabel LblSport;
    private javax.swing.JLabel LblStateTeam1;
    private javax.swing.JLabel LblStateTeam2;
    private javax.swing.JLabel Lblpen;
    private javax.swing.JLabel Line;
    private javax.swing.JTextArea MatchArea;
    private javax.swing.JLabel NameTeam1;
    private javax.swing.JLabel NameTeam2;
    private javax.swing.JPanel PanelButtons;
    private javax.swing.JPanel PanelChecks;
    private javax.swing.JPanel PanelFm;
    private javax.swing.JPanel PanelHistory;
    private javax.swing.JPanel PanelMark;
    private javax.swing.JPanel PanelOptions;
    private javax.swing.JPanel PanelPen;
    private javax.swing.JPanel PanelScore;
    private javax.swing.JPanel PanelStat1;
    private javax.swing.JPanel PanelStat2;
    private javax.swing.JPanel PanelTime;
    private javax.swing.JScrollBar ScrollTime;
    private javax.swing.JComboBox<String> TxtNameTeam1;
    private javax.swing.JComboBox<String> TxtNameTeam2;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
