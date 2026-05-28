package database;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import database.DBConnection;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 */
public class GlavnaForma extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GlavnaForma.class.getName());

    public void prikaziAktivnosti() {

    try {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM Aktivnosti";

        PreparedStatement pst = con.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        DefaultTableModel model =
                (DefaultTableModel) tabela.getModel();

        model.setRowCount(0);

        while(rs.next()) {

            model.addRow(new Object[] {
                rs.getInt("AktivnostID"),
                rs.getString("NazivAktivnosti"),
                rs.getString("Dan"),
                rs.getString("Pocetak").substring(0,5),
                rs.getString("Zavrsetak").substring(0,5)
            });

        }

    } catch(Exception e) {
        e.printStackTrace();
    }
}
    /**
     * Creates new form GlavnaForma
     */
    public GlavnaForma() {
        initComponents();
        prikaziAktivnosti();
         prikaziStatistiku();
    nacrtajGrafikon();
    }
public void prikaziStatistiku() {
    try {
        Connection con = DBConnection.getConnection();
        
        // Svi dani u nedelji sa LEFT JOIN da prikaže i one sa 0
     String sql = "SELECT d.Dan, COUNT(a.AktivnostID) AS Broj " +
             "FROM (SELECT 'Ponedeljak' AS Dan UNION ALL " +
             "      SELECT 'Utorak' UNION ALL " +
             "      SELECT 'Sreda' UNION ALL " +
             "      SELECT 'Cetvrtak' UNION ALL " +
             "      SELECT 'Petak' UNION ALL " +
             "      SELECT 'Subota' UNION ALL " +
             "      SELECT 'Nedelja') d " +
             "LEFT JOIN Aktivnosti a ON d.Dan = a.Dan " +
             "GROUP BY d.Dan " +
             "ORDER BY CASE d.Dan " +
             "  WHEN 'Ponedeljak' THEN 1 " +
             "  WHEN 'Utorak' THEN 2 " +
             "  WHEN 'Sreda' THEN 3 " +
             "  WHEN 'Cetvrtak' THEN 4 " +
             "  WHEN 'Petak' THEN 5 " +
             "  WHEN 'Subota' THEN 6 " +
             "  WHEN 'Nedelja' THEN 7 " +
             "END";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tabelaStatistika.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("Dan"),
                rs.getInt("Broj")
            });
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void nacrtajGrafikon() {
    try {
        Connection con = DBConnection.getConnection();
   String sql = "SELECT d.Dan, COUNT(a.AktivnostID) AS Broj " +
             "FROM (SELECT 'Ponedeljak' AS Dan UNION ALL " +
             "      SELECT 'Utorak' UNION ALL " +
             "      SELECT 'Sreda' UNION ALL " +
             "      SELECT 'Cetvrtak' UNION ALL " +
             "      SELECT 'Petak' UNION ALL " +
             "      SELECT 'Subota' UNION ALL " +
             "      SELECT 'Nedelja') d " +
             "LEFT JOIN Aktivnosti a ON d.Dan = a.Dan " +
             "GROUP BY d.Dan " +
             "ORDER BY CASE d.Dan " +
             "  WHEN 'Ponedeljak' THEN 1 " +
             "  WHEN 'Utorak' THEN 2 " +
             "  WHEN 'Sreda' THEN 3 " +
             "  WHEN 'Cetvrtak' THEN 4 " +
             "  WHEN 'Petak' THEN 5 " +
             "  WHEN 'Subota' THEN 6 " +
             "  WHEN 'Nedelja' THEN 7 " +
             "END";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        while (rs.next()) {
            String dan = rs.getString("Dan");
            int broj = rs.getInt("Broj");
            dataset.setValue(broj, "Broj aktivnosti", dan);
        }

        JFreeChart chart = ChartFactory.createBarChart(
            null,                 // bez naslova (kao na slici)
            null,                 // bez X labele
            null,                 // bez Y labele
            dataset
        );

        // OVO JE KLJUČNO — fiksne dimenzije
        panelGrafik.removeAll();
        panelGrafik.setLayout(new java.awt.BorderLayout());
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 250));
        
        panelGrafik.add(chartPanel, java.awt.BorderLayout.CENTER);
        panelGrafik.setPreferredSize(new java.awt.Dimension(340, 200));
        panelGrafik.validate();
        panelGrafik.repaint();

    } catch (Exception e) {
        e.printStackTrace();
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtSifra = new javax.swing.JTextField();
        txtNaziv = new javax.swing.JTextField();
        txtPocetak = new javax.swing.JTextField();
        txtZavrsetak = new javax.swing.JTextField();
        cmbDan = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaStatistika = new javax.swing.JTable();
        btnStatistika = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        panelGrafik = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtPocetak.addActionListener(this::txtPocetakActionPerformed);

        cmbDan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ponedeljak", "Utorak", "Sreda", "Cetvrtak", "Petak", "Subota", "Nedelja" }));

        jButton1.setText("Unesi");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Izadji");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jLabel3.setText("Dan u nedelji");

        jLabel4.setText("Vreme pocetka");

        jLabel1.setText("Sifra");

        jLabel5.setText("Vreme zavrsetka");

        jLabel2.setText("Naziv");

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Sifra", "Naziv", "Dan", "Pocetak", "Zavrsetak"
            }
        ));
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSifra, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtZavrsetak, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtPocetak, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDan, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSifra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPocetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtZavrsetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Aktivnost", jPanel1);

        tabelaStatistika.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Dan", "Broj dece"
            }
        ));
        tabelaStatistika.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaStatistikaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaStatistika);

        btnStatistika.setText("Prikazi");
        btnStatistika.addActionListener(this::btnStatistikaActionPerformed);

        jButton4.setText("Izadji");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        javax.swing.GroupLayout panelGrafikLayout = new javax.swing.GroupLayout(panelGrafik);
        panelGrafik.setLayout(panelGrafikLayout);
        panelGrafikLayout.setHorizontalGroup(
            panelGrafikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
        );
        panelGrafikLayout.setVerticalGroup(
            panelGrafikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnStatistika, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGrafik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelGrafik, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStatistika)
                    .addComponent(jButton4))
                .addGap(46, 46, 46))
        );

        jTabbedPane1.addTab("Statistika", jPanel2);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Prva forma – Aktivnosti\n\nPrikazuje spisak svih aktivnosti u tabeli.\nKlikom na red u tabeli, podaci se učitavaju u polja za izmenu.\nDugme Unesi dodaje novu aktivnost (sifra se dodeljuje automatski).\nDugme Izadji zatvara celu aplikaciju.\n\nDruga forma – Statistika\n\nOtvara se klikom na dugme sa ikonom grafika u alatnoj traci.\nDugme Prikazi osvezava i prikazuje tabelu sa brojem dece po danima i grafikon.\nDugme Izadji zatvara formu i vraća na početnu.\nNapomena\nBaza podataka mora biti pokrenuta pre pokretanja aplikacije.");
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("O aplikaciji", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnStatistikaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatistikaActionPerformed

    prikaziStatistiku();

    nacrtajGrafikon();
   
    }//GEN-LAST:event_btnStatistikaActionPerformed

    private void tabelaStatistikaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaStatistikaMouseClicked

    }//GEN-LAST:event_tabelaStatistikaMouseClicked

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        int red = tabela.getSelectedRow();

        txtSifra.setText(
            tabela.getValueAt(red, 0).toString()
        );

        txtNaziv.setText(
            tabela.getValueAt(red, 1).toString()
        );
        
        txtSifra.setText(tabela.getValueAt(red, 0).toString());
txtNaziv.setText(tabela.getValueAt(red, 1).toString());
cmbDan.setSelectedItem(tabela.getValueAt(red, 2).toString());
txtPocetak.setText(tabela.getValueAt(red, 3).toString());
txtZavrsetak.setText(tabela.getValueAt(red, 4).toString());
    }//GEN-LAST:event_tabelaMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO Aktivnosti "
            + "(NazivAktivnosti, Dan, Pocetak, Zavrsetak) "
            + "VALUES (?, ?, ?, ?)";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, txtNaziv.getText());
            pst.setString(2, cmbDan.getSelectedItem().toString());
            pst.setString(3, txtPocetak.getText());
            pst.setString(4, txtZavrsetak.getText());

            pst.executeUpdate();

            prikaziAktivnosti();


        } catch(Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtPocetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPocetakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPocetakActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new GlavnaForma().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStatistika;
    private javax.swing.JComboBox<String> cmbDan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel panelGrafik;
    private javax.swing.JTable tabela;
    private javax.swing.JTable tabelaStatistika;
    private javax.swing.JTextField txtNaziv;
    private javax.swing.JTextField txtPocetak;
    private javax.swing.JTextField txtSifra;
    private javax.swing.JTextField txtZavrsetak;
    // End of variables declaration//GEN-END:variables
}
