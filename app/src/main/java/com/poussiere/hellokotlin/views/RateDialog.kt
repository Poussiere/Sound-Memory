import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import com.poussiere.hellokotlin.R
import com.poussiere.hellokotlin.utils.SharedPreferencesHelper

fun AppCompatActivity.showRateDialog(prefs: SharedPreferencesHelper) {
    val dial = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))
            .setTitle(R.string.rate_title)
            .setMessage(R.string.rate_message)
            .setPositiveButton(R.string.rate_positive) { dialog, _ ->
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + this.packageName)))
                prefs.rate()
                dialog.dismiss()
            }
            .setNegativeButton(R.string.rate_later) { dialog, _ ->
                dialog.dismiss()
            }.create()
    dial.window?.attributes?.windowAnimations = R.style.DialogAnimation
    dial.show()
}
