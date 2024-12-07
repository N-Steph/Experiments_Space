import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class SearchPanel extends JPanel {
    String imagePath = "D:\\Projects\\IctInventoryManagementSystem\\src\\pngtree.jpg";
    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
    Color lineColor = new Color(0, 0, 0);
    JLabel panelTitle = new JLabel("Search Items");
    JTextField searchItem = new JTextField("e.g. Maggi", 30);
    JButton searchButton = new JButton("search");

    public SearchPanel() {
        super();
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
        
        ImagePanel imagePanel = new ImagePanel();
        JPanel middlePanel = new JPanel();
        searchButton.addActionListener(new SearchItemListener());
        //JPanel bottomPanel = new JPanel();
        
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

        // searchButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        middlePanel.add(searchItem);
        middlePanel.add(searchButton);

        this.add(topPanel);
        this.add(middlePanel);
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

    class SearchItemListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String itemToSearch = searchItem.getText();
            try {
                Connection conn = DatabaseManager.getConnection();
                DatabaseManager.searchItem(conn, itemToSearch);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            
        }
    }
}