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

/**
 * This class represents a single Twitter service that can be connected to.
 * 
 * @author Terry Yiu
 */
public class TwitterService {

	/**
	 * Name of the Twitter service.
	 * 
	 * @see #getName()
	 */
	private final String name;

	/**
	 * String representation of the URL to the Twitter service.
	 * 
	 * @see #getUrl()
	 */
	private final String url;

	/**
	 * Creates a new {@link TwitterService} with the specified name and URL.
	 * 
	 * @param name
	 *            The name of the Twitter service.
	 * @param url
	 *            The String representation of the URL to the Twitter service.
	 */
	public TwitterService(String name, String url) {
		this.name = name;
		this.url = url;
	}

	/**
	 * Returns the name of the Twitter service.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the String representation of the URL to the Twitter service.
	 * 
	 * @return the URL
	 */
	public String getUrl() {
		return url;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
