
import java.util.Random;
import java.util.Scanner;

import java.sql.*;


public class Main {

    private static Random random = new Random();

    private static String characters =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";



    public static String shorten(String longurl)
        {
            String shorturl = "";
            boolean isUnique = false;

            while (!isUnique) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 6; i++) {
                    int index = random.nextInt(characters.length());
                    sb.append(characters.charAt(index));
                }
                shorturl = sb.toString();

                if (!database.exists(shorturl)) {
                    isUnique = true;
                }
            }

            database.save(shorturl, longurl);
            return shorturl;
        }
    private static void create(Scanner input){
        System.out.println("Enter a link to shorten: " );
        String longurl = input.nextLine();
        if (!isValidUrl){
            System.out.println("Invalid URL. Must start with http:// or https://");
            return;
        }
        String shorturl = shorten(longurl);
        System.out.println("Shortened link: " + shorturl);
        System.out.println("Long URL: " + longurl);
    }
    public static boolean isValidUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }
        return url.startsWith("http://") || url.startsWith("https://");
    }
    public static void printMenu(){
        System.out.println("\n--- URL Shortener ---");
        System.out.println("1. Create - shorten a new link");
        System.out.println("2. exists   - look up a long URL by its short code");
        System.out.println("3. Update - change the long URL for an existing short code");
        System.out.println("4. Delete - remove a short code");
        System.out.println("5. Quit");
        System.out.print("Choose an option: ");
    }




    public static void main(String[] args) {

        database.initialize();

        Scanner input = new Scanner(System.in);
        while (true) {
        printMenu();







        }
        System.out.println("Thanks for shoertening");
        input.close();
    }


}
