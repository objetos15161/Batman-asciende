import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Muestra los records en la pantalla.
 * 
 * @author (Julio Cesar Marin) 
 * @version (2015)
 */
public class BotonRecords extends Boton
{
    private ScoreBoard scoreboard;
    /**
     * Contructor de la clase BotonRecords.
     */
    public BotonRecords()
    {
        scoreboard = new ScoreBoard(400,400);
    }
    
    /**
     * Act - do whatever the BotonRecords wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    /**
     * Muestra los records.
     */
    public void click()
    {
        if(Greenfoot.mouseClicked(this))
        {
            clicSound.play();
            getWorld().addObject(scoreboard, 
                                getWorld().getWidth()/2,
                                getWorld().getHeight()/2);
            getWorld().addObject(new Back(scoreboard), 
                                scoreboard.getX() - scoreboard.getImage().getWidth()/2,
                                scoreboard.getY() - scoreboard.getImage().getHeight()/2);
        }
    }
}
