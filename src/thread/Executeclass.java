package thread;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;






public class Executeclass {

	//private static final int MYTHREADS = 4;
	public  void thread(int MYTHREADS,String filename) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
		
		 File inputFile= new File(filename);
	     
				String psPONum;
			
				BufferedReader br=new BufferedReader(new FileReader(inputFile));
				while((psPONum=br.readLine())!=null)
				{
					MyRunnable worker = new MyRunnable(psPONum);

					executor.execute(worker);
					
					
				}
				
		
		
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {
 
		}
		System.out.println("\nFinished all threads");
			
		
	}
	public static class MyRunnable implements Runnable {
		private final String psPONum;
 
		MyRunnable(String psPONum) {
			this.psPONum = psPONum;
		}
 
		@Override
		public void run() {
			 System.out.println(" Create Thread " + Thread.currentThread().getName());
			
			String i =  Thread.currentThread().getName();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
			//	System.out.println(psPONum);
				
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			} 
		}
	}


