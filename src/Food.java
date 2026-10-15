public class Food {
    private String name;
    private double price;
    private int prepTime;

    public Food(String name, double price, int prepTime)
    {
        if (name == null || name.isEmpty()
            || price < 0 || prepTime < 0)
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

    public int getPrepTime(){
        return prepTime;
    }
}
