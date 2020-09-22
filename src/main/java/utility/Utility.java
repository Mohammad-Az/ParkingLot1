package utility;

import java.util.Date;
import java.util.List;

public class Utility {
    public static String generateList(List<String> dataList) {
        StringBuilder sb = new StringBuilder();
        dataList.forEach(item -> sb.append(item+ ","));
        return sb.toString();
    }

    public static long calHours(Date exit, Date enter) {
        long diff = exit.getTime() - enter.getTime();
        long  diffHours = (long) Math.ceil(diff / (60 * 60 * 1000) % 24);
        return diffHours;
    }
}
