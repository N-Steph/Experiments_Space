import java.sql.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.awt.event.*;

public class DatabaseManager {
    private static String dbms = "sqlite";
    private static String dbName = "./db/inventory.db";

    public static Connection getConnection() throws SQLException {

        Connection conn = null;
        
        if (DatabaseManager.dbms.equals("sqlite")) {
            conn = DriverManager.getConnection(
                "jdbc:" + DatabaseManager.dbms + ":" +
                DatabaseManager.dbName
            );
        }
        System.out.println("Connected to database");
        return conn;
    }

    public static void searchItem(Connection con, String item) throws SQLException {
        String query = "Select * FROM Items WHERE name=?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) { // Statement object represents SQL statement
            pstmt.setString(1, item);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String id = rs.getString("id");
                String category = rs.getString("category");
                String supplier = rs.getString("supplier");
                double price = rs.getDouble("price");
                System.out.println(name + ", " + supplier + ", " + price);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public static void addRowTable(Connection con, String[] values) throws SQLException {
        String query = "INSERT INTO Items VALUES(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, values[0]);
            pstmt.setString(2, values[1]);
            if (values[2].length() == 0) {
                pstmt.setString(3, "null");
            }
            else {
                pstmt.setInt(3, Integer.parseInt(values[2]));
            }
            pstmt.setString(4, values[3]);
            pstmt.setString(5, values[4]);
            if (values[2].length() == 0) {
                pstmt.setString(6, "null");
            }
            else {
                pstmt.setDouble(6, Double.parseDouble(values[5]));
            }
            pstmt.executeUpdate();
            
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        con.close();
    }

    // public static void main(String[] args) {
    //     try {
    //         Connection conn = new DatabaseManager().getConnection();
    //         DatabaseManager.viewTable(conn);
    //         conn.close();
    //     } catch (SQLException e) {
    //         System.out.println(e.getMessage());
    //     }
        
        // JFrame frame = new JFrame("Display table");
        // JButton button = new JButton("click");
        // TableModel dataModel = new AbstractTableModel() {
        //     public int getColumnCount() { return 10;}
        //     public int getRowCount() { return 10;}
        //     public Object getValueAt(int row, int col) { return Integer.valueOf(row*col);}
        // };
        // JTable table = new JTable(dataModel);
        // JScrollPane scrollpane = new JScrollPane(table);
        // frame.add(scrollpane);
        // // button.addActionListener(new ButtonListener());
        // frame.add(button, BorderLayout.SOUTH);
        // frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // frame.setSize(500, 500);
        // frame.setLocationRelativeTo(null);
        // frame.setVisible(true);
    // }

    // class ButtonListener implements ActionListener {
    //     public void actionPerformed(ActionEvent e) {

    //     }
    // }
}