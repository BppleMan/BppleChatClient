package register;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import data.CommandSource;
import data.UserInfo;

public class RegistValid
{
	private UserInfo userInfo = null;
	private String canRegist = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;

	public RegistValid(UserInfo userInfo, ObjectOutputStream oos, ObjectInputStream ois)
	{
		this.userInfo = userInfo;
		this.ois = ois;
		this.oos = oos;
	}

	public void validRegist()
	{
		try
		{
			oos.writeObject(CommandSource.registCommand);
			oos.writeObject(userInfo);
			canRegist = (String) ois.readObject();
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

	public boolean getCanRegist()
	{
		if (canRegist.equals(CommandSource.canRegistCommand))
			return true;
		else if (canRegist.equals(CommandSource.notCanRegistCommand))
			return false;
		return false;
	}
}
