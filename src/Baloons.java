import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Baloons extends JPanel
{
    Baloon[] baloons;
    Timer timer;

    public Baloons()
    {
        setVisible(true);
        baloons = new Baloon[100];

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = dim.width;
        int y = dim.height;

        for(int i=0; i<baloons.length; i++)
        {
            baloons[i] = new Baloon(0 + i*(x/100), y);
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        for(int i=0; i<baloons.length; i++)
        {
            g.drawImage(baloons[i].img, baloons[i].x, baloons[i].y, this);
        }
        super.paintComponent(g);
    }

    public void setBaloonsFree()
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = dim.width;
        int y = dim.height;

        for(int i=0; i<baloons.length; i++)
        {
            baloons[i] = new Baloon(200 + i*((x-200)/100), y);
        }

        if(timer != null)
            timer.stop();
        timer = new Timer(20,
                e -> {
                    for(int i=0; i<baloons.length; i++)
                    {
                        Random rand = new Random();
                        baloons[i].addToVelocity(rand.nextInt()%2, rand.nextInt()%3-1);
                        baloons[i].addVelocity();
                    }
                    repaint();
                });
        timer.start();
    }
}
