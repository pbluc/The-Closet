package columbia.pbluc.thecloset.models;

import android.net.Uri;

import java.io.Serializable;

public class ClosetItem implements Serializable {
    private String documentId;
    private String imageFilename;
    private Uri imageUri;
    private String closetCategory;
    private boolean isSelected;

    public ClosetItem(String documentId, String imageFilename, String closetCategory, Uri imageUri) {
        this.documentId = documentId;
        this.imageFilename = imageFilename;
        this.imageUri = imageUri;
        this.closetCategory = closetCategory;
        this.isSelected = false;
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getImageFilename() {
        return imageFilename;
    }

    public String getClosetCategory() {
        return closetCategory;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setClosetCategory(String closetCategory) {
        this.closetCategory = closetCategory;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
