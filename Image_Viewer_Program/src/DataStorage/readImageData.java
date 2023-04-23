package DataStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class readImageData extends ArrayList<imageData>
{
    public readImageData(String fileName) throws FileNotFoundException
    {
        Scanner fIn = new Scanner(new File(fileName));
        String line = "";
        while(fIn.hasNextLine())
        {
            line = fIn.nextLine();

            if(line.trim().length() == 0 || line.charAt(0) == '#')
            {
                continue;
            }
            this.add(new imageData(line));
        }
    }

}
