import javax.swing.*;
import java.awt.*;

public class Wallet extends JPanel
{
    int money = 100;
    JTextField display;
    JTextField withdraw;

    public Wallet(JTable table, BetOutcome outcome)
    {
        setLayout(new GridLayout(3, 1, 20, 0));

        display = new JTextField();
        withdraw = new JTextField();

        display.setEditable(false);
        display.setFont(new Font("TimesNewRoman", Font.BOLD, 40));
        display.setHorizontalAlignment(JTextField.CENTER);
        display.setText("Money:\n" + money);
        add(display);

        add(withdraw);

        JButton button = new JButton("Bet");
        button.addActionListener(
                e -> {
                    RouletteTableModel model = (RouletteTableModel)table.getModel();

                    if(model.checkIfAnyCellSelected())
                    {
                        RouletteRoll roll = new RouletteRoll(table, outcome, this);
                        if(roll.init())
                            roll.start();
                    }
                    else
                        JOptionPane.showMessageDialog(this, "Select a bet first!");
                });
        button.setSize(getMaximumSize());
        add(button);
    }
    public int withdrawForBet()
    {
        int amount = 0;
        try
        {
            amount = Integer.parseInt(withdraw.getText());

            if(amount <= 0)
                JOptionPane.showMessageDialog(this, "You have to bet something!");
            else if(amount > money)
            {
                JOptionPane.showMessageDialog(this, "You don't have that much money");
                return 0;
            }
            else
                money-=amount;
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Not a valid amount to bet");
        }
        display.setText("Money:\n" + money);

        return amount;
    }
    public void addBetOutcome(int outcome)
    {
        money+=outcome;

        if(money <= 0)
        {
            JOptionPane.showMessageDialog(this, "You don't have any money. Get out");
            System.exit(0);
        }

        display.setText("Money:\n" + money);
    }
}
