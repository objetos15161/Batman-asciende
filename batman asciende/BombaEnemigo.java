import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Este ataque espera 10 segundos desde que se creo en el mundo, después sigue al jugador para explotar.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class BombaEnemigo extends Ataque
{
    private SimpleTimer timerShot;
    private GreenfootImage bomba0;
    private GreenfootImage bomba1;
    /**
     * Constructor de la clase BombaEnemigo.
     */
    public BombaEnemigo()
    {
        timerShot = new SimpleTimer();
        timerShot.mark();
        bomba0 = new GreenfootImage("bane.png");
        bomba1 = new GreenfootImage("espantapajaros.png");
        setImage(bomba0);
    }
    
    /**
     * Act - do whatever the BombaEnemigo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    /**
     * Checa si toca al jugador, si es así le resta salud y de borra a sí mismo.
     * Checa si salió del mundo, si es así se elimina a sí mismo.
     * Checa si toco a un disparo del jugador, si es así se eliminan ambos objetos.
     * Y se mueve por el mundo.
     */
    public void disparar()
    {
        if(isTouching(Batman.class))
        {
            Batman i = (Batman)getOneIntersectingObject(Batman.class);
            i.restaSalud(7);
            getWorld().addObject(new Explosion(2), getX(), getY());
            getWorld().removeObject(this);
        }
        else if(getY()-getImage().getHeight()/2 > getWorld().getHeight())
            getWorld().removeObject(this);
        else if(isTouching(DisparoJugador.class))
        {
            DisparoJugador dp = (DisparoJugador)getOneIntersectingObject(DisparoJugador.class);
            getWorld().removeObject(dp);
            getWorld().addObject(new Explosion(3), getX(), getY());
            Escenario es = (Escenario)getWorld();
            es.getBatman().setPuntos(4);
            getWorld().removeObject(this);
        }
        else
            movimiento();
    }
    
    /**
     * Cuando se crea una bomba en el mundo, se espera un lapso de tiempo de 10 segundos
     * y al transcurrido ese tiempo la bomba comienza a seguir al jugador.
     */
    public void movimiento()
    {
        if(timerShot.millisElapsed() > 10000)
        {
            setImage(bomba1);
            Escenario e = (Escenario)getWorld();
            Batman i = e.getBatman();
            turnTowards(i.getX(), i.getY());
            move(5);
        }
    }
}