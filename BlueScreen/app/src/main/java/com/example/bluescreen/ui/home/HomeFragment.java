package com.example.bluescreen.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.bluescreen.R;
import com.example.bluescreen.adapters.MoviePageListAdapter;
import com.example.bluescreen.adapters.SlidePagerAdapter;
import com.example.bluescreen.models.Result;
import com.example.bluescreen.models.Slide;
import com.example.bluescreen.viewmodels.MainViewModel;
import com.example.bluescreen.viewmodels.MainViewModelFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {
    private RecyclerView listPopularMuvies;
    private RecyclerView listTopratedMovies;
    private RecyclerView listUpcomingMovies;
    private LinearLayoutManager layoutManager;
    public MoviePageListAdapter adapterPopular;
    public MoviePageListAdapter adapterToprated;
    public MoviePageListAdapter adapterUpcoming;
    private MainViewModel viewModel;
    private String location = "home";
    private View root;
   private ViewPager pager;
    //private HomeViewModel homeViewModel;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        viewModel = ViewModelProviders.of(this,new MainViewModelFactory(location)).get(MainViewModel.class);

        initViews();


        viewModel.getListLiveDataPopularMovies().observe(getViewLifecycleOwner(), new Observer<PagedList<Result>>() {
             @Override
             public void onChanged(PagedList<Result> results) {
                 if(results != null){
                     adapterPopular.submitList(results);

                     if(results.size() > 0) {
                         List<Result> moviesToSlide = new ArrayList<>();

                         for (int i = 0; i < 5; i++) {
                             moviesToSlide.add(results.get(i));
                         }
                         SlidePagerAdapter pagerAdapter = new SlidePagerAdapter(getContext(), moviesToSlide);
                         pager.setAdapter(pagerAdapter);
                     }
                 }
             }
         });

        viewModel.getListLiveDataTopratedMovies().observe(getViewLifecycleOwner(), new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                if(results != null){
                    adapterToprated.submitList(results);
                }
            }
        });
        viewModel.getListLiveDataUpcomingMovies().observe(getViewLifecycleOwner(), new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                if(results != null){
                    adapterUpcoming.submitList(results);
                }
            }
        });

        return root;
    }

    private void initViews(){

        pager = (ViewPager)root.findViewById(R.id.slidePager);

        listPopularMuvies=(RecyclerView)root.findViewById(R.id.listPopularMuvies);
        listTopratedMovies=(RecyclerView)root.findViewById(R.id.listTopratedMovies);
        listUpcomingMovies=(RecyclerView)root.findViewById(R.id.listUpcomingMuvies);


        adapterPopular = new MoviePageListAdapter(false);
        adapterToprated = new MoviePageListAdapter(false);
        adapterUpcoming = new MoviePageListAdapter(false);


        listPopularMuvies.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));
        listTopratedMovies.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));
        listUpcomingMovies.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));

        listPopularMuvies.setHasFixedSize(true);
        listTopratedMovies.setHasFixedSize(true);
        listUpcomingMovies.setHasFixedSize(true);

        listPopularMuvies.setAdapter(adapterPopular);
        listTopratedMovies.setAdapter(adapterToprated);
        listUpcomingMovies.setAdapter(adapterUpcoming);
    }
}
