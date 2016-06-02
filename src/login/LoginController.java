package login;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import data.CommandSource;
import data.SocketPort;
import data.UserInfo;
import friend.FriendController;
import register.RegistController;
import tool.PathSource;

public class LoginController
{
	private Socket socket = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	private UserInfo userInfo = null;
	private String userName = "admin";
	private String passWord = "admin";
	private LoginFrame loginFrame = new LoginFrame();
	private FriendController friendController = null;

	ActionListener loginAction = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JButton btn = (JButton) e.getSource();
			if (btn.getText().equals("Login"))
			{
				userInfo = loginFrame.getUserInfo();
				try
				{
					socket = new Socket(PathSource.host, SocketPort.dataServerPort);
					oos = new ObjectOutputStream(socket.getOutputStream());
					ois = new ObjectInputStream(socket.getInputStream());
					LoginInfoValid valid = new LoginInfoValid(socket, ois, oos, userInfo);
					String loginCommand = valid.CanLogin();
					if (loginCommand.equals(CommandSource.canLoginCommand))
					{
						// 登录成功
						System.out.println(userInfo.getUserName() + "登录成功");
						friendController = new FriendController(socket, ois, oos, userInfo);
						loginFrame.setVisible(false);
					}
					else if (loginCommand.equals(CommandSource.passWordError))
					{
						// 显示一个密码错误弹窗
						showErrorDialog("密码输错了，再好好想想～");
					}
					else if (loginCommand.equals(CommandSource.notFoundUser))
					{
						// 显示一个未注册错误弹窗
						showErrorDialog("您还没有注册哦，先注册吧");
					}
				}
				catch (IOException e1)
				{
					// 显示一个网络错误弹窗
					showErrorDialog("我们的服务器正在维护，我们很快回来～");
				}
			}
		}
	};

	ActionListener registAction = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JButton btn = (JButton) e.getSource();
			if (btn.getText().equals("Regist"))
			{
				RegistController registController = new RegistController(loginFrame.getBounds(),
						loginFrame.getListener());
			}
		}
	};

	public LoginController()
	{
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setVisible(true);
		// 获取登陆按钮
		JButton button = loginFrame.getLoginButton();
		button.addActionListener(loginAction);
		// 获取注册按钮
		button = loginFrame.getRegistButton();
		button.addActionListener(registAction);
	}

	public void showErrorDialog(String errorString)
	{
		JDialog dialog = new JDialog(loginFrame, "非常抱歉哦～", true);
		Rectangle rect = loginFrame.getBounds();
		rect.setLocation((int) rect.getX(), (int) (rect.getY() - rect.getHeight()));
		dialog.setBounds(rect);
		JLabel label = new JLabel(errorString);
		dialog.setLayout(new BorderLayout());
		label.setHorizontalAlignment(JLabel.CENTER);
		dialog.add(label);
		dialog.setVisible(true);
	}
}
