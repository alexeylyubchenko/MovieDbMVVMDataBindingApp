package alexey.moviedbapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import alexey.moviedbapp.data.viewmodels.items.ItemRequestHistoryViewModel;
import alexey.moviedbapp.databinding.ItemHistoryBinding;

/**
 * Created by Alexey Lyubchenko.
 */

public class RequestHistoryAdapter extends RecyclerView.Adapter<RequestHistoryAdapter.RequestHistoryViewHolder>{


    List<String> queries;

    public RequestHistoryAdapter() {
        queries = new ArrayList<>();
    }

    public void setQueries(List<String> queries) {
        this.queries = queries;
        notifyDataSetChanged();
    }

    public void clear() {
        queries.clear();
        notifyDataSetChanged();
    }

    public String[] getArrayOfQueries() {
        return queries.toArray(new String[queries.size()]);
    }

    @Override
    public RequestHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemHistoryBinding itemHistoryBinding =
                ItemHistoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new RequestHistoryViewHolder(itemHistoryBinding);
    }

    @Override
    public void onBindViewHolder(RequestHistoryViewHolder holder, int position) {
        if(queries != null && queries.size() > position) {
            holder.bindQuery(queries.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return queries.size();
    }

    class RequestHistoryViewHolder extends RecyclerView.ViewHolder {

        ItemHistoryBinding mItemHistoryBinding;

        public RequestHistoryViewHolder(ItemHistoryBinding mItemHistoryBinding) {
            super(mItemHistoryBinding.getRoot());
            this.mItemHistoryBinding = mItemHistoryBinding;
        }

        public void bindQuery(String query) {
            if(mItemHistoryBinding.getItemQuery() == null) {
                mItemHistoryBinding.setItemQuery(new ItemRequestHistoryViewModel(query));
            } else {
                mItemHistoryBinding.getItemQuery().historyQ.set(query);
            }
        }

    }
}
