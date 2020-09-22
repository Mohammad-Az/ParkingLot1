package controller;
import model.CarType;
import service.LotService;
import service.LotServiceImpl;
import utility.Utility;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {


    private static final List<String > OPERATIONS = Arrays.asList("exit","enter");
    private static final List<String > types = Arrays.asList(CarType.STA.getType(),
            CarType.KW20.getType(),
            CarType.KW50.getType());

    private static final String STOP_KEY = "stop";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LotService ls = new LotServiceImpl();
        try{
            while (true ) {
                //Type of Operation ()
                System.out.println(String.format("Please select a valid operation (%s)", Utility.generateList(OPERATIONS)));
                String operation = in.next();

                String carData = "";
                System.out.println(String.format("Please enter Cars data( ex: id, type) - types: %s", Utility.generateList(types)));
                carData = in.next();
                String status = ls.registerCarInLot(carData, operation);
                if (status.equals(STOP_KEY)) {
                    break;
                }else {
                    System.out.println(status);
                }

            }

        } catch( Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            if (in != null) {
                in.close();
            }
        }
    }

}