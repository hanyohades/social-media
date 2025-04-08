# 👾 Ibook - A Terminal-Based Social Network 👾
## 👾 Overview 👾
  Ibook is a simple terminal-based social network application that simulates core social media functionalities. 
  Built in Java, this project demonstrates the fundamental concepts of data structures and object-oriented programming 
  while creating an interactive social media experience directly in your terminal.

## 🤖 Features 🤖
- User Authentication: Sign up and sign in functionality with password protection
- Profile Management: Create and update personal profiles with information like name, date of birth, and status
- Friend System: Add friends, view friend lists, and manage friend connections
- Friend Discovery: Discover potential new connections through "friends of friends" recommendations
- News Feed: View posts from yourself and friends in a chronological feed
- Post Creation: Share your thoughts and updates with your network
- Mutual Friend Detection: See mutual connections when viewing other profiles

## 🧐 Technical Implementation 🧐
  ### Essential data structures:

- Graph: Represents the social network where vertices are users and edges are friendships
- Hash Dictionary: Enables efficient user lookups by username and full name
- Queues and Stacks: Used for traversal operations like finding friends of friends

## 🤔 How to Run 🤔

1. Ensure you have Java installed on your system
2. Clone this repository
```cmd
$ git clone https://github.com/hanyohades/social-media
```

3. Compile and run the Driver file:
```cmd
$ javac Driver.java
$ java Driver.java
```
4. Create an account or use an existing account in the database folder.

## Project Structure 
- Driver.java - Main class with UI and controller logic
- Profile.java- User profile information and management
- Post.java - Social media post implementation
- DataBase.java - Data management and graph operations
  

## When you run the application, you'll be presented with a main menu:
    ------------------------ Welcome to Ibook ------------------------
    How can I help you?
    1. Sign in
    2. Sign up a new account
    3. Exit!


From here, you can navigate through the various features of the social network using the numbered menu options.

## ✨ Visuals ✨
### - Main Menu
![Image](https://github.com/user-attachments/assets/771d2499-01ba-4515-aa3d-cadbc2566184)

### - Home Page
![Image](https://github.com/user-attachments/assets/d3f0d944-f257-42f8-a883-0463bccfc0b1)

### - Friends List
![Image](https://github.com/user-attachments/assets/dbf5bd4f-a55d-40ff-87e0-97f583f1cf7d)

### - Searching
![Image](https://github.com/user-attachments/assets/3e493a6e-96f5-4409-81c6-23ae7a98b80e)

### - Posts
![Image](https://github.com/user-attachments/assets/5b1882a4-f5c5-47dd-93ff-1477ae15b4fd)
