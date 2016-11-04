//package appkg.kg.servicekg.adapter;
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.Button;
//import android.widget.TextView;
//
//import appkg.kg.servicekg.R;
//
///**
// * Created by Suimonkul on 04-Aug-16.
// */
//public class SelectCategoryAdapter extends BaseExpandableListAdapter {
//
//    private final LayoutInflater inflater;
//    private String[] groups;
//    private String[][] children;
//    private Context context;
//    Activity activity;
//
//
//    public SelectCategoryAdapter(String[] groups, String[][] children, Context context) {
//        this.groups = groups;
//        this.children = children;
//        this.context = context;
//        inflater = LayoutInflater.from(context);
//    }
//
//    @Override
//    public int getGroupCount() {
//        return groups.length;
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        return children[groupPosition].length;
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        return groups[groupPosition];
//    }
//
//    @Override
//    public Object getChild(int groupPosition, int childPosition) {
//        return children[groupPosition][childPosition];
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return true;
//    }
//
//    @Override
//    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//
//        ViewHolder holder;
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.list_item, parent, false);
//            holder = new ViewHolder();
//
//            holder.button = (Button) convertView.findViewById(R.id.lblListItem);
//            convertView.setTag(holder);
//
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        holder.button.setText(getChild(groupPosition, childPosition).toString());
//
//
//        return convertView;
//    }
//
//
//    @Override
//    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        ViewHolder holder;
//
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.list_group, parent, false);
//
//            holder = new ViewHolder();
//            holder.textHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        holder.textHeader.setText(getGroup(groupPosition).toString());
//
//        return convertView;
//    }
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return true;
//    }
//
//
//    private class ViewHolder {
//        Button button;
//        TextView textHeader;
//    }
//}