package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class QLSinhVien extends javax.swing.JFrame {

    private Socket socket;
    private DataOutputStream out;

    public QLSinhVien() {
        initComponents();
        loadSV();
    }
    
    public void loadSV(){
        DefaultTableModel dtm = (DefaultTableModel) table_sinhVien.getModel();
        dtm.setNumRows(0);
        Connection ketNoi = layKetNoi();
        Vector vt;
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM SV");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getInt("Id"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getString("Class"));
                vt.add(rs.getString("Address"));
                vt.add(rs.getString("Gender"));
                vt.add(rs.getString("Phone"));
                dtm.addRow(vt);
            }
            table_sinhVien.setModel(dtm);
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QLSinhVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection layKetNoi() {
        Connection ketNoi = null;
        String uRL = "jdbc:sqlserver://;databaseName=SINHVIEN";
        String userName = "sa";
        String password = "123";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            ketNoi = DriverManager.getConnection(uRL, userName, password);
            System.out.println("Kết nối thành công!");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Kết nối không thành công!!");
        }
        return ketNoi;
    }

    private void themSV(int maSV, String ten, String lop, String diaChi, String gioiTinh, String sdt) {
        Connection ketNoi = layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO SV VALUES (?,?,?,?,?,?)");
            ps.setInt(1, maSV);
            ps.setString(2, ten);
            ps.setString(3, lop);
            ps.setString(4, diaChi);
            ps.setString(5, gioiTinh);
            ps.setString(6, sdt);
            ps.executeUpdate();
            out.writeUTF("INSERT INTO SV VALUES (" + maSV + ", N'" + ten + "', N'" + lop + "', N'" + diaChi + "', N'" + gioiTinh + "', N'" + sdt + "')");
            out.flush();
        } catch (Exception e) {
            Logger.getLogger(QLSinhVien.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void xoaSV(int maSV) {
        Connection ketNoi = layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("DELETE FROM SV WHERE Id = ?");
            ps.setInt(1, maSV);
            ps.executeUpdate();
            out.writeUTF("DELETE FROM SV WHERE Id = " + maSV);
            out.flush();
        } catch (Exception e) {
            Logger.getLogger(QLSinhVien.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void suaSV(int maSV, String ten, String lop, String diaChi, String gioiTinh, String sdt) {
        Connection ketNoi = layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("UPDATE SV SET Name = ?, Class = ?, Address = ?, Gender = ?, Phone = ? where Id = ?");
            ps.setString(1, ten);
            ps.setString(2, lop);
            ps.setString(3, diaChi);
            ps.setString(4, gioiTinh);
            ps.setString(5, sdt);
            ps.setInt(6, maSV);
            ps.executeUpdate();
            out.writeUTF("UPDATE SV SET Name = N'" + ten + "', Class = N'" + lop + "', Address = N'" + diaChi + "', Gender = N'" + gioiTinh + "', Phone = N'" + sdt + "' where Id = " + maSV);
            out.flush();
        } catch (Exception e) {
            Logger.getLogger(QLSinhVien.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfd_maSV = new javax.swing.JTextField();
        tfd_ten = new javax.swing.JTextField();
        tfd_lop = new javax.swing.JTextField();
        tfd_diaChi = new javax.swing.JTextField();
        tfd_gioiTinh = new javax.swing.JTextField();
        tfd_dienThoai = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_sinhVien = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtIp = new javax.swing.JTextField();
        btnConnect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ SINH VIÊN");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Mã sinh viên:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tên:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Lớp:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Địa chỉ:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Giới tính:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Điện thoại:");

        btn_them.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_xoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_xoa.setText("Xóa");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_sua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Danh sách sinh viên");

        table_sinhVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sinh viên", "Tên", "Lớp", "Địa chỉ", "Giới tính", "Điện thoại"
            }
        ));
        jScrollPane1.setViewportView(table_sinhVien);

        jLabel9.setText("IP");

        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConnect)
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfd_maSV, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfd_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfd_lop, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfd_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfd_gioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfd_dienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(334, 334, 334)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnConnect)
                    .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfd_maSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfd_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfd_lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(tfd_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5)))
                                    .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(tfd_gioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(tfd_dienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        String maSV = tfd_maSV.getText();
        String ten = tfd_ten.getText();
        String lop = tfd_lop.getText();
        String diaChi = tfd_diaChi.getText();
        String gioiTinh = tfd_gioiTinh.getText();
        String sdt = tfd_dienThoai.getText();
        boolean kt = true;
        String mau = "";

        mau = "[0-9]{1,5}";
        if (maSV.matches(mau) == false) {
            kt = false;
        }
        if(ten.equals("") == true){
            kt = false;
        }
        if(lop.equals("") == true){
            kt = false;
        }
        if(diaChi.equals("") == true){
            kt = false;
        }
        if(gioiTinh.equals("Nữ") && gioiTinh.equals("Nam")){
            kt = false;
        }
        mau = "[0-9]{9,11}";
        if (sdt.matches(mau) == false) {
            kt = false;
        }
        if (kt == false) {
            JOptionPane.showMessageDialog(this, "Thông tin nhập vào không chính xác!");
        } else {
            themSV(Integer.parseInt(maSV), ten, lop, diaChi, gioiTinh, sdt);
            JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công!");
            loadSV();
        }

    }//GEN-LAST:event_btn_themActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        try {
            socket = new Socket(txtIp.getText().trim(), 9999);
            out = new DataOutputStream(socket.getOutputStream());
            String temp = "";
            out.writeUTF(temp);
            out.flush();
            JOptionPane.showMessageDialog(this, "Kết nối thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConnectActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        String maSV = tfd_maSV.getText();
        boolean kt = true;
        if(maSV.equals("") == true){
            kt = false;
        }
        String mau = "[0-9]{1,5}";
        if (maSV.matches(mau) == false) {
            kt = false;
        }
        if (kt == true) {
            xoaSV(Integer.parseInt(maSV));
            JOptionPane.showMessageDialog(this, "Xóa sinh viên thành công!");
            loadSV();
        }
        else{
            JOptionPane.showMessageDialog(this, "Thông tin nhập vào không chính xác!");
        }
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        String maSV = tfd_maSV.getText();
        String ten = tfd_ten.getText();
        String lop = tfd_lop.getText();
        String diaChi = tfd_diaChi.getText();
        String gioiTinh = tfd_gioiTinh.getText();
        String sdt = tfd_dienThoai.getText();
        boolean kt = true;
        String mau = "";

        mau = "[0-9]{1,5}";
        if (maSV.matches(mau) == false) {
            kt = false;
        }
        if(ten.equals("") == true){
            kt = false;
        }
        if(lop.equals("") == true){
            kt = false;
        }
        if(diaChi.equals("") == true){
            kt = false;
        }
        if(gioiTinh.equals("Nữ") && gioiTinh.equals("Nam")){
            kt = false;
        }
        mau = "[0-9]{9,11}";
        if (sdt.matches(mau) == false) {
            kt = false;
        }
        if (kt == true) {
            suaSV(Integer.parseInt(maSV), ten, lop, diaChi, gioiTinh, sdt);
            JOptionPane.showMessageDialog(this, "Sửa sinh viên thành công!");
            loadSV();
        }
        else{
            JOptionPane.showMessageDialog(this, "Thông tin nhập vào không chính xác!");
        }
    }//GEN-LAST:event_btn_suaActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLSinhVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_sinhVien;
    private javax.swing.JTextField tfd_diaChi;
    private javax.swing.JTextField tfd_dienThoai;
    private javax.swing.JTextField tfd_gioiTinh;
    private javax.swing.JTextField tfd_lop;
    private javax.swing.JTextField tfd_maSV;
    private javax.swing.JTextField tfd_ten;
    private javax.swing.JTextField txtIp;
    // End of variables declaration//GEN-END:variables
}
