import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

class FizzBuzz {
    private int n;
    private int counter;
    private Semaphore semaphore;

    public FizzBuzz(int n) {
        this.n = n;
        this.counter = 1;
        this.semaphore = new Semaphore(1, true); // default is false,, true guarantees all thread won't wait too long
    }

    private void outputString(IntConsumer func, Supplier<Boolean> condition) throws InterruptedException {
        while (this.counter <= this.n) {
            semaphore.acquire();
            try {
                if (condition.get()) {
                    func.accept(this.counter);
                    this.counter++;
                }
            } finally {
                semaphore.release();
            }
        }

    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        this.outputString(num -> printFizz.run(), () -> this.counter % 3 == 0 && this.counter % 5 != 0);
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        this.outputString(num -> printBuzz.run(), () -> this.counter % 5 == 0 && this.counter % 3 != 0);
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        this.outputString(num -> printFizzBuzz.run(), () -> this.counter % 5 == 0 && this.counter % 3 == 0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        this.outputString(printNumber, () -> this.counter % 5 != 0 && this.counter % 3 != 0);
    }
}

// review CountDownLatch, ReentrantLock
// CountDownLatch example: https://www.jianshu.com/p/205a61af1205
// Semaphore：
// https://www.cnblogs.com/jiading/articles/12519704.html#:~:text=Semaphore%20%E6%98%AF%E7%94%A8%E6%9D%A5%E4%BF%9D%E6%8A%A4,%E4%B8%BA0%2C%20%E7%BA%BF%E7%A8%8B%E8%BF%9B%E5%85%A5%E4%BC%91%E7%9C%A0%E3%80%82