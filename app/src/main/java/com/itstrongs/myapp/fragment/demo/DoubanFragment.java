package com.itstrongs.myapp.fragment.demo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itstrongs.library.base.BaseFragment;
import com.itstrongs.myapp.R;
import com.itstrongs.myapp.data.ConstantPool;
import com.itstrongs.myapp.data.bean.Movie;
import com.itstrongs.myapp.data.http.HttpService;
import com.itstrongs.myapp.data.http.RetrofitHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by itstrongs on 2017/12/20.
 */
public class DoubanFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MyAdapter mMovieAdapter;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_douban;
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mMovieAdapter = new MyAdapter());
    }

    @Override
    protected void initData() {
//        RetrofitHelper.getInstance().getDoubanService().getTop250(0, 20)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Movie>() {
//                    @Override
//                    public void accept(Movie movieData) throws Exception {
//                        mMovieAdapter.setMovies(mContext, movieData.subjects);
//                        mMovieAdapter.notifyDataSetChanged();
//                    }
//                });
        RetrofitHelper.getInstance().getRetrofit(ConstantPool.URL_DOUBAN).create(HttpService.class).getTop250(0, 20)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Movie>() {
                    @Override
                    public void accept(Movie movieData) throws Exception {
                        mMovieAdapter.setMovies(mContext, movieData.subjects);
                        mMovieAdapter.notifyDataSetChanged();
                    }
                });
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private List<Movie.video> mMovies;
        private Context mContext;

        public void setMovies(Context context, List<Movie.video> movies) {
            this.mContext = context;
            this.mMovies = movies;
        }

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.moive_item,null));
        }

        @Override
        public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
            Movie.video movie = mMovies.get(position);
            MyViewHolder movieHolder = holder;
            Picasso.with(mContext).load(movie.images.small).into(movieHolder.mImageView);
            movieHolder.time.setText("上映时间："+movie.year + "年");
            movieHolder.title.setText(movie.title);
            movieHolder.subTitle.setText(movie.original_title);
        }

        @Override
        public int getItemCount() {
            return mMovies == null ? 0:mMovies.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            public ImageView mImageView;
            public TextView title;
            public TextView subTitle;
            public TextView time;
            public MyViewHolder(View itemView) {
                super(itemView);
                mImageView = itemView.findViewById(R.id.movie_image);
                title = itemView.findViewById(R.id.movie_title);
                subTitle = itemView.findViewById(R.id.movie_sub_title);
                time = itemView.findViewById(R.id.movie_time);
            }
        }
    }

}
