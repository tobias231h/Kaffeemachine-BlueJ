import java.awt.*;

/**
 * Diese Klasse ist zuständig für das Zeichnen eines Rechtecks auf dem Canvas
 * 
 * @author  Tobias Häberlein, Tim Stark
 * @version 1.3.3.7 (06.04.2017)
 */

public class Rechteck implements IShape
{
    public int width;
    public int height;
    public int xPosition;
    public int yPosition;
    public Color color;
    public boolean isVisible;
    public String name;

    /**
     * Erstellt ein neues Rechteck mit den angegebenen Parametern
     */
    public Rechteck(String name, int width, int height, int xPosition, int yPosition, Color color, boolean isVisible)
    {
        this.width = width;
        this.height = height;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        this.isVisible = isVisible;
        this.name = name;
        if (isVisible)
            draw();
    }

    /**
     * Macht das Rechteck sichtbar
     */
    public void makeVisible()
    {
        isVisible = true;
        draw();
    }
    
    /**
     * Macht das Rechteck unsichtbar
     */
    public void makeInvisible()
    {
        erase();
        isVisible = false;
    }
    
    /**
     * Verschiebt das Rechteck 20px nach rechts
     */
    public void moveRight()
    {
        moveHorizontal(20);
    }

    /**
     * Verschiebt das Rechteck 20px nach links
     */
    public void moveLeft()
    {
        moveHorizontal(-20);
    }

    /**
     * Verschiebt das Rechteck 20px nach oben
     */
    public void moveUp()
    {
        moveVertical(-20);
    }

    /**
     * Verschiebt das Rechteck 20px nach unten
     */
    public void moveDown()
    {
        moveVertical(20);
    }

    /**
     * Verschiebt das Rechteck 'distance' px horizontal nach rechts/links
     */
    public void moveHorizontal(int distance)
    {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Verschiebt das Rechteck 'distance' px vertikal nach unten/oben
     */
    public void moveVertical(int distance)
    {
        erase();
        yPosition += distance;
        draw();
    }
    
    public void moveVerticalWithoutRedraw(int distance)
    {
        yPosition += distance;
    }

    /**
     * Verschiebt das Rechteck langsam um 'distance' px horizontal nach rechts/links
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
     * Verschiebt das Rechteck langsam um 'distance' px vertikal nach unten/oben
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
     * Ändert die Länge / Breite des Rechtecks. Die Werte müssen > 0 sein
     */
    public void changeSize(int width, int height)
    {
        erase();
        this.width = width;
        this.height = height;
        draw();
    }
    
    public void changeSizeWithoutRedraw(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    /**
     * Ändert die Farbe des Rechtecks
     * Erlaubt sind alle Farben der Color-Enumeration
     */
    public void changeColor(Color newColor)
    {
        color = newColor;
        draw();
    }
    
    /*
     * Zeichnet das Rechteck mit den aktuellen Eigenschaften auf dem Canvas
     */
    public void draw()
    {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                        new Rectangle(xPosition, yPosition, width, height));
            canvas.wait(10);
        }
    }

    /*
     * Löscht das Rechteck auf dem Canvas
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
        return this.width;
    }
    
    public int getHeight()
    {
        return this.height;
    }
    
    public String getInstanceName()
    {
        return this.name;
    }
}
