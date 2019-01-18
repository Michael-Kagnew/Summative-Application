

package calendarprogram;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.UIManager.*;


public class CalendarProgram {

    static JLabel LMonth, LYear;
    static JButton BtPrev, BtNext;
    static JTable CalendarTb;
    static JComboBox cmbYear;
    static JFrame Main;
    static Container container;
    static DefaultTableModel mtblCalendar; //Table model
    static JScrollPane CalendarScroll;
    static JPanel CalendarPnl; //Where the calendar is
    static int rday, rMonth, ryear, curYear, curMonth;
    
    public static void main(String[] args) {
try {
    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
} catch (Exception e) {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
}
    }
}
