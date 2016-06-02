package chatcontroller;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

public final class Message extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1798322313886052177L;
	JLabel spokesPerson = new JLabel();
	JLabel spokesString = new JLabel();

	public Message(String name, String string)
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));

		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		Font font1 = new Font("Menlo-Regular", Font.PLAIN, 14);
		Font font2 = new Font("Menlo-Regular", Font.PLAIN, 18);
		spokesPerson.setFont(font1);
		spokesPerson.setText(name + " " + date.format(new Date()));
		spokesString.setFont(font2);
		spokesString.setText(string);

		spokesPerson.setPreferredSize(new Dimension(400, 30));
		spokesString.setPreferredSize(new Dimension(400, 40));

		add(spokesPerson);
		add(spokesString);

		addComponentListener();
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}

	public void addComponentListener()
	{
		addComponentListener(new ComponentAdapter()
		{
			@Override
			public void componentResized(ComponentEvent e)
			{
				spokesPerson.setPreferredSize(new Dimension(getWidth(), 30));
				spokesString.setPreferredSize(new Dimension(getWidth(), 40));
				repaint();
			}
		});
	}
}
