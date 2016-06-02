package chatcontroller;

import java.util.Vector;

import data.TalkInfo;

public class TalkInfoBuff
{
	Vector<TalkInfo> talkInfoBuff = null;

	/**
	*
	*/
	public TalkInfoBuff()
	{
		talkInfoBuff = new Vector<>();
	}

	public void apend(TalkInfo talkInfo)
	{
		talkInfoBuff.add(talkInfo);
	}

	public TalkInfo getTalkInfo(int index)
	{
		return talkInfoBuff.get(index);
	}

	public TalkInfo getTalkInfo(TalkInfo talkInfo)
	{
		for (TalkInfo temp : talkInfoBuff)
		{
			if (temp.equals(talkInfo))
				return temp;
		}
		return null;
	}

	public void remove(TalkInfo talkInfo)
	{
		talkInfoBuff.remove(talkInfo);
	}

	public void remove(int index)
	{
		talkInfoBuff.remove(index);
	}

	public boolean isEmpty()
	{
		if (talkInfoBuff.size() == 0)
			return true;
		else
			return false;
	}

	public int getSize()
	{
		return talkInfoBuff.size();
	}
}
