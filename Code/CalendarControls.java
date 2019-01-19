/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarprogram;

import static calendarprogram.CalendarProgram.CalendarPnl;
import java.util.GregorianCalendar;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Shadow
 */
public class CalendarControls extends CalendarProgram {

    public static void CalendarCommand() {

        //Adding the controls for Calendar    
        container.add(CalendarPnl);
        CalendarPnl.add(LMonth);
        CalendarPnl.add(LYear);
        CalendarPnl.add(cmbYear);
        CalendarPnl.add(BtPrev);
        CalendarPnl.add(BtNext);
        CalendarPnl.add(CalendarScroll);

        //Setting the bounds for the controls
        CalendarPnl.setBounds(0, 0, 320, 335);
        LMonth.setBounds(160 - LMonth.getPreferredSize().width / 2, 25, 100, 25); //When month name changes, size of label changes
        LYear.setBounds(10, 305, 80, 20);
        cmbYear.setBounds(230, 305, 80, 20);
        BtPrev.setBounds(10, 25, 50, 25);
        BtNext.setBounds(260, 25, 50, 25);
        CalendarScroll.setBounds(10, 50, 300, 250);
        
    }

    public static void CellSelection() {
        CalendarTb.setColumnSelectionAllowed(true);
        CalendarTb.setRowSelectionAllowed(true);
        CalendarTb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
    }

    public static void refreshCalendar(int month, int year) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int nod, som; //Number Of Days, Start Of Month

        BtPrev.setEnabled(true); //Enable buttons first
        BtNext.setEnabled(true);
        if (month == 0 && year <= ryear - 10) {
            BtPrev.setEnabled(false);
        } //Cant go further past
        if (month == 11 && year >= ryear + 100) {
            BtNext.setEnabled(false);
        } //Can't go further future
        LMonth.setText(months[month]); //Refresh the month label (at the top)
        LMonth.setBounds(160 - LMonth.getPreferredSize().width / 2, 25, 180, 25); //Re-align label with calendar
        cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box

        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);

        //Clear table
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                mCalendarTb.setValueAt(null, i, j);
            }
        }
        //Draw calendar
        for (int i = 1; i <= nod; i++) {
            int row = new Integer((i + som - 2) / 7);
            int column = (i + som - 2) % 7;
            mCalendarTb.setValueAt(i, row, column);
        }
   
    CalendarTb.setDefaultRenderer(CalendarTb.getColumnClass(0), new tblCalendarRender()); //Apply renderer
    }

}
