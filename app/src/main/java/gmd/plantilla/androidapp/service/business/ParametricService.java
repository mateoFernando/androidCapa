package gmd.plantilla.androidapp.service.business;

import android.content.Context;

/**
 * Created by jmauriciog on 02/06/2016.
 */
public interface ParametricService {
    public boolean isEmpty();
    public void updateParametricFromWebService(Context ctx);
    public void getParametricFromWebService(Context ctx, String callback);

}
