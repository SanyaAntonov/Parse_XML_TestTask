import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SAXParserHandler extends DefaultHandler {

    private List<City> cityList = new ArrayList<>();
    private City city;
    private Date startDate;
    private Date endDate;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("item")) {
            city = new City();
            city.setName(attributes.getValue(0));
            city.setStreet(attributes.getValue(1));
            city.setHouse(Integer.parseInt(attributes.getValue(2)));
            city.setFloor(Integer.parseInt(attributes.getValue(3)));
            cityList.add(city);
            city = null;
        }
    }

    @Override
    public void startDocument() throws SAXException {
        startDate = new Date();
        System.out.println("Start document");
    }

    @Override
    public void endDocument() throws SAXException {
        endDate = new Date();
        System.out.println("End document");
        System.out.println();
        System.out.println("==============================================");
        System.out.println();
        System.out.println("Общее время парсинга документа : " +
                ((double)(endDate.getTime() - startDate.getTime())/ 1000) + " секунд." );
    }

    public List<City> getCityList() {
        return cityList;
    }
}
