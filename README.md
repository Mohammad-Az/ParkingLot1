# ParkingLot

## Project Detailing & Use Casesva API :

A toll parking contains multiple parking slots of different types : 
the standard parking slots for sedan cars.
parking slots with 20kw power supply for electric cars.
parking slots with 50kw power supply for electric cars.

20kw electric cars cannot use 50kw power supplies and vice-versa.

Every Parking is free to implement is own pricing policy :

Some only bills their customer for each hour spent in the parking (nb hours * hour price)
Some other bill a fixed amount + each hour spent in the parking (fixed amount + nb hours * hour price) In the future, there will be others pricing policies.
Cars of all types come in and out randomly, the API must :

Send them to the right parking slot of refuse them if there is no slot (of the right type) left.
Mark the parking slot as Free when the car leaves it
Bill the customer when the car leaves.

## How to import:
Launch your IDE (ex:IntelliJ)
If the Welcome screen opens, press Ctrl+Shift+A,
type "project from existing sources", and click the Import project from existing sources action in the popup.

## How to use :
After import, Launch Main.Java and follow below sample scenario, 
```
Please select a valid operation (exit,enter,)
$enter
Please enter Cars data( ex: id, type) - types: sta,kw20,kw50,
$A220,kw20
Welcome!. Time: Wed Sep 23 09:37:03 CEST 2020

Please select a valid operation (exit,enter,)
$enter
Please enter Cars data( ex: id, type) - types: sta,kw20,kw50,
$A230,kw20
Welcome!. Time: Wed Sep 23 09:38:28 CEST 2020

Please enter Cars data( ex: id, type) - types: sta,kw20,kw50,
$A240,kw20
There is no space for your car!

Please select a valid operation (exit,enter,)
$exit
Please enter Cars data( ex: id, type) - types: sta,kw20,kw50,
$A230,kw20
Thanks you, See you latet. Price :  20.000000

```

## Applying new Price policy
To update or add new pricing policy.

```Java
public interface PriceCalculate {

    float cal(long hours, float hourRate) ;

    static PriceCalculate calculateHourlyBase() {
        return (hrs, hourRate) -> (hrs *  hourRate) ;
    }

    static PriceCalculate calculateHourlyAndFixBase() {
        return (hrs, hourRate) -> 50 + (hrs *  hourRate) ;
    }
}
```
