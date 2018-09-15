package info.vcartera.androidexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import info.vcartera.cathway.androidBindableControls.AndroidBindableControlFactory
import info.vcartera.cathway.binder.ModelBinder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = AndroidBindableControlFactory(root)
        val binder = ModelBinder(factory)
        binder.startWatch()

        val person = PersonViewModel().apply {
            firstName = "Jora"
            lastName = "Vasili"
            isOver18 = true
        }

        printModelBtn.setOnClickListener{
            val modelContent = "First name: ${person.firstName}\n" +
                    "Last name: ${person.lastName}\n" +
                    "Is over 18: ${person.isOver18}"

            modelOutputLabel.text = modelContent
        }

        changeValueOfFirstNameDirectlyOnModelBtn.setOnClickListener{
            person.firstName = "James Hetfield"
        }

        changeValueOfIsOver18DirectlyOnModelBtn.setOnClickListener{
            person.isOver18 = true
        }
    }
}
