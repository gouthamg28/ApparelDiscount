package apparel.discount.scanner;

import java.util.Scanner;

import apparel.discount.Logger;
import apparel.discount.repository.DataRepository;

public class BrandDiscountScanner implements IScanner {

	@Override
	public void scan(Scanner scanner) {
		String line = null;

		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			if (line.trim().equals("")) {
				continue;
			}
			Logger.printLog("------------------------------------------------------");
			
			String[] details = line.split("=");
			if (details.length != 2)	{
				Logger.printLog("<"+line+">" + " is not having proper data so skipping");
				continue;
			}

			Integer discount = null;
			try {
				discount = Integer.parseInt(details[1]);
				if(discount < 0)
					discount = 0;
			} catch (NumberFormatException e) {
				discount = 0;
			}
			DataRepository.addBrand(details[0], discount);
		}
		Logger.printLog("------------------------------------------------------");
		Logger.printLog("BRAND WISE DISCOUNTS SCANNED SUCCESSFULLY");
	}
}
