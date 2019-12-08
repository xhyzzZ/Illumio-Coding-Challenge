import java.io.IOException;

public class Firewall {
    public static void main(String[] args) throws IOException {
        Utils utils = new Utils();
        utils.readFile("rule.csv");
        boolean test1 = acceptPacket("outbound", "tcp", 20000, "192.168.10.11");
        boolean test2 = acceptPacket("inbound", "tcp",80,"192.168.1.2");
        boolean test3 = acceptPacket("inbound", "tcp",80,"192.168.1.322");
        boolean test4 = acceptPacket("inbound", "udp",43,"12.53.6.25");
        boolean test5 = acceptPacket("inbound", "tcp",673,"123.45.56.83");
        boolean test6 = acceptPacket("inbound", "udp",677,"123.45.56.85");
        boolean test7 = acceptPacket("inbound", "tcp",677,"123.45.56.78");
        boolean test8 = acceptPacket("inbound", "tcp",670,"123.45.56.85");
        boolean test9 = acceptPacket("outbound", "udp",999,"52.12.48.92");
        boolean test10 = acceptPacket("outbound", "udp",1500,"52.12.48.92");
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);
        System.out.println(test4);
        System.out.println(test5);
        System.out.println(test6);
        System.out.println(test7);
        System.out.println(test8);
        System.out.println(test9);
        System.out.println(test10);
    }

    public static boolean acceptPacket(String direction, String protocol, int port, String ipAddress) {
        NetworkRule rule = new NetworkRule(direction, protocol, port, Utils.ipToLong(ipAddress));
        if (direction.equals("inbound") && protocol.equals("udp")) {
            return Utils.InUdp.containsKey(rule.hash());
        } else if (direction.equals("outbound") && protocol.equals("udp")) {
            return Utils.OutUdp.containsKey(rule.hash());
        } else if (direction.equals("inbound") && protocol.equals("tcp")) {
            return Utils.InTcp.containsKey(rule.hash());
        } else {
            return Utils.OutTcp.containsKey(rule.hash());
        }
    }
}
