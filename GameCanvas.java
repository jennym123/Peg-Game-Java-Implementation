import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.event.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.scene.canvas.*;
import javafx.scene.input.*;

class GameCanvas extends Canvas //This class creates the canvas in the middle and circle for the gamepane
{
   public GameCanvas()
   {
      setWidth(80); //set the canvas width and height to 80
      setHeight(80);
   }
   
   public GameCanvas(int emptyCirc){
      setWidth(80);
      setHeight(80);
   }
   
   GraphicsContext gc = getGraphicsContext2D();
   
   public void draw() //draw method takes in x and y values
   {
      //Draw a circle 
      gc.setFill(Color.BLACK);//Set fill to black
      gc.fillOval(0,0,80,80);
   }
   
   public void drawEmpty(){
      gc.clearRect(0,0,1000,1000);
   }
   
   
}


