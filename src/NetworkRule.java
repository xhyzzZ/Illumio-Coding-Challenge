public class NetworkRule {
    private String direction;
    private String protocol;
    private int port;
    private long ipAddress;

    public NetworkRule(String direction, String protocol, int port, long ipAddress) {
        this.direction = direction;
        this.protocol = protocol;
        this.port = port;
        this.ipAddress = ipAddress;
    }

    long hash() {
        long hash = this.ipAddress + this.port + this.direction.hashCode() + this.protocol.hashCode(); //get unique key from all the components
        return Long.valueOf(hash).hashCode();
    }
}
