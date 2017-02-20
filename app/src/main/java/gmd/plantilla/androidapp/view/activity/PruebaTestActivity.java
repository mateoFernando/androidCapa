package gmd.plantilla.androidapp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gmd.plantilla.androidapp.R;

public class PruebaTestActivity extends AppCompatActivity {


    @Bind(R.id.txt_num_1)
    EditText txtNum1;
    @Bind(R.id.txt_num_2)
    EditText txtNum2;
    @Bind(R.id.btn_sumar)
    Button btnSumar;
    @Bind(R.id.txt_salida)
    TextView txtSalida;
    @Bind(R.id.activity_prueba_test)
    LinearLayout activityPruebaTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_test);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.btn_sumar)
    public void onClick() {
        Integer num1=Integer.parseInt(txtNum1.getText().toString());
        Integer num2=Integer.parseInt(txtNum2.getText().toString());
        txtSalida.setText(String.valueOf((num1+num2)));
    }
}
