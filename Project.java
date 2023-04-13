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

public class Project extends Application{

   public void start(Stage stage){      
      l = new Label("The text field!"); //Make a label set it to top of border pane
      bp.setAlignment(l, Pos.TOP_CENTER);
      bp.setTop(l);
      
      //Make a 4 by 4 of the gamepanes and add to center of borderpane
      for (int i=0;i<4;i++){
         for(int j=0;j<4;j++){
            pane = new GamePane();
            gp.add(pane,i,j);
            if(i==0&&j==2){
               pane.setBallVisibility("false");
            
            }
            else{
               pane.setBallVisibility("true");
            }
            allPanes[i][j]=pane;
         
            //pane.draw();
            //Add each pane into a 2D array to keep track of them
            //allPanes[i][j]=pane;
         }
      }
      
      canMove(); //Call can move before drawing in order to establish buttons
      //Seperate for loop to be able to loop now through the 2D Array and draw
      
      for (int i=0;i<4;i++){
         for(int j=0;j<4;j++){
            allPanes[i][j].draw(); //Draw each pane
         }
      }
      
      gp.setAlignment(Pos.CENTER); //GridPane's default alignment is top left, so set center
      bp.setCenter(gp); //set the center of borderpane to have the gridpane of GamePanes
      
      Scene scene = new Scene(bp, 600, 600); //Scene code
      stage.setScene(scene);
      stage.setTitle("Ball Game");
      stage.show();
     
      //bp.requestFocus(); //Request focus onto borderpane
   }
   GridPane gp = new GridPane(); //4 by 4 grid pane so can set to center
   GamePane pane; //Here for scope reasons
   private GamePane[][] allPanes=new GamePane[4][4];
   
   private Label l;
   
   public class GamePane extends GridPane{ //Create a game pane class
      //Component creation variables
      private Button left = new Button(); //Create the left button
      private Button right = new Button(); //Create the right button
      private Button top = new Button(); //Create the top button
      private Button bottom = new Button(); //Create the bottom button
      private GameCanvas gameCanvas = new GameCanvas();
      private GameCanvas gameCanvEmpty = new GameCanvas(1);
      
      //Functionality variables
      private boolean isVisible=true; //boolean whether the ball is visible
      private boolean leftButtonVis=false; //Four booleans for if each button visible
      private boolean rightButtonVis=false;
      private boolean topButtonVis=false;
      private boolean bottomButtonVis=false;
      
      public GamePane(){//GamePane constructor
         left.setPrefSize(20, 80); //Set the size of the buttons
         left.setOnAction(new ButtonListener()); //Setting the buttons on action
         right.setPrefSize(20,80);
         right.setOnAction(new ButtonListener()); //Parameters of direction and x and y of the buttons in the gridpane
         top.setPrefSize(80,20);
         top.setOnAction(new ButtonListener());
         bottom.setPrefSize(80,20);
         bottom.setOnAction(new ButtonListener());
         
         add(gameCanvas,1,1); //Add the object of the canvas class to center, should draw circle if visible
         add(top,1,0);
         add(bottom,1,2);
         add(left,0,1);
         add(right,2,1);
         //canMove();
         //draw();
      }
      
      public void draw(){ //Draws components if they are visible
         changeText();
         if(getBallVisibility()){ //If GamePane/ball is visible
            gameCanvas.draw();
         }
         else{
            gameCanvas.drawEmpty(); //Draws an empty ball area
         }
         
         if(getTopVis()){ //If button visible = true
            top.setVisible(true);
         }
         else{ //If not hide the button
            top.setVisible(false);
         }
         
         if(getBottomVis()){
            bottom.setVisible(true);
         }
         else{
            bottom.setVisible(false);
         }
         
         if(getLeftVis()){
            left.setVisible(true); 
         }
         else{
            left.setVisible(false);
         }
         
         if(getRightVis()){
            right.setVisible(true);
         }
         else{
            right.setVisible(false);
         }
         
      }
      
      //These 5 methods return the status of visibility for ball, then buttons
      public boolean getBallVisibility(){ //Getter for ball visibilty
         return isVisible;
      }
      public boolean getTopVis(){ //Getter for ball visibilty
         return topButtonVis;
      }
      public boolean getBottomVis(){ //Getter for ball visibilty
         return bottomButtonVis;
      }
      public boolean getLeftVis(){ //Getter for ball visibilty
         return leftButtonVis;
      }
      public boolean getRightVis(){ //Getter for ball visibilty
         return rightButtonVis;
      }
      
      
      //Setters for the visibility of ball and buttons
      public void setBallVisibility(String trueOrFalse){ //Setter for ball visibility
         if(trueOrFalse.equals("false")){
            this.isVisible=false; //Sets ball visibility to false
         }
         if(trueOrFalse.equals("true")){
            isVisible=true;
         }
      } 
      public void setTopVis(String trueOrFalse){ //Setter for top visibility
         if(trueOrFalse.equals("false")){
            topButtonVis=false; //Sets top visibility to false
         }
         if(trueOrFalse.equals("true")){
            topButtonVis=true;
         }
      } 
      public void setBottomVis(String trueOrFalse){ //Setter for bottom visibility
         if(trueOrFalse.equals("false")){
            bottomButtonVis=false; //Sets bottom button visibility to false
         }
         if(trueOrFalse.equals("true")){
            bottomButtonVis=true;
         }
      }
      public void setLeftVis(String trueOrFalse){ //Setter for left visibility
         if(trueOrFalse.equals("false")){
            leftButtonVis=false; //Sets left button visibility to false
         }
         if(trueOrFalse.equals("true")){
            leftButtonVis=true;
         }
      } 
      public void setRightVis(String trueOrFalse){ //Setter for right visibility
         if(trueOrFalse.equals("false")){
            rightButtonVis=false; //Sets right button visibility to false
         }
         if(trueOrFalse.equals("true")){
            rightButtonVis=true;
         }
      }
      
   } //End of gamepane class
   private int buttons = 0;
   
   public void setButtons(){
      buttons++;
   }
   
   public int getButtons(){
      return buttons;
   }
   
   //Method to determine whether or not can move in application class
   public void canMove(){
   buttons=0;
      for(int i=0; i<4; i++){
         for (int j=0; j<4; j++){ //Double for loop to loop through the gridpane
         //Check for bottom button visibility / eligibility
         //Checks three conditions, existence of current ball, existence of near ball, no existence of ball two away
            if (j>=2 && allPanes[i][j].getBallVisibility() && allPanes[i][j-1].getBallVisibility() && !allPanes[i][j-2].getBallVisibility()) { //If row is last two
               allPanes[i][j].setBottomVis("true");
               setButtons();
            } 
            else{
               allPanes[i][j].setBottomVis("false");
            }
         
         //Check for top button visibility
            if (j<=1 && allPanes[i][j].getBallVisibility() && allPanes[i][j+1].getBallVisibility() && !allPanes[i][j+2].getBallVisibility()) {
               allPanes[i][j].setTopVis("true");
               setButtons();
            } 
            else{
               allPanes[i][j].setTopVis("false");
            }
         
         //Check for left button visibility
            if (i<=1 && allPanes[i][j].getBallVisibility() && allPanes[i+1][j].getBallVisibility() && !allPanes[i+2][j].getBallVisibility()) {
               allPanes[i][j].setLeftVis("true");
               setButtons();
            } 
            else{
               allPanes[i][j].setLeftVis("false");
            }
         
         //Check for right button visibility 
            if (i>=2 && allPanes[i][j].getBallVisibility() && allPanes[i-1][j].getBallVisibility() && !allPanes[i-2][j].getBallVisibility()) {
               allPanes[i][j].setRightVis("true");
               setButtons();
            } 
            else{
               allPanes[i][j].setRightVis("false");
            }
         
         }
      } //End of the double for loop
   for (int i=0;i<4;i++){
            for(int j=0;j<4;j++){
               allPanes[i][j].draw(); //Draw each pane
            }
         }
   } //End of can move method
   
   public class ButtonListener implements EventHandler<ActionEvent> {
      private String direction=""; //Direction, x, y declaration
      private int x;
      private int y;
      private int posX;
      private int posY;
      
   /*public ButtonListener() { //Constructor in order to pass in the parameters
                this.x = x;
        this.y = y;
    }*/
    
      public void handle(ActionEvent ae){
      
         //Find the source button and indicate this as direction
         for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
               if (ae.getSource() == allPanes[i][j].top){ 
                  System.out.println("top");
               //If top button pressed, direction is down
                  direction = "down";
                  posX=i;
                  posY=j;
                  x=0;
                  y=2;
               }
               if (ae.getSource() == allPanes[i][j].bottom){ //Bottom pressed go up
                  direction = "up";
                  posX=i;
                  posY=j;
                  x=0;
                  y=-2;
               }
               if (ae.getSource() == allPanes[i][j].right){ //Right pressed go left
                  direction = "left";
                  posX=i;
                  posY=j;
                  x=-2;
                  y=0;
               }
               if (ae.getSource() == allPanes[i][j].left){ //Left pressed go right
                  direction = "right";
                  posX=i;
                  posY=j;
                  x=2;
                  y=0;
               } //if the indexes are valid, then use click so no out of bounds 
               //if(isValid(i,j,x,y)){
               //draw();
            }
         }
         canMove();
         click(direction, posX, posY,x,y); //Calls click method
         for (int i=0;i<4;i++){
            for(int j=0;j<4;j++){
               allPanes[i][j].draw(); //Draw each pane
            }
         }
      }
   }
   
   private int x;
   private int y;
   //private boolean valid = false;
   
   //Create a method to verify the indexes in order to be able to execute click
   //without an out of bounds error
   /*public boolean isValid(int i, int j, int x, int y){ //x and y indicate direction
      this.x=x;
      this.y=y;
      if(j+x<0||j+x>3||i+y<0||i+y>3){ //if valid return true
         this.valid= false;
      }
      
      else{
      //return false 
         this.valid= true;
      }  
      return valid;
   }*/
   
   //Click method in application class
   public void click(String direction, int i, int j, int x, int y){
   //for (int i=0;i<4;i++){ //Goes through all of the panes
      //for(int j=0;j<4;j++){
      if (direction.equals("right")&&i<2){ //Moving a ball right
         allPanes[i][j].setBallVisibility("false");//Set current ball to invisible
         allPanes[i+1][j].setBallVisibility("false");//Set ball row+1 to invisible
         allPanes[i+2][j].setBallVisibility("true");//Set ball row+2 to visible 
      }
      if (direction.equals("left")&&i>1){ //Moving a ball left
         allPanes[i][j].setBallVisibility("false");//Set current ball to invisible
         allPanes[i-1][j].setBallVisibility("false");//Set ball row-1 to invisible
         allPanes[i-2][j].setBallVisibility("true");//Set ball row-2 to visible 
      }
      if (direction.equals("up")&&j>1){ //Moving a ball down
         allPanes[i][j].setBallVisibility("false");//Set current ball to invisible
         allPanes[i][j-1].setBallVisibility("false");//Set ball column-1 to invisible
         allPanes[i][j-2].setBallVisibility("true");//Set ball column-2 to visible 
      }
      if (direction.equals("down")&&j<2){ //Moving a ball up
         allPanes[i][j].setBallVisibility("false");//Set current ball to invisible
         allPanes[i][j+1].setBallVisibility("false");//Set ball column+1 to invisible
         allPanes[i][j+2].setBallVisibility("true");//Set ball column+2 to visible 
      }
      //draw();
      //}
   //}
   canMove(); 
   }
  
   private BorderPane bp = new BorderPane(); //Instantiation of the borderpane(outside for scope)
   
   private int balls =0;
   public int getBalls(){ //Getter and setter for balls
   balls=0;
      for (int i=0;i<4;i++){
            for(int j=0;j<4;j++){
               if(allPanes[i][j].getBallVisibility()){ //Draw each pane
                  balls++;
               }
            }
      }
      return balls;
   }
   
   
   //The text field updater
   public void changeText(){
      int ballCount = getBalls();
      int buttonCount= getButtons();
      l.setText("Balls Left: " +ballCount+ "   Possible Moves: " +buttonCount);
      if(ballCount==1){
         l.setText("You Win");
      }
      else if(buttonCount==0){
         l.setText("You Lose");
      }
   }
   
   public static void main(String[] args){ //Launch args
      launch(args);
   }
}