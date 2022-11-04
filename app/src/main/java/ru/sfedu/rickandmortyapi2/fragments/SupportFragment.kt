package ru.sfedu.rickandmortyapi2.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import ru.sfedu.rickandmortyapi2.R

class SupportFragment {
    fun startFragment(fragment: Fragment, activity: FragmentActivity?, saved: Boolean = false) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        if (saved) {
            transaction?.replace(R.id.mainFragmentContainerView, fragment)
            transaction?.addToBackStack(null)
        } else {
            transaction?.replace(R.id.mainFragmentContainerView, fragment)
        }
        transaction?.commit()
    }
}