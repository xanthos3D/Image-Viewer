package DataStorage;

import java.io.File;
import DataStorage.imageData;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;
import java.io.FileWriter;   // Import the FileWriter class

public class writeImageData {
    private String fileData;

    //this data file does the writing to our image datafile.
    public void writeImageDataToFile(ArrayList<imageData> allImageData,int file) throws FileNotFoundException {
        //this string is used to store the image data.
        String allDataToFile = "";
        // the given for loop produces a viable write to the data file by filling out string with the contents of all imagedata
        for (int counter = 0; counter < allImageData.size(); counter++)
        {
            // adds one line at a time. example line: /Images/image.jpg,24,stuff,things
            allDataToFile += allImageData.get(counter).getImagePath() +","+ allImageData.get(counter).getImageIndex() + allImageData.get(counter).returnTags()+"\n";
        }
        System.out.println(allDataToFile);
        // simple statement to make sure that the file can be found a written to. is used to write to both the temp popupdata file and the main storage data file.
        if(file == 0) {
            try {
                FileWriter myWriter = new FileWriter("./src/ImageData.csv");
                myWriter.write(allDataToFile);
                myWriter.close();
                System.out.println("Successfully wrote Image to file.");
            } catch (IOException e) {
                System.out.println("An error occurred while attempting to write to file.");
                e.printStackTrace();
            }
        }else if(file == 1){
            try {
                FileWriter myWriter = new FileWriter("./src/PopupImageData.csv");
                myWriter.write(allDataToFile);
                myWriter.close();
                System.out.println("Successfully wrote Image to file.");
            } catch (IOException e) {
                System.out.println("An error occurred while attempting to write to file.");
                e.printStackTrace();
            }
        }
    }
}
