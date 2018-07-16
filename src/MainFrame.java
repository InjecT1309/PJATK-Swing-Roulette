import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    public static void main(String args[])
    {
        new MainFrame();
    }
    public MainFrame()
    {
        setTitle("Roulette");
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        BetHistory history = new BetHistory();

        add(history, BorderLayout.WEST);

        Baloons baloons = new Baloons();
        setGlassPane(baloons);
        getGlassPane().setVisible(true);
        ((JPanel)getGlassPane()).setOpaque(false);

        Roulette roulette = new Roulette(history, baloons);
        add(roulette, BorderLayout.CENTER);

        add(new Wallet(roulette.table, roulette.outcome), BorderLayout.EAST);

        pack();
        setVisible(true);
    }
}
