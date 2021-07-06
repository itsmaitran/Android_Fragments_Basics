package com.lambdaschool.congressfragmentsproject.activity

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.widget.Toast
import com.lambdaschool.congressfragmentsproject.R
import com.lambdaschool.congressfragmentsproject.api.CongressDao
import com.lambdaschool.congressfragmentsproject.api.CongresspersonDetails
import com.lambdaschool.congressfragmentsproject.api.CongresspersonOverview
import com.lambdaschool.congressfragmentsproject.fragment.CongresspersonOverviewFragment
import com.lambdaschool.congressfragmentsproject.fragment.DetailsFragment
import java.util.ArrayList

class MainActivity : AppCompatActivity(),
    CongresspersonOverviewFragment.OnListFragmentInteractionListener,
    DetailsFragment.OnFragmentInteractionListener {

    companion object {
        const val CONGRESS_KEY = "CONGRESS_PERSON"
    }

    override fun onFragmentInteraction() {

    }

    override fun onListFragmentInteraction(item: CongresspersonOverview) {
        Toast.makeText(this, "Fragment Interacted ${item.id}", Toast.LENGTH_SHORT).show()

        val fragment = DetailsFragment()

        val bundle = Bundle()
        bundle.putString(CONGRESS_KEY, item.id)

        fragment.arguments = bundle
        fragment.enterTransition = Explode()
        fragment.exitTransition = Explode()

        if (getResources().getBoolean(R.bool.is_tablet)) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.secondary_fragment_holder, fragment)
                .addToBackStack(null)
                .commit()
        } else {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get an overview list for all members of congress
        val allMembers: ArrayList<CongresspersonOverview> = CongressDao.allMembers

        // get details for a single member of congress
        val singleMemberDetails: CongresspersonDetails? = allMembers[0].id?.let { CongressDao.getMemberDetails(it) }

        // get congressperson portrait
        val image: Bitmap? = allMembers[0].id?.let { CongressDao.getImage(it) }

        val fragment = CongresspersonOverviewFragment()
        //fragment.enterTransition = Explode()
        //fragment.exitTransition = Explode()

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_holder, fragment)
            .commit()
    }
}
