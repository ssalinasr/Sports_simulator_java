/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package OlympicGames;

import Interfaces.Sports.SquashInterface;
import Interfaces.Sports.TableTennisInterface;
import Interfaces.Sports.WaterpoloInterface;
import Interfaces.Sports.BadmintonInterface;
import Interfaces.Sports.FencingInterface;
import Interfaces.Sports.BasketballInterface;
import Interfaces.Sports.BaseballInterface;
import Interfaces.Sports.CurlingInterface;
import Interfaces.Sports.MenFutsalInterface;
import Interfaces.Sports.HockeyInterface;
import Interfaces.Sports.VolleyballInterface;
import Interfaces.Sports.RugbyInterface;
import Interfaces.Sports.TennisInterface;
import Interfaces.Sports.WomenFootballInterface;
import Interfaces.Sports.ArcheryInterface;
import Interfaces.Sports.HandballInterface;
import Interfaces.Sports.MenFootballInterface;
import Interfaces.Sports.BeachVolleyballInterface;
import Interfaces.Sports.WomenFutsalInterface;
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
public class FrameOlympic extends javax.swing.JFrame {

    public MenFootballInterface footm;
    public WomenFootballInterface footw;
    public MenFutsalInterface footms;
    public WomenFutsalInterface footws;
    public VolleyballInterface voll;
    public BasketballInterface bask;
    public BaseballInterface base;
    public TennisInterface tenn;
    public TableTennisInterface tenm;
    public BadmintonInterface badm;
    public CurlingInterface curl;
    public BeachVolleyballInterface volb;
    public SquashInterface sqsh;
    public RugbyInterface rugb;
    public HandballInterface hand;
    public WaterpoloInterface watp;
    public HockeyInterface hock;
    public ArcheryInterface arch;
    public FencingInterface fenc;

    public DBConsultas db;
    public ArrayList<Team> database;
    public Team currentTeam1;
    public Team currentTeam2;
    public boolean auto = false;
    public int timeelapsed = 90;
    public int duration = 500;
    public String[] goals;
    public String[] penalties;

    /**
     * Creates new form FrameOlympic
     */
    public FrameOlympic() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Olympic Matches");
        getContentPane().setBackground(new Color(0, 51, 51));
        this.MatchArea.setEditable(false);
        this.footm = new MenFootballInterface();
        this.footw = new WomenFootballInterface();
        this.footms = new MenFutsalInterface();
        this.footws = new WomenFutsalInterface();
        this.voll = new VolleyballInterface();
        this.bask = new BasketballInterface();
        this.base = new BaseballInterface();
        this.tenn = new TennisInterface();
        this.tenm = new TableTennisInterface();
        this.badm = new BadmintonInterface();
        this.curl = new CurlingInterface();
        this.volb = new BeachVolleyballInterface();
        this.sqsh = new SquashInterface();
        this.rugb = new RugbyInterface();
        this.hand = new HandballInterface();
        this.watp = new WaterpoloInterface();
        this.hock = new HockeyInterface();
        this.arch = new ArcheryInterface();
        this.fenc = new FencingInterface();
        this.BtnRunMatch.setEnabled(false);
        this.goals = new String[2];
        this.penalties = new String[2];
    }

    /**
     * Creates new form FrameOlympic
     *
     * @param sport
     */
    public FrameOlympic(String sport, String tn1, String tn2) {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Olympic Matches");
        this.MatchArea.setEditable(false);
        this.footm = new MenFootballInterface();
        this.footw = new WomenFootballInterface();
        this.footms = new MenFutsalInterface();
        this.footws = new WomenFutsalInterface();
        this.voll = new VolleyballInterface();
        this.bask = new BasketballInterface();
        this.base = new BaseballInterface();
        this.tenn = new TennisInterface();
        this.tenm = new TableTennisInterface();
        this.badm = new BadmintonInterface();
        this.curl = new CurlingInterface();
        this.volb = new BeachVolleyballInterface();
        this.sqsh = new SquashInterface();
        this.rugb = new RugbyInterface();
        this.hand = new HandballInterface();
        this.watp = new WaterpoloInterface();
        this.hock = new HockeyInterface();
        this.arch = new ArcheryInterface();
        this.fenc = new FencingInterface();
        this.BtnRunMatch.setEnabled(false);
        this.ComboBoxSports.setSelectedItem(sport);
        this.TxtNameTeam1.setSelectedItem(tn1);
        this.TxtNameTeam2.setSelectedItem(tn2);
        this.goals = new String[2];
        this.penalties = new String[2];
        System.out.println("Fixture On Frame: " + this.TxtNameTeam1.getSelectedItem() + "-" + this.TxtNameTeam2.getSelectedItem());
    }

    /**
     * Creates new form FrameOlympic
     *
     * @param sport
     */
    public FrameOlympic(String sport, String tn1, String tn2, boolean TE) {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Olympic Matches");
        this.MatchArea.setEditable(false);
        this.footm = new MenFootballInterface();
        this.footw = new WomenFootballInterface();
        this.footms = new MenFutsalInterface();
        this.footws = new WomenFutsalInterface();
        this.voll = new VolleyballInterface();
        this.bask = new BasketballInterface();
        this.base = new BaseballInterface();
        this.tenn = new TennisInterface();
        this.tenm = new TableTennisInterface();
        this.badm = new BadmintonInterface();
        this.curl = new CurlingInterface();
        this.volb = new BeachVolleyballInterface();
        this.sqsh = new SquashInterface();
        this.rugb = new RugbyInterface();
        this.hand = new HandballInterface();
        this.watp = new WaterpoloInterface();
        this.hock = new HockeyInterface();
        this.arch = new ArcheryInterface();
        this.fenc = new FencingInterface();
        this.BtnRunMatch.setEnabled(false);
        this.ComboBoxSports.setSelectedItem(sport);
        this.TxtNameTeam1.setSelectedItem(tn1);
        this.TxtNameTeam2.setSelectedItem(tn2);
        this.goals = new String[2];
        this.CheckTE.setSelected(TE);
        this.penalties = new String[2];
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
        String value = (String) this.ComboBoxSports.getSelectedItem();
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
        System.out.println("Fixture CR: "+ this.currentTeam1.getName() + "-" + this.currentTeam2.getName());
        switch (value) {
            case "MenFootball":
                this.timeelapsed = 90;
                if (!this.CheckDoubleR.isSelected()) {

                    this.footm.getDifference(this.currentTeam1, this.currentTeam2);
                    if (this.CheckLoc.isSelected()) {
                        this.footm.getLocalBuff(this.currentTeam1, this.currentTeam2);
                    }

                    this.footm.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                            this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                            this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);

                    if ((this.currentTeam1.getGoalsteam() == this.currentTeam2.getGoalsteam()) && this.CheckTE.isSelected()) {
                        this.footm.penaltiesMethod(GoalPTeam1, GoalPTeam2, currentTeam1, currentTeam2, MatchArea);
                    }
                    this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                    this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                    this.penalties[0] = "" + this.GoalPTeam1.getText();
                    this.penalties[1] = "" + this.GoalPTeam2.getText();

                } else {
                    int globalcount = 1;
                    while (globalcount < 3) {
                        this.footm.getDifference(this.currentTeam1, this.currentTeam2);
                        if (this.CheckLoc.isSelected()) {
                            this.footm.getLocalBuff(this.currentTeam1, this.currentTeam2);
                        }
                        this.footm.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                                this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                                this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, globalcount);
                        if ((this.currentTeam1.getGlobalgoalsteam() == this.currentTeam2.getGlobalgoalsteam()) && this.CheckTE.isSelected()
                                && globalcount == 2) {
                            this.footm.penaltiesMethod(GoalPTeam1, GoalPTeam2, currentTeam1, currentTeam2, MatchArea);
                        }

                        //this.currentTeam1.setGlobalgoalsteam(this.currentTeam1.getGlobalgoalsteam() + this.currentTeam1.getGoalsteam());
                        //this.currentTeam2.setGlobalgoalsteam(this.currentTeam2.getGlobalgoalsteam() + this.currentTeam2.getGoalsteam());
                        this.GoalTeamDT1.setText(this.currentTeam1.getGlobalgoalsteam() + "");
                        this.GoalTeamDT2.setText(this.currentTeam2.getGlobalgoalsteam() + "");
                        this.resetMatch();
                        globalcount++;
                    }

                }
                break;
            case "WomenFootball":
                this.timeelapsed = 90;
                if (!this.CheckDoubleR.isSelected()) {

                    this.footw.getDifference(this.currentTeam1, this.currentTeam2);
                    if (this.CheckLoc.isSelected()) {
                        this.footw.getLocalBuff(this.currentTeam1, this.currentTeam2);
                    }

                    this.footw.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                            this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                            this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);
                    if ((this.currentTeam1.getGoalsteam() == this.currentTeam2.getGoalsteam()) && this.CheckTE.isSelected()) {
                        this.footw.penaltiesMethod(GoalPTeam1, GoalPTeam2, currentTeam1, currentTeam2, MatchArea);
                    }
                    this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                    this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                    this.penalties[0] = "" + this.GoalPTeam1.getText();
                    this.penalties[1] = "" + this.GoalPTeam2.getText();

                } else {
                    int globalcount = 1;
                    while (globalcount < 3) {
                        this.footw.getDifference(this.currentTeam1, this.currentTeam2);
                        if (this.CheckLoc.isSelected()) {
                            this.footw.getLocalBuff(this.currentTeam1, this.currentTeam2);
                        }
                        this.footw.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                                this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                                this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, globalcount);
                        if ((this.currentTeam1.getGlobalgoalsteam() == this.currentTeam2.getGlobalgoalsteam()) && this.CheckTE.isSelected()
                                && globalcount == 2) {
                            this.footw.penaltiesMethod(GoalPTeam1, GoalPTeam2, currentTeam1, currentTeam2, MatchArea);
                        }

                        //this.currentTeam1.setGlobalgoalsteam(this.currentTeam1.getGlobalgoalsteam() + this.currentTeam1.getGoalsteam());
                        //this.currentTeam2.setGlobalgoalsteam(this.currentTeam2.getGlobalgoalsteam() + this.currentTeam2.getGoalsteam());
                        this.GoalTeamDT1.setText(this.currentTeam1.getGlobalgoalsteam() + "");
                        this.GoalTeamDT2.setText(this.currentTeam2.getGlobalgoalsteam() + "");

                        this.resetMatch();
                        globalcount++;
                    }

                }
                break;

            case "MenFutsal":
                this.timeelapsed = 60;
                this.footms.getDifference(this.currentTeam1, this.currentTeam2);
                if (this.CheckLoc.isSelected()) {
                    this.footms.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                this.footms.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);

                if ((this.currentTeam1.getGoalsteam() == this.currentTeam2.getGoalsteam()) && this.CheckTE.isSelected()) {
                    this.footms.penaltiesMethod(GoalPTeam1, GoalPTeam2, currentTeam1, currentTeam2, MatchArea);
                }
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                this.penalties[0] = "" + this.GoalPTeam1.getText();
                this.penalties[1] = "" + this.GoalPTeam2.getText();
                break;
            case "WomenFutsal":
                this.timeelapsed = 60;
                this.footws.getDifference(this.currentTeam1, this.currentTeam2);
                if (this.CheckLoc.isSelected()) {
                    this.footws.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                this.footws.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);

                if ((this.currentTeam1.getGoalsteam() == this.currentTeam2.getGoalsteam()) && this.CheckTE.isSelected()) {
                    this.footws.penaltiesMethod(GoalPTeam1, GoalPTeam2, currentTeam1, currentTeam2, MatchArea);
                }

                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                this.penalties[0] = "" + this.GoalPTeam1.getText();
                this.penalties[1] = "" + this.GoalPTeam1.getText();
                break;

            case "Volleyball":
                this.voll.getDifference(this.currentTeam1, this.currentTeam2);
                if (this.CheckLoc.isSelected()) {
                    this.voll.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                while (this.currentTeam1.getGlobalgoalsteam() < 3 && this.currentTeam2.getGlobalgoalsteam() < 3) {
                    this.voll.getDifference(this.currentTeam1, this.currentTeam2);
                    if (this.CheckLoc.isSelected()) {
                        this.voll.getLocalBuff(this.currentTeam1, this.currentTeam2);
                    }
                    this.voll.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                            this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                            this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);

                    if (this.currentTeam1.getGoalsteam() > this.currentTeam2.getGoalsteam()) {
                        this.currentTeam1.setGlobalgoalsteam(this.currentTeam1.getGlobalgoalsteam() + 1);
                    } else {
                        this.currentTeam2.setGlobalgoalsteam(this.currentTeam2.getGlobalgoalsteam() + 1);
                    }

                    this.GoalTeamDT1.setText(this.currentTeam1.getGlobalgoalsteam() + "");
                    this.GoalTeamDT2.setText(this.currentTeam2.getGlobalgoalsteam() + "");

                    this.resetMatch();
                }
                this.goals[0] = "" + this.currentTeam1.getGlobalgoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGlobalgoalsteam();
                break;

            case "Basketball":
                this.timeelapsed = 200;
                this.bask.getDifference(this.currentTeam1, this.currentTeam2);

                if (this.CheckLoc.isSelected()) {
                    this.bask.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                this.bask.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                break;

            case "Baseball":
                this.timeelapsed = 90;
                this.base.getDifference(this.currentTeam1, this.currentTeam2);

                if (this.CheckLoc.isSelected()) {
                    this.base.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                this.base.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                break;

            case "Tennis":
                this.tenn.getDifference(this.currentTeam1, this.currentTeam2);
                if (this.CheckLoc.isSelected()) {
                    this.tenn.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                while ((this.currentTeam1.getGlobalgoalsteam() < 6 && this.currentTeam2.getGlobalgoalsteam() < 6) || (currentTeam1.getGoalsteam() > 5 && currentTeam2.getGoalsteam() - currentTeam1.getGoalsteam() < 2)) {
                    this.tenn.getDifference(this.currentTeam1, this.currentTeam2);
                    if (this.CheckLoc.isSelected()) {
                        this.tenn.getLocalBuff(this.currentTeam1, this.currentTeam2);
                    }
                    this.tenn.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                            this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                            this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);

                    if (this.currentTeam1.getGoalsteam() > this.currentTeam2.getGoalsteam()) {
                        this.currentTeam1.setGlobalgoalsteam(this.currentTeam1.getGlobalgoalsteam() + 1);
                    } else {
                        this.currentTeam2.setGlobalgoalsteam(this.currentTeam2.getGlobalgoalsteam() + 1);
                    }

                    this.GoalTeamDT1.setText(this.currentTeam1.getGlobalgoalsteam() + "");
                    this.GoalTeamDT2.setText(this.currentTeam2.getGlobalgoalsteam() + "");

                    this.resetMatch();
                }
                this.goals[0] = "" + this.currentTeam1.getGlobalgoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGlobalgoalsteam();
                break;

            case "TableTennis":
                this.tenm.getDifference(this.currentTeam1, this.currentTeam2);
                if (this.CheckLoc.isSelected()) {
                    this.tenm.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                while ((this.currentTeam1.getGlobalgoalsteam() < 3 && this.currentTeam2.getGlobalgoalsteam() < 3) || (currentTeam1.getGoalsteam() > 2 && currentTeam2.getGoalsteam() - currentTeam1.getGoalsteam() < 2)) {
                    this.tenm.getDifference(this.currentTeam1, this.currentTeam2);
                    if (this.CheckLoc.isSelected()) {
                        this.tenm.getLocalBuff(this.currentTeam1, this.currentTeam2);
                    }
                    this.tenm.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                            this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                            this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);

                    if (this.currentTeam1.getGoalsteam() > this.currentTeam2.getGoalsteam()) {
                        this.currentTeam1.setGlobalgoalsteam(this.currentTeam1.getGlobalgoalsteam() + 1);
                    } else {
                        this.currentTeam2.setGlobalgoalsteam(this.currentTeam2.getGlobalgoalsteam() + 1);
                    }

                    this.GoalTeamDT1.setText(this.currentTeam1.getGlobalgoalsteam() + "");
                    this.GoalTeamDT2.setText(this.currentTeam2.getGlobalgoalsteam() + "");

                    this.resetMatch();
                }
                this.goals[0] = "" + this.currentTeam1.getGlobalgoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGlobalgoalsteam();
                break;

            case "Badminton":
                this.badm.getDifference(this.currentTeam1, this.currentTeam2);
                if (this.CheckLoc.isSelected()) {
                    this.badm.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                while ((this.currentTeam1.getGlobalgoalsteam() < 3 && this.currentTeam2.getGlobalgoalsteam() < 3) || (currentTeam1.getGoalsteam() > 2 && currentTeam2.getGoalsteam() - currentTeam1.getGoalsteam() < 2)) {
                    this.badm.getDifference(this.currentTeam1, this.currentTeam2);
                    if (this.CheckLoc.isSelected()) {
                        this.badm.getLocalBuff(this.currentTeam1, this.currentTeam2);
                    }
                    this.badm.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                            this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                            this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);

                    if (this.currentTeam1.getGoalsteam() > this.currentTeam2.getGoalsteam()) {
                        this.currentTeam1.setGlobalgoalsteam(this.currentTeam1.getGlobalgoalsteam() + 1);
                    } else {
                        this.currentTeam2.setGlobalgoalsteam(this.currentTeam2.getGlobalgoalsteam() + 1);
                    }

                    this.GoalTeamDT1.setText(this.currentTeam1.getGlobalgoalsteam() + "");
                    this.GoalTeamDT2.setText(this.currentTeam2.getGlobalgoalsteam() + "");

                    this.resetMatch();
                }
                this.goals[0] = "" + this.currentTeam1.getGlobalgoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGlobalgoalsteam();
                break;

            case "Curling":
                this.timeelapsed = 100;
                this.curl.getDifference(this.currentTeam1, this.currentTeam2);

                if (this.CheckLoc.isSelected()) {
                    this.curl.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                this.curl.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                break;

            case "BeachVolley":
                this.volb.getDifference(this.currentTeam1, this.currentTeam2);
                if (this.CheckLoc.isSelected()) {
                    this.volb.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                while (this.currentTeam1.getGlobalgoalsteam() < 3 && this.currentTeam2.getGlobalgoalsteam() < 3) {
                    this.volb.getDifference(this.currentTeam1, this.currentTeam2);
                    if (this.CheckLoc.isSelected()) {
                        this.volb.getLocalBuff(this.currentTeam1, this.currentTeam2);
                    }
                    this.volb.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                            this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                            this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);

                    if (this.currentTeam1.getGoalsteam() > this.currentTeam2.getGoalsteam()) {
                        this.currentTeam1.setGlobalgoalsteam(this.currentTeam1.getGlobalgoalsteam() + 1);
                    } else {
                        this.currentTeam2.setGlobalgoalsteam(this.currentTeam2.getGlobalgoalsteam() + 1);
                    }

                    this.GoalTeamDT1.setText(this.currentTeam1.getGlobalgoalsteam() + "");
                    this.GoalTeamDT2.setText(this.currentTeam2.getGlobalgoalsteam() + "");

                    this.resetMatch();
                }
                this.goals[0] = "" + this.currentTeam1.getGlobalgoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGlobalgoalsteam();
                break;

            case "Squash":
                this.sqsh.getDifference(this.currentTeam1, this.currentTeam2);
                if (this.CheckLoc.isSelected()) {
                    this.sqsh.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                while (this.currentTeam1.getGlobalgoalsteam() < 3 && this.currentTeam2.getGlobalgoalsteam() < 3) {
                    this.sqsh.getDifference(this.currentTeam1, this.currentTeam2);
                    if (this.CheckLoc.isSelected()) {
                        this.sqsh.getLocalBuff(this.currentTeam1, this.currentTeam2);
                    }
                    this.sqsh.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                            this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                            this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);

                    if (this.currentTeam1.getGoalsteam() > this.currentTeam2.getGoalsteam()) {
                        this.currentTeam1.setGlobalgoalsteam(this.currentTeam1.getGlobalgoalsteam() + 1);
                    } else {
                        this.currentTeam2.setGlobalgoalsteam(this.currentTeam2.getGlobalgoalsteam() + 1);
                    }

                    this.GoalTeamDT1.setText(this.currentTeam1.getGlobalgoalsteam() + "");
                    this.GoalTeamDT2.setText(this.currentTeam2.getGlobalgoalsteam() + "");

                    this.resetMatch();
                }
                this.goals[0] = "" + this.currentTeam1.getGlobalgoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGlobalgoalsteam();
                break;

            case "Rugby":
                this.timeelapsed = 80;
                this.rugb.getDifference(this.currentTeam1, this.currentTeam2);
                if (this.CheckLoc.isSelected()) {
                    this.rugb.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                this.rugb.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);

                if ((this.currentTeam1.getGoalsteam() == this.currentTeam2.getGoalsteam()) && this.CheckTE.isSelected()) {
                    this.rugb.penaltiesMethod(GoalPTeam1, GoalPTeam2, currentTeam1, currentTeam2, MatchArea);
                }
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                this.penalties[0] = "" + this.GoalPTeam1.getText();
                this.penalties[1] = "" + this.GoalPTeam2.getText();
                break;

            case "Handball":
                this.timeelapsed = 60;
                this.hand.getDifference(this.currentTeam1, this.currentTeam2);
                if (this.CheckLoc.isSelected()) {
                    this.hand.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                this.hand.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);

                if ((this.currentTeam1.getGoalsteam() == this.currentTeam2.getGoalsteam()) && this.CheckTE.isSelected()) {
                    this.hand.penaltiesMethod(GoalPTeam1, GoalPTeam2, currentTeam1, currentTeam2, MatchArea);
                }
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                this.penalties[0] = "" + this.GoalPTeam1.getText();
                this.penalties[1] = "" + this.GoalPTeam2.getText();
                break;

            case "Waterpolo":
                this.timeelapsed = 32;
                this.watp.getDifference(this.currentTeam1, this.currentTeam2);

                if (this.CheckLoc.isSelected()) {
                    this.watp.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                this.watp.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                break;

            case "Hockey":
                this.timeelapsed = 60;
                this.hock.getDifference(this.currentTeam1, this.currentTeam2);
                if (this.CheckLoc.isSelected()) {
                    this.hock.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                this.hock.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);

                if ((this.currentTeam1.getGoalsteam() == this.currentTeam2.getGoalsteam()) && this.CheckTE.isSelected()) {
                    this.hock.penaltiesMethod(GoalPTeam1, GoalPTeam2, currentTeam1, currentTeam2, MatchArea);
                }
                this.goals[0] = "" + this.currentTeam1.getGoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGoalsteam();
                this.penalties[0] = "" + this.GoalPTeam1.getText();
                this.penalties[1] = "" + this.GoalPTeam2.getText();
                break;

            case "Archery":
                this.arch.getDifference(this.currentTeam1, this.currentTeam2);
                if (this.CheckLoc.isSelected()) {
                    this.arch.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                while (this.currentTeam1.getGlobalgoalsteam() < 6 && this.currentTeam2.getGlobalgoalsteam() < 6) {
                    this.arch.getDifference(this.currentTeam1, this.currentTeam2);
                    if (this.CheckLoc.isSelected()) {
                        this.arch.getLocalBuff(this.currentTeam1, this.currentTeam2);
                    }
                    this.arch.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                            this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                            this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);

                    if (this.currentTeam1.getGoalsteam() > this.currentTeam2.getGoalsteam()) {
                        this.currentTeam1.setGlobalgoalsteam(this.currentTeam1.getGlobalgoalsteam() + 2);
                    } else {
                        if (this.currentTeam2.getGoalsteam() > this.currentTeam1.getGoalsteam()) {
                            this.currentTeam2.setGlobalgoalsteam(this.currentTeam2.getGlobalgoalsteam() + 2);
                        } else {
                            this.currentTeam1.setGlobalgoalsteam(this.currentTeam1.getGlobalgoalsteam() + 1);
                            this.currentTeam2.setGlobalgoalsteam(this.currentTeam2.getGlobalgoalsteam() + 1);
                        }
                    }

                    this.GoalTeamDT1.setText(this.currentTeam1.getGlobalgoalsteam() + "");
                    this.GoalTeamDT2.setText(this.currentTeam2.getGlobalgoalsteam() + "");

                    this.resetMatch();
                }
                this.goals[0] = "" + this.currentTeam1.getGlobalgoalsteam();
                this.goals[1] = "" + this.currentTeam2.getGlobalgoalsteam();
                break;
            case "Fencing":
                this.timeelapsed = 150;
                this.fenc.getDifference(this.currentTeam1, this.currentTeam2);

                if (this.CheckLoc.isSelected()) {
                    this.fenc.getLocalBuff(this.currentTeam1, this.currentTeam2);
                }

                this.fenc.goalsInstantMatch(this.timeelapsed, this.currentTeam1, this.currentTeam2,
                        this.CheckTE, this.CheckDoubleR.isSelected(), this.auto, this.duration,
                        this.MatchArea, this.GoalTeam1, this.GoalTeam2, this.BarTime, 0);
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
            q.setGoalsteam(0);
            q.setTeamdiff(0.0);
            q.setGlobalgoalsteam(0);
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
        this.CheckTE.setSelected(false);

    }

    public void switchTeams() {
        Object team1 = this.TxtNameTeam1.getSelectedItem();
        Object team2 = this.TxtNameTeam2.getSelectedItem();

        //this.GoalTeamDT1.setText(Integer.toString((int) Math.floor(this.currentTeam1.getGlobalgoalsteam())));
        //this.GoalTeamDT2.setText(Integer.toString((int) Math.floor(this.currentTeam2.getGlobalgoalsteam())));
        this.TxtNameTeam1.setSelectedItem(team2);
        this.TxtNameTeam2.setSelectedItem(team1);

        /*
        Team aux = this.currentTeam1;
        this.currentTeam1 = this.currentTeam2;
        this.currentTeam2 = aux;
         */
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
        ComboBoxSports = new javax.swing.JComboBox<>();
        PanelButtons = new javax.swing.JPanel();
        BtnInsertTeam1 = new javax.swing.JButton();
        BtnInsertTeam2 = new javax.swing.JButton();
        BtnSwitch = new javax.swing.JButton();
        BtnRunMatch = new javax.swing.JButton();
        BtnBack = new javax.swing.JButton();
        BtnResetMatch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelMark.setBackground(new java.awt.Color(0, 51, 51));
        PanelMark.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Board", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        PanelScore.setBackground(new java.awt.Color(0, 51, 51));
        PanelScore.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PanelScore.setPreferredSize(new java.awt.Dimension(173, 192));

        GoalTeam1.setBackground(new java.awt.Color(0, 125, 125));
        GoalTeam1.setFont(new java.awt.Font("Corbel Light", 1, 18)); // NOI18N
        GoalTeam1.setForeground(new java.awt.Color(255, 255, 255));
        GoalTeam1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GoalTeam1.setText("0");
        GoalTeam1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        GoalTeam1.setOpaque(true);

        GoalTeam2.setBackground(new java.awt.Color(0, 125, 125));
        GoalTeam2.setFont(new java.awt.Font("Corbel Light", 1, 18)); // NOI18N
        GoalTeam2.setForeground(new java.awt.Color(255, 255, 255));
        GoalTeam2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GoalTeam2.setText("0");
        GoalTeam2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
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
        PanelOptions.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Board", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        PanelHistory.setBackground(new java.awt.Color(0, 51, 51));
        PanelHistory.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Board", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

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
        LblSport.setText("Selected Sport");

        ComboBoxSports.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ComboBoxSports.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select Sport>", "MenFootball", "WomenFootball", "MenFutsal", "WomenFutsal", "Volleyball", "Basketball", "Baseball", "Badminton", "Curling", "Tennis", "Rugby", "Squash", "BeachVolley", "TableTennis", "Handball", "Waterpolo", "Hockey", "Archery", "Fencing" }));
        ComboBoxSports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxSportsActionPerformed(evt);
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
                    .addComponent(ComboBoxSports, 0, 170, Short.MAX_VALUE))
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
                .addComponent(ComboBoxSports, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelButtons.setBackground(new java.awt.Color(0, 51, 51));
        PanelButtons.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Board", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

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

    private void ComboBoxSportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxSportsActionPerformed
        String value = (String) this.ComboBoxSports.getSelectedItem();
        this.TxtNameTeam1.removeAllItems();
        this.TxtNameTeam2.removeAllItems();
        switch (value) {
            case "MenFootball":
                this.database = this.footm.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;
            case "WomenFootball":
                this.database = this.footw.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "MenFutsal":
                this.database = this.footms.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "WomenFutsal":
                this.database = this.footws.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "Volleyball":
                this.database = this.voll.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "Basketball":
                this.database = this.bask.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "Baseball":
                this.database = this.base.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "Tennis":
                this.database = this.tenn.getDatabaseValues();
                for (Team t : this.database) {
                    System.out.print(t.getName() + " " + t.getClass());
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "TableTennis":
                this.database = this.tenm.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "Badminton":
                this.database = this.badm.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "Curling":
                this.database = this.curl.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "BeachVolley":
                this.database = this.volb.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "Squash":
                this.database = this.sqsh.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "Rugby":
                this.database = this.rugb.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }

            case "Handball":
                this.database = this.hand.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "Waterpolo":
                this.database = this.watp.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "Hockey":
                this.database = this.hock.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "Archery":
                this.database = this.hock.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

            case "Fencing":
                this.database = this.fenc.getDatabaseValues();
                for (Team t : this.database) {
                    this.TxtNameTeam1.addItem(t.getName());
                    this.TxtNameTeam2.addItem(t.getName());
                }
                break;

        }
    }//GEN-LAST:event_ComboBoxSportsActionPerformed

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
            java.util.logging.Logger.getLogger(FrameOlympic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameOlympic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameOlympic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameOlympic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameOlympic().setVisible(true);
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
    private javax.swing.JComboBox<String> ComboBoxSports;
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
