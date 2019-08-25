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
import javax.swing.JComboBox;
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
public class MatInsert extends JDialog {
	
	Date now = new Date(System.currentTimeMillis());
	final SpinnerDateModel model = new SpinnerDateModel(now, null, null, Calendar.DAY_OF_WEEK);
	JSpinner spinDate = new JSpinner(model);
	final DateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
	JSpinner.DateEditor editor = new JSpinner.DateEditor(spinDate, "yyyy-MM-dd");
	
	JPanel pnlLbl = new JPanel();
	JPanel pnlTxf = new JPanel();
	JPanel pnlBtn = new JPanel();
	
	JLabel lblCateNo = new JLabel("ǰ�� : ", SwingConstants.RIGHT);
	JLabel lblOtherType = new JLabel("ǰ�� : ", SwingConstants.RIGHT);
	JLabel lblSize1 = new JLabel("ũ��1 : ", SwingConstants.RIGHT);
	JLabel lblSize2 = new JLabel("ũ��2 : ", SwingConstants.RIGHT);
	JLabel lblSize3 = new JLabel("ũ��3 : ", SwingConstants.RIGHT);
	JLabel lblQuantity = new JLabel("���� : ", SwingConstants.RIGHT);
	JLabel lblUnitPrice = new JLabel("�ܰ� : ", SwingConstants.RIGHT);
	JLabel lblUnitSet = new JLabel("���� : ", SwingConstants.RIGHT);
	JLabel lblRecDate = new JLabel("�԰��� : ", SwingConstants.RIGHT);
	JLabel lblCustNo = new JLabel("�ŷ�ó : ", SwingConstants.RIGHT);
	
	JComboBox<String> cmbCateNo = new JComboBox<>();
	Vector<Integer> vecCateNo = new Vector<>();
	JTextField txfOtherType = new JTextField();
	JTextField txfSize1 = new JTextField();
	JTextField txfSize2 = new JTextField();
	JTextField txfSize3 = new JTextField();
	JTextField txfQuantity = new JTextField();
	JTextField txfUnitPrice = new JTextField();
	JComboBox<Integer> cmbUnitSet = new JComboBox<>();
	JComboBox<String> cmbCustNo = new JComboBox<>();
	Vector<Integer> vecCustNo = new Vector<>();
	
	JButton btnCancel = new JButton("���");
	JButton btnConfirm = new JButton("Ȯ��");
	
	String date = "";
	
	// �ŷ�ó dao
	CustDao custDao = CustDao.getInstance();
	
	// ��ǰ dao
	MatDao matDao = MatDao.getInstance();
	
	// ��ư������
	ButtonListener btnListener = new ButtonListener();
	
	// ���͸�����
	NextListener nextListener = new NextListener();
	
	public MatInsert(Frame frame, String title) {
		super(frame, title);
		
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
		
		// ǰ��
		Vector<MatCateVo> vecMat = matDao.getMatList();
		for (MatCateVo vo : vecMat) {
			cmbCateNo.addItem(vo.getCateName());
			vecCateNo.add(vo.getCateNo());
		}
		
		// ����
		Vector<Integer> vecUnit = matDao.getUnitList();
		for (Integer unit : vecUnit) {
			cmbUnitSet.addItem(unit);
		}
		
		// �ŷ�ó���
//		Vector<CustDto> vecCust = custDao.getCustList();
//		for (CustDto dto : vecCust) {
//			cmbCustNo.addItem(dto.getCust_name());
//			vecCustNo.add(dto.getCust_no());
//		}
		setCustList();
		
		setLayout(new BorderLayout(10, 10));
		
		pnlLbl.setLayout(new GridLayout(10, 1));
		pnlLbl.add(lblCateNo);
		pnlLbl.add(lblOtherType);
		pnlLbl.add(lblSize1);
		pnlLbl.add(lblSize2);
		pnlLbl.add(lblSize3);
		pnlLbl.add(lblQuantity);
		pnlLbl.add(lblUnitPrice);
		pnlLbl.add(lblUnitSet);
		pnlLbl.add(lblRecDate);
		pnlLbl.add(lblCustNo);
		
		pnlTxf.setLayout(new GridLayout(10, 1));
		pnlTxf.add(cmbCateNo);
		pnlTxf.add(txfOtherType);
		pnlTxf.add(txfSize1);
		pnlTxf.add(txfSize2);
		pnlTxf.add(txfSize3);
		pnlTxf.add(txfQuantity);
		pnlTxf.add(txfUnitPrice);
		pnlTxf.add(cmbUnitSet);
		pnlTxf.add(spinDate);
		pnlTxf.add(cmbCustNo);
		
		pnlBtn.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlBtn.add(btnCancel);
		pnlBtn.add(btnConfirm);
		
		add(pnlLbl, BorderLayout.WEST);
		add(pnlTxf, BorderLayout.CENTER);
		add(pnlBtn, BorderLayout.SOUTH);
		
		setSize(200, 400);
		setVisible(false);
		
		btnCancel.addActionListener(btnListener);
		btnConfirm.addActionListener(btnListener);
		
		cmbCateNo.addActionListener(nextListener);
		txfOtherType.addActionListener(nextListener);
		txfSize1.addActionListener(nextListener);
		txfSize2.addActionListener(nextListener);
		txfSize3.addActionListener(nextListener);
		txfQuantity.addActionListener(nextListener);
		txfUnitPrice.addActionListener(nextListener);
		cmbUnitSet.addActionListener(nextListener);
		cmbCustNo.addActionListener(nextListener);
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj =  e.getSource();
			
			if (obj == btnConfirm) {
				
				int indexCate = cmbCateNo.getSelectedIndex();
				int indexCust = cmbCustNo.getSelectedIndex();

				int cateNo = vecCateNo.get(indexCate);
				String otherType = txfOtherType.getText();
				int size1;
				int size2;
				int size3;
				int quantity = Integer.parseInt(txfQuantity.getText());
				int unitPrice = Integer.parseInt(txfUnitPrice.getText());
				int unitSet = (int)cmbUnitSet.getSelectedItem();
				String receiveDate = date;
				int custNo = vecCustNo.get(indexCust);
				long itemSerial = 0;
				long no = setRecNo();
				if (itemSerial != no) {
					itemSerial = no;
				}
				
				if (txfSize1.getText().equals("")) {
					size1 = 0;
				} else {
					size1 = Integer.parseInt(txfSize1.getText());
				}
				if (txfSize2.getText().equals("")) {
					size2 = 0;
				} else {
					size2 = Integer.parseInt(txfSize2.getText());
				}
				if (txfSize3.getText().equals("")) {
					size3 = 0;
				} else {
					size3 = Integer.parseInt(txfSize3.getText());
				}
				
//				System.out.println(cateNo + otherType + size1 + size2 + size3 + quantity + unitPrice + unitSet + receiveDate + custNo);
				
				MatVo vo = new MatVo(cateNo, otherType, size1, size2, size3, quantity, unitPrice,
						unitSet, receiveDate, custNo, itemSerial);
				
				int count = matDao.insertMat(vo);
				System.out.println(count + "�� �Է¿Ϸ�.");
			}
			setClear();
			setVisible(false);
		}
		
	}
	
	class NextListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			
			if (obj == cmbCateNo) {
				int index = cmbCateNo.getSelectedIndex();
				int select = vecCateNo.get(index);
				switch (select) {
				case IItem.PLYWOOD :
					setPlywood();
					break;
				case IItem.SINK :
					setSink();
					break;
				case IItem.PIECE :
					setPiece();
					break;
				case IItem.ETC :
					setEtc();
					break;
				}
				txfOtherType.requestFocus();
			} else if (obj == txfOtherType) {
				txfSize1.requestFocus();
			} else if (obj == txfSize1) {
				txfSize2.requestFocus();
			} else if (obj == txfSize2) {
				txfSize3.requestFocus();
			} else if (obj == txfSize3) {
				txfQuantity.requestFocus();
			} else if (obj == txfQuantity) {
				txfUnitPrice.requestFocus();
			} else if (obj == txfUnitPrice) {
				cmbUnitSet.requestFocus();
			} else if (obj == cmbUnitSet) {
				cmbCustNo.requestFocus();
			}
		}
		
	}
	
	public void setCustList() {
		cmbCustNo.removeAllItems();
		vecCustNo.removeAllElements();
//		revalidate();
//		repaint();
		// �ŷ�ó���
		Vector<CustDto> vecCust = custDao.getCustList();
		for (CustDto dto : vecCust) {
			cmbCustNo.addItem(dto.getCust_name());
			vecCustNo.add(dto.getCust_no());
		}
	}
	
	public long setRecNo() {
		long itemSerialNo = 0;
		boolean flag = false;
		
		int indexCust = cmbCustNo.getSelectedIndex();
		
		String receiveDate = date;
		String date1 = receiveDate.substring(2, 4);
		String date2 = receiveDate.substring(5, 7);
		String date3 = receiveDate.substring(8, 10);
		int custNo = vecCustNo.get(indexCust);
		
		int serialNo = 1;
		String code = "";
		
		Vector<Long> vecReg = matDao.getSerialList();
		
		if (vecReg.isEmpty()) {
			code = date1 + date2 + date3 + custNo + String.format("%03d",serialNo);
			itemSerialNo = Long.parseLong(code);
		}
		
		while(true) {
			for (Long index : vecReg) {
				code = date1 + date2 + date3 + custNo + String.format("%03d",serialNo);
				System.out.println("code : " + code);
				System.out.println("long : " + index);
				if (!(code.equals(String.valueOf(index)))) {
					flag = true;
				} else {
					flag = false;
				}
				System.out.println(serialNo);
			}
			
			if (flag == true) {
				itemSerialNo = Long.parseLong(code);
				return itemSerialNo;
			} else {
				serialNo++;
			}
		
		}
	}
	
	private void setPlywood() {
		lblOtherType.setText("���� : ");
		lblSize1.setText("���� : ");
		lblSize2.setText("���� : ");
		lblSize3.setText("�β� : ");
	}
	
	private void setSink() {
		lblOtherType.setText("ǰ�� : ");
		lblSize1.setText("���� : ");
		lblSize2.setText("���� : ");
		lblSize3.setText("���� : ");
	}
	
	private void setPiece() {
		lblOtherType.setText("���� : ");
		lblSize1.setText("M(ũ��) : ");
		lblSize2.setText("P(��ġ) : ");
		lblSize3.setText("H(�����) : ");
	}
	
	private void setEtc() {
		lblOtherType.setText("ǰ�� : ");
		lblSize1.setText("ũ��1 : ");
		lblSize2.setText("ũ��2 : ");
		lblSize3.setText("ũ��3 : ");
	}
	
	private void setClear() {
		cmbCateNo.setSelectedIndex(0);
		txfOtherType.setText("");
		txfSize1.setText("");
		txfSize2.setText("");
		txfSize3.setText("");
		txfQuantity.setText("");
		txfUnitPrice.setText("");
		cmbUnitSet.setSelectedIndex(0);
		model.setValue(now);
		spinDate.setModel(model);
		cmbCustNo.setSelectedIndex(0);
	}
	
}
