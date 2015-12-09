import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Si se da clic en éste boton se inicia el juego.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class BotonPlay extends Boton
{
    /**
     * Act - do whatever the BotonPlay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    /**
     * A dar clic a este boton se inicia el juego.
     */
    public void click()
    {
        if(Greenfoot.mouseClicked(this)){
            clicSound.play();
            Greenfoot.setWorld(new Escenario());
        }
    }
}
