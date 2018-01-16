package eamv.dmu17he.lancrewappprototype;

/**
 * Created by hkkrestlessPC on 16-01-2018.
 */

public class messageAzureDBEntity
{
    @com.google.gson.annotations.SerializedName("sentBy")
    public String sentByName;

    @com.google.gson.annotations.SerializedName("groupName")
    public String crewGroupType;

    @com.google.gson.annotations.SerializedName("message")
    public String theMessage;

    @com.google.gson.annotations.SerializedName("id")
    private String mId;
    public String getId() { return mId; }
    public final void setId(String id) { mId = id; }

    @com.google.gson.annotations.SerializedName("complete")
    private boolean mComplete;
    public boolean isComplete() { return mComplete; }
    public void setComplete(boolean complete) { mComplete = complete; }

    @com.google.gson.annotations.SerializedName("version")
    private String mVersion;
    public String getVersion() { return mVersion; }
    public final void setVersion(String version) { mVersion = version; }
}
