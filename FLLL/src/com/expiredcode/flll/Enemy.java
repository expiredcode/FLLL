package com.expiredcode.flll;

import com.expiredcode.libs.Collider;

public class Enemy extends Collider
{
	float healt;
	float shotdelay;
	public Enemy(String n,float xx, float yy, float ww,float hh)
	{
		super(n,xx,yy,ww,hh);
		shotdelay=20;
		this.healt = (int)(Math.random()*150);
	}
}
