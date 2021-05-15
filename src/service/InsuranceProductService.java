package service;
import java.util.ArrayList;
import entity.InsuranceProduct;
import entity.InsuranceProducts;

public interface InsuranceProductService {
	public ArrayList<InsuranceProduct> showAllList();
	public ArrayList<InsuranceProduct> approvalInsuranceProduct();
	public void designInsuranceProduct(InsuranceProducts insuranceProducts);
}