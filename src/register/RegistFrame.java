package register;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import data.UserInfo;

public class RegistFrame extends JFrame
{
	// 定义用户名、密码、重复密码的Label
	JLabel userLabel = new JLabel("UserName:");
	JLabel passwordLabel = new JLabel("PassWord:");
	JLabel againPwdLabel = new JLabel("AgainPwd:");

	// 定义用户名、密码、重复密码的输入框
	JTextField userTextField = new JTextField("123456");
	JPasswordField passwordTextFirld = new JPasswordField("123456");
	JPasswordField againpwdTextFirld = new JPasswordField("123456");

	// 定义“注册”按钮
	JButton registButton = new JButton("Regist");

	// 定义四个容器分别装用户组、密码组、重复密码组、注册按钮
	JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 1));
	JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 1));
	JPanel againpwdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 1));
	JPanel registPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));

	// 输入的信息用userInfo封装
	UserInfo userInfo = null;

	public RegistFrame(Rectangle frame)
	{
		setBounds(frame);
		setLayout(new FlowLayout());

		userTextField.setPreferredSize(new Dimension(100, 30));
		passwordTextFirld.setPreferredSize(new Dimension(100, 30));
		againpwdTextFirld.setPreferredSize(new Dimension(100, 30));
		registButton.setPreferredSize(new Dimension(190, 30));

		userPanel.add(userLabel);
		userPanel.add(userTextField);

		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordTextFirld);

		againpwdPanel.add(againPwdLabel);
		againpwdPanel.add(againpwdTextFirld);

		registPanel.add(registButton);

		add(userPanel);
		add(passwordPanel);
		add(againpwdPanel);
		add(registPanel);

	}

	public UserInfo getUserInfo()
	{
		String pwd = String.valueOf(passwordTextFirld.getPassword());
		userInfo = new UserInfo(userTextField.getText(), pwd);
		return userInfo;
	}

	public JButton getRegistButton()
	{
		return registButton;
	}

}