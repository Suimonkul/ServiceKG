package appkg.kg.servicekg.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import appkg.kg.servicekg.R;
import appkg.kg.servicekg.activity.ADVListActivity;
import appkg.kg.servicekg.model.Category;

/**
 * Created by Suimonkul on 28.10.2016.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.PostHolder> {

    ArrayList<Category> list;
    private Context context;
    private Activity activity;

    public CategoryAdapter(ArrayList<Category> list) {
        this.list = list;
    }

    @Override
    public CategoryAdapter.PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_category, parent, false);
        CategoryAdapter.PostHolder ph = new CategoryAdapter.PostHolder(view);
        Log.d("TAG", "" + context);
        context = parent.getContext();
        activity = (Activity) context;
        return ph;
    }

    @Override
    public void onBindViewHolder(final CategoryAdapter.PostHolder holder, int position) {

        if (position < list.size()) {

            final Category category = list.get(position);


            holder.title.setText(category.getName());
            Picasso.with(context).load(category.getImage()).into(holder.image);
            holder.rlCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent url = new Intent(context, ADVListActivity.class);
                    url.putExtra("url", category.getUrl());
                    context.startActivity(url);
                    activity.overridePendingTransition(R.anim.activity_down_up_enter, R.anim.activity_down_up_exit);
                }
            });


        } else {
            holder.rlCategory.setVisibility(View.GONE);
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PostHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        RelativeLayout rlCategory;


        public PostHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.category_image);
            title = (TextView) itemView.findViewById(R.id.category_title);
            rlCategory = (RelativeLayout) itemView.findViewById(R.id.rlCategory);

        }
    }


}
