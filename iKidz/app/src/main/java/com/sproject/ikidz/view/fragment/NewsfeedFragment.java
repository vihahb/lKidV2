package com.sproject.ikidz.view.fragment;

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
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.viewObject.Feature;
import com.sproject.ikidz.model.entity.viewObject.News;
import com.sproject.ikidz.view.activity.home.main_feature.AdapterMainFeature;
import com.sproject.ikidz.view.activity.home.main_feature.AdapterNews;
import com.stone.vega.library.VegaLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class NewsfeedFragment extends Fragment {
    AdapterNews adapterNews;
    AdapterMainFeature adapterMainFeature;
    List<Feature> featureList;
    List<News> newsList;
    RecyclerView rcl_feature, rcl_news;
    LinearLayoutManager horizontaLayoutlManager;
    VegaLayoutManager verticalLayoutManager;
    GridLayoutManager gridLayoutlManager;
//    private static int firstVisibleInListview;

    boolean notScrolling = true;
    private boolean setGrid = true;

    public static NewsfeedFragment newInstance() {
        Bundle args = new Bundle();
        NewsfeedFragment fragment = new NewsfeedFragment();
        fragment.setArguments(args);
        return fragment;
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
        featureList = new ArrayList<>();
        newsList = new ArrayList<>();

        horizontaLayoutlManager = new LinearLayoutManager(getContext(), 0, false);
        rcl_feature = view.findViewById(R.id.rcl_feature);
        gridLayoutlManager = new GridLayoutManager(getContext(), 3);
        rcl_feature.setLayoutManager(gridLayoutlManager);

        adapterMainFeature = new AdapterMainFeature(featureList, getActivity());
        rcl_feature.setAdapter(adapterMainFeature);

        rcl_news = view.findViewById(R.id.rcl_news);
        verticalLayoutManager = new VegaLayoutManager();
        rcl_news.setLayoutManager(verticalLayoutManager);
        adapterNews = new AdapterNews(newsList, getContext());
        rcl_news.setAdapter(adapterNews);

        initData();
        handleScroll();
    }

    private void handleScroll() {
//        firstVisibleInListview = verticalLayoutManager.findFirstVisibleItemPosition();
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
        for (int i = 0; i < 6; i++) {
            featureList.add(new Feature("Item " + i, i));
        }
        adapterMainFeature.notifyDataSetChanged();

        for (int i = 0; i < 16; i++) {
            newsList.add(new News("Item " + i, "This is the content item " + i));
        }
        adapterNews.notifyDataSetChanged();
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
}
