package org.jabref.preferences;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import org.bibsonomy.common.enums.GroupingEntity;
import org.bibsonomy.model.enums.Order;

public class BibsonomyPreferences {
    private final StringProperty apiUrl;
    private final StringProperty apiUsername;
    private final StringProperty apiKey;
    private final StringProperty bibsonomyVisibility;
    private final StringProperty bibsonomySidePaneVisibilityName;
    private final BooleanProperty bibsonomyDocumentsImport;
    private final BooleanProperty bibsonomyDocumentsExport;
    private final BooleanProperty bibsonomySaveApiKey;
    private final BooleanProperty bibsonomyTagsRefreshOnStartup;
    private final BooleanProperty bibsonomyRequestSizeIgnoreWarning;
    private final BooleanProperty bibsonomyTagsIgnoreNoTags;
    private final IntegerProperty bibsonomyTagCloudSize;
    private final IntegerProperty bibsonomyRequestSize;
    private final ObjectProperty<GroupingEntity> bibsonomySidePaneVisibilityType;
    private final ObjectProperty<Order> bibsonomyTagCloudOrder;

    public BibsonomyPreferences(String apiUrl, String apiUsername, String apiKey,
                                String bibsonomySidePaneVisibilityName, String bibsonomyVisibility,
                                GroupingEntity bibsonomySidePaneVisibilityType, Order bibsonomyTagCloudOrder,
                                boolean bibsonomyDocumentsImport, boolean bibsonomyDocumentsExport,
                                boolean bibsonomySaveApiKey, boolean bibsonomyTagsRefreshOnStartup,
                                boolean bibsonomyRequestSizeIgnoreWarning, boolean bibsonomyTagsIgnoreNoTags,
                                int bibsonomyTagCloudSize, int bibsonomyRequestSize) {
        this.apiUrl = new SimpleStringProperty(apiUrl);
        this.apiUsername = new SimpleStringProperty(apiUsername);
        this.apiKey = new SimpleStringProperty(apiKey);

        this.bibsonomyVisibility = new SimpleStringProperty(bibsonomyVisibility);
        this.bibsonomyTagCloudOrder = new SimpleObjectProperty<Order>(bibsonomyTagCloudOrder);
        this.bibsonomySidePaneVisibilityType = new SimpleObjectProperty<GroupingEntity>(bibsonomySidePaneVisibilityType);

        this.bibsonomyDocumentsImport = new SimpleBooleanProperty(bibsonomyDocumentsImport);
        this.bibsonomyDocumentsExport = new SimpleBooleanProperty(bibsonomyDocumentsExport);
        this.bibsonomySaveApiKey = new SimpleBooleanProperty(bibsonomySaveApiKey);
        this.bibsonomyTagsRefreshOnStartup = new SimpleBooleanProperty(bibsonomyTagsRefreshOnStartup);
        this.bibsonomyRequestSizeIgnoreWarning = new SimpleBooleanProperty(bibsonomyRequestSizeIgnoreWarning);
        this.bibsonomySidePaneVisibilityName = new SimpleStringProperty(bibsonomySidePaneVisibilityName);
        this.bibsonomyTagsIgnoreNoTags = new SimpleBooleanProperty(bibsonomyTagsIgnoreNoTags);

        this.bibsonomyRequestSize = new SimpleIntegerProperty(bibsonomyRequestSize);
        this.bibsonomyTagCloudSize = new SimpleIntegerProperty(bibsonomyTagCloudSize);
    }

    public String getApiUrl() {
        return apiUrl.get();
    }

    public StringProperty apiUrlProperty() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl.set(apiUrl);
    }

    public String getApiUsername() {
        return apiUsername.get();
    }

    public StringProperty apiUsernameProperty() {
        return apiUsername;
    }

    public void setApiUsername(String apiUsername) {
        this.apiUsername.set(apiUsername);
    }

    public String getApiKey() {
        return apiKey.get();
    }

    public StringProperty apiKeyProperty() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey.set(apiKey);
    }

    public String getBibsonomyVisibility() {
        return bibsonomyVisibility.get();
    }

    public StringProperty bibsonomyVisibilityProperty() {
        return bibsonomyVisibility;
    }

    public void setBibsonomyVisibility(String bibsonomyVisibility) {
        this.bibsonomyVisibility.set(bibsonomyVisibility);
    }

    public String getBibsonomySidePaneVisibilityName() {
        return bibsonomySidePaneVisibilityName.get();
    }

    public StringProperty bibsonomySidePaneVisibilityNameProperty() {
        return bibsonomySidePaneVisibilityName;
    }

    public void setBibsonomySidePaneVisibilityName(String bibsonomySidePaneVisibilityName) {
        this.bibsonomySidePaneVisibilityName.set(bibsonomySidePaneVisibilityName);
    }

    public boolean isBibsonomyDocumentsImport() {
        return bibsonomyDocumentsImport.get();
    }

    public BooleanProperty bibsonomyDocumentsImportProperty() {
        return bibsonomyDocumentsImport;
    }

    public void setBibsonomyDocumentsImport(boolean bibsonomyDocumentsImport) {
        this.bibsonomyDocumentsImport.set(bibsonomyDocumentsImport);
    }

    public boolean isBibsonomyDocumentsExport() {
        return bibsonomyDocumentsExport.get();
    }

    public BooleanProperty bibsonomyDocumentsExportProperty() {
        return bibsonomyDocumentsExport;
    }

    public void setBibsonomyDocumentsExport(boolean bibsonomyDocumentsExport) {
        this.bibsonomyDocumentsExport.set(bibsonomyDocumentsExport);
    }

    public boolean isBibsonomySaveApiKey() {
        return bibsonomySaveApiKey.get();
    }

    public BooleanProperty bibsonomySaveApiKeyProperty() {
        return bibsonomySaveApiKey;
    }

    public void setBibsonomySaveApiKey(boolean bibsonomySaveApiKey) {
        this.bibsonomySaveApiKey.set(bibsonomySaveApiKey);
    }

    public boolean isBibsonomyTagsRefreshOnStartup() {
        return bibsonomyTagsRefreshOnStartup.get();
    }

    public BooleanProperty bibsonomyTagsRefreshOnStartupProperty() {
        return bibsonomyTagsRefreshOnStartup;
    }

    public void setBibsonomyTagsRefreshOnStartup(boolean bibsonomyTagsRefreshOnStartup) {
        this.bibsonomyTagsRefreshOnStartup.set(bibsonomyTagsRefreshOnStartup);
    }

    public boolean isBibsonomyRequestSizeIgnoreWarning() {
        return bibsonomyRequestSizeIgnoreWarning.get();
    }

    public BooleanProperty bibsonomyRequestSizeIgnoreWarningProperty() {
        return bibsonomyRequestSizeIgnoreWarning;
    }

    public void setBibsonomyRequestSizeIgnoreWarning(boolean bibsonomyRequestSizeIgnoreWarning) {
        this.bibsonomyRequestSizeIgnoreWarning.set(bibsonomyRequestSizeIgnoreWarning);
    }

    public boolean isBibsonomyTagsIgnoreNoTags() {
        return bibsonomyTagsIgnoreNoTags.get();
    }

    public BooleanProperty bibsonomyTagsIgnoreNoTagsProperty() {
        return bibsonomyTagsIgnoreNoTags;
    }

    public void setBibsonomyTagsIgnoreNoTags(boolean bibsonomyTagsIgnoreNoTags) {
        this.bibsonomyTagsIgnoreNoTags.set(bibsonomyTagsIgnoreNoTags);
    }

    public int getBibsonomyTagCloudSize() {
        return bibsonomyTagCloudSize.get();
    }

    public IntegerProperty bibsonomyTagCloudSizeProperty() {
        return bibsonomyTagCloudSize;
    }

    public void setBibsonomyTagCloudSize(int bibsonomyTagCloudSize) {
        this.bibsonomyTagCloudSize.set(bibsonomyTagCloudSize);
    }

    public int getBibsonomyRequestSize() {
        return bibsonomyRequestSize.get();
    }

    public IntegerProperty bibsonomyRequestSizeProperty() {
        return bibsonomyRequestSize;
    }

    public void setBibsonomyRequestSize(int bibsonomyRequestSize) {
        this.bibsonomyRequestSize.set(bibsonomyRequestSize);
    }

    public GroupingEntity getBibsonomySidePaneVisibilityType() {
        return bibsonomySidePaneVisibilityType.get();
    }

    public ObjectProperty<GroupingEntity> bibsonomySidePaneVisibilityTypeProperty() {
        return bibsonomySidePaneVisibilityType;
    }

    public void setBibsonomySidePaneVisibilityType(GroupingEntity bibsonomySidePaneVisibilityType) {
        this.bibsonomySidePaneVisibilityType.set(bibsonomySidePaneVisibilityType);
    }

    public Order getBibsonomyTagCloudOrder() {
        return bibsonomyTagCloudOrder.get();
    }

    public ObjectProperty<Order> bibsonomyTagCloudOrderProperty() {
        return bibsonomyTagCloudOrder;
    }

    public void setBibsonomyTagCloudOrder(Order bibsonomyTagCloudOrder) {
        this.bibsonomyTagCloudOrder.set(bibsonomyTagCloudOrder);
    }
}
