package Image.Viewer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;
import DataStorage.imageData;
import DataStorage.readImageData;
import DataStorage.writeImageData;
import DataStorage.popupData;
import DataStorage.readPopupData;
import DataStorage.writePopupData;
/*
* ====================================================================================================================================================================================
* ====================================================================================================================================================================================
* Scene Element & global variable Declarations
* ====================================================================================================================================================================================
* ====================================================================================================================================================================================
* */
public  class UiController implements Initializable {
    //fucking reader mode
    @FXML
    private GridPane imageGrid;
    @FXML
    private ImageView view1;
    @FXML
    private ImageView view2;
    @FXML
    private ImageView view3;
    @FXML
    private ImageView view4;
    @FXML
    private ImageView view5;
    @FXML
    private ImageView view6;
    @FXML
    private ImageView view7;
    @FXML
    private ImageView view8;
    @FXML
    private ImageView view9;
    @FXML
    private ImageView view10;
    @FXML
    private ImageView view11;
    @FXML
    private ImageView view12;
    @FXML
    private ImageView view13;
    @FXML
    private ImageView view14;
    @FXML
    private ImageView view15;
    @FXML
    private ImageView view16;
    @FXML
    private ImageView view17;
    @FXML
    private ImageView view18;
    @FXML
    private ImageView view19;
    @FXML
    private ImageView view20;
    @FXML
    private ImageView view21;
    @FXML
    private ImageView view22;
    @FXML
    private ImageView view23;
    @FXML
    private ImageView view24;
    @FXML
    private ImageView view25;
    @FXML
    private ImageView popupImageView0;
    @FXML
    private Button btn_right;
    @FXML
    private Button btn_left;
    @FXML
    private Button btn_right_end;
    @FXML
    private Button btn_left_end;
    @FXML
    private Button Add_Image;
    @FXML
    private Button filter;
    @FXML
    private Button btn_add_tag;
    @FXML
    private Button direct_Search;
    @FXML
    private Button tag_Search;
    @FXML
    private Button clear_Search;
    @FXML
    private Button btn_remove_tag;
    @FXML
    private Button btn_remove_image;
    @FXML
    private TextField text_field;
    @FXML
    private TextField tf_add_tag;
    @FXML
    private Label window_number;
    @FXML
    private Label popup_counter;
    @FXML
    private Label tag_list;
    @FXML
    private Label tag_Error;
    @FXML
    private Label lblErrorDisplay;

    readImageData imageFileData;

    readImageData defaultImages;

    readPopupData isPopupData;
    // needs implementation. keeps track of the location in which user clicked by reading the file using the readPlacement class.

    int placement = 0;
    int popupPlacement = 0;

    boolean inPopup = false;
    //needs implementation. used for creating the filtered list based on tags.
    ArrayList<imageData> filteredList = new ArrayList<imageData>();
    ArrayList<imageData> allImageData = new ArrayList<imageData>();
    /*
     * ====================================================================================================================================================================================
     * ====================================================================================================================================================================================
     * Main Window Function Definitions
     * ====================================================================================================================================================================================
     * ====================================================================================================================================================================================
     * */
    //function used to validate if popup window is open
    public void isPopupOpen(String fileName)
    {
        System.out.println("Entering isPopupOpen");
        try {
            isPopupData = new readPopupData(fileName);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
            System.out.println("Problem Opening PopupData: "+ fileName);
        }
        //gets the popup data by accessing the writepopupdata class then the popupdataclass
        System.out.println("isPopupdata values:(isPopupActive: "+isPopupData.get(0).getIsActive() + ") (placement: " +isPopupData.get(0).getPlacement() +") (searchActive: "+ isPopupData.get(0).getSearchActive()+ ")");
        System.out.println("Exiting isPopupOpen");
    }


    //function to validate if image file is readable.
    public void validateImageFile(String fileName)
    {
        System.out.println("Entering ValidateImageFile");
        try {
            imageFileData = new readImageData(fileName);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
            System.out.println("Problem Opening File: "+ fileName);
        }
        System.out.println("Exiting ValidateImageFile");
    }


    //function to validate if default image file is readable.
    public void validateDefaultImageFile(String fileName)
    {
        System.out.println("Entering validateDefaultImageFile");
        try {
            defaultImages = new readImageData(fileName);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
            System.out.println("Problem Opening File: "+ fileName);
        }
        System.out.println("Exiting validateDefaultImageFile");
    }

    //function to check if all image paths stored in the datafile are correct. if not changes them to a default image. slows run time massively if used.
    public void validateImagePaths()
    {
        System.out.println("Entering validateImagePaths");
        for (int index = 0; index < imageFileData.size(); index++) {
            try {
                Image Verify = new Image(imageFileData.get(index).getImagePath());
            } catch (Exception ex) {
                System.out.println("problem with image path detected. Bad image set to default.");
                imageFileData.get(index).setLocation("./DefaultImages/default.jpg");
            }
        }
        System.out.println("all paths Verifyed Sucessfully");
        System.out.println("exiting validateImagePaths");
    }
    //function was an extra saftey net to make sure image paths are correct however it causes increased run time so function is not needed.
    public void validateFilteredImagePaths() {
        for (int index = 0; index < filteredList.size(); index++) {
            try {
                Image Verify = new Image(filteredList.get(index).getImagePath());
            } catch (Exception ex) {
                System.out.println("problem with image path detected. Bad image set to default");
                filteredList.get(index).setLocation("./DefaultImages/default.jpg");
                System.out.println(filteredList.get(index).getImagePath());
            }
        }
    }

    //saves all image data to file
    public void saveAllImageData()
    {
        System.out.println("Entering saveAllImageData");
        writeImageData data = new writeImageData();
        try {
            data.writeImageDataToFile(allImageData,0);
        } catch (Exception ex) {
            System.out.println("Could not write to file.");
        }
        System.out.println("Exiting saveAllImageData");
    }

    //function used to fill the display arraylist
    public ArrayList<imageData> fillImageArraylist()
    {
        //for loop is used to add the image file data to the display arraylist so that images can be displayed.
        System.out.println("Entering fillImageArraylist");
        ArrayList<imageData> display = new ArrayList<imageData>();
        for (int index = placement; index < imageFileData.size(); index++) {
            if (index == imageFileData.size()) {
                display.add(imageFileData.get(index));
                break;
            }
            display.add(imageFileData.get(index));
        }
        while (display.size() < 25) {
            display.add(defaultImages.get(1));
        }

        //allImageData is filled with the contents of the file.
        for (int counter = 0; counter < imageFileData.size(); counter++) {
            allImageData.add(imageFileData.get(counter));
        }
        for (int counter = 0; counter < imageFileData.size(); counter++) {
            filteredList.add(imageFileData.get(counter));
        }
        System.out.println("Exiting fillImageArraylist");
        return display;
    }
    public void setDisplay(ArrayList<imageData> display)
    {
        System.out.println("Entering setDisplay");
        Image testImage1 = new Image(display.get(0).getImagePath());
        Image testImage2 = new Image(display.get(1).getImagePath());
        Image testImage3 = new Image(display.get(2).getImagePath());
        Image testImage4 = new Image(display.get(3).getImagePath());
        Image testImage5 = new Image(display.get(4).getImagePath());
        Image testImage6 = new Image(display.get(5).getImagePath());
        Image testImage7 = new Image(display.get(6).getImagePath());
        Image testImage8 = new Image(display.get(7).getImagePath());
        Image testImage9 = new Image(display.get(8).getImagePath());
        Image testImage10 = new Image(display.get(9).getImagePath());
        Image testImage11 = new Image(display.get(10).getImagePath());
        Image testImage12 = new Image(display.get(11).getImagePath());
        Image testImage13 = new Image(display.get(12).getImagePath());
        Image testImage14 = new Image(display.get(13).getImagePath());
        Image testImage15 = new Image(display.get(14).getImagePath());
        Image testImage16 = new Image(display.get(15).getImagePath());
        Image testImage17 = new Image(display.get(16).getImagePath());
        Image testImage18 = new Image(display.get(17).getImagePath());
        Image testImage19 = new Image(display.get(18).getImagePath());
        Image testImage20 = new Image(display.get(19).getImagePath());
        Image testImage21 = new Image(display.get(20).getImagePath());
        Image testImage22 = new Image(display.get(21).getImagePath());
        Image testImage23 = new Image(display.get(22).getImagePath());
        Image testImage24 = new Image(display.get(23).getImagePath());
        Image testImage25 = new Image(display.get(24).getImagePath());
        view1.setImage(testImage1);
        view2.setImage(testImage2);
        view3.setImage(testImage3);
        view4.setImage(testImage4);
        view5.setImage(testImage5);
        view6.setImage(testImage6);
        view7.setImage(testImage7);
        view8.setImage(testImage8);
        view9.setImage(testImage9);
        view10.setImage(testImage10);
        view11.setImage(testImage11);
        view12.setImage(testImage12);
        view13.setImage(testImage13);
        view14.setImage(testImage14);
        view15.setImage(testImage15);
        view16.setImage(testImage16);
        view17.setImage(testImage17);
        view18.setImage(testImage18);
        view19.setImage(testImage19);
        view20.setImage(testImage20);
        view21.setImage(testImage21);
        view22.setImage(testImage22);
        view23.setImage(testImage23);
        view24.setImage(testImage24);
        view25.setImage(testImage25);
        System.out.println("Exiting setDisplay");
    }

    public void moveRight()
    {
        System.out.println("[Right pressed]");
        System.out.println("isPopupdata values:(isPopupActive: "+isPopupData.get(0).getIsActive() + ") (placement: " +isPopupData.get(0).getPlacement() +") (searchActive: "+ isPopupData.get(0).getSearchActive()+ ")");

        if (placement+25 < filteredList.size()) {
            placement += 25;
            window_number.setText(String.valueOf((placement/25)+1));
            System.out.println("moved right, placement = "+placement +" filteredList size = "+ filteredList.size());
        }else if (!(placement > filteredList.size())) {
            for(int counter = 0;counter <= filteredList.size()-1;counter+= 25)
            {
                if((counter + 25) >= filteredList.size())
                {
                    placement = counter;
                }
            }
            window_number.setText(String.valueOf((placement/25)+1));
            System.out.println("moved end right, placement = "+placement +" filteredList size = "+ filteredList.size());
        }
        System.out.println("waiting?");
        //updates the image display.
        refreshDisplay();
    }
    public void moveEndRight()
    {
        System.out.println("[Right pressed]");
        System.out.println("isPopupdata values:(isPopupActive: "+isPopupData.get(0).getIsActive() + ") (placement: " +isPopupData.get(0).getPlacement() +") (searchActive: "+ isPopupData.get(0).getSearchActive()+ ")");

        if (!(placement > filteredList.size())) {
            for(int counter = 0;counter <= filteredList.size()-1;counter+= 25)
            {
                if((counter + 25) >= filteredList.size())
                {
                    placement = counter;
                }
            }
            window_number.setText(String.valueOf((placement/25)+1));
            System.out.println("moved end right, placement = "+placement +" filteredList size = "+ filteredList.size());
        }
        //updates the image display.
        refreshDisplay();
    }
    public void moveEndLeft()
    {
        System.out.println("[Right pressed]");
        System.out.println("isPopupdata values:(isPopupActive: "+isPopupData.get(0).getIsActive() + ") (placement: " +isPopupData.get(0).getPlacement() +") (searchActive: "+ isPopupData.get(0).getSearchActive()+ ")");

        if (!(placement <= 0)) {
            placement = 0;
            window_number.setText(String.valueOf((placement/25)+1));
            System.out.println("moved end left, placement = "+placement +" filteredList size = "+ filteredList.size());
        }
        //updates the image display.
        refreshDisplay();
    }
    public void moveLeft()
    {
        System.out.println("[left pressed]");
        System.out.println("isPopupdata values:(isPopupActive: "+isPopupData.get(0).getIsActive() + ") (placement: " +isPopupData.get(0).getPlacement() +") (searchActive: "+ isPopupData.get(0).getSearchActive()+ ")");
        if (!(placement <= 0)) {
            placement -= 25;
            window_number.setText(String.valueOf((placement/25)+1));
            System.out.println("moved left, placement = "+placement +" filteredList size = "+ filteredList.size());
        }
        //updates the image display.
        refreshDisplay();
    }
    public void save()
    {
        //code makes it so that every image has the correct sequential index. is a safty net for editing tags.
        for(int counter = 0;counter < allImageData.size();counter++)
        {
            allImageData.get(counter).setImageIndex(counter+1);
        }

        //fills imagefiledata and makes sure its readable.important code for making sure file data is acurate
        validateImageFile("./src/ImageData.csv");

        //allImageData is filled with the contents of the file.
        allImageData.clear();
        for (int counter = 0; counter < imageFileData.size(); counter++) {
            allImageData.add(imageFileData.get(counter));
        }

        filteredList.clear();
        //fills filtered list with allimagedata so that added tags appear.
        for (int counter = 0; counter < allImageData.size(); counter++) {
            filteredList.add(allImageData.get(counter));
        }

        // saves allimage data to file.
        writeImageData data = new writeImageData();
        try {
            data.writeImageDataToFile(allImageData,0);
        } catch (Exception ex) {
            System.out.println("Could not write to file.");
        }
        refreshDisplay();
    }
    public void filter()
    {
        System.out.println("[filter pressed]");
        System.out.println("isPopupdata values:(isPopupActive: "+isPopupData.get(0).getIsActive() + ") (placement: " +isPopupData.get(0).getPlacement() +") (searchActive: "+ isPopupData.get(0).getSearchActive()+ ")");
        String pathHolder1 = "";
        String pathHolder2 = "";
        String nameHolder1 = "";
        String nameHolder2 = "";

        //code makes it so that every image has the correct sequential index. is a safty net for editing tags.
        for(int counter = 0;counter < allImageData.size();counter++)
        {
            allImageData.get(counter).setImageIndex(counter+1);
        }

        //fills imagefiledata and makes sure its readable.important code for making sure file data is acurate
        validateImageFile("./src/ImageData.csv");

        //allImageData is filled with the contents of the file.
        allImageData.clear();
        for (int counter = 0; counter < imageFileData.size(); counter++) {
            allImageData.add(imageFileData.get(counter));
        }

        //clears filteredlist important for making sure previous searches dont linger when nothing is input for search
        filteredList.clear();
        //fills filtered list with allimagedata if nothing is input.
        for (int counter = 0; counter < allImageData.size(); counter++) {
            filteredList.add(allImageData.get(counter));
        }

        if(filteredList.size() > 0 ){
            for (int size = 0; size < filteredList.size(); size++) {
                //sorts one image. uses string arrays to seperate the extra path information from the name so the comparason is acurate.
                for (int index = 0; index < filteredList.size() - 1; index++) {

                    pathHolder1 = filteredList.get(index).getImagePath();
                    pathHolder2 = filteredList.get(index + 1).getImagePath();
                    String[] getName1 = pathHolder1.split("/");
                    String[] getName2 = pathHolder2.split("/");
                    nameHolder1 = getName1[getName1.length - 1];
                    nameHolder2 = getName2[getName2.length - 1];
                    String[] getCleanName1 = nameHolder1.split("\\.");
                    String[] getCleanName2 = nameHolder2.split("\\.");
                    String cleanHolder1 = getCleanName1[0].toLowerCase();
                    String cleanHolder2 = getCleanName2[0].toLowerCase();

                    // shen comparing two strings it tests to see if they are equal meaning that no change swap is needed
                    if (cleanHolder1.compareTo(cleanHolder2) == 0) {

                        continue;
                        // second test is hugely important. if strings have similiar character i use the .compareTo function to determine if the two string need to be swapped
                    } else if (cleanHolder1.compareTo(cleanHolder2) < 0) {
                        // switches tags
                        ArrayList<String> tempTags = new ArrayList<String>();
                        tempTags = filteredList.get(index).getTags();
                        filteredList.get(index).setTags(filteredList.get(index + 1).getTags());
                        filteredList.get(index + 1).setTags(tempTags);
                        //switches image paths
                        String tempPath = pathHolder1;
                        pathHolder1 = pathHolder2;
                        pathHolder2 = tempPath;
                        filteredList.get(index).setLocation(pathHolder1);
                        filteredList.get(index + 1).setLocation(pathHolder2);

                        continue;

                    }
                }
            }
        }

        // loops as many times as the size of the array
        for (int size = 0; size < allImageData.size(); size++) {
            //sorts one image. uses string arrays to seperate the extra path information from the name so the comparason is acurate.
            for (int index = 0; index < allImageData.size() - 1; index++) {

                pathHolder1 = allImageData.get(index).getImagePath();
                pathHolder2 = allImageData.get(index + 1).getImagePath();
                String[] getName1 = pathHolder1.split("/");
                String[] getName2 = pathHolder2.split("/");
                nameHolder1 = getName1[getName1.length - 1];
                nameHolder2 = getName2[getName2.length - 1];
                String[] getCleanName1 = nameHolder1.split("\\.");
                String[] getCleanName2 = nameHolder2.split("\\.");
                String cleanHolder1 = getCleanName1[0].toLowerCase();
                String cleanHolder2 = getCleanName2[0].toLowerCase();

                // shen comparing two strings it tests to see if they are equal meaning that no change swap is needed
                if (cleanHolder1.compareTo(cleanHolder2) == 0) {

                    continue;
                    // second test is hugely important. if strings have similiar character i use the .compareTo function to determine if the two string need to be swapped
                } else if (cleanHolder1.compareTo(cleanHolder2) < 0) {
                    // switches tags
                    ArrayList<String> tempTags = new ArrayList<String>();
                    tempTags = allImageData.get(index).getTags();
                    allImageData.get(index).setTags(allImageData.get(index + 1).getTags());
                    allImageData.get(index + 1).setTags(tempTags);
                    //switches image paths
                    String tempPath = pathHolder1;
                    pathHolder1 = pathHolder2;
                    pathHolder2 = tempPath;
                    allImageData.get(index).setLocation(pathHolder1);
                    allImageData.get(index + 1).setLocation(pathHolder2);
                    continue;
                }
            }
        }
        //fixes the index order of the sort.
        for(int counter = 0;counter < allImageData.size();counter++)
        {
            allImageData.get(counter).setImageIndex(counter+1);
        }

        // saves allimage data to file.
        writeImageData data = new writeImageData();
        try {
            data.writeImageDataToFile(allImageData,0);
        } catch (Exception ex) {
            System.out.println("Could not write to file.");
        }
        //updates the image display.
        refreshDisplay();
    }
    public void clearSearch()
    {
        int searchActive = Integer.parseInt(isPopupData.get(0).getSearchActive());
        //code makes it so that every image has the correct sequential index. is a safty net for editing tags.
        for(int counter = 0;counter < allImageData.size();counter++)
        {
            allImageData.get(counter).setImageIndex(counter+1);
        }

        //fills imagefiledata and makes sure its readable.important code for making sure file data is acurate
        validateImageFile("./src/ImageData.csv");

        //allImageData is filled with the contents of the file.
        allImageData.clear();
        for (int counter = 0; counter < imageFileData.size(); counter++) {
            allImageData.add(imageFileData.get(counter));
        }

        //clears filteredlist important for making sure previous searches dont linger when nothing is input for search
        filteredList.clear();
        //fills filtered list with allimagedata if nothing is input.
        for (int counter = 0; counter < allImageData.size(); counter++) {
            filteredList.add(allImageData.get(counter));
        }
        placement= 0;
        window_number.setText(String.valueOf((placement)+1));
        lblErrorDisplay.setText("Search Cleared");
        searchActive = 0;
        isPopupData.get(0).setSearchActive(Integer.toString(searchActive));
        text_field.requestFocus();
        text_field.selectAll();

        // saves allimage data to file.
        writeImageData data = new writeImageData();
        try {
            data.writeImageDataToFile(allImageData,0);
        } catch (Exception ex) {
            System.out.println("Could not write to file.");
        }
        //updates the image display.
        refreshDisplay();
    }
    public void directSearch()
    {
        int searchActive = Integer.parseInt(isPopupData.get(0).getSearchActive());
        //code makes it so that every image has the correct sequential index. is a safty net for editing tags.
        for(int counter = 0;counter < allImageData.size();counter++)
        {
            allImageData.get(counter).setImageIndex(counter+1);
        }

        //fills imagefiledata and makes sure its readable.important code for making sure file data is acurate
        validateImageFile("./src/ImageData.csv");

        //allImageData is filled with the contents of the file.
        allImageData.clear();
        for (int counter = 0; counter < imageFileData.size(); counter++) {
            allImageData.add(imageFileData.get(counter));
        }

        if(text_field.getText().length() == 0){
            lblErrorDisplay.setText("You Haven't Entered Anything.");
            text_field.requestFocus();
            text_field.selectAll();
        } else if(text_field.getText().length() > 0)
        {
            //clears the list before the search
            filteredList.clear();
            //sets placement to 0 so that pannels are not set out of bounds of the size. ex 4 images but searched on pannel 4 so pannel 4 through 2 are blank. puts user at the beginning pannel
            placement = 0;
            window_number.setText(String.valueOf((placement/25)+1));

            //temp variables for holding path
            String pathHolder1 = "";
            String nameHolder1 = "";
            //makes it so that other buttons dont reset the search results if movement is activated.
            searchActive = 1;
            isPopupData.get(0).setSearchActive(Integer.toString(searchActive));

            lblErrorDisplay.setText("Searching for Image: "+ text_field.getText());

            for (int index = 0; index < allImageData.size(); index++) {
                // cleans up the path into the name only.
                pathHolder1 = allImageData.get(index).getImagePath();
                String[] getName1 = pathHolder1.split("/");
                nameHolder1 = getName1[getName1.length - 1];
                String[] getCleanName1 = nameHolder1.split("\\.");
                String cleanHolder1 = getCleanName1[0];
                // tests if the name equals any names in the allimagedata array
                if(cleanHolder1.equals(text_field.getText())){
                    filteredList.add(allImageData.get(index));
                }
            }
        }
        text_field.setText("");

        // saves allimage data to file.
        writeImageData data = new writeImageData();
        try {
            data.writeImageDataToFile(allImageData,0);
        } catch (Exception ex) {
            System.out.println("Could not write to file.");
        }
        //updates the image display.
        refreshDisplay();
    }
    public void tagSearch()
    {
        int searchActive = Integer.parseInt(isPopupData.get(0).getSearchActive());
        //code makes it so that every image has the correct sequential index. is a safty net for editing tags.
        for(int counter = 0;counter < allImageData.size();counter++)
        {
            allImageData.get(counter).setImageIndex(counter+1);
        }

        //fills imagefiledata and makes sure its readable.important code for making sure file data is acurate
        validateImageFile("./src/ImageData.csv");

        //allImageData is filled with the contents of the file.
        allImageData.clear();
        for (int counter = 0; counter < imageFileData.size(); counter++) {
            allImageData.add(imageFileData.get(counter));
        }

        // default condition if button is press but no input is given
        if(text_field.getText().length() == 0){
            lblErrorDisplay.setText("You Haven't Entered Anything.");
            text_field.requestFocus();
            text_field.selectAll();
        }else if(text_field.getText().length() > 0) {
            searchActive = 1;
            isPopupData.get(0).setSearchActive(Integer.toString(searchActive));
            //clears the list before the search
            filteredList.clear();
            //sets placement to 0 so that pannels are not set out of bounds of the size. ex 4 images but searched on pannel 4 so pannel 4 through 2 are blank. puts user at the beginning pannel
            placement = 0;
            window_number.setText(String.valueOf((placement / 25) + 1));
        }

        if(text_field.getText().length() == 0)
        {
            lblErrorDisplay.setText("Nothing has been entered.");
            text_field.requestFocus();
            text_field.selectAll();
            //does stuff if input is detected
        }else if(text_field.getText().length() > 0)
        {
            //clears the list before the search
            filteredList.clear();
            //sets placement to 0 so that pannels are not set out of bounds of the size. ex 4 images but searched on pannel 4 so pannel 4 through 2 are blank. puts user at the beginning pannel
            placement = 0;
            window_number.setText(String.valueOf((placement/25)+1));
            // splits up the tags into array items.
            String[] splitTags = text_field.getText().split(",");
            ArrayList<String> currentTags = new ArrayList<String>();
            //fills the array currenttags with tags given by the user.
            for(int inc = 0;inc < splitTags.length;inc++)
            {
                currentTags.add(splitTags[inc]);
            }
            //if only one tag is input then search for that tag.
            if(currentTags.size() == 1)
            {
                lblErrorDisplay.setText("Searching for tag <"+ currentTags.get(0)+">");
                System.out.println("one tag entered for Tag Search");
                //loops through the whole array.
                for (int index = 0; index < allImageData.size(); index++) {
                    //creates a temp array of tags from allimagedata member at index
                    ArrayList<String> tempTags = new ArrayList<String>();
                    tempTags = allImageData.get(index).getTags();
                    // compares tags to given input tags in
                    for (int index1 = 0; index1 < tempTags.size() ; index1++) {
                        if(text_field.getText().equals(tempTags.get(index1))){
                            System.out.println("found tag "+currentTags.get(0)+" with "+allImageData.get(index).getImagePath());
                            filteredList.add(allImageData.get(index));
                            break;
                        }

                    }
                }

            }else if(splitTags.length > 1)
            {
                System.out.println("multiple tags search detected");
                String allSearchedTags = "";
                // fills the display so it shows all tags
                for(int fill = 0; fill< splitTags.length;fill++)
                {
                    if (fill == splitTags.length-1)
                    {
                        allSearchedTags += currentTags.get(fill);
                    } else
                    {
                        allSearchedTags += currentTags.get(fill)+", ";
                    }
                }
                lblErrorDisplay.setText("Searching for tags <"+ allSearchedTags+">");

                System.out.println("TAGS TO BE SEARCHED");
                for(int inc = 0;inc < splitTags.length;inc++)
                {
                    System.out.println(currentTags.get(inc));
                }
                //loops through the whole array to compare and see if the tags where present.
                for (int index = 0; index < allImageData.size(); index++) {
                    //creates a temp array of tags from allimagedata member at index
                    int tagsFound = 0;
                    for(int counter = 0;counter < currentTags.size();counter++) {
                        //fills an array with data of the given tags from a single item from allimagedata
                        ArrayList<String> tempTags = new ArrayList<String>();
                        tempTags = allImageData.get(index).getTags();
                        // compares tags to given input tags in
                        for (int index1 = 0; index1 < tempTags.size(); index1++) {
                            System.out.println("in comparing a single tag");
                            if (currentTags.get(counter).equals(tempTags.get(index1))) {
                                System.out.println("found tag " + currentTags.get(0) + " with " + allImageData.get(index).getImagePath());
                                tagsFound++;
                            }

                        }
                    }
                    if (tagsFound == currentTags.size())
                    {
                        filteredList.add(allImageData.get(index));
                    }
                }
            }

        }
        text_field.setText("");

        // saves allimage data to file.
        writeImageData data = new writeImageData();
        try {
            data.writeImageDataToFile(allImageData,0);
        } catch (Exception ex) {
            System.out.println("Could not write to file.");
        }
        //updates the image display.
        refreshDisplay();
    }
    public void addImage()
    {
        //code makes it so that every image has the correct sequential index. is a safty net for editing tags.
        for(int counter = 0;counter < allImageData.size();counter++)
        {
            allImageData.get(counter).setImageIndex(counter+1);
        }

        FileChooser Image_Add = new FileChooser();
        String currentPath = "./src/Images";
        Image_Add.setInitialDirectory(new File(currentPath));
        File Add = Image_Add.showOpenDialog(null);
        String path = Add.getAbsolutePath();
        String locate = "";
        String truePath = "";
        Boolean error = false;

        //fills imagefiledata and makes sure its readable.
        validateImageFile("./src/ImageData.csv");

        //allImageData is filled with the contents of the file.
        allImageData.clear();
        for (int counter = 0; counter < imageFileData.size(); counter++) {
            allImageData.add(imageFileData.get(counter));
        }
        //if no image is detected
        if (Image_Add == null) {
            return;
        } else {
            for (int counter = 0; counter <= path.length() - 1; counter++) {
                if ((path.charAt(counter) >= 32 && path.charAt(counter) <= 91) || (path.charAt(counter) >= 93 && path.charAt(counter) <= 127)) {
                    locate += Add.getAbsolutePath().charAt(counter);
                } else {
                    locate += "/";
                }
            }
            String[] fields = locate.split("src");
            try {
                truePath = fields[1];
            } catch (Exception ex) {
                error = true;
                System.out.println("Image in Wrong Folder. Put under src in program files.");
            }
            if (error == false) {
                truePath = fields[1];
                truePath = "." + truePath;
                System.out.println(truePath);
            }
            try {
                Image Verify = new Image(truePath);
            } catch (Exception ex) {
                error = true;
                System.out.println("problem with image path detected. Please Try Agian.");
            }
            // when adding a new image this line sets the index order to the last index.
            truePath+=","+String.valueOf(allImageData.size()+1);
        }
        if (error == false) {
            imageData verifyed = new imageData(truePath);
            allImageData.add(verifyed);
            filteredList.add(verifyed);
            //saves data
            writeImageData data = new writeImageData();
            try {
                data.writeImageDataToFile(allImageData,0);
            } catch (Exception ex) {
                System.out.println("Could not write to file.");
            }
        }
        //updates the image display.
        refreshDisplay();
    }
    public void refreshDisplay()
    {
        System.out.println("Entering refreshDisplay");
        //code that displays images to the image pannels in the 5 x 5 grid
        ArrayList<imageData> display = new ArrayList<imageData>();

        //function was called hear as an added saftey net to protect agians a bad image path. not needed and causes increased run time.
        //validateFilteredImagePaths();

        for (int index = placement; index < filteredList.size(); index++) {

            if (index == filteredList.size()) {
                display.add(filteredList.get(index));
                break;
            }
            display.add(filteredList.get(index));
        }
        while (display.size() < 25) {
            display.add(defaultImages.get(1));
        }
        //another important check to make sure that images displayed dont change while popupis open. i might change this as it could be useful to change the images displayed on the main window if possible. at the very least i could change the start up code to load the images bassed on placement.
        setDisplay(display);
        System.out.println("Exiting refreshDisplay");
    }
    public void writeToFilteredList()
    {
        writeImageData data = new writeImageData();
        try {
            data.writeImageDataToFile(filteredList,1);
        } catch (Exception ex) {
            System.out.println("Could not write to file.");
        }
    }

    /*
     * ====================================================================================================================================================================================
     * ====================================================================================================================================================================================
     * Main Window Function Definitions
     * ====================================================================================================================================================================================
     * ====================================================================================================================================================================================
     * */

    public void popupStartIndex()
    {
        for(int counter = 0;counter < allImageData.size();counter++)
        {
            allImageData.get(counter).setImageIndex(counter+1);
        }
    }

    public void popupMoveRight()
    {
        System.out.println("moved right");
        if (popupPlacement < filteredList.size()-1 ) {
            popupPlacement += 1;
            System.out.println("popupplacement: "+popupPlacement+" Filtered list size: "+ filteredList.size());
            popup_counter.setText("Index: "+String.valueOf(String.valueOf(popupPlacement+1)));
            String allTags = "Tags: ";
            ArrayList<String> currentTags = filteredList.get(popupPlacement).getTags();
            for(int counter = 0;counter < currentTags.size();counter++)
            {
                if(counter<currentTags.size()-1){
                    allTags+= currentTags.get(counter)+",";
                }else{
                    allTags+= currentTags.get(counter);
                }
            }
            tag_list.setText(allTags);
            tag_Error.setText("");
            tf_add_tag.clear();
        }
    }

    public void popupMoveLeft()
    {
        System.out.println("moved right");
        if ((popupPlacement > 0)) {
            popupPlacement -= 1;
            System.out.println("popupplacement: "+popupPlacement+" Filtered list size: "+ filteredList.size());
            popup_counter.setText("Index: "+String.valueOf(popupPlacement+1));
            String allTags = "Tags: ";
            ArrayList<String> currentTags = filteredList.get(popupPlacement).getTags();
            for(int counter = 0;counter < currentTags.size();counter++)
            {
                if(counter<currentTags.size()-1){
                    allTags+= currentTags.get(counter)+",";
                }else{
                    allTags+= currentTags.get(counter);
                }
            }
            tag_list.setText(allTags);
            tag_Error.setText("");
            tf_add_tag.clear();
        }
    }

    public void popupAddTag()
    {
        System.out.println("entering btn_add_tag");
        tag_Error.setText("");
        // does nothing and directs user to the
        if(tf_add_tag.getText().length() == 0){
            tag_Error.setText("You Haven't Entered Anything.");
            tf_add_tag.requestFocus();
            tf_add_tag.selectAll();
        }else if(tf_add_tag.getText().length() > 0){
            // optional protection. used to make sure that user does not put in multiple tags because they put a comma in there input. might not be needed.
            String noCommas = tf_add_tag.getText();
            boolean commasDetected = false;
            for(int counter = 0;counter < noCommas.length();counter++){
                if(noCommas.charAt(counter) == ','){
                    commasDetected = true;
                    tag_Error.setText("Error, please avoid commas in input.");
                    tf_add_tag.clear();
                    break;
                }
            }
            if(commasDetected == false)
            {
                System.out.println("entering adding tag with no commas detected");
                // grabs the index of the image in allimagedata so added tag is going to the right data item in all image data.
                int trueIndex = filteredList.get(popupPlacement).getImageIndex();
                // loops through the whole allimagedata array to find the data item with the correct index.
                for(int counter = 0; counter < allImageData.size();counter++)
                {

                    System.out.println("in loop checking allimagedata for the image with the right indext to add the new tag to");
                    //tests to see if index we want is at given counter.
                    if(allImageData.get(counter).getImageIndex() == trueIndex) {
                        //dumby array to add our new tag
                        ArrayList<String> indexTags = allImageData.get(counter).getTags();
                        //adds tag to dumby array, line below was commented out due to a bug where the tag was added twice
                        //indexTags.add(tf_add_tag.getText());
                        //sets our new array to the array of the data item.

                        System.out.println("adding "+tf_add_tag.getText()+" to allimagedata");
                        allImageData.get(counter).setTags(indexTags);



                        //does the same thing as above accept it adds the tag to the current list filtered or not.
                        ArrayList<String> displayTags = filteredList.get(popupPlacement).getTags();
                        displayTags.add(tf_add_tag.getText());
                        filteredList.get(popupPlacement).setTags(displayTags);
                        tag_Error.setText("Adding tag: <" + tf_add_tag.getText() + ">");

                        // updates the visual tags being shown.
                        String allTags = "Tags: ";
                        ArrayList<String> currentTags = filteredList.get(popupPlacement).getTags();
                        for (int counter1 = 0; counter1 < currentTags.size(); counter1++) {
                            if (counter1 < currentTags.size() - 1) {
                                allTags += currentTags.get(counter1) + ",";
                            } else {
                                allTags += currentTags.get(counter1);
                            }
                        }
                        tag_list.setText(allTags);

                        //saves data to file to make sure tag is added to the data base.
                        writeImageData data1 = new writeImageData();
                        try {
                            data1.writeImageDataToFile(allImageData, 0);
                        } catch (Exception ex) {
                            System.out.println("Could not write to file.");
                        }
                    }
                }
            }
        }
        tf_add_tag.clear();
    }

    public void popupRemoveTag()
    {
        String allTags = "Tags: ";
        ArrayList<String> currentTags = filteredList.get(popupPlacement).getTags();
        for(int counter = 0;counter < currentTags.size();counter++)
        {
            if(counter<currentTags.size()-1){
                allTags+= currentTags.get(counter)+",";
            }else{
                allTags+= currentTags.get(counter);
            }
        }
        // default display if nothings entered
        if (tf_add_tag.getText().length() == 0) {
            tag_Error.setText("You Haven't Entered Anything.");
            tf_add_tag.requestFocus();
            tf_add_tag.selectAll();
        }
        else if (tf_add_tag.getText().length() > 0)
        {
            tag_Error.setText("Tag <" + tf_add_tag.getText() + "> not found.");
            System.out.println("one tag entered for Tag removal");
            //creates a temp array of tags from allimagedata member at index
            // used to tell where the given object is in allimagedata
            int trueIndex = filteredList.get(popupPlacement).getImageIndex();
            //first loop compares all tags in current tags item
            for (int index1 = 0; index1 < currentTags.size(); index1++) {
                if (tf_add_tag.getText().equals(currentTags.get(index1))) {
                    System.out.println("found tag " + currentTags.get(index1) + " for removal:" + tf_add_tag.getText());
                    // same code from add except it removes one item.
                    //if tag is found then loop through image data
                    for(int counter = 0; counter < allImageData.size();counter++)
                    {
                        //tests to see if index we want is at given counter.
                        if(allImageData.get(counter).getImageIndex() == trueIndex)
                        {
                            //dumby array to add our new tag
                            ArrayList<String> indexTags = allImageData.get(counter).getTags();
                            //adds tag to dumby array
                            indexTags.remove(tf_add_tag.getText());
                            //sets our new array to the array of the data item.
                            //allImageData.get(counter).setTags(indexTags);

                            //does the same thing as above accept it adds the tag to the current list filtered or not.
                            ArrayList<String> displayTags = filteredList.get(popupPlacement).getTags();
                            //line removed due to bug where removal of tag happened twice
                            //displayTags.remove(index1);
                            filteredList.get(popupPlacement).setTags(displayTags);

                            // updates the visual tags being shown.
                            allTags = "Tags: ";
                            ArrayList<String> currentImageTags = filteredList.get(popupPlacement).getTags();
                            for(int counter1 = 0;counter1 < currentImageTags.size();counter1++)
                            {
                                if(counter1<currentImageTags.size()-1){
                                    allTags+= currentImageTags.get(counter1)+",";
                                }else{
                                    allTags+= currentImageTags.get(counter1);
                                }
                            }
                            tag_list.setText(allTags);
                            //where to up the fix for index out of range when removing objects
                            placement--;
                            isPopupData.get(0).setPlacement(Integer.toString((placement)));
                            //writes to ispopupdata file.
                            writeImageData data1 = new writeImageData();
                            try {
                                data1.writeImageDataToFile(filteredList,1);
                            } catch (Exception ex) {
                                System.out.println("Could not write to file.");
                            }

                            //saves data to file to make sure tag is added to the data base.
                            writeImageData data2 = new writeImageData();
                            try {
                                data2.writeImageDataToFile(allImageData,0);
                            } catch (Exception ex) {
                                System.out.println("Could not write to file.");
                            }
                        }
                    }

                    tag_Error.setText("removed tag: <" + tf_add_tag.getText() + ">");
                    break;
                }

            }
        }
        tag_list.setText(allTags);
        tf_add_tag.clear();
    }
    public void popupRemoveImage()
    {
        //looks through all of allimagedata
        for(int counter = 0;counter < allImageData.size();counter++)
        {
            //if we find a match for the image in allimage data and filtered list
            if(allImageData.get(counter).getImageIndex() == filteredList.get(popupPlacement).getImageIndex())
            {
                //then we remove that item from both array lists
                allImageData.remove(counter);
                filteredList.remove(popupPlacement);
                popupPlacement--;
                //preps messages to be correct.
                tag_Error.setText("image successfully removed.");

                //shifts the tags over.
                String allTags = "Tags: ";
                ArrayList<String> currentImageTags = filteredList.get(popupPlacement).getTags();
                for(int counter1 = 0;counter1 < currentImageTags.size();counter1++)
                {
                    if(counter1<currentImageTags.size()-1){
                        allTags+= currentImageTags.get(counter1)+",";
                    }else{
                        allTags+= currentImageTags.get(counter1);
                    }
                }
                //integer with the correct index value to be displayed.
                int correctIndex = popupPlacement+1;
                popup_counter.setText("index: "+ correctIndex);

                // preservers the order between button presses. fixes an issue where remove tag function stopped working if remove image was used. 1 2 4 5 6 7 vs 1 2 3 4 5 6 after being called.
                for(int counter1 = 0;counter1 < filteredList.size();counter1++)
                {
                    filteredList.get(counter1).setImageIndex(counter1+1);
                }

                for(int counter2 = 0;counter2 < allImageData.size();counter2++)
                {
                    allImageData.get(counter2).setImageIndex(counter2+1);
                }

                // updates the visual tags being shown.
                allTags = "Tags: ";
                ArrayList<String> currentTags = filteredList.get(popupPlacement).getTags();
                for (int counter1 = 0; counter1 < currentTags.size(); counter1++) {
                    if (counter1 < currentTags.size() - 1) {
                        allTags += currentTags.get(counter1) + ",";
                    } else {
                        allTags += currentTags.get(counter1);
                    }
                }
                tag_list.setText(allTags);
                //updates popup display to be accurate
                Image popupRefresh = new Image(filteredList.get(popupPlacement).getImagePath());
                popupImageView0.setImage(popupRefresh);

                //writes to the nessary file so that the change is viewed
                writeImageData data1 = new writeImageData();
                try {
                    data1.writeImageDataToFile(filteredList,1);
                } catch (Exception ex) {
                    System.out.println("Could not write to file.");
                }
            }
        }

        // saves allimage data to file.
        writeImageData data1 = new writeImageData();
        try {
            data1.writeImageDataToFile(allImageData,0);
        } catch (Exception ex) {
            System.out.println("Could not write to file.");
        }
    }


    /*
     * ====================================================================================================================================================================================
     * ====================================================================================================================================================================================
     * Main Initilizer
     * ====================================================================================================================================================================================
     * ====================================================================================================================================================================================
     * */
    //creates the first instance of uicontroller and loads the image view with the first page of images.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //first one verifys if the popup window is open by reading the popup file
        System.out.println("\n[entering UiController Initializer]");
        //checks to see if popup window is open.
        isPopupOpen("./src/PopupData.csv");

        if ("1" == isPopupData.get(0).getIsActive()) {
            inPopup = true;
        } else {
            inPopup = false;
        }

        //tests to see if image  data file can be read as well as the default image data.
        validateImageFile("./src/ImageData.csv");
        validateDefaultImageFile("./src/DefaultImages.csv");

        //checks if all image paths are valid. slows run time massively
        //validateImagePaths();

        //creates an arraylist of image data types. then fills the array with the display items by calling the fillImageArraylist function
        ArrayList<imageData> display = fillImageArraylist();

        //protects the second instance of uicontroller from trying to display images to imageviewers not present.
        int tester = Integer.parseInt(isPopupData.get(0).getIsActive());

            if (tester == 0)
            {
                System.out.println("\n[entering UiController Initializer, tester = 0]");
                System.out.println("isPopupdata values:(isPopupActive: "+isPopupData.get(0).getIsActive() + ") (placement: " +isPopupData.get(0).getPlacement() +") (searchActive: "+ isPopupData.get(0).getSearchActive()+ ")");

                //code makes it so that every image has the correct sequential index. is a safty net for editing tags.
                for(int counter = 0;counter < allImageData.size();counter++)
                {
                    allImageData.get(counter).setImageIndex(counter+1);
                }

                //sets place in textfield to display pannel number
                window_number.setText(String.valueOf(placement+1));

                //calls function to output images to there respective viewers
                setDisplay(display);

                //sets search active to zero on boot up. not possible for a search to be active whe program is booting up
                isPopupData.get(0).setSearchActive(Integer.toString(0));

                //code makes it so that every image has the correct sequential index. is a safty net for editing tags.
                for(int counter = 0;counter < allImageData.size();counter++)
                {
                    allImageData.get(counter).setImageIndex(counter+1);
                }

                // saves all image data to file.
                saveAllImageData();

                System.out.println("[exiting UiController Initializer, tester = 0]\n");
            }
            //when popup is open this code makes sure that the image clicked on is displayed.
            //grabs popupdate from file to see. the variable changes depending on if the popup is open or not.
            int test = Integer.parseInt(isPopupData.get(0).getIsActive());
        /*
         * ====================================================================================================================================================================================
         * ====================================================================================================================================================================================
         * Popup Initilizer
         * ====================================================================================================================================================================================
         * ====================================================================================================================================================================================
         * */
            if (test == 1) {
                //fills list so that pop up functions correctly on start up.

                System.out.println("\n[entering UiController Initializer, tester = 1]");
                System.out.println("isPopupdata values:(isPopupActive: "+isPopupData.get(0).getIsActive() + ") (placement: " +isPopupData.get(0).getPlacement() +") (searchActive: "+ isPopupData.get(0).getSearchActive()+ ")");

                //fills imagefiledata and makes sure its readable.important code for making sure file data is acurate
                validateImageFile("./src/ImageData.csv");

                //allImageData is filled with the contents of the file.
                allImageData.clear();
                for (int counter = 0; counter < imageFileData.size(); counter++) {
                    allImageData.add(imageFileData.get(counter));
                }

                //tests to see if default data is readable as well as the files they come from. if not throws exception
                try {
                    filteredList = new readImageData("./src/PopupImageData.csv");
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                    System.exit(1);
                }
                // nested for loop to make sure if a tag is added that it will persist between popup instances without needing to click another button.
                //makes sure that tags remain consistant between popup window instances. fixes the problem of adding tags, closing popup window,
                // then opening the popup window again and having the proper tangs not being displayed
                for (int counter1 = 0; counter1 < allImageData.size(); counter1++) {
                    for (int counter2 = 0; counter2 < filteredList.size(); counter2++) {
                        //System.out.println("outer: "+counter1+ " inner: "+ counter2+ " allImageData path:"+allImageData.get(counter1).getImagePath()+ " filteredList path:"+filteredList.get(counter2).getImagePath());
                        if (allImageData.get(counter1).getImageIndex() == filteredList.get(counter2).getImageIndex()) {
                            filteredList.get(counter2).setTags(allImageData.get(counter1).getTags());
                            break;
                        }
                    }
                }
                //code to set the counter variable to be acurate on start up.
                try{
                    //sets pop up counter text
                    popup_counter.setText("Index: "+String.valueOf(Integer.parseInt(isPopupData.get(0).getPlacement())));
                    System.out.println("starting position of popup: "+Integer.parseInt(isPopupData.get(0).getPlacement()));
                    String allTags = "Tags: ";
                    ArrayList<String> currentTags = filteredList.get(placement + Integer.parseInt(isPopupData.get(0).getPlacement())-1).getTags();
                    for(int counter = 0;counter < currentTags.size();counter++)
                    {
                        if(counter<currentTags.size()-1){
                            allTags+= currentTags.get(counter)+",";
                        }else{
                            allTags+= currentTags.get(counter);
                        }
                    }
                    tag_list.setText(allTags);
                }catch(Exception ex){
                    System.out.println("popup active incorrectly set. now fixing");
                    isPopupData.get(0).setIsActive("0");
                }
                //tests to see if image cant be loaded to the popup viewer. also insures that an image will be loaded on popup startup.
            try {
                //chunk of code to make sure that empty images used to fill the blank image viewer pannels are not going to be acessable. ensures that the popup window image displayed stays iwth in the bounds of allimagedata arraylist
                //test to see if at the end of the main display. if a default image is clicked on then the placement compares the allimagedata size to see how far over image is.
                //then sets image and location to the last image in allimagedata arraylist.
                if(placement + Integer.parseInt(isPopupData.get(0).getPlacement()) > filteredList.size())
                {
                    //sets image view to image clicked on
                    Image popupImageStartView = new Image(filteredList.get(filteredList.size()-1).getImagePath());
                    popupImageView0.setImage(popupImageStartView);
                    //sets pop up placement to the list size
                    popupPlacement = filteredList.size()-1;
                    popup_counter.setText("Index: "+String.valueOf(filteredList.size()));
                    //Code sets tags so they can be viewed on the popup window.
                    String allTags = "Tags: ";
                    ArrayList<String> currentTags = filteredList.get(filteredList.size()-1).getTags();
                    for(int counter = 0;counter < currentTags.size();counter++)
                    {
                        if(counter<currentTags.size()-1){
                            allTags+= currentTags.get(counter)+",";
                        }else{
                            allTags+= currentTags.get(counter);
                        }
                    }
                    tag_list.setText(allTags);
                }else {
                    Image popupImageStartView = new Image(filteredList.get(Integer.parseInt(isPopupData.get(0).getPlacement()) - 1).getImagePath());
                    popupImageView0.setImage(popupImageStartView);
                    popupPlacement = Integer.parseInt(isPopupData.get(0).getPlacement())-1;
                    System.out.println(" original popup placement value:"+ popupPlacement);
                }
            } catch (Exception ex) {
                System.out.println("Could not put image in imageview");
            }
                System.out.println("[exiting UiController Initializer, tester = 1]\n");
        }
        System.out.println("[exiting UiController Initializer]\n");
    }
    //main controller for buttons.
    /*
     * ====================================================================================================================================================================================
     * ====================================================================================================================================================================================
     *  Main Controller
     * ====================================================================================================================================================================================
     * ====================================================================================================================================================================================
     * */
    @FXML
    void imageMovement(ActionEvent event) {

        System.out.println("\n[entering UiController imageMovement]");
        System.out.println("isPopupdata values:(isPopupActive: "+isPopupData.get(0).getIsActive() + ") (placement: " +isPopupData.get(0).getPlacement() +") (searchActive: "+ isPopupData.get(0).getSearchActive()+ ")");

        //variables for processing the button names.
        int Image_add = 0;
        Button btn = (Button) event.getSource();
        String btnName = btn.getId();
        String temp = "";
        int tester = Integer.parseInt(isPopupData.get(0).getIsActive());
        int searchActive = Integer.parseInt(isPopupData.get(0).getSearchActive());
        //important if check to make sure buttons cannot be accessed if popup is open.

        //clears filtered list so that left over search results do not linger in filtered list if another search is performed.

        //fills filtered list
        if(searchActive == 0) {
            //clears filtered list so that left over search results do not linger in filtered list if another search is performed.
            filteredList.clear();
            for (int counter = 0; counter < allImageData.size(); counter++) {
                filteredList.add(allImageData.get(counter));
            }
        }

        //important if check to make sure buttons cannot be accessed if popup is open.
        if (tester == 0) {
            System.out.println("\n[entering UiController imageMovement, tester = 0]");
            System.out.println("isPopupdata values:(isPopupActive: "+isPopupData.get(0).getIsActive() + ") (placement: " +isPopupData.get(0).getPlacement() +") (searchActive: "+ isPopupData.get(0).getSearchActive()+ ")");
            //important fix to a instance problem.
            //this sequence is important to make sure that tags added while in popup window stay and are not over written by the current instance of allimage data
            //first we read the image file data.
            validateImageFile("./src/ImageData.csv");

            //then we verify every image saved to file is valid. if not throws exception and changes the bad image path to one of the default images.
            //validateImagePaths();

            //then we fill the all image data with our data file. now our data will not be overwritten.
            allImageData.clear();
            for (int counter = 0; counter < imageFileData.size(); counter++) {
                allImageData.add(imageFileData.get(counter));
            }

            //code makes it so that every image has the correct sequential index. is a saftey net for editing tags.
            for(int counter = 0;counter < allImageData.size();counter++)
            {
                allImageData.get(counter).setImageIndex(counter+1);
            }

            //code to filter image data
            if(btnName.equals("filter")) {
                filter();
            }

            //code to navigate between image panels
            //moves right one pannel
            if (btnName.equals("btn_right")) {
                moveRight();
            }

            //moves left one panel
            if (btnName.equals("btn_left")) {
                moveLeft();
            }

            //moves to the end of the panels
            if (btnName.equals("btn_right_end")) {
                moveEndRight();
            }

            //moves to the beginning of the panels
            if (btnName.equals("btn_left_end")) {
                moveEndLeft();
            }

            // controls save button. used to save new image data added by the use to the image data file
            if (btnName.equals("btn_save")) {
                save();
            }
            //clears the search if a search was attempted
            if(btnName.equals("clear_Search")){
                clearSearch();
            }

            //does a direct name search for image
            if (btnName.equals("direct_Search")) {
                directSearch();
            }

            //searches images based on tags
            if (btnName.equals("tag_Search")) {
                tagSearch();
            }

            // code to add image to the data base.
            if (btnName.equals("Add_Image")) {
                addImage();
            }

            System.out.println("[exiting UiController imageMovement, tester = 0]\n");
            /*
             * ====================================================================================================================================================================================
             * ====================================================================================================================================================================================
             *  Popup Controller
             * ====================================================================================================================================================================================
             * ====================================================================================================================================================================================
             * */
        } else if (tester == 1) {
            System.out.println("[entering UiController imageMovement, tester = 1]");
            //code to navigate between image popup pannels

            // fills the popup data file so that it can be acurate when popup is activated
            writeToFilteredList();

            //code makes it so that every image has the correct sequential index. is a safty net for editing tags.
            popupStartIndex();

                if (btnName.equals("btn_right")) {
                    popupMoveRight();
                }

                if (btnName.equals("btn_left")) {
                    popupMoveLeft();
                }
                //adds a tag to the given image
                if(btnName.equals("btn_add_tag")){
                    popupAddTag();
                }
                //removes tags using the search engine from our main controller when tester == 0
                if(btnName.equals("btn_remove_tag")) {
                    popupRemoveTag();
                }
                if(btnName.equals("btn_remove_image"))
                {
                    popupRemoveImage();
                }

                //sets popup imageviewer to display correct image.
                try{
                    Image popupImageStartView = new Image(filteredList.get(popupPlacement).getImagePath());
                    popupImageView0.setImage(popupImageStartView);
                }catch (Exception ex)
                {
                    System.out.println("popup index out of range.");
                }


            System.out.println("[exiting UiController imageMovement, tester = 1]\n");
        }
    }

    /*
     * ====================================================================================================================================================================================
     * ====================================================================================================================================================================================
     *  Popup Launcher
     * ====================================================================================================================================================================================
     * ====================================================================================================================================================================================
     * */
    //controller to launch larger view window popup
    @FXML
    public void imageView(MouseEvent event) throws IOException {
        //variables to grap the image view id as well as test int to see if popup is on.
        System.out.println("popup window activated");
        ImageView viewer = (ImageView) event.getSource();
        String viewerName = viewer.getId();
        String[] fields = viewerName.split("w");
        String location = "";
        location = fields[1];
        int test = Integer.parseInt(isPopupData.get(0).getIsActive());
        int place = Integer.parseInt(location);
        place += placement;
        isPopupData.get(0).setPlacement(String.valueOf(place));

        System.out.println("booting up popup window");

        if (viewerName.contains("view") && (test == 0)) {
            String Location = viewerName;
            String[] ImageHolder = viewerName.split("w");

            isPopupData.get(0).setIsActive("1");
            System.out.println(isPopupData.get(0).getIsActive() + "clicked");

                // fills the popup data file so that it can be read when pop up is active.
                writeImageData data = new writeImageData();
                try {
                    data.writeImageDataToFile(filteredList,1);
                } catch (Exception ex) {
                    System.out.println("Could not write to file.");
                }

            writePopupData entered = new writePopupData();
            try {
                entered.writePopupDataToFile(isPopupData.get(0).getIsActive(),isPopupData.get(0).getPlacement(),isPopupData.get(0).getSearchActive());
            } catch (Exception ex) {
                System.out.println("Could not write to Popup file.");
            }
            //code that creates that launches the popup
            Parent root = FXMLLoader.load(getClass().getResource("PopupLayout.fxml"));
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("Image Popup");
            secondaryStage.setScene(new Scene(root, 1920, 1020));
            secondaryStage.show();
            {
                //code that makes sure to change the popupdata file from a 1 to a 0 when closed so that the orininal instance of uicontroller doesn't brick.
                secondaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent e) {

                        try {
                            readPopupData read = new readPopupData("./src/PopupData.csv");
                            popupData temp = new popupData("0:0");
                            read.add(temp);
                            writePopupData entered = new writePopupData();
                            try {
                                read.get(0).setIsActive("0");
                                entered.writePopupDataToFile(read.get(0).getIsActive(),read.get(0).getPlacement(),read.get(0).getSearchActive());
                            } catch (Exception ex) {
                                System.out.println("Could not write to Popup file.");
                            }
                        } catch (Exception ex) {
                            System.err.println(ex.getMessage());
                            System.exit(1);
                        }
                        //important reset of the ispopupdata so that the window can be reopened if closed
                        isPopupData.get(0).setIsActive("0");
                    }
                });
            }
        }
    }
}


