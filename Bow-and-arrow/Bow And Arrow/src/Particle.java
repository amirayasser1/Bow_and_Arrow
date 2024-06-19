import java.util.ArrayList;
import processing.core.*;

public class Particle {
    static ArrayList<Particle> particles = new ArrayList<>();
    float x, y;
    float speedX, speedY;
    float gravity;
    int lifespan;
    static PApplet sketch;
    int red;
    int green;
    int blue;


    Particle(PApplet sketch,float x, float y, int red,int green,int blue) {
        Particle.sketch = sketch;
        this.x = x;
        this.y = y;
        this.speedX = sketch.random(-2, 2);
        this.speedY = sketch.random(-5, -1);
        this.gravity = 0.05F;
        this.lifespan = 255;
        this.red=red;
        this.green=green;
        this.blue=blue;

    }

    void update() {
        speedY += gravity;
        x += speedX;
        y += speedY;
        lifespan -= 1; // To make the effect Fade over time
    }

    void display() {
        sketch.noStroke();
        sketch.fill(red,green,blue, lifespan);
        sketch.ellipse(x, y, 8, 8);
    }

    boolean isDead() {
        return lifespan <= 0;
    }

}