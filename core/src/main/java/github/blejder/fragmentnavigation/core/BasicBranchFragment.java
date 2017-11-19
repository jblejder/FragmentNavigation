package github.blejder.fragmentnavigation.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

import github.blejder.fragmentnavigation.core.utils.Preconditions;

import static github.blejder.fragmentnavigation.core.utils.Constants.COMMON_DATA;

public class BasicBranchFragment extends BranchFragment {

    public static BasicBranchFragment newInstance(Class<? extends Fragment> firstFragment) {
        Bundle args = new Bundle();
        args.putSerializable(COMMON_DATA, firstFragment);
        BasicBranchFragment fragment = new BasicBranchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.container_view, container, false);
        if (savedInstanceState == null) {
            openFragment(createStartingFragment(), TransactionOptions.replace());
        }
        return view;
    }

    @Override
    public int getContainerId() {
        return R.id.container;
    }

    protected Fragment createStartingFragment() {
        Serializable serializable = getArguments().getSerializable(COMMON_DATA);
        Object createdFragment = null;
        if (serializable instanceof Class) {
            try {
                createdFragment = ((Class) serializable).newInstance();
            } catch (java.lang.InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                throw new IllegalStateException(e);
            }
        }
        Preconditions.checkTrue(createdFragment instanceof Fragment, "created object is not Fragment");
        return ((Fragment) createdFragment);
    }
}
