package com.example.bluescreen.ui.toprated;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluescreen.R;
import com.example.bluescreen.adapters.CategoriotMoviesListAdapter;
import com.example.bluescreen.models.Result;
import com.example.bluescreen.viewmodels.MainViewModel;
import com.example.bluescreen.viewmodels.MainViewModelFactory;

public class TopRatedFragment extends Fragment {

   private View root;
   private RecyclerView listTopRatedMovies;
   private MainViewModel viewModel;
   private CategoriotMoviesListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_toprated, container, false);

        viewModel = ViewModelProviders.of(this,new MainViewModelFactory("toprated")).get(MainViewModel.class);

        initViews();


        viewModel.getListLiveDataTopratedMovies().observe(getViewLifecycleOwner(), new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                if(results != null){
                    adapter.submitList(results);
                }
            }
        });
        return root;
    }

    private void initViews(){


        listTopRatedMovies=(RecyclerView)root.findViewById(R.id.listTopratedMovies);

        adapter = new CategoriotMoviesListAdapter(getContext());

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            listTopRatedMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        else
            listTopRatedMovies.setLayoutManager(new GridLayoutManager(getActivity(),3));

        listTopRatedMovies.setHasFixedSize(true);

        listTopRatedMovies.setAdapter(adapter);

    }
}
