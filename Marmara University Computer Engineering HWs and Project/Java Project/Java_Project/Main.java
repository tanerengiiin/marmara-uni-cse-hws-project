package application;
	
import java.awt.Event;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.VLineTo;
import javafx.scene.text.Text;

public class Main extends Application {
	// This is the file for the Github Repository.
	private Text moveCounter; //Text that holds the number of moves
	private Text currentLevel; //Text showing the level played
	private Text nextLevel; //Text showing success or failure to pass to the next level
	private int moveNum=0; //to keep track of how much cell movement there is in a section
	private Button btn; //button to continue
	private ImageView bgGridPane[][]=new ImageView[4][4]; //a list that keeps cells in the background
	private int coordinates[]=new int[4]; //list holding coordinates of two cells when replacing one cell with another
	private ArrayList<Cell> cellDatas=new ArrayList<>(); //the list that holds these objects while the cells are being generated
	private GridPane pane; //the pane is defined here for use in other methods
	private Pane startPane; // this is the pane of the start screen
	private Pane finishPane; 
	private ArrayList<ImageView> images=new ArrayList<>(); //the list that holds these images while producing the images
	private ArrayList<File> files=new ArrayList<>(); //list holding files
	private Scanner input; //Scanner for reading a file
	private int nextCellNum; //keeps the number of the next cell while checking if the game is over
	private int currentCellNum; //keeps the current cell number while checking if the game is over
	private int levelNum=0; //files are called according to this level number if a level is finished it is incremented
	private String direction; //to keep track of which direction the movement is
	private ImageView  ball; //if a level is completed successfully the ball will move animatedly
	
	@Override
	public void start(Stage primaryStage) {
		try {
			pane = new GridPane(); //pane is defined as gridpane
			finishPane=new Pane();
			startPane=new Pane(); // start screen
			//level files are added to the files list
			files.add(new File("level1.txt"));
			files.add(new File("level2.txt"));
			files.add(new File("level3.txt"));
			files.add(new File("level4.txt"));
			files.add(new File("level5.txt"));
			files.add(new File("level6.txt"));
			files.add(new File("level7.txt"));
			input=new Scanner(files.get(levelNum)); //Receives input by level number
			pane.setAlignment(Pos.CENTER); //It is ensured that the pane is aligned to the center
			//moveCounter is defined as Text, moveNum is sent to this Text, text color is set to black, font size is set to 20 with setStyle and font is set to Poppins
			moveCounter = new Text();
	        moveCounter.setText("Moves: "+moveNum);
	        moveCounter.setFill(Color.BLACK);
	        moveCounter.setStyle("-fx-font: 20 poppins; ");
	        //Same things apply as moveCounter but currentLevel is set to 1
			currentLevel = new Text();
			currentLevel.setText("Level: "+(1));
			currentLevel.setFill(Color.BLACK);
			currentLevel.setStyle("-fx-font: 20 poppins; ");
			//Same things apply as currentLevel
			nextLevel = new Text();
			nextLevel.setText("");
			nextLevel.setFill(Color.BLACK);
			nextLevel.setStyle("-fx-font: 20 poppins; ");
			btn = new Button("Continue"); //The button is defined and Continue is printed in it.
			btn.setStyle("-fx-background-color: #3C3A3E; " //button's background color is set
					+ "-fx-text-fill: #FFFFFF; " //the text inside the button is made white
					+ "-fx-padding: 6 8 6 8; " //button expands from inside
					+ "-fx-font-size: 13pt; " 
					+ "-fx-cursor: hand;"); //mouse cursor becomes hand when over button
			 final FadeTransition fadeIn = new FadeTransition(Duration.millis(100)); //fade animation on button hover
		     fadeIn.setNode(btn); //assigning button to this animation
		     fadeIn.setToValue(1); //opacity becomes 1 when hovered
		     btn.setOnMouseEntered(e -> fadeIn.playFromStart()); //Runs when mouse hovers over the button
		     Button playAgainBtn=new Button("Play Again"); //what is applied to the top button is applied
		     playAgainBtn.setStyle("-fx-background-color: #3C3A3E; "
						+ "-fx-text-fill: #FFFFFF; "
						+ "-fx-padding: 6 8 6 8; "
						+ "-fx-font-size: 20pt; "
						+ "-fx-cursor: hand;");
				 final FadeTransition fadeInPlayAgain = new FadeTransition(Duration.millis(100));
				 fadeInPlayAgain.setNode(playAgainBtn);
				 fadeInPlayAgain.setToValue(1);
				 playAgainBtn.setOnMouseEntered(e -> fadeInPlayAgain.playFromStart());
			     final FadeTransition fadeOutPlayAgain = new FadeTransition(Duration.millis(100));
			     fadeOutPlayAgain.setNode(playAgainBtn);
			     fadeOutPlayAgain.setToValue(0.7);
			     playAgainBtn.setOnMouseExited(e -> fadeOutPlayAgain.playFromStart());
			     playAgainBtn.setOpacity(0.7);
			     playAgainBtn.setAlignment(Pos.CENTER);
			     Label lbl2=new Label("Congratulations");
			     lbl2.setStyle( "-fx-font-size:60pt;"); //the font of the text on the start screen is set to 60,
			     VBox vbox2 = new VBox(20, lbl2,playAgainBtn); //everything is placed vertically on the start screen and the spacing between them is set to 20
			     vbox2.setPadding(new Insets(30)); 
			     vbox2.setAlignment(Pos.CENTER); //center vertically placed elements
				finishPane.getChildren().add(vbox2); //vertically created box is placed on the start pane
				finishPane.setBackground(new Background(new BackgroundFill(Color.web("#F58531",1.0), CornerRadii.EMPTY, Insets.EMPTY))); //set the background color of the start pane
				
		     
		     final FadeTransition fadeOut = new FadeTransition(Duration.millis(100)); //the fade that occurs when leaving the button
		     fadeOut.setNode(btn);
		     fadeOut.setToValue(0.6);
		     btn.setOnMouseExited(e -> fadeOut.playFromStart());
		     btn.setOpacity(0.6);
		     pane.setBackground(new Background(new BackgroundFill(Color.web("#FFFFFF",1.0), CornerRadii.EMPTY, Insets.EMPTY)));
			
	        GridPane.setHalignment(btn, HPos.CENTER); //center nodes horizontally
			GridPane.setHalignment(moveCounter, HPos.CENTER);
			GridPane.setHalignment(currentLevel, HPos.CENTER);
			GridPane.setHalignment(nextLevel, HPos.CENTER);
			//get lines from a file
			while(input.hasNextLine()) { //checks if there is a line in line
				String dataText=input.nextLine();
				String fileDatas[]=dataText.split(","); //creates an array by dividing a line by commas
				int id=Integer.parseInt(fileDatas[0]); //converts the first word on the line to a number
				cellDatas.add(new Cell(id,fileDatas[1],fileDatas[2]));	//It sends the created cell to the cellDatas array I mentioned above.
			}
			fillCellList(); //This method will be explained
			
			Button playButton=new Button("Play"); //what is applied to the top button is applied
			playButton.setStyle("-fx-background-color: #3C3A3E; "
					+ "-fx-text-fill: #FFFFFF; "
					+ "-fx-padding: 6 8 6 8; "
					+ "-fx-font-size: 20pt; "
					+ "-fx-cursor: hand;");
			 final FadeTransition fadeInStart = new FadeTransition(Duration.millis(100));
			 fadeInStart.setNode(playButton);
			 fadeInStart.setToValue(1);
			 playButton.setOnMouseEntered(e -> fadeInStart.playFromStart());
			 
		     final FadeTransition fadeOutStart = new FadeTransition(Duration.millis(100));
		     fadeOutStart.setNode(playButton);
		     fadeOutStart.setToValue(0.7);
		     playButton.setOnMouseExited(e -> fadeOutStart.playFromStart());
		     playButton.setOpacity(0.7);
		     playButton.setAlignment(Pos.CENTER);
		     Label lbl=new Label("Pipe Puzzle Game"); //to write a text on the start screen
		     ImageView startImg=new ImageView(new Image(new File("PipeStatic_10.png").toURI().toString())); //image on the start screen
		     PathTransition pt2 = new PathTransition();
		     pt2.setDuration(Duration.millis(3000)); //animation duration is 3 seconds
		     Circle circle = new Circle(125, 100, 50);
		     pt2.setPath(circle); //Adds the path through which the animation will be provided
		     pt2.setNode(startImg);
		     pt2.setOrientation(
		     PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		     pt2.setCycleCount(Timeline.INDEFINITE); // animation runs forever
		     pt2.setAutoReverse(true); //animation changes direction when it comes to the end
		     pt2.play();
		     startImg.setFitHeight(150); //lengths of the image on the start screen
		     startImg.setFitWidth(150);
		     lbl.setStyle( "-fx-font-size:60pt;"); //the font of the text on the start screen is set to 60,
		     VBox vbox = new VBox(20, lbl,playButton,startImg); //everything is placed vertically on the start screen and the spacing between them is set to 20
		     vbox.setPadding(new Insets(30)); 
		     vbox.setAlignment(Pos.CENTER); //center vertically placed elements
			startPane.getChildren().add(vbox); //vertically created box is placed on the start pane
			startPane.setBackground(new Background(new BackgroundFill(Color.web("#F58531",1.0), CornerRadii.EMPTY, Insets.EMPTY))); //set the background color of the start pane
			Scene startScene = new Scene(startPane,750,750); //a pane is added to the screen and its size is adjusted
			Scene finishScene = new Scene(finishPane,750,750);
			Scene gameScene = new Scene(pane,750,750);
			
			playButton.setOnMouseClicked(e -> { //clicking the play button on the login screen
				levelNum=0;
				resetGameScene(); //level number is reset
				 primaryStage.setScene(gameScene); //Clicking this button replaces startScene with gameScene
					primaryStage.show();
		     });
			btn.setOnMouseClicked(e -> { //events that occur when the button is clicked
		    	 try {
					if(!isLevelFinish()) {
						//If the person cannot pass the level, the text of the nextLevel will be set to Try Again and the background color of the pane will be red
			    		 nextLevel.setText("Try Again");
			    		 pane.setBackground(new Background(new BackgroundFill(Color.web("#FF7171",1.0), CornerRadii.EMPTY, Insets.EMPTY)));
			    	 }else {
			    		 //if the person passes the level, the levelNum is increased by 1 and the text of the nextLevel is set to Succesfull
			    		 levelNum++;
			    		 nextLevel.setText("Succesfull");
			    		 if(levelNum==8) {
							 primaryStage.setScene(finishScene); //if we passed the last level, finalScene is put instead of scene
								primaryStage.show();
			    		 }
			    	 }
		    	 } catch (FileNotFoundException e1) {
					e1.printStackTrace();
		    	 }
		     });
			 playAgainBtn.setOnMouseClicked(e -> {
				 levelNum=0; //level number is reset
				resetGameScene(); 
				 primaryStage.setScene(gameScene); //Clicking the replay button will set the gameScene again
					primaryStage.show();
		     });
			primaryStage.setTitle("Pipe Puzzle Game"); //stage's title is set
			primaryStage.setScene(startScene); //the screen is added to the stage
			primaryStage.show(); //and stage is presented
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void fillCellList() {
		int k=0, l=0; //numbers retained to place background cells in 2D
		for(int m=0; m<16; m++) {
			Cell cell=cellDatas.get(m); //An image is created according to the properties of the cells drawn sequentially from cellDatas
				ImageView img=null; //an empty imageView is created
				if(cell.getType().equals("Starter")) { //if cell's type is Starter
					if(cell.getProperty().equals("Vertical")) { //if cell's property is Vertical
						//This is how to get an image file
						img=new ImageView(new Image(new File("Starter_Vertical.png").toURI().toString()));
					}else{ //if cell's property is Horizontal
						img=new ImageView(new Image(new File("Starter_Horizontal.png").toURI().toString()));
					}
				}else if(cell.getType().equals("End")) {
					if(cell.getProperty().equals("Vertical")) {
						img=new ImageView(new Image(new File("End_Vertical.png").toURI().toString()));
					}else{
						img=new ImageView(new Image(new File("End_Horizontal.png").toURI().toString()));
					}
				}else if(cell.getType().equals("Pipe")) {
					if(cell.getProperty().equals("Vertical")) {
						img=new ImageView(new Image(new File("Pipe_Vertical.png").toURI().toString()));
					}else if(cell.getProperty().equals("Horizontal")) {
						img=new ImageView(new Image(new File("Pipe_Horizontal.png").toURI().toString()));
					}else if(cell.getProperty().equals("00")) {
						img=new ImageView(new Image(new File("Pipe_00.png").toURI().toString()));
					}else if(cell.getProperty().equals("01")) {
						img=new ImageView(new Image(new File("Pipe_01.png").toURI().toString()));
					}else if(cell.getProperty().equals("10")) {
						img=new ImageView(new Image(new File("Pipe_10.png").toURI().toString()));
					}else if(cell.getProperty().equals("11")) {
						img=new ImageView(new Image(new File("Pipe_11.png").toURI().toString()));
					}
					setDragImage(img); //This function is only called here as the only moveable elements are Pipe and None
				}else if(cell.getType().equals("Empty")) {
					if(cell.getProperty().equals("Free")) {
						img=new ImageView(new Image(new File("Empty_Free.png").toURI().toString()));
						changeCell(img); //This function is only called here, since cells that can be moved are only Free
					}else if(cell.getProperty().equals("none")) {
						img=new ImageView(new Image(new File("Empty.png").toURI().toString()));
						setDragImage(img); //This function is only called here as the only moveable elements are Pipe and None
					}
				}else if(cell.getType().equals("PipeStatic")) {
					if(cell.getProperty().equals("Vertical")) {
						img=new ImageView(new Image(new File("PipeStatic_Vertical.png").toURI().toString()));
					}else if(cell.getProperty().equals("Horizontal")) {
						img=new ImageView(new Image(new File("PipeStatic_Horizontal.png").toURI().toString()));
					}else if(cell.getProperty().equals("00")) {
						img=new ImageView(new Image(new File("PipeStatic_00.png").toURI().toString()));
					}else if(cell.getProperty().equals("01")) {
						img=new ImageView(new Image(new File("PipeStatic_01.png").toURI().toString()));
					}else if(cell.getProperty().equals("10")) {
						img=new ImageView(new Image(new File("PipeStatic_10.png").toURI().toString()));
					}else if(cell.getProperty().equals("11")) {
						img=new ImageView(new Image(new File("PipeStatic_11.png").toURI().toString()));
					}
				}
				img.setFitWidth(150); //the lengths of each image are adjusted
				img.setFitHeight(150);
				img.setCursor(Cursor.HAND); //hovering over an image becomes cursor hand
				images.add(img); //added to the images list
				bgGridPane[k][l]=img; //added to cells kept in the background
				l++; //The number of columns is incremented by one.
				if(l==4) { //if the number of columns has reached the maximum, the number of rows is increased to move to the bottom row
					l=0; //column count starts over again
					k++;
				}
		}
		createGridArea(bgGridPane,pane); //function will be explained
	}
	public void changeCell(ImageView img) { //The image where the dragged image is dropped is sent here
		img.setOnMouseDragExited(e -> { //Codes that run when drag processing is finished
			for(int x=0; x<4; x++) {
				for(int y=0; y<4; y++) {
					if(bgGridPane[x][y]==(ImageView)e.getSource()) { //The coordinates of the left image are taken from the coordinates that it is equal to in the background.
						coordinates[2]=x; //Set as 2nd and 3rd element
						coordinates[3]=y;
						break;
					}
				}
			}
			if(isUnit(coordinates)) { //this function will be explained
				//cells at these coordinates are removed first
				pane.getChildren().remove(bgGridPane[coordinates[2]][coordinates[3]]);
				pane.getChildren().remove(bgGridPane[coordinates[0]][coordinates[1]]);
				pane.add(bgGridPane[coordinates[2]][coordinates[3]],coordinates[1],coordinates[0]); //background cells are replaced
				pane.add(bgGridPane[coordinates[0]][coordinates[1]],coordinates[3],coordinates[2]);
				ImageView imgTemp=bgGridPane[coordinates[2]][coordinates[3]];
				bgGridPane[coordinates[2]][coordinates[3]]=bgGridPane[coordinates[0]][coordinates[1]];
				bgGridPane[coordinates[0]][coordinates[1]]=imgTemp;
				//Converting from 2 dimensions to one dimension can be done in this way
				Cell cellTemp = cellDatas.get(coordinates[2]*4+coordinates[3]);
				cellDatas.set(coordinates[2]*4+coordinates[3], cellDatas.get(coordinates[0]*4+coordinates[1]));
				cellDatas.set(coordinates[0]*4+coordinates[1], cellTemp);
				moveNum++; //Increases the number of moves
				pane.setBackground(new Background(new BackgroundFill(Color.web("#FFFFFF",1.0), CornerRadii.EMPTY, Insets.EMPTY))); //turns light orange color to white
				moveCounter.setText("Moves: "+moveNum); //Change the moveCounter Text
			}
			
            //We end DragDetected event
         e.consume();
     });
	}
	public void setDragImage(ImageView img) { //the dragged image is sent here
		img.setOnDragDetected(e -> { //If a drag is detected on the sent image
			for(int x=0; x<4; x++) {
				for(int y=0; y<4; y++) {
					if(bgGridPane[x][y]==(ImageView)e.getSource()) {
						//in the same way, the coordinates that are equal to the cell' in the background are taken.
						coordinates[0]=x; //and these coordinates are set to 0 and 1.
						coordinates[1]=y;
						break;
					}
				}
			}
			//background color turns slightly orange when a drag is detected
			pane.setBackground(new Background(new BackgroundFill(Color.web("#FFEFE4",1.0), CornerRadii.EMPTY, Insets.EMPTY)));
	        img.startFullDrag(); //the drag process is started
	        e.consume();
	     });
	}
	public boolean isLevelFinish() throws FileNotFoundException {
		for(int i=0; i<cellDatas.size(); i++) { //first find the starter cell and then continue accordingly
			if(cellDatas.get(i).getType().equals("Starter")) {
				currentCellNum=i;
				if(cellDatas.get(i).getProperty().equals("Vertical")) {
					nextCellNum=i+4; //if starter's property is vertical then we need to move forward 4 in ArrayList and after that our direction will be downwards
					direction="DOWN";
				}else if(cellDatas.get(i).getProperty().equals("Horizontal")) {
					nextCellNum=i-1; //if the starter's property is Horizontal, it is necessary to go back 1 unit and the direction is to the left
					direction="LEFT";
				} //all because there are two types of starters
				break;
			}
		}
		boolean isFinish=true;
		while(isFinish) {
			if(nextCellNum<0 || nextCellNum>15|| currentCellNum<0 || currentCellNum>15) {
				isFinish=false; //if nextCell goes outside the 16 dimensional ArrayList, the loop ends and the game is not successful
				return false;
			}else if(cellDatas.get(nextCellNum).getType().equals("Empty")) {
				isFinish=false; //it will fail again if the type of the next cell is empty
				return false;
			}else if(cellDatas.get(nextCellNum).getType().equals("End")) {
				//if the next cell is End, other controls are checked
				if(direction.equals("RIGHT")) {
					if(cellDatas.get(nextCellNum).getProperty().equals("Horizontal")) {
						isFinish=false; //If the direction is to the right and End Cell's property is Horizontal, the game is over.
						ball=new ImageView(new Image(new File("Ball.png").toURI().toString()));
						ball.setFitWidth(40); //added to ball pane
						ball.setFitHeight(40);
						pane.getChildren().add(ball);
						 PathTransition p = playAnimation(); //an animation is assigned to the ball
				 			p.setOnFinished(t -> {
				 				if(levelNum==files.size()) { //When you reach the last level, it writes Finish
				 					 nextLevel.setText("Finish");
				 				 }else {
				 					 resetGameScene();
				 				 } //function that runs when animation ends
				 			});
				 			
							return true;
					}else {
						isFinish=false;
						return false;
					}
				}else if(direction.equals("UP")) { //If the direction is up and the End Cell property is Vertical, it's game over.
					if(cellDatas.get(nextCellNum).getProperty().equals("Vertical")) {
						ball=new ImageView(new Image(new File("Ball.png").toURI().toString()));
						ball.setFitWidth(40); //again the ball is created and added to the pane
						ball.setFitHeight(40);
						pane.getChildren().add(ball);
						isFinish=false;
			    		PathTransition p = playAnimation();
			 			p.setOnFinished(t -> {
			 				if(levelNum==files.size()) { //When you reach the last level, it writes Finish
			 					 nextLevel.setText("Finish");
			 				 }else {
			 					 resetGameScene();
			 				 } //function that runs when animation ends
			 			});
						return true;
					}else {
						isFinish=false;
						return false;
					}
				}else {
					isFinish=false;
					return false;
				}
			}else {
				findNextCell(); //if the next cell is none of the above, we're on our way and the next cells are found
				isFinish=true;
			}
		}
		return false;
	}
	public void resetGameScene() {
		nextLevel.setText("");
		 currentLevel.setText("Level: "+(levelNum+1));
		 try {
			input=new Scanner(files.get(levelNum)); //next file is taken
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 images.clear(); //The images ArrayList is emptied
		 cellDatas.clear(); //cellDatas is emptied
		 while(input.hasNextLine()) {
				String dataText=input.nextLine();
				String fileDatas[]=dataText.split(",");
				int id=Integer.parseInt(fileDatas[0]); //here again the texts are taken from the file
				cellDatas.add(new Cell(id,fileDatas[1],fileDatas[2]));
			}
			fillCellList(); //this function will be explained
	 
	}
	public boolean findNextCell() {
		if(direction.equals("UP")) {
			if(cellDatas.get(nextCellNum).getProperty().equals("Vertical")) {
				//if the direction is up, the property of the next cell is Vertical, the next cell is 4 units behind and stays the same because the direction is Vertical
				currentCellNum=nextCellNum;
				nextCellNum=currentCellNum-4;
			}else if(cellDatas.get(nextCellNum).getProperty().equals("10")) {
				//if the direction is up, the next cell's property is 10, the next cell is 1 unit to the right and the direction is left
				currentCellNum=nextCellNum;
				nextCellNum=currentCellNum+1;
				direction="LEFT";
			}else if(cellDatas.get(nextCellNum).getProperty().equals("11")) {
				// if the direction is up, the next cell's property is 11, the next cell is 1 unit left and the direction is right
				currentCellNum=nextCellNum;
				nextCellNum=currentCellNum-1;
				direction="RIGHT";
			}else {
				return false; //UP direction does not match with other properties
			}
		}else if(direction.equals("DOWN")) {
			if(cellDatas.get(nextCellNum).getProperty().equals("Vertical")) {
				//If the direction is down, the property of the next cell is Vertical, the next cell is 4 units behind and the direction remains the same as it is Vertical.
				currentCellNum=nextCellNum;
				nextCellNum=currentCellNum+4;
			}else if(cellDatas.get(nextCellNum).getProperty().equals("00")) {
				//if the direction is downwards and the property is 00, the other cell is 1 unit to the left and the direction is to the left
				currentCellNum=nextCellNum;
				nextCellNum=currentCellNum-1;
				direction="LEFT";
			}else if(cellDatas.get(nextCellNum).getProperty().equals("01")) {
				//if the direction is down and property is 01, the other cell is 1 unit to the right and the direction is to the right
				currentCellNum=nextCellNum;
				nextCellNum=currentCellNum+1;
				direction="RIGHT";
			}else {
				return false; //DOWN direction does not match with other properties
			}
		}else if(direction.equals("RIGHT")) {
			if(cellDatas.get(nextCellNum).getProperty().equals("Horizontal")) {
				//if the direction is to the right the property is Horizontal then the next cell is 1 unit to the right and the direction does not change
				currentCellNum=nextCellNum;
				nextCellNum=currentCellNum+1;
			}else if(cellDatas.get(nextCellNum).getProperty().equals("00")) {
				//if the direction is to the right the property is 00 then the next cell is 4 units to the left and the direction is up
				currentCellNum=nextCellNum;
				nextCellNum=currentCellNum-4;
				direction="UP";
			}else if(cellDatas.get(nextCellNum).getProperty().equals("10")) {
				//if the direction is to the right the property is 10 then the next cell is 4 units to the right and the direction is down
				currentCellNum=nextCellNum;
				nextCellNum=currentCellNum+4;
				direction="DOWN";
			}else {
				return false; //RIGHT direction does not match with other properties
			}
		}else if(direction.equals("LEFT")) {
			if(cellDatas.get(nextCellNum).getProperty().equals("Horizontal")) {
				//if the direction is to the left the property is Horizontal then the next cell is 1 unit to the left and the direction does not change
				currentCellNum=nextCellNum;
				nextCellNum=currentCellNum-1;
			}else if(cellDatas.get(nextCellNum).getProperty().equals("01")) {
				//if the direction is to the left the property is 01 then the next cell is 4 units to the left and the direction is up
				currentCellNum=nextCellNum;
				nextCellNum=currentCellNum-4;
				direction="UP";
			}else if(cellDatas.get(nextCellNum).getProperty().equals("11")) {
				//if the direction is to the left the property is 11 then the next cell is 4 units to the right and the direction is down
				currentCellNum=nextCellNum;
				nextCellNum=currentCellNum+4;
				direction="DOWN";
			}else {
				return false; //LEFT direction does not match with other properties
			}
		}
		return true;
	}
	public boolean isUnit(int[] coordinates) { //The coordinates array filled in the drag operation is checked.
		if(Math.abs(coordinates[0]-coordinates[2])==1 && Math.abs(coordinates[1]-coordinates[3])==0) {
			return true; //If the difference of the x axes is 1 unit and the difference of the y axes is 0
		}else if(Math.abs(coordinates[0]-coordinates[2])==0 && Math.abs(coordinates[1]-coordinates[3])==1) {
			return true; //If the difference of the x axes is 0 units and the difference of the y axes is 1
		}	
		return false;
	}
	public void createGridArea(ImageView bgGridPane[][], GridPane pane) {
		pane.getChildren().clear(); //the board is cleaned first
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				pane.add(bgGridPane[i][j], j, i); //the pane is populated with background cells
			}
		}
		moveNum=0; //move number is set to 0
		moveCounter.setText("Moves: "+moveNum); //and moveCounter is reloaded
		pane.add(moveCounter, 1, 4); //again these elements are added to the pane
		pane.add(btn, 2, 4);
	    pane.add(currentLevel, 0, 4);
	    pane.add(nextLevel, 3, 4);
	}
	public PathTransition playAnimation(){
	        Path path = new Path(); //a path is created for the animation
	        int duration=3; //Defined as animation duration and set to 3 by default
	        if(levelNum<3){ //Since the first 3 levels move in the same line, their animations will be the same.
	        	double x = 75;
		        double y = 50;
		        path.getElements().add(new MoveTo(x,y));
	            path.getElements().add(new VLineTo(470)); //vertical progression
	            path.getElements().add(new ArcTo(200,200,100,180,470,false,false));
	            path.getElements().add(new HLineTo(520)); //horizontal progression
	        }else if(levelNum<5){ //The same animation is drawn as it moves along the same line in the animation between 3 and 5
	        	double x = 75;
		        double y = 50;
		        path.getElements().add(new MoveTo(x,y));
	            path.getElements().add(new VLineTo(240));
	            path.getElements().add(new ArcTo(180,180,0,180,320,false,false));
	            path.getElements().add(new HLineTo(525));
	            path.getElements().add(new ArcTo(180,190,320,525,320,false,false));
	            path.getElements().add(new VLineTo(170));
	        }else if(levelNum==5){
	        	double x =  520;
		        double y =  470;
		        path.getElements().add(new MoveTo(x,y));
	            path.getElements().add(new HLineTo(225));
	            path.getElements().add(new VLineTo(325));
	            duration=2;
	        }else if(levelNum==6){
	        	double x = 500;
		        double y = 25;
		        path.getElements().add(new MoveTo(x,y));
	            path.getElements().add(new HLineTo(230));
	            path.getElements().add(new VLineTo(170));
	            path.getElements().add(new HLineTo(375));
	            path.getElements().add(new VLineTo(325));
	            path.getElements().add(new HLineTo(525));
	            path.getElements().add(new VLineTo(475));
	            path.getElements().add(new HLineTo(225));
	            path.getElements().add(new VLineTo(325));
	            duration=5;
	        }
	        PathTransition animation = new PathTransition(); //a transition is created
	        animation.setPath(path); //path and ball are added to this transition
	        animation.setNode(ball);
	        animation.setDuration(Duration.seconds(duration)); //a time is added to the transition
	        animation.setCycleCount(1); //it is specified that the animation will run once
	        animation.play(); //animation is run
	        return animation;
	    }
	public static void main(String[] args) {
		launch(args); //javafx is run
	}
}
