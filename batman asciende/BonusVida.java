import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Le da una vida al jugador.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class BonusVida extends Bonus
{
    /**
     * Act - do whatever the BonusVida wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    /**
     * Aumenta una vida al jugador.
     */
    public void bonus(Batman i)
    {
        vidaUp.setVolume(80);
        vidaUp.play();
        i.incrementaVidas();
        getWorld().removeObject(this);
    }
}
