package register;

import java.awt.Color;

import javax.swing.JTextField;

public class RegistInfoValid extends Thread
{
	JTextField textField1 = null;
	JTextField textField2 = null;
	JTextField textField3 = null;

	public RegistInfoValid(JTextField t1, JTextField t2, JTextField t3)
	{
		textField1 = t1;
		textField2 = t2;
		textField3 = t3;
	}

	private void validTextField(JTextField t)
	{
		String regex = "^[A-Za-z0-9]{6,20}$";
		String str = t.getText();
		/*
		 * 绿色RGB(115, 191, 44) 红色RGB(245, 149, 176)
		 */
		if (t.equals(textField3))
		{
			if (str != null && str.length() > 0)
			{
				if (str.equals(textField2.getText()))
				{
					t.setBackground(new Color(115, 191, 44));
				}
				else
				{
					t.setBackground(new Color(245, 149, 176));
				}
			}
			else
			{
				t.setBackground(Color.WHITE);
			}
		}
		else if (str != null && str.length() > 0)
		{
			if (str.matches(regex))
			{
				t.setBackground(new Color(115, 191, 44));
			}
			else
			{
				t.setBackground(new Color(245, 149, 176));
			}
		}
		else
		{
			t.setBackground(Color.WHITE);
		}
	}

	@Override
	public void run()
	{
		while (true)
		{
			validTextField(textField1);
			validTextField(textField2);
			validTextField(textField3);
		}
	}
}
