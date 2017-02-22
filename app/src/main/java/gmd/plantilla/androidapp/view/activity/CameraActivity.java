package gmd.plantilla.androidapp.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.view.fragment.CameraFragment;

@SuppressWarnings("deprecation")
public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        addCameraFragment();
    }

    private void addCameraFragment(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Fragment cameraFragment = new CameraFragment();

        FragmentManager FM = getSupportFragmentManager();

        FragmentTransaction FT = FM.beginTransaction();
        FT.replace(R.id.camera_content, cameraFragment, CameraFragment.TAG);
        FT.commit();
    }
}
