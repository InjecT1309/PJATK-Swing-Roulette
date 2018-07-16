import javax.swing.*;
import java.awt.*;

public class BetOutcome extends JTextField
{
    private BetHistory history_;
    private Baloons baloons_;
    public BetOutcome(BetHistory history, Baloons baloons)
    {
        history_ = history;
        baloons_ = baloons;

        setEditable(false);
        setFont(new Font("TimesNewRoman", Font.BOLD, 50));
        setHorizontalAlignment(JTextField.CENTER);
    }
    public int calculateOutcome(RouletteCellStruct cell, int bet_amount)
    {
        history_.addBet(getText());

        String bet_type = "";
        int won_amount = 0;

        if(cell.color_selected)
        {
            bet_type = cell.value % 2 == 0 ? "green " : "red ";
            won_amount = 2 * bet_amount;
        }
        else if(cell.number_selected)
        {
            bet_type = Integer.toString(cell.value) + " ";
            won_amount = 10 * bet_amount;
        }

        if(won_amount > 0)
            baloons_.setBaloonsFree();

        setText("Bet: " + bet_type + "won " + won_amount + " money");

        repaint();

        return won_amount;
    }
}
