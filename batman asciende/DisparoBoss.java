import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Disparo del GeneralBoss.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class DisparoBoss extends Ataque
{
    private int dir;
    /**
     * Constructor de la clase DisparoBoss.
     */
    public DisparoBoss(int direction)
    {
        velDisparo = 7;
        dir = direction;
    }
    
    /**
     * Act - do whatever the DisparoBoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }   
    
    /**
     * Checa si toco al jugador, si salió del mundo, y si toco a un disparo del jugador, de ser 
     * así se elimina a sí mismo.
     * Dependiendo de la dirección el disparo se mueve en línea recta o en diagonal.
     */
    public void disparar()
    {
        if(isTouching(Batman.class))
        {
            Batman i = (Batman)getOneIntersectingObject(Batman.class);
            i.restaSalud(5);
            getWorld().addObject(new Explosion(2), getX(), getY());
            getWorld().removeObject(this);
        }
        else if(getX() < 0)
            getWorld().removeObject(this);
        else if(isTouching(DisparoJugador.class))
        {
            DisparoJugador dp = (DisparoJugador)getOneIntersectingObject(DisparoJugador.class);
            getWorld().removeObject(dp);
            getWorld().addObject(new Explosion(3), getX(), getY());
            Escenario es = (Escenario)getWorld();
            es.getBatman().setPuntos(3);
            getWorld().removeObject(this);
        }
        else
        {
            switch(dir)
            {
                case 0://se mueve en linea recta
                    setLocation(getX()-velDisparo, getY());
                break;
            
                case 1://se mueve en diagonal hacia arriba
                    setLocation(getX()-velDisparo, getY()-5);
                break;
            
                case 2://se mueve en diagonal hacia abajo
                    setLocation(getX()-velDisparo, getY()+5);
                break;
            }
            invierteDireccion();
        }
    }
    
    /**
     * Checa si la bala va en diagonal y si toca el borde arriba o abajo se cambia de 
     * direccion.
     */
    public void invierteDireccion()
    {
        if(dir == 1 && getY() < 0+getImage().getHeight()/2)
            dir=2;
        else if(dir == 2 && getY() > getWorld().getHeight()-getImage().getHeight()/2)
            dir=1;
    }
}
