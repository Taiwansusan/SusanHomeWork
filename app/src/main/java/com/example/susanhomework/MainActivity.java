package com.example.susanhomework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.susanhomework.adapter.UserAdapter;
import com.example.susanhomework.mvp.model.UserData;
import com.example.susanhomework.mvp.presenter.MvpPresenter;
import com.example.susanhomework.mvp.view.MvpView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MvpView {

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private TextView textView;
    private ArrayList<UserData> mUserDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        //初始化進度條
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在載入中...");
        MvpPresenter presenter = new MvpPresenter(this);
        presenter.getData("normal");
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
    public void showData(ArrayList<UserData> data) {
        textView.setVisibility(View.GONE);
        mUserDataList = data;
        setRecyclerView(mUserDataList);
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        textView.setText(msg);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "請求異常", Toast.LENGTH_SHORT).show();
        textView.setText("請求異常");
    }

    private void setRecyclerView(ArrayList<UserData> list) {
        UserAdapter userAdapter = new UserAdapter(this, list, this);
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public void ItemListener(int pos) {
        Intent intent = new Intent();
        intent.setClass(this,DetailActivity.class);
        intent.putExtra("userName",mUserDataList.get(pos).getLogin());
        startActivity(intent);
    }
}