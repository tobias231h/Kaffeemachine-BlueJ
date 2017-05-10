import java.awt.*;
import java.awt.geom.*;
import java.lang.*;

/**
 * Diese Klasse ist zuständig für das Zeichnen eines Kreises auf dem Canvas
 * 
 * @author  Tobias Häberlein, Tim Stark
 * @version 1.3.3.7 (06.04.2017)
 */

public class Circle implements IShape
{
    private int diameter;
    private int xPosition;
    private int yPosition;
    private Color color;
    private boolean isVisible;
    public String name;
    
    /**
     * Erstellt einen neuen Kreis mit den angegebenen Parametern
     */
    public Circle(String name, int diameter, int xPosition, int yPosition, Color color, boolean isVisible)
    {
        this.diameter = diameter; // Durchmesser
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        this.isVisible = isVisible;
        this.name = name;
        if (isVisible)
            draw();
    }
        
    /**
     * Macht den Kreis sichtbar
     */
    public void makeVisible()
    {
        isVisible = true;
        draw();
    }
    
    /**
     * Macht den Kreis unsichtbar
     */
    public void makeInvisible()
    {
        erase();
        isVisible = false;
    }
    
    /**
     * Verschiebt den Kreis 20px nach rechts
     */
    public void moveRight()
    {
        moveHorizontal(20);
    }

    /**
     * Verschiebt den Kreis 20px nach links
     */
    public void moveLeft()
    {
        moveHorizontal(-20);
    }

    /**
     * Verschiebt den Kreis 20px nach oben
     */
    public void moveUp()
    {
        moveVertical(-20);
    }

    /**
     * Verschiebt den Kreis 20px nach unten
     */
    public void moveDown()
    {
        moveVertical(20);
    }

    /**
     * Verschiebt den Kreis 'distance' px horizontal nach rechts/links
     */
    public void moveHorizontal(int distance)
    {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Verschiebt den Kreis 'distance' px vertikal nach unten/oben
     */
    public void moveVertical(int distance)
    {
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Verschiebt den Kreis langsam um 'distance' px horizontal nach rechts/links
     */
    public void slowMoveHorizontal(int distance)
    {
        int delta;

        if(distance < 0) 
        {
            delta = -1;
            distance = -distance;
        }
        else 
        {
            delta = 1;
        }

        for(int i = 0; i < distance; i++)
        {
            xPosition += delta;
            draw();
        }
    }

    /**
     * Verschiebt den Kreis langsam um 'distance' px vertikal nach unten/oben
     */
    public void slowMoveVertical(int distance)
    {
        int delta;

        if(distance < 0) 
        {
            delta = -1;
            distance = -distance;
        }
        else 
        {
            delta = 1;
        }

        for(int i = 0; i < distance; i++)
        {
            yPosition += delta;
            draw();
        }
    }

    /**
     * Ändert die Länge / Breite des Kreises. Die Werte müssen > 0 sein
     */
    public void changeSize(int newDiameter)
    {
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Ändert die Farbe des Kreises
     * Erlaubt sind alle Farben der Color-Enumeration
     */
    public void changeColor(Color newColor)
    {
        color = newColor;
        draw();
    }

    /*
     * Zeichnet den Kreis mit den aktuellen Eigenschaften auf dem Canvas
     */
    private void draw()
    {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new Ellipse2D.Double(xPosition, yPosition, 
                                                          diameter, diameter));
            canvas.wait(10);
        }
    }

    /*
     * Löscht den Kreis auf dem Canvas
     */
    private void erase()
    {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * Für das IShape-Interface
     */
    public int getX()
    {
        return this.xPosition;
    }
    
    public int getY()
    {
        return this.yPosition;
    }
    
    public int getWidth()
    {
        return this.diameter;
    }
    
    public int getHeight()
    {
        return this.diameter;
    }
    
    public String getInstanceName()
    {
        return this.name;
    }
}
