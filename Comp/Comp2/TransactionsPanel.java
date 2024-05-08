import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;




public class TransactionsPanel extends JPanel{

    private BufferedImage backgroundImage;

    public TransactionsPanel(){
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLayout(new GridBagLayout());
        
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new Insets(10, 10,10,10);

        JButton deposit = new JButton("Transaction - Deposit");
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.WEST;
        this.add(deposit,cons);
        DepositListener dl =  new DepositListener();
        deposit.addActionListener(dl);

        
        JButton withdraw = new JButton("Transaction - Withdraw");
        cons.gridx = 0;
        cons.gridy = 1;
        cons.anchor = GridBagConstraints.WEST;
        this.add(withdraw,cons);
        WithdrawListener wl = new WithdrawListener();
        withdraw.addActionListener(wl);

        JButton convo = new JButton("Transaction - Convertion");
        cons.gridx = 0;
        cons.gridy = 2;
        cons.anchor = GridBagConstraints.WEST;
        this.add(convo,cons);
        ConversionListener cl = new ConversionListener();
        convo.addActionListener(cl);

        JButton inTran = new JButton("Internal Transfer");
        cons.gridx = 0;
        cons.gridy = 3;
        cons.anchor = GridBagConstraints.WEST;
        this.add(inTran,cons);
        InternalTransferListener itl = new InternalTransferListener();
        inTran.addActionListener(itl);

        JButton disp = new JButton("Display Transactions");
        cons.gridx = 0;
        cons.gridy = 4;
        cons.anchor = GridBagConstraints.WEST;
        this.add(disp,cons);
        DisplayListener displ = new DisplayListener();
        disp.addActionListener(displ);


    }
}
 
/*public class TransactionsPanel extends JPanel{

    private BufferedImage backgroundImage;

    public TransactionsPanel(){
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String items[] = {"account1","account2","account3","account4","account5"};
        JComboBox accs = new JComboBox<>(items);
        this.add(accs);



    }
}*/



/*
public class TransactionsPanel extends JPanel {
    private BufferedImage backgroundImage;
    private JComboBox<String> accountComboBox;
    private JTable transactionsTable;

    public TransactionsPanel() {
        setLayout(new BorderLayout(5, 5)); // Provide some spacing
        initializeBackground();
        initializeComponents();
    }

    private void initializeBackground() {
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeComponents() {
        String[] items = {"account1", "account2", "account3", "account4", "account5"};
        accountComboBox = new JComboBox<>(items);
        add(accountComboBox, BorderLayout.NORTH);

        // Setup the table for transactions
        transactionsTable = new JTable();
        setupTable();
        JScrollPane scrollPane = new JScrollPane(transactionsTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void setupTable() {
        // Define column names
        String[] columnNames = {"Date", "Description", "Amount", "Balance"};
        // Example data
        Object[][] data = {
            {"2024-05-07", "Deposit", 500.00, 1500.00},
            {"2024-05-08", "Withdrawal", -200.00, 1300.00}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        transactionsTable.setModel(model);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
*/