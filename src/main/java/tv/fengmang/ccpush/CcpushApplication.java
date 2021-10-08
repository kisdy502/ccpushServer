package tv.fengmang.ccpush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tv.fengmang.ccpush.cc.CcPushServer;

@SpringBootApplication
public class CcpushApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcpushApplication.class, args);
        CcPushServer.start();
    }

}
