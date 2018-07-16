import javax.swing.*;
import java.awt.*;

public class Roulette extends JPanel
{
    JTable table;
    BetOutcome outcome;

    public Roulette(BetHistory history, Baloons baloons)
    {
        setLayout(new BorderLayout());

        table = new JTable();
        table.setModel(new RouletteTableModel());

        table.setRowHeight(130);

        for(int i=0; i<table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setCellRenderer(new RouletteCellRenderer());

        table.addMouseListener(new RouletteMouseListener(table));
        add(table, BorderLayout.CENTER);

        outcome = new BetOutcome(history, baloons);
        add(outcome, BorderLayout.SOUTH);
    }
}