import java.util.Random;

class Philosopher extends Thread {
    private final Chopstick left, right;
    private final int id, maxCycles, maxThinkingTime, maxEatingTime;
    private final Random random = new Random();

    public Philosopher(int id, Chopstick left, Chopstick right, int maxCycles, int maxThinkingTime, int maxEatingTime) {
        this.id = id;
        this.left = left;
        this.right = right;
        this.maxCycles = maxCycles;
        this.maxThinkingTime = maxThinkingTime;
        this.maxEatingTime = maxEatingTime;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; maxCycles == 0 || i < maxCycles; i++) {
                // Thinking
                int thinkTime = think();
                System.out.println("Philosopher " + id + " thinks for " + thinkTime + " units");
                
                // Trying to pick up chopsticks
                synchronized (left) {
                    System.out.println("Philosopher " + id + " wants left chopstick");
                    System.out.println("Philosopher " + id + " has left chopstick");
                    synchronized (right) {
                        System.out.println("Philosopher " + id + " wants right chopstick");
                        System.out.println("Philosopher " + id + " has right chopstick");

                        // Eating
                        int eatTime = eat();
                        System.out.println("Philosopher " + id + " eats for " + eatTime + " units");
                        System.out.println("Philosopher " + id + " releases right chopstick");
                    }
                    System.out.println("Philosopher " + id + " releases left chopstick");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int think() throws InterruptedException {
        if (maxThinkingTime > 0) {
            int time = random.nextInt(maxThinkingTime) + 1; // Ensure time is always positive
            Thread.sleep(time);
            return time;
        }
        return 0; // No thinking time needed
    }

    private int eat() throws InterruptedException {
        if (maxEatingTime > 0) {
            int time = random.nextInt(maxEatingTime) + 1; // Ensure time is always positive
            Thread.sleep(time);
            return time;
        }
        return 0; // No eating time needed
    }
}
