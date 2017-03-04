package gmd.plantilla.androidapp.domain.ro.response;

import java.util.List;
import gmd.plantilla.androidapp.domain.model.Event;

/**
 * Created by jmauriciog on 30/12/2016.
 */

public class EventResponse {


    public int offset;
    public String nextPage;

    public int totalObjects;

    public List<Event> data;

    public List<Event> getResult() {
        return data;
    }

    public void setResult(List<Event> result) {
        this.data = result;
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
