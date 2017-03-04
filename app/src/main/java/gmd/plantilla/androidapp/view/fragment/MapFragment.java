package gmd.plantilla.androidapp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by juanmauricio on 3/03/17.
 */

public class MapFragment extends Fragment {

    public static MapFragment newInstance(String tipo) {
        Bundle args = new Bundle();

        args.putString("tipo", tipo);
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);


        return fragment;
    }
}
