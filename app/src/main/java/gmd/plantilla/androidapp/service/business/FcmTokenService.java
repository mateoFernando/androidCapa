package gmd.plantilla.androidapp.service.business;

import gmd.plantilla.androidapp.domain.model.FcmToken;

/**
 * Created by jmauriciog on 02/06/2016.
 */
public interface FcmTokenService {

    public long setDevice(FcmToken device);
    public FcmToken getDevice();
    public void updateTokenToExpired();
    public void sendRegistrationToServer(final String token, String userId);
}
