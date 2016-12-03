package com.expiredcode.libs;

public class Box2BoxCollision {
	
	
	public static boolean B2BCollision(Collider a, Collider b)
	{
		if((a.y+a.height>b.y)&&(a.y+a.height<b.y+b.height))
			if(((a.x>b.x)&&(a.x<b.x+b.width))||((a.x+a.width>b.x)&&(a.x+a.width<b.x+b.width)))
			{
				return true;		
			}
		if(a.y<=b.y+b.height&&a.y>b.y)
			if((a.x>=b.x && a.x<=b.x+b.width)||(a.x+a.width>=b.x&&a.x+a.width<=b.x+b.width))
			{
				return true;
			}
		if(a.x<=b.x+b.width && a.x>b.x)
		{	if((a.y>=b.y+b.width&&a.y<b.y+b.height)||(a.y+a.height>b.y+b.width&&a.y+a.height<=b.y+b.height))
			{
				return true;
			}
		}			
		//right collision
		if(a.x+a.width>=b.x&&a.x+a.width<b.x+b.width)
		{	
			if((a.y>=b.y&&a.y<b.y)||((a.y+a.height>b.y)&&(a.y+a.height<b.y+b.height)))
			{			
				return true;
			}
		}
		
		return false;
	}

}
