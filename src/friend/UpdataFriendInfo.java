/**
 * 
 */
package friend;

import java.io.IOException;
import java.io.ObjectInputStream;

import data.FriendInfo;

/**
 * @author BppleMan
 *
 */
public class UpdataFriendInfo extends Thread
{
	private ObjectInputStream ois = null;
	private FriendListFrame friendListFrame = null;

	/**
	 * 
	 */
	public UpdataFriendInfo(FriendListFrame friendListFrame, ObjectInputStream ois)
	{
		this.friendListFrame = friendListFrame;
		this.ois = ois;
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
			try
			{
				FriendInfo friendInfo = (FriendInfo) ois.readObject();
				friendListFrame.updataUserState(friendInfo);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}

}
