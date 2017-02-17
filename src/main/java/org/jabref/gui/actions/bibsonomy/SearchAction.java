package org.jabref.gui.actions.bibsonomy;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.jabref.gui.JabRefFrame;
import org.jabref.gui.bibsonomy.GroupingComboBoxItem;
import org.jabref.gui.bibsonomy.SearchType;
import org.jabref.gui.bibsonomy.SearchTypeComboBoxItem;
import org.jabref.gui.worker.bibsonomy.ImportPostsByCriteriaWorker;

/**
 * {@link SearchAction} runs the {@link ImportPostsByCriteriaWorker} with the values of the search text box.
 */
public class SearchAction extends AbstractBibSonomyAction {

    private JTextField searchTextField;
    private JComboBox<?> searchTypeComboBox;
    private JComboBox<?> groupingComboBox;

    public void actionPerformed(ActionEvent e) {
        SearchType searchType = ((SearchTypeComboBoxItem) searchTypeComboBox.getSelectedItem()).getKey();
        String criteria = searchTextField.getText();

        if (criteria != null) {
            ImportPostsByCriteriaWorker worker = new ImportPostsByCriteriaWorker(getJabRefFrame(), criteria, searchType, ((GroupingComboBoxItem) groupingComboBox.getSelectedItem()).getKey(), ((GroupingComboBoxItem) groupingComboBox.getSelectedItem()).getValue(), false);
            performAsynchronously(worker);
        }
    }

    public SearchAction(JabRefFrame jabRefFrame, JTextField searchTextField, JComboBox<?> searchTypeComboBox, JComboBox<?> groupingComboBox) {
        super(jabRefFrame, "", new ImageIcon(SearchAction.class.getResource("/images/bibsonomy/magnifier.png")));

        this.searchTextField = searchTextField;
        this.searchTypeComboBox = searchTypeComboBox;
        this.groupingComboBox = groupingComboBox;
    }
}
