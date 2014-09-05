/*
 * MikroCalendar Date/Time Picker
 * User interface for selecting a date and time.
 * Copyright (C) 2011  Terry Yiu
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ca.uwaterloo.cs.cs349.mikrocalendar.ui.datetimepicker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.joda.time.DateTime;

import ca.uwaterloo.cs.cs349.mikrocalendar.ui.SpringUtilities;

/**
 * The purpose of this {@link JDialog} is for picking a {@link DateTime}.
 * 
 * @author Terry Yiu
 * 
 */
public class DateTimePickerDialog extends JDialog {

	/**
	 * The original {@link DateTime} before any user modification.
	 */
	private final DateTime dateTime;

	/**
	 * This {@link JComboBox} allows the month portion of the date time to be
	 * selected.
	 */
	private final JComboBox monthComboBox;

	/**
	 * This {@link JComboBox} allows the day portion of the date time to be
	 * selected.
	 */
	private final JComboBox dayComboBox;

	/**
	 * This {@link JTextField} allows the year portion of the date time to be
	 * entered.
	 */
	private final JTextField yearTextField;

	/**
	 * This {@link JComboBox} allows the hour portion of the date time to be
	 * selected.
	 */
	private final JComboBox hourComboBox;

	/**
	 * This {@link JComboBox} allows the minute portion of the date time to be
	 * selected.
	 */
	private final JComboBox minuteComboBox;

	/**
	 * This {@link JButton} confirms the selection of the date time. If the date
	 * time is not valid, this button is disabled.
	 */
	private final JButton okButton;

	/**
	 * This {@link JButton} discards any changes made to the date time.
	 */
	private final JButton cancelButton;

	/**
	 * Creates a new {@link DateTimePickerDialog} with a specified
	 * {@link DateTime}.
	 * 
	 * @param dateTime
	 *            The {@link DateTime}.
	 */
	public DateTimePickerDialog(DateTime dateTime) {
		super((Frame)null, true);
		setTitle("Date Time Picker");
		
		this.dateTime = dateTime;
		
		// Array of selectable months.
		String[] months = new String[]{
				null,
				"January", 
				"February", 
				"March", 
				"April", 
				"May", 
				"June", 
				"July", 
				"August", 
				"September", 
				"October", 
				"November", 
				"December"};
		
		// Array of selectable days.
		Integer days[] = new Integer[32];
		days[0] = null;
		for (int i = 1; i < days.length; i++) {
			days[i] = new Integer(i);
		}
		
		// Array of selectable hours.
		Integer hours[] = new Integer[24];
		for (int i = 0; i < hours.length; i++) {
			hours[i] = new Integer(i);
		}
		
		// Array of selectable minutes.
		Integer minutes[] = new Integer[60];
		for (int i = 0; i < minutes.length; i++) {
			minutes[i] = new Integer(i);
		}
		
		// Date time fields.
		monthComboBox = new JComboBox(months);
		dayComboBox = new JComboBox(days);
		yearTextField = new JTextField();
		yearTextField.setPreferredSize(
				new Dimension(40, (int) yearTextField.getPreferredSize().getHeight()));
		hourComboBox = new JComboBox(hours);
		minuteComboBox = new JComboBox(minutes);
		
		if (dateTime == null) {
			monthComboBox.setSelectedIndex(0);
			dayComboBox.setSelectedIndex(0);
			yearTextField.setText(null);
			hourComboBox.setSelectedIndex(0);
			minuteComboBox.setSelectedIndex(0);
		} else {
			monthComboBox.setSelectedIndex(dateTime.getMonthOfYear());
			dayComboBox.setSelectedIndex(dateTime.getDayOfMonth());
			yearTextField.setText(String.valueOf(dateTime.getYear()));
			hourComboBox.setSelectedIndex(dateTime.getHourOfDay());
			minuteComboBox.setSelectedIndex(dateTime.getMinuteOfHour());
		}
		
		// Labels to indicate what each field is for.
		JLabel monthLabel = new JLabel("Month");
		JLabel dayLabel = new JLabel("Day");
		JLabel yearLabel = new JLabel("Year");
		JLabel hourLabel = new JLabel("Hour");
		JLabel minuteLabel = new JLabel("Minute");
		
		// Main panel.
		final JPanel dateTimePanel = new JPanel(new SpringLayout());
		dateTimePanel.add(monthLabel);
		dateTimePanel.add(dayLabel);
		dateTimePanel.add(yearLabel);
		dateTimePanel.add(hourLabel);
		dateTimePanel.add(minuteLabel);
		dateTimePanel.add(monthComboBox);
		dateTimePanel.add(dayComboBox);
		dateTimePanel.add(yearTextField);
		dateTimePanel.add(hourComboBox);
		dateTimePanel.add(minuteComboBox);
		
		final ActionListener validateListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				validateFields();
			}
		};
		
		// Add listeners to the date time fields which enable or disable
		// the OK button.
		monthComboBox.addActionListener(validateListener);
		dayComboBox.addActionListener(validateListener);
		yearTextField.addActionListener(validateListener);
		hourComboBox.addActionListener(validateListener);
		dayComboBox.addActionListener(validateListener);
		
		// Reposition components for a better look.
		SpringUtilities.makeCompactGrid(dateTimePanel, 2, 5, 10, 10, 10, 10);
		
		// Create buttons.
		cancelButton = new JButton("Cancel");
		okButton = new JButton("OK");
		JButton resetButton = new JButton(new AbstractAction("Reset") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resetFields();
			}
		});
		
		// Add buttons to panel.
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(resetButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(cancelButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(okButton);
		
		getContentPane().add(dateTimePanel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
		
		// Set fields to the date time that was passed into the constructor.
		resetFields();
	}

	/**
	 * Sets the {@link Action} to perform when the Cancel button is clicked.
	 * 
	 * @param cancelAction
	 *            the {@link Action}
	 */
	public void setCancelAction(Action cancelAction) {
		cancelButton.setAction(cancelAction);
	}

	/**
	 * Sets the {@link Action} to perform when the OK button is clicked.
	 * 
	 * @param okAction
	 *            the {@link Action}
	 */
	public void setOKAction(Action okAction) {
		okButton.setAction(okAction);
		validateFields();
	}

	/**
	 * Sets the fields to the original date time.
	 */
	private void resetFields() {
		if (dateTime == null) {
			monthComboBox.setSelectedIndex(0);
			dayComboBox.setSelectedIndex(0);
			yearTextField.setText(null);
			hourComboBox.setSelectedIndex(0);
			minuteComboBox.setSelectedIndex(0);
		} else {
			monthComboBox.setSelectedIndex(dateTime.getMonthOfYear());
			dayComboBox.setSelectedIndex(dateTime.getDayOfMonth());
			yearTextField.setText(String.valueOf(dateTime.getYear()));
			hourComboBox.setSelectedIndex(dateTime.getHourOfDay());
			minuteComboBox.setSelectedIndex(dateTime.getMinuteOfHour());
		}
		
		validateFields();
	}

	/**
	 * Validates the fields. The OK button is enabled only if all the date time
	 * fields are filled in.
	 */
	private void validateFields() {
		okButton.setEnabled(isDateTimeValid());
	}

	/**
	 * Returns whether or not the date time is valid.
	 * 
	 * @return true if date time is valid. false otherwise.
	 */
	private boolean isDateTimeValid() {
		try {
			return monthComboBox.getSelectedIndex() > 0
			&& dayComboBox.getSelectedIndex() > 0
			&& Integer.parseInt(yearTextField.getText()) >= 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Returns the {@link DateTime} representation of the filled in fields for
	 * the date time.
	 * 
	 * @return the {@link DateTime}
	 */
	public DateTime getDateTime() {
		if (!isDateTimeValid()) {
			return null;
		}
		
		DateTime dateTime = new DateTime(
				Integer.parseInt(yearTextField.getText()), 
				monthComboBox.getSelectedIndex(), 
				dayComboBox.getSelectedIndex(), 
				hourComboBox.getSelectedIndex(), 
				minuteComboBox.getSelectedIndex(), 
				0, 
				0);
		return dateTime;
	}
	
}
