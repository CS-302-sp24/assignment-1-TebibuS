public class DiningPhilosophers {

  public static void main(String[] args) {
      if (args.length < 5) {
          System.out.println("Usage: java DiningPhilosophers np nc tt et rl");
          return;
      }

      int np = Integer.parseInt(args[0]);
      int nc = Integer.parseInt(args[1]);
      int tt = Integer.parseInt(args[2]);
      int et = Integer.parseInt(args[3]);
      int rl = Integer.parseInt(args[4]);

      Philosopher[] philosophers = new Philosopher[np];
      Chopstick[] chopsticks = new Chopstick[np];

      for (int i = 0; i < np; i++) {
          chopsticks[i] = new Chopstick(i);
      }

      for (int i = 0; i < np; i++) {
          Chopstick left = chopsticks[i];
          Chopstick right = chopsticks[(i + 1) % np];
          if (rl == 1 && i % 2 == 1) {
              // Swap for odd-numbered philosophers if rl is 1
              Chopstick temp = left;
              left = right;
              right = temp;
          }
          philosophers[i] = new Philosopher(i, left, right, nc, tt, et);
          philosophers[i].start();
      }

      for (Philosopher p : philosophers) {
          try {
              p.join();
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
          }
      }
  }
}
