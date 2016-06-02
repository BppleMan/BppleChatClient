/**
 * 
 */
package chatcontroller;

import java.util.EventListener;

import data.TalkInfo;

/**
 * @author BppleMan
 *
 */
public interface TalkInfoListener extends EventListener
{
	public void sendMessageTOSocket(TalkInfo talkInfo);
}
