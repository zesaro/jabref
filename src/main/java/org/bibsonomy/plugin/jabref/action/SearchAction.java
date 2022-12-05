package org.bibsonomy.plugin.jabref.action;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import net.sf.jabref.JabRefFrame;

import org.bibsonomy.plugin.jabref.gui.GroupingComboBoxItem;
import org.bibsonomy.plugin.jabref.gui.SearchType;
import org.bibsonomy.plugin.jabref.gui.SearchTypeComboBoxItem;
import org.bibsonomy.plugin.jabref.worker.ImportPostsByCriteriaWorker;

/**
 * {@link SearchAction} runs the {@link ImportPostsByCriteriaWorker} with the values of the search text box
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 *
 */
public class SearchAction extends AbstractPluginAction {

	private static final long serialVersionUID = -2051315699879554553L;

	private JTextField searchTextField;

	private JComboBox<?> searchTypeComboBox;

	private JComboBox<?> groupingComboBox;

	public void actionPerformed(ActionEvent e) {

		SearchType st = ((SearchTypeComboBoxItem) searchTypeComboBox.getSelectedItem()).getKey();
		String criteria = searchTextField.getText();

		if(criteria != null) {
			ImportPostsByCriteriaWorker worker = new ImportPostsByCriteriaWorker(getJabRefFrame(), criteria, st, ((GroupingComboBoxItem) groupingComboBox.getSelectedItem()).getKey(), ((GroupingComboBoxItem) groupingComboBox.getSelectedItem()).getValue(), false);
			performAsynchronously(worker);
		}
	}

	public SearchAction(JabRefFrame jabRefFrame, JTextField searchTextField, JComboBox<?> searchTypeComboBox, JComboBox<?> groupingComboBox) {

		super(jabRefFrame, "", new ImageIcon(SearchAction.class.getResource("/images/magnifier.png")));

		this.searchTextField = searchTextField;
		this.searchTypeComboBox = searchTypeComboBox;
		this.groupingComboBox = groupingComboBox;
	}
}
