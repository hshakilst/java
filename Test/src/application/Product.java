package application;

public class Product {
	private String name;
	private String item;
	private String pin;
	private String print;
	private String media;
	private String liner;
	private String rate;
	
	public Product(String name, String item, String pin, String print, String media, String liner, String rate) {
		super();
		this.name = name;
		this.item = item;
		this.pin = pin;
		this.print = print;
		this.media = media;
		this.liner = liner;
		this.rate = rate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPrint() {
		return print;
	}

	public void setPrint(String print) {
		this.print = print;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getLiner() {
		return liner;
	}

	public void setLiner(String liner) {
		this.liner = liner;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	
	
}
