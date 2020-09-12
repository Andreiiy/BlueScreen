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
import com.example.bluescreen.databinding.ItemVideoBinding;
import com.example.bluescreen.models.Result;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class MoviePageListAdapter extends PagedListAdapter<Result,MoviePageListAdapter.MViewModel> {

private Boolean bigItem;

    public MoviePageListAdapter(Boolean bigItem) {
        super(diffCallback);
        this.bigItem = bigItem;
    }


    @NonNull
    @Override
    public MViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideoBinding itemVideoBinding;
        if(bigItem) {
            itemVideoBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), R.layout.item_big, parent, false
            );
            }else {
             itemVideoBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), R.layout.item_video, parent, false
            );
        }
        return new MViewModel(itemVideoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MViewModel holder, int position) {
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

    public class MViewModel extends RecyclerView.ViewHolder{
        private String IMAGE_URL = "https://image.tmdb.org/t/p/";
        private String IMAGE_SIZE = "w200";
        private ItemVideoBinding itemsBinding;
        public MViewModel(ItemVideoBinding itemsBinding) {
            super(itemsBinding.getRoot());

            this.itemsBinding = itemsBinding;

        }

        public void bind(Result item) {
            if(item!=null){
                String thumbURL = IMAGE_URL + IMAGE_SIZE + item.getBackdropPath();
                //String thumbURL2 ="https://image.tmdb.org/t/p/w200/wwemzKWzjKYJFfCeiB57q3r4Bcm.png";
                Picasso.get().load(thumbURL).into(itemsBinding.thumb);
                itemsBinding.title.setText(item.getTitle());
            }
        }
    }
}
