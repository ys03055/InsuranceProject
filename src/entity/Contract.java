package entity;
import java.util.Calendar;
import java.util.Date;

public class Contract {
	private Client client;
	private Date insuranceContractDate;
	private Date insuranceExpiryDate;
	private InsuranceProduct insuranceProduct;
	private Manager salesPerson;
	private boolean approval;
	private boolean[] month = new boolean[12];

	
	public boolean[] getMonth() {//contract로 옮기는걸 추천.
		return month;
	}
	public void setMonth(boolean[] month) {
		this.month = month;
	}
	public Contract() {
		this.approval = false;
	}

	public void setInsuranceExpiryDate(Date insuranceExpiryDate) {
		this.insuranceExpiryDate = insuranceExpiryDate;
	}
	
	public Date getInsuranceContractDate() {
		return insuranceContractDate;
	}
	
	public void setInsuranceContractDate(Date insuranceContractDate) {
		this.insuranceContractDate = insuranceContractDate;
	}
	
	public Date getInsuranceExpiryDate() {
		return insuranceExpiryDate;
	}
	
	public void setInsuranceExpiryDate(int paymentPeriod) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(this.insuranceContractDate);
		ca.add(Calendar.YEAR, paymentPeriod);
		this.insuranceExpiryDate = ca.getTime()  ;
	}
	
	public boolean isApproval() {
		return approval;
	}
	
	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public InsuranceProduct getInsuranceProduct() {
		return insuranceProduct;
	}

	public void setInsuranceProduct(InsuranceProduct insuranceProduct) {
		this.insuranceProduct = insuranceProduct;
	}

	public Manager getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(Manager salesPerson) {
		this.salesPerson = salesPerson;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String Id() {
		return client.getId();
	}
}