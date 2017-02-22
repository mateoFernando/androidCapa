package gmd.plantilla.androidapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.domain.model.Slide;
import gmd.plantilla.androidapp.util.AppPreferences;
import gmd.plantilla.androidapp.view.adapter.AdapterSlide;

import static gmd.plantilla.androidapp.util.Constants.GETSLIDE;


/**
 * Created by innovagmd on 30/11/16.
 */

public class SlideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {


    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.indicator)
    CirclePageIndicator indicator;
    @Bind(R.id.omitir)
    TextView omitir;
    @Bind(R.id.bt_back)
    ImageButton btBack;
    @Bind(R.id.bt_next)
    Button btNext;
    private int position = 0;
    private Context context;
    List<Slide> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        context = this;
        btBack.setVisibility(View.GONE);
        items= GETSLIDE();

        viewPager.setAdapter(new AdapterSlide(this,items));
        // mViewPager.addOnPageChangeListener(
        viewPager.addOnPageChangeListener(this);
        indicator.setViewPager(viewPager);
        saveFlagOnboarding();

    }

    @OnClick({R.id.omitir, R.id.bt_back, R.id.bt_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.omitir:
                goToHistoriasActivity();
                break;
            case R.id.bt_back:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                break;
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

        if (position == items.size()-1) {
            btNext.setText(getString(R.string.slide_finalizar));
            omitir.setVisibility(View.GONE);
        } else {
            btNext.setText(getString(R.string.siguiente));
            omitir.setVisibility(View.VISIBLE);
        }

        if (position == 0) {
            btBack.setVisibility(View.GONE);
        } else {
            btBack.setVisibility(View.VISIBLE);
        }
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
