package DataStorage;

import java.util.ArrayList;
import javafx.scene.image.Image;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class imageData extends writeImageData
{
    //holds the image path
    private String Location;
    //holds the index order in the overall list.
    private int indexOrder;
    //array that holds tags of image.
    private ArrayList<String> tags = new ArrayList<String> ();
    //default way of setting up a imageData Data type
    public imageData(String line)
    {
        //default constructor recieves a string ans splitsi t by"," using regex
        String[] fields = line.split(",");
        //the first index in the string will be the image path
        Location = fields[0];
        //the second index should hold the order in the data base
        indexOrder = Integer.parseInt(fields[1]);
        //every index after the first two holds the tags for the image. so this for loop fills the tags string arraylist.
       for(int index = 2;index < fields.length; index++)
       {
          tags.add(fields[index]);
       }
    }
    // acessors and mutators for our tata types incase these tata types need to be altured during the run time of the program.
    public String getImagePath() {
        return Location;
    }

    public void setLocation(String imageName) {
        this.Location = imageName;
    }

    public int getImageIndex() {
        return indexOrder;
    }

    public void setImageIndex(int imageIndex) {
        this.indexOrder = imageIndex;
    }

    public ArrayList<String> getTags()
    {
        return tags;
    }

    public String returnTags()
    {
        String allTags = "";
        for(int counter = 0; counter < tags.size();counter++)
        {
            allTags += "," + tags.get(counter);
        }
                return allTags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}


