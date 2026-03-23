/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Simulation;

import Exports.OlympicSimulatorExport;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Modules.TeamOlympic;
import Main.FrameInicio;
import Tools.Database.DBConsultasOL;
import java.util.Arrays;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Salinas
 */
public class OlympicSimulation extends javax.swing.JFrame {

    private DBConsultasOL dbl = new DBConsultasOL();
    private ArrayList<TeamOlympic> teams = new ArrayList<>();
    private DefaultTableModel md = new DefaultTableModel();
    private int summer_events = 20;
    private int num_intervals = 7;

    /**
     * Creates new form sim_olimpicos Belgica Dinamarca Rep.Checa Polonia
     * Portugal Corea del Sur China Eslovenia
     */
    public OlympicSimulation() {
        initComponents();
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0, 51, 51));
        setResizable(false);
        setTitle("Simulador Juegos Olímpicos");
    }

    public JComboBox getGameBox() {
        return this.BoxGame;
    }

    public JComboBox getCategoryBox() {
        return this.BoxCategoria;
    }

    public JComboBox getSportBox() {
        return this.BoxPrueba;
    }

    public DefaultTableModel getSimTable() {
        return (DefaultTableModel) this.TableSimulation.getModel();
    }

    public void setGame(String game) {
        this.BoxGame.getModel().setSelectedItem(game);
    }

    public void setCategory(String category) {
        this.BoxCategoria.getModel().setSelectedItem(category);
    }

    public void setSport(String sport) {
        this.BoxPrueba.getModel().setSelectedItem(sport);
    }

    public void setBoxAll(boolean selected) {
        this.BtnCheckAll.setSelected(selected);
    }

    public void resetTable() {
        this.TableSimulation.setModel(new DefaultTableModel());
    }

    public void generateTable() {

        md = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        md.addColumn("Pais");
        md.addColumn("Ranking");
        md.addColumn("Puntaje");
        this.TableSimulation.setModel(md);
        this.TableSimulation.setAutoCreateRowSorter(true);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(this.TableSimulation.getModel());
        this.TableSimulation.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();

        int ptsIndex = 2;
        sortKeys.add(new RowSorter.SortKey(ptsIndex, SortOrder.DESCENDING));

        sorter.setSortKeys(sortKeys);
        sorter.setSortsOnUpdates(true);
        sorter.sort();

    }

    public void generateTableGE() {

        md = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        md.addColumn("Pais");
        md.addColumn("Promedio");
        md.addColumn("Tiempo");
        md.addColumn("Punteria");
        md.addColumn("Muertes");
        md.addColumn("Disparos");
        this.TableSimulation.setModel(md);
        this.TableSimulation.setAutoCreateRowSorter(true);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(this.TableSimulation.getModel());
        this.TableSimulation.setRowSorter(sorter);

    }

    public void insertValueTable(TeamOlympic team, int curr_value) {
        Object[] row = new Object[3];
        double result = team.getResult();
        //result = result.replace(".", ",");
        row[0] = team.getName();
        row[1] = curr_value;
        row[2] = result;
        md.addRow(row);
        this.TableSimulation.setModel(md);
                this.TableSimulation.setAutoCreateRowSorter(true);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(this.TableSimulation.getModel());
        this.TableSimulation.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();

        int ptsIndex = 2;
        sortKeys.add(new RowSorter.SortKey(ptsIndex, SortOrder.DESCENDING));

        sorter.setSortKeys(sortKeys);
        sorter.setSortsOnUpdates(true);
        sorter.sort();

    }

    public void insertValueTableGE(TeamOlympic team, ArrayList<Double> result) {
        Object[] row = new Object[6];
        //result = result.replace(".", ",");
        row[0] = team.getName();
        row[1] = team.getLevelteam();
        row[2] = result.get(0);
        row[3] = result.get(1);
        row[4] = result.get(2);
        row[5] = result.get(3);
        md.addRow(row);
        this.TableSimulation.setModel(md);
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double getRandValue(double best_res, double last_res, double team_rank) {
        double rand = 0.000;
        Random r = new Random();
        double prob = Math.random();
        ArrayList<Double[]> intervals = new ArrayList<>();

        if (last_res < best_res) {
            
            double difference = (best_res - last_res)/7;
            double first_int = best_res;
            for (int i = 0; i < 7; i++) {
                Double[] inval = new Double[2];
                inval[0] = first_int;
                inval[1] = first_int - difference;
                first_int = inval[1];
                intervals.add(inval);
            }
            

        } else {
            double difference = (best_res - last_res)/7;
            double first_int = best_res;
            for (int i = 0; i < 7; i++) {
                Double[] inval = new Double[2];
                inval[0] = first_int;
                inval[1] = first_int - difference;
                first_int = inval[1];
                intervals.add(inval);
            }
        }       
            if (team_rank == 1) {

                if (prob <= 0.3) {
                    Double[] curr_inv = intervals.get(1);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.01) {
                    Double[] curr_inv = intervals.get(2);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.001333) {
                    Double[] curr_inv = intervals.get(3);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.0004) {
                    Double[] curr_inv = intervals.get(4);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.00004) {
                    Double[] curr_inv = intervals.get(5);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.000001) {
                    Double[] curr_inv = intervals.get(6);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }

                if (prob <= 1) {
                    Double[] curr_inv = intervals.get(0);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }

            }
            if (team_rank == 2) {
                if (prob <= 0.01) {
                    Double[] curr_inv = intervals.get(0);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }

                if (prob <= 0.3) {
                    Double[] curr_inv = intervals.get(2);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.001) {
                    Double[] curr_inv = intervals.get(3);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.00133) {
                    Double[] curr_inv = intervals.get(4);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.0004) {
                    Double[] curr_inv = intervals.get(5);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.00004) {
                    Double[] curr_inv = intervals.get(6);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 1) {
                    Double[] curr_inv = intervals.get(1);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
            }

            if (team_rank == 3) {
                if (prob <= 0.01) {
                    Double[] curr_inv = intervals.get(0);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.001) {
                    Double[] curr_inv = intervals.get(1);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }

                if (prob <= 0.3) {
                    Double[] curr_inv = intervals.get(3);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;

                }
                if (prob <= 0.001) {
                    Double[] curr_inv = intervals.get(4);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.00133) {
                    Double[] curr_inv = intervals.get(5);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.0004) {
                    Double[] curr_inv = intervals.get(6);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 1) {
                    Double[] curr_inv = intervals.get(2);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
            }

            if (team_rank == 4) {
                if (prob <= 0.001333) {
                    Double[] curr_inv = intervals.get(0);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.001) {
                    Double[] curr_inv = intervals.get(1);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.01) {
                    Double[] curr_inv = intervals.get(2);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }

                if (prob <= 0.3) {
                    Double[] curr_inv = intervals.get(4);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.001) {
                    Double[] curr_inv = intervals.get(5);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.001333) {
                    Double[] curr_inv = intervals.get(6);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 1) {
                    Double[] curr_inv = intervals.get(3);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
            }

            if (team_rank == 5) {
                if (prob <= 0.0004) {
                    Double[] curr_inv = intervals.get(0);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.001333) {
                    Double[] curr_inv = intervals.get(1);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.001) {
                    Double[] curr_inv = intervals.get(2);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.01) {
                    Double[] curr_inv = intervals.get(3);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }

                if (prob <= 0.3) {
                    Double[] curr_inv = intervals.get(5);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.01) {
                    Double[] curr_inv = intervals.get(6);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 1) {
                    Double[] curr_inv = intervals.get(4);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
            }

            if (team_rank == 6) {
                if (prob <= 0.00004) {
                    Double[] curr_inv = intervals.get(0);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.0004) {
                    Double[] curr_inv = intervals.get(1);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.001333) {
                    Double[] curr_inv = intervals.get(2);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.01) {
                    Double[] curr_inv = intervals.get(3);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.01) {
                    Double[] curr_inv = intervals.get(4);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }

                if (prob <= 0.3) {
                    Double[] curr_inv = intervals.get(6);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 1) {
                    Double[] curr_inv = intervals.get(5);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
            }

            if (team_rank == 7) {
                if (prob <= 0.000001) {
                    Double[] curr_inv = intervals.get(0);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.00004) {
                    Double[] curr_inv = intervals.get(1);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.0004) {
                    Double[] curr_inv = intervals.get(2);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.001333) {
                    Double[] curr_inv = intervals.get(3);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.01) {
                    Double[] curr_inv = intervals.get(4);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 0.3) {
                    Double[] curr_inv = intervals.get(5);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
                if (prob <= 1) {
                    Double[] curr_inv = intervals.get(6);
                    rand = ((Math.random() * (curr_inv[0] - curr_inv[1])) + curr_inv[1]);
                    prob = 1.01;
                }
            }
        //System.out.println(best_res);
        //System.out.println(last_res);

        return round(rand, 3);

    }

    public void simulateSport() {
        this.generateTable();
        String pr = (String) this.BoxPrueba.getSelectedItem();
        String value = (String) this.BoxGame.getSelectedItem();
        ResultSet qr = null;
        double best_res = 0.000;
        double last_res = 0.000;

        if (value.equals("Londres")) {
            try {
                qr = dbl.getValuesLondon(pr);
                while (qr.next()) {
                    best_res = Double.parseDouble(qr.getString("best_res"));
                    last_res = Double.parseDouble(qr.getString("last_res"));
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Invierno")) {
            try {
                qr = dbl.getValuesWinter(pr);
                while (qr.next()) {
                    best_res = Double.parseDouble(qr.getString("best_res"));
                    last_res = Double.parseDouble(qr.getString("last_res"));
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Tokyo")) {
            try {
                qr = dbl.getValuesTokyo(pr);
                while (qr.next()) {
                    best_res = Double.parseDouble(qr.getString("best_res"));
                    last_res = Double.parseDouble(qr.getString("last_res"));
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Excitebike")) {
            try {
                qr = dbl.getValuesEbike(pr);
                while (qr.next()) {
                    best_res = Double.parseDouble(qr.getString("best_res"));
                    last_res = Double.parseDouble(qr.getString("last_res"));
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Formula 1")) {
            try {
                qr = dbl.getValuesForm(pr);
                while (qr.next()) {
                    best_res = Double.parseDouble(qr.getString("best_res"));
                    last_res = Double.parseDouble(qr.getString("last_res"));
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("F-Zero")) {
            try {
                qr = dbl.getValuesFzero(pr);
                while (qr.next()) {
                    best_res = Double.parseDouble(qr.getString("best_res"));
                    last_res = Double.parseDouble(qr.getString("last_res"));
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Mario Kart")) {
            try {
                qr = dbl.getValuesMkart(pr);
                while (qr.next()) {
                    best_res = Double.parseDouble(qr.getString("best_res"));
                    last_res = Double.parseDouble(qr.getString("last_res"));
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("SSB")) {
            try {
                qr = dbl.getValuesSsb(pr);
                while (qr.next()) {
                    best_res = Double.parseDouble(qr.getString("best_res"));
                    last_res = Double.parseDouble(qr.getString("last_res"));
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Muñecos")) {
            try {
                qr = dbl.getValuesMun(pr);
                while (qr.next()) {
                    best_res = Double.parseDouble(qr.getString("best_res"));
                    last_res = Double.parseDouble(qr.getString("last_res"));
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        //System.out.println(best_res);
        //System.out.println(last_res);
        this.LblBest.setText("Best Res: " + best_res);
        this.LblLast.setText("Worst Res: " + last_res);

        if (!value.equals("Goldeneye")) {
            this.generateTable();
            for (TeamOlympic t : teams) {
                if (value.equals("SSB")) {
                    if (!t.getName().contains("/")) {
                        double sim = this.getRandValue(best_res, last_res, t.getLevelteam());
                        t.setResult(sim);
                        this.insertValueTable(t, (int) t.getLevelteam());
                    }
                } else {
                    String val_categoria = (String) this.BoxCategoria.getSelectedItem();
                    int curr_value = 7;
                    switch (val_categoria) {
                        case "Atletismo":
                            curr_value = t.getSumRankValue(0);
                            break;

                        case "BMX":
                            curr_value = t.getSumRankValue(1);
                            break;

                        case "Ciclismo Montana":
                            curr_value = t.getSumRankValue(2);
                            break;

                        case "Ciclismo Pista":
                            curr_value = t.getSumRankValue(3);
                            break;

                        case "Ciclismo Ruta":
                            curr_value = t.getSumRankValue(4);
                            break;

                        case "Ciclismo Urbano":
                            curr_value = t.getSumRankValue(5);
                            break;

                        case "Equitacion":
                            curr_value = t.getSumRankValue(6);
                            break;

                        case "Gimnasia Artistica":
                            curr_value = t.getSumRankValue(7);
                            break;

                        case "Gimnasia en Trampolin":
                            curr_value = t.getSumRankValue(8);
                            break;

                        case "Gimnasia Ritmica":
                            curr_value = t.getSumRankValue(9);
                            break;

                        case "Halterofilia":
                            curr_value = t.getSumRankValue(10);
                            break;

                        case "Nado Sincronizado":
                            curr_value = t.getSumRankValue(11);
                            break;

                        case "Natacion":
                            curr_value = t.getSumRankValue(12);
                            break;

                        case "Patinaje":
                            curr_value = t.getSumRankValue(13);
                            break;

                        case "Piraguismo":
                            curr_value = t.getSumRankValue(14);
                            break;

                        case "Remo":
                            curr_value = t.getSumRankValue(15);
                            break;

                        case "Saltos":
                            curr_value = t.getSumRankValue(16);
                            break;

                        case "Skateboarding":
                            curr_value = t.getSumRankValue(17);
                            break;

                        case "Tiro Deportivo":
                            curr_value = t.getSumRankValue(18);
                            break;

                        case "Vela":
                            curr_value = t.getSumRankValue(19);
                            break;

                        default:
                            curr_value = 7;
                            break;
                    }
                    double sim = this.getRandValue(best_res, last_res, curr_value);
                    t.setResult(sim);
                    this.insertValueTable(t, curr_value);
                }

            }
        } else {
            this.generateTableGE();
            try {
                for (TeamOlympic t : teams) {
                    qr = dbl.getValuesGeye(pr);
                    ArrayList<Double> ge_results = new ArrayList<>();
                    int counter = 0;
                    while (qr.next()) {

                        best_res = Double.parseDouble(qr.getString("best_res"));
                        last_res = Double.parseDouble(qr.getString("last_res"));
                        this.LblBest.setText("Best Res: " + best_res);
                        this.LblLast.setText("Worst Res: " + last_res);
                        double sim;
                        if (counter == 1) {
                            sim = this.getRandValue(last_res - 20, best_res - 20, t.getLevelteam());
                        } else {
                            sim = this.getRandValue(best_res, last_res, t.getLevelteam());
                        }

                        if (sim < 0) {
                            sim = 0.00;
                        }

                        ge_results.add(sim);
                        counter++;
                    }
                    System.out.println(ge_results);
                    this.insertValueTableGE(t, ge_results);
                }

            } catch (SQLException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }

        }

    }

    public void updateTeams() {
        String value = (String) this.BoxGame.getSelectedItem();
        ResultSet qt = null;
        if (value.equals("Londres")) {
            try {
                this.teams.clear();
                if (this.BtnCheckAll.isSelected()) {
                    qt = dbl.getTeamsOL();
                } else {
                    qt = dbl.getTeamsLondon();
                }

                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    act_sum_values.add(qt.getInt("r_atletismo"));
                    act_sum_values.add(qt.getInt("r_bmx"));
                    act_sum_values.add(qt.getInt("r_montana"));
                    act_sum_values.add(qt.getInt("r_pista"));
                    act_sum_values.add(qt.getInt("r_ruta"));
                    act_sum_values.add(qt.getInt("r_urbano"));
                    act_sum_values.add(qt.getInt("r_equitacion"));
                    act_sum_values.add(qt.getInt("r_artistica"));
                    act_sum_values.add(qt.getInt("r_trampolin"));
                    act_sum_values.add(qt.getInt("r_ritmica"));
                    act_sum_values.add(qt.getInt("r_halterofilia"));
                    act_sum_values.add(qt.getInt("r_sincronizada"));
                    act_sum_values.add(qt.getInt("r_natacion"));
                    act_sum_values.add(qt.getInt("r_patinaje"));
                    act_sum_values.add(qt.getInt("r_piraguismo"));
                    act_sum_values.add(qt.getInt("r_remo"));
                    act_sum_values.add(qt.getInt("r_saltos"));
                    act_sum_values.add(qt.getInt("r_skateboarding"));
                    act_sum_values.add(qt.getInt("r_tiro"));
                    act_sum_values.add(qt.getInt("r_vela"));

                    TeamOlympic tm = new TeamOlympic(qt.getString("team_name"), 4001.0, 0.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
                System.out.println(ex);
            }
        }

        if (value.equals("Invierno")) {
        }

        if (value.equals("Tokyo")) {
            try {
                this.teams.clear();
                if (this.BtnCheckAll.isSelected()) {
                    qt = dbl.getTeamsOL();
                } else {
                    qt = dbl.getTeamsTokyo();
                }

                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    TeamOlympic tm = new TeamOlympic(qt.getString("team_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Excitebike")) {
            try {
                this.teams.clear();
                qt = dbl.getPlayersEbike();
                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    TeamOlympic tm = new TeamOlympic(qt.getString("team_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Formula 1")) {
            try {
                this.teams.clear();
                qt = dbl.getPlayersForm();
                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    TeamOlympic tm = new TeamOlympic(qt.getString("player_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("F-Zero")) {
            try {
                this.teams.clear();
                qt = dbl.getPlayersFzero();
                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    TeamOlympic tm = new TeamOlympic(qt.getString("player_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Goldeneye")) {
            try {
                this.teams.clear();
                qt = dbl.getPlayersGeye();
                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    TeamOlympic tm = new TeamOlympic(qt.getString("player_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Mario Kart")) {
            try {
                this.teams.clear();
                qt = dbl.getPlayersMkart();
                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    TeamOlympic tm = new TeamOlympic(qt.getString("player_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("SSB")) {
            try {
                this.teams.clear();
                qt = dbl.getPlayersSsb();
                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    TeamOlympic tm = new TeamOlympic(qt.getString("player_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Muñecos")) {
            try {
                this.teams.clear();
                qt = dbl.getPlayersMun();
                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    TeamOlympic tm = new TeamOlympic(qt.getString("mun_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
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

        PanelData = new javax.swing.JPanel();
        LblGame = new javax.swing.JLabel();
        BoxGame = new javax.swing.JComboBox<>();
        LblCategoria = new javax.swing.JLabel();
        BoxCategoria = new javax.swing.JComboBox<>();
        BtnSimulate = new javax.swing.JButton();
        LblPrueba = new javax.swing.JLabel();
        BoxPrueba = new javax.swing.JComboBox<>();
        LblBest = new javax.swing.JLabel();
        LblLast = new javax.swing.JLabel();
        BtnBack = new javax.swing.JButton();
        BtnCheckAll = new javax.swing.JCheckBox();
        BtnUpdate = new javax.swing.JButton();
        BtnExport = new javax.swing.JButton();
        PanelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableSimulation = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelData.setBackground(new java.awt.Color(0, 51, 51));
        PanelData.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        PanelData.setForeground(new java.awt.Color(0, 51, 51));

        LblGame.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblGame.setForeground(new java.awt.Color(255, 255, 255));
        LblGame.setText("Juego");

        BoxGame.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BoxGame.setForeground(new java.awt.Color(255, 255, 255));
        BoxGame.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select Game>", "Londres", "Invierno", "Tokyo", "Excitebike", "Formula 1", "F-Zero", "Goldeneye", "Mario Kart", "SSB", "Muñecos" }));
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

        LblCategoria.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblCategoria.setForeground(new java.awt.Color(255, 255, 255));
        LblCategoria.setText("Categoria");

        BoxCategoria.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BoxCategoria.setForeground(new java.awt.Color(255, 255, 255));
        BoxCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BoxCategoriaItemStateChanged(evt);
            }
        });
        BoxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxCategoriaActionPerformed(evt);
            }
        });

        BtnSimulate.setBackground(new java.awt.Color(0, 102, 102));
        BtnSimulate.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnSimulate.setForeground(new java.awt.Color(255, 255, 255));
        BtnSimulate.setText("Simular");
        BtnSimulate.setBorder(null);
        BtnSimulate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimulateActionPerformed(evt);
            }
        });

        LblPrueba.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblPrueba.setForeground(new java.awt.Color(255, 255, 255));
        LblPrueba.setText("Prueba");

        BoxPrueba.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BoxPrueba.setForeground(new java.awt.Color(255, 255, 255));

        LblBest.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblBest.setForeground(new java.awt.Color(255, 255, 255));
        LblBest.setText("Best Res: ");

        LblLast.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        LblLast.setForeground(new java.awt.Color(255, 255, 255));
        LblLast.setText("Worst Res: ");

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

        BtnCheckAll.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnCheckAll.setForeground(new java.awt.Color(255, 255, 255));
        BtnCheckAll.setText("Include All");

        BtnUpdate.setBackground(new java.awt.Color(0, 102, 102));
        BtnUpdate.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        BtnUpdate.setText("Actualizar");
        BtnUpdate.setBorder(null);
        BtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdateActionPerformed(evt);
            }
        });

        BtnExport.setBackground(new java.awt.Color(0, 102, 102));
        BtnExport.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        BtnExport.setForeground(new java.awt.Color(255, 255, 255));
        BtnExport.setText("Exportar");
        BtnExport.setBorder(null);
        BtnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDataLayout = new javax.swing.GroupLayout(PanelData);
        PanelData.setLayout(PanelDataLayout);
        PanelDataLayout.setHorizontalGroup(
            PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(LblBest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BoxGame, javax.swing.GroupLayout.Alignment.LEADING, 0, 130, Short.MAX_VALUE)
                    .addComponent(LblGame, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblLast, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDataLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LblPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDataLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BoxPrueba, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDataLayout.createSequentialGroup()
                                .addComponent(BtnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BtnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnCheckAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnSimulate, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelDataLayout.setVerticalGroup(
            PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDataLayout.createSequentialGroup()
                        .addComponent(BtnSimulate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnCheckAll))
                    .addGroup(PanelDataLayout.createSequentialGroup()
                        .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDataLayout.createSequentialGroup()
                                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(LblGame)
                                    .addComponent(LblCategoria))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(BoxGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BoxPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(LblPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(LblBest)
                                .addComponent(LblLast))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDataLayout.createSequentialGroup()
                                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(BtnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BtnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))))
        );

        PanelTable.setBackground(new java.awt.Color(0, 51, 51));
        PanelTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        TableSimulation.setBackground(new java.awt.Color(0, 153, 153));
        TableSimulation.setFont(new java.awt.Font("Corbel Light", 1, 12)); // NOI18N
        TableSimulation.setForeground(new java.awt.Color(255, 255, 255));
        TableSimulation.setModel(new javax.swing.table.DefaultTableModel(
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
        TableSimulation.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(TableSimulation);

        javax.swing.GroupLayout PanelTableLayout = new javax.swing.GroupLayout(PanelTable);
        PanelTable.setLayout(PanelTableLayout);
        PanelTableLayout.setHorizontalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTableLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelTableLayout.setVerticalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoxGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxGameActionPerformed
        String value = (String) this.BoxGame.getSelectedItem();
        ResultSet qr = null;
        ResultSet qt = null;
        this.BoxCategoria.removeAllItems();
        if (value.equals("Londres")) {
            try {
                qr = dbl.getCategoriesLondon();
                while (qr.next()) {
                    this.BoxCategoria.addItem(qr.getString("class_par"));
                }
                this.teams.clear();
                if (this.BtnCheckAll.isSelected()) {
                    qt = dbl.getTeamsOL();
                } else {
                    qt = dbl.getTeamsLondon();
                }

                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    act_sum_values.add(qt.getInt("r_atletismo"));
                    act_sum_values.add(qt.getInt("r_bmx"));
                    act_sum_values.add(qt.getInt("r_montana"));
                    act_sum_values.add(qt.getInt("r_pista"));
                    act_sum_values.add(qt.getInt("r_ruta"));
                    act_sum_values.add(qt.getInt("r_urbano"));
                    act_sum_values.add(qt.getInt("r_equitacion"));
                    act_sum_values.add(qt.getInt("r_artistica"));
                    act_sum_values.add(qt.getInt("r_trampolin"));
                    act_sum_values.add(qt.getInt("r_ritmica"));
                    act_sum_values.add(qt.getInt("r_halterofilia"));
                    act_sum_values.add(qt.getInt("r_sincronizada"));
                    act_sum_values.add(qt.getInt("r_natacion"));
                    act_sum_values.add(qt.getInt("r_patinaje"));
                    act_sum_values.add(qt.getInt("r_piraguismo"));
                    act_sum_values.add(qt.getInt("r_remo"));
                    act_sum_values.add(qt.getInt("r_saltos"));
                    act_sum_values.add(qt.getInt("r_skateboarding"));
                    act_sum_values.add(qt.getInt("r_tiro"));
                    act_sum_values.add(qt.getInt("r_vela"));

                    TeamOlympic tm = new TeamOlympic(qt.getString("team_name"), 0.0, 0.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
                System.out.println(ex);
            }
        }

        if (value.equals("Invierno")) {
            try {
                qr = dbl.getCategoriesWinter();
                while (qr.next()) {
                    this.BoxCategoria.addItem(qr.getString("class_par"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Tokyo")) {
            try {
                qr = dbl.getCategoriesTokyo();
                while (qr.next()) {
                    this.BoxCategoria.addItem(qr.getString("class_par"));
                }
                this.teams.clear();
                if (this.BtnCheckAll.isSelected()) {
                    qt = dbl.getTeamsOL();
                } else {
                    qt = dbl.getTeamsTokyo();
                }

                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    TeamOlympic tm = new TeamOlympic(qt.getString("team_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Excitebike")) {
            try {
                qr = dbl.getCategoriesEbike();
                while (qr.next()) {
                    this.BoxCategoria.addItem(qr.getString("class_par"));
                }
                this.teams.clear();
                qt = dbl.getPlayersEbike();
                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    TeamOlympic tm = new TeamOlympic(qt.getString("player_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Formula 1")) {
            try {
                qr = dbl.getCategoriesForm();
                while (qr.next()) {
                    this.BoxCategoria.addItem(qr.getString("class_par"));
                }
                this.teams.clear();
                qt = dbl.getPlayersForm();
                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    TeamOlympic tm = new TeamOlympic(qt.getString("player_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("F-Zero")) {
            try {
                qr = dbl.getCategoriesFzero();
                while (qr.next()) {
                    this.BoxCategoria.addItem(qr.getString("class_par"));
                }
                this.teams.clear();
                qt = dbl.getPlayersFzero();
                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    TeamOlympic tm = new TeamOlympic(qt.getString("player_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
                System.out.println(ex);
            }
        }

        if (value.equals("Goldeneye")) {
            try {
                qr = dbl.getCategoriesGeye();
                while (qr.next()) {
                    this.BoxCategoria.addItem(qr.getString("class_par"));
                }
                this.teams.clear();
                qt = dbl.getPlayersGeye();
                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    TeamOlympic tm = new TeamOlympic(qt.getString("player_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Mario Kart")) {
            try {
                qr = dbl.getCategoriesMkart();
                while (qr.next()) {
                    this.BoxCategoria.addItem(qr.getString("class_par"));
                }
                this.teams.clear();
                qt = dbl.getPlayersMkart();
                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    TeamOlympic tm = new TeamOlympic(qt.getString("player_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("SSB")) {
            try {
                qr = dbl.getCategoriesSsb();
                while (qr.next()) {
                    this.BoxCategoria.addItem(qr.getString("class_par"));
                }
                this.teams.clear();
                qt = dbl.getPlayersSsb();
                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    TeamOlympic tm = new TeamOlympic(qt.getString("player_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Muñecos")) {
            try {
                qr = dbl.getCategoriesMun();
                while (qr.next()) {
                    this.BoxCategoria.addItem(qr.getString("class_par"));
                }
                this.teams.clear();
                qt = dbl.getPlayersMun();
                while (qt.next()) {
                    ArrayList<Integer> act_sum_values = new ArrayList<>();
                    ArrayList<Integer> act_win_values = new ArrayList<>();
                    TeamOlympic tm = new TeamOlympic(qt.getString("mun_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                    this.teams.add(tm);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }
    }//GEN-LAST:event_BoxGameActionPerformed

    private void BoxCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BoxCategoriaItemStateChanged
        String value = (String) this.BoxGame.getSelectedItem();
        String value_pr = (String) this.BoxCategoria.getSelectedItem();
        ResultSet qr = null;
        ResultSet qt = null;
        this.BoxPrueba.removeAllItems();
        if (value.equals("Londres")) {
            try {
                qr = dbl.getSportLondon(value_pr);
                while (qr.next()) {
                    this.BoxPrueba.addItem(qr.getString("name_par"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
                System.out.println(ex);
            }
        }

        if (value.equals("Invierno")) {
            try {
                qr = dbl.getSportWinter(value_pr);
                while (qr.next()) {
                    this.BoxPrueba.addItem(qr.getString("name_par"));
                }
                this.teams.clear();
                if (this.BoxCategoria.getItemCount() != 0) {
                    if (value_pr.equals("Nagano")) {
                        qt = dbl.getTeamsWinterNag();
                    }
                    if (value_pr.equals("Vancouver")) {
                        qt = dbl.getTeamsWinterVan();
                    }

                    while (qt.next()) {
                        ArrayList<Integer> act_sum_values = new ArrayList<>();
                        ArrayList<Integer> act_win_values = new ArrayList<>();
                        TeamOlympic tm = new TeamOlympic(qt.getString("team_name"), 4001.0, 4001.0, act_sum_values, act_win_values, "", "");
                        this.teams.add(tm);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
                System.out.println(ex);
            }
        }

        if (value.equals("Tokyo")) {
            try {
                qr = dbl.getSportTokyo(value_pr);
                while (qr.next()) {
                    this.BoxPrueba.addItem(qr.getString("name_par"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Excitebike")) {
            try {
                qr = dbl.getSportEbike(value_pr);
                while (qr.next()) {
                    this.BoxPrueba.addItem(qr.getString("name_par"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Formula 1")) {
            try {
                qr = dbl.getSportForm(value_pr);
                while (qr.next()) {
                    this.BoxPrueba.addItem(qr.getString("name_par"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("F-Zero")) {
            try {
                qr = dbl.getSportFzero(value_pr);
                while (qr.next()) {
                    this.BoxPrueba.addItem(qr.getString("name_par"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Goldeneye")) {
            try {
                qr = dbl.getSportGeye(value_pr);
                ArrayList<String> sports = new ArrayList<>();
                while (qr.next()) {
                    String element = qr.getString("name_par");
                    element = element.replaceAll("Pt", "");
                    element = element.replaceAll("Tm", "");
                    element = element.replaceAll("Dp", "");
                    element = element.replaceAll("Mt", "");
                    element = element.trim();
                    if (!sports.contains(element)) {
                        sports.add(element);
                        this.BoxPrueba.addItem(element);
                    }

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Mario Kart")) {
            try {
                qr = dbl.getSportMkart(value_pr);
                while (qr.next()) {
                    this.BoxPrueba.addItem(qr.getString("name_par"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("SSB")) {
            try {
                qr = dbl.getSportSsb(value_pr);
                while (qr.next()) {
                    this.BoxPrueba.addItem(qr.getString("name_par"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

        if (value.equals("Muñecos")) {
            try {
                qr = dbl.getSportMun(value_pr);
                while (qr.next()) {
                    this.BoxPrueba.addItem(qr.getString("name_par"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Exception, there was a fail on query.", "Warning", 0);
            }
        }

    }//GEN-LAST:event_BoxCategoriaItemStateChanged

    private void BoxGameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BoxGameItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxGameItemStateChanged

    private void BtnSimulateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimulateActionPerformed
        this.simulateSport();
    }//GEN-LAST:event_BtnSimulateActionPerformed

    private void BtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBackActionPerformed
        FrameInicio f = new FrameInicio();
        f.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnBackActionPerformed

    private void BoxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxCategoriaActionPerformed

    private void BtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdateActionPerformed
        this.updateTeams();
    }//GEN-LAST:event_BtnUpdateActionPerformed

    private void BtnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExportActionPerformed
        OlympicSimulatorExport f = new OlympicSimulatorExport();
        f.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnExportActionPerformed

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
            java.util.logging.Logger.getLogger(OlympicSimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OlympicSimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OlympicSimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OlympicSimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new OlympicSimulation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BoxCategoria;
    private javax.swing.JComboBox<String> BoxGame;
    private javax.swing.JComboBox<String> BoxPrueba;
    private javax.swing.JButton BtnBack;
    private javax.swing.JCheckBox BtnCheckAll;
    private javax.swing.JButton BtnExport;
    private javax.swing.JButton BtnSimulate;
    private javax.swing.JButton BtnUpdate;
    private javax.swing.JLabel LblBest;
    private javax.swing.JLabel LblCategoria;
    private javax.swing.JLabel LblGame;
    private javax.swing.JLabel LblLast;
    private javax.swing.JLabel LblPrueba;
    private javax.swing.JPanel PanelData;
    private javax.swing.JPanel PanelTable;
    private javax.swing.JTable TableSimulation;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
