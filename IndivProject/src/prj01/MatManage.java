package prj01;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MatManage extends JFrame{
	
	final int ZERO = 0;
	final int DEFAULT_WIDTH = 550;
	final int DEFAULT_HEIGHT = 140;
	final int START_WIDTH = 1250;
	final int START_HEIGHT = 700;
	
	String[] subMat = {"합판류", "씽크대류", "피스류", "기타물품", "입고날짜", "거래처"};
	final int[] MATNUM = {IItem.PLYWOOD, IItem.SINK, IItem.PIECE, IItem.ETC, IItem.DATE, IItem.CUSTOMER};
	
	Container c = getContentPane();
	
	// 상단부분
	JPanel pnlNorth = new JPanel();
	
	JPanel pnlNorthCenter = new JPanel(); // 프로그램명 넣는부분
	JLabel lblTitle = new JLabel("자재 관리 프로그램", SwingConstants.CENTER); // 타이틀
	// 검색 부분
	JPanel pnlNorthSouth = new JPanel(); // 검색 부분
	JComboBox<String> cmbSearch = new JComboBox<>();
	JTextField txfSearch = new JTextField(20);
	JButton btnSearch = new JButton("검색");
	
	
	JPanel pnlNorthEast = new JPanel(); // 로그인 부분
	// 아이디 패스워드 안내부분
	JPanel pnlLogin = new JPanel();
	JPanel pnlLbl = new JPanel();
	JLabel lblId = new JLabel("아이디 : ", SwingConstants.RIGHT);
	JLabel lblPw = new JLabel("비밀번호 : ", SwingConstants.RIGHT);
	// 아이디 패스워드 입력부분
	JPanel pnlTxf = new JPanel();
	JTextField txfId = new JTextField(15);
	JPasswordField txfPw = new JPasswordField(15);
	// 회원가입 로그인 패널
	JPanel pnlBtn = new JPanel();
//	JButton btnSignUp = new JButton("회원가입");
	JButton btnLogIn = new JButton("로그인");
	// 로그인 후
	JPanel pnlLogon = new JPanel();
	JLabel lblLogin = new JLabel();
	JPanel pnlLogout = new JPanel();
	JButton btnLogOut = new JButton("로그아웃");
	
	
	// 좌측부분
	JPanel pnlWest = new JPanel();
	JButton btnSelect = new JButton("전 체");
	JButton btnInsert = new JButton("입 고");
//	JButton btnUpdate = new JButton("수 정");
	JButton btnCustomer = new JButton("거래처");
	
	// 메인부분
	JPanel pnlCenter = new JPanel();
	JPanel pnlCenterNorth = new JPanel();
	JPanel pnlResult = new JPanel();
	JPanel pnlNo = new JPanel();
	JTextArea txaResult = new JTextArea();
	JTabbedPane tabResult = new JTabbedPane();

	
	// 로그인 리스너
	LoginListener logListener = new LoginListener();
	
	// 검색 리스너
	SearchListener srchListener = new SearchListener();
	
	// 왼쪽 패널 버튼 리스너
	BtnListListener btnListener = new BtnListListener();
	
	// 수량 수정 리스너
	UpdateListener updateListener = new UpdateListener();
	
	// 사용자 등록 클래스
	CustSignUp signup = new CustSignUp(this, "거래처 등록", true);
	MatInsert matReg = new MatInsert(this, "제품입고");
	
	// 결과출력용 벡터
	Vector<JButton> vecList = new Vector<>();
	Vector<JButton> vecNo = new Vector<>();
	Vector<JTextField> vecTxfCateNo = new Vector<>();
	Vector<JTextField> vecTxfType = new Vector<>();
	Vector<JTextField> vecTxfSize1 = new Vector<>();
	Vector<JTextField> vecTxfSize2 = new Vector<>();
	Vector<JTextField> vecTxfSize3 = new Vector<>();
	Vector<JTextField> vecTxfQuan = new Vector<>();
	Vector<JTextField> vecTxfPrice = new Vector<>();
	Vector<JTextField> vecTxfUnit = new Vector<>();
	Vector<JTextField> vecTxfDate = new Vector<>();
	Vector<JTextField> vecTxfCustNo = new Vector<>();
	Vector<Long> vecSerial = new Vector<>();
	
	Vector<JTextField> vecTxfCustName = new Vector<>();
	Vector<JTextField> vecTxfLicNum = new Vector<>();
	Vector<JTextField> vecTxfRegDate = new Vector<>();
	Vector<JTextField> vecTxfPhoneNum = new Vector<>();
	
	// Dao
	MatDao mdao = MatDao.getInstance(); // 제품dao
	CustDao cdao = CustDao.getInstance(); // 거래처 dao
	
	// Vo
	MatVo mvo;
	
	// ======================================================================================================
	
	/**
	 * 생성자 클래스
	 */
	public MatManage() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Material Management Program v1.0");
		
		setUI();
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
//		setExtendedState(JFrame.MAXIMIZED_BOTH); // 전체화면
		setVisible(true);
		
		txfId.requestFocus();
	}
	
	/**
	 * UI
	 */
	private void setUI() {

		c.setLayout(new BorderLayout());
		
		pnlNorth.setLayout(new BorderLayout());
		pnlNorth.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// 타이틀과 검색부분
		lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		
		pnlNorthSouth.setLayout(new FlowLayout());
		
		for (String s : subMat) {
			cmbSearch.addItem(s);
		}
		
		pnlNorthSouth.add(cmbSearch);
		pnlNorthSouth.add(txfSearch);
		pnlNorthSouth.add(btnSearch);
		
		pnlNorthCenter.setLayout(new BorderLayout());
		pnlNorthCenter.add(lblTitle, BorderLayout.CENTER);
		pnlNorthCenter.add(pnlNorthSouth, BorderLayout.SOUTH);
		
		// 아이디 패스워드 부분
		pnlNorthEast.setLayout(new CardLayout());
		
		pnlLogin.setLayout(new BorderLayout());
		
		pnlLbl.setLayout(new GridLayout(2, 1));
		pnlLbl.add(lblId);
		pnlLbl.add(lblPw);
		pnlTxf.setLayout(new GridLayout(2, 1));
		pnlTxf.add(txfId);
		pnlTxf.add(txfPw);
		pnlBtn.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
//		pnlBtn.add(btnSignUp);
		pnlBtn.add(btnLogIn);
		
		pnlLogin.add(pnlLbl, BorderLayout.WEST);
		pnlLogin.add(pnlTxf, BorderLayout.CENTER);
		pnlLogin.add(pnlBtn, BorderLayout.SOUTH);
		
		// 로그인 성공하면 띄울 부분
		pnlLogon.setLayout(new BorderLayout());
		
		pnlLogout.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlLogout.add(btnLogOut);
		
		pnlLogon.add(lblLogin, BorderLayout.CENTER);
		pnlLogon.add(pnlLogout, BorderLayout.SOUTH);
		
		pnlLogon.setVisible(false);
		
		pnlNorthEast.add("login", pnlLogin);
		pnlNorthEast.add("logon", pnlLogon);
		
		// 상단 부분
		pnlNorth.add(pnlNorthCenter, BorderLayout.CENTER);
		pnlNorth.add(pnlNorthEast, BorderLayout.EAST);
		
		// 변동부분
		pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
		pnlWest.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		btnCustomer.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		btnCustomer.setToolTipText("새 거래처를 등록합니다.");
		pnlWest.add(btnCustomer);
		pnlWest.add(Box.createVerticalStrut(50));
		btnSelect.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnSelect.setToolTipText("전체 자재 내역을 최근입고순으로 보여줍니다.");
		pnlWest.add(btnSelect);
		pnlWest.add(Box.createVerticalStrut(10));
		btnInsert.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnInsert.setToolTipText("자재 입고 내역을 등록합니다.");
		pnlWest.add(btnInsert);
		pnlWest.add(Box.createVerticalStrut(10));
//		btnUpdate.setFont(new Font("맑은 고딕", Font.BOLD, 20));
//		btnUpdate.setToolTipText("자재의 세부 내역을 수정합니다.");
//		pnlWest.add(btnUpdate);
		
		pnlCenter.setLayout(new BorderLayout());
		pnlCenterNorth.setLayout(new BorderLayout());
		
//		pnlResult.setLayout(new BorderLayout());
//		pnlResult.add(new JScrollPane(txaResult), BorderLayout.CENTER);
		pnlCenterNorth.setVisible(false);
		
		pnlCenterNorth.add(pnlNo, BorderLayout.WEST);
		pnlCenterNorth.add(pnlResult, BorderLayout.CENTER);
		pnlCenter.add(pnlCenterNorth, BorderLayout.NORTH);
		
		setClose();
		
		c.add(pnlNorth, BorderLayout.NORTH);
		c.add(pnlWest, BorderLayout.WEST);
		c.add(new JScrollPane(pnlCenter), BorderLayout.CENTER);
		
		
		
		// 로그인 로그아웃 관련 리스너
		btnCustomer.addActionListener(logListener);
		btnLogIn.addActionListener(logListener);
		txfId.addActionListener(logListener);
		txfPw.addActionListener(logListener);
		btnLogOut.addActionListener(logListener);
		
		// 검색 관련 리스너
		txfSearch.addActionListener(srchListener);
		btnSearch.addActionListener(srchListener);
		
		// 왼쪽 패널 버튼 리스너
		btnInsert.addActionListener(btnListener);
		btnSelect.addActionListener(btnListener);
//		btnUpdate.addActionListener(btnListener);
	}
	
	/**
	 * 로그인 성공시 보이는 화면
	 */
	private void setOpen() {
		cmbSearch.setVisible(true);
		txfSearch.setVisible(true);
		btnSearch.setVisible(true);
		btnInsert.setVisible(true);
		btnSelect.setVisible(true);
//		btnUpdate.setEnabled(true);
		btnCustomer.setVisible(true);
//		txaResult.setVisible(true);
		pnlCenter.setBackground(Color.WHITE);
		pnlCenter.setVisible(true);
		setSize(START_WIDTH, START_HEIGHT);
	}
	
	/**
	 * 로그아웃시 보이는 화면
	 */
	private void setClose() {
		cmbSearch.setVisible(false);
		txfSearch.setVisible(false);
		btnSearch.setVisible(false);
		btnInsert.setVisible(false);
		btnSelect.setVisible(false);
//		btnUpdate.setEnabled(false);
		btnCustomer.setVisible(false);
//		txaResult.setVisible(false);
		pnlCenter.setVisible(false);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * 로그인/로그아웃 버튼 리스너
	 * @author KH
	 *
	 */
	class LoginListener implements ActionListener {


		@Override
		public void actionPerformed(ActionEvent e) {
			// 새창 화면위치
			int x = getX();
			int y = getY();
			Dimension size = getSize();
			int xSize = size.width;
			
			Object obj = e.getSource();
			
			if (obj == txfId) {
				txfPw.requestFocus();
			}
			if (obj == btnLogIn || obj == txfPw) {
				// 로그인 클래스
				LogIn log = LogIn.getInstance();
				log.getInfo(txfId.getText(), txfPw.getPassword());
				boolean loginOk = log.chkLogin();
				if (loginOk == true) {
					lblLogin.setText(txfId.getText() + " 님 로그인되었습니다.");
					setOpen();
					pnlLogin.setVisible(false);
					pnlLogon.setVisible(true);
				}
			}
			if (obj == btnLogOut) {
				// 로그아웃
				setClose();
				txfId.setText("");
				txfPw.setText("");
				pnlLogon.setVisible(false);
				pnlLogin.setVisible(true);
				pnlCenterNorth.removeAll();
				txfId.requestFocus();
			}
			if (obj == btnCustomer) {
				// 거래처 등록
				signup.setCustNo();
				signup.setLocation(x+xSize, y);
				signup.setVisible(true);
				// 거래처목록 갱신
				matReg.setCustList();
			}
		}
		
	}
	
	/**
	 * 검색부분 리스너
	 * @author KH
	 *
	 */
	class SearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int index = cmbSearch.getSelectedIndex();
			int choice = MATNUM[index];
			String str = "";
			
			if (!(txfSearch.getText().equals(""))) {
				str = txfSearch.getText();
			}
			switch (choice) {
			case IItem.ETC : case IItem.PLYWOOD : case IItem.SINK : case IItem.PIECE :
				// 품목별 검색
//				MatDao mdao = MatDao.getInstance();
				Vector<MatDto> vecMat = mdao.searchMat(choice, str);
				resultMat(vecMat);
				break;
			case IItem.DATE :
				// 입고날짜별 검색
//				MatDao ddao = MatDao.getInstance();
				Vector<MatDto> vecDate = mdao.searchDate(str);
				resultDate(vecDate);
				break;
			case IItem.CUSTOMER :
				// 거래처별 검색
//				CustDao cdao = CustDao.getInstance();
				Vector<CustDto> vecCust = cdao.searchCust(str);
				resultCust(vecCust);
				break;
			}
			pnlCenterNorth.setVisible(true);
		}
		
	}
	
	/**
	 * 좌측 버튼 리스너
	 * @author KH
	 *
	 */
	class BtnListListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// 새창 화면위치
			int x = getX();
			int y = getY();
			Dimension size = getSize();
			int xSize = size.width;
			
			Object obj = e.getSource();
			
			if (obj == btnInsert) {
				// 입고버튼
				matReg.setLocation(x+xSize, y);
				matReg.setVisible(true);
			}
			if (obj == btnSelect) {
				// 전체버튼
//				MatDao mdao = MatDao.getInstance();
				Vector<MatDto> vecMat = mdao.selectAllMat();
				resultDate(vecMat);
				pnlCenterNorth.setVisible(true);
			}
		}
		
	}
	
	/**
	 * 결과창에서 수정하는 리스너
	 * @author KH
	 *
	 */
	class UpdateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 화면상에서 수정해서 업데이트하는 리스너
			System.out.println("actioncommand : " + e.getActionCommand()); // 입력받은값 표시
			System.out.println("source : " + e.getSource());
			System.out.println("id : " + e.getID());
			System.out.println("modifier : " + e.getModifiers());
			System.out.println("when : " + e.getWhen());
			System.out.println("class : " + e.getClass());
			
			Object obj = e.getSource();
			
			for (int i = 0; i < vecTxfQuan.size(); i++) {
				if (obj == vecTxfQuan.get(i)) {
					System.out.println("i : " + i);
					int quan = Integer.parseInt(e.getActionCommand());
					long serial = vecSerial.get(i).longValue();
					int count = mdao.update(quan, serial);
					System.out.println(count + "행 업데이트 완료.");
					if (quan == 0) {
						vecTxfQuan.get(i).setEditable(false);
					}
				}
			}
		}
		
	}
	
	/**
	 * 품목별 결과
	 * @param vec
	 */
	private void resultMat(Vector<MatDto> vec) {
		removeAll();
		
		int i = 0;
		int size = vec.size();
		String[] msg = {"종류", "크기1", "크기2", "크기3", "수량", "단가", "단위", "입고날짜", "거래처번호"};

		pnlNo.setLayout(new GridLayout(size+1, 1));
		pnlResult.setLayout(new GridLayout(size+1, msg.length));
		
		
		vecNo.add(new JButton("번호"));
		pnlNo.add(vecNo.get(i));
		
		for (int j = 0; j < msg.length; j++) {
			vecList.add(new JButton(msg[j]));
			pnlResult.add(vecList.get(j));
		}
		
		
		for (MatDto dto : vec) {
			
			String otherType = dto.getOtherType();
			int size1 = dto.getSize1();
			int size2 = dto.getSize2();
			int size3 = dto.getSize3();
			int quantity = dto.getQuantity();
			int unitPrice = dto.getUnitPrice();
			int unitSet = dto.getUnitSet();
			String receiveDate = dto.getReceiveDate();
			receiveDate = receiveDate.substring(0,10);
			int custNo = dto.getCustNo();
			long itemSerial = dto.getItemSerial();
			

			vecNo.add(new JButton(String.valueOf(i+1)));
			vecTxfType.add(new JTextField(otherType));
			vecTxfSize1.add(new JTextField(String.valueOf(size1)));
			vecTxfSize2.add(new JTextField(String.valueOf(size2)));
			vecTxfSize3.add(new JTextField(String.valueOf(size3)));
			vecTxfQuan.add(new JTextField(String.valueOf(quantity)));
			vecTxfPrice.add(new JTextField(String.valueOf(unitPrice)));
			vecTxfUnit.add(new JTextField(String.valueOf(unitSet)));
			vecTxfDate.add(new JTextField(receiveDate));
			vecTxfCustNo.add(new JTextField(String.valueOf(custNo)));
			vecSerial.add(itemSerial);
			
			vecTxfType.get(i).setEditable(false);
			vecTxfSize1.get(i).setEditable(false);
			vecTxfSize2.get(i).setEditable(false);
			vecTxfSize3.get(i).setEditable(false);
			vecTxfPrice.get(i).setEditable(false);
			vecTxfUnit.get(i).setEditable(false);
			vecTxfDate.get(i).setEditable(false);
			vecTxfCustNo.get(i).setEditable(false);
			
			if (quantity <= ZERO) {
				vecTxfQuan.get(i).setEditable(false);
			}
			
			vecTxfQuan.get(i).addActionListener(updateListener);
			
			pnlNo.add(vecNo.get(i+1));
			pnlResult.add(vecTxfType.get(i));
			pnlResult.add(vecTxfSize1.get(i));
			pnlResult.add(vecTxfSize2.get(i));
			pnlResult.add(vecTxfSize3.get(i));
			pnlResult.add(vecTxfQuan.get(i));
			pnlResult.add(vecTxfPrice.get(i));
			pnlResult.add(vecTxfUnit.get(i));
			pnlResult.add(vecTxfDate.get(i));
			pnlResult.add(vecTxfCustNo.get(i));
			
			i++;
		}
		revalidate();
		repaint();
	}
	
	/**
	 * 입고날짜 검색결과
	 * @param vec
	 */
	private void resultDate(Vector<MatDto> vec) {
		removeAll();
		
		int i = 0;
		int size = vec.size();
		String[] msg = {"품목", "종류", "크기1", "크기2", "크기3", "수량", "단가", "단위", "입고날짜", "거래처번호"};

		pnlNo.setLayout(new GridLayout(size+1, 1));
		pnlResult.setLayout(new GridLayout(size+1, msg.length));
		
		vecNo.add(new JButton("번호"));
		pnlNo.add(vecNo.get(i));
		
		for (int j = 0; j < msg.length; j++) {
			vecList.add(new JButton(msg[j]));
			pnlResult.add(vecList.get(j));
		}
		for (MatDto dto : vec) {
			int cateNo = dto.getCateNo();
			String otherType = dto.getOtherType();
			int size1 = dto.getSize1();
			int size2 = dto.getSize2();
			int size3 = dto.getSize3();
			int quantity = dto.getQuantity();
			int unitPrice = dto.getUnitPrice();
			int unitSet = dto.getUnitSet();
			String receiveDate = dto.getReceiveDate();
			receiveDate = receiveDate.substring(0,10);
			int custNo = dto.getCustNo();
			long itemSerial = dto.getItemSerial();
			
			vecNo.add(new JButton(String.valueOf(i+1)));
			vecTxfCateNo.add(new JTextField(String.valueOf(cateNo)));
			vecTxfType.add(new JTextField(otherType));
			vecTxfSize1.add(new JTextField(String.valueOf(size1)));
			vecTxfSize2.add(new JTextField(String.valueOf(size2)));
			vecTxfSize3.add(new JTextField(String.valueOf(size3)));
			vecTxfQuan.add(new JTextField(String.valueOf(quantity)));
			vecTxfPrice.add(new JTextField(String.valueOf(unitPrice)));
			vecTxfUnit.add(new JTextField(String.valueOf(unitSet)));
			vecTxfDate.add(new JTextField(receiveDate));
			vecTxfCustNo.add(new JTextField(String.valueOf(custNo)));
			vecSerial.add(itemSerial);
			
			vecTxfCateNo.get(i).setEditable(false);
			vecTxfType.get(i).setEditable(false);
			vecTxfSize1.get(i).setEditable(false);
			vecTxfSize2.get(i).setEditable(false);
			vecTxfSize3.get(i).setEditable(false);
			vecTxfPrice.get(i).setEditable(false);
			vecTxfUnit.get(i).setEditable(false);
			vecTxfDate.get(i).setEditable(false);
			vecTxfCustNo.get(i).setEditable(false);
			
			if (quantity <= ZERO) {
				vecTxfQuan.get(i).setEditable(false);
			}
			
			vecTxfQuan.get(i).addActionListener(updateListener);
			
			pnlNo.add(vecNo.get(i+1));
			pnlResult.add(vecTxfCateNo.get(i));
			pnlResult.add(vecTxfType.get(i));
			pnlResult.add(vecTxfSize1.get(i));
			pnlResult.add(vecTxfSize2.get(i));
			pnlResult.add(vecTxfSize3.get(i));
			pnlResult.add(vecTxfQuan.get(i));
			pnlResult.add(vecTxfPrice.get(i));
			pnlResult.add(vecTxfUnit.get(i));
			pnlResult.add(vecTxfDate.get(i));
			pnlResult.add(vecTxfCustNo.get(i));
			
			i++;
		}
		revalidate();
		repaint();
	}
	
	/**
	 * 거래처 검색결과
	 * @param vec
	 */
	private void resultCust(Vector<CustDto> vec) {
		removeAll();
		
		int i = 0;
		int size = vec.size();
		String[] msg = {"거래처번호", "거래처명", "사업자번호", "거래시작일", "전화번호"};
		
		pnlNo.setLayout(new GridLayout(size+1, 1));
		pnlResult.setLayout(new GridLayout(size+1, msg.length));
		
		vecNo.add(new JButton("번호"));
		pnlNo.add(vecNo.get(i));
		
		for (int j = 0; j < msg.length; j++) {
			vecList.add(new JButton(msg[j]));
			pnlResult.add(vecList.get(j));
		}
		for (CustDto dto : vec) {
			int custNo = dto.getCust_no();
			String custName = dto.getCust_name();
			int licNum1 = dto.getBusiness_license1();
			int licNum2 = dto.getBusiness_license2();
			int licNum3 = dto.getBusiness_license3();
			String regDate = dto.getTrade_reg_date();
			int phoneNum1 = dto.getPhone_no1();
			int phoneNum2 = dto.getPhone_no2();
			int phoneNum3 = dto.getPhone_no3();
			regDate = regDate.substring(0,10);
			
			vecNo.add(new JButton(String.valueOf(i+1)));
			vecTxfCustNo.add(new JTextField(String.valueOf(custNo)));
			vecTxfCustName.add(new JTextField(custName));
			vecTxfLicNum.add(new JTextField(String.format("%03d", licNum1)+"-"+String.format("%02d", licNum2)+"-"+String.format("%05d", licNum3)));
			vecTxfRegDate.add(new JTextField(regDate));
			vecTxfPhoneNum.add(new JTextField(String.format("%03d", phoneNum1)+"-"+String.format("%04d", phoneNum2)+"-"+String.format("%04d", phoneNum3)));
			
			vecTxfCustNo.get(i).setEditable(false);
			vecTxfCustName.get(i).setEditable(false);
			vecTxfRegDate.get(i).setEditable(false);
			vecTxfLicNum.get(i).setEditable(false);
			vecTxfPhoneNum.get(i).setEditable(false);
			
			vecTxfLicNum.get(i).addActionListener(updateListener);
			vecTxfPhoneNum.get(i).addActionListener(updateListener);
			
			pnlNo.add(vecNo.get(i+1));
			pnlResult.add(vecTxfCustNo.get(i));
			pnlResult.add(vecTxfCustName.get(i));
			pnlResult.add(vecTxfLicNum.get(i));
			pnlResult.add(vecTxfRegDate.get(i));
			pnlResult.add(vecTxfPhoneNum.get(i));
			
			i++;
		}
		revalidate();
		repaint();
	}
	
	public void removeAll() {
		pnlNo.removeAll();
		pnlResult.removeAll();
		vecList.removeAllElements();
		vecNo.removeAllElements();
		vecTxfCateNo.removeAllElements();
		vecTxfType.removeAllElements();
		vecTxfSize1.removeAllElements();
		vecTxfSize2.removeAllElements();
		vecTxfSize3.removeAllElements();
		vecTxfQuan.removeAllElements();
		vecTxfPrice.removeAllElements();
		vecTxfUnit.removeAllElements();
		vecTxfDate.removeAllElements();
		vecTxfCustNo.removeAllElements();
		vecSerial.removeAllElements();
		vecTxfCustName.removeAllElements();
		vecTxfLicNum.removeAllElements();
		vecTxfRegDate.removeAllElements();
		vecTxfPhoneNum.removeAllElements();
	}
	
	public static void main(String[] args) {
		new MatManage();
	}
}