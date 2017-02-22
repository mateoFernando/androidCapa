package gmd.plantilla.androidapp.view.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.remote.LocationService;
import pe.com.gmd.ao.innova.androidLib.HandlerUtils;
import pe.com.gmd.ao.innova.androidLib.ImageUtils;
import pe.com.gmd.ao.innova.androidLib.LogUtil;
import pe.com.gmd.ao.innova.androidLib.SpannableStringUtils;
import pe.com.gmd.ao.innova.androidLib.ToastUtils;


public class MainActivity extends AppCompatActivity implements HandlerUtils.OnReceiveMessageListener {
    private LocationService mLocationService;

    @Bind(R.id.tx_n_registros)
    TextView txNRegistros;
    private HandlerUtils.HandlerHolder handlerHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        new Thread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showShortToastSafe("show_short_toast_safe");
            }
        }).start();


        handlerHolder = new HandlerUtils.HandlerHolder(this);
        //ImageUtils
        Bitmap src = ImageUtils.getBitmap(getResources(), R.drawable.ic_arrow_back);
        //ivGray.setImageBitmap(ImageUtils.toGray(src));


        bindService(new Intent(this, LocationService.class), conn, Context.BIND_AUTO_CREATE);


    }

    @Override
    public void handlerMessage(Message message) {

       //EJECUTAR DESPUES DE 3 SEGUNDOS

    }





    private void TEXTO(){
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                ToastUtils.showShortToast("GMD GLARAB");
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };
        txNRegistros.setText(SpannableStringUtils
                .getBuilder("hola\n")
                .setBold().setForegroundColor(Color.YELLOW).setBackgroundColor(Color.GRAY).setAlign(Layout.Alignment.ALIGN_CENTER)
                .append("DFDSF1")
                .append("DSFDSF2").setForegroundColor(Color.GREEN)
                .append("DSFDSF3\n").setBackgroundColor(Color.RED)
                .append("DSFDSF4\n").setLeadingMargin(30, 50)
                .append("DSFDSF5\n").setQuoteColor(Color.YELLOW)
                .append("SDFDSF6\n").setBullet(30, Color.YELLOW)
                .append("DSFDSF7")
                .append("DSFDSF8\n").setProportion(2)
                .append("SDFDSFDS9")
                .append("DSFDSFDSF00\n").setXProportion(2)
                .append("SDFDSFDSFDSF00")
                .append("SDFDSFDSFDS00").setStrikethrough()
                .append("SDFDSFDSF00\n").setUnderline()
                .append("DSFDSF00")
                .append("DFDSkj,mn,mnfds00").setSuperscript()
                .append("dsfdsfdsf00\n").setSubscript()
                .append("dsfsdfdsfhh")
                .append("gfdgfd").setBold()
                .append("gdfgfdg").setItalic()
                .append("dsfdsfdsfdfg\n").setBoldItalic()
                .append("dsfdsfdsfmonospace font\n").setFontFamily("monospace")
                .append("serif font\n").setFontFamily("serif")
                .append("sans-serif font\n").setFontFamily("sans-serif")
                .append("fdgfdg\n").setAlign(Layout.Alignment.ALIGN_NORMAL)
                .append("dsfdsfdfg\n").setAlign(Layout.Alignment.ALIGN_CENTER)
                .append("dsfdsfdfg\n").setAlign(Layout.Alignment.ALIGN_OPPOSITE)
                .append("dsfsdfdsdfg")
                .append("dsfdsfdsfdfg\n").setResourceId(R.mipmap.ic_launcher)
                .append("dfg")
                .append("dgf\n").setClickSpan(clickableSpan)
                .append("dfg")
                .append("URL\n").setUrl("https://github.com/Blankj/AndroidUtilCode")
                .append("fdASDSADSADg")
                .append("65DSFFDSF465\n").setBlur(3, BlurMaskFilter.Blur.NORMAL)
                .create()
        );
    }



/**
 *
 public Fragment rootFragment;
 rootFragment = FragmentUtils.addFragment(getSupportFragmentManager(), Demo0Fragment.newInstance(), R.id.fragment_container);
 }


 @Override
 public void onBackPressed() {
 if (!FragmentUtils.dispatchBackPress(getSupportFragmentManager())) {
 super.onBackPressed();
 }
 }
 */
@Override
protected void onDestroy() {
    unbindService(conn);
    super.onDestroy();
}

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mLocationService = ((LocationService.LocationBinder) service).getService();
            mLocationService.setOnGetLocationListener(new LocationService.OnGetLocationListener() {
                @Override
                public void getLocation(final String lastLatitude, final String lastLongitude, final String latitude, final String longitude, final String country, final String locality, final String street) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            LogUtil.i("lastLatitude: " + lastLatitude +
                                    "\nlastLongitude: " + lastLongitude +
                                    "\nlatitude: " + latitude +
                                    "\nlongitude: " + longitude +
                                    "\ngetCountryName: " + country +
                                    "\ngetLocality: " + locality +
                                    "\ngetStreet: " + street
                            );
                        }
                    });
                }
            });
        }
    };
}

