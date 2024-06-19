import processing.core.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Balloon
{
    int positionX;
    float speed;
    float positionY;
    PImage img;
    float endHitBoxY;
    int endHitBoxX;
    boolean isRed;
    float acceleration = 0.5f;
    private static final Random RAND = new Random();
    public Balloon(int type, int positionX, int positionY)
    {
        this.positionX = positionX;
        this.positionY = positionY;

        //generating red balloon
        if (type == 0)
        {
            this.speed = 5;
            this.isRed=true;
            this.img = Main.imgRed;
            this.endHitBoxY=positionY+50;
            this.endHitBoxX=positionX+30;
            img.resize(30, 70);
        }

        //generating yellow balloon
        if (type == 1)
        {
            this.speed = RAND.nextInt(6,10);
            this.isRed=false;
            this.img = Main.imgYellow;
            img.resize(80, 80);
            this.endHitBoxY=positionY+60;
            this.endHitBoxX=positionX+80;
        }
    }

    //generating the balloons
    public static void getBalloon(int type, boolean generateRandom , ArrayList<Balloon> balloonArray)
    {
        if (!Main.inGame && !generateRandom && type == 0)
        {
            // Red balloon and not generating random balloons
            for (int i = 0; i < 15; i++)
            {
                balloonArray.add(new Balloon(0,(int) (Main.windowWidth / 4 + 0.04 * Main.windowWidth * i) , Main.windowHeight));
                balloonArray.get(i).speed = 2;
                Main.inGame = true;
            }
        }

        else if (generateRandom && !Main.inGame)
        {
            // Generate random balloons for other levels
            int yellowBalloonCount = 0;
            ArrayList<Integer> indices = new ArrayList<>();
            for(int i = 0 ; i < 15 ; i++)
            {
                indices.add(i);
            }
            Collections.shuffle(indices);
            for (int index: indices)
            {
                if(yellowBalloonCount <3)
                {
                balloonArray.add(new Balloon(1, (int) (Main.windowWidth / 4 + 0.03455 * Main.windowWidth * index), RAND.nextInt(Main.windowHeight , Main.windowHeight*2)));
                yellowBalloonCount++;
                }

                else
                {
                    balloonArray.add(new Balloon(0, (int) (Main.windowWidth / 2 + 0.03455* Main.windowWidth * index), RAND.nextInt(Main.windowHeight , Main.windowHeight*2)));
                }
            }
            Main.inGame = true;
        }
    }

    //moving the balloon
    public static void update(ArrayList<Balloon> balloonArray)
    {
        for (Balloon balloon : balloonArray)
        {
            if(balloon.isRed)
            {
                balloon.positionY -= balloon.speed;
                balloon.endHitBoxY = balloon.positionY + 50;
            }

            else
            {
                if (balloon.speed > 16)
                {
                    balloon.acceleration = -1 * RAND.nextFloat(0.01f,0.24f);
                }
                else if (balloon.speed < 3 )
                {
                    balloon.acceleration = RAND.nextFloat(0.01f,0.44f);
                }
                balloon.speed += balloon.acceleration;
                balloon.positionY -= balloon.speed;
                balloon.endHitBoxY= balloon.positionY + 60;
            }
            for (Particle particle : Particle.particles) {
                particle.update();
            }
            //if balloon goes up it resets at bottom
            if (balloon.positionY+ 100 < 0)
            {
                balloon.positionY = Main.windowHeight + balloon.endHitBoxY;
            }
        }
    }
}
