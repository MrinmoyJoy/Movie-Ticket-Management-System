/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Booking extends javax.swing.JFrame {

    public Booking() {
        initComponents();
        connectDB();
        Customerinfo();
        Theatre();
        Seat();
        Show();
        Bookingtable();

    }
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;

    public void connectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=t2;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT Price FROM Tickets");

            while (resultSet.next()) {

                System.out.println(" NAME:"
                        + resultSet.getString("Price"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Theatre() {

        try {
            pst = connection.prepareStatement("Select  TheatreID from Theatre");
            rs = pst.executeQuery();
            txttid.removeAllItems();

            while (rs.next()) {

                txttid.addItem(rs.getString("TheatreID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Theatre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Customerinfo() {

        try {
            pst = connection.prepareStatement("Select  CID from Customer");
            rs = pst.executeQuery();
            txtcid.removeAllItems();

            while (rs.next()) {

                txtcid.addItem(rs.getString("CID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customerinfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Seat() {

        try {
            pst = connection.prepareStatement("Select SeatID from Seat where Occupied='no' ");
            rs = pst.executeQuery();
            txtsid.removeAllItems();

            while (rs.next()) {

                txtsid.addItem(rs.getString("SeatID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Seat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Show() {

        try {
            pst = connection.prepareStatement("Select ShowID from Show ");
            rs = pst.executeQuery();
            txtswid.removeAllItems();

            while (rs.next()) {

                txtswid.addItem(rs.getString("ShowID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Show.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Bookingtable() {

        try {
            pst = connection.prepareStatement("select   Tickets.TicketId, Customer.CName, Customer.Email, Theatre.TheatreName,Tickets.SeatID, Show.ShowTime, Movie.MovieName, Tickets.Price\n"
                    + " from Tickets\n"
                    + " inner join Customer_Ticket on Tickets.TicketID=Customer_Ticket.TicketID\n"
                    + " inner join Customer on Customer.CID=Customer_Ticket.CID\n"
                    + " inner join Show on Tickets.ShowID=Show.ShowID\n"
                    + " inner join Movie on Show.MovieID=Movie.MovieID\n"
                    + " inner join Theatre on Tickets.TheatreID=Theatre.TheatreId order by TicketID");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData RSM = rs.getMetaData();
            int c;
            c = RSM.getColumnCount();
            DefaultTableModel DF = (DefaultTableModel) jTable1.getModel();
            DF.setRowCount(0);
            while (rs.next()) {
                Vector v2;
                v2 = new Vector();
                for (int i = 1; i <= c; i++) {
                    v2.add(rs.getString("TicketId"));
                    v2.add(rs.getString("CName"));
                    v2.add(rs.getString("Email"));
                    v2.add(rs.getString("TheatreName"));
                    v2.add(rs.getString("SeatID"));
                    v2.add(rs.getString("ShowTime"));
                    v2.add(rs.getString("MovieName"));
                    v2.add(rs.getString("Price"));
                }
                DF.addRow(v2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtcid = new javax.swing.JComboBox<>();
        txttid = new javax.swing.JComboBox<>();
        txtsid = new javax.swing.JComboBox<>();
        txtswid = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtr = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("BOOKING");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Price :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Seat_ID         :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Theatre_ID   :");

        txtcid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcidActionPerformed(evt);
            }
        });

        txttid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttidActionPerformed(evt);
            }
        });

        txtswid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtswidActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Show_ID         :");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TicketId", "Customer Name", "Customer Email", "TheatreName", "SeatNo", "ShowTime", "MovieName", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("BACK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Customer_ID:");

        jButton3.setText("Change");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtr.setColumns(20);
        txtr.setRows(5);
        jScrollPane2.setViewportView(txtr);

        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Print");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Exit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(480, 480, 480)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(9, 9, 9)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtswid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txttid, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtcid, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(175, 175, 175)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtp, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(345, 345, 345)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5)
                                .addGap(18, 18, 18)
                                .addComponent(jButton6))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1207, 1207, 1207))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton5)
                            .addComponent(jButton6)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtcid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel2)
                                    .addComponent(txtp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txttid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtsid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtswid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(38, 38, 38)
                                .addComponent(jButton1))
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1452, 813));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtcidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcidActionPerformed

    private void txtswidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtswidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtswidActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
        page pt = new page();
        pt.show();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        //    String sid = txtsid.getText();
        String cust = txtcid.getSelectedItem().toString();
        String theatre = txttid.getSelectedItem().toString();
        String seat = txtsid.getSelectedItem().toString();
        String show = txtswid.getSelectedItem().toString();
        String price = txtp.getText();

        try {
            pst = connection.prepareStatement("insert into Tickets values(?,?,?,?);  insert into Customer_Ticket values(?,(SELECT max(TicketId) FROM Tickets));  Update Seat Set Occupied='yes' where SeatID = ? ");

            pst.setString(1, theatre);
            pst.setString(2, seat);
            pst.setString(3, show);
            pst.setString(4, price);
            pst.setString(5, cust);
            pst.setString(6, seat);


            //pst = connection.prepareStatement("insert into Customer_Ticket values(?,(SELECT max(TicketId) FROM Tickets))");

            


            int k = pst.executeUpdate();
            
            txtr.append(


        "\t\t Receipt \n\n"+

             "  \n======================================\n "+
                "Customer ID:\t\t\t"+txtcid.getSelectedItem().toString()+"\n\n"+
                  "Theatre ID:\t\t\t"+txttid.getSelectedItem().toString()+"\n\n"+
                  "Seat ID:\t\t\t"+txtsid.getSelectedItem().toString()+"\n\n"+
                  "Show ID:\t\t\t"+txtswid.getSelectedItem().toString()+"\n\n"+
                            "Price :\t\t\t"+txtp.getText()+"\n"+
                "=========================================="








        );

            if (k == 1) {
                JOptionPane.showMessageDialog(this, "Record Adddeddd");

                txtcid.setSelectedIndex(0);
                txttid.setSelectedIndex(0);
                txtsid.setSelectedIndex(0);
                txtswid.setSelectedIndex(0);
                txtp.setText("500");

                Bookingtable();
            } else {
                JOptionPane.showMessageDialog(this, "Record Failed");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try { System.out.println(txttid.getSelectedItem().toString());
            if (txttid.getSelectedItem().toString().equals("1")) {

            pst = connection.prepareStatement("Select SeatID from Seat where  SeatID like '1%' "); 
            }//txttid mane theatre id
             if (txttid.getSelectedItem().toString().equals("2")) {

            pst = connection.prepareStatement("Select SeatID from Seat where  SeatID like '2%' "); 
            } if (txttid.getSelectedItem().toString().equals("3")) {

            pst = connection.prepareStatement("Select SeatID from Seat where  SeatID like '3%' "); 
            } if (txttid.getSelectedItem().toString().equals("4")) {

            pst = connection.prepareStatement("Select SeatID from Seat where  SeatID like '4%' "); 
            } if (txttid.getSelectedItem().toString().equals("5")) {

            pst = connection.prepareStatement("Select SeatID from Seat where  SeatID like '5%' "); 
            } if (txttid.getSelectedItem().toString().equals("6")) {

            pst = connection.prepareStatement("Select SeatID from Seat where  SeatID like '6%' "); 
            } if (txttid.getSelectedItem().toString().equals("7")) {

            pst = connection.prepareStatement("Select SeatID from Seat where  SeatID like '7%' "); 
            }



            System.out.println(txttid.getSelectedItem().toString());
            rs = pst.executeQuery();
            txtsid.removeAllItems();

            while (rs.next()) {

                txtsid.addItem(rs.getString("SeatID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txtr.setText("");
               txtcid.setSelectedIndex(0);
               txttid.setSelectedIndex(0);
               txtsid.setSelectedIndex(0);
               txtswid.setSelectedIndex(0);
               txtp.setText("");
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
        try {
            txtr.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private JFrame frame;
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
        frame=new JFrame();
    if(JOptionPane.showConfirmDialog(frame,"confirm if u want to exit","Print System",
            JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
        System.exit(0);}
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txttidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttidActionPerformed

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
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Booking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> txtcid;
    private javax.swing.JTextField txtp;
    private javax.swing.JTextArea txtr;
    private javax.swing.JComboBox<String> txtsid;
    private javax.swing.JComboBox<String> txtswid;
    private javax.swing.JComboBox<String> txttid;
    // End of variables declaration//GEN-END:variables
}
