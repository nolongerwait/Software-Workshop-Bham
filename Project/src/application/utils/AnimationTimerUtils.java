package application.utils;

import javafx.animation.AnimationTimer;

public abstract class AnimationTimerUtils extends AnimationTimer
{
	private long interval = 0;
	private long lastTime = 0; 
		
	public AnimationTimerUtils(int interval)
	{
		this.interval = interval * 1000000L;
	}

	@Override
	public void handle(long now)
	{		
		if( now - lastTime >= interval)
		{
			if(lastTime == 0) 
				lastTime = now;
			else 
				lastTime += interval;
			updateAnimation();
		}		
	}
	public abstract void updateAnimation();

}
