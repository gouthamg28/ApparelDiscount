package apparel.discount.scanner;

public class ScannerFactory {
	
	public static IScanner getScanner(int scanId) {
		IScanner scanner = null;
		switch(scanId)	{
		case IScanner.SCANNER_CATEGORY:
			scanner = new CategoryDiscountScanner();
			break;
		case IScanner.SCANNER_BRAND:
			scanner = new BrandDiscountScanner();
			break;
		case IScanner.SCANNER_INVENTORY:
			scanner = new InventoryScanner();
			break;
		}
		return scanner;
	}
}
