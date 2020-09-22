package service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LotServiceImplTest {

    @Test
    void registerCarInLot() {
        LotService lotservice = new LotServiceImpl();
        String actualValue = lotservice.registerCarInLot("A2,str","ent");
        String expectValue = "Operation is NOT valid!";
        Assert.assertEquals (actualValue,expectValue);
    }
}


