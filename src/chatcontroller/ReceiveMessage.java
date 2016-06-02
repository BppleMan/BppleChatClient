/**
 * 
 */
package chatcontroller;

import java.io.IOException;
import java.io.ObjectInputStream;

import data.TalkInfo;

/**
 * @author BppleMan
 *
 */
public class ReceiveMessage extends Thread
{
	private TalkInfoBuff talkInfoBuff = null;
	private ObjectInputStream ois = null;

	/**
	 * 
	 */
	public ReceiveMessage(TalkInfoBuff talkInfoBuff, ObjectInputStream ois)
	{
		this.talkInfoBuff = talkInfoBuff;
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
				TalkInfo talkInfo = (TalkInfo) ois.readObject();
				talkInfoBuff.apend(talkInfo);
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
