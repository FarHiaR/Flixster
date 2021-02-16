package com.example.flixster.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.flixster.DetailActivity;
import com.example.flixster.MainActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.lang.annotation.Target;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
// public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Movie> movies;
    private final int POPULAR=1;
    private final int NON_POPULAR=0;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    //usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }
  //involves populating data into item through holder
  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      Log.d("MovieAdapter", "onBindViewHolder " + position);
      //Get the movie at the passed in position
      Movie movie = movies.get(position);
      //bind the movie data into the VH
      holder.bind(movie);
  }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Movie movie) {

            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageURL;

            //If phone is in landscape
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ) {
                //then imageURL = backdrop image
                imageURL = movie.getBackdropPath();
            } else {
                //else imageURL = poster image
                imageURL = movie.getPosterPath();
            }
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions
                    .fitCenter()
                    .placeholder(R.drawable.loader);

            Glide.with(context)
                    .load(imageURL)
                    .apply(requestOptions)
                    .transform(new RoundedCorners(40))
                    .into(ivPoster);

            // 1.Register click listener on the whole row
                container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    // 2.Navigate to a new activity on tap
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    // Shared element transition
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, itemView, "profile");
                    context.startActivity(i, options.toBundle());
                }
              });
            }
        } //END
}



//FAILED TO DO HETEROGENOUS RECYCLER VIEW (CODE FOR HETEROGENOUS...)
/*  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView;
        Log.d("MovieAdapter", "onCreateViewHolder");
        if(viewType == POPULAR){
            movieView = LayoutInflater.from(context).inflate(R.layout.popular_movie, parent, false);
        }
        else  {
            movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        }
        return new ViewHolder(movieView);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView;
        RecyclerView.ViewHolder holder;
        Log.d("MovieAdapter", "onCreateViewHolder");
        switch (viewType) {
            case R.layout.popular_movie:
                movieView = LayoutInflater.from(context).inflate(R.layout.popular_movie, parent, false);
                holder = new ViewHolderPopularMovies(movieView);
                break;
            case R.layout.item_movie:
                movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
                holder = new ViewHolder(movieView);
                break;
            default:
                movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
                holder = new ViewHolder(movieView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder " + position);
        //Get the movie at the passed in position
        Movie movie = movies.get(position);

        //Bind popular movie data into VH
        if (holder.getItemViewType() == POPULAR) {
            ViewHolderPopularMovies VHpopular = (ViewHolderPopularMovies) holder;
            VHpopular.popular_bind(movie);
        } else if(holder.getItemViewType()==NON_POPULAR) {
            //bind the movie data into the VH
           // holder.bind(movie);
            ViewHolder holder1 = (ViewHolder) holder;
            holder1.bind(movie);
        }
    }

    //involves populating data into item through holder
  /*  @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder " + position);
        //Get the movie at the passed in position
        Movie movie = movies.get(position);

        //Bind popular movie data into VH
        if(holder.getItemViewType() == POPULAR){
            ViewHolderPopularMovies VHpopular = (ViewHolderPopularMovies) holder;
            VHpopular.popular_bind(movie);
        }
        else {
            //bind the movie data into the VH
            holder.bind(movie);
        }
    }
    /*      //Another View Holder class for popular movies
        public class ViewHolderPopularMovies extends RecyclerView.ViewHolder {
            ImageView ivPoster;

            public ViewHolderPopularMovies(@NonNull View itemView) {
                super(itemView);
                ivPoster = itemView.findViewById(R.id.ivPoster);
            }

            public void popular_bind(Movie movie) {

                RequestOptions requestOptions=new RequestOptions();
                requestOptions=requestOptions.placeholder(R.drawable.loader);

                Glide.with(context)
                        .load(movie.getBackdropPath())
                        .apply(requestOptions)
                        .into(ivPoster);

                // 1.Register click listener on the whole row
                ivPoster.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        // 2.Navigate to a new activity on tap
                        Intent i = new Intent(context, DetailActivity.class);
                        i.putExtra("movie", Parcels.wrap(movie));
                        // Shared element transition
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, itemView, "profile");
                        context.startActivity(i, options.toBundle());
                    }
                });
            }
             /*    //Returns the view type of the item at position for the purposes of view recycling.
        @Override
        public int getItemViewType(int position) {
            if (movies.get(position).getRating()>5) {
               // return POPULAR;
                return R.layout.popular_movie;
            } else  {
               // return NON_POPULAR;
                return R.layout.item_movie;
            }
        } */




