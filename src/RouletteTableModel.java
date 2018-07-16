import javax.swing.table.AbstractTableModel;

public class RouletteTableModel extends AbstractTableModel
{
    RouletteCellStruct data[][];

    RouletteTableModel()
    {
        int rows = getRowCount();
        int columns = getColumnCount();

        data = new RouletteCellStruct[rows][columns];

        for(int r=0; r<rows; r++)
        {
            for(int c=0; c<columns; c++)
            {
                RouletteCellStruct cell = new RouletteCellStruct();
                cell.value = r + 5*c + 1;

                data[r][c] = cell;
            }
        }
    }

    @Override
    public int getRowCount() { return 5; }

    @Override
    public int getColumnCount() { return 2; }

    @Override
    public Object getValueAt(int r, int c)
    {
        return data[r][c];
    }

    @Override
    public boolean isCellEditable(int r, int c) { return false; }

    public void clearUserSelection()
    {
        int rows = getRowCount();
        int columns = getColumnCount();

        for(int r=0; r<rows; r++)
        {
            for(int c=0; c<columns; c++)
            {
                RouletteCellStruct cell = (RouletteCellStruct)getValueAt(r, c);
                cell.number_selected = false;
                cell.color_selected = false;
            }
        }
    }

    public void clearRollSelection()
    {
        int rows = getRowCount();
        int columns = getColumnCount();

        for(int r=0; r<rows; r++)
        {
            for(int c=0; c<columns; c++)
            {
                RouletteCellStruct cell = (RouletteCellStruct)getValueAt(r, c);
                cell.roll_selected = false;
            }
        }
    }
    public boolean checkIfAnyCellSelected()
    {
        int rows = getRowCount();
        int columns = getColumnCount();

        for(int r=0; r<rows; r++)
        {
            for(int c=0; c<columns; c++)
            {
                RouletteCellStruct cell = (RouletteCellStruct)getValueAt(r, c);
                if(cell.number_selected || cell.color_selected)
                    return true;
            }
        }
        return false;
    }
    public RouletteCellStruct getRolled()
    {
        int rows = getRowCount();
        int columns = getColumnCount();

        for(int r=0; r<rows; r++)
        {
            for (int c = 0; c < columns; c++)
            {
                RouletteCellStruct cell = (RouletteCellStruct) getValueAt(r, c);

                if (cell.roll_selected)
                    return cell;
            }
        }
        return null;
    }
}