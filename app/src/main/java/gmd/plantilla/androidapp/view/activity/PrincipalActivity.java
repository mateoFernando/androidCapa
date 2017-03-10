package gmd.plantilla.androidapp.view.activity;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.view.fragment.ListaDiscoFragment;
import gmd.plantilla.androidapp.view.fragment.ListaEventosFragment;
import gmd.plantilla.androidapp.view.fragment.MapsFragment;
import gmd.plantilla.androidapp.view.fragment.ProfileFragment;

public class PrincipalActivity extends AppCompatActivity {

    public SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    // private static final int[] ICONS = new int[]{R.drawable.ic_home, R.drawable.ic_favorito, R.drawable.ic_notificacion,R.drawable.ic_user};
    private static final int[] ICONS = new int[]{R.drawable.ic_home, R.drawable.ic_favorito, R.drawable.ic_notificacion,R.drawable.ic_user};
    private final int COUNT_OPTIONS_TOOLBAR= 4;
    TabLayout tabLayout;
    Context context;
    public String data;


    @Bind(R.id.icon_tab_options)
    ImageView taboptions;
    final List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        data = getIntent().getStringExtra("data");
        if(data==null)
            data="da";


        /*PRINCIPAL TAB*/

        fragments.add(ListaDiscoFragment.newInstance("1"));
        fragments.add(ListaEventosFragment.newInstance(""));
        fragments.add(MapsFragment.newInstance("1"));
        fragments.add(ProfileFragment.newInstance("1"));

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),fragments);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        /**Detectar tabs seleccionados*/
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                ((ImageView)view.findViewById(R.id.icon_tab_options)).setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                ((ImageView)view.findViewById(R.id.icon_tab_options)).setColorFilter(getResources().getColor(R.color.grayRatingBar), PorterDuff.Mode.SRC_IN);
                // tab.getIcon().setColorFilter(getResources().getColor(android.R.color.holo_red_dark), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        customIconTab();



        //ingrese cuando es version menor a 21
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            initTab();
        }


        ///cambios
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //if(position == 2){
                    Log.i("ingresa","posicion 2");
                    //Fragment fragm = fragments.get(position);
                   // Fragment fragm = TabsPushMensajeT.newInstance("1");
                   // mViewPager.setAdapter(mSectionsPagerAdapter);
                 //   mViewPager.getAdapter().get
                  //  fragments.add(TabsPushMensajeT.newInstance("1"));
                   // fragments.set(position, fragm);
                  //  mViewPager.getAdapter().notifyDataSetChanged();

                //}


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    void initTab(){
        View view = tabLayout.getTabAt(0).getCustomView();
        ((ImageView)view.findViewById(R.id.icon_tab_options)).setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
        //pintar deseleccionados
        View viewTab2 = tabLayout.getTabAt(1).getCustomView();
        ((ImageView)viewTab2.findViewById(R.id.icon_tab_options)).setColorFilter(getResources().getColor(R.color.ratingText), PorterDuff.Mode.SRC_IN);
        View viewTab3 = tabLayout.getTabAt(2).getCustomView();
        ((ImageView)viewTab3.findViewById(R.id.icon_tab_options)).setColorFilter(getResources().getColor(R.color.ratingText), PorterDuff.Mode.SRC_IN);
        View viewTab4 = tabLayout.getTabAt(3).getCustomView();
        ((ImageView)viewTab4.findViewById(R.id.icon_tab_options)).setColorFilter(getResources().getColor(R.color.ratingText), PorterDuff.Mode.SRC_IN);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void customIconTab(){
        for(int i = 0; i < COUNT_OPTIONS_TOOLBAR;i++){
            View view = getLayoutInflater().from(this).inflate(R.layout.tab_item_selected_toolbar, null, false);
            ImageView imageView = (ImageView)view.findViewById(R.id.icon_tab_options);

            imageView.setImageDrawable(getResources().getDrawable(ICONS[i]));
            //tabLayout.getTabAt(i).setIcon(getDrawable(ICONS[i]));
            tabLayout.getTabAt(i).setCustomView(view);
            //para setear iconos
            //  tabLayout.getTabAt(i).setIcon(getResources().getDrawable(ICONS[i]));
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_principal, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            ImageView taboptions = (ImageView) rootView.findViewById(R.id.icon_tab_options);
            taboptions.setBackgroundColor(getResources().getColor(R.color.white));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> fragments;

        public SectionsPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
                 return fragments.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return COUNT_OPTIONS_TOOLBAR;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            /*switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }*/
            return null;
        }
    }


}
