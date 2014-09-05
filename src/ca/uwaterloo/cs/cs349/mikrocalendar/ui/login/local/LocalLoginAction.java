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

package ca.uwaterloo.cs.cs349.mikrocalendar.ui.login.local;

import java.io.File;

import ca.uwaterloo.cs.cs349.mikrocalendar.events.MikroEventManager;
import ca.uwaterloo.cs.cs349.mikrocalendar.events.local.LocalEventManager;
import ca.uwaterloo.cs.cs349.mikrocalendar.ui.login.LoginAction;

/**
 * This {@link LoginAction} logs in to a local event logging system by using a
 * JSON file.
 * 
 * @author Terry Yiu
 * 
 */
public class LocalLoginAction extends LoginAction {

	/**
	 * Creates a new {@link LocalLoginAction} with a specified
	 * {@link LocalLoginDialog}.
	 * 
	 * @param loginDialog
	 *            the {@link LocalLoginDialog}
	 */
	public LocalLoginAction(LocalLoginDialog loginDialog) {
		super(loginDialog);
	}
	
	@Override
	protected LocalLoginDialog getLoginDialog() {
		return (LocalLoginDialog) super.getLoginDialog();
	}
	
	@Override
	protected MikroEventManager createMikroEventManager() throws Exception {
		File localJSONFile = new File(getLoginDialog().getAbsolutePath());
		return new LocalEventManager(getLoginDialog().getUsername(), localJSONFile);
	}

}
