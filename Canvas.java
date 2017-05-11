import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * Canvas is a class to allow for simple graphical drawing on a canvas.
 * This is a modification of the general purpose Canvas, specially made for
 * the BlueJ "shapes" example. 
 *
 * @author: Bruce Quig
 * @author: Michael Kolling (mik)
 *
 * @version: 1.6 (shapes)
 */
public class Canvas
{
    // Note: The implementation of this class (specifically the handling of
    // shape identity and colors) is slightly more complex than necessary. This
    // is done on purpose to keep the interface and instance fields of the
    // shape objects in this project clean and simple for educational purposes.

    private static Canvas canvasSingleton;

    /**
     * Factory method to get the canvas singleton object.
     */
    public static Canvas getCanvas()
    {
        if(canvasSingleton == null) {
            canvasSingleton = new Canvas("L4H4 Total-Nice Coffee 420 XxXMachine", 500, 500, 
                                         Color.white);
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }
    
    //  ----- instance part -----

    private JFrame frame;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color backgroundColour;
    private Image canvasImage;
    private List objects;
    private HashMap shapes;
    
    public static Kaffeemachine kaffeemachine;
    public static void setKaff(Kaffeemachine k)
    {
        kaffeemachine = k;
    }
    
    /**
     * Create a Canvas.
     * @param title  title to appear in Canvas Frame
     * @param width  the desired width for the canvas
     * @param height  the desired height for the canvas
     * @param bgClour  the desired background colour of the canvas
     */
    private Canvas(String title, int width, int height, Color bgColour)
    {
        frame = new JFrame();
        canvas = new CanvasPane();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColour = bgColour;
        frame.pack();
        objects = new ArrayList();
        shapes = new HashMap();
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() 
        {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                kaffeemachine.setAction("Abort");
                System.exit(0);
            }
        });
    }

    /**
     * Set the canvas visibility and brings canvas to the front of screen
     * when made visible. This method can also be used to bring an already
     * visible canvas to the front of other windows.
     * @param visible  boolean value representing the desired visibility of
     * the canvas (true or false) 
     */
    public void setVisible(boolean visible)
    {
        if(graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background colour
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(backgroundColour);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(visible);
    }

    /**
     * Draw a given shape onto the canvas.
     * @param  referenceObject  an object to define identity for this shape
     * @param  color            the color of the shape
     * @param  shape            the shape object to be drawn on the canvas
     */
     // Note: this is a slightly backwards way of maintaining the shape
     // objects. It is carefully designed to keep the visible shape interfaces
     // in this project clean and simple for educational purposes.
    public void draw(Object referenceObject, Color color, Shape shape)
    {
        objects.remove(referenceObject);   // just in case it was already there
        objects.add(referenceObject);      // add at the end
        shapes.put(referenceObject, new ShapeDescription(shape, color));
        redraw();
    }
      
 
    /**
     * Erase a given shape's from the screen.
     * @param  referenceObject  the shape object to be erased 
     */
    public void erase(Object referenceObject)
    {
        objects.remove(referenceObject);   // just in case it was already there
        shapes.remove(referenceObject);
        redraw();
    }

    /**
     * Set the foreground colour of the Canvas.
     * @param  newColour   the new colour for the foreground of the Canvas 
     */
    public void setForegroundColor(Color colorEnum)
    {
        graphic.setColor(colorEnum);
    }

    /**
     * Wait for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milliseconds  the number 
     */
    public void wait(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        } 
        catch (Exception e)
        {
            // ignoring exception at the moment
        }
    }

    /**
     * Redraw ell shapes currently on the Canvas.
     */
    private void redraw()
    {
        erase();
        for(Iterator i=objects.iterator(); i.hasNext(); ) {
            ((ShapeDescription)shapes.get(i.next())).draw(graphic);
        }
        canvas.repaint();
    }
       
    /**
     * Erase the whole canvas. (Does not repaint.)
     */
    private void erase()
    {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColour);
        Dimension size = canvas.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }
    
    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class CanvasPane extends JPanel implements MouseListener
    {                
        public CanvasPane()
        {
            addMouseListener(this);
        } 
           
        public void paint(Graphics g)
        {
            g.drawImage(canvasImage, 0, 0, null);
        }
        
        /**
         * Methoden aus dem MouseListener-Interface
         * Diese müssen implementiert werden
         */
        public void mousePressed(MouseEvent e) 
        {   
        }
        
        public void mouseReleased(MouseEvent e)
        {    
        }
        
        public void mouseEntered(MouseEvent e) 
        {   
        }
        
        public void mouseExited(MouseEvent e) 
        {   
        }
        
        public void mouseClicked(MouseEvent e) 
        {
            // VERARBEITUNG
            IShape[] shp = determineObject(e.getPoint());
            for (int i = 0; i < shp.length; i++)
            {
                if(shp[i] != null)
                {
                    String msg = "";
                    switch(shp[i].getInstanceName())
                    {
                        case "CappuccinoButton":
                           kaffeemachine.setAction("Cappuccino");
                           break;
                        case "KaffeeButton":
                           kaffeemachine.setAction("Kaffee");
                           break;
                        case "HeisseMilchButton":
                           kaffeemachine.setAction("HM");
                           break;
                        case "+":
                            kaffeemachine.setAction("+");
                            break;
                        case "-":
                            kaffeemachine.setAction("-");
                            break;
                        case "AnAusKnopf":
                            kaffeemachine.setAction("switchAnAus");
                            break;
                        case "Tasse":
                            kaffeemachine.setAction("Tasse");
                            break;
                        case "LatteButton":
                            kaffeemachine.setAction("Latte");
                            break;
                        case "Boden":
                            kaffeemachine.setAction("TROL");
                            break;
                        case "Wasser":
                            kaffeemachine.setAction("Wasser");
                            break;
                    }
                }
            }
        }
        
        public IShape[] determineObject(Point p)
        {
            IShape[] shp = null;
            int count = 0;
            for ( Object obj : shapes.keySet() ) 
            {
                if(p.getX() >= ((IShape)obj).getX() && p.getX() <= (((IShape)obj).getX() + ((IShape)obj).getWidth()))
                {
                    if(p.getY() >= ((IShape)obj).getY() && p.getY() <= (((IShape)obj).getY() + ((IShape)obj).getHeight()))
                    {
                        if(shp == null)
                        {
                            shp = new IShape[5];
                        }
                        shp[count] = (IShape)obj;
                        count++;
                    }
                }
            }
            
            if(shp == null) shp = new IShape[0];
            
            return shp;
        }    
    }
    
    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class ShapeDescription
    {
        private Shape shape;
        private Color colorEnum;

        public ShapeDescription(Shape shape, Color color)
        {
            this.shape = shape;
            colorEnum = color;
        }

        public void draw(Graphics2D graphic)
        {
            graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            setForegroundColor(colorEnum);
            graphic.fill(shape);
        }
    }

}
