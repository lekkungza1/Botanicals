
package mainairai.rhere.com.liveat500px.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TreeItemDao implements Parcelable {



    @SerializedName("tree_id")
    @Expose
    private String treeId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("local_name")
    @Expose
    private String localName;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("benefit")
    @Expose
    private String benefit;
    @SerializedName("locations")
    @Expose
    private List<Location> locations = null;
    @SerializedName("scientific_name")
    @Expose
    private String scientificName;
    @SerializedName("family_name")
    @Expose
    private String familyName;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("gallery")
    @Expose
    private List<String> gallery = null;

    public TreeItemDao(){

    }

    protected TreeItemDao(Parcel in) {
        treeId = in.readString();
        name = in.readString();
        localName = in.readString();
        detail = in.readString();
        benefit = in.readString();
        locations = in.createTypedArrayList(Location.CREATOR);
        scientificName = in.readString();
        familyName = in.readString();
        thumbnail = in.readString();
        gallery = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(treeId);
        dest.writeString(name);
        dest.writeString(localName);
        dest.writeString(detail);
        dest.writeString(benefit);
        dest.writeTypedList(locations);
        dest.writeString(scientificName);
        dest.writeString(familyName);
        dest.writeString(thumbnail);
        dest.writeStringList(gallery);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TreeItemDao> CREATOR = new Creator<TreeItemDao>() {
        @Override
        public TreeItemDao createFromParcel(Parcel in) {
            return new TreeItemDao(in);
        }

        @Override
        public TreeItemDao[] newArray(int size) {
            return new TreeItemDao[size];
        }
    };

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<String> getGallery() {
        return gallery;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }

}
