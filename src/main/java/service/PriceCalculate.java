package service;

/**
 * calculate Total price based on selected Strategic
 * @param hours total hours that the car has parked inside Parking lot
 * @param hourRare
 * @return Total Price.
 */

public interface PriceCalculate {

    float cal(long hours, float hourRate) ;

    static PriceCalculate calculateHourlyBase() {
        return (hrs, hourRate) -> (hrs *  hourRate) ;
    }

    static PriceCalculate calculateHourlyAndFixBase() {
        return (hrs, hourRate) -> 50 + (hrs *  hourRate) ;
    }


}
