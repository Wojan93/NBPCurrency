package atj.rest.server;

import java.lang.reflect.Field;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ExchangeRatesSeries")
public class ExchangeRatesSeries {

	private String table;
	private String currency;
	private String code;
	private List<Rate> rates;
	//Rate mids = new Rate();

	public ExchangeRatesSeries() {
	}

	public ExchangeRatesSeries(String table, String currency, String code, List<Rate> rates) {

		this.table = table;
		this.currency = currency;
		this.code = code;
		this.rates = rates;
	}

	@XmlElement(name = "Table")
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	@XmlElement(name = "Currency")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@XmlElement(name = "Code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlElementWrapper(name = "Rates")
	@XmlElement(name = "Rate")
	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		String res = "\n";
		try {
			for (Field field : fields) {
				res += field.getName() + " : " + field.get(this) + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

}