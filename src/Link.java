package ASTC;
import java.util.ArrayList;
import java.util.List;


class Link extends Network_link {
    private List<Integer> airplaneIds;

    public Link() {
        this.airplaneIds = new ArrayList<>();
    }

    public void occupy(int airplaneId) {
        airplaneIds.add(airplaneId);
    }

    public void release() {
        airplaneIds.clear();
    }
}
