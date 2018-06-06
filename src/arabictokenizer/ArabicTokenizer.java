/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabictokenizer;

import static arabictokenizer.ArabicUtils.openFileForReading;
import static arabictokenizer.ArabicUtils.openFileForWriting;
import static arabictokenizer.ArabicUtils.tokenize;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author aahmed
 */
public class ArabicTokenizer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        	int i = 0;
	String arg;
	String infile = "";
	String outfile = "";
	String scheme = "def";
	Boolean norm = false; // no normalization Default
        Boolean lemma = false;
	int args_flag = 0; // correct set of arguments

	while (i < args.length)
	{
	    arg = args[i++];
	    // 
	    if (arg.equals("--help") || arg.equals("-h") || (args.length != 0 && args.length != 2 && args.length != 4 && args.length != 6 && args.length != 8))
	    {
		System.out.println("Usage: ArabicTokenizer  <[-i|--input] [in-filename]> <[-o|--output] [out-filename]>");
		System.exit(-1);
	    }

	    if (arg.equals("--input") || arg.equals("-i"))
	    {
		args_flag++;
		infile = args[i];   
	    }
	    if (arg.equals("--output") || arg.equals("-o"))
	    {
		args_flag++;
		outfile = args[i];
	    }
	    
	}

	if (args_flag == 0)
	{
	    processFile();
	}
	else
	{
           
            processFile(infile, outfile);
            
            
	}
    }
    
    private static void processFile() throws FileNotFoundException, IOException
    {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	processBuffer(br, bw);
    }

    private static void processFile(String filename, String outfilename) throws FileNotFoundException, IOException
    {
	BufferedReader br; 
	BufferedWriter bw;  
	if (!filename.equals(""))
	{
	    br = openFileForReading(filename);
	}
	else
	{
	    br = new BufferedReader(new InputStreamReader(System.in));
	}

	if (!outfilename.equals(""))
	{
	    bw = openFileForWriting(outfilename);
	}
	else
	{
	    bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}

	processBuffer(br, bw);
    }

    private static void processBuffer(BufferedReader br, BufferedWriter bw) throws FileNotFoundException, IOException
    {

	String line;
	String topSolution;
	
        ArrayList<String> words;
 
	while ((line = br.readLine()) != null)
	{

	    // normalize Farsi letter
	    line = ArabicUtils.replaceFarsiCharacters(line);

	    words = tokenize(line);
            
	    for (String w : words)
	    {	
		bw.write(w + " ");
                bw.flush();

	    }
	    bw.write("\n");
            words.clear();
            bw.flush();
   
	}
	bw.close();
             
    }
    
}
