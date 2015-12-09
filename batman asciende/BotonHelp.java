import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Pone la ayuda en la pantalla.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class BotonHelp extends Boton
{
    private Help help;
    /**
     * Constructor de la clase BotonHelp.
     */
    public BotonHelp()
    {
        help = new Help();
    }
    
    /**
     * Act - do whatever the BotonHelp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }   
    
    /**
     * Al dar clic en el boton aparece la ayuda.
     */
    public void click()
    {
        if(Greenfoot.mouseClicked(this)){
            clicSound.play();
            getWorld().addObject(help, 
                                getWorld().getWidth()/2,
                                getWorld().getHeight()/2);
            getWorld().addObject(new Back(help), 
                                help.getX() - help.getImage().getWidth()/2,
                                help.getY() - help.getImage().getHeight()/2);
        }
    }
}

