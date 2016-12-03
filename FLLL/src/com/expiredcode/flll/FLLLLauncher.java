package com.expiredcode.flll;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.expiredcode.libs.GameScreen;

public class FLLLLauncher  extends Game {

	MyUtils m = new MyUtils();
	
	@Override
	public void render()
	{

		GameScreen currentScreen = getScreen();

        // update the screen
        currentScreen.render(Gdx.graphics.getDeltaTime());

        // When the screen is done we change to the
        // next screen. Ideally the screen transitions are handled
        // in the screen itself or in a proper state machine.
        if (currentScreen.isDone()) 
        {
                // dispose the resources of the current screen
                currentScreen.dispose();
                switch(m.screen)
                {
                	case 0:{
                			//	setScreen(new Home());
                		
                			}//break;
                	
                	case 1:{                		
                		setScreen(new Level());
                	}break;
                }
        }		
	}
	@Override
	public void create() 
	{
		//setScreen(new MainMenu());
		//setScreen(new LevelOne());
		setScreen(new Level());
		//setScreen(new LevelThree());
	}
	
	@Override
	public void dispose()
	{
		
	}
	@Override
    public GameScreen getScreen () 
	{
            return (GameScreen)super.getScreen();
    }

}
