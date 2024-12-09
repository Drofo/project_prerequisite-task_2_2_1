package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {

      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Toyota", 123);
      Car car2 = new Car("BMW", 456);
      Car car3 = new Car("Audi", 789);
      Car car4 = new Car("Ford", 145);

      userService.addUserWithCar(new User("User1", "Lastname1", "user1@mail"), car1);
      userService.addUserWithCar(new User("User2", "Lastname2", "user2@mail"), car2);
      userService.addUserWithCar(new User("User3", "Lastname3", "user3@mail"), car3);
      userService.addUserWithCar(new User("User4", "Lastname4", "user4@mail"), car4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }

      String model = "Toyota";
      int series = 123;
      User userByCar = userService.getUserByCar(model, series);
      System.out.println("User with car (" + model + ", " + series + "):");
      System.out.println("Id = " + userByCar.getId());
      System.out.println("First Name = " + userByCar.getFirstName());
      System.out.println("Last Name = " + userByCar.getLastName());
      System.out.println("Email = " + userByCar.getEmail());

      context.close();
   }
}

