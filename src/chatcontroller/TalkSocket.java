/**
 * 
 */
package chatcontroller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import data.SocketPort;
import data.UserInfo;
import tool.PathSource;

/**
 * @author BppleMan
 *
 */
public class TalkSocket
{
	private Socket socket = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	private TalkInfoBuff talkInfoBuff = null;
	private ReceiveMessage receiveMessage = null;
	private SendMessage sendMessage = null;

	/**
	 * 
	 */
	public TalkSocket(UserInfo userInfo)
	{
		try
		{
			socket = new Socket(PathSource.host, SocketPort.talkServerPort);
			System.out.println("连接成功");
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());

			talkInfoBuff = new TalkInfoBuff();
			System.out.println("到这里");
			oos.writeObject(userInfo);
			System.out.println("发送成功");
			receiveMessage = new ReceiveMessage(talkInfoBuff, ois);
			receiveMessage.start();
			sendMessage = new SendMessage(oos);
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public SendMessage getOutputThread()
	{
		return sendMessage;
	}

	public ReceiveMessage getInputThread()
	{
		return receiveMessage;
	}

	public ObjectInputStream getOIS()
	{
		return ois;
	}

	public ObjectOutputStream getOOS()
	{
		return oos;
	}

	public TalkInfoBuff getBUFF()
	{
		return talkInfoBuff;
	}
}
