/**
 * 
 */
package friend;

import java.util.ArrayList;
import java.util.Vector;

import chatcontroller.ChatFrame;

/**
 * @author BppleMan
 *
 */
public class FriendManege
{
	public Vector<ChatFrame> chatFrames = null;
	public ArrayList<FriendCard> friendCards = null;
	private static FriendManege friendManege = new FriendManege();

	/**
	 * 
	 */
	private FriendManege()
	{
		chatFrames = new Vector<>();
	}

	public void setFriendCards(ArrayList<FriendCard> list)
	{
		this.friendCards = list;
	}

	public static FriendManege getInstance()
	{
		return friendManege;
	}

	public void addChatFrame(ChatFrame cf)
	{
		chatFrames.addElement(cf);
	}

	public void removeChatFrame(ChatFrame cf)
	{
		chatFrames.remove(cf);
	}

	public ChatFrame getChatFrame(String friendName)
	{
		for (ChatFrame chatFrame : chatFrames)
		{
			if (chatFrame.getFriendName().equals(friendName))
				return chatFrame;
		}
		return null;
	}

	public FriendCard getFriendCard(String friendName)
	{
		for (FriendCard friendCard : friendCards)
		{
			if (friendCard.getFriendName().equals(friendName))
				return friendCard;
		}
		return null;
	}
}
