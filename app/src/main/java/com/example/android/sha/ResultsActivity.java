package com.example.android.sha;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResultsActivity extends AppCompatActivity {

    DatabaseReference mReference;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Result>()
                .setQuery(mReference, Result.class)
                .setLifecycleOwner(this)
                .build();

        recyclerView.setAdapter(new ResultsAdapter(this, options));
    }
}


class ResultsAdapter extends FirebaseRecyclerAdapter<Result, ResultViewHolder>{
    Context context;

    public ResultsAdapter(Context context, @NonNull FirebaseRecyclerOptions<Result> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ResultViewHolder holder, int position, @NonNull Result thing) {
        holder.name.setText(thing.name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set things/user-fav/$uid$/$thingkey=true/false
            }
        });
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ResultViewHolder(LayoutInflater.from(context).inflate(R.layout.item_result, parent, false));
    }
}

class ResultViewHolder extends RecyclerView.ViewHolder {
    public TextView name;

    public ResultViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
    }
}

class Result {
    public String name;

    public Result(){}

    public Result(String name) {
        this.name = name;
    }
}
