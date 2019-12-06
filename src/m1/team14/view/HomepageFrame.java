package m1.team14.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import m1.team14.controller.HomePageController;
import m1.team14.controller.AbstractController;
import m1.team14.view.SecondHalfViewPanel;
import dataproto.Dealer;


public class HomepageFrame extends BaseGuiFrame implements IViewPanel {
    // dealer icon image path(absolute path)
    private final String curDealerIconPath = "../../images/businessman64px.png";
    private final String scrollIconPath = "../../images/businessman32px.png";
    // private final String curDealerIconPath = "/INFO-5100-Project/src/m1/team14/images/businessman64px.png";
    // private final String scrollIconPath = "/INFO-5100-Project/src/m1/team14/images/businessman32px.png";
    private static final long serialVersionUID = 4L;

    List<String> getDealers(int num){
        List<String> allDealers = new ArrayList<>();

        for (int i = 1; i <= num; i++){
            String dealerId = "Dealer " + i;
            allDealers.add(dealerId);
        }

        return allDealers;
    }

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
     private JLabel headerLabel;

    // scrollable bar
    private JScrollPane scrollPanel;
    private JPanel viewPanel;

    // test panel for the layout testing
    private JPanel blankPanel;

    // Page controller
    private HomePageController homepageUpCtrl;
    private SecondHalfViewPanel secondHalfViewPanel;

    public HomepageFrame(HomePageController homepageUpCtrl, SecondHalfViewPanel secondHalfViewPanel) {
        super(homepageUpCtrl, secondHalfViewPanel);
        System.out.println(secondHalfViewPanel);
    }
    @Override void init(AbstractController controller, JPanel view) {
        this.homepageUpCtrl = (HomePageController)controller;
        this.secondHalfViewPanel = (SecondHalfViewPanel)view;
        super.init(controller, view);
    }

    @Override
    public void create() {
        headPanel = new JPanel();

        // current dealer panel
        curDealerIconPanel = new JPanel();
        curDealerIcon = new ImageIcon(getClass().getResource(curDealerIconPath));
        curDealerImg = new JLabel(curDealerIcon);
        curDealerLabel = new JLabel("Current Dealer");

        // button components
        buttonPanel = new JPanel();
        searchBtn = new JButton("Search");
        searchBtn.setBounds(0, 0, 100, 100);
        searchBtn.addActionListener(e -> {
         this.homepageUpCtrl.gotoSearch();
        });
        loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> {
         this.homepageUpCtrl.gotoLogin();
        });
        historyBtn = new JButton("History");
        historyBtn.addActionListener(e -> {
         this.homepageUpCtrl.gotoHistory();
        });

        // scroll panel
//        scrollPanel = new JScrollPane();
        viewPanel = new JPanel();
    }
    @Override
    void add(Container container) {
        // addCurDealerIcon
        Container mainPanel = getContentPane();
        addCurDealerIcon(curDealerIconPanel);

        addButtons(buttonPanel);

        addViewPanel(viewPanel);

        addHeadPanel(headPanel);

        addToMain(mainPanel);
    }

    private void addViewPanel(JPanel viewPanel) {
      List<Dealer> dealers = homepageUpCtrl.getDealers();
        for (Dealer dealer : dealers){
            viewPanel.add(createIconPanel(dealer));
        }
    }

    private JPanel createIconPanel(Dealer dealer) {
        JPanel padPanel = new JPanel();
        padPanel.setBorder(new EmptyBorder(2,1,2,1));

        JPanel dealerPanel = new JPanel(new BorderLayout());

        JPanel iconPanel = new JPanel();
        ImageIcon dealImg = new ImageIcon(getClass().getResource(scrollIconPath));
        JButton iconLabel = new JButton(dealImg);
        iconLabel.setFocusPainted(false);
        iconLabel.setMargin(new Insets(0, 0, 0, 0));
        iconLabel.setContentAreaFilled(false);
        iconLabel.setBorderPainted(false);
        iconLabel.setOpaque(false);
        iconLabel.addActionListener(e->{
          this.homepageUpCtrl.changeDealer(dealer);
        });

        iconPanel.add(iconLabel);

        JPanel labelPanel = new JPanel();
        JLabel dealerIdLabel = new JLabel(dealer.getName());
        labelPanel.add(dealerIdLabel);

        dealerPanel.add(iconPanel, BorderLayout.CENTER);
        dealerPanel.add(labelPanel, BorderLayout.SOUTH);
        padPanel.add(dealerPanel);
        return padPanel;
    }

    private void addHeadPanel(JPanel headPanel) {
        Box hbox1 = Box.createHorizontalBox();
        Box hbox2 = Box.createHorizontalBox();
        Box hbox3 = Box.createHorizontalBox();

        hbox1.add(curDealerIconPanel);
        hbox1.add(Box.createHorizontalStrut(140));
        hbox1.add(buttonPanel);

        scrollPanel = new JScrollPane(viewPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.setPreferredSize(new Dimension(400, 100));
        hbox2.add(scrollPanel);
        hbox3.add(secondHalfViewPanel);

        Box vbox1 = Box.createVerticalBox();
        vbox1.add(hbox1);
        vbox1.add(Box.createVerticalStrut(30));
        vbox1.add(hbox2);
        vbox1.add(Box.createVerticalStrut(30));
        vbox1.add(hbox3);

        headPanel.add(vbox1);
        headPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
    }

    private void addButtons(JPanel buttonPanel) {
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        fillButtonPanel(buttonPanel, searchBtn, constraints, 0, 0, 1, 1, 0.6, 1, GridBagConstraints.NONE, GridBagConstraints.NORTH);
        fillButtonPanel(buttonPanel, historyBtn, constraints, 1, 0, 1, 1, 0.2, 1, GridBagConstraints.NONE, GridBagConstraints.NORTH);
        fillButtonPanel(buttonPanel, loginBtn, constraints, 2, 0, 1, 1, 0.2, 1, GridBagConstraints.NONE, GridBagConstraints.NORTH);

    }

    /*
    * Add component to container with the GridBagConstrains
    * @param: target container
    * @param: added component
    * @param: GridBagConstrain
    * @param: x coordinator of grid
    * @param: y coordinator of grid
    * @param: grid width
    * @param: grid height
    * @param: the grid's weight of the whole grid width
    * @param: the grid's weight of the whole grid height
    * @param: grid fill strategy
    * @param: grid anchor strategy
    * */
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
        curDealerIconPanel.add(imagePanel, BorderLayout.CENTER);
        curDealerIconPanel.add(curDealerLabel, BorderLayout.SOUTH);
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {

    }
}
