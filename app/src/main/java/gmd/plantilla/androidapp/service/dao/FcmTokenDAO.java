package gmd.plantilla.androidapp.service.dao;

import gmd.plantilla.androidapp.domain.model.FcmToken;

/**
 * Created by jmauriciog on 02/06/2016.
 */
public interface FcmTokenDAO {

    public FcmToken getDevice();

    public void updateTokenToExpired();

    public long insert(FcmToken model);


}
