package comp3111.webscraper;


import org.junit.Test;
import static org.junit.Assert.*;


public class ControllerTest {
	
	Controller c= new Controller();

	@Test
	public void NonEmptySearchedDataTest() {
		//gets keyword and gives console text//public String getSearchedData (String keyword)
		try{
			assertTrue((c.getSearchedData("apple")).contains("apple"));	
	
		}catch(NullPointerException e) {
			e.printStackTrace();
			
		}
	}
	
	@Test
	public void EmptySearchedDataTest() {
		//give keyword and get console text//public String getSearchedData (String keyword)
		
		try{
			assertEquals("",c.getSearchedData("     "));
	
		}catch(NullPointerException e) {
			e.printStackTrace();
			
		}	
	}
	
	@Test
	public void RefinedDataTest() {
	    //gets console text and keyword and gives refined console text//public String getRefinedData ( String consoleText, String keyword) {

		try{
			String line1 = "Small honey jar	0.0	https://newyork.craigslist.org/https://newyork.craigslist.org/que/zip/d/small-honey-jar/6747273831.html";
			String line2 = "Honey Bee Packages	137.0	https://newyork.craigslist.org/https://newyork.craigslist.org/brk/grd/d/honey-bee-packages/6743244115.html";
			String text = line1 + "\n" + line2;
		
			assertTrue((c.getRefinedData(text,"Honey")).contains("Honey"));
		
	
		}catch(NullPointerException e) {
			e.printStackTrace();
			
		}
	
	}

	@Test
	public void priceArrayList() {
		
		try{
			String line1 = "Small honey jar	0.0	https://newyork.craigslist.org/https://newyork.craigslist.org/que/zip/d/small-honey-jar/6747273831.html";
			String line2 = "Honey Bee Packages	137.0	https://newyork.craigslist.org/https://newyork.craigslist.org/brk/grd/d/honey-bee-packages/6743244115.html";

			//		String text = line1 + "\n" + line2;
			
		
			String lines[] = new String[2];
			lines[0] = line1;
			lines[1] = line2;
			
			c.SummaryTab(lines);
			
			assertEquals("2",c.getDataCount());
	
		}catch(NullPointerException e) {
			e.printStackTrace();
			
		}

	}

	
	@Test
	public void minimumURL() {
		try{
			String line1 = "Small honey jar	0.0	https://newyork.craigslist.org/https://newyork.craigslist.org/que/zip/d/small-honey-jar/6747273831.html";
			String line2 = "Honey Bee Packages	137.0	https://newyork.craigslist.org/https://newyork.craigslist.org/brk/grd/d/honey-bee-packages/6743244115.html";
		
			String lines[] = new String[2];
			lines[0] = line1;
			lines[1] = line2;
			
			c.SummaryTab(lines);
			
			
			assertEquals("https://newyork.craigslist.org/https://newyork.craigslist.org/que/zip/d/small-honey-jar/6747273831.html",c.getLowestUrl());
			
		}catch(NullPointerException e) {
			e.printStackTrace();
			
		}
	
	}

	@Test
	public void retrieveAveragePrice() {
	    //gets console text and keyword and gives refined console text//public String getRefinedData ( String consoleText, String keyword) {

		
		
		try{
			String line1 = "Small honey jar	20.0	https://newyork.craigslist.org/https://newyork.craigslist.org/que/zip/d/small-honey-jar/6747273831.html";
			String line2 = "Honey Bee Packages	30.0	https://newyork.craigslist.org/https://newyork.craigslist.org/brk/grd/d/honey-bee-packages/6743244115.html";

			String lines[] = new String[2];
			lines[0] = line1;
			lines[1] = line2;
			
			c.SummaryTab(lines);
			assertEquals("25.0",c.getAveragePrice());
		}catch(NullPointerException e) {
			e.printStackTrace();
			
		}
		
	}
	
	@Test
	public void RefinedDataMixedCaseTest(){
	    //gets console text and keyword and gives refined console text//public String getRefinedData ( String consoleText, String keyword) {

		
		try{
			String line1 = "Small honey jar	0.0	https://newyork.craigslist.org/https://newyork.craigslist.org/que/zip/d/small-honey-jar/6747273831.html";
			String line2 = "Honey Bee Packages	137.0	https://newyork.craigslist.org/https://newyork.craigslist.org/brk/grd/d/honey-bee-packages/6743244115.html";
			String text  = line1 + "\n" + line2;
		
			assertTrue((c.getRefinedData(text,"HoNeY")).contains("Honey"));
		
	
		}catch(NullPointerException e) {
			e.printStackTrace();
			
		}
	
	}
	
	@Test
	public void emptyConsoleAfterCloseTest(){
	   //takes in keyword and stores it and returns empty string to set to console// public String closeSearch(String keyword) {

		try{
			assertEquals("",c.closeSearch("happy"));	
	
		}catch(NullPointerException e) {
			e.printStackTrace();
			
		}
	
	}
	
	@Test
	public void LastSearchedTest() {
		
		
		try{
			
			String flag = c.getSearchedData("orange");
			flag =c.getSearchedData("grape");
			
			assertEquals(c.getLastSearched(), "orange");	
		}catch(NullPointerException e) {
			e.printStackTrace();
			
		}
		
	
	}

	@Test
	public void retrieveLastSearchAfterCloseTest() {

		try{
			
			String flag = c.getSearchedData("orange");
			flag =c.getSearchedData("grape");
			c.closeSearch("grape");
			assertEquals(c.getLastSearched(), "grape");	
		}catch(NullPointerException e) {
			e.printStackTrace();
			
		}
	}
	
}


