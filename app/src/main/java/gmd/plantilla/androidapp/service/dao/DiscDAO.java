package gmd.plantilla.androidapp.service.dao;

import gmd.plantilla.androidapp.domain.model.Disc;

/**
 * Created by jmauriciog on 01/06/2016.
 */
public interface DiscDAO {

    public long insert(Disc model);

    public Disc getCurrentDisc();

}
