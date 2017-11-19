package com.blejder.fragmentnavigation.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.blejder.fragmentnavigation.LeafFragment;


public class FunctionalFragment extends LeafFragment {

    public static String TAG = FunctionalFragment.class.getSimpleName();

    Depth depth;

    Button newBranchButton;
    Button newLeafButton;

    TextView leafTextView;
    TextView branchTextView;

    public static FunctionalFragment newInstance(Depth depth) {
        Bundle args = new Bundle();
        args.putSerializable("depth", depth);
        FunctionalFragment fragment = new FunctionalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        depth = getDepth();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.functional_fragment, container, false);

        newLeafButton = view.findViewById(R.id.newLeaf);
        newBranchButton = view.findViewById(R.id.newBranch);

        leafTextView = view.findViewById(R.id.leafFragment);
        branchTextView = view.findViewById(R.id.branchFragment);

        newBranchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(NavigationFragment.newInstance(depth.newDepthLevel()));
            }
        });
        newLeafButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(FunctionalFragment.newInstance(depth.newLeaf()));
            }
        });

        leafTextView.setText(String.valueOf(depth.getLeafs()));
        branchTextView.setText(String.valueOf(depth.getBranchDepth()));

        return view;
    }

    private Depth getDepth() {
        return (Depth) getArguments().getSerializable("depth");
    }

    @Override
    public boolean onBackPress() {
        Log.d(TAG, "onBackPress: " + getDepth().toString());
        return super.onBackPress();
    }
}
