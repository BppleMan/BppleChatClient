/**
 * 
 */
package chatcontroller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.TalkInfo;

/**
 * @author BppleMan
 *
 */
public class MessagePoor extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2053112648057400774L;
	private JScrollPane jsp = null;
	private Message message = null;
	private int messageCount = 0;
	private int outputWidth = 0;

	/**
	 * @param b
	 * @param object
	 * 
	 */
	public MessagePoor()
	{
		super(new GridLayout(0, 1), true);
		setBackground(Color.WHITE);
	}

	public void setJSP(JScrollPane jsp)
	{
		this.jsp = jsp;
	}

	public void addMessages(TalkInfo talkInfo, Message.Mode mode)
	{
		message = new Message(talkInfo.getSendUser(), talkInfo.getMessage(), mode);
		add(message);
		repaint();
		setPreferredSize(new Dimension(outputWidth, 20 + (messageCount + 1) * 90));
		jsp.repaint();
		jsp.validate();
		jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
	}

	public void addMessageCount()
	{
		this.messageCount++;
	}
}
