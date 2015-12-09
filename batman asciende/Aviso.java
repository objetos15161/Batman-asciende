import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Pone un letrero en la pantalla.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class Aviso extends Actor
{
    private int vel;
    private boolean fin; //si fin==true cuando desaparece el aviso crea un nuevo menu.
    /**
     * Constructor de la clase Aviso.
     */
    public Aviso(String text, boolean f)
    {
        setImage( new GreenfootImage(text,100,java.awt.Color.WHITE,java.awt.Color.BLACK) );
        
        vel = 100;
        
        fin = f;
    }
    
    /**
     * Act - do whatever the Aviso wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Escenario e = (Escenario)getWorld();
        if(vel<0)
            move(vel);
        vel--;
        if(getX()+getImage().getWidth()/2 < 0)
        {
            if(fin == true)
            {
                if(e.getActualSound().isPlaying())
                    e.getActualSound().stop();
                    Greenfoot.setWorld(new Menu());
            }
            else
                getWorld().removeObject(this);
        }
    }    
}
