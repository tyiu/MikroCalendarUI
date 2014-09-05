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

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;

import org.joda.time.DateTime;

import ca.uwaterloo.cs.cs349.mikrocalendar.ui.DateTimePicker;

/**
 * This {@link DateTimePicker} implementation interacts with a
 * {@link DateTimePickerDialog} to allow users to pick a start and end
 * {@link DateTime}.
 * 
 * @author Terry Yiu
 * 
 */
public class DateTimePickerImpl implements DateTimePicker {

	/**
	 * The start {@link DateTime} that is selected.
	 * 
	 * @see #getStartDateTime()
	 */
	private DateTime startDateTime;
	
	/**
	 * The end {@link DateTime} that is selected.
	 * 
	 * @see #getEndDateTime()
	 */
	private DateTime endDateTime;

	/**
	 * boolean flag to determine whether the user pressed cancel on the
	 * {@link DateTimePickerDialog}.
	 */
	private boolean userCancelled;
	
	/**
	 * Creates a new {@link DateTimePickerImpl}.
	 */
	public DateTimePickerImpl() {
		this(null, null);
	}

	/**
	 * Creates a new {@link DateTimePickerImpl} with a specified start date
	 * time.
	 * 
	 * @param startDateTime
	 *            the start {@link DateTime}
	 */
	public DateTimePickerImpl(DateTime startDateTime) {
		this(startDateTime, null);
	}

	/**
	 * Creates a new {@link DateTimePickerImpl} with a specified start and end
	 * date time.
	 * 
	 * @param startDateTime
	 *            the start {@link DateTime}
	 * @param endDateTime
	 *            the end {@link DateTime}
	 */
	public DateTimePickerImpl(DateTime startDateTime, DateTime endDateTime) {
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		userCancelled = false;
	}
	
	@Override
	public DateTime getStartDateTime() {
		return startDateTime;
	}

	@Override
	public DateTime getEndDateTime() {
		return endDateTime;
	}

	@Override
	public boolean userChoseNoStartTime() {
		return startDateTime == null;
	}

	@Override
	public boolean userChoseNoEndTime() {
		return endDateTime == null;
	}

	@Override
	public boolean userCancelled() {
		return userCancelled;
	}

	@Override
	public void showDateTimePicker() {
		userCancelled = false;
		
		final DateTimePickerDialog dialog = new DateTimePickerDialog(startDateTime);
		
		dialog.setOKAction(new AbstractAction("OK") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				userCancelled = false;
				dialog.dispose();
			}
		});
		
		Action cancelAction = new AbstractAction("Cancel") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				userCancelled = true;
				dialog.dispose();
			}
		};
		
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				userCancelled = true;
			}
		});
		
		dialog.setCancelAction(cancelAction);
		
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);
		
		if (!userCancelled) {
			startDateTime = dialog.getDateTime();
		}
	}

}
