import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePanel extends JPanel{
    String[] labels = {"Welcome to ICTU Canteen", 
        "Inventory Management System",
        "Add item", "Search item", 
    };
    String imagePath = "D:\\Projects\\IctInventoryManagementSystem\\rsc\\pngtree.jpg";
    JLabel title = new JLabel(labels[0]);
    JLabel subtitle = new JLabel(labels[1]);
    JButton addItem = new JButton(labels[2]);
    JButton searchItem = new JButton(labels[3]);
    JPanel buttonsContainer = new JPanel();
    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 40);

    public HomePanel() { 
        initialization();
    }

    public void initialization() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addItem.addActionListener(new AddItemListener());
        searchItem.addActionListener(new SearchItemListener());
        ImagePanel imagePanel = new ImagePanel();
        this.add(imagePanel);
        title.setFont(font);
        this.add(title);
        this.add(subtitle);
        title.setAlignmentX(CENTER_ALIGNMENT);
        subtitle.setAlignmentX(CENTER_ALIGNMENT);
        buttonsContainer.add(addItem);
        buttonsContainer.add(searchItem);
        this.add(buttonsContainer);
        buttonsContainer.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));
    }

    class ImagePanel extends JPanel {
        private Image image = null;
        public ImagePanel() {
            image =  new ImageIcon(imagePath).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        }
        public void  paintComponent(Graphics g) {
            super.paintComponent(g);
            
            if (image != null) {
                g.drawImage(image, 300, 0, this);
            }
            
        }
    }

    class AddItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout) (Main.cards.getLayout());
            cl.show(Main.cards, "add item panel");
        }
    }

    class SearchItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout) (Main.cards.getLayout());
            cl.show(Main.cards, "search item panel");
        }
    }
}