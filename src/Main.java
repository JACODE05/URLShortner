
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

    public static boolean isValidUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }
        return url.startsWith("http://") || url.startsWith("https://");
    }
    public static void printMenu(){
        System.out.println("\n--- URL Shortener ---");
        System.out.println("1. Create - shorten a new link");
        System.out.println("2. Read   - look up a long URL by its short code");
        System.out.println("3. List   - show every saved link");
        System.out.println("4. Update - change the long URL for an existing short code");
        System.out.println("5. Delete - remove a short code");
        System.out.println("6. Quit");
        System.out.print("Choose an option: ");
    }




    public static void main(String[] args) {

        database.initialize();

        Scanner input = new Scanner(System.in);
        while (true) {
        printMenu();
            System.out.println("Please enter the link to shroten: ");
            String longurl = input.nextLine().trim();
            if (longurl.equalsIgnoreCase("quit")) {
                break;
            }
            if (!isValidUrl(longurl)) {
                System.out.println("Invalid URL. Must start with http:// or https://");

                continue;
            }


            String shorturl = shorten(longurl);


            System.out.println("Shorten link: " + shorturl);
            System.out.println("longurl: " + longurl);

            System.out.println("Looking it back up: " + database.findLongUrl(shorturl));



        }
        System.out.println("Thanks for shoertening");
        input.close();
    }


}
