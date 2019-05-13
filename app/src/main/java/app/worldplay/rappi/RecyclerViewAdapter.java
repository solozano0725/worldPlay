package app.worldplay.rappi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.worldplay.rappi.model.Movies;
import app.worldplay.rappi.model.Result;
import app.worldplay.rappi.service.MoviesServices;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderAll> implements View.OnClickListener {

    List<Result> arrayList;
    Context context;
    LayoutInflater inflater;
    View.OnClickListener listener;

    public RecyclerViewAdapter(List<Result> array) {
        this.arrayList = array;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolderAll onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        view.setOnClickListener(this);
        return new ViewHolderAll(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderAll holder, int position) {
        holder.bind(arrayList.get(position));
        /**holder.title.setText(arrayList.get(position).getTITLE());
         holder.subtitle.setText(arrayList.get(position).getSUBTITLE());
         Picasso.with(context).load(arrayList.get(position).getIMAGE()).into(holder.imageView);**/
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
        //return 50;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public class ViewHolderAll extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView title;
        public TextView list_genres;
        public TextView rating;
        public TextView release_date;

        public ViewHolderAll(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imgMovie);
            title = (TextView) itemView.findViewById(R.id.item_title_movie);
            list_genres = (TextView) itemView.findViewById(R.id.item_list_genres);
            rating = (TextView) itemView.findViewById(R.id.item_movie_rating);
            release_date = (TextView) itemView.findViewById(R.id.item_date_release_movie);
        }

        public void bind(Result r){
            title.setText(r.getTitle());
            list_genres.setText(r.getGenreIds().toString());
            rating.setText(String.valueOf(r.getPopularity()));
            release_date.setText(r.getReleaseDate());
        }
    }
}
