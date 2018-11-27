package cs518.sample.activityLifecycle

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

/*
 * In Activity2.java we use the instanceStateBundle to maintain the counter information
 * In MyActivityLifeCycleActivity.java we do not save any state.
 * note the run time differences, when/is the counter reset ?
 *
 */
class Activity2 : Activity() {
    protected var counter = 0

    /** Called when the activity is first created.  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() ")
        // using the same layout as in the main activity
        setContentView(R.layout.main)
        // change the message
        (findViewById(R.id.message) as TextView).setText(R.string.messageactivity2)
        // don't use these buttons on 2nd activity
        var button = findViewById(R.id.activityButton) as Button
        button.visibility = View.GONE
        button = findViewById(R.id.dialogueButton) as Button
        button.visibility = View.GONE

        // restore savedInstanceState here or in onRestoreInstanceState(Bundle)
        /*
		if (savedInstanceState != null) {
			counter = savedInstanceState.getInt("counter");
		}
		*/

        val killButton = findViewById(R.id.killButton) as Button
        killButton.setOnClickListener {
            Log.d(TAG, "finish()")
            finish()
        }

        val countButton = findViewById(R.id.countButton) as Button
        // don't use the button in the
        countButton.setOnClickListener {
            Log.d(TAG, "counting")
            counter++
            updateCounter()
        }

    } // onCreate()

    fun rotateScreen(view: View) {
        if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        else
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    } // rotateScreen()

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart()")
    }

    // State methods
    // we don't need to keep the state of EditText etc if we use them,
    // all Views with an id are saved by the superclass in
    // the instance bundle automatically by Android
    // if called it will be run before onStop()
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState()")
        outState.putInt("counter", counter)
    }

    // only called if activity killed
    // restore savedInstanceState here or in onCreate(Bundle)
    override fun onRestoreInstanceState(inState: Bundle) {
        super.onRestoreInstanceState(inState)
        Log.d(TAG, "onRestoreInstanceState()")
        // restore savedInstanceState here or in onCreate(Bundle)
        counter = inState.getInt("counter")
        updateCounter()
    }

    private fun updateCounter() {
        val textView = findViewById(R.id.counterValue) as TextView
        textView.text = "Actual counter: $counter"
    }

    companion object {

        val TAG = "LIFECYC2"
    }

}
