import javax.swing.*;
import java.awt.*;

public class BetHistory extends JTextArea
{
    public BetHistory()
    {
        setOpaque(true);
        setPreferredSize(new Dimension(300, 100));
        setEditable(false);
        setFont(new Font("TimesNewRoman", Font.BOLD, 20));
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }
    public void addBet(String text)
    {
        insert(text + "\n", 0);

        if(getLineCount() > 6)
        {
            String[] records = getText().split("\n");
            String new_text = "";
            for(int i=0; i<records.length-1; i++)
                new_text = new_text + records[i] + "\n";

            setText(new_text);
        }

        repaint();
    }
}
