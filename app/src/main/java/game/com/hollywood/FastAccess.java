package game.com.hollywood;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

public class FastAccess
{
    Vector<String> v=new Vector<String>();
    public FastAccess(InputStream wordListStream)throws IOException
    {
        BufferedReader in=new BufferedReader(new InputStreamReader(wordListStream));
        String line=null;
        while ((line=in.readLine())!=null)
        {
            String word=line.trim();
            v.add(word);
        }
    }
}