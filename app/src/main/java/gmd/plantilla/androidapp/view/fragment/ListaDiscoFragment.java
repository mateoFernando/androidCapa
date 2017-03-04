package gmd.plantilla.androidapp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gmdinnovacion.beneficiosgmd.disfruta.R;
import com.gmdinnovacion.beneficiosgmd.disfruta.domain.model.BeneficioLista;
import com.gmdinnovacion.beneficiosgmd.disfruta.domain.ro.response.BeneficioListaResponse;
import com.gmdinnovacion.beneficiosgmd.disfruta.services.business.BandejaBeneficioFavoritoListaService;
import com.gmdinnovacion.beneficiosgmd.disfruta.services.business.BandejaBeneficioListaService;
import com.gmdinnovacion.beneficiosgmd.disfruta.services.business.impl.BandejaBeneficioListaImpl;
import com.gmdinnovacion.beneficiosgmd.disfruta.services.business.impl.BandejaBeneficiosFavoritoListadoImpl;
import com.gmdinnovacion.beneficiosgmd.disfruta.services.dao.UserDAO;
import com.gmdinnovacion.beneficiosgmd.disfruta.services.dao.impl.UserDAOImpl;
import com.gmdinnovacion.beneficiosgmd.disfruta.utiles.EndlessRecyclerOnScrollListener;
import com.gmdinnovacion.beneficiosgmd.disfruta.utiles.PaginationScrollListener;
import com.gmdinnovacion.beneficiosgmd.disfruta.view.adapter.AdapterDetalleDescuento;
import com.gmdinnovacion.beneficiosgmd.disfruta.view.adapter.AdapterDetalleFavoritoDescuento;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaDiscoFragment extends Fragment {

    private static String tipoC;

    @BindView(R.id.lista_categoria)
    RecyclerView recyclerView;
    @BindView(R.id.filtroCategoria)
    ImageView filtroCategoria;
    @BindView(R.id.totalCantidades)
    TextView totalCantidades;
    @BindView(R.id.refreshBeneficioLista)
    SwipeRefreshLayout refreshBeneficioLista;
    private RefreshCall mRefreshCall;
    GridLayoutManager lLayout;
    Context contexr;

    public interface RefreshCall {

        boolean onMethodRefreshCall();
    }

    BandejaBeneficioFavoritoListaService
            bandejaService = new BandejaBeneficiosFavoritoListadoImpl();

    UserDAO userDAO = new UserDAOImpl();

    public static ListaDetalleFavoritoDscto newInstance(String tipo) {
        Bundle args = new Bundle();

        args.putString("tipo", tipo);
        ListaDetalleFavoritoDscto fragment = new ListaDetalleFavoritoDscto();
        fragment.setArguments(args);


        return fragment;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contexr=getActivity();
        //EventBus.getDefault().register(this);

    }
    public ListaDiscoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onPause() {
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @BindView(R.id.main_progress)
    ProgressBar progressBar;

    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 2;
    private int currentPage = PAGE_START;
    private static final String TAG = "ListaDetalleFavoritoDscto";
    AdapterDetalleFavoritoDescuento rcAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_detalle_dscto_rv, container, false);
        ButterKnife.bind(this, view);
        allItems = new ArrayList<>();
        tipoC = getArguments().getString("tipo");
        bandejaService.CargarBerneficiosLista(contexr,userDAO.getCurrentUser().getIdUsuario(),null,0);





        linearLayoutManager= new LinearLayoutManager(contexr);
        recyclerView.setLayoutManager(linearLayoutManager);


        /*recyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                // do something...

            //if(current_page>1) {
                progressBar.setVisibility(View.VISIBLE);
                rcAdapter.addLoadingFooter();
                bandejaService.CargarBerneficiosLista(contexr, userDAO.getCurrentUser().getIdUsuario(), idcategoria, current_page);
            //}

            }
        });*/

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //AdapterDetalleFavoritoDescuento rcAdapter = new AdapterDetalleFavoritoDescuento(allItems, this, tipoC);
        rcAdapter = new AdapterDetalleFavoritoDescuento(getActivity());
        recyclerView.setAdapter(rcAdapter);
        filtroCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoriasFavoritoFragment fragment = new CategoriasFavoritoFragment(new CategoriasFragmentDismmiss());
                fragment.show(getFragmentManager(),"");
            }
        });



        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //progressBar.setVisibility(View.VISIBLE);

                        /*if (currentPage != TOTAL_PAGES) rcAdapter.addLoadingFooter();
                        else {
                            isLastPage = true;
                            rcAdapter.removeLoadingFooter();
                            isLoading = false;
                        }*/
                        bandejaService.CargarBerneficiosLista(contexr, userDAO.getCurrentUser().getIdUsuario(), (idcategoria==0)?null:idcategoria, currentPage);

                    }
                }, 1000);

            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });




        refreshBeneficioLista = (SwipeRefreshLayout) view.findViewById(R.id.refreshBeneficioLista);
        refreshBeneficioLista.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        refreshBeneficioLista.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                llamarServicioListaSolicitudes();
            }
        });

        return view;
    }

    void llamarServicioListaSolicitudes() {
        refreshBeneficioLista.setRefreshing(false);
        idcategoria=0;
        allItems = new ArrayList<>();
        currentPage = 0;
        isLastPage = false;
        isLoading = false;

        rcAdapter.clear();
        rcAdapter.notifyDataSetChanged();
        bandejaService.CargarBerneficiosLista(contexr,userDAO.getCurrentUser().getIdUsuario(),null,0);

    }

    List<BeneficioLista> allItems;

    LinearLayoutManager linearLayoutManager;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void BeneficiosCorrecto(List<BeneficioLista> response) {

        //isLoading = true;
        for (BeneficioLista beneficio: response) {
            //allItems.add(new DetalleDescuentoRepositorio(beneficio.getIdBeneficio(),beneficio.getImgBeneficio(),beneficio.getNomBeneficio(),beneficio.getNomEje(),(beneficio.getIdFavorito()==1)?true:false,Float.parseFloat(""+beneficio.getPuntBeneficio()),""+beneficio.getPuntBeneficio(),((beneficio.getInAbierto().equals("S"))?"ABIERTO":"")+((beneficio.getInAbierto().equals("N"))?"CERRADO":""),"a - "+beneficio.getNumDistancia()+"km "+beneficio.getNomDistrito(),"",""+beneficio.getPorcDescuento()+"%",beneficio.getIdEje()));
            allItems.add(beneficio);
        }

        totalCantidades.setText(String.valueOf(allItems.size()));


        if(response.size()!=0) {
            //progressBar.setVisibility(View.GONE);
            rcAdapter.clear();
            rcAdapter.addAll(allItems);
            //rcAdapter.addLoadingFooter();
            rcAdapter.notifyDataSetChanged();
            //if (currentPage <= TOTAL_PAGES)
            // rcAdapter.addLoadingFooter();
            //else
            if (currentPage == 0) {
                rcAdapter.addLoadingFooter();
            }
            //recyclerView.removeOnScrollListener(null);
            //isLastPage = true;
        }else{

            //recyclerView.clearOnScrollListeners();
            isLastPage = true;
            rcAdapter.removeLoadingFooter();
            isLoading = false;
        }

                //if (currentPage == 0) {

                //isLastPage = true;
                //rcAdapter.removeLoadingFooterx();
        /*if (currentPage == 0) {
                if (currentPage <= TOTAL_PAGES) rcAdapter.addLoadingFooter();
                else isLastPage = true;
        }else{
            //rcAdapter.addLoadingFooter();
        }*/
    }


    int idcategoria,numpag=0;
    //boolean isLoading = false,isLastPage=false;

    private class CategoriasFragmentDismmiss extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int[] data = (int[]) msg.obj; // If object is of String
            idcategoria=data[0];
            allItems = new ArrayList<>();
            currentPage = 0;
            isLastPage = false;
            isLoading = false;
            rcAdapter.clear();
            rcAdapter.notifyDataSetChanged();

            bandejaService.CargarBerneficiosLista(contexr,userDAO.getCurrentUser().getIdUsuario(),data[0],0);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        //EventBus.getDefault().register(this);
    }



}
