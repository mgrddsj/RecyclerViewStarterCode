package com.example.recyclerviewstudentversion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<Player> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPlayers();

        recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new MyRecyclerAdapter((List<Player>)list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
            {
                final int fromPos = viewHolder.getAdapterPosition();
                final int toPos = target.getAdapterPosition();
                list.add(toPos, list.remove(fromPos));
                mAdapter.notifyItemMoved(fromPos, toPos);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
            {
                final int position = viewHolder.getAdapterPosition();
                final Player deleted = list.get(position);
                switch (direction)
                {
                    case ItemTouchHelper.LEFT:
                        list.remove(position);
                        mAdapter.notifyItemRemoved(position);
                        Snackbar.make(recyclerView, "Swiped left, item deleted. ", Snackbar.LENGTH_SHORT).
                                setAction("Undo", new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
                                        list.add(position, deleted);
                                        mAdapter.notifyItemInserted(position);
                                    }
                                }).show();
                }
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private List getPlayers()
    {
      list = new ArrayList<Player>(Arrays.asList(new Player[]{
              new Player("Lionel Messi", 32, 44224400.00, "football", R.drawable.mesi),
              new Player("David Robert Joseph Beckham", 44, 1291800.00, "football", R.drawable.beckham),
              new Player("Hanyu Yuzulu", 24, 500000, "figure skating", R.drawable.hanyu),
              new Player("Zhang Jike", 31, 500000, "Ping-pong", R.drawable.zhang),
              new Player("Doris", 16, 100000, "Basketball", R.drawable.member_placeholder_female),
              new Player("LeBron James", 34, 40000000, "Basketball", R.drawable.team_man_placeholder),
              new Player("Kevin Durant", 31, 30000000, "Basketball", R.drawable.team_man_placeholder),
              new Player("Kyrie Irving", 27, 20100000, "Basketball", R.drawable.team_man_placeholder),
              new Player("James Harden", 30, 28300000, "Basketball", R.drawable.team_man_placeholder),
              new Player("Stephen Curry", 31, 37460000, "Basketball", R.drawable.team_man_placeholder)
      }));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                mAdapter.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }
}
