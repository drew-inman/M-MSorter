/* NAME: Drew Inman
 * DATE: 06/06/2019
 * PROJECT: Programming Assignment 1
 * 
 * This is the RequestProcessor. This has the lock and queue and other fun
 * stuff!
 */
import java.util.concurrent.locks.ReentrantLock;

public class RequestProcessor implements Runnable {
    private Queue<Integer> mmQueue = new Queue<>();
    ReentrantLock lock = new ReentrantLock();
    
    public RequestProcessor(Queue<Integer> queue) {
        mmQueue = queue;
        
    }

    //This is the run method that is run when we call RequestProcessor in
    //the driver
    public void run() {
        ThreadStatisticsSetup tss = null;
        lock.lock();
        try {
        	tss = ThreadStatisticsSetup.create((int)Thread.currentThread().getId());
        } catch(Exception e) {
        	e.printStackTrace();
        } finally {
        	lock.unlock();
        }
        while (true) {
        	lock.lock();
            try {
            	if(!mmQueue.isEmpty()) {
	            	Integer temp = mmQueue.dequeue();
	                tss.setColorStorageAmount(temp, tss.getColorStorageAmount(temp) + 1);
            	} else {
                	break;
            	}
            } catch(Exception e) {
            	e.printStackTrace();
            } finally {
            	lock.unlock();
            }
        
        }
    }
}
