import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class MySAXParser {

    public List<City> parse() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParserHandler handler = new SAXParserHandler();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (Exception e) {
            throw new Exception("Open SAX parser error " + e.toString());
        }
        File file = new File("address.xml");
        try {
            parser.parse(file, handler);
        } catch (SAXException e) {
            throw new SAXException("SAX parsing error " + e.toString());
        } catch (IOException e) {
            throw new IOException("IO parsing error " + e.toString());
        }
        return handler.getCityList();
    }

    private List<City> getSortedCityList(List<City> parsedXML) {
        return parsedXML.stream()
                .sorted(Comparator.comparing(City::getName)
                        .thenComparing(City::getStreet)
                        .thenComparing(City::getHouse))
                .collect(Collectors.toList());
    }

    public void printAllDuplicates(List<City> parsedXML) {
        parsedXML = getSortedCityList(parsedXML);
        System.out.println("Общее количество записей : " + parsedXML.size());
        System.out.println();
        System.out.println("==============================================");
        System.out.println();
        System.out.println("Дубликаты :");
        System.out.println();

        Map<City, Integer> duplicates = new HashMap<>();
        for (int i = 1; i < parsedXML.size(); i++) {
            City prevCity = parsedXML.get(i - 1);
            City currentCity = parsedXML.get(i);
            if (prevCity.equals(currentCity)) {
                duplicates.putIfAbsent(prevCity, 1);
                duplicates.merge(prevCity, 1, Integer::sum);
            }
        }
        for (Map.Entry<City, Integer> pair : duplicates.entrySet()) {
            System.out.println(pair.getKey().toString() + " дубликат, количество повторений : " + pair.getValue());
        }
    }

    public void printAllCityBuildingsSortedByFloor(List<City> parsedXML) {
        Set<City> citySet = new HashSet<>(parsedXML);
        Map<String, Map<Integer, Integer>> buildingsByCity = new HashMap<>();
        for (City city : citySet) {
            Map<Integer, Integer> map;
            if (!buildingsByCity.containsKey(city.getName())) {
                map = new HashMap<>();
                map.put(city.getFloor(), 1);
            } else {
                map = buildingsByCity.get(city.getName());
                map.merge(city.getFloor(), 1, Integer::sum);
            }
            buildingsByCity.put(city.getName(), map);
        }
        System.out.println();
        System.out.println("==============================================");
        System.out.println();
        System.out.println("Количество в каждом городе 1, 2, 3, 4 и 5 этажных зданий :");
        for (Map.Entry<String, Map<Integer, Integer>> multimap : buildingsByCity.entrySet()) {
            for (Map.Entry<Integer, Integer> pair : multimap.getValue().entrySet()) {
                System.out.println();
                System.out.println("В городе " + new String(multimap.getKey().getBytes(StandardCharsets.UTF_8)) +
                        " " + pair.getValue() + " домов с " + pair.getKey() + " этажами.");
            }
        }
    }
}
