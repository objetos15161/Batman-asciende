import greenfoot.*;

/**
 * Write a description of class Menu here.
 * 
 * @author (Julio Cesar Marin) 
 * @version (a version number or a date)
 */
public class Menu extends World
{
    private GreenfootSound soundFondo;
    private BotonPlay botonplay;
    int i;
    /**
     * Constructor for objects of class Menu.
     * 
     */
    public Menu()
    {    
        super(1500,600, 1, false); 
        
        soundFondo = new GreenfootSound("Hans Zimmer - End (Batman the Dark knight).mp3");
        botonplay = new BotonPlay();
        
        prepare(); 
        Greenfoot.setWorld(this);
    }
    
    public void act()
    {
       
        soundFondo.playLoop();
        if(Greenfoot.mouseClicked(botonplay) && soundFondo.isPlaying())
            soundFondo.stop();
    }
    
    /**
     * Coloca los botones en el mundo
     */
    public void prepare()
    {
        addObject(botonplay, 10, 100);
        addObject(new BotonHelp(),100, 200);
        //addObject(new BotonCredits(), -600, 300);
        addObject(new BotonRecords(), -800, 400);
    }
}
