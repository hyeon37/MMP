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
	
	String[] subMat = {"���Ƿ�", "��ũ���", "�ǽ���", "��Ÿ��ǰ", "�԰�¥", "�ŷ�ó"};
	final int[] MATNUM = {IItem.PLYWOOD, IItem.SINK, IItem.PIECE, IItem.ETC, IItem.DATE, IItem.CUSTOMER};
	
	Container c = getContentPane();
	
	// ��ܺκ�
	JPanel pnlNorth = new JPanel();
	
	JPanel pnlNorthCenter = new JPanel(); // ���α׷��� �ִºκ�
	JLabel lblTitle = new JLabel("���� ���� ���α׷�", SwingConstants.CENTER); // Ÿ��Ʋ
	// �˻� �κ�
	JPanel pnlNorthSouth = new JPanel(); // �˻� �κ�
	JComboBox<String> cmbSearch = new JComboBox<>();
	JTextField txfSearch = new JTextField(20);
	JButton btnSearch = new JButton("�˻�");
	
	
	JPanel pnlNorthEast = new JPanel(); // �α��� �κ�
	// ���̵� �н����� �ȳ��κ�
	JPanel pnlLogin = new JPanel();
	JPanel pnlLbl = new JPanel();
	JLabel lblId = new JLabel("���̵� : ", SwingConstants.RIGHT);
	JLabel lblPw = new JLabel("��й�ȣ : ", SwingConstants.RIGHT);
	// ���̵� �н����� �Էºκ�
	JPanel pnlTxf = new JPanel();
	JTextField txfId = new JTextField(15);
	JPasswordField txfPw = new JPasswordField(15);
	// ȸ������ �α��� �г�
	JPanel pnlBtn = new JPanel();
//	JButton btnSignUp = new JButton("ȸ������");
	JButton btnLogIn = new JButton("�α���");
	// �α��� ��
	JPanel pnlLogon = new JPanel();
	JLabel lblLogin = new JLabel();
	JPanel pnlLogout = new JPanel();
	JButton btnLogOut = new JButton("�α׾ƿ�");
	
	
	// �����κ�
	JPanel pnlWest = new JPanel();
	JButton btnSelect = new JButton("�� ü");
	JButton btnInsert = new JButton("�� ��");
//	JButton btnUpdate = new JButton("�� ��");
	JButton btnCustomer = new JButton("�ŷ�ó");
	
	// ���κκ�
	JPanel pnlCenter = new JPanel();
	JPanel pnlCenterNorth = new JPanel();
	JPanel pnlResult = new JPanel();
	JPanel pnlNo = new JPanel();
	JTextArea txaResult = new JTextArea();
	JTabbedPane tabResult = new JTabbedPane();

	
	// �α��� ������
	LoginListener logListener = new LoginListener();
	
	// �˻� ������
	SearchListener srchListener = new SearchListener();
	
	// ���� �г� ��ư ������
	BtnListListener btnListener = new BtnListListener();
	
	// ���� ���� ������
	UpdateListener updateListener = new UpdateListener();
	
	// ����� ��� Ŭ����
	CustSignUp signup = new CustSignUp(this, "�ŷ�ó ���", true);
	MatInsert matReg = new MatInsert(this, "��ǰ�԰�");
	
	// �����¿� ����
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
	MatDao mdao = MatDao.getInstance(); // ��ǰdao
	CustDao cdao = CustDao.getInstance(); // �ŷ�ó dao
	
	// Vo
	MatVo mvo;
	
	// ======================================================================================================
	
	/**
	 * ������ Ŭ����
	 */
	public MatManage() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Material Management Program v1.0");
		
		setUI();
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
//		setExtendedState(JFrame.MAXIMIZED_BOTH); // ��üȭ��
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
		
		// Ÿ��Ʋ�� �˻��κ�
		lblTitle.setFont(new Font("���� ���", Font.BOLD, 25));
		
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
		
		// ���̵� �н����� �κ�
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
		
		// �α��� �����ϸ� ��� �κ�
		pnlLogon.setLayout(new BorderLayout());
		
		pnlLogout.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlLogout.add(btnLogOut);
		
		pnlLogon.add(lblLogin, BorderLayout.CENTER);
		pnlLogon.add(pnlLogout, BorderLayout.SOUTH);
		
		pnlLogon.setVisible(false);
		
		pnlNorthEast.add("login", pnlLogin);
		pnlNorthEast.add("logon", pnlLogon);
		
		// ��� �κ�
		pnlNorth.add(pnlNorthCenter, BorderLayout.CENTER);
		pnlNorth.add(pnlNorthEast, BorderLayout.EAST);
		
		// �����κ�
		pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
		pnlWest.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		btnCustomer.setFont(new Font("���� ���", Font.BOLD, 16));
		btnCustomer.setToolTipText("�� �ŷ�ó�� ����մϴ�.");
		pnlWest.add(btnCustomer);
		pnlWest.add(Box.createVerticalStrut(50));
		btnSelect.setFont(new Font("���� ���", Font.BOLD, 20));
		btnSelect.setToolTipText("��ü ���� ������ �ֱ��԰������ �����ݴϴ�.");
		pnlWest.add(btnSelect);
		pnlWest.add(Box.createVerticalStrut(10));
		btnInsert.setFont(new Font("���� ���", Font.BOLD, 20));
		btnInsert.setToolTipText("���� �԰� ������ ����մϴ�.");
		pnlWest.add(btnInsert);
		pnlWest.add(Box.createVerticalStrut(10));
//		btnUpdate.setFont(new Font("���� ���", Font.BOLD, 20));
//		btnUpdate.setToolTipText("������ ���� ������ �����մϴ�.");
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
		
		
		
		// �α��� �α׾ƿ� ���� ������
		btnCustomer.addActionListener(logListener);
		btnLogIn.addActionListener(logListener);
		txfId.addActionListener(logListener);
		txfPw.addActionListener(logListener);
		btnLogOut.addActionListener(logListener);
		
		// �˻� ���� ������
		txfSearch.addActionListener(srchListener);
		btnSearch.addActionListener(srchListener);
		
		// ���� �г� ��ư ������
		btnInsert.addActionListener(btnListener);
		btnSelect.addActionListener(btnListener);
//		btnUpdate.addActionListener(btnListener);
	}
	
	/**
	 * �α��� ������ ���̴� ȭ��
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
	 * �α׾ƿ��� ���̴� ȭ��
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
	 * �α���/�α׾ƿ� ��ư ������
	 * @author KH
	 *
	 */
	class LoginListener implements ActionListener {


		@Override
		public void actionPerformed(ActionEvent e) {
			// ��â ȭ����ġ
			int x = getX();
			int y = getY();
			Dimension size = getSize();
			int xSize = size.width;
			
			Object obj = e.getSource();
			
			if (obj == txfId) {
				txfPw.requestFocus();
			}
			if (obj == btnLogIn || obj == txfPw) {
				// �α��� Ŭ����
				LogIn log = LogIn.getInstance();
				log.getInfo(txfId.getText(), txfPw.getPassword());
				boolean loginOk = log.chkLogin();
				if (loginOk == true) {
					lblLogin.setText(txfId.getText() + " �� �α��εǾ����ϴ�.");
					setOpen();
					pnlLogin.setVisible(false);
					pnlLogon.setVisible(true);
				}
			}
			if (obj == btnLogOut) {
				// �α׾ƿ�
				setClose();
				txfId.setText("");
				txfPw.setText("");
				pnlLogon.setVisible(false);
				pnlLogin.setVisible(true);
				pnlCenterNorth.removeAll();
				txfId.requestFocus();
			}
			if (obj == btnCustomer) {
				// �ŷ�ó ���
				signup.setCustNo();
				signup.setLocation(x+xSize, y);
				signup.setVisible(true);
				// �ŷ�ó��� ����
				matReg.setCustList();
			}
		}
		
	}
	
	/**
	 * �˻��κ� ������
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
				// ǰ�� �˻�
//				MatDao mdao = MatDao.getInstance();
				Vector<MatDto> vecMat = mdao.searchMat(choice, str);
				resultMat(vecMat);
				break;
			case IItem.DATE :
				// �԰�¥�� �˻�
//				MatDao ddao = MatDao.getInstance();
				Vector<MatDto> vecDate = mdao.searchDate(str);
				resultDate(vecDate);
				break;
			case IItem.CUSTOMER :
				// �ŷ�ó�� �˻�
//				CustDao cdao = CustDao.getInstance();
				Vector<CustDto> vecCust = cdao.searchCust(str);
				resultCust(vecCust);
				break;
			}
			pnlCenterNorth.setVisible(true);
		}
		
	}
	
	/**
	 * ���� ��ư ������
	 * @author KH
	 *
	 */
	class BtnListListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// ��â ȭ����ġ
			int x = getX();
			int y = getY();
			Dimension size = getSize();
			int xSize = size.width;
			
			Object obj = e.getSource();
			
			if (obj == btnInsert) {
				// �԰��ư
				matReg.setLocation(x+xSize, y);
				matReg.setVisible(true);
			}
			if (obj == btnSelect) {
				// ��ü��ư
//				MatDao mdao = MatDao.getInstance();
				Vector<MatDto> vecMat = mdao.selectAllMat();
				resultDate(vecMat);
				pnlCenterNorth.setVisible(true);
			}
		}
		
	}
	
	/**
	 * ���â���� �����ϴ� ������
	 * @author KH
	 *
	 */
	class UpdateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO ȭ��󿡼� �����ؼ� ������Ʈ�ϴ� ������
			System.out.println("actioncommand : " + e.getActionCommand()); // �Է¹����� ǥ��
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
					System.out.println(count + "�� ������Ʈ �Ϸ�.");
					if (quan == 0) {
						vecTxfQuan.get(i).setEditable(false);
					}
				}
			}
		}
		
	}
	
	/**
	 * ǰ�� ���
	 * @param vec
	 */
	private void resultMat(Vector<MatDto> vec) {
		removeAll();
		
		int i = 0;
		int size = vec.size();
		String[] msg = {"����", "ũ��1", "ũ��2", "ũ��3", "����", "�ܰ�", "����", "�԰�¥", "�ŷ�ó��ȣ"};

		pnlNo.setLayout(new GridLayout(size+1, 1));
		pnlResult.setLayout(new GridLayout(size+1, msg.length));
		
		
		vecNo.add(new JButton("��ȣ"));
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
	 * �԰�¥ �˻����
	 * @param vec
	 */
	private void resultDate(Vector<MatDto> vec) {
		removeAll();
		
		int i = 0;
		int size = vec.size();
		String[] msg = {"ǰ��", "����", "ũ��1", "ũ��2", "ũ��3", "����", "�ܰ�", "����", "�԰�¥", "�ŷ�ó��ȣ"};

		pnlNo.setLayout(new GridLayout(size+1, 1));
		pnlResult.setLayout(new GridLayout(size+1, msg.length));
		
		vecNo.add(new JButton("��ȣ"));
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
	 * �ŷ�ó �˻����
	 * @param vec
	 */
	private void resultCust(Vector<CustDto> vec) {
		removeAll();
		
		int i = 0;
		int size = vec.size();
		String[] msg = {"�ŷ�ó��ȣ", "�ŷ�ó��", "����ڹ�ȣ", "�ŷ�������", "��ȭ��ȣ"};
		
		pnlNo.setLayout(new GridLayout(size+1, 1));
		pnlResult.setLayout(new GridLayout(size+1, msg.length));
		
		vecNo.add(new JButton("��ȣ"));
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