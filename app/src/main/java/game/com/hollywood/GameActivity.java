package game.com.hollywood;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Vector;

public class GameActivity extends AppCompatActivity {

    String s="",x="";
    int c=0;
    private FastAccess fastaccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            fastaccess = new FastAccess(inputStream);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load movie list", Toast.LENGTH_LONG);
            toast.show();
        }
        reset(null);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event)
    {
        char ch = (char) event.getUnicodeChar();
        TextView n = (TextView) findViewById(R.id.name);
        TextView stat = (TextView) findViewById(R.id.status);
        x=n.getText().toString();
        if(contains(s,ch)&&!contains(x, ch))
        {
            x=x+ch;
            String st=add();
            n.setText(st);
            if(s.equals(st))
            {
                Toast toast = Toast.makeText(this, "You Won!!! Congratulations!!!", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        else
        {
            c++;
            if(c<="GUESS_THE_WORD".length())
            {
                SpannableString ss=  new SpannableString("GUESS_THE_WORD");
                ss.setSpan(new ForegroundColorSpan(Color.RED), 0, c, 0);
                stat.setText(ss);
            }
            else
            {
                Toast toast = Toast.makeText(this, "You Lost!!!", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        return false;
    };

    String add()
    {
        String st="";
        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(x.indexOf(c)!=-1&&c!=' ')
                st=st+c;
            else if(c==' ')
                st=st+" ";
            else
                st=st+" _ ";
        }
        return st;
    }

    boolean contains(String s,char ch)
    {
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)==ch)
                return true;
        }
        return false;
    }

    public void show(View v)
    {
        Toast toast = Toast.makeText(this, s, Toast.LENGTH_LONG);
        toast.show();
    }

    public void reset(View v)
    {
        c=0;
        x="";
        TextView stat = (TextView) findViewById(R.id.status);
        TextView n = (TextView) findViewById(R.id.name);
        stat.setText("GUESS_THE_WORD");
        Random r=new Random();
        Vector vec=fastaccess.v;
        s=vec.get(r.nextInt(vec.size())).toString();
        String st="";
        for(int i=0;i<s.length();i++)
        {
            char ch=s.charAt(i);
            if(ch==' ')
            {
                st=st+" ";
            }
            else
                st=st+" _ ";
        }
        n.setText(st);
    }
}
