package com.blejder.fragmentnavigation;


import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.Locale;

import static com.blejder.fragmentnavigation.utils.Preconditions.checkTrue;


public class LeafFragment extends Fragment implements OnBackPressListener {

    private static final String TAG = "LeafFragment";

    public void openFragment(Fragment fragment, TransactionOptions options) {
        Fragment pf = getParentFragment();
        checkTrue(pf instanceof BranchFragment, "Parent fragment is not BranchFragment");
        ((BranchFragment) pf).openFragment(fragment, options);
    }

    public void openFragment(Fragment fragment) {
        openFragment(fragment, TransactionOptions.addToBackStack());
    }

    @Override
    public boolean onBackPress() {
        return false;
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
            parentFragment = parentFragment.getParentFragment();
        }
        Activity activity = getActivity();
        if (clazz.isInstance(activity)) {
            return (T) activity;
        }
        Log.w(TAG, String.format(Locale.ENGLISH, "firstWhoIs: $s not found", clazz.getName()));
        return null;
    }
}
