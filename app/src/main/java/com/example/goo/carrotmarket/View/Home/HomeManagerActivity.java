package com.example.goo.carrotmarket.View.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.example.goo.carrotmarket.Model.Category;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goo on 2019-05-03.
 */

public class HomeManagerActivity extends AppCompatActivity implements View.OnClickListener, HomeView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.relative_digital)
    RelativeLayout relative_digital;
    @BindView(R.id.check_digital)
    ImageView check_digital;
    @BindView(R.id.checked_digital)
    ImageView checked_digital;

    @BindView(R.id.relative_furniture)
    RelativeLayout relative_furniture;
    @BindView(R.id.check_furniture)
    ImageView check_furniture;
    @BindView(R.id.checked_furniture)
    ImageView checked_furniture;

    @BindView(R.id.relative_child)
    RelativeLayout relative_child;
    @BindView(R.id.check_child)
    ImageView check_child;
    @BindView(R.id.checked_child)
    ImageView checked_child;

    @BindView(R.id.relative_life)
    RelativeLayout relative_life;
    @BindView(R.id.check_life)
    ImageView check_life;
    @BindView(R.id.checked_life)
    ImageView checked_life;

    @BindView(R.id.relative_woman_fashion)
    RelativeLayout relative_woman_fashion;
    @BindView(R.id.check_woman_fashion)
    ImageView check_woman_fashion;
    @BindView(R.id.checked_woman_fashion)
    ImageView checked_woman_fashion;

    @BindView(R.id.relative_woman_etc)
    RelativeLayout relative_woman_etc;
    @BindView(R.id.check_woman_etc)
    ImageView check_woman_etc;
    @BindView(R.id.checked_woman_etc)
    ImageView checked_woman_etc;

    @BindView(R.id.relative_beauty)
    RelativeLayout relative_beauty;
    @BindView(R.id.check_beauty)
    ImageView check_beauty;
    @BindView(R.id.checked_beauty)
    ImageView checked_beauty;

    @BindView(R.id.relative_man)
    RelativeLayout relative_man;
    @BindView(R.id.check_man)
    ImageView check_man;
    @BindView(R.id.checked_man)
    ImageView checked_man;

    @BindView(R.id.relative_sports)
    RelativeLayout relative_sports;
    @BindView(R.id.check_sports)
    ImageView check_sports;
    @BindView(R.id.checked_sports)
    ImageView checked_sports;

    @BindView(R.id.relative_game)
    RelativeLayout relative_game;
    @BindView(R.id.check_game)
    ImageView check_game;
    @BindView(R.id.checked_game)
    ImageView checked_game;

    @BindView(R.id.relative_ticket)
    RelativeLayout relative_ticket;
    @BindView(R.id.check_ticket)
    ImageView check_ticket;
    @BindView(R.id.checked_ticket)
    ImageView checked_ticket;

    @BindView(R.id.relative_pet)
    RelativeLayout relative_pet;
    @BindView(R.id.check_pet)
    ImageView check_pet;
    @BindView(R.id.checked_pet)
    ImageView checked_pet;

    @BindView(R.id.relative_etc)
    RelativeLayout relative_etc;
    @BindView(R.id.check_etc)
    ImageView check_etc;
    @BindView(R.id.checked_etc)
    ImageView checked_etc;

    @BindView(R.id.relative_buying)
    RelativeLayout relative_buying;
    @BindView(R.id.check_buying)
    ImageView check_buying;
    @BindView(R.id.checked_buying)
    ImageView checked_buying;

    int flag_digital, flag_furniture, flag_child, flag_life, flag_woman_fashion, flag_woman_etc, flag_beauty, flag_man, flag_sports, flag_game, flag_ticket, flag_pet, flag_etc, flag_buying;

    SessionManager sessionManager;
    HashMap<String, String> user;
    List<Category> listCategory;
    String nick;
    HomePresenter presenter;
    Intent returnIntent;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_manager);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        returnIntent = getIntent();
        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();
        listCategory = new ArrayList<>();
        nick = user.get(sessionManager.NICK).toString();
        //프레젠터
        presenter = new HomePresenter(this);
        presenter.getCategory(nick);

        count = 0;


        initFlagValue();
        initButton();
    }

    public void initFlagValue() {
        flag_digital = 1;
        flag_furniture = 1;
        flag_child = 1;
        flag_life = 1;
        flag_woman_fashion = 1;
        flag_woman_etc = 1;
        flag_beauty = 1;
        flag_man = 1;
        flag_sports = 1;
        flag_game = 1;
        flag_ticket = 1;
        flag_pet = 1;
        flag_etc = 1;
        flag_buying = 1;
    }

    public void initButton() {
        relative_digital.setOnClickListener((View.OnClickListener) this);
        relative_furniture.setOnClickListener((View.OnClickListener) this);
        relative_child.setOnClickListener((View.OnClickListener) this);
        relative_life.setOnClickListener((View.OnClickListener) this);
        relative_woman_fashion.setOnClickListener((View.OnClickListener) this);
        relative_woman_etc.setOnClickListener((View.OnClickListener) this);
        relative_beauty.setOnClickListener((View.OnClickListener) this);
        relative_man.setOnClickListener((View.OnClickListener) this);
        relative_sports.setOnClickListener((View.OnClickListener) this);
        relative_game.setOnClickListener((View.OnClickListener) this);
        relative_ticket.setOnClickListener((View.OnClickListener) this);
        relative_pet.setOnClickListener((View.OnClickListener) this);
        relative_etc.setOnClickListener((View.OnClickListener) this);
        relative_buying.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_back, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                returnIntent.putExtra("managerDone", 1);
                setResult(Activity.RESULT_OK, returnIntent);

                finish();

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void snackBar(String text) {
        Snackbar.make(getWindow().getDecorView().getRootView(), text.toString(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.relative_digital:

                if (flag_digital == 0) {

                    count++;
                    check_digital.setVisibility(View.GONE);
                    checked_digital.setVisibility(View.VISIBLE);
                    presenter.sendCategorytoServer(nick, "디지털", "디지털/가전");

                    snackBar("추가 됐습니다.");
                    flag_digital = 1;
                } else {

                    if (count == 1) {
                        relative_digital.setClickable(false);
                        snackBar("최소 1개 이상선택되어 있어야 합니다.");

                        // relative_digital.setEnabled(false);
                    } else {

                        count--;
                        check_digital.setVisibility(View.VISIBLE);
                        checked_digital.setVisibility(View.GONE);
                        presenter.sendCategorytoServer(nick, "디지털", "");
                        snackBar("해제 됐습니다.");
                        flag_digital = 0;
                    }


                }
                System.out.println("카운트는 ++++++++++" + count);
                break;
            case R.id.relative_furniture:

                if (flag_furniture == 0) {

                    count++;
                    check_furniture.setVisibility(View.GONE);
                    checked_furniture.setVisibility(View.VISIBLE);
                    presenter.sendCategorytoServer(nick, "가구", "가구/인테리어");
                    snackBar("추가 됐습니다.");

                    flag_furniture = 1;
                } else {

                    if (count == 1) {
                        relative_furniture.setEnabled(false);
                        snackBar("최소 1개 이상선택되어 있어야 합니다.");
                    } else {

                        count--;
                        check_furniture.setVisibility(View.VISIBLE);
                        checked_furniture.setVisibility(View.GONE);
                        presenter.sendCategorytoServer(nick, "가구", "");
                        snackBar("해제 됐습니다.");

                        flag_furniture = 0;
                    }

                }
                System.out.println("카운트는 ++++++++++" + count);
                break;
            case R.id.relative_child:

                if (flag_child == 0) {

                    count++;
                    check_child.setVisibility(View.GONE);
                    checked_child.setVisibility(View.VISIBLE);
                    presenter.sendCategorytoServer(nick, "유아동", "유아동/유아도서");
                    snackBar("추가 됐습니다.");
                    flag_child = 1;
                } else {

                    if (count == 1) {
                        relative_child.setEnabled(false);
                        snackBar("최소 1개 이상선택되어 있어야 합니다.");
                    } else {

                        count--;
                        check_child.setVisibility(View.VISIBLE);
                        checked_child.setVisibility(View.GONE);
                        presenter.sendCategorytoServer(nick, "유아동", "");
                        snackBar("해제 됐습니다.");
                        flag_child = 0;
                    }

                }
                System.out.println("카운트는 ++++++++++" + count);
                break;
            case R.id.relative_life:

                if (flag_life == 0) {

                    count++;
                    check_life.setVisibility(View.GONE);
                    checked_life.setVisibility(View.VISIBLE);
                    presenter.sendCategorytoServer(nick, "생활", "생활/가공식품");
                    snackBar("추가 됐습니다.");
                    flag_life = 1;
                } else {

                    if (count == 1) {
                        relative_life.setEnabled(false);
                        snackBar("최소 1개 이상선택되어 있어야 합니다.");
                    } else {

                        count--;
                        check_life.setVisibility(View.VISIBLE);
                        checked_life.setVisibility(View.GONE);
                        presenter.sendCategorytoServer(nick, "생활", "");
                        snackBar("해제 됐습니다.");
                        flag_life = 0;
                    }

                }
                System.out.println("카운트는 ++++++++++" + count);
                break;
            case R.id.relative_woman_fashion:

                if (flag_woman_fashion == 0) {

                    count++;
                    check_woman_fashion.setVisibility(View.GONE);
                    checked_woman_fashion.setVisibility(View.VISIBLE);
                    presenter.sendCategorytoServer(nick, "여성의류", "여성의류");
                    snackBar("추가 됐습니다.");
                    flag_woman_fashion = 1;
                } else {

                    if (count == 1) {
                        relative_woman_fashion.setEnabled(false);
                        snackBar("최소 1개 이상선택되어 있어야 합니다.");
                    } else {

                        count--;
                        check_woman_fashion.setVisibility(View.VISIBLE);
                        checked_woman_fashion.setVisibility(View.GONE);
                        presenter.sendCategorytoServer(nick, "여성의류", "");
                        snackBar("해제 됐습니다.");
                        flag_woman_fashion = 0;
                    }

                }
                System.out.println("카운트는 ++++++++++" + count);
                break;
            case R.id.relative_woman_etc:

                if (flag_woman_etc == 0) {

                    count++;
                    check_woman_etc.setVisibility(View.GONE);
                    checked_woman_etc.setVisibility(View.VISIBLE);
                    presenter.sendCategorytoServer(nick, "여성잡화", "여성잡화");
                    snackBar("추가 됐습니다.");
                    flag_woman_etc = 1;
                } else {

                    if (count == 1) {
                        relative_woman_etc.setEnabled(false);
                        snackBar("최소 1개 이상선택되어 있어야 합니다.");
                    } else {

                        count--;
                        check_woman_etc.setVisibility(View.VISIBLE);
                        checked_woman_etc.setVisibility(View.GONE);
                        presenter.sendCategorytoServer(nick, "여성잡화", "");
                        snackBar("해제 됐습니다.");
                        flag_woman_etc = 0;
                    }

                }
                System.out.println("카운트는 ++++++++++" + count);
                break;
            case R.id.relative_beauty:

                if (flag_beauty == 0) {

                    count++;
                    check_beauty.setVisibility(View.GONE);
                    checked_beauty.setVisibility(View.VISIBLE);
                    presenter.sendCategorytoServer(nick, "뷰티", "뷰티/미용");
                    snackBar("추가 됐습니다.");
                    flag_beauty = 1;
                } else {

                    if (count == 1) {
                        relative_beauty.setEnabled(false);
                        snackBar("최소 1개 이상선택되어 있어야 합니다.");
                    } else {

                        count--;
                        check_beauty.setVisibility(View.VISIBLE);
                        checked_beauty.setVisibility(View.GONE);
                        presenter.sendCategorytoServer(nick, "뷰티", "");
                        snackBar("해제 됐습니다.");
                        flag_beauty = 0;
                    }

                }
                System.out.println("카운트는 ++++++++++" + count);
                break;
            case R.id.relative_man:

                if (flag_man == 0) {

                    count++;
                    check_man.setVisibility(View.GONE);
                    checked_man.setVisibility(View.VISIBLE);
                    presenter.sendCategorytoServer(nick, "남성패션", "남성패션/잡화");
                    snackBar("추가 됐습니다.");
                    flag_man = 1;
                } else {

                    if (count == 1) {
                        relative_man.setEnabled(false);
                        snackBar("최소 1개 이상선택되어 있어야 합니다.");
                    } else {

                        count--;
                        check_man.setVisibility(View.VISIBLE);
                        checked_man.setVisibility(View.GONE);
                        presenter.sendCategorytoServer(nick, "남성패션", "");
                        snackBar("해제 됐습니다.");
                        flag_man = 0;
                    }

                }
                System.out.println("카운트는 ++++++++++" + count);
                break;
            case R.id.relative_sports:

                if (flag_sports == 0) {

                    count++;
                    check_sports.setVisibility(View.GONE);
                    checked_sports.setVisibility(View.VISIBLE);
                    presenter.sendCategorytoServer(nick, "스포츠", "스포츠/레저");

                    snackBar("추가 됐습니다.");
                    flag_sports = 1;
                } else {

                    if (count == 1) {
                        relative_sports.setEnabled(false);
                        snackBar("최소 1개 이상선택되어 있어야 합니다.");
                    } else {

                        count--;
                        check_sports.setVisibility(View.VISIBLE);
                        checked_sports.setVisibility(View.GONE);
                        presenter.sendCategorytoServer(nick, "스포츠", "");
                        snackBar("해제 됐습니다.");
                        flag_sports = 0;
                    }

                }
                break;
            case R.id.relative_game:

                if (flag_game == 0) {

                    count++;
                    check_game.setVisibility(View.GONE);
                    checked_game.setVisibility(View.VISIBLE);
                    presenter.sendCategorytoServer(nick, "게임", "게임/취미");
                    snackBar("추가 됐습니다.");
                    flag_game = 1;
                } else {

                    if (count == 1) {
                        relative_game.setEnabled(false);
                        snackBar("최소 1개 이상선택되어 있어야 합니다.");
                    } else {

                        count--;
                        check_game.setVisibility(View.VISIBLE);
                        checked_game.setVisibility(View.GONE);
                        presenter.sendCategorytoServer(nick, "게임", "");
                        snackBar("해제 됐습니다.");
                        flag_game = 0;
                    }

                }
                break;
            case R.id.relative_ticket:

                if (flag_ticket == 0) {

                    count++;
                    check_ticket.setVisibility(View.GONE);
                    checked_ticket.setVisibility(View.VISIBLE);
                    presenter.sendCategorytoServer(nick, "도서", "도서/티켓/음반");
                    snackBar("추가 됐습니다.");
                    flag_ticket = 1;
                } else {

                    if (count == 1) {
                        relative_ticket.setEnabled(false);
                        snackBar("최소 1개 이상선택되어 있어야 합니다.");
                    } else {

                        count--;
                        check_ticket.setVisibility(View.VISIBLE);
                        checked_ticket.setVisibility(View.GONE);
                        presenter.sendCategorytoServer(nick, "도서", "");
                        snackBar("해제 됐습니다.");
                        flag_ticket = 0;
                    }

                }
                break;
            case R.id.relative_pet:

                if (flag_pet == 0) {

                    count++;
                    check_pet.setVisibility(View.GONE);
                    checked_pet.setVisibility(View.VISIBLE);
                    presenter.sendCategorytoServer(nick, "반려동물용품", "반려동물용품");
                    snackBar("추가 됐습니다.");
                    flag_pet = 1;
                } else {

                    if (count == 1) {
                        relative_pet.setEnabled(false);
                        snackBar("최소 1개 이상선택되어 있어야 합니다.");
                    } else {


                        count--;
                        check_pet.setVisibility(View.VISIBLE);
                        checked_pet.setVisibility(View.GONE);
                        presenter.sendCategorytoServer(nick, "반려동물용품", "");
                        snackBar("해제 됐습니다.");
                        flag_pet = 0;
                    }

                }
                break;
            case R.id.relative_etc:

                if (flag_etc == 0) {

                    count++;
                    check_etc.setVisibility(View.GONE);
                    checked_etc.setVisibility(View.VISIBLE);
                    presenter.sendCategorytoServer(nick, "기타", "기타 중고물품");
                    snackBar("추가 됐습니다.");
                    flag_etc = 1;
                } else {

                    if (count == 1) {
                        relative_etc.setEnabled(false);
                        snackBar("최소 1개 이상선택되어 있어야 합니다.");
                    } else {

                        count--;
                        check_etc.setVisibility(View.VISIBLE);
                        checked_etc.setVisibility(View.GONE);
                        presenter.sendCategorytoServer(nick, "기타", "");
                        snackBar("해제 됐습니다.");
                        flag_etc = 0;
                    }

                }
                break;
            case R.id.relative_buying:

                if (flag_buying == 0) {

                    count++;
                    check_buying.setVisibility(View.GONE);
                    checked_buying.setVisibility(View.VISIBLE);
                    presenter.sendCategorytoServer(nick, "삽니다", "삽니다");
                    snackBar("추가 됐습니다.");
                    flag_buying = 1;
                } else {

                    if (count == 1) {
                        relative_buying.setEnabled(false);
                        snackBar("최소 1개 이상선택되어 있어야 합니다.");
                    } else {

                        count--;
                        check_buying.setVisibility(View.VISIBLE);
                        checked_buying.setVisibility(View.GONE);
                        presenter.sendCategorytoServer(nick, "삽니다", "");
                        snackBar("해제 됐습니다.");
                        flag_buying = 0;
                    }

                }
                break;
        }

        enableRelative();
    }

    public void enableRelative() {
        relative_digital.setEnabled(true);
        relative_furniture.setEnabled(true);
        relative_child.setEnabled(true);
        relative_life.setEnabled(true);
        relative_woman_fashion.setEnabled(true);
        relative_woman_etc.setEnabled(true);
        relative_beauty.setEnabled(true);
        relative_man.setEnabled(true);
        relative_sports.setEnabled(true);
        relative_game.setEnabled(true);
        relative_ticket.setEnabled(true);
        relative_pet.setEnabled(true);
        relative_etc.setEnabled(true);
        relative_buying.setEnabled(true);
    }


    public void disableSelect(RelativeLayout relative) {
        if (count == 1) {
            relative.setEnabled(false);
        } else {
            relative.setEnabled(true);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void onGetResult(List<Product> products) {

    }

    @Override
    public void onGetResultCategory(List<Category> category) {
        listCategory = category;

        if (listCategory.get(0).getDigital().equals("디지털/가전")) {
            flag_digital = 1;
            check_digital.setVisibility(View.GONE);
            checked_digital.setVisibility(View.VISIBLE);
            count++;
        } else {
            flag_digital = 0;
            check_digital.setVisibility(View.VISIBLE);
            checked_digital.setVisibility(View.GONE);
        }

        if (listCategory.get(0).getFurniture().equals("가구/인테리어")) {
            flag_furniture = 1;
            check_furniture.setVisibility(View.GONE);
            checked_furniture.setVisibility(View.VISIBLE);
            count++;
        } else {
            flag_furniture = 0;
            check_furniture.setVisibility(View.VISIBLE);
            checked_furniture.setVisibility(View.GONE);
        }

        if (listCategory.get(0).getChild().equals("유아동/유아도서")) {
            flag_child = 1;
            check_child.setVisibility(View.GONE);
            checked_child.setVisibility(View.VISIBLE);
            count++;
        } else {
            flag_child = 0;
            check_child.setVisibility(View.VISIBLE);
            checked_child.setVisibility(View.GONE);
        }

        if (listCategory.get(0).getLife().equals("생활/가공식품")) {
            flag_life = 1;
            check_life.setVisibility(View.GONE);
            checked_life.setVisibility(View.VISIBLE);
            count++;
        } else {
            flag_life = 0;
            check_life.setVisibility(View.VISIBLE);
            checked_life.setVisibility(View.GONE);
        }

        if (listCategory.get(0).getFemale_fashion().equals("여성의류")) {
            flag_woman_fashion = 1;
            check_woman_fashion.setVisibility(View.GONE);
            checked_woman_fashion.setVisibility(View.VISIBLE);
            count++;
        } else {
            flag_woman_fashion = 0;
            check_woman_fashion.setVisibility(View.VISIBLE);
            checked_woman_fashion.setVisibility(View.GONE);
        }

        if (listCategory.get(0).getFemale_etc().equals("여성잡화")) {
            flag_woman_etc = 1;
            check_woman_etc.setVisibility(View.GONE);
            checked_woman_etc.setVisibility(View.VISIBLE);
            count++;
        } else {
            flag_woman_etc = 0;
            check_woman_etc.setVisibility(View.VISIBLE);
            checked_woman_etc.setVisibility(View.GONE);
        }

        if (listCategory.get(0).getBeauty().equals("뷰티/미용")) {
            flag_beauty = 1;
            check_beauty.setVisibility(View.GONE);
            checked_beauty.setVisibility(View.VISIBLE);
            count++;
        } else {
            flag_beauty = 0;
            check_beauty.setVisibility(View.VISIBLE);
            checked_beauty.setVisibility(View.GONE);
        }

        if (listCategory.get(0).getMale().equals("남성패션/잡화")) {
            flag_man = 1;
            check_man.setVisibility(View.GONE);
            checked_man.setVisibility(View.VISIBLE);
            count++;
        } else {
            flag_man = 0;
            check_man.setVisibility(View.VISIBLE);
            checked_man.setVisibility(View.GONE);
        }

        if (listCategory.get(0).getSports().equals("스포츠/레저")) {
            flag_sports = 1;
            check_sports.setVisibility(View.GONE);
            checked_sports.setVisibility(View.VISIBLE);
            count++;
        } else {
            flag_sports = 0;
            check_sports.setVisibility(View.VISIBLE);
            checked_sports.setVisibility(View.GONE);
        }

        if (listCategory.get(0).getGame().equals("게임/취미")) {
            flag_game = 1;
            check_game.setVisibility(View.GONE);
            checked_game.setVisibility(View.VISIBLE);
            count++;
        } else {
            flag_game = 0;
            check_game.setVisibility(View.VISIBLE);
            checked_game.setVisibility(View.GONE);
        }

        if (listCategory.get(0).getTicket().equals("도서/티켓/음반")) {
            flag_ticket = 1;
            check_ticket.setVisibility(View.GONE);
            checked_ticket.setVisibility(View.VISIBLE);
            count++;
        } else {
            flag_ticket = 0;
            check_ticket.setVisibility(View.VISIBLE);
            checked_ticket.setVisibility(View.GONE);
        }

        if (listCategory.get(0).getPet().equals("반려동물용품")) {
            flag_pet = 1;
            check_pet.setVisibility(View.GONE);
            checked_pet.setVisibility(View.VISIBLE);
            count++;
        } else {
            flag_pet = 0;
            check_pet.setVisibility(View.VISIBLE);
            checked_pet.setVisibility(View.GONE);
        }

        if (listCategory.get(0).getEtc().equals("기타 중고물품")) {
            flag_etc = 1;
            check_etc.setVisibility(View.GONE);
            checked_etc.setVisibility(View.VISIBLE);
            count++;
        } else {
            flag_etc = 0;
            check_etc.setVisibility(View.VISIBLE);
            checked_etc.setVisibility(View.GONE);
        }
        if (listCategory.get(0).getBuying().equals("삽니다")) {
            flag_buying = 1;
            check_buying.setVisibility(View.GONE);
            checked_buying.setVisibility(View.VISIBLE);
            count++;
        } else {
            flag_buying = 0;
            check_buying.setVisibility(View.VISIBLE);
            checked_buying.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetResultCategory(String text) {

    }

    @Override
    public void moveActivity(Class activity) {

    }

    @Override
    public void moveActivityForResult(Class activity) {

    }
}
