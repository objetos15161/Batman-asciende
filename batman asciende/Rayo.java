import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ataque del jugador que destruye todo a su paso.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015
 */
public class Rayo extends Ataque
{
    /**
     * Act - do whatever the Rayo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }
    
    /**
     * Checa si toca a algún objeto enemigo y lo elimina del mundo.
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
            es.getBatman().setPuntos(25);
        }
        else if(getX()-getImage().getWidth()/2 > getWorld().getWidth())
            getWorld().removeObject(this);
        else if(isTouching(DisparoEnemigo.class))
        {
            DisparoEnemigo de = (DisparoEnemigo)getOneIntersectingObject(DisparoEnemigo.class);
            getWorld().addObject(new Explosion(3), de.getX(), de.getY());
            getWorld().removeObject(de);
            es.getBatman().setPuntos(10);
        }
        else if(isTouching(BombaEnemigo.class))
        {
            BombaEnemigo be = (BombaEnemigo)getOneIntersectingObject(BombaEnemigo.class);
            getWorld().addObject(new Explosion(3), be.getX(), be.getY());
            getWorld().removeObject(be);
            es.getBatman().setPuntos(15);
        }
        else if(isTouching(GeneralBoss.class))
        {
            GeneralBoss g = (GeneralBoss)getOneIntersectingObject(GeneralBoss.class);
            g.restaSalud(1, explosionSound);
            setLocation(getX()+8,getY());
        }
        else
            setLocation(getX()+8,getY());
    }
}
