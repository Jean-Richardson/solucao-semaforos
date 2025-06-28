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

     public static void main(String[] args) {
        Semaforo example = new Semaforo();

        Thread incrementer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.increment();
                sleepRandom();
            }
        }, "Incrementer");

        Thread decrementer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.decrement();
                sleepRandom();
            }
        }, "Decrementer");

        incrementer.start();
        decrementer.start();
    }

    private static void sleepRandom() {
        try {
            Thread.sleep((long)(Math.random() * 500));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
 }
}
}

   