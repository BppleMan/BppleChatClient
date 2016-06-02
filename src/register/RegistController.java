package register;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import data.SocketPort;
import data.UserInfo;
import login.UserInfoListener;
import tool.PathSource;

public class RegistController
{
	private RegistFrame registFrame = null;
	private JButton registButton = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	private UserInfoListener userInfoListener = null;
	private JLabel registSuccess = null;
	ActionListener registAction = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				if (registSuccess != null)
				{
					registFrame.getContentPane().remove(registSuccess);
					registFrame.getContentPane().validate();
				}
				UserInfo userInfo = registFrame.getUserInfo();
				Socket socket = new Socket(PathSource.host, SocketPort.dataServerPort);
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				RegistValid valid = new RegistValid(userInfo, oos, ois);
				valid.validRegist();
				if (valid.getCanRegist())
				{
					System.out.println(userInfo.getUserName() + "注册成功");
					userInfoListener.setUserInfo(userInfo);
					registSuccess = new JLabel("注册成功", JLabel.CENTER);
					registSuccess.setPreferredSize(new Dimension(registFrame.getWidth(), 30));
					registFrame.getContentPane().add(registSuccess);
					registFrame.validate();
					ois.close();
					oos.close();
					socket.close();
					ois = null;
					oos = null;
					socket = null;
				}
				else
				{
					System.out.println(userInfo.getUserName() + "注册失败");
					registSuccess = new JLabel("注册失败", JLabel.CENTER);
					registSuccess.setPreferredSize(new Dimension(registFrame.getWidth(), 30));
					registFrame.getContentPane().add(registSuccess);
					registFrame.validate();
					ois.close();
					oos.close();
					socket.close();
					ois = null;
					oos = null;
					socket = null;
				}
				Runnable r = new Runnable()
				{
					public void run()
					{
						long time = System.currentTimeMillis();
						while (System.currentTimeMillis() - time < 3000)
						{
						}
						if (registSuccess != null)
						{
							registFrame.getContentPane().remove(registSuccess);
							registFrame.getContentPane().validate();
						}
					}
				};
				r.run();
			}
			catch (IOException ex)
			{
				showDialog();
			}
		}
	};

	public RegistController(Rectangle frame, UserInfoListener userInfoListener)
	{
		this.userInfoListener = userInfoListener;
		registFrame = new RegistFrame(frame);
		registFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		registFrame.setVisible(true);

		(new RegistInfoValid(registFrame.userTextField, registFrame.passwordTextFirld, registFrame.againpwdTextFirld))
				.start();

		registButton = registFrame.getRegistButton();
		registButton.addActionListener(registAction);
	}

	public void showDialog()
	{
		JDialog dialog = new JDialog(registFrame);
		Rectangle rect = registFrame.getBounds();
		rect.setLocation((int) rect.getX(), (int) (rect.getY() - rect.getHeight()));
		dialog.setBounds(rect);
		dialog.setTitle("非常抱歉哦～");
		JLabel label = new JLabel("我们的服务器正在维护，我们很快回来～");
		dialog.setLayout(new BorderLayout());
		label.setHorizontalAlignment(JLabel.CENTER);
		dialog.add(label);
		dialog.setVisible(true);
	}
}