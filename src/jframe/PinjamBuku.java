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
public class PinjamBuku extends javax.swing.JFrame {

    /**
     * Creates new form PinjamBuku
     */
    public PinjamBuku() {
        initComponents();
    }
    
    //ambil data buku dari database
    public void getBookdetail(){
        int idbuku = Integer.parseInt(txt_id_buku.getText());
        
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pst= con.prepareStatement("select * from buku where id_buku = ?");
            pst.setInt(1, idbuku);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {                
                lbl_idbuku.setText(rs.getString("id_buku"));
                lbl_judulbuku.setText(rs.getString("judul"));
                lbl_pengarangbuku.setText(rs.getString("pengarang"));
                lbl_jumlahbuku.setText(rs.getString("jumlah"));
            }else{
                    lbl_bukueror1.setText("Id buku tidak ditemukan");
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //ambil data siswa dari database
    public void getSiswadetail(){
        int idsiswa = Integer.parseInt(txt_nis.getText());
        
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement pst= con.prepareStatement("select * from siswa where id_siswa = ?");
            pst.setInt(1, idsiswa);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {                
                lbl_idsiswa.setText(rs.getString("id_siswa"));
                lbl_namasiswa.setText(rs.getString("nama_siswa"));
                lbl_kelas.setText(rs.getString("kelas"));
                lbl_absen.setText(rs.getString("absen"));
            }else {
                lbl_siswaeror.setText("Id siswa tidak ditemukan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //memasukkan value pinjam buku ke database
    
    public boolean pinjembuku(){
        boolean isIssued = false;
        int idbuku = Integer.parseInt(txt_id_buku.getText());
        int idsiswa = Integer.parseInt(txt_nis.getText());
        String judul = lbl_judulbuku.getText();
        String nama = lbl_namasiswa.getText();
        
        java.util.Date uIssueDate = date_pinjam.getDatoFecha();
        java.util.Date uDueDate = date_kembali.getDatoFecha();
        
        Long l1 = uIssueDate.getTime();
        long l2 = uDueDate.getTime();
        
        java.sql.Date sIssueDate = new java.sql.Date(l1);
        java.sql.Date sDueDate = new java.sql.Date(l2);
        
        try {
            Connection con = DBconnect.getConnection();
            String sql = "insert into pinjam_buku(id_buku,judul, id_siswa, nama_siswa,"
                    + "tanggal_pinjam, tanggal_kembali, status) values(?,?,?,?,?,?,?)";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setInt(1,idbuku);
                    pst.setString(2, judul);
                    pst.setInt(3, idsiswa);
                    pst.setString(4, nama);
                    pst.setDate(5, sIssueDate);
                    pst.setDate(6, sDueDate);
                    pst.setString(7, "pending");
                    
                    int rowCount = pst.executeUpdate();
                    if (rowCount > 0){
                        isIssued = true;
                    }else{
                        isIssued = false;
                    }
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isIssued;
    }
    
    //update jumlah buku
    public void updatequantity(){
        int idbuku = Integer.parseInt(txt_id_buku.getText());
        try {
            Connection con = DBconnect.getConnection();
            String sql = "update buku set jumlah = jumlah -1 where id_buku = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idbuku);
            
            int RowCount = pst.executeUpdate();
            
            if (RowCount > 0) {
                JOptionPane.showMessageDialog(this, "jumlah buku diupdate");
                int initialCount = Integer.parseInt(lbl_jumlahbuku.getText());
                lbl_jumlahbuku.setText(Integer.toString(initialCount - 1));
            }else{
                JOptionPane.showMessageDialog(this, "update jumlah buku gagal");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //cek buku
    public boolean isAlreadyIssued(){
        boolean isAlreadyIssued = false;
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
                isAlreadyIssued = true;
            }else{
                isAlreadyIssued = false;
            }
                   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  isAlreadyIssued;
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
        lbl_jumlahbuku = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_idbuku = new javax.swing.JLabel();
        lbl_judulbuku = new javax.swing.JLabel();
        lbl_pengarangbuku = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbl_bukueror1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_absen = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbl_idsiswa = new javax.swing.JLabel();
        lbl_namasiswa = new javax.swing.JLabel();
        lbl_kelas = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbl_siswaeror = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_id_buku = new app.bolivia.swing.JCTextField();
        txt_nis = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        date_pinjam = new rojeru_san.componentes.RSDateChooser();
        jLabel10 = new javax.swing.JLabel();
        date_kembali = new rojeru_san.componentes.RSDateChooser();
        jLabel11 = new javax.swing.JLabel();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        jLabel20 = new javax.swing.JLabel();

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
        jLabel2.setText("Detail Buku yang dipinjam");
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
        jLabel6.setText("Pengarang :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, -1, -1));

        lbl_jumlahbuku.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lbl_jumlahbuku.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_jumlahbuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, 140, 20));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Id buku :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        lbl_idbuku.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lbl_idbuku.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_idbuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 140, 20));

        lbl_judulbuku.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lbl_judulbuku.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_judulbuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 140, 20));

        lbl_pengarangbuku.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lbl_pengarangbuku.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_pengarangbuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 140, 20));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Jumlah :");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, -1, -1));

        lbl_bukueror1.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lbl_bukueror1.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(lbl_bukueror1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 540, 270, 20));

        main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 810));

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Absen :");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, -1, -1));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, -1, -1));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Nama Siswa :");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 90, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Kelas :");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, -1, -1));

        lbl_absen.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lbl_absen.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_absen, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, 140, 20));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("NIS Siswa :");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        lbl_idsiswa.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lbl_idsiswa.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_idsiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 140, 20));

        lbl_namasiswa.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lbl_namasiswa.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_namasiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 140, 20));

        lbl_kelas.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lbl_kelas.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_kelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 140, 20));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Detail Siswa yang meminjam");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        lbl_siswaeror.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lbl_siswaeror.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lbl_siswaeror, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 540, 270, 20));

        main.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 420, 810));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 255));
        jLabel13.setText("Pinjam Buku");
        main.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 160, 160, 30));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel7.setText("ID buku");
        main.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 240, 200, 20));

        txt_id_buku.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)));
        txt_id_buku.setPlaceholder("Masukkan ID buku");
        txt_id_buku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_id_bukuFocusLost(evt);
            }
        });
        main.add(txt_id_buku, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, -1, -1));

        txt_nis.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)));
        txt_nis.setPlaceholder("Masukkan NIS");
        txt_nis.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_nisFocusLost(evt);
            }
        });
        main.add(txt_nis, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 350, -1, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel9.setText("Tanggal Peminjaman");
        main.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 400, -1, -1));

        date_pinjam.setColorBackground(new java.awt.Color(153, 153, 255));
        date_pinjam.setColorForeground(new java.awt.Color(153, 153, 255));
        date_pinjam.setPlaceholder("Pilih tanggal peminjaman");
        main.add(date_pinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 430, -1, -1));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel10.setText("NIS Siswa");
        main.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 320, -1, -1));

        date_kembali.setColorBackground(new java.awt.Color(153, 153, 255));
        date_kembali.setColorForeground(new java.awt.Color(153, 153, 255));
        date_kembali.setPlaceholder("Pilih tanggal pengembalian");
        main.add(date_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 520, -1, -1));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel11.setText("Tanggal Pengembalian");
        main.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 490, -1, -1));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(153, 153, 255));
        rSMaterialButtonRectangle1.setText("PINJAM");
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });
        main.add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 600, 240, -1));

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 51, 51));
        jLabel20.setText("X");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        main.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 0, -1, -1));

        getContentPane().add(main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 800));

        setSize(new java.awt.Dimension(1411, 803));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        AdminDasboard home = new AdminDasboard();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txt_id_bukuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_id_bukuFocusLost
        if(!txt_id_buku.getText().equals("")){
            getBookdetail();
        }
    }//GEN-LAST:event_txt_id_bukuFocusLost

    private void txt_nisFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nisFocusLost
        if (!txt_nis.getText().equals("")){
            getSiswadetail();
        }
    }//GEN-LAST:event_txt_nisFocusLost

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        if (lbl_jumlahbuku.getText().equals("0")) {
            JOptionPane.showMessageDialog(this, "buku ini tidak tersedia");
            
        }else{
        
        if (isAlreadyIssued() == false) {
            if (pinjembuku() == true) {
            JOptionPane.showMessageDialog(this, "buku berhasil dipinjam");
            updatequantity();
        }else{
            JOptionPane.showMessageDialog(this, "buku gagal dipinjam");
        }
        }else{
            JOptionPane.showMessageDialog(this, "siswa sudah meminjam buku ini!");
        }
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel20MouseClicked

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
            java.util.logging.Logger.getLogger(PinjamBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PinjamBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PinjamBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PinjamBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PinjamBuku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_kembali;
    private rojeru_san.componentes.RSDateChooser date_pinjam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_absen;
    private javax.swing.JLabel lbl_bukueror1;
    private javax.swing.JLabel lbl_idbuku;
    private javax.swing.JLabel lbl_idsiswa;
    private javax.swing.JLabel lbl_judulbuku;
    private javax.swing.JLabel lbl_jumlahbuku;
    private javax.swing.JLabel lbl_kelas;
    private javax.swing.JLabel lbl_namasiswa;
    private javax.swing.JLabel lbl_pengarangbuku;
    private javax.swing.JLabel lbl_siswaeror;
    private javax.swing.JPanel main;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private app.bolivia.swing.JCTextField txt_id_buku;
    private app.bolivia.swing.JCTextField txt_nis;
    // End of variables declaration//GEN-END:variables
}
