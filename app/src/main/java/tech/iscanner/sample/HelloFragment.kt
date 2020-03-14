package tech.iscanner.sample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_hello.*

class HelloFragment : Fragment(R.layout.fragment_hello) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        main_button.setOnClickListener {
            activity?.changeFragment(DemoFragment())
        }
    }
}
