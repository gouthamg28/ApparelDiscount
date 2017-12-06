package apparel.discount.scanner;

import java.util.Scanner;

public interface IScanner {
	
	public int SCANNER_CATEGORY = 1;
	public int SCANNER_BRAND = 2;
	public int SCANNER_INVENTORY = 3;
	
	public void scan(Scanner scanner);
}
