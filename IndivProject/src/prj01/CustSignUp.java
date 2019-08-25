package prj01;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


@SuppressWarnings("serial")
public class CustSignUp extends JDialog implements ActionListener {
	
	Date now = new Date(System.currentTimeMillis());
	final SpinnerDateModel model = new SpinnerDateModel(now, null, null, Calendar.DAY_OF_WEEK);
	JSpinner spinDate = new JSpinner(model);
	final DateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
	JSpinner.DateEditor editor = new JSpinner.DateEditor(spinDate, "yyyy-MM-dd");
	
	
	JPanel pnlLbl = new JPanel();
	JPanel pnlTxf = new JPanel();
	JPanel pnlBtn = new JPanel();
	JPanel pnlLic = new JPanel();
	JPanel pnlDate = new JPanel();
	JPanel pnlPhone = new JPanel();
	
	JLabel lblNo = new JLabel("�ŷ�ó��ȣ : ", SwingConstants.RIGHT);
	JLabel lblName = new JLabel("�ŷ�ó�̸� : ", SwingConstants.RIGHT);
	JLabel lblLicense = new JLabel("����ڹ�ȣ : ", SwingConstants.RIGHT);
	JLabel lblDate = new JLabel("�ŷ������� : ", SwingConstants.RIGHT);
	JLabel lblPhone = new JLabel("�� ȭ �� ȣ : ", SwingConstants.RIGHT);
	String[] strDash = {" - "," - ",""};
	JLabel[] lblDash = new JLabel[strDash.length];
	JTextField txfNo = new JTextField(15);
	JTextField txfName = new JTextField(15);
	
	int[] licF = {3,2,5};
	int[] phoneF = {3,4,4};
	
	JTextField[] txfLic = new JTextField[licF.length];
	JTextField[] txfPhone = new JTextField[phoneF.length];
	
	JButton btnCancel = new JButton("���");
	JButton btnSignup = new JButton("���");
	
	String date = "";
	
	CustDao dao = CustDao.getInstance();
	
	public CustSignUp(Frame frame, String title, boolean modal) {
		super(frame, title, modal);
		
		JFormattedTextField ftxf = editor.getTextField();
		ftxf.setEditable(true);
		ftxf.setHorizontalAlignment(JTextField.CENTER);
		date = datef.format((Date)model.getValue());
		spinDate.setEditor(editor);
		spinDate.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				Date value = (Date)model.getValue();
				Date next = (Date)model.getNextValue();
				if (value != null && next != null) {
//					System.out.println("value = " + datef.format(value) + "\t" + "next = " + datef.format(next));
					date = datef.format(value);
				}
			}
		});
		
		for (int i = 0; i < strDash.length; i++) {
			lblDash[i] = new JLabel(strDash[i]);
		}
		
		setLayout(new BorderLayout(10, 10));
		// �ŷ�ó ��� ���
		pnlLbl.setLayout(new GridLayout(5, 1));
		pnlLbl.add(lblNo);
		pnlLbl.add(lblName);
		pnlLbl.add(lblLicense);
		pnlLbl.add(lblDate);
		pnlLbl.add(lblPhone);
		// �ŷ�ó ���� �Է¹޴ºκ�
		pnlTxf.setLayout(new GridLayout(5, 1));
		
		txfNo.setEditable(false);
		
		pnlTxf.add(txfNo); // ��ȣ, �ڵ� ������
		pnlTxf.add(txfName); // �ŷ�ó��
		pnlLic.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5)); // ����ڹ�ȣ
		for (int i = 0; i < licF.length; i++) {
			txfLic[i] = new JTextField(licF[i]);
			lblDash[i] = new JLabel(strDash[i]);
			pnlLic.add(txfLic[i]);
			pnlLic.add(lblDash[i]);
		}
		pnlTxf.add(pnlLic);
		pnlDate.setLayout(new BorderLayout()); // ��¥
		pnlDate.add(spinDate);
		pnlTxf.add(pnlDate);
		pnlPhone.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5)); // ��ȭ��ȣ
		for (int i = 0; i < phoneF.length; i++) {
			txfPhone[i] = new JTextField(phoneF[i]);
			lblDash[i] = new JLabel(strDash[i]);
			pnlPhone.add(txfPhone[i]);
			pnlPhone.add(lblDash[i]);
		}
		pnlTxf.add(pnlPhone);
		// ��� Ȯ�� ��ư
		pnlBtn.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlBtn.add(btnCancel);
		pnlBtn.add(btnSignup);
		
		
		add(pnlLbl, BorderLayout.WEST);
		add(pnlTxf, BorderLayout.CENTER);
		add(pnlBtn, BorderLayout.SOUTH);
		
		setSize(300, 250);
		
		btnCancel.addActionListener(this);
		btnSignup.addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String comm = e.getActionCommand();
		
		if (comm.equals(btnCancel.getText())) {
			System.out.println("��ҵ�");
			setClear();
		}
		if (comm.equals(btnSignup.getText())) {
			int cno = Integer.parseInt(txfNo.getText());
			String cname = txfName.getText();
			int[] lic = new int[licF.length];
			int[] phone = new int[phoneF.length];
			for (int i = 0; i < licF.length; i++) {
				if (txfLic[i].getText().equals("")) {
					lic[i] = 0;
				} else {
					lic[i] = Integer.parseInt(txfLic[i].getText());
				}
			}
			String date = this.date;
			for (int i = 0; i < phoneF.length; i++) {
				if (txfPhone[i].getText().equals("")) {
					phone[i] = 0;
				} else {
					phone[i] = Integer.parseInt(txfPhone[i].getText());
				}
			}
			System.out.println("cno : " + cno);
			CustVo vo = new	CustVo(cno, cname, lic[0], lic[1], lic[2], 
						date, phone[0], phone[1], phone[2]);
			int num = dao.insertCust(vo);
			System.out.println(num + "�� �Է� �Ϸ�.");
		}
		setClear();
//		dispose(); // �޸𸮿��� ���� (���α׷� ����)
		setVisible(false); // �޸𸮿� �����ϰ�, ȭ�鿡�� �Ⱥ���
	}
	
	public void setCustNo() {
		int custNo = 1001;
		int index = 0;
		Vector<CustDto> vecCustNo = dao.getCustNo();
		for (CustDto dto : vecCustNo) {
			index = dto.getCust_no();
//			System.out.println("index : " + index);
//			System.out.println("custNo : " + custNo);
			if (custNo != index) {
				txfNo.setText(String.valueOf(custNo));
				break;
			}
			custNo++;
		}
		
		if(custNo >= index || vecCustNo.isEmpty()) {
			txfNo.setText(String.valueOf(custNo));
		}
	}
	
	private void setClear() {
		txfName.setText("");
		txfLic[0].setText("");
		txfLic[1].setText("");
		txfLic[2].setText("");
		model.setValue(now);
		spinDate.setModel(model);
		txfPhone[0].setText("");
		txfPhone[1].setText("");
		txfPhone[2].setText("");
	}
}
