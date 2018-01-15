package eamv.dmu17he.lancrewappprototype;
import com.microsoft.windowsazure.mobileservices.table.DateTimeOffset;
/**
 * Created by hkkrestlessPC on 13-01-2018.
 */

public class userAzureDBEntity
{
    @com.google.gson.annotations.SerializedName("userName")
    public String uName;

    @com.google.gson.annotations.SerializedName("userPassword")
    public String uPassword;

    @com.google.gson.annotations.SerializedName("id")
    private String mId;
    public String getId() { return mId; }
    public final void setId(String id) { mId = id; }

    @com.google.gson.annotations.SerializedName("complete")
    private boolean mComplete;
    public boolean isComplete() { return mComplete; }
    public void setComplete(boolean complete) { mComplete = complete; }

    @com.google.gson.annotations.SerializedName("text")
    private String mText;
    public String getText() { return mText; }
    public final void setText(String text) { mText = text; }

    @com.google.gson.annotations.SerializedName("version")
    private String mVersion;
    public String getVersion() { return mVersion; }
    public final void setVersion(String version) { mVersion = version; }
}
