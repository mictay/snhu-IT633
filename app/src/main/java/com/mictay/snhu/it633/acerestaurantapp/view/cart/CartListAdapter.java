package com.mictay.snhu.it633.acerestaurantapp.view.cart;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mictay.snhu.it633.acerestaurantapp.R;
import com.mictay.snhu.it633.acerestaurantapp.model.CartItem;
import com.mictay.snhu.it633.acerestaurantapp.model.MenuCategory;
import com.mictay.snhu.it633.acerestaurantapp.model.MenuItem;
import com.mictay.snhu.it633.acerestaurantapp.util.AceUtils;
import com.mictay.snhu.it633.acerestaurantapp.view.home.HomeActivity;
import com.mictay.snhu.it633.acerestaurantapp.viewmodel.CartListViewModel;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 *
 */
public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartListViewHolder> {

    private ArrayList<CartItem> cartList;
    private Context context;

    /*********************************************************
     * Constructor
     *
     * @param cartList
     */
    public CartListAdapter(Context context, ArrayList<CartItem> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    /*********************************************************
     *
     * @param cartList
     */
    public void updateList(List<CartItem> cartList) {
        Log.d("app", "updateList called");
        this.cartList = new ArrayList<>();
        this.cartList.addAll(cartList);
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
    public CartListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("app", "onCreateViewHolder called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartListViewHolder(view);
    }

    /*********************************************************
     * Bind the data to the view
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull CartListViewHolder holder, int position) {
        Log.d("app", "Cart onBindViewHolder called position=" + position);

        ImageView image = holder.itemView.findViewById(R.id.cart_image_view);

        if (cartList.get(position).imageUrl != null && cartList.get(position).imageUrl.length() > 0) {
            image.setImageResource( Integer.valueOf(cartList.get(position).imageUrl));
        } else {
            image.setImageResource( R.drawable.ic_category );
        }

        TextView name = holder.itemView.findViewById(R.id.cart_text_view_name);
        name.setText(cartList.get(position).itemName);

        TextView description = holder.itemView.findViewById(R.id.cart_text_view_description);
        description.setText(AceUtils.formatCurrency(cartList.get(position).itemPrice) + " x" + cartList.get(position).quantity);

        // Support the edit Button
        Button editButton = holder.itemView.findViewById(R.id.cart_item_edit_button);
        editButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("menuItemId", cartList.get(position).itemId);
            bundle.putInt("quantity", cartList.get(position).quantity);

            Navigation.findNavController(holder.itemView)
                    .navigate(R.id.action_cartFragment_to_menuItemDetailFragment, bundle);

        });

        // Support the remove Button
        Button removeButton = holder.itemView.findViewById(R.id.cart_item_remove_button);
        removeButton.setOnClickListener(v -> {
            CartData.removeCartItem(cartList.get(position).itemId);

            // Does this work?
            HomeActivity activity = (HomeActivity)(holder.itemView.getContext());
            activity.updateCartTotal();

            // TODO:  Couldn't figure how to trigger the observable from Adapter
            // moving on
            Navigation.findNavController(holder.itemView)
                    .navigate(R.id.action_cartFragment_self, null);

        });

    }

    /*********************************************************
     * Simple method to return the size of the array.
     *
     * @return
     */
    @Override
    public int getItemCount() {
        Log.d("app", "getItemCount called size=" + cartList.size() );
        return cartList.size();
    }

    /*********************************************************
     *
     */
    class CartListViewHolder extends RecyclerView.ViewHolder {

        public View itemView;

        public CartListViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("app", "CartViewHolder called");
            this.itemView = itemView;
        }

    }

}
