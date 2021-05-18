import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        MySAXParser parser = new MySAXParser();
        List<City> parsedXML = parser.parse();
        parser.printAllDuplicates(parsedXML);
        parser.printAllCityBuildingsSortedByFloor(parsedXML);
    }
}
