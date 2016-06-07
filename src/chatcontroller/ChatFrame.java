package chatcontroller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Dimension2D;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import data.TalkInfo;

public class ChatFrame extends JFrame
{

	private static final long serialVersionUID = 2454801675788779790L;
	private String userName = null;
	private String friendName = null;
	private MessagePoor outputFeature = null;
	private TextArea inputFeature = null;
	private JScrollPane jsp = null;
	private Message sendMessage = null;
	private TalkInfoListener outputListener = null;

	public ChatFrame(String userName, String friendName, TalkInfoListener outputListener)
	{
		this.userName = userName;
		this.friendName = friendName;
		this.outputListener = outputListener;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension2D d = toolkit.getScreenSize();
		int ScreenWidth = (int) d.getWidth();
		int ScreenHeight = (int) d.getHeight();
		setSize(ScreenWidth * 3 / 5, ScreenHeight * 2 / 3);
		setLocation((ScreenWidth - getWidth()) / 2, (ScreenHeight - getHeight()) / 2);
		setTitle("Chat With " + friendName);

		initAllComponent();
		addKeyListener();
		addComponentListener();
	}

	public void initAllComponent()
	{
		// 输入框
		inputFeature = new TextArea();
		inputFeature.setPreferredSize(new Dimension(getWidth(), getHeight() / 3));
		// 输出框（消息池）
		outputFeature = new MessagePoor();
		jsp = new JScrollPane(outputFeature, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setDoubleBuffered(true);
		outputFeature.setJSP(jsp);
		add(jsp, BorderLayout.CENTER);
		add(inputFeature, BorderLayout.SOUTH);
	}

	public String getFriendName()
	{
		return friendName;
	}

	// 本地发送的消息
	public void sendMessage(TalkInfo talkInfo)
	{
		// 需要把消息加入到消息池
		outputFeature.addMessages(talkInfo, Message.Mode.send);
		outputFeature.addMessageCount();
		// 同时需要把消息发送给socket
		outputListener.sendMessageTOSocket(talkInfo);
	}

	// 网络接收到的消息
	public void addMessage(TalkInfo talkInfo)
	{
		// 把消息加入到消息池
		outputFeature.addMessages(talkInfo, Message.Mode.receive);
		outputFeature.addMessageCount();
	}

	public void addKeyListener()
	{
		inputFeature.addKeyListener(new KeyListener()
		{

			@Override
			public void keyTyped(KeyEvent e)
			{
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					inputFeature.setText(null);
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getModifiers() == InputEvent.CTRL_MASK && e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					inputFeature.append("\n");
				}
				else if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					TalkInfo talkInfo = new TalkInfo(userName, friendName, inputFeature.getText());
					sendMessage(talkInfo);
				}
			}
		});
	}

	public void addComponentListener()
	{
		addComponentListener(new ComponentAdapter()
		{
			@Override
			public void componentResized(ComponentEvent e)
			{
				jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
			}
		});
	}

	public static void main(String[] args)
	{
		ChatFrame frame = new ChatFrame("admin", "bppleman", null);
		frame.setDefaultCloseOperation(ChatFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
