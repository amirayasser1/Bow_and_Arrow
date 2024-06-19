import processing.core.*;
import java.util.ArrayList;
public class Methods {
     PApplet p;
    Button Start;
    Button How_to_play;
    Button Back;
    Button Level1;
    private static float creditsPositionY = 1080+44;
    private static boolean unlockedLevel2 = false;

    public Methods(PApplet p, Button s, Button h, Button b, Button l1) {
        this.p = p;
        this.Start = s;
        this.How_to_play = h;
        this.Back = b;
        this.Level1 = l1;
    }

    public void Setup_Window(ArrayList<Balloon> balloonArray, ArrayList<Arrow> arrows, Player player) {
        //drawing the windows
        p.background(144);
        switch (Main.Game_State)
        {
            case Main.Main_Menu:
                //go to main menu window
                Draw_Main_Menu();
                break;
            case Main.HowToPlay:
                //go to how to play window
                Draw_How_To_Play();
                break;
            case Main.Level_Selection:
                //go to level selection window
                Draw_Level_Selection();
                break;
            case Main.Level:
                //draw the level
                Draw_Level(balloonArray, arrows, player);
                break;
        }
        if (Main.Game_State != Main.Main_Menu)
            Back_Display();
    }

    public void Draw_Main_Menu()
    {
        Start.Set_Button_X(p.width/2);
        Start.Set_Button_Y(p.height/2);
        p.fill(0);
        p.rectMode(p.CENTER);
        p.rect(Start.Get_Button_X(), Start.Get_Button_Y(), Start.Get_Button_Width(), Start.Get_Button_Height(), 444);
        p.textAlign(p.CENTER, p.CENTER);
        ShowText("Start",255,40,Start.Get_Button_X(),Start.Get_Button_Y());

        How_to_play.Set_Button_X(p.width/2);
        How_to_play.Set_Button_Y(p.height/2+140);
        p.fill(0);
        p.rect(How_to_play.Get_Button_X(), How_to_play.Get_Button_Y(), How_to_play.Get_Button_Width(), How_to_play.Get_Button_Height(), 444);
        p.textAlign(p.CENTER, p.CENTER);
        ShowText("How to play",255,40,How_to_play.Get_Button_X(),How_to_play.Get_Button_Y());
    }

    public void Back_Display()
    {
        Back.Set_Button_X(p.width/16-70);
        Back.Set_Button_Y(p.height/16-35);
        Back.Set_Button_Width(100);
        Back.Set_Button_Height(40);
        p.fill(0);
        p.rect(Back.Get_Button_X(), Back.Get_Button_Y(), Back.Get_Button_Width(), Back.Get_Button_Height(), 444);
        p.textAlign(p.CENTER, p.CENTER);
        ShowText("Back",255,30, Back.Get_Button_X(),Back.Get_Button_Y()-4);
    }

    public void Draw_How_To_Play() {
        p.background(Main.Hitler);
        p.textAlign(p.CENTER, p.CENTER);
        ShowText("Hello! ", 255, 50, p.width / 2f, p.height / 16f);

        p.textAlign(p.LEFT, p.CENTER);
        ShowText("Welcome to the Bow and Arrow challenge – an exciting game that tests your precision speed, and strategic thinking!\n In this game, you'll step into the shoes of an archer with the mission to pop colorful balloons using your bow and arrow.\n Are you up for the challenge?", 255, 30, p.width / 32f - 40, p.height / 8f + 50);

        p.textAlign(p.LEFT, p.CENTER);
        ShowText("Controls :\n It is quite simple...\n-Control the vertical placement of the player by clicking and dragging the left mouse button.\n-Right Click: Reload your arrow.\n-Left Click: Fire the arrow towards the balloons.\n\nAmmo and Reload:\nYou start the game with a quiver of 20 arrows. Right-click to reload your arrow and keep\nthe action going. Manage your arrows wisely – accuracy is key!", 255, 30, p.width / 32f - 40, (p.height / 8f + 50) * 2.8f);

        p.textAlign(p.CENTER, p.CENTER);
        ShowText("Have Fun and Aim True!", 255, 50, p.width / 2f, p.height - 300);

        p.textAlign(p.LEFT, p.CENTER);
        ShowText("Embark on this bow and arrow adventure,test your archery skills, and aim for the highest score possible.\n Can you hit all the balloons and emerge as the ultimate archer?\nGood luck, and may your arrows fly straight and true!", 255, 50, p.width / 32f - 40, p.height - 150);
    }

    public void Draw_Level_Selection() {
        Level1.Set_Button_X(p.width/2);
        Level1.Set_Button_Y(p.height/2);

        ShowText("Total Score : "+Score.totalScore,255, 50, p.width-244, p.height/32f);
        for (int i = 0; i < 2; i++) {
            p.fill(255, 204, 51);
            p.rect(Level1.Get_Button_X(), Level1.Get_Button_Y() + i * 100, Level1.Get_Button_Width(), Level1.Get_Button_Height(), 444);
            p.textAlign(p.CENTER, p.CENTER);
            ShowText("Level "+(i+1),0,50, Level1.Get_Button_X(), Level1.Get_Button_Y()+i*100-8 );
        }
    }

    public void Draw_Level(ArrayList<Balloon> balloonArray, ArrayList<Arrow> arrows, Player player) {
        // In this stage we check first for states if is "0" go to level 1
        // If states is "1" go to level 2
        switch (Main.Selected_Level) {
            case 0:
                level1(balloonArray, player, arrows);
                if (player.arrowCount == 0 && arrows.isEmpty()) {
                    player.noArrows = true;
                }
                Score.checkIfHit(balloonArray, arrows);
                if (balloonArray.isEmpty() && Main.inGame) {
                    p.image(Main.backgroundImage, 0, 0, p.width, p.height);
                    p.textAlign(p.CENTER, p.CENTER);
                    ShowText("Congratulations,\n you have passed level 1!\nyour score is "+(player.arrowCount + 1)* 15, 0,50,p.width/2f,p.height/2f);
                    Main.win.play();
                    Main.backgroundmusic.pause();
                    Main.backgroundmusic.rewind();
                    arrows.clear();
                    Score.addScore();
                    unlockLevel2();
                } else if (player.noArrows) {
                    Main.backgroundmusic.pause();
                    Main.backgroundmusic.rewind();
                    fail(Main.backgroundImage);
                }
                break;

            case 1:
                if (isLevel2Unlocked()) {
                    level2(Main.backgroundImage, balloonArray, arrows, player);
                    if (player.arrowCount == 0 && arrows.isEmpty()) {
                        player.noArrows = true;
                    }
                    Score.checkIfHit(balloonArray, arrows);
                    if (balloonArray.isEmpty() && Main.inGame) {
                        Main.backgroundmusic.pause();
                        Main.backgroundmusic.rewind();
                        p.image(Main.abdo , 0 , 0);
                        Main.creditsOST.play();
                        creditsPositionY -= 5;
                        p.image(Main.credits, 0, creditsPositionY);
                        if (creditsPositionY <=  -p.height+44)
                        {
                            creditsPositionY = 1080+44;
                        }
                        ShowText("your score is " + (player.arrowCount + 1) * 15 + "\nyour total score is " +Score.totalScore,255, 50, p.width-244, p.height-144 );
                        arrows.clear();
                        Score.addScore();
                    } else if (player.noArrows) {
                        Main.backgroundmusic.pause();
                        Main.backgroundmusic.rewind();
                        fail(Main.backgroundImage2);
                    }
                } else {
                    Main.backgroundmusic.pause();
                    p.textAlign(p.CENTER, p.CENTER);
                    ShowText("You must pass \n level 1 first!",0,50, p.width/2f, p.height/2f);
                }
                break;
        }
    }

    public void level1(ArrayList<Balloon> balloonArray, Player player, ArrayList<Arrow> arrows) {
        p.image(Main.backgroundImage, 0, 0, p.width, p.height);
        ShowText("Remaining Balloons : " + balloonArray.size() + " \t \t \t \t Arrows : "+player.getArrowCount(),255,50, p.width-500,p.height/32f);
        // Create balloons if none are in game
        if (!Main.inGame) {
            Balloon.getBalloon(0, false, balloonArray);
        }

        Balloon.update(balloonArray);
        for (Balloon balloon : balloonArray) {
            p.image(balloon.img, balloon.positionX, balloon.positionY);
        }

        // Display the archer
        player.draw();
        player.update();

        // Checking for the arrows counter
        for (int i = 0; i < arrows.size(); i++) {
            Arrow arrow = arrows.get(i);
            if (arrow.isActive()) {
                arrow.display();
                arrow.update();
            } else if (player.noArrows) {
                fail(Main.backgroundImage);
            } else {
                arrows.remove(i); // Remove inactive arrows from the list
                i--; // Adjust the index after removing an element
            }
        }
    }

    public void level2(PImage backgroundImage, ArrayList<Balloon> balloonArray, ArrayList<Arrow> arrows, Player player) {
        // Draw the background
        p.image(Main.backgroundImage2, 0, 0, p.width, p.height);
        ShowText("Remaining Balloons : " + balloonArray.size() + " \t \t \t \t Arrows : "+player.getArrowCount(),255,50, p.width-500,p.height/32f);

        // Display the balloons
        Balloon.getBalloon(1, true, balloonArray);
        Balloon.update(balloonArray);
        for (Balloon balloon : balloonArray) {
            p.image(balloon.img, balloon.positionX, balloon.positionY);
        }

        // Display the archer
        player.draw();
        player.update();

        // Checking for the arrows counter
        for (int i = 0; i < arrows.size(); i++) {
            Arrow arrow = arrows.get(i);
            if (arrow.isActive()) {
                arrow.display();
                arrow.update();
            } else if (player.noArrows) {
                fail(backgroundImage);
            } else {
                arrows.remove(i); // Remove inactive arrows from the list
                i--; // Adjust the index after removing an element
            }
        }
    }

    //fail window
    public void fail(PImage backgroundImage) {
        // Draw a new window
        p.image(backgroundImage, 0, 0, p.width, p.height);
        ShowText("You ran out of arrows\n Press back to try again",0,50,p.width/2f, p.height/2f);
        Main.lose.play();
    }

    //setter and getter for unlockedLevel2
    public static void unlockLevel2() {
        unlockedLevel2 = true;
    }

    public static boolean isLevel2Unlocked() {
        return unlockedLevel2;
    }
    public void ShowText(String text,int color ,int size,float x,float y)
    {
        p.fill(color);
       p.textSize(size);
        p.text(text,x,y);
    }

    public void mousePressed(ArrayList<Balloon> balloonArray, Player player, ArrayList<Arrow> arrows) {
        switch (Main.Game_State) {
            case Main.Main_Menu:
                if (p.mouseX > Start.Get_Button_X() - Start.Get_Button_Width() / 2
                        && p.mouseX < Start.Get_Button_X() + Start.Get_Button_Width() / 2
                        && p.mouseY > Start.Get_Button_Y() - Start.Get_Button_Height() / 2
                        && p.mouseY < Start.Get_Button_Y() + Start.Get_Button_Height() / 2) {
                    //checks if button start is clicked then go to level selection
                    Main.Game_State = Main.Level_Selection;
                } else if (p.mouseX > How_to_play.Get_Button_X() - How_to_play.Get_Button_Width() / 2
                        && p.mouseX < How_to_play.Get_Button_X() + How_to_play.Get_Button_Width() / 2
                        && p.mouseY > How_to_play.Get_Button_Y() - How_to_play.Get_Button_Height() / 2
                        && p.mouseY < How_to_play.Get_Button_Y() + How_to_play.Get_Button_Height() / 2) {
                    //checks if button how to play is clicked then go to how to play
                    Main.Game_State = Main.HowToPlay;
                }
                break;

            case Main.HowToPlay:
                if (p.mouseX > Back.Get_Button_X() - Back.Get_Button_Width() / 2
                        && p.mouseX < Back.Get_Button_X() + Back.Get_Button_Width() / 2
                        && p.mouseY > Back.Get_Button_Y() - Back.Get_Button_Height() / 2
                        && p.mouseY < Back.Get_Button_Y() + Back.Get_Button_Height() / 2) {
                    //checks if the back button is clicked then go to main menu
                    Main.Game_State = Main.Main_Menu;
                }
                break;

            case Main.Level_Selection:
                if (p.mouseX > Back.Get_Button_X() - Back.Get_Button_Width() / 2
                        && p.mouseX < Back.Get_Button_X() + Back.Get_Button_Width() / 2
                        && p.mouseY > Back.Get_Button_Y() - Back.Get_Button_Height() / 2
                        && p.mouseY < Back.Get_Button_Y() + Back.Get_Button_Height() / 2) {
                    //checks if back button is pressed then go back to main menu
                    Main.Game_State = Main.Main_Menu;
                }

                // Check if level buttons are clicked
                for (int i = 0; i < 2; i++)
                    if (p.mouseX > Level1.Get_Button_X() - Level1.Get_Button_Width() / 2
                            && p.mouseX < Level1.Get_Button_X() + Level1.Get_Button_Width() / 2
                            && p.mouseY > Level1.Get_Button_Y() + i * 100 - Level1.Get_Button_Height() / 2
                            && p.mouseY < Level1.Get_Button_Y() + i * 100 + Level1.Get_Button_Height() / 2) {
                        Main.Selected_Level = i;
                        Main.Game_State = Main.Level;
                        Main.backgroundmusic.loop();
                    }
                break;

            case Main.Level:
                if (p.mouseX > Back.Get_Button_X() - Back.Get_Button_Width() / 2
                        && p.mouseX < Back.Get_Button_X() + Back.Get_Button_Width() / 2
                        && p.mouseY > Back.Get_Button_Y() - Back.Get_Button_Height() / 2
                        && p.mouseY < Back.Get_Button_Y() + Back.Get_Button_Height() / 2)
                //checks for back button click
                {
                    Main.Game_State = Main.Level_Selection;
                    Main.inGame = false;
                    balloonArray.clear();
                    arrows.clear();
                    Main.player.arrowCount = 20;
                    Main.player.noArrows = false;
                    Score.scoreAdded = false;
                    Main.win.pause();
                    Main.win.rewind();
                    Main.lose.pause();
                    Main.lose.rewind();
                    Main.backgroundmusic.pause();
                    Main.backgroundmusic.rewind();
                    Main.creditsOST.pause();
                    Main.creditsOST.rewind();
                }
                break;

        }
        player.checkMouseClick(arrows, player); // checks the mouse click for the character
    }
}