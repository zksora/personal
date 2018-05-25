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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itstrongs on 2018/1/5.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private Context mContext;
    private List<Gallery.Picture> mPictures;
    private OnItemClickListener mOnItemClickListener;

    public PhotoAdapter(Context context) {
        mContext = context;
        mPictures = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setDataList(List<Gallery.Picture> pictures) {
        mPictures.addAll(pictures);
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(View.inflate(parent.getContext(), R.layout.item_list_girl, null));
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, final int position) {
        Gallery.Picture picture = mPictures.get(position);
        Picasso.with(mContext).load(picture.getUrl()).into(holder.imageView);
        holder.textView.setText(picture.getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClickListener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPictures.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view);
        }
    }
}
