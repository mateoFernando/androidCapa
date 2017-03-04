package gmd.plantilla.androidapp.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.domain.model.User;
import gmd.plantilla.androidapp.service.business.UserService;
import gmd.plantilla.androidapp.service.business.impl.UserServiceImpl;


/**
 * Created by innovagmd on 25/01/17.
 */

public class BaseActivity extends AppCompatActivity {
    BaseActivity activity;
    //UserService userService;
    //User user;
    /*EmpresaService empresaService;
    Empresa empresa;*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        //userService=new UserServiceImpl();
        //user = userService.getCurrentUser();
    }

    //public UserService getUserService() {
        //return userService;
    //}

    //public void setUserService(UserService userService) {
        //this.userService = userService;
    //}

//    public User getUser() {
//        return user;
//    }



    protected void callActivity(final String classCalled) {
        Bundle bundle = new Bundle();
        callActivity(classCalled, bundle);
    }

    public void callActivity(final String classCalled, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClassName(this, classCalled);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void callActivity(final String classCalled, Bundle bundle,
                             final int requestCode) {
        Intent intent = new Intent();
        intent.setClassName(this, classCalled);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    public void toastShow(int resId) {
        Toast.makeText(activity, resId, Toast.LENGTH_SHORT).show();
    }

    public void toastShow(String resId) {
        Toast.makeText(activity, resId, Toast.LENGTH_SHORT).show();
    }

    public ProgressDialog progressDialog;

    public ProgressDialog showProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(getString(R.string.mensaje_progress_dialog));
        progressDialog.show();
        return progressDialog;
    }

    public ProgressDialog showProgressDialog(CharSequence message) {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            progressDialog.dismiss();
        }
    }


    public  void getUsuarioActivo(){

    }

    public User getIdUsuarioActivo(){
       UserService userService=new UserServiceImpl();
       return userService.getCurrentUser();


    }
    public void _reportError(Exception e) {
        StackTraceElement objTraceElement = e.getStackTrace()[0];


        String strUserId = "CODIGO_USUARIO";
        String strCellId = "LOAD";//AppPreferences.getInstance(this)._loadCellId();
        String strErrorMessage = e.toString();

        int intErrorLine = objTraceElement.getLineNumber();
        String strErrorProcedure = objTraceElement.getMethodName();
        long lngDate = System.currentTimeMillis();
        int intLogId = (strUserId + strCellId + strErrorProcedure + String.valueOf(intErrorLine) + strErrorMessage).hashCode();

        /* objLog = new pe.beyond.gls.model.Log(intLogId,
                strUserId, strCellId, strErrorMessage, intErrorLine,
                strErrorProcedure, lngDate);
        ContentValues values = LogDAO.createContentValues(objLog);
        this.getContentResolver().insert(LogDAO.INSERT_URI, values);*/

    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }



}
