package org.jabref.gui.specialfields;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.swing.undo.UndoManager;

import org.jabref.gui.DialogService;
import org.jabref.gui.LibraryTab;
import org.jabref.gui.StateManager;
import org.jabref.gui.actions.Action;
import org.jabref.gui.actions.StandardActions;
import org.jabref.gui.icon.JabRefIcon;
import org.jabref.gui.undo.UndoableFieldChange;
import org.jabref.logic.preferences.CliPreferences;
import org.jabref.logic.util.UpdateField;
import org.jabref.model.FieldChange;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.SpecialField;
import org.jabref.model.entry.field.SpecialFieldValue;

public class SpecialFieldViewModel {

    private final SpecialField field;
    private final CliPreferences preferences;
    private final UndoManager undoManager;

    public SpecialFieldViewModel(SpecialField field, CliPreferences preferences, UndoManager undoManager) {
        this.field = Objects.requireNonNull(field);
        this.preferences = Objects.requireNonNull(preferences);
        this.undoManager = Objects.requireNonNull(undoManager);
    }

    public SpecialField getField() {
        return field;
    }

    public SpecialFieldAction getSpecialFieldAction(SpecialFieldValue value,
                                                    Supplier<LibraryTab> tabSupplier,
                                                    DialogService dialogService,
                                                    StateManager stateManager) {
        return new SpecialFieldAction(
                tabSupplier,
                field,
                value.getFieldValue().orElse(null),
                // if field contains only one value, it has to be nulled, as another setting does not empty the field
                field.getValues().size() == 1,
                getLocalization(),
                dialogService,
                preferences,
                undoManager,
                stateManager);
    }

    public JabRefIcon getIcon() {
        return getAction().getIcon().get();
    }

    public String getLocalization() {
        return getAction().getText();
    }

    public Action getAction() {
        return switch (field) {
            case PRINTED -> StandardActions.PRINTED;
            case PRIORITY -> StandardActions.PRIORITY;
            case QUALITY -> StandardActions.QUALITY;
            case RANKING -> StandardActions.RANKING;
            case READ_STATUS -> StandardActions.READ_STATUS;
            case RELEVANCE -> StandardActions.RELEVANCE;
        };
    }

    public JabRefIcon getEmptyIcon() {
        return getIcon();
    }

    public List<SpecialFieldValueViewModel> getValues() {
        return field.getValues().stream()
                    .map(SpecialFieldValueViewModel::new)
                    .collect(Collectors.toList());
    }

    public void setSpecialFieldValue(BibEntry bibEntry, SpecialFieldValue value) {
        Optional<FieldChange> change = UpdateField.updateField(bibEntry, getField(), value.getFieldValue().orElse(null), getField().isSingleValueField());

        change.ifPresent(fieldChange -> undoManager.addEdit(new UndoableFieldChange(fieldChange)));
    }

    public void toggle(BibEntry entry) {
        setSpecialFieldValue(entry, getField().getValues().getFirst());
    }
}
