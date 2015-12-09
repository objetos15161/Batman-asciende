import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Kamikase here.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class Kamikase extends Enemigo
{
    /**
     * Act - do whatever the Kamikase wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
       
    }    
    
     /**
     * Mueve al enemigo de forma que persiga al jugador.
     */
    public void mover()
    {
        move(velMov);
        Escenario e = (Escenario)getWorld();
        Batman i = e.getBatman();
        turnTowards(i.getX(), i.getY());
    }
    
    /**
     * Ataca al enemigo. Obtiene la posición del jugador y se dirige hacia él para
     * explotar a su lado.
     */
    public void atacar()
    {
        if(isTouching(Batman.class))
        {
            Escenario e = (Escenario)getWorld();
            Batman i = e.getBatman();
            i.restaSalud(7);
        }
    }
}
