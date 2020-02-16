package atj.rest.client;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="root")
public class RetXml {
	
	private String currency;
	private String lastDays;
	private double average;

	public RetXml() {
		
	}
	
	public RetXml(String currency, String lastDays, double average) {
		this.currency = currency;
		this.lastDays = lastDays;
		this.average = average;
	}
	
	@XmlElement(name = "Currency")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@XmlElement(name = "LastDays")
	public String getLastDays() {
		return lastDays;
	}

	public void setLastDays(String lastDays) {
		this.lastDays = lastDays;
	}
	
	@XmlElement(name = "Average")
	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

}
