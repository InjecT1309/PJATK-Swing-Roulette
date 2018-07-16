import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RouletteMouseListener implements MouseListener
{
    private JTable parent_;
    private RouletteTableModel model_;

    RouletteMouseListener(JTable table)
    {
        parent_ = table;
        model_ = (RouletteTableModel)(parent_.getModel());
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int r_s = parent_.rowAtPoint(e.getPoint());
        int c_s = parent_.columnAtPoint(e.getPoint());
        RouletteCellStruct cell_clicked = (RouletteCellStruct)parent_.getModel().getValueAt(r_s, c_s);

        if(SwingUtilities.isLeftMouseButton(e))
        {
            model_.clearUserSelection();
            cell_clicked.number_selected = true;
        }
        else if(SwingUtilities.isRightMouseButton(e))
        {
            model_.clearUserSelection();

            int rows = parent_.getRowCount();
            int columns = parent_.getColumnCount();

            for(int r=0; r<rows; r++)
            {
                for(int c=0; c<columns; c++)
                {
                    RouletteCellStruct cell = (RouletteCellStruct)parent_.getValueAt(r, c);

                    if((cell_clicked.value % 2) == (cell.value%2))
                        cell.color_selected = true;
                }
            }
        }

        parent_.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}