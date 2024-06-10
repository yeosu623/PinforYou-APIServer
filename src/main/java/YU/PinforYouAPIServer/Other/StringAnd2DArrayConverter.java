package YU.PinforYouAPIServer.Other;

import java.util.ArrayList;
import java.util.Arrays;

public class StringAnd2DArrayConverter {

    // 2D ArrayList -> String
    public String atos(ArrayList<ArrayList<String>> arrayList) {
        StringBuilder sb = new StringBuilder();
        for (ArrayList<String> row : arrayList) {
            sb.append(String.join(",", row));
            sb.append(";");
        }
        // 마지막 세미콜론 제거
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    // String -> 2D ArrayList
    public ArrayList<ArrayList<String>> stoa(String str) {
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        String[] rows = str.split(";");
        for (String row : rows) {
            ArrayList<String> rowList = new ArrayList<>(Arrays.asList(row.split(",")));
            arrayList.add(rowList);
        }
        return arrayList;
    }
}
