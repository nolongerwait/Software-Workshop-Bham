package application.utils;

import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.stage.Window;

/**
 * The Toast Class defined the different style of warning tips.
 * @author Zetian Qin zxq876
 */
public class Toast
{
	public enum Level{ 	INFO, WARN, ERROR  	};
	
	public Color[] bgColors = {
		Color.GREENYELLOW	,
		Color.ORANGE,
		Color.RED
	};
	
	public Color[] textColors = {
		Color.web("#444"),
		Color.web("#444"),
		Color.web("#fff")
	};
	
	public double initialAlpha = 0.9;
	public Pos pos = Pos.CENTER;
	private Window owner;
	private Node node;
	
	
	public Toast(Window owner)
	{
		this.owner = owner;
	}
	
	public Toast(Node node)
	{
		this.node = node;
	}
	
	public void show(String text)
	{
		show(Level.INFO, 1500, text);
	}
	
	public void show(Level level, String text)
	{
		show(level, 1500, text);
	}
	
	public void show(Level level, int timeout, String text)
	{
		ToastrWindow toastr = new ToastrWindow();
		
		if(level == Level.INFO) 
		{
			toastr.bgColor = bgColors[0]; //Color.CHARTREUSE,
			toastr.textColor = textColors[0];
		}
		else if(level == Level.WARN)
		{
			toastr.bgColor = bgColors[1]; //Color.CHARTREUSE,
			toastr.textColor = textColors[1];
		}
		else if(level == Level.ERROR)
		{
			toastr.bgColor = bgColors[2];; //Color.CHARTREUSE,
			toastr.textColor = textColors[2];
		}
		
		toastr.initialAlpha = this.initialAlpha;
		
		toastr.setText(text);	

		if(owner == null) owner = node.getScene().getWindow();
		double x = owner.getX(), y = owner.getY();
		double w = owner.getWidth(), h = owner.getHeight();
		
		Bounds bounds = toastr.root.getBoundsInLocal();		
		if(pos == Pos.CENTER)
		{
			x += ( w - bounds.getWidth())/2;
			y += ( h - bounds.getHeight())/2;
		}
		else if(pos == Pos.TOP_CENTER)
		{
			x += ( w - bounds.getWidth())/2;
			y += 50;
		}
		else if(pos == Pos.BOTTOM_CENTER)
		{
			x += ( w - bounds.getWidth())/2;
			y += ( h - bounds.getHeight() - 50);
		}
				
		//System.out.println(bounds.toString());
		
		toastr.show(owner, x, y);
	}
}