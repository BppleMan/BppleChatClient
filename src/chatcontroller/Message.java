package chatcontroller;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import tool.PathSource;

public final class Message extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1798322313886052177L;
	private MessageBubble messageBubble = null;
	private JTextArea userNameArea = null;
	private Image image = null;
	private int imageWidth = 40;
	private int imageHeight = 40;
	private int stringWidth = 0;
	private int nameWidth = 0;

	static public enum Mode
	{
		send, receive
	};

	private Mode messageMode;

	public Message(String name, String string, Mode mode)
	{
		setLayout(null);
		setOpaque(false);
		messageMode = mode;

		stringWidth = string.length() * 20;
		nameWidth = name.length() * 12;
		Font font = new Font("Menlo-Regular", Font.PLAIN, 14);
		image = this.getToolkit().getImage(PathSource.iconimagePath);
		userNameArea = new JTextArea(name);
		userNameArea.setFont(font);
		userNameArea.setSize(nameWidth, 30);
		userNameArea.setEditable(false);
		userNameArea.setOpaque(false);
		messageBubble = new MessageBubble(string, mode);

		add(userNameArea);
		add(messageBubble);
		setBorder(null);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (messageMode == Mode.send)
		{
			userNameArea.setLocation(getWidth() - imageWidth - userNameArea.getWidth(), 0);
			g2d.drawImage(image, getWidth() - imageWidth, 0, imageWidth, imageHeight, this);
			messageBubble.setBounds(getWidth() - imageWidth - stringWidth, 20, stringWidth, 40);
			messageBubble.revalidate();
		}
		else if (messageMode == Mode.receive)
		{
			userNameArea.setLocation(imageWidth + 20, 0);
			g2d.drawImage(image, 0, 0, 40, 40, this);
			messageBubble.setBounds(imageWidth, 20, stringWidth, 40);
			messageBubble.revalidate();
		}
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(2, 1));
		frame.setBounds(200, 0, 500, 200);

		Message m1 = new Message("admin", "hello", Message.Mode.send);
		m1.setPreferredSize(new Dimension(400, 100));
		m1.setBounds(40, 10, 400, 70);
		frame.add(m1);
		Message m2 = new Message("bppleman", "hello", Message.Mode.receive);
		m2.setPreferredSize(new Dimension(400, 100));
		m2.setBounds(40, 90, 400, 70);
		frame.add(m2);

		frame.setVisible(true);
	}
}
