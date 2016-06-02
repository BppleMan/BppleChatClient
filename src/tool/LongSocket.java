/**
 * 
 */
package tool;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import data.SocketPort;
import data.UserInfo;

/**
 * @author BppleMan
 *
 */
public class LongSocket extends Thread
{
	private static LongSocket singleLongSocket = null;
	private static UserInfo userInfo = null;
	private Socket socket = null;

	public static LongSocket getLongSocket(UserInfo userInfo)
	{
		if (singleLongSocket == null)
		{
			LongSocket.userInfo = userInfo;
			singleLongSocket = new LongSocket();
		}
		return singleLongSocket;
	}

	/**
	 * 
	 */
	private LongSocket()
	{
		try
		{
			socket = new Socket("localhost", SocketPort.listenConnectPort);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			System.out.println(userInfo);
			oos.writeObject(userInfo);
			System.out.println("建立长链接");
			start();
		}
		catch (UnknownHostException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		try
		{
			while (true)
				socket.sendUrgentData(0xff);
		}
		catch (IOException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
