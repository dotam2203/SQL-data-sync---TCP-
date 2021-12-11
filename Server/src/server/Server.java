package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Server extends javax.swing.JFrame {

    public Server() {
        initComponents();
        loadSV();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        btnStartServer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_sinhVien = new javax.swing.JTable();

        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(list);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("Server");

        btnStartServer.setText("Start Server");
        btnStartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartServerActionPerformed(evt);
            }
        });

        txt.setEditable(false);
        txt.setColumns(20);
        txt.setRows(5);
        jScrollPane1.setViewportView(txt);

        table_sinhVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sinh viên", "Tên", "Lớp", "Địa chỉ", "Giới tính", "Điện thoại"
            }
        ));
        jScrollPane5.setViewportView(table_sinhVien);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(539, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(562, 562, 562))
            .addGroup(layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnStartServer)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane5)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btnStartServer)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private ServerSocket server;
    private DataInputStream in;
    private DataOutputStream out;
    private DefaultListModel mod = new DefaultListModel();
    private void btnStartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartServerActionPerformed
        loadSV();
        list.setModel(mod);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    server = new ServerSocket(9999);
                    txt.append("Server stating ...\n");
                    Socket s = server.accept();
                    in = new DataInputStream(s.getInputStream());
                    String text = in.readUTF();
                    while (true) {
                        text = in.readUTF();
                        Connection ketNoi = layKetNoi();
                        try {
                            PreparedStatement ps = ketNoi.prepareStatement(text);
                            ps.executeUpdate();
                            loadSV();
                            ps.close();
                            ketNoi.close();
                        } catch (Exception e) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
                        }
                        mod.addElement(text);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(Server.this, e, "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        }).start();
    }//GEN-LAST:event_btnStartServerActionPerformed

    public void loadSV() {
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
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
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
            System.out.println("Kết nối thất bại!!");
        }
        return ketNoi;
    }
    
    private void listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseClicked
       
    }//GEN-LAST:event_listMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JList<String> list;
    private javax.swing.JTable table_sinhVien;
    private javax.swing.JTextArea txt;
    // End of variables declaration//GEN-END:variables
}
