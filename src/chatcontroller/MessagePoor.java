/**
 * 
 */
package chatcontroller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

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
	private int messageX = 0;
	private int messageY = 0;
	private int outputWidth = 0;

	/**
	 * @param b
	 * @param object
	 * 
	 */
	public MessagePoor(LayoutManager lm, boolean b, Dimension d)
	{
		super(lm, b);
		setPreferredSize(d);
		setBackground(Color.WHITE);

		// 每条消息的起始位置
		messageX = messageY = 10;
		outputWidth = (int) d.getWidth();
	}

	public void setJSP(JScrollPane jsp)
	{
		this.jsp = jsp;
	}

	public void addMessages(TalkInfo talkInfo)
	{
		message = new Message(talkInfo.getSendUser(), talkInfo.getMessage());
		message.setBounds(messageX, messageY + messageCount * 90, outputWidth - 10, 80);
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
