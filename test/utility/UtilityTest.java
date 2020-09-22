package utility;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    void generateList() {
        List<String> strs = new ArrayList<String>();
        strs.add("a");
        strs.add("b");
        String expectResult = "a,b,";
        String actualResult = Utility.generateList(strs);
        Assert.assertEquals(expectResult, actualResult);
    }

    @Test
    void calHours() {
        StringBuffer stringBuffer = new StringBuffer();
        Date now = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        simpleDateFormat.format(now);

        Date exit = now;
        Date enter = now;
        long expecthrls = 0;
        long actualhrls = Utility.calHours(exit, enter);
        Assert.assertEquals(expecthrls, actualhrls);
    }
}