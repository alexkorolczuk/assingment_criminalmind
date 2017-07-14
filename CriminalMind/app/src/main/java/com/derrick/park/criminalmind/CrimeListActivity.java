package com.derrick.park.criminalmind;

import android.support.v4.app.Fragment;

/**
 * Created by park on 2017-06-01.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
