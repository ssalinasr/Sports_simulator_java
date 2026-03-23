/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Exports;

import Main.FrameInicio;
import Modules.TableTeam;
import Modules.TournamentNode;
import OlympicGames.FrameEliminationSports;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Salinas
 */
public class SportsLeaguesExport extends javax.swing.JFrame {

    /**
     * Creates new form SportsLeaguesExport
     */
    private ManyMatchesSports sport_sim;
    private ManyMatchesSports sport_sim_group;
    private FrameEliminationSports sport_trn;
    private FrameEliminationSports sport_trn_final;
    private ArrayList<DefaultTableModel> table_groups = new ArrayList<>();

//    private ArrayList<DefaultTableModel> table_groups = new ArrayList<>();
    private int numRotationsFut = 32;
    private int numRotationsElse = 16;

    private ArrayList<String> qualifiedTeams = new ArrayList<>();
    private ArrayList<String> qualifiedAfTeams = new ArrayList<>();
    private ArrayList<String> qualifiedAsTeams = new ArrayList<>();
    private ArrayList<String> qualifiedAmTeams = new ArrayList<>();
    private ArrayList<String> qualifiedEuTeams = new ArrayList<>();

    private ArrayList<String> groupStageTeams = new ArrayList<>();
    private ArrayList<TableTeam> thirdFinalistTeams = new ArrayList<>();
    private ArrayList<String> finalistTeams = new ArrayList<>();
    private ArrayList<String> groupNames = new ArrayList<>();

    private ArrayList<TableTeam> secondAfTeams = new ArrayList<>();
    private ArrayList<TableTeam> secondAsTeams = new ArrayList<>();
    private ArrayList<TableTeam> thirdTeams = new ArrayList<>();

    private int radius = 20; // Tree node radius
    private int vGap = 50; // Gap between two levels in a tree

    public SportsLeaguesExport() {
        initComponents();
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0, 51, 51));
        setResizable(false);
        setTitle("Exportador Ligas Juegos Olimpicos");
        this.sport_sim = new ManyMatchesSports();
        this.sport_sim_group = new ManyMatchesSports();
        this.sport_trn = new FrameEliminationSports();
        this.sport_trn_final = new FrameEliminationSports();
        this.BtnGenerate.setEnabled(false);
    }

    public void clearLists() {
        this.qualifiedAfTeams.clear();
        this.qualifiedAmTeams.clear();
        this.qualifiedAsTeams.clear();
        this.qualifiedEuTeams.clear();
        this.qualifiedTeams.clear();
        this.groupStageTeams.clear();
        this.thirdFinalistTeams.clear();
        this.finalistTeams.clear();
        this.groupNames.clear();
        this.secondAfTeams.clear();
        this.secondAsTeams.clear();
        this.thirdTeams.clear();
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

    public void discardThirdFinalists() {
        Collections.sort(this.thirdFinalistTeams);
        for (TableTeam t : this.thirdFinalistTeams) {
            System.out.println(t.getName() + " " + t.getPts());
        }
        //First Eliminated
        this.thirdFinalistTeams.remove(this.thirdFinalistTeams.size() - 1);
        //Second Eliminated
        this.thirdFinalistTeams.remove(this.thirdFinalistTeams.size() - 1);
        //Third Eliminated
        this.thirdFinalistTeams.remove(this.thirdFinalistTeams.size() - 1);
        //Fourth Eliminated
        this.thirdFinalistTeams.remove(this.thirdFinalistTeams.size() - 1);

        for (TableTeam t : this.thirdFinalistTeams) {
            this.finalistTeams.add(t.getName());
        }
    }

    public void discardLastSeconds() {
        Collections.sort(this.secondAfTeams);
        Collections.sort(this.secondAsTeams);

        for (TableTeam t : this.secondAfTeams) {
            System.out.println(t.getName() + " " + t.getPts());
        }
        //First Eliminated
        this.secondAfTeams.remove(this.secondAfTeams.size() - 1);
        //Second Eliminated
        this.secondAfTeams.remove(this.secondAfTeams.size() - 1);

        for (TableTeam t : this.secondAfTeams) {
            this.qualifiedAfTeams.add(t.getName());
        }

        for (TableTeam t : this.secondAsTeams) {
            System.out.println(t.getName() + " " + t.getPts());
        }
        //First Eliminated
        this.secondAsTeams.remove(this.secondAsTeams.size() - 1);
        //Second Eliminated
        this.secondAsTeams.remove(this.secondAsTeams.size() - 1);

        for (TableTeam t : this.secondAsTeams) {
            this.qualifiedAsTeams.add(t.getName());
        }
    }

    public void discardLastThirds() {
        Collections.sort(this.thirdTeams);
        for (TableTeam t : this.thirdTeams) {
            System.out.println(t.getName() + " " + t.getPts());

        }
        //First Eliminated
        this.thirdTeams.remove(this.thirdTeams.size() - 1);
        //Second Eliminated
        this.thirdTeams.remove(this.thirdTeams.size() - 1);

        for (TableTeam t : this.thirdTeams) {
            this.qualifiedAmTeams.add(t.getName());
        }
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
        BoxSport = new javax.swing.JComboBox<>();
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
        LblSport.setText("Deporte");

        BoxSport.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BoxSport.setForeground(new java.awt.Color(255, 255, 255));
        BoxSport.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select Sport>", "MenFootball", "WomenFootball", "MenFutsal", "WomenFutsal", "Volleyball", "Tennis", "TableTennis", "Baseball", "Basketball", "Rugby", "Handball", "Waterpolo", "Badminton", "Squash", "Hockey", "BeachVolley", "Curling", "Archery", "Fencing" }));
        BoxSport.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BoxSportItemStateChanged(evt);
            }
        });
        BoxSport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxSportActionPerformed(evt);
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
                            .addComponent(BoxSport, javax.swing.GroupLayout.Alignment.LEADING, 0, 130, Short.MAX_VALUE)
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
                            .addComponent(BoxSport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void BoxSportItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BoxSportItemStateChanged

    }//GEN-LAST:event_BoxSportItemStateChanged

    private void BoxSportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxSportActionPerformed

    }//GEN-LAST:event_BoxSportActionPerformed

    private void BtnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerateActionPerformed
        String sport = (String) this.BoxSport.getModel().getSelectedItem();
        String mainland = "";
        int from_year = Integer.parseInt((String) this.BoxFrom.getSelectedItem());
        int to_year = Integer.parseInt((String) this.BoxTo.getSelectedItem());
        int period = Integer.parseInt((String) this.BoxPeriods.getSelectedItem());

        for (int p = from_year; p <= to_year; p += period) {
            this.table_groups.clear();
            this.clearLists();
            this.sport_sim.setComboSportValue(sport);
            this.sport_sim.generateLeagues();
            this.sport_sim.setShieldSelected(true);
            this.sport_sim.setLocalSelected(true);
            
            System.out.println(this.sport_sim.getTeamList());
            
            for (int i = 0; i < this.numRotationsFut; i++) {
                //First Stage
                if (i <= 8) {
                    mainland = "Africa";
                }
                if (i > 8 && i <= 17) {
                    mainland = "AsiaOceania";
                }
                if (i > 17 && i <= 23) {
                    mainland = "America";
                }
                if (i > 23) {
                    mainland = "Europa";
                }

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
                    String team = (String) tblpts.getValueAt(r, 0);
                    if (r == 0 && !this.qualifiedTeams.contains(team)) {

                        if (i <= 8) {
                            this.qualifiedAfTeams.add(team);
                            this.groupNames.add("Africa " + (i + 1));
                        }
                        if (i > 8 && i <= 17) {
                            this.qualifiedAsTeams.add(team);
                            this.groupNames.add("AsiaOceania " + (i - 8));
                        }
                        if (i > 17 && i <= 23) {
                            this.qualifiedAmTeams.add(team);
                            this.groupNames.add("America " + (i - 17));
                        }
                        if (i > 23) {
                            this.qualifiedEuTeams.add(team);
                            this.groupNames.add("Europa " + (i - 23));
                        }

                    }
                    if ((r == 2 && !this.qualifiedTeams.contains(team)) && (i > 17 && i <= 23)) {
                        TableTeam sec = new TableTeam(team);
                        sec.setPts((int) tblpts.getValueAt(r, 1));
                        sec.setVic((int) tblpts.getValueAt(r, 2));
                        sec.setDer((int) tblpts.getValueAt(r, 3));
                        sec.setEmp((int) tblpts.getValueAt(r, 4));
                        sec.setGf((int) tblpts.getValueAt(r, 5));
                        sec.setGc((int) tblpts.getValueAt(r, 6));
                        sec.setDif((int) tblpts.getValueAt(r, 7));
                        this.thirdTeams.add(sec);

                    } else {
                        if ((r == 1 && !this.qualifiedTeams.contains(team)) && i > 17) {
                            this.qualifiedTeams.add(team);
                            if (i > 17 && i <= 23) {
                                this.qualifiedAmTeams.add(team);
                            }
                            if (i > 23) {
                                this.qualifiedEuTeams.add(team);
                            }
                        } else {
                            if ((r == 1 && !this.qualifiedTeams.contains(team)) && (i >= 0 && i <= 8)) {
                                TableTeam sec = new TableTeam(team);
                                sec.setPts((int) tblpts.getValueAt(r, 1));
                                sec.setVic((int) tblpts.getValueAt(r, 2));
                                sec.setDer((int) tblpts.getValueAt(r, 3));
                                sec.setEmp((int) tblpts.getValueAt(r, 4));
                                sec.setGf((int) tblpts.getValueAt(r, 5));
                                sec.setGc((int) tblpts.getValueAt(r, 6));
                                sec.setDif((int) tblpts.getValueAt(r, 7));
                                this.secondAfTeams.add(sec);
                            } else {
                                if ((r == 1 && !this.qualifiedTeams.contains(team)) && (i > 8 && i <= 17)) {
                                    TableTeam sec = new TableTeam(team);
                                    sec.setPts((int) tblpts.getValueAt(r, 1));
                                    sec.setVic((int) tblpts.getValueAt(r, 2));
                                    sec.setDer((int) tblpts.getValueAt(r, 3));
                                    sec.setEmp((int) tblpts.getValueAt(r, 4));
                                    sec.setGf((int) tblpts.getValueAt(r, 5));
                                    sec.setGc((int) tblpts.getValueAt(r, 6));
                                    sec.setDif((int) tblpts.getValueAt(r, 7));
                                    this.secondAsTeams.add(sec);
                                }
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
                this.sport_sim.resetMatches();
            }
            this.discardLastSeconds();
            this.discardLastThirds();

            //Mainland tournaments
            this.sport_trn.setNumberTeams("16");
            JTable tblpts;
            RowSorter<? extends TableModel> rwstr;
            DefaultTableModel fixed_model;
            this.sport_trn.setComboSportsValue(sport);
            //Africa
            this.groupNames.add("Final Africa");
            for (String aft : this.qualifiedAfTeams) {
                this.sport_trn.setComboTeamsValue(aft);
                this.sport_trn.addTeam();
            }
            this.sport_trn.playMatches();
            this.sport_trn.generateTree();
            tblpts = this.sport_trn.getSimTable();
            rwstr = this.sport_trn.getSimTableSorter();
            tblpts.setRowSorter(rwstr);
            fixed_model = new DefaultTableModel();
            fixed_model.setColumnCount(tblpts.getColumnCount());
            fixed_model.setRowCount(tblpts.getRowCount());

            for (int r = 0; r < fixed_model.getRowCount(); r++) {
                String team = (String) tblpts.getValueAt(r, 0);
                if (r >= 0 && r < 12) {
                    this.groupStageTeams.add(team);
                }

                for (int c = 0; c < fixed_model.getColumnCount(); c++) {
                    fixed_model.setValueAt(tblpts.getValueAt(r, c), r, c);
                    System.out.print(tblpts.getValueAt(r, c) + " ");
                }
                System.out.println("");
            }

            JLabel af_team_tree = this.sport_trn.getTreeLabel();
            TournamentNode af_tree = this.sport_trn.getTreeGraphics();
            {
                try {
                    this.exportTreeImage(af_team_tree, sport + "AfricaCup" + p, af_tree);
                } catch (IOException ex) {
                    System.out.println("AfricaCup not created...");
                }
            }

            this.table_groups.add(fixed_model);
            this.sport_trn.resetMatches();
            //AsiaOceania
            this.groupNames.add("Final Asia");
            for (String as : this.qualifiedAsTeams) {
                this.sport_trn.setComboTeamsValue(as);
                this.sport_trn.addTeam();
            }
            this.sport_trn.playMatches();
            this.sport_trn.generateTree();
            tblpts = this.sport_trn.getSimTable();
            rwstr = this.sport_trn.getSimTableSorter();
            tblpts.setRowSorter(rwstr);
            fixed_model = new DefaultTableModel();
            fixed_model.setColumnCount(tblpts.getColumnCount());
            fixed_model.setRowCount(tblpts.getRowCount());

            for (int r = 0; r < fixed_model.getRowCount(); r++) {
                String team = (String) tblpts.getValueAt(r, 0);
                if (r >= 0 && r < 12) {
                    this.groupStageTeams.add(team);
                }

                for (int c = 0; c < fixed_model.getColumnCount(); c++) {
                    fixed_model.setValueAt(tblpts.getValueAt(r, c), r, c);
                    System.out.print(tblpts.getValueAt(r, c) + " ");
                }
                System.out.println("");
            }

            JLabel as_team_tree = this.sport_trn.getTreeLabel();
            TournamentNode as_tree = this.sport_trn.getTreeGraphics();
            {
                try {
                    this.exportTreeImage(as_team_tree, sport + "AsianCup" + p, as_tree);
                } catch (IOException ex) {
                    System.out.println("AsianCup not created...");
                }
            }

            this.table_groups.add(fixed_model);
            this.sport_trn.resetMatches();
            //America
            this.groupNames.add("Final America");
            for (String am : this.qualifiedAmTeams) {
                this.sport_trn.setComboTeamsValue(am);
                this.sport_trn.addTeam();
            }
            this.sport_trn.playMatches();
            this.sport_trn.generateTree();
            tblpts = this.sport_trn.getSimTable();
            rwstr = this.sport_trn.getSimTableSorter();
            tblpts.setRowSorter(rwstr);
            fixed_model = new DefaultTableModel();
            fixed_model.setColumnCount(tblpts.getColumnCount());
            fixed_model.setRowCount(tblpts.getRowCount());

            for (int r = 0; r < fixed_model.getRowCount(); r++) {
                String team = (String) tblpts.getValueAt(r, 0);
                if (r >= 0 && r < 12) {
                    this.groupStageTeams.add(team);
                }

                for (int c = 0; c < fixed_model.getColumnCount(); c++) {
                    fixed_model.setValueAt(tblpts.getValueAt(r, c), r, c);
                    System.out.print(tblpts.getValueAt(r, c) + " ");
                }
                System.out.println("");
            }

            JLabel am_team_tree = this.sport_trn.getTreeLabel();
            TournamentNode am_tree = this.sport_trn.getTreeGraphics();
            {
                try {
                    this.exportTreeImage(am_team_tree, sport + "AmericaCup" + p, am_tree);
                } catch (IOException ex) {
                    System.out.println("AmericaCup not created...");
                }
            }

            this.table_groups.add(fixed_model);
            this.sport_trn.resetMatches();
            //Europa
            this.groupNames.add("Final Europa");
            for (String eu : this.qualifiedEuTeams) {
                this.sport_trn.setComboTeamsValue(eu);
                this.sport_trn.addTeam();
            }
            this.sport_trn.playMatches();
            this.sport_trn.generateTree();
            tblpts = this.sport_trn.getSimTable();
            rwstr = this.sport_trn.getSimTableSorter();
            tblpts.setRowSorter(rwstr);
            fixed_model = new DefaultTableModel();
            fixed_model.setColumnCount(tblpts.getColumnCount());
            fixed_model.setRowCount(tblpts.getRowCount());

            for (int r = 0; r < fixed_model.getRowCount(); r++) {
                String team = (String) tblpts.getValueAt(r, 0);
                if (r >= 0 && r < 12) {
                    this.groupStageTeams.add(team);
                }

                for (int c = 0; c < fixed_model.getColumnCount(); c++) {
                    fixed_model.setValueAt(tblpts.getValueAt(r, c), r, c);
                    System.out.print(tblpts.getValueAt(r, c) + " ");
                }
                System.out.println("");
            }

            JLabel eu_team_tree = this.sport_trn.getTreeLabel();
            TournamentNode eu_tree = this.sport_trn.getTreeGraphics();
            {
                try {
                    this.exportTreeImage(eu_team_tree, sport + "EuropeanCup" + p, eu_tree);
                } catch (IOException ex) {
                    System.out.println("EuropeanCup not created...");
                }
            }

            this.table_groups.add(fixed_model);
            this.sport_trn.resetMatches();
            System.out.println(this.groupStageTeams);
            //Group Stage
            this.sport_sim_group.setComboSportValue(sport);
            this.sport_sim_group.setShieldSelected(true);
            this.sport_sim_group.setLocalSelected(true);

            for (int n = 0; n < this.groupStageTeams.size() / 4; n++) {
                this.groupNames.add("Grupo " + (n + 1));
                this.sport_sim_group.setComboTeamsValue(this.groupStageTeams.get(n));
                this.sport_sim_group.addTeam();
                this.sport_sim_group.setComboTeamsValue(this.groupStageTeams.get(n + (12 * 1)));
                this.sport_sim_group.addTeam();
                this.sport_sim_group.setComboTeamsValue(this.groupStageTeams.get(n + (12 * 2)));
                this.sport_sim_group.addTeam();
                this.sport_sim_group.setComboTeamsValue(this.groupStageTeams.get(n + (12 * 3)));
                this.sport_sim_group.addTeam();
                this.sport_sim_group.createFixture();
                this.sport_sim_group.playMatches();
                JTable tblpts_groups = this.sport_sim_group.getSimTable();
                RowSorter<? extends TableModel> rwstr_groups = this.sport_sim_group.getSimTableSorter();
                tblpts_groups.setRowSorter(rwstr_groups);

                DefaultTableModel fixed_model_groups = new DefaultTableModel();
                fixed_model_groups.setColumnCount(tblpts_groups.getColumnCount());
                fixed_model_groups.setRowCount(tblpts_groups.getRowCount());

                for (int r = 0; r < tblpts_groups.getRowCount(); r++) {
                    String finalist = (String) tblpts_groups.getValueAt(r, 0);
                    if ((r == 0 || r == 1) && !this.finalistTeams.contains(finalist)) {
                        this.finalistTeams.add(finalist);
                    }

                    if (r == 2 && !this.finalistTeams.contains(finalist)) {
                        TableTeam thr = new TableTeam(finalist);
                        thr.setPts((int) tblpts_groups.getValueAt(r, 1));
                        thr.setVic((int) tblpts_groups.getValueAt(r, 2));
                        thr.setDer((int) tblpts_groups.getValueAt(r, 3));
                        thr.setEmp((int) tblpts_groups.getValueAt(r, 4));
                        thr.setGf((int) tblpts_groups.getValueAt(r, 5));
                        thr.setGc((int) tblpts_groups.getValueAt(r, 6));
                        thr.setDif((int) tblpts_groups.getValueAt(r, 7));
                        this.thirdFinalistTeams.add(thr);
                    }

                    for (int c = 0; c < tblpts_groups.getColumnCount(); c++) {
                        fixed_model_groups.setValueAt(tblpts_groups.getValueAt(r, c), r, c);
                        System.out.print(tblpts_groups.getValueAt(r, c) + " ");
                    }
                    System.out.println("");

                }

                this.table_groups.add(fixed_model_groups);
                this.sport_sim_group.resetMatches();

            }
            this.discardThirdFinalists();
            System.out.println(this.finalistTeams);

            //Final
            this.groupNames.add("Final");
            this.sport_trn_final.setNumberTeams("32");
            JTable tblpts_final;
            RowSorter<? extends TableModel> rwstr_final;
            DefaultTableModel fixed_model_final;
            this.sport_trn_final.setComboSportsValue(sport);
            for (String fn : this.finalistTeams) {
                this.sport_trn_final.setComboTeamsValue(fn);
                this.sport_trn_final.addTeam();
            }
            this.sport_trn_final.playMatches();
            this.sport_trn_final.generateTree();
            tblpts_final = this.sport_trn_final.getSimTable();
            rwstr_final = this.sport_trn_final.getSimTableSorter();
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
            JLabel final_team_tree = this.sport_trn_final.getTreeLabel();
            TournamentNode final_tree = this.sport_trn_final.getTreeGraphics();
            this.sport_trn_final.resetMatches();
            {
                try {
                    this.exportTreeImage(final_team_tree, sport + "Final" + p, final_tree);
                } catch (IOException ex) {
                    System.out.println("Final not created...");
                }
            }
            /*
                    for (int i = 0; i < this.numRotationsElse; i++) {
                        //First Stage
                        if (i <= 3) {
                            mainland = "Africa";
                            this.groupNames.add("Africa " + (i + 1));
                        }
                        if (i > 3 && i <= 7) {
                            mainland = "AsiaOceania";
                            this.groupNames.add("AsiaOceania " + (i - 3));
                        }
                        if (i > 7 && i <= 11) {
                            mainland = "America";
                            this.groupNames.add("America " + (i - 7));
                        }
                        if (i > 11) {
                            mainland = "Europa";
                            this.groupNames.add("Europa " + (i - 11));
                        }

                        this.sport_sim.putGroups();
                        this.sport_sim.createFixture();
                        this.sport_sim.playMatches();
                        JTable tblpts_else = this.sport_sim.getSimTable();
                        RowSorter<? extends TableModel> rwstr_else = this.sport_sim.getSimTableSorter();
                        tblpts_else.setRowSorter(rwstr_else);

                        DefaultTableModel fixed_model_else = new DefaultTableModel();
                        fixed_model_else.setColumnCount(tblpts_else.getColumnCount());
                        fixed_model_else.setRowCount(tblpts_else.getRowCount());

                        for (int r = 0; r < tblpts_else.getRowCount(); r++) {
                            String team = (String) tblpts_else.getValueAt(r, 0);
                            if (r == 0 && !this.qualifiedTeams.contains(team)) {
                                this.qualifiedTeams.add(team);
                            }
                            if ((r == 1 && !this.qualifiedTeams.contains(team))) {
                                this.qualifiedTeams.add(team);
                            }
                            for (int c = 0; c < tblpts_else.getColumnCount(); c++) {
                                fixed_model_else.setValueAt(tblpts_else.getValueAt(r, c), r, c);
                                System.out.print(tblpts_else.getValueAt(r, c) + " ");
                            }
                            System.out.println("");
                        }

                        this.table_groups.add(fixed_model_else);
                        this.sport_sim.resetMatches();
                        System.out.println(this.qualifiedTeams);
                    }
                    //group stage
                    this.sport_sim_group.setComboSportValue(sport);
                    this.sport_sim_group.setShieldSelected(true);
                    this.sport_sim_group.setLocalSelected(true);

                    for (int n = 0; n < this.qualifiedTeams.size() / 4; n++) {
                        this.groupNames.add("Grupo " + (n + 1));
                        this.sport_sim_group.setComboTeamsValue(this.qualifiedTeams.get(n));
                        this.sport_sim_group.addTeam();
                        this.sport_sim_group.setComboTeamsValue(this.qualifiedTeams.get(n + (8 * 1)));
                        this.sport_sim_group.addTeam();
                        this.sport_sim_group.setComboTeamsValue(this.qualifiedTeams.get(n + (8 * 2)));
                        this.sport_sim_group.addTeam();
                        this.sport_sim_group.setComboTeamsValue(this.qualifiedTeams.get(n + (8 * 3)));
                        this.sport_sim_group.addTeam();
                        this.sport_sim_group.createFixture();
                        this.sport_sim_group.playMatches();
                        JTable tblpts_groups = this.sport_sim_group.getSimTable();
                        RowSorter<? extends TableModel> rwstr_groups = this.sport_sim_group.getSimTableSorter();
                        tblpts_groups.setRowSorter(rwstr_groups);

                        DefaultTableModel fixed_model_groups = new DefaultTableModel();
                        fixed_model_groups.setColumnCount(tblpts_groups.getColumnCount());
                        fixed_model_groups.setRowCount(tblpts_groups.getRowCount());

                        for (int r = 0; r < tblpts_groups.getRowCount(); r++) {
                            String finalist = (String) tblpts_groups.getValueAt(r, 0);
                            if ((r == 0 || r == 1) && !this.finalistTeams.contains(finalist)) {
                                this.finalistTeams.add(finalist);
                            }

                            for (int c = 0; c < tblpts_groups.getColumnCount(); c++) {
                                fixed_model_groups.setValueAt(tblpts_groups.getValueAt(r, c), r, c);
                                System.out.print(tblpts_groups.getValueAt(r, c) + " ");
                            }
                            System.out.println("");

                        }
                        this.table_groups.add(fixed_model_groups);
                        this.sport_sim_group.resetMatches();
                    }
                    System.out.println(this.finalistTeams);
                    //Final
                    this.groupNames.add("Final");
                    this.sport_trn_final.setNumberTeams("16");
                    JTable tblpts_final_else;
                    RowSorter<? extends TableModel> rwstr_final_else;
                    DefaultTableModel fixed_model_final_else;
                    this.sport_trn_final.setComboSportsValue(sport);
                    for (String fn : this.finalistTeams) {
                        this.sport_trn_final.setComboTeamsValue(fn);
                        this.sport_trn_final.addTeam();
                    }
                    this.sport_trn_final.playMatches();
                    this.sport_trn_final.generateTree();
                    tblpts_final_else = this.sport_trn_final.getSimTable();
                    rwstr_final_else = this.sport_trn_final.getSimTableSorter();
                    tblpts_final_else.setRowSorter(rwstr_final_else);
                    fixed_model_final_else = new DefaultTableModel();
                    fixed_model_final_else.setColumnCount(tblpts_final_else.getColumnCount());
                    fixed_model_final_else.setRowCount(tblpts_final_else.getRowCount());

                    for (int r = 0; r < fixed_model_final_else.getRowCount(); r++) {
                        for (int c = 0; c < fixed_model_final_else.getColumnCount(); c++) {
                            fixed_model_final_else.setValueAt(tblpts_final_else.getValueAt(r, c), r, c);
                            System.out.print(tblpts_final_else.getValueAt(r, c) + " ");
                        }
                        System.out.println("");
                    }
                    this.table_groups.add(fixed_model_final_else);
                    JLabel final_team_tree_else = this.sport_trn_final.getTreeLabel();
                    TournamentNode final_tree_else = this.sport_trn_final.getTreeGraphics();
                    this.sport_trn_final.resetMatches();
                     {
                        try {
                            this.exportTreeImage(final_team_tree_else, sport + "Final" + p, final_tree_else);
                        } catch (IOException ex) {
                            System.out.println("Final not created...");
                        }
                    }
             */
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
            java.util.logging.Logger.getLogger(SportsLeaguesExport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SportsLeaguesExport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SportsLeaguesExport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SportsLeaguesExport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SportsLeaguesExport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BoxFrom;
    private javax.swing.JComboBox<String> BoxPeriods;
    private javax.swing.JComboBox<String> BoxSport;
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
