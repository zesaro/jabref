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
    private final StringProperty bibsonomyVisibilty;
    private final StringProperty bibsonomySidepaneVisibilityName;
    private final BooleanProperty bibsonomyDocumentsImport;
    private final BooleanProperty bibsonomyDocumentsExport;
    private final BooleanProperty bibsonomySaveapikey;
    private final BooleanProperty bibsonomyTagsRefreshonstartup;
    private final BooleanProperty bibsonomyRequestSizeIgnorewarning;
    private final BooleanProperty bibsonomyTagsIgnorenotags;
    private final IntegerProperty bibsonomyTagcloudSize;
    private final IntegerProperty bibsonomyRequestSize;
    private final ObjectProperty<GroupingEntity> bibsonomySidepaneVisibilityType;
    private final ObjectProperty<Order> bibsonomyTagcloudOrder;

    public BibsonomyPreferences(String apiUrl, String apiUsername, String apiKey, String bibsonomySidepaneVisibilityName, String bibsonomyVisibilty, GroupingEntity bibsonomySidepaneVisibilityType, Order bibsonomyTagcloudOrder, boolean bibsonomyDocumentsImport, boolean bibsonomyDocumentsExport, boolean bibsonomySaveapikey, boolean bibsonomyTagsRefreshonstartup, boolean bibsonomyRequestSizeIgnorewarning, boolean bibsonomyTagsIgnorenotags, int bibsonomyTagcloudSize, int bibsonomyRequestSize) {
        this.apiUrl = new SimpleStringProperty(apiUrl);
        this.apiUsername = new SimpleStringProperty(apiUsername);
        this.apiKey = new SimpleStringProperty(apiKey);

        this.bibsonomyVisibilty = new SimpleStringProperty(bibsonomyVisibilty);
        this.bibsonomyTagcloudOrder = new SimpleObjectProperty<Order>(bibsonomyTagcloudOrder);
        this.bibsonomySidepaneVisibilityType = new SimpleObjectProperty<GroupingEntity>(bibsonomySidepaneVisibilityType);

        this.bibsonomyDocumentsImport = new SimpleBooleanProperty(bibsonomyDocumentsImport);
        this.bibsonomyDocumentsExport = new SimpleBooleanProperty(bibsonomyDocumentsExport);
        this.bibsonomySaveapikey = new SimpleBooleanProperty(bibsonomySaveapikey);
        this.bibsonomyTagsRefreshonstartup = new SimpleBooleanProperty(bibsonomyTagsRefreshonstartup);
        this.bibsonomyRequestSizeIgnorewarning = new SimpleBooleanProperty(bibsonomyRequestSizeIgnorewarning);
        this.bibsonomySidepaneVisibilityName = new SimpleStringProperty(bibsonomySidepaneVisibilityName);
        this.bibsonomyTagsIgnorenotags = new SimpleBooleanProperty(bibsonomyTagsIgnorenotags);

        this.bibsonomyRequestSize = new SimpleIntegerProperty(bibsonomyRequestSize);
        this.bibsonomyTagcloudSize = new SimpleIntegerProperty(bibsonomyTagcloudSize);
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

    public String getBibsonomyVisibilty() {
        return bibsonomyVisibilty.get();
    }

    public StringProperty bibsonomyVisibiltyProperty() {
        return bibsonomyVisibilty;
    }

    public void setBibsonomyVisibilty(String bibsonomyVisibilty) {
        this.bibsonomyVisibilty.set(bibsonomyVisibilty);
    }

    public String getBibsonomySidepaneVisibilityName() {
        return bibsonomySidepaneVisibilityName.get();
    }

    public StringProperty bibsonomySidepaneVisibilityNameProperty() {
        return bibsonomySidepaneVisibilityName;
    }

    public void setBibsonomySidepaneVisibilityName(String bibsonomySidepaneVisibilityName) {
        this.bibsonomySidepaneVisibilityName.set(bibsonomySidepaneVisibilityName);
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

    public boolean isBibsonomySaveapikey() {
        return bibsonomySaveapikey.get();
    }

    public BooleanProperty bibsonomySaveapikeyProperty() {
        return bibsonomySaveapikey;
    }

    public void setBibsonomySaveapikey(boolean bibsonomySaveapikey) {
        this.bibsonomySaveapikey.set(bibsonomySaveapikey);
    }

    public boolean isBibsonomyTagsRefreshonstartup() {
        return bibsonomyTagsRefreshonstartup.get();
    }

    public BooleanProperty bibsonomyTagsRefreshonstartupProperty() {
        return bibsonomyTagsRefreshonstartup;
    }

    public void setBibsonomyTagsRefreshonstartup(boolean bibsonomyTagsRefreshonstartup) {
        this.bibsonomyTagsRefreshonstartup.set(bibsonomyTagsRefreshonstartup);
    }

    public boolean isBibsonomyRequestSizeIgnorewarning() {
        return bibsonomyRequestSizeIgnorewarning.get();
    }

    public BooleanProperty bibsonomyRequestSizeIgnorewarningProperty() {
        return bibsonomyRequestSizeIgnorewarning;
    }

    public void setBibsonomyRequestSizeIgnorewarning(boolean bibsonomyRequestSizeIgnorewarning) {
        this.bibsonomyRequestSizeIgnorewarning.set(bibsonomyRequestSizeIgnorewarning);
    }

    public boolean isBibsonomyTagsIgnorenotags() {
        return bibsonomyTagsIgnorenotags.get();
    }

    public BooleanProperty bibsonomyTagsIgnorenotagsProperty() {
        return bibsonomyTagsIgnorenotags;
    }

    public void setBibsonomyTagsIgnorenotags(boolean bibsonomyTagsIgnorenotags) {
        this.bibsonomyTagsIgnorenotags.set(bibsonomyTagsIgnorenotags);
    }

    public int getBibsonomyTagcloudSize() {
        return bibsonomyTagcloudSize.get();
    }

    public IntegerProperty bibsonomyTagcloudSizeProperty() {
        return bibsonomyTagcloudSize;
    }

    public void setBibsonomyTagcloudSize(int bibsonomyTagcloudSize) {
        this.bibsonomyTagcloudSize.set(bibsonomyTagcloudSize);
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

    public GroupingEntity getBibsonomySidepaneVisibilityType() {
        return bibsonomySidepaneVisibilityType.get();
    }

    public ObjectProperty<GroupingEntity> bibsonomySidepaneVisibilityTypeProperty() {
        return bibsonomySidepaneVisibilityType;
    }

    public void setBibsonomySidepaneVisibilityType(GroupingEntity bibsonomySidepaneVisibilityType) {
        this.bibsonomySidepaneVisibilityType.set(bibsonomySidepaneVisibilityType);
    }

    public Order getBibsonomyTagcloudOrder() {
        return bibsonomyTagcloudOrder.get();
    }

    public ObjectProperty<Order> bibsonomyTagcloudOrderProperty() {
        return bibsonomyTagcloudOrder;
    }

    public void setBibsonomyTagcloudOrder(Order bibsonomyTagcloudOrder) {
        this.bibsonomyTagcloudOrder.set(bibsonomyTagcloudOrder);
    }
}
