import javax.swing.*;
import java.awt.*;

public class Baloon extends JLabel
{
    int x, y;
    int velX, velY;
    Image img;

    public Baloon(int pos_x, int pos_y)
    {
        x = pos_x;
        y = pos_y;
        velX = 0;
        velY = 0;
        img = new ImageIcon("res/Baloon.jpg").getImage();
    }
    public void addVelocity()
    {
        x += velX;
        y += velY;
    }
    public void addToVelocity(int plusX, int plusY)
    {
        if(velX + plusX < 10 && velX + plusX > -10)
            velX += plusX;
        if(velY + plusY < 5 && velY + plusY > -5)
            velY += plusY;
    }
}
