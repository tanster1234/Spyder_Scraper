package comp3111.webscraper;



public class Item {
	private String title ; 
	private double price ;
	private String url ;
	
	public Item (){
		
	}
	public Item (String title, double price,String url) {
		setTitle(title);
		setPrice(price);
		setUrl(url);
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	

}
