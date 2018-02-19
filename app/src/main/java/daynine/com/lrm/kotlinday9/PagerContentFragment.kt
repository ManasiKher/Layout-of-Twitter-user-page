package daynine.com.lrm.kotlinday9

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by manasi on 15/2/18.
 */
class PagerContentFragment: Fragment() {

    private lateinit var rvContents: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<MyAndroidAdapter.ViewHolder>
    private lateinit var arrayList : ArrayList<TwitterFeed>
    //private
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater?.inflate(R.layout.fragment_scrolling_content, container, false)
        rvContents = root?.findViewById(R.id.rv_feed_contents) as RecyclerView
        arrayList = ArrayList<TwitterFeed>()
        setUpRecyclerView()
        return root
    }

    fun setUpRecyclerView()
    {
        var x: Int
        x=4
        while (x>0) {
            arrayList.add(TwitterFeed(R.drawable.calendar, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam", "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat"))
        x--
        }
        //adding a layoutmanager
        rvContents.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager?

        adapter = MyAndroidAdapter(arrayList)
        //now adding the adapter to recyclerview
        rvContents.adapter = adapter
    }


    class MyAndroidAdapter(val timeSlotList: ArrayList<TwitterFeed>) : RecyclerView.Adapter<MyAndroidAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAndroidAdapter.ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_twitter_feed, parent, false)

            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: MyAndroidAdapter.ViewHolder, position: Int) {
            holder.bindItems(timeSlotList[position])

        }

        override fun getItemCount(): Int {
            return timeSlotList.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindItems(twitterFeed: TwitterFeed)
            {
                val ivFeedIcon = itemView.findViewById(R.id.iv_feed) as ImageView
                val ivProfileIcon = itemView.findViewById(R.id.iv_profile) as ImageView
                val tvTitle  = itemView.findViewById(R.id.tv_feed_title) as TextView
                val tvDesc  = itemView.findViewById(R.id.tv_feed_desc) as TextView
                ivFeedIcon.setBackgroundResource(twitterFeed.icon)
                ivProfileIcon.setBackgroundResource(R.drawable.profile)
                tvTitle.text = twitterFeed.title
                tvDesc.text = twitterFeed.desc

            }
        }
    }
}