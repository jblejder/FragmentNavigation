package com.blejder.fragmentnavigation;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public abstract class TransactionOptions {

    protected TransactionOptions() {
    }

    public abstract void applyTransactionType(FragmentManager fragmentManager,
                                              FragmentTransaction transaction,
                                              int containerViewId,
                                              Fragment fragment);

    public static TransactionOptions addToBackStack() {
        return new AddToBackStack();
    }

    public static TransactionOptions replace() {
        return new Replace();
    }


    public static class AddToBackStack extends TransactionOptions {

        @Override
        public void applyTransactionType(FragmentManager fragmentManager, FragmentTransaction transaction, int containerViewId, Fragment fragment) {
            transaction.add(containerViewId, fragment);
            transaction.addToBackStack(null);
        }
    }

    public static class Replace extends TransactionOptions {

        @Override
        public void applyTransactionType(FragmentManager fragmentManager, FragmentTransaction transaction, int containerViewId, Fragment fragment) {
            transaction.replace(containerViewId, fragment);
        }
    }
}
