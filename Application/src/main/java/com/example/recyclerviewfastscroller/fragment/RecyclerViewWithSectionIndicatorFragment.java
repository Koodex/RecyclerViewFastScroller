package com.example.recyclerviewfastscroller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.recyclerview.R;
import com.example.recyclerviewfastscroller.data.ColorDataSet;
import com.example.recyclerviewfastscroller.recyclerview.ColorfulAdapter;
import com.example.recyclerviewfastscroller.ui.scroller.sectionindicator.title.SectionTitleIndicator;
import com.example.recyclerviewfastscroller.ui.scroller.vertical.VerticalRecyclerViewFastScroller;

/**
 * Adapted from sample code that demonstrates the use of {@link RecyclerView} with a {@link LinearLayoutManager}
 */
public class RecyclerViewWithSectionIndicatorFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.recycler_view_with_fast_scroller_section_title_indicator_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        RecyclerView.Adapter adapter = new ColorfulAdapter(new ColorDataSet());
        recyclerView.setAdapter(adapter);

        VerticalRecyclerViewFastScroller fastScroller =
                (VerticalRecyclerViewFastScroller) rootView.findViewById(R.id.fast_scroller);
        fastScroller.setRecyclerView(recyclerView);

        recyclerView.setOnScrollListener(fastScroller.getOnScrollListener());

        SectionTitleIndicator sectionTitleIndicator =
                (SectionTitleIndicator) rootView.findViewById(R.id.fast_scroller_section_title_popup);
        fastScroller.setSectionIndicator(sectionTitleIndicator);

        setRecyclerViewLayoutManager(recyclerView);

        return rootView;
    }

    /**
     * Set RecyclerView's LayoutManager
     */
    public void setRecyclerViewLayoutManager(RecyclerView recyclerView) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (recyclerView.getLayoutManager() != null) {
            scrollPosition =
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.scrollToPosition(scrollPosition);
    }

}
