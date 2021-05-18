import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class City {
    private String name;
    private String street;
    private int house;
    private int floor;

    public City() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + new String(name.getBytes(StandardCharsets.UTF_8)) + '\'' +
                ", street='" + new String(street.getBytes(StandardCharsets.UTF_8)) + '\'' +
                ", house=" + house +
                ", floor=" + floor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return house == city.house && floor == city.floor && Objects.equals(name, city.name) && Objects.equals(street, city.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, street, house, floor);
    }
}
