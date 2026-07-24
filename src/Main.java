import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Random random = new Random();

    private static String characters =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String shorten(String longurl) {

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

    private static void create(Scanner input) {

        System.out.print("Enter a link to shorten: ");
        String longurl = input.nextLine();

        if (!isValidUrl(longurl)) {
            System.out.println("Invalid URL. Must start with http:// or https://");
            return;
        }

        String shorturl = shorten(longurl);

        System.out.println("Shortened link: " + shorturl);
        System.out.println("Long URL: " + longurl);
    }

    private static void lookup(Scanner input) {

        System.out.print("Enter the short code: ");
        String shortCode = input.nextLine();

        String longUrl = database.findLongUrl(shortCode);

        if (longUrl == null) {
            System.out.println("Short code not found.");
        } else {
            System.out.println("Long URL: " + longUrl);
        }
    }

    private static void update(Scanner input) {

        System.out.print("Enter the short code: ");
        String shortCode = input.nextLine();

        System.out.print("Enter the new long URL: ");
        String newLongUrl = input.nextLine();

        if (!isValidUrl(newLongUrl)) {
            System.out.println("Invalid URL. Must start with http:// or https://");
            return;
        }

        if (database.update(shortCode, newLongUrl)) {
            System.out.println("URL updated successfully.");
        } else {
            System.out.println("Short code not found.");
        }
    }

    private static void delete(Scanner input) {

        System.out.print("Enter the short code: ");
        String shortCode = input.nextLine();

        if (database.delete(shortCode)) {
            System.out.println("Short code deleted.");
        } else {
            System.out.println("Short code not found.");
        }
    }

    private static void quit(Scanner input) {

        System.out.println("Thanks for shortening!");
        input.close();
        System.exit(0);
    }

    public static boolean isValidUrl(String url) {

        if (url == null || url.trim().isEmpty()) {
            return false;
        }

        return url.startsWith("http://")
                || url.startsWith("https://");
    }

    public static void printMenu() {

        System.out.println("\n--- URL Shortener ---");
        System.out.println("1. Create - shorten a new link");
        System.out.println("2. Lookup - look up a long URL by its short code");
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

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    create(input);
                    break;

                case 2:
                    lookup(input);
                    break;

                case 3:
                    update(input);
                    break;

                case 4:
                    delete(input);
                    break;

                case 5:
                    quit(input);
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
