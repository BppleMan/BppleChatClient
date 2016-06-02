/**
 * 
 */
package friend;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import data.CommandSource;
import data.FriendInfo;
import data.UserInfo;

/**
 * @author BppleMan
 *
 */
public class GetFriendList
{
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	private UserInfo userInfo = null;
	private ArrayList<FriendInfo> friendList = null;

	public GetFriendList(ObjectInputStream ois, ObjectOutputStream oos, UserInfo userInfo)
	{
		this.userInfo = userInfo;
		this.ois = ois;
		this.oos = oos;
	}

	public ArrayList<FriendInfo> getList()
	{
		friendList = new ArrayList<>();
		try
		{
			oos.writeObject(CommandSource.getFriendCommand);
			oos.writeObject(userInfo);
			Object object = ois.readObject();
			if (object instanceof ArrayList)
			{
				friendList = (ArrayList<FriendInfo>) object;
				System.out.println("接收成功");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return friendList;
	}
}
