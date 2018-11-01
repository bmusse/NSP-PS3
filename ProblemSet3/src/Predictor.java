import java.util.*;
import java.io.*;

public class Predictor 
{
	
	public static Hashtable<Integer, String> uniHash = new Hashtable<Integer, String>(10000000);
	public static Hashtable<Integer, String> biHash  = new Hashtable<Integer, String>(10000000);
	public static Hashtable<Integer, String> triHash = new Hashtable<Integer, String>(10000000);
        public static String biOutput;
        public static String triOutput;
	
	public static void main(String[] args) throws IOException
	{           
            run();
        }
        public static void run() throws IOException
        {
                int wordCount;
		int count = 0;
		int number   = 0;
		double freq = 0;
		double d = 0;
		String comp = null;
                String input = "to be or";//simpleGUI.inputOut();
                String biToken = "";
                String triToken = "";
                String c = "";
                String e = "";
                biOutput = "";
                triOutput= "";
                
                
		
		
		
		HashMap<String, Integer> uniNum = new HashMap<>(10000000);
		HashMap<String, Double> uniFreq = new HashMap<>(10000000);
		HashMap<String, Integer> biNum = new HashMap<>(10000000);
		HashMap<String, Double> biFreq = new HashMap<>(10000000);
		HashMap<String, Integer> triNum = new HashMap<>(10000000);
		HashMap<String, Double> triFreq = new HashMap<>(10000000);
		
		tokenCreate();
		
		for( int i = 0; i < uniHash.size()-1; i++)
		{
			String uni1 = (String) uniHash.get(i);
			String uni2 = (String) uniHash.get(i+1);
			String bi   = uni1 + " " + uni2;
			
			biHash.put(count, bi);
			count++;
		}
		
		count = 0;
		
		for( int i = 0; i < uniHash.size()-2; i++)
		{
			String uni1 = (String) uniHash.get(i);
			String uni2 = (String) uniHash.get(i+1);
			String uni3 = (String) uniHash.get(i+2);
			String tri  = uni1 + " " + uni2 + " " + uni3;
			
			triHash.put(count, tri);
			count++;
		}
		
		double uniSize = uniHash.size();
		for( int i = 0; i < uniHash.size(); i++)
		{
			comp = (String) uniHash.get(i);
			number = 0;
                        freq = 0;
			for(int j = 0; j < uniHash.size(); j++)
			{
                                if(comp.equals(uniHash.get(j)))
                                {
                                        number++;
                                }
			}
			freq = number/uniSize;
			uniNum.put(comp, number);
			uniFreq.put(comp, freq);
		}
		
		
		for( int i = 0; i < biHash.size(); i++)
		{
			
			comp = (String) biHash.get(i);
			number = 0;
			freq = 0;
			String[] biSplit = comp.split(" ");
			d = uniNum.get(biSplit[0]);
			for(int j = 0; j < biHash.size(); j++)
			{
                                if(comp.equals(biHash.get(j)))
                                {
                                        number++;
                                }
			}
			freq = number/d;
			biNum.put(comp, number);
			biFreq.put(comp, freq);
		}
		for( int i = 0; i < triHash.size(); i++)
		{
			comp = (String) triHash.get(i);
			number = 0;
			freq = 0;
			String[] triSplit = comp.split(" ");
			String biGram = (triSplit[0] + " " + triSplit[1]);
			d = biNum.get(biGram);
			for(int j = 0; j < triHash.size(); j++)
			{
                                if(comp.equals(triHash.get(j)))
                                {
                                        number++;
                                }
			}
			
			freq = number/d;
			triNum.put(comp, number);
			triFreq.put(comp, freq);
		}
		
                StringTokenizer t1 = new StringTokenizer(input);
                StringTokenizer t2 = new StringTokenizer(input);
                while(t1.hasMoreTokens())
                {
                    biToken += (t1.nextToken() + " ");
                }
                
                while(t2.hasMoreTokens())
                {
                    triToken += (t2.nextToken() + " ");
                }
                
                        
                        
                        for(int k = 0; k < 10; k++)
                        {
                        freq = 0;
                        for(int j = 0; j < biHash.size()-1; j++)
			{
                         if( k == 0)
                         {
			  String[] biSplit = biToken.split(" ");
                          String[] hashSplit1 = (biHash.get(j)).split(" ");
                          String[] hashSplit2 = (biHash.get(j+1)).split(" ");
                          String a = (biSplit[0]);
                          String b = (hashSplit1[1]);
                                if(a == null ? b == null : a.equals(b))
                                {
                                    if(freq < uniFreq.get(b))
                                    {
                                      freq = uniFreq.get(b);
                                      c = hashSplit2[0];
                                      e = (hashSplit1[1]);
                                    }
                                
                                }
                         }
                         else
                         {
                          String[] hashSplit3 = (biHash.get(j)).split(" ");
                          String[] hashSplit4 = (biHash.get(j+1)).split(" ");
                          String z = (hashSplit3[0]);
                          if(e == null ? z == null : e.equals(z))
                               {
                                    if(freq < uniFreq.get(z))
                                    {
                                      freq = uniFreq.get(z);
                                      c = hashSplit4[0];
                                      e = (hashSplit3[1]);
                                    }
                                
                                }
                         }
                         
			}
                        biOutput += c + " " ;
                        }
                        for(int k = 0; k < 10; k++)
                        {
                        freq = 0;
                        for(int j = 0; j < triHash.size()-1; j++)
			{
                         if( k == 0)
                         {
			  String[] triSplit = triToken.split(" ");
                          String[] hashSplit1 = (triHash.get(j)).split(" ");
                          String[] hashSplit2 = (triHash.get(j+1)).split(" ");
                          String a = (triSplit[0] + " " + triSplit[1]);
                          String b = (hashSplit1[1] + " " + hashSplit1[2]);
                                if(a == null ? b == null : a.equals(b))
                                {
                                    if(freq < biFreq.get(b))
                                    {
                                      freq = biFreq.get(b);
                                      c = hashSplit2[0];
                                      e = (hashSplit1[1] + " " + hashSplit1[2]);
                                    }
                                
                                }
                         }
                         else
                         {
                          String[] hashSplit3 = (triHash.get(j)).split(" ");
                          String[] hashSplit4 = (triHash.get(j+1)).split(" ");
                          String z = (hashSplit3[0] + " " + hashSplit3[1]);
                          if(e == null ? z == null : e.equals(z))
                               {
                                    if(freq < biFreq.get(z))
                                    {
                                      freq = biFreq.get(z);
                                      c = hashSplit4[0];
                                      e = (hashSplit3[1] + " " + hashSplit3[2]);
                                    }
                                
                                }
                         }
                         
			}
                        triOutput += c + " " ;
                        }
                        
			
                        
                           
		
                System.out.println(biOutput);
                System.out.println(triOutput);
		System.out.println(uniHash.values());
		//System.out.println(biHash.values());
		//System.out.println(triHash.values());
		//System.out.println(uniFreq);
		//System.out.println(biFreq);
		//System.out.println(triFreq);
                //System.out.println(biToken + " " + triToken);
	}
	
	public static void tokenCreate() throws IOException
	{
            String words = "";
            int num = 0;
            int num2 = 0;
		InputStream fis = new FileInputStream("Shakespeare.txt");
                InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
                String[] moreWords = new String[1000000];
		String line = "";
		
		
		
		while((line = br.readLine()) != null)
		{
			moreWords[num2] += line.split("\\s+");
                        num2++;
		}
                
                for(int i = 1000000; i < moreWords.length; i--)
                {
                    
                    words += moreWords[i] + " ";
                    
                }
		/*Scanner sc2 = null;
                try
                {
                    sc2 = new Scanner(new File("testData2.txt"));
                }
                catch(FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                
                while(sc2.hasNextLine())
                {
                    Scanner s2 = new Scanner(sc2.nextLine());
                    while(s2.hasNext())
                    {
                        words += s2.next() + " ";
                    }
                }*/
		StringTokenizer token = new StringTokenizer(words);
		
		while(token.hasMoreTokens())
		{
			String current = token.nextToken();
			uniHash.put(num, current);
			num++;
		}
		br.close();
	}
        public static String biOut()
        {          
            return biOutput;
        }
        public static String triOut()
        {
            return triOutput;
        }
	

}
