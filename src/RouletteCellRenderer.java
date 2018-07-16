import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class RouletteCellRenderer extends JLabel implements TableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column)
    {
        setOpaque(true);
        RouletteCellStruct struct = (RouletteCellStruct)value;
        setHorizontalAlignment(JLabel.CENTER);
        setText(Integer.toString(struct.value));
        setFont(new Font("TimesNewRoman", Font.BOLD, 50));

        if(struct.value % 2 == 0)
            setBackground(Color.GREEN);
        else
            setBackground(Color.RED);

        if(struct.color_selected)
            setBackground(Color.ORANGE);
        if(struct.roll_selected)
            setBackground(Color.CYAN);

        if(struct.number_selected)
            setForeground(Color.ORANGE);
        else
            setForeground(Color.BLACK);

        return this;
    }
}