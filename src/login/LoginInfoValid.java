package login;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import data.CommandSource;
import data.UserInfo;

public class LoginInfoValid
{
	private Socket socket = null;
	private UserInfo userInfo = null;
	private String canLogin = null;
	private String loginCommand = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;

	public LoginInfoValid(Socket socket, ObjectInputStream ois, ObjectOutputStream oos, UserInfo userInfo)
	{
		this.userInfo = userInfo;
		this.socket = socket;
		this.ois = ois;
		this.oos = oos;
	}

	public String CanLogin()
	{
		try
		{
			oos.writeObject(CommandSource.loginCommand);
			try
			{
				Thread.sleep(300);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			oos.writeObject(userInfo);
			loginCommand = (String) ois.readObject();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return loginCommand;
	}
}
