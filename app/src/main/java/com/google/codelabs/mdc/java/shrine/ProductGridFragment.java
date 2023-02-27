package com.google.codelabs.mdc.java.shrine;

import android.os.Build;
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
import com.google.codelabs.mdc.java.shrine.staggeredgridlayout.StaggeredProductCardRecyclerViewAdapter;

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

        //기본 카드
        //normalGrid();

        //조금더 발전된 카드 모양
        staggeredGrid();

        //background shape 는 android marshmallow 이상에서만 설정 가능하다
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //background resource 를 set
            mbinding.productGrid.setBackgroundResource(R.drawable.shr_product_grid_background_shape);
        }
        return view;
    }

    private void staggeredGrid() {
        cardRecyclerView = mbinding.recyclerView;
        cardRecyclerView.setHasFixedSize(true);

        //grid 설정
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,RecyclerView.HORIZONTAL,false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 2? 2: 1;
            }
        });
        cardRecyclerView.setLayoutManager(gridLayoutManager);

        StaggeredProductCardRecyclerViewAdapter adapter = new StaggeredProductCardRecyclerViewAdapter(
                ProductEntry.initProductEntryList(getResources()));

        cardRecyclerView.setAdapter(adapter);

        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_large);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_small);

        cardRecyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding,smallPadding));
    }

    private void normalGrid() {
        //in onCreateView(), add the RecyclerView initialization code into ProductGridFragment.java
        // after you call setUpToolbar(view) and before the return statement
        cardRecyclerView = mbinding.recyclerView;
        cardRecyclerView.setHasFixedSize(true);
        cardRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL,false));

        //어댑터를 통해 등록할 리스트 연결
        //이때 initProductEntryList에 의해서 이미지등이 처리된다
        ProductCardRecyclerViewAdapter adapter = new ProductCardRecyclerViewAdapter(
                ProductEntry.initProductEntryList(getResources()));

        //실질적으로 보이는 이유
        cardRecyclerView.setAdapter(adapter);

        //꾸미는 규칙
        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        cardRecyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));

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
