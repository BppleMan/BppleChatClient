/**
 * 
 */
package chatcontroller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import data.TalkInfo;
import friend.FriendCard;
import friend.FriendManege;

/**
 * @author BppleMan
 *
 */
public class ChatController extends Thread
{
	private SendMessage sendMessage = null;
	private TalkInfoBuff talkInfoBuff = null;
	private WindowListener windowListener = null;

	public ChatController(TalkInfoBuff talkInfoBuff, SendMessage sendMessage)
	{
		this.talkInfoBuff = talkInfoBuff;
		this.sendMessage = sendMessage;
		initWindowListener();
	}

	public void creatAChatFrame(String userName, String friendName)
	{
		ChatFrame chatFrame = new ChatFrame(userName, friendName, sendMessage.getTalkInfoListener());
		chatFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		chatFrame.setVisible(true);
		FriendManege.getInstance().addChatFrame(chatFrame);
		chatFrame.addWindowListener(windowListener);
	}

	public void initWindowListener()
	{
		windowListener = new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				ChatFrame temp = (ChatFrame) e.getSource();
				FriendManege.getInstance().removeChatFrame(temp);
			}
		};
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		while (true)
		{
			if (!talkInfoBuff.isEmpty())
			{
				TalkInfo talkInfo = talkInfoBuff.getTalkInfo(0);
				ChatFrame chatFrame = FriendManege.getInstance().getChatFrame(talkInfo.getSendUser());
				if (chatFrame != null)
				{
					chatFrame.addMessage(talkInfo);
					talkInfoBuff.remove(talkInfo);
				}
				else
				{
					FriendCard friendCard = FriendManege.getInstance().getFriendCard(talkInfo.getSendUser());
					friendCard.updataNotifyLabel();
				}
			}
		}
	}
}
