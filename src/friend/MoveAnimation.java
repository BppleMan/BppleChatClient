package friend;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * 
 */

/**
 * @author BppleMan
 *
 */
public class MoveAnimation extends Thread
{
	FriendCard friendCard = null;
	Container conPanel = null;
	int fromX, fromY, toX, toY;
	double theta = 0;
	Timer timer = null;

	/**
	 * 
	 */
	public MoveAnimation(FriendCard fi, Container con)
	{
		friendCard = fi;
		conPanel = con;
		fromX = friendCard.getX();
		fromY = friendCard.getY();
		toX = friendCard.toX;
		toY = friendCard.toY;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		timer = new Timer(1, new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (fromX < toX)
				{
					fromX += 4;
					friendCard.setLocation(fromX, fromY);
					conPanel.repaint();
				}
				else
					timer.stop();
			}
		});
		timer.start();
	}
}
