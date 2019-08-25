package prj01;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class SignUp extends JDialog {
	
	JPanel pnlLbl = new JPanel();
	JPanel pnlTxf = new JPanel();
	JPanel pnlBtn = new JPanel();
	
	JLabel lblId = new JLabel("아이디 : ", SwingConstants.RIGHT);
	JLabel lblPw = new JLabel("비밀번호 : ", SwingConstants.RIGHT);
	JTextField txfId = new JTextField(15);
	JTextField txfPw = new JTextField(15);
	JButton btnCancel = new JButton("취소");
	JButton btnSignup = new JButton("회원가입");
	
	public SignUp(JFrame frame, String title, boolean modal) {
		super(frame, title, modal);
		
		setLayout(new BorderLayout(10, 10));
		pnlLbl.setLayout(new GridLayout(2, 1));
		pnlLbl.add(lblId);
		pnlLbl.add(lblPw);
		pnlTxf.setLayout(new GridLayout(2, 1));
		pnlTxf.add(txfId);
		pnlTxf.add(txfPw);
		pnlBtn.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlBtn.add(btnCancel);
		pnlBtn.add(btnSignup);
		
		add(pnlLbl, BorderLayout.WEST);
		add(pnlTxf, BorderLayout.CENTER);
		add(pnlBtn, BorderLayout.SOUTH);
		
		setSize(200, 150);
	}
}
