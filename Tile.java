/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game2048;

/**
 *
 * @author Kashu
 */
import java.awt.Color;



public class Tile
{
    int value;

    Color tileColor;


    public Tile()
    {
        value = 0;
    }


    public Tile( int number )
    {
        value = number;
    }


   
    public int getValue()
    {
        return value;
    }

    public void setValue( int value )
    {
        this.value = value;
    }


    
    public String toString()
    {
        return "" + value;
    }


  
    public void setColor()
    {
        if ( this.getValue() == 2 )
        {
            tileColor = new Color( 238, 228, 218 );
        }
        else if ( this.getValue() == 4 )
        {
            tileColor = new Color( 237, 224, 200 );
        }
        else if ( this.getValue() == 8 )
        {
            tileColor = new Color( 242, 177, 121 );
        }
        else if ( this.getValue() == 16 )
        {
            tileColor = new Color( 245, 149, 99 );
        }
        else if ( this.getValue() == 32 )
        {
            tileColor = new Color( 246, 124, 95 );
        }
        else if ( this.getValue() == 64 )
        {
            tileColor = new Color( 246, 94, 59 );
        }
        else if ( this.getValue() == 128 )
        {
            tileColor = new Color( 237, 207, 114 );
        }
        else if ( this.getValue() == 256 )
        {
            tileColor = new Color( 237, 204, 97 );
        }
        else if ( this.getValue() == 512 )
        {
            tileColor = new Color( 237, 200, 80 );
        }
        else if ( this.getValue() == 1024 )
        {
            tileColor = new Color( 237, 197, 63 );
        }
        else
        {
            tileColor = new Color( 237, 194, 46 );
        }
    }


    
    public Color getColor()
    {
        this.setColor();
        return tileColor;
    }

}