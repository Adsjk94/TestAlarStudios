package com.madhram.testalarstudios.activities;

import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.madhram.testalarstudios.R;
import com.madhram.testalarstudios.api.AlarstudiosApi;
import com.madhram.testalarstudios.utils.App;
import com.madhram.testalarstudios.utils.ConstantManager;
import com.madhram.testalarstudios.utils.NetworkStatusChecker;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.login)
    AppCompatEditText mLogin;
    @BindView(R.id.password)
    AppCompatEditText mPassword;

    @Inject
    AlarstudiosApi mApi;

    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    private boolean checkInputField() {
        if (!TextUtils.isEmpty(mLogin.getText()) && !TextUtils.isEmpty(mPassword.getText())) {
            return true;
        } else {
            showMessage(R.string.login_form_toast_empty_fields);
            return false;
        }
    }

    @OnClick(R.id.enter_btn)
    public void enterClick() {
        if (checkInputField()) {
            mDisposable = mApi.authentification(mLogin.getText().toString(), mPassword.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(authModel -> {
                        if (authModel.getStatus().equals("error")) {
                            showMessage(R.string.login_form_toast_error_fields);
                        } else {
                            code = authModel.getCode();
                            Intent intent = new Intent(this, TableActivity.class);
                            intent.putExtra(ConstantManager.CODE, code);
                            startActivity(intent);
                        }
                    }, error -> {
                        if (!NetworkStatusChecker.isNetworkAviable(getApplicationContext())) {
                            showMessage(R.string.network_error);
                        } else {
                            error.printStackTrace();
                            showMessage(R.string.login_form_toast_error_text);
                            showMessage(error.getMessage());
                        }
                    });
        }
    }


}