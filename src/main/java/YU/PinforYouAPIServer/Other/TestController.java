package YU.PinforYouAPIServer.Other;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestController {

    @GetMapping("/QRTest")
    public ResponseEntity<byte[]> getQRCode() throws IOException, WriterException {
        return QRCode.generate("aaaaaa");
    }

    @GetMapping("/BarcodeTest")
    public ResponseEntity<byte[]> getBarcode() throws IOException, WriterException {
        return Barcode.generate("1029364928345");
    }
}
