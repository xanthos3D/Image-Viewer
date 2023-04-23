package DataStorage;

import java.io.File;
import DataStorage.imageData;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;
import java.io.FileWriter;   // Import the FileWriter class

public class writePopupData {
    private String fileData;
    private String changePopupOption;

    public void writePopupDataToFile(String currentIsActive,String currentPlacement,String searchActive) throws FileNotFoundException {
        System.out.println("\n[entering the WritePopupData class]");
        String allDataToFile1 = "-PopupisActive:";
        String allDataToFile2 = "-PopupPlacement:";
        String allDataToFile3 = "-SearchActive:";
        allDataToFile1 += currentIsActive;
        allDataToFile2 += currentPlacement;
        allDataToFile3 += searchActive;
        System.out.println("writing to PopupData.csv "+ allDataToFile1 + " " + allDataToFile2 + " " + allDataToFile3 );
        try
        {
            FileWriter myWriter = new FileWriter("./src/PopupData.csv");
            myWriter.write(allDataToFile1 + allDataToFile2 + allDataToFile3);
            myWriter.close();
            System.out.println("Successfully wrote PopupData to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while attempting to write to file.");
            e.printStackTrace();
        }
        System.out.println("[exit the WritePopupData class]\n");
    }
}
