package daynine.com.lrm.kotlinday9

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by manasi on 15/2/18.
 */
class PagerAdapter (fragmentManager: FragmentManager) :
        FragmentStatePagerAdapter(fragmentManager) {

    // METHOD 2
    override fun getItem(position: Int): Fragment {
        return PagerContentFragment()
    }

    // METHOD 3
    override fun getCount(): Int {
        return 3
    }
}