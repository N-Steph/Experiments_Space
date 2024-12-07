import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Main implements Runnable {
    static final String[] labels = {
        "ICT Canteen Inventory Management System", "Home", "Add item", "Search item",
        "\u00a9 ICT University 2024"
    };
    ArrayList<JButton> toolBarButton = new ArrayList<JButton>();
    public static JFrame frame = new JFrame();
    JToolBar toolBar = new JToolBar();
    JButton home = new JButton(labels[1]);
    JButton addItem = new JButton(labels[2]);
    JButton searchItem = new JButton(labels[3]);
    JLabel copyRight = new JLabel(labels[4]);
    Color backgroundColorButton = new Color(200, 100, 50);
    Color lineColor = new Color(0, 0, 0);
    JPanel bottomPanel = new JPanel();
    HomePanel homePanel = new HomePanel();
    public static JPanel cards = new JPanel(new CardLayout());
    AddItemPanel addItemPanel = new AddItemPanel();
    SearchPanel searchItemPanel = new SearchPanel();

    public void run() {
        cards.add(homePanel, "home panel");
        cards.add(addItemPanel, "add item panel");
        cards.add(searchItemPanel, "search item panel");
        frame.setTitle(labels[0]);
        toolBar.setFloatable(false);
        toolBarButton.add(home);
        toolBarButton.add(addItem);
        toolBarButton.add(searchItem);
        bottomPanel.add(copyRight);

        home.addActionListener(new HomeListener());
        addItem.addActionListener(new AddItemListener());
        searchItem.addActionListener(new SearchItemListener());

        for (JButton button : toolBarButton) {
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMaximumSize().height));
            toolBar.add(button);
        }
        toolBarButton.get(0).setBackground(backgroundColorButton);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(BorderLayout.NORTH, toolBar);
        frame.add(BorderLayout.SOUTH, bottomPanel);
        frame.add(BorderLayout.CENTER, cards);
        copyRight.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.setBorder(BorderFactory.createLineBorder(lineColor));
        copyRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setSize(700, 550);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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

    class HomeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout) (Main.cards.getLayout());
            cl.show(Main.cards, "home panel");
        }
    }

    public static void main(String[] args) {
         SwingUtilities.invokeLater(new Main());
    }
}