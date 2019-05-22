package app.worldplay.rappi.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;

import app.worldplay.rappi.R;
import app.worldplay.rappi.common.Constants;
import app.worldplay.rappi.model.Movies;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movies> moviestList;
    private Context c;

    public List<Movies> getMovies() {
        return moviestList;
    }

    public void setMovies(List<Movies> movieResults) {
        this.moviestList = movieResults;
    }

    public MoviesAdapter(List<Movies> moviestList, Context c) {
        this.moviestList = moviestList;
        this.c = c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movies m = moviestList.get(position);
        holder.title.setText(m.getTitle());
        Picasso.with(c)
                .load(Constants.imageUrl + m.getBackdropPath())
                .placeholder(R.drawable.ic_film)
                .error(R.drawable.ic_error)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return moviestList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.txt_item_title);
            img = view.findViewById(R.id.img_item_image);
        }
    }
}