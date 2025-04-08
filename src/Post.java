/**
 * Represents a post on the social media
 * Contains the information about the content of the post,
 * the author, and the time the post is published.
 */

 import java.util.Date;
 import java.text.SimpleDateFormat;
 
 public class Post {
     private String content;      // the content of the post
     private String author;       // the author of the post
     private Date timePosted;     // the time the post was up
     private boolean edited;      // whether the pose was editted
 
 
     public Post(String content, String author) {
         this.content = content;
         this.author = author; 
         this.timePosted = new Date();
         this.edited = false;
     }
 
 
     /**
      * print out the post on the screen
      */
     public void printPost() {
         System.out.println("\n");
         // print out the author 
         System.out.print(author);
         // print out the date
         System.out.print("\t\t" + (edited ? "Edited" : "Posted") + " on ");
         SimpleDateFormat fomatter = new SimpleDateFormat("MM/dd/yyy HH:mm:ss");
         System.out.println(fomatter.format(timePosted));
         // print the content of the post
         System.out.println("\n" + content);
     }
 
 
     /**
      * edit the content of the post
      * 
      * @param newContent the new content after the edit
      */
     public void edit(String newContent) {
         content = newContent;
         timePosted = new Date();
         edited = true;
     }
 
 
     /**
      * get the name of the author
      * 
      * @return the author of the post
      */
     public String getAuthor() {
         return author;
     }
 
 
     /**
      * get the date and time that the user posted the post
      * 
      * @return the date and time that the post was up
      */
     public Date getTimePosted() {
         return timePosted;
     }
 }