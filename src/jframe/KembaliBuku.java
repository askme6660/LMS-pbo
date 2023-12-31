/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;


import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author arkan
 */
public class KembaliBuku extends javax.swing.JFrame {

    /**
     * Creates new form PinjamBuku
     */
    public KembaliBuku() {
        initComponents();
    }
    
    //mengambil value dari database untuk ditampilkan
    public void getBookDetails(){
        int idbuku = Integer.parseInt(txt_id_buku.getText());
        int idsiswa = Integer.parseInt(txt_nis.getText());
        
        try {
            Connection con = DBconnect.getConnection();
            String sql = "select * from pinjam_buku where id_buku = ? and id_siswa = ? and status = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idbuku);
            pst.setInt(2, idsiswa);
            pst.setString(3, "pending");
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                lbl_idpinjam.setText(rs.getString("id_pinjam"));
                lbl_judulbuku.setText(rs.getString("judul"));
                lbl_namasiswa.setText(rs.getString("nama_siswa"));
                lbl_tglpinjam.setText(rs.getString("tanggal_pinjam"));
                lbl_tglbalik.setText(rs.getString("tanggal_kembali"));
                lbl_bukueror1.setText("");
                }else{
                lbl_bukueror1.setText("Tidak dapat menemukan Peminjaman");
                lbl_idpinjam.setText("");
                lbl_judulbuku.setText("");
                lbl_namasiswa.setText("");
                lbl_tglpinjam.setText("");
                lbl_tglbalik.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // set status telah kembali
    public boolean bukuKembali(){
        boolean isReturn = false;
        int idbuku = Integer.parseInt(txt_id_buku.getText());
        int idsiswa = Integer.parseInt(txt_nis.getText());
        
        try {
            Connection con = DBconnect.getConnection();
            String sql = "update pinjam_buku set status = ? where id_siswa = ? and id_buku = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "dikembalikan");
            pst.setInt(2, idsiswa);
            pst.setInt(3, idbuku);
            pst.setString(4, "pending");
            
            int RowCount = pst.executeUpdate();
            
            if (RowCount > 0) {
                isReturn = true;
                
            }else{
                isReturn = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isReturn;
    }
    
    
    //update jumlah buku
    public void updatequantity(){
        int idbuku = Integer.parseInt(txt_id_buku.getText());
        try {
            Connection con = DBconnect.getConnection();
            String sql = "update buku set jumlah = jumlah + 1 where id_buku = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idbuku);
            
            int RowCount = pst.executeUpdate();
            
            if (RowCount > 0) {
                JOptionPane.showMessageDialog(this, "jumlah buku diupdate");
                
                
            }else{
                JOptionPane.showMessageDialog(this, "update jumlah buku gagal");
            }
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

        main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_tglbalik = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_idpinjam = new javax.swing.JLabel();
        lbl_judulbuku = new javax.swing.JLabel();
        lbl_namasiswa = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbl_bukueror1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbl_tglpinjam = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_id_buku = new app.bolivia.swing.JCTextField();
        txt_nis = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        jLabel20 = new javax.swing.JLabel();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        main.setBackground(new java.awt.Color(255, 255, 255));
        main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Kembali");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Detail Buku yang dikembalikan");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, -1, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Judul :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 60, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nama Siswa :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, -1, -1));

        lbl_tglbalik.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lbl_tglbalik.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_tglbalik, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 520, 140, 20));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Id Peminjaman :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        lbl_idpinjam.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lbl_idpinjam.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_idpinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 140, 20));

        lbl_judulbuku.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lbl_judulbuku.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_judulbuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 140, 20));

        lbl_namasiswa.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lbl_namasiswa.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_namasiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 140, 20));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tanggal Kembali :");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, -1, -1));

        lbl_bukueror1.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lbl_bukueror1.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(lbl_bukueror1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 580, 270, 20));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Tanggal Pinjam :");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, -1, -1));

        lbl_tglpinjam.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lbl_tglpinjam.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_tglpinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, 140, 20));

        main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 810));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 255));
        jLabel13.setText("Kembalikan Buku");
        main.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 160, 200, 30));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel7.setText("ID buku");
        main.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 240, 200, 20));

        txt_id_buku.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)));
        txt_id_buku.setPlaceholder("Masukkan ID buku");
        txt_id_buku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_id_bukuFocusLost(evt);
            }
        });
        main.add(txt_id_buku, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, -1, -1));

        txt_nis.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)));
        txt_nis.setPlaceholder("Masukkan NIS");
        txt_nis.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_nisFocusLost(evt);
            }
        });
        main.add(txt_nis, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 350, -1, -1));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel10.setText("NIS Siswa");
        main.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 320, -1, -1));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(255, 0, 51));
        rSMaterialButtonRectangle1.setText("KEMBALIKAN");
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });
        main.add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 570, 240, -1));

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 51, 51));
        jLabel20.setText("X");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        main.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, -1, -1));

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(153, 153, 255));
        rSMaterialButtonRectangle2.setText("CARI");
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });
        main.add(rSMaterialButtonRectangle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 480, 240, -1));

        getContentPane().add(main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 800));

        setSize(new java.awt.Dimension(990, 803));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        AdminDasboard home = new AdminDasboard();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txt_id_bukuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_id_bukuFocusLost
        
    }//GEN-LAST:event_txt_id_bukuFocusLost

    private void txt_nisFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nisFocusLost
        
    }//GEN-LAST:event_txt_nisFocusLost

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        if (bukuKembali() == true ) {
            JOptionPane.showMessageDialog(this, "Buku berhasil dikembalikan"); 
            updatequantity();
        }else{
            JOptionPane.showMessageDialog(this, "Buku gagal dikembalikan");
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        getBookDetails();
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

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
            java.util.logging.Logger.getLogger(KembaliBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KembaliBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KembaliBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KembaliBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KembaliBuku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_bukueror1;
    private javax.swing.JLabel lbl_idpinjam;
    private javax.swing.JLabel lbl_judulbuku;
    private javax.swing.JLabel lbl_namasiswa;
    private javax.swing.JLabel lbl_tglbalik;
    private javax.swing.JLabel lbl_tglpinjam;
    private javax.swing.JPanel main;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private app.bolivia.swing.JCTextField txt_id_buku;
    private app.bolivia.swing.JCTextField txt_nis;
    // End of variables declaration//GEN-END:variables
}
