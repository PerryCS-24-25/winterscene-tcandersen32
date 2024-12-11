
import javax.swing.Timer;

/**
 * Draw a pretty picture composed of shape objects on a canvas
 * 
 * @author: (Your name)
 * @version: (Date)
 * 
 */
public class Picture
{
    // Private member (instance) variables
    private Canvas pic;
    private int width;
    private int height;
    private Circle[] snow; // Array containing the snow
    private int snowCount = 100;
    private int dx = 1; // Speed in x direction for the snow
    private int dy = 2; // Speed in y direction for the snow
    private Rect house;
    private Rect ground;
    private Triangle roof;
    private Rect window1;
    private Rect window2;
    private Rect chimney;
    
    public Picture()
    {
        // Get a reference to the canvas for this drawing and set its title.
        pic = Canvas.getCanvas();
        pic.setTitle("Animated Winter Scene");
        pic.setBackgroundColor("black");
        width = pic.getWidth();
        height = pic.getHeight();
        // Turn off automatic redrawing
        pic.pause(true);
        
        snow = new Circle[snowCount];
        for(int i = 0; i < snow.length; i++){
            snow[i] = new Circle((int)(Math.random() * width), (int)(Math.random() * height), 2, "white", true);
        }
        
        ground = new Rect(0, height * 3/4, width, height / 4, "white", true);
        house = new Rect(50, height * 3/4 - 100, 100, 100, "brown", true);
        chimney = new Rect(120,  height * 3/4 - 100, 20, -50, "#333333", true);
        roof = new Triangle(100, height * 3/4 - 150, 150, 50, "#40320c", true);
        window1 = new Rect(60, height * 3/4 - 90, 20, 20, "yellow", true);
        window1 = new Rect(120, height * 3/4 - 90, 20, 20, "yellow", true);

        // Show the initial picture
        pic.redraw();
    }
    
    /**
     * Update the screen to create a new frame for the animation
     */
    public void update() 
    {
        for(Circle snowflake : snow){
            int x = snowflake.getX();
            int y = snowflake.getY();
            
            // Calculate a new position for the ball
            x = x + dx;
            y = y + dy;        
            
            // Bounce off the edges
            if (x < 0) {
                x = pic.getWidth();
            }
            else if (x > pic.getWidth()) {
                x = 0;
            }
            
            if (y > pic.getHeight()) {
                y = 0;
                x = (int)(Math.random() * pic.getWidth());
            }
            
            // Move the ball
            snowflake.setPosition(x, y);
        }
        
        // Update the screen
        pic.redraw();
        
    }
    
    /**
     * This main method prepares and regularly updates a picture.
     */
    public static void main(String[] args)
    {
        Picture pic = new Picture();
        
        // update the screen every 33ms (30 times / second)
        new Timer(33, e->pic.update()).start();        
    }
   
}