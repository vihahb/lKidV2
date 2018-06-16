package com.sproject.ikidz.view.fragment.news;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.RESP.RESP_DataNews;
import com.sproject.ikidz.model.entity.NewsEntity;
import com.sproject.ikidz.model.entity.viewObject.Feature;
import com.sproject.ikidz.model.entity.viewObject.News;
import com.sproject.ikidz.presenter.news.NewsPresenter;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.view.activity.home.main_feature.AdapterMainFeature;
import com.sproject.ikidz.view.activity.home.main_feature.AdapterNews;
import com.stone.vega.library.VegaLayoutManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NewsfeedFragment extends Fragment implements NewsInf {
    AdapterNews adapterNews;
    AdapterMainFeature adapterMainFeature;
    List<Feature> featureList;
    List<NewsEntity> newsList;
    RecyclerView rcl_feature, rcl_news;
    LinearLayoutManager horizontaLayoutlManager;
    VegaLayoutManager verticalLayoutManager;
    GridLayoutManager gridLayoutlManager;
    TextView tv_message;
//    private static int firstVisibleInListview;

    boolean notScrolling = true;
    private boolean setGrid = true;

    private NewsPresenter presenter;

    int page = 1;

    public static NewsfeedFragment newInstance() {
        Bundle args = new Bundle();
        NewsfeedFragment fragment = new NewsfeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewsPresenter(this);
        featureList = new ArrayList<>();
        newsList = new ArrayList<>();

        horizontaLayoutlManager = new LinearLayoutManager(getContext(), 0, false);
        gridLayoutlManager = new GridLayoutManager(getContext(), 3);
        verticalLayoutManager = new VegaLayoutManager();

        adapterMainFeature = new AdapterMainFeature(featureList, getActivity());
        adapterNews = new AdapterNews(newsList, getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_newsfeed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {

        tv_message = view.findViewById(R.id.tv_message);

        rcl_feature = view.findViewById(R.id.rcl_feature);
        rcl_feature.setLayoutManager(gridLayoutlManager);
        rcl_feature.setAdapter(adapterMainFeature);

        rcl_news = view.findViewById(R.id.rcl_news);
        rcl_news.setLayoutManager(verticalLayoutManager);
        rcl_news.setAdapter(adapterNews);

        initData();
        handleScroll();
        presenter.getNews(page);
    }

    private void handleScroll() {
        rcl_news.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        Log.e("RecyclerView Scroll:", "The RecyclerView is not scrolling");
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        notScrolling = true;
                        Log.e("RecyclerView Scroll:", "Scrolling now");
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:

                        Log.e("RecyclerView Scroll:", "Scroll Settling");
                        int pastVisibleItems = verticalLayoutManager.findFirstVisibleItemPosition();
                        if (pastVisibleItems == 0) {
                            if (!setGrid) {
                                setGrid = true;
                                rcl_feature.setLayoutManager(gridLayoutlManager);
                                setShowFull(true);
                            }
                            Toast.makeText(getContext(), "Top most item", Toast.LENGTH_SHORT).show();
                        }
                        break;

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    // Scrolling up

                    int pastVisibleItems = verticalLayoutManager.findFirstVisibleItemPosition();
                    if (pastVisibleItems > 0) {
                        if (setGrid) {
                            setGrid = false;
                            rcl_feature.setLayoutManager(horizontaLayoutlManager);
                            setShowFull(false);
                        }
//                        Toast.makeText(getContext(), "Top most item", Toast.LENGTH_SHORT).show();
                    }
                    Log.e("RecyclerView Scroll:", "up");
                } else {
                    // Scrolling down

                    Log.e("RecyclerView Scroll:", "down");
                }
            }
        });
    }

    private void initData() {
        Feature feature_1 = new Feature("Xin nghỉ", 1, 0, R.drawable.round_feature_1, true, R.mipmap.absent);
        Feature feature_2 = new Feature("Điểm danh đến", 2, 0, R.drawable.round_feature_2, true, R.mipmap.diemdanhden);
        Feature feature_3 = new Feature("Dặn thuốc", 3, 0, R.drawable.round_feature_3, true, R.mipmap.drug);
        Feature feature_4 = new Feature("Xin học thêm giờ", 4, 0, R.drawable.round_feature_4, true, R.mipmap.xinhocthem);
        Feature feature_5 = new Feature("Điểm danh về", 5, 0, R.drawable.round_feature_5, true, R.mipmap.diemdanhve);
        Feature feature_6 = new Feature("Tạo album", 6, 0, R.drawable.round_feature_6, true, R.mipmap.picture);
        featureList.add(feature_1);
        featureList.add(feature_2);
        featureList.add(feature_3);
        featureList.add(feature_4);
        featureList.add(feature_5);
        featureList.add(feature_6);

        adapterMainFeature.notifyDataSetChanged();
    }

    private void setShowFull(boolean b){
        adapterMainFeature.setShowFull(b);
    }


    @Override
    public void onResume() {
        super.onResume();
        fetchCount();
    }

    private void fetchCount() {
      int absent =  SharedUtils.getInstance().getIntValue(Constants.ABSENT, 0);
      int add_drug =  SharedUtils.getInstance().getIntValue(Constants.ADD_DRUG, 0);
      int register_time =  SharedUtils.getInstance().getIntValue(Constants.register_more_time, 0);

      featureList.get(0).setNotifyCount(absent);
      featureList.get(2).setNotifyCount(add_drug);
      featureList.get(3).setNotifyCount(register_time);
      adapterMainFeature.notifyItemRangeChanged(0, 6);

    }

    //Rerun Animation layout recyclerview
    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void GetNewsSuccess(@NotNull RESP_DataNews news, int page) {
        if (news.getData().getData().size() == 0 && page < 2){
            tv_message.setVisibility(View.VISIBLE);
            tv_message.setText(getContext().getResources().getString(R.string.mesage_no_data_news));
        } else {
            tv_message.setVisibility(View.GONE);
            if (page < 2){
                newsList.clear();
            }
            newsList.addAll(news.getData().getData());
            adapterNews.notifyDataSetChanged();
        }
    }

    @Override
    public void GetNewsError(@NotNull String s) {
        tv_message.setVisibility(View.VISIBLE);
        tv_message.setText(s);
    }
}
