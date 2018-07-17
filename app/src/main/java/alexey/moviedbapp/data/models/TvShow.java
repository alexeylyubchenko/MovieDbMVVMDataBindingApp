package alexey.moviedbapp.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import alexey.moviedbapp.utils.Constants;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Alexey Lyubchenko.
 */

public class TvShow implements Parcelable {

    @Getter @Setter
    @SerializedName(Constants.API_JSON_PARAM_RESULTS_ID)
    long id;

    @Getter @Setter
    @SerializedName(Constants.API_JSON_PARAM_RESULTS_NAME)
    String name;

    @Getter @Setter
    @SerializedName(Constants.API_JSON_PARAM_RESULTS_VOTE_AVG)
    float voteAvg;

    @Getter @Setter
    @SerializedName(Constants.API_JSON_PARAM_RESULTS_OVERVIEW)
    String overview;

    @Getter @Setter
    @SerializedName(Constants.API_JSON_PARAM_RESULTS_BACKDROP)
    String backdropImgUrl;

    @Getter @Setter
    @SerializedName(Constants.API_JSON_PARAM_RESULTS_POSTER)
    String posterImgUrl;

    public TvShow(long id, String name, float voteAvg, String overview, String posterImgUrl) {
        this.id = id;
        this.name = name;
        this.voteAvg = voteAvg;
        this.overview = overview;
        this.posterImgUrl = posterImgUrl;
    }

    protected TvShow(Parcel in) {
        id = in.readLong();
        name = in.readString();
        voteAvg = in.readFloat();
        overview = in.readString();
        backdropImgUrl = in.readString();
        posterImgUrl = in.readString();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeFloat(voteAvg);
        dest.writeString(overview);
        dest.writeString(backdropImgUrl);
        dest.writeString(posterImgUrl);
    }
}
