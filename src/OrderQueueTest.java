import org.junit.Test;
import static org.junit.Assert.*;

public class OrderQueueTest {
    @Test
    public void testConstructor(){
        OrderQueue queue = new OrderQueue();
        assertEquals(0, queue.getNumberOfOrders());

    }
}
