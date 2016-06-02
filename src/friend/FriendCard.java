package friend;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import tool.PathSource;

public class FriendCard extends JButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4235032769388998042L;
	private ImageIcon imageIcon = null;
	private Image image = null;
	private JLabel friendNameLabel = null;
	private JLabel friendStateLabel = null;
	private JButton imageButton = null;
	private JLabel notifyLabel = null;
	private String friendName = null;
	private int notifyCount = 0;
	int toX, toY;

	public FriendCard(String name, String state)
	{
		initAllComponent();
		friendName = name;
		friendNameLabel.setText(name);
		friendStateLabel.setText(state);

		add(imageButton);
		add(friendNameLabel);
		add(friendStateLabel);
	}

	// 初始化所有组件以及bounds
	public void initAllComponent()
	{
		imageIcon = new ImageIcon(PathSource.imagePath);
		image = imageIcon.getImage();
		friendNameLabel = new JLabel();
		friendStateLabel = new JLabel();
		imageButton = new JButton();

		setLayout(null);
		imageIcon.setImage(image.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		imageButton.setIcon(imageIcon);
		imageButton.setBounds(5, 5, 60, 60);
		friendNameLabel.setBounds(70, 10, 100, 30);
		friendStateLabel.setBounds(70, 35, 100, 30);
	}

	public void setToLocation(int x, int y)
	{
		toX = x;
		toY = y;
	}

	public void updataNotifyLabel()
	{
		notifyCount++;
		if (notifyLabel == null)
		{
			notifyLabel = new JLabel(String.valueOf(notifyCount), JLabel.CENTER);
			notifyLabel.setBounds(getWidth() - 25, 5, 20, getHeight() - 10);
			notifyLabel.setOpaque(true);
			notifyLabel.setBackground(Color.RED);
			add(notifyLabel);
			validate();
		}
		else
		{
			notifyLabel.setText(String.valueOf(notifyCount));
			repaint();
		}
	}

	public void clearNotifyCount()
	{
		if (notifyLabel != null)
		{
			notifyCount = 0;
			remove(notifyLabel);
			notifyLabel = null;
		}
		repaint();
	}

	/*
	 * 这里需要写button的setter方法
	 */
	public JButton getButton()
	{
		return imageButton;
	}

	public void setFriendName(String friendName)
	{
		this.friendNameLabel.setText(friendName);
		repaint();
	}

	public String getFriendName()
	{
		return friendName;
	}

	public void setFriendState(String friendState)
	{
		this.friendStateLabel.setText(friendState);
		repaint();
	}

	public String getFriendState()
	{
		return friendStateLabel.getText();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see java.awt.Component#toString()
	 */
	@Override
	public String toString()
	{
		return friendName;
	}
}
