/* NAME: Drew Inman
 * DATE: 06/06/2019
 * PROJECT: Programming Assignment 1
 * 
 * This is the driver class that we aren't supposed to modify at all. I didn't.
 */
public class Driver {
	
	public static void main(String[] args) {
		
		int totalMM = 100000;
        int numberOfThreads = 2;
		
        Queue<Integer> collection = new Queue<Integer>();
        
        for(int index = 0; index < totalMM; index++) {
            int candidate = ((int) (Math.random() * 10000)) % 5;
            Integer integ = new Integer(candidate);
            collection.enqueue(integ);
        }
        
        RequestProcessor processor = new RequestProcessor(collection);
        Thread[] threads = new Thread[numberOfThreads];
        
        for(int index = 0; index < threads.length; index++) {
            threads[index] = new Thread(processor);
            threads[index].start();
        }
        
        try {
            for(int index = 0; index < threads.length; index++) {
                threads[index].join();
            }
        } catch(InterruptedException ie) {
            ie.printStackTrace();
        }
        
        ThreadStatisticsSetup.print();
    }
}
