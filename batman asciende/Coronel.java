import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Coronel here.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class Coronel extends Enemigo
{
    /**
     * Act - do whatever the Coronel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       super.act(); 
    }   
    
    /**
     * Mueve al Coronel en linea recta, si el Coronel traspasa el límite derecho del mundo
     * cambia su posición en x a 5000 para que vuelva a aparecer en el mundo.
     */
    public void mover()
    {
        setLocation(getX()-7, getY());
        if(pasoAlJugador())
        {
            setLocation(5000,getY());
        }
    }
    
    /**
     * Pone bombas en el mundo cada 2 segundos.
     */
    public void atacar()
    {
        if(shotTimer.millisElapsed() > 2000)
        {
            getWorld().addObject(new BombaEnemigo(),getX(), getY()+getImage().getHeight()/2);
            shotTimer.mark();
        }
    }
}
