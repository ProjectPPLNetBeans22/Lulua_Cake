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
public class mUbahStatus extends javax.swing.JInternalFrame {

    /**
     * Creates new form mUbahStatus
     */
    JDesktopPane desktop;
    public mUbahStatus(JDesktopPane desktop) {
        initComponents();
        this.desktop = desktop;

        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        
        btnSimpan.setBackground(new Color(0,0,0,0));
        btnSimpan.setOpaque(false);
        
        btnKembali.setBackground(new Color(0,0,0,0));
        btnKembali.setOpaque(false);
        
        getOrderDikerjakan();
        getOrderBelumDiAmbil();
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
    
    public void bersih() {
        tNoOrder.setText("");
        tStatusPesanan.setText("");
        tComboStatus.setSelectedIndex(0);
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
        btnSimpan = new javax.swing.JPanel();
        btnKembali = new javax.swing.JPanel();
        tStatusPesanan = new javax.swing.JTextField();
        tNoOrder = new javax.swing.JTextField();
        tComboStatus = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        tOrderSelesai = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tOrderBelumDiAmbil = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tOrderDikerjakan = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        tCari = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tSortBy = new javax.swing.JComboBox<>();

        setMinimumSize(new java.awt.Dimension(845, 690));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(845, 690));
        jPanel1.setPreferredSize(new java.awt.Dimension(845, 690));
        jPanel1.setLayout(null);

        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnSimpanLayout = new javax.swing.GroupLayout(btnSimpan);
        btnSimpan.setLayout(btnSimpanLayout);
        btnSimpanLayout.setHorizontalGroup(
            btnSimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 88, Short.MAX_VALUE)
        );
        btnSimpanLayout.setVerticalGroup(
            btnSimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(btnSimpan);
        btnSimpan.setBounds(162, 370, 88, 30);

        btnKembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKembaliMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnKembaliLayout = new javax.swing.GroupLayout(btnKembali);
        btnKembali.setLayout(btnKembaliLayout);
        btnKembaliLayout.setHorizontalGroup(
            btnKembaliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        btnKembaliLayout.setVerticalGroup(
            btnKembaliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(btnKembali);
        btnKembali.setBounds(697, 32, 70, 30);

        tStatusPesanan.setEditable(false);
        tStatusPesanan.setBackground(new java.awt.Color(215, 215, 215));
        tStatusPesanan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tStatusPesanan.setBorder(null);
        tStatusPesanan.setDisabledTextColor(new java.awt.Color(215, 215, 215));
        jPanel1.add(tStatusPesanan);
        tStatusPesanan.setBounds(165, 287, 130, 27);

        tNoOrder.setBackground(new java.awt.Color(248, 248, 245));
        tNoOrder.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tNoOrder.setBorder(null);
        tNoOrder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tNoOrderKeyReleased(evt);
            }
        });
        jPanel1.add(tNoOrder);
        tNoOrder.setBounds(165, 249, 100, 25);

        tComboStatus.setBackground(new java.awt.Color(204, 204, 204));
        tComboStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tComboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Status -", "Dipesan", "Dikerjakan", "Belum Di Ambil", "Selesai" }));
        jPanel1.add(tComboStatus);
        tComboStatus.setBounds(159, 327, 143, 27);

        jPanel4.setBackground(new java.awt.Color(75, 53, 36));

        tOrderSelesai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tOrderSelesai.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tOrderSelesai, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tOrderSelesai, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(559, 125, 100, 30);

        jPanel3.setBackground(new java.awt.Color(75, 53, 36));

        tOrderBelumDiAmbil.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tOrderBelumDiAmbil.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tOrderBelumDiAmbil, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tOrderBelumDiAmbil, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(314, 125, 100, 30);

        jPanel2.setBackground(new java.awt.Color(75, 53, 36));

        tOrderDikerjakan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tOrderDikerjakan.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tOrderDikerjakan, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tOrderDikerjakan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(66, 125, 100, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Form Ubah Status.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(50, 30, 720, 370);

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
        jScrollPane1.setBounds(50, 450, 730, 170);

        tCari.setBackground(new java.awt.Color(248, 248, 245));
        tCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tCariKeyReleased(evt);
            }
        });
        jPanel1.add(tCari);
        tCari.setBounds(610, 410, 170, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Cari :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(570, 410, 31, 30);

        tSortBy.setBackground(new java.awt.Color(204, 204, 204));
        tSortBy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tSortBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih -", "Dipesan", "Dikerjakan", "Belum Di Ambil", "Selesai" }));
        tSortBy.setBorder(null);
        tSortBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSortByActionPerformed(evt);
            }
        });
        jPanel1.add(tSortBy);
        tSortBy.setBounds(417, 410, 130, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 845, 690);

        setBounds(0, 0, 845, 690);
    }// </editor-fold>//GEN-END:initComponents

    private void btnKembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKembaliMouseClicked
        // TODO add your handling code here:
        mDetailPesanan mDPsn = new mDetailPesanan(desktop);
        desktop.add(mDPsn);
        mDPsn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKembaliMouseClicked

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

    private void tSortByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSortByActionPerformed
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
                            + "GROUP BY orders.ID_orders HAVING transaksi.status_pesanan = 'Dipesan'";
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
                            + "GROUP BY orders.ID_orders HAVING transaksi.status_pesanan = 'Dikerjakan'";
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
                            + "GROUP BY orders.ID_orders HAVING transaksi.status_pesanan = 'Belum Di Ambil'";
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
                            + "GROUP BY orders.ID_orders HAVING transaksi.status_pesanan = 'Selesai'";
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
    }//GEN-LAST:event_tSortByActionPerformed

    private void tNoOrderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNoOrderKeyReleased
        // TODO add your handling code here:
        if (tNoOrder.getText().isEmpty()) {
            tStatusPesanan.setText("");
            tComboStatus.setSelectedIndex(0);
        } else {
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
                            + "GROUP BY orders.ID_orders HAVING orders.ID_orders = '"+ tNoOrder.getText() +"'";
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
                        tStatusPesanan.setText(res.getString("transaksi.status_pesanan"));
                        tabel.setModel(tbl);
                    }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Data Gagal Diambil");
                System.out.println(ex);
            }   
        }
    }//GEN-LAST:event_tNoOrderKeyReleased

    private void btnSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseClicked
        // TODO add your handling code here:
        if (tComboStatus.getSelectedItem() == "- Pilih Status -") {
            JOptionPane.showMessageDialog(rootPane, "Pilih Status Terbaru!");
        } else {
            try {
                String sql = "UPDATE transaksi SET status_pesanan = '"+ tComboStatus.getSelectedItem() +"' WHERE ID_orders = '"+ tNoOrder.getText() +"'";
                Statement stat = Koneksi.GetConnection().createStatement();
                stat.execute(sql);
                JOptionPane.showMessageDialog(rootPane, "Status Pesanan Berhasil Di Update!");
                dataTabel();
                bersih();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Status Pesanan Gagal Di Update!");
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_btnSimpanMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnKembali;
    private javax.swing.JPanel btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tCari;
    private javax.swing.JComboBox<String> tComboStatus;
    private javax.swing.JTextField tNoOrder;
    private javax.swing.JLabel tOrderBelumDiAmbil;
    private javax.swing.JLabel tOrderDikerjakan;
    private javax.swing.JLabel tOrderSelesai;
    private javax.swing.JComboBox<String> tSortBy;
    private javax.swing.JTextField tStatusPesanan;
    private javax.swing.JTable tabel;
    // End of variables declaration//GEN-END:variables
}
