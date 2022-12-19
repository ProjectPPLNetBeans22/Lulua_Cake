/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import database.Koneksi;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hafid
 */
public class mDetailPesanan extends javax.swing.JInternalFrame {

    /**
     * Creates new form mDetailPesanan
     */
    JDesktopPane desktop;
    public mDetailPesanan(JDesktopPane desktop) {
        initComponents();
        this.desktop = desktop;
        
        btnKueTart.setBackground(new Color(0,0,0,0));
        btnKueTart.setOpaque(false);
        
        btnKueCupCake.setBackground(new Color(0,0,0,0));
        btnKueCupCake.setOpaque(false);
        
        btnKuePudding.setBackground(new Color(0,0,0,0));
        btnKuePudding.setOpaque(false);
        
        btnKueBrownies.setBackground(new Color(0,0,0,0));
        btnKueBrownies.setOpaque(false);
        
        btnStatusPesanan.setBackground(new Color(0,0,0,0));
        btnStatusPesanan.setOpaque(false);
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        
        getOrderBelumDiAmbil();
        getOrderDikerjakan();
        getOrderSelesai();
        
        dataTabel();
    }
    
    public void getOrderDikerjakan() {
        try {
            String sql = "SELECT COUNT(transaksi.ID_transaksi) as Jumlah FROM transaksi WHERE status_pesanan = 'Dikerjakan'";
            Statement stat = Koneksi.GetConnection().createStatement();
            ResultSet res = stat.executeQuery(sql);
            if (res.next()) {
                tOrderDikerjakan.setText(res.getString("Jumlah"));
            } else {
                JOptionPane.showMessageDialog(rootPane, "Data Order Gagal Di Ambil");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void getOrderBelumDiAmbil() {
        try {
            String sql = "SELECT COUNT(transaksi.ID_transaksi) as Jumlah FROM transaksi WHERE status_pesanan = 'Belum Di Ambil'";
            Statement stat = Koneksi.GetConnection().createStatement();
            ResultSet res = stat.executeQuery(sql);
            if (res.next()) {
                tOrderBelumDiAmbil.setText(res.getString("Jumlah"));
            } else {
                JOptionPane.showMessageDialog(rootPane, "Data Order Gagal Di Ambil");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void getOrderSelesai() {
        try {
            String sql = "SELECT COUNT(transaksi.ID_transaksi) as Jumlah FROM transaksi WHERE status_pesanan = 'Selesai'";
            Statement stat = Koneksi.GetConnection().createStatement();
            ResultSet res = stat.executeQuery(sql);
            if (res.next()) {
                tOrderSelesai.setText(res.getString("Jumlah"));
            } else {
                JOptionPane.showMessageDialog(rootPane, "Data Order Gagal Di Ambil");
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
                
        tbl.addColumn("ID Orders");
        tbl.addColumn("Nama Pemesan");
        tbl.addColumn("Jenis Kue");
        tbl.addColumn("Detail Kue");
        tbl.addColumn("Tanggal Pesan");
        tbl.addColumn("Tanggal Ambil");
        tbl.addColumn("Status");
        tabel.setModel(tbl);
        tabel.getColumnModel().getColumn(0).setMaxWidth(100);
        tabel.getColumnModel().getColumn(3).setMinWidth(100);

        try {
            String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                        + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                        + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                        + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                        + "AND orders.ID_orders = transaksi.ID_orders "
                        + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                        + "AND produk.kode_produk = detail_transaksi.kode_produk "
                        + "GROUP BY orders.ID_orders";
            Statement stat = Koneksi.GetConnection().createStatement();
            ResultSet res = stat.executeQuery(sql);
            while(res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("orders.ID_orders"),
                    res.getString("orders.nama_pemesan"),
                    res.getString("produk.nama_produk"),
                    res.getString("detailKue"),
                    res.getString("tanggalPesan"),
                    res.getString("tanggalAmbil"),
                    res.getString("transaksi.status_pesanan"),
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
        jPanel4 = new javax.swing.JPanel();
        tOrderSelesai = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tOrderBelumDiAmbil = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tOrderDikerjakan = new javax.swing.JLabel();
        btnKueBrownies = new javax.swing.JPanel();
        btnKuePudding = new javax.swing.JPanel();
        btnStatusPesanan = new javax.swing.JPanel();
        btnKueCupCake = new javax.swing.JPanel();
        btnKueTart = new javax.swing.JPanel();
        tCari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tSortBy = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(845, 690));
        setPreferredSize(new java.awt.Dimension(845, 690));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(845, 690));
        jPanel1.setPreferredSize(new java.awt.Dimension(845, 690));
        jPanel1.setLayout(null);

        jPanel4.setBackground(new java.awt.Color(75, 53, 36));

        tOrderSelesai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tOrderSelesai.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tOrderSelesai, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tOrderSelesai, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(565, 125, 120, 30);

        jPanel3.setBackground(new java.awt.Color(75, 53, 36));

        tOrderBelumDiAmbil.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tOrderBelumDiAmbil.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tOrderBelumDiAmbil, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tOrderBelumDiAmbil, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(320, 125, 120, 30);

        jPanel2.setBackground(new java.awt.Color(75, 53, 36));

        tOrderDikerjakan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tOrderDikerjakan.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tOrderDikerjakan, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tOrderDikerjakan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(75, 125, 120, 30);

        btnKueBrownies.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKueBrownies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKueBrowniesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnKueBrowniesLayout = new javax.swing.GroupLayout(btnKueBrownies);
        btnKueBrownies.setLayout(btnKueBrowniesLayout);
        btnKueBrowniesLayout.setHorizontalGroup(
            btnKueBrowniesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        btnKueBrowniesLayout.setVerticalGroup(
            btnKueBrowniesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 125, Short.MAX_VALUE)
        );

        jPanel1.add(btnKueBrownies);
        btnKueBrownies.setBounds(622, 237, 150, 125);

        btnKuePudding.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKuePudding.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKuePuddingMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnKuePuddingLayout = new javax.swing.GroupLayout(btnKuePudding);
        btnKuePudding.setLayout(btnKuePuddingLayout);
        btnKuePuddingLayout.setHorizontalGroup(
            btnKuePuddingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        btnKuePuddingLayout.setVerticalGroup(
            btnKuePuddingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 125, Short.MAX_VALUE)
        );

        jPanel1.add(btnKuePudding);
        btnKuePudding.setBounds(432, 237, 150, 125);

        btnStatusPesanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStatusPesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStatusPesananMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnStatusPesananLayout = new javax.swing.GroupLayout(btnStatusPesanan);
        btnStatusPesanan.setLayout(btnStatusPesananLayout);
        btnStatusPesananLayout.setHorizontalGroup(
            btnStatusPesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        btnStatusPesananLayout.setVerticalGroup(
            btnStatusPesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(btnStatusPesanan);
        btnStatusPesanan.setBounds(653, 36, 118, 30);

        btnKueCupCake.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKueCupCake.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKueCupCakeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnKueCupCakeLayout = new javax.swing.GroupLayout(btnKueCupCake);
        btnKueCupCake.setLayout(btnKueCupCakeLayout);
        btnKueCupCakeLayout.setHorizontalGroup(
            btnKueCupCakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        btnKueCupCakeLayout.setVerticalGroup(
            btnKueCupCakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 125, Short.MAX_VALUE)
        );

        jPanel1.add(btnKueCupCake);
        btnKueCupCake.setBounds(242, 237, 150, 125);

        btnKueTart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKueTart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKueTartMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnKueTartLayout = new javax.swing.GroupLayout(btnKueTart);
        btnKueTart.setLayout(btnKueTartLayout);
        btnKueTartLayout.setHorizontalGroup(
            btnKueTartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        btnKueTartLayout.setVerticalGroup(
            btnKueTartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 125, Short.MAX_VALUE)
        );

        jPanel1.add(btnKueTart);
        btnKueTart.setBounds(57, 237, 150, 125);

        tCari.setBackground(new java.awt.Color(248, 248, 245));
        tCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tCariKeyReleased(evt);
            }
        });
        jPanel1.add(tCari);
        tCari.setBounds(610, 410, 170, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Form Detail Pesanan.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(54, 30, 720, 336);

        tSortBy.setBackground(new java.awt.Color(204, 204, 204));
        tSortBy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tSortBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih -", "Dipesan", "Dikerjakan", "Belum Di Ambil", "Selesai" }));
        tSortBy.setBorder(null);
        jPanel1.add(tSortBy);
        tSortBy.setBounds(50, 410, 150, 30);

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabel);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(50, 460, 730, 170);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Cari :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(570, 410, 31, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 845, 690);

        setBounds(0, 0, 845, 690);
    }// </editor-fold>//GEN-END:initComponents

    private void tCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCariKeyReleased
        // TODO add your handling code here:
        DefaultTableModel tbl = new DefaultTableModel() {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
                
        tbl.addColumn("ID Orders");
        tbl.addColumn("Nama Pemesan");
        tbl.addColumn("Jenis Kue");
        tbl.addColumn("Detail Kue");
        tbl.addColumn("Tanggal Pesan");
        tbl.addColumn("Tanggal Ambil");
        tbl.addColumn("Status");
        tabel.setModel(tbl);
        tabel.getColumnModel().getColumn(0).setMaxWidth(100);
        tabel.getColumnModel().getColumn(3).setMinWidth(100);

        try {
            String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                        + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                        + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                        + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                        + "AND orders.ID_orders = transaksi.ID_orders "
                        + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                        + "AND produk.kode_produk = detail_transaksi.kode_produk "
                        + "GROUP BY orders.ID_orders HAVING orders.ID_orders LIKE '%"+ tCari.getText() +"%' OR orders.nama_pemesan LIKE '%"+ tCari.getText() +"%'";
            Statement stat = Koneksi.GetConnection().createStatement();
            ResultSet res = stat.executeQuery(sql);
            while(res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("orders.ID_orders"),
                    res.getString("orders.nama_pemesan"),
                    res.getString("produk.nama_produk"),
                    res.getString("detailKue"),
                    res.getString("tanggalPesan"),
                    res.getString("tanggalAmbil"),
                    res.getString("transaksi.status_pesanan"),
                });
                tabel.setModel(tbl);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
            System.out.println(ex);
        }
    }//GEN-LAST:event_tCariKeyReleased

    private void btnKueTartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKueTartMouseClicked
        // TODO add your handling code here:
         if (tSortBy.getSelectedItem() == "Dipesan") {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Kue Tart' AND transaksi.status_pesanan = 'Dipesan'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        } else if (tSortBy.getSelectedItem() == "Dikerjakan") {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                 String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Kue Tart' AND transaksi.status_pesanan = 'Dikerjakan'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        } else if (tSortBy.getSelectedItem() == "Belum Di Ambil") {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Kue Tart' AND transaksi.status_pesanan = 'Belum Di Ambil'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        } else if (tSortBy.getSelectedItem() == "Selesai") {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
               String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Kue Tart' AND transaksi.status_pesanan = 'Selesai'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        } else if (tSortBy.getSelectedIndex() == 0) {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Kue Tart'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        }                         
    }//GEN-LAST:event_btnKueTartMouseClicked

    private void btnKueCupCakeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKueCupCakeMouseClicked
        // TODO add your handling code here:
        if (tSortBy.getSelectedItem() == "Dipesan") {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Cup Cake' AND transaksi.status_pesanan = 'Dipesan'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        } else if (tSortBy.getSelectedItem() == "Dikerjakan") {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Cup Cake' AND transaksi.status_pesanan = 'Dikerjakan'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        } else if (tSortBy.getSelectedItem() == "Belum Di Ambil") {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                 String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Cup Cake' AND transaksi.status_pesanan = 'Belum Di Ambil'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        } else if (tSortBy.getSelectedItem() == "Selesai") {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                 String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Cup Cake' AND transaksi.status_pesanan = 'Selesai'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        } else if (tSortBy.getSelectedIndex() == 0) {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                 String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Cup Cake'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        }                                              
    }//GEN-LAST:event_btnKueCupCakeMouseClicked

    private void btnKuePuddingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKuePuddingMouseClicked
        // TODO add your handling code here:
        if (tSortBy.getSelectedItem() == "Dipesan") {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                 String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Puding' AND transaksi.status_pesanan = 'Dipesan'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        } else if (tSortBy.getSelectedItem() == "Dikerjakan") {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                 String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Puding' AND transaksi.status_pesanan = 'Dikerjakan'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_Orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        } else if (tSortBy.getSelectedItem() == "Belum Di Ambil") {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                 String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Puding' AND transaksi.status_pesanan = 'Belum Di Ambil'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        } else if (tSortBy.getSelectedItem() == "Selesai") {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                 String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Puding' AND transaksi.status_pesanan = 'Selesai'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        } else if (tSortBy.getSelectedIndex() == 0) {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                                        
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                 String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Puding'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_btnKuePuddingMouseClicked

    private void btnKueBrowniesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKueBrowniesMouseClicked
        // TODO add your handling code here:
        if (tSortBy.getSelectedItem() == "Dipesan") {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Fudgy Brownies' AND transaksi.status_pesanan = 'Dipesan'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        } else if (tSortBy.getSelectedItem() == "Dikerjakan") {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };   
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                 String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Fudgy Brownies' AND transaksi.status_pesanan = 'Dikerjakan'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        } else if (tSortBy.getSelectedItem() == "Belum Di Ambil") {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
                    
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                 String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Fudgy Brownies' AND transaksi.status_pesanan = 'Belum Di Ambil'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        } else if (tSortBy.getSelectedItem() == "Selesai") {
            DefaultTableModel tbl = new DefaultTableModel(){
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
            
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                 String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Fudgy Brownies' AND transaksi.status_pesanan = 'Selesai'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        } else if (tSortBy.getSelectedIndex() == 0) {
            DefaultTableModel tbl = new DefaultTableModel() {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
            
            tbl.addColumn("ID Orders");
            tbl.addColumn("Nama Pemesan");
            tbl.addColumn("Jenis Kue");
            tbl.addColumn("Detail Kue");
            tbl.addColumn("Tanggal Pesan");
            tbl.addColumn("Tanggal Ambil");
            tbl.addColumn("Status");
            tabel.setModel(tbl);
            tabel.getColumnModel().getColumn(0).setMaxWidth(100);
            tabel.getColumnModel().getColumn(3).setMinWidth(100);

            try {
                 String sql = "SELECT orders.ID_orders, orders.nama_pemesan, produk.nama_produk, detail_produk.nama_produk AS detailKue, "
                            + "DATE_FORMAT(tgl_pesan, '%d-%m-%Y') AS tanggalPesan, DATE_FORMAT(tgl_ambil, '%d-%m-%Y') AS tanggalAmbil, transaksi.status_pesanan "
                            + "FROM transaksi, detail_transaksi, detail_produk, orders, produk "
                            + "WHERE transaksi.ID_transaksi = detail_transaksi.ID_transaksi "
                            + "AND orders.ID_orders = transaksi.ID_orders "
                            + "AND detail_produk.kode_sub_produk = detail_transaksi.kode_sub_produk "
                            + "AND produk.kode_produk = detail_transaksi.kode_produk "
                            + "GROUP BY orders.ID_orders HAVING produk.nama_produk = 'Fudgy Brownies'";
                Statement stat = Koneksi.GetConnection().createStatement();
                ResultSet res = stat.executeQuery(sql);
                while(res.next()) {
                    tbl.addRow(new Object[]{
                        res.getString("orders.ID_orders"),
                        res.getString("orders.nama_pemesan"),
                        res.getString("produk.nama_produk"),
                        res.getString("detailKue"),
                        res.getString("tanggalPesan"),
                        res.getString("tanggalAmbil"),
                        res.getString("transaksi.status_pesanan"),
                    });
                    tabel.setModel(tbl);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_btnKueBrowniesMouseClicked

    private void btnStatusPesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStatusPesananMouseClicked
        // TODO add your handling code here:
        mUbahStatus mUS = new mUbahStatus(desktop);
        desktop.add(mUS);
        this.dispose();
        mUS.setVisible(true);
    }//GEN-LAST:event_btnStatusPesananMouseClicked
                                                                     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnKueBrownies;
    private javax.swing.JPanel btnKueCupCake;
    private javax.swing.JPanel btnKuePudding;
    private javax.swing.JPanel btnKueTart;
    private javax.swing.JPanel btnStatusPesanan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tCari;
    private javax.swing.JLabel tOrderBelumDiAmbil;
    private javax.swing.JLabel tOrderDikerjakan;
    private javax.swing.JLabel tOrderSelesai;
    private javax.swing.JComboBox<String> tSortBy;
    private javax.swing.JTable tabel;
    // End of variables declaration//GEN-END:variables
}
