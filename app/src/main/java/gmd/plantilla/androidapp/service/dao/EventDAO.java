package gmd.plantilla.androidapp.service.dao;

import gmd.plantilla.androidapp.domain.model.Disc;
import gmd.plantilla.androidapp.domain.model.Event;

/**
 * Created by jmauriciog on 01/06/2016.
 */
public interface EventDAO {

    public long insert(Event model);

    public Event getCurrentEvent();

}
