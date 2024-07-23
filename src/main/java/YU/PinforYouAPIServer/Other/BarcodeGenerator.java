package YU.PinforYouAPIServer.Other;

import java.util.UUID;

public class BarcodeGenerator {

    public static String generateBarcode() {
        return UUID.randomUUID().toString();
    }
}