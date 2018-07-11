//package com.snq.ikidz.view.fragment.news;
//
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.snq.ikidz.R;
//import com.snq.ikidz.model.entity.AlbumEntity;
//import com.snq.ikidz.outLibs.Assymetric.AGVRecyclerViewAdapter;
//import com.snq.ikidz.outLibs.Assymetric.AsymmetricItem;
//import com.squareup.picasso.Picasso;
//
//import java.util.List;
//
//public class ChildImageAdapter extends RecyclerView.Adapter<ChildImageAdapter.ViewHolder> {
//
//    List<AlbumEntity> albums;
//    private int mDisplay = 0;
//    private int mTotal = 0;
//
//    public ChildImageAdapter(List<AlbumEntity> albums, int mDisplay, int mTotal) {
//        this.albums = albums;
//        this.mDisplay = mDisplay;
//        this.mTotal = mTotal;
//    }
//
//    @Override
//    public AsymmetricItem getItem(int position) {
//        return (AsymmetricItem) albums.get(position);
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.e("RecyclerViewActivity", "onCreateView");
//        return new ViewHolder(parent, viewType, albums);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Log.e("RecyclerViewActivity", "onBindView position=" + position);
//        Picasso.with(holder.itemView.getContext()).load(albums.get(position).getImage()).into(holder.mImageView);
////        holder.bind(albums,position,mDisplay,mTotal);
//    }
//
//    @Override
//    public int getItemCount() {
//        return albums.size();
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder {
//        private final ImageView mImageView;
//
//        public ViewHolder() {
//            super(itemView);
//            mImageView = (ImageView) itemView.findViewById(R.id.mImageView);
//        }
//
//        public void setData(AlbumEntity entity) {
//            if (entity.getImage() != null)
//                Picasso.with(itemView.getContext()).load(entity.getImage()).error(R.mipmap.ic_launcher).into(mImageView);
//        }
//    }
//
//    public void setMTotal(int mTotal) {
//        this.mTotal = mTotal;
//        notifyDataSetChanged();
//    }
//}
