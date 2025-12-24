package tricentis.demowebshop.utils;

public class TimeUtils {
	
	protected final static int SHORT_TIME = 5000;
	protected final static int MEDIUM_TIME = 8000;
	protected final static int LONG_TIME = 15000;
	protected final static int LONGER_TIME = 20000;
	
	
	public static void shortWait() {
		try {
			Thread.sleep(SHORT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	}
	
	public static void mediumWait() {
		try {
			Thread.sleep(MEDIUM_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	}
	
	
	public static void longWait() {
		try {
			Thread.sleep(LONG_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	}
	
	public static void longerWait() {
		try {
			Thread.sleep(LONGER_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	}
	
	

}
