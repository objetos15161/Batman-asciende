import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Batman here.
 * 
 * @author (Julio Cesar Marin) 
 * @version (Diciembre 2015)
 */
public class Batman extends Personaje
{
    private GreenfootImage[] arrImages; //Arreglo de imagenes para uso del jugador (Batman)
    private int vidas; //Numero de vidas del jugador.
    private int salud; //Cantidad de salud del jugador.
    private int numDisparos; //Cantidad de disparos que puede realizar el jugador.
   
    private int puntos;
    
    private GreenfootSound rayoSound;
   
    private GreenfootSound disparoSound;
    
    protected SimpleTimer rayoTimer;
    /**
     * Constructor de la clase IronMan. Se cargan las imágenes de Iron Man, se inicializan las variables
     * de vida, salud, puntos y escudo. Se cargan los sonidos de los ataques.
     */
    public Batman()
    {
        arrImages = new GreenfootImage [3];
        arrImages[0] = new GreenfootImage("batman volando 1.png");
        arrImages[1] = new GreenfootImage("batman volando 2.png");
        
        vidas = 10;
       
        this.setImage(arrImages[0]);
        numDisparos = 70;
        
        puntos=0;
        
        rayoSound = new GreenfootSound("laser.mp3");
       
        disparoSound = new GreenfootSound("disparo.mp3");
       
        
        
        rayoTimer = new SimpleTimer();
        rayoTimer.mark();
    }
    
    /**
     * Act - do whatever the IronMan wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        this.mover();
        this.atacar();
        this.tocoEnemigo();
       
    }    
    
    /**
     * Metodo para los movimientos del jugador.
     */
    public void mover()
    {
        if(Greenfoot.isKeyDown("right"))
        {
            this.setImage(arrImages[0]);
            if(this.getX()+this.getImage().getWidth()/2 < getWorld().getWidth())
                this.setLocation(this.getX() + 5,this.getY());
        }
        else if(Greenfoot.isKeyDown("left"))
        {
            this.setImage(arrImages[0]);
            if(this.getX()-this.getImage().getWidth()/2 > 0)
                this.setLocation(this.getX() - 5, this.getY());
        }
        else if(Greenfoot.isKeyDown("up"))
        {
            this.setImage(arrImages[1]);
            if(this.getY()-this.getImage().getHeight()/2 > 0)
                this.setLocation(this.getX(), this.getY() - 5);
        }
        else if(Greenfoot.isKeyDown("down"))
        {
            this.setImage(arrImages[1]);
            if(this.getY()+this.getImage().getHeight()/2 < getWorld().getHeight())
                this.setLocation(this.getX(), this.getY() + 5);
        }
    }
    
    /**
     * Cuando el jugador presiona la tecla espacio, "batman" lanzara un disparo. 
     * Cada disparo tiene un delay de 250 milisegundos.
     * Cuando se presiona la letra z lanza tres rayos.
     */
    public void atacar()
    {
        if(shotTimer.millisElapsed() > 250 && numDisparos > 0)
        {
            if(Greenfoot.isKeyDown("space"))
            { 
                disparoSound.setVolume(80);
                disparoSound.play();
                getWorld().addObject(new DisparoJugador(), this.getX()+getImage().getWidth()/2, this.getY());
                numDisparos--;
                shotTimer.mark();
            }
        }
       
    }
    
    /**
     * Metodo que resta 's' salud del jugador.
     * Y si la salud llega a 0 se le resta una vida y se le restaura la salud en 50.
     */
    public void restaSalud(int s)
    {
       
        salud-=s;
        if(salud <= 0)
        {
            if(vidas>0)
            vidas--;
            salud = 50;
            if(vidas==0)
                salud=0;
        }
    }
    
    
    /**
     * Verifica si toco a un enegmigo y lo destruye.
     */
    public void tocoEnemigo()
    {
        Enemigo e = (Enemigo)getOneIntersectingObject(Enemigo.class);
        GeneralBoss g = (GeneralBoss)getOneIntersectingObject(GeneralBoss.class);
        if(e!=null)
        {
            Escenario es = (Escenario)getWorld();
            es.restaNumEnemigos();
    
            getWorld().addObject(new Explosion(1), e.getX(), e.getY());
            getWorld().removeObject(e);
            this.restaSalud(1);
        }
        else if(g!=null)
        {
            if(g.getSalud() > 0)
            {
                this.restaSalud(5);
                getWorld().addObject(new Explosion(3), getX()+getImage().getWidth()/2, getY());
            }
        }
    }
    
    /**
     * Regresa las vidas del jugador.
     */
    public int getVidas()
    {
        return vidas;
    }
    
    /**
     * Regresa la cantidad de disparos del jugador.
     */
    public int getNumDisparos()
    {
        return numDisparos;
    }
    
    /**
     * Regresa los puntos.
     */
    public int getPuntos()
    {
        return puntos;
    }
    
    public int getSalud()
    {
        return salud;
    }
    
    /**
     * Asigna una cantidad de disparos.
     */
    public void setDisparos(int d)
    {
        numDisparos += d;
    }
    
    /**
     * Asigna 90 disparos.
     */
    public void setDisparos()
    {
        numDisparos = 100;
    }
    
    /**
     * Asigna un valor a los puntos.
     */
    public void setPuntos(int p)
    {
        puntos += p;
    }
    
    /**
     * Incrementa en una unidad las vidas.
     */
    public void incrementaVidas()
    {
        vidas++;
    }
    
    /**
     * Genera bonus aleatoriamente a lo largo del juego.
     */
    public void generaBonus()
    {
        if(numDisparos < 15)
        {
            if(Greenfoot.getRandomNumber(5000) < 20)
            {
                getWorld().addObject( new BonusDisparo(),
                Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(500));
            }
        }
        if(Greenfoot.getRandomNumber(18000) < 5 && vidas<5)
        {
            getWorld().addObject( new BonusVida(), 
            Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(500));
        }
    }
}
