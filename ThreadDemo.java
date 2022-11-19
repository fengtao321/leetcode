import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

class NewThread extends Thread {
   Thread thread;

   NewThread() {
      // 创建第二个新线程
      thread = new Thread(this, "Demo Thread");
      System.out.println("Child thread: " + thread);
      thread.start(); // 开始线程
   }

   // 第二个线程入口
   public void run() {
      try {
         for (int i = 5; i > 0; i--) {
            System.out.println("Child Thread: " + i);
            // 暂停线程
            Thread.sleep(1000);
         }
      } catch (InterruptedException e) {
         System.out.println("Child interrupted.");
      }
      System.out.println("Exiting child thread.");
   }
}

public class ThreadDemo {
   public static void main(String args[]) {
      new NewThread(); // 创建一个新线程
      try {
         for (int i = 5; i > 0; i--) {
            System.out.println("Main Thread: " + i);
            Thread.sleep(2000);
         }
      } catch (InterruptedException e) {
         System.out.println("Main thread interrupted.");
      }
      System.out.println("Main thread exiting.");
   }
}

class HelloWorld {
   static int sumTest = 0;

   public static void main(String[] args) {

      int[] widgets = { 1, 2, 3, 4, 5, 6 };

      // ArrayList<Integer> widgets = new ArrayList<>();

      // int sum = Arrays.stream(widgets)
      // .filter(w -> w > 3)
      // .forEach(System.out::println);
      // System.out.println(sumTest);

      Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);

      // Using forEach(Consumer action) to print
      // Character at index 1 in reverse order
      stream
            .filter(w -> w > 3)
            .forEach(HelloWorld::add);

      System.out.println(HelloWorld.sumTest);
   }

   private static void add(int x) {
      sumTest = sumTest + x;
   }
}