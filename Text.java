import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.font.*;

/**
 * Diese Klasse ist zuständig für das Zeichnen von Text auf dem Canvas
 * 
 * @author  Tobias Häberlein, Tim Stark
 * @version 1.3.3.8 (04.05.2017)
 */

public class Text implements IShape
{
    public String text;
    public int size;
    private int width;
    private int height;
    public String font;
    public int xPosition;
    public int yPosition;
    public Color color;
    public boolean isVisible;
    public String name;
    
    /**
     * Erstellt einen neuen Text mit den angegebenen Parametern
     */
    public Text(String name, String text, String font, int xPosition, int yPosition, int size, Color color, boolean isVisible)
    {
        this.text = text;
        this.font = font;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.size = size;
        this.color = color;
        this.isVisible = isVisible;
        this.name = name;
        if (isVisible)
            draw();        
    }
    
    /**
     * Macht den Text sichtbar
     */
    public void makeVisible()
    {
        isVisible = true;
        draw();
    }
    
    /**
     * Macht den Text unsichtbar
     */
    public void makeInvisible()
    {
        erase();
        isVisible = false;
    }
    
    /**
     * Verschiebt den Text 20px nach rechts
     */
    public void moveRight()
    {
        moveHorizontal(20);
    }

    /**
     * Verschiebt den Text 20px nach links
     */
    public void moveLeft()
    {
        moveHorizontal(-20);
    }

    /**
     * Verschiebt den Text 20px nach oben
     */
    public void moveUp()
    {
        moveVertical(-20);
    }

    /**
     * Verschiebt den Text 20px nach unten
     */
    public void moveDown()
    {
        moveVertical(20);
    }
    
    /**
     * Verschiebt den Text 'distance' px horizontal nach rechts/links
     */
    public void moveHorizontal(int distance)
    {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Verschiebt den Text 'distance' px vertikal nach unten/oben
     */
    public void moveVertical(int distance)
    {
        erase();
        yPosition += distance;
        draw();
    }
    
    /**
     * Ändert den Text
     */
    public void changeText(String text)
    {
        this.text = text;
        draw();
    }
    
    /**
     * Ändert die X-Koordinate
     */
    public void setX(int x)
    {
        xPosition = x;
        draw();
    }

    /**
     * Verschiebt den langsam um 'distance' px horizontal nach rechts/links
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
     * Verschiebt den Text langsam um 'distance' px vertikal nach unten/oben
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
     * Ändert die Länge / Breite des Textes. Die Werte müssen > 0 sein
     */
    public void changeSize(int size)
    {
        erase();
        this.size = size;
        draw();
    }

    /**
     * Ändert die Farbe des Textes
     * Erlaubt sind alle Farben der Color-Enumeration
     */
    public void changeColor(Color newColor)
    {
        color = newColor;
        draw();
    }
    
    /*
     * Zeichnet den Text mit den aktuellen Eigenschaften auf dem Canvas
     */
    private void draw()
    {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, generateShapeFromText(new Font(font, 100, size), 
                        text, xPosition, yPosition));
            canvas.wait(10);
        }
    }

    /*
     * Löscht den Text auf dem Canvas
     */
    private void erase()
    {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /*
     * Erstellt einen Shape aus dem Text
     */
    private Shape generateShapeFromText(Font font, String string, int x, int y) 
    {
        BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        
        try {
           GlyphVector vect = font.createGlyphVector(g2.getFontRenderContext(), string);
           Shape shape = vect.getOutline(0f + x, (float) -vect.getVisualBounds().getY() + y);
           
           width = (int)shape.getBounds().getWidth();
           height = (int)shape.getBounds().getHeight();
           
           return shape;
        } finally {
           g2.dispose();
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
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public String getInstanceName()
    {
        return this.name;
    }
}
