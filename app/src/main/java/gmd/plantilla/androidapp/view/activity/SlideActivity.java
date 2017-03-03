package gmd.plantilla.androidapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.domain.model.Slide;
import gmd.plantilla.androidapp.util.AppPreferences;
import gmd.plantilla.androidapp.view.adapter.SlideAdapter;

import static gmd.plantilla.androidapp.util.Constants.GETSLIDE;


/**
 * Created by innovagmd on 30/11/16.
 */

public class SlideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {


    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.bt_next)
    Button btNext;
    private int position = 0;
    private Context context;
    List<Slide> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        context = this;
        items= GETSLIDE();
        viewPager.setAdapter(new SlideAdapter(this,items));
        viewPager.addOnPageChangeListener(this);
        saveFlagOnboarding();

    }

    @OnClick({R.id.bt_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_next:
                if (position == items.size()-1) {
                    goToHistoriasActivity();
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void saveFlagOnboarding() {
        AppPreferences.getInstance(context).savePreference(AppPreferences.FLAG_ONBOARDING,true);
    }

    private void goToHistoriasActivity() {
        Intent i = new Intent(SlideActivity.this , LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        // disable
    }

}
