package apparel.discount.scanner;

import java.util.Scanner;

import apparel.discount.Logger;
import apparel.discount.repository.DataRepository;

public class CategoryDiscountScanner implements IScanner {

	@Override
	public void scan(Scanner scanner) {
		String line = null;
		
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			if (line.trim().equals("")) {
				continue;
			}

			Logger.printLog("------------------------------------------------------");
			Logger.printLog("CategoryDiscountScanner.scan() input : " + line);
			String[] strArray = line.split(",");

			String categoryName = null;
			int discount = 0;
			String[] categoryDetails = strArray[strArray.length - 1].split("=");
			if (categoryDetails.length == 2) {
				categoryName = categoryDetails[0];
				discount = Integer.parseInt(categoryDetails[1]);
				if(discount < 0)
					discount = 0;
			} else {
				Logger.printLog("<"+line+">"+ " is not having proper data so skipping");
				continue;// continue with next line
			}
			String parentCategoryName = null;
			if (strArray.length > 1) {
				parentCategoryName = strArray[strArray.length - 2];
			}
			DataRepository.addCategory(parentCategoryName, categoryName, discount);
		}
		Logger.printLog("------------------------------------------------------");
		Logger.printLog("CATEGORY DISCOUNTS SCANNED SUCCESSFULLY");
	}
}
