import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Capitan here.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class Capitan extends Enemigo
{
    private int stepsX;
    private int stepsY;
    private GreenfootSound disparoEnemigo;
    /**
     * Constructor de la clase Capitan. Se inicializan las variables stepsX y stepsY que se utilizan para 
     * el movimiento del enemigo.
     */
    public Capitan()
    {
        stepsX = 100;
        stepsY = 1;
        disparoEnemigo = new GreenfootSound("disparo.mp3");
    }
    
    /**
     * Act - do whatever the Capitan wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!pasoAlJugador())
        {    
            super.act();
            rebota();
        }
        else 
        {
            Escenario e = (Escenario)getWorld();
            Batman i = e.getBatman();
            //i.restaSalud(i.getSalud()/2);
            
            e.restaNumEnemigos();
            
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Mueve al Capitan en zig-zag
     */
    public void mover()
    {
        if(stepsX > 0)
        {
            setLocation(this.getX()-velMov, this.getY()+stepsY);
            stepsX--;
        }
        else if(stepsX <= 0 && stepsX > -70 && getX()+getImage().getWidth()/2 < getWorld().getWidth())
        {
            setLocation(this.getX()+velMov, this.getY()+stepsY);
            stepsX--;
        }   
        else if(stepsX <= -70)
            stepsX = 100;
    }
    
    /**
     * Si toca un borde ya sea superior o inferior se invierte la dirección.
     */
    public void rebota()
    {
        if(getY() <= getImage().getHeight()/2)
            stepsY = 1;
        else if(getY() > getWorld().getHeight()-getImage().getHeight()/2)
            stepsY = -1;
        else if(getX() >= getWorld().getWidth())
            stepsX = 100;
    }
    
    /**
     * Lanza 3 disparos cada 5 segundos.
     */
    public void atacar()
    {
        if(shotTimer.millisElapsed() > 5000)
        {
            disparoEnemigo.play();
            getWorld().addObject(new DisparoEnemigo(),this.getX()-getImage().getWidth()/2, this.getY()-50);
            getWorld().addObject(new DisparoEnemigo(),this.getX()-getImage().getWidth()/2, this.getY());
            getWorld().addObject(new DisparoEnemigo(),this.getX()-getImage().getWidth()/2, this.getY()+50);
            shotTimer.mark();
        }
    }
}
