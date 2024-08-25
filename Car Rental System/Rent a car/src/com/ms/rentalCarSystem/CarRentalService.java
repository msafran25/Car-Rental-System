package com.ms.rentalCarSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CarRentalService {
    private List<Car> cars;
    private List<Customer> customers;
    private List<BookedCarInformation> bookedCarInformations;

    public CarRentalService(){
         cars = new ArrayList<>();
         customers = new ArrayList<>();
         bookedCarInformations = new ArrayList<>();    
    }

    public void addCars(Car car){
        cars.add(car);
    }

    public void bookedCar(Car car, Customer customer, int days){
        if (car.getNoOfAvailableCar()>0){
            car.setNoOfAvailableCar(car.getNoOfAvailableCar() - 1);
            bookedCarInformations.add(new BookedCarInformation(car,customer,days));
        } else {
            System.out.println("car is not available ");
        }
    }

    public void returnCar(Car car, BookedCarInformation bookedCarInformation){
        car.setNoOfAvailableCar(car.getNoOfAvailableCar() + 1);
        bookedCarInformations.remove(bookedCarInformation);
    }

    public void addCustomer(Customer custname){
       customers.add(custname);
    }

    public void option(){
      while (true){
          Scanner sc = new Scanner(System.in);
          System.out.println("==== Welcome to Our Car Rental System ====");
          System.out.println("1. Rent a Car");
          System.out.println("2. Return a Car");
          System.out.println("3. Available Car");
          System.out.println("4. Exit");

          System.out.println("Enter You Choice: ");
          int choice = sc.nextInt();
          sc.nextLine();

          if (choice == 1){
              System.out.println("== For Renting a Car please provide below details==");
              System.out.println("Enter your name");

              String custName = sc.nextLine();

              System.out.println("Enter a Car ID you want to rent");
              String carId = sc.nextLine();

              System.out.println("Enter number of day for rental");
              int days = sc.nextInt();

              Customer customer =new Customer("CUSTOMER "+(customers.size()+1), custName);
              addCustomer(customer);

              Optional<Car> optionalCar = cars.stream().filter(c->c.getCarId().equalsIgnoreCase(carId)
              && c.getNoOfAvailableCar()>0).findAny();

              if (optionalCar.isEmpty()){
                  System.out.println("car is not available. Please try to book another car.");
                  option();
                  return;
              }
              Car selectedCar = optionalCar.get();

              System.out.println("== Bill Receipt ==");
              System.out.println("Customer Name: " + customer.getId());
              System.out.println("Customer ID: " + customer.getName());
              System.out.println("Car Brand: " + selectedCar.getBrand()+ " Model: " + selectedCar.getModel());
              System.out.println("Rental Days: " + days);
              System.out.println("Total price: " + selectedCar.calculatePrice(days));

              System.out.println("Confirm rental (Y/N): ");
              String confirmation = sc.next();

              if (confirmation.equalsIgnoreCase("Y")){
                  bookedCar(selectedCar,customer,days);
                  System.out.println("Car Booking is done successfully.");
              } else{
                  System.out.println("Car booking is canceled.");
              }
          } else if (choice == 2) {
              System.out.println("== Return a Car ==");
              System.out.println("Enter the car ID you want to return: ");
              String carId = sc.nextLine();

              Optional<Car> optionalCar = cars.stream().filter(c ->c.getCarId().equalsIgnoreCase(carId)).findAny();

              if (optionalCar.isEmpty()){
                  System.out.println("please provide valid car details.");
                  option();
                  return;
              }

              Car carToReturn = optionalCar.get();

              BookedCarInformation bookedCarInformation = bookedCarInformations.stream()
                      .filter(c->c.getCar() == carToReturn).findAny().orElse(null);

              if (bookedCarInformation == null){
                  System.out.println("Car information not available please provide valid details.");
                  option();
                  return;
              }

              Customer cust = bookedCarInformation.getCustomer();
              returnCar(carToReturn,bookedCarInformation);
              System.out.println("Car returned successfully by " + cust.getName());
          } else if (choice == 3) {
              System.out.println("== Available Cars ==");
              cars.stream().filter(c->c.getNoOfAvailableCar() > 0).
              forEach(car -> System.out.println(car.getCarId()+ " " + car.getBrand() + " " + car.getModel() + " " +
              " " + car.getNoOfAvailableCar()));
          } else if (choice == 4) {
              System.out.println("Thank you for choosing us");
              break;
          } else {
              System.out.println("Please provide valid option");
          }
      }
    }
}
