
// import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class scrapePics{

    // Location to store images.
    public static double COMPLETION = 0;
    private static double increment;

    public static void Process(String strURL, String location, GUI_Code temp){
        if (strURL.isEmpty() || location.isEmpty()){
            return;
        }
        try{

            // Replace it with your url, from where you want to download images.
            // String strURL = "https://www.google.com/";

            // Scanner input = new Scanner(System.in);
            // System.out.println("Enter the websites URL to download images: ");
            // String strURL = input.nextLine();
            // input.close();

            // Connect to the website and get the document.
            Document document = Jsoup.connect(strURL).userAgent("Mozilla/5.0").timeout(10*1000).get();

            // Select all img tags.
            Elements imageElements = document.select("img");
            increment = 100.0 / imageElements.size();
            System.out.println(imageElements.size());
            System.out.println(increment);
            // Iterate over each image.
            COMPLETION = 0;
            // temp.l3.setText("Starting Download.");
            for(Element imageElement:imageElements){

                temp.l3.setText("Downloading in progress...");

                // Make sure to get the absolute URL using abs: prefix.
                String strImageURL = imageElement.attr("abs:src");
                // String strImageURL = imageElement.attr("abs:data-src"); For some sites...

                // Download image one by one.
                downloadImage(strImageURL, location, temp);
                COMPLETION += increment;

                if(COMPLETION >= 99.99){
                    temp.l3.setText("Downloading Complete.");
                }

            }
            COMPLETION += increment;
        }catch(IOException e){
            // e.printStackTrace();
            // GUI_Code temp = new GUI_Code();
            temp.l3.setText("Operation could not be performed.");
            
        }


    }

    private static void downloadImage(String strImageURL, String location, GUI_Code temp){

        // Get file name from image path.
        String strImageName = strImageURL.substring(strImageURL.lastIndexOf("/") + 1);

        // Sanitize file name to follow windows naming rules.
        int invalidIndex = strImageName.indexOf("?");

        String correctedFileName;
        if(invalidIndex != -1){
            correctedFileName = strImageName.substring(0, invalidIndex);
        }else{
            correctedFileName = strImageName;
        }

        // Get file extension from image path.
        // String strImageExtension = strImageURL.substring(strImageURL.lastIndexOf(".") + 1);

        System.out.println("\nSaving: " + strImageName + ", from " + strImageURL);

        // GUI_Code temp2 = new GUI_Code();
        // temp.l3.setText("Downloading in progress...");

        try{

            // Open the stream from URL.
            URL urlImage = new URL(strImageURL);
            InputStream in = urlImage.openStream();

            byte[] buffer = new byte[4096];
            int n = -1;

            OutputStream os = new FileOutputStream(location + "/" + correctedFileName);


            // Write bytes to the output stream.
            while( (n = in.read(buffer)) != -1 ){
                os.write(buffer, 0, n);
            }

            // Close the stream.
            os.close();

            System.out.println(" Image Saved.");

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}