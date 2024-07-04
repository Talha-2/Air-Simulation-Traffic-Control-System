package ASTC;


enum LinkState {
    AVAILABLE,
    RESERVED
}

class Network_link {
    private LinkState state;

    public Network_link() {
        this.state = LinkState.AVAILABLE;
    }

    public LinkState getState() {
        return state;
    }

    public void setState(LinkState newState) {
        state = newState;
    }
}

