package com.example.bluescreen.ui.popular;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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

public class PopularFragment extends Fragment {

    private RecyclerView listPopularMovies;
    private LinearLayoutManager layoutManager;
    public CategoriotMoviesListAdapter adapter;
    private MainViewModel viewModel;
    private String location = "popular";
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_popular, container, false);
        viewModel = ViewModelProviders.of(this,new MainViewModelFactory(location)).get(MainViewModel.class);

        initViews();


        viewModel.getListLiveDataPopularMovies().observe(getViewLifecycleOwner(), new Observer<PagedList<Result>>() {
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


        listPopularMovies=(RecyclerView)root.findViewById(R.id.listMovies);

        adapter = new CategoriotMoviesListAdapter(getContext());

if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        listPopularMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
else
    listPopularMovies.setLayoutManager(new GridLayoutManager(getActivity(),3));

        listPopularMovies.setHasFixedSize(true);

        listPopularMovies.setAdapter(adapter);

    }
}
