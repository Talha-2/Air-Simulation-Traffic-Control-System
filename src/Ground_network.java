package ASTC;
import java.util.ArrayList;
import java.util.List;

public class Ground_network {
    private Runway runway;
    private List<Taxiway> taxiways;
    private List<Gate> gates;

    public Ground_network() {
        this.runway = new Runway(1);
        this.taxiways = new ArrayList<>();
        this.gates = new ArrayList<>();

        taxiways.add(new Taxiway(1));
        taxiways.add(new Taxiway(2));
        taxiways.add(new Taxiway(3));
        taxiways.add(new Taxiway(4));

        gates.add(new Gate(1));
        gates.add(new Gate(2));
        gates.add(new Gate(3));
    }

    public boolean reserveNetworkLink(Network_link link, int durationInSeconds) {
        if (link.getState() == LinkState.AVAILABLE) {
            link.setState(LinkState.RESERVED);
            System.out.println("Link had been reserved for a time being............");
            scheduleLinkReleaseTask(link, durationInSeconds);
            return true;
        } else {
            System.out.println("Link is not available right now");
            return false;
        }
    }

    private void scheduleLinkReleaseTask(Network_link link, int durationInSeconds) {
        new Thread(() -> {
            try {
                Thread.sleep(durationInSeconds * 1000);
                link.setState(LinkState.AVAILABLE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public Runway findRunway(int destinationID) {
        if (destinationID == 1) {
            return runway;
        }
        return null;
    }

    public boolean isTaxiway(int destinationID) {
    
        return destinationID > 1 && destinationID <= taxiways.size() + 1;
    }

    public Taxiway findTaxiway(int destinationID) {
        if (isTaxiway(destinationID)) {
            return taxiways.get(destinationID - 2);
        }
        return null;
    }

    public boolean isGate(int destinationID) {
       
        return destinationID > taxiways.size() + 1 && destinationID <= taxiways.size() + gates.size() + 1;
    }

    public Gate findGate(int destinationID) {
        if (isGate(destinationID)) {
            return gates.get(destinationID - taxiways.size() - 2);
        }
        return null;
    }
}
