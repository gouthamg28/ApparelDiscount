package apparel.discount;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import apparel.discount.repository.DataRepository;
import apparel.discount.scanner.BrandDiscountScanner;
import apparel.discount.scanner.CategoryDiscountScanner;
import apparel.discount.scanner.IScanner;
import apparel.discount.scanner.InventoryScanner;
import apparel.discount.scanner.ScannerFactory;

public class DiscountAppMain {

	public static void main(String[] args) {
		Logger.enableLogs(true);
		if (args.length != 2) {
			System.err.println(
					"Usage: java Discount <path of the category discounts textfile> <path of the brand discounts textfile>");
			System.exit(-1);
		}
		String catDiscountFile = args[0];
		String brandDiscountFile = args[1];
		Logger.printLog("Path of category discounts file is: " + catDiscountFile);
		Logger.printLog("Path of brand discounts file is: " + brandDiscountFile);

		try {
			
			//STEP-1 : Scanning category wise discounts 
			scanCategoryDetails(catDiscountFile);
			Logger.printLog("");
			
			//STEP-2 : Scanning brand discount
			scanBrandDetails(brandDiscountFile);
			Logger.printLog("");
			
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException " + e.getMessage());
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("IOException " + e.getMessage());
			System.exit(-1);
		}
		
		//STEP-3 : Scanning inventory details from console
		Scanner inScanner = new Scanner(System.in);
		InventoryScanner inventory = new InventoryScanner();
		inventory.scan(inScanner);
		
		//STEP-4 : Calculating price based on customer purchase choices 
		calculatePrice(inScanner);
	}

	private static void calculatePrice(Scanner inScanner) {
		System.out.println("Please enter number of inputs of customer choices for ex:- 2");
		int inputs = 0;
		while(true){
			try	{ 
				inputs = Integer.parseInt(inScanner.nextLine());
				break;
			} catch (NumberFormatException e)	{
				System.out.println("Please enter number a valid number of inputs");		
			}
		}
		
		int[] output = new int[inputs];
		for(int j=0; j<inputs; j++)	{
			System.out.println("Please enter choices for input "+(j+1)+" as comma saperated string");
			String inputStr = inScanner.nextLine();
			String[] choiceArray = inputStr.split(",");
			int totalPrice = 0;
			for(String choiceStr : choiceArray)	{
				int choice = Integer.parseInt(choiceStr);
				totalPrice += DataRepository.getInventoryPrice(choice);
			}
			output[j] = totalPrice;
		}
		
		System.out.println("========================OUTPUT START====================");
		
		for(int k=0; k<inputs; k++)	{
			System.out.println(output[k]);
		}
		System.out.println("========================OUTPUT END====================");
	}

	private static void scanBrandDetails(String brandDiscountFile) throws FileNotFoundException {
		Scanner brandInput = new Scanner(new File(brandDiscountFile));
		IScanner brandScanner = ScannerFactory.getScanner(IScanner.SCANNER_BRAND);
		brandScanner.scan(brandInput);
	}

	private static void scanCategoryDetails(String categoryDiscountFile) throws FileNotFoundException {
		Scanner categoryInput = new Scanner(new File(categoryDiscountFile));
		IScanner categoryScanner = ScannerFactory.getScanner(IScanner.SCANNER_CATEGORY);
		categoryScanner.scan(categoryInput);
	}
}
