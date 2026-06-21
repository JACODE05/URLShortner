import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Map;


public class Main {
    private static Map<String, String> urlmap = new HashMap<>();
    private static Random random = new Random();

    private static String characters =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static String shorturl = "";


    public static String shorten(String longurl)
        {
            for (int i = 0; i < 6; i++) {
                int index = random.nextInt(characters.length());
                shorturl += characters.charAt(index);
            }

            urlmap.put(shorturl, longurl);

            return shorturl;
        }







    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter the link to shroten: ");
        String longurl = input.nextLine();

        String shorturl = shorten(longurl);


        System.out.println("Shorten link: "+shorturl);
        System.out.println("longurl: "+longurl);


        input.close();


    }

}
