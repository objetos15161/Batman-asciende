import greenfoot.*;

/**
 * Write a description of class Escenario here.
 * 
 * @author (Julio Cesar Marin) 
 * @version (a version number or a date)
 */
public class Escenario extends World
{
    private Batman batman;
    private GreenfootImage imagenFondo; //Imagen de fondo del escenario.
    private GreenfootImage imagenFondo2; 
    private int x; //Coordenada en x para el escenario.
    private int x2; // Coordenada 2 para el eje x.
    
    private GreenfootImage imagenFondoNivel2;
    private GreenfootImage imagenFondoNivel3;
    
    private Counter cVidas;
    private Counter cSalud;
    private Counter cDisparos;
    
    private Counter cEnemigos;
    private Counter cPuntos;
    private Counter cSaludBoss;
    
    private GreenfootSound soundFondo;
    private GreenfootSound soundFondo2;
    private GreenfootSound soundFondo3;
    private GreenfootSound actualSound;
    
    private int numEnemigos;
    private int nivel;
    private int contCambioNivel; //Contador para hacer el cambio de nivel.
    
    private boolean perdioJugador;
    
    private GeneralBoss gb;
    /**
     * Constructor for objects of class Escenario.
     * 
     */
    public Escenario()
    {    
        // Create a new world with 1000x500 cells with a cell size of 1x1 pixels.
        super(1000,500, 1,false); 
        imagenFondo = imagenFondo2 = new GreenfootImage("fondo1 1500x600.jpg");
        x = 0;
        imagenFondoNivel2 = new GreenfootImage("fondo.jpg");
        imagenFondoNivel3 = new GreenfootImage("fondo2.png");
        
        prepare();
        
        soundFondo = new GreenfootSound("hans zimmer - The Way of the Sword.mp3");
        soundFondo2 = new GreenfootSound("Hans Zimmer - End (Batman the Dark knight).mp3");
        soundFondo3 = new GreenfootSound("Hans Zimmer - The Dark Knight Rises (Main Theme).mp3");
        
        numEnemigos = 54; //Hans Zimmer - End (Batman the Dark knight).mp3
        nivel=1;
        contCambioNivel = 0;//
        
        perdioJugador = false;
        
        gb = new GeneralBoss();
    }

    /**
     * Act - do whatever the IronMan wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(batman.getVidas() > 0)
        {
            if(nivel == 1 )
            {   
                soundFondo.playLoop();
                actualSound = soundFondo;
                if(contCambioNivel == 0)
                {
                    prepareNivel1();
                    contCambioNivel++;
                }
            
                if(numEnemigos ==27 && contCambioNivel == 1)
                {
                    prepareNivel1();
                    addObject(new BonusDisparo(), 300,300);
                    addObject(new BonusDisparo(), 350,300);
                    addObject(new BonusDisparo(), 400,350);
                    addObject(new BonusVida(), 300,400);
                    contCambioNivel++;
                }
                else if(numEnemigos == 0 /*&& contCambioNivel == 2*/)
                {
                    nivel++;
                    contCambioNivel=0;
                    if(soundFondo.isPlaying())
                        soundFondo.stop();
                }
                this.scroll();
            }
            else if(nivel == 2 /*&& contCambioNivel == 0*/)
            {
                soundFondo2.playLoop();
                actualSound = soundFondo2;
                if(contCambioNivel == 0)
                {
                    imagenFondo = imagenFondo2 = imagenFondoNivel2;
                    addObject(new Aviso("Nivel 2", false), getWidth()/2, getHeight()/2);
                    cEnemigos.setValue(60);
                    numEnemigos = 60;
                    //ironman.setEnergia(100);
                    batman.setDisparos();
                   // ironman.setSalud(50);
                    prepareNivel2();
                    contCambioNivel++;
                }
                else if(contCambioNivel == 1 && numEnemigos == 30)
                {
                    prepareNivel2();
                    addObject(new BonusDisparo(), 400,300);
                    addObject(new BonusVida(), 300,400);
                    contCambioNivel++;
                }
                    
                if(numEnemigos == 0 && batman.getVidas() >= 1 && contCambioNivel == 2)
                {
                    contCambioNivel=0;
                    nivel++;
                    if(soundFondo2.isPlaying())
                        soundFondo2.stop();
                }
                
                this.scroll();
            }
            else if(nivel == 3)
            {
                soundFondo3.playLoop();
                actualSound = soundFondo3;
                if(contCambioNivel == 0)
                {
                    prepareNivel3();
                    addObject(new Aviso("Nivel 3", false), getWidth()/2, getHeight()/2);
                    getBackground().drawImage(imagenFondoNivel3,0,0);
                    //ironman.setEnergia(100);
                    batman.setDisparos(200);
                    //ironman.setSalud(50);
                    
                    contCambioNivel++;
                }
                if(gb.getSalud()<=0)
                {
                    records(batman.getPuntos());
                    //addObject(new Aviso("GANASTE!!!", true), getWidth()/2, getHeight()/2);
                    if(soundFondo3.isPlaying())
                        soundFondo3.stop();
                }
                cSaludBoss.setPrefix("BOSS: " + gb.getSalud() + "/100");
            }
        }
        else if(batman.getVidas() <= 0 && perdioJugador == false) 
        {
            records(batman.getPuntos());
            addObject(new Aviso("PERDISTE!!!", true), getWidth()/2, getHeight()/2);
            perdioJugador = true;
        }
        this.updateCounters();
    }

    /**
     * Metodo que mueve el escenario de fondo para hacer el scroll.
     */
    public void scroll()
    {
        getBackground().drawImage(imagenFondo,x,0);
        x-=10;
        if(x == -500)
            x2 = 1000;
        if(x < -500)
        {
            getBackground().drawImage(imagenFondo2,x2,0);
            x2-=10;
        }
        if(x == -1500)
            x = 0;
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        batman = new Batman();
        addObject(batman, 93, 249);

        cVidas = new Counter("Vidas = ");
        cSalud = new Counter("Salud = ");
        cDisparos = new Counter("Disparos = ");
        //cEnergia = new Counter();
        //cEscudo = new Counter();
        cEnemigos = new Counter();
        cPuntos = new Counter("Puntos = ");

        cVidas.setValue(batman.getVidas());
        //cSalud.setValue(ironman.getSalud());
        cDisparos.setValue(batman.getNumDisparos());
        //cEnergia.setPrefix("Energia = " + ironman.getEnergia() + "/10");
        //cEscudo.setPrefix("Escudo = " + ironman.getEscudo() + "/2");
        cEnemigos.setPrefix("Enemigos = " + numEnemigos + "/");
        cEnemigos.setValue(54);
        cPuntos.setValue(batman.getPuntos());

        addObject(cVidas,55,15);
        //addObject(cSalud,170,15);
        addObject(cDisparos,305,15);
        //addObject(cEnergia,475,15);
        //addObject(cEscudo, 75,45);
        addObject(cEnemigos, 900, 15);
        addObject(cPuntos, 630, 15);
    }
    
    /**
     * Regresa a Batman
     */
    public Batman getBatman()
    {
        return batman;
    }
    
    
    /**
     * Pone en el mundo los enemigos del nivel 1.
     */
    private void prepareNivel1()
    {
        Kamikase kamikase = new Kamikase(); //1
        addObject(kamikase, 1500, 259);
        Kamikase kamikase2 = new Kamikase();//2
        addObject(kamikase2, 2620, 459);
        Kamikase kamikase3 = new Kamikase();//3
        addObject(kamikase3, 3000,100 );
        Kamikase kamikase4 = new Kamikase();//4
        addObject(kamikase4, 3500, 305);
        Kamikase kamikase5 = new Kamikase();//5
        addObject(kamikase5, 4000, 450);
        Kamikase kamikase6 = new Kamikase();//6
        addObject(kamikase6, 5500, 480);
        Kamikase kamikase7 = new Kamikase();//7
        addObject(kamikase7, 6000, 190);
        Kamikase kamikase8 = new Kamikase();//8
        addObject(kamikase8, 7500, 496);
        Kamikase kamikase9 = new Kamikase();//9
        addObject(kamikase9, 8500, 321);
        Kamikase kamikase10 = new Kamikase();//10
        addObject(kamikase10, 9500, 50);
        Kamikase kamikase11 = new Kamikase();//11
        addObject(kamikase11, 1000, 350);
        
        Capitan capitan = new Capitan();
        addObject(capitan, 4500, 117);
        Capitan capitan2 = new Capitan();
        addObject(capitan2, 5000, 40);
        Capitan capitan3 = new Capitan();
        addObject(capitan3, 6000, 430);
        Capitan capitan4 = new Capitan();
        addObject(capitan4, 6500, 200);
        Capitan capitan5 = new Capitan();
        addObject(capitan5, 7000, 400);
        Capitan capitan6 = new Capitan();
        addObject(capitan6, 7500, 340);
        Capitan capitan7 = new Capitan();
        addObject(capitan7, 8000, 141);
        Capitan capitan8 = new Capitan();
        addObject(capitan8, 8500, 50);
        Capitan capitan9 = new Capitan();
        addObject(capitan9, 10000, 356);
        Capitan capitan10 = new Capitan();
        addObject(capitan10, 9500, 442);
        Capitan capitan11 = new Capitan();
        addObject(capitan11, 10200, 415);
        Capitan capitan12 = new Capitan();
        addObject(capitan12, 10500, 355);
        Capitan capitan13 = new Capitan();
        addObject(capitan13, 10800, 55);
        Capitan capitan14 = new Capitan();
        addObject(capitan14, 10900, 415);
        Capitan capitan15 = new Capitan();
        addObject(capitan15, 11100, 215);
        
        Coronel coronel = new Coronel();
        addObject(coronel,10500,75);
    }
    
    /**
     * Pone en el mundo los enemigos del nivel 2.
     */
    private void prepareNivel2()
    {
        Kamikase kamikase = new Kamikase(); //1
        addObject(kamikase, 2000, Greenfoot.getRandomNumber(400)+50);
        Kamikase kamikase2 = new Kamikase();//2
        addObject(kamikase2, 2600, Greenfoot.getRandomNumber(400)+50);
        Kamikase kamikase3 = new Kamikase();//3
        addObject(kamikase3, 3000, Greenfoot.getRandomNumber(400)+50);
        Kamikase kamikase4 = new Kamikase();//4
        addObject(kamikase4, 3500, Greenfoot.getRandomNumber(400)+50);
        Kamikase kamikase5 = new Kamikase();//5
        addObject(kamikase5, 6000, Greenfoot.getRandomNumber(400)+50);
        Kamikase kamikase6 = new Kamikase();//6
        addObject(kamikase6, 6000, Greenfoot.getRandomNumber(400)+50);
        Kamikase kamikase7 = new Kamikase();//7
        addObject(kamikase7, 8000, Greenfoot.getRandomNumber(400)+50);
        Kamikase kamikase8 = new Kamikase();//8
        addObject(kamikase8, 9000, Greenfoot.getRandomNumber(400)+50);
        Kamikase kamikase9 = new Kamikase();//9
        addObject(kamikase9, 10000, Greenfoot.getRandomNumber(400)+50);
        Kamikase kamikase10 = new Kamikase();//10
        addObject(kamikase10, 12000, Greenfoot.getRandomNumber(400)+50);
        Kamikase kamikase11 = new Kamikase();//11
        addObject(kamikase11, 12050, Greenfoot.getRandomNumber(400)+50);
        
        Capitan capitan = new Capitan();//1
        addObject(capitan, 3500, Greenfoot.getRandomNumber(400)+50);
        Capitan capitan2 = new Capitan();//2
        addObject(capitan2, 4000, Greenfoot.getRandomNumber(400)+50);
        Capitan capitan3 = new Capitan();//3
        addObject(capitan3, 4600, Greenfoot.getRandomNumber(400)+50);
        Capitan capitan4 = new Capitan();//4
        addObject(capitan4, 6000, Greenfoot.getRandomNumber(400)+50);
        Capitan capitan5 = new Capitan();//5
        addObject(capitan5, 6000, Greenfoot.getRandomNumber(400)+50);
        Capitan capitan6 = new Capitan();//6
        addObject(capitan6, 7000, Greenfoot.getRandomNumber(400)+50);
        Capitan capitan7 = new Capitan();//7
        addObject(capitan7, 9000, Greenfoot.getRandomNumber(400)+50);
        Capitan capitan8 = new Capitan();//8
        addObject(capitan8, 10500, Greenfoot.getRandomNumber(400)+50);
        Capitan capitan9 = new Capitan();//9
        addObject(capitan9, 13000, 150);
        Capitan capitan10 = new Capitan();//10
        addObject(capitan10, 13100, 200);
        Capitan capitan11 = new Capitan();//11
        addObject(capitan11, 13200, 250);
        Capitan capitan12 = new Capitan();//12
        addObject(capitan12, 13100, 300);
        Capitan capitan13 = new Capitan();//13
        addObject(capitan13, 13000, 350);
        Capitan capitan14 = new Capitan();//14
        addObject(capitan14, 14000, Greenfoot.getRandomNumber(400)+50);
        Capitan capitan15 = new Capitan();//15
        addObject(capitan15, 14000, Greenfoot.getRandomNumber(400)+50);
        
        Coronel coronel = new Coronel();//1
        addObject(coronel,9000,Greenfoot.getRandomNumber(400)+50);
        Coronel coronel2 = new Coronel();//2
        addObject(coronel2, 11000,Greenfoot.getRandomNumber(400)+50);
        Coronel coronel3 = new Coronel();//3
        addObject(coronel3, 14000,Greenfoot.getRandomNumber(400)+50);
        Coronel coronel4 = new Coronel();//4
        addObject(coronel4, 14500,Greenfoot.getRandomNumber(400)+50);
    }
    
    /**
     * Pone en el mundo los enemigos del nivel 3.
     */
    public void prepareNivel3()
    {
        
        addObject(gb, getWidth()+gb.getImage().getWidth(), getHeight()/2);
        
        removeObject(cEnemigos);
        
        cSaludBoss = new Counter();
        cSaludBoss.setPrefix("BOSS: " + gb.getSalud() + "/100");
        addObject(cSaludBoss, 925, 485);
    }
    
    /**
     * Regresa a batman
     */
    public Batman getbatman()
    {
        return batman;
    }
    
    /**
     * Mantiene actualizados los contadores en el mundo.
     */
    public void updateCounters()
    {
        cVidas.setValue(batman.getVidas());
        cSalud.setValue(batman.getSalud());
        cDisparos.setValue(batman.getNumDisparos());
        cEnemigos.setPrefix("Enemigos = " + numEnemigos + "/");
        cPuntos.setValue(batman.getPuntos());
    }
    
    /**
     * Resta en una unidad el número de enemigos.
     */
    public void restaNumEnemigos()
    {
        numEnemigos--;
    }
    
    /**
     * Regresa el nivel.
     */
    public int getNivel()
    {
        return nivel;
    }
    
    /**
     * Regresa el sonido de fondo actual.
     */
    public GreenfootSound getActualSound()
    {
        return actualSound;
    }
    
    /**
     * Guarda los records en linea.
     */
    public void records(int puntos)
    {
        if (UserInfo.isStorageAvailable()) 
        {
            UserInfo myInfo = UserInfo.getMyInfo();
            if (puntos > myInfo.getScore()) 
            {
                myInfo.setScore(puntos);
                myInfo.store();  // write back to server
            }
        }
    }
}


     
    
