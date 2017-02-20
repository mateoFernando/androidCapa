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

public class AdapterSlide extends PagerAdapter {

    private Context mContext;
    private List<Slide> slides;
    public AdapterSlide(Context context,List<Slide> slides) {
        mContext = context;
        this.slides=slides;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
       // Slides modelObject = Slides.values()[position];
        Slide slideActual = slides.get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.slide, collection, false);


        TextView textoTitulo= (TextView) layout.findViewById(R.id.title_slide);
        TextView textoDesc= (TextView) layout.findViewById(R.id.descripcion_slide);
        ImageView imgView= (ImageView) layout.findViewById(R.id.img_slide);
        textoTitulo.setText(slideActual.getTitulo());
        textoDesc.setText(slideActual.getDescripcion());
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

    @Override
    public CharSequence getPageTitle(int position) {
       // Slides customPagerEnum = slides.get(position).getTitulo();
        return mContext.getString(slides.get(position).getTitulo());
    }
}