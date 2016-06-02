package main;

import javax.swing.JFrame;

import login.LoginController;

public class BChatClient
{
	String userName = "admin";
	String passWord = "admin";
	JFrame aFrame = null;
	LoginController loginController = null;

	public BChatClient()
	{
		loginController = new LoginController();
	}

	public static void main(String[] args)
	{
		new BChatClient();
	}
}
