import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Disparo del capitan.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class DisparoEnemigo extends Ataque
{
    /**
     * Constructor de la clase DisparoEnemigo.
     * Inicializa la variable 'velDisparo' con un numero aleatorio para la
     * velocidad de la bala.
     */
    public DisparoEnemigo()
    {
        velDisparo = Greenfoot.getRandomNumber(11)+10;
    }
    
    /**
     * Act - do whatever the DisparoEnemigo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }   
    
    /**
     *  Cuando se cre el disparo en el mundo:
     *  Checa si toca al jugador, si es asi le resta salud y se elimina la bala. 
     *  Checa si las coordenadas son menores a 0 y si es asi elimina la bala.
     *  Checa si toca el disparo del jugador, si es asi se eliminan ambos.
     *  Se mueve por el mundo.
     */
    public void disparar()
    {
        if(isTouching(Batman.class))
        {
            Batman i = (Batman)getOneIntersectingObject(Batman.class);
            i.restaSalud(Greenfoot.getRandomNumber(4)+2);
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
            setLocation(getX()-velDisparo,getY());
    }
}
