package appkg.kg.servicekg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import appkg.kg.servicekg.R;
import appkg.kg.servicekg.dispatcher.UrlChangeDispatcher;

/**
 * Created by Suimonkul on 04-Aug-16.
 */
public class ExpandableAdapter extends BaseExpandableListAdapter {

    private final LayoutInflater inflater;
    private String[] groups;
    private String[][] children;
    private Context context;

    ArrayList list = new ArrayList();

    String url = "http://192.168.0.105/api/v1/advert/?category__name=";
    String format_json = "&format=json";

    public ExpandableAdapter(String[] groups, String[][] children, Context context) {
        this.groups = groups;
        this.children = children;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return groups.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return children[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return children[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();

            holder.button = (Button) convertView.findViewById(R.id.lblListItem);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.button.setText(getChild(groupPosition, childPosition).toString());

        final String position_url = getChild(groupPosition, childPosition).toString();
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlChangeDispatcher.getInstance().notifyListeners(url + position_url + format_json);
            }
        });

        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_group, parent, false);

            holder = new ViewHolder();
            holder.textHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textHeader.setText(getGroup(groupPosition).toString());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private class ViewHolder {
        Button button;
        TextView textHeader;
    }
}