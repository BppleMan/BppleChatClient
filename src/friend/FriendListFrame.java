package friend;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.geom.Dimension2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import data.FriendInfo;
import data.UserInfo;
import login.LoginController;

public class FriendListFrame extends JFrame
{
	private UserInfo userInfo;
	private JButton logoutButton = null;
	private ArrayList<FriendCard> friendCards = null;
	private MouseListener mouseListener = null;
	private Container rootCon = null;
	private JPanel myCon = null;
	private JScrollPane scrollPane = null;
	private int width, height;
	private Timer timer = null;

	public FriendListFrame(UserInfo userInfo, ArrayList<FriendCard> friendInfoList, MouseListener mouseListener)
	{
		this.userInfo = userInfo;
		this.friendCards = friendInfoList;
		this.mouseListener = mouseListener;
		initAllComponent();
	}

	public void initAllComponent()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension2D d = toolkit.getScreenSize();
		int ScreenWidth = (int) d.getWidth();
		int ScreenHeight = (int) d.getHeight();

		setSize(new Dimension(300, ScreenHeight - 200));
		setLocation(ScreenWidth - 350, 50);
		setTitle(userInfo.getUserName());

		rootCon = getContentPane();
		myCon = new JPanel(null, true);
		myCon.setPreferredSize(new Dimension(getWidth() - 20, 0));
		scrollPane = new JScrollPane(myCon);

		logoutButton = new JButton("退出登录");
		logoutButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
				new LoginController();
			}
		});

		rootCon.add(scrollPane, BorderLayout.CENTER);
		rootCon.add(logoutButton, BorderLayout.SOUTH);
		width = getWidth() - 25;
		height = 70;
	}

	public void addFriendToContainer(int i)
	{
		FriendCard friendCard = friendCards.get(i);
		friendCard.setBounds(-width, i * 80, width, height);
		friendCard.setToLocation(5, friendCard.getY());
		myCon.add(friendCard);
		myCon.repaint();
		myCon.setPreferredSize(new Dimension(getWidth() - 20, (i + 1) * 80));
		scrollPane.validate();
		friendCard.addMouseListener(mouseListener);
		new MoveAnimation(friendCard, myCon).start();
	}

	public void updataUserState(FriendInfo friendInfo)
	{
		for (FriendCard friendCard : friendCards)
		{
			if (friendCard.getFriendName().equals(friendInfo.getFriendName()))
			{
				friendCard.setFriendState(friendInfo.getFriendState());
			}
		}
	}

	public void startAddAnimation()
	{
		timer = new Timer(80, new ActionListener()
		{
			int i = 0;

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (i < friendCards.size())
				{
					addFriendToContainer(i);
					i++;
				}
				else
					timer.stop();
			}
		});
		timer.start();
	}

	public static void main(String[] args)
	{
		ArrayList<FriendCard> list = new ArrayList<>();
		list.add(new FriendCard("friend1", "offline"));
		list.add(new FriendCard("friend2", "offline"));
		list.add(new FriendCard("friend3", "offline"));
		list.add(new FriendCard("friend4", "offline"));
		FriendListFrame frame = new FriendListFrame(new UserInfo("admin", "123456"), list, null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.startAddAnimation();
	}
}
