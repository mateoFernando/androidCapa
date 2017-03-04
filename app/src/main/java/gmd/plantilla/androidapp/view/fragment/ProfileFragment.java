package gmd.plantilla.androidapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.domain.model.User;
import gmd.plantilla.androidapp.service.business.UserService;
import gmd.plantilla.androidapp.service.business.impl.UserServiceImpl;
import gmd.plantilla.androidapp.util.DividerItemDecoration;
import gmd.plantilla.androidapp.util.PaginationScrollListener;
import gmd.plantilla.androidapp.view.activity.LoginActivity;
import gmd.plantilla.androidapp.view.activity.MainActivity;
import gmd.plantilla.androidapp.view.activity.PrincipalActivity;
import gmd.plantilla.androidapp.view.activity.SplashActivity;
import gmd.plantilla.androidapp.view.adapter.AdapterDetalleDisco;
import android.view.View.OnClickListener;

/**
 * Created by fernando on 3/4/17.
 */

public class ProfileFragment extends Fragment implements OnClickListener  {


    private TextView textoNombre;
    TextView cerrarSesion;

    UserService userInfo;
    User usuario;


    public static ProfileFragment newInstance(String tipo) {
        Bundle args = new Bundle();

        args.putString("tipo", tipo);
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userInfo = new UserServiceImpl();
        usuario = userInfo.getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        textoNombre = (TextView)view.findViewById(R.id.user_profile_name);

        textoNombre.setText(usuario.getCodigoUsuarioSesion());
        cerrarSesion = (TextView)view.findViewById(R.id.TextView03);
        cerrarSesion.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {

        Intent i = new Intent(getActivity() , LoginActivity.class);
        startActivity(i);
        getActivity().finish();

    }
}
