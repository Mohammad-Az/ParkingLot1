package service;
/**
 * Implements lot services
 * @version 1.0
 * @since 2020-09-21
 */

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Car;
import model.CarType;
import utility.Utility;

public class LotServiceImpl implements LotService{

    private static final Map<String, Car> cars = new HashMap<>();
    /** Capacity of slots
     */
    private static Integer STA_CAPACITY = 2;
    private static Integer KW20_CAPACITY = 2;
    private static Integer KW50_CAPACITY = 2;
    /** Store each car in relevant slot
     */
    private static final Map<String, Integer> capacities = new HashMap<String, Integer>();
    static {
        capacities.put(CarType.STA.getType(), STA_CAPACITY);
        capacities.put(CarType.KW20.getType(), KW20_CAPACITY);
        capacities.put(CarType.KW50.getType(), KW50_CAPACITY);
    }

    private static final int ID_INDEX = 0;
    private static final int TYPE_INDEX = 1;

    private static final String STOP_KEY = "stop";
    private static final String EXIT = "exit";
    private static final String ENTER = "enter";

    private static final List<String> PARKINGS = Arrays.asList(CarType.STA.getType(),CarType.KW20.getType(), CarType.KW50.getType());
    private static final List<String> OPERATIONS = Arrays.asList("exit","enter");

    public LotServiceImpl ( ) {
    }


    @Override
    public String registerCarInLot(String carData, String operation) {
        if ( null == operation) {
            return "Operation is NOT valid!";
        }
        operation = operation.toLowerCase();
        boolean operationStatus = verifyOperation(operation);
        if ( STOP_KEY.equals(operation) || STOP_KEY.equals(carData.toLowerCase())){
            return STOP_KEY;
        }

        if ( !operationStatus) {
            return "Operation is NOT valid!";
        }
        String[]  carDataStrs = verifyCarData(carData);
        if ( carDataStrs == null) {
            return "Car data is not valid!";
        } else {
            return registerCar(carDataStrs, operation);
        }
    }


    private  String registerCar(String[] carDataStrs, String operation) {
        String id = carDataStrs[ID_INDEX];
        String type = carDataStrs[TYPE_INDEX];
        if ( ENTER.equals(operation)) {
            return registerEnterCar(id, type);
        } else if ( EXIT.equals(operation)) {
            return settleExitCar(id, type);
        } else {
            return "Operation is not valid!";
        }
    }


    private String registerEnterCar(String id, String type) {
        Car car = checkIfCarExists(id);
        if (car != null) {
            return "Car already exist!";
        } else {
            int availableSpace = getAvailableSpace(type);
            if (availableSpace > 0) {
                Date date = new Date(System.currentTimeMillis());
                car = new Car(id, CarType.get(type), date , null);
                settleCapacity(type,ENTER, availableSpace);
                cars.put(id,car);
                return String.format("Welcome!. Time: %s", date.toString()) ;
            } else {
                return "There is no space for your car!";
            }
        }
    }

    private String settleExitCar(String id, String type) {
        Car car = checkIfCarExists(id);
        if ( car == null) {
            return "Car does not exist!";
        } else {
            Date exitDate = new Date(System.currentTimeMillis());
            car.setExitTime(exitDate);
            int availableSpace = getAvailableSpace(type);
            settleCapacity(type,EXIT, availableSpace);
            Float totalPrice = calculatePrice(car);
            cars.remove(id);
            return String.format("Thanks you, See you latet. Price :  %f", totalPrice) ;
        }
    }

    private Float calculatePrice(Car car) {
        long hours = Utility.calHours(car.getExitTime(), car.getEnterTime());
        if ( car.getType().equals(CarType.STA)){
            return PriceCalculate.calculateHourlyBase().cal(hours, 10);

        } else if ( car.getType().equals(CarType.KW20)){
            return PriceCalculate.calculateHourlyBase().cal(hours, 20);
        } else {
            return PriceCalculate.calculateHourlyAndFixBase().cal(hours, 20);
        }
    }


    private void settleCapacity(String type, String operation, int availableSpace) {
        int newValue = 0;
        if ( EXIT.equals(operation)) {
            newValue = availableSpace + 1;
        } else if (ENTER.equals(operation)) {
            newValue = availableSpace - 1;
        }

        capacities.put(type, newValue);
    }

    private Car checkIfCarExists(String id) {
        Car car = cars.get(id);
        return car;
    }


    private int getAvailableSpace(String type) {
        return capacities.get(type);
    }


    private String[] verifyCarData(String carData) {
        if ( null == carData || carData.length() < 1 ) {
            return null;
        }
        String[] strs = carData.split(",");

        if ( strs.length < 2) {
            return null;
        }
        String type = strs[TYPE_INDEX];
        if (!PARKINGS.contains(type)) {
            return null;
        }


        return strs;
    }

    private boolean verifyOperation(String operation ) {
        return OPERATIONS.contains(operation);
    }

}
