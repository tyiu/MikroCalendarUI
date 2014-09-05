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

package ca.uwaterloo.cs.cs349.mikrocalendar.ui.login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import ca.uwaterloo.cs.cs349.mikrocalendar.ui.SpringUtilities;

/**
 * This {@link JDialog} is a user interface for logging into event logging
 * services. The user can select where to log their events to and provide a
 * username.
 * 
 * @author Terry Yiu
 * 
 */
public class LoginDialog extends JDialog {

	/**
	 * This {@link JTextField} contains the username to login with.
	 * 
	 * @see #getUsername()
	 */
	private final JTextField usernameTextField;

	/**
	 * This {@link JButton} can be clicked to login to the event logging
	 * service.
	 */
	private final JButton loginButton;

	/**
	 * This {@link JPanel} contains the main components of the login form.
	 */
	protected final JPanel infoPanel;
	
	/**
	 * Creates a new {@link LoginDialog}.
	 */
	public LoginDialog() {
		super();
		
		setTitle("Login");
		
		// Create the main components.
		final JLabel usernameLabel = new JLabel("Username:");
		usernameTextField = new JTextField();
		
		// Create the Quit and Login buttons.
		final JButton quitButton = new JButton(new AbstractAction("Quit") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginDialog.this.dispose();
			}
		});
		loginButton = new JButton("Login");
		
		// Add main components to panel.
		infoPanel = new JPanel(new SpringLayout());
		infoPanel.add(usernameLabel);
		infoPanel.add(usernameTextField);
		
		// Reposition components for a better look.
		SpringUtilities.makeCompactGrid(infoPanel, 1, 2, 10, 10, 10, 10);
		
		// Add buttons to panel.
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(quitButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(loginButton);
		
		// Reposition the two panels.
		getContentPane().add(infoPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
		
		// Set the login button as the default button so that pressing Enter on
		// the keyboard will trigger its action.
		getRootPane().setDefaultButton(loginButton);
		
		// Set the default focus to be the username text field so that the user
		// can immediately type their username when the dialog opens.
		usernameTextField.requestFocusInWindow();
		
		setMinimumSize(new Dimension(300, (int) getMinimumSize().getHeight()));
	}

	/**
	 * Sets the {@link Action} to perform when the Login button is clicked.
	 * 
	 * @param loginAction
	 *            the action
	 */
	public void setLoginAction(Action loginAction) {
		loginButton.setAction(loginAction);
	}

	/**
	 * Enables or disables all of the user editable components in the login
	 * form.
	 * 
	 * @param enable
	 *            true if components should be enabled. false if components
	 *            should be disabled.
	 */
	public void enableComponents(boolean enable) {
		usernameTextField.setEnabled(enable);
	}
	
	/**
	 * Returns the username that is entered in the {@link JTextField}.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return usernameTextField.getText();
	}

}
