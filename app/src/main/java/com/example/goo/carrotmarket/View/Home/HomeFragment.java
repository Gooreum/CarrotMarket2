package com.example.goo.carrotmarket.View.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.GlobalBus.Events;
import com.example.goo.carrotmarket.Util.GlobalBus.GlobalBus;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Detail.DetailActivity;
import com.example.goo.carrotmarket.View.Home.Filter.HomeManagerActivity;
import com.example.goo.carrotmarket.View.Home.Search.SearchActivity;
import com.example.goo.carrotmarket.View.MyProfile.SetMyLocation.SetMyLocationActivity;
import com.squareup.otto.Subscribe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-04-24.
 */

public class HomeFragment extends Fragment implements HomeView {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.spinner)
    Spinner spinner;

    ArrayAdapter<String> spinnerAdapter;
    List<String> userList;

    HomePresenter presenter;
    HomeAdapter adapter;
    HomeAdapter.ItemClickListener itemClickListener;

    List<Product> product;

    SessionManager sessionManager;
    HashMap<String, String> user;
    String nick, city, gu, dong, dong2;

    private Parcelable recyclerViewState;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);
        GlobalBus.getBus().register(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //로그인 세션
        sessionManager = new SessionManager(getContext());
        user = sessionManager.getUserDetail();
        product = new ArrayList<>();
        //스피너 값 설정
        setSpinner();

        //Toast.makeText(getContext(), user.get(sessionManager.PROFILEIMAGE).toString(), Toast.LENGTH_SHORT).show();
        //툴바
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        setHasOptionsMenu(true);

        //프레젠터
        presenter = new HomePresenter(this, getContext());

        //로그인 상태일 때의 상품 판매 목록 불러오기
        if (sessionManager.isLoggIn() == true) {

            nick = user.get(sessionManager.NICK).toString();
            if (user.get(sessionManager.LOCATION1_STATE.toString()).equals("1")) {
                city = user.get(sessionManager.CITY).toString();
                gu = user.get(sessionManager.GU).toString();
                dong = user.get(sessionManager.DONG).toString();
                // presenter.getProducts(nick, city, gu, dong);
            } else if (user.get(sessionManager.LOCATION2_STATE.toString()).equals("1")) {
                city = user.get(sessionManager.CITY2).toString();
                gu = user.get(sessionManager.GU2).toString();
                dong = user.get(sessionManager.DONG2).toString();
                //presenter.getProducts(nick, city, gu, dong);
            }


            //새로고침
            swipe_refresh.setOnRefreshListener(
                    () -> presenter.getProducts(nick, city, gu, dong)
            );
            //비회원 상태일 때의 상품 판매 목록 불러오기
        } else {
            presenter.getProducts();

            //새로고침
            swipe_refresh.setOnRefreshListener(
                    () -> presenter.getProducts()
            );
        }


        //리사이클러뷰 아이템 클릭 리스너
        itemClickListener = ((view1, position) -> {
            recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
            String id = String.valueOf(product.get(position).getId());
            String seller = product.get(position).getSeller();
            int hide = product.get(position).getHide();
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("seller", seller);
            intent.putExtra("hide", hide);
            intent.putExtra("position", position);
            intent.putExtra("fragment", "home");
            getContext().startActivity(intent);
            Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();

        });

        return view;
    }

    //필터화면에서 다시 돌아왔을 떄 새로고침 해주기
    @Subscribe
    public void BackToHomeFromFilter(Events.BackToHomeFromFilter backToHome) {
        presenter.getProducts(nick, city, gu, dong);
/*        if (sessionManager.isLoggIn() == true) {

            nick = user.get(sessionManager.NICK).toString();
            if (user.get(sessionManager.LOCATION1_STATE.toString()).equals("1")) {
                city = user.get(sessionManager.CITY).toString();
                gu = user.get(sessionManager.GU).toString();
                dong = user.get(sessionManager.DONG).toString();
                presenter.getProducts(nick, city, gu, dong);
            } else if (user.get(sessionManager.LOCATION2_STATE.toString()).equals("1")) {
                city = user.get(sessionManager.CITY2).toString();
                gu = user.get(sessionManager.GU2).toString();
                dong = user.get(sessionManager.DONG2).toString();
                presenter.getProducts(nick, city, gu, dong);
            }
        }*/
    }

    //내 지역설정 화면에서 다시 돌아왔을 떄 새로고침 해주기
    @Subscribe
    public void BackToHomeFromSetMyLocation(Events.BackToHomeFromSetMyLocation backToHome) {
        Toast.makeText(getContext(), "내 지역설정 화면에서 왔습니다.", Toast.LENGTH_SHORT).show();
        System.out.println("로케이션1 : " + user.get(sessionManager.LOCATION1_STATE).toString());
        System.out.println("로케이션2 : " + user.get(sessionManager.LOCATION2_STATE).toString());
        setSpinner();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("온 리쥼 : ", "온 리쥼");
        if (sessionManager.isLoggIn() == true) {
            presenter.getProductsFromDetail(nick, city, gu, dong);

        } else {
            presenter.getProductsFromDetailNotLogin();

        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        GlobalBus.getBus().unregister(this);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.appbar_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                return true;
            case R.id.search:
                presenter.nextActivityWithoutLogin(SearchActivity.class);

                return true;

            case R.id.filter:
                presenter.nextActivityIsLogin(getContext(), HomeManagerActivity.class);

                return true;

            case R.id.alarm:


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void setSpinner() {

        //스피너 위치 툴바보다 아래에 잡아주기
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            ListPopupWindow window = (ListPopupWindow) popup.get(spinner);
            window.setHeight(700); //pixel


        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sessionManager.isLoggIn() == true) {


            userList = new ArrayList<>();
            if (user.get(sessionManager.LOCATION1_STATE).equals("1")) {
                dong = user.get(sessionManager.DONG).toString();
                if (!user.get(sessionManager.DONG2).toString().equals("empty")) {

                    dong2 = user.get(sessionManager.DONG2).toString();
                    userList.add(dong);
                    userList.add(dong2);
                    userList.add("내 동네 설정");
                } else {
                    userList.add(dong);
                    userList.add("내 동네 설정");
                }
            } else if (user.get(sessionManager.LOCATION2_STATE).equals("1")) {
                dong2 = user.get(sessionManager.DONG2).toString();
                if (!user.get(sessionManager.DONG).toString().equals("empty")) {
                    dong = user.get(sessionManager.DONG).toString();

                    userList.add(dong2);
                    userList.add(dong);
                    userList.add("내 동네 설정");
                } else {
                    userList.add(dong2);
                    userList.add("내 동네 설정");
                }
            }


            spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, userList);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    //Toast.makeText(getContext(), userList.get(i).toString(), Toast.LENGTH_SHORT).show();
                    if (userList.get(i).equals("내 동네 설정")) {
                        moveActivity(SetMyLocationActivity.class);
                    } else {
                        dong = user.get(sessionManager.DONG).toString();
                        dong2 = user.get(sessionManager.DONG2).toString();
                        if (userList.get(i).equals(dong)) {
                            city = user.get(sessionManager.CITY).toString();
                            gu = user.get(sessionManager.GU).toString();
                            presenter.getProductsFromSpinner1(nick, city, gu, dong);

                        } else if (userList.get(i).equals(dong2)) {
                            city = user.get(sessionManager.CITY2).toString();
                            gu = user.get(sessionManager.GU2).toString();
                            presenter.getProductsFromSpinner2(nick, city, gu, dong2);

                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        } else {
            userList = new ArrayList<>();
            dong = user.get(sessionManager.DONG).toString();
            userList.add(dong);
            userList.add("내 동네 설정");

            spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, userList);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // Toast.makeText(getContext(), userList.get(i).toString(), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    @Override
    public void showProgress() {
        swipe_refresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipe_refresh.setRefreshing(false);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(List<Product> products) {
        //리사이클러뷰 메니저

        adapter = new HomeAdapter(getContext(), products, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        product = products;
    }

    @Override
    public void onGetRefreshResult(List<Product> products) {
        product = products;
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        adapter = new HomeAdapter(getContext(), products, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void snackBar(String dong) {
        //Snackbar.make(getView(), dong + "으로 지역이 변경되었습니다.", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void moveActivity(Class activity) {
        Intent intent = new Intent(getActivity(), activity);
        getActivity().startActivity(intent);

    }

    public void onGetResultFromSpinner1(List<Product> products) {
        //리사이클러뷰 메니저
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeAdapter(getContext(), products, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        product = products;

        sessionManager.updateLocation1(city, gu, dong, "1", "0");
        // Toast.makeText(getContext(), city + gu + dong, Toast.LENGTH_SHORT).show();
    }

    public void onGetResultFromSpinner2(List<Product> products) {
        //리사이클러뷰 메니저
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeAdapter(getContext(), products, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        product = products;
        sessionManager.updateLocation2(city, gu, dong2, "0", "1");

    }


}
