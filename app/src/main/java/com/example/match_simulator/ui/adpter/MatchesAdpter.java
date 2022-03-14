package com.example.match_simulator.ui.adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.match_simulator.domain.Match;
import com.example.match_simulator.databinding.MatchItemBinding;
import com.example.match_simulator.ui.DetailActivity;

import java.util.List;

public class MatchesAdpter extends RecyclerView.Adapter<MatchesAdpter.ViewHolder> {

    private List<Match> matches;

    public MatchesAdpter(List<Match> matches) {
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MatchItemBinding binding = MatchItemBinding.inflate(    layoutInflater, parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context =holder.itemView.getContext();
        Match match = matches.get(position);

        Glide.with(context).load(match.getHomeTeam().getImage()).into(holder.binding.ivHomeTeam);
        holder.binding.tvHomeTeamName.setText(match.getHomeTeam().getName());
        if (match.getHomeTeam().getScore() != null){
            holder.binding.tvHomeTeamScore.setText(String.valueOf(match.getHomeTeam().getScore()));
        }

        Glide.with(context).load(match.getAwayTeam().getImage()).into(holder.binding.ivAwayTeam);
        holder.binding.tvAwayteamName.setText(match.getHomeTeam().getName());
        if (match.getHomeTeam().getScore() != null){
            holder.binding.tvAwayTeamScore.setText(String.valueOf(match.getAwayTeam().getScore()));
        }

        holder.itemView.setOnClickListener(view ->{
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.Extras.MATCH, match);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final MatchItemBinding binding;

        public ViewHolder(MatchItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
