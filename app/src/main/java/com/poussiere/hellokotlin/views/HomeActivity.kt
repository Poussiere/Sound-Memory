/*Copyright (C) <2018>  <Nicolas BOUTIN>
        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.
        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.
        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <http://www.gnu.org/licenses/
        contact us : von.linnasta@gmail.com
*/

package com.poussiere.hellokotlin.views

import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import com.poussiere.hellokotlin.R
import com.poussiere.hellokotlin.databinding.ActivityMainBinding
import com.poussiere.hellokotlin.utils.SharedPreferencesHelper
import com.poussiere.hellokotlin.utils.setFullScreen
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import showRateDialog

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()
    private val prefs: SharedPreferencesHelper by inject()
    private val disposables = CompositeDisposable()
    private lateinit var infoDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        instanciateInfoDialog()

        //Set databinding
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        binding.viewModel = homeViewModel

        val shouldShowRatePopup = prefs.launchCount() > 2 && !prefs.hasRated()
        if (shouldShowRatePopup) {
            showRateDialog(prefs)
        }

        /**
         * Start the game when user clicks on the main view of the home activity
         */
        disposables.add(homeViewModel.onMainViewClick.subscribe { clicked ->
            if (clicked) {
                val intent = Intent(this@HomeActivity, GameBoardActivity::class.java)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@HomeActivity,
                    binding.mainTextView,
                    ViewCompat.getTransitionName(binding.mainTextView)!!
                )
                prefs.incrementLaunchCount()
                startActivity(intent, options.toBundle())
            }
        })

        /**
         * Display the privacy policy
         */
        disposables.add(homeViewModel.onInfoClick.subscribe { clicked ->
            if (clicked) {
                with(infoDialog) {
                    if (!isShowing) {
                        show()
                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        //Make app full screen
        setFullScreen()
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    private fun instanciateInfoDialog() {
        infoDialog = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))
            .setTitle(R.string.info_title)
            .setMessage(R.string.info_content)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }.create()
            .apply {
                window?.attributes?.windowAnimations = R.style.DialogAnimation
            }
    }
}
