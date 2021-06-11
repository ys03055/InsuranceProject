package entity;

import type.CancerType;

public class Cancer extends InsuranceProduct {

	private int limitAge;
	private CancerType guaranteedType;

	public Cancer() {
		//m_CancerHistory.getClientCancerCareer();
		//m_CancerHistory.getFamilyCancerCareer();
	}

	public CancerType getCancerType() {
		return guaranteedType;
	}


	public CancerType getGuaranteedType() {
		return guaranteedType;
	}

	public void setGuaranteedType(CancerType guaranteedType) {
		this.guaranteedType = guaranteedType;
	}

	public int getLimitAge() {
		return limitAge;
	}

	public void setLimitAge(int limitAge) {
		this.limitAge = limitAge;
	}
	
	public Cancer clone() {
		return (Cancer)super.clone();
	}
	
	@Override
	public double calculationRate(Client client) {
		double clientCancerCareerRate = client.getMedicalHistory().getClientCancerCareer().getRate();
		double familyCancerCareerRate = client.getMedicalHistory().getFamilyCancerCareer().getRate();
		return clientCancerCareerRate*familyCancerCareerRate*basicInsurancePremium;
	}

}