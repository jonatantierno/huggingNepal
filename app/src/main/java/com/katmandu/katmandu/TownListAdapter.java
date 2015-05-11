package com.katmandu.katmandu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonatan on 5/05/15.
 */
public class TownListAdapter extends RecyclerView.Adapter<TownListAdapter.ViewHolder> implements Filterable{

    private final List<TownSummary> fullList;
    private final List<TownSummary> visibleList;
    private final TownListActivity activity;
    private final TownFilter filter;

    public TownListAdapter(TownListActivity townListActivity) {
        this.fullList = new ArrayList<>();
        this.visibleList = new ArrayList<>();

        this.activity = townListActivity;
        this.filter = new TownFilter();
    }


    public void setData(List<TownSummary> data,String constraint) {
        fullList.clear();
        fullList.addAll(data);
        filter.filter(constraint);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_view, parent, false);

        ViewHolder vh = new ViewHolder(v);

        v.setOnClickListener(activity);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final TownSummary summary= visibleList.get(position);
        viewHolder.nameTextView.setText(summary.name);

        viewHolder.synchTextView.setBackgroundResource(0);
        if (summary.isDraft){
            viewHolder.synchTextView.setBackgroundResource(R.drawable.ic_action_add_draft);
        } else if (summary.synchPending){
            viewHolder.synchTextView.setBackgroundResource(R.drawable.ic_action_refresh);
        }
        viewHolder.clickedView.setTag(new Integer(position));
        viewHolder.statusCircle.setBackgroundResource(summary.status.getIcon());
    }

    @Override
    public int getItemCount() {
        return visibleList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    public TownSummary getSummaryFromClickedElement(View v) {
        return visibleList.get(((Integer) v.getTag()).intValue());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View clickedView;
        public TextView nameTextView;
        public View synchTextView;
        public View statusCircle;

        public ViewHolder(View rootView) {
            super(rootView);

            clickedView = rootView;
            nameTextView = (TextView) rootView.findViewById(R.id.nameTextView);
            synchTextView = rootView.findViewById(R.id.synchTextView);
            statusCircle = rootView.findViewById(R.id.statusCircle);
        }
    }

    private class TownFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            visibleList.clear();

            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length()==0){
                visibleList.addAll(fullList);
            } else {
                String constraintLowerCase = constraint.toString().toLowerCase();
                for(TownSummary summary: fullList){
                    if (summary.nameLowerCase.contains(constraintLowerCase)){
                       visibleList.add(summary);
                    }
                }
            }
            results.values = visibleList;
            results.count = visibleList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notifyDataSetChanged();
        }
    }
}
