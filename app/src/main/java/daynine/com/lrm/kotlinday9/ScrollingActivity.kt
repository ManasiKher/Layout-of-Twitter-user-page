package daynine.com.lrm.kotlinday9

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*

class ScrollingActivity : AppCompatActivity() {

    private lateinit var pagerAdapter:PagerAdapter
    private lateinit var viewPager : ViewPager
    private lateinit var tabLayout: LinearLayout
    private lateinit var rlContents: RelativeLayout
    private lateinit var fab : FloatingActionButton
    private var tabOne: Button? = null
    private var tabTwo: Button? = null
    private var tabThree: Button? = null
    private var selectedTab = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        viewPager=findViewById(R.id.vp_content)
        tabLayout=findViewById(R.id.tb_pager)
        rlContents=findViewById(R.id.rl_contents)
        fab= findViewById(R.id.fab)
        setData()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        tabOne?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {

                vp_content.setCurrentItem(0)
                pagerAdapter.notifyDataSetChanged()
            }
        })
        tabTwo?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {

                vp_content.setCurrentItem(1)
                pagerAdapter.notifyDataSetChanged()
            }
        })
        tabThree?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {

                vp_content.setCurrentItem(2)
                pagerAdapter.notifyDataSetChanged()
            }
        })

        vp_content.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                    }

                    override fun onPageSelected(position: Int) {
                        selectedTab = position
                        setTabBackground(position)
                    }

                    override fun onPageScrollStateChanged(state: Int) {

                    }
                })



        app_bar.addOnOffsetChangedListener(object :AppBarLayout.OnOffsetChangedListener{
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
               Log.d("Offset : ","$verticalOffset")

                if(verticalOffset>-10)
                {
                   reverseAnimate(1.0f)
                }
                else if(verticalOffset<-166&& verticalOffset>-188)
                {
                    reverseAnimate(0.5f)
                }
                else if(verticalOffset<-200&& verticalOffset>-280)
                {
                    reverseAnimate(0.4f)
                    fab.visibility=View.GONE
                    tv_title.visibility=View.GONE

                }
                else if(verticalOffset<-280&& verticalOffset>-300)
                {
                    reverseAnimate(0.0f)
                    fab.visibility=View.GONE
                    tv_details.visibility=View.GONE
                    fab2.visibility=View.GONE
                    tv_followers.visibility=View.GONE
                    tv_likes.visibility=View.GONE
                }
                else if(verticalOffset>-330&&verticalOffset<-300)
                {
                   fab2.visibility= View.GONE
                    tv_details.visibility=View.GONE

                }

                else if(verticalOffset<-330)
                {
                    fab2.visibility= View.GONE
                    animationState()
                }

            }
        })

    }

    fun reverseAnimate(scale: Float)
    {
        animator(fab,scale)
        fab.visibility=View.VISIBLE
        fab2.visibility=View.GONE
        tv_title.visibility=View.VISIBLE
        tv_details.visibility=View.VISIBLE
        tv_followers.visibility=View.VISIBLE
        tv_likes.visibility=View.VISIBLE
        initToolbar("")
    }


    fun animationState()
    {
        fab.visibility=View.GONE
        tv_title.visibility=View.GONE
        tv_details.visibility=View.GONE
        tv_followers.visibility=View.GONE
        tv_likes.visibility=View.GONE
        initToolbar("GitHub")
    }


    fun animator(view: View,scale:Float ) {
        val scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", scale,1.0f)
        val scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", scale,1.0f)
        //scaleDownX.duration = 120
        //scaleDownY.duration = 120

        val scaleDown = AnimatorSet()
        scaleDown.play(scaleDownX).with(scaleDownY)

        scaleDownX.addUpdateListener {
            val p = view.parent as View
            p.invalidate()
        }
        scaleDown.start()
        scaleDown.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {

            }

            override fun onAnimationEnd(animator: Animator) {
                /*view.setBackgroundResource(0)
                view.setBackgroundResource(R.color.white)
                val scaleUpX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f)
                val scaleUpY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f)
                scaleUpX.duration = 0
                scaleUpY.duration = 0
                val scaleUp = AnimatorSet()
                scaleUp.play(scaleUpX).with(scaleUpY)
                scaleUp.start()*/
            }

            override fun onAnimationCancel(animator: Animator) {

            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })
    }

    fun setTabBackground(tabNo: Int) {
        when (tabNo)
        {
            0 -> {
                tabOne?.setBackgroundResource(R.drawable.tab_selected)
                tabTwo?.setBackgroundResource(R.drawable.tab_unselected)
                tabThree?.setBackgroundResource(R.drawable.tab_unselected)
            }
            1 -> {
                tabOne?.setBackgroundResource(R.drawable.tab_unselected)
                tabTwo?.setBackgroundResource(R.drawable.tab_selected)
                tabThree?.setBackgroundResource(R.drawable.tab_unselected)
            }
            2 -> {
                tabOne?.setBackgroundResource(R.drawable.tab_unselected)
                tabTwo?.setBackgroundResource(R.drawable.tab_unselected)
                tabThree?.setBackgroundResource(R.drawable.tab_selected)

            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings ->
                return true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun setData()
    {
        initToolbar("")
        pagerAdapter = PagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        tabOne = findViewById(R.id.btn_tab1) as Button
        tabOne?.setBackgroundResource(R.drawable.tab_selected)
        tabTwo = findViewById(R.id.btn_tab2) as Button
        tabTwo?.setBackgroundResource(R.drawable.tab_unselected)
        tabThree = findViewById(R.id.btn_tab3) as Button
        tabThree?.setBackgroundResource(R.drawable.tab_unselected)
    }

    fun initToolbar(title: String)
    {
        if (toolbar != null) {
            toolbar.setTitleTextColor(resources.getColor(R.color.white))
            // toolbar.setNavigationIcon(R.drawable.ic_yellow_back);
            toolbar.title = title
                //toolbar.setNavigationIcon(R.drawable.ic_back_icon)
                // add back arrow to toolbar
                if (supportActionBar != null) {
                    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                    supportActionBar!!.setDisplayShowHomeEnabled(true)
                }
                toolbar.setNavigationOnClickListener { onBackPressed() }
            }
            setSupportActionBar(toolbar)
        }


}
