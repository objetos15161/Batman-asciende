import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Disparo del jugador.
 * 
 * @author (Julio Cesar Marin) 
 * @version (2015)
 */
public class DisparoJugador extends Ataque
{
    /**
     * Constructor de Disparo
     */
    public DisparoJugador()
    {
        velDisparo = 10;
    }
    
    /**
     * Act - do whatever the Disparo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    /**
     * Cuando se crea un Disparo en el mundo, avanza.
     * Checa si toco a un enemigo, o a un ataque de este, de ser así se eliminan ambos objetos.
     * También checa si salió del mundo, si es así se elimina.
     */
    public void disparar()
    {
        Escenario es = (Escenario)getWorld();
        if(isTouching(Enemigo.class))
        {
            explosionSound.play();
            Enemigo e = (Enemigo)getOneIntersectingObject(Enemigo.class);
            getWorld().addObject(new Explosion(1), e.getX(), e.getY());
            getWorld().removeObject(e);
            es.restaNumEnemigos();
            es.getBatman().setPuntos(Greenfoot.getRandomNumber(6)+10);
            getWorld().removeObject(this);
        }
        else if(getX() > getWorld().getWidth())
            getWorld().removeObject(this);
        else if(isTouching(DisparoEnemigo.class))
        {
            DisparoEnemigo de = (DisparoEnemigo)getOneIntersectingObject(DisparoEnemigo.class);
            getWorld().removeObject(de);
            getWorld().addObject(new Explosion(3), getX(), getY());
            es.getBatman().setPuntos(3);
            getWorld().removeObject(this);
        }
        else if(isTouching(GeneralBoss.class))
        {
            GeneralBoss g = (GeneralBoss)getOneIntersectingObject(GeneralBoss.class);
            g.restaSalud(8, explosionSound);
            es.getBatman().setPuntos(5);
            getWorld().removeObject(this);
        }
        else
            setLocation(getX()+velDisparo,getY());
    }
}