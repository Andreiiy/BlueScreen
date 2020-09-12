package com.example.bluescreen.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluescreen.DescriptionMovieActivity;
import com.example.bluescreen.R;
import com.example.bluescreen.databinding.ItemBigBinding;
import com.example.bluescreen.databinding.ItemVideoBinding;
import com.example.bluescreen.models.Result;
import com.squareup.picasso.Picasso;


    public class CategoriotMoviesListAdapter extends PagedListAdapter<Result, CategoriotMoviesListAdapter.MViewModel> {

        private Context context;

        public CategoriotMoviesListAdapter(Context context) {
            super(diffCallback);
         this.context = context;
        }


        @NonNull
        @Override
        public CategoriotMoviesListAdapter.MViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemBigBinding itemVideoBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), R.layout.item_big, parent, false
            );

            return new CategoriotMoviesListAdapter.MViewModel(itemVideoBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull CategoriotMoviesListAdapter.MViewModel holder, int position) {
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

        public class MViewModel extends RecyclerView.ViewHolder implements View.OnClickListener {
            private String IMAGE_URL = "https://image.tmdb.org/t/p/";
            private String IMAGE_SIZE = "w500";
            private ItemBigBinding itemsBinding;
            public MViewModel(ItemBigBinding itemsBinding) {
                super(itemsBinding.getRoot());

                this.itemsBinding = itemsBinding;
                 itemView.setOnClickListener(this);
            }

            public void bind(Result item) {
                if(item!=null){
                    String thumbURL = IMAGE_URL + IMAGE_SIZE + item.getBackdropPath();
                    Picasso.get().load(thumbURL).into(itemsBinding.thumb);
                    itemsBinding.title.setText(item.getTitle());
                }
            }

            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                Result movie = getItem(pos);
                Intent intent = new Intent(context, DescriptionMovieActivity.class);
                intent.putExtra("resalt",movie);
                context.startActivity(intent);
            }
        }
    }


