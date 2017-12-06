package apparel.discount.model;

public interface ICategory {

	public String getParentName();
	public String getCategoryName();
	public int getDiscount();
	public void setDiscount(int discount);
}
