import greenfoot.*;

/**
 * Clase padre de los botones del menu.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class Boton extends Actor
{
    private int velX;
    private GreenfootSound sound;
    protected GreenfootSound clicSound;
    private boolean playSonido;
    public Boton()
    {
        velX=0;
        sound = new GreenfootSound("bonus.mp3");
        clicSound = new GreenfootSound("bonus.mp3");
        playSonido = false;
    }
    /**
     * Act - do whatever the Boton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        acomoda();
        click();
       
    }  
    
    /**
     * Pone el boton en su lugar.
     */
    public void acomoda()
    {
        if(getX() < getWorld().getWidth()/2)
            move(velX);
        else
        {
            setLocation(getWorld().getWidth()/2, getY());
            if(!playSonido)
            {
                sound.play();
                playSonido = true;
            }
        }
        if(velX < 10)
            velX++;
    }
    
    /**
     * Metodo polimórfico.
     */
    public void click(){}
}