package controller;

import java.util.ArrayList;
import java.util.Scanner;

import entity.ActualExpense;
import entity.Cancer;
import entity.Client;
import entity.CompensationHandle;
import entity.Contract;
import entity.InsuranceProduct;
import entity.InsuranceProducts;
import entity.InsuranceProductsAcceptance;
import entity.Life;
import entity.Manager;
import entity.Pension;
import service.ClientServiceImpl;
import service.InsuranceProductServiceImpl;
import service.ManagerServiceImpl;

public class ConsoleController {
	private Scanner sc;
	private ClientServiceImpl clientService;
	private InsuranceProductServiceImpl insuranceProductService;
	private ManagerServiceImpl managerService;
	private Manager managerLogin;
	private Client clientLogin;
	private InsuranceProduct insuranceProduct;

	public ConsoleController() {
		this.sc = new Scanner(System.in);
		this.clientService = new ClientServiceImpl();
		this.insuranceProductService = new InsuranceProductServiceImpl();
		this.managerService = new ManagerServiceImpl();
		this.insuranceProduct = new InsuranceProduct();
		this.managerLogin = null;
		this.clientLogin = null;
	}

	public void run() {
		this.mainMenu();
	}

	private void mainMenu() {
		while (true) {
			System.out.println("\n---MainMenu---");
			System.out.println("1.관리자");
			System.out.println("2.회원");
			System.out.println("3.보험");
			System.out.println("4.끝내기");
			switch (sc.nextInt()) {
			case 1:
				managerMenu();
				break;
			case 2:
				clientMenu();
				break;
			case 3:
				if (insuranceProductService.showAllList().isEmpty()) {//초기화면에서 보험 목록보여주는 페이지
					System.out.println("---현재 상품 준비중입니다.---");
					return;
				} else {
					basicInsuranceMenu();
					return;
				}
			case 4:
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
				;
			}
		}
	}

	private void managerMenu() {// ManagerMenus
		while (true) {
			System.out.println("\n---ManagerMenu---");
			System.out.println("1.관리자 등록");
			System.out.println("2.관리자 로그인");
			System.out.println("3.관리자 삭제");
			System.out.println("4.돌아가기");
			switch (sc.nextInt()) {
			case 1:
				System.out.println(managerService.register() ? "등록이 완료되었습니다." : "등록에 실패하였습니다.");
				break;
			case 2:
				if (managerLogin == null)
					managerLogin = managerService.login();
				if (managerLogin != null)
					managerWorkMenu();
				else
					System.out.println("등록되지 않은 매니저입니다.");
				break;
			case 3:
				System.out.println(managerService.delete() ? "삭제가 완료되었습니다." : "삭제에 실패하였습니다.");
				break;
			case 4:
				return;
			default:
				System.out.println("잘못된 값을 입력하셨습니다.");
				break;
			}
		}
	}

	private void managerWorkMenu() {
		switch (managerLogin.getJobPosition()) {
		case IP:
			insuranceProductsMenu();
			break;
		case IPA:
			insuranceProductsAcceptanceMenu();
			break;
		case UW:
			underWriterMenu();
			break;
		case CM:
			contractManagerMenu();
			break;
		case CH:
			compensationHandleMenu();
			break;
		case SP:
			salesPersonMenu();
			break;
		}
	}

	private void insuranceProductsMenu() {// IP
		InsuranceProducts ip = (InsuranceProducts) managerLogin;
		while (true) {
			System.out.println("\n---InsuranceProductsMenu---");
			System.out.println("1.보험상품 설계");
			System.out.println("2.사후관리");
			System.out.println("3.로그아웃");
			switch (sc.nextInt()) {
			case 1:
				InsuranceProduct developedProduct = ip.designInsurance().developInsurance();
				insuranceProductService.add(developedProduct);
				break;
			case 2:
				followUpInsurance(insuranceProduct);
				break;
			case 3:
				managerLogin = null;
				return;
			}
		}
	}
	private void insuranceProductsAcceptanceMenu(){
		InsuranceProductsAcceptance ipa = (InsuranceProductsAcceptance) managerLogin;
		while (true) {
			System.out.println("---insuranceProductsAcceptanceMenu---");
			System.out.println("1.승인할 보험 선택하기 2.승인된 보험 삭제 3.로그아웃");
			switch (sc.nextInt()) {
			case 1:
				if (insuranceProductService.showAllList().isEmpty()) {
					System.out.println("현재 만들어진 보험이 없습니다.");
					return;
				} else
				insuranceMenu();
				approvalMenu();
				break;
			case 2:
				if (insuranceProductService.approvalInsuranceProduct().isEmpty()) {
					System.out.println("현재 승인된 보험이 없습니다.");
					return;
				}else 
					System.out.println("--현재 승인된 보험 목록입니다.--");
					insuranceProductService.approvalInsuranceProduct();
					approvalInsuranceDelete();
				break;
			case 3:
				managerLogin = null;
				return;
			}
		}
	}
	private void approvalMenu() {
		System.out.println("\n--승인할 보험의 번호를 입력해주세요.--");
		int a = sc.nextInt();
		InsuranceProduct aip = insuranceProductService.showAllList().get(a-1);
		switch (aip.getInsuranceProductType()) {
		case ACTUALEXPENSE:
			System.out.println("상품명: " + aip.getProductName());
			break;
		case CANCER:
			System.out.println();
			break;
		case LIFE:
			System.out.println();
			break;
		case PENSION:
			System.out.println();
			break;
		default:
			break;
		}
		System.out.println("\n1.보험승인 2.보험승인거절 3.돌아가기");
		int input = sc.nextInt();
		switch (input) {
		case 1:
			aip.setApproval(true);
			insuranceProductService.showAllList().remove(aip);
			insuranceProductService.approvalInsuranceProduct().add(aip);
			//insuranceProduct.setApproval(true);
			System.out.println("승인이 완료되었습니다.");
			return;
		case 2:
			insuranceProductService.showAllList().remove(aip);
			System.out.println("승인이 거절 되었습니다. 목록에서 삭제합니다.");
			return;
		case 3:
			return;
		}
	}
	
	private void approvalInsuranceDelete() {
		System.out.println("--삭제할 보험을 선택해주세요.--");
		int a = sc.nextInt();
		
		System.out.println("1.삭제하기 2.돌아가기");
		switch(sc.nextInt()) {
		case 1:
			insuranceProductService.approvalInsuranceProduct().remove(a-1);
			break;
		case 2:
			return;
		}
	}

	public void followUpInsurance(InsuranceProduct InsuranceProduct) {
		System.out.println("보험목록에서 사후관리할 보험을 선택해주세요.");
		insuranceMenu();
		sc.nextInt();
		System.out.println("1.기본보험료 관리 2.납입기간 관리 3.납입주기 관리");
	}

	private void clientMenu() {// clientMenus
		while (true) {
			System.out.println("\n---ClientMenu---");
			System.out.println("1.회원가입");
			System.out.println("2.회원 로그인");
			System.out.println("3.회원 탈퇴");
			System.out.println("4.돌아가기");
			switch (sc.nextInt()) {
			case 1:
				clientService.register();
				break;
			case 2:
				if (clientLogin == null)
					clientLogin = clientService.login();
				if (clientLogin != null)
					clientWorkMenu();
				else
					System.out.println("입력한 정보가 잘못되었습니다.");
				break;
			case 3:
				clientService.delete();
				break;
			case 4:
				return;
			}
		}
	}

	private void clientWorkMenu() {
		System.out.println("1.모든 보험 조회하기 2.가입한 보험 조회하기 3.로그아웃");
		switch (sc.nextInt()) {
		case 1:
			basicInsuranceMenu();
		case 2:
			
		case 3:
			clientLogin = null;
			return;
		}
	}
	
	private void underWriterMenu() {
//		UW uw = (UW)managerLogin;
		while(true) {
			System.out.println("\n---UWMenu---");
			System.out.println("1.인수심사하기");
			System.out.println("2.로그아웃");
			switch(sc.nextInt()) {
			case 1:
				this.underwriteClient(this.selectUnderWriteContract());
				break;
			case 2:
				managerLogin = null;
				return;
			}
		}
	}
	
	private void underwriteClient(Contract contract){
		if(contract != null) {
			System.out.println("해당 계약을 승인하시겠습니까? (1. 승인하기, 2. 승인거절)");
			switch(sc.nextInt()) {
			case 1:
				contract.setApproval(true);
				System.out.println("승인이 완료되었습니다.");
				break;
			case 2:
				System.out.println("승인을 거절하였습니다.");
				break;
			}
		}
	}
	
	private Contract selectUnderWriteContract() {
		ArrayList<Contract> contractList = contractService.selectNotApproval();
		if(contractList.size()>0) {
			System.out.println("[인수심사 계약 목록]");
			for(int i = 0; i < contractList.size(); i++)
				System.out.println(String.format("%d.%5s%10s", i+1, contractList.get(i).getClientID(), contractList.get(i).getProductName()));
			System.out.println("인수심사할 계약의 번호를 입력해주세요.");
			int input = sc.nextInt();
			Contract contract = contractList.get(input-1);
			this.showClientInfo(contract.getClientID());
//			this.showInsuranceProductDetail(contract.getProductName());
			return contract;
		}else {
			System.out.println("현재 심사할 계약이 없습니다.");
			return null;
		}
		
	}
	
	private void showClientInfo(String clientID) {
		Client client = clientService.search(clientID);
		System.out.println("[고객 정보]");
		System.out.println("이름: " + client.getName());
		System.out.println("나이: " + client.getAge());
		System.out.println("성별: " + (client.isGender()? "남자" : "여자"));
		System.out.println("직업: " + client.getJob().getJobName());
		System.out.println("암경력: " + client.getMedicalHistory().getClientCancerCareer().getCancerName() + "(본인)"
							+ client.getMedicalHistory().getFamilyCancerCareer().getCancerName() + "(가족)");
		System.out.println("입원내역: " + client.getMedicalHistory().getNumberOfHospitalizations());
		System.out.println("병원진료: " + client.getMedicalHistory().getNumberOfHospitalVisits());
	}
	
	private void contractManagerMenu() {
		//ContractManagement contractManagement = (ContractManagement)managerLogin;
		while(true) {
			System.out.println("\n---ContractManagementMenu---");
			System.out.println("");
		}
	}
	
	private void compensationHandleMenu() {
		CompensationHandle compensationHandle = (CompensationHandle)managerLogin;
		while(true) {
			System.out.println("\n---CompensationHandleMenu---");
			System.out.println("1.사고처리");
			System.out.println("2.로그아웃");
			switch(sc.nextInt()) {
			case 1:
				this.accidentHandlingMenu(compensationHandle);
				break;
			case 2:
				managerLogin = null;
				break;
			}
		}
	}
	
	private void accidentHandlingMenu(CompensationHandle compensationHandle) {
		while(true) {
			System.out.println("보고싶은 사고의 보험종류를 선택해주세요.");
			System.out.println("[1.실비보험, 2.암보험, 3.연금보험, 4.종신보험, 5.돌아가기]");
			int input = sc.nextInt();
			if(input == 5) break; 
			ArrayList<Accident> accidentList = contractService.showAccidentListByProductType(InsuranceProductType.values()[input-1]);		
			System.out.println("[사고 목록]");
			int i = 0;
			for(Accident accident : accidentList) {
				Client client = clientService.search(accident.getClientID());
				System.out.println(String.format("%d.%5s%10s%12s", i+1, client.getName(), accident.getProductName(), accident.getReceptionDate().toString()));
				i++;
			}
			System.out.println("상세정보를 보고 싶은 사고의 번호를 입력해주세요.");
			input = sc.nextInt();
			this.showAccidentDetail(compensationHandle, accidentList.get(input-1));
		}
	}
	
	private void showAccidentDetail(CompensationHandle compensationHandle, Accident accident) {
		Client client = clientService.search(accident.getClientID());
		System.out.println("[상세정보]");
		System.out.println("고객 이름: " + client.getName());
		System.out.println("고객 나이: " + client.getAge());
		System.out.println("접수 내용: " + accident.getAccidentDetail());
		System.out.println("접수 날짜:" + accident.getReceptionDate());
		
		System.out.println("\n1.보험금 입력");
		System.out.println("2.돌아가기");
		switch(sc.nextInt()) {
			case 1:
				System.out.println("보험금을 입력해주세요.");
				System.out.println(compensationHandle.payInsuranceMoney(sc.nextInt(), client)? "보험금 지급이 완료되었습니다." : "보험금 지급에 실패하였습니다.");
				break;
			case 2:
				return;
		}
	}
	
	private void salesPersonMenu() {
		while(true) {
			System.out.println("\n---salesPersonMenu---");
			System.out.println("1.영업 활동 관리");
			System.out.println("2.모든 보험 조회");
			System.out.println("4.로그아웃");
			switch(sc.nextInt()) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				managerLogin = null;
				break;
			}
		}
	}

	public void insuranceMenu() {// insuranceMenus
		System.out.println("\n---notApprovalInsuranceList---");
		int i = 1;
		for (InsuranceProduct insuranceProduct : insuranceProductService.showAllList()) {
			System.out.println(i + ". 상품명: " + insuranceProduct.getProductName() + " 보험종류: "
					+ insuranceProduct.getInsuranceProductType().getInsuranceName() + " 승인여부: "
					+ insuranceProduct.isApproval());
			i++;
		}
		System.out.println("\n---approvalInsuranceList---");
		int x = 1;
		for (InsuranceProduct approvalip : insuranceProductService.approvalInsuranceProduct()) {
			System.out.println(x + ". 상품명: " + approvalip.getProductName() + " 보험종류: "
					+ approvalip.getInsuranceProductType().getInsuranceName() + " 승인여부: " + approvalip.isApproval());
			x++;
		}
	}

	public void basicInsuranceMenu() {
		System.out.println("\n현재 저희 회사는 다음과 같은 보험이 있습니다.");
		int i = 1;
		for (InsuranceProduct insuranceProduct : insuranceProductService.approvalInsuranceProduct()) {
			System.out.println(i + ". 상품명: " + insuranceProduct.getProductName() + " 보험종류: "
					+ insuranceProduct.getInsuranceProductType().getInsuranceName());
			i++;
		}
	}
}
