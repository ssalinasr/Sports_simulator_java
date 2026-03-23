/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Exports;

import Main.FrameInicio;
import Modules.TableTeam;
import Modules.TournamentNode;
import OlympicGames.FrameEliminationSports;
import OtherGames.FrameEliminationGames;
import Simulation.ManyMatchesGames;
import Simulation.ManyMatchesSports;
import Simulation.OlympicSimulation;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Salinas
 */
public class GamesExport extends javax.swing.JFrame {

    /**
     * Creates new form SportsLeaguesExport
     */
    private ManyMatchesGames sport_sim;
    private FrameEliminationGames sport_trn;

    private ArrayList<String> ssb_list = new ArrayList<String>() {
        {
            add("SSB-Time");
            add("SSB-Lives");
            add("SSB-Coins");
            add("SSB-Stamina");
            add("SSB-SingleMode");
            add("SSB-SuddenDeath");
            add("SSB-LightningMode");
        }
    };

    private ArrayList<String> ge_list = new ArrayList<String>() {
        {
            add("GE-Time");
            add("GE-Lives");
            add("GE-License to Kill");
            add("GE-SSDV");
        }
    };

    private ArrayList<String> mk_list = new ArrayList<String>() {
        {
            add("MK-Battles");
        }
    };

    private ArrayList<DefaultTableModel> table_groups = new ArrayList<>();

//    private ArrayList<DefaultTableModel> table_groups = new ArrayList<>();
    private int numRotations = 32;

    private ArrayList<String> qualifiedTeams_SSBLV = new ArrayList<>();
    private ArrayList<String> qualifiedTeams_SSBTM = new ArrayList<>();
    private ArrayList<String> qualifiedTeams_SSBCN = new ArrayList<>();
    private ArrayList<String> qualifiedTeams_SSBST = new ArrayList<>();
    private ArrayList<String> qualifiedTeams_SSBLM = new ArrayList<>();
    private ArrayList<String> qualifiedTeams_SSBSD = new ArrayList<>();
    private ArrayList<String> qualifiedTeams_SSBSM = new ArrayList<>();

    private ArrayList<String> qualifiedTeams_GELV = new ArrayList<>();
    private ArrayList<String> qualifiedTeams_GETM = new ArrayList<>();
    private ArrayList<String> qualifiedTeams_GELM = new ArrayList<>();
    private ArrayList<String> qualifiedTeams_GESSDV = new ArrayList<>();
    private ArrayList<String> qualifiedTeams_GETMS = new ArrayList<>();

    private ArrayList<String> qualifiedGroupsTeams_SSBLV = new ArrayList<>();
    private ArrayList<String> qualifiedGroupsTeams_SSBTM = new ArrayList<>();
    private ArrayList<String> qualifiedGroupsTeams_SSBCN = new ArrayList<>();
    private ArrayList<String> qualifiedGroupsTeams_SSBST = new ArrayList<>();
    private ArrayList<String> qualifiedGroupsTeams_SSBSM = new ArrayList<>();
    private ArrayList<String> qualifiedGroupsTeams_SSBSD = new ArrayList<>();
    private ArrayList<String> qualifiedGroupsTeams_SSBLM = new ArrayList<>();
    private ArrayList<String> groupNames = new ArrayList<>();

    private int radius = 20; // Tree node radius
    private int vGap = 50; // Gap between two levels in a tree

    public GamesExport() {
        initComponents();
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0, 51, 51));
        setResizable(false);
        setTitle("Exportador Juegos Juegos Olimpicos");
        this.sport_sim = new ManyMatchesGames();
        this.sport_trn = new FrameEliminationGames();
        this.BtnGenerate.setEnabled(false);
    }

    public void clearLists() {

        this.qualifiedTeams_SSBLV.clear();
        this.qualifiedTeams_SSBTM.clear();
        this.qualifiedTeams_SSBCN.clear();
        this.qualifiedTeams_SSBST.clear();
        this.qualifiedTeams_SSBLM.clear();
        this.qualifiedTeams_SSBSD.clear();
        this.qualifiedTeams_SSBSM.clear();

        this.qualifiedTeams_GELV.clear();
        this.qualifiedTeams_GETM.clear();
        this.qualifiedTeams_GESSDV.clear();
        this.qualifiedTeams_GELM.clear();
        this.qualifiedTeams_GETMS.clear();

        this.qualifiedGroupsTeams_SSBLV.clear();
        this.qualifiedGroupsTeams_SSBTM.clear();
        this.qualifiedGroupsTeams_SSBCN.clear();
        this.qualifiedGroupsTeams_SSBST.clear();
        this.qualifiedGroupsTeams_SSBLM.clear();
        this.qualifiedGroupsTeams_SSBSD.clear();
        this.qualifiedGroupsTeams_SSBSM.clear();

        this.groupNames.clear();

    }

    public void exportToExcel(ArrayList<DefaultTableModel> tables, String filePath) throws FileNotFoundException, IOException {
        System.out.println(filePath);
        Workbook workbook = new HSSFWorkbook();
        Row row;
        Cell cell;

        int index = 0;

        for (DefaultTableModel mdl : tables) {
            DefaultTableModel model = mdl;
            Sheet sheet = workbook.createSheet("Grupo " + this.groupNames.get(index));
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelData = new javax.swing.JPanel();
        LblSport = new javax.swing.JLabel();
        BoxGame = new javax.swing.JComboBox<>();
        BtnGenerate = new javax.swing.JButton();
        BtnBack = new javax.swing.JButton();
        LblPeriod = new javax.swing.JLabel();
        BoxPeriods = new javax.swing.JComboBox<>();
        LblFrom = new javax.swing.JLabel();
        LblTo = new javax.swing.JLabel();
        BoxFrom = new javax.swing.JComboBox<>();
        BoxTo = new javax.swing.JComboBox<>();
        PanelData1 = new javax.swing.JPanel();
        LblFileName = new javax.swing.JLabel();
        TxtFileName = new javax.swing.JTextField();
        LblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelData.setBackground(new java.awt.Color(0, 51, 51));
        PanelData.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        PanelData.setForeground(new java.awt.Color(0, 51, 51));

        LblSport.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblSport.setForeground(new java.awt.Color(255, 255, 255));
        LblSport.setText("Juego");

        BoxGame.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BoxGame.setForeground(new java.awt.Color(255, 255, 255));
        BoxGame.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select Game>", "GoldenEye", "Mario Kart", "Super Smash Bros" }));
        BoxGame.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BoxGameItemStateChanged(evt);
            }
        });
        BoxGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxGameActionPerformed(evt);
            }
        });

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

        BoxPeriods.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BoxPeriods.setForeground(new java.awt.Color(255, 255, 255));
        BoxPeriods.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select Period Time>", "1", "2", "4" }));
        BoxPeriods.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BoxPeriodsItemStateChanged(evt);
            }
        });
        BoxPeriods.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxPeriodsActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout PanelDataLayout = new javax.swing.GroupLayout(PanelData);
        PanelData.setLayout(PanelDataLayout);
        PanelDataLayout.setHorizontalGroup(
            PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDataLayout.createSequentialGroup()
                        .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(BoxGame, javax.swing.GroupLayout.Alignment.LEADING, 0, 130, Short.MAX_VALUE)
                            .addComponent(LblSport, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblPeriod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BoxPeriods, 0, 180, Short.MAX_VALUE)
                            .addGroup(PanelDataLayout.createSequentialGroup()
                                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(BoxFrom, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(LblFrom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(LblTo, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                    .addComponent(BoxTo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BtnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnGenerate, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))
                    .addComponent(PanelData1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelDataLayout.setVerticalGroup(
            PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDataLayout.createSequentialGroup()
                        .addComponent(BtnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelDataLayout.createSequentialGroup()
                        .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblSport)
                            .addComponent(LblPeriod))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BoxGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BoxPeriods, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblFrom)
                            .addComponent(LblTo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BoxFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BoxTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(PanelData1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoxGameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BoxGameItemStateChanged

    }//GEN-LAST:event_BoxGameItemStateChanged

    private void BoxGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxGameActionPerformed

    }//GEN-LAST:event_BoxGameActionPerformed

    private void BtnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerateActionPerformed
        String game = (String) this.BoxGame.getModel().getSelectedItem();
        String mainland = "";
        int from_year = Integer.parseInt((String) this.BoxFrom.getSelectedItem());
        int to_year = Integer.parseInt((String) this.BoxTo.getSelectedItem());
        int period = Integer.parseInt((String) this.BoxPeriods.getSelectedItem());

        for (int p = from_year; p <= to_year; p += period) {
            this.table_groups.clear();
            this.clearLists();

            switch (game) {
                case "Super Smash Bros":
                    //Individual
                    for (String ssb : this.ssb_list) {
                        this.sport_sim.setGroupsSelected(false);

                        if (ssb.equals("SSB-SingleMode")) {
                            this.sport_sim.setComboGameValue("SSB-Time");
                        } else {
                            if (ssb.equals("SSB-SuddenDeath")) {
                                this.sport_sim.setComboGameValue("SSB-Lives");
                            } else {
                                if (ssb.equals("SSB-LightningMode")) {
                                    this.sport_sim.setComboGameValue("SSB-Time");
                                } else {
                                    this.sport_sim.setComboGameValue(ssb);
                                }
                            }
                        }
                        this.sport_sim.generateLeagues();
                        this.numRotations = 6;
                        for (int i = 0; i < this.numRotations; i++) {
                            this.groupNames.add(ssb + " Ind. " + (i + 1));
                            this.sport_sim.putGroups();
                            this.sport_sim.createFixture();
                            this.sport_sim.playMatches();

                            JTable tblpts = this.sport_sim.getSimTable();
                            RowSorter<? extends TableModel> rwstr = this.sport_sim.getSimTableSorter();
                            tblpts.setRowSorter(rwstr);

                            DefaultTableModel fixed_model = new DefaultTableModel();
                            fixed_model.setColumnCount(tblpts.getColumnCount());
                            fixed_model.setRowCount(tblpts.getRowCount());

                            for (int r = 0; r < tblpts.getRowCount(); r++) {
                                String player = (String) tblpts.getValueAt(r, 0);
                                switch (ssb) {
                                    case "SSB-Time":
                                        if (r == 0 && !this.qualifiedTeams_SSBTM.contains(player)) {
                                            this.qualifiedTeams_SSBTM.add(player);
                                        }
                                        break;
                                    case "SSB-Lives":
                                        if (r == 0 && !this.qualifiedTeams_SSBLV.contains(player)) {
                                            this.qualifiedTeams_SSBLV.add(player);
                                        }
                                        break;
                                    case "SSB-Coins":
                                        if (r == 0 && !this.qualifiedTeams_SSBCN.contains(player)) {
                                            this.qualifiedTeams_SSBCN.add(player);
                                        }
                                        break;
                                    case "SSB-Stamina":
                                        if (r == 0 && !this.qualifiedTeams_SSBST.contains(player)) {
                                            this.qualifiedTeams_SSBST.add(player);
                                        }
                                        break;
                                    case "SSB-SingleMode":
                                        if (r == 0 && !this.qualifiedTeams_SSBSM.contains(player)) {
                                            this.qualifiedTeams_SSBSM.add(player);
                                        }
                                        break;
                                    case "SSB-SuddenDeath":
                                        if (r == 0 && !this.qualifiedTeams_SSBSD.contains(player)) {
                                            this.qualifiedTeams_SSBSD.add(player);
                                        }
                                        break;
                                    case "SSB-LightningMode":
                                        if (r == 0 && !this.qualifiedTeams_SSBLM.contains(player)) {
                                            this.qualifiedTeams_SSBLM.add(player);
                                        }
                                        break;

                                }

                                for (int c = 0; c < tblpts.getColumnCount(); c++) {
                                    fixed_model.setValueAt(tblpts.getValueAt(r, c), r, c);
                                    System.out.print(tblpts.getValueAt(r, c) + " ");
                                }
                                System.out.println("");
                            }

                            this.table_groups.add(fixed_model);
                            this.sport_sim.resetMatches();
                        }
                    }

                    //Groups
                    for (String ssb : this.ssb_list) {
                        this.sport_sim.setGroupsSelected(true);
                        if (ssb.equals("SSB-SingleMode")) {
                            this.sport_sim.setComboGameValue("SSB-Time");
                        } else {
                            if (ssb.equals("SSB-SuddenDeath")) {
                                this.sport_sim.setComboGameValue("SSB-Lives");
                            } else {
                                if (ssb.equals("SSB-LightningMode")) {
                                    this.sport_sim.setComboGameValue("SSB-Time");
                                } else {
                                    this.sport_sim.setComboGameValue(ssb);
                                }
                            }
                        }
                        this.sport_sim.generateLeagues();
                        this.numRotations = 3;
                        for (int i = 0; i < this.numRotations; i++) {
                            this.groupNames.add(ssb + " Gr. " + (i + 1));
                            this.sport_sim.putGroups();
                            this.sport_sim.createFixture();
                            this.sport_sim.playMatches();

                            JTable tblpts = this.sport_sim.getSimTable();
                            RowSorter<? extends TableModel> rwstr = this.sport_sim.getSimTableSorter();
                            tblpts.setRowSorter(rwstr);

                            DefaultTableModel fixed_model = new DefaultTableModel();
                            fixed_model.setColumnCount(tblpts.getColumnCount());
                            fixed_model.setRowCount(tblpts.getRowCount());

                            for (int r = 0; r < tblpts.getRowCount(); r++) {
                                String player = (String) tblpts.getValueAt(r, 0);
                                switch (ssb) {
                                    case "SSB-Time":
                                        if (r < 2 && !this.qualifiedGroupsTeams_SSBTM.contains(player)) {
                                            this.qualifiedGroupsTeams_SSBTM.add(player);
                                        }
                                        break;
                                    case "SSB-Lives":
                                        if (r < 2 && !this.qualifiedGroupsTeams_SSBLV.contains(player)) {
                                            this.qualifiedGroupsTeams_SSBLV.add(player);
                                        }
                                        break;
                                    case "SSB-Coins":
                                        if (r < 2 && !this.qualifiedGroupsTeams_SSBCN.contains(player)) {
                                            this.qualifiedGroupsTeams_SSBCN.add(player);
                                        }
                                        break;
                                    case "SSB-Stamina":
                                        if (r < 2 && !this.qualifiedGroupsTeams_SSBST.contains(player)) {
                                            this.qualifiedGroupsTeams_SSBST.add(player);
                                        }
                                        break;
                                    case "SSB-SingleMode":
                                        if (r < 2 && !this.qualifiedGroupsTeams_SSBSM.contains(player)) {
                                            this.qualifiedGroupsTeams_SSBSM.add(player);
                                        }
                                        break;
                                    case "SSB-SuddenDeath":
                                        if (r < 2 && !this.qualifiedGroupsTeams_SSBSD.contains(player)) {
                                            this.qualifiedGroupsTeams_SSBSD.add(player);
                                        }
                                        break;
                                    case "SSB-LightningMode":
                                        if (r < 2 && !this.qualifiedGroupsTeams_SSBLM.contains(player)) {
                                            this.qualifiedGroupsTeams_SSBLM.add(player);
                                        }
                                        break;

                                }
                                for (int c = 0; c < tblpts.getColumnCount(); c++) {
                                    fixed_model.setValueAt(tblpts.getValueAt(r, c), r, c);
                                    System.out.print(tblpts.getValueAt(r, c) + " ");
                                }
                                System.out.println("");
                            }

                            this.table_groups.add(fixed_model);
                            this.sport_sim.resetMatches();

                        }
                    }

                    //Final Individual
                    for (String ssb : this.ssb_list) {
                        this.sport_sim.setGroupsSelected(false);

                        if (ssb.equals("SSB-SingleMode")) {
                            this.sport_sim.setComboGameValue("SSB-Time");
                        } else {
                            if (ssb.equals("SSB-SuddenDeath")) {
                                this.sport_sim.setComboGameValue("SSB-Lives");
                            } else {
                                if (ssb.equals("SSB-LightningMode")) {
                                    this.sport_sim.setComboGameValue("SSB-Time");
                                } else {
                                    this.sport_sim.setComboGameValue(ssb);
                                }
                            }
                        }
                        this.numRotations = 1;
                        for (int i = 0; i < this.numRotations; i++) {
                            switch (ssb) {
                                case "SSB-Time":
                                    for (String tm : this.qualifiedTeams_SSBTM) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;
                                case "SSB-Lives":
                                    for (String tm : this.qualifiedTeams_SSBLV) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;
                                case "SSB-Coins":
                                    for (String tm : this.qualifiedTeams_SSBCN) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;
                                case "SSB-Stamina":
                                    for (String tm : this.qualifiedTeams_SSBST) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;
                                case "SSB-SingleMode":
                                    for (String tm : this.qualifiedTeams_SSBSM) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;
                                case "SSB-SuddenDeath":
                                    for (String tm : this.qualifiedTeams_SSBSD) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;
                                case "SSB-LightningMode":
                                    for (String tm : this.qualifiedTeams_SSBLM) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;

                            }
                            this.groupNames.add(ssb + " Final Ind. ");
                            this.sport_sim.createFixture();
                            this.sport_sim.playMatches();

                            JTable tblpts = this.sport_sim.getSimTable();
                            RowSorter<? extends TableModel> rwstr = this.sport_sim.getSimTableSorter();
                            tblpts.setRowSorter(rwstr);

                            DefaultTableModel fixed_model = new DefaultTableModel();
                            fixed_model.setColumnCount(tblpts.getColumnCount());
                            fixed_model.setRowCount(tblpts.getRowCount());

                            for (int r = 0; r < tblpts.getRowCount(); r++) {
                                for (int c = 0; c < tblpts.getColumnCount(); c++) {
                                    fixed_model.setValueAt(tblpts.getValueAt(r, c), r, c);
                                    System.out.print(tblpts.getValueAt(r, c) + " ");
                                }
                                System.out.println("");
                            }

                            this.table_groups.add(fixed_model);
                            this.sport_sim.resetMatches();
                        }
                    }

                    //Final Groups
                    for (String ssb : this.ssb_list) {
                        this.sport_sim.setGroupsSelected(true);
                        if (ssb.equals("SSB-SingleMode")) {
                            this.sport_sim.setComboGameValue("SSB-Time");
                        } else {
                            if (ssb.equals("SSB-SuddenDeath")) {
                                this.sport_sim.setComboGameValue("SSB-Lives");
                            } else {
                                if (ssb.equals("SSB-LightningMode")) {
                                    this.sport_sim.setComboGameValue("SSB-Time");
                                } else {
                                    this.sport_sim.setComboGameValue(ssb);
                                }
                            }
                        }
                        this.numRotations = 1;
                        for (int i = 0; i < this.numRotations; i++) {
                            this.groupNames.add(ssb + " Final Gr. ");
                            switch (ssb) {
                                case "SSB-Time":
                                    for (String tm : this.qualifiedGroupsTeams_SSBTM) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;
                                case "SSB-Lives":
                                    for (String tm : this.qualifiedGroupsTeams_SSBLV) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;
                                case "SSB-Coins":
                                    for (String tm : this.qualifiedGroupsTeams_SSBCN) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;
                                case "SSB-Stamina":
                                    for (String tm : this.qualifiedGroupsTeams_SSBST) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;
                                case "SSB-SingleMode":
                                    for (String tm : this.qualifiedGroupsTeams_SSBSM) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;
                                case "SSB-SuddenDeath":
                                    for (String tm : this.qualifiedGroupsTeams_SSBSD) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;
                                case "SSB-LightningMode":
                                    for (String tm : this.qualifiedGroupsTeams_SSBLM) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;

                            }
                            this.sport_sim.createFixture();
                            this.sport_sim.playMatches();

                            JTable tblpts = this.sport_sim.getSimTable();
                            RowSorter<? extends TableModel> rwstr = this.sport_sim.getSimTableSorter();
                            tblpts.setRowSorter(rwstr);

                            DefaultTableModel fixed_model = new DefaultTableModel();
                            fixed_model.setColumnCount(tblpts.getColumnCount());
                            fixed_model.setRowCount(tblpts.getRowCount());

                            for (int r = 0; r < tblpts.getRowCount(); r++) {
                                for (int c = 0; c < tblpts.getColumnCount(); c++) {
                                    fixed_model.setValueAt(tblpts.getValueAt(r, c), r, c);
                                    System.out.print(tblpts.getValueAt(r, c) + " ");
                                }
                                System.out.println("");
                            }

                            this.table_groups.add(fixed_model);
                            this.sport_sim.resetMatches();

                        }
                    }

                    System.out.println("SSB-LV");
                    System.out.println(this.qualifiedTeams_SSBLV);
                    System.out.println("SSB-TM");
                    System.out.println(this.qualifiedTeams_SSBTM);
                    System.out.println("SSB-CN");
                    System.out.println(this.qualifiedTeams_SSBCN);
                    System.out.println("SSB-ST");
                    System.out.println(this.qualifiedTeams_SSBST);
                    System.out.println("SSB-LM");
                    System.out.println(this.qualifiedTeams_SSBLM);
                    System.out.println("SSB-SD");
                    System.out.println(this.qualifiedTeams_SSBSD);
                    System.out.println("SSB-SM");
                    System.out.println(this.qualifiedTeams_SSBSM);

                    System.out.println("SSB-GR-LV");
                    System.out.println(this.qualifiedGroupsTeams_SSBLV);
                    System.out.println("SSB-GR-TM");
                    System.out.println(this.qualifiedGroupsTeams_SSBTM);
                    System.out.println("SSB-GR-CN");
                    System.out.println(this.qualifiedGroupsTeams_SSBCN);
                    System.out.println("SSB-GR-ST");
                    System.out.println(this.qualifiedGroupsTeams_SSBST);
                    System.out.println("SSB-GR-LM");
                    System.out.println(this.qualifiedGroupsTeams_SSBLM);
                    System.out.println("SSB-GR-SD");
                    System.out.println(this.qualifiedGroupsTeams_SSBSD);
                    System.out.println("SSB-GR-SM");
                    System.out.println(this.qualifiedGroupsTeams_SSBSM);
                    break;
                case "GoldenEye":
                    //Individual
                    for (String ge : this.ge_list) {
                        this.sport_sim.setGroupsSelected(false);
                        this.sport_sim.setComboGameValue(ge);
                        this.sport_sim.generateLeagues();
                        this.numRotations = 8;
                        for (int i = 0; i < this.numRotations; i++) {
                            this.groupNames.add(ge + " Ind. " + (i + 1));
                            this.sport_sim.putGroups();
                            this.sport_sim.createFixture();
                            this.sport_sim.playMatches();

                            JTable tblpts = this.sport_sim.getSimTable();
                            RowSorter<? extends TableModel> rwstr = this.sport_sim.getSimTableSorter();
                            tblpts.setRowSorter(rwstr);

                            DefaultTableModel fixed_model = new DefaultTableModel();
                            fixed_model.setColumnCount(tblpts.getColumnCount());
                            fixed_model.setRowCount(tblpts.getRowCount());

                            for (int r = 0; r < tblpts.getRowCount(); r++) {
                                String player = (String) tblpts.getValueAt(r, 0);
                                switch (ge) {
                                    case "GE-Time":
                                        if (r == 0 && !this.qualifiedTeams_GETM.contains(player)) {
                                            this.qualifiedTeams_GETM.add(player);
                                        }
                                        break;
                                    case "GE-Lives":
                                        if (r == 0 && !this.qualifiedTeams_GELV.contains(player)) {
                                            this.qualifiedTeams_GELV.add(player);
                                        }
                                        break;
                                    case "GE-SSDV":
                                        if (r == 0 && !this.qualifiedTeams_GESSDV.contains(player)) {
                                            this.qualifiedTeams_GESSDV.add(player);
                                        }
                                        break;
                                    case "GE-License to Kill":
                                        if (r == 0 && !this.qualifiedTeams_GELM.contains(player)) {
                                            this.qualifiedTeams_GELM.add(player);
                                        }
                                        break;

                                }

                                for (int c = 0; c < tblpts.getColumnCount(); c++) {
                                    fixed_model.setValueAt(tblpts.getValueAt(r, c), r, c);
                                    System.out.print(tblpts.getValueAt(r, c) + " ");
                                }
                                System.out.println("");
                            }

                            this.table_groups.add(fixed_model);
                            this.sport_sim.resetMatches();
                        }
                    }

                    //Groups
                    this.sport_sim.setGroupsSelected(true);
                    this.sport_sim.setComboGameValue("GE-Teams");
                    this.sport_sim.generateLeagues();
                    this.numRotations = 2;
                    for (int i = 0; i < this.numRotations; i++) {
                        this.groupNames.add("GE-Teams" + " Gr. " + (i + 1));
                        System.out.println(this.sport_sim.getTeamList());
                        this.sport_sim.putGroups();
                        this.sport_sim.createFixture();
                        this.sport_sim.playMatches();

                        JTable tblpts = this.sport_sim.getSimTable();
                        RowSorter<? extends TableModel> rwstr = this.sport_sim.getSimTableSorter();
                        tblpts.setRowSorter(rwstr);

                        DefaultTableModel fixed_model = new DefaultTableModel();
                        fixed_model.setColumnCount(tblpts.getColumnCount());
                        fixed_model.setRowCount(tblpts.getRowCount());

                        for (int r = 0; r < tblpts.getRowCount(); r++) {
                            String player = (String) tblpts.getValueAt(r, 0);

                            if (r < 2 && !this.qualifiedTeams_GETMS.contains(player)) {
                                this.qualifiedTeams_GETMS.add(player);
                            }

                            for (int c = 0; c < tblpts.getColumnCount(); c++) {
                                fixed_model.setValueAt(tblpts.getValueAt(r, c), r, c);
                                System.out.print(tblpts.getValueAt(r, c) + " ");
                            }
                            System.out.println("");
                        }

                        this.table_groups.add(fixed_model);
                        this.sport_sim.resetMatches();

                    }

                    //Final Individual
                    for (String ge : this.ge_list) {
                        this.sport_sim.setGroupsSelected(false);
                        this.sport_sim.setComboGameValue(ge);
                        this.numRotations = 1;
                        for (int i = 0; i < this.numRotations; i++) {
                            this.groupNames.add(ge + " Final Ind. ");
                            switch (ge) {
                                case "GE-Time":
                                    for (String tm : this.qualifiedTeams_GETM) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;
                                case "GE-Lives":
                                    for (String tm : this.qualifiedTeams_GELV) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;
                                case "GE-SSDV":
                                    for (String tm : this.qualifiedTeams_GESSDV) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;
                                case "GE-License to Kill":
                                    for (String tm : this.qualifiedTeams_GELM) {
                                        this.sport_sim.setComboTeamsValue(tm);
                                        this.sport_sim.addPlayer();
                                    }
                                    break;

                            }
                            this.sport_sim.createFixture();
                            this.sport_sim.playMatches();

                            JTable tblpts = this.sport_sim.getSimTable();
                            RowSorter<? extends TableModel> rwstr = this.sport_sim.getSimTableSorter();
                            tblpts.setRowSorter(rwstr);

                            DefaultTableModel fixed_model = new DefaultTableModel();
                            fixed_model.setColumnCount(tblpts.getColumnCount());
                            fixed_model.setRowCount(tblpts.getRowCount());

                            for (int r = 0; r < tblpts.getRowCount(); r++) {
                                for (int c = 0; c < tblpts.getColumnCount(); c++) {
                                    fixed_model.setValueAt(tblpts.getValueAt(r, c), r, c);
                                    System.out.print(tblpts.getValueAt(r, c) + " ");
                                }
                                System.out.println("");
                            }

                            this.table_groups.add(fixed_model);
                            this.sport_sim.resetMatches();
                        }
                    }

                    //Final Groups
                    this.sport_sim.setGroupsSelected(true);
                    this.sport_sim.setComboGameValue("GE-Teams");
                    this.numRotations = 1;
                    for (int i = 0; i < this.numRotations; i++) {
                        this.groupNames.add("GE-Teams" + " Gr. ");
                        for (String ge : this.qualifiedTeams_GETMS) {
                            this.sport_sim.setComboTeamsValue(ge);
                            this.sport_sim.addPlayer();
                        }
                        this.sport_sim.createFixture();
                        this.sport_sim.playMatches();

                        JTable tblpts = this.sport_sim.getSimTable();
                        RowSorter<? extends TableModel> rwstr = this.sport_sim.getSimTableSorter();
                        tblpts.setRowSorter(rwstr);

                        DefaultTableModel fixed_model = new DefaultTableModel();
                        fixed_model.setColumnCount(tblpts.getColumnCount());
                        fixed_model.setRowCount(tblpts.getRowCount());

                        for (int r = 0; r < tblpts.getRowCount(); r++) {
                            String player = (String) tblpts.getValueAt(r, 0);

                            if (r < 2 && !this.qualifiedGroupsTeams_SSBTM.contains(player)) {
                                this.qualifiedGroupsTeams_SSBTM.add(player);
                            }

                            for (int c = 0; c < tblpts.getColumnCount(); c++) {
                                fixed_model.setValueAt(tblpts.getValueAt(r, c), r, c);
                                System.out.print(tblpts.getValueAt(r, c) + " ");
                            }
                            System.out.println("");
                        }

                        this.table_groups.add(fixed_model);
                        this.sport_sim.resetMatches();

                    }

                    break;
                case "Mario Kart":
                    //Final
                    this.groupNames.add("Final");
                    this.sport_trn.setNumberTeams("8");
                    JTable tblpts_final;
                    RowSorter<? extends TableModel> rwstr_final;
                    DefaultTableModel fixed_model_final;
                    this.sport_trn.setComboGamesValue("MK-Battles");
                    ArrayList<String> teams = this.sport_trn.getComboTeamsValues();
                    for (String tm : teams) {
                        this.sport_trn.setComboTeamsValue(tm);
                        this.sport_trn.addPlayer();
                    }
                    this.sport_trn.playMatches();
                    this.sport_trn.generateTree();
                    tblpts_final = this.sport_trn.getSimTable();
                    rwstr_final = this.sport_trn.getSimTableSorter();
                    tblpts_final.setRowSorter(rwstr_final);
                    fixed_model_final = new DefaultTableModel();
                    fixed_model_final.setColumnCount(tblpts_final.getColumnCount());
                    fixed_model_final.setRowCount(tblpts_final.getRowCount());

                    for (int r = 0; r < fixed_model_final.getRowCount(); r++) {
                        for (int c = 0; c < fixed_model_final.getColumnCount(); c++) {
                            fixed_model_final.setValueAt(tblpts_final.getValueAt(r, c), r, c);
                            System.out.print(tblpts_final.getValueAt(r, c) + " ");
                        }
                        System.out.println("");
                    }
                    this.table_groups.add(fixed_model_final);
                    JLabel final_team_tree = this.sport_trn.getTreeLabel();
                    TournamentNode final_tree = this.sport_trn.getTreeGraphics();
                    this.sport_trn.resetMatches();
                     {
                        try {
                            this.exportTreeImage(final_team_tree, "Mario Kart " + "Final" + p, final_tree);
                        } catch (IOException ex) {
                            System.out.println("Final not created...");
                        }
                    }
                    break;
                default:
                    break;
            }

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

        }


    }//GEN-LAST:event_BtnGenerateActionPerformed

    private void BtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBackActionPerformed
        FrameInicio f = new FrameInicio();
        f.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnBackActionPerformed

    private void BoxPeriodsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BoxPeriodsItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxPeriodsItemStateChanged

    private void BoxPeriodsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxPeriodsActionPerformed
        int value = Integer.parseInt((String) this.BoxPeriods.getSelectedItem());
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
    }//GEN-LAST:event_BoxPeriodsActionPerformed

    private void BoxFromItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BoxFromItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxFromItemStateChanged

    private void BoxFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxFromActionPerformed
        int value = Integer.parseInt((String) this.BoxPeriods.getSelectedItem());
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
    }//GEN-LAST:event_BoxFromActionPerformed

    private void BoxToItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BoxToItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxToItemStateChanged

    private void BoxToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxToActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxToActionPerformed

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
            java.util.logging.Logger.getLogger(GamesExport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GamesExport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GamesExport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GamesExport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GamesExport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BoxFrom;
    private javax.swing.JComboBox<String> BoxGame;
    private javax.swing.JComboBox<String> BoxPeriods;
    private javax.swing.JComboBox<String> BoxTo;
    private javax.swing.JButton BtnBack;
    private javax.swing.JButton BtnGenerate;
    private javax.swing.JLabel LblFileName;
    private javax.swing.JLabel LblFrom;
    private javax.swing.JLabel LblPeriod;
    private javax.swing.JLabel LblSport;
    private javax.swing.JLabel LblStatus;
    private javax.swing.JLabel LblTo;
    private javax.swing.JPanel PanelData;
    private javax.swing.JPanel PanelData1;
    private javax.swing.JTextField TxtFileName;
    // End of variables declaration//GEN-END:variables
}
