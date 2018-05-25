package com.itstrongs.myapp.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itstrongs.myapp.R;
import com.itstrongs.myapp.data.bean.Gallery;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 影集图片适配器
 *
 * Created by itstrongs on 2017/12/21.
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.MyViewHolder> {

    private Context mContext;
    private List<Gallery.Picture> mPictures;

    public PictureAdapter(Context context, List<Gallery.Picture> pictures) {
        mContext = context;
        mPictures = pictures;
    }

    @Override
    public PictureAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(parent.getContext(), R.layout.item_picture, null));
    }

    @Override
    public void onBindViewHolder(PictureAdapter.MyViewHolder holder, int position) {
        Gallery.Picture picture = mPictures.get(position);
        Picasso.with(mContext).load(picture.getUrl()).into(holder.imageView);
        holder.textView.setText(picture.getName());
    }

    @Override
    public int getItemCount() {
        return mPictures.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view);
        }
    }
}
