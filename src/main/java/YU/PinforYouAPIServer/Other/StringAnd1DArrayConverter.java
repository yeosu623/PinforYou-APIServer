package YU.PinforYouAPIServer.Other;

import java.util.ArrayList;
import java.util.Arrays;

public class StringAnd1DArrayConverter {

    // 1D ArrayList -> String
    public String atos(ArrayList<String> list) {
        return String.join(",", list);
    }

    // String -> 1D ArrayList
    public ArrayList<String> stoa(String str) {
        // 빈 문자열이 들어오는 경우를 처리하기 위해 빈 ArrayList를 반환
        if (str == null || str.isEmpty()) {
            return new ArrayList<>();
        }
        String[] items = str.split(",");
        return new ArrayList<>(Arrays.asList(items));
    }
}
