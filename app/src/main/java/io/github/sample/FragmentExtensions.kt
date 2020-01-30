package io.github.sample

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*

fun FragmentActivity.changeFragment(fragment: Fragment, cleanStack: Boolean = false) {
    if (cleanStack) {
        clearBackStack()
    }

    with(supportFragmentManager.beginTransaction()) {
        setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
        replace(fragment_container.id, fragment)
        addToBackStack(null)
        commit()
    }

}

private fun FragmentActivity.clearBackStack() {
    with(supportFragmentManager) {
        if (backStackEntryCount > 0) {
            val first = getBackStackEntryAt(0)
            popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}