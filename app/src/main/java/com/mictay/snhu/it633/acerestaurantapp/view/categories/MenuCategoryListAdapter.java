package com.mictay.snhu.it633.acerestaurantapp.view.categories;

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
import com.mictay.snhu.it633.acerestaurantapp.model.MenuCategory;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 *
 */
public class MenuCategoryListAdapter extends RecyclerView.Adapter<MenuCategoryListAdapter.MenuCategoryViewHolder> {

    private ArrayList<MenuCategory> menuCategoryList;
    private Context context;

    /*********************************************************
     * Constructor
     *
     * @param menuCategoryList
     */
    public MenuCategoryListAdapter(Context context, ArrayList<MenuCategory> menuCategoryList) {
        this.context = context;
        this.menuCategoryList = menuCategoryList;
    }

    /*********************************************************
     *
     * @param menuCategoryList
     */
    public void updateList(List<MenuCategory> menuCategoryList) {
        Log.d("app", "updateList called");

        if (menuCategoryList == null)
            menuCategoryList = new ArrayList<MenuCategory>();

        this.menuCategoryList.addAll(menuCategoryList);
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
    public MenuCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("app", "onCreateViewHolder called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_category, parent, false);
        return new MenuCategoryViewHolder(view);
    }

    /*********************************************************
     * Bind the data to the view
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MenuCategoryViewHolder holder, int position) {
        Log.d("app", "onBindViewHolder called position=" + position);

        ImageView image = holder.itemView.findViewById(R.id.menu_category_image_view);

        TextView name = holder.itemView.findViewById(R.id.menu_category_text_view_name);
        name.setText(menuCategoryList.get(position).categoryName);

        TextView description = holder.itemView.findViewById(R.id.menu_category_text_view_description);
        description.setText(menuCategoryList.get(position).categoryDescription);
    }

    /*********************************************************
     * Simple method to return the size of the array.
     *
     * @return
     */
    @Override
    public int getItemCount() {
        Log.d("app", "getItemCount called size=" + menuCategoryList.size() );
        return menuCategoryList.size();
    }

    /*********************************************************
     *
     */
    class MenuCategoryViewHolder extends RecyclerView.ViewHolder {

        public View itemView;

        public MenuCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("app", "MenuCategoryViewHolder called with " + itemView.toString());
            this.itemView = itemView;
        }

    }

}
