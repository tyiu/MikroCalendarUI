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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import ca.uwaterloo.cs.cs349.mikrocalendar.ui.SpringUtilities;
import ca.uwaterloo.cs.cs349.mikrocalendar.ui.login.LoginDialog;

/**
 * This {@link LoginDialog} provides a form for the user to login to a local
 * event logging service (specifically, to a JSON file).
 * 
 * @author Terry Yiu
 * 
 */
public class LocalLoginDialog extends LoginDialog {

	/**
	 * This {@link JTextField} holds the absolute path to the JSON file.
	 */
	private final JTextField fileTextField;

	/**
	 * This {@link JButton} triggers a {@link JFileChooser} to display, which is
	 * subsequently used to select a JSON file to login to.
	 */
	private final JButton fileButton;
	
	/**
	 * Creates a new {@link LocalLoginDialog}.
	 */
	public LocalLoginDialog() {
		super();
		
		// Create file choosing components.
		final JLabel fileLabel = new JLabel("File:");
		fileTextField = new JTextField();
		fileButton = new JButton(new AbstractAction("...") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Bring up file chooser to choose the JSON file.
				final JFileChooser fileChooser = new JFileChooser(fileTextField.getText());
				
				// Filter only for directories or JSON files.
				fileChooser.setFileFilter(new FileFilter() {
					
					@Override
					public String getDescription() {
						return "JSON - JavaScript Object Notation";
					}
					
					@Override
					public boolean accept(File f) {
						if (f.isFile()) {
							String filename = f.getName();
							int index = filename.lastIndexOf('.');
							return index != -1 && filename.substring(index + 1).equalsIgnoreCase("json");
						} else {
							return true;
						}
					}
				});
				fileChooser.setAcceptAllFileFilterUsed(false);
				
				// Prompt user to select a file.
				int result = fileChooser.showOpenDialog(LocalLoginDialog.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					fileTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		// Add file choosing components to a panel.
		final JPanel filePanel = new JPanel(new BorderLayout(10, 0));
		filePanel.add(fileTextField, BorderLayout.CENTER);
		filePanel.add(fileButton, BorderLayout.LINE_END);
		
		// Add file choosing panel to the main form panel.
		infoPanel.add(fileLabel, 0);
		infoPanel.add(filePanel, 1);
		
		// Reposition components for a better look.
		SpringUtilities.makeCompactGrid(infoPanel, 2, 2, 10, 10, 10, 10);
	}
	
	@Override
	public void enableComponents(boolean enable) {
		super.enableComponents(enable);
		fileTextField.setEnabled(enable);
		fileButton.setEnabled(enable);
	}

	/**
	 * Returns the absolute path to the JSON file to login to.
	 * 
	 * @return the absolute path
	 */
	public String getAbsolutePath() {
		return fileTextField.getText();
	}
	
}
