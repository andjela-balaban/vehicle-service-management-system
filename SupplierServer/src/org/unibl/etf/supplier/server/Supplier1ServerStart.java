package org.unibl.etf.supplier.server;

import static org.unibl.etf.logger.MyLogger.logger;
import java.io.IOException;
import java.util.logging.Level;

public class Supplier1ServerStart {
    public static void main(String[] args) {
        try {
            SupplierServer server = new SupplierServer("supplier1");
            new Thread(() -> {
                try {
                    server.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.log(Level.WARNING, "Exception: ", e);
                }
            }).start();

            System.out.println("SupplierServer pokrenut za test.");
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Exception: ", e);
        }
    }
}
