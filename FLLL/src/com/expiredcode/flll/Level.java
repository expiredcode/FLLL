package com.expiredcode.flll;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import com.expiredcode.flll.MyUtils;
import com.expiredcode.libs.Collider;
import com.expiredcode.libs.GameScreen;
import com.expiredcode.libs.Box2BoxCollision;

public class Level extends GameScreen{
	
	boolean done = false;
	
	MyUtils myu = new MyUtils();
	
	int WORLD_WIDTH = 800, WORLD_HEIGHT = 480;
	
	OrthographicCamera camera;
	SpriteBatch batcher;
	
	Texture background,ship,shot1,e1,e2,e3,e4,se1,se2,se3,se4;
	float ba,ck;
	float backspeed = 45, speed= 150,shotspeed=300;
	float shootdelay=20,spawndelay=0,spawn=5;
	float healt=100;
	Vector2 player;
	List<Collider> shots = new ArrayList<Collider>();
	Iterator it,t;
	Collider c,ship1,ship2,ship3;
	Enemy e;
	List<Enemy> enemy = new ArrayList<Enemy>();
	List<Collider> enemyshots = new ArrayList<Collider>();
	Music song;
	
	
	Level()
	{
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
		
		batcher = new SpriteBatch();
		
		background = new Texture(Gdx.files.internal("data/back.png")); //1010*480
		ba=-14;ck=993;
		
		song = Gdx.audio.newMusic(Gdx.files.internal("data/RoccoW_-_02_-_Weeklybeats_2014_2_-_Daniels_Kruis.mp3"));
		//song.play();
		
		ship = new Texture(Gdx.files.internal("data/ship.png"));
		player = new Vector2(210,40);
		shot1 = new Texture(Gdx.files.internal("data/s.png"));
		
		e1 = new Texture(Gdx.files.internal("data/e1.png"));
		e2 = new Texture(Gdx.files.internal("data/e2.png"));
		e3 = new Texture(Gdx.files.internal("data/e3.png"));
		e4 = new Texture(Gdx.files.internal("data/e4.png"));
		
		se1 = new Texture(Gdx.files.internal("data/se1.png"));
		se2 = new Texture(Gdx.files.internal("data/se2.png"));
		se3 = new Texture(Gdx.files.internal("data/se3.png"));
		se4 = new Texture(Gdx.files.internal("data/se4.png"));
		
		
		enemy.add(new Enemy("e1",110,900,128,64));
		
		ship1 = new Collider("ship",47,27,11,11);
		ship2 = new Collider("ship",21,19,26,26);
		ship3 = new Collider("ship", 8, 0,50,21);

	}

	
	@Override
	public void render(float deltatime)
	{
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		update(deltatime);
		batcher.begin();
		
		batcher.draw(background, 0, ba);
		batcher.draw(background, 0, ck);
		batcher.draw(ship, player.x, player.y);
		
		it = enemy.iterator();
		while(it.hasNext())
		{
			e=(Enemy)it.next();
			if(e.name.equals("e1"))
				batcher.draw(e1,e.x,e.y);
			else
				if(e.name.equals("e2"))
					batcher.draw(e2,e.x,e.y);
				else
					if(e.name.equals("e3"))
						batcher.draw(e3,e.x,e.y);
					else
						batcher.draw(e4,e.x,e.y);
			
			e.y -= (backspeed+15)*deltatime;
			if(e.y<-100) it.remove();
			if(e.shotdelay>2)
			{
				switch((int)(Math.random()*3)+1)
				{
					case 1: enemyshots.add(new Collider("s"+e.name,e.x+16,e.y+10,8,16)); break;
					case 2: enemyshots.add(new Collider("s"+e.name,e.x+108,e.y+10,8,16));break;
					case 3: enemyshots.add(new Collider("s"+e.name,e.x+60,e.y+10,8,16)); break;
				}
				e.shotdelay=0;
			}else e.shotdelay+=deltatime;
			
		}
		
		it = shots.iterator();
		while(it.hasNext())
		{
			c=(Collider)it.next();
			batcher.draw(shot1,c.x,c.y);
			c.y+=shotspeed*deltatime;
			if(c.y>820)it.remove();
		}
		
		it = enemyshots.iterator();
		while(it.hasNext())
		{
			c=(Collider)it.next();
			if(c.name.equals("se1"))
				batcher.draw(se1,c.x,c.y);
			else
				if(c.name.equals("se2"))
					batcher.draw(se2,c.x,c.y);
				else
					if(c.name.equals("se3"))
						batcher.draw(se3,c.x,c.y);
					else
						batcher.draw(se4,c.x,c.y);
			
			c.y-=shotspeed*deltatime;
			if(c.y<-30)it.remove();
		}
		
		batcher.end();
	}
	
	void update(float delta)
	{
		ba-=backspeed*delta;
		ck-=backspeed*delta;
		if(ba<-1010) ba = 995;
		if(ck<-1010) ck = 995;
		
		if(Gdx.input.isKeyPressed(Input.Keys.A))
		{
			player.x-=speed*delta;
			if(player.x<0) player.x=0;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D))
		{
			player.x+=speed*delta;
			if(player.x>416) player.x=416;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
		{
			if(shootdelay>0.3)
			{
				shots.add(new Collider("sparo",player.x+32,player.y+32,8,16));
				shootdelay=0;
			}
		}	
		shootdelay+=delta;		
		
		if(spawndelay>spawn)
		{
			switch((int)(Math.random()*4)+1)
			{
				case 1: enemy.add(new Enemy("e1",(float) (Math.random()*332),900,128,64));break;
				case 2: enemy.add(new Enemy("e2",(float) (Math.random()*332),900,128,64));break;
				case 3: enemy.add(new Enemy("e3",(float) (Math.random()*332),900,128,64));break;
				case 4: enemy.add(new Enemy("e4",(float) (Math.random()*332),900,128,64));break;
			}
			spawndelay=0;
			
		}else
			spawndelay+=delta;
		collisions();
		
		spawn-=delta/5; if(spawn<1.3) spawn=2f;
		backspeed+=delta/2;
	}
	
	void collisions()
	{
		it = shots.iterator();
		while(it.hasNext())
		{
			c=(Collider)it.next();
			t = enemy.iterator();
			while(t.hasNext())
			{
				e=(Enemy)t.next();
				if (Box2BoxCollision.B2BCollision(c,(Collider)e))
				{
					it.remove();				
					e.healt-=(int)(Math.random()*30);
					if(e.healt<=0)
						t.remove();
				}
			}
		}
		
		it = enemyshots.iterator();
		while(it.hasNext())
		{
			c=(Collider)it.next();
			ship1.x+=player.x; ship1.y+=player.y;
			ship2.x+=player.x; ship2.y+=player.y;
			ship3.x+=player.x; ship3.y+=player.y;
			if(Box2BoxCollision.B2BCollision(c,ship1)||Box2BoxCollision.B2BCollision(c,ship2)||Box2BoxCollision.B2BCollision(c,ship3))
			{
				healt-=(int)(Math.random()*20);
				Gdx.app.log("healt", ""+healt);
				it.remove();
			}
			ship1.x=47; ship1.y=27;
			ship2.x=21; ship2.y=19;
			ship3.x=8; ship3.y=0;
		}
		
	}
	
	public boolean isDone()
	{
		return done;		
	}
	
	@Override
	public void dispose() 
	{
		background.dispose();
	}

}
