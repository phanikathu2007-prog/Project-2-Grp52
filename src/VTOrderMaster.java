import java.util.HashMap;
import java.util.Scanner;

public class VTOrderMaster{
    private Scanner scanner;
    private Client client;
    private OrderInfo order;
    private HashMap<String, Food> menu;
    private OrderQueue orderQueue;

public VTOrderMaster(){
    scanner = new Scanner(System.in);
    menu = new HashMap<String, Food>();
    orderQueue = new OrderQueue();

    menu.put("Burger", new Food("Burger", 7.00, 0, 15));
    menu.put("Fries", new Food("Fries", 3.00, 0, 5));

}

    
}