package YU.PinforYouAPIServer.Other;

public class HandleCardNumber {

    public static String split(String number) {
        String first4 = number.substring(0, 4);
        String second4 = number.substring(4, 8);
        String third4 = number.substring(8, 12);
        String fourth4 = number.substring(12, 16);

        return first4 + "-" + second4 + "-" + third4 + "-" + fourth4;
    }
}
