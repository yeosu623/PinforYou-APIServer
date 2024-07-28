package YU.PinforYouAPIServer.Other;

import java.security.SecureRandom;

public class InviteCode {

    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int LENGTH = 12;
    private static final SecureRandom RANDOM = new SecureRandom();

    // 0~9, a~z, A~Z로 이루어진 12자리 랜덤 문자열
    public static String generate() {
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
}
