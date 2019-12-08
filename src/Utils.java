import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Utils {
    protected static HashMap<Long, Boolean> InTcp = new HashMap<>();
    protected static HashMap<Long, Boolean> OutTcp = new HashMap<>();
    protected static HashMap<Long, Boolean> InUdp = new HashMap<>();
    protected static HashMap<Long, Boolean> OutUdp = new HashMap<>();
    public void readFile(String filepath) throws IOException {

        File file = new File(filepath);
        BufferedReader br = new BufferedReader(new java.io.FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            String[] info = line.split(",");
            String direction = info[0], protocol = info[1], portRange = info[2], ipRange = info[3];
            put(direction, protocol, portRange, ipRange);
        }
    }

    private void put(String direction, String protocol, String portRange, String ipRange) {
        String[] port = portRange.split("-");
        int portFrom = Integer.parseInt(port[0]), portTo;
        if (port.length > 1) {
            portTo = Integer.parseInt(port[1]);
        } else {
            portTo = portFrom;
        }

        String[] ip = ipRange.split("-");
        long ipFrom = ipToLong(ip[0]), ipTo;
        if (ip.length > 1) {
            ipTo = ipToLong(ip[1]);
        } else {
            ipTo = ipFrom;
        }
        int portDiff = portTo - portFrom;
        long ipDiff = ipTo - ipFrom;
        for (int i = 0; i <= portDiff; i++) {
            for (long j = 0; j <= ipDiff; j++) {
                NetworkRule rule = new NetworkRule(direction, protocol, portFrom + i, ipFrom + j);
                if (direction.equals("inbound") && protocol.equals("udp")) {
                    InUdp.put(rule.hash(), Boolean.TRUE);
                } else if (direction.equals("outbound") && protocol.equals("udp")) {
                    OutUdp.put(rule.hash(), Boolean.TRUE);
                } else if (direction.equals("inbound") && protocol.equals("tcp")) {
                    InTcp.put(rule.hash(), Boolean.TRUE);
                } else {
                    OutTcp.put(rule.hash(), Boolean.TRUE);
                }
            }
        }
    }

    public static long ipToLong(String ipAddress) {
        String[] ipAddressInArray = ipAddress.split("\\.");
        long result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {
            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);
            result += ip * Math.pow(256, power);
        }
        return result;
    }
}
