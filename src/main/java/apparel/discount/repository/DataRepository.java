package apparel.discount.repository;

import java.util.HashMap;

import apparel.discount.Logger;
import apparel.discount.model.CategoryImpl;
import apparel.discount.model.ICategory;

public class DataRepository {

	private static HashMap<String, ICategory> categoryMap = new HashMap<String, ICategory>();
	private static HashMap<String, Integer> brandMap = new HashMap<String, Integer>();
	private static HashMap<Integer, Integer> inventoryMap = new HashMap<Integer, Integer>();
	private static String ALREADY_EXIST = " already exists in repository so ignoring";

	public static void addCategory(String parentName, String categoryName, int discount) {
		Logger.printLog("DataRepository.addCategory() parentName : " + parentName);
		Logger.printLog("DataRepository.addCategory() category : " + categoryName);
		Logger.printLog("DataRepository.addCategory() original discount : " + discount);

		ICategory parentCategory = categoryMap.get(parentName);
		if (parentCategory != null) {
			discount = Integer.max(parentCategory.getDiscount(), discount);
		}
		Logger.printLog("DataRepository.addCategory() updated discount : " + discount);
		CategoryImpl categoryImpl = new CategoryImpl(parentName, categoryName, discount);
		if(!categoryMap.containsKey(categoryName))
			categoryMap.put(categoryName, categoryImpl);
		else
			Logger.printLog(categoryName + ALREADY_EXIST);
	}

	public static void addBrand(String brandName, Integer discount) {
		Logger.printLog("DataRepository.addBrand() brandname : " + brandName);
		Logger.printLog("DataRepository.addBrand() discount : " + discount);
		if(!brandMap.containsKey(brandName))	
			brandMap.put(brandName, discount);
		else
			Logger.printLog(brandName + ALREADY_EXIST);
	}

	public static void addInventoryEntry(int slNum, Integer discountedPrice) {
		Logger.printLog("DataRepository.addInventoryEntry() slNum : " + slNum);
		Logger.printLog("DataRepository.addInventoryEntry() discountedPrice : " + discountedPrice);
		if(!inventoryMap.containsKey(slNum))
			inventoryMap.put(slNum, discountedPrice);
		else
			Logger.printLog(slNum + ALREADY_EXIST);
	}

	public static int getCategoryDiscount(String item) {
		return (categoryMap.get(item) != null) ? categoryMap.get(item).getDiscount() : 0;
	}

	public static int getBrandDiscount(String item) {
		return (brandMap.get(item) != null) ? brandMap.get(item) : 0;
	}

	public static int getInventoryPrice(int slNum) {
		return (inventoryMap.get(slNum) != null) ? inventoryMap.get(slNum) : 0;
	}
}
