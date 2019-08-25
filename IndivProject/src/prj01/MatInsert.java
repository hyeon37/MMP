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
	
	JLabel lblCateNo = new JLabel("품목 : ", SwingConstants.RIGHT);
	JLabel lblOtherType = new JLabel("품명 : ", SwingConstants.RIGHT);
	JLabel lblSize1 = new JLabel("크기1 : ", SwingConstants.RIGHT);
	JLabel lblSize2 = new JLabel("크기2 : ", SwingConstants.RIGHT);
	JLabel lblSize3 = new JLabel("크기3 : ", SwingConstants.RIGHT);
	JLabel lblQuantity = new JLabel("수량 : ", SwingConstants.RIGHT);
	JLabel lblUnitPrice = new JLabel("단가 : ", SwingConstants.RIGHT);
	JLabel lblUnitSet = new JLabel("단위 : ", SwingConstants.RIGHT);
	JLabel lblRecDate = new JLabel("입고일 : ", SwingConstants.RIGHT);
	JLabel lblCustNo = new JLabel("거래처 : ", SwingConstants.RIGHT);
	
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
	
	JButton btnCancel = new JButton("취소");
	JButton btnConfirm = new JButton("확인");
	
	String date = "";
	
	// 거래처 dao
	CustDao custDao = CustDao.getInstance();
	
	// 물품 dao
	MatDao matDao = MatDao.getInstance();
	
	// 버튼리스너
	ButtonListener btnListener = new ButtonListener();
	
	// 엔터리스너
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
		
		// 품목
		Vector<MatCateVo> vecMat = matDao.getMatList();
		for (MatCateVo vo : vecMat) {
			cmbCateNo.addItem(vo.getCateName());
			vecCateNo.add(vo.getCateNo());
		}
		
		// 단위
		Vector<Integer> vecUnit = matDao.getUnitList();
		for (Integer unit : vecUnit) {
			cmbUnitSet.addItem(unit);
		}
		
		// 거래처목록
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
				System.out.println(count + "행 입력완료.");
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
		// 거래처목록
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
		lblOtherType.setText("색상 : ");
		lblSize1.setText("가로 : ");
		lblSize2.setText("세로 : ");
		lblSize3.setText("두께 : ");
	}
	
	private void setSink() {
		lblOtherType.setText("품명 : ");
		lblSize1.setText("가로 : ");
		lblSize2.setText("세로 : ");
		lblSize3.setText("높이 : ");
	}
	
	private void setPiece() {
		lblOtherType.setText("종류 : ");
		lblSize1.setText("M(크기) : ");
		lblSize2.setText("P(피치) : ");
		lblSize3.setText("H(나사산) : ");
	}
	
	private void setEtc() {
		lblOtherType.setText("품명 : ");
		lblSize1.setText("크기1 : ");
		lblSize2.setText("크기2 : ");
		lblSize3.setText("크기3 : ");
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
