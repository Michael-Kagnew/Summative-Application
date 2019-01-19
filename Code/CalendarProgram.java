

package calendarprogram;

import calendarprogram.CalendarControls.*;
import static calendarprogram.CalendarControls.CalendarCommand;
import static calendarprogram.CalendarControls.refreshCalendar;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.UIManager.*;
import sun.swing.table.DefaultTableCellHeaderRenderer;

public class CalendarProgram {

    static JLabel LMonth, LYear;
    static JButton BtPrev, BtNext;
    static JTable CalendarTb;
    static JComboBox cmbYear;
    static JFrame FMain;
    static Container container;
    static DefaultTableModel mCalendarTb; //Table model
    static JScrollPane CalendarScroll;
    static JPanel CalendarPnl; //Where the calendar is
    static int rday, rMonth, ryear, curYear, curMonth;

    public static void main(String[] args) {

        //Sets the overall theme of the application
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {
        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        }

        //Setting up the frame
        FMain = new JFrame("Calendar App");
        FMain.setSize(330, 375); //Setting size of top level container, the frame
        container = FMain.getContentPane(); //change to container, is 
        container.setLayout(null); //Apply the null layout
        FMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LMonth = new JLabel("January");
        LYear = new JLabel("Change year:");
        cmbYear = new JComboBox();
        BtPrev = new JButton("Prev Month");
        BtNext = new JButton("Next Month");
        mCalendarTb = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        CalendarPnl = new JPanel(null); //The panel where buttons/components placed
        CalendarScroll = new JScrollPane(CalendarTb);//ALlows for the scrolling on app
        CalendarTb = new JTable(mCalendarTb); //Table using default table creation

        CalendarPnl.setBorder(BorderFactory.createTitledBorder("Calendar")); //Set border

        CalendarCommand(); //Add controls to pane

        //Get real month/year
        GregorianCalendar cal = new GregorianCalendar(); //Create calendar
        rday = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        rMonth = cal.get(GregorianCalendar.MONTH); //Get month
        ryear = cal.get(GregorianCalendar.YEAR); //Get year
        curMonth = rMonth; //Match month and year
        curYear = ryear;

        //Add headers
        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All days
        for (int i = 0; i < 7; i++) {
            mCalendarTb.addColumn(headers[i]);
        }
        CalendarTb.getParent().setBackground(CalendarTb.getBackground()); //Set background

        //No resize/reorder
        CalendarTb.getTableHeader().setResizingAllowed(false);
        CalendarTb.getTableHeader().setReorderingAllowed(false);

        //Single cell selection
        CalendarControls.CellSelection();

        //Set row/column count
        CalendarTb.setRowHeight(38);
        mCalendarTb.setColumnCount(7);
        mCalendarTb.setRowCount(6);

        //ALlows to see 100 years ago and future
        for (int i = ryear - 100; i <= ryear + 100; i++) {
            cmbYear.addItem(String.valueOf(i));
        }

        refreshCalendar(rMonth, ryear); //Refresh calendar

            //Register action listeners
    BtPrev.addActionListener(new btnPrev_Action());
    BtNext.addActionListener(new btnNext_Action());
    cmbYear.addActionListener(new cmbYear_Action());
    
    }

    static class tblCalendarRender extends DefaultTableCellHeaderRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);

            if (column == 0 || column == 6) { //Week-end
                setBackground(new Color(255, 220, 220));
            } else { //Week
                setBackground(new Color(255, 255, 255));
            }
            if (value != null) {
                if (Integer.parseInt(value.toString()) == rday && curMonth == rMonth && curYear == ryear) { //Today
                    setBackground(new Color(220, 220, 255));
                }
            }
            setBorder(null);
            setForeground(Color.black);
            return this;
        }
    }
    //When clicking back, will either go back just one month, or one month to last year
    static class btnPrev_Action implements ActionListener{
	public void actionPerformed (ActionEvent e){
		if (curMonth == 0){ //Back one year
			curMonth = 11;
			curYear -= 1;
		}
		else{ //Back one month
			curMonth -= 1;
		}
		refreshCalendar(curMonth, curYear);
	}
}
       //Opposite of above function 
    static class btnNext_Action implements ActionListener{
	public void actionPerformed (ActionEvent e){
		if (curMonth == 11){ //Foward one year
			curMonth = 0;
			curYear += 1;
		}
		else{ //Foward one month
			curMonth += 1;
		}
		refreshCalendar(curMonth, curYear);
	}
}
    static class cmbYear_Action implements ActionListener{
	public void actionPerformed (ActionEvent e){
		if (cmbYear.getSelectedItem() != null){
			String b = cmbYear.getSelectedItem().toString();
			curYear = Integer.parseInt(b); //Get the numeric value
			refreshCalendar(curMonth, curYear); //Refresh
		}
	}
}
}
