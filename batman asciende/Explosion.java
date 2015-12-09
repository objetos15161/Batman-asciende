import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *Pone una imagen de una explosión cuando se destruye una nave.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class Explosion extends Actor
{
    private GreenfootImage[] explosiones;
    private int tipoExplosion;
    SimpleTimer timer;
    /**
     *  Si tipo==1 exploto nave; tipo==2 balaVSironman; tipo==3 ataqueVSataque
     */
    public Explosion(int tipo)
    {
        explosiones = new GreenfootImage[5];
        explosiones[0] = new GreenfootImage("explosion.png");
        explosiones[1] = new GreenfootImage("explosion.png");
        explosiones[2] = new GreenfootImage("explosion.png");
        explosiones[3] = new GreenfootImage("explosion.png");
        explosiones[4] = new GreenfootImage("explosion.png");
        
        tipoExplosion = tipo; 
        
        timer = new SimpleTimer();
        timer.mark();
    }
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        explota();
    }    
    
    /**
     * Cuando explota una nave aparece una imagen, dura 1 segundo y desaparece.
     */
    public void explota()
    {
        move(-10);
        
        if(tipoExplosion == 1)
        {
            if(timer.millisElapsed() < 150)
                setImage(explosiones[2]);
            else if(timer.millisElapsed() > 150 && timer.millisElapsed() < 300)
                setImage(explosiones[1]);
            else if(timer.millisElapsed() > 300 && timer.millisElapsed() < 450)
                setImage(explosiones[0]);
            else
                getWorld().removeObject(this);
        }
        else if(tipoExplosion == 2)
        {
            if(timer.millisElapsed() < 500)
                setImage(explosiones[3]);
            else
                getWorld().removeObject(this);
        }
        else if(tipoExplosion == 3)
        {
            if(timer.millisElapsed() < 500)
                setImage(explosiones[4]);
            else
                getWorld().removeObject(this);
        }
    }
}
