import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase padre de los diversos ataques.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class Ataque extends Actor
{
    protected GreenfootSound explosionSound;
    protected int velDisparo;
    /**
     * Constructor de la clase ataque.
     */
    public Ataque()
    {
        explosionSound = new GreenfootSound("laser.mp3");
    }
    
    /**
     * Act - do whatever the Ataque wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        disparar();
    }
    
    /**
     * Metodo para hacer el polimorfismo de los ataques.
     */
    public void disparar()
    {}
}
