    /**
    * Class that holds attributes for the food that will be cooked
    */
public class Food {
    private String name;
    private double price;
    private double prepTime;
    /**
    * @param name
    *    name of the object
    * @param price
    *     price of the food
    * @param prepTime
    *     amount of time it takes to make
    */
    public Food(String name, double price, double prepTime)
    {
        if (name == null || name.isEmpty()
            || price < 0.00 || prepTime < 0.00)
        {
            throw new IllegalArgumentException(
                "Invalid food information.");
        }
        this.name = name;
        this.price = price;
        this.prepTime = prepTime;
    }
    /**
    * @Return
    *     returns name of the food
    */
    public String getName(){
        return name;
    }
    /**
    * @Return
    *    returns price of the food
    */
    public double getPrice(){
        return price;
    }
    /**
    * @Return
    *    returns the amount of time that the food takes to cook
    */
    public double getPrepTime(){
        return prepTime;
    }
}
