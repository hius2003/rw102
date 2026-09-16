public class Position {
    int id;
    PositionName name;

    public enum PositionName {
        DEV, TEST, SCRUM_MASTER, PM
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}