package gmd.plantilla.androidapp.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.domain.DTO.CTermCondDTO;


public class TerminosYCondiciones extends AppCompatActivity {

    @Bind(R.id.email_aceptar_in_button)
    Button aceptar;

    @Bind(R.id.checkBox)
    AppCompatCheckBox checbox;
    @Bind(R.id.boton_toolbar_cerrar)
    ImageButton botonToolbarCerrar;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminos_y_condiciones);

        ButterKnife.bind(this);
        getSupportActionBar().hide();

        context=this;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        aceptar.setEnabled(false);
        aceptar.setBackgroundColor(Color.GRAY);

        checbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checbox.isChecked()) {
                    aceptar.setEnabled(true);
                    aceptar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
                    //aceptar.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.selector_boton_inferior));
                } else {
                    aceptar.setEnabled(false);
                    aceptar.setBackgroundColor(Color.GRAY);
                }
            }
        });

        botonToolbarCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checbox.setChecked(false);
                goToBack();
            }
        });
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goToBack();
            }
        });
    }

    private void goToBack() {
    //   /*Intent intent = new Intent();
        CTermCondDTO CTermCondBUS =new CTermCondDTO();
        if(checbox.isChecked()){
            CTermCondBUS.setChecked(true);
            EventBus.getDefault().postSticky(CTermCondBUS);
        }else{
            CTermCondBUS.setChecked(false);
            EventBus.getDefault().postSticky(CTermCondBUS);
        }
    finish();
    }

    @Override
    public void onBackPressed() {

        checbox.setChecked(false);
        goToBack();
    }
}
