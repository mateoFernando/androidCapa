package gmd.plantilla.androidapp.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.domain.model.Event;
import gmd.plantilla.androidapp.service.dao.EventDAO;
import gmd.plantilla.androidapp.service.dao.impl.EventDAOImpl;

public class DetalleEventoActivity extends BaseActivity {

    @Bind(R.id.close)
    ImageButton close;

    @Bind(R.id.txtDireccion)
    TextView txtDireccion;
    @Bind(R.id.imageViewEmpresa)
    ImageView imageViewEmpresa;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    /*AdapterGalery galleryAdapter;
    AdapterServicio servicioAdapter;*/
    RecyclerView.LayoutManager manager;
    GridLayoutManager lLayout;
    Activity activity;
    Context ctx;
    int categoria = 0;
    int idProveedorSelect;
    double longitud= 0.0;
    double latitud = 0.0;
    String iconMap = "";

    EventDAO eventDao;
    Toolbar toolbar;


    @Bind(R.id.app_bar)
    AppBarLayout mAppBarLayout;

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;

    @Bind(R.id.toolbar_subtitle)
    TextView toolbar_subtitle;

    @Bind(R.id.txtdescripcion)
    TextView txtdescripcion;

    private final android.text.format.DateFormat _sdfWatchTime = new android.text.format.DateFormat();

    java.text.SimpleDateFormat sdf =
            new java.text.SimpleDateFormat("EEEE d MMMM, yyyy");
    String codigoId;
    Event event;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);
        ButterKnife.bind(this);
        eventDao = new EventDAOImpl();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.blue_toolbar));
            getWindow().setStatusBarColor(getResources().getColor(R.color.blue_toolbar));
        }

        idProveedorSelect = getIntent().getExtras().getInt("idEvent");
        //Toast.makeText(getApplicationContext(),""+idProveedorSelect, Toast.LENGTH_LONG).show();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ctx = this;
        activity = this;

        //Traer datos para Adapter
        Log.e("xds", "sdsds");
        codigoId = getIntent().getExtras().getString("CodigoIdCodigdoId");
        // Log.e("codigoDetalleContrato",codigoId);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        event = eventDao.getCurrentEvent();


        /*
        *INICIO Servicio
        */
        /*DetalleProveedorService detalleProveedorService = new DetalleProveedorServiceImpl();
        int beneficio       = 0 ;
        int local           = 0 ;

        beneficio           = idProveedorSelect;
        local               = 1;

        detalleProveedorService.getDetalleProveedor(ctx ,beneficio, local);*/

        /*
        *FIN Servicio
        */
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            float per = Math.abs(mAppBarLayout.getY()) / mAppBarLayout.getTotalScrollRange();
            boolean setExpanded = (per <= 0.5F);
            mAppBarLayout.setExpanded(setExpanded, true);
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onResume() {
        super.onResume();


        toolbar.setTitle(event.getName());
        toolbar.setSubtitle(event.getDistrict());
        getSupportActionBar().setSubtitle(event.getDistrict());
        getSupportActionBar().setTitle(event.getName());
        setTitle(event.getName());

        Date date = new Date(Long.parseLong(event.getDate()));
        txtDireccion.setText(_sdfWatchTime.format("EEEE d MMMM, yyyy",date).toString());
        txtdescripcion.setText(event.getDescription());
            //appCompatRatingBar.setRating(listResultado.get(0).getAppCompatRatingBarDcto());
            //txtRatingText.setText(String.valueOf(listResultado.get(0).getRatingText()));
            //txtTelProveedor.setText(listResultado.get(0).getTelefonos());
            /*txtWeb.setText(listResultado.get(0).getUrlWeb());
            txtDescripcion.setText(listResultado.get(0).getDescripcion());
            txtReservaLug.setText(listResultado.get(0).getReservas());
            txtDireccion.setText(listResultado.get(0).getDireccion());
            txtTarjetaCredito.setText(listResultado.get(0).getTarjetasCredito());
            txtWifi.setText(listResultado.get(0).getWifi());
            txtEstacinamiento.setText(listResultado.get(0).getEstacionamiento());*/

            Picasso.with(getApplicationContext()).load(event.getImage()).into(imageViewEmpresa);
            /*longitud= Double.parseDouble(listResultado.get(0).getLongitud());
            latitud = Double.parseDouble(listResultado.get(0).getLatitud());*/


        //Llamar a mis elementos d la lista
        /*ElementosPruebaRepositorio objElemPru = new ElementosPruebaRepositorio();
        List<String> lista = objElemPru.listaGaleria(categoriaProv);

        galleryAdapter = new AdapterGalery(lista, this);
        listaGaleria.setAdapter(galleryAdapter);*/





        /* categoria*/
/*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle data=new Bundle();
                data.putString("data","data");
                callActivity(PrincipalMapaActivity.class.getName(), data);

//                Intent intent = new Intent(v.getContext(), PrincipalMapaActivity.class);
//                intent.putExtra("data", "data");
//                startActivity(intent);

            }
        });


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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void DetalleCorrecto(List<Event> response) {
        LogUtil.i(response.toString());
        //Mostrar datos

        this.setTitle(response.getName());
        //appCompatRatingBar.setRating(Float.valueOf(String.valueOf(response.getPuntBeneficio())));
        txtDescripcion.setText(response.getDistance());
        //txtRatingText.setText(String.valueOf(response.getPuntBeneficio()).toString());
        txtTelProveedor.setText(response.getLocal().getNumTelefono().toString());
        txtWeb.setText(response.getUrlProveedor().toString());
        txtDireccion.setText(response.getLocal().getDirLocal().toString());
        txtApertura.setText(getInAbierto(response.getLocal().getInAbierto().toString()));
        Picasso.with(getApplicationContext()).load(response.getFonBeneficio()).into(imageViewEmpresa);
        latitud = Double.parseDouble(response.getLocal().getNumLatitud().toString());
        longitud= Double.parseDouble(response.getLocal().getNumLongitud().toString());
        iconMap = response.getEje().getIconEje().toString();
        categoria = response.getEje().getIdEje();

        //listaGaleria.setHasFixedSize(true);
        manager = new GridLayoutManager(this, 3);
        listaGaleria.setLayoutManager(manager);

        lLayout = new GridLayoutManager(this, 1);
        listaServicio.setLayoutManager(lLayout);


        //Llamar a mis elementos d la lista Galery
        List<ImagenBean> listImagenes = new ArrayList<>();

        int i=0;
        for(ImagenBean lstResultado : response.getImagenes()){
            int id = response.getImagenes().get(i).getIdImagen();
            String url = response.getImagenes().get(i).getUrlImagen();
            String urlPreview = response.getImagenes().get(i).getUrlImagenPrevia();
            if(id!=0 && !url.isEmpty() && !urlPreview.isEmpty()){
                listImagenes.add(new ImagenBean(id,url.toString(),urlPreview.toString())  );
            }
            i++;
        }

        //Llamar a mis elementos d la lista Servicio
        List<ServicioBean> listServicios = new ArrayList<>();

        i=0;
        for(ServicioBean lstResultado : response.getLocal().getServicios()){
            int id = response.getLocal().getServicios().get(i).getIdServicio();
            String nombre = response.getLocal().getServicios().get(i).getNomServicio();
            String activo = response.getLocal().getServicios().get(i).getInActivo();
            if(id!=0 && !nombre.isEmpty() && !activo.isEmpty()){
                listServicios.add(new ServicioBean(id,nombre.toString(),activo.toString())  );
            }
            i++;
        }

        galleryAdapter = new AdapterGalery(listImagenes, ctx);
        listaGaleria.setAdapter(galleryAdapter);
        servicioAdapter = new AdapterServicio(listServicios, ctx);
        listaServicio.setAdapter(servicioAdapter);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        /* categoria*/

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle data=new Bundle();
                data.putString("data","data");
                callActivity(PrincipalActivity.class.getName(), data);

//                Intent intent = new Intent(v.getContext(), PrincipalMapaActivity.class);
//                intent.putExtra("data", "data");
//                startActivity(intent);

            }
        });



    }

    public String getInAbierto(String inAbierto) {

        if(inAbierto.toString().equals("S")){
            return"ABIERTO";
        }
        else{
            return"CERRADO";
        }
    }


    /*//////////////////////////////////////////////////////////////////*/







    public void llamar(View view) {

        //String llamarMesaAyuda = txtTelProveedor.getText().toString().trim();
        //Log.v("oe", llamarMesaAyuda);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE}, 144);
        }else{
            //startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + llamarMesaAyuda)));
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==144){
            //llamar(llamarproveedor);
        }
    }
}
