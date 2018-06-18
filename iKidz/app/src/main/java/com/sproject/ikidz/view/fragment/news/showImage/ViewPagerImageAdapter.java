package com.sproject.ikidz.view.fragment.news.showImage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sproject.ikidz.model.entity.AlbumEntity;

import java.util.List;

public class ViewPagerImageAdapter extends FragmentStatePagerAdapter {

    private List<AlbumEntity> albums;

    public ViewPagerImageAdapter(FragmentManager fm, List<AlbumEntity> albums) {
        super(fm);
        this.albums = albums;
    }

    @Override
    public Fragment getItem(int position) {
        return PageImageFragment.getInstance(albums.get(position));
    }

    @Override
    public int getCount() {
        return albums.size();
    }
}