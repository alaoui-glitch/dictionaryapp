package com.example.dictionaryapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.ViewHolder> {
    private List<DictionaryItem> dictionaryItems;

    public DictionaryAdapter(List<DictionaryItem> dictionaryItems) {
        this.dictionaryItems = dictionaryItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DictionaryItem item = dictionaryItems.get(position);
        holder.textViewWord.setText(item.getWord());
        holder.textViewDefinition.setText(item.getDefinition());
    }

    @Override
    public int getItemCount() {
        return dictionaryItems.size();
    }

    public void updateData(List<DictionaryItem> newData) {
        dictionaryItems.clear();
        dictionaryItems.addAll(newData);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewWord;
        TextView textViewDefinition;

        ViewHolder(View itemView) {
            super(itemView);
            textViewWord = itemView.findViewById(R.id.textViewWord);
            textViewDefinition = itemView.findViewById(R.id.textViewDefinition);
        }
    }
}


