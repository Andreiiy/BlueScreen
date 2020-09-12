package com.example.bluescreen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.bluescreen.R;
import com.example.bluescreen.models.Result;
import com.example.bluescreen.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SlidePagerAdapter extends PagerAdapter {

    Context context;
    List<Result> listMovies;

    public SlidePagerAdapter(Context context, List<Result> listMovies) {
        this.context = context;
        this.listMovies = listMovies;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.item_slide,null);

        ImageView slideImage = (ImageView)slideLayout.findViewById(R.id.imageSlide);
        TextView slideTitle = (TextView)slideLayout.findViewById(R.id.slideTitle);

         String IMAGE_URL = "https://image.tmdb.org/t/p/";
         String IMAGE_SIZE = "w200";
         Result movie = (Result)(listMovies.get(position));
        String thumbURL = IMAGE_URL + IMAGE_SIZE + listMovies.get(position).getBackdropPath();
        //String thumbURL2 ="https://image.tmdb.org/t/p/w200/wwemzKWzjKYJFfCeiB57q3r4Bcm.png";
        Picasso.get().load(thumbURL).into(slideImage);

        slideTitle.setText(listMovies.get(position).getTitle());

        container.addView(slideLayout);

        return slideLayout;

    }

    @Override
    public int getCount() {
        return listMovies.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
