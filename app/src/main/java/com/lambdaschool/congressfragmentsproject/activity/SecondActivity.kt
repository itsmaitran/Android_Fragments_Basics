package com.lambdaschool.congressfragmentsproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import com.lambdaschool.congressfragmentsproject.R
import com.lambdaschool.congressfragmentsproject.api.CongresspersonOverview
import com.lambdaschool.congressfragmentsproject.fragment.CongresspersonOverviewFragment
import com.lambdaschool.congressfragmentsproject.fragment.DetailsFragment

class SecondActivity : AppCompatActivity(),
    CongresspersonOverviewFragment.OnListFragmentInteractionListener,
    DetailsFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction() {

    }

    override fun onListFragmentInteraction(person: CongresspersonOverview) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val fragment = DetailsFragment()
        val extras = intent.extras
        val person = extras!!.getString(MainActivity.CONGRESS_KEY)

        val bundle = Bundle()
        bundle.putString(MainActivity.CONGRESS_KEY, person)

        fragment.arguments = bundle
        fragment.enterTransition = Explode()
        fragment.exitTransition = Explode()

        supportFragmentManager.beginTransaction()
            .replace(R.id.second_fragment_holder, fragment)
            .addToBackStack(null)
            .commit()
    }
}
