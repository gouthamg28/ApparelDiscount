package apparel.discount.scanner;

import java.util.Scanner;

import apparel.discount.Logger;
import apparel.discount.repository.DataRepository;

public class InventoryScanner implements IScanner {

	@Override
	public void scan(Scanner inScanner) {
		System.out.println("Please enter no. of items in inventory");
		int numItems = 0;
		while(true){
			try	{ 
				numItems = Integer.parseInt(inScanner.nextLine());
				break;
			} catch (NumberFormatException e)	{
				System.out.println("Please enter a valid number of items in inventory");		
			}
		}
		for (int i = 0; i < numItems; i++) {
			try	{
				System.out.println("------------------------------------------------");
				System.out.println("Please enter inventory details for item " + (i + 1)+" as comma separated ex:- 1, Arrow,Shirts,800");
				String itemDetails = inScanner.nextLine();
				String[] detailsArray = itemDetails.split(",");
				if(detailsArray.length != 4)	{
					System.out.println(itemDetails + " is not in expected format so ignoring");
					continue;
				}
				int slNum = Integer.parseInt(detailsArray[0]);
				String brandName = detailsArray[1];
				String categoryName = detailsArray[2];
				int price = Integer.parseInt(detailsArray[3]);
				int brandWiseDiscount = DataRepository.getBrandDiscount(brandName.trim());
				int categoryWiseDiscount = DataRepository.getCategoryDiscount(categoryName.trim());
				int maxDiscount = Integer.max(brandWiseDiscount, categoryWiseDiscount);
				int discountedPrice = price - (price / 100 * maxDiscount);
				DataRepository.addInventoryEntry(slNum, discountedPrice);
			} catch(NumberFormatException e)	{
				System.out.println("input has invalid number so ignoring");
				continue;
			}
		}
		System.out.println("------------------------------------------------");
	}
}
