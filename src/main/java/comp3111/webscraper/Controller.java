/**
 * 
 */
package comp3111.webscraper;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Hyperlink;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.application.Platform;
import java.util.*;
import java.util.ArrayList;
import javafx.scene.text.Font;
import javafx.application.HostServices;


import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.apache.http.impl.client.SystemDefaultCredentialsProvider;


public class Controller {
	
	@FXML
	private Button btnRefine;
	
	@FXML
	private Button btnGo;
	
	@FXML
    private MenuItem menuQuit;
	
	
	@FXML
    private MenuItem menuLastSearch;

    @FXML 
    private Label labelCount; 

    @FXML 
    private Label labelPrice; 

    @FXML 
    private Hyperlink labelMin; 

    @FXML 
    private Hyperlink labelLatest; 

    @FXML
    private TextField textFieldKeyword;
    
    @FXML
    private TextArea textAreaConsole;
    
    private WebScraper scraper;
    private  ArrayList<String> history = new ArrayList<>();
    
    
    
    @FXML
    private TableView<Item> searchTable;
    @FXML 
    private TableColumn<Item,String> itemTitle = new TableColumn();

    @FXML 
    private TableColumn<Item,String> itemPrice = new TableColumn();
    @FXML 
    private TableColumn<Item, String> itemURL = new TableColumn();
    @FXML 
    private TableColumn<Item, String> itemPostDate = new TableColumn();

    private String[] resultsList;
    private String dataCount;
    private String latestIitemURL;
    private String averagePrice;
    private HostServices hostServices;
    public  int urlIndex; 
    public String minurl;

    /**
     * Default controller
     */
    public Controller() {
    	scraper = new WebScraper();	
    }

    /**
     * Default initializer. Disables the Last Search and Refine button until a keyword is searched.
     */
    @FXML
    private void initialize() {
//		menuLastSearch.setDisable(true);	
		menuLastSearch.setDisable(true);
    	btnRefine.setDisable(true);
   
    	
    }
 
    
    /**
     * Called when the search button is pressed. 
     * Calls the getSearchedData () to get the scraped  data related to the keyword.
     * Enables the Last Search and Refine button.
     * Sets the scraped data in the table.
     */

    @FXML
    private void actionSearch() {
    	
    		if (textFieldKeyword.getText() != null) {
    			String searchedConsoleData =getSearchedData(textFieldKeyword.getText());
    			textAreaConsole.setText(searchedConsoleData);
    			
    			if(!searchedConsoleData.equals("")) {	
    				menuLastSearch.setDisable(false);	
    				btnRefine.setDisable(false);
    	   			
    			}
    		}
    
    		this.itemTitle.setCellValueFactory(new PropertyValueFactory<Item,String>("title"));
        	this.itemPrice.setCellValueFactory(new PropertyValueFactory<Item,String>("price"));
        	this.itemURL.setCellValueFactory(new PropertyValueFactory<Item,String>("url"));
        	this.itemPostDate.setCellValueFactory(new PropertyValueFactory<Item,String>("date"));
   
    }
    

    /**
     *  Called when the refine button is pressed. Calls the getRefinedData() method to refine the searched data 
     *  Disables the  Refine button after it is clicked.
     */

    @FXML
    private void actionRefine() {
    	
    	System.out.println("actionRefine");
    	btnRefine.setDisable(true);
    	
    	textAreaConsole.setText(getRefinedData(textAreaConsole.getText(),textFieldKeyword.getText()));
    	

    }
    

    /**
     * Called when the Last Search button is pressed. Calls the getLastSearched() method to get the keyword last searched.
     * Disables the Last Search button after it is clicked. 
     */
    @FXML
    private void actionNew() {
    	
    	System.out.println("Last Search");
//    	menuLastSearch.setDisable(true);
    	
    	String lastSearchedKeyword = getLastSearched();

    	textFieldKeyword.setText(lastSearchedKeyword);
    	actionSearch();
    	
    	if(!lastSearchedKeyword.equals(" ")) {
    	 	history.remove(history.size()-1);
           	for(String s:history) {
    			System.out.println(s);
    		}	
    	}
    	menuLastSearch.setDisable(true);
    }
    
    
    /**
     * Called when the Close button is pressed.
     *  Calls the closeSearch() method to clear the current search record and store the last searched
     */
    @FXML
    private void actionClose() {
    	System.out.println("close");
    	textAreaConsole.setText(closeSearch(textFieldKeyword.getText()));
    	textFieldKeyword.setText("");
    	btnRefine.setDisable(true); //disable refine when u click close
    }
    

    /**
     * Called when the Quit button is pressed. Method exits the program and closes all connections
     */
    @FXML
    private void actionQuit() {    	
    	System.out.println("quit");
    	
    	menuQuit.setOnAction(e->Platform.exit());
    	System.exit(0);
    }
 
    /**
     * Called when the About Your Team button is pressed.
     * Method displays a dialog box that shows all the team members name, itsc account, and github account.
     */    
    
    
    @FXML
    private void actionTeam() {
    	
    	System.out.println("actionTeam");
   		Stage window = new Stage();
   		window.initModality(Modality.APPLICATION_MODAL);
   		window.setTitle("About The Team");
   		window.setMinWidth(300);
   		window.setMinHeight(200);
   		Label label = new Label();
   		label.setText("this is my team");
   		
   		
   		GridPane pane = new GridPane(); // Create a pane and set its properties
   	    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
   	    pane.setHgap(5.5);
   	    pane.setVgap(8);
	    
   	    pane.setAlignment(Pos.CENTER);

        final Label labelName = new Label("NAME");
        labelName.setFont(Font.font("Cambria", 20));
       
        final Label labelITSC = new Label("ITSC");
        labelITSC.setFont(Font.font("Cambria", 20));
       
        final Label labelGit = new Label("GITHUB ACCOUNT");
        labelGit.setFont(Font.font("Cambria", 20));

   	    pane.add(labelName, 1, 0); // Place nodes in the pane
	    pane.add(labelITSC, 2, 0); // Place nodes in the pane
	    pane.add(labelGit, 3, 0); // Place nodes in the pane
   	    
	    labelName.setAlignment(Pos.CENTER);
	    labelITSC.setAlignment(Pos.CENTER);
	    labelGit.setAlignment(Pos.CENTER);
	    
   	    pane.add(new Label("1."), 0, 1); // Place nodes in the pane
   	    pane.add(new Label("Vaishnavi Ganesh"), 1, 1); // Place nodes in the pane
   	    pane.add(new Label("vganesh"), 2, 1); // Place nodes in the pane
   	    pane.add(new Label("vaishnavig11"), 3, 1); // Place nodes in the pane
   	    pane.add(new Label("2."), 0, 2); // Place nodes in the pane
	    pane.add(new Label("Tanmay Bhanushali"), 1, 2); // Place nodes in the pane
	    pane.add(new Label("tbhanushali"), 2, 2); // Place nodes in the pane
	    pane.add(new Label("tbhanushali"), 3,2 ); // Place nodes in the pane
	    
	   	    
   	    pane.add(new Label("3."), 0, 3); // Place nodes in the pane
	    pane.add(new Label("Ishaan Batra"), 1, 3); // Place nodes in the pane
	    pane.add(new Label("ibatra"), 2, 3); // Place nodes in the pane
	    pane.add(new Label("ibatra"), 3,3 ); // Place nodes in the pane
	    
//	    pane.add(new Button("OK"), 4,3 ); // Place nodes in the pane
	   
	    Button buttonOK = new Button ("BACK");
	    pane.add(buttonOK, 3,4 );
	    buttonOK.setPrefWidth(100);
	    
	    buttonOK.setOnAction( e -> {
            //here u implement the handle method
	    	window.close();
        });
	    
	    pane.setVgap(20);
	    pane.setHgap(20);

    	Scene scene =  new Scene(pane);
    	window.setScene(scene);
    	window.showAndWait();
    	
    }
    
    
    /**
     * This function scrapes data for the keyword entered.
     * Keeps a record of the keyword in history.
     * This method also updates the summary and the table tabs
     * 
     * @param keyword The keyword entered by the user
     * @return The scraped data
     * 
     */
    
    public String getSearchedData (String keyword) {
    	
		 if(keyword.isEmpty()==true||keyword.trim().isEmpty()==true) {
//  				return "";
		 }
		 
			 String output = "";
   			//disable Refine and Last Search
   			
		    	history.add(keyword);
			
		    	System.out.println("actionSearch: " + keyword);
		    	List<Item> result = scraper.scrape(keyword);
		        ArrayList<String> summary = new ArrayList<>();

		    	
		    	for (Item item : result) {
		    		output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\n";
		    		summary.add(item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\n");
		    		
		    	}
		    	fitDataInTable(result);
		    			    	
		    	String[] summaryArray = new String[summary.size()];
		    	summaryArray = summary.toArray(summaryArray);
		    	SummaryTab(summaryArray);
		    	 System.out.print("checking if output contains the keyword");
				 System.out.print(output.contains(keyword));
		    	return output;

		 

    }
    
    /**
     * This method refines the scraped data.
     * Displays only the items whose title contains the searched keyword . 
     * Calls the fitRefinedDataInTable() to update table
     * 
     * @param consoleText All scraped data for the keyword
     * @param keyword The keyword entered by the user
     * @return The refined data 
     * 
     */
    
    public String getRefinedData ( String consoleText, String keyword) {
    	
      	String lines[] = consoleText.split("\\r?\\n");

      	ArrayList<String> refinedLines = new ArrayList<>();


    	String output = "";
    	for(int i=0;i<lines.length;i++)
    	{
    		//if title contains the keyword, display the title
    		String currentLine =lines [i];
    		String[] rowDetail = currentLine.split("\t", -1);
    		String title = rowDetail[0];

    		if(title.toLowerCase().contains(keyword.toLowerCase())) {
        		output += currentLine+"\n";
        		refinedLines.add(lines [i]);	
    		}	
    	}
    	
    	//refined lines--> List of Strings which has all the refined data.
    	
    	fitRefinedDataInTable(refinedLines);
    	
    	String[] refinedArray = new String[refinedLines.size()];
    	refinedArray = refinedLines.toArray(refinedArray);
    	SummaryTab(refinedArray);
    	
    	return output;
    }
    
    /**
     * This method reverts search result to the previous search. 
     * Shows empty Text Console if there is no previous searches
     * 
     * @return The last searched keyword 
     * 
     */
    public String getLastSearched() {
    	String lastSearched=" ";
    	if(history.size()==1) {}
    	else if(history.size()>1) {		
			lastSearched = history.get(history.size()-2);
			System.out.println("LastSearched is "+lastSearched);     		
    	}
    	return lastSearched;	
    }
    
    /**
     * This method clears the current search record.
     * Initializes the summary and the table their initial state.
     * Keeps track of the record which was cleared so that it can be retrieved upon clicking Last Searched. 
     * @param keyword The keyword entered by the user
     * @return Returns empty string. This will be displayed in the Text Area.
     * 
     */
    
    public String closeSearch(String keyword) {
    	//store keyword
    	history.add(keyword); 
    	//option1: this line will allow u to retireve the keyword searched right before close was clicked.
    	//menuLastSearch.setDisable(true); //option 2: disable last search when close is clicked. 
    	//do nothing//option 3: after click close, u can so last search and see the item which was the last search of the item which was closed. eg: apple orange close. then u can see apple	
    	
 		List<Item> emptyList = new ArrayList<Item>();

 		fitDataInTable(emptyList);
    	labelCount.setText("");
    	labelPrice.setText("");
    	labelMin.setText("");
    	labelLatest.setText("");
    	return "";
    }
    
    
    
   
    public void fitDataInTable(List<Item> result) {
    	this.searchTable.setItems(FXCollections.observableArrayList(result));
    	
    }
    
    
    /**
     * This method takes in the refined data and update the table tab with the refined data.
     * @param refinedLines A list of Strings with the refined data
     * 
     */
    
    public void fitRefinedDataInTable(List<String> refinedLines) {

    	//refined array is an array of refined strings.

    	String[] refinedArray = new String[refinedLines.size()];
    	refinedArray = refinedLines.toArray(refinedArray);
    	
     	if(refinedArray.length>0) {
     	
     		List<Item> itemListofRefinedData = new ArrayList<Item>();
     		for(int i=0;i<refinedArray.length;i++)
        	{
        		//if title contains the keyword, display the title
        		String currentLine =refinedArray [i];
        		String[] rowDetail = currentLine.split("\t", -1);
        		String title = rowDetail[0];
        		double price = Double.parseDouble(rowDetail[1]);
        		String url =   rowDetail[2];
        		
        		Item item = new Item(title,price,url);
        		itemListofRefinedData.add(item);
        	}
        	this.searchTable.setItems(FXCollections.observableArrayList(itemListofRefinedData));
     	}
    
    }
    
public void SummaryTab(String results[]) {
    	
    	double[] priceArray = new double[results.length];   //define priceArray
    	String []urlArray = new String  [results.length];
    	for(int i=0;i<results.length;i++)
    	{
    		String currentLine =results [i];
    		String[] rowDetail = currentLine.split("\t", -1);
    		String price = rowDetail[1];
    		String url = rowDetail[2];
    		
    		priceArray[i] =  Double.parseDouble(price); 
    		urlArray[i] =  url;
    	}
    	
    	
    	//Find Minimum Price
    	double minValue = priceArray[0];
    	for (int i = 0; i<priceArray.length; i++) {
//    		if(priceArray[i]!=0 && priceArray[i] < minValue && minValue!=0 )
        	if( priceArray[i] < minValue ){
    			  minValue = priceArray[i];
    			  urlIndex=i;
    			  minurl = urlArray[urlIndex];			 	  
    		}
    	}

    	this.labelMin.setText(String.valueOf(minValue));
    	this.labelMin.setOnAction(e -> this.hostServices.showDocument(minurl));
    	
    	double average =calcAveragePrice(priceArray);
    	this.averagePrice = String.valueOf(average);
    	labelPrice.setText(String.valueOf(average));

    		
    	int numberOfData = results.length;

    	this.dataCount = String.valueOf(numberOfData);
    	labelCount.setText(String.valueOf(numberOfData));
 
    	if(numberOfData ==0) {
           
        	labelCount.setText("-");
        	labelPrice.setText("-");
        	labelMin.setText("-");
        	labelLatest.setText("-");
    	}		
    }


public double calcAveragePrice(double priceArray[]) {
 	//Find Average Price
	
	DecimalFormat df2 = new DecimalFormat(".##");

	if(priceArray.length ==0) {
		System.out.println(0);
		return 0;
	}
	else {
		
		double sum = 0; 
		for (int i = 0; i<priceArray.length; i++) {
			sum = sum+ priceArray[i];
		}
		double average = sum/priceArray.length;
		
		
		df2.setRoundingMode(RoundingMode.DOWN);
		String avg = df2.format(average);
		
		System.out.println("Average Value");
		
		average = Double.parseDouble(avg);
		System.out.println(average);

		return average;
	}
}

public void setHostServices(HostServices hostServices) {	
	this.hostServices = hostServices; 
}

public String getDataCount() {
    return this.dataCount;
}

public String getLowestUrl() {
    return this.minurl;
}

public String getLatestUrl() {
    return this.latestIitemURL;
}

public String getAveragePrice() {
    return this.averagePrice;
}
    
}

