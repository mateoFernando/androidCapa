package gmd.plantilla.androidapp.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.domain.dto.CTermCondDTO;
import gmd.plantilla.androidapp.domain.ro.response.LoginResponse;
import gmd.plantilla.androidapp.service.business.UserService;
import gmd.plantilla.androidapp.service.business.impl.UserServiceImpl;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.input_iniciar_sesion)
    EditText inputIniciarSesion;
    @Bind(R.id.input_contrasenia)
    EditText inputContrasenia;
    @Bind(R.id.layout_contrasenia)
    TextInputLayout layoutContrasenia;
    @Bind(R.id.terminos_y_condiciones)
    AppCompatCheckBox terminosYCondiciones;
    @Bind(R.id.txt_check_box)
    TextView txtCheckBox;
    @Bind(R.id.txt_recuperar_clave)
    TextView txtRecuperarClave;
    @Bind(R.id.btn_ingreso)
    AppCompatButton btnIngreso;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.input_iniciar_sesion, R.id.input_contrasenia, R.id.terminos_y_condiciones, R.id.txt_check_box, R.id.txt_recuperar_clave, R.id.btn_ingreso})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.input_iniciar_sesion:


                break;
            case R.id.input_contrasenia:
                break;
            case R.id.terminos_y_condiciones:

                break;
            case R.id.txt_check_box:
                callActivity(TerminosYCondiciones.class.getName());
                break;
            case R.id.txt_recuperar_clave:
                //event textRecuperar clave
                break;
            case R.id.btn_ingreso:

                if(validar()){
                    UserService loginService=new UserServiceImpl();
                    String usuario="beto@gmd.com.pe";
                    String contrasenna="123456";
                    loginService.login(context,usuario,contrasenna);
                }
                break;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    @Override
    protected void onDestroy() {
     super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void LoginCorrecto(LoginResponse response) {
          callActivity(MainActivity.class.getName());
    }

    private boolean validar() {
        //validar
        if(inputIniciarSesion.getText().length()<=0){
            YoYo.with(Techniques.Tada)
                    .duration(700)
                    .playOn(findViewById(R.id.input_iniciar_sesion));
            inputIniciarSesion.requestFocus();
            return false;
        }
        if(inputContrasenia.getText().length()<=0){
            YoYo.with(Techniques.Tada)
                   .duration(700)
                    .playOn(findViewById(R.id.input_contrasenia));
            inputContrasenia.requestFocus();
            return false;
        }

        return true;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.BACKGROUND)
    public void ChecKTerminosCondiciones(CTermCondDTO response) {
      //  callActivity(MainActivity.class.getName());
        terminosYCondiciones.setChecked(response.getChecked());
    }

}
