package m1.team14.view;

import m1.team14.controller.AbstractController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class HomepageFrame extends BaseGuiFrame implements IViewPanel {
    /*
    * Page components
    * */
    // include the header part, like current dealer img, search, login, history
    private JPanel headPanel;

    private JPanel curDealerIconPanel;
    private JLabel curDealerImg;
    private ImageIcon curDealerIcon;
    private JLabel curDealerLabel;

    private JPanel buttonPanel;
    private JButton searchBtn;
    private JButton loginBtn;
    private JButton historyBtn;

    // scrollable bar
    private JPanel scrollPanel;
    private JLabel leftShiftLabel;
    private JLabel rightShiftLabel;

    // test panel for the layout testing
    private JPanel blankPanel;

    /*
    * Page controller
    * */
    private AbstractController homepageUpCtrl;

    public HomepageFrame() {
    }

    public HomepageFrame(AbstractController homepageUpCtrl) {
        this.homepageUpCtrl = homepageUpCtrl;
    }

    @Override
    public void create() {
        headPanel = new JPanel();
        curDealerIconPanel = new JPanel();
        curDealerIcon = new ImageIcon("/Users/leichenzhou/Documents/Fall2019Semester/info-5100/final5100_module1/INFO-5100-Project/src/m1/team14/images/businessman64px.png");
        curDealerImg = new JLabel(curDealerIcon);
        curDealerLabel = new JLabel("Current Dealer");

        // button components
        buttonPanel = new JPanel();
        searchBtn = new JButton("Search");
        searchBtn.setBounds(0, 0, 100, 100);
        loginBtn = new JButton("Login");
        historyBtn = new JButton("History");
    }

    @Override
    void add(Container container) {
        // addCurDealerIcon
        Container mainPanel = getContentPane();
        addCurDealerIcon(curDealerIconPanel);
        addButtons(buttonPanel);
        addHeadPanel(headPanel);

        addToMain(mainPanel);
    }

    private void addHeadPanel(JPanel headPanel) {
        Box hbox1 = Box.createHorizontalBox();
        Box hbox2 = Box.createHorizontalBox();

        hbox1.add(curDealerIconPanel);
        hbox1.add(Box.createHorizontalStrut(140));
        hbox2.add(buttonPanel);

        headPanel.add(hbox1);
        headPanel.add(hbox2);
        headPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
    }

    private void addButtons(JPanel buttonPanel) {
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        fillButtonPanel(buttonPanel, searchBtn, constraints, 0, 0, 1, 1, 0.6, 1, GridBagConstraints.NONE, GridBagConstraints.NORTH);
        fillButtonPanel(buttonPanel, historyBtn, constraints, 1, 0, 1, 1, 0.2, 1, GridBagConstraints.NONE, GridBagConstraints.NORTH);
        fillButtonPanel(buttonPanel, loginBtn, constraints, 2, 0, 1, 1, 0.2, 1, GridBagConstraints.NONE, GridBagConstraints.NORTH);

    }

    private void fillButtonPanel(JPanel container,
                                 JComponent component,
                                 GridBagConstraints constraints,
                                 int gridx, int gridy,
                                 int gridWidth, int gridHeight,
                                 double weightx, double weighty,
                                 int fill, int anchor) {

        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridWidth;
        constraints.gridheight = gridHeight;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.fill = fill;
        constraints.anchor = anchor;

        container.add(component, constraints);
    }

    private void addToMain(Container mainPanel) {
        mainPanel.add(headPanel, BorderLayout.NORTH);
    }

    private void addCurDealerIcon(JPanel curDealerIconPanel) {
        BorderLayout borderLayout = new BorderLayout();
        curDealerIconPanel.setLayout(borderLayout);

        JPanel imagePanel = new JPanel();
        imagePanel.add(curDealerImg);
        JPanel labelPanel = new JPanel();
        labelPanel.add(curDealerLabel);
        curDealerIconPanel.add(imagePanel, BorderLayout.CENTER);
        curDealerIconPanel.add(labelPanel, BorderLayout.SOUTH);
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {

    }
}
