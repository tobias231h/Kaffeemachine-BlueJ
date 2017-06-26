import java.awt.*;

/**
 * Machinentest mit allen Formen für die Kaffeemachine inkl. deren Funktionen
 */
public class Machine
{  
    /**
     * Die Kaffeemachine in ihrer vollen Pracht
     */
    // Stärke
    private Rechteck[] staerken;
    private int staerkeLevel;
    private Text statusText;
    private Text staerkeText;
    private Text minusText;
    private Text plusText;
    private Rechteck tasse;
    private Rechteck tassenHenkel;
    private Rechteck wasser;
    
    public int wasserStand = 194;
    
    public Machine()
    {
        Rechteck machine = new Rechteck("Machine", 440, 420, 30, 30, Color.black, true);
        Rechteck boden = new Rechteck("Boden", 480, 15, 10, 442, Color.gray, true);
        Rechteck knopfHintergrund = new Rechteck("KnopfHintergrund", 20, 60, 10, 372, Color.black, true);
        Circle anAusKnopf = new Circle("AnAusKnopf", 18, 11, 393, Color.red, true);
        Rechteck anAusStrich = new Rechteck("AnAusStrich", 4, 5, 18, 388, Color.red, true);
        Rechteck statusDisplay = new Rechteck("StatusDisplay", 230, 45, 125, 35, Color.lightGray, true);
        Rechteck auswahlDisplay = new Rechteck("AuswahlDisplay", 400, 70, 50, 80, Color.white, true);
        
        // Wasserfüllstand
        Rechteck fuellBody = new Rechteck("FuellBody", 15, 200, 15, 60, Color.black, true);
        Rechteck weisserTeil = new Rechteck("WeisserTeil", 9, 194, 18, 63, Color.white, true); 
        wasser = new Rechteck("Wasser", 9, 194, 18, 63, Color.cyan.darker().darker(), true);
        
        int coffeChooseOffset = 20;
        Text cappuccino = new Text("Cappuccino", "Cappuccino", "Arial", 53 + coffeChooseOffset, 132, 15, Color.black, true);
        Circle cappuccinoButton = new Circle("CappuccinoButton", 35, 74 + coffeChooseOffset, 90, Color.darkGray, true);
        Text kaffee = new Text("Kaffee", "Kaffee", "Arial", 143 + coffeChooseOffset, 132, 15, Color.black, true);
        Circle kaffeeButton = new Circle("KaffeeButton", 35, 150 + coffeChooseOffset, 90, Color.darkGray, true);
        Text heisseMilch = new Text("HeisseMilch", "Heiße Milch", "Arial", 200 + coffeChooseOffset, 132, 15, Color.black, true);
        Circle heisseMilchButton = new Circle("HeisseMilchButton", 35, 220 + coffeChooseOffset, 90, Color.darkGray, true);
        Text latte = new Text("Latte", "Latte Macchiato", "Arial", 290 + coffeChooseOffset, 132, 15, Color.black, true);
        Circle latteButton = new Circle("LatteButton", 35, 320 + coffeChooseOffset, 90, Color.darkGray, true);
        
        // Tasse + Unterlage
        Rechteck unterlage = new Rechteck("Unterlage", 150, 10, 167, 432, Color.white, true);
        tasse = new Rechteck("Tasse", 100, 100, 190, 331, Color.cyan, true);
        tassenHenkel = new Rechteck("TassenHenkel", 20, 45, 292, 340, Color.cyan, true);
        
        // Auslass
        Rechteck auslassKoerper = new Rechteck("AuslassKoerper", 150, 100, 167, 158, Color.white.darker().darker().darker(), true);
        Rechteck auslass1 = new Rechteck("Auslass1", 15, 40, 198, 259, Color.white.darker().darker().darker(), true);
        Rechteck auslass2 = new Rechteck("Auslass2", 15, 40, 263, 259, Color.white.darker().darker().darker(), true);
        
        // Status-Display
        statusText = new Text("Status", "Status: Bereit", "Arial", 190, 42, 15, Color.green, true);
        staerkeText = new Text("Stärke", "Stärke", "Arial", 130, 60, 15, Color.white, true);
        plusText = new Text("+", "+", "Arial", 315, 62, 18, Color.white, true);
        minusText = new Text("-", "-", "Arial", 191, 66, 18, Color.white, true);
        
        staerken = new Rechteck[5];
        staerkeLevel = 0;
        staerken[0] = new Rechteck("S1", 10, 10, 210, 62, Color.black, true);
        staerken[1] = new Rechteck("S2", 10, 10, 229, 62, Color.black, false);
        staerken[2] = new Rechteck("S3", 10, 10, 248, 62, Color.black, false);
        staerken[3] = new Rechteck("S4", 10, 10, 267, 62, Color.black, false);
        staerken[4] = new Rechteck("S5", 10, 10, 286, 62, Color.black, false);
    }
    
    /**
     * Becher:
     * 1 = Normal, blau mit Henkel
     * 2 = Becher, grau
     */
    public void switchToBecher(int num)
    {
        switch(num)
        {
            case 1:
                tasse.changeColor(Color.cyan);
                tassenHenkel.changeColor(Color.cyan);
                break;
            case 2:
                tasse.changeColor(Color.gray);
                tassenHenkel.changeColor(Color.black);
                break;
        }
    }
    
    /**
     * HASTDUDIEANZAHLANWASSERGESEHENAAAAAA = Anzahl an Wasser (max. 194 weniger)
     */
    public void wenigerWasser(int HASTDUDIEANZAHLANWASSERGESEHENAAAAAA)
    {
        while(HASTDUDIEANZAHLANWASSERGESEHENAAAAAA > 0)
        {
            if(wasser.height == 0) {  
                HASTDUDIEANZAHLANWASSERGESEHENAAAAAA = 0;
                break;
            }
            wasser.changeSizeWithoutRedraw(wasser.width, --wasser.height);
            wasser.moveVerticalWithoutRedraw(1);
            
            wasser.draw();
            
            Canvas.getCanvas().wait(40);
            HASTDUDIEANZAHLANWASSERGESEHENAAAAAA--;
            wasserStand--;
        }
    }
    
    public void wasserFuellen()
    {
        while(wasserStand < 194)
        {
            wasser.changeSizeWithoutRedraw(wasser.width, ++wasser.height);
            wasser.moveVerticalWithoutRedraw(-1);
            
            wasser.draw();
            
            Canvas.getCanvas().wait(40);
            wasserStand++;
        }
    }
    
    /**
     * num-Werte:
     * 1 = An
     * 2 = Aus
     */
    public void switchOnOff(int num)
    {
        if(num == 2)
        {
            statusText.makeInvisible();
            staerkeText.makeInvisible();
            plusText.makeInvisible();
            minusText.makeInvisible();
            while(staerkeLevel > 0)
            {
                staerken[staerkeLevel].makeInvisible();
                staerkeLevel--;
            }
            staerken[0].makeInvisible();
        } else if (num == 1)
        {
            statusText.makeVisible();
            staerkeText.makeVisible();
            plusText.makeVisible();
            minusText.makeVisible();
            staerken[0].makeVisible();
            setStatus(1);
        }
    }
    
    public void mehrStaerke()
    {
        if(staerkeLevel < 4)
        {
            staerkeLevel++;
            staerken[staerkeLevel].makeVisible();   
        }
    }
    
    public void wenigerStaerke()
    {
        if(staerkeLevel > 0)
        {
            staerken[staerkeLevel].makeInvisible();
            staerkeLevel--;
        }
    }
    
    /**
     * Status Nummern:
     * 1 = Bereit
     * 2 = Ausschenken
     * 3 = Becher hinstellen
     */
    public void setStatus(int status)
    {
        switch(status)
        {
            case 1:
                statusText.changeColor(Color.green);
                statusText.changeText("Status: Bereit");
                statusText.setX(190);
                break;
            case 2:
                statusText.changeColor(Color.red);
                statusText.changeText("Status: Ausschenken");
                statusText.setX(172);
                break;
            case 3:
                statusText.changeColor(Color.blue);
                statusText.changeText("Bitte Becher unterstellen");
                statusText.setX(165);
                break;
            case 4:
                statusText.changeColor(Color.blue);
                statusText.changeText("Bitte mit Wasser füllen");
                statusText.setX(159);
        }
    }
    
    public void wasser()
    {
        auslassen(Color.cyan);
    }
    
    public void kaffee()
    {
        wenigerWasser(25);
        switch (staerkeLevel)
        {
            case 0:
                auslassen(new Color(152, 123, 123));
                break;
            case 1:
                auslassen(new Color(126, 98, 98));
                break;
            case 2:
                auslassen(new Color(112, 87, 87));
                break;
            case 3:
                auslassen(new Color(83, 65, 65));
                break;
            case 4:
                auslassen(new Color(30, 20, 20));
                break;
        }
    }
    
    public void milch()
    {
        auslassen(Color.white);
    }
    
    /*
     * Methode zum Auslassen einer Flüssigkeit
     * Milch: Color.white
     * Kaffee: new Color(101, 74, 74);
     */
    private void auslassen(java.awt.Color color)
    {
        setStatus(2);
        
        Rechteck milchLinks = new Rechteck("MilchLinks", 9, 1, 201, 300, color, true);
        Rechteck milchRechts = new Rechteck("MilchRechts", 9, 1, 266, 300, color, true);
        
        while ((milchLinks.yPosition + milchLinks.height) < 330)
        {
            // Anfang des Auslassens
            milchLinks.changeSizeWithoutRedraw(9, (milchLinks.height + 1));
            milchRechts.changeSizeWithoutRedraw(9, (milchRechts.height + 1));
            
            milchRechts.draw();
            milchLinks.draw();
            
            Canvas.getCanvas().wait(10);
        }
        
        Canvas.getCanvas().wait(5000);
        
        while (milchLinks.height > 0)
        {
            milchLinks.changeSizeWithoutRedraw(9, (milchLinks.height - 1));
            milchLinks.moveVerticalWithoutRedraw(1);
            milchRechts.changeSizeWithoutRedraw(9, (milchRechts.height - 1));
            milchRechts.moveVerticalWithoutRedraw(1);
            
            milchLinks.draw();
            milchRechts.draw();
            
            Canvas.getCanvas().wait(10);
        }
        
        setStatus(1);
        
        if(wasserStand < 25)
        {
            setStatus(4);
        }
    }
}
