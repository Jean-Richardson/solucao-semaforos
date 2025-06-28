import java.util.concurrent.Semaphore;

public class Semaforo{

    private int counter = 0;

   
    private final Semaphore semaphore = new Semaphore(1);

 
    public void increment() {
        try {
           
            semaphore.acquire();

          
            counter++;
            System.out.printf("%s incrementou: %d%n", Thread.currentThread().getName(), counter);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            
            semaphore.release();
        }
    }

    public void decrement() {
        try {
            semaphore.acquire();

            counter--;
            System.out.printf("%s decrementou: %d%n", Thread.currentThread().getName(), counter);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }

   