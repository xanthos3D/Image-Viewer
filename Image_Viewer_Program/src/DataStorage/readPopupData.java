package DataStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class readPopupData extends ArrayList<popupData> {
    public readPopupData(String fileName) throws FileNotFoundException {
        System.out.println("\n[entering the readPopupData class]");
        Scanner fIn = new Scanner(new File(fileName));
        String line = "";
        String settings = "";
        while (fIn.hasNextLine()) {
            line = fIn.nextLine();

            if (line.trim().length() == 0 || line.charAt(0) == '#') {
                continue;
            }
            settings += line;
        }
        this.add(new popupData(settings));
        System.out.println("line recieved from reading popupData.csv: "+ settings );
        System.out.println("[exiting the readPopupData class]\n");
    }
}


