public class Food {
    private String name;
    private double price;
    private double prepTime;

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

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public double getPrepTime(){
        return prepTime;
    }
}
