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
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.domain.model.Event;
import gmd.plantilla.androidapp.service.dao.EventDAO;
import gmd.plantilla.androidapp.service.dao.impl.EventDAOImpl;
import gmd.plantilla.androidapp.view.activity.DetalleEventoActivity;

//public class AdapterDetalleFavoritoDescuento extends RecyclerView.Adapter<AdapterDetalleFavoritoDescuento.listaDetalleDcto> {

public class AdapterDetalleEvento extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Event> event;
    Context context;
    String tipo="categoria";
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private boolean isLoadingAdded = false;
    private final android.text.format.DateFormat _sdfWatchTime = new android.text.format.DateFormat();

    //List<Disc> disc, ListaDetalleFavoritoDscto context, String tipo
    public AdapterDetalleEvento(Context context){
        this.event = new ArrayList<Event>();
        this.context = context;
        this.tipo = tipo;
    }


    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
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
        View v1 = inflater.inflate(R.layout.design_lista_detalle_evento, parent, false);
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

                holder.nombreDisco.setText(event.get(position).getName());
                Picasso.with(holder.itemView.getContext()).load(event.get(position).getImage()).into(holder.photoDisco);
                holder.distancia.setText(event.get(position).getType());
                Date date = new Date(Long.parseLong(event.get(position).getDate()));
                holder.fecha.setText(_sdfWatchTime.format("EEEE d MMMM, yyyy",date).toString());
                //holder.horaDscto.setText(beneficiosListados.get(position).getHoraDesct());
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
        return event == null ? 0 : event.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == event.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    public void add(Event mc) {
        event.add(mc);
        notifyItemInserted(event.size() - 1);
    }

    public void addAll(List<Event> mcList) {
        for (Event mc : mcList) {
            add(mc);
        }
    }

    public void remove(Event city) {
        int position = event.indexOf(city);
        if (position > -1) {
            event.remove(position);
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
        add(new Event());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = event.size() - 1;
        Event item = getItem(position);

        if (item != null) {
            event.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Event getItem(int position) {
        return event.get(position);
    }


    public class listaDetalleDcto extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView photoDisco;
        public TextView nombreDisco;
        public TextView distancia;
        public TextView fecha;
        public ImageView favorito;

        EventDAO eventDao = new EventDAOImpl();
        public listaDetalleDcto(View view) {

            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            photoDisco = (ImageView)view.findViewById(R.id.photoDisco);
            nombreDisco = (TextView)view.findViewById(R.id.nombreDisco);
            distancia = (TextView)view.findViewById(R.id.txtDistanciaDisco);
            favorito = (ImageView)view.findViewById(R.id.photoFavorito);
            fecha = (TextView)view.findViewById(R.id.txtfecha);

            //para ocultar precio
            if(tipo.equals("categoria")){
                favorito.setVisibility(View.GONE);
            }else{
                favorito.setVisibility(View.VISIBLE);
            }

            /*favorito.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    event.get(getAdapterPosition()).setFavorite(!event.get(getAdapterPosition()).getFavorite());
                    notifyDataSetChanged();
                }
            });*/

        }

        @Override
        public void onClick(View v) {
         //   int a=Integer.parseInt(v.getTag().toString());
            //Toast.makeText(v.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
//            CategoriasFragment fragment = new CategoriasFragment();
//            fragment.show(getSupportFragmentManager(),"");
            int a= Integer.parseInt(v.getTag().toString());
            int idEvent = event.get(a).getId();
            eventDao.insert(event.get(a));
            Intent moreIntent=new Intent(context,DetalleEventoActivity.class);
            Bundle args = new Bundle();
            args.putInt("idEvent",idEvent);
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


