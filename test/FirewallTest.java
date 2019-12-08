import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class FirewallTest {
    @Before
    public void setUp() throws IOException {
        Utils utils = new Utils();
        utils.readFile("rule.csv");
    }
    @Test
    public void test() {
        Assert.assertEquals(true, Firewall.acceptPacket("outbound", "tcp", 20000, "192.168.10.11"));
        Assert.assertEquals(true, Firewall.acceptPacket("inbound", "tcp",80,"192.168.1.2"));
        Assert.assertEquals(false, Firewall.acceptPacket("inbound", "tcp",80,"192.168.1.322"));
        Assert.assertEquals(false, Firewall.acceptPacket("inbound", "udp",43,"12.53.6.25"));
        Assert.assertEquals(true, Firewall.acceptPacket("inbound", "tcp",673,"123.45.56.83"));
        Assert.assertEquals(false, Firewall.acceptPacket("inbound", "udp",677,"123.45.56.85"));
        Assert.assertEquals(true, Firewall.acceptPacket("inbound", "tcp",677,"123.45.56.78"));
        Assert.assertEquals(true, Firewall.acceptPacket("inbound", "tcp",670,"123.45.56.85"));
        Assert.assertEquals(false, Firewall.acceptPacket("outbound", "udp",999,"52.12.48.92"));
        Assert.assertEquals(true, Firewall.acceptPacket("outbound", "udp",1500,"52.12.48.92"));
        Assert.assertEquals(true, Firewall.acceptPacket("inbound", "tcp",23,"0.0.0.0"));
        Assert.assertEquals(true, Firewall.acceptPacket("outbound", "udp",23,"255.255.255.255"));
    }
}
