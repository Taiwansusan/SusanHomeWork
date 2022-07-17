package com.example.susanhomework;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.susanhomework.interfaces.ApiInterface;
import com.example.susanhomework.mvp.model.UserDataDetail;
import com.example.susanhomework.mvp.presenter.DetailPresenter;
import com.example.susanhomework.mvp.presenter.MvpPresenter;
import com.example.susanhomework.mvp.view.DetailView;
import com.example.susanhomework.mvp.view.MvpView;
import com.example.susanhomework.ui.LogoView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements DetailView {

    private ProgressDialog progressDialog;
    private UserDataDetail mUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //初始化進度條
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在載入中...");

        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getString("userName") != null) {
            String mUserName = getIntent().getExtras().getString("userName");
            DetailPresenter presenter = new DetailPresenter(this);
            presenter.getData(mUserName);
        }
    }

    @Override
    public void showLoading() {
        if (!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void showData(UserDataDetail data) {
        mUserData = data;
        setView();
    }

    private void setView() {
        ImageView imgClose = findViewById(R.id.img_close);
        LogoView userFace = findViewById(R.id.img_face);
        TextView userName = findViewById(R.id.user_name);
        TextView userBio = findViewById(R.id.user_bio);
        TextView userLogin = findViewById(R.id.user_login);
        TextView userAdmin = findViewById(R.id.user_staff);
        TextView userLocation = findViewById(R.id.user_location);
        TextView userBlog = findViewById(R.id.user_blog);

        imgClose.setOnClickListener(v -> finish());
        userFace.setImage(mUserData.getAvatar_url(), LogoView.CIRCLEIMAGE);
        userName.setText(mUserData.getName());
        userBio.setText(mUserData.getBio());
        userLogin.setText(mUserData.getLogin());
        userAdmin.setText(mUserData.getType());

        userLocation.setText(mUserData.getLocation());
        userBlog.setText(mUserData.getBlog());
        userBlog.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(mUserData.getBlog()));
            startActivity(intent);
        });
    }
}
