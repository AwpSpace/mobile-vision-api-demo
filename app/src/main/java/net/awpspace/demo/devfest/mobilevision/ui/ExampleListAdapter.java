package net.awpspace.demo.devfest.mobilevision.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.awpspace.demo.devfest.mobilevision.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luceefer on 11/25/16.
 * AwpSpace
 * dev.awpspace@gmail.com
 */

public class ExampleListAdapter extends RecyclerView.Adapter<ExampleListAdapter.ExampleHolder> {

    interface ExampleHandler {

        void viewExample(int index);
    }

    private ExampleHandler handler;

    ExampleListAdapter(ExampleHandler handler) {
        this.handler = handler;
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public ExampleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_list_item, parent, false);
        return new ExampleHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleHolder holder, int position) {
        switch (position) {
            case 0:
                holder.exampleName.setText("Face Detection");
                holder.exampleBackground.setImageResource(R.drawable.example_face);
                break;
            case 1:
                holder.exampleName.setText("Barcode Detection");
                holder.exampleBackground.setImageResource(R.drawable.example_barcode);
                break;
            case 2:
                holder.exampleName.setText("Text Recognition");
                holder.exampleBackground.setImageResource(R.drawable.example_text);
                break;
            case 3:
                holder.exampleName.setText("Multi Detectors");
                holder.exampleBackground.setImageResource(R.drawable.example_multi);
                break;
            case 4:
                holder.exampleName.setText("Face Detection with OpenCV");
                holder.exampleBackground.setImageResource(R.drawable.example_opencv);
                break;
        }

        holder.itemView.setOnClickListener(view -> handler.viewExample(position));
    }

    static class ExampleHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.example_item_name)
        TextView exampleName;

        @BindView(R.id.example_item_background)
        ImageView exampleBackground;

        ExampleHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
