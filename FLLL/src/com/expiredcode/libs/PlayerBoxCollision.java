package com.expiredcode.libs;

import com.badlogic.gdx.math.Vector2;

public class PlayerBoxCollision {
	
	boolean leftcollision,rightcollision,topcollision,lowcollision;
	Vector2 lowleft,topleft,topright,lowright;
	boolean grounded = true;
	boolean goingleft = false;
	boolean ascending=false,descending=false;
	Collider c;
	
	float pw=36,ph=54;
	
	PlayerBoxCollision()
	{
		
	}
	
	public boolean collision(Vector2 player,Collider c)
	{
		
		
			lowleft.x=c.x; lowleft.y=c.y;
			topleft.x=c.x; topleft.y=c.y+c.height;
			lowright.x=c.x+c.width; lowright.y=c.y;
			topright.x=c.x+c.width; topright.y=c.y+c.height;
			
						
			//top collision
			if(ascending)
			{
				if((player.y+ph>lowleft.y)&&(player.y+ph<topleft.y))
					if(((player.x>lowleft.x)&&(player.x<lowright.x))||((player.x+pw>lowleft.x)&&(player.x+pw<lowright.x)))
					{
						topcollision = true;
						ascending = false;
						descending = true;	
						player.y=lowleft.y-1-ph;			
					}
			}else
				if(descending)
				{
					if(player.y<=topleft.y&&player.y>lowleft.y)
						if((player.x>=topleft.x && player.x<=topright.x)||(player.x+pw>=topleft.x&&player.x+pw<=topright.x))
						{
							lowcollision = true;
							player.y=topleft.y+1;
							grounded=true;
							descending=false;
						}
				}
			
			//left collision			
			if(player.x<=lowright.x && player.x>lowleft.x)
			{	if((player.y>=lowright.y&&player.y<topright.y)||(player.y+ph>lowright.y&&player.y+ph<=topright.y))
				{
					leftcollision=true;
					player.x=lowright.x+1;
				
				}
			}			
			//right collision
			if(player.x+pw>=lowleft.x&&player.x+pw<lowright.x)
			{	
				if((player.y>=lowleft.y&&player.y<topleft.y)||((player.y+ph>lowright.y)&&(player.y+ph<topright.y)))
				{			
					rightcollision=true;
					player.x=lowleft.x-1-pw;
				
				}
			}
			
		return rightcollision||leftcollision||lowcollision||topcollision;
	}	
	
	

}
