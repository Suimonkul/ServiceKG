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
import android.widget.Button;
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
    private Context context;
    Activity activity;
    String cat;

    public ListAbstractAdapter(ArrayList list) {
        this.list = list;
    }

    public static class PostHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvTitle, tvDescription, tvOrder;
        Button buttonDetail;

        public PostHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvOrder = (TextView) itemView.findViewById(R.id.tvOrder);
            buttonDetail = (Button) itemView.findViewById(R.id.btnDetail);
        }
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


        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvDescription.setText(list.get(position).getDescription());
        holder.tvOrder.setText("Цена : " + list.get(position).getOrder()+" сом");
        final int finalPosition = position;
        holder.buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("title", list.get(finalPosition).getTitle());
                intent.putExtra("desc", list.get(finalPosition).getDescription());
                intent.putExtra("phone", list.get(finalPosition).getPhone());
                intent.putExtra("phone_two", list.get(finalPosition).getPhone_two());
                intent.putExtra("phone_three", list.get(finalPosition).getPhone_three());
                intent.putExtra("order", list.get(finalPosition).getOrder());
                intent.putExtra("name", list.get(finalPosition).getName());
                intent.putExtra("category", cat);
                context.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
