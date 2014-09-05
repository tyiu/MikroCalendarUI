/*
 * MikroCalendar Login Dialog
 * Login user interface for local and Twitter event systems.
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

package ca.uwaterloo.cs.cs349.mikrocalendar.ui.login.twitter;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import ca.uwaterloo.cs.cs349.mikrocalendar.ui.SpringUtilities;
import ca.uwaterloo.cs.cs349.mikrocalendar.ui.login.LoginDialog;

/**
 * This {@link LoginDialog} provides a form for the user to login to a Twitter
 * service.
 * 
 * @author Terry Yiu
 * 
 */
public class TwitterLoginDialog extends LoginDialog {

	/**
	 * This {@link JComboBox} selects the Twitter service to connect to.
	 * 
	 * @see #getSelectedTwitterService()
	 */
	private final JComboBox serviceComboBox;

	/**
	 * This {@link JPasswordField} contains the password to login with.
	 * 
	 * @see #getPassword()
	 */
	private final JPasswordField passwordField;
	
	/**
	 * Creates a new {@link TwitterLoginDialog}.
	 */
	public TwitterLoginDialog() {
		super();
		
		// Create the service selection and password field components.
		final JLabel serviceLabel = new JLabel("Service:");
		final JLabel passwordLabel = new JLabel("Password:");
		
		serviceComboBox = new JComboBox();
		passwordField = new JPasswordField();
		
		// Add the service selection and password field components to the main panel.
		infoPanel.add(serviceLabel, 0);
		infoPanel.add(serviceComboBox, 1);
		infoPanel.add(passwordLabel, -1);
		infoPanel.add(passwordField, -1);
		
		// Reposition components for a better look.
		SpringUtilities.makeCompactGrid(infoPanel, 3, 2, 10, 10, 10, 10);
	}
	
	/**
	 * Adds a {@link TwitterService} to the {@link JComboBox} of services that
	 * can be connected to.
	 * 
	 * @param twitterService
	 *            the Twitter service
	 */
	public void addTwitterService(TwitterService twitterService) {
		serviceComboBox.addItem(twitterService);
	}
	
	@Override
	public void enableComponents(boolean enable) {
		super.enableComponents(enable);
		serviceComboBox.setEnabled(enable);
		passwordField.setEnabled(enable);
	}
	
	/**
	 * Returns the selected {@link TwitterService}.
	 * 
	 * @return the selected Twitter service
	 */
	public TwitterService getSelectedTwitterService() {
		return (TwitterService) serviceComboBox.getSelectedItem();
	}

	/**
	 * Returns the {@link String} representation of the password that is entered
	 * in the {@link JPasswordField}.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return new String(passwordField.getPassword());
	}

	
}
