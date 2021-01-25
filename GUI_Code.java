import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
public class GUI_Code{
    JLabel l3;
    public static void main(String[] args) {
        new GUI_Code();
    }
    public GUI_Code(){
        JFrame frame = new JFrame("Image Downloader");
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.darkGray);   //Color
        

        JTextField tf1 = new JTextField();
        tf1.setBounds(50, 50, 150, 20);
        frame.add(tf1, BorderLayout.NORTH);

        JLabel l1 = new JLabel("Enter file location(Location must be created before).");
        l1.setBounds(50, 25, 320, 20);
        l1.setForeground(Color.orange);  //Color
        frame.add(l1, BorderLayout.NORTH);

        final JTextField tf2 = new JTextField();
        tf2.setBounds(50, 100, 150, 20);
        frame.add(tf2, BorderLayout.NORTH);

        JLabel l2 = new JLabel("Enter website URL: ");
        l2.setBounds(50, 75, 150, 20);
        l2.setForeground(Color.orange);
        frame.add(l2, BorderLayout.NORTH);

        JButton b = new JButton("Download");
        b.setBounds(130, 150, 100, 40);
        b.setBackground(Color.BLUE); //Color
        b.setForeground(Color.YELLOW);    //Color
        frame.add(b, BorderLayout.NORTH);

        JProgressBar bar = new JProgressBar();
//        bar.setVisible(false);
        bar.setBounds(100,300,300, 50);
        ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
        s.scheduleAtFixedRate(() -> bar.setValue((int)Math.ceil(scrapePics.COMPLETION)),1,1, TimeUnit.NANOSECONDS);

        frame.add(bar, BorderLayout.SOUTH);

        l3 = new JLabel("");
        l3.setBounds(100, 350, 150, 20);
        l3.setForeground(Color.green);
        frame.add(l3, BorderLayout.NORTH);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b.addActionListener(e -> {

            l3.setText("Starting download.");

            ExecutorService service = Executors.newSingleThreadExecutor();
            service.execute(() -> {scrapePics.Process(tf2.getText().trim(), tf1.getText().trim(), this);});
            // scrapePics.Process(tf2.getText().trim(), tf1.getText().trim());
            


        });


    }

}