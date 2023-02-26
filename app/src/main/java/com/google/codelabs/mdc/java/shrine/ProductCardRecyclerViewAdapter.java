package com.google.codelabs.mdc.java.shrine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.codelabs.mdc.java.shrine.network.ImageRequester;
import com.google.codelabs.mdc.java.shrine.network.ProductEntry;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter used to show a simple grid of products.
 * The adapter class above manages the content of our grid.
 */
public class ProductCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {

    private List<ProductEntry> productList;
    private ImageRequester imageRequester;

    ProductCardRecyclerViewAdapter(List<ProductEntry> productList) {
        this.productList = productList;
        //getInstance 처리인 것을 보아하니, 싱글톤인듯
        //이미지를 요청받을 수 있는 api 정도로 생각
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    //각 카드를 어떤 모양으로 할 것인지 설정
    /**
     * RecyclerView가 항목을 나타내기 위해 지정된 유형의 새로운 RecyclerView.ViewHolder가 필요할 때 호출됩니다.
     * 이 새로운 ViewHolder는 주어진 유형의 항목을 나타낼 수 있는 새로운 View로 구성되어야 합니다.
     * 새 보기를 수동으로 생성하거나 XML 레이아웃 파일에서 확장할 수 있습니다.
     * 새 ViewHolder는 onBindViewHolder(RecyclerView.ViewHolder, int, List)를 사용하여 어댑터의 항목을 표시하는 데 사용됩니다.
     * 데이터 세트의 다른 항목을 표시하는 데 재사용되므로
     * 불필요한 View.findViewById(int) 호출을 피하기 위해 보기의 하위 보기에 대한 참조를 캐시하는 것이 좋습니다.
     *
     * Params:
     * parent – 어댑터 위치에 바인딩된 후 새 보기가 추가될 ViewGroup입니다.
     * viewType – 새 보기의 보기 유형입니다.
     * Returns:
     * 주어진 보기 유형의 보기를 보유하는 새 ViewHolder입니다.
     * **/
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shr_product_card, parent, false);
        return new ProductCardViewHolder(layoutView);
    }

    /**
     * To determine what each view should do with its given content
     * To set the information on each view
     * **/

    /**
     * 지정된 위치에 데이터를 표시하기 위해 RecyclerView에 의해 호출됩니다.
     * 이 메서드는 RecyclerView.ViewHolder.itemView의 내용을 업데이트하여 지정된 위치의 항목을 반영해야 합니다.
     *
     * android.widget.ListView와 달리 RecyclerView는 항목 자체가 무효화되거나 새 위치를 결정할 수 없는 경우가 아니면 데이터 세트에서 항목의 위치가 변경될 때 이 메서드를 다시 호출하지 않습니다.
     * 따라서 이 메서드 내에서 관련 데이터 항목을 획득하는 동안에는 position 매개변수만 사용해야 하며 사본을 보관해서는 안 됩니다.
     *
     * 나중에 항목의 위치가 필요한 경우(예: 클릭 리스너에서) 업데이트된 어댑터 위치를 갖는 RecyclerView.ViewHolder.getAdapterPosition()을 사용하십시오.
     *
     * 어댑터가 효율적인 부분 바인드를 처리할 수 있는 경우 대신 onBindViewHolder(RecyclerView.ViewHolder, int, List)를 재정의합니다.
     *
     * 매개변수:
     * holder – 데이터 세트의 지정된 위치에 있는 항목의 내용을 나타내기 위해 업데이트해야 하는 ViewHolder입니다.
     * position – 어댑터 데이터 세트 내 항목의 위치입니다.
     * **/
    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        // TODO: Put ViewHolder binding code here in MDC-102
        if (productList != null && position < productList.size()) {
            ProductEntry product = productList.get(position);
            holder.productTitle.setText(product.title);
            holder.productPrice.setText(product.price);
            //product 의 url 에서 얻어온 url을 productImage에 set
            imageRequester.setImageFromUrl(holder.productImage, product.url);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
