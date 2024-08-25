package com.ms.rentalCarSystem;
public class RCSEntry {
    public static void main(String[] args) {
        Car teslaCar = new Car();
        teslaCar.setBrand("Tesla");
        teslaCar.setCarId("CAR-1");
        teslaCar.setModel("T-Auto");
        teslaCar.setNoOfAvailableCar(1);
        teslaCar.setPricePerDay(5000);

        Car nissanCar = new Car();
        nissanCar.setCarId("CAR-2");
        nissanCar.setBrand("Nissan");
        nissanCar.setModel("V10");
        nissanCar.setNoOfAvailableCar(2);
        nissanCar.setPricePerDay(4000);

        Car landOverCar = new Car();
        landOverCar.setCarId("CAR-3");
        landOverCar.setBrand("Defender");
        landOverCar.setModel("DF7");
        landOverCar.setNoOfAvailableCar(3);
        landOverCar.setPricePerDay(3500);

        CarRentalService carRentalService = new CarRentalService();
        carRentalService.addCars(teslaCar);
        carRentalService.addCars(nissanCar);
        carRentalService.addCars(landOverCar);

        carRentalService.option();

    }
}
