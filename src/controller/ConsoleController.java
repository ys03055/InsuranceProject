package controller;
<<<<<<< HEAD
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
=======
>>>>>>> 0256c1c125ff4c463f1198edcc973869bcb36a1c
import java.util.Scanner;

import entity.Client;
<<<<<<< HEAD
import entity.CompensationHandle;
import entity.Contract;
import entity.ContractManagement;
=======
>>>>>>> 0256c1c125ff4c463f1198edcc973869bcb36a1c
import entity.InsuranceProduct;
import entity.InsuranceProducts;
import entity.Manager;
<<<<<<< HEAD
import entity.Pension;
import list.ClientList;
import service.ClientService;
=======
>>>>>>> 0256c1c125ff4c463f1198edcc973869bcb36a1c
import service.ClientServiceImpl;
import service.InsuranceProductServiceImpl;
import service.ManagerServiceImpl;

public class ConsoleController{
	
	private Scanner sc;
	private ClientServiceImpl clientService;
	private InsuranceProductServiceImpl insuranceProductService;
	private ManagerServiceImpl managerService;
	private Manager managerLogin;
	private Client clientLogin;
	
	public ConsoleController() {
		this.sc = new Scanner(System.in);
		this.clientService = new ClientServiceImpl();
		this.insuranceProductService = new InsuranceProductServiceImpl();
		this.managerService = new ManagerServiceImpl();
		
		this.managerLogin = null;
		this.clientLogin = null;
	}
	
	public void run() {
		this.mainMenu();
	}
	
	private void mainMenu() {
		while(true) {
			System.out.println("\n---MainMenu---");
			System.out.println("1.������");
			System.out.println("2.ȸ��");
			System.out.println("3.����");
			System.out.println("4.������");
			switch(sc.nextInt()) {
			case 1:
				managerMenu();
				break;
			case 2:
				clientMenu();
				break;
			case 3:
				insuranceMenu();
				break;
			case 4:
				return;
			}
		}
	}
	
	//ManagerMenus
	private void managerMenu() {
		while(true) {
			System.out.println("\n---ManagerMenu---");
			System.out.println("1.������ ���");
			System.out.println("2.������ �α���");
			System.out.println("3.������ ����");
			System.out.println("4.���ư���");
			switch(sc.nextInt()) {
			case 1:
				System.out.println(managerService.register() ? "����� �Ϸ�Ǿ����ϴ�." : "��Ͽ� �����Ͽ����ϴ�.");
				break;
			case 2:
				if(managerLogin == null) managerLogin = managerService.login();
				if(managerLogin != null) managerWorkMenu();
				else System.out.println("���� �Ŵ����Դϴ�.");
				break;
			case 3:
				System.out.println(managerService.delete() ? "������ �Ϸ�Ǿ����ϴ�." : "������ �����Ͽ����ϴ�.");
				break;
			case 4:
				return;
			default: 
				System.out.println("�߸��� ���� �Է��ϼ̽��ϴ�.");
				break;
			}
		}
	}
	
<<<<<<< HEAD
	private void managerRegisterMenu() {
		System.out.println("[JobPosition]");
		System.out.println("[1.보험상품개발자 2.보험상품승인자 3.U/W 4.계약관리자 5.보상처리자 6.영업사원]");
		int input = sc.nextInt();
		Manager manager = ManagerType.values()[input-1].getManager().clone();
		manager.setJobPosition(ManagerType.values()[input-1]);
		sc.nextLine();
		
		//System.out.println("[이름]");
		//manager.setName(sc.nextLine());
		
		//System.out.println("[나이]");
		//manager.setAge(sc.nextInt());
		//sc.nextLine();
		
		//System.out.println("[전화번호]");
		//manager.setPhoneNumber(sc.nextLine());
		
		System.out.println("[ID]");
		manager.setId(sc.nextLine());
		
		System.out.println("[Password]");
		manager.setPassword(sc.nextLine());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("current: " + df.format(cal.getTime()));
        cal.add(Calendar.YEAR, 10);
        System.out.println("after: " + df.format(cal.getTime()));
		System.out.println(managerService.register(manager) ? "등록이 완료되었습니다." : "등록에 실패하였습니다.");
	}
	
	private void managerLoginMenu() {
		sc.nextLine();
		if (managerLogin == null) {
			System.out.println("[ID]");
			String id = sc.nextLine();
			System.out.println("[Password]");
			String pw = sc.nextLine();
			
			managerLogin = managerService.login(id, pw);
		}
		if (managerLogin != null)
			managerWorkMenu();
		else
			System.out.println("등록되지 않은 매니저입니다.");	
	}

	private void managerDeleteMenu() {
		System.out.println("[ID]");
		String id = sc.nextLine();
		System.out.println("[Password]");
		String pw = sc.nextLine();
		
		System.out.println(managerService.delete(id, pw) ? "삭제가 완료되었습니다." : "삭제에 실패하였습니다.");
	}
	
	private void managerWorkMenu() {//managerWorkMenu
		switch (managerLogin.getJobPosition()) {//manager직업마다 다른 메뉴
=======
	private void managerWorkMenu() {
		switch(managerLogin.getJobPosition()) {
>>>>>>> 0256c1c125ff4c463f1198edcc973869bcb36a1c
		case IP:
			insuranceProductsMenu();
			break;
		case IPA:
			
			break;
		case UW:
			
			break;
		case CM:
			contractManagementMenu();
			break;
			
		case CH:
			
			break;
		case SP:
			
			break;
		}
	}
	
	private void insuranceProductsMenu() {
		InsuranceProducts ip = (InsuranceProducts)managerLogin;
		while(true) {
			System.out.println("\n---InsuranceProductsMenu---");
			System.out.println("1.�����ǰ ����");
			System.out.println("2.���İ���");
			System.out.println("3.������");
			System.out.println("3.�α׾ƿ�");
			switch(sc.nextInt()) {
			case 1:
				InsuranceProduct developedProduct = ip.designInsurance().developInsurance();
				insuranceProductService.add(developedProduct);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				managerLogin = null;
				return;
			}
		}
	}
<<<<<<< HEAD
	
	private void approvalMenu(InsuranceProduct insuranceProduct) {
		System.out.println("\n1.보험승인 2.보험승인거절 3.돌아가기");
		int a = sc.nextInt();
		switch (a) {
		case 1:
			insuranceProduct.setApproval(true);
			System.out.println("승인이 완료되었습니다.");
			return;
		case 2:
			insuranceProductService.showInsuranceProductIsNotApproval().remove(insuranceProduct);
			System.out.println("승인이 거절 되었습니다. 목록에서 삭제합니다.");
			return;
		case 3:
			return;
		}
	}
	
	private void approvalInsuranceDelete() {//승인한보험삭제
		System.out.println("--삭제할 보험을 선택해주세요.--");
		int x = sc.nextInt();
		System.out.println("1.삭제하기 2.돌아가기");
		int y = sc.nextInt();
		switch(y) {
		case 1:
			insuranceProductService.showInsuranceProductIsApproval().remove(x-1);
			System.out.println(insuranceProductService.showInsuranceProductIsApproval().size());
			System.out.println("삭제가 완료되었습니다.");
			break;
		case 2:
			return;
		}
	}
	
	private void emailSend() {//이메일 보내기 (21.05.18)
		String user = ""; // gmail계정
		String password = ""; // 패스워드

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("hm5395@naver.com")); // 수신자
			message.setSubject("Test"); // 메일 제목을 입력
			message.setText("Test"); // 메일 내용을 입력
			Transport.send(message); // 전송
			System.out.println("Message sent successfully...!!");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private void followUpInsurance() {//사후관리
		System.out.println("보험목록에서 사후관리할 보험을 선택해주세요.");
		InsuranceProduct selectedInsuranceProduct = this.insuranceMenu(insuranceProductService.showInsuranceProductIsApproval());
		System.out.println("해당 보험을 수정하시겠습니까? 1.수정하기, 2.뒤로가기");
		int input = sc.nextInt();
		sc.nextLine();
		switch(input) {
		case 1:
			this.modifyInsuranceProduct(selectedInsuranceProduct);
			break;
		case 2:
			return;
		}
		
	}

	private void modifyInsuranceProduct(InsuranceProduct selectedInsuranceProduct) {
		switch(selectedInsuranceProduct.getInsuranceProductType()) {
		case ACTUALEXPENSE:
			this.developActualExpense(selectedInsuranceProduct);
			break;
		case CANCER:
			this.developCancer(selectedInsuranceProduct);
			break;
		case PENSION:
			this.developPension(selectedInsuranceProduct);
			break;
		case LIFE:
			this.developLife(selectedInsuranceProduct);
			break;
		}

	}

	private void underWriterMenu() {//UW(UW)
//		UW uw = (UW)managerLogin;
=======
	private void contractManagementMenu() {
		ContractManagement cm = (ContractManagement)managerLogin;
>>>>>>> 0256c1c125ff4c463f1198edcc973869bcb36a1c
		while(true) {
			System.out.println("\n---ContractManagementMenu");
			System.out.println("1.����� ���� ����");
			System.out.println("2.����� ������ ����");
			System.out.println("3.�α׾ƿ�");
			switch(sc.nextInt()) {
			case 1:
				Contract c
			}
		}
	}
	
<<<<<<< HEAD
	private Contract selectUnderWriteContract() {
		ArrayList<Contract> contractList = contractService.selectNotApproval();
		if (contractList.size() > 0) {
			System.out.println("[인수심사 계약 목록]");
			for (int i = 0; i < contractList.size(); i++)
				System.out.println(String.format("%d.%5s%10s", i + 1, contractList.get(i).getClientID(),
						contractList.get(i).getProductName()));
			System.out.println("인수심사할 계약의 번호를 입력해주세요.");
			int input = sc.nextInt();
			Contract contract = contractList.get(input - 1);
			this.showClientInfo(contract.getClientID());
//			this.showInsuranceProductDetail(contract.getProductName());
			return contract;
		} else {
			System.out.println("현재 심사할 계약이 없습니다.");
			return null;
		}
	}
	
	private void showClientInfo(String clientID) {
		Client client = clientService.login(clientID, clientID);
		System.out.println("[고객 정보]");
		System.out.println("이름: " + client.getName());
		System.out.println("나이: " + client.getAge());
		System.out.println("성별: " + (client.isGender() ? "남자" : "여자"));
		System.out.println("직업: " + client.getJob().getJobName());
		System.out.println("암경력: " + client.getMedicalHistory().getClientCancerCareer().getCancerName() + "(본인)"
				+ client.getMedicalHistory().getFamilyCancerCareer().getCancerName() + "(가족)");
		System.out.println("입원내역: " + client.getMedicalHistory().getNumberOfHospitalizations());
		System.out.println("병원진료: " + client.getMedicalHistory().getNumberOfHospitalVisits());
	}
	
	private void contractManagerMenu() {// CM(계약관리자)
		// ContractManagement contractManagement = (ContractManagement)managerLogin;
		
	}
	
	private void compensationHandleMenu() {// CH(보상처리자)
		CompensationHandle compensationHandle = (CompensationHandle) managerLogin;
		while (true) {
			System.out.println("\n---CompensationHandleMenu---");
			System.out.println("1.사고처리");
			System.out.println("2.로그아웃");
			switch (sc.nextInt()) {
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
				Client client = accident.getClient();
				System.out.println(String.format("%d.%5s%10s%12s", i+1, client.getName(), accident.getInsuranceProduct().getProductName(), accident.getReceptionDate().toString()));
				i++;
			}
			System.out.println("상세정보를 보고 싶은 사고의 번호를 입력해주세요.");
			input = sc.nextInt();
			this.showAccidentDetail(compensationHandle, accidentList.get(input-1));
		}
	}
	
	private void showAccidentDetail(CompensationHandle compensationHandle, Accident accident) {
		Client client = accident.getClient();
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
	
	private void salesPersonMenu() {//SP(영업사원)
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
	
	private void clientWorkMenu() {//미완성
		System.out.println("1.모든 보험 조회하기 2.가입한 보험 조회하기 3.로그아웃");
		switch (sc.nextInt()) {
		case 1:
			if(insuranceMenu(insuranceProductService.showInsuranceProductIsApproval()) == null) {
				System.out.println("\n이전 페이지로 돌아갑니다.");
				return;
			}else
			contractRegisterClient();
		case 2:
			signUpInsuranceProductMenu();
		case 3:
			clientLogin = null;
			return;
		}
	}
	
	private void contractRegisterClient() {//미완성
			System.out.println("/n1.가입하기 2.돌아가기");
			int a = sc.nextInt();
			switch(a) {
			case 1://계약 리스트에 넣기
				
				break;
			case 2:
				return;
			}
		}
	
	private void signUpInsuranceProductMenu() {//미완성
		
	}
	
	private void clientMenu() {// clientMenu
		while (true) {
=======
	//clientMenus
	private void clientMenu() {
		while(true) {
>>>>>>> 0256c1c125ff4c463f1198edcc973869bcb36a1c
			System.out.println("\n---ClientMenu---");
			System.out.println("1.ȸ������");
			System.out.println("2.ȸ�� �α���");
			System.out.println("3.ȸ�� Ż��");
			System.out.println("4.���ư���");
			switch(sc.nextInt()) {
			case 1:
				clientService.register();
				break;
			case 2:
				if(clientLogin == null) clientLogin = clientService.login();
				break;
			case 3:
				clientService.delete();
				break;
			case 4:
				return;
			}
		}
	}
<<<<<<< HEAD

	private void clientRegisterMenu() {
		sc.nextLine();
		Client client = new Client();
		//clientService.checkClientID(client.getId()) == null
		System.out.println("[ID]");
		client.setId(sc.nextLine());
		
		if(clientService.checkClientID(client.getId()) !=null) {
			System.out.println("이미 가입된 ID입니다. 다시 입력해주세요.");
			client.setId(sc.nextLine());
		}
		System.out.println(client.getId());
		System.out.println("비밀번호를 입력하세요");
		client.setPassword(sc.nextLine());
		System.out.println(client.getPassword());
		System.out.println("[이름]");
		client.setName(sc.nextLine());
		System.out.println("[나이]");
		client.setAge(sc.nextInt());
		//System.out.println("[Email]");
		//client.setEmail(sc.nextLine());
		//sc.nextLine();
		/*System.out.println("[성별 (1.남 2.여)]");
		if (sc.nextInt() == 1) {
			client.setGender(true);
			System.out.println("남자");
		} else {
			client.setGender(false);
			System.out.println("여자");
		}*/
		/*System.out.println("[ClientJobType]");
		System.out.println("[1.군인 2.PW 3.AF 4.DRIVER 5.ETC]");
		int input = sc.nextInt();
		ClientJobType.values()[input - 1].getJobName();
		System.out.println(ClientJobType.values()[input - 1].getJobName());
		sc.nextLine();
		System.out.println("주소를 입력하세요.");
		client.setAddress(sc.nextLine());
		System.out.println("핸드폰 번호를 입력하세요.");
		client.setPhoneNumber(sc.nextLine());
		System.out.println("주민등록번호를 입력하세요.");
		client.setResidentRegistrationNumber(sc.nextLine());
		System.out.println("계좌번호를 입력하세요.");
		client.setBankAccountNumber(sc.nextLine());*/
		
		System.out.println(clientService.register(client) ? "회원가입이 완료되었습니다." : "회원가입에 실패했습니다.");
	}

	private void clientLoginMenu(){
		if (clientLogin == null) {sc.nextLine();
			System.out.println("--ID를 입력해주세요.--");
			String id = sc.nextLine();
			System.out.println("--Password를 입력해주세요.--");
			String pw = sc.nextLine();
			clientLogin = clientService.login(id, pw);
		}
		if (clientLogin != null) {
			clientWorkMenu();
		} else
			System.out.println("입력하신 정보를 확인해주세요.");
	}
=======
>>>>>>> 0256c1c125ff4c463f1198edcc973869bcb36a1c
	
	//insuranceMenus
	private void insuranceMenu() {
		System.out.println("\n---InsuranceList---");
		int i = 1;
<<<<<<< HEAD
		if (insuranceProductList.isEmpty()) {
			System.out.println("현재 준비된 상품이 없습니다.");
		} else {
			for (InsuranceProduct insuranceProduct : insuranceProductList) {
				System.out.println(i + ". " + insuranceProduct.getProductName() + " "
						+ insuranceProduct.getInsuranceProductType().getInsuranceName());
				i++;
			}
				System.out.println("\n보험상품의 번호를 입력해주세요.");
				InsuranceProduct selectInsurance = insuranceProductList.get(sc.nextInt() - 1);
				this.showInsuranceProductDetail(selectInsurance);
				return selectInsurance;
=======
		for(InsuranceProduct insuranceProduct : insuranceProductService.showAllList()) {
			System.out.println(i+". " + insuranceProduct.getProductName() +" "+ insuranceProduct.getInsuranceProductType().getInsuranceName());
			i++;
>>>>>>> 0256c1c125ff4c463f1198edcc973869bcb36a1c
		}
	//contractMenu
	private void contractMenu() {
		System.out.println("\n---ContractList---");
		int i = 1;
		for()
	}
	}
	

}
