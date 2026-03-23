/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;

import Exports.ClubsExport;
import Interfaces.Sports.MenClubsFootballInterface;
import Main.FrameInicio;
import Modules.TableTeam;
import Tools.RRAlgorithm.calendarioRR;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import Modules.*;
import OlympicGames.FrameClubs;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Flia_Salinas
 */
public class ManyMatchesClubs extends javax.swing.JFrame {

    /**
     * Creates new form ManyMatchesSports
     */
    ArrayList<TableTeam> tableteam = new ArrayList<>();
    private ArrayList<Team> database = new ArrayList<>();
    private ArrayList<Vector> results = new ArrayList<>();
    private DefaultListModel modlist = new DefaultListModel();
    FrameClubs ol;
    private DefaultTableModel tblres = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public ManyMatchesClubs() {
        initComponents();
        getContentPane().setBackground(new Color(0, 51, 51));
        setResizable(false);
        setLocationRelativeTo(null);
        this.AreaFixture.setEditable(false);
        this.TxtFriendly.setEditable(false);
        setTitle("Automatic Club Leagues");
        this.initTableGUI();
        this.initTableResults();
        MenClubsFootballInterface footm = new MenClubsFootballInterface();
        try {
            ArrayList<String> leagues = footm.getLeagues();
            for (String s : leagues) {
                this.ComboLeague.addItem(s);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed at getting leagues, F", "Warning", 0);
            System.out.println(ex);
        }
    }

    public void putResults(String[] results) {
        tblres.addRow(results);
        this.TableResults.setModel(tblres);
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

    public String getClubLeague(String team) {
        try {
            MenClubsFootballInterface fm = new MenClubsFootballInterface();
            return fm.getClubLeagues(team);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            System.out.println(ex);
        }
        return "";
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

    public void setComboTeamsValue(String team) {
        this.ComboTeams.getModel().setSelectedItem(team);
    }

    public void setComboLeagueValue(String league) {
        this.ComboLeague.getModel().setSelectedItem(league);
    }

    public ArrayList<String> getComboLeagueElements() {
        ArrayList<String> elements = new ArrayList<>();
        for (int i = 0; i < this.ComboLeague.getModel().getSize(); i++) {
            elements.add(this.ComboLeague.getModel().getElementAt(i));
        }
        return elements;
    }

    public void setLocalSelected(boolean sel) {
        this.CheckLocVis.setSelected(sel);
    }

    public void setShieldSelected(boolean sel) {
        this.CheckShield.setSelected(sel);
    }

    public void setHomeSelected(boolean sel) {
        this.CheckHome.setSelected(sel);
    }

    public JTable getSimTable() {
        return this.TablePoints;
    }

    public RowSorter<? extends TableModel> getSimTableSorter() {
        RowSorter<? extends TableModel> rowSorter = this.TablePoints.getRowSorter();
        return rowSorter;
    }

    public DefaultTableModel getResTeable() {
        return (DefaultTableModel) this.TableResults.getModel();
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

        if (!this.ComboTeams.getSelectedItem().toString().equals("select team") && !isDuplicated) {
            this.modlist.addElement(this.ComboTeams.getSelectedItem());
            this.ListSelectedTeams.setModel(modlist);
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

    public void addAllTeams() {
        for (int i = 0; i < this.ComboTeams.getItemCount(); i++) {
            if (!this.ComboTeams.getSelectedItem().toString().equals("select team")) {
                this.modlist.addElement(this.ComboTeams.getItemAt(i));
                this.ListSelectedTeams.setModel(modlist);
            }
        }
    }

    public void createFixture() {
        calendarioRR r = new calendarioRR();
        this.AreaFixture.setText(null);
        this.TableResults.setModel(new DefaultTableModel());
        Object[] countries = this.modlist.toArray();
        String c_new = "";

        for (int i = 0; i < countries.length; i++) {
            if (i == countries.length - 1) {
                c_new += countries[i];
            } else {
                c_new += countries[i] + ",";
            }
        }

        String result = "";
        try {
            result = r.runScript(c_new, this.CheckHome.isSelected());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el fixture.", "Error", 0);
        } finally {
            this.AreaFixture.setText(result);
        }

    }

    public void playMatches() {
        try {
            String fixture = this.AreaFixture.getText();
            String[] fix_split = fixture.split("\n");
            for (String f : fix_split) {
                System.out.print(f + " ");
                String[] fx = f.split("-");
                String team1 = fx[0];
                String team2 = fx[1];

                String league1 = this.getClubLeague(team1);
                String league2 = this.getClubLeague(team2);

                this.ol = new FrameClubs("Clubs", team1, team2, league1, league2);
                this.ol.autoExecution();

                String[] goals = this.ol.goals;
                this.ol.resetTotalMatch();

                team1 = team1.replaceAll("\\s", "|");
                team2 = team2.replaceAll("\\s", "|");

                String[] results_one = new String[4];
                results_one[0] = team1;
                results_one[1] = goals[0];
                results_one[2] = team2;
                results_one[3] = goals[1];

                this.putResults(results_one);
                this.ol.dispose();
            }
            this.createTable();
        } catch (InterruptedException ex) {
            System.out.println("Interrupted Exception");
        }

    }

    public void resetMatches() {
        this.AreaFixture.setText(null);
        this.ListSelectedTeams.setModel(new DefaultListModel());
        this.tableteam.clear();
        this.TableResults.setModel(new DefaultTableModel());
        this.initTableGUI();
        this.initTableResults();
        this.modlist.clear();
        this.results.clear();
    }

    public void generateGroups() {
        Object[] countries = this.modlist.toArray();
        if (countries.length % 4 == 0 && countries.length > 0) {
            try {
                RandomMatches rnd = new RandomMatches();
                rnd.createGroups(countries);
                this.TxtFriendly.setText(null);
                this.TxtFriendly.setText(this.TxtFriendly.getText() + rnd.mat);
            } catch (SQLException ex) {
                System.out.println("ERROR " + ex);
            }
        } else {
            if (countries.length == 0) {
                JOptionPane.showMessageDialog(null, "The list is empty", "Warnimg", 2);
            }
            if (countries.length % 4 != 0 && countries.length > 0) {
                JOptionPane.showMessageDialog(null, "The number of teams should be a multiple of 4", "Warnimg", 2);
            }

        }
    }

    public void putGroup() {
        String groupsText = this.TxtFriendly.getText();
        String[] splGroups = groupsText.split("\n");
        for (String str : splGroups) {
            if (str.equals("Grupos: ") || str.equals("Africa ") || str.equals("America ")
                    || str.equals("Europa ") || str.equals("Asia/Oceania ") || str.equals("")) {
                System.out.println("Skip this text...");
                this.TxtFriendly.setText(this.TxtFriendly.getText().replace(str, ""));
            } else {
                this.TxtFriendly.setText(this.TxtFriendly.getText().replace(str, ""));
                str = str.replaceAll("\\[", "").replaceAll("\\]", "");
                String actGroup[] = str.split(",");
                System.out.println(Arrays.toString(actGroup));
                this.modlist.clear();
                int counter = 0;
                for (String team : actGroup) {
                    ListModel vm = this.ListSelectedTeams.getModel();
                    if (counter != 0) {
                        team = team.replaceFirst("\\s", "");
                    }
                    this.modlist.addElement(team);
                    this.ListSelectedTeams.setModel(modlist);
                    counter++;
                }
                break;
            }

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

        PanelOptions = new javax.swing.JPanel();
        PanelActions = new javax.swing.JPanel();
        BtnAddTeam = new javax.swing.JButton();
        BtnChangeTeam = new javax.swing.JButton();
        BtnRemoveTeam = new javax.swing.JButton();
        BtnAddAllTeams = new javax.swing.JButton();
        BtnRandom = new javax.swing.JButton();
        PanelParameters = new javax.swing.JPanel();
        CheckShield = new javax.swing.JCheckBox();
        CheckHome = new javax.swing.JCheckBox();
        CheckLocVis = new javax.swing.JCheckBox();
        CheckTE = new javax.swing.JCheckBox();
        PanelDuration = new javax.swing.JPanel();
        BtnReset = new javax.swing.JButton();
        BtnSoftReset = new javax.swing.JButton();
        PanelTeams = new javax.swing.JPanel();
        ComboTeams = new javax.swing.JComboBox<>();
        ComboLeague = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        ListSelectedTeams = new javax.swing.JList<>();
        PanelFixture = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        AreaFixture = new javax.swing.JTextArea();
        BtnFixture = new javax.swing.JButton();
        BtnMatches = new javax.swing.JButton();
        BtnBack = new javax.swing.JButton();
        PanelResults = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablePoints = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        TxtFriendly = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableResults = new javax.swing.JTable();
        BtnGroup = new javax.swing.JButton();
        BtnGenGroups = new javax.swing.JButton();
        BtnExportClub = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 0));

        PanelOptions.setBackground(new java.awt.Color(0, 51, 51));
        PanelOptions.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        PanelActions.setBackground(new java.awt.Color(0, 51, 51));
        PanelActions.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Actions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        PanelActions.setForeground(new java.awt.Color(255, 255, 255));

        BtnAddTeam.setBackground(new java.awt.Color(0, 102, 102));
        BtnAddTeam.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnAddTeam.setForeground(new java.awt.Color(255, 255, 255));
        BtnAddTeam.setText("Add");
        BtnAddTeam.setBorder(null);
        BtnAddTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddTeamActionPerformed(evt);
            }
        });

        BtnChangeTeam.setBackground(new java.awt.Color(0, 102, 102));
        BtnChangeTeam.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnChangeTeam.setForeground(new java.awt.Color(255, 255, 255));
        BtnChangeTeam.setText("Change");
        BtnChangeTeam.setBorder(null);
        BtnChangeTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnChangeTeamActionPerformed(evt);
            }
        });

        BtnRemoveTeam.setBackground(new java.awt.Color(0, 102, 102));
        BtnRemoveTeam.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnRemoveTeam.setForeground(new java.awt.Color(255, 255, 255));
        BtnRemoveTeam.setText("Remove");
        BtnRemoveTeam.setBorder(null);
        BtnRemoveTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRemoveTeamActionPerformed(evt);
            }
        });

        BtnAddAllTeams.setBackground(new java.awt.Color(0, 102, 102));
        BtnAddAllTeams.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnAddAllTeams.setForeground(new java.awt.Color(255, 255, 255));
        BtnAddAllTeams.setText("Add All");
        BtnAddAllTeams.setBorder(null);
        BtnAddAllTeams.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddAllTeamsActionPerformed(evt);
            }
        });

        BtnRandom.setBackground(new java.awt.Color(0, 102, 102));
        BtnRandom.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnRandom.setForeground(new java.awt.Color(255, 255, 255));
        BtnRandom.setText("Random Clubs");
        BtnRandom.setBorder(null);
        BtnRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRandomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelActionsLayout = new javax.swing.GroupLayout(PanelActions);
        PanelActions.setLayout(PanelActionsLayout);
        PanelActionsLayout.setHorizontalGroup(
            PanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelActionsLayout.createSequentialGroup()
                .addGroup(PanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelActionsLayout.createSequentialGroup()
                        .addComponent(BtnAddTeam, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnChangeTeam, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnRemoveTeam, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                    .addComponent(BtnAddAllTeams, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnRandom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelActionsLayout.setVerticalGroup(
            PanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelActionsLayout.createSequentialGroup()
                .addGroup(PanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAddTeam)
                    .addComponent(BtnChangeTeam)
                    .addComponent(BtnRemoveTeam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnAddAllTeams)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnRandom))
        );

        PanelParameters.setBackground(new java.awt.Color(0, 51, 51));
        PanelParameters.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        CheckShield.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        CheckShield.setForeground(new java.awt.Color(255, 255, 255));
        CheckShield.setText("SH");

        CheckHome.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        CheckHome.setForeground(new java.awt.Color(255, 255, 255));
        CheckHome.setText("OH");

        CheckLocVis.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        CheckLocVis.setForeground(new java.awt.Color(255, 255, 255));
        CheckLocVis.setText("LV");

        CheckTE.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        CheckTE.setForeground(new java.awt.Color(255, 255, 255));
        CheckTE.setText("ET");

        javax.swing.GroupLayout PanelParametersLayout = new javax.swing.GroupLayout(PanelParameters);
        PanelParameters.setLayout(PanelParametersLayout);
        PanelParametersLayout.setHorizontalGroup(
            PanelParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelParametersLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(CheckShield, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CheckHome, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CheckLocVis, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CheckTE, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelParametersLayout.setVerticalGroup(
            PanelParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelParametersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CheckShield)
                    .addComponent(CheckLocVis)
                    .addComponent(CheckHome)
                    .addComponent(CheckTE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelDuration.setBackground(new java.awt.Color(0, 51, 51));
        PanelDuration.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Reset", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        BtnReset.setBackground(new java.awt.Color(0, 102, 102));
        BtnReset.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnReset.setForeground(new java.awt.Color(255, 255, 255));
        BtnReset.setText("Reset");
        BtnReset.setBorder(null);
        BtnReset.setBorderPainted(false);
        BtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResetActionPerformed(evt);
            }
        });

        BtnSoftReset.setBackground(new java.awt.Color(0, 102, 102));
        BtnSoftReset.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnSoftReset.setForeground(new java.awt.Color(255, 255, 255));
        BtnSoftReset.setText("Soft Reset");
        BtnSoftReset.setBorder(null);
        BtnSoftReset.setBorderPainted(false);
        BtnSoftReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSoftResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDurationLayout = new javax.swing.GroupLayout(PanelDuration);
        PanelDuration.setLayout(PanelDurationLayout);
        PanelDurationLayout.setHorizontalGroup(
            PanelDurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDurationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnReset, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(BtnSoftReset, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelDurationLayout.setVerticalGroup(
            PanelDurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDurationLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(BtnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnSoftReset, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelTeams.setBackground(new java.awt.Color(0, 51, 51));
        PanelTeams.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Teams", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        ComboTeams.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        ComboTeams.setForeground(new java.awt.Color(255, 255, 255));
        ComboTeams.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select team>" }));
        ComboTeams.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboTeamsItemStateChanged(evt);
            }
        });
        ComboTeams.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboTeamsActionPerformed(evt);
            }
        });

        ComboLeague.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        ComboLeague.setForeground(new java.awt.Color(255, 255, 255));
        ComboLeague.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select League>" }));
        ComboLeague.setName(""); // NOI18N
        ComboLeague.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboLeagueActionPerformed(evt);
            }
        });

        ListSelectedTeams.setBackground(new java.awt.Color(0, 153, 153));
        ListSelectedTeams.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        ListSelectedTeams.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane6.setViewportView(ListSelectedTeams);

        javax.swing.GroupLayout PanelTeamsLayout = new javax.swing.GroupLayout(PanelTeams);
        PanelTeams.setLayout(PanelTeamsLayout);
        PanelTeamsLayout.setHorizontalGroup(
            PanelTeamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTeamsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelTeamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ComboTeams, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(ComboLeague, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelTeamsLayout.setVerticalGroup(
            PanelTeamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTeamsLayout.createSequentialGroup()
                .addComponent(ComboTeams, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboLeague, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PanelFixture.setBackground(new java.awt.Color(0, 51, 51));
        PanelFixture.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Fixture", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        AreaFixture.setBackground(new java.awt.Color(0, 153, 153));
        AreaFixture.setColumns(20);
        AreaFixture.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        AreaFixture.setForeground(new java.awt.Color(255, 255, 255));
        AreaFixture.setRows(5);
        jScrollPane1.setViewportView(AreaFixture);

        BtnFixture.setBackground(new java.awt.Color(0, 102, 102));
        BtnFixture.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnFixture.setForeground(new java.awt.Color(255, 255, 255));
        BtnFixture.setText("Create Fixture");
        BtnFixture.setBorder(null);
        BtnFixture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFixtureActionPerformed(evt);
            }
        });

        BtnMatches.setBackground(new java.awt.Color(0, 102, 102));
        BtnMatches.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnMatches.setForeground(new java.awt.Color(255, 255, 255));
        BtnMatches.setText("Play Matches");
        BtnMatches.setBorder(null);
        BtnMatches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMatchesActionPerformed(evt);
            }
        });

        BtnBack.setBackground(new java.awt.Color(0, 102, 102));
        BtnBack.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnBack.setForeground(new java.awt.Color(255, 255, 255));
        BtnBack.setText("Back");
        BtnBack.setBorder(null);
        BtnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelFixtureLayout = new javax.swing.GroupLayout(PanelFixture);
        PanelFixture.setLayout(PanelFixtureLayout);
        PanelFixtureLayout.setHorizontalGroup(
            PanelFixtureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelFixtureLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelFixtureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BtnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(PanelFixtureLayout.createSequentialGroup()
                        .addComponent(BtnFixture, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(BtnMatches, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelFixtureLayout.setVerticalGroup(
            PanelFixtureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFixtureLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelFixtureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnMatches, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(BtnFixture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnBack, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelOptionsLayout = new javax.swing.GroupLayout(PanelOptions);
        PanelOptions.setLayout(PanelOptionsLayout);
        PanelOptionsLayout.setHorizontalGroup(
            PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOptionsLayout.createSequentialGroup()
                .addGroup(PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelParameters, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelDuration, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelOptionsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelActions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PanelTeams, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PanelFixture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        PanelOptionsLayout.setVerticalGroup(
            PanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOptionsLayout.createSequentialGroup()
                .addComponent(PanelTeams, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelActions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelFixture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelParameters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelDuration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelResults.setBackground(new java.awt.Color(0, 51, 51));
        PanelResults.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        TxtFriendly.setBackground(new java.awt.Color(0, 153, 153));
        TxtFriendly.setColumns(20);
        TxtFriendly.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        TxtFriendly.setForeground(new java.awt.Color(255, 255, 255));
        TxtFriendly.setRows(5);
        jScrollPane4.setViewportView(TxtFriendly);

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

        BtnGroup.setBackground(new java.awt.Color(0, 102, 102));
        BtnGroup.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnGroup.setForeground(new java.awt.Color(255, 255, 255));
        BtnGroup.setText("Put Group");
        BtnGroup.setBorder(null);
        BtnGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGroupActionPerformed(evt);
            }
        });

        BtnGenGroups.setBackground(new java.awt.Color(0, 102, 102));
        BtnGenGroups.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnGenGroups.setForeground(new java.awt.Color(255, 255, 255));
        BtnGenGroups.setText("Generate Groups");
        BtnGenGroups.setBorder(null);
        BtnGenGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGenGroupsActionPerformed(evt);
            }
        });

        BtnExportClub.setBackground(new java.awt.Color(0, 102, 102));
        BtnExportClub.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnExportClub.setForeground(new java.awt.Color(255, 255, 255));
        BtnExportClub.setText("Export");
        BtnExportClub.setBorder(null);
        BtnExportClub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExportClubActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelResultsLayout = new javax.swing.GroupLayout(PanelResults);
        PanelResults.setLayout(PanelResultsLayout);
        PanelResultsLayout.setHorizontalGroup(
            PanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelResultsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelResultsLayout.createSequentialGroup()
                        .addComponent(BtnGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnExportClub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnGenGroups, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelResultsLayout.setVerticalGroup(
            PanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelResultsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnGenGroups, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnExportClub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelResults, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnFixtureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFixtureActionPerformed
        this.createFixture();
    }//GEN-LAST:event_BtnFixtureActionPerformed

    private void BtnMatchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMatchesActionPerformed
        this.playMatches();
    }//GEN-LAST:event_BtnMatchesActionPerformed

    private void BtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResetActionPerformed
        this.resetMatches();
    }//GEN-LAST:event_BtnResetActionPerformed

    private void BtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBackActionPerformed
        FrameInicio f = new FrameInicio();
        f.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnBackActionPerformed

    private void ComboTeamsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboTeamsItemStateChanged

    }//GEN-LAST:event_ComboTeamsItemStateChanged

    private void ComboTeamsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTeamsActionPerformed

    }//GEN-LAST:event_ComboTeamsActionPerformed

    private void BtnChangeTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnChangeTeamActionPerformed
        this.changeTeam();
    }//GEN-LAST:event_BtnChangeTeamActionPerformed

    private void BtnRemoveTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRemoveTeamActionPerformed
        this.removeTeam();
    }//GEN-LAST:event_BtnRemoveTeamActionPerformed

    private void BtnAddTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddTeamActionPerformed
        this.addTeam();
    }//GEN-LAST:event_BtnAddTeamActionPerformed

    private void ComboLeagueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboLeagueActionPerformed
        this.ComboTeams.removeAllItems();
        MenClubsFootballInterface footm = new MenClubsFootballInterface();
        String league = (String) this.ComboLeague.getSelectedItem();
        ArrayList<Team> aux = footm.getDatabaseValues(league);
        for (Team tm : aux) {
            if (!this.database.contains(tm)) {
                this.database.add(tm);
                this.ComboTeams.addItem(tm.getName());
            }
        }
    }//GEN-LAST:event_ComboLeagueActionPerformed

    private void BtnSoftResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSoftResetActionPerformed
        this.tableteam.clear();
        this.TableResults.setModel(new DefaultTableModel());
        this.initTableGUI();
        this.initTableResults();
        this.results.clear();
    }//GEN-LAST:event_BtnSoftResetActionPerformed

    private void BtnAddAllTeamsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddAllTeamsActionPerformed
        this.addAllTeams();
    }//GEN-LAST:event_BtnAddAllTeamsActionPerformed

    private void BtnRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRandomActionPerformed
        int numTeams = 1;
        try {
            numTeams = Integer.parseInt(JOptionPane.showInputDialog("Please input number of teams: "));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid number...", "Warning", 1);
        }
        this.modlist.clear();
        MenClubsFootballInterface foot = new MenClubsFootballInterface();
        ArrayList<Team> tempTeams = foot.getDatabaseValues();
        String sl = "";
        while (this.modlist.getSize() < numTeams) {
            int rand = (int) Math.floor(Math.random() * tempTeams.size());
            try {
                sl = foot.getClubLeagues(tempTeams.get(rand).getName());
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            this.ComboLeague.setSelectedItem(sl);
            this.modlist.addElement(tempTeams.get(rand).getName());
            tempTeams.remove(rand);
            this.ListSelectedTeams.setModel(modlist);
        }
    }//GEN-LAST:event_BtnRandomActionPerformed

    private void BtnGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGroupActionPerformed
        this.putGroup();
    }//GEN-LAST:event_BtnGroupActionPerformed

    private void BtnGenGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenGroupsActionPerformed
        this.generateGroups();
    }//GEN-LAST:event_BtnGenGroupsActionPerformed

    private void BtnExportClubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExportClubActionPerformed
        ClubsExport f = new ClubsExport();
        f.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnExportClubActionPerformed

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
            java.util.logging.Logger.getLogger(ManyMatchesClubs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManyMatchesClubs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManyMatchesClubs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManyMatchesClubs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new ManyMatchesClubs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AreaFixture;
    private javax.swing.JButton BtnAddAllTeams;
    private javax.swing.JButton BtnAddTeam;
    private javax.swing.JButton BtnBack;
    private javax.swing.JButton BtnChangeTeam;
    private javax.swing.JButton BtnExportClub;
    private javax.swing.JButton BtnFixture;
    private javax.swing.JButton BtnGenGroups;
    private javax.swing.JButton BtnGroup;
    private javax.swing.JButton BtnMatches;
    private javax.swing.JButton BtnRandom;
    private javax.swing.JButton BtnRemoveTeam;
    private javax.swing.JButton BtnReset;
    private javax.swing.JButton BtnSoftReset;
    private javax.swing.JCheckBox CheckHome;
    private javax.swing.JCheckBox CheckLocVis;
    private javax.swing.JCheckBox CheckShield;
    private javax.swing.JCheckBox CheckTE;
    private javax.swing.JComboBox<String> ComboLeague;
    private javax.swing.JComboBox<String> ComboTeams;
    private javax.swing.JList<String> ListSelectedTeams;
    private javax.swing.JPanel PanelActions;
    private javax.swing.JPanel PanelDuration;
    private javax.swing.JPanel PanelFixture;
    private javax.swing.JPanel PanelOptions;
    private javax.swing.JPanel PanelParameters;
    private javax.swing.JPanel PanelResults;
    private javax.swing.JPanel PanelTeams;
    private javax.swing.JTable TablePoints;
    private javax.swing.JTable TableResults;
    private javax.swing.JTextArea TxtFriendly;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    // End of variables declaration//GEN-END:variables
}
