package login;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.geom.Dimension2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import data.UserInfo;

public class LoginFrame extends JFrame
{
	private JLabel userLabel = new JLabel("UserName:");
	private JLabel passwordLabel = new JLabel("PassWord:");

	private JTextField userNameText = new JTextField("admin");
	private JPasswordField passWordText = new JPasswordField("admin");

	private JButton loginButton = new JButton("Login");
	private JButton registButton = new JButton("Regist");

	private UserInfo userInfo = null;
	private UserInfoListener userInfoListener = null;

	public LoginFrame()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension2D d = toolkit.getScreenSize();
		int ScreenWidth = (int) d.getWidth();
		int ScreenHeight = (int) d.getHeight();

		setSize(ScreenWidth / 4, ScreenHeight / 4);
		setLocation((ScreenWidth - getWidth()) / 2, (ScreenHeight - getHeight()) / 2);

		setLayout(new FlowLayout());

		userNameText.setPreferredSize(new Dimension(100, 30));
		passWordText.setPreferredSize(new Dimension(100, 30));
		loginButton.setPreferredSize(new Dimension(190, 30));
		registButton.setPreferredSize(new Dimension(190, 30));

		JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		JPanel registPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));

		userPanel.add(userLabel);
		userPanel.add(userNameText);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passWordText);
		loginPanel.add(loginButton);
		registPanel.add(registButton);

		add(userPanel);
		add(passwordPanel);
		add(loginPanel);
		add(registPanel);

		initUserInfoListener();
	}

	public UserInfo getUserInfo()
	{
		String pwd = String.valueOf(passWordText.getPassword());
		userInfo = new UserInfo(userNameText.getText(), pwd);
		return userInfo;
	}

	public JButton getRegistButton()
	{
		return registButton;
	}

	public JButton getLoginButton()
	{
		return loginButton;
	}

	public void initUserInfoListener()
	{
		userInfoListener = new UserInfoListener()
		{

			@Override
			public void setUserInfo(UserInfo userInfo)
			{
				userNameText.setText(userInfo.getUserName());
				passWordText.setText(userInfo.getPassWord());
				getContentPane().repaint();
			}
		};
	}

	public UserInfoListener getListener()
	{
		return userInfoListener;
	}
}
