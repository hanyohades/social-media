import java.util.Calendar;
import ListPackage.ArrayList;



public class Profile{
    private String firstName;
    private String lastName;
    private String name;
    private String birthday;
    private int age;
    private String status;
    private String password;
    private String userName;
    private ArrayList<Post> newsFeed;
    private final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    
    public Profile(String firstName, String lastName, String accName, 
                   String birthday, String status ,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        if (status == null) 
            status = "Hello, I am " + firstName;
        this.password = password;
        this.userName = accName;
        this.newsFeed = new ArrayList<Post>();
        this.status = status;
        setName();
        calculateAge();
    }


    public String getName() { return name; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; setName(); }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; setName(); }

    public String getAccountName() { return userName; }

    public String getDateOfBirth() { return birthday; }

    public void setDateOfBirth(String dateOfBirth) { birthday = dateOfBirth; }

    public int getAge() { return age; }

    public String getPassword() { return password; }

    public String getUserName() { return userName; }

    public void setPassword(String password) { this.password = password; }

    public void setStatus(String status) { this.status = status; }
    //print out the publicable information of the profile
    public void printProfile() {
        String nameDisplayed = firstName + " " + lastName.charAt(0);
        System.out.println("Here is the informations about:" + nameDisplayed);
        System.out.print("Age: " + age + " years old\n");
        System.out.print("Birthday: " + birthday + "\n");
        System.out.println("Status: \"" + status + "\"");
    }


    // Combine first name and last name to get fullname
    private void setName() { name = firstName + " " + lastName; }

    public void addPostToNewsFeed(Post post) {
        newsFeed.add(post);
    }

    public void showNewsFeed() {
        int count = 0;
        for (int i = newsFeed.getLength(); i > 0; i--) {
            Post post = newsFeed.getEntry(i);
            post.printPost();

            if (count == 2) {
                if (i > 1) {
                    if (Driver.yesNoResponse("\n\nDo you want to see older posts? (y/n): ")) {
                        Driver.clearScreen();
                        count = 0;
                    }
                    else
                        break;
                }
            }
            count++;
        }
    }

    // calculate the age of the person according to the birthday
    private void calculateAge() {
        String[] str = birthday.split("/");
        age = CURRENT_YEAR - Integer.parseInt(str[2]);
    }
}