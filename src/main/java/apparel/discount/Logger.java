package apparel.discount;

public class Logger {

	private static boolean loggerOn = false;
	
	public static void enableLogs(boolean flag)	{
		loggerOn = flag;
	}
	
	public static void printLog(String message)	{
		if(loggerOn)	
			System.out.println(message);
	}
}
