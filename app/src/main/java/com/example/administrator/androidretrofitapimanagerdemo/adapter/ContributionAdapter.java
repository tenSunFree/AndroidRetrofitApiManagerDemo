package com.example.administrator.androidretrofitapimanagerdemo.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.androidretrofitapimanagerdemo.R;
import com.example.administrator.androidretrofitapimanagerdemo.activity.MainActivity;
import com.example.administrator.androidretrofitapimanagerdemo.model.Contributor;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rifatullah on 3/22/18.
 */

public class ContributionAdapter extends RecyclerView.Adapter<ContributionAdapter.ContributionViewHolder> {

    List<Contributor> contributorList;
    Context context;

    public ContributionAdapter(Context context, List<Contributor> contributorList) {
        this.context = context;
        this.contributorList = contributorList;
    }

    @Override
    public ContributionAdapter.ContributionViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.contributor_row, parent, false);

        /** 自定義 RecyclerView的Item的layout_weight */
        if (MainActivity.isOpenApplicationForTheFirstTime == true) {
            MainActivity.isOpenApplicationForTheFirstTime = false;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    MainActivity.ContributionAdapterParentMeasuredHeight = parent.getMeasuredHeight() / 6;
                    MainActivity.ContributionAdapterParentMeasuredWidth = parent.getMeasuredWidth();
                }
            }, 150);
        }
        int height = MainActivity.ContributionAdapterParentMeasuredHeight;
        int width = MainActivity.ContributionAdapterParentMeasuredWidth;
        view.setLayoutParams(new RecyclerView.LayoutParams(width, height));

        return new ContributionAdapter.ContributionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContributionAdapter.ContributionViewHolder viewHolder, final int position) {
        Contributor contributor = contributorList.get(position);
        viewHolder.textViewUserName.setText("Name: " + contributor.getLogin());
        viewHolder.textViewContributions.setText(String.format("Total contributions: %d", contributor.getContributions()));
        Picasso.get()
                .load(contributor.getAvatar_url())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(viewHolder.imageViewUser);
    }

    @Override
    public int getItemCount() {
        return contributorList.size();
    }

    public class ContributionViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUserName;
        TextView textViewContributions;
        ImageView imageViewUser;

        public ContributionViewHolder(View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.textViewUserName);
            textViewContributions = itemView.findViewById(R.id.textViewUserContribution);
            imageViewUser = itemView.findViewById(R.id.imageViewUser);
        }
    }
}
