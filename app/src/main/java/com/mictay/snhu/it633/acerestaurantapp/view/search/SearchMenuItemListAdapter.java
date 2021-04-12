package com.mictay.snhu.it633.acerestaurantapp.view.search;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mictay.snhu.it633.acerestaurantapp.R;
import com.mictay.snhu.it633.acerestaurantapp.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 *
 */
public class SearchMenuItemListAdapter extends RecyclerView.Adapter<SearchMenuItemListAdapter.SearchItemViewHolder> {

    private ArrayList<MenuItem> menuItemList;
    Context context;

    /*********************************************************
     * Constructor
     *
     * @param menuItemList
     */
    public SearchMenuItemListAdapter(Context context, ArrayList<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
        this.context = context;
    }

    /*********************************************************
     *
     * @param menuItemList
     */
    public void updateList(List<MenuItem> menuItemList) {
        this.menuItemList = new ArrayList<MenuItem>();
        this.menuItemList.addAll(menuItemList);
        notifyDataSetChanged();
    }

    /*********************************************************
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("app", "onCreateViewHolder called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new SearchItemViewHolder(view);
    }

    /*********************************************************
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, int position) {
        Log.d("app", "onBindViewHolder called position=" + position);

        // Icon
        ImageView image = holder.itemView.findViewById(R.id.menu_item_image_view);
        if (menuItemList.get(position).imageUrl != null && menuItemList.get(position).imageUrl.length() > 0) {
            image.setImageResource( Integer.valueOf(menuItemList.get(position).imageUrl));
        } else {
            image.setImageResource( R.drawable.ic_item );
        }

        // Name
        TextView name = holder.itemView.findViewById(R.id.menu_item_text_view_name);
        name.setText(menuItemList.get(position).itemName);

        // Description
        TextView description = holder.itemView.findViewById(R.id.menu_item_text_view_description);
        String text = menuItemList.get(position).itemDescription;
        description.setText(text);

        // Navigation to Item
        holder.itemView.setOnClickListener(v -> {
            MenuItem menuItem = menuItemList.get(position);

            // Can this close our Keyboard?
            InputMethodManager manager = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(holder.itemView.getWindowToken(), 0);

            Bundle bundle = new Bundle();
            bundle.putString("menuItemId", menuItem.itemId);
            Navigation.findNavController(holder.itemView)
                    .navigate(R.id.action_searchFragment_to_menuItemDetailFragment, bundle);
        });

    }

    /*********************************************************
     *
     * @return
     */
    @Override
    public int getItemCount() {
        Log.d("app", "getItemCount called size=" + menuItemList.size() );

        return menuItemList.size();
    }

    /*********************************************************
     *
     */
    class SearchItemViewHolder extends RecyclerView.ViewHolder {

        public View itemView;

        public SearchItemViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("app", "SearchItemViewHolder called");
            this.itemView = itemView;
        }
    }

}
