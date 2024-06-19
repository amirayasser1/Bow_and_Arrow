import java.util.ArrayList;
public class Score
{
    static int totalScore = 0;
    static boolean scoreAdded = false;
    //checking the arrow hits the balloon
    public static void checkIfHit(ArrayList<Balloon> balloonArray, ArrayList<Arrow> arrows) {
        for (int i = 0; i < balloonArray.size(); i++) {
            Balloon balloon = balloonArray.get(i);
            for (Arrow arrow : arrows)
            {
                if (checkCoordinates(balloon.positionX, balloon.endHitBoxX, arrow.hitBoxX) && checkCoordinates(balloon.positionY, balloon.endHitBoxY, arrow.hitBoxY)) {
                    balloonArray.remove(i);
                    Main.okay.rewind();
                    Main.okay.play();
                    for (int j = 0; j < 16; j++) {
                        if (balloon.isRed) {
                            Particle.particles.add(new Particle(Particle.sketch, balloon.positionX, balloon.positionY, 255, 0, 0));
                        } else {
                            Particle.particles.add(new Particle(Particle.sketch, balloon.positionX, balloon.positionY, 255, 255, 0));
                        }
                    }

                    break; // Break out of the arrow loop after hitting the balloon
                }
            }
        }

        // Update and display particles outside the hit detection loop
        for (int l = Particle.particles.size() - 1; l >= 0; l--) {
            Particle p = Particle.particles.get(l);
            p.display();
            p.update();
            if (p.isDead()) {
                Particle.particles.remove(l);
            }
        }
    }


//condition
    public static boolean checkCoordinates(float start, float end, float arrow)
    {
        return start < arrow && end > arrow;
    }

    //adding the score
    public static void addScore()
    {
        if (!scoreAdded)
        {
            totalScore += (Main.player.arrowCount + 1) * 15;
            scoreAdded = true;
        }
    }


}
