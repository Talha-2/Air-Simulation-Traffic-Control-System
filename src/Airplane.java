package ASTC;


public class Airplane extends Thread {
    private Ground_network groundNetwork;
    private Task task;

    public Airplane(Task obj, Ground_network groundNetwork) {
        this.task = obj;
        this.groundNetwork = groundNetwork;
    }

    public void run() {
        checkGroundState();
    }

    public boolean checkGroundState() {
        synchronized (groundNetwork) {
            int destinationID = task.get_destination_id();

            System.out.println("Checking ground state for Aircraft " + task.get_airplane_id() + " at destination " + destinationID);

            if (destinationID == 1) {
                System.out.println("Destination ID == 1");
                Runway currentRunway = groundNetwork.findRunway(destinationID);
                if (currentRunway != null) {
                    System.out.println("Trying to reserve link for Aircraft " + task.get_airplane_id());
                    if (groundNetwork.reserveNetworkLink(currentRunway, 20)) {
                        land();
                        return true;
                    } else {
                        System.out.println("Link is currently occupied for Aircraft " + task.get_airplane_id());
                    }
                }
            } else if (groundNetwork.isTaxiway(destinationID)) {
                Taxiway currentTaxiway = groundNetwork.findTaxiway(destinationID);
                if (currentTaxiway != null) {
                    if (groundNetwork.reserveNetworkLink(currentTaxiway, 20)) {
                        taxi();
                        return true;
                    } else {
                       
                        return false;
                    }
                }
            } else if (groundNetwork.isGate(destinationID)) {
                Gate currentGate = groundNetwork.findGate(destinationID);
                if (currentGate != null) {
                    if (groundNetwork.reserveNetworkLink(currentGate, 20)) {
                       parking();
                        return true;
                    } else {
                     
                        return false;
                    }
                }
            }

            return false;
        }
    }

    public void land() {
      
        System.out.println("Aircraft " + task.get_airplane_id() + " is landing at " +  task.get_destination_id()  + ".");
    
    }

    public void taxi() {
      
        System.out.println("Aircraft " + task.get_airplane_id() + " is taxiing from " +  task.get_current_state_id() + " to " + task.get_destination_id()  + ".");
        
    }
    public void parking() {
        
        System.out.println("Aircraft " + task.get_airplane_id() + " is parking from " +  task.get_current_state_id() + " to " + task.get_destination_id()  + ".");
        
    }
   public void holding() {
        
        System.out.println("Aircraft " + task.get_airplane_id() + " is at hloding state  " +  task.get_current_state_id() + " .");
        
    }
}
