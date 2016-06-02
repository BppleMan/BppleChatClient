/**
 * 
 */
package login;

import java.util.EventListener;

import data.UserInfo;

/**
 * @author BppleMan
 *
 */
public interface UserInfoListener extends EventListener
{
	public void setUserInfo(UserInfo userInfo);
}
