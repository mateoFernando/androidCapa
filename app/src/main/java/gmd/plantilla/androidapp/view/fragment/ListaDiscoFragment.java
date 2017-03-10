package gmd.plantilla.androidapp.view.fragment;

import android.content.Context;
import android.graphics.Rect;
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
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.domain.model.Disc;
import gmd.plantilla.androidapp.service.business.DiscsService;
import gmd.plantilla.androidapp.service.business.impl.DiscsImpl;
import gmd.plantilla.androidapp.util.DividerItemDecoration;
import gmd.plantilla.androidapp.util.PaginationScrollListener;
import gmd.plantilla.androidapp.view.adapter.AdapterDetalleDisco;

public class ListaDiscoFragment extends Fragment {

    private static String tipoC;

    @Bind(R.id.lista_categoria)
    RecyclerView recyclerView;
    @Bind(R.id.filtroCategoria)
    ImageView filtroCategoria;
    @Bind(R.id.totalCantidades)
    TextView totalCantidades;
    @Bind(R.id.refreshDisc)
    SwipeRefreshLayout refreshDisc;
    private RefreshCall mRefreshCall;
    GridLayoutManager lLayout;
    Context contexr;

    public interface RefreshCall {

        boolean onMethodRefreshCall();
    }

    DiscsService
            bandejaService = new DiscsImpl();


    public static ListaDiscoFragment newInstance(String tipo) {
        Bundle args = new Bundle();

        args.putString("tipo", tipo);
        ListaDiscoFragment fragment = new ListaDiscoFragment();
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

    @Bind(R.id.main_progress)
    ProgressBar progressBar;

    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 2;
    private int currentPage = PAGE_START;
    private static final String TAG = "ListaDetalleFavoritoDscto";
    AdapterDetalleDisco rcAdapter;

    public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private final int verticalSpaceHeight;

        public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
            this.verticalSpaceHeight = verticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.bottom = verticalSpaceHeight;
            outRect.right = verticalSpaceHeight;
            outRect.left = verticalSpaceHeight;
            outRect.top = verticalSpaceHeight;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_detalle_discos, container, false);
        ButterKnife.bind(this, view);
        allItems = new ArrayList<>();
        tipoC = getArguments().getString("tipo");
        bandejaService.LoadDiscsLista(contexr,0,null,0);




        linearLayoutManager= new GridLayoutManager(contexr,2);
        recyclerView.setLayoutManager(linearLayoutManager);


        //add ItemDecoration
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(15));
        //or
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        //or
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider));

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
        rcAdapter = new AdapterDetalleDisco(getActivity());
        recyclerView.setAdapter(rcAdapter);
        filtroCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*CategoriasFavoritoFragment fragment = new CategoriasFavoritoFragment(new CategoriasFragmentDismmiss());
                fragment.show(getFragmentManager(),"");*/
            }
        });



        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 4;


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
                        bandejaService.LoadDiscsLista(contexr, 0, (idcategoria==0)?null:idcategoria, currentPage);

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




        refreshDisc = (SwipeRefreshLayout) view.findViewById(R.id.refreshDisc);
        refreshDisc.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        refreshDisc.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                llamarServicioListaSolicitudes();
            }
        });

        return view;
    }

    void llamarServicioListaSolicitudes() {
        refreshDisc.setRefreshing(false);
        idcategoria=0;
        allItems = new ArrayList<>();
        currentPage = 0;
        isLastPage = false;
        isLoading = false;

        rcAdapter.clear();
        rcAdapter.notifyDataSetChanged();
        bandejaService.LoadDiscsLista(contexr,0,null,0);

    }

    List<Disc> allItems;

    LinearLayoutManager linearLayoutManager;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void BeneficiosCorrecto(List<Disc> response) {

        //isLoading = true;
        for (Disc beneficio: response) {
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

            bandejaService.LoadDiscsLista(contexr,0,data[0],0);
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
