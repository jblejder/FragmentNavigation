package com.blejder.fragmentnavigation;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import static com.blejder.fragmentnavigation.utils.Preconditions.checkNotNull;
import static com.blejder.fragmentnavigation.utils.Preconditions.checkTrue;


public abstract class BranchFragment extends Fragment {

    private static final String TAG = "BranchFragment";
    private ViewGroup container;

    public abstract int getContainerId();

    @NonNull
    private ViewGroup getFragmentContainer() {
        if (container != null) {
            return container;
        }
        int containerId = getContainerId();

        checkNotNull(getView(), "Fragment hasn't got view inflated");

        View view = getView().findViewById(containerId);

        checkNotNull(view, "Container has not been found");
        checkTrue(view instanceof ViewGroup, "Container isn't ViewGroup");

        container = (ViewGroup) view;
        return container;
    }

    public void openFragment(Fragment fragment, TransactionOptions options) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        Fragment prevFragment = fm.getPrimaryNavigationFragment();
        if (prevFragment != null) {
            tr.hide(prevFragment);
        }
        options.applyTransactionType(tr, getContainerId(), fragment);
        tr.setPrimaryNavigationFragment(fragment);
        tr.commit();
    }

    public void openFragment(Fragment fragment) {
        openFragment(fragment, TransactionOptions.addToBackStack());
    }

    public boolean onBackPress() {
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.getPrimaryNavigationFragment();
        boolean handled = false;
        if (fragment instanceof OnBackPressListener) {
            handled = ((OnBackPressListener) fragment).onBackPress();
        }
//        if (!handled) {
//            if (fm.getBackStackEntryCount() > 0) {
//                fm.popBackStack();
//                handled = true;
//            }
//        }
        return handled;
    }

    @Nullable
    public <T> T firstWhoIs(Class<T> clazz) {
        if (clazz.isInstance(this)) {
            return (T) this;
        }
        Fragment parentFragment = getParentFragment();
        while (parentFragment != null) {
            if (clazz.isInstance(parentFragment)) {
                return (T) parentFragment;
            }
            parentFragment = getParentFragment();
        }
        Activity activity = getActivity();
        if (clazz.isInstance(activity)) {
            return (T) activity;
        }
        Log.w(TAG, String.format(Locale.ENGLISH, "firstWhoIs: $s not found", clazz.getName()));
        return null;
    }
}
