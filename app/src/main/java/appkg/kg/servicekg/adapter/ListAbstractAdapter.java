package appkg.kg.servicekg.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import appkg.kg.servicekg.R;
import appkg.kg.servicekg.activity.DetailActivity;
import appkg.kg.servicekg.model.Info;

/**
 * Created by Suimonkul on 26.06.2016.
 */
public class ListAbstractAdapter extends RecyclerView.Adapter<ListAbstractAdapter.PostHolder> {

    ArrayList<Info> list;
    Activity activity;
    String cat;
    private Context context;

    public ListAbstractAdapter(ArrayList list) {
        this.list = list;
    }

    @Override
    public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_list, parent, false);
        PostHolder ph = new PostHolder(view);
        Log.d("TAG", "" + context);
        context = parent.getContext();
        activity = (Activity) context;
        Intent intent = activity.getIntent();
        cat = intent.getStringExtra("category");
        return ph;
    }

    @Override
    public void onBindViewHolder(final PostHolder holder, int position) {

        if (position < list.size()) {

            final Info info = list.get(position);

            holder.tvTitle.setText(info.getTitle());
            holder.tvDescription.setText(info.getDescription());
            holder.tvOrder.setText("Цена : " + info.getOrder() + " сом");
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("info", info);
                    context.startActivity(intent);
                    activity.overridePendingTransition(R.anim.activity_down_up_enter, R.anim.activity_down_up_exit);

                }
            });
        } else {
            holder.cardView.setVisibility(View.GONE);
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

        CardView cardView;
        TextView tvTitle, tvDescription, tvOrder;

        public PostHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvOrder = (TextView) itemView.findViewById(R.id.tvOrder);
        }
    }


}
