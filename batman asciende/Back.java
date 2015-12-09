import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Boton para cerrar una ventana.
 * 
 * @author (Julio Cesar Marin) 
 * @version (aDiciembre 2015)
 */
public class Back extends Boton
{
    private Actor actor;
    /**
     * Constructor de la clase Back.
     */
    public Back(Actor a)
    {
        actor = a;
    }
    
    /**
     * Act - do whatever the Back wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        this.click();
    }    
    
    /**
     * Cierra la ventana para ver nuevamente el menu.
     */
    public void click()
    {
        if(Greenfoot.mouseClicked(this)){
            clicSound.play();
            getWorld().removeObject(actor);
            getWorld().removeObject(this);
        }
    }
}
