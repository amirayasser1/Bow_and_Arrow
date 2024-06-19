import processing.core.PApplet;
import processing.core.PImage;

public class Arrow
{
     PApplet sketch;
     PImage arrowImage;

     public boolean isCharging() {
        return isCharging;
    }

    public void setCharging() {
        isCharging = true;
    }

    static boolean isCharging=false;
   private float x;
    float y;
     float speed;
    private boolean isActive;
     float hitBoxY;
    float hitBoxX;

    public Arrow(PApplet sketch, float x, float y, float speed)
    {
        this.sketch = sketch;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.isActive = false;
        // Load arrow image
        arrowImage = sketch.loadImage("Pictures/Arrow.png");
        arrowImage.resize(70, 50); // Resize arrow image
        hitBoxX=x+70;
        hitBoxY=y+25;
    }

    //showing the arrow
    public void display()
    {
        if (isActive)
        {
            sketch.image(arrowImage, x, y);
            hitBoxX=x+70;
            hitBoxY=y+25;

        }
    }

    //moving the arrow
    public void update()
    {
        if (isActive)
        {
            x += speed;
            if (x > sketch.width)
            {
                isActive = false;
            }
        }
    }


    public void activate()
    {
        isActive = true;
    }

    //getter
    public boolean isActive()
    {
        return isActive;
    }
}


