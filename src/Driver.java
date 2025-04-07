import java.text.DecimalFormat;

import java.util.Calendar;
import java.util.Scanner;

import GraphPackage.LinkedQueue;

public class Driver {
    private static Scanner sc = new Scanner(System.in);
    private static DataBase dataBase = new DataBase();
    private static Profile myProfile = null;
    public static void main(String[] args) {
            dataBase.loadUsersFromFile("database/users.txt");
            mainMenu();
    }

    public static void mainMenu() {
        resetScanner();
        clearScreen();
        System.out.println("- ".repeat(12) + "Welcome to Ibook" + " -".repeat(12));
        System.out.println("");
        System.out.println("How can I help you?");
        System.out.println("1. Sign in");
        System.out.println("2. Sign up a new account");
        System.out.println("3. Exit!");



        int userInput = getInt(4, 1);
        switch (userInput) {
            case 1:
                signIn();
                break;
            case 2:
                signUp();
                break;
            case 3:
                {
                    System.out.println("Thank you for using Ibook!");
                    System.exit(0);
                }
        }
    }

    public static void signIn() {
        clearScreen();
        System.out.println("- ".repeat(14) + "Sign in" + " -".repeat(14));
        System.out.println("");
        System.out.println("What is your username & password?");
        System.out.println("");

        boolean checkUsername = false;
        boolean checkPassword = false;
        resetScanner();
        System.out.print("Username: ");
        String userName = sc.nextLine();
        int wrongInput = 4;
        resetScanner();
        myProfile = dataBase.logIn(userName);
        while (myProfile == null) {
            wrongInput--;
            if (wrongInput == 0) {
                mainMenu();
            }
            if (yesNoResponse("We cannot find your account. Do you want to re-try? (y-n): "))
            {
                System.out.println();
                System.out.println("We will return you back to main menu if you failed " + wrongInput + " times");
                System.out.println();
                System.out.print("Username: ");
                userName = sc.nextLine();
                System.out.println();
                myProfile = dataBase.logIn(userName);
            }
            else {
                mainMenu();
            }
        }

        if (myProfile != null) {
            checkUsername = true;
        }
        System.out.print("Password: ");
        String userPass = sc.nextLine();
        wrongInput = 0;
        while (!userPass.equals(myProfile.getPassword())) {
            System.out.println("Invalid password! Please try again: ");
            userPass = sc.nextLine();
            wrongInput++;
            if (userPass.equals("n") && wrongInput > 2) {
                mainMenu();
            }
            else if (wrongInput > 2) {
                System.out.println("");
                System.out.println("Maybe you are forgot your password.");
                System.out.println("Input n to exit back to main menu!");
            }
        }

        if (userPass.equals(myProfile.getPassword())) {
            checkPassword = true;
        }


        if (checkUsername && checkPassword) {
            homeScreen();
        }
    }

    public static void homeScreen() {
        clearScreen();
        System.out.println("- ".repeat(4) + "Welcome back! " + myProfile.getName() + " How can I help you?" + " -".repeat(4));
        System.out.println("");
        System.out.println("1. Manage your account");
        System.out.println("2. Search user");
        System.out.println("3. My friends");
        System.out.println("4. Discovery new friends");
        System.out.println("5. Check friends of friends ");
        System.out.println("6. Manage news feed");
        System.out.println("7. Log out ");

        int userInput = getInt(7, 1);

        switch (userInput) {
            case 1: manageAccount();    break;
            case 2: searchUser();   break;
            case 3: manageFriendList(); break;
            case 4: showNewFriends(); break;
            case 5: checkFriendsLists(); break;
            case 6: manageNewFeed(); break;
            case 7: 
                if (yesNoResponse("Are you sure you want to log out?\n" + " Please enter 'y' or 'n': "))
                    exitScreen();
                    break;
        }

    }

    public static void manageAccount() {
        resetScanner();
        clearScreen();

        System.out.println("- ".repeat(14) + "Account Manager" + " -".repeat(14));
        System.out.println("");
        System.out.println("1. Update profile");
        System.out.println("2. Change password");
        System.out.println("3. Delete account");
        System.out.println("4. Go back");
        int response = getInt(4, 1);
        switch (response) {
            case 1 ->  {
                clearScreen();

                System.out.println("- ".repeat(14) + "Profile update" + " -".repeat(14));
                Profile oldProfile = myProfile;
                System.out.println("1. Name" + "\n2. Date of brith" + "\n3. Set status" + "\n4. Back");
                int option = getInt(3, 1);
                if (option == 1) {
                    clearScreen();

                    System.out.println("- ".repeat(14) + "Update name" + " -".repeat(14));
                    resetScanner();
                    System.out.print("Please enter your new first name: ");
                    String newFirstName = sc.nextLine();
                    System.out.print("Please enter your new last name: ");
                    String newLastName = sc.nextLine();
                    myProfile.setFirstName(newFirstName);
                    myProfile.setLastName(newLastName);
                    System.out.println("Success! Your name is changed to: " + myProfile.getName());

                } 
                else if (option == 2) {
                    clearScreen();

                    System.out.println("- ".repeat(14) + "Update Date of Birth" + " -".repeat(14));
                    resetScanner();
                    final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
                    DecimalFormat myFormat = new DecimalFormat("##");
                    System.out.print("Please enter your day, month, year of birth respectively: \n");
                    System.out.print("Day: ");
                    int day = getInt(31, 1);
                    System.out.print("Month: ");
                    int month = getInt(12, 1);
                    System.out.print("Year: ");
                    int year = getInt(CURRENT_YEAR, CURRENT_YEAR-100);
                    String dateOfBirth = myFormat.format(month) + "/" + myFormat.format(day) + "/" + year;
                    myProfile.setDateOfBirth(dateOfBirth);
                    System.out.println("Success! Your date of birth is changed to: " + myProfile.getDateOfBirth());
                } 
                else if (option == 3) {
                    clearScreen();

                    System.out.println("- ".repeat(14) + "Update status" + " -".repeat(14));
                    resetScanner();
                    System.out.println("Let set your status " + myProfile.getFirstName() + ": ");
                    String status = sc.nextLine();
                    myProfile.setStatus(status);
                    System.out.println("Thanks you for sharing! Your status is set");
                }

                else if (option == 4) {
                    manageAccount();
                }

                System.out.println("You will be back to home screen");
                pressEnterToContinue();
                homeScreen();
                dataBase.updateProfile(oldProfile, myProfile);
            }
            case 2 ->  {
                clearScreen();
                
                System.out.println("- ".repeat(14) + "Update new password" + " -".repeat(14));
                resetScanner();
                System.out.print("Enter your current password: ");
                String password = sc.nextLine();
                if (password.equals(myProfile.getPassword())) {
                    System.out.print("Enter your new password: ");
                    String newPassword = sc.nextLine();
                    myProfile.setPassword(newPassword);
                    System.out.println("Success! Your password is set to new one");
                }
                else {
                    System.out.println("Incorrect password! You will be brought back to the home screen");
                }
                pressEnterToContinue();
                homeScreen();
            }
            case 3 -> {
                clearScreen();
                System.out.println("- ".repeat(14) + "Delete account" + " -".repeat(14));
                System.out.println("Are you sure you want to permanently delete your account? ");
                if (yesNoResponse("Please enter 'y' or 'n': ")) {
                    resetScanner();
                    System.out.print("Please enter your password to verify: ");
                    String passw = sc.nextLine();
                    if (passw.equals(myProfile.getPassword())) {
                        dataBase.remove(myProfile.getAccountName());
                        System.out.println("Your account was successfully removed!\n" +
                                            "You will be back to main menu");
                        pressEnterToContinue();
                        mainMenu();
                    } else
                        System.out.println("Incorrect password! you will be back to home menu");
                        homeScreen();
                }
            }
            case 4 -> {homeScreen();}
        }
    }

    public static void searchUser() {
        sc.nextLine();
        clearScreen();
        System.out.println("- ".repeat(14) + "Searching" + " -".repeat(14));
        System.out.println();
        System.out.print("Please enter the full name of the person you are looking: ");
        String lookFor = sc.nextLine();
        Profile foundProfile = dataBase.getProfile(lookFor);
        if (foundProfile != null) {
            foundProfile.printProfile();

            if (!dataBase.areFriends(myProfile, foundProfile) && !foundProfile.equals(myProfile)) {
                System.out.println();
                showMutual(myProfile, foundProfile);
            }
            makeFriend(foundProfile);
        }   else {
            System.out.println(lookFor + " is not existed in our network.");
        }
        System.out.println("You will be back to home screen");
        pressEnterToContinue();
        homeScreen();
    }

    public static void manageFriendList() {
        sc.nextLine();
        clearScreen();
        System.out.println("- ".repeat(14) + "Friends Manager" + " -".repeat(14));
        System.out.println();
        System.out.println("1. See your friend list" + "\n2. Search a friend in your list");
        System.out.print("Please enter 1 or 2: ");
        int response = getInt(2, 1);

        clearScreen();
        if (response == 1) {
            System.out.println("- ".repeat(14) + "Friends List" + " -".repeat(14));
            System.out.println();
            System.out.println("Your friends are: ");
            if (showFriendList(myProfile))
                response = 2;
        }
        if (response == 2) {
            Profile foundFriend = findPersonInFriendList(myProfile);
            if (foundFriend != null) {
                clearScreen();
                foundFriend.printProfile();
                System.out.println("Do you want to remove " + foundFriend.getFirstName() +
                                 " fromt the friends list? ");
                if (yesNoResponse("Please enter 'y' or 'n': ")) {
                    dataBase.endFriendship(myProfile, foundFriend);
                    System.out.println("You and " + foundFriend.getFirstName() + " are no longer friend");
                }
            }
        }
        System.out.println("You will be back to home screen");
        pressEnterToContinue();
        homeScreen();
    }

    public static void showNewFriends() {
        LinkedQueue<Profile> queue = dataBase.getFriendsOfFriends(myProfile);

        if (!queue.isEmpty()) {
            clearScreen();
            System.out.println("You may know: ");
            System.out.println();
            while(!queue.isEmpty()) {
                Profile personMayKnow = queue.dequeue();
                System.out.print("☞ " + personMayKnow.getName() + "\n");
                showMutual(personMayKnow, myProfile);
            }
        } else {
            clearScreen();
            System.out.println("Sorry, we cannot find any friend recommendation for you. \n" +
                                "(Suggestion: You can add more friend so that we can give out recommendation)");

        }
        System.out.println("You will be back to home screen");
        pressEnterToContinue();
        homeScreen();
    }

    public static void checkFriendsLists() {
        sc.nextLine();
        clearScreen();
        System.out.println("- ".repeat(14) + "Friends of your friend" + " -".repeat(14));
        System.out.print("\nPlease enter the full name of a friend: ");
        String lookFor = sc.nextLine();
        System.out.println();
        // check if the friend list has the input profile
        Profile foundFriend = dataBase.findFriend(myProfile, lookFor);

        if (foundFriend != null) {
            System.out.println(foundFriend.getFirstName() + "'s friends: ");
            if (showFriendList(foundFriend)) {
                Profile found = findPersonInFriendList(foundFriend);
                if (found != null) {
                    clearScreen();
                    found.printProfile();
                    makeFriend(found);
                }
            }
        }
        System.out.println("You will be back to home screen");
        pressEnterToContinue();
        homeScreen();
    }

    public static void manageNewFeed() {
        sc.nextLine();
        clearScreen();
        System.out.println("- ".repeat(14) + "News Feed manager" + " -".repeat(14));
        System.out.println("1. Check News Feed\n" + "2. Post something");
        System.out.print("Please select an option: ");
        int option = getInt(2, 1);
        clearScreen();

        switch (option) {
            case 1:
                System.out.println("- ".repeat(14) + "News Feed" + " -".repeat(14));
                myProfile.showNewsFeed();
                System.out.println("\n");
                break;
        
            case 2:
                System.out.println("- ".repeat(14) + "Post New Feed" + " -".repeat(14));
                System.out.println("Please enter the content of the post: ");
                String content = getMultipleLinesInput();
                Post post = new Post(content, myProfile.getName());
                dataBase.addPost(myProfile, post);

                System.out.println("Success! Your post is now on your page");
                System.out.println("You will be back to home page");
                pressEnterToContinue();
                homeScreen();
                break;
        }
        System.out.println("You will be back to home screen");
        pressEnterToContinue();
        homeScreen();
    }
    public static void makeFriend(Profile profile) {
        sc.nextLine();
        if (!dataBase.areFriends(profile, myProfile) && !profile.equals(myProfile)) {
            System.out.println("\nDo you want to make friend with " +
                    profile.getName() + "?");
            if (yesNoResponse("Please enter 'y' or 'n': ")) {
                System.out.println("\n");
                dataBase.createFriendship(profile, myProfile);
            }
        } else {
            if (dataBase.areFriends(myProfile, profile))
                System.out.println("You and " + profile.getName() + " are already friends\n\n");
        }
    }

    public static void signUp() {
        resetScanner();
        clearScreen();
        System.out.println("- ".repeat(14) + "Sign up" + " -".repeat(14));
        System.out.println("");
        System.out.println("Please answer all the informations for your new account");
        System.out.println("");
        System.out.println("- ".repeat(32));
        signUpForm();

    }

    public static void signUpForm() {

        System.out.print("First name: ");
        String firstName = sc.nextLine();

        System.out.print("Last name: ");
        String lastName = sc.nextLine();

        String fullName = firstName + " "+ lastName;
        System.out.println("Please enter your day, month, year of birth respectively");
        final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
        final int MIN_AGE = 13;
        DecimalFormat myFormat = new DecimalFormat("##");
        System.out.print("Day(1-31): ");
        int day = getInt(31, 1);
        System.out.print("Month(1-12): ");
        int month = getInt(12, 1);
        System.out.print("Year: ");
        int year = getInt(CURRENT_YEAR, CURRENT_YEAR-100);

        String dateOfBirth = myFormat.format(month) + "/" + myFormat.format(day) + "/" + year;

        if (CURRENT_YEAR - year < MIN_AGE) {
            System.out.println("\nSorry, you must be at least " + MIN_AGE + " to join social network ");
            pressEnterToContinue();
            mainMenu();
        }
        sc.nextLine();

        System.out.print("Username: ");
        String userName = sc.nextLine();
        while (dataBase.logIn(userName) != null) {
            System.out.println("This Username is already exists! Please try a different one");
            System.out.print("Username: ");
            userName = sc.nextLine();
        }
        System.out.print("Password: ");
        String passWord = sc.nextLine();

        dataBase.add(userName, fullName, new Profile(firstName, lastName, userName, dateOfBirth, null , passWord));
        System.out.println("");
        System.out.println("Congratulations! Your account is created. Let's try logging in to your account!");
        pressEnterToContinue();
        signIn();
    }


    public static String getMultipleLinesInput() {
        String message = "You can press enter once to skip a line, " +
                         "twice to finish inputing.\n";
        System.out.println(message);
        String content = "";
        String line = "";
        sc.nextLine();
        while (true) {
            line = sc.nextLine().trim();
            if (line.equals(""))
                break;
            content += line + "\n";
        }
        return content;
    }

    public static void showMutual(Profile left, Profile right) {
        System.out.print("\tThis person has " + dataBase.findMutualFriends(left, right) +
                " mutual friends with you");

        LinkedQueue<Profile> mutualFriends = dataBase.getMutualFriends(left, right);
        if (!mutualFriends.isEmpty()) {
            System.out.println(", including:\n\t");
            while (!mutualFriends.isEmpty()) {
                System.out.print(" " + mutualFriends.dequeue().getName() + "  ");
            }
        }
        System.out.println("\n");
    }

    public static boolean showFriendList(Profile profile) {
        System.out.print("\n");
        if (printFriendListOf(profile)) {
            return yesNoResponse("Do you want to search for a specific person in this list? (y/n): ");
        }
        return false;
    }

    public static boolean printFriendListOf(Profile profile) {
        LinkedQueue<Profile> queue = dataBase.getFriendListOf(profile);
        boolean isNotEmpty;

        if (!queue.isEmpty()) {
            isNotEmpty = true;
            while (!queue.isEmpty()) {
                System.out.println("\t ☞" + queue.dequeue().getName());
            }
            System.out.println();
        } else {
            isNotEmpty = false;
            clearScreen();
            System.out.println(profile.getName() + " has no friends yet!");
            System.out.println("You will be bring back to home page");
            pressEnterToContinue();
            homeScreen();
        }
        return isNotEmpty;
    }

    public static Profile findPersonInFriendList(Profile profile) {
        sc.nextLine();
        Profile result;
        System.out.print("\nPlease enter the name of the person you are looking for: ");
        String lookFor = sc.nextLine();
        result = dataBase.findFriend(profile, lookFor);

        return result;
    }

    public static int getInt(int max , int min) {
        int result = 0;
        boolean pass;
        do {
            pass = true;
            if (sc.hasNextInt()) {
                result = sc.nextInt();
            }
            else {
                pass = false;
                sc.next();
                System.out.println("Please re-enter an appropriate integer: ");
            }
            if (pass)
                if (result > max || result < min) {
                    System.out.println("Input out of range, please re-enter: ");
                    pass = false;
                }
        } while (!pass);
        return result;
    }

    public static boolean yesNoResponse(String message) {
        resetScanner();
        System.out.print(message);
        
        while (true) {
            String response = sc.nextLine().trim().toLowerCase();
            if (response.equals("y")) {
                return true;
            } else if (response.equals("n")) {
                return false;
            }
            System.out.print("Please enter only 'y' or 'n': ");
        }
    }

    public static void clearInputBuffer() {
        if (sc.hasNextLine()) {
            sc.nextLine(); // Consume any leftover input
        }
    }
    public static void exitScreen() {
        System.out.println("You are exiting back to main menu");
        pressEnterToContinue();
        mainMenu();
    }

    // Helper method to reset Scanner to avoid buffer issues
    public static void resetScanner() {
        sc = new Scanner(System.in);
    }

    public static void pressEnterToContinue() {
        System.out.print("Press Enter to continue...");
        try{System.in.read();}
        catch(Exception ignored){}
    }

    public static void clearScreen() {
        String os = System.getProperty("os.name");
        try {
            if (os.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();
                
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}