import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Player {
    private final PApplet sketch;
    private PImage image;
    private float positionY;
    private final float positionX;
    protected int arrowCount;
    protected boolean noArrows = false;
    private boolean loaded = false;

    Player(PApplet sketch, PImage image, float positionX, float positionY, int arrowCount) {
        this.sketch = sketch;
        this.image = image;
        this.positionY = positionY;
        this.positionX = positionX;
        this.arrowCount = arrowCount;
    }

    // Draw the character and the slider
    float speed=0;
    void draw() {
        sketch.image(image, positionX, positionY, sketch.width / 32f + 40, sketch.height / 10f + 32);
        if(Arrow.isCharging&&speed<20){
            speed+=0.1F;
        }
        sketch.stroke(0, 0, 255); // Set stroke color to blue (RGB)
        sketch.strokeWeight(4);
        sketch.line(20,sketch.mouseY+160,20+speed*2,sketch.mouseY+160);
    }

    // Update the character
    void update() {
        positionY = PApplet.constrain(sketch.mouseY, 0, sketch.height - image.height);
    }

    // Check for mouse clicks and adjust arrow speed based on the slider value
    public void checkMouseClick(ArrayList<Arrow> arrows, Player player) {

        if (sketch.mousePressed && sketch.mouseButton == sketch.LEFT && loaded && Main.inGame) {
            if (arrowCount > 0) {
                float arrowSpeed = speed; // Get the slider value as arrow speed
                Arrow arrow = new Arrow(sketch, positionX + 100, positionY + 40, arrowSpeed);
                arrows.add(arrow);
                arrow.activate(); // Activate the arrow
                arrowCount--;
                player.image = Main.playerNotLoadedImage;
                loaded = false;
                speed=0;
                Arrow.isCharging=false;

            }
        } else if (sketch.mousePressed && sketch.mouseButton == sketch.RIGHT && !loaded && Main.inGame && arrowCount != 0) {
            player.image = Main.playerLoadedImage;
            loaded = true;
            Arrow.isCharging=true;

                }
        }



    public int getArrowCount() {
        return this.arrowCount;
    }
}
