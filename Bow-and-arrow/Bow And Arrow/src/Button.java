import processing.core.PApplet;
public class Button
{
    PApplet P;
    public Button(PApplet P)
    {
        this.P=P;
    }
    private  int BUTTON_WIDTH = 200;
    private  int BUTTON_HEIGHT = 80;
    private int Button_X=960;
    private int Button_Y=500;

    //setters & getters
    public void Set_Button_Width(int width)
    {
        this.BUTTON_WIDTH=width;
    }
    public int Get_Button_Width()
    {
        return this.BUTTON_WIDTH;
    }
    public void Set_Button_Height(int height)
    {
        this.BUTTON_HEIGHT=height;
    }
    public int Get_Button_Height()
    {
        return this.BUTTON_HEIGHT;
    }
    public void Set_Button_X(int x)
    {
        this.Button_X=x;
    }
    public int Get_Button_X ()
    {
        return this.Button_X;
    }
    public void Set_Button_Y(int y)
    {
        this.Button_Y=y;
    }
    public int Get_Button_Y()
    {
        return this.Button_Y;
    }

}