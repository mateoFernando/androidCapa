package gmd.plantilla.androidapp.view;


import gmd.plantilla.androidapp.domain.model.UserEntity;

/**
 * Created by emedinaa on 11/12/15.
 */
public interface LoadView extends BaseView{

    void showLoading();
    void hideLoading();
    void showConnectionErrorMessage();
    void showError(String message);
    void onLoadSuccess(UserEntity userEntity);
}
