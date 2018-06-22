package al.ib.lawyer.model.calimmodel;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("result")
    private String result;

    @SerializedName("Claims")
    private List<ClaimsItem> claims;

    @SerializedName("TotalRecord")
    private int totalRecord;

    @SerializedName("details")
    private String details;

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setClaims(List<ClaimsItem> claims) {
        this.claims = claims;
    }

    public List<ClaimsItem> getClaims() {
        return claims;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return
                "Result{" +
                        "result = '" + result + '\'' +
                        ",claims = '" + claims + '\'' +
                        ",totalRecord = '" + totalRecord + '\'' +
                        ",details = '" + details + '\'' +
                        "}";
    }
}