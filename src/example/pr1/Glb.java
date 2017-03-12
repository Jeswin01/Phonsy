package example.pr1;

import android.app.Application;

public class Glb extends Application 
{

    public static String sb;

    public String getGlobalVarValue() 
    {
    	
        return sb;
    }

    public void setGlobalVarValue(String str) 
    {
        sb = str;
        
    }
    public Glb() 
    {
	
    	// TODO Auto-generated constructor stub
    	sb="on";
    }
}