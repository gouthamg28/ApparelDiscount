package apparel.discount.model;

public class CategoryImpl implements ICategory {

	String parentName;
	String categoryName;
	int discount;
	
	public CategoryImpl(String parentName, String categoryName, int discount) {
		this.parentName = parentName;
		this.categoryName = categoryName;
		this.discount = discount;
	}
	
	@Override
	public String getParentName() {
		return parentName;
	}

	@Override
	public String getCategoryName() {
		return categoryName;
	}	
	
	@Override
	public int getDiscount() {
		return discount;
	}

	@Override
	public void setDiscount(int discount) {
		this.discount = discount;
	}
}
