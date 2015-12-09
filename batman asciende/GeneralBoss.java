import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GeneralBoss here.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class GeneralBoss extends Personaje
{
    private int salud;
    private int modoMovimiento;
    private int tipoAtaque;
    private boolean finDeMovimiento;
    private int contAtaques;
    private SimpleTimer timer; // timer para aparecer en pantalla y atacar cada cierto tiempo.
    private boolean murio;
    /**
     * Constructor de la clase. Se inicializa las variables.
     */
    public GeneralBoss()
    {
        salud = 200;
        modoMovimiento = 0;
        tipoAtaque = Greenfoot.getRandomNumber(3);
        finDeMovimiento = false;
        contAtaques=0;
        timer = new SimpleTimer();
        timer.mark();
        murio=false;
    }
    
    /**
     * Act - do whatever the GeneralBoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(salud>0)
        {
            mover();
            atacar();
            checaContador();
        }
        else if(salud==0)
        {
            murio = true;
            timer.mark();
            salud = -1;//para que solo entre una vez
            setLocation(getWorld().getWidth()/2, getWorld().getHeight()/2);
        }
        saludEsCero();
    }
    
    /**
     * Mueve al GeneralBoss en el mundo.
     */
    public void mover()
    {
        switch(modoMovimiento)
        {
            case 0://se mueve a la izquierda.
                if(getX()>800 && finDeMovimiento==false)
                {
                    if(timer.millisElapsed() > 3000)
                        setLocation(getX()-2, getY());
                }
                else
                {
                    finDeMovimiento=true;
                    modoMovimiento=2;
                }
            break;
            
            case 1://se mueve a la derecha hasta que se sale de la pantalla.
                if(getX() < getWorld().getWidth()+getImage().getWidth()/2 && finDeMovimiento==false)
                {
                    setLocation(getX()+2, getY());
                    //finDeMovimiento=true;
                }
                else
                {
                    timer.mark();
                    modoMovimiento = 0;
                }
            break;
            
            case 2://se mueve hacia arriba
                if(getY() > 0+getImage().getHeight()/2)
                    setLocation(getX(), getY()-5);
                else
                    modoMovimiento = 3;
            break;
            
            case 3://se mueve hacia abajo
                if(getY() < getWorld().getHeight()-getImage().getHeight()/2)
                    setLocation(getX(), getY()+5);
                else
                    modoMovimiento=2;
            break;
        }
    }
    
    /**
     * Hace diferentes ataques al jugador.
     */
    public void atacar()
    {
        if(finDeMovimiento == true)
        {
            switch(tipoAtaque)
            {
                case 0:
                    if(shotTimer.millisElapsed() > 300 && timer.millisElapsed() > 5000)
                    {
                        getWorld().addObject(new DisparoBoss(Greenfoot.getRandomNumber(3)), getX()-40, getY()-getImage().getHeight()/2);
                        contAtaques++;
                        shotTimer.mark();
                    }
                break;
            
                case 1:
                    if(shotTimer.millisElapsed() > 500 && timer.millisElapsed() > 6000)
                    {
                        getWorld().addObject(new BombaEnemigo(), getX()-40, getY()-getImage().getHeight()/2);
                        contAtaques++;
                        shotTimer.mark();
                    }
                break;
            
                case 2:
                    if(shotTimer.millisElapsed() > 1000  && timer.millisElapsed() > 10000)
                    {
                        generaEnemigo();
                        getWorld().addObject(new DisparoBoss(Greenfoot.getRandomNumber(3)), getX()-40, getY()-getImage().getHeight()/2);
                        contAtaques++;
                        shotTimer.mark();
                    }
                break;
            }
        }
    }
    
    /**
     * Genera un numero aleatorio para crear un enemigo que se pone en el mundo.
     */
    public void generaEnemigo()
    {
        switch(Greenfoot.getRandomNumber(3))
        {
            case 0:
                getWorld().addObject(new Kamikase(), 2000, getY());
            break;
            
            case 1:
                getWorld().addObject(new Capitan(), 2000, getY());
            break;
            
            case 2:
                getWorld().addObject(new Coronel(), 2000, getY());
            break;
        }
    }
    
    /**
     * Controla el comprtamiento del GeneralBoss.
     */
    public void checaContador()
    {
        if(contAtaques > 5)
        {
            contAtaques=0;
            tipoAtaque = Greenfoot.getRandomNumber(3);
            modoMovimiento=1;
            finDeMovimiento = false;
        }
    }
    
    /**
     * Checa si el Genereal esta en la pantalla, de ser así resta 's' la salud.
     * También reproduce un sonido indicando que lo golpeó una ataque del jugador.
     */
    public void restaSalud(int s, GreenfootSound sound)
    {
        if(getX()-getImage().getWidth()/2 < getWorld().getWidth() && salud > 0)
        {
            salud -= s;
            sound.play();
        }
        else if(salud < 0)
            salud = 0;
    }
    
    /**
     * Regresa la salud del GenerealBoss.
     */
    public int getSalud()
    {
        return salud;
    }
    
    /**
     * Si la salud es <= a cero la nave explota y desaparece.
     */
    public void saludEsCero()
    {
        if(murio)
        {
            
                //getWorld().addObject(new Explosion(1), getX(), getY());
                getWorld().addObject(new Aviso("GANASTE!!!", true), getWorld().getWidth()/2, getWorld().getHeight()/2);
                getWorld().removeObject(this);
            }
        }
    }
