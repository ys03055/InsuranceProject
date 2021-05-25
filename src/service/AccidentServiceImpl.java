package service;

import java.util.ArrayList;
import entity.Accident;
import list.AccidentList;
import list.AccidentListImpl;

public class AccidentServiceImpl implements AccidentService{
	private AccidentList accidentList;
	
	public AccidentServiceImpl() {
		this.accidentList = new AccidentListImpl();
	}
	
	public ArrayList<Accident> showAllAccidentList(){
		return accidentList.getAccidentList();
	}
	
	
	

}
