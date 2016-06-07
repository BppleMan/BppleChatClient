package login;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
	private LoginFrame loginFrame = null;
	private FriendController friendController = null;
	private ActionListener registAction = null;
	private ActionListener loginAction = null;
	private KeyListener enterkeyListener = null;

	public LoginController()
	{
		loginFrame = new LoginFrame();
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setVisible(true);

		initListener();
		loginFrame.getLoginButton().addActionListener(loginAction);
		loginFrame.getRegistButton().addActionListener(registAction);

		loginFrame.getUserNameText().addKeyListener(enterkeyListener);
		loginFrame.getPassWordText().addKeyListener(enterkeyListener);
	}

	public void showErrorDialog(String title, String errorString)
	{
		JDialog dialog = new JDialog(loginFrame, title, true);
		Rectangle rect = loginFrame.getBounds();
		rect.setLocation((int) rect.getX(), (int) (rect.getY() - rect.getHeight()));
		dialog.setBounds(rect);
		JLabel label = new JLabel(errorString);
		dialog.setLayout(new BorderLayout());
		label.setHorizontalAlignment(JLabel.CENTER);
		dialog.add(label);
		dialog.setVisible(true);
	}

	public void initListener()
	{
		enterkeyListener = new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					loginFrame.getLoginButton().doClick(10);
				}
			}
		};
		loginAction = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
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
							showErrorDialog("非常抱歉哦～", "密码输错了，再好好想想～");
						}
						else if (loginCommand.equals(CommandSource.notFoundUser))
						{
							// 显示一个未注册错误弹窗
							showErrorDialog("非常抱歉哦～", "您还没有注册哦，先注册吧");
						}
					}
					catch (IOException e1)
					{
						// 显示一个网络错误弹窗
						showErrorDialog("非常抱歉哦～", "我们的服务器正在维护，我们很快回来～");
					}
				}
				catch (Exception e2)
				{
					if (e2.getMessage().equals("Not found username"))
					{
						showErrorDialog("非常抱歉哦～", "请正确输入用户名");
					}
					else
					{
						showErrorDialog("非常抱歉哦～", "请正确输入密码");
					}
				}
			}
		};
		registAction = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				RegistController registController = new RegistController(loginFrame.getBounds(),
						loginFrame.getListener());
			}
		};
	}
}
