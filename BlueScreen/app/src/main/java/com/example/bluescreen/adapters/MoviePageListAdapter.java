package com.example.bluescreen.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluescreen.R;
import com.example.bluescreen.databinding.ItemBigBinding;
import com.example.bluescreen.databinding.ItemVideoBinding;
import com.example.bluescreen.models.Result;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class MoviePageListAdapter extends PagedListAdapter<Result,MoviePageListAdapter.MoviesViewModel> {

private Boolean bigItem;

    public MoviePageListAdapter(Boolean bigItem) {
        super(diffCallback);
        this.bigItem = bigItem;
    }


    @NonNull
    @Override
    public MoviesViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideoBinding   itemVideoBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), R.layout.item_video, parent, false
            );

        return new MoviesViewModel(itemVideoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewModel holder, int position) {
         holder.bind(getItem(position));
    }

    public static DiffUtil.ItemCallback<Result> diffCallback =
            new DiffUtil.ItemCallback<Result>() {
                @Override
                public boolean areItemsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
                    return oldItem.getId()==newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public class MoviesViewModel extends RecyclerView.ViewHolder{
        private String IMAGE_URL = "https://image.tmdb.org/t/p/";
        private String IMAGE_SIZE = "w500";
        private ItemVideoBinding itemsBinding;
        public MoviesViewModel(ItemVideoBinding itemsBinding) {
            super(itemsBinding.getRoot());

            this.itemsBinding = itemsBinding;

        }

        public void bind(Result item) {
            if(item!=null){
                String thumbURL = IMAGE_URL + IMAGE_SIZE + item.getBackdropPath();
                Picasso.get().load(thumbURL).into(itemsBinding.thumb);
                itemsBinding.title.setText(item.getTitle());
            }
        }
    }
}
