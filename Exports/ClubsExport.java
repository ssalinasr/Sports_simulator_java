/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Exports;

import Main.FrameInicio;
import Modules.TournamentNode;
import Simulation.ManyMatchesClubs;
import OlympicGames.FrameEliminationClubs;
import Tools.Database.DBConsultas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Salinas
 */
public class ClubsExport extends javax.swing.JFrame {

    private ArrayList<String[]> qualifiedAsTeams = new ArrayList<>();
    private ArrayList<String[]> qualifiedAmTeams = new ArrayList<>();
    private ArrayList<String[]> qualifiedEuTeams = new ArrayList<>();

    private ArrayList<String[]> championsLeagueTeams = new ArrayList<>();
    private ArrayList<String[]> libertadoresTeams = new ArrayList<>();
    private ArrayList<String[]> afcChampionsTeams = new ArrayList<>();
    private ArrayList<String[]> worldCupTeams = new ArrayList<>();
    private ArrayList<String[]> playoffOneTeams = new ArrayList<>();
    private ArrayList<String[]> playoffTwoTeams = new ArrayList<>();

    private ArrayList<String> ascEngland = new ArrayList<>();
    private ArrayList<String> descEngland = new ArrayList<>();

    private ArrayList<String> ascSpain = new ArrayList<>();
    private ArrayList<String> descSpain = new ArrayList<>();

    private ArrayList<String> ascItaly = new ArrayList<>();
    private ArrayList<String> descItaly = new ArrayList<>();

    private ArrayList<String> ascFrance = new ArrayList<>();
    private ArrayList<String> descFrance = new ArrayList<>();

    private ManyMatchesClubs clubs_leagues;
    private ManyMatchesClubs clubs_leagues_groups;
    private FrameEliminationClubs clubs_trn;
    private FrameEliminationClubs clubs_trn_final;
    private ArrayList<DefaultTableModel> table_groups = new ArrayList<>();
    private ArrayList<String> groupNames = new ArrayList<>();
    private ArrayList<String[]> region_leagues = new ArrayList<>();
    private DBConsultas db;
    private final int numRotations = 8;
    private int radius = 20; // Tree node radius
    private int vGap = 50; // Gap between two levels in a tree

    /**
     * Creates new form ClubsExport
     */
    public ClubsExport() {
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0, 51, 51));
        setResizable(false);
        setTitle("Exportador Clubes");
        initComponents();
        this.clubs_leagues = new ManyMatchesClubs();
        this.clubs_leagues_groups = new ManyMatchesClubs();
        this.clubs_trn = new FrameEliminationClubs();
        this.clubs_trn_final = new FrameEliminationClubs();
        this.BtnGenerate.setEnabled(false);
        this.db = new DBConsultas();

    }

    public void exportTreeImage(JLabel label, String path, TournamentNode n) throws IOException {
        File out = new File("export/" + path + ".png");
        BufferedImage img = new BufferedImage(label.getWidth(), label.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g2d = img.createGraphics();
        this.displayTree(g2d, n, label.getWidth() / 2, vGap, label.getWidth() / 4);
        //label.printAll(g2d);
        g2d.dispose();
        ImageIO.write(img, "png", out);
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

    public void clearLists() {
        this.qualifiedAmTeams.clear();
        this.qualifiedAsTeams.clear();
        this.qualifiedEuTeams.clear();
        this.championsLeagueTeams.clear();
        this.libertadoresTeams.clear();
        this.afcChampionsTeams.clear();
        this.worldCupTeams.clear();
        this.playoffOneTeams.clear();
        this.playoffTwoTeams.clear();
        this.groupNames.clear();

        this.ascEngland.clear();
        this.descEngland.clear();

        this.ascSpain.clear();
        this.descSpain.clear();

        this.ascItaly.clear();
        this.descItaly.clear();

        this.ascFrance.clear();
        this.descFrance.clear();

    }

    public void exportToExcel(ArrayList<DefaultTableModel> tables, String filePath) throws FileNotFoundException, IOException {
        System.out.println(filePath);
        Workbook workbook = new HSSFWorkbook();
        Row row;
        Cell cell;

        int index = 0;

        for (DefaultTableModel mdl : tables) {
            DefaultTableModel model = mdl;
            Sheet sheet = workbook.createSheet(this.groupNames.get(index));
            // write the column headers
            row = sheet.createRow(0);
            for (int c = 0; c < model.getColumnCount(); c++) {
                cell = row.createCell(c);
                cell.setCellValue(model.getColumnName(c));
            }

            // write the data rows
            for (int r = 0; r < model.getRowCount(); r++) {
                row = sheet.createRow(r + 1);
                for (int c = 0; c < model.getColumnCount(); c++) {

                    cell = row.createCell(c);
                    Object value = model.getValueAt(r, c);
                    if (value instanceof String) {
                        cell.setCellValue((String) value);
                    } else if (value instanceof Double) {
                        cell.setCellValue((Double) value);
                    } else if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);
                    }
                }
            }
            index++;
        }

        FileOutputStream out = new FileOutputStream(filePath);
        workbook.write(out);
        out.close();
        workbook.close();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelData = new javax.swing.JPanel();
        BtnGenerate = new javax.swing.JButton();
        BtnBack = new javax.swing.JButton();
        LblPeriod = new javax.swing.JLabel();
        LblFrom = new javax.swing.JLabel();
        LblTo = new javax.swing.JLabel();
        BoxFrom = new javax.swing.JComboBox<>();
        BoxTo = new javax.swing.JComboBox<>();
        BoxPeriod = new javax.swing.JComboBox<>();
        PanelData1 = new javax.swing.JPanel();
        LblFileName = new javax.swing.JLabel();
        TxtFileName = new javax.swing.JTextField();
        LblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelData.setBackground(new java.awt.Color(0, 51, 51));
        PanelData.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        PanelData.setForeground(new java.awt.Color(0, 51, 51));

        BtnGenerate.setBackground(new java.awt.Color(0, 102, 102));
        BtnGenerate.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnGenerate.setForeground(new java.awt.Color(255, 255, 255));
        BtnGenerate.setText("Generar");
        BtnGenerate.setBorder(null);
        BtnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGenerateActionPerformed(evt);
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

        LblPeriod.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblPeriod.setForeground(new java.awt.Color(255, 255, 255));
        LblPeriod.setText("Periodo");

        LblFrom.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblFrom.setForeground(new java.awt.Color(255, 255, 255));
        LblFrom.setText("Desde");

        LblTo.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblTo.setForeground(new java.awt.Color(255, 255, 255));
        LblTo.setText("Hasta");

        BoxFrom.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BoxFrom.setForeground(new java.awt.Color(255, 255, 255));
        BoxFrom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BoxFromItemStateChanged(evt);
            }
        });
        BoxFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxFromActionPerformed(evt);
            }
        });

        BoxTo.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BoxTo.setForeground(new java.awt.Color(255, 255, 255));
        BoxTo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BoxToItemStateChanged(evt);
            }
        });
        BoxTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxToActionPerformed(evt);
            }
        });

        BoxPeriod.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BoxPeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "4" }));
        BoxPeriod.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BoxPeriodItemStateChanged(evt);
            }
        });
        BoxPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxPeriodActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDataLayout = new javax.swing.GroupLayout(PanelData);
        PanelData.setLayout(PanelDataLayout);
        PanelDataLayout.setHorizontalGroup(
            PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblPeriod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelDataLayout.createSequentialGroup()
                        .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(BoxFrom, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LblFrom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LblTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BoxTo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(BoxPeriod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        PanelDataLayout.setVerticalGroup(
            PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDataLayout.createSequentialGroup()
                        .addComponent(LblPeriod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BoxPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblFrom)
                            .addComponent(LblTo)))
                    .addGroup(PanelDataLayout.createSequentialGroup()
                        .addComponent(BtnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BoxFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BoxTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelData1.setBackground(new java.awt.Color(0, 51, 51));
        PanelData1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        PanelData1.setForeground(new java.awt.Color(0, 51, 51));

        LblFileName.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblFileName.setForeground(new java.awt.Color(255, 255, 255));
        LblFileName.setText("Nombre del Archivo");

        LblStatus.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblStatus.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PanelData1Layout = new javax.swing.GroupLayout(PanelData1);
        PanelData1.setLayout(PanelData1Layout);
        PanelData1Layout.setHorizontalGroup(
            PanelData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelData1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LblFileName, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addComponent(TxtFileName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelData1Layout.setVerticalGroup(
            PanelData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelData1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelData1Layout.createSequentialGroup()
                        .addComponent(LblFileName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelData1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelData1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerateActionPerformed

        //First Stage
        int from_year = Integer.parseInt((String) this.BoxFrom.getSelectedItem());
        int to_year = Integer.parseInt((String) this.BoxTo.getSelectedItem());
        int period = Integer.parseInt((String) this.BoxPeriod.getSelectedItem());
        //ArrayList<String> leagues = this.clubs_leagues.getComboLeagueElements();

        try {
            ResultSet rs = db.getClubsLand();
            while (rs.next()) {
                String[] lg = new String[2];
                lg[0] = rs.getString("team_league");
                lg[1] = rs.getString("team_region");
                this.region_leagues.add(lg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubsExport.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int p = from_year; p <= to_year; p += period) {
            this.table_groups.clear();
            this.clearLists();
            this.clubs_leagues.setShieldSelected(true);
            this.clubs_leagues.setHomeSelected(true);
            for (String[] lg : this.region_leagues) {
                this.clubs_leagues.setComboLeagueValue(lg[0]);
                this.clubs_leagues.addAllTeams();
                this.clubs_leagues.createFixture();
                this.clubs_leagues.playMatches();
                this.groupNames.add(lg[0]);

                JTable tblpts = this.clubs_leagues.getSimTable();
                RowSorter<? extends TableModel> rwstr = this.clubs_leagues.getSimTableSorter();
                tblpts.setRowSorter(rwstr);

                DefaultTableModel fixed_model = new DefaultTableModel();
                fixed_model.setColumnCount(tblpts.getColumnCount());
                fixed_model.setRowCount(tblpts.getRowCount());

                for (int r = 0; r < tblpts.getRowCount(); r++) {
                    String team = (String) tblpts.getValueAt(r, 0);
                    if (lg[1].equals("America")) {

                        switch (lg[0]) {
                            case "Brasileirao Assai":
                            case "Liga Argentina":
                                if (r < 5) {
                                    String[] pf_team = new String[2];
                                    pf_team[0] = team;
                                    pf_team[1] = lg[0];
                                    this.qualifiedAmTeams.add(pf_team);
                                }
                                break;
                            case "Liga Betplay Dimayor":
                            case "Primera División Uruguaya":
                                if (r < 3) {
                                    String[] pf_team = new String[2];
                                    pf_team[0] = team;
                                    pf_team[1] = lg[0];
                                    this.qualifiedAmTeams.add(pf_team);
                                }
                                break;
                            default:
                                if (r < 2) {
                                    String[] pf_team = new String[2];
                                    pf_team[0] = team;
                                    pf_team[1] = lg[0];
                                    this.qualifiedAmTeams.add(pf_team);
                                }
                        }
                    }

                    if (lg[1].equals("Europa")) {
                        switch (lg[0]) {
                            case "Premier League":
                                if (r < 2) {
                                    String[] pf_team = new String[2];
                                    pf_team[0] = team;
                                    pf_team[1] = lg[0];
                                    this.qualifiedEuTeams.add(pf_team);
                                }
                                if (r > 16 && r < 20) {
                                    this.descEngland.add(team);
                                }
                                break;
                            case "Serie A":
                                if (r < 2) {
                                    String[] pf_team = new String[2];
                                    pf_team[0] = team;
                                    pf_team[1] = lg[0];
                                    this.qualifiedEuTeams.add(pf_team);
                                }
                                if (r > 16 && r < 20) {
                                    this.descItaly.add(team);
                                }
                                break;
                            case "LaLiga Santander":
                                if (r < 2) {
                                    String[] pf_team = new String[2];
                                    pf_team[0] = team;
                                    pf_team[1] = lg[0];
                                    this.qualifiedEuTeams.add(pf_team);
                                }
                                if (r > 16 && r < 20) {
                                    this.descSpain.add(team);
                                }
                                break;
                            case "Ligue 1":
                                if (r < 2) {
                                    String[] pf_team = new String[2];
                                    pf_team[0] = team;
                                    pf_team[1] = lg[0];
                                    this.qualifiedEuTeams.add(pf_team);
                                }
                                if (r > 16 && r < 20) {
                                    this.descFrance.add(team);
                                }
                                break;
                            case "Eredivisie":
                            case "Liga Portugal":
                            case "Bundesliga":
                                if (r < 2) {
                                    String[] pf_team = new String[2];
                                    pf_team[0] = team;
                                    pf_team[1] = lg[0];
                                    this.qualifiedEuTeams.add(pf_team);
                                }
                                break;
                            case "Serie B":
                                if (r < 3) {
                                    this.ascItaly.add(team);
                                }
                                break;
                            case "EFL Championship":
                                if (r < 3) {
                                    this.ascEngland.add(team);
                                }
                                break;
                            case "LaLiga Smartbank":
                                if (r < 3) {
                                    this.ascSpain.add(team);
                                }
                                break;
                            case "Ligue 2":
                                if (r < 3) {
                                    this.ascFrance.add(team);
                                }
                                break;

                            case "Armenian Premier League":
                            case "Azerbaijan Premier League":
                            case "First Professional Football League BGR":
                            case "Moldovan Super Liga":
                            case "Kazakhstan Premier League":
                            case "Cypriot First Division":
                            case "Uzbekistan Super League":
                                if (r == 0) {
                                    String[] pf_team = new String[2];
                                    pf_team[0] = team;
                                    pf_team[1] = lg[0];
                                    this.playoffOneTeams.add(pf_team);
                                }
                                break;
                            case "Superliga ROU":
                            case "Veikkausliiga FIN":
                            case "Meirstiliiga EST":
                            case "A Lyga LTU":
                            case "Allsvenskan SWE":
                            case "Prva Liga SVN":
                                if (r == 0) {
                                    String[] pf_team = new String[2];
                                    pf_team[0] = team;
                                    pf_team[1] = lg[0];
                                    this.playoffTwoTeams.add(pf_team);
                                }
                                break;
                            default:
                                if (r == 0) {
                                    String[] pf_team = new String[2];
                                    pf_team[0] = team;
                                    pf_team[1] = lg[0];
                                    this.qualifiedEuTeams.add(pf_team);
                                }

                        }
                    }

                    if (lg[1].equals("Asia")) {
                        switch (lg[0]) {
                            case "J1 League":
                            case "K1 League":
                            case "Chinese Super League":
                                if (r < 4) {
                                    String[] pf_team = new String[2];
                                    pf_team[0] = team;
                                    pf_team[1] = lg[0];
                                    this.qualifiedAsTeams.add(pf_team);
                                }
                                break;
                            case "Persian Gulf Pro League":
                            case "Saudi Pro League":
                                if (r < 3) {
                                    String[] pf_team = new String[2];
                                    pf_team[0] = team;
                                    pf_team[1] = lg[0];
                                    this.qualifiedAsTeams.add(pf_team);
                                }
                                break;
                            case "Qatar Stars League":
                            case "A-League":
                            case "UAE Pro League":
                            case "Thai League 1":
                                if (r < 2) {
                                    String[] pf_team = new String[2];
                                    pf_team[0] = team;
                                    pf_team[1] = lg[0];
                                    this.qualifiedAsTeams.add(pf_team);
                                }
                                break;
                            default:
                                if (r == 0) {
                                    String[] pf_team = new String[2];
                                    pf_team[0] = team;
                                    pf_team[1] = lg[0];
                                    this.qualifiedAsTeams.add(pf_team);
                                }
                        }
                    }

                    for (int c = 0; c < tblpts.getColumnCount(); c++) {
                        fixed_model.setValueAt(tblpts.getValueAt(r, c), r, c);
                        System.out.print(tblpts.getValueAt(r, c) + " ");
                    }
                    System.out.println("");
                }
                this.table_groups.add(fixed_model);
                //this.table_results.add(curr_res_table);
                this.clubs_leagues.resetMatches();

            }

            //playoff One
            for (String[] pfo : this.playoffOneTeams) {
                this.clubs_leagues.setComboLeagueValue(pfo[1]);
                this.clubs_leagues.setComboTeamsValue(pfo[0]);
                this.clubs_leagues.addTeam();
            }
            this.clubs_leagues.createFixture();
            this.clubs_leagues.playMatches();
            this.groupNames.add("PlayOff 1");

            JTable tblpts = this.clubs_leagues.getSimTable();
            RowSorter<? extends TableModel> rwstr = this.clubs_leagues.getSimTableSorter();
            tblpts.setRowSorter(rwstr);

            DefaultTableModel fixed_model = new DefaultTableModel();
            fixed_model.setColumnCount(tblpts.getColumnCount());
            fixed_model.setRowCount(tblpts.getRowCount());

            for (int r = 0; r < tblpts.getRowCount(); r++) {
                String team = (String) tblpts.getValueAt(r, 0);
                String league = "";

                for (String[] tt : this.playoffOneTeams) {
                    if (tt[0] == team) {
                        league = tt[1];
                        break;
                    }
                }

                if (r == 0) {
                    String[] pf_team = new String[2];
                    pf_team[0] = team;
                    pf_team[1] = league;
                    this.qualifiedEuTeams.add(pf_team);
                }
                for (int c = 0; c < tblpts.getColumnCount(); c++) {
                    fixed_model.setValueAt(tblpts.getValueAt(r, c), r, c);
                    System.out.print(tblpts.getValueAt(r, c) + " ");
                }
                System.out.println("");
            }

            this.table_groups.add(fixed_model);
            this.clubs_leagues.resetMatches();

            //playoff Two
            for (String[] pfo : this.playoffTwoTeams) {
                this.clubs_leagues.setComboLeagueValue(pfo[1]);
                this.clubs_leagues.setComboTeamsValue(pfo[0]);
                this.clubs_leagues.addTeam();
            }
            this.clubs_leagues.createFixture();
            this.clubs_leagues.playMatches();
            this.groupNames.add("PlayOff 2");

            JTable tblpts2 = this.clubs_leagues.getSimTable();
            RowSorter<? extends TableModel> rwstr2 = this.clubs_leagues.getSimTableSorter();
            tblpts.setRowSorter(rwstr2);

            DefaultTableModel fixed_model2 = new DefaultTableModel();
            fixed_model2.setColumnCount(tblpts2.getColumnCount());
            fixed_model2.setRowCount(tblpts2.getRowCount());

            for (int r = 0; r < tblpts2.getRowCount(); r++) {
                String team = (String) tblpts2.getValueAt(r, 0);
                String league = "";

                for (String[] tt : this.playoffTwoTeams) {
                    if (tt[0] == team) {
                        league = tt[1];
                        break;
                    }
                }

                if (r == 0) {
                    String[] pf_team = new String[2];
                    pf_team[0] = team;
                    pf_team[1] = league;
                    this.qualifiedEuTeams.add(pf_team);
                }
                for (int c = 0; c < tblpts2.getColumnCount(); c++) {
                    fixed_model2.setValueAt(tblpts2.getValueAt(r, c), r, c);
                    System.out.print(tblpts2.getValueAt(r, c) + " ");
                }
                System.out.println("");
            }

            this.table_groups.add(fixed_model2);
            this.clubs_leagues.resetMatches();

            //Checking
            for (String[] eu_ck : this.qualifiedEuTeams) {
                if (eu_ck[1].equals("EFL Championship") || eu_ck[1].equals("Ligue 2")
                        || eu_ck[1].equals("Serie B") || eu_ck[1].equals("LaLiga Smartbank")) {
                    this.qualifiedEuTeams.remove(eu_ck);
                    break;
                }
            }

            for (String[] eu : this.qualifiedEuTeams) {
                System.out.print(eu[0] + ", ");
            }
            System.out.println("");
            for (String[] am : this.qualifiedAmTeams) {
                System.out.print(am[0] + ", ");
            }
            System.out.println("");
            for (String[] as : this.qualifiedAsTeams) {
                System.out.print(as[0] + ", ");
            }
            System.out.println("");

            //Copa Libertadores
            for (String[] am : this.qualifiedAmTeams) {
                this.clubs_leagues_groups.setComboLeagueValue(am[1]);
                this.clubs_leagues_groups.setComboTeamsValue(am[0]);
                this.clubs_leagues_groups.addTeam();
            }
            this.clubs_leagues_groups.generateGroups();

            for (int l = 0; l < this.numRotations; l++) {
                this.clubs_leagues_groups.putGroup();
                this.clubs_leagues_groups.createFixture();
                this.clubs_leagues_groups.playMatches();
                this.groupNames.add("Libertadores " + (l + 1));
                JTable tblpts_gp = this.clubs_leagues_groups.getSimTable();
                RowSorter<? extends TableModel> rwstr_gp = this.clubs_leagues_groups.getSimTableSorter();
                tblpts_gp.setRowSorter(rwstr_gp);

                DefaultTableModel fixed_model_gp = new DefaultTableModel();
                fixed_model_gp.setColumnCount(tblpts_gp.getColumnCount());
                fixed_model_gp.setRowCount(tblpts_gp.getRowCount());

                for (int r = 0; r < fixed_model_gp.getRowCount(); r++) {

                    if (r < 2) {
                        String team = (String) tblpts_gp.getValueAt(r, 0);
                        String league = "";

                        for (String[] tt : this.qualifiedAmTeams) {
                            if (tt[0].equals(team)) {
                                league = tt[1];
                                break;
                            }
                        }

                        String[] lb_tm = new String[2];
                        lb_tm[0] = team;
                        lb_tm[1] = league;

                        this.libertadoresTeams.add(lb_tm);
                    }

                    for (int c = 0; c < fixed_model_gp.getColumnCount(); c++) {
                        fixed_model_gp.setValueAt(tblpts_gp.getValueAt(r, c), r, c);
                        System.out.print(tblpts_gp.getValueAt(r, c) + " ");
                    }
                    System.out.println("");
                }

                this.table_groups.add(fixed_model_gp);
                this.clubs_leagues_groups.resetMatches();
            }

            //UEFA Champions League
            for (String[] eu : this.qualifiedEuTeams) {
                this.clubs_leagues_groups.setComboLeagueValue(eu[1]);
                this.clubs_leagues_groups.setComboTeamsValue(eu[0]);
                this.clubs_leagues_groups.addTeam();
            }
            this.clubs_leagues_groups.generateGroups();

            for (int l = 0; l < this.numRotations; l++) {
                this.clubs_leagues_groups.putGroup();
                this.clubs_leagues_groups.createFixture();
                this.clubs_leagues_groups.playMatches();
                this.groupNames.add("UEFA Champions League " + (l + 1));
                JTable tblpts_gp = this.clubs_leagues_groups.getSimTable();
                RowSorter<? extends TableModel> rwstr_gp = this.clubs_leagues_groups.getSimTableSorter();
                tblpts_gp.setRowSorter(rwstr_gp);

                DefaultTableModel fixed_model_gp = new DefaultTableModel();
                fixed_model_gp.setColumnCount(tblpts_gp.getColumnCount());
                fixed_model_gp.setRowCount(tblpts_gp.getRowCount());

                for (int r = 0; r < fixed_model_gp.getRowCount(); r++) {

                    if (r < 2) {
                        String team = (String) tblpts_gp.getValueAt(r, 0);
                        String league = "";

                        for (String[] tt : this.qualifiedEuTeams) {
                            if (tt[0].equals(team)) {
                                league = tt[1];
                                break;
                            }
                        }

                        String[] eu_tm = new String[2];
                        eu_tm[0] = team;
                        eu_tm[1] = league;

                        this.championsLeagueTeams.add(eu_tm);
                    }
                    for (int c = 0; c < fixed_model_gp.getColumnCount(); c++) {
                        fixed_model_gp.setValueAt(tblpts_gp.getValueAt(r, c), r, c);
                        System.out.print(tblpts_gp.getValueAt(r, c) + " ");
                    }
                    System.out.println("");
                }

                this.table_groups.add(fixed_model_gp);
                this.clubs_leagues_groups.resetMatches();
            }
            //AFC Champions League
            for (String[] as : this.qualifiedAsTeams) {
                this.clubs_leagues_groups.setComboLeagueValue(as[1]);
                this.clubs_leagues_groups.setComboTeamsValue(as[0]);
                this.clubs_leagues_groups.addTeam();
            }
            this.clubs_leagues_groups.generateGroups();

            for (int l = 0; l < this.numRotations; l++) {
                this.clubs_leagues_groups.putGroup();
                this.clubs_leagues_groups.createFixture();
                this.clubs_leagues_groups.playMatches();
                this.groupNames.add("AFC Champions League " + (l + 1));
                JTable tblpts_gp = this.clubs_leagues_groups.getSimTable();
                RowSorter<? extends TableModel> rwstr_gp = this.clubs_leagues_groups.getSimTableSorter();
                tblpts_gp.setRowSorter(rwstr_gp);

                DefaultTableModel fixed_model_gp = new DefaultTableModel();
                fixed_model_gp.setColumnCount(tblpts_gp.getColumnCount());
                fixed_model_gp.setRowCount(tblpts_gp.getRowCount());

                for (int r = 0; r < fixed_model_gp.getRowCount(); r++) {
                    if (r < 2) {
                        String team = (String) tblpts_gp.getValueAt(r, 0);
                        String league = "";

                        for (String[] tt : this.qualifiedAsTeams) {
                            if (tt[0].equals(team)) {
                                league = tt[1];
                                break;
                            }
                        }

                        String[] as_tm = new String[2];
                        as_tm[0] = team;
                        as_tm[1] = league;

                        this.afcChampionsTeams.add(as_tm);
                    }
                    for (int c = 0; c < fixed_model_gp.getColumnCount(); c++) {
                        fixed_model_gp.setValueAt(tblpts_gp.getValueAt(r, c), r, c);
                        System.out.print(tblpts_gp.getValueAt(r, c) + " ");
                    }
                    System.out.println("");
                }

                this.table_groups.add(fixed_model_gp);
                this.clubs_leagues_groups.resetMatches();
            }

            //Final Stages
            this.clubs_trn.setNumberTeams("16");
            JTable tblpts_fin;
            RowSorter<? extends TableModel> rwstr_fin;
            DefaultTableModel fixed_model_fin;

            //Copa Libertadores
            this.groupNames.add("Final Copa Libertadores");
            for (String[] lbt : this.libertadoresTeams) {
                this.clubs_trn.setComboLeagueValue(lbt[1]);
                this.clubs_trn.setComboTeamsValue(lbt[0]);
                this.clubs_trn.addTeam();
            }
            this.clubs_trn.playMatches();
            this.clubs_trn.generateTree();
            tblpts_fin = this.clubs_trn.getSimTable();
            rwstr_fin = this.clubs_trn.getSimTableSorter();
            tblpts_fin.setRowSorter(rwstr_fin);
            fixed_model_fin = new DefaultTableModel();
            fixed_model_fin.setColumnCount(tblpts_fin.getColumnCount());
            fixed_model_fin.setRowCount(tblpts_fin.getRowCount());

            for (int r = 0; r < fixed_model_fin.getRowCount(); r++) {
                String team = (String) tblpts_fin.getValueAt(r, 0);
                String league = "";
                if (r >= 0 && r < 5) {
                    for (String[] tt : this.libertadoresTeams) {
                        if (tt[0].equals(team)) {
                            league = tt[1];
                            break;
                        }
                    }
                    String[] wc_team = new String[2];
                    wc_team[0] = team;
                    wc_team[1] = league;
                    this.worldCupTeams.add(wc_team);
                }

                for (int c = 0; c < fixed_model_fin.getColumnCount(); c++) {
                    fixed_model_fin.setValueAt(tblpts_fin.getValueAt(r, c), r, c);
                    System.out.print(tblpts_fin.getValueAt(r, c) + " ");
                }
                System.out.println("");
            }

            JLabel lb_team_tree = this.clubs_trn.getTreeLabel();
            TournamentNode lb_tree = this.clubs_trn.getTreeGraphics();
            {
                try {
                    this.exportTreeImage(lb_team_tree, "Final Copa Libertadores" + p, lb_tree);
                } catch (IOException ex) {
                    System.out.println("Copa Libertadores not created...");
                }
            }
            this.table_groups.add(fixed_model_fin);
            this.clubs_trn.resetMatches();

            //UEFA Champions League
            this.groupNames.add("Final UEFA Champions League");
            for (String[] ucl : this.championsLeagueTeams) {
                this.clubs_trn.setComboLeagueValue(ucl[1]);
                this.clubs_trn.setComboTeamsValue(ucl[0]);
                this.clubs_trn.addTeam();
            }
            this.clubs_trn.playMatches();
            this.clubs_trn.generateTree();
            tblpts_fin = this.clubs_trn.getSimTable();
            rwstr_fin = this.clubs_trn.getSimTableSorter();
            tblpts_fin.setRowSorter(rwstr_fin);
            fixed_model_fin = new DefaultTableModel();
            fixed_model_fin.setColumnCount(tblpts_fin.getColumnCount());
            fixed_model_fin.setRowCount(tblpts_fin.getRowCount());

            for (int r = 0; r < fixed_model_fin.getRowCount(); r++) {
                String team = (String) tblpts_fin.getValueAt(r, 0);
                String league = "";
                if (r >= 0 && r < 6) {
                    for (String[] tt : this.championsLeagueTeams) {
                        if (tt[0].equals(team)) {
                            league = tt[1];
                            break;
                        }
                    }
                    String[] wc_team = new String[2];
                    wc_team[0] = team;
                    wc_team[1] = league;
                    this.worldCupTeams.add(wc_team);
                }

                for (int c = 0; c < fixed_model_fin.getColumnCount(); c++) {
                    fixed_model_fin.setValueAt(tblpts_fin.getValueAt(r, c), r, c);
                    System.out.print(tblpts_fin.getValueAt(r, c) + " ");
                }
                System.out.println("");
            }

            JLabel ucl_team_tree = this.clubs_trn.getTreeLabel();
            TournamentNode ucl_tree = this.clubs_trn.getTreeGraphics();
            {
                try {
                    this.exportTreeImage(ucl_team_tree, "Final UEFA Champions League" + p, ucl_tree);
                } catch (IOException ex) {
                    System.out.println("Final UEFA Champions League not created...");
                }
            }
            this.table_groups.add(fixed_model_fin);
            this.clubs_trn.resetMatches();

            //AFC Champions League
            this.groupNames.add("AFC Champions League");
            for (String[] lbt : this.afcChampionsTeams) {
                this.clubs_trn.setComboLeagueValue(lbt[1]);
                this.clubs_trn.setComboTeamsValue(lbt[0]);
                this.clubs_trn.addTeam();
            }
            this.clubs_trn.playMatches();
            this.clubs_trn.generateTree();
            tblpts_fin = this.clubs_trn.getSimTable();
            rwstr_fin = this.clubs_trn.getSimTableSorter();
            tblpts_fin.setRowSorter(rwstr_fin);
            fixed_model_fin = new DefaultTableModel();
            fixed_model_fin.setColumnCount(tblpts_fin.getColumnCount());
            fixed_model_fin.setRowCount(tblpts_fin.getRowCount());

            for (int r = 0; r < fixed_model_fin.getRowCount(); r++) {
                String team = (String) tblpts_fin.getValueAt(r, 0);
                String league = "";
                if (r >= 0 && r < 5) {
                    for (String[] tt : this.afcChampionsTeams) {
                        if (tt[0].equals(team)) {
                            league = tt[1];
                            break;
                        }
                    }
                    String[] wc_team = new String[2];
                    wc_team[0] = team;
                    wc_team[1] = league;
                    this.worldCupTeams.add(wc_team);
                }

                for (int c = 0; c < fixed_model_fin.getColumnCount(); c++) {
                    fixed_model_fin.setValueAt(tblpts_fin.getValueAt(r, c), r, c);
                    System.out.print(tblpts_fin.getValueAt(r, c) + " ");
                }
                System.out.println("");
            }

            JLabel as_team_tree = this.clubs_trn.getTreeLabel();
            TournamentNode as_tree = this.clubs_trn.getTreeGraphics();
            {
                try {
                    this.exportTreeImage(as_team_tree, "Final AFC Champions League" + p, as_tree);
                } catch (IOException ex) {
                    System.out.println("AFC Champions League not created...");
                }
            }
            this.table_groups.add(fixed_model_fin);
            this.clubs_trn.resetMatches();

            //World Clubs Final
            this.groupNames.add("Mundial de Clubes");
            for (String[] lbt : this.worldCupTeams) {
                this.clubs_trn.setComboLeagueValue(lbt[1]);
                this.clubs_trn.setComboTeamsValue(lbt[0]);
                this.clubs_trn.addTeam();
            }
            this.clubs_trn.playMatches();
            this.clubs_trn.generateTree();
            tblpts_fin = this.clubs_trn.getSimTable();
            rwstr_fin = this.clubs_trn.getSimTableSorter();
            tblpts_fin.setRowSorter(rwstr_fin);
            fixed_model_fin = new DefaultTableModel();
            fixed_model_fin.setColumnCount(tblpts_fin.getColumnCount());
            fixed_model_fin.setRowCount(tblpts_fin.getRowCount());

            for (int r = 0; r < fixed_model_fin.getRowCount(); r++) {
                for (int c = 0; c < fixed_model_fin.getColumnCount(); c++) {
                    fixed_model_fin.setValueAt(tblpts_fin.getValueAt(r, c), r, c);
                    System.out.print(tblpts_fin.getValueAt(r, c) + " ");
                }
                System.out.println("");
            }

            JLabel wc_team_tree = this.clubs_trn.getTreeLabel();
            TournamentNode wc_tree = this.clubs_trn.getTreeGraphics();
            {
                try {
                    this.exportTreeImage(wc_team_tree, "Final Mundial de Clubes" + p, wc_tree);
                } catch (IOException ex) {
                    System.out.println("Mundial de Clubes not created...");
                }
            }
            this.table_groups.add(fixed_model_fin);
            this.clubs_trn.resetMatches();

            for (String[] lb : this.libertadoresTeams) {
                System.out.print(lb[0] + ", ");
            }
            System.out.println("");
            for (String[] cl : this.championsLeagueTeams) {
                System.out.print(cl[0] + ", ");
            }
            System.out.println("");
            for (String[] acl : this.afcChampionsTeams) {
                System.out.print(acl[0] + ", ");
            }
            System.out.println("");
            for (String[] wcl : this.worldCupTeams) {
                System.out.print(wcl[0] + ", ");
            }
            System.out.println("|");
            String fileName = this.TxtFileName.getText();
            if (fileName.isEmpty()) {
                fileName = "dumptest";
            }
            String filePath = "export/" + fileName + p + ".xls";

            try {
                this.exportToExcel(this.table_groups, filePath);
                this.LblStatus.setText("El archivo " + fileName + p + " ha sido generado...");
            } catch (IOException ex) {
                this.LblStatus.setText("El archivo no ha sido generado...");
            }
            System.out.println("Asc: ");
            for (String wcl : this.ascEngland) {
                System.out.print(wcl + ", ");
            }
            System.out.println("");
            for (String wcl : this.ascFrance) {
                System.out.print(wcl + ", ");
            }
            System.out.println("");
            for (String wcl : this.ascSpain) {
                System.out.print(wcl + ", ");
            }
            System.out.println("");
            for (String wcl : this.ascItaly) {
                System.out.print(wcl + ", ");
            }
            System.out.println("Desc:");
            for (String wcl : this.descEngland) {
                System.out.print(wcl + ", ");
            }
            System.out.println("");
            for (String wcl : this.descFrance) {
                System.out.print(wcl + ", ");
            }
            System.out.println("");
            for (String wcl : this.descSpain) {
                System.out.print(wcl + ", ");
            }
            System.out.println("");
            for (String wcl : this.descItaly) {
                System.out.print(wcl + ", ");
            }

            //Update Leagues...
            DBConsultas addb = new DBConsultas();
            for (String dsc : this.descEngland) {
                try {
                    addb.updateClubLeague("EFL Championship", dsc);
                } catch (SQLException ex) {
                    System.out.println("Couldn't update asc/desc teams");
                    System.out.println(ex);
                }
            }

            for (String dsc : this.descItaly) {
                try {
                    addb.updateClubLeague("Serie B", dsc);
                } catch (SQLException ex) {
                    System.out.println("Couldn't update asc/desc teams");
                    System.out.println(ex);
                }
            }

            for (String dsc : this.descSpain) {
                try {
                    addb.updateClubLeague("LaLiga Smartbank", dsc);
                } catch (SQLException ex) {
                    System.out.println("Couldn't update asc/desc teams");
                    System.out.println(ex);
                }
            }

            for (String dsc : this.descFrance) {
                try {
                    addb.updateClubLeague("Ligue 2", dsc);
                } catch (SQLException ex) {
                    System.out.println("Couldn't update asc/desc teams");
                    System.out.println(ex);
                }
            }

            for (String dsc : this.ascEngland) {
                try {
                    addb.updateClubLeague("Premier League", dsc);
                } catch (SQLException ex) {
                    System.out.println("Couldn't update asc/desc teams");
                    System.out.println(ex);
                }
            }

            for (String dsc : this.ascItaly) {
                try {
                    addb.updateClubLeague("Serie A", dsc);
                } catch (SQLException ex) {
                    System.out.println("Couldn't update asc/desc teams");
                    System.out.println(ex);
                }
            }

            for (String dsc : this.ascSpain) {
                try {
                    addb.updateClubLeague("LaLiga Santander", dsc);
                } catch (SQLException ex) {
                    System.out.println("Couldn't update asc/desc teams");
                    System.out.println(ex);
                }
            }

            for (String dsc : this.ascFrance) {
                try {
                    addb.updateClubLeague("Ligue 1", dsc);
                } catch (SQLException ex) {
                    System.out.println("Couldn't update asc/desc teams");
                    System.out.println(ex);
                }
            }

        }


    }//GEN-LAST:event_BtnGenerateActionPerformed

    private void BtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBackActionPerformed
        FrameInicio f = new FrameInicio();
        f.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnBackActionPerformed

    private void BoxFromItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BoxFromItemStateChanged
        int value = Integer.parseInt((String) this.BoxPeriod.getSelectedItem());
        int curr_time = Integer.parseInt((String) this.BoxFrom.getSelectedItem());
        this.BtnGenerate.setEnabled(true);
        this.BoxTo.removeAllItems();
        switch (value) {
            case 1:
                for (int i = curr_time; i < 2200; i++) {
                    this.BoxTo.addItem(i + "");
                }
                break;
            case 2:
                for (int i = curr_time; i < 2200; i += 2) {
                    this.BoxTo.addItem(i + "");
                }
                break;
            case 4:
                for (int i = curr_time; i < 2200; i += 4) {
                    this.BoxTo.addItem(i + "");
                }
                break;
            default:
                for (int i = curr_time; i < 2200; i++) {
                    this.BoxTo.addItem(i + "");
                }
                break;
        }
    }//GEN-LAST:event_BoxFromItemStateChanged

    private void BoxFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxFromActionPerformed

    }//GEN-LAST:event_BoxFromActionPerformed

    private void BoxToItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BoxToItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxToItemStateChanged

    private void BoxToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxToActionPerformed

    }//GEN-LAST:event_BoxToActionPerformed

    private void BoxPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxPeriodActionPerformed
        int value = Integer.parseInt((String) this.BoxPeriod.getSelectedItem());
        this.BoxFrom.removeAllItems();
        switch (value) {
            case 1:
                for (int i = 1880; i < 2200; i++) {
                    this.BoxFrom.addItem(i + "");
                }
                break;
            case 2:
                for (int i = 1880; i < 2200; i += 2) {
                    this.BoxFrom.addItem(i + "");
                }
                break;
            case 4:
                for (int i = 1880; i < 2200; i += 4) {
                    this.BoxFrom.addItem(i + "");
                }
                break;
            default:
                for (int i = 1880; i < 2200; i++) {
                    this.BoxFrom.addItem(i + "");
                }
                break;
        }
    }//GEN-LAST:event_BoxPeriodActionPerformed

    private void BoxPeriodItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BoxPeriodItemStateChanged

    }//GEN-LAST:event_BoxPeriodItemStateChanged

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
            java.util.logging.Logger.getLogger(ClubsExport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClubsExport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClubsExport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClubsExport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClubsExport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BoxFrom;
    private javax.swing.JComboBox<String> BoxPeriod;
    private javax.swing.JComboBox<String> BoxTo;
    private javax.swing.JButton BtnBack;
    private javax.swing.JButton BtnGenerate;
    private javax.swing.JLabel LblFileName;
    private javax.swing.JLabel LblFrom;
    private javax.swing.JLabel LblPeriod;
    private javax.swing.JLabel LblStatus;
    private javax.swing.JLabel LblTo;
    private javax.swing.JPanel PanelData;
    private javax.swing.JPanel PanelData1;
    private javax.swing.JTextField TxtFileName;
    // End of variables declaration//GEN-END:variables
}
