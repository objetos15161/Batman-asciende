import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase padre de los bonus.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class Bonus extends Actor
{
    private int dir;
    private int velMov;
    protected GreenfootSound vidaUp;
    protected GreenfootSound bonusSound;
    /**
     * Constructor de la clase Bonus.
     */
    public Bonus()
    {
        dir = Greenfoot.getRandomNumber(3) + 1;
        velMov=dir;
        vidaUp = new GreenfootSound("vida.mp3");
        bonusSound = new GreenfootSound("bonus.mp3");
    }
    
    /**
     * Act - do whatever the Bonus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        mueve();
        tocoBorde();
        tocoJugador();
    }    
    
    /**
     * Bonus del juego!!!
     * Checa si toco al jugador y pone la energia en 100.
     */
    public void tocoJugador()
    {
        Batman i = (Batman)getOneIntersectingObject(Batman.class);
        if(i!=null)
        {
            i.setPuntos(10);
            bonus(i);
        }
    }
    
    public void bonus(Batman i)
    {}
    
    /**
     * Dependiendo de la direccion (dir) la figura se mueve.
     */
    public void mueve()
    {
        switch(dir)
        {
            case 1:setLocation(getX()+velMov,getY()+velMov);
            break;
            case 2:setLocation(getX()-velMov,getY()+velMov);
            break;
            case 3:setLocation(getX()-velMov,getY()-velMov);
            break;
            case 4:setLocation(getX()+velMov,getY()-velMov);
            break;
        }
    }
    
    /**
     * Si toca el borde el circulo rebota.
     */
    public void tocoBorde()
    {
        if(getY() >= getWorld().getHeight()-getImage().getWidth()/2)
        {
            if(dir == 1)
                dir=4;
            else if(dir==2)
                dir=3;
        }
        else if(getY() <= getImage().getWidth()/2)
        {
            if(dir == 3)
                dir=2;
            else if(dir == 4)
                dir=1; 
        }
        else if(getX() >= getWorld().getWidth()-getImage().getWidth()/2)
        {
            if(dir == 4)
                dir=3;
            else if(dir == 1)
                dir=2;
        }
        else if(getX() <= getImage().getWidth()/2)
        {
            if(dir == 3)
                dir=4;
            else if(dir == 2)
                dir = 1;
        }
    }
}
