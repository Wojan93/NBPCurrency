package atj.rest.server;

import java.lang.reflect.Field;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name="Rate")
public class Rate {

	private String no;

	private LocalDate effectiveDate;

	private double mid;

	public Rate() {
		super();
	}

	public Rate(String no, LocalDate effectiveDate, double mid) {
		super();
		this.no = no;
		this.effectiveDate = effectiveDate;
		this.mid = mid;
	}

	
	@XmlElement(name = "EffectiveDate")
	 @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	
	@XmlElement(name = "Mid")
	public double getRate() {
		return mid;
	}

	public void setRate(double mid) {
		this.mid = mid;
	}

	@XmlElement(name = "No")
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Override
    public String toString() {
        Field[] fields = this.getClass().getDeclaredFields();
        String res = "{";
        try {
            for (Field field : fields) {
                res += field.getName() + " : " + field.get(this);
            }
            res += "}";
        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return res;
    }
	
}