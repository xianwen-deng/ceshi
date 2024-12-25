package com.dxw.intWork;





import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.*;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;


/**
 * Classname: AppTask
 * Package: com.example.dxw.config
 * Description:
 *
 * @Author
 * @Create 2023/6/4 14:57
 * Version 1.0
 */

@Component
public class AppTask {

    @Value("${server.port}")
    private String port;

    @PostConstruct
    public void init() {
        System.out.println("http://localhost:" + port);
        System.out.println("http://localhost:" + port + "/doc.html");
        System.out.println();
        Set<IPAddressEntiy> ipList = new TreeSet<>((o1, o2) -> Double.compare(o2.getFirst(), o1.getFirst()) == 0 ? 1 : Double.compare(o2.getFirst(), o1.getFirst()));
        LinkedList<String> ips = dynamicIp();
        for (Object ip : ips) {
            if (ip.equals("127.0.0.1")) continue;
            ipList.add(new IPAddressEntiy(ip.toString()));
        }
        ipList.forEach(ip -> System.out.println("http://" + ip.getIp() + ":" + port + "/doc.html"));
    }

    public static LinkedList<String> dynamicIp() {
        LinkedList<String> ipList = new LinkedList<>();
        Enumeration<NetworkInterface> interfs = null;
        try {
            interfs = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        int i = 0;
        while (interfs.hasMoreElements()) {
            NetworkInterface interf = interfs.nextElement();
            Enumeration<InetAddress> addres = interf.getInetAddresses();
            boolean b = addres.hasMoreElements();
            while (addres.hasMoreElements()) {
                InetAddress in = addres.nextElement();
                if (in instanceof Inet4Address) {
                    String hostAddress = in.getHostAddress();
                    ipList.add(hostAddress);
                } else if (in.getHostAddress().indexOf("2409") != -1 && in instanceof Inet6Address) {
//                    //      "v6:" + in.getHostAddress());
                    i++;
                }
            }
        }
        return ipList;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor//传出去的第一次vo
    public class IPAddressEntiy {
        String ip;

        public int getFirst() {
            return Integer.parseInt(ip.split("\\.")[0]);
        }
    }


}
