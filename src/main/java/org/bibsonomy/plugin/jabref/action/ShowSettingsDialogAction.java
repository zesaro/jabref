package org.bibsonomy.plugin.jabref.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.bibsonomy.plugin.jabref.gui.PluginSettingsDialog;

import net.sf.jabref.JabRefFrame;

/**
 * {@link ShowSettingsDialogAction} creates and displays the {@link PluginSettingsDialog}
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 *
 */
public class ShowSettingsDialogAction extends AbstractAction {

	private static final long serialVersionUID = -953537334224313648L;

	private JabRefFrame jabRefFrame;

	public void actionPerformed(ActionEvent e) {

		PluginSettingsDialog psd = new PluginSettingsDialog(jabRefFrame);
		psd.setVisible(true);
		psd.setLocationRelativeTo(jabRefFrame);
	}

	public ShowSettingsDialogAction(JabRefFrame jabRefFrame) {

		super("Settings", new ImageIcon(ShowSettingsDialogAction.class.getResource("/images/wrench-screwdriver.png")));
		this.jabRefFrame = jabRefFrame;
	}
}
