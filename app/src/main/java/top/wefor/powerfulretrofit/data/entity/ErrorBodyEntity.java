package top.wefor.powerfulretrofit.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created on 16/8/4.
 *
 * @author ice
 */
public class ErrorBodyEntity implements Parcelable {
    private String message;
    private String documentationUrl;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocumentationUrl() {
        return documentationUrl;
    }

    public void setDocumentationUrl(String documentationUrl) {
        this.documentationUrl = documentationUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeString(this.documentationUrl);
    }

    public ErrorBodyEntity() {
    }

    protected ErrorBodyEntity(Parcel in) {
        this.message = in.readString();
        this.documentationUrl = in.readString();
    }

    public static final Creator<ErrorBodyEntity> CREATOR = new Creator<ErrorBodyEntity>() {
        @Override
        public ErrorBodyEntity createFromParcel(Parcel source) {
            return new ErrorBodyEntity(source);
        }

        @Override
        public ErrorBodyEntity[] newArray(int size) {
            return new ErrorBodyEntity[size];
        }
    };
}
