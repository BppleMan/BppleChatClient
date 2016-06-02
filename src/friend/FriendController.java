package friend;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;

import chatcontroller.ChatController;
import chatcontroller.TalkSocket;
import data.FriendInfo;
import data.UserInfo;

public class FriendController
{
	private Socket socket = null;
	private TalkSocket talkSocket = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	private FriendListFrame friendFrame = null;
	private ArrayList<FriendInfo> friendList = null;
	private ArrayList<FriendCard> friendCards = null;
	private MouseListener mouseListener = null;
	private UserInfo userInfo = null;
	private UpdataFriendInfo updataFriendInfo = null;
	private ChatController chatController = null;

	public FriendController(Socket socket, ObjectInputStream ois, ObjectOutputStream oos, UserInfo userInfo)
	{
		this.userInfo = userInfo;
		this.oos = oos;
		this.ois = ois;
		friendCards = new ArrayList<>();
		// 通过服务器获取好友列表
		getFriendList();
		// 根据好友列表信息组装FriendCard
		initFriendCards();
		// 初始化鼠标监听器
		initMouseListener();
		friendFrame = new FriendListFrame(userInfo, friendCards, mouseListener);
		friendFrame.startAddAnimation();
		friendFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		friendFrame.setVisible(true);

		// 初始化好友状态更新组件
		updataFriendInfo = new UpdataFriendInfo(friendFrame, ois);
		updataFriendInfo.start();

		// 创建消息传输socket
		talkSocket = new TalkSocket(userInfo);

		// 创建消息管理器，用于将消息分发到不同的窗口
		chatController = new ChatController(talkSocket.getBUFF(), talkSocket.getOutputThread());
		chatController.start();
	}

	public void getFriendList()
	{
		GetFriendList getFriendList = new GetFriendList(ois, oos, userInfo);
		friendList = getFriendList.getList();
	}

	public void initFriendCards()
	{
		for (int i = 0; i < friendList.size(); i++)
		{
			String friendName = friendList.get(i).getFriendName();
			String friendState = friendList.get(i).getFriendState();
			friendCards.add(new FriendCard(friendName, friendState));
		}
		FriendManege.getInstance().setFriendCards(friendCards);
	}

	public void initMouseListener()
	{
		mouseListener = new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				// TODO 自动生成的方法存根
				if (e.getClickCount() == 2)
				{
					FriendCard friendCard = (FriendCard) e.getSource();
					CreatANewChatFrame(friendCard.getFriendName());
				}
			}
		};
	}

	public void CreatANewChatFrame(String friendName)
	{
		chatController.creatAChatFrame(userInfo, friendName);
	}

}
