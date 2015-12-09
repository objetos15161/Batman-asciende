import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Le da disparos extra al jugador.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class BonusDisparo extends Bonus
{
    /**
     * Act - do whatever the BonusDisparo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    /**
     * Incrementa los disparos al jugador.
     */
    public void bonus(Batman i)
    {
        bonusSound.play();
        i.setDisparos(10);
        getWorld().removeObject(this);
    }
}
