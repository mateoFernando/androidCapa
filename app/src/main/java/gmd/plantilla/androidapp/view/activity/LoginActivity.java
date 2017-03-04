package gmd.plantilla.androidapp.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.method.PasswordTransformationMethod;
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
import gmd.plantilla.androidapp.domain.ro.response.LoginResponse;
import gmd.plantilla.androidapp.service.business.UserService;
import gmd.plantilla.androidapp.service.business.impl.UserServiceImpl;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.input_iniciar_sesion)
    EditText inputIniciarSesion;
    @Bind(R.id.input_contrasenia)
    EditText inputContrasenia;

    @Bind(R.id.txt_recuperar_clave)
    TextView txtRecuperarClave;
    @Bind(R.id.btn_ingreso)
    AppCompatButton btnIngreso;
    Context context;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        context = this;
        inputContrasenia.setTypeface(Typeface.DEFAULT);
        inputContrasenia.setTransformationMethod(new PasswordTransformationMethod());

    }

    @OnClick({R.id.input_iniciar_sesion, R.id.input_contrasenia, R.id.txt_recuperar_clave, R.id.btn_ingreso})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.input_iniciar_sesion:
                break;
            case R.id.input_contrasenia:
                break;
            case R.id.txt_recuperar_clave:
                break;
            case R.id.btn_ingreso:

                if(validar()){
                    dialog = ProgressDialog.show(context, getResources().getString(R.string.app_name),
                            "Iniciando sesión", true);
                    UserService loginService=new UserServiceImpl();
                    String usuario=inputIniciarSesion.getText().toString();
                    String contrasenna=inputContrasenia.getText().toString();
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

        if(dialog!=null)
            dialog.dismiss();

        Intent i = new Intent(LoginActivity.this , PrincipalActivity.class);
        startActivity(i);
        finish();

    }

    private boolean validar() {
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


}
