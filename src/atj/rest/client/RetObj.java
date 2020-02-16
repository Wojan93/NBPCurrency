package atj.rest.client;

public class RetObj {
private String currency;
	
	private String lastDays;
	private double average;
	
	public RetObj(String currency, String lastDays, double average) {
		this.currency = currency;
		this.lastDays = lastDays;
		this.average = average;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getLastDays() {
		return lastDays;
	}

	public void setLastDays(String lastDays) {
		this.lastDays = lastDays;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	
}
