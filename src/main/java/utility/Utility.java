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
        float diff = exit.getTime() - enter.getTime();
        long  diffHours = (long)  Math.ceil(diff / (60.0 * 60.0 * 1000.0) % 24.0);
        return diffHours;
    }
}
