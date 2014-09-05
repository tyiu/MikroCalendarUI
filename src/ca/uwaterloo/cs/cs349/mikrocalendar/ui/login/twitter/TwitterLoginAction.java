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

import ca.uwaterloo.cs.cs349.mikrocalendar.events.MikroEventManager;
import ca.uwaterloo.cs.cs349.mikrocalendar.events.twitter.TwitterEventManager;
import ca.uwaterloo.cs.cs349.mikrocalendar.ui.login.LoginAction;

/**
 * This {@link LoginAction} logs into a Twitter service.
 * 
 * @author Terry Yiu
 * 
 */
public class TwitterLoginAction extends LoginAction {

	/**
	 * Creates a new {@link TwitterLoginAction} with a specified
	 * {@link TwitterLoginDialog}.
	 * 
	 * @param twitterLoginDialog the {@link TwitterLoginDialog}
	 */
	public TwitterLoginAction(TwitterLoginDialog twitterLoginDialog) {
		super(twitterLoginDialog);
	}

	@Override
	protected TwitterLoginDialog getLoginDialog() {
		return (TwitterLoginDialog) super.getLoginDialog();
	}
	
	@Override
	protected MikroEventManager createMikroEventManager() throws Exception {
		return new TwitterEventManager(
				getLoginDialog().getSelectedTwitterService().getUrl(), 
				getLoginDialog().getUsername(), 
				getLoginDialog().getPassword());
	}

}
