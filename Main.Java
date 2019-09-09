package application;
	
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class Main extends Application {
	
public static ObservableList<LexPrint> printers = FXCollections.observableArrayList();
	
// this is for FXML to generate the GUI 
    @FXML 
    public TableView<LexPrint> printTable = new TableView<>();
    
	@FXML 
	TableColumn<LexPrint, String>name= new TableColumn<>();     
	
	@FXML 
	TableColumn<LexPrint, Integer>priority= new TableColumn<>();   
	
	@FXML
	TableColumn<LexPrint, Integer>fuser= new TableColumn<>();
	
	@FXML
	TableColumn<LexPrint, Integer>image= new TableColumn<>();
	
	@FXML
	TableColumn<LexPrint, Integer>threek= new TableColumn<>();
	
	@FXML
	TableColumn<LexPrint, Integer>sixk= new TableColumn<>();
	
	@FXML
	TableColumn<LexPrint, Integer>toner= new TableColumn<>();
	
	//this method is used to create printer objects
	public static void realData(){
		String line = null;
		int nameStart=0;
		int nameEnd=0;
		int iPStart=0;
		int iPEnd=0;
		int priorityStart=0;
		int priorityEnd=0;
		
		try{
			//this loads a text file and creates printer objects if statements are how data is separated in the text file
			FileReader fr = new FileReader("printers.txt");
			BufferedReader br = new BufferedReader(fr);
			 while((line = br.readLine()) != null) {
	             for(int i = 0; i < line.length(); i++){
	            	 char check = line.charAt(i);
	            	 if(check =='@'){nameStart=i; };
	            	 if(check =='!'){nameEnd=i; };
	            	 if(check =='#'){iPStart=i; };
	            	 if(check =='*'){iPEnd=i; };
	            	 if(check =='^'){priorityStart=i; };
	            	 if(check ==','){priorityEnd=i; }; 
	             };
	             String name = line.substring(nameStart, nameEnd);
	             String iP = line.substring(iPStart, iPEnd);
	             String pp = line.substring(++priorityStart, priorityEnd);
	             int priority = Integer.parseInt(pp);
	             LexPrint prin = new LexPrint(name,iP,priority);
	             printers.add(prin);
	             
	            }   
			 br.close();
		}
		catch(FileNotFoundException n){
			System.out.println("File Not Found");
		}
		catch(IOException na){
			System.out.println("File able to be read");
		}
		
	};	
	
	
	
	@Override
	public void start(Stage primaryStage) {
		//loads the interface
		try {
			Parent root = FXMLLoader.load(getClass().getResource("PrintS.fxml"));
			Scene scene = new Scene(root,900,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//reads the text file creates printers then loads the GUI
		realData();
		
		launch(args);
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		
		//initializes FXML content
		
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		name.setMinWidth(printTable.getMaxWidth()/8);
		priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
		priority.setMinWidth(printTable.getMaxWidth()/8);
		fuser.setCellValueFactory(new PropertyValueFactory<>("fuser"));
		fuser.setMinWidth(printTable.getMaxWidth()/8);
		image.setCellValueFactory(new PropertyValueFactory<>("image"));
		image.setMinWidth(printTable.getMaxWidth()/8);
		threek.setCellValueFactory(new PropertyValueFactory<>("k300"));
		threek.setMinWidth(printTable.getMaxWidth()/8);
		sixk.setCellValueFactory(new PropertyValueFactory<>("k600"));
		sixk.setMinWidth(printTable.getMaxWidth()/8);
		toner.setCellValueFactory(new PropertyValueFactory<>("toner"));
		toner.setMinWidth(printTable.getMaxWidth()/8);
		printTable.setItems(printers);
		
	}
	
	
}
