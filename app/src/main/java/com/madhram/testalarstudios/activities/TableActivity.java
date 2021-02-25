package com.madhram.testalarstudios.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.madhram.testalarstudios.R;
import com.madhram.testalarstudios.adapters.DataAdapter;
import com.madhram.testalarstudios.api.AlarstudiosApi;
import com.madhram.testalarstudios.model.Datum;
import com.madhram.testalarstudios.utils.App;
import com.madhram.testalarstudios.utils.ConstantManager;
import com.madhram.testalarstudios.utils.NetworkStatusChecker;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pub.devrel.easypermissions.EasyPermissions;

public class TableActivity extends BaseActivity implements DataAdapter.ItemClickListener, SwipeRefreshLayout.OnRefreshListener, EasyPermissions.PermissionCallbacks {

    @Inject
    AlarstudiosApi mApi;

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refresher)
    SwipeRefreshLayout mRefresher;

    private DataAdapter mDataAdapter = new DataAdapter(this);
    private String code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
        ButterKnife.bind(this);
        mRefresher.setOnRefreshListener(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            code = bundle.getString(ConstantManager.CODE);
        }
        onRefresh();

        askPermission();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_table;
    }

    private void setupRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        mRecycler.setAdapter(mDataAdapter);
    }

    @Override
    public void onItemClick(Datum model) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantManager.DATA, model);
        Intent intent = new Intent(this, CardActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        getData();
    }

    public void getData() {
        mDisposable = mApi.getData(code)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(data -> mRefresher.isRefreshing())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    if (data.getStatus().equals("ok")) {
                        mRefresher.setRefreshing(false);
                        mDataAdapter.addData(data.getData());
                        setupRecycler();
                    } else {
                        showMessage(R.string.table_toast_error_data);
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

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        askPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private void askPermission() {
        final String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
        } else {
            Handler h = new Handler(Looper.getMainLooper());
            h.postDelayed(() -> EasyPermissions.requestPermissions(this, getString(R.string.map_permission), 100, perms), 200);
        }
    }
}
