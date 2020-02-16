package atj.rest.client;

import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.google.gson.Gson;

import atj.rest.server.ExchangeRatesSeries;
import atj.rest.server.Rate;

@Path("/webapi")
public class KlientNBP {

	ExchangeRatesSeries exrs = new ExchangeRatesSeries();
	List<Rate> syl;

	private double sum;

	private double calculateAverage() {
		ArrayList<Double> aver = new ArrayList<Double>();

		for (Rate field : syl) {
			aver.add(field.getRate());
			System.out.println(field.getRate());
			sum += field.getRate();
		}
		return sum / aver.size();
	}

	private String jaxbObjectToXML(RetXml retxml) {
		String xmlString = "";
		try {
			JAXBContext context = JAXBContext.newInstance(RetXml.class);
			StringWriter sw = new StringWriter();
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(retxml, sw);
			xmlString = sw.toString();
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlString;
	}

	public ExchangeRatesSeries unmarshaller(String curParam, String numParam) throws MalformedURLException {
		try {
			JAXBContext jc = JAXBContext.newInstance(ExchangeRatesSeries.class);
			Unmarshaller u = jc.createUnmarshaller();
			URL url = new URL(
					"http://api.nbp.pl/api/exchangerates/rates/a/" + curParam + "/last/" + numParam + "/?format=xml");
			exrs = (ExchangeRatesSeries) u.unmarshal(url);
			System.out.println(" unmarshalled " + exrs.getCode());
			System.out.println(" unmarshalled " + exrs.getRates());
			syl = exrs.getRates();

			return exrs;

		} catch (JAXBException e) {
			e.printStackTrace();
			System.out.println("Niepowodzenie");
		}
		return null;
	}

	@GET
	@Path("/{currency}/{lastDays}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public ExchangeRatesSeries getStream(@PathParam("currency") String curParam, @PathParam("lastDays") String numParam)
			throws MalformedURLException {
		return unmarshaller(curParam, numParam);
	}

	@GET
	@Path("/{currency}/{lastDays}/average")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public String getAverageTextPlain(@PathParam("currency") String curParam, @PathParam("lastDays") String numParam)
			throws MalformedURLException {
		syl = getStream(curParam, numParam).getRates();

		return "�redni kurs" + curParam.toUpperCase() + " z ostatnich " + numParam + " dni to : "
				+ calculateAverage();
	}

	@GET
	@Path("/{currency}/{lastDays}/average")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String getAverageXml(@PathParam("currency") String curParam, @PathParam("lastDays") String numParam)
			throws MalformedURLException {
		syl = getStream(curParam, numParam).getRates();
		RetXml retxml = new RetXml(curParam, numParam, calculateAverage());

		return jaxbObjectToXML(retxml);

	}

	@GET
	@Path("/{currency}/{lastDays}/average")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getAverageJson(@PathParam("currency") String curParam, @PathParam("lastDays") String numParam)
			throws MalformedURLException {
		syl = getStream(curParam, numParam).getRates();
		RetObj retobj = new RetObj(curParam, numParam, calculateAverage());

		Gson gson = new Gson();

		return gson.toJson(retobj);
	}

	@GET
	@Path("/{currency}/{lastDays}/average")
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML)
	public String getAverageHtml(@PathParam("currency") String curParam, @PathParam("lastDays") String numParam)
			throws MalformedURLException {
		syl = getStream(curParam, numParam).getRates();

		return "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<body>\r\n" + "<h1><b> " + calculateAverage() + " </b></h1>\r\n"
				+ "<p>Kurs �redni " + curParam.toUpperCase() + " z ostatnich " + numParam + " dni </p>\r\n"
				+ "</body>\r\n" + "</html>\r\n";

	}
}
