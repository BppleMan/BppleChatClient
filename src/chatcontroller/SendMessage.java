/**
 * 
 */
package chatcontroller;

import java.io.IOException;
import java.io.ObjectOutputStream;

import data.TalkInfo;

/**
 * @author BppleMan
 *
 */
public class SendMessage implements TalkInfoListener
{
	private ObjectOutputStream oos = null;
	private TalkInfoListener talkInfoListener = this;

	/**
	 * 
	 */
	public SendMessage(ObjectOutputStream oos)
	{
		this.oos = oos;
	}

	public TalkInfoListener getTalkInfoListener()
	{
		return talkInfoListener;
	}

	public void sendTalkInfo(TalkInfo talkInfo)
	{
		try
		{
			oos.writeObject(talkInfo);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see chatcontroller.TalkInfoListener#sendMessageTOSocket(data.TalkInfo)
	 */
	@Override
	public void sendMessageTOSocket(TalkInfo talkInfo)
	{
		sendTalkInfo(talkInfo);
	}
}
