import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase padre de los personajes del juego.
 * 
 * @author (Julio Cesar Marin) 
 * @version (a version number or a date)
 */
public class Personaje extends Actor
{
    protected SimpleTimer shotTimer;
    /**
     * Constructor de la clase Personaje.
     */
    public Personaje()
    {
        shotTimer = new SimpleTimer();  
        shotTimer.mark();
    }
    
    /**
     * Act - do whatever the Personaje wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }
}