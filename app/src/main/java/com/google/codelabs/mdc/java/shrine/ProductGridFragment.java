package com.google.codelabs.mdc.java.shrine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.codelabs.mdc.java.shrine.databinding.ShrProductGridFragmentBinding;
import com.google.codelabs.mdc.java.shrine.network.ProductEntry;

public class ProductGridFragment extends Fragment {

    private ShrProductGridFragmentBinding mbinding = null;
    private Toolbar toolbar = null;
    private RecyclerView cardRecyclerView = null;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //OptionMenu를 가짐
        setHasOptionsMenu(true);
        mbinding = ShrProductGridFragmentBinding.inflate(inflater,container,false);
        View view = mbinding.getRoot();

        //set up the bar
        setUpToolbar(view);


        //in onCreateView(), add the RecyclerView initialization code into ProductGridFragment.java
        // after you call setUpToolbar(view) and before the return statement
        cardRecyclerView = mbinding.recyclerView;
        cardRecyclerView.setHasFixedSize(true);
        cardRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL,false));
        ProductCardRecyclerViewAdapter adapter = new ProductCardRecyclerViewAdapter(
                ProductEntry.initProductEntryList(getResources()));
        cardRecyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        cardRecyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.shr_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setUpToolbar(View view){
        //Fragment에서 activity를 호출한 후 toolbar를 ActionBar로 동적으로 설정
        if(mbinding != null) {
            toolbar = mbinding.appBar;
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if(activity != null){
                activity.setSupportActionBar(toolbar);
            }
        }
    }
}
