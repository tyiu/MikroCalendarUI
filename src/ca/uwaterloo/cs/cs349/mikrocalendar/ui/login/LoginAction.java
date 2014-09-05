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

import java.awt.Cursor;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ca.uwaterloo.cs.cs349.mikrocalendar.events.MikroEventManager;
import ca.uwaterloo.cs.cs349.mikrocalendar.ui.login.twitter.TwitterService;

/**
 * This {@link Action} logs into a local event system or a Twitter service.
 * 
 * @author Terry Yiu
 * 
 */
public abstract class LoginAction extends AbstractAction {

	/**
	 * The {@link LoginDialog} that contains the {@link TwitterService},
	 * username, and password information.
	 * 
	 * @see #getLoginDialog()
	 */
	private final LoginDialog loginDialog;

	/**
	 * Creates a new {@link AbstractLoginAction} with a specified
	 * {@link LoginDialog}.
	 * 
	 * @param loginDialog
	 *            The {@link LoginDialog} that contains the selected
	 *            {@link TwitterService}, username, and password information.
	 */
	public LoginAction(LoginDialog loginDialog) {
		super("Login");
		
		if (loginDialog == null) {
			throw new IllegalArgumentException("LoginDialog cannot be null.");
		}
		
		this.loginDialog = loginDialog;
	}

	/**
	 * Creates a {@link MikroEventManager} and attempts to login to the service.
	 * This method should not be called on the event dispatching thread. If
	 * creating the {@link MikroEventManager} is a long task, the event
	 * dispatching thread will be noticeably blocked.
	 * 
	 * @return the {@link MikroEventManager}
	 * @throws Exception
	 *             Thrown if login fails.
	 */
	protected abstract MikroEventManager createMikroEventManager() throws Exception;

	/**
	 * Returns the {@link LoginDialog} that this {@link LoginAction} is tied to.
	 * 
	 * @return the {@link LoginDialog}
	 */
	protected LoginDialog getLoginDialog() {
		return loginDialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Create a new thread for the login operation to run on.
		// By creating a separate thread, the thread that the login dialog is
		// running on will not block.
		Thread loginThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					final MikroEventManager mikroEventManager = createMikroEventManager();
					
					// Login is successful. Change the UI accordingly on the
					// event dispatching thread.
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							// The cursor can be set back to normal and the
							// login dialog can be disposed of.
							loginDialog.setCursor(Cursor.getDefaultCursor());
							loginDialog.dispose();
							
							// TODO Create the main frame and populate its components
							// by retrieving data from the MikroEventManager.
						}
					});
					
				} catch (Exception e) {
					// An exception is caught if login fails.
					// Change the UI accordingly on the event dispatching
					// thread.
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							// The cursor can be set back to normal.
							loginDialog.setCursor(Cursor.getDefaultCursor());
							
							// Show error dialog to user.
							JOptionPane.showMessageDialog(
									loginDialog, 
									"Your username or password is invalid.", 
									"Error", 
									JOptionPane.ERROR_MESSAGE);
							
							// Re-enable components so that the user can fix their login
							// information.
							loginDialog.enableComponents(true);
							setEnabled(true);
						}
					});
				}
			}
		});
		
		// Set the cursor to a throbber to indicate that logging in is in progress.
		loginDialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		// Disable components so that they may not be interacted with while
		// login in is in progress.
		setEnabled(false);
		loginDialog.enableComponents(false);
		
		// Start login thread.
		loginThread.start();
	}

}
