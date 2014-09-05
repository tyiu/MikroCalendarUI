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

import javax.swing.JDialog;

import ca.uwaterloo.cs.cs349.mikrocalendar.events.twitter.TwitterEventManager;
import ca.uwaterloo.cs.cs349.mikrocalendar.ui.login.local.LocalLoginAction;
import ca.uwaterloo.cs.cs349.mikrocalendar.ui.login.local.LocalLoginDialog;
import ca.uwaterloo.cs.cs349.mikrocalendar.ui.login.twitter.TwitterLoginAction;
import ca.uwaterloo.cs.cs349.mikrocalendar.ui.login.twitter.TwitterLoginDialog;
import ca.uwaterloo.cs.cs349.mikrocalendar.ui.login.twitter.TwitterService;

/**
 * This factory class creates instances of {@link LoginDialog}.
 * 
 * @author Terry Yiu
 * 
 */
public class LoginDialogFactory {

	/**
	 * Instances of this factory cannot be created.
	 */
	private LoginDialogFactory() {
		// No-op.
	}

	/**
	 * Creates a new {@link LoginDialog} that can login to two predefined
	 * Twitter services ("Channel W" and "Channel W Test").
	 * 
	 * @return the dialog
	 */
	public static LoginDialog createLoginDialog() {
		return createLoginDialog(false);
	}

	/**
	 * Creates a new {@link LoginDialog} that can login to either a local event
	 * system or a Twitter service.
	 * 
	 * @param local
	 *            true if a local event system should be accessed. false if a
	 *            Twitter service should be accessed.
	 * @return the {@link LoginDialog}
	 */
	public static LoginDialog createLoginDialog(boolean local) {
		LoginDialog loginDialog;
		
		// Set the action to perform when the Login button is clicked.
		if (local) {
			LocalLoginDialog localLoginDialog = new LocalLoginDialog();
			localLoginDialog.setLoginAction(new LocalLoginAction(localLoginDialog));
			
			loginDialog = localLoginDialog;
			
		} else {
			TwitterLoginDialog twitterLoginDialog = new TwitterLoginDialog();
			twitterLoginDialog.setLoginAction(
					new TwitterLoginAction(twitterLoginDialog));
			
			// Create and add the two Twitter services.
			TwitterService productionService = new TwitterService("Channel W", TwitterEventManager.PRODUCTION_SERVICE_URL);
			TwitterService testingService = new TwitterService("Channel W Test", TwitterEventManager.TESTING_SERVICE_URL);
			
			twitterLoginDialog.addTwitterService(productionService);
			twitterLoginDialog.addTwitterService(testingService);
			
			loginDialog = twitterLoginDialog;
		}
		
		// Ensure that the dialog is disposed of when closed.
		loginDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		// Pack to resize components.
		loginDialog.pack();
		
		// Center the dialog to the middle of the screen.
		loginDialog.setLocationRelativeTo(null);
		
		// Prevent dialog from being resized. It should already be set to an
		// optimal size.
		loginDialog.setResizable(false);
		
		return loginDialog;
	}
	
}
