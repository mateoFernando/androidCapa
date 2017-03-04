package gmd.plantilla.androidapp.domain.ro.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import gmd.plantilla.androidapp.domain.model.Disc;

/**
 * Created by jmauriciog on 30/12/2016.
 */

public class DiscResponse {


    public int offset;
    public String nextPage;

    public int totalObjects;

    @SerializedName("data")
    public List<Disc> result;

    public List<Disc> getResult() {
        return result;
    }

    public void setResult(List<Disc> result) {
        this.result = result;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalObjects() {
        return totalObjects;
    }

    public void setTotalObjects(int totalObjects) {
        this.totalObjects = totalObjects;
    }

}
