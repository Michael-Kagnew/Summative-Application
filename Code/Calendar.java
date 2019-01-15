package calendar;

import javax.swing.JFrame;
import javax.swing.*;
//import com.mindfusion.scheduling.Calendar;
import com.mindfusion.common.*;
import com.mindfusion.common.Rectangle;
import com.mindfusion.drawing.*;
import com.mindfusion.drawing.awt.AwtImage;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.awt.*;
import com.mindfusion.scheduling.model.*;
import java.awt.BorderLayout;




public class Calendar extends JFrame {
 //Only can be accessed by subclasses, so that not to be used by others
   protected Calendar()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setTitle("Calendar");
    }
    private static final long serialVersionUID = 1L;

       public static void main(String[] args) {
        
        
        Calendar calendar = new Calendar();
        
        //Intializes the whole app, letting it be seen
        SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                     new Calendar().setVisible(true);
               
         };
    });
    }
}
 

    

    
    

