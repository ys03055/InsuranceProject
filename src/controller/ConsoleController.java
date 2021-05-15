package controller;

import java.util.ArrayList;
import java.util.Scanner;

import entity.ActualExpense;
import entity.Cancer;
import entity.Client;
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
				if (insuranceProductService.showAllList().isEmpty()) {
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

			break;
		case CM:

			break;
		case CH:

			break;
		case SP:

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
				break;
			case 3:
				managerLogin = null;
				return;
			}
		}
	}

	private void insuranceProductsAcceptanceMenu() {// IPA
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
		InsuranceProduct aip = insuranceProductService.showAllList().get(a - 1);
		ActualExpense aae = (ActualExpense) insuranceProductService.showAllList().get(a - 1);
		Cancer cae = (Cancer) insuranceProductService.showAllList().get(a-1);
		Life lae = (Life) insuranceProductService.showAllList().get(a-1);
		Pension pae = (Pension) insuranceProductService.showAllList().get(a-1);
		switch (aip.getInsuranceProductType()) {
		case ACTUALEXPENSE:
			System.out.println("상품명: " + aip.getProductName() + " 기본보험료: " + aip.getBasicInsurancePremium() + " 납입기간: "
					+ aip.getPaymentPeriod() + " 납입주기: " + aip.getPaymentCycle() + " 제한나이: " + aae.getLimitAge()
					+ " 보장내역: " + aae.getActualExpenseType().values()[a-1].getactualexpensename() + " 보험금:" + aae.getInsuranceMoney());
			break;
		case CANCER:
			System.out.println("상품명: " + cae.getProductName() + " 기본보험료: " + cae.getBasicInsurancePremium() + " 납입기간: "
					+ cae.getPaymentPeriod() + " 납입주기: " + cae.getPaymentCycle() + " 제한나이: " + cae.getLimitAge()
					+ " 보장내역: " + cae.getCancerType().values()[a-1].getCancerName() + cae.getCancerType().values()[a-1].getRate() + " 보험금:" + aae.getInsuranceMoney());
			break;
		case LIFE:
			System.out.println("상품명: " + lae.getProductName() + " 기본보험료: " + lae.getBasicInsurancePremium() + " 납입기간: "
					+ lae.getPaymentPeriod() + " 납입주기: " + lae.getPaymentCycle() + " 제한나이: " + cae.getLimitAge()
					+ " 보험금:" + aae.getInsuranceMoney());
			break;
		case PENSION:
			System.out.println("상품명: " + pae.getProductName() + " 기본보험료: " + pae.getBasicInsurancePremium() + " 납입기간: "
					+ pae.getPaymentPeriod() + " 납입주기: " + pae.getPaymentCycle() + " 보험금:" + pae.getInsuranceMoney());
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
			// insuranceProduct.setApproval(true);
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
		insuranceProductService.approvalInsuranceProduct();
		int a = sc.nextInt();
		System.out.println("1.삭제하기 2.돌아가기");
		switch(a) {
		case 1:
			insuranceProductService.approvalInsuranceProduct().remove(a-1);
			break;
		case 2:
			return;
		}
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
