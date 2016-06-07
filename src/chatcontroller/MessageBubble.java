package chatcontroller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MessageBubble extends JPanel
{
	private Message.Mode mode;
	private JTextArea messageArea = null;

	public MessageBubble(String messageString, Message.Mode mode)
	{
		this.mode = mode;
		messageArea = new JTextArea(messageString);
		messageArea.setOpaque(false);
		Font font = new Font("Menlo-Regular", Font.PLAIN, 18);
		messageArea.setFont(font);
		messageArea.setEditable(false);

		add(messageArea);
		setBorder(null);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if (mode == Message.Mode.send)
		{
			RoundRectangle2D rr2d = new RoundRectangle2D.Double(0, 0, getWidth() - 10, 40, 20, 20);
			int[] xPoints = { getWidth() - 10, getWidth(), getWidth() - 10 };
			int[] yPoints = { getHeight() * 1 / 3, getHeight() * 2 / 6, getHeight() * 2 / 3 };
			int nPoints = xPoints.length;
			g2d.setColor(new Color(212, 237, 244, 255));
			g2d.fillPolygon(xPoints, yPoints, nPoints);
			g2d.fill(rr2d);
		}
		else
		{
			RoundRectangle2D rr2d = new RoundRectangle2D.Double(10, 0, getWidth() - 10, 40, 20, 20);
			int[] xPoints = { 10, 0, 10 };
			int[] yPoints = { getHeight() * 1 / 3, getHeight() * 2 / 6, getHeight() * 2 / 3 };
			int nPoints = xPoints.length;
			g2d.setColor(new Color(212, 237, 244, 255));
			g2d.fillPolygon(xPoints, yPoints, nPoints);
			g2d.fill(rr2d);
		}
	}
}
