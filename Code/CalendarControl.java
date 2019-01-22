/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar;

import static calendar.Calendar.btnPrev;
import static calendar.Calendar.pane;
import static calendar.Calendar.CalendarTb;
import java.util.GregorianCalendar;
import javax.swing.ListSelectionModel;

public class CalendarControl extends Calendar {

    public static void CalendarCont() {
        //Add controls to pane
        pane.add(pnlCalendar);
        pnlCalendar.add(lblMonth);
        pnlCalendar.add(lblYear);
        pnlCalendar.add(cmbYear);
        pnlCalendar.add(btnPrev);
        pnlCalendar.add(btnNext);
        pnlCalendar.add(sCalendarTb);
        pnlCalendar.add(but);
        pnlCalendar.add(area);
        pnlCalendar.add(cmbTheme);

        //Set bounds
        pnlCalendar.setBounds(0, 0, 650, 335);
        lblMonth.setBounds(160 - lblMonth.getPreferredSize().width / 2, 25, 100, 25);
        lblYear.setBounds(337, 305, 80, 20);
        cmbYear.setBounds(230, 305, 80, 20);
        btnPrev.setBounds(10, 25, 50, 25);
        btnNext.setBounds(260, 25, 50, 25);
        sCalendarTb.setBounds(10, 50, 300, 250);
        but.setBounds(330,20, 300,30);
        area.setBounds(330, 50, 300, 270);
        cmbTheme.setBounds(10, 305, 80, 20);
    }

    public static void CellSelection() {
        //Single cell selection
        CalendarTb.setColumnSelectionAllowed(true);
        CalendarTb.setRowSelectionAllowed(true);
        CalendarTb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public static void refreshCalendar(int month, int year) {
        //Variables
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int nod, som; //Number Of Days, Start Of Month

        //Allow/disallow buttons
        btnPrev.setEnabled(true);
        btnNext.setEnabled(true);
        if (month == 0 && year <= rYear - 10) {
            btnPrev.setEnabled(false);
        } //Too early
        if (month == 11 && year >= rYear + 100) {
            btnNext.setEnabled(false);
        } //Too late
        lblMonth.setText(months[month]); //Refresh the month label (at the top)
        lblMonth.setBounds(160 - lblMonth.getPreferredSize().width / 2, 25, 180, 25); //Re-align label with calendar
        cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box

        //Clear table
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                mCalendarTb.setValueAt(null, i, j);
            }
        }

        //Get first day of month and number of days
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);
        
        //Draw calendar
        for (int i = 1; i <= nod; i++) {
            int row = new Integer((i + som - 2) / 7);
            int column = (i + som - 2) % 7;
            mCalendarTb.setValueAt(i, row, column);
        }
        
        //Apply renderers
        CalendarTb.setDefaultRenderer(CalendarTb.getColumnClass(0), new CalendarTbRenderer());
    }
}
