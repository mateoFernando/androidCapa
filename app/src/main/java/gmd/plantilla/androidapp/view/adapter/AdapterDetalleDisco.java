package gmd.plantilla.androidapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.domain.model.Disc;
import gmd.plantilla.androidapp.service.dao.DiscDAO;
import gmd.plantilla.androidapp.service.dao.impl.DiscDAOImpl;
import gmd.plantilla.androidapp.view.activity.DetalleDiscoActivity;

//public class AdapterDetalleFavoritoDescuento extends RecyclerView.Adapter<AdapterDetalleFavoritoDescuento.listaDetalleDcto> {

public class AdapterDetalleDisco extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Disc> disc;
    Context context;
    String tipo="categoria";
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private boolean isLoadingAdded = false;

    //List<Disc> disc, ListaDetalleFavoritoDscto context, String tipo
    public AdapterDetalleDisco(Context context){
        this.disc = new ArrayList<Disc>();
        this.context = context;
        this.tipo = tipo;
    }


    public List<Disc> getDisc() {
        return disc;
    }

    public void setDisc(List<Disc> disc) {
        this.disc = disc;
    }

    //public AdapterDetalleFavoritoDescuento.listaDetalleDcto onCreateViewHolder(ViewGroup parent, int viewType) {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        /*View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_lista_detalle_disco,parent, false);
        AdapterDetalleFavoritoDescuento.listaDetalleDcto rcv = new AdapterDetalleFavoritoDescuento.listaDetalleDcto(view);
        */
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.design_lista_detalle_disco, parent, false);
        viewHolder = new listaDetalleDcto(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder2, int position) {

        //Disc movie = disc.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                listaDetalleDcto holder = (listaDetalleDcto) holder2;


                holder.itemView.setTag(String.valueOf(position));

                holder.nombreDisco.setText(disc.get(position).getName());
                Picasso.with(holder.itemView.getContext()).load(disc.get(position).getImage()).into(holder.photoDisco);
                holder.distancia.setText(disc.get(position).getDistance());
                //holder.horaDscto.setText(beneficiosListados.get(position).getHoraDesct());
                if(disc.get(position).getFavorite() == true) {

                    holder.favorito.setImageResource(R.drawable.ic_favorito);
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        holder.favorito.setColorFilter(context.getResources().getColor(R.color.red_tab_selected_icon));
                    }
                } else {

                    holder.favorito.setImageResource(R.drawable.ic_favorito_plomo);

                }
                break;
            case LOADING:
//                Do nothing
                break;
        }

    }

    /*@Override
    public int getItemCount() {
        return this.disc.size();
    }*/


    @Override
    public int getItemCount() {
        return disc == null ? 0 : disc.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == disc.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    public void add(Disc mc) {
        disc.add(mc);
        notifyItemInserted(disc.size() - 1);
    }

    public void addAll(List<Disc> mcList) {
        for (Disc mc : mcList) {
            add(mc);
        }
    }

    public void remove(Disc city) {
        int position = disc.indexOf(city);
        if (position > -1) {
            disc.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Disc());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = disc.size() - 1;
        Disc item = getItem(position);

        if (item != null) {
            disc.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Disc getItem(int position) {
        return disc.get(position);
    }


    public class listaDetalleDcto extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView photoDisco;
        public TextView nombreDisco;
        public TextView distancia;
        public ImageView favorito;

        DiscDAO discDao = new DiscDAOImpl();
        public listaDetalleDcto(View view) {

            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            photoDisco = (ImageView)view.findViewById(R.id.photoDisco);
            nombreDisco = (TextView)view.findViewById(R.id.nombreDisco);
            distancia = (TextView)view.findViewById(R.id.txtDistanciaDisco);
            favorito = (ImageView)view.findViewById(R.id.photoFavorito);

            //para ocultar precio
            if(tipo.equals("categoria")){
                favorito.setVisibility(View.GONE);
            }else{
                favorito.setVisibility(View.VISIBLE);
            }

            favorito.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    disc.get(getAdapterPosition()).setFavorite(!disc.get(getAdapterPosition()).getFavorite());
                    notifyDataSetChanged();
                }
            });

        }

        @Override
        public void onClick(View v) {
         //   int a=Integer.parseInt(v.getTag().toString());
            //Toast.makeText(v.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
//            CategoriasFragment fragment = new CategoriasFragment();
//            fragment.show(getSupportFragmentManager(),"");
            int a= Integer.parseInt(v.getTag().toString());
            int idDisc = disc.get(a).getId();
            discDao.insert(disc.get(a));
            Intent moreIntent=new Intent(context,DetalleDiscoActivity.class);
            Bundle args = new Bundle();
            args.putInt("idDisc",idDisc);
            moreIntent.putExtras(args);
            v.getContext().startActivity(moreIntent);

        }

    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }

}


