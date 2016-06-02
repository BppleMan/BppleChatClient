/**
 * 
 */
package tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author BppleMan
 *
 */
public class LogString
{
	String log = null;

	/**
	 * 
	 */
	public LogString(String log)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.log = df.format(new Date()) + ":" + log;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return log;
	}
}
