package github.blejder.fragmentnavigation.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import github.blejder.fragmentnavigation.core.BranchFragment;
import github.blejder.fragmentnavigation.core.TransactionOptions;

public class NavigationFragment extends BranchFragment {

    public static NavigationFragment newInstance(Depth depth) {
        Bundle args = new Bundle();
        args.putSerializable("depth", depth);
        NavigationFragment fragment = new NavigationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContainerId() {
        return R.id.container;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.container_view, container, false);
        ((TextView) view.findViewById(R.id.depthLevel)).setText(String.valueOf(getDepth().getBranchDepth()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            openFragment(FunctionalFragment.newInstance(getDepth()), TransactionOptions.replace());
        }
    }

    private Depth getDepth() {
        return (Depth) getArguments().getSerializable("depth");
    }
}
