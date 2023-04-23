package DataStorage;

// this class keeps track of a different data file used to keep track of runtime settings.
public class popupData extends writePopupData
{
    // the first member keeps track of the popup window. this is important as the UiController works differently depending on if the popup window is open.
    private String IsActive;
    // second keeps track of the window placement at a given time.
    private String Placement;
    //third keeps track of if a search is on going.
    private String searchActive;

    public popupData(String line)
    {
        System.out.println("\n[entering the WritePopupData class]");

        try {
            String[] setting = line.split("-");
            String[] value1 = setting[1].split(":");
            String[] value2 = setting[2].split(":");
            String[] value3 = setting[2].split(":");
            IsActive = value1[1];
            Placement = value2[1];
            searchActive = value3[1];
        }catch(Exception ex)
        {

        }
        System.out.println("[exiting the WritePopupData class]\n");
    }
    //accessors and mutators for data members
    public String getIsActive() { return IsActive; }

    public String getPlacement() { return Placement; }

    public String getSearchActive() { return searchActive; }

    public void setIsActive(String activeName) {
        this.IsActive = activeName;
    }

    public void setPlacement(String placementName) {
        this.Placement = placementName;
    }

    public void setSearchActive(String searchName) {
        this.searchActive = searchName;
    }
}

