import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddItemPanel extends JPanel {
    String[] labels = {"save", "cancel", 
        "Item name", "Item Category", "Item Quantity", "Item ID",
        "Supplier", "Price", "Add New Item to Canteen"
    };
    String imagePath = "D:\\Projects\\IctInventoryManagementSystem\\src\\pngtree.jpg";
    JButton saveButton = new JButton(labels[0]);
    JButton cancelButton = new JButton(labels[1]);
    JLabel itemName = new JLabel(labels[2]);
    JLabel itemCat = new JLabel(labels[3]);
    JLabel itemQt = new JLabel(labels[4]);
    JLabel itemId = new JLabel(labels[5]);
    JLabel supplier = new JLabel(labels[6]);
    JLabel price = new JLabel(labels[7]);
    JTextField inputItemName = new JTextField(15);
    JTextField inputItemCat = new JTextField(15);
    JTextField inputItemQt = new JTextField( 15);
    JTextField inputItemId = new JTextField(15);
    JTextField inputSupplier = new JTextField(15);
    JTextField inputPrice = new JTextField(15);
    JLabel panelTitle = new JLabel(labels[8]);
    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
    Color lineColor = new Color(0, 0, 0);

    public AddItemPanel() {
        initialization();
    }

    public void initialization() {
        JPanel topPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(700, 120);
            }

            @Override
            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };

        cancelButton.addActionListener(new BackToHomeListener());
        saveButton.addActionListener(new SaveButtonListener());
        
        ImagePanel imagePanel = new ImagePanel();
        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        
        panelTitle.setFont(font);
        panelTitle.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagLayout gridBag = new GridBagLayout();
        topPanel.setLayout(gridBag);
        GridBagConstraints c = new GridBagConstraints();
        
        // Image panel constraints
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.2;
        c.weighty = 1.0;
        c.ipadx = 10;
        c.ipady = 20;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 5, 5);
        topPanel.add(imagePanel, c);
        
        // Title label constraints
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.8;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 5, 5);
        topPanel.add(panelTitle, c);

        topPanel.setBorder(BorderFactory.createLineBorder(lineColor));
        
        middlePanel.setLayout(new GridLayout(3, 2, 5, 0));
        bottomPanel.add(saveButton);
        bottomPanel.add(cancelButton);

        JPanel innerPanel1 = new JPanel();
        JPanel innerPanel2 = new JPanel();
        JPanel innerPanel3 = new JPanel();
        JPanel innerPanel4 = new JPanel();
        JPanel innerPanel5 = new JPanel();
        JPanel innerPanel6 = new JPanel();
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        innerPanel1.add(itemName);
        innerPanel1.add(inputItemName);
        middlePanel.add(innerPanel1);

        innerPanel2.add(itemCat);
        innerPanel2.add(inputItemCat);
        middlePanel.add(innerPanel2);

        innerPanel3.add(itemQt);
        innerPanel3.add(inputItemQt);
        middlePanel.add(innerPanel3);
        
        innerPanel4.add(itemId);
        innerPanel4.add(inputItemId);
        middlePanel.add(innerPanel4);
        
        innerPanel5.add(supplier);
        innerPanel5.add(inputSupplier);
        middlePanel.add(innerPanel5);

        innerPanel6.add(price);
        innerPanel6.add(inputPrice);
        middlePanel.add(innerPanel6);
        
        this.add(topPanel);
        this.add(middlePanel);
        this.add(bottomPanel);
    }

    class ImagePanel extends JPanel {
        private Image image = null;
        public ImagePanel() {
            image =  new ImageIcon(imagePath).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        }
        public void  paintComponent(Graphics g) {
            super.paintComponent(g);
            
            if (image != null) {
                g.drawImage(image, 0, 0, this);
            }
            
        }
    }

    class BackToHomeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout) (Main.cards.getLayout());
            cl.previous(Main.cards);
        }
    }

    class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String[] newData = {
                inputItemName.getText(),
                inputItemCat.getText(),
                inputItemQt.getText(),
                inputItemId.getText(),
                supplier.getText(),
                inputPrice.getText()
            };
            try {
                Connection conn = DatabaseManager.getConnection();
                DatabaseManager.addRowTable(conn, newData);
            } catch(SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

}