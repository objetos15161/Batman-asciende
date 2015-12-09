import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase padre de los enemigos.
 * 
 * @author (Julio Cesar Marin) 
 * @version (a version number or a date)
 */
public class Enemigo extends Personaje
{
    protected int velMov; //Variable que representa la velocidad del enemigo.
    /**
     * Constructor de la clase Enemigo. Se inicializa la variable velMov con un 
     * mínimo de 5 y un máximo de 7.
     */
    public Enemigo()
    {
        velMov = Greenfoot.getRandomNumber(3)+5;
    }
    
    /**
     * Act - do whatever the Enemigo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        mover();
        if(getX() <= 1800)
            atacar();
    }
    
    /**
     * Metodo polimórfico.
     */
    public void mover(){}
    
    /**
     * Metodo polimórfico.
     */
    public void atacar(){}
    
    /**
     * Si la posicion en 'X' del enemigo es menor a 0, regresa true
     * si no, regresa false.
     */
    public boolean pasoAlJugador()
    {
        if(getX()+getImage().getWidth()/2 < 0)
            return true;
        return false;
    }
}
