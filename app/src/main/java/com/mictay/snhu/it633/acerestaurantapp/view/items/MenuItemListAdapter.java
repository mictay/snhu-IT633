package com.mictay.snhu.it633.acerestaurantapp.view.items;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mictay.snhu.it633.acerestaurantapp.R;
import com.mictay.snhu.it633.acerestaurantapp.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 *
 */
public class MenuItemListAdapter extends RecyclerView.Adapter<MenuItemListAdapter.MenuItemViewHolder> {

    private ArrayList<MenuItem> menuItemList;
    Context context;

    /*********************************************************
     * Constructor
     *
     * @param menuItemList
     */
    public MenuItemListAdapter(Context context, ArrayList<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
        this.context = context;
    }

    /*********************************************************
     *
     * @param menuItemList
     */
    public void updateList(List<MenuItem> menuItemList) {
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
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("app", "onCreateViewHolder called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new MenuItemViewHolder(view);
    }

    /*********************************************************
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        Log.d("app", "onBindViewHolder called position=" + position);
        ImageView image = holder.itemView.findViewById(R.id.menu_item_image_view);
        TextView name = holder.itemView.findViewById(R.id.menu_item_text_view_name);
        name.setText(menuItemList.get(position).itemName);
        TextView description = holder.itemView.findViewById(R.id.menu_item_text_view_description);
        description.setText(menuItemList.get(position).itemDescription);
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
    class MenuItemViewHolder extends RecyclerView.ViewHolder {

        public View itemView;

        public MenuItemViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("app", "MenuItemViewHolder called with " + itemView.toString());
            this.itemView = itemView;
        }
    }

}
