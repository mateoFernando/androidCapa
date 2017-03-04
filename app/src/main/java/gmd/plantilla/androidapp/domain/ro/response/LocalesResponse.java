package gmd.plantilla.androidapp.domain.ro.response;

import java.util.List;

import gmd.plantilla.androidapp.domain.model.Disc;
import gmd.plantilla.androidapp.domain.model.Local;

/**
 * Created by juanmauricio on 4/03/17.
 */

public class LocalesResponse {

    private int offset;
    private String nextPage;

    private int totalObjects;

    private List<Local> data;


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

    public List<Local> getData() {
        return data;
    }

    public void setData(List<Local> data) {
        this.data = data;
    }
}
