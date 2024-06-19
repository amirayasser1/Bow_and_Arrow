import processing.core.*;
import ddf.minim.*;
import java.util.ArrayList;
public class Main extends PApplet
{
    public static void main(String[] args) {PApplet.main("Main");}
    //declare Buttons
    Button Start = new Button(this);
    Button How_to_play = new Button(this);
    Button Back = new Button(this);
    Button Level1 = new Button(this);
    Methods methods = new Methods( this,Start, How_to_play, Back, Level1);

    //declare the character
    static Player player;
    //declare list for arrows and balloons
    private final ArrayList<Arrow> arrows = new ArrayList<>();
    private final ArrayList<Balloon> balloonArray = new ArrayList<>();

    //declare images
    static PImage imgYellow;
    static PImage backgroundImage;
    static PImage backgroundImage2;
    static PImage imgRed;
    static PImage playerLoadedImage;
    static PImage playerNotLoadedImage;
    static PImage Hitler;
    static PImage abdo;
    static PImage credits;


    //Sounds
    Minim Okay;
    static AudioPlayer okay;
    Minim Win;
    static AudioPlayer win;
    Minim Lose;
    static AudioPlayer lose;
    Minim BackGroundMusic;
    static AudioPlayer backgroundmusic;
    Minim CreditsOST;
    static AudioPlayer creditsOST;

    //declare states of the game
    final static int Main_Menu = 0;
    final static int HowToPlay = 1;
    final static int Level_Selection = 2;
    final static int Level = 3;
    static int Game_State = Main_Menu;
    static int Selected_Level = 0;

    public static int windowWidth;
    public static int windowHeight;
    //Boolean that checks if the player is in a game
    static boolean inGame = false;
    public void settings()
    {
        size(1920,1080,P2D);
        fullScreen();
        windowHeight= height;
        windowWidth= width;

    }
    public void setup()
    {
        // Balloons image loading
        imgYellow = loadImage("Pictures/Yellow.png");
        imgRed = loadImage("Pictures/red balloon.png");

        //background image loading
        backgroundImage = loadImage("Pictures/2.jpeg");
        backgroundImage2=loadImage("Pictures/1.jpeg");
        Hitler=loadImage("Pictures/Hitler.jpeg");
        Hitler.resize(width,height);

        //player image loading
        playerLoadedImage = loadImage("Pictures/playerLoaded.png");
        player = new Player(this, playerLoadedImage, 0, 200, 20);
        playerLoadedImage.resize(144, 144);
        playerNotLoadedImage = loadImage("Pictures/playerNotLoaded.png");
        player = new Player(this, playerNotLoadedImage, 0, 200, 20);
        playerNotLoadedImage.resize(144, 144);

        //credits images
        credits = loadImage("Pictures/credits.png");
        credits.resize(width,height);   
        abdo = loadImage("Pictures/abdo.jpeg");
        abdo.resize(width,height);

        //sounds loading
        Okay = new Minim(this);
        okay = Okay.loadFile("Audios/pop1.mp3");
        Win = new Minim(this);
        win = Win.loadFile("Audios/win.mp3");
        Lose = new Minim(this);
        lose = Lose.loadFile("Audios/lose.mp3");
        BackGroundMusic = new Minim(this);
        backgroundmusic = BackGroundMusic.loadFile("Audios/backgroundMusic.mp3");
        CreditsOST = new Minim(this);
        creditsOST = CreditsOST.loadFile("Audios/44.mp3");
        creditsOST.setGain(0.5f);

        Particle.sketch=this;
    }
    public void draw() {methods.Setup_Window(balloonArray, arrows , player);}

    //checks the mouse interactions
    public void mousePressed() {methods.mousePressed(balloonArray, player , arrows);
    }
}