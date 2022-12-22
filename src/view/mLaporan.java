/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import com.toedter.calendar.JYearChooser;
import database.Koneksi;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author hafid
 */
public class mLaporan extends javax.swing.JInternalFrame {

    /**
     * Creates new form mLaporan
     */
    private int hargaSatuan = 0;
    NumberFormat nf = NumberFormat.getNumberInstance(new Locale("in", "ID"));
    
    public mLaporan() {
        initComponents();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        
        jYearChooser1.setHorizontalAlignment((int) JYearChooser.LEFT_ALIGNMENT);
        
        getKaryawan();
        getOrderBerlangsung();
        getTotalTransaksi();
        getOrderSelesai();
        
        dataTabel();
    }
    
    public void getKaryawan() {
        try {
            Statement stat = Koneksi.GetConnection().createStatement();
            ResultSet res = stat.executeQuery("SELECT COUNT(pegawai.ID_pegawai) AS jumlah FROM pegawai");
            if(res.next()) {
                tKaryawan.setText(res.getString("jumlah"));
            } else {
                JOptionPane.showMessageDialog(rootPane, "Data Pegawai Gagal Di Ambil!");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void getOrderBerlangsung() {
        try {
            String sql = "SELECT COUNT(transaksi.ID_transaksi) AS orderBerlangsung FROM transaksi WHERE status_pesanan = 'Dipesan'";
            Statement stat = Koneksi.GetConnection().createStatement();
            ResultSet res = stat.executeQuery(sql);
            if(res.next()) {
                tOrderBerlangsung.setText(res.getString("orderBerlangsung"));
            } else {
                JOptionPane.showMessageDialog(rootPane, "Data Orderan Berlangsung Gagal Di Ambil!");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void getTotalTransaksi() {
        try {
            String sql = "SELECT COUNT(transaksi.ID_transaksi) AS transaksi FROM transaksi";
            Statement stat = Koneksi.GetConnection().createStatement();
            ResultSet res = stat.executeQuery(sql);
            if(res.next()) {
                tTotalTransaksi.setText(res.getString("transaksi"));
            } else {
                JOptionPane.showMessageDialog(rootPane, "Data Total Transaksi Gagal Di Ambil!");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void getOrderSelesai() {
        try {
            String sql = "SELECT COUNT(transaksi.ID_transaksi) AS orderSelesai FROM transaksi WHERE status_pesanan = 'Selesai'";
            Statement stat = Koneksi.GetConnection().createStatement();
            ResultSet res = stat.executeQuery(sql);
            if(res.next()) {
                tOrderSelesai.setText(res.getString("orderSelesai"));
            } else {
                JOptionPane.showMessageDialog(rootPane, "Data Orderan Selesai Gagal Di Ambil!");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void dataTabel() {
        DefaultTableModel tbl = new DefaultTableModel() {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
                
        tbl.addColumn("Nama Admin");
        tbl.addColumn("Total Transaksi");
        tbl.addColumn("Total Kue");
        tbl.addColumn("Total Pendapatan");
        tabel.setModel(tbl);

        try {
            String sql =  "SELECT pegawai.nama_pegawai, COUNT(transaksi.ID_transaksi) AS totalTransaksi, transaksi.tgl_pesan, "
                        + "SUM(detail_transaksi.total_kue) AS totalKue, SUM(detail_transaksi.harga_total) AS totalPendapatan "
                        + "FROM transaksi, detail_transaksi, produk, pegawai "
                        + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                        + "AND produk.kode_produk = detail_transaksi.kode_produk "
                        + "AND pegawai.ID_pegawai = transaksi.ID_pegawai GROUP BY pegawai.nama_pegawai";
            Statement stat = Koneksi.GetConnection().createStatement();
            ResultSet res = stat.executeQuery(sql);
            while(res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("pegawai.nama_pegawai"),
                    res.getString("totalTransaksi"),
                    res.getString("totalKue"),
                    nf.format(Integer.parseInt(res.getString("totalPendapatan"))),
                });
                tabel.setModel(tbl);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
            System.out.println(ex);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        tOrderSelesai = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        tTotalTransaksi = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tOrderBerlangsung = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tKaryawan = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        tBulan = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(845, 690));
        setPreferredSize(new java.awt.Dimension(845, 690));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel5.setBackground(new java.awt.Color(75, 53, 36));

        tOrderSelesai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tOrderSelesai.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tOrderSelesai, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tOrderSelesai, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5);
        jPanel5.setBounds(631, 122, 100, 30);

        jPanel4.setBackground(new java.awt.Color(75, 53, 36));

        tTotalTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tTotalTransaksi.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tTotalTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tTotalTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(446, 122, 100, 30);

        jPanel3.setBackground(new java.awt.Color(75, 53, 36));

        tOrderBerlangsung.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tOrderBerlangsung.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tOrderBerlangsung, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tOrderBerlangsung, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(261, 122, 100, 30);

        jPanel2.setBackground(new java.awt.Color(75, 53, 36));

        tKaryawan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tKaryawan.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tKaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tKaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(76, 122, 100, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Form Laporan.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(59, 33, 720, 133);

        tabel.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabel);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(58, 300, 730, 240);

        tBulan.setBackground(new java.awt.Color(204, 204, 204));
        tBulan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tBulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Bulan -", "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));
        tBulan.setBorder(null);
        jPanel1.add(tBulan);
        tBulan.setBounds(440, 250, 120, 30);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jButton1.setText("Cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(718, 250, 69, 30);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setText("Tahun :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(580, 250, 50, 30);

        jYearChooser1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(jYearChooser1);
        jYearChooser1.setBounds(630, 250, 70, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Bulan :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(390, 250, 50, 30);

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Cetak Laporan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(658, 555, 130, 33);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 850, 710);

        setBounds(0, 0, 861, 733);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (tBulan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Mohon Pilih Bulan Terlebih Dahulu");
        } else {
            DefaultTableModel tbl = new DefaultTableModel() {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
                
        tbl.addColumn("Nama Admin");
        tbl.addColumn("Total Transaksi");
        tbl.addColumn("Total Kue");
        tbl.addColumn("Total Pendapatan");
        tabel.setModel(tbl);

        try {
            String sql =  "SELECT pegawai.nama_pegawai, COUNT(transaksi.ID_transaksi) AS totalTransaksi, transaksi.tgl_pesan, "
                        + "SUM(detail_transaksi.total_kue) AS totalKue, SUM(detail_transaksi.harga_total) AS totalPendapatan "
                        + "FROM transaksi, detail_transaksi, produk, pegawai "
                        + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                        + "AND produk.kode_produk = detail_transaksi.kode_produk "
                        + "AND pegawai.ID_pegawai = transaksi.ID_pegawai GROUP BY pegawai.nama_pegawai "
                        + "HAVING DATE_FORMAT(transaksi.tgl_pesan,'%m') = '"+ tBulan.getSelectedIndex() +"' AND DATE_FORMAT(transaksi.tgl_pesan, '%Y') = '"+ jYearChooser1.getYear() +"'";
            Statement stat = Koneksi.GetConnection().createStatement();
            ResultSet res = stat.executeQuery(sql);
            while(res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("pegawai.nama_pegawai"),
                    res.getString("totalTransaksi"),
                    res.getString("totalKue"),
                    nf.format(Integer.parseInt(res.getString("totalPendapatan"))),
                });
                tabel.setModel(tbl);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
            System.out.println(ex);
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        java.sql.Connection con = null;
        if (tBulan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Mohon Pilih Bulan Terlebih Dahulu");
        } else {
            try {
                String jdbcDriver = "com.mysql.jdbc.Driver";
                Class.forName(jdbcDriver);

                String url = "jdbc:mysql://localhost:3306/toko_kue";
                String user = "root";
                String pass = "";

                con = DriverManager.getConnection(url, user, pass);
                Statement stat = con.createStatement();

                try {
                    String report = "C:\\Users\\hafid\\OneDrive\\Documents\\NetBeansProjects\\TOKO KUE\\src\\report\\Laporan.jrxml";
                    HashMap hash = new HashMap();
                    hash.put("bulan", tBulan.getSelectedIndex());
                    hash.put("tahun", jYearChooser1.getYear());

                    JasperReport JsRpt = JasperCompileManager.compileReport(report);
                    JasperPrint jsPrint = JasperFillManager.fillReport(JsRpt, hash, con);
                    JasperViewer.viewReport(jsPrint, false);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JComboBox<String> tBulan;
    private javax.swing.JLabel tKaryawan;
    private javax.swing.JLabel tOrderBerlangsung;
    private javax.swing.JLabel tOrderSelesai;
    private javax.swing.JLabel tTotalTransaksi;
    private javax.swing.JTable tabel;
    // End of variables declaration//GEN-END:variables
}
