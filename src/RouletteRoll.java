import javax.swing.*;

public class RouletteRoll
{
    private JTable parent_;
    private RouletteTableModel model_;
    private BetOutcome outcome_;
    private Wallet wallet_;

    private Timer t_rollOutcome;
    private Timer t_stop;

    private int roll_row;
    private int roll_col;

    RouletteRoll(JTable table, BetOutcome outcome, Wallet wallet)
    {
        parent_ = table;
        outcome_ = outcome;
        wallet_ = wallet;
    }
    public boolean init()
    {

        model_ = (RouletteTableModel)parent_.getModel();
        int bet_amount = wallet_.withdrawForBet();
        if(bet_amount <= 0)
            return false;

        roll_row = 0;
        roll_col = 0;

        t_rollOutcome = new Timer(50,
                e-> {
                    model_.clearRollSelection();

                    if(roll_row + 1 < parent_.getRowCount())
                        roll_row++;
                    else
                    {
                        roll_row = 0;
                        if(roll_col + 1 < parent_.getColumnCount())
                            roll_col++;
                        else
                            roll_col = 0;
                    }

                    RouletteCellStruct cell = (RouletteCellStruct) parent_.getValueAt(roll_row, roll_col);
                    cell.roll_selected = true;

                    parent_.repaint();
                });

        t_stop = new Timer((int)(Math.random()*2000 + 1000),
                e -> {
                    t_rollOutcome.stop();

                    Timer checkOutcome = new Timer(1000,
                            e1 -> {
                                wallet_.addBetOutcome(outcome_.calculateOutcome(model_.getRolled(), bet_amount));

                                model_.clearUserSelection();
                                model_.clearRollSelection();
                                parent_.repaint();
                            });
                    checkOutcome.setRepeats(false);
                    checkOutcome.start();
                });
        t_stop.setRepeats(false);
        return true;
    }
    public void start()
    {
        t_rollOutcome.start();
        t_stop.start();
    }
}