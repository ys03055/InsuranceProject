package entity;
public class SalesPerson extends Manager {

	public SalesPerson(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	
	public SalesPerson clone() {
		return (SalesPerson)super.clone();
	}

	/**
	 * 
	 * @param Client
	 */
	public void ManageClient(Client Client){

	}

	public void ModifyInformation(){

	}

	/**
	 * 
	 * @param InsuranceProducts
	 */
	public void ProvideInsuranceProducts(InsuranceProducts InsuranceProducts){

	}

	/**
	 * 
	 * @param Client
	 */
	public void SignUpInsurance(Client Client){

	}

}