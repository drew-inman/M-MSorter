/* NAME: Drew Inman
 * DATE: 06/06/2019
 * PROJECT: Programming Assignment 1
 * 
 * This is the ThreadStatisticsSetup class that queues data to be printed
 * to the console. The results end up looking like the results from the 
 * assignment requirements sheet.
 */

public class ThreadStatisticsSetup {
	static Queue<ThreadStatisticsSetup> threadData = new Queue<>();
	int[] colorStorage = new int[5];
	private int threadID;
	
	public ThreadStatisticsSetup(int threadID) {
		this.threadID = threadID;
	}

	//This method creates a new ThreadStatisticsSetup, and enqueues it
	//into a queue.
	static public ThreadStatisticsSetup create(int threadID){
		ThreadStatisticsSetup tss = new ThreadStatisticsSetup(threadID);
		threadData.enqueue(tss);
		return tss;
	}
	//This method return colorStorage data at whatever index
	//So, whatever number of MMs from some index
    public int getColorStorageAmount(int index) {
        return colorStorage[index];
    }

    //This method stores the amount of MMs in the corresponding array index
    public void setColorStorageAmount(int index, int value) {
        colorStorage[index] = value;
    }
    
    //This is the print method that is called in the driver. It prints out the
    //amount of MMs counted per thread and then the total percentages of MMs
    //colors.
	public static void print() {
		int[] totalColors = new int[5];
		int total = 0;
		
		while(!threadData.isEmpty()) {
			ThreadStatisticsSetup tss = threadData.dequeue();
			System.out.println(tss);
			
			for(int i=0;i<totalColors.length;i++) {
				 totalColors[i] += tss.getColorStorageAmount(i);
				 total += tss.getColorStorageAmount(i);
			}
		}
		System.out.println("===================TOTALS===================");
		System.out.printf("There are %s in total\n", (int)total);
		for(int i=0;i<totalColors.length;i++) {
			double percentage = (100*((double)totalColors[i]/(double)total));
			System.out.printf("%s makes up %d of the total =~ %.2f%%\n",colorForNumber(i), totalColors[i], percentage);
		}
	}
	@Override
	//This structures the print statements so when the print() method calls them,
	//they're ready to go.
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int total = 0;
		for(int i=0;i<colorStorage.length;i++) {
			 total += colorStorage[i];
		}
		for(int i=0;i<colorStorage.length;i++) {
			int colorAmount = colorStorage[i];
			double percentage = (100*(colorAmount/(double)total));
			sb.append("Thread " + threadID + " counted " + colorAmount + 
				" for color " + colorForNumber(i) + " =~ " + String.format("%.2f", percentage) + "%");
			 sb.append("\n");
		}
		return sb.toString();
	}
	
	//This method converts a number to a color according to the assignment description.
	static private String colorForNumber(int a) {
		switch(a) {
		case 0:
			return "Red";
		case 1:
			return "Brown";
		case 2:
			return "Yellow";
		case 3:
			return "Green";
		case 4:
			return "Blue";
		default:
			return null;
		}
	}
}
