package gmd.plantilla.androidapp.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.domain.model.Slide;



/**
 * Created by innovagmd on 30/11/16.
 */

public class SlideAdapter extends PagerAdapter {

    private Context mContext;
    private List<Slide> slides;
    public SlideAdapter(Context context, List<Slide> slides) {
        mContext = context;
        this.slides=slides;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        Slide slideActual = slides.get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.slide, collection, false);

        ImageView imgView= (ImageView) layout.findViewById(R.id.img_slide);
        imgView.setImageResource(slideActual.getImagen());

        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return slides.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}